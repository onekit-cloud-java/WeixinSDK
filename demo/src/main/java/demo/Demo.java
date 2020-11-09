package demo;

import cn.onekit.thekit.JSON;
import com.qq.weixin.api.Media;
import com.qq.weixin.api.WeixinAPI;
import com.qq.weixin.api.sdk.WeixinSDK;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/weixin_demo")
public class Demo {
    final String sig_method = "hmac_sha256";

    @RequestMapping("/getAccessToken")
    public WeixinAPI.cgi_bin$token_response getAccessToken() throws Exception {
        return new WeixinSDK().cgi_bin$token(WeixinAccount.appid, WeixinAccount.secret, "client_credential");
    }

    @RequestMapping("/checkSessionKey")
    public WeixinAPI.wxa$checksession_response checkSessionKey(
            @RequestParam String access_token,
            @RequestParam String openid,
            @RequestParam String session_key,
            @RequestParam String data) throws Exception {
        String signature = WeixinSDK.crypto(sig_method, session_key,data);
        return new WeixinSDK().wxa$checksession(access_token,openid,signature,sig_method);
    }

    @RequestMapping("/code2Session")
    public WeixinAPI.snc$jscode2session_response code2Session(
            @RequestParam String js_code) throws Exception {
        return new WeixinSDK().snc$jscode2session(WeixinAccount.appid, WeixinAccount.secret, js_code,"client_credential");
    }

    @RequestMapping("/imgSecCheck")
    public WeixinAPI.wxa$img_sec_check_response imgSecCheck(
            @RequestParam String access_token) throws Exception {
        WeixinSDK WeixinSDK=new WeixinSDK();
        Media Media = new Media();
        Media.setContentType("image/png");
        Media.setValue(null);
        WeixinSDK.wxa$img_sec_check_body body = new WeixinSDK.wxa$img_sec_check_body();
        body.setMedia(Media);

        return new WeixinSDK().wxa$img_sec_check(access_token,body);

    }

    @RequestMapping("/mediaCheckAsync")
    public WeixinAPI.wxa$media_check_async_response mediaCheckAsync(
            @RequestParam String access_token) throws Exception {
        WeixinSDK.wxa$media_check_async_body body = new WeixinSDK.wxa$media_check_async_body();
        //body.setMedia_type(2);
        body.setMedia_url("");
        return new WeixinSDK().wxa$media_check_async(access_token,body);


    }

    @RequestMapping("/msgSecCheck")
    public WeixinAPI.wxa$msg_sec_check_response msgSecCheck(
            @RequestParam String access_token) throws Exception {
        WeixinSDK.wxa$msg_sec_check_body body = new WeixinSDK.wxa$msg_sec_check_body();
        body.setContent("");
        return new WeixinSDK().wxa$msg_sec_check(access_token,body);

    }

    @RequestMapping("/removeUserStorage")
    public WeixinAPI.wxa$remove_user_storage_response removeUserStorage(
            @RequestParam String access_token,
            @RequestParam String openid,
            @RequestParam String session_key,
            @RequestParam String data) throws Exception {
        String signature = WeixinSDK.crypto(sig_method, session_key,data);
        WeixinSDK.wxa$remove_user_storage_body body = new WeixinSDK.wxa$remove_user_storage_body();
        body.setKey("");
        return new WeixinSDK().wxa$remove_user_storage(access_token,openid,signature,sig_method,body);

    }

    @RequestMapping("/removeUserStorage")
    public WeixinAPI.wxa$setuserinteractivedata_response setUserInteractiveData(
            @RequestParam String access_token,
            @RequestParam String openid,
            @RequestParam String session_key,
            @RequestParam String data) throws Exception {
        String signature = WeixinSDK.crypto(sig_method, session_key,data);


        WeixinSDK.wxa$setuserinteractivedata_body body = new WeixinSDK.wxa$setuserinteractivedata_body();
        //body.setKv_list();
        return new WeixinSDK().wxa$setuserinteractivedata(access_token,openid,signature,sig_method,body);

    }



}
