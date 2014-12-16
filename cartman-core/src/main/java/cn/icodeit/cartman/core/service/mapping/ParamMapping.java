package cn.icodeit.cartman.core.service.mapping;

/**
 * @author zhoucong
 */
public class ParamMapping {
    private String annotationName;
    private Class classType;
    private boolean required;


    public ParamMapping(String annotationName, Class classType, boolean required) {
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
