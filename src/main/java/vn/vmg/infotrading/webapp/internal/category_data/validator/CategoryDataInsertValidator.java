package vn.vmg.infotrading.webapp.internal.category_data.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.internal.category_data.domain.CategoryData;
import vn.vmg.infotrading.webapp.repository.CategoryDataOracleRepository;

@Component
public class CategoryDataInsertValidator implements InternalValidator {

    @Autowired
    private CategoryDataOracleRepository categoryDataOracleRepository;

    @Autowired
    private CommonErrorsMsg commonErrorsMsg;

    @Override
    public InternalResult valid(Object domain) {
        CategoryData categoryData = (CategoryData) domain;
        InternalResult result = new InternalResult(commonErrorsMsg);
        String code = categoryData.getCode();
        Integer status = categoryData.getStatus();

        result.rejectIfEmptyOrWhitespace(code,"code");
        result.rejectIfEmptyOrWhitespace(status.toString(),"status");
//        result.rejectIfEmptyOrWhitespace(information.getInforGroupId(),"inforGroupId");

        return result;
    }
}
