package com.kong.controller;

import com.kong.pojo.Items;
import com.kong.pojo.ItemsImg;
import com.kong.pojo.ItemsParam;
import com.kong.pojo.ItemsSpec;
import com.kong.pojo.vo.CommentLevelCountsVO;
import com.kong.pojo.vo.ItemInfoVO;
import com.kong.pojo.vo.ShopcartVO;
import com.kong.service.ItemService;
import com.kong.utils.IMOOCJSONResult;
import com.kong.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "商品接口", tags = {"商品信息展示的相关接口"})
@RestController
@RequestMapping("items")
public class ItemsController extends BaseController{
    @Resource
    private ItemService itemService;

    @GetMapping("/info/{itemId}")
    @ApiOperation(value = "查询商品详情", notes = "查询商品详情", httpMethod = "GET")
    public IMOOCJSONResult info(@ApiParam(name = "itemId", value = "商品id", required = true)
                                  @PathVariable String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg(null);
        }

        Items item = itemService.queryItemById(itemId);
        List<ItemsImg> itemImgList = itemService.queryItemImgList(itemId);
        List<ItemsSpec> itemSpecList = itemService.queryItemSpecList(itemId);
        ItemsParam itemParams = itemService.queryItemParam(itemId);

        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(item);
        itemInfoVO.setItemImgList(itemImgList);
        itemInfoVO.setItemSpecList(itemSpecList);
        itemInfoVO.setItemParams(itemParams);

        return IMOOCJSONResult.ok(itemInfoVO);
    }

    @GetMapping("/commentLevel")
    @ApiOperation(value = "查询商品评价等级", notes = "查询商品评价等级", httpMethod = "GET")
    public IMOOCJSONResult commentLevel(@ApiParam(name = "itemId", value = "商品id", required = true)
                                        @RequestParam String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg(null);
        }

        CommentLevelCountsVO countsVO = itemService.queryCommentsCounts(itemId);

        return IMOOCJSONResult.ok(countsVO);
    }

    @GetMapping("/comments")
    @ApiOperation(value = "查询商品评论", notes = "查询商品评论", httpMethod = "GET")
    public IMOOCJSONResult comments(
            @ApiParam(name = "itemId", value = "商品id", required = true) @RequestParam String itemId,
            @ApiParam(name = "level", value = "评价等级", required = false) @RequestParam Integer level,
            @ApiParam(name = "page", value = "查询下一页的第几页", required = false) @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "分页的每页条数", required = false) @RequestParam Integer pageSize) {
        if (StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg(null);
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = COMMENT_PAGE_SIZE;
        }

        PagedGridResult gird = itemService.queryPagedComments(itemId, level, page, pageSize);

        return IMOOCJSONResult.ok(gird);
    }

    @GetMapping("/search")
    @ApiOperation(value = "搜索商品列表", notes = "搜索商品列表", httpMethod = "GET")
    public IMOOCJSONResult search(
            @ApiParam(name = "keywords", value = "关键字", required = true) @RequestParam String keywords,
            @ApiParam(name = "sort", value = "排序", required = false) @RequestParam String sort,
            @ApiParam(name = "page", value = "查询下一页的第几页", required = false) @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "分页的每页条数", required = false) @RequestParam Integer pageSize) {
        if (StringUtils.isBlank(keywords)) {
            return IMOOCJSONResult.errorMsg(null);
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }

        PagedGridResult gird = itemService.searchItems(keywords, sort, page, pageSize);

        return IMOOCJSONResult.ok(gird);
    }

    @GetMapping("/catItems")
    @ApiOperation(value = "通过分类id搜索商品列表", notes = "通过分类id搜索商品列表", httpMethod = "GET")
    public IMOOCJSONResult catItems(
            @ApiParam(name = "catId", value = "关键字", required = true) @RequestParam Integer catId,
            @ApiParam(name = "sort", value = "排序", required = false) @RequestParam String sort,
            @ApiParam(name = "page", value = "查询下一页的第几页", required = false) @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "分页的每页条数", required = false) @RequestParam Integer pageSize) {
        if (catId == null) {
            return IMOOCJSONResult.errorMsg(null);
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }

        PagedGridResult gird = itemService.searchItems(catId, sort, page, pageSize);

        return IMOOCJSONResult.ok(gird);
    }

    @ApiOperation(value = "根据商品规格ids查找最新的商品数据", notes = "根据商品规格ids查找最新的商品数据", httpMethod = "GET")
    @GetMapping("/refresh")
    public IMOOCJSONResult refresh(@ApiParam(name = "itemSpecIds", value = "拼接的规格ids", required = true, example = "1001, 1002, 1003")
                                   @RequestParam String itemSpecIds) {
        if (StringUtils.isBlank(itemSpecIds)) {
            return IMOOCJSONResult.ok();
        }
        List<ShopcartVO> list = itemService.queryItemsBySpecIds(itemSpecIds);
        return IMOOCJSONResult.ok(list);
    }
}
