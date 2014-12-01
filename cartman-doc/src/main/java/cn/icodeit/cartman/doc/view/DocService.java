package cn.icodeit.cartman.doc.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcf on 2014/11/26.
 */
public class DocService {
    String apiVersion;
    String basePath;
    String resourcePath;
    String path;
    List<DocMapping> apis = new ArrayList<DocMapping>();
    String description;

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


    @Override
    public String toString() {
        return "DocService{" +
                "path='" + path + '\'' +
                ", apis=" + apis +
                ", description='" + description + '\'' +
                '}';
    }
}
