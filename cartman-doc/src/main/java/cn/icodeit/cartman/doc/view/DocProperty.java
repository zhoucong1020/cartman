package cn.icodeit.cartman.doc.view;

/**
 * Created by lcf on 2014/12/2.
 */
public class DocProperty {
    private String type;
    private  String format;

    public DocProperty() {
    }

    public DocProperty(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
