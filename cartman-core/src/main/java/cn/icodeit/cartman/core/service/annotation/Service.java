package cn.icodeit.cartman.core.service.annotation;

import cn.icodeit.cartman.core.annotation.MethodField;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yuanweifeng
 * @since 0.0.1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {

    String value() default "";

    RequestMethod method() default RequestMethod.POST;

    String description() default "";
}
