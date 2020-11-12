package han.communitylab.community.service;

import han.communitylab.community.enums.CommentTypeEnum;
import han.communitylab.community.exception.CustomizeErrorCode;
import han.communitylab.community.exception.CustomizeException;
import han.communitylab.community.mapper.CommentMapper;
import han.communitylab.community.mapper.QuestionExtMapper;
import han.communitylab.community.mapper.QuestionMapper;
import han.communitylab.community.model.Comment;
import han.communitylab.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper extMapper;
    public void insert(Comment comment) {

        if(comment.getParentId()==null||comment.getParentId()==0){
            throw  new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType()==null|| !CommentTypeEnum.isExist(comment.getType())){
            throw  new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if(comment.getType() == CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment==null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        }
        else{
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(question==null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            extMapper.incCommentCount(question);
        }
    }
}