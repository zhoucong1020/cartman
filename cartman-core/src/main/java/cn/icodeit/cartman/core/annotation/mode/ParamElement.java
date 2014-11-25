package cn.icodeit.cartman.core.annotation.mode;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-24
 * Time: 上午9:31
 */
public class ParamElement {
    private String annotationName;
    private Class classType;
    private boolean optional;


    public ParamElement(String annotationName, Class classType, boolean optional) {
        this.annotationName = annotationName;
        this.classType = classType;
        this.optional = optional;
    }

    public String getAnnotationName() {
        return annotationName;
    }

    public Class getClassType() {
        return classType;
    }

    public boolean isOptional() {
        return optional;
    }
}
