package vn.vmg.infotrading.webapp.internal.sample.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.internal.sample.domain.RegisterInfo;
import vn.vmg.infotrading.webapp.internal.sample.repository.SampleRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static vn.vmg.infotrading.webapp.internal.InternalConstant.USERNAME_PATTERN;


@Component
public class RegisterValidator implements InternalValidator {

    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private CommonErrorsMsg commonErrorsMsg;

    @Override
    public InternalResult valid(Object domain) {
        RegisterInfo registerInfo = (RegisterInfo) domain;
        InternalResult result = new InternalResult(commonErrorsMsg);
        result.rejectIfEmptyOrWhitespace(registerInfo.getUsername(), "username");
        result.rejectIfEmptyOrWhitespace(registerInfo.getPassword(), "password");
        result.rejectIfEmptyOrWhitespace(registerInfo.getConfirmPassword(), "confirmPassword");
        result.rejectIfEmptyOrWhitespace(registerInfo.getFullName(), "fullName");
        result.rejectIfEmptyOrWhitespace(registerInfo.getMentor(), "mentor");
        result.rejectIfEmptyOrWhitespace(registerInfo.getCmnd(), "cmnd");
        result.rejectIfEmptyOrWhitespace(registerInfo.getPhone(), "phone");

        if (!result.hasFieldErrors("username")) {
            if (!isValid(USERNAME_PATTERN, registerInfo.getUsername())) {
                result.rejectValue("username", "");
            } else {
                if (sampleRepository.findByUsername(registerInfo.getUsername()) != null) {
                    result.rejectValue("username", "");
                }
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
