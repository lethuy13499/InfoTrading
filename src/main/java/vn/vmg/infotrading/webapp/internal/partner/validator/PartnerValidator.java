package vn.vmg.infotrading.webapp.internal.partner.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.internal.partner.domain.PartnerRequest;
import vn.vmg.infotrading.webapp.internal.partner.repository.PartnerRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static vn.vmg.infotrading.webapp.internal.InternalConstant.IP_PATTERN;

@Component
public class PartnerValidator implements InternalValidator {
    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private CommonErrorsMsg commonErrorsMsg;

    @Override
    public InternalResult valid(Object domain) {
        PartnerRequest partnerRequest = (PartnerRequest) domain;
        Integer partnerParent = Integer.valueOf(partnerRequest.getPartnerParent());
        Integer id = partnerRequest.getId();
        InternalResult result = new InternalResult(commonErrorsMsg);
        result.rejectIfEmptyOrWhitespace(partnerRequest.getName(), "name");
        result.rejectIfEmptyOrWhitespace(partnerRequest.getSupplierType().toString(), "supplierType");
        result.rejectIfEmptyOrWhitespace(partnerRequest.getCustomerType().toString(), "customerType");
        //check partner parent
        if (partnerParent != null) {
            PartnerRequest check = partnerRepository.getById(id);
            if (check == null) {
                result.rejectValue("partner", "partner not exist");
            }
        }
        //validate ip
        if (partnerRequest.getIpPartner() != null) {
            if (!isValid(IP_PATTERN, partnerRequest.getIpPartner())) {
                result.rejectValue("ipPartner", "ip sai định dạng");
            }
        }
        // validate dải ip
        if (partnerRequest.getCustomerType().equals(1)) {
        }
        return result;
    }


    private boolean isValid(String regex, String data) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);

        return matcher.matches();
    }


}
