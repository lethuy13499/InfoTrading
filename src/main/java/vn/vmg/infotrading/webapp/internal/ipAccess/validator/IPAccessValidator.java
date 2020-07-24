package vn.vmg.infotrading.webapp.internal.ipAccess.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.internal.ipAccess.domain.IPAccess;
import vn.vmg.infotrading.webapp.internal.ipAccess.repository.IpAccessRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static vn.vmg.infotrading.webapp.internal.InternalConstant.IP_PATTERN;

@Component
public class IPAccessValidator implements InternalValidator {
    @Autowired
    private IpAccessRepository ipAccessRepository ;
    @Autowired
    private CommonErrorsMsg commonErrorsMsg;
    @Override
    public InternalResult valid(Object domain) {
        IPAccess ipAccess = (IPAccess) domain;
        InternalResult result = new InternalResult(commonErrorsMsg);
        result.rejectIfEmptyOrWhitespace(ipAccess.getIp(),"ip");
        result.rejectIfEmptyOrWhitespace(ipAccess.getPartner_id().toString(),"partner_id");
        if (!result.hasFieldErrors("ip")) {
            if (!isValid(IP_PATTERN, ipAccess.getIp())) {
                result.rejectValue("ip", "không đúng định dạng");

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
