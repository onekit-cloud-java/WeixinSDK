package com.qq.weixin.api.sdk;

import cn.onekit.thekit.AJAX;
import cn.onekit.thekit.Crypto;
import cn.onekit.thekit.JSON;
import cn.onekit.thekit.STRING;
import com.google.gson.JsonObject;
import com.qq.weixin.api.WeixinAPI;

import java.util.HashMap;
import java.util.Map;

public class WeixinSDK extends WeixinAPI {

    static public String crypto(String sig_method, String session_key, String data) throws Exception {
        Crypto.Method method;
        switch (sig_method) {
            case "hmac_sha256":
                method = Crypto.Method.HMACSHA256;
                break;
            default:
                throw new Exception(sig_method);
        }
        return new Crypto(method).encode(session_key, data);
    }

    @Override
    public cgi_bin$token_response cgi_bin$token(String appid, String secret, String grant_type) {
         JsonObject result;
        try {
            result = (JsonObject) JSON.parse (AJAX.request("https://api.weixin.qq.com/cgi-bin/token", "get", new HashMap<String,String>(){{
                put("appid", appid);
                put("secret", secret);
                put("grant_type", grant_type);
            }}));
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonObject();
        }

        return JSON.json2object(result,cgi_bin$token_response.class);
    }

    @Override
    public snc$jscode2session_response snc$jscode2session(String appid,String secret,String js_code,String grant_type) {
        JsonObject result ;
        try {
            result  =(JsonObject) JSON.parse(AJAX.request("https://api.weixin.qq.com/wxa/checksession","get",new HashMap<String, String>(){{
                put("appid", appid);
                put("secret", secret);
                put("grant_type", grant_type);
                if(!STRING.isEmpty(js_code)){
                    put("js_code",js_code);
                }
            }}));
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonObject();
        }

        return JSON.json2object(result,snc$jscode2session_response.class);
    }

    @Override
    public wxa$checksession_response wxa$checksession(String s, String s1, String s2, String s3) {
        return null;
    }

    @Override
    public wxa$img_sec_check_response wxa$img_sec_check(String s, wxa$img_sec_check_body wxa$img_sec_check_body) {
        return null;
    }

    @Override
    public wxa$media_check_async_response wxa$media_check_async(String s, wxa$media_check_async_body wxa$media_check_async_body) {
        return null;
    }

    @Override
    public wxa$msg_sec_check_response wxa$msg_sec_check(String s, wxa$msg_sec_check_body wxa$msg_sec_check_body) {
        return null;
    }

    @Override
    public wxa$remove_user_storage_response wxa$remove_user_storage(String s, String s1, String s2, String s3, wxa$remove_user_storage_body wxa$remove_user_storage_body) {
        return null;
    }

    @Override
    public wxa$setuserinteractivedata_response wxa$setuserinteractivedata(String s, String s1, String s2, String s3, wxa$setuserinteractivedata_body wxa$setuserinteractivedata_body) {
        return null;
    }

    @Override
    public wxa$set_user_storage_response wxa$set_user_storage(String s, String s1, String s2, String s3, wxa$set_user_storage_body wxa$set_user_storage_body) {
        return null;
    }

    @Override
    public cgi_bin$message$wxopen$activityid$create_response cgi_bin$message$wxopen$activityid$create(String s, String s1) {
        return null;
    }

    @Override
    public cgi_bin$message$wxopen$updatablemsg$send_response cgi_bin$message$wxopen$updatablemsg$send(String s, updatablemsg$send_body updatablemsg$send_body) {
        return null;
    }

    @Override
    public cgi_bin$wxaapp$createwxaqrcode_response cgi_bin$wxaapp$createwxaqrcode(String s, wxaapp$createwxaqrcode_body wxaapp$createwxaqrcode_body) {
        return null;
    }

    @Override
    public wxa$getwxacode_response wxa$getwxacode(String s, wxa$getwxacode_body wxa$getwxacode_body) {
        return null;
    }

    @Override
    public wxa$getwxacodeunlimit_response wxa$getwxacodeunlimit(String s, wxa$getwxacodeunlimit_body wxa$getwxacodeunlimit_body) {
        return null;
    }

    @Override
    public datacube$getgameanalysisdata_response datacube$getgameanalysisdata(String s, datacube$getgameanalysisdata_body datacube$getgameanalysisdata_body) {
        return null;
    }

    @Override
    public wxa$createwxagameroom_response wxa$createwxagameroom(String s, wxa$createwxagameroom_body wxa$createwxagameroom_body) {
        return null;
    }

    @Override
    public wxa$getwxagameframe_response wxa$getwxagameframe(String s, String s1, long l, long l1) {
        return null;
    }

    @Override
    public wxa$getwxagameidentityinfo_response wxa$getwxagameidentityinfo(String s, String s1) {
        return null;
    }

    @Override
    public wxa$getwxagameroominfo_response wxa$getwxagameroominfo(String s, String s1) {
        return null;
    }

    @Override
    public cgi_bin$message$subscribe$send_response cgi_bin$message$subscribe$send(String s, subscribe$send_body subscribe$send_body) {
        return null;
    }


}
