package vn.vmg.infotrading.webapp.internal.ipAccess.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.internal.ipAccess.domain.IPAccess;
import vn.vmg.infotrading.webapp.internal.ipAccess.repository.IpAccessRepository;
import vn.vmg.infotrading.webapp.utils.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static vn.vmg.infotrading.webapp.internal.InternalConstant.IP_PATTERN;
@Component
public class UpdateIPAccessValidator implements InternalValidator {
    @Autowired
    private IpAccessRepository ipAccessRepository;
    @Autowired
    private CommonErrorsMsg commonErrorsMsg;

    @Override
    public InternalResult valid(Object domain) {
        IPAccess ipAccess = (IPAccess) domain;
        InternalResult result = new InternalResult(commonErrorsMsg);
        String ip = ipAccess.getIp();
        Integer id = ipAccess.getId();
        Integer partner_id = ipAccess.getPartner_id();
        result.rejectIfEmptyOrWhitespace("ip", ip);
        result.rejectIfEmptyOrWhitespace("partner_id", partner_id.toString());
        if (!result.hasFieldErrors("ip")) {
            if (!isValid(IP_PATTERN, ip)) {
                result.rejectValue("ip", "không đúng định dạng");

            }
        }
        IPAccess check = ipAccessRepository.getById(id);
        if (check == null) {
            result.rejectValue("IP access", "ip access not exist");
        }
        return result;

    }

    private boolean isValid(String regex, String data) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);

        return matcher.matches();
    }
}
