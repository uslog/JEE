package com.dwq.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.function.Consumer;

public interface BaseDate {
    default <V> V asViewObject(Class<V> clazz, Consumer<V> consumer) {
        V v = this.asViewObject(clazz);
        consumer.accept(v);
        return v;
    }

    default <V> V asViewObject(Class<V> clazz) {
        try {
            Field[] fields = clazz.getDeclaredFields();
            Constructor<V> constructor = clazz.getConstructor();
            V v = constructor.newInstance();
            Arrays.asList(fields).forEach(field -> convert(field, v));
            return v;
        } catch (ReflectiveOperationException exception) {
            Logger logger = LoggerFactory.getLogger(BaseDate.class);
            logger.error("在VO与DTO转换时出现了一些错误", exception);
            throw new RuntimeException(exception.getMessage());
        }
    }

    private void convert(Field field, Object target){
        try {
            Field source = this.getClass().getDeclaredField(field.getName());
            field.setAccessible(true);
            source.setAccessible(true);
            field.set(target, source.get(this));
        } catch (IllegalAccessException | NoSuchFieldException ignored) {}
    }
}
