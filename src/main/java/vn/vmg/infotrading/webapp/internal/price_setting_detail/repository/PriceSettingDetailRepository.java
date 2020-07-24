package vn.vmg.infotrading.webapp.internal.price_setting_detail.repository;

import vn.vmg.infotrading.webapp.internal.price_setting_detail.domain.PriceSettingDetail;
import vn.vmg.infotrading.webapp.internal.price_setting_detail.domain.PriceSettingDetailModel;

import java.util.List;

public interface PriceSettingDetailRepository {

    String PKG_PRICE_SETTING_DETAILS = "PKG_PRICE_SETTING_DETAILS";
    String PRC_GET_ALL_BY_PRICE_SETTING_ID = "PRC_GET_ALL_BY_PRICE_SETTING_ID";
    String PRC_INSERT = "PRC_INSERT";
    String PRC_UPDATE = "PRC_UPDATE";
    String PRC_DELETE = "PRC_DELETE";
    String PRC_DELETE_BY_PRICE_SETTING_ID = "PRC_DELETE_BY_PRICE_SETTING_ID";

    List<PriceSettingDetail> getAllByPriceSettingId(Long priceSettingId);

    Long insert(PriceSettingDetailModel priceSettingDetailModel);

    void update(PriceSettingDetailModel priceSettingDetailModel);

    void delete(Long id);

    void deleteByPriceSettingId(Long priceSettingId);

}
