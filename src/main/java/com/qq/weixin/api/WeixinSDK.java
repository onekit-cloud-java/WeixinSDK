package com.qq.weixin.api;

import cn.onekit.thekit.AJAX;
import cn.onekit.thekit.CRYPTO;
import cn.onekit.thekit.JSON;
import cn.onekit.thekit.STRING;
import com.google.gson.JsonObject;
import com.qq.weixin.api.entity.*;

import java.util.HashMap;


public class WeixinSDK implements WeixinAPI {

    public String _decrypt(String wx_encryptedData,String wx_iv,String wx_session_key) throws Exception {
        return new CRYPTO(CRYPTO.Key.AES, CRYPTO.Mode.PKCS5, 128).decrypt(wx_encryptedData, wx_iv, wx_session_key);
    }
    @Override
    public cgi_bin__token_response cgi_bin__token(String wx_appid, String wx_secret, String wx_grant_type) throws WeixinError{
        JsonObject result;
        try {
            String url = "https://api.weixin.qq.com/cgi-bin/token";
            result = (JsonObject) JSON.parse (AJAX.request(url, "get", new HashMap<String,String>(){{
                put("appid", wx_appid);
                put("secret", wx_secret);
                put("grant_type", wx_grant_type);
            }}));
        } catch (Exception e) {
            WeixinError error = new WeixinError();
            error.setErrcode(9527);
            error.setErrmsg(e.getMessage());
            throw error;
        }

        return JSON.json2object(result,cgi_bin__token_response.class);
    }

    @Override
    public WeixinResponse wxa__checksession(String wx_access_token,String openid,String signature,String sig_method,String wx_body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/checksession?access_token=%s&openid=%s&signature=%s&sig_method=%s",wx_access_token,openid,signature,sig_method);
            result = (JsonObject) JSON.parse(AJAX.request(url,"POST", wx_body));
        } catch (Exception e) {
            cgi_bin__token_response error = new cgi_bin__token_response();
            error.setErrcode(9527);
            error.setErrmsg(e.getMessage());
            return error;
        }

        return JSON.json2object(result,WeixinResponse.class);
    }


    @Override
    public snc__jscode2session_response snc__jscode2session(String wx_appid,String wx_secret,String wx_js_code,String wx_grant_type) {
        JsonObject result ;
        try {
            String url = "https://api.weixin.qq.com/sns/jscode2session";
            result  =(JsonObject) JSON.parse(AJAX.request(url,"get",new HashMap<String, String>(){{
                put("appid", wx_appid);
                put("secret", wx_secret);
                put("grant_type", wx_grant_type);
                if(!STRING.isEmpty(wx_js_code)){
                    put("js_code",wx_js_code);
                }
            }}));
        } catch (Exception e) {
            snc__jscode2session_response error = new snc__jscode2session_response();
            error.setErrcode(9527);
            error.setErrmsg(e.getMessage());
            return error;
        }

        return JSON.json2object(result,snc__jscode2session_response.class);
    }


    @Override
    public WeixinResponse wxa__img_sec_check(String wx_access_token,byte[] wx_body) {
        JsonObject reuslt;
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/img_sec_check?access_token=%s",wx_access_token);
            reuslt = (JsonObject) JSON.parse(AJAX.upload(url,wx_body));
        } catch (Exception e) {
            snc__jscode2session_response error = new snc__jscode2session_response();
            error.setErrcode(9527);
            error.setErrmsg(e.getMessage());
            return error;
        }
        return JSON.json2object(reuslt,WeixinResponse.class);
    }

    @Override
    public wxa__media_check_async_response wxa__media_check_async(String wx_access_token,wxa__media_check_async_body wx_body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/media_check_async?access_token=%s",wx_access_token);
            JsonObject post_body = (JsonObject) JSON.object2json(wx_body);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));
        } catch (Exception e) {
            wxa__media_check_async_response error = new wxa__media_check_async_response();
            error.setErrcode(9527);
            error.setErrmsg(e.getMessage());
            return error;
        }
        return JSON.json2object(result,wxa__media_check_async_response.class);
    }

    @Override
    public WeixinResponse wxa__msg_sec_check(String wx_access_token, wxa__msg_sec_check_body wx_body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/msg_sec_check?access_token=%s",wx_access_token);
            JsonObject post_body = (JsonObject) JSON.object2json(wx_body);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));
        } catch (Exception e) {
            WeixinResponse error = new WeixinResponse();
            error.setErrcode(9527);
            error.setErrmsg(e.getMessage());
            return error;
        }
        return JSON.json2object(result,WeixinResponse.class);

    }

    @Override
    public WeixinResponse wxa__remove_user_storage(String wx_access_token, String openid, String signature, String sig_method, wxa__remove_user_storage_body wx_body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/remove_user_storage?access_token=%s&signature=%s&openid=%s&sig_method=%s",
                    wx_access_token,openid,signature,sig_method);
            JsonObject post_body = (JsonObject) JSON.object2json(wx_body);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));

        } catch (Exception e) {
            WeixinResponse error = new WeixinResponse();
            error.setErrcode(9527);
            error.setErrmsg(e.getMessage());
            return error;
        }
        return JSON.json2object(result,WeixinResponse.class);
    }

    @Override
    public WeixinResponse wxa__setuserinteractivedata(String wx_access_token, String openid, String signature, String sig_method, wxa__setuserinteractivedata_body wx_body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/setuserinteractivedata?access_token=%s&signature=%s&openid=%s&sig_method=%s",
                    wx_access_token,signature,openid,sig_method);
            JsonObject post_body = (JsonObject) JSON.object2json(wx_body);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));
        } catch (Exception e) {
            WeixinResponse error = new WeixinResponse();
            error.setErrcode(9527);
            error.setErrmsg(e.getMessage());
            return error;
        }
        return JSON.json2object(result,WeixinResponse.class);
    }

    @Override
    public WeixinResponse wxa__set_user_storage(String wx_access_token, String openid, String signature, String sig_method, wxa__set_user_storage_body wx_body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/set_user_storage?access_token=%s&signature=%s&openid=%s&sig_method=%s",
                    wx_access_token,signature,openid,sig_method);
            JsonObject post_body = (JsonObject) JSON.object2json(wx_body);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));
        } catch (Exception e) {
            WeixinResponse error = new WeixinResponse();
            error.setErrcode(9527);
            error.setErrmsg(e.getMessage());
            return error;
        }
        return JSON.json2object(result,WeixinResponse.class);
    }

    @Override
    public cgi_bin__message__wxopen__activityid__create_response cgi_bin__message__wxopen__activityid__create(String wx_access_token, String unionid) {
        JsonObject result;
        try {
            String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/activityid/create";
            result = (JsonObject) JSON.parse(AJAX.request(url,"get",new HashMap<String, String>(){{
                put("access_token",wx_access_token);
                put("unionid",unionid);
            }}));
        } catch (Exception e) {
            cgi_bin__message__wxopen__activityid__create_response error = new cgi_bin__message__wxopen__activityid__create_response();
            error.setErrcode(9527);
            return error;
        }
        return JSON.json2object(result,cgi_bin__message__wxopen__activityid__create_response.class);
    }

    @Override
    public WeixinResponse cgi_bin__message__wxopen__updatablemsg__send(String wx_access_token,updatablemsg__send_body wx_body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/set_user_storage?access_token=%s",wx_access_token);
            JsonObject post_body = (JsonObject) JSON.object2json(wx_body);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));
        } catch (Exception e) {
            WeixinResponse error = new WeixinResponse();
            error.setErrcode(9527);
            error.setErrmsg(e.getMessage());
            return error;
        }
        return JSON.json2object(result,WeixinResponse.class);
    }

    @Override
    public byte[] cgi_bin__wxaapp__createwxaqrcode(String wx_access_token,wxaapp__createwxaqrcode_body wx_body) throws WeixinError {

        try {
            String url = String.format("https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=%s",wx_access_token);
            JsonObject post_body = (JsonObject) JSON.object2json(wx_body);
            return AJAX.download(url,"post",post_body.toString());
        } catch (Exception e) {
            WeixinError error = new WeixinError();
            error.setErrcode(9527);
            error.setErrmsg(e.getMessage());
            throw error;
        }
    }

    @Override
    public byte[] wxa__getwxacode(String wx_access_token,wxa__getwxacode_body wx_body) throws WeixinError{
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/getwxacode?access_token=%s",wx_access_token);
            JsonObject post_body = (JsonObject) JSON.object2json(wx_body);
            return AJAX.download(url,"post",post_body.toString());
        } catch (Exception e) {
            WeixinError error = new WeixinError();
            error.setErrcode(9527);
            error.setErrmsg(e.getMessage());
            throw error;
        }
    }

    @Override
    public byte[] wxa__getwxacodeunlimit(String wx_access_token,wxa__getwxacodeunlimit_body wx_body)throws WeixinError {
        try {
            String url = String.format("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=%s",wx_access_token);
            JsonObject post_body = (JsonObject) JSON.object2json(wx_body);
            return AJAX.download(url,"post",post_body.toString());
        } catch (Exception e) {
            WeixinError error = new WeixinError();
            error.setErrcode(9527);
            error.setErrmsg(e.getMessage());
            throw error;
        }
    }



    @Override
    public WeixinResponse cgi_bin__message__subscribe__send(String wx_access_token,subscribe__send_body wx_body) {
        JsonObject result;
        try {
            String url = String.format("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=%s",wx_access_token);
            JsonObject post_body = (JsonObject) JSON.object2json(wx_body);
            result = (JsonObject) JSON.parse(AJAX.request(url,"post",post_body.toString()));
        } catch (Exception e) {
            WeixinResponse error = new WeixinResponse();
            error.setErrcode(9527);
            error.setErrmsg(e.getMessage());
            return error;
        }
        return JSON.json2object(result,WeixinResponse.class);
    }


}
