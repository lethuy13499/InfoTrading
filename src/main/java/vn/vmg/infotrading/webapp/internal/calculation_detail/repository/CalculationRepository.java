package vn.vmg.infotrading.webapp.internal.calculation_detail.repository;

import vn.vmg.infotrading.webapp.internal.calculation_detail.domain.CalculationDetail;
import vn.vmg.infotrading.webapp.internal.calculation_detail.domain.CalculationDetailModel;

import java.util.List;

public interface CalculationRepository {

    String PKG_CALCULATION_DETAILS = "PKG_CALCULATION_DETAILS";
    String PRC_GET_ALL_BY_PRICE_SETTING_DETAIL_ID = "PRC_GET_ALL_BY_PRICE_SETTING_DETAIL_ID";
    String PRC_INSERT = "PRC_INSERT";
    String PRC_UPDATE = "PRC_UPDATE";
    String PRC_DELETE = "PRC_DELETE";
    String PRC_DELETE_BY_PRICE_SETTING_DETAIL_ID = "PRC_DELETE_BY_PRICE_SETTING_DETAIL_ID";

    List<CalculationDetail> getAllByPriceSettingDetailId(Long priceSettingDetailId);

    Long insert(CalculationDetailModel calculationDetailModel);

    void update(CalculationDetailModel calculationDetailModel);

    void delete(Long id);

    void deleteByPriceSettingDetailId(Long priceSettingDetailId);

}
