package vn.vmg.infotrading.webapp.internal.appendix.service;

import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.appendix.domain.Appendix;
import vn.vmg.infotrading.webapp.internal.appendix.domain.AppendixModel;
import vn.vmg.infotrading.webapp.internal.appendix.repository.AppendixRepository;
import vn.vmg.infotrading.webapp.internal.appendix.validator.AppendixDeleteByContractIdValidator;
import vn.vmg.infotrading.webapp.internal.appendix.validator.AppendixDeleteValidator;
import vn.vmg.infotrading.webapp.internal.appendix.validator.AppendixInsertValidator;
import vn.vmg.infotrading.webapp.internal.appendix.validator.AppendixUpdateValidator;
import vn.vmg.infotrading.webapp.internal.appendix_file.domain.AppendixFile;
import vn.vmg.infotrading.webapp.internal.appendix_file.repository.AppendixFileRepository;
import vn.vmg.infotrading.webapp.internal.calculation_detail.domain.CalculationDetail;
import vn.vmg.infotrading.webapp.internal.price_setting.domain.PriceSetting;
import vn.vmg.infotrading.webapp.internal.price_setting_detail.domain.PriceSettingDetail;
import vn.vmg.infotrading.webapp.internal.sharing_partner.domain.SharingPartner;
import vn.vmg.infotrading.webapp.repository.CalculationDetailOracleRepository;
import vn.vmg.infotrading.webapp.repository.PriceSettingDetailOracleRepository;
import vn.vmg.infotrading.webapp.repository.PriceSettingOracleRepository;
import vn.vmg.infotrading.webapp.repository.SharingPartnerOracleRepository;
import vn.vmg.infotrading.webapp.utils.ApiResponseUtils;

import java.util.List;

@Service
public class AppendixService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppendixService.class);

    @Autowired
    private AppendixRepository appendixRepository;

    @Autowired
    private AppendixFileRepository appendixFileRepository;

    @Autowired
    private PriceSettingOracleRepository priceSettingRepository;

    @Autowired
    private PriceSettingDetailOracleRepository priceSettingDetailRepository;

    @Autowired
    private CalculationDetailOracleRepository calculationDetailRepository;

    @Autowired
    private SharingPartnerOracleRepository sharingPartnerRepository;

    @Autowired
    private AppendixInsertValidator appendixInsertValidator;

    @Autowired
    private AppendixUpdateValidator appendixUpdateValidator;

    @Autowired
    private AppendixDeleteValidator appendixDeleteValidator;

    @Autowired
    private AppendixDeleteByContractIdValidator appendixDeleteByContractIdValidator;

    public JsonObject getAll(AppendixModel appendixModel) {
        JsonObject result = new JsonObject();

        List<JsonObject> appendixes = appendixRepository.getAll(appendixModel);
        Integer recordsTotal = appendixRepository.getRecordsTotal();

        result.put("data", appendixes);
        result.put("recordsTotal", recordsTotal);
        result.put("recordsFiltered", appendixes.size());

        return result;
    }

    public RxResult getById(Long id) {
        Appendix appendix = appendixRepository.getById(id);
        if (appendix == null) {
            return ApiResponseUtils.buildFailResponse("Appendix with id: " + id + " is not exist", null);
        }

        List<AppendixFile> appendixFiles = appendixFileRepository.getAllByAppendixId(id);
        List<PriceSetting> priceSettings = priceSettingRepository.getAllByAppendixId(id);
        for (PriceSetting priceSetting : priceSettings) {
            List<PriceSettingDetail> priceSettingDetails = priceSettingDetailRepository.getAllByPriceSettingId(priceSetting.getId());
            for (PriceSettingDetail priceSettingDetail : priceSettingDetails) {
                List<CalculationDetail> calculationDetails = calculationDetailRepository.getAllByPriceSettingDetailId(priceSettingDetail.getId());
                priceSettingDetail.setCalculationDetails(calculationDetails);
            }
            priceSetting.setPriceSettingDetails(priceSettingDetails);
        }
        List<SharingPartner> sharingPartners = sharingPartnerRepository.getAllByAppendixId(id);

        JsonObject result = new JsonObject();
        result.put("appendix", appendix.toJson());
        result.put("appendixFiles", appendixFiles);
        result.put("priceSettings", priceSettings);
        result.put("sharingPartners", sharingPartners);

        return ApiResponseUtils.buildSuccessResponse("Get appendix by id successful", result);
    }

    @Transactional(rollbackFor = Exception.class)
    public RxResult insert(AppendixModel appendixModel) {
        InternalResult appendixInternalResult = appendixInsertValidator.valid(appendixModel);
        if (appendixInternalResult.hasErrors()) {
            return ApiResponseUtils.buildBadRequestResponse(appendixInternalResult.getErrors());
        }

        appendixRepository.insert(appendixModel);

        return ApiResponseUtils.buildSuccessResponse("Insert appendix successful", null);
    }

    @Transactional(rollbackFor = Exception.class)
    public RxResult update(AppendixModel appendixModel) {
        InternalResult internalResult = appendixUpdateValidator.valid(appendixModel);
        if (internalResult.hasErrors()) {
            return ApiResponseUtils.buildBadRequestResponse(internalResult.getErrors());
        }

        appendixRepository.update(appendixModel);

        return ApiResponseUtils.buildSuccessResponse("Update appendix successful", null);
    }

    @Transactional(rollbackFor = Exception.class)
    public RxResult delete(Long id) {
        InternalResult internalResult = appendixDeleteValidator.valid(id);
        if (internalResult.hasErrors()) {
            return ApiResponseUtils.buildBadRequestResponse(internalResult.getErrors());
        }

        List<PriceSetting> priceSettings = priceSettingRepository.getAllByAppendixId(id);
        for (PriceSetting priceSetting : priceSettings) {
            List<PriceSettingDetail> priceSettingDetails = priceSettingDetailRepository.getAllByPriceSettingId(priceSetting.getId());
            for (PriceSettingDetail priceSettingDetail : priceSettingDetails) {
                calculationDetailRepository.deleteByPriceSettingDetailId(priceSettingDetail.getId());
            }
            priceSettingDetailRepository.deleteByPriceSettingId(priceSetting.getId());
            priceSettingRepository.delete(priceSetting.getId());
        }
        sharingPartnerRepository.deleteByAppendixId(id);
        appendixFileRepository.deleteByAppendixId(id);
        appendixRepository.delete(id);

        return ApiResponseUtils.buildSuccessResponse("Delete appendix successful", null);
    }

    @Transactional(rollbackFor = Exception.class)
    public RxResult deleteByContractId(Long contractId) {
        InternalResult internalResult = appendixDeleteByContractIdValidator.valid(contractId);
        if (internalResult.hasErrors()) {
            return ApiResponseUtils.buildBadRequestResponse(internalResult.getErrors());
        }

        List<Appendix> appendixes = appendixRepository.getAllByContractId(contractId);
        for (Appendix appendix : appendixes) {
            List<PriceSetting> priceSettings = priceSettingRepository.getAllByAppendixId(appendix.getId());
            for (PriceSetting priceSetting : priceSettings) {
                List<PriceSettingDetail> priceSettingDetails = priceSettingDetailRepository.getAllByPriceSettingId(priceSetting.getId());
                for (PriceSettingDetail priceSettingDetail : priceSettingDetails) {
                    calculationDetailRepository.deleteByPriceSettingDetailId(priceSettingDetail.getId());
                }
                priceSettingDetailRepository.deleteByPriceSettingId(priceSetting.getId());
                priceSettingRepository.delete(priceSetting.getId());
            }
            sharingPartnerRepository.deleteByAppendixId(appendix.getId());
            appendixFileRepository.deleteByAppendixId(appendix.getId());
        }
        appendixRepository.deleteByContractId(contractId);

        return ApiResponseUtils.buildSuccessResponse("Delete appendix by contract id successful", null);
    }
}