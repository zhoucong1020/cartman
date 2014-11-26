package cn.icodeit.cartman.core.annotation.mode.convert;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-26
 * Time: 上午9:58
 */
public class JsonConvert implements Convert {

    private static Convert jsonInstance = new JsonConvert();

    private JsonConvert() {

    }
    //TODO
    public Object convert(String paramString, Class convertClazz) {

        return "this is convert method";

        //return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    public boolean isBaseType() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    //TODO
    public String StringConvert(Object object) {
        return "this is stringConvert method ";
        //return null;
    }

    public static Convert getInstance() {
        return jsonInstance;
    }
}
