package cn.icodeit.cartman.core.boot.testService.tesd;

import cn.icodeit.cartman.core.service.annotation.*;


/**
 * .
 * User: yuanweifeng
 * Date: 14-11-26
 * Time: 上午10:51
 */
@Service(value = "s002")
public class ServiceA {

    @ServiceMethod(value = "aa", method = RequestMethod.GET, status = ResponseCode.success)
    public String test(
            @Param(value = "$xyz", description = "", required = false)
            String abc,
            @Param(value = "abc", description = "")
            String aaq
    ) {
        ResponseCode.success.record("abc");
        return "$test method " + abc + " :" + aaq;
    }


    @ServiceMethod(value = "ss", status = ResponseCode.success)
    public String test(

    ) {

        return "abc";
    }


}
