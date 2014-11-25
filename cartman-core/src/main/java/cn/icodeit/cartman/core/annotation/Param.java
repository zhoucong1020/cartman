package cn.icodeit.cartman.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-19
 * Time: 下午2:59
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Param {

    String value() default "";

    String description() default "";

    boolean required() default true;
}
