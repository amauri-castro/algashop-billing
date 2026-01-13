package com.algashop.billing.application.utility;

public interface Mapper {
    <T> T convert(Object o, Class<T> destinationClass);
}
