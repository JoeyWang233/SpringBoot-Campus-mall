package com.imooc.o2o.web.frontend;

import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.service.ProductCategoryService;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: o2o
 * @description:
 * @author: Joey
 * @create: 2019-04-18 10:52
 */
@Controller
@RequestMapping("/frontend")
public class ShopDetailController {

    private final ShopService shopService;

    private final ProductCategoryService productCategoryService;

    private final ProductService productService;

    @Autowired
    public ShopDetailController(ShopService shopService, ProductCategoryService productCategoryService, ProductService productService) {
        this.shopService = shopService;
        this.productCategoryService = productCategoryService;
        this.productService = productService;
    }

    /**
     * 获取店铺信息以及该店铺下面的商品类别列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/listshopdetailpageinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listShopDetailPageInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        Shop shop;
        List<ProductCategory> productCategoryList;

        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if (shopId != -1) {
            shop = shopService.getByShopId(shopId);
            productCategoryList = productCategoryService.getProductCategoryList(shopId);
            modelMap.put("shop", shop);
            modelMap.put("productCategoryList", productCategoryList);
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty shopId");
        }
        return modelMap;
    }

    /**
     * 根据条件查询某一商店下的商品
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/listproductsbyshop", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listproductsbyshop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        long shopId = HttpServletRequestUtil.getLong(request, "shopId");

        if ((pageIndex > -1) && (pageSize > -1) && (shopId > -1)) {
            long productCategoryId = HttpServletRequestUtil.getLong(request, "productCategoryId");
            String productName = HttpServletRequestUtil.getString(request, "productName");
            Product productCondition = this.compactProductCondition4Search(shopId, productCategoryId, productName);
            ProductExecution se = productService.getProductList(productCondition, pageIndex, pageSize);
            modelMap.put("productList", se.getProductList());
            modelMap.put("count", se.getCount());
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
        }
        return modelMap;
    }

    private Product compactProductCondition4Search(long shopId, long productCategoryId, String productName) {
        Product productCondition = new Product();
        Shop shop = new Shop(shopId);
        productCondition.setShop(shop);

        if (productCategoryId > -1) {
            ProductCategory productCategory = new ProductCategory(productCategoryId);
            productCondition.setProductCategory(productCategory);
        }

        if (productName != null) {
            productCondition.setProductName(productName);
        }

        productCondition.setEnableStatus(1);
        return productCondition;
    }
}
