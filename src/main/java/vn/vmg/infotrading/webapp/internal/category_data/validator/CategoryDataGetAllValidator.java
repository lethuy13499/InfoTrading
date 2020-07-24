package vn.vmg.infotrading.webapp.internal.category_data.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.internal.category_data.domain.CategoryDataModel;
import vn.vmg.infotrading.webapp.repository.CategoryDataOracleRepository;

@Component
public class CategoryDataGetAllValidator implements InternalValidator {

    @Autowired
    private CategoryDataOracleRepository categoryDataRepository;

    @Autowired
    private CommonErrorsMsg commonErrorsMsg;

    @Override
    public InternalResult valid(Object domain) {
        CategoryDataModel categoryDataModel = (CategoryDataModel) domain;
        Long categoryId = categoryDataModel.getCategoryId();

        InternalResult result = new InternalResult(commonErrorsMsg);
        result.rejectIfEmptyOrWhitespace("categoryId", categoryId);

        return result;
    }
}
