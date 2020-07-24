package vn.vmg.infotrading.webapp.internal.appendix.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.common.Constants;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.internal.appendix.domain.Appendix;
import vn.vmg.infotrading.webapp.internal.appendix.domain.AppendixModel;
import vn.vmg.infotrading.webapp.repository.AppendixOracleRepository;
import vn.vmg.infotrading.webapp.utils.StringUtils;

import java.sql.Timestamp;

@Component
public class AppendixUpdateValidator implements InternalValidator {

    @Autowired
    private CommonErrorsMsg commonErrorsMsg;

    @Autowired
    private AppendixOracleRepository appendixRepository;

    @Override
    public InternalResult valid(Object domain) {
        AppendixModel appendixModel = (AppendixModel) domain;
        Long id = appendixModel.getId();
        String code = appendixModel.getCode();
        Integer type = appendixModel.getType();
        String description = appendixModel.getDescription();
        Integer status = appendixModel.getStatus();
        Timestamp updateTime = appendixModel.getUpdateTime();
        Timestamp signTime = appendixModel.getSignTime();
        Timestamp validityTime = appendixModel.getValidityTime();
        Timestamp expiryTime = appendixModel.getExpiryTime();
        Long contractId = appendixModel.getContractId();
        Long partnerId = appendixModel.getPartnerId();

        InternalResult result = new InternalResult(commonErrorsMsg);
        result.rejectIfEmptyOrWhitespace("id", id);
        result.rejectIfEmptyOrWhitespace("code", code);
        result.rejectIfEmptyOrWhitespace("type", type);
        result.rejectIfEmptyOrWhitespace("status", status);
        result.rejectIfEmptyOrWhitespace("updateTime", updateTime);
        result.rejectIfEmptyOrWhitespace("signTime", signTime);
        result.rejectIfEmptyOrWhitespace("validityTime", validityTime);
        result.rejectIfEmptyOrWhitespace("expiryTime", expiryTime);
        result.rejectIfEmptyOrWhitespace("contractId", contractId);
        result.rejectIfEmptyOrWhitespace("partnerId", partnerId);

        if (!StringUtils.isNullOrEmpty(code)) {
            result.rejectIfInvalidMaxLength("code", code, 50);
        }

        if (!StringUtils.isNullOrEmpty(description)) {
            result.rejectIfInvalidMaxLength("description", description, 500);
        }

        if (type != null) {
            if (!type.equals(Constants.AppendixType.EXPERIMENT) &&
                    !type.equals(Constants.AppendixType.OFFICIAL)) {
                result.rejectInvalidValue("type");
            }
        }

        if (status != null) {
            if (!status.equals(Constants.AppendixStatus.PENDING) &&
                    !status.equals(Constants.AppendixStatus.APPROVED) &&
                    !status.equals(Constants.AppendixStatus.DECLINE) &&
                    !status.equals(Constants.AppendixStatus.CANCEL)) {
                result.rejectInvalidValue("status");
            }
        }

        Appendix oldAppendix = appendixRepository.getById(id);
        if (!oldAppendix.getCode().equals(code)) {
            if (appendixRepository.isCodeExist(code)) {
                result.rejectDuplicateValue("code");
            }
        }

        if (!oldAppendix.getUpdateTime().equals(updateTime)) {
            result.rejectIfRecordChange("update");
        }

        return result;
    }
}
