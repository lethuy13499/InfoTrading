package vn.vmg.infotrading.webapp.internal.sharing_partner.repository;

import vn.vmg.infotrading.webapp.internal.calculation_detail.domain.CalculationDetail;
import vn.vmg.infotrading.webapp.internal.calculation_detail.domain.CalculationDetailModel;

import java.util.List;

public interface SharingPartnerRepository {

    List<CalculationDetail> getAllByPriceSettingDetailId(Long priceSettingDetailId);

    Long insert(CalculationDetailModel calculationDetailModel);

    void update(CalculationDetailModel calculationDetailModel);

    void delete(Long id);

    void deleteByPriceSettingDetailId(Long priceSettingDetailId);

}
