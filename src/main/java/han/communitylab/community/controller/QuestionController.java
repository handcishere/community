package han.communitylab.community.controller;

import han.communitylab.community.dto.CommentDTO;
import han.communitylab.community.dto.QuestionDTO;
import han.communitylab.community.enums.CommentTypeEnum;
import han.communitylab.community.service.CommentService;
import han.communitylab.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/question/{id}")
    public  String question(@PathVariable(name="id")Long id, Model model){
        QuestionDTO questionDTO=questionService.getById(id);
        List<CommentDTO> comments=commentService.ListByTargetId(id, CommentTypeEnum.QUESTION);
        //阅读+1
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        return "question";
    }
}
