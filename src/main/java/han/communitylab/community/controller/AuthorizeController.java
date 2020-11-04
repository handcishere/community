package han.communitylab.community.controller;

import han.communitylab.community.dto.AccessTokenDTO;
import han.communitylab.community.dto.GitHubUser;
import han.communitylab.community.mapper.UserMapper;
import han.communitylab.community.model.User;
import han.communitylab.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name = "state")String state,
                           HttpServletRequest request){
        AccessTokenDTO accesstokenDTO = new AccessTokenDTO();
        accesstokenDTO.setClient_id(clientId);
        accesstokenDTO.setClient_secret(clientSecret);
        accesstokenDTO.setCode(code);
        accesstokenDTO.setRedirect_uri(redirectUri);
        accesstokenDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accesstokenDTO);
        GitHubUser githubUser =gitHubProvider.getUser(accessToken);
        if(githubUser!=null){
            //登录成功 写cookie session
            System.out.println(githubUser.getName());
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtcreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtcreate());
            userMapper.insert(user);
            request.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        }
        else{
            return "redirect:/";
        }
    }
}
