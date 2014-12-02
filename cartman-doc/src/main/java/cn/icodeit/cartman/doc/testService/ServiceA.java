package cn.icodeit.cartman.doc.testService;

import cn.icodeit.cartman.core.annotation.Mapping;
import cn.icodeit.cartman.core.annotation.MethodField;
import cn.icodeit.cartman.core.annotation.Param;
import cn.icodeit.cartman.core.annotation.Service;
import cn.icodeit.cartman.core.annotation.errorCode.ResponseCode;
import cn.icodeit.cartman.doc.view.DocItem;
import cn.icodeit.cartman.doc.view.Operation;

import java.util.List;

/**
 * .
 * User: yuanweifeng
 * Date: 14-11-26
 * Time: 上午10:51
 */
@Service(value = "/s001")
public class ServiceA {
//
//    @Mapping(value = "getString", method = MethodField.GET, status = ResponseCode.success)
//    public String getString(
//            @Param(value = "param", description = "")String abc
//    ) {
//        return "test method " + abc + " :";
//    }
//    @Mapping(value = "postString", method = MethodField.POST, status = ResponseCode.success)
//    public String postString(
//            @Param(value = "param", description = "")String abc
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
//    @Mapping(value = "getParam", status = ResponseCode.success, method = MethodField.GET)
//    public String getParam(int a,boolean b,Integer c,char d) {
//        return "test method getParam a = " +a
//                + "     "+"b =" + b
//                + "     "+"c =" + c
//                + "     "+"d =" + d;
//    }
//    @Mapping(value = "getParam2", status = ResponseCode.success, method = MethodField.GET)
//    public String getParam2(Integer a,Boolean b,Integer c,Character d) {
//        return "test method getParam a = " +a
//                + "     "+"b =" + b
//                + "     "+"c =" + c
//                + "     "+"d =" + d;
//    }
    @Mapping(value = "operation", status = ResponseCode.success, method = MethodField.POST)
    public Operation operation(Operation operation,List<Operation> ops){

        return operation;
    }
    @Mapping(value = "getOperation", status = ResponseCode.success, method = MethodField.POST)
    public Operation getOperation(Operation operation){
        System.out.println(operation);
        return operation;
    }
    @Mapping(value = "getDocItem", status = ResponseCode.success, method = MethodField.POST)
    public DocItem getDocItem(){
        DocItem docItem = new DocItem("ffff");
        return docItem;
    }
}
