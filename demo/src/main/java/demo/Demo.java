package demo;

import cn.onekit.thekit.JSON;
import com.qq.weixin.api.entity.*;
import com.qq.weixin.api.sdk.WeixinSDK;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;


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
        return new WeixinSDK().snc__jscode2session(WeixinAccount.appid, WeixinAccount.secret, js_code,"authorization_code");
    }

    @RequestMapping("/imgSecCheck")
    public WeixinResponse imgSecCheck(
            @RequestParam String access_token) throws Exception {
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
        //
        Media Media = new Media();
        Media.setContentType("image/png");
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
        body.setMedia_type(2);
        body.setMedia_url("#");
        return new WeixinSDK().wxa__media_check_async(access_token,body);


    }

    @RequestMapping("/msgSecCheck")
    public WeixinResponse msgSecCheck(
            @RequestParam String access_token) throws Exception {
        wxa__msg_sec_check_body body = new wxa__msg_sec_check_body();
        body.setContent("xx");
        return new WeixinSDK().wxa__msg_sec_check(access_token,body);

    }

    @RequestMapping("/removeUserStorage")
    public WeixinResponse removeUserStorage(
            @RequestParam String access_token,
            @RequestParam String openid,
            @RequestParam String session_key) throws Exception {

        wxa__remove_user_storage_body body = new wxa__remove_user_storage_body();

        body.setKey(new ArrayList<String>(){{add("key1");}});
        String signature = new WeixinSDK()._crypto(sig_method, session_key, JSON.object2string(body));

        return new WeixinSDK().wxa__remove_user_storage(access_token,openid,signature,sig_method,body);

    }

    @RequestMapping("/setUserInteractiveData")
    public WeixinResponse setUserInteractiveData(
            @RequestParam String access_token,
            @RequestParam String openid,
            @RequestParam String session_key) throws Exception {
        wxa__setuserinteractivedata_body body = new wxa__setuserinteractivedata_body();

        body.setKv_list(new ArrayList<KV<Integer>>(){{add(new KV<Integer>("1",0));}});
        String signature = new WeixinSDK()._crypto(sig_method, session_key,JSON.object2string(body));

        return new WeixinSDK().wxa__setuserinteractivedata(access_token,openid,signature,sig_method,body);

    }

    @RequestMapping("/setUserStorage")
    public WeixinResponse setUserStorage(
            @RequestParam String access_token,
            @RequestParam String openid,
            @RequestParam String session_key) throws Exception {
        wxa__set_user_storage_body body = new wxa__set_user_storage_body();

        body.setKv_list(new ArrayList<KV<String>>(){{add(new KV<String>("key1","value1"));}});
        String signature = new WeixinSDK()._crypto(sig_method, session_key,JSON.object2string(body));

        return new WeixinSDK().wxa__set_user_storage(access_token,openid,signature,sig_method,body);

    }

    @RequestMapping("/createActivityId")
    public cgi_bin__message__wxopen__activityid__create_response createActivityId(
            @RequestParam String access_token,
            @RequestParam String unionid) throws Exception {
        return new WeixinSDK().cgi_bin__message__wxopen__activityid__create(access_token,unionid);

    }

    @RequestMapping("/setUpdatableMsg")
    public WeixinResponse setUpdatableMsg(
            @RequestParam String access_token) throws Exception {
        updatablemsg__send_body body = new updatablemsg__send_body();
        body.setActivity_id("xxx");
        body.setTarget_state(1);
        Parameter parameter = new Parameter();
        parameter.setName("name1");
        parameter.setValue("value1");
        body.setTemplate_info(new ArrayList<Parameter>(){{add(parameter);}});
        return new WeixinSDK().cgi_bin__message__wxopen__updatablemsg__send(access_token,body);

    }

    @RequestMapping("/createQRCode")
    public String createQRCode(
            @RequestParam String access_token) throws Exception {
        wxaapp__createwxaqrcode_body body = new wxaapp__createwxaqrcode_body();
        body.setPath("index/index");
        body.setWidth(500);
        return  Base64.encodeBase64String(new WeixinSDK().cgi_bin__wxaapp__createwxaqrcode(access_token,body));

    }

    @RequestMapping("/get")
    public String get(
            @RequestParam String access_token) throws Exception {
        wxa__getwxacode_body body = new wxa__getwxacode_body();
        body.setPath("index/index");
        body.setWidth(500);
        body.setAuto_color(true);
        body.setIs_hyaline(true);
        RGB grb = new RGB();
        grb.setG(0);
        grb.setR(255);
        grb.setB(0);
        body.setLine_color(grb);
        return Base64.encodeBase64String(new WeixinSDK().wxa__getwxacode(access_token,body));

    }

    @RequestMapping("/getUnlimited")
    public String getUnlimited(
            @RequestParam String access_token) throws Exception {
        wxa__getwxacodeunlimit_body body = new wxa__getwxacodeunlimit_body();
        body.setScene("xxxxxxx");
        body.setPage("index/index");
        body.setWidth(500);
        body.setAuto_color(true);
        RGB grb = new RGB();
        grb.setG(0);
        grb.setR(0);
        grb.setB(255);
        body.setLine_color(grb);
        body.setIs_hyaline(true);
        return Base64.encodeBase64String( new WeixinSDK().wxa__getwxacodeunlimit(access_token,body));

    }

    @RequestMapping("/send")
    public WeixinResponse send(
            @RequestParam String access_token) throws Exception {
        subscribe__send_body body = new subscribe__send_body();
        body.setTouser("xxx");
        body.setTemplate_id("xxx");
        body.setPage("xxx");
        subscribe__send_body.Data.DataValue dataValue = new subscribe__send_body.Data.DataValue();
        dataValue.setValue("xxx");

        body.setData(new HashMap<String, subscribe__send_body.Data.DataValue>(){{put("xxx",dataValue);}});
        body.setMiniprogram_state("formal");
        body.setLang("zh_CN");

        return new WeixinSDK().cgi_bin__message__subscribe__send(access_token,body);
    }

}
