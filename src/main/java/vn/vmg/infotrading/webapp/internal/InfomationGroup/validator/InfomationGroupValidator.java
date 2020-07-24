package vn.vmg.infotrading.webapp.internal.InfomationGroup.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.contrants.CheckInfogroup;
import vn.vmg.infotrading.webapp.internal.InfomationGroup.domain.InformationGroup;
import vn.vmg.infotrading.webapp.internal.InfomationGroup.repository.InfomationGroupRepository;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.internal.infomation.domain.Information;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class InfomationGroupValidator implements InternalValidator {
    @Autowired
    private InfomationGroupRepository infomationGroupRepository;
    @Autowired
    private CommonErrorsMsg commonErrorsMsg;

    @Override
    public InternalResult valid(Object domain) {
        InformationGroup infomationGroup = (InformationGroup) domain;
        String code = infomationGroup.getCode();
        InternalResult result = new InternalResult(commonErrorsMsg);
        result.rejectIfEmptyOrWhitespace(infomationGroup.getCode(), "code");
        result.rejectIfEmptyOrWhitespace(infomationGroup.getStatus().toString(), "status");
        CheckInfogroup.validLengthString("code", infomationGroup.getCode(), 16, "length không đúng định dạng", result);
        //check code không trùng nhau
        if (CheckInfogroup.getString(infomationGroup.getCode()) != null) {
            InformationGroup informationGroup = infomationGroupRepository.findByCode(infomationGroup.getCode());
            if (infomationGroup.getId() == null) {
                result.rejectValue("informatio group", "informatio group exist");
            }
        }
        return result;
    }

    private boolean isValid(String regex, String data) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);

        return matcher.matches();
    }
}
