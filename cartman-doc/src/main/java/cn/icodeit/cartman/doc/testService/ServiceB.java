package cn.icodeit.cartman.doc.testService;

import cn.icodeit.cartman.core.annotation.Mapping;
import cn.icodeit.cartman.core.annotation.MethodField;
import cn.icodeit.cartman.core.annotation.Service;
import cn.icodeit.cartman.core.annotation.errorCode.ResponseCode;
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
//    @Mapping(value = "getString", method = MethodField.GET, status = ResponseCode.success)
//    public String getString(
//            @Param(value = "param", description = "")String abc
//    ) {
//        return "test method " + abc + " :";
//    }
//    @Mapping(value = "postString", method = MethodField.POST, status = ResponseCode.success)
//    public String postString(
//            @Param(value = "param", description = "")String abc,
//          int a,int b
//    ) {
//        return "test method " + abc + " :";
//    }
//
//
//    @Mapping(value = "postNoParameter", status = ResponseCode.success, method = MethodField.POST)
//    public String postNoParameter() {
//        return "test method postNoParameter";
//    }
//
//
//    @Mapping(value = "getNoParameter", status = ResponseCode.success, method = MethodField.GET)
//    public String getNoParameter() {
//        return "test method getNoParameter";
//    }
    @Mapping(value = "getParam", status = ResponseCode.success, method = MethodField.GET)
    public String getParam(int a, boolean b, Integer c, char d) {
        return "test method getParam a = " + a
                + "     " + "b =" + b
                + "     " + "c =" + c
                + "     " + "d =" + d;
    }

    //    @Mapping(value = "getParam2", status = ResponseCode.success, method = MethodField.GET)
//    public String getParam2(Integer a,Boolean b,Integer c,Character d) {
//        return "test method getParam a = " +a
//                + "     "+"b =" + b
//                + "     "+"c =" + c
//                + "     "+"d =" + d;
//    }
//    @Mapping(value = "operation", status = ResponseCode.success, method = MethodField.POST)
//    public Operation operation(Operation operation,List<Operation> ops){
//
//        return operation;
//    }
    @Mapping(value = "getOperation", status = ResponseCode.success, method = MethodField.POST)
    public Operation getOperation(Operation operation) {
        System.out.println(operation);
        return operation;
    }
//    @Mapping(value = "getDocItem", status = ResponseCode.success, method = MethodField.POST)
//    public DocItem getDocItem(){
//        DocItem docItem = new DocItem("ffff");
//        return docItem;
//    }
//    @Mapping(value = "getDocItem", status = ResponseCode.success, method = MethodField.POST)
//    public DocItem getOperation(DocItem docItem){
//        return docItem;
//    }
//    @Mapping(value = "getDocInfo", status = ResponseCode.success, method = MethodField.POST)
//    public DocInfo getDocInfo(DocInfo docInfo){
//        return docInfo;
//    }

    public List<Operation> list(List<Operation> operations) {
        return operations;
    }

    @Mapping(value = "max", method = MethodField.POST, status = ResponseCode.success)
    public int max(int a, int b) {
        return Math.max(a, b);
    }

    @Mapping(value = "sum", method = MethodField.POST, status = ResponseCode.success)
    public int sum(int a, int b) {
        return a + b;
    }

    @Mapping(value = "dive", method = MethodField.POST, status = ResponseCode.success)
    public double dive(double a, double b) {
        return a / b;
    }

    @Mapping(value = "test", method = MethodField.POST, status = ResponseCode.success)
    public double test(double a, double b) {
        return a / b;
    }

    public DocInfo docInfo(DocInfo docInfo) {
        return docInfo;
    }

    public String testChinese(String str) {
        return str;
    }

    @Mapping(value = "testChinese", method = MethodField.GET, status = ResponseCode.success)
    public String testChineseGet(String str) {
        return str;
    }
}
