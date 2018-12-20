package post.klay;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class SendPost {
    /**
     * 发送json请求到指定url
     * @param url
     * @param jsonData
     * @return
     */
    public ResponseEntity send(String url,String jsonData){
        if ("".equals(url)) return null;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        //设置请求头的类型，以及添加authorization,身份验证
        headers.setContentType(type);
        headers.add("Authorization",
                "NLAuth nlauth_account=5179288_SB1,nlauth_email=newton.chen@hitpointcloud.com,nlauth_signature=Netsuite123, nlauth_role=1000");
        //请求实体
        HttpEntity<String> httpEntity =
                new HttpEntity<String>(jsonData, headers);
        //响应体，调用restTemplate 的exchange方法
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        return responseEntity;
    }
}
