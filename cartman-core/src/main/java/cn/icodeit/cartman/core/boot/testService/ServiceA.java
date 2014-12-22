package cn.icodeit.cartman.core.boot.testService;

import cn.icodeit.cartman.core.annotation.*;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-26
 * Time: 上午10:51
 */
@Service(value = "s001")
public class ServiceA {

    @ServiceMethod(value = "aa/*/bb", method = RequestMethod.GET,
            errors = {
                    @ServiceError(value = 200, description = "")
            }
    )
    public int test(
            @Param(value = "$xyz", description = "", required = false)
            String abc,
            @Param(value = "abc", description = "")
            String aaq
    ) {
        return 12121212;
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
