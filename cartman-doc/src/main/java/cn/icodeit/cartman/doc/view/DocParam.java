package cn.icodeit.cartman.doc.view;

/**
 * Created by lcf on 2014/11/26.
 */
public class DocParam {
    String name;
    String description;
    boolean required;
    String type;
    String paramType;

    public DocParam() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    @Override
    public String toString() {
        return "DocParam{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", required=" + required +
                ", type='" + type + '\'' +
                ", paramType='" + paramType + '\'' +
                '}';
    }
}
