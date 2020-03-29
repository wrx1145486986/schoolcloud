package com.wrx.schoolcould.utils;

import java.lang.reflect.Field;

public class Convert {
    /**
     * 反射获取当前对象
     * 将对象中为 "" 的值设置为 null
     * @param object
     * @throws IllegalAccessException
     */
    public static void emptyToNull(Object object) throws IllegalAccessException {
//        获取该对象的属性字段
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
//            设置可获取private修饰的变量
            field.setAccessible(true);
//            判空 不然比较时会抛异常
            if (field.get(object) != null && field.get(object).equals("") ){
                field.set(object,null);
            }
        }
    }

}
