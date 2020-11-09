package com.qq.weixin.api.sdk;

import cn.onekit.thekit.AJAX;
import cn.onekit.thekit.Crypto;
import cn.onekit.thekit.JSON;
import cn.onekit.thekit.STRING;
import com.google.gson.JsonObject;
import com.qq.weixin.api.WeixinAPI;

import java.util.HashMap;


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
            String url = "https://api.weixin.qq.com/cgi-bin/token";
            result = (JsonObject) JSON.parse (AJAX.request(url, "get", new HashMap<String,String>(){{
                put("appid", appid);
                put("secret", secret);
                put("grant_type", grant_type);
            }}));
        } catch (Exception e) {
            return new cgi_bin$token_response();
        }
        if (result.has("error")) {
            return new cgi_bin$token_response();
        }

        return JSON.json2object(result,cgi_bin$token_response.class);
    }

    @Override
    public wxa$checksession_response wxa$checksession(String access_token,String openid,String signature,String sig_method) {
        JsonObject result;
        try {
            String url = "https://api.weixin.qq.com/wxa/checksession";
            result = (JsonObject) JSON.parse(AJAX.request(url,"get",new HashMap<String, String>(){{
                put("appid", access_token);
                put("secret", openid);
                put("signature", signature);
                put("sig_method",sig_method);
            }}));
        } catch (Exception e) {
            e.printStackTrace();
            result =new JsonObject();
        }

        return JSON.json2object(result,wxa$checksession_response.class);
    }


    @Override
    public snc$jscode2session_response snc$jscode2session(String appid,String secret,String js_code,String grant_type) {
        JsonObject result ;
        try {
            String url = "https://api.weixin.qq.com/sns/jscode2session";
            result  =(JsonObject) JSON.parse(AJAX.request(url,"get",new HashMap<String, String>(){{
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
    public wxa$img_sec_check_response wxa$img_sec_check(String access_token,wxa$img_sec_check_body body) {
        JsonObject reuslt;
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/img_sec_check?access_token=%s",access_token);
            JsonObject post_body = (JsonObject) JSON.object2json(body);
            reuslt = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            reuslt = new JsonObject();
        }
        return JSON.json2object(reuslt,wxa$img_sec_check_response.class);
    }

    @Override
    public wxa$media_check_async_response wxa$media_check_async(String access_token,wxa$media_check_async_body body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/media_check_async?access_token=%s",access_token);
            JsonObject post_body = (JsonObject) JSON.object2json(body);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonObject();
        }
        return JSON.json2object(result,wxa$media_check_async_response.class);
    }

    @Override
    public wxa$msg_sec_check_response wxa$msg_sec_check(String access_token, wxa$msg_sec_check_body body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/msg_sec_check?access_token=%s",access_token);
            JsonObject post_body = (JsonObject) JSON.object2json(body);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonObject();
        }
        return JSON.json2object(result,wxa$msg_sec_check_response.class);

    }

    @Override
    public wxa$remove_user_storage_response wxa$remove_user_storage(String access_token,String openid,String signature,String sig_method,wxa$remove_user_storage_body body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/remove_user_storage?access_token=%s&signature=%s&openid=%s&sig_method=%s",
                    access_token,openid,signature,sig_method);
            JsonObject post_body = (JsonObject) JSON.object2json(body);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));

        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonObject();
        }
        return JSON.json2object(result,wxa$remove_user_storage_response.class);
    }

    @Override
    public wxa$setuserinteractivedata_response wxa$setuserinteractivedata(String access_token, String openid, String signature, String sig_method, wxa$setuserinteractivedata_body body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/setuserinteractivedata?access_token=%s&signature=%s&openid=%s&sig_method=%s",
                    access_token,signature,openid,sig_method);
            JsonObject post_body = (JsonObject) JSON.object2json(body);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonObject();
        }
        return JSON.json2object(result,wxa$setuserinteractivedata_response.class);
    }

    @Override
    public wxa$set_user_storage_response wxa$set_user_storage(String access_token, String openid, String signature, String sig_method, wxa$set_user_storage_body body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/set_user_storage?access_token=%s&signature=%s&openid=%s&sig_method=%s",
                    access_token,signature,openid,sig_method);
            JsonObject post_body = (JsonObject) JSON.object2json(body);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonObject();
        }
        return JSON.json2object(result,wxa$set_user_storage_response.class);
    }

    @Override
    public cgi_bin$message$wxopen$activityid$create_response cgi_bin$message$wxopen$activityid$create(String access_token, String unionid) {
        JsonObject result;
        try {
            String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/activityid/create";
            result = (JsonObject) JSON.parse(AJAX.request(url,"get",new HashMap<String, String>(){{
                put("access_token",access_token);
                put("unionid",unionid);
            }}));
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonObject();
        }
        return JSON.json2object(result,cgi_bin$message$wxopen$activityid$create_response.class);
    }

    @Override
    public cgi_bin$message$wxopen$updatablemsg$send_response cgi_bin$message$wxopen$updatablemsg$send(String access_token,updatablemsg$send_body body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/set_user_storage?access_token=%s",access_token);
            JsonObject post_body = (JsonObject) JSON.object2json(body);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonObject();
        }
        return JSON.json2object(result,cgi_bin$message$wxopen$updatablemsg$send_response.class);
    }

    @Override
    public cgi_bin$wxaapp$createwxaqrcode_response cgi_bin$wxaapp$createwxaqrcode(String access_token,wxaapp$createwxaqrcode_body body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=%s",access_token);
            JsonObject post_body = (JsonObject) JSON.object2json(body);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonObject();
        }
        return JSON.json2object(result,cgi_bin$wxaapp$createwxaqrcode_response.class);
    }

    @Override
    public wxa$getwxacode_response wxa$getwxacode(String access_token,wxa$getwxacode_body body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/getwxacode?access_token=%s",access_token);
            JsonObject post_body = (JsonObject) JSON.object2json(body);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonObject();
        }
        return JSON.json2object(result,wxa$getwxacode_response.class);
    }

    @Override
    public wxa$getwxacodeunlimit_response wxa$getwxacodeunlimit(String access_token,wxa$getwxacodeunlimit_body body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=%s",access_token);
            JsonObject post_body = (JsonObject) JSON.object2json(body);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonObject();
        }
        return JSON.json2object(result,wxa$getwxacodeunlimit_response.class);
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
    public cgi_bin$message$subscribe$send_response cgi_bin$message$subscribe$send(String access_token,subscribe$send_body body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/datacube/getgameanalysisdata?access_token=%s",access_token);
            JsonObject post_body = (JsonObject) JSON.object2json(body);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonObject();
        }
        return JSON.json2object(result,cgi_bin$message$subscribe$send_response.class);
    }


}
