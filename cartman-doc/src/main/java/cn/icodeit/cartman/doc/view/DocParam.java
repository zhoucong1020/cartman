package cn.icodeit.cartman.doc.view;

/**
 * Created by lcf on 2014/11/26.
 */
public class DocParam {
    private  String name;
    private String description;
    private boolean required;
    private String type;
    private  String paramType;
    private  DocItem items;

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

    public DocItem getItems() {
        return items;
    }

    public void setItems(DocItem items) {
        this.items = items;
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
