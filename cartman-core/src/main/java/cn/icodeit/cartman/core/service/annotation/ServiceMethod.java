package cn.icodeit.cartman.core.service.annotation;

import cn.icodeit.cartman.core.annotation.MethodField;
import cn.icodeit.cartman.core.annotation.errorCode.ResponseCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yuanweifeng
 * @since 0.0.1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceMethod {

    String value() default "";

    RequestMethod method() default RequestMethod.POST;

    ResponseCode[] status();

    String description() default "";
}
