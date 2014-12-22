package cn.icodeit.cartman.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author zhoucong
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceError {

    int value();

    String description() default "";
}
