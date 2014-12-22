package cn.icodeit.cartman.core;

/**
 * @author zhoucong
 */
public class ParamImpl {

    private String name;
    private Class<?> clazz;
    private boolean required;

    public ParamImpl(String name, Class<?> clazz, boolean required) {
        this.name = name;
        this.clazz = clazz;
        this.required = required;
    }

    public String getName() {
        return name;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public boolean isRequired() {
        return required;
    }
}
