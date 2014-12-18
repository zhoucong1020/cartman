package cn.icodeit.cartman.core.annotation;

import cn.icodeit.cartman.core.annotation.errorCode.ResponseCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-19
 * Time: 下午2:58
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mapping {

    String value() default "";

    MethodField method() default MethodField.POST;

    ResponseCode[] status();

    String description() default "";
}
