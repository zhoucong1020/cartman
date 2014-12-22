package cn.icodeit.cartman.doc.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lcf on 2014/11/26.
 */
public class DocService {
    private String apiVersion = "";
    private String basePath = "";
    private String resourcePath = "";
    private String path = "";
    private List<DocMapping> apis = new ArrayList<DocMapping>();
    private Map<String, Object> models = new HashMap<>();
    private String description;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<DocMapping> getApis() {
        return apis;
    }

    public void setApis(List<DocMapping> apis) {
        this.apis = apis;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public Map<String, Object> getModels() {
        return models;
    }

    public void setModels(Map<String, Object> models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return "DocService{" +
                "path='" + path + '\'' +
                ", apis=" + apis +
                ", description='" + description + '\'' +
                '}';
    }
}
