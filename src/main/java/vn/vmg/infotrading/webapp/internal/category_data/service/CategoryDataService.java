package vn.vmg.infotrading.webapp.internal.category_data.service;

import io.vertx.core.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.category_data.domain.CategoryData;
import vn.vmg.infotrading.webapp.internal.category_data.domain.CategoryDataModel;
import vn.vmg.infotrading.webapp.internal.category_data.validator.CategoryDataDeleteValidator;
import vn.vmg.infotrading.webapp.internal.category_data.validator.CategoryDataGetAllValidator;
import vn.vmg.infotrading.webapp.internal.category_data.validator.CategoryDataInsertValidator;
import vn.vmg.infotrading.webapp.internal.category_data.validator.CategoryDataUpdateValidator;
import vn.vmg.infotrading.webapp.repository.CategoryDataOracleRepository;
import vn.vmg.infotrading.webapp.utils.ApiResponseUtils;

import java.util.List;

@Service
public class CategoryDataService {

    @Autowired
    private CategoryDataOracleRepository categoryDataRepository;

    @Autowired
    private CategoryDataGetAllValidator categoryDataGetAllValidator;

    @Autowired
    private CategoryDataInsertValidator categoryDataInsertValidator;

    @Autowired
    private CategoryDataUpdateValidator categoryDataUpdateValidator;

    @Autowired
    private CategoryDataDeleteValidator categoryDataDeleteValidator;

    public JsonObject getAll(CategoryDataModel categoryDataModel) {
        JsonObject result = new JsonObject();

        InternalResult internalResult = categoryDataGetAllValidator.valid(categoryDataModel);
        if (internalResult.hasErrors()) {
            result.put("message", "Get category data fail");
            result.put("status", false);
            result.put("content", internalResult.getErrors());
            return result;
        }

        List<JsonObject> categoryData = categoryDataRepository.getAll(categoryDataModel);
        Integer recordsTotal = categoryDataRepository.getRecordsTotal(categoryDataModel.getCategoryId());

        result.put("data", categoryData);
        result.put("recordsTotal", recordsTotal);
        result.put("recordsFiltered", categoryData.size());

        return result;
    }

    public RxResult getById(Long id) {
        CategoryData result = categoryDataRepository.getById(id);

        return ApiResponseUtils.buildSuccessResponse("Get category data by id successful", result);
    }

    @Transactional(rollbackFor = Exception.class)
    public RxResult insert(CategoryDataModel categoryDataModel) {
        InternalResult internalResult = categoryDataInsertValidator.valid(categoryDataModel);
        if (internalResult.hasErrors()) {
            return ApiResponseUtils.buildBadRequestResponse(internalResult.getErrors());
        }

        categoryDataRepository.insert(categoryDataModel);

        return ApiResponseUtils.buildSuccessResponse("Insert category data successful", null);
    }

    @Transactional(rollbackFor = Exception.class)
    public RxResult update(CategoryDataModel categoryDataModel) {
        InternalResult internalResult = categoryDataUpdateValidator.valid(categoryDataModel);
        if (internalResult.hasErrors()) {
            return ApiResponseUtils.buildBadRequestResponse(internalResult.getErrors());
        }

        categoryDataRepository.update(categoryDataModel);

        return ApiResponseUtils.buildSuccessResponse("Update category data successful", null);
    }

    @Transactional(rollbackFor = Exception.class)
    public RxResult delete(Long id) {
        InternalResult internalResult = categoryDataDeleteValidator.valid(id);
        if (internalResult.hasErrors()) {
            return ApiResponseUtils.buildBadRequestResponse(internalResult.getErrors());
        }

        categoryDataRepository.delete(id);

        return ApiResponseUtils.buildSuccessResponse("Delete category data successful", null);
    }

}