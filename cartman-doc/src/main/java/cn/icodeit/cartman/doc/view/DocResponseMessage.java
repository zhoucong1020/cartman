package cn.icodeit.cartman.doc.view;

/**
 * Created by lcf on 2014/11/26.
 */
public class DocResponseMessage {
    String message;
    int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "DocResponseMessage{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
