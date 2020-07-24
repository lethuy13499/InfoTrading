package vn.vmg.infotrading.webapp.internal.appendix.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.repository.AppendixOracleRepository;

@Component
public class AppendixDeleteByContractIdValidator implements InternalValidator {

    @Autowired
    private CommonErrorsMsg commonErrorsMsg;

    @Autowired
    private AppendixOracleRepository appendixRepository;

    @Override
    public InternalResult valid(Object domain) {
        Long contractId = (Long) domain;

        InternalResult result = new InternalResult(commonErrorsMsg);
        result.rejectIfEmptyOrWhitespace("contractId", contractId);

        return result;
    }
}
