package han.communitylab.community.controller;

import han.communitylab.community.dto.CommentCreateDTO;
import han.communitylab.community.dto.CommentDTO;
import han.communitylab.community.dto.ResultDTO;
import han.communitylab.community.enums.CommentTypeEnum;
import han.communitylab.community.exception.CustomizeErrorCode;
import han.communitylab.community.model.Comment;
import han.communitylab.community.model.User;
import han.communitylab.community.service.CommentService;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @ResponseBody
    @RequestMapping(value="/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if(user==null){
            return ResultDTO.errorOf(CustomizeErrorCode.NP_LOGIN);
        }
        if(commentCreateDTO == null|| StringUtils.isNullOrEmpty(commentCreateDTO.getContent())){
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
 
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
    @ResponseBody
    @RequestMapping(value="/comment/{id}",method = RequestMethod.GET)
    public  ResultDTO comments(@PathVariable(name="id")Long id){
        List<CommentDTO> commentDTOS = commentService.ListByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
