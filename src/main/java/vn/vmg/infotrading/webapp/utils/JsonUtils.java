package vn.vmg.infotrading.webapp.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.MultiMap;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonUtils {
    public JsonUtils() {
    }

    public static <T> T mapJsonObjectToClass(JsonObject jsonObject, Class<T> mapValue, boolean skipUnknown)
            throws IOException {
        return parseJsonObjToClass(jsonObject, mapValue, skipUnknown);
    }

    private static <T> T parseJsonObjToClass(Object content, Class<T> mapValue, boolean skipUnknown)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // config skip node json if this not exist in Object class
        if (skipUnknown) {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }

        if (content instanceof File) {
            return mapper.readValue((File) content, mapValue);
        } else if (content instanceof String) {
            return mapper.readValue(content.toString(), mapValue);
        } else if (content instanceof JsonObject) {
            return mapper.readValue(content.toString(), mapValue);
        }

        return mapper.readValue(mapper.writeValueAsString(content), mapValue);
    }

    //    public static JsonNode multiMapToJsLowerKey(MultiMap queryParams) {
    public static JsonNode multiMapToJsNodeLowerKey(MultiMap map) {
        if (map == null || map.size() < 1) return JsonUtils.newEmptyObj();
        JsonNode paramJs = JsonUtils.newEmptyObj();
//        boolean isCheckKey=(lstAllowKey!=null && lstAllowKey.size()>0);
        for (String name : map.names()) {
            //nếu chỉ muốn lấy ra một vài param
//            if(isCheckKey && !lstAllowKey.contains(name.toLowerCase())) continue;
            JsonUtils.addNodeValue(paramJs, name.toLowerCase(), map.get(name));
        }
        return paramJs;
    }

    public static JsonObject multiMapToJsObjLowerKey(MultiMap map) {
        JsonObject metadata = new JsonObject();
        for (Map.Entry<String, String> entry : map.entries()) {
            String key = entry
                    .getKey()
                    .replace("-", "");
            metadata.put(key.toLowerCase(), entry.getValue());
        }
        return metadata;
    }

    public static JsonObject mapToJsObj(Map<String, String> map) {
        JsonObject metadata = new JsonObject();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry
                    .getKey();
            metadata.put(key, entry.getValue());
        }
        return metadata;
    }


    public static JsonObject mapToJsObjLowerKey(Map<String, String> map) {
        JsonObject metadata = new JsonObject();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry
                    .getKey()
                    .replace("-", "");
            metadata.put(key.toLowerCase(), entry.getValue());
        }
        return metadata;
    }

    public static JsonNode newEmptyObj() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree("{}");
        } catch (IOException e) {
            return null;
        }
    }

    public static void addNodeValue(JsonNode jsonData, String nodeName, Object nodeData) {
        ObjectNode jNode = (ObjectNode) jsonData;
        if (nodeData instanceof String) {
            jNode.put(nodeName, nodeData.toString());
            return;
        }

        if (nodeData instanceof JsonNode) {
            JsonNode nodeTmp = (JsonNode) nodeData;
            jNode.set(nodeName, nodeTmp);
            return;
        }

        jNode.putPOJO(nodeName, nodeData);
    }

    public static String toJsonStr(Object input) {
        try {
            return parseObjToJsStr(input, false);
        } catch (Exception ex) {
            System.out.println(ex);
            return "unknow format!";
        }
    }

    public static String toJsonStrPretty(Object input) {
        try {
            return parseObjToJsStr(input, true);
        } catch (Exception ex) {
            System.out.println(ex);
            return "unknow format!";
        }
    }

    public static String parseObjToJsStr(Object objectInput, boolean setPrettyFm) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        if (setPrettyFm) {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectInput);
        } else {
            return mapper.writeValueAsString(objectInput);
        }
    }

    public static JsonObject strToJsObj(String str) {
        if (str.trim().equals("")) {
            return new JsonObject("{}");
        }
        return new JsonObject(str);
    }

    public static JsonArray strToJsArr(String str) {
        if (str.trim().equals("")) {
            return new JsonArray("[]");
        }
        return new JsonArray(str);
    }

    public static boolean validParamSearch(Object param) {
        return (param != null && !String.valueOf(param).isEmpty());
    }

    public static JsonObject multiMapToJsObj(MultiMap map) {
        JsonObject metadata = new JsonObject();
        for (Map.Entry<String, String> entry : map.entries()) {
            String key = entry
                    .getKey();
            metadata.put(key, entry.getValue());
        }
        return metadata;
    }

}
