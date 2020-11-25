package han.communitylab.community.controller;

import han.communitylab.community.cache.HotTagCache;
import han.communitylab.community.dto.PaginationDTO;
import han.communitylab.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private HotTagCache hotTagCache;
    @GetMapping("/")
    public String hello(HttpServletRequest request,
                        Model model,
                        @RequestParam(name="page",defaultValue = "1")Integer page,
                        @RequestParam(name="size",defaultValue = "5")Integer size,
                        @RequestParam(name="search",required = false)String search,
                        @RequestParam(name="tag",required = false)String tag) {

        PaginationDTO pagination=questionService.List(search,tag,page,size);
        List<String> tags = hotTagCache.getHots();
        model.addAttribute("pagination",pagination);
        model.addAttribute("search",search);
        model.addAttribute("tags",tags);
        model.addAttribute("tag",tag);
        return "index";
    }
}
