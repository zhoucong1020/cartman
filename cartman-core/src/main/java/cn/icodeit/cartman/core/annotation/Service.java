package cn.icodeit.cartman.core.annotation;

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
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {

    String value() default "";

    MethodField method() default MethodField.POST;

    String description() default "";


}
