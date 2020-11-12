package han.communitylab.community.controller;

import han.communitylab.community.dto.QuestionDTO;
import han.communitylab.community.mapper.QuestionMapper;
import han.communitylab.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/question/{id}")
    public  String question(@PathVariable(name="id")Long id, Model model){
        QuestionDTO questionDTO=questionService.getById(id);
        //阅读+1
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
