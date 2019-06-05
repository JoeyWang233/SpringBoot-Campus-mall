package com.imooc.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @program: o2o
 * @description: html页面存放在WEB-INF中，用户不能直接访问，需要内部转发。
 * @author: Joey
 * @create: 2019-02-25 21:43
 */
@Controller
@RequestMapping(value = "/shopadmin", method = {RequestMethod.GET})
public class ShopAdminController {

    // 店铺注册/编辑页面
    @RequestMapping(value = "/shopoperation")
    public String shopOperation() {
        // 之所以不是"/WEB-INF/html/shop/shopoperation.html"是因为在spring-web.xml文件中定义了viewResolver Bean，其内设置了前后缀
        return "shop/shopoperation";
    }

    @RequestMapping(value = "/shoplist", method = {RequestMethod.GET})
    public String shopList() {
        return "shop/shoplist";
    }

    @RequestMapping(value = "/shopmanagement")
    public String shopManagement() {
        return "shop/shopmanagement";
    }

    @RequestMapping(value = "/productcategorymanagement",method = RequestMethod.GET)
    public String productCategoryManage(){
        return "shop/productcategorymanagement";
    }

    // 商品添加/编辑页面
    @RequestMapping(value = "/productoperation")
    public String productOperation(){
        return "shop/productoperation";
    }

    @RequestMapping(value = "/productmanagement")
    public String productManagement(){
        return "shop/productmanagement";
    }
}
