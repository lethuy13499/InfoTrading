package vn.vmg.infotrading.webapp.internal.price_setting.repository;

import vn.vmg.infotrading.webapp.internal.price_setting.domain.PriceSetting;
import vn.vmg.infotrading.webapp.internal.price_setting.domain.PriceSettingModel;

import java.util.List;

public interface PriceSettingRepository {

    String PKG_PRICE_SETTINGS = "PKG_PRICE_SETTINGS";
    String PRC_GET_ALL_BY_APPENDIX_ID = "PRC_GET_ALL_BY_APPENDIX_ID";
    String PRC_INSERT = "PRC_INSERT";
    String PRC_UPDATE = "PRC_UPDATE";
    String PRC_DELETE = "PRC_DELETE";

    List<PriceSetting> getAllByAppendixId(Long appendixId);

    Long insert(PriceSettingModel priceSettingModel);

    void update(PriceSettingModel priceSettingModel);

    void delete(Long id);

}
