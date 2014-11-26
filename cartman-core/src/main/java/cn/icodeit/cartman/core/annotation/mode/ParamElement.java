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
    private boolean required;


    public ParamElement(String annotationName, Class classType, boolean required) {
        this.annotationName = annotationName;
        this.classType = classType;
        this.required = required;
    }

    public String getAnnotationName() {
        return annotationName;
    }

    public Class getClassType() {
        return classType;
    }

    public boolean isRequired() {
        return required;
    }
}
