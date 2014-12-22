package cn.icodeit.cartman.core.annotation;

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

    RequestMethod method() default RequestMethod.GET;

    ServiceError[] errors();

    String description() default "";
}
