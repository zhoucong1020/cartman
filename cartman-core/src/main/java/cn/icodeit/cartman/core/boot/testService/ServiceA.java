package cn.icodeit.cartman.core.boot.testService;

import cn.icodeit.cartman.core.annotation.Mapping;
import cn.icodeit.cartman.core.annotation.MethodField;
import cn.icodeit.cartman.core.annotation.Param;
import cn.icodeit.cartman.core.annotation.Service;
import cn.icodeit.cartman.core.annotation.errorCode.ResponseCode;


/**
 * .
 * User: yuanweifeng
 * Date: 14-11-26
 * Time: 上午10:51
 */
@Service(value = "s001")
public class ServiceA {

    @Mapping(value = "aa",method = MethodField.GET, status = ResponseCode.success)
    public String test(
            @Param(value = "$xyz", description = "",required = false)
            String abc ,
            @Param(value = "abc",description = "")
            String aaq
    ) {
             ResponseCode.success.record( "abc");
        return "$test method " + abc + " :" + aaq;
    }


    @Mapping(value = "ss", status = ResponseCode.success)
    public String test(

    ) {

        return "abc";
    }


}
