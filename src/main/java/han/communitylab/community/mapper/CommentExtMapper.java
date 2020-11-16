package han.communitylab.community.mapper;

import han.communitylab.community.model.Comment;
import han.communitylab.community.model.CommentExample;
import han.communitylab.community.model.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}