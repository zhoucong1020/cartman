package cn.icodeit.cartman.core.boot.testService.tesd;

import cn.icodeit.cartman.core.annotation.*;


/**
 * .
 * User: yuanweifeng
 * Date: 14-11-26
 * Time: 上午10:51
 */
@Service(value = "s002")
public class ServiceA {

    @ServiceMethod(value = "aa", method = RequestMethod.GET,
            errors = {
                    @ServiceError(value = 200, description = "")
            }
    )
    public String test(
            @Param(value = "$xyz", description = "", required = false)
            String abc,
            @Param(value = "abc", description = "")
            String aaq
    ) {
        return "$test method " + abc + " :" + aaq;
    }


    @ServiceMethod(value = "ss",
            errors = {
                    @ServiceError(value = 200, description = "")
            }
    )
    public String test(

    ) {

        return "abc";
    }


}
