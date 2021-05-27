package com.kong.service;

import com.kong.pojo.Items;
import com.kong.pojo.ItemsImg;
import com.kong.pojo.ItemsParam;
import com.kong.pojo.ItemsSpec;

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
}
