package vn.vmg.infotrading.webapp.internal.appendix_file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.appendix_file.domain.AppendixFileModel;
import vn.vmg.infotrading.webapp.internal.appendix_file.validator.AppendixFileDeleteValidator;
import vn.vmg.infotrading.webapp.internal.appendix_file.validator.AppendixFileInsertValidator;
import vn.vmg.infotrading.webapp.repository.AppendixFileOracleRepository;
import vn.vmg.infotrading.webapp.utils.ApiResponseUtils;

@Service
public class AppendixFileService {

    @Autowired
    private AppendixFileOracleRepository appendixFileRepository;

    @Autowired
    private AppendixFileInsertValidator appendixFileInsertValidator;

    @Autowired
    private AppendixFileDeleteValidator appendixFileDeleteValidator;

    @Transactional(rollbackFor = Exception.class)
    public RxResult insert(AppendixFileModel appendixFileModel) {
        InternalResult internalResult = appendixFileInsertValidator.valid(appendixFileModel);
        if (internalResult.hasErrors()) {
            return ApiResponseUtils.buildBadRequestResponse(internalResult.getErrors());
        }

        appendixFileRepository.insert(appendixFileModel);

        return ApiResponseUtils.buildSuccessResponse("Insert appendix file successful", null);
    }

    @Transactional(rollbackFor = Exception.class)
    public RxResult delete(Long id) {
        InternalResult internalResult = appendixFileDeleteValidator.valid(id);
        if (internalResult.hasErrors()) {
            return ApiResponseUtils.buildBadRequestResponse(internalResult.getErrors());
        }

        appendixFileRepository.delete(id);

        return ApiResponseUtils.buildSuccessResponse("Delete appendix file successful", null);
    }
}