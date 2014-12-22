package cn.icodeit.cartman.doc.testService;

import cn.icodeit.cartman.core.annotation.RequestMethod;
import cn.icodeit.cartman.core.annotation.Service;
import cn.icodeit.cartman.core.annotation.ServiceError;
import cn.icodeit.cartman.core.annotation.ServiceMethod;
import cn.icodeit.cartman.doc.view.DocInfo;
import cn.icodeit.cartman.doc.view.Operation;

import java.util.List;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-26
 * Time: 上午10:51
 */
@Service(value = "mathB")
public class ServiceB {
    //
//    @ServiceMethod(value = "getString", method = MethodField.GET, status = ResponseCode.SUCCESS)
//    public String getString(
//            @Param(value = "param", description = "")String abc
//    ) {
//        return "test method " + abc + " :";
//    }
//    @ServiceMethod(value = "postString", method = MethodField.POST, status = ResponseCode.SUCCESS)
//    public String postString(
//            @Param(value = "param", description = "")String abc,
//          int a,int b
//    ) {
//        return "test method " + abc + " :";
//    }
//
//
//    @ServiceMethod(value = "postNoParameter", status = ResponseCode.SUCCESS, method = MethodField.POST)
//    public String postNoParameter() {
//        return "test method postNoParameter";
//    }
//
//
//    @ServiceMethod(value = "getNoParameter", status = ResponseCode.SUCCESS, method = MethodField.GET)
//    public String getNoParameter() {
//        return "test method getNoParameter";
//    }
    @ServiceMethod(value = "getParam",
            errors = {
                    @ServiceError(value = 200, description = "")
            }
    )
    public String getParam(int a, boolean b, Integer c, char d) {
        return "test method getParam a = " + a
                + "     " + "b =" + b
                + "     " + "c =" + c
                + "     " + "d =" + d;
    }

    //    @ServiceMethod(value = "getParam2", status = ResponseCode.SUCCESS, method = MethodField.GET)
//    public String getParam2(Integer a,Boolean b,Integer c,Character d) {
//        return "test method getParam a = " +a
//                + "     "+"b =" + b
//                + "     "+"c =" + c
//                + "     "+"d =" + d;
//    }
//    @ServiceMethod(value = "operation", status = ResponseCode.SUCCESS, method = MethodField.POST)
//    public Operation operation(Operation operation,List<Operation> ops){
//
//        return operation;
//    }
    @ServiceMethod(value = "getOperation",
            errors = {
                    @ServiceError(value = 200, description = "")
            }
    )
    public Operation getOperation(Operation operation) {
        System.out.println(operation);
        return operation;
    }
//    @ServiceMethod(value = "getDocItem", status = ResponseCode.SUCCESS, method = RequestMethod.POST)
//    public DocItem getDocItem(){
//        DocItem docItem = new DocItem("ffff");
//        return docItem;
//    }
//    @ServiceMethod(value = "getDocItem", status = ResponseCode.SUCCESS, method = RequestMethod.POST)
//    public DocItem getOperation(DocItem docItem){
//        return docItem;
//    }
//    @ServiceMethod(value = "getDocInfo", status = ResponseCode.SUCCESS, method = RequestMethod.POST)
//    public DocInfo getDocInfo(DocInfo docInfo){
//        return docInfo;
//    }

    public List<Operation> list(List<Operation> operations) {
        return operations;
    }

    @ServiceMethod(value = "max", method = RequestMethod.POST,
            errors = {
                    @ServiceError(value = 200, description = "")
            }
    )
    public int max(int a, int b) {
        return Math.max(a, b);
    }

    @ServiceMethod(value = "sum", method = RequestMethod.POST,
            errors = {
                    @ServiceError(value = 200, description = "")
            }
    )
    public int sum(int a, int b) {
        return a + b;
    }

    @ServiceMethod(value = "dive", method = RequestMethod.POST,
            errors = {
                    @ServiceError(value = 200, description = "")
            }
    )
    public double dive(double a, double b) {
        return a / b;
    }

    @ServiceMethod(value = "test", method = RequestMethod.POST,
            errors = {
                    @ServiceError(value = 200, description = "")
            }
    )
    public double test(double a, double b) {
        return a / b;
    }

    public DocInfo docInfo(DocInfo docInfo) {
        return docInfo;
    }

    public String testChinese(String str) {
        return str;
    }

    @ServiceMethod(value = "testChinese", method = RequestMethod.GET,
            errors = {
                    @ServiceError(value = 200, description = "")
            }
    )
    public String testChineseGet(String str) {
        return str;
    }
}
