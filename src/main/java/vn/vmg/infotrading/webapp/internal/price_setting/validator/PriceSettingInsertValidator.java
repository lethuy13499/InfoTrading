package vn.vmg.infotrading.webapp.internal.price_setting.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.common.Constants;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.internal.calculation_detail.domain.CalculationDetailModel;
import vn.vmg.infotrading.webapp.internal.price_setting.domain.PriceSettingModel;
import vn.vmg.infotrading.webapp.internal.price_setting_detail.domain.PriceSettingDetailModel;
import vn.vmg.infotrading.webapp.utils.StringUtils;

import java.sql.Timestamp;
import java.util.List;

@Component
public class PriceSettingInsertValidator implements InternalValidator {

    @Autowired
    private CommonErrorsMsg commonErrorsMsg;

    @Override
    public InternalResult valid(Object domain) {
        InternalResult result = new InternalResult(commonErrorsMsg);

        PriceSettingModel priceSettingModel = (PriceSettingModel) domain;
        Timestamp validityTime = priceSettingModel.getValidityTime();
        Long appendixId = priceSettingModel.getAppendixId();

        result.rejectIfEmptyOrWhitespace("validityTime", validityTime);
        result.rejectIfEmptyOrWhitespace("appendixId", appendixId);

        if (result.hasErrors()) {
            return result;
        }

        PriceSettingDetailModel priceSettingDetailModel = priceSettingModel.getPriceSettingDetailModel();
        Long informationGroupId = priceSettingDetailModel.getInformationGroupId();
        String informationField = priceSettingDetailModel.getInformationField();
        Integer countMethod = priceSettingDetailModel.getCountMethod();
        String requireList = priceSettingDetailModel.getRequireList();

        result.rejectIfEmptyOrWhitespace("informationGroupId", informationGroupId);
        result.rejectIfEmptyOrWhitespace("informationField", informationField);
        result.rejectIfEmptyOrWhitespace("countMethod", countMethod);

        if (!StringUtils.isNullOrEmpty(informationField)) {
            result.rejectIfInvalidMaxLength("informationField", informationField, 2000);
        }

        if (!StringUtils.isNullOrEmpty(countMethod)) {
            if (!countMethod.equals(Constants.CountMethod.ALL) &&
                    !countMethod.equals(Constants.CountMethod.ANY) &&
                    !countMethod.equals(Constants.CountMethod.REQUIRE_LIST)) {
                result.rejectInvalidValue("countMethod");
            }
        }

        if (!StringUtils.isNullOrEmpty(requireList)) {
            result.rejectIfInvalidMaxLength("requireList", requireList, 2000);
        }

        if (result.hasErrors()) {
            return result;
        }

        List<CalculationDetailModel> calculationDetailModels = priceSettingDetailModel.getCalculationDetailModels();
        for (CalculationDetailModel calculationDetailModel : calculationDetailModels) {
            Integer calculationMethod = calculationDetailModel.getCalculationMethod();
            String commitmentInputQuantityFrame = calculationDetailModel.getCommitmentInputQuantityFrame();
            String successOutputQuantityFrame = calculationDetailModel.getSuccessOutputQuantityFrame();
            Double unitPrice = calculationDetailModel.getUnitPrice();

            result.rejectIfEmptyOrWhitespace("calculationMethod", calculationMethod);
            result.rejectIfEmptyOrWhitespace("successOutputQuantityFrame", successOutputQuantityFrame);
            result.rejectIfEmptyOrWhitespace("unitPrice", unitPrice);

            if (calculationMethod != null) {
                if (!calculationMethod.equals(Constants.CalculationMethod.INPUT_AND_HITRATE) &&
                        !calculationMethod.equals(Constants.CalculationMethod.SUCCESS_OUTPUT)) {
                    result.rejectInvalidValue("calculationMethod");
                }
            }

            if (!StringUtils.isNullOrEmpty(commitmentInputQuantityFrame)) {
                result.rejectIfInvalidMaxLength("commitmentInputQuantityFrame", commitmentInputQuantityFrame, 100);
            }

            if (!StringUtils.isNullOrEmpty(successOutputQuantityFrame)) {
                result.rejectIfInvalidMaxLength("successOutputQuantityFrame", successOutputQuantityFrame, 100);
            }

            if (result.hasErrors()) {
                return result;
            }
        }

        return result;
    }
}
