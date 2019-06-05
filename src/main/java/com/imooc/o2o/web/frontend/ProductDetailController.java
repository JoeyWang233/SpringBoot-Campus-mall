package com.imooc.o2o.web.frontend;

import com.imooc.o2o.entity.Product;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: o2o
 * @description:
 * @author: Joey
 * @create: 2019-04-18 16:13
 */
@Controller
@RequestMapping("/frontend")
public class ProductDetailController {

    private final ProductService productService;

    @Autowired
    public ProductDetailController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 根据商品Id获取商品详情
     * @param request
     * @return
     */
    @RequestMapping(value = "/listproductdetailpageinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listproductdetailpageinfo(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();

        long productId = HttpServletRequestUtil.getLong(request, "productId");
        if(productId>-1){
            Product product = productService.getProductById(productId);
            modelMap.put("product",product);
            modelMap.put("success",true);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty productId");
        }

        return modelMap;
    }
}
