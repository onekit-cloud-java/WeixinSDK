package demo;

import com.qq.weixin.api.WeixinAPI;
import com.qq.weixin.api.entity.*;
import com.qq.weixin.api.sdk.WeixinSDK;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;


@RestController
@RequestMapping("/weixin_demo")
public class Demo {
    final String sig_method = "hmac_sha256";

    @RequestMapping("/getAccessToken")
    public cgi_bin__token_response getAccessToken() throws Exception {
        return new WeixinSDK().cgi_bin__token(WeixinAccount.appid, WeixinAccount.secret, "client_credential");
    }

    @RequestMapping("/checkSessionKey")
    public WeixinResponse checkSessionKey(
            @RequestParam String access_token,
            @RequestParam String openid,
            @RequestParam String session_key) throws Exception {
        String body = "xx";
        String signature = new WeixinSDK()._crypto(sig_method, session_key,body);
        return new WeixinSDK().wxa__checksession(access_token,openid,signature,sig_method,body);
    }

    @RequestMapping("/code2Session")
    public snc__jscode2session_response code2Session(
            @RequestParam String js_code) throws Exception {
        return new WeixinSDK().snc__jscode2session(WeixinAccount.appid, WeixinAccount.secret, js_code,"client_credential");
    }

    @RequestMapping("/imgSecCheck")
    public WeixinResponse imgSecCheck(
            @RequestParam String access_token) throws Exception {
        Media Media = new Media();
        Media.setContentType("image/png");
        //
        File file = ResourceUtils.getFile("classpath:demo.png");
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        byte[] b = new byte[1024];

        int n;

        while ((n = fis.read(b)) != -1) {
            bos.write(b, 0, n);
        }

        byte[] data = bos.toByteArray();
        bos.close();
        fis.close();
        Media.setValue(data);
        //
        wxa__img_sec_check_body body = new wxa__img_sec_check_body();
        body.setMedia(Media);

        return new WeixinSDK().wxa__img_sec_check(access_token, body);

    }

    @RequestMapping("/mediaCheckAsync")
    public wxa__media_check_async_response mediaCheckAsync(
            @RequestParam String access_token) throws Exception {
        wxa__media_check_async_body body = new wxa__media_check_async_body();
        //body.setMedia_type(2);
        body.setMedia_url("");
        return new WeixinSDK().wxa__media_check_async(access_token,body);


    }

    @RequestMapping("/msgSecCheck")
    public WeixinResponse msgSecCheck(
            @RequestParam String access_token) throws Exception {
        wxa__msg_sec_check_body body = new wxa__msg_sec_check_body();
        body.setContent("");
        return new WeixinSDK().wxa__msg_sec_check(access_token,body);

    }

    @RequestMapping("/removeUserStorage")
    public WeixinResponse removeUserStorage(
            @RequestParam String access_token,
            @RequestParam String openid,
            @RequestParam String session_key,
            @RequestParam String data) throws Exception {
        String signature = new WeixinSDK()._crypto(sig_method, session_key,data);
        wxa__remove_user_storage_body body = new wxa__remove_user_storage_body();
        //body.setKey("");
        return new WeixinSDK().wxa__remove_user_storage(access_token,openid,signature,sig_method,body);

    }

    @RequestMapping("/setUserInteractiveData")
    public WeixinResponse setUserInteractiveData(
            @RequestParam String access_token,
            @RequestParam String openid,
            @RequestParam String session_key,
            @RequestParam String data) throws Exception {
        String signature = new WeixinSDK()._crypto(sig_method, session_key,data);


        wxa__setuserinteractivedata_body body = new wxa__setuserinteractivedata_body();
        //body.setKv_list();
        return new WeixinSDK().wxa__setuserinteractivedata(access_token,openid,signature,sig_method,body);

    }



}
