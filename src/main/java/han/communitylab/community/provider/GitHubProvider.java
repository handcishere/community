package han.communitylab.community.provider;

import com.alibaba.fastjson.JSON;
import han.communitylab.community.dto.AccessTokenDTO;
import han.communitylab.community.dto.GitHubUser;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class GitHubProvider {
    @Value("${github.redirect.uri}")
    private String redirectUri;
    public String getAccessToken(AccessTokenDTO accesstokenDTO){
        MediaType mediaType= MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accesstokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token?client_id=ed39b5ebee3e8262f3ca&client_secret=024ee24fb8488196cb035e903990c3709ac09fcc&code="+accesstokenDTO.getCode()+"&redirect_uri="+redirectUri+"&state=1")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
             String string=response.body().string();
            String token = string.split("&")[0].split("=")[1];
            System.out.println(token);
            return token;

        } catch (IOException e) {
        }
        return null;
    }
    public GitHubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string=response.body().string();
            GitHubUser gitHubUser = JSON.parseObject(string, GitHubUser.class);
           // System.out.println(string);
            return gitHubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
