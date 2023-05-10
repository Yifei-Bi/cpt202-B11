package com.example.finall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.example.finall.pojo.Comments;

import java.util.List;

@Mapper
public interface CommentsMapper {
    int addComments(Comments comments);
//    int addComments(String text);


    //删除

    int deleteCommentsById(int order_id);

    //更新
    //int updateComments(Comments comments);

    //查询

    Comments QueryCommentsByText(String text);

//    Comments QueryCommentsById(String comment_id);

    //void updateStatus(int order_id,String status);

    List<Comments> QueryAllComments();

}
