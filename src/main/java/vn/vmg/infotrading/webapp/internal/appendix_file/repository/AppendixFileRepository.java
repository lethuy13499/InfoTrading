package vn.vmg.infotrading.webapp.internal.appendix_file.repository;

import vn.vmg.infotrading.webapp.internal.appendix_file.domain.AppendixFile;
import vn.vmg.infotrading.webapp.internal.appendix_file.domain.AppendixFileModel;

import java.util.List;

public interface AppendixFileRepository {

    String PKG_APPENDIX_FILES = "PKG_APPENDIX_FILES";
    String PRC_GET_ALL_BY_APPENDIX_ID = "PRC_GET_ALL_BY_APPENDIX_ID";
    String PRC_GET_BY_ID = "PRC_GET_BY_ID";
    String PRC_INSERT = "PRC_INSERT";
    String PRC_DELETE = "PRC_DELETE";
    String PRC_DELETE_BY_APPENDIX_ID = "PRC_DELETE_BY_APPENDIX_ID";

    List<AppendixFile> getAllByAppendixId(Long appendixId);

    AppendixFile getById(Long id);

    Long insert(AppendixFileModel appendixFileModel);

    void delete(Long id);

    void deleteByAppendixId(Long appendixId);

}
