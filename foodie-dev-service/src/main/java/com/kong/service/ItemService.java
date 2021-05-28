package com.kong.service;

import com.kong.pojo.Items;
import com.kong.pojo.ItemsImg;
import com.kong.pojo.ItemsParam;
import com.kong.pojo.ItemsSpec;
import com.kong.pojo.vo.CommentLevelCountsVO;
import com.kong.pojo.vo.ItemCommentVO;
import com.kong.pojo.vo.ShopcartVO;
import com.kong.utils.PagedGridResult;

import java.util.List;

public interface ItemService {
    // 根据商品ID查询详情
    Items queryItemById(String itemId);

    // 根据商品ID查询商品图片列表
    List<ItemsImg> queryItemImgList(String itemId);

    // 根据商品ID查询商品规格
    List<ItemsSpec> queryItemSpecList(String itemId);

    // 根据商品ID查询商品参数
    ItemsParam queryItemParam(String itemId);

    // 根据商品ID查询商品的评价等级数量
    CommentLevelCountsVO queryCommentsCounts(String itemId);

    // 根据商品ID查询商品的评价(分页)
    PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize);

    // 搜索商品列表
    PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize);

    // 根据分类id搜索商品列表
    PagedGridResult searchItems(Integer catId, String sort, Integer page, Integer pageSize);

    // 根据规格ids查询最新的购物车中商品数据(用于刷新渲染购物车中的商品数据)
    List<ShopcartVO> queryItemsBySpecIds(String specIds);
}
