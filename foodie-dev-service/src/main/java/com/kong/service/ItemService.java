package com.kong.service;

import com.kong.pojo.Items;
import com.kong.pojo.ItemsImg;
import com.kong.pojo.ItemsParam;
import com.kong.pojo.ItemsSpec;
import com.kong.pojo.vo.CommentLevelCountsVO;
import com.kong.pojo.vo.ItemCommentVO;
import com.kong.utils.PagedGridResult;

import java.util.List;

public interface ItemService {
    // 根据商品ID查询详情
    public Items queryItemById(String itemId);

    // 根据商品ID查询商品图片列表
    public List<ItemsImg> queryItemImgList(String itemId);

    // 根据商品ID查询商品规格
    public List<ItemsSpec> queryItemSpecList(String itemId);

    // 根据商品ID查询商品参数
    public ItemsParam queryItemParam(String itemId);

    // 根据商品ID查询商品的评价等级数量
    public CommentLevelCountsVO queryCommentsCounts(String itemId);

    // 根据商品ID查询商品的评价(分页)
    public PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize);

    // 搜索商品列表
    public PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize);
}
