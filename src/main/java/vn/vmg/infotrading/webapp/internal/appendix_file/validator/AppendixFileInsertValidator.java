package vn.vmg.infotrading.webapp.internal.appendix_file.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.internal.appendix_file.domain.AppendixFileModel;
import vn.vmg.infotrading.webapp.utils.StringUtils;

@Component
public class AppendixFileInsertValidator implements InternalValidator {

  @Autowired
  private CommonErrorsMsg commonErrorsMsg;

  @Override
  public InternalResult valid(Object domain) {
    AppendixFileModel appendixFileModel = (AppendixFileModel) domain;
    String name = appendixFileModel.getName();
    String size = appendixFileModel.getSize();
    String content = appendixFileModel.getContent();
    Long appendixId = appendixFileModel.getAppendixId();

    InternalResult result = new InternalResult(commonErrorsMsg);
    result.rejectIfEmptyOrWhitespace("name", name);
    result.rejectIfEmptyOrWhitespace("size", size);
    result.rejectIfEmptyOrWhitespace("content", content);
    result.rejectIfEmptyOrWhitespace("appendixId", appendixId);

    if (!StringUtils.isNullOrEmpty(name)) {
      result.rejectIfInvalidMaxLength("name", name, 100);
    }

    if (!StringUtils.isNullOrEmpty(size)) {
      result.rejectIfInvalidMaxLength("size", size, 50);
    }

    return result;
  }
}
