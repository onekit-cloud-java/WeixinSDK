package com.qq.weixin.api.sdk;

import cn.onekit.thekit.AJAX;
import cn.onekit.thekit.Crypto;
import cn.onekit.thekit.JSON;
import cn.onekit.thekit.STRING;
import com.google.gson.JsonObject;
import com.qq.weixin.api.WeixinAPI;
import com.qq.weixin.api.entity.*;

import java.util.HashMap;


public class WeixinSDK implements WeixinAPI {

     public String _crypto(String sig_method, String session_key, String data) throws Exception {
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
    public cgi_bin__token_response cgi_bin__token(String appid, String secret, String grant_type) {
        JsonObject result;
        try {
            String url = "https://api.weixin.qq.com/cgi-bin/token";
            result = (JsonObject) JSON.parse (AJAX.request(url, "get", new HashMap<String,String>(){{
                put("appid", appid);
                put("secret", secret);
                put("grant_type", grant_type);
            }}));
        } catch (Exception e) {
            return new cgi_bin__token_response();
        }
        if (result.has("error")) {
            return new cgi_bin__token_response();
        }

        return JSON.json2object(result,cgi_bin__token_response.class);
    }

    @Override
    public WeixinResponse wxa__checksession(String access_token,String openid,String signature,String sig_method,String body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/checksession?access_token=%s&openid=%s&signature=%s&sig_method=%s",access_token,openid,signature,sig_method);
            result = (JsonObject) JSON.parse(AJAX.request(url,"POST", body));
        } catch (Exception e) {
            e.printStackTrace();
            result =new JsonObject();
        }

        return JSON.json2object(result,WeixinResponse.class);
    }


    @Override
    public snc__jscode2session_response snc__jscode2session(String appid,String secret,String js_code,String grant_type) {
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

        return JSON.json2object(result,snc__jscode2session_response.class);
    }


    @Override
    public WeixinResponse wxa__img_sec_check(String access_token,wxa__img_sec_check_body body) {
        JsonObject reuslt;
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/img_sec_check?access_token=%s",access_token);
            JsonObject post_body = (JsonObject) JSON.object2json(body);
            reuslt = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            reuslt = new JsonObject();
        }
        return JSON.json2object(reuslt,WeixinResponse.class);
    }

    @Override
    public wxa__media_check_async_response wxa__media_check_async(String access_token,wxa__media_check_async_body body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/media_check_async?access_token=%s",access_token);
            JsonObject post_body = (JsonObject) JSON.object2json(body);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonObject();
        }
        return JSON.json2object(result,wxa__media_check_async_response.class);
    }

    @Override
    public WeixinResponse wxa__msg_sec_check(String access_token, wxa__msg_sec_check_body body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/msg_sec_check?access_token=%s",access_token);
            JsonObject post_body = (JsonObject) JSON.object2json(body);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonObject();
        }
        return JSON.json2object(result,WeixinResponse.class);

    }

    @Override
    public WeixinResponse wxa__remove_user_storage(String access_token, String openid, String signature, String sig_method, wxa__remove_user_storage_body body) {
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
        return JSON.json2object(result,WeixinResponse.class);
    }

    @Override
    public WeixinResponse wxa__setuserinteractivedata(String access_token, String openid, String signature, String sig_method, wxa__setuserinteractivedata_body body) {
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
        return JSON.json2object(result,WeixinResponse.class);
    }

    @Override
    public WeixinResponse wxa__set_user_storage(String access_token, String openid, String signature, String sig_method, wxa__set_user_storage_body body) {
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
        return JSON.json2object(result,WeixinResponse.class);
    }

    @Override
    public cgi_bin__message__wxopen__activityid__create_response cgi_bin__message__wxopen__activityid__create(String access_token, String unionid) {
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
        return JSON.json2object(result,cgi_bin__message__wxopen__activityid__create_response.class);
    }

    @Override
    public WeixinResponse cgi_bin__message__wxopen__updatablemsg__send(String access_token,updatablemsg__send_body body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/set_user_storage?access_token=%s",access_token);
            JsonObject post_body = (JsonObject) JSON.object2json(body);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonObject();
        }
        return JSON.json2object(result,WeixinResponse.class);
    }

    @Override
    public cgi_bin__wxaapp__createwxaqrcode_response cgi_bin__wxaapp__createwxaqrcode(String access_token,wxaapp__createwxaqrcode_body body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=%s",access_token);
            JsonObject post_body = (JsonObject) JSON.object2json(body);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonObject();
        }
        return JSON.json2object(result,cgi_bin__wxaapp__createwxaqrcode_response.class);
    }

    @Override
    public WeixinResponse wxa__getwxacode(String access_token,wxa__getwxacode_body body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/getwxacode?access_token=%s",access_token);
            JsonObject post_body = (JsonObject) JSON.object2json(body);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonObject();
        }
        return JSON.json2object(result,WeixinResponse.class);
    }

    @Override
    public wxa__getwxacodeunlimit_response wxa__getwxacodeunlimit(String access_token,wxa__getwxacodeunlimit_body body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=%s",access_token);
            JsonObject post_body = (JsonObject) JSON.object2json(body);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonObject();
        }
        return JSON.json2object(result,wxa__getwxacodeunlimit_response.class);
    }



    @Override
    public WeixinResponse cgi_bin__message__subscribe__send(String access_token,subscribe__send_body body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/datacube/getgameanalysisdata?access_token=%s",access_token);
            JsonObject post_body = (JsonObject) JSON.object2json(body);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonObject();
        }
        return JSON.json2object(result,WeixinResponse.class);
    }


}
