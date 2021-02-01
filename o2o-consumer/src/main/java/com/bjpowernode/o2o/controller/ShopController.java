package com.bjpowernode.o2o.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjpowernode.o2o.domain.Area;
import com.bjpowernode.o2o.domain.Shop;
import com.bjpowernode.o2o.domain.ShopCategory;
import com.bjpowernode.o2o.dto.ImageHolder;
import com.bjpowernode.o2o.dto.ShopExecution;
import com.bjpowernode.o2o.enums.ShopStateEnum;
import com.bjpowernode.o2o.exception.ShopOperationException;
import com.bjpowernode.o2o.service.AreaService;
import com.bjpowernode.o2o.service.ShopCategoryService;
import com.bjpowernode.o2o.service.ShopService;
import com.bjpowernode.o2o.utils.CodeUtil;
import com.bjpowernode.o2o.utils.HttpServletRequestUtil;
import com.bjpowernode.o2o.utils.ShopSetImgUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.chenxy
 * @date 2021/1/27
 */
@Controller
public class ShopController {

    @Reference(interfaceClass = ShopService.class, version = "1.0.0", check = false)
    private ShopService shopService;

    @Reference(interfaceClass = AreaService.class, version = "1.0.0", check = false)
    private AreaService areaService;

    @Reference(interfaceClass = ShopCategoryService.class, version = "1.0.0", check = false)
    private ShopCategoryService shopCategoryService;

    @RequestMapping("/shop")
    public String shop() {
        return "shop/shopoperation";
    }

    @RequestMapping(value = "/shopadmin/registershop", method = RequestMethod.POST)
    @ResponseBody
    // 从表单中传来的店铺信息都会保存在request对象中
    private Map<String, Object> registerShop(@RequestParam("shopImg") MultipartFile shopImg,
                                             HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        // 1.接收并转化相应的参数，包括店铺信息以及图片信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        // ObjectMapper是com.fasterxml.jackson.databind.ObjectMapper;
        // 用于json和pojo对象的转换。
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            // 将店铺注册表单中接收到的shop信息转成shop对象
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        // request.getSession().getServletContext() 从本次会话当中的上下文获取上传文件的内容
        //CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        // 由于在前端验证了图片, 所以直接将request转成multipartHttpServletRequest
        //MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        // 获取到文件流
        //shopImg = multipartHttpServletRequest.getFile("shopImg");



        // 2.注册店铺
        if (shop != null && shopImg != null) {
            //先将ownerId写死

            shop.setOwnerId(1);
            ShopExecution se;
            try {
                ImageHolder imageHolder = new ImageHolder(shopImg.getOriginalFilename(), shopImg.getInputStream());
                se = shopService.addShop(shop, imageHolder);
                shop = se.getShop();
                ShopSetImgUtil.addShopImg(shop, imageHolder);
                shopService.updateShop(shop);
                if (se.getState() == ShopStateEnum.CHECK.getState()) {
                    modelMap.put("success", true);
                    // 该用户可以操作的店铺列表
                    @SuppressWarnings("unchecked")
                    List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
                    if (shopList == null || shopList.size() == 0) {
                        shopList = new ArrayList<Shop>();
                    }
                    shopList.add(se.getShop());
                    request.getSession().setAttribute("shopList", shopList);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (ShopOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
            return modelMap;
        }
    }

    @RequestMapping(value = "/shopadmin/getshopinitinfo")
    @ResponseBody
    private Map<String, Object> getShopInitInfo() {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
        List<Area> areaList = new ArrayList<Area>();
        try {
            ShopCategory shopCategory = null;
            shopCategoryList = shopCategoryService
                    .getShopCategoryList(shopCategory);
            areaList = areaService.getAllArea();
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }

}
