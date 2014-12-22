package cn.icodeit.cartman.doc.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcf on 2014/11/26.
 */
public class DocMapping {
    private String path;
    private List<Operation> operations = new ArrayList<Operation>();
    private String description;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    @Override
    public String toString() {
        return "DocMapping{" +
                "path='" + path + '\'' +
                ", operations=" + operations +
                ", description='" + description + '\'' +
                '}';
    }
}
