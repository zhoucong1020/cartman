package cn.icodeit.cartman.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author zhoucong
 * @since 0.0.2
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceError {

    int value();

    String description() default "";
}
