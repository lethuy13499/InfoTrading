package vn.vmg.infotrading.webapp.internal.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.sample.domain.RegisterInfo;
import vn.vmg.infotrading.webapp.internal.sample.validator.RegisterValidator;
import vn.vmg.infotrading.webapp.repository.SampleOracleRepository;

@Service
public class SampleService {

    @Autowired
    private SampleOracleRepository sampleRepository;

    @Autowired
    private RegisterValidator registerValidator;

    public InternalResult register(RegisterInfo info) {
        InternalResult internalResult = registerValidator.valid(info);
        if (!internalResult.hasErrors()) {
            int record = sampleRepository.save(info);
            internalResult.setStatus(record > 0);
        }

        return internalResult;
    }
}
