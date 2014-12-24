package cn.icodeit.cartman.core;

/**
 * @author zhoucong
 * @since 0.0.1
 */
public interface Transformer {
    //TODO: ->lcf 考虑transformer的用途，目前用作参数的反序列化和结果的序列化，以json为主
    //考虑后端模板系统中，视图的渲染是否可以看做一种序列化
    //模板<h1>${user}</h1> 模型model.user="user01"
    //渲染出<h1>user01</h1> 是否可以看做一种序列化
    //能否在现有架构中支持后端模板系统，如freemarker

    public String serialize(Object object);

    public <T> T deserialize(String str, Class<T> convertClazz);

    public String contentType();
}
