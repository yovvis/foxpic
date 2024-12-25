package com.ayfox.web.utils;

import com.ayfox.web.exception.BusinessException;
import com.ayfox.web.exception.ErrorCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * JsonUtils
 *
 * @author tenyon
 * @date 2024/12/25
 */
public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象2json字符串
     *
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public static String toJsonStr(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "对象转json字符串报错");
        }
    }

    /**
     * json字符串2ist
     *
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> List<T> toList(String jsonStr, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonStr, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "json字符串转list失败");
        }
    }
}
