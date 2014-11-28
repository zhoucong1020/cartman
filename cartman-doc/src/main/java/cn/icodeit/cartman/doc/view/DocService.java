package cn.icodeit.cartman.doc.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcf on 2014/11/26.
 */
public class DocService {
    String path;
    List<DocMapping> docMappings = new ArrayList<DocMapping>();
    String description;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<DocMapping> getDocMappings() {
        return docMappings;
    }

    public void setDocMappings(List<DocMapping> docMappings) {
        this.docMappings = docMappings;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DocService{" +
                "path='" + path + '\'' +
                ", docMappings=" + docMappings +
                ", description='" + description + '\'' +
                '}';
    }
}
