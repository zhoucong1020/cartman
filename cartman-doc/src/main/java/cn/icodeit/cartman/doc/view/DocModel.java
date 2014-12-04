package cn.icodeit.cartman.doc.view;

import java.util.Map;

/**
 * Created by lcf on 2014/12/2.
 */
public class DocModel {
    private  String id;
    private  Map<String,Object> properties;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
