package han.communitylab.community.controller;

import han.communitylab.community.dto.AccessTokenDTO;
import han.communitylab.community.dto.GitHubUser;
import han.communitylab.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,@RequestParam(name = "state")String state){
        AccessTokenDTO accesstokenDTO = new AccessTokenDTO();
        accesstokenDTO.setClient_id(clientId);
        accesstokenDTO.setClient_secret(clientSecret);
        accesstokenDTO.setCode(code);
        accesstokenDTO.setRedirect_uri(redirectUri);
        accesstokenDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accesstokenDTO);
        GitHubUser user =gitHubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
