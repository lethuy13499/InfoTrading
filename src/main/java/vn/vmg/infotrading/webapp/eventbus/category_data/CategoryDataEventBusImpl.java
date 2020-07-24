package vn.vmg.infotrading.webapp.eventbus.category_data;

import io.reactivex.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.SingleHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.HeaderModel;
import vn.vmg.infotrading.webapp.internal.category_data.domain.CategoryDataModel;
import vn.vmg.infotrading.webapp.internal.category_data.service.CategoryDataService;
import vn.vmg.infotrading.webapp.internal.gateway.service.GatewayService;
import vn.vmg.infotrading.webapp.utils.ApiResponseUtils;
import vn.vmg.infotrading.webapp.utils.JsonUtils;

import java.io.IOException;

public class CategoryDataEventBusImpl implements CategoryDataEventBus {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryDataEventBusImpl.class);

    private final CategoryDataService categoryDataService;

    private final GatewayService gatewayService;

    public CategoryDataEventBusImpl(CategoryDataService categoryDataService, GatewayService gatewayService, Handler<AsyncResult<CategoryDataEventBus>> readyHandler) {
        this.categoryDataService = categoryDataService;
        this.gatewayService = gatewayService;
        Single.just(this).subscribe(SingleHelper.toObserver(readyHandler));
    }

    @Override
    public CategoryDataEventBus getAll(JsonObject jsRequest, Handler<AsyncResult<JsonObject>> readyHandler) {
        JsonObject result = new JsonObject();
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            CategoryDataModel categoryDataModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), CategoryDataModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result.put("status", false);
                result.put("message", "Jwt is invalid");
            } else {
                result = categoryDataService.getAll(categoryDataModel);
            }
        } catch (IOException e) {
            e.printStackTrace();
            result.put("status", false);
            result.put("message", "Get category data fail");
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public CategoryDataEventBus getById(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            CategoryDataModel categoryDataModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), CategoryDataModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = categoryDataService.getById(categoryDataModel.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Get category data by id fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public CategoryDataEventBus insert(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            CategoryDataModel categoryDataModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), CategoryDataModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = categoryDataService.insert(categoryDataModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Insert category data fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public CategoryDataEventBus update(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            CategoryDataModel categoryDataModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), CategoryDataModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = categoryDataService.update(categoryDataModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Update category data fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public CategoryDataEventBus delete(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            CategoryDataModel categoryDataModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), CategoryDataModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = categoryDataService.delete(categoryDataModel.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Delete category data fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

}
