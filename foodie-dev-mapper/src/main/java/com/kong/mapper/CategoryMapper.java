package com.kong.mapper;

import com.kong.my.MyMapper;
import com.kong.pojo.Category;
import com.kong.pojo.vo.CategoryVO;
import com.kong.pojo.vo.NewItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapper extends MyMapper<Category> {

    public List<CategoryVO> getSubCatList(Integer rootCatId);

    public List<NewItemsVO> getSixNewItemsLazy(@Param("paramsMap") Map<String, Object> map);
}