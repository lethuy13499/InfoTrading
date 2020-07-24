package vn.vmg.infotrading.webapp.eventbus;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.serviceproxy.ServiceBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.eventbus.infomation.InfomationEventBus;
import vn.vmg.infotrading.webapp.eventbus.infomationGroup.InfomationGroupEventBus;
import vn.vmg.infotrading.webapp.eventbus.ipAccess.IPAccessEventBus;
import vn.vmg.infotrading.webapp.eventbus.partner.PartnerEventBus;
import vn.vmg.infotrading.webapp.internal.InfomationGroup.service.InfomationGroupService;
import vn.vmg.infotrading.webapp.internal.infomation.service.InformationService;
import vn.vmg.infotrading.webapp.internal.ipAccess.service.IPAccessService;
import vn.vmg.infotrading.webapp.internal.partner.service.PartnerService;
import vn.vmg.infotrading.webapp.eventbus.appendix.AppendixEventBus;
import vn.vmg.infotrading.webapp.eventbus.appendix_file.AppendixFileEventBus;
import vn.vmg.infotrading.webapp.eventbus.auth.AuthEventBus;
import vn.vmg.infotrading.webapp.eventbus.category_data.CategoryDataEventBus;
import vn.vmg.infotrading.webapp.eventbus.common.CommonEventBus;
import vn.vmg.infotrading.webapp.eventbus.price_setting.PriceSettingEventBus;
import vn.vmg.infotrading.webapp.eventbus.sharing_partner.SharingPartnerEventBus;
import vn.vmg.infotrading.webapp.eventbus.user_info.UserInfoEventBus;
import vn.vmg.infotrading.webapp.internal.appendix.service.AppendixService;
import vn.vmg.infotrading.webapp.internal.appendix_file.service.AppendixFileService;
import vn.vmg.infotrading.webapp.internal.auth.service.AuthService;
import vn.vmg.infotrading.webapp.internal.category_data.service.CategoryDataService;
import vn.vmg.infotrading.webapp.internal.common.service.CommonService;
import vn.vmg.infotrading.webapp.internal.gateway.service.GatewayService;
import vn.vmg.infotrading.webapp.internal.price_setting.service.PriceSettingService;
import vn.vmg.infotrading.webapp.internal.sample.service.SampleService;
import vn.vmg.infotrading.webapp.internal.sharing_partner.service.SharingPartnerService;
import vn.vmg.infotrading.webapp.internal.user_info.service.UserInfoService;

@Component
public class RxEventBusVerticle extends AbstractVerticle {
    private Logger LOGGER = LoggerFactory.getLogger(RxEventBusVerticle.class);

    @Autowired
    private GatewayService gatewayService;

    @Autowired
    private SampleService sampleService;
    @Autowired
    private PartnerService partnerService;
    @Autowired
    private IPAccessService ipAccessService;
    @Autowired
    private InfomationGroupService infomationGroupService;
    @Autowired
    private InformationService infomationService;

    @Autowired
    private AuthService authService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private AppendixService appendixService;

    @Autowired
    private AppendixFileService appendixFileService;

    @Autowired
    private SharingPartnerService sharingPartnerService;

    @Autowired
    private CategoryDataService categoryDataService;

    @Autowired
    private PriceSettingService priceSettingService;

    @Override
    public void start(Promise<Void> promise) throws Exception {
        LOGGER.info("init InfoTradingEventBus... ");

        ServiceBinder binder = new ServiceBinder(vertx.getDelegate());
        Future<Void> step = binderAuthService(authService, gatewayService, binder)
                .compose(v -> bindingPartner(partnerService, binder))
                .compose(v -> bindingIPAccess(ipAccessService, binder))
                .compose(v -> bindingInfoGroup(infomationGroupService, binder))
                .compose(v -> bindingInfo(infomationService, binder))
                .compose(v -> binderCommonService(commonService, gatewayService, binder))
                .compose(v -> binderUserInfoService(userInfoService, gatewayService, binder))
                .compose(v -> binderAppendixService(appendixService, gatewayService, binder))
                .compose(v -> binderAppendixFileService(appendixFileService, gatewayService, binder))
                .compose(v -> binderSharingPartnerService(sharingPartnerService, gatewayService, binder))
                .compose(v -> binderCategoryData(categoryDataService, gatewayService, binder))
                .compose(v -> binderPriceSetting(priceSettingService, gatewayService, binder));
        step.onComplete(ar -> {
            if (ar.succeeded()) {
                promise.complete();
            } else {
                promise.fail(ar.cause());
            }
        });

    }

    private Future<Void> bindingPartner(PartnerService partnerService, ServiceBinder binder) {
        Promise<Void> promise = Promise.promise();

        PartnerEventBus.create(partnerService, ready -> {
            if (ready.succeeded()) {
                binder.setAddress(EventBusConstant.EB_PARTNER_ADDRESS).register(PartnerEventBus.class, ready.result());
                LOGGER.info("Created PartnerEvenBus... ");
                promise.complete();
            } else {
                LOGGER.info("fail PartnerEvenBus... ");
                promise.fail(ready.cause());
            }
        });

        return promise.future();
    }

    private Future<Void> bindingIPAccess(IPAccessService ipAccessService, ServiceBinder binder) {
        Promise<Void> promise = Promise.promise();
        IPAccessEventBus.create(ipAccessService, ready -> {
            if (ready.succeeded()) {
                binder.setAddress(EventBusConstant.EB_IP_ACCESS).register(IPAccessEventBus.class, ready.result());
                LOGGER.info("Created IPAccessEventBus... ");
                promise.complete();
            } else {
                LOGGER.info("fail IPAccessEventBus... ");
                promise.fail(ready.cause());
            }
        });
        return promise.future();
    }


    private Future<Void> bindingInfoGroup(InfomationGroupService infomationGroupService, ServiceBinder binder) {
        Promise<Void> promise = Promise.promise();

        InfomationGroupEventBus.create(infomationGroupService, ready -> {
            if (ready.succeeded()) {
                binder.setAddress(EventBusConstant.EB_INFOMATION_GROUPS).register(InfomationGroupEventBus.class, ready.result());
                LOGGER.info("Created InfomationGroupEventBus... ");
                promise.complete();
            } else {
                LOGGER.info("fail InfomationGroupEventBus... ");
                promise.fail(ready.cause());
            }
        });

        return promise.future();
    }

    private Future<Void> bindingInfo(InformationService infomationService, ServiceBinder binder) {
        Promise<Void> promise = Promise.promise();

        InfomationEventBus.create(infomationService, ready -> {
            if (ready.succeeded()) {
                binder.setAddress(EventBusConstant.EB_INFOMATION_ADDRESS).register(InfomationEventBus.class, ready.result());
                LOGGER.info("Created InfomationEventBus... ");
                promise.complete();
            } else {
                LOGGER.info("fail InfomationEventBus... ");
                promise.fail(ready.cause());
            }
        });

        return promise.future();
    }


    private Future<Void> binderAuthService(AuthService authService, GatewayService gatewayService, ServiceBinder binder) {
        Promise<Void> promise = Promise.promise();
        AuthEventBus.create(authService, gatewayService, ready -> {
            if (ready.succeeded()) {
                binder.setAddress(EventBusConstant.EB_AUTH_SERVICE).register(AuthEventBus.class, ready.result());
                LOGGER.info("Created AuthEventBus... ");
                promise.complete();
            } else {
                LOGGER.info("fail AuthEventBus... ");
                promise.fail(ready.cause());
            }
        });
        return promise.future();
    }

    private Future<Void> binderCommonService(CommonService commonService, GatewayService gatewayService, ServiceBinder binder) {
        Promise<Void> promise = Promise.promise();
        CommonEventBus.create(commonService, gatewayService, ready -> {
            if (ready.succeeded()) {
                binder.setAddress(EventBusConstant.EB_COMMON_SERVICE).register(CommonEventBus.class, ready.result());
                LOGGER.info("Created CommonEventBus... ");
                promise.complete();
            } else {
                LOGGER.info("fail CommonEventBus... ");
                promise.fail(ready.cause());
            }
        });
        return promise.future();
    }

    private Future<Void> binderUserInfoService(UserInfoService userInfoService, GatewayService gatewayService, ServiceBinder binder) {
        Promise<Void> promise = Promise.promise();
        UserInfoEventBus.create(userInfoService, gatewayService, ready -> {
            if (ready.succeeded()) {
                binder.setAddress(EventBusConstant.EB_USER_INFO_SERVICE).register(UserInfoEventBus.class, ready.result());
                LOGGER.info("Created UserInfoEventBus... ");
                promise.complete();
            } else {
                LOGGER.info("fail UserInfoEventBus... ");
                promise.fail(ready.cause());
            }
        });
        return promise.future();
    }

    private Future<Void> binderAppendixService(AppendixService appendixService, GatewayService gatewayService, ServiceBinder binder) {
        Promise<Void> promise = Promise.promise();
        AppendixEventBus.create(appendixService, gatewayService, ready -> {
            if (ready.succeeded()) {
                binder.setAddress(EventBusConstant.EB_APPENDIX_SERVICE).register(AppendixEventBus.class, ready.result());
                LOGGER.info("Created AppendixEventBus... ");
                promise.complete();
            } else {
                LOGGER.info("fail AppendixEventBus... ");
                promise.fail(ready.cause());
            }
        });
        return promise.future();
    }

    private Future<Void> binderAppendixFileService(AppendixFileService appendixFileService, GatewayService gatewayService, ServiceBinder binder) {
        Promise<Void> promise = Promise.promise();
        AppendixFileEventBus.create(appendixFileService, gatewayService, ready -> {
            if (ready.succeeded()) {
                binder.setAddress(EventBusConstant.EB_APPENDIX_FILE_SERVICE).register(AppendixFileEventBus.class, ready.result());
                LOGGER.info("Created AppendixFileEventBus... ");
                promise.complete();
            } else {
                LOGGER.info("fail AppendixFileEventBus... ");
                promise.fail(ready.cause());
            }
        });
        return promise.future();
    }

    private Future<Void> binderSharingPartnerService(SharingPartnerService sharingPartnerService, GatewayService gatewayService, ServiceBinder binder) {
        Promise<Void> promise = Promise.promise();
        SharingPartnerEventBus.create(sharingPartnerService, gatewayService, ready -> {
            if (ready.succeeded()) {
                binder.setAddress(EventBusConstant.EB_SHARING_PARTNER_SERVICE).register(SharingPartnerEventBus.class, ready.result());
                LOGGER.info("Created SharingPartnerEventBus... ");
                promise.complete();
            } else {
                LOGGER.info("fail SharingPartnerEventBus... ");
                promise.fail(ready.cause());
            }
        });
        return promise.future();
    }

    private Future<Void> binderCategoryData(CategoryDataService categoryDataService, GatewayService gatewayService, ServiceBinder binder) {
        Promise<Void> promise = Promise.promise();

        CategoryDataEventBus.create(categoryDataService, gatewayService, ready -> {
            if (ready.succeeded()) {
                binder.setAddress(EventBusConstant.EB_CATEGORY_DATA).register(CategoryDataEventBus.class, ready.result());
                LOGGER.info("Created CategoryDataEventBus... ");
                promise.complete();
            } else {
                LOGGER.info("fail CategoryDataEventBus... ");
                promise.fail(ready.cause());
            }
        });

        return promise.future();
    }

    private Future<Void> binderPriceSetting(PriceSettingService priceSettingService, GatewayService gatewayService, ServiceBinder binder) {
        Promise<Void> promise = Promise.promise();

        PriceSettingEventBus.create(priceSettingService, gatewayService, ready -> {
            if (ready.succeeded()) {
                binder.setAddress(EventBusConstant.EB_PRICE_SETTING).register(PriceSettingEventBus.class, ready.result());
                LOGGER.info("Created PriceSettingEventBus... ");
                promise.complete();
            } else {
                LOGGER.info("fail PriceSettingEventBus... ");
                promise.fail(ready.cause());
            }
        });

        return promise.future();
    }
}
