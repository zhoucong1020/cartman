package cn.icodeit.cartman.doc.view;

import java.util.List;

/**
 * Created by lcf on 2014/11/26.
 */
public class Operation {
    String method;
    String summary;
    String notes;
    String type;
    String nickName="";
    List<DocParam> parameters;
    List<DocResponseMessage> docResponseMessages;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<DocParam> getParameters() {
        return parameters;
    }

    public void setParameters(List<DocParam> parameters) {
        this.parameters = parameters;
    }

    public List<DocResponseMessage> getDocResponseMessages() {
        return docResponseMessages;
    }

    public void setDocResponseMessages(List<DocResponseMessage> docResponseMessages) {
        this.docResponseMessages = docResponseMessages;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "method='" + method + '\'' +
                ", summary='" + summary + '\'' +
                ", notes='" + notes + '\'' +
                ", type='" + type + '\'' +
                ", nickName='" + nickName + '\'' +
                ", parameters=" + parameters +
                ", docResponseMessages=" + docResponseMessages +
                '}';
    }
}
