package vn.vmg.infotrading.webapp.internal.calculation_detail.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.internal.appendix_file.domain.AppendixFileModel;
import vn.vmg.infotrading.webapp.utils.StringUtils;

import java.util.List;

@Component
public class CalculationDetailInsertValidator implements InternalValidator {

    @Autowired
    private CommonErrorsMsg commonErrorsMsg;

    @Override
    public InternalResult valid(Object domain) {
        List<AppendixFileModel> appendixFileModels = (List<AppendixFileModel>) domain;

        InternalResult result = new InternalResult(commonErrorsMsg);

        for (AppendixFileModel appendixFileModel : appendixFileModels) {
            String name = appendixFileModel.getName();
            String size = appendixFileModel.getSize();
            String content = appendixFileModel.getContent();

            result.rejectIfEmptyOrWhitespace("name", name);
            result.rejectIfEmptyOrWhitespace("size", size);
            result.rejectIfEmptyOrWhitespace("content", content);

            if (!StringUtils.isNullOrEmpty(name)) {
                result.rejectIfInvalidMaxLength("name", name, 100);
            }

            if (!StringUtils.isNullOrEmpty(size)) {
                result.rejectIfInvalidMaxLength("size", size, 50);
            }

            if (result.hasErrors()) {
                return result;
            }
        }
        return result;
    }
}
