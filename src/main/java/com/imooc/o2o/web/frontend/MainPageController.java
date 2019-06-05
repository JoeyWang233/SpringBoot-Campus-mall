package com.imooc.o2o.web.frontend;

import com.imooc.o2o.entity.HeadLine;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.service.HeadLineService;
import com.imooc.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/frontend")
public class MainPageController {
    private final HeadLineService headLineService;

    private final ShopCategoryService shopCategoryService;

    @Autowired
    public MainPageController(HeadLineService headLineService, ShopCategoryService shopCategoryService) {
        this.headLineService = headLineService;
        this.shopCategoryService = shopCategoryService;
    }

    @RequestMapping(value = "/listmainpageinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listMainPageInfo() {
        Map<String,Object> modelMap = new HashMap<>();

        List<ShopCategory> shopCategoryList;
        try {
            // 获取一级店铺类别列表（即parentId为空的ShopCategory）
            shopCategoryList = shopCategoryService.getShopCategoryList(null);
            modelMap.put("shopCategoryList",shopCategoryList);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }

        List<HeadLine> headLineList;
        try {
            // 获取状态为可用（enableStatus=1）的头条列表
            HeadLine headLineCondition = new HeadLine();
            headLineCondition.setEnableStatus(1);
            headLineList = headLineService.getHeadLineList(headLineCondition);
            modelMap.put("headLineList",headLineList);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }

        modelMap.put("success",true);
        return modelMap;
    }
}