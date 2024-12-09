package com.ayfox.web.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * ObjectUtils
 *
 * @author tenyon
 * @date 2024/11/22
 */
public class ObjectUtils {
    /**
     * 非空
     *
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 空
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        } else if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        } else if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        } else if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        } else if (obj instanceof Iterable) {
            return !((Iterable) obj).iterator().hasNext();
        } else if (obj instanceof Iterator) {
            return !((Iterator) obj).hasNext();
        } else {
            return false;
        }
    }
}
