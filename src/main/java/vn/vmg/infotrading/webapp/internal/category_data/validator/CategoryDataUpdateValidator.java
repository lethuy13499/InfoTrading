package vn.vmg.infotrading.webapp.internal.category_data.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.repository.CategoryDataOracleRepository;

@Component
public class CategoryDataUpdateValidator implements InternalValidator {

    @Autowired
    private CategoryDataOracleRepository categoryDataOracleRepository;

    @Autowired
    private CommonErrorsMsg commonErrorsMsg;

    @Override
    public InternalResult valid(Object domain) {
//        CategoryData information = (CategoryData) domain;
        InternalResult result = new InternalResult(commonErrorsMsg);
//        result.rejectIfEmptyOrWhitespace(information.getFieldName(),"fieldName");
//        result.rejectIfEmptyOrWhitespace(information.getPartnerId(),"partnerId");
//        result.rejectIfEmptyOrWhitespace(information.getInforGroupId(),"inforGroupId");

        return result;
    }
}
