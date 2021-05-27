package com.kong.mapper;

import com.kong.my.MyMapper;
import com.kong.pojo.ItemsComments;
import com.kong.pojo.vo.ItemCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsCommentsMapper extends MyMapper<ItemsComments> {

    public List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String, Object> map);

}