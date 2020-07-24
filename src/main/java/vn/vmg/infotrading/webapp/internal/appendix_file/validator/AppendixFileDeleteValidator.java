package vn.vmg.infotrading.webapp.internal.appendix_file.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.internal.appendix_file.domain.AppendixFile;
import vn.vmg.infotrading.webapp.repository.AppendixFileOracleRepository;

@Component
public class AppendixFileDeleteValidator implements InternalValidator {

    @Autowired
    private CommonErrorsMsg commonErrorsMsg;

    @Autowired
    private AppendixFileOracleRepository appendixFileRepository;

    @Override
    public InternalResult valid(Object domain) {
        Long id = (Long) domain;

        InternalResult result = new InternalResult(commonErrorsMsg);
        result.rejectIfEmptyOrWhitespace("id", id);

        AppendixFile oldAppendixFile = appendixFileRepository.getById(id);
        if (oldAppendixFile == null) {
            result.rejectIfRecordChange("delete");
        }

        return result;
    }
}
