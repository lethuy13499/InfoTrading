package vn.vmg.infotrading.webapp.internal.sharing_partner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.sharing_partner.domain.SharingPartnerModel;
import vn.vmg.infotrading.webapp.internal.sharing_partner.validator.SharingPartnerDeleteValidator;
import vn.vmg.infotrading.webapp.internal.sharing_partner.validator.SharingPartnerInsertValidator;
import vn.vmg.infotrading.webapp.internal.sharing_partner.validator.SharingPartnerUpdateValidator;
import vn.vmg.infotrading.webapp.repository.SharingPartnerOracleRepository;
import vn.vmg.infotrading.webapp.utils.ApiResponseUtils;

@Service
public class SharingPartnerService {

    @Autowired
    private SharingPartnerOracleRepository sharingPartnerRepository;

    @Autowired
    private SharingPartnerInsertValidator sharingPartnerInsertValidator;

    @Autowired
    private SharingPartnerUpdateValidator sharingPartnerUpdateValidator;

    @Autowired
    private SharingPartnerDeleteValidator sharingPartnerDeleteValidator;

    @Transactional(rollbackFor = Exception.class)
    public RxResult insert(SharingPartnerModel sharingPartnerModel) {
        InternalResult internalResult = sharingPartnerInsertValidator.valid(sharingPartnerModel);
        if (internalResult.hasErrors()) {
            return ApiResponseUtils.buildBadRequestResponse(internalResult.getErrors());
        }

        sharingPartnerRepository.insert(sharingPartnerModel);

        return ApiResponseUtils.buildSuccessResponse("Insert sharing partner successful", null);
    }

    @Transactional(rollbackFor = Exception.class)
    public RxResult update(SharingPartnerModel sharingPartnerModel) {
        InternalResult internalResult = sharingPartnerUpdateValidator.valid(sharingPartnerModel);
        if (internalResult.hasErrors()) {
            return ApiResponseUtils.buildBadRequestResponse(internalResult.getErrors());
        }

        sharingPartnerRepository.update(sharingPartnerModel);

        return ApiResponseUtils.buildSuccessResponse("Update sharing partner successful", null);
    }

    @Transactional(rollbackFor = Exception.class)
    public RxResult delete(Long id) {
        InternalResult internalResult = sharingPartnerDeleteValidator.valid(id);
        if (internalResult.hasErrors()) {
            return ApiResponseUtils.buildBadRequestResponse(internalResult.getErrors());
        }

        sharingPartnerRepository.delete(id);

        return ApiResponseUtils.buildSuccessResponse("Delete sharing partner successful", null);
    }
}