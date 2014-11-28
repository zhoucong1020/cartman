package cn.icodeit.cartman.doc.view;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lcf on 2014/11/26.
 */
public class DocApi {

    String apiVersion;
    String basePath;
    String resourcePath;
    List<DocService> apis = new ArrayList<>();
    Map<String,Object> models;
    DocInfo docInfo;

    public DocApi() {
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

    public List<DocService> getApis() {
        return apis;
    }

    public void setApis(List<DocService> apis) {
        this.apis = apis;
    }

    public Map<String, Object> getModels() {
        return models;
    }

    public void setModels(Map<String, Object> models) {
        this.models = models;
    }

    public DocInfo getDocInfo() {
        return docInfo;
    }

    public void setDocInfo(DocInfo docInfo) {
        this.docInfo = docInfo;
    }

    @Override
    public String toString() {
        return "DocApi{" +
                "apiVersion='" + apiVersion + '\'' +
                ", basePath='" + basePath + '\'' +
                ", resourcePath='" + resourcePath + '\'' +
                ", apis=" + apis +
                ", models=" + models +
                ", docInfo=" + docInfo +
                '}';
    }
}
