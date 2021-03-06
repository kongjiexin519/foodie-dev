package com.kong.controller;

import com.kong.enums.ExceptionEnum;
import com.kong.enums.YesOrNo;
import com.kong.pojo.Carousel;
import com.kong.pojo.Category;
import com.kong.pojo.vo.CategoryVO;
import com.kong.pojo.vo.NewItemsVO;
import com.kong.service.CarouselService;
import com.kong.service.CategoryService;
import com.kong.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "首页", tags = {"首页展示的相关接口"})
@RestController
@RequestMapping("index")
public class IndexController {
    @Autowired
    private CarouselService carouselService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/carousel")
    @ApiOperation(value = "获取首页轮播图列表", notes = "获取首页轮播图列表", httpMethod = "GET")
    public IMOOCJSONResult carousel() {
        List<Carousel> list = carouselService.queryAll(YesOrNo.YES.getType());
        return IMOOCJSONResult.ok(list);
    }

    /**
     * 首页分类展示需求：
     * 1. 第一次刷新主页查询大分类，渲染展示到首页
     * 2. 如果鼠标移动到大分类上，则加载其子分类的内容，如果已经存在子分类，则不需要加载(懒加载)
     */
    @GetMapping("/cats")
    @ApiOperation(value = "获取商品分类(一级分类)", notes = "获取商品分类(一级分类)", httpMethod = "GET")
    public IMOOCJSONResult cats() {
        List<Category> categories = categoryService.queryAllRootLevelCat();
        return IMOOCJSONResult.ok(categories);
    }

    @GetMapping("/subCat/{rootCatId}")
    @ApiOperation(value = "获取商品子分类", notes = "获取商品子分类", httpMethod = "GET")
    public IMOOCJSONResult subCat(@ApiParam(name = "rootCatId", value = "一级分类id", required = true)
                                  @PathVariable Integer rootCatId){
        if (rootCatId == null) {
            IMOOCJSONResult.errorMsg(ExceptionEnum.CATEGORY_NOT_EXIST.getMsg());
        }

        List<CategoryVO> subCatList = categoryService.getSubCatList(rootCatId);

        return IMOOCJSONResult.ok(subCatList);
    }

    @GetMapping("/sixNewItems/{rootCatId}")
    @ApiOperation(value = "查询每个一级分类下的最新6条商品数据", notes = "查询每个一级分类下的最新6条商品数据", httpMethod = "GET")
    public IMOOCJSONResult sixNewItems(@ApiParam(name = "rootCatId", value = "一级分类id", required = true)
                                       @PathVariable Integer rootCatId) {
        if (rootCatId == null) {
            return IMOOCJSONResult.errorMsg(ExceptionEnum.CATEGORY_NOT_EXIST.getMsg());
        }

        List<NewItemsVO> list = categoryService.getSixNewItemsLazy(rootCatId);
        return IMOOCJSONResult.ok(list);
    }
}
