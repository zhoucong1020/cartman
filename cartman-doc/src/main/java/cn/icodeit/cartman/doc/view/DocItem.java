package cn.icodeit.cartman.doc.view;

/**
 * Created by lcf on 2014/12/2.
 */
public class DocItem {
    private String $ref ="";

    public DocItem() {
    }

    public DocItem(String $ref) {
        this.$ref = $ref;
    }

    public String get$ref() {
        return $ref;
    }

    public void set$ref(String $ref) {
        this.$ref = $ref;
    }


    @Override
    public String toString() {
        return "DocItem{" +
                "$ref='" + $ref + '\'' +
                '}';
    }
}
