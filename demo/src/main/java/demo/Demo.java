package demo;


import cn.onekit.thekit.FileDB;
import com.qq.weixin.api.WeixinSDK;
import com.qq.weixin.api.request.Code2SessionRequest;
import com.qq.weixin.api.request.GetAccessTokenRequest;
import com.qq.weixin.api.request.GetPaidUnionIdRequest;
import com.qq.weixin.api.response.Code2SessionResponse;
import com.qq.weixin.api.response.GetAccessTokenResponse;
import com.qq.weixin.api.response.GetPaidUnionIdResponse;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;


@RestController
@RequestMapping("/")
public class Demo {
    @SuppressWarnings("FieldCanBeLocal")
    private final String sig_method = "hmac_sha256";
    private WeixinSDK sdk=new WeixinSDK("https://api.weixin.qq.com");
    @RequestMapping("/decrypt")
    public String decrypt(
            @RequestParam String session_key,
            @RequestParam String iv,
            @RequestParam String encryptedData,
            @RequestParam String rawData,
            @RequestParam String signature
    ) throws Exception {
        if(!sdk._signRaw(rawData,session_key).equals(signature)){
            throw new Exception("bad sign!!");
        }
        return sdk._decrypt(encryptedData,iv,session_key);
    }

    @RequestMapping("/code2session")
    public Code2SessionResponse code2session() throws Exception {
        Code2SessionRequest request  = new Code2SessionRequest();
        request.setAppid(WeixinAccount.appid);
        request.setSecret(WeixinAccount.secret);
        request.setJs_code("0330X5nl2OUKE64h3vml2mCiA620X5na");
        Code2SessionResponse code2SessionResponse = sdk.code2Session(request);
        FileDB.set("code2session","openid",code2SessionResponse.getOpenid());
        return code2SessionResponse;
    }

    @RequestMapping("/getAccessToken")
    public GetAccessTokenResponse getAccessToken() throws Exception {
        GetAccessTokenRequest request  = new GetAccessTokenRequest();
        request.setAppid(WeixinAccount.appid);
        request.setSecret(WeixinAccount.secret);
        GetAccessTokenResponse getAccessTokenResponse = sdk.getAccessToken(request);
        FileDB.set("getAccessToken","accesstoken",getAccessTokenResponse.getAccess_token());
        return getAccessTokenResponse;

    }

    @RequestMapping("/getPaidUnionId")
    public GetPaidUnionIdResponse getPaidUnionId() throws Exception {
        GetPaidUnionIdRequest request  = new GetPaidUnionIdRequest();
        String accesstoken = FileDB.get("getAccessToken","accesstoken").value;
        String openid = FileDB.get("code2session","openid").value;
        request.setAccess_token(accesstoken);
        request.setOpenid(openid);
        request.setTransaction_id("1217752501201407033233368018");
        return sdk.getPaidUnionId(request);
    }




}
