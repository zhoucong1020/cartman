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
    String nickname ="";
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
                ", nickname='" + nickname + '\'' +
                ", parameters=" + parameters +
                ", docResponseMessages=" + docResponseMessages +
                '}';
    }
}
