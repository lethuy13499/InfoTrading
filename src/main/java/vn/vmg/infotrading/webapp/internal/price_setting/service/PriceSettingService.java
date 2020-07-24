package vn.vmg.infotrading.webapp.internal.price_setting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.calculation_detail.domain.CalculationDetailModel;
import vn.vmg.infotrading.webapp.internal.price_setting.domain.PriceSettingModel;
import vn.vmg.infotrading.webapp.internal.price_setting.validator.PriceSettingDeleteValidator;
import vn.vmg.infotrading.webapp.internal.price_setting.validator.PriceSettingInsertValidator;
import vn.vmg.infotrading.webapp.internal.price_setting.validator.PriceSettingUpdateValidator;
import vn.vmg.infotrading.webapp.internal.price_setting_detail.domain.PriceSettingDetail;
import vn.vmg.infotrading.webapp.internal.price_setting_detail.domain.PriceSettingDetailModel;
import vn.vmg.infotrading.webapp.repository.CalculationDetailOracleRepository;
import vn.vmg.infotrading.webapp.repository.PriceSettingDetailOracleRepository;
import vn.vmg.infotrading.webapp.repository.PriceSettingOracleRepository;
import vn.vmg.infotrading.webapp.utils.ApiResponseUtils;

import java.util.List;

@Service
public class PriceSettingService {

    @Autowired
    private PriceSettingOracleRepository priceSettingRepository;

    @Autowired
    private PriceSettingDetailOracleRepository priceSettingDetailRepository;

    @Autowired
    private CalculationDetailOracleRepository calculationDetailRepository;

    @Autowired
    private PriceSettingInsertValidator priceSettingInsertValidator;

    @Autowired
    private PriceSettingUpdateValidator priceSettingUpdateValidator;

    @Autowired
    private PriceSettingDeleteValidator priceSettingDeleteValidator;

    @Transactional(rollbackFor = Exception.class)
    public RxResult insert(PriceSettingModel priceSettingModel) {
        PriceSettingDetailModel priceSettingDetailModel = priceSettingModel.getPriceSettingDetailModel();
        List<CalculationDetailModel> calculationDetailModels = priceSettingDetailModel.getCalculationDetailModels();

        InternalResult internalResult = priceSettingInsertValidator.valid(priceSettingModel);
        if (internalResult.hasErrors()) {
            return ApiResponseUtils.buildBadRequestResponse(internalResult.getErrors());
        }

        Long priceSettingId = priceSettingRepository.insert(priceSettingModel);
        priceSettingDetailModel.setPriceSettingId(priceSettingId);
        Long priceSettingDetailId = priceSettingDetailRepository.insert(priceSettingDetailModel);
        for (CalculationDetailModel calculationDetailModel : calculationDetailModels) {
            calculationDetailModel.setPriceSettingDetailId(priceSettingDetailId);
            calculationDetailRepository.insert(calculationDetailModel);
        }

        return ApiResponseUtils.buildSuccessResponse("Insert price setting successful", null);
    }

    @Transactional(rollbackFor = Exception.class)
    public RxResult update(PriceSettingModel priceSettingModel) {
        PriceSettingDetailModel priceSettingDetailModel = priceSettingModel.getPriceSettingDetailModel();
        List<CalculationDetailModel> calculationDetailModels = priceSettingDetailModel.getCalculationDetailModels();

        InternalResult internalResult = priceSettingUpdateValidator.valid(priceSettingModel);
        if (internalResult.hasErrors()) {
            return ApiResponseUtils.buildBadRequestResponse(internalResult.getErrors());
        }

        priceSettingRepository.update(priceSettingModel);
        priceSettingDetailRepository.update(priceSettingDetailModel);
        for (CalculationDetailModel calculationDetailModel : calculationDetailModels) {
            calculationDetailRepository.update(calculationDetailModel);
        }

        return ApiResponseUtils.buildSuccessResponse("Update price setting successful", null);
    }

    @Transactional(rollbackFor = Exception.class)
    public RxResult delete(Long id) {
        InternalResult internalResult = priceSettingDeleteValidator.valid(id);
        if (internalResult.hasErrors()) {
            return ApiResponseUtils.buildBadRequestResponse(internalResult.getErrors());
        }

        List<PriceSettingDetail> priceSettingDetails = priceSettingDetailRepository.getAllByPriceSettingId(id);
        for (PriceSettingDetail priceSettingDetail : priceSettingDetails) {
            calculationDetailRepository.deleteByPriceSettingDetailId(priceSettingDetail.getId());
        }
        priceSettingDetailRepository.deleteByPriceSettingId(id);
        priceSettingRepository.delete(id);

        return ApiResponseUtils.buildSuccessResponse("Delete price setting successful", null);
    }
}