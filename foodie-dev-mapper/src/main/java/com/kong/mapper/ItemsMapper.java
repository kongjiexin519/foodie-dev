package com.kong.mapper;

import com.kong.my.MyMapper;
import com.kong.pojo.Items;
import com.kong.pojo.vo.SearchItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsMapper extends MyMapper<Items> {
    public List<SearchItemsVO> searchItems(@Param("paramsMap") Map<String, Object> map);
    public List<SearchItemsVO> searchItemsByThirdCat(@Param("paramsMap") Map<String, Object> map);
}