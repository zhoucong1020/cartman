package cn.icodeit.cartman.doc.view;

import java.util.Map;

/**
 * Created by lcf on 2014/12/2.
 */
public class DocModel {
    private  String id;
    private  Map<String,DocProperty> properties;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, DocProperty> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, DocProperty> properties) {
        this.properties = properties;
    }
}
