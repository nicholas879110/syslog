package com.gome.log.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author zhangliewei
 * @Date 2016/7/4
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class JsonUtils {
    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    private static ObjectMapper mapper = new ObjectMapper();

    private JsonUtils() {
    }

    public static String toJson(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (Exception var2) {
            logger.error("objectto json  error : ", var2);
            return null;
        }
    }

    public static <T> T toObject(String json, Class<T> valueType) {
        Assert.hasText(json);
        Assert.notNull(valueType);

        try {
            return mapper.readValue(json, valueType);
        } catch (Exception var3) {
            logger.error("json to object error : ", var3);
            return null;
        }
    }

    public static <T> T toObject(String json, TypeReference<?> typeReference) {
        Assert.hasText(json);
        Assert.notNull(typeReference);

        try {
            return mapper.readValue(json, typeReference);
        } catch (Exception var3) {
            logger.error("json to object error : ", var3);
            return null;
        }
    }

    public static <T> List<T> toObjectList(String json, Class<T> clazz) {
        Assert.hasText(json);
        Assert.notNull(clazz);

        try {
            return (List)mapper.readValue(json, new TypeReference() {
            });
        } catch (Exception var3) {
            logger.error("json to list error : ", var3);
            return null;
        }
    }

    public static <T> T toObject(String json, JavaType javaType) {
        Assert.hasText(json);
        Assert.notNull(javaType);

        try {
            return mapper.readValue(json, javaType);
        } catch (Exception var3) {
            logger.error("json to object error : ", var3);
            return null;
        }
    }

    public static void writeValue(Writer writer, Object value) {
        try {
            mapper.writeValue(writer, value);
        } catch (Exception var3) {
            logger.error("", var3);
        }

    }

    public static <T> List<T> getObjectsFromJson(String json, Class<T> valueType) {
        ArrayList list = null;

        try {
            JsonParser e = mapper.getJsonFactory().createJsonParser(json);
            JsonNode nodes = (JsonNode)e.readValueAsTree();
            list = new ArrayList(nodes.size());
            Iterator i$ = nodes.iterator();

            while(i$.hasNext()) {
                JsonNode node = (JsonNode)i$.next();
                list.add(mapper.readValue(node.textValue(), valueType));
            }
        } catch (Exception var7) {
            logger.error("json to list errror : ", var7);
        }

        return list;
    }

    public static <T> Map<String, T> getMapFromJson(String json) {
        try {
            Map userData = (Map)mapper.readValue(json, Map.class);
            return userData;
        } catch (Exception var3) {
            logger.error("json to map errro : ", var3);
            return null;
        }
    }

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
