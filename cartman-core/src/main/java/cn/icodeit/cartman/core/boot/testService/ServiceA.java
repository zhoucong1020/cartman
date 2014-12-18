package cn.icodeit.cartman.core.boot.testService;

import cn.icodeit.cartman.core.annotation.Param;
import cn.icodeit.cartman.core.annotation.errorCode.ResponseCode;
import cn.icodeit.cartman.core.service.annotation.RequestMethod;
import cn.icodeit.cartman.core.service.annotation.Service;
import cn.icodeit.cartman.core.service.annotation.ServiceMethod;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-26
 * Time: 上午10:51
 */
@Service(value = "s001")
public class ServiceA {

    @ServiceMethod(value = "aa",method = RequestMethod.GET, status = ResponseCode.success)
    public String test(
            @Param(value = "$xyz", description = "",required = false)
            String abc ,
            @Param(value = "abc",description = "")
            String aaq
    ) {
             ResponseCode.success.record( "abc");
        return "$test method " + abc + " :" + aaq;
    }


    @ServiceMethod(value = "ss", status = ResponseCode.success)
    public String test(

    ) {

        return "abc";
    }


}
