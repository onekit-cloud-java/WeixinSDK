package com.qq.weixin.api;


import cn.onekit.thekit.AJAX;
import cn.onekit.thekit.CRYPTO;
import cn.onekit.thekit.JSON;
import com.google.gson.JsonObject;
import com.qq.weixin.api.request.*;
import com.qq.weixin.api.response.*;



@SuppressWarnings("unused")
public class WeixinSDK implements WeixinAPI {

    private final String host;

    public WeixinSDK(String host){
        this.host=host;
    }

    public String _decrypt(String wx_encryptedData,String wx_iv,String wx_session_key) throws Exception {
        return new CRYPTO(CRYPTO.Key.AES, CRYPTO.Mode.PKCS5, 128).decrypt(wx_encryptedData, wx_iv, wx_session_key);
    }


    @Override
    public Code2SessionResponse code2Session(Code2SessionRequest code2SessionRequest) throws WeixinError {
        try {
            String url = String.format("%s/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=%s", host,
                    code2SessionRequest.getAppid(),
                    code2SessionRequest.getSecret(),
                    code2SessionRequest.getJs_code(),
                    Code2SessionRequest.Grant_type.authorization_code
            );
            /////////////////////////////////////////
            JsonObject result = (JsonObject) JSON.parse(AJAX.request(url));
//            if (result.get("errcode").getAsInt() != 0) {
//                throw JSON.json2object(result, WeixinError.class);
//            }
            //////////////////////////////////////
            return JSON.json2object(result, Code2SessionResponse.class);
        } catch (WeixinError e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new WeixinError();
        }

    }

    @Override
    public GetPaidUnionIdResponse getPaidUnionId(GetPaidUnionIdRequest getPaidUnionIdRequest) throws WeixinError {
        try {
            if(getPaidUnionIdRequest.getTransaction_id()!=null){
                String url = String.format("%s/wxa/getpaidunionid?access_token=%s&openid=%s&transaction_id=%s", host,
                        getPaidUnionIdRequest.getAccess_token(),
                        getPaidUnionIdRequest.getOpenid(),
                        getPaidUnionIdRequest.getTransaction_id()
                );
                /////////////////////////////////////////
                JsonObject result = (JsonObject) JSON.parse(AJAX.request(url));
            /*if (result.get("errcode").getAsInt() != 0) {
                throw JSON.json2object(result, WeixinError.class);
            }*/
                //////////////////////////////////////
                return JSON.json2object(result, GetPaidUnionIdResponse.class);
            }else{
                String url = String.format("%s/wxa/getpaidunionid?access_token=%s&openid=%s&mch_id=%s&out_trade_no=%s", host,
                        getPaidUnionIdRequest.getAccess_token(),
                        getPaidUnionIdRequest.getOpenid(),
                        getPaidUnionIdRequest.getMch_id(),
                        getPaidUnionIdRequest.getOut_trade_no()
                );
                /////////////////////////////////////////
                JsonObject result = (JsonObject) JSON.parse(AJAX.request(url));
            /*if (result.get("errcode").getAsInt() != 0) {
                throw JSON.json2object(result, WeixinError.class);
            }*/
                //////////////////////////////////////
                return JSON.json2object(result, GetPaidUnionIdResponse.class);
            }

        } catch (WeixinError e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new WeixinError();
        }
    }

    @Override
    public GetAccessTokenResponse getAccessToken(GetAccessTokenRequest getAccessTokenRequest) throws WeixinError {
        try {
            String url = String.format("%s/cgi-bin/token?grant_type=%s&appid=%s&secret=%s", host,
                    GetAccessTokenRequest.Grant_type.client_credential,
                    getAccessTokenRequest.getAppid(),
                    getAccessTokenRequest.getSecret()
            );
            /////////////////////////////////////////
            JsonObject result = (JsonObject) JSON.parse(AJAX.request(url));
            /*if (result.get("errcode").getAsInt() != 0) {
                throw JSON.json2object(result, WeixinError.class);
            }*/
            //////////////////////////////////////
            return JSON.json2object(result, GetAccessTokenResponse.class);
        } catch (WeixinError e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new WeixinError();
        }
    }

    @Override
    public GetDailyRetainResponse getDailyRetain(GetDailyRetainRequest getDailyRetainRequest) {
        return null;
    }

    @Override
    public GetMonthlyRetainResponse getMonthlyRetain(GetMonthlyRetainRequest getMonthlyRetainRequest) {
        return null;
    }

    @Override
    public GetWeeklyRetainResponse getWeeklyRetain(GetWeeklyRetainRequest getWeeklyRetainRequest) {
        return null;
    }

    @Override
    public GetDailySummaryResponse getDailySummary(GetDailySummaryRequest getDailySummaryRequest) {
        return null;
    }

    @Override
    public GetDailyVisitTrendResponse getDailyVisitTrend(GetDailyVisitTrendRequest getDailyVisitTrendRequest) {
        return null;
    }

    @Override
    public GetMonthlyVisitTrendResponse getMonthlyVisitTrend(GetMonthlyVisitTrendRequest getMonthlyVisitTrendRequest) {
        return null;
    }

    @Override
    public GetWeeklyVisitTrendResponse getWeeklyVisitTrend(GetWeeklyVisitTrendRequest getWeeklyVisitTrendRequest) {
        return null;
    }

    @Override
    public GetPerformanceDataResponse getPerformanceData(GetPerformanceDataRequest getPerformanceDataRequest) {
        return null;
    }

    @Override
    public GetUserPortraitResponse getUserPortrait(GetUserPortraitRequest getUserPortraitRequest) {
        return null;
    }

    @Override
    public GetVisitDistributionResponse getVisitDistribution(GetVisitDistributionRequest getVisitDistributionRequest) {
        return null;
    }

    @Override
    public GetVisitPageResponse getVisitPage(GetVisitPageRequest getVisitPageRequest) {
        return null;
    }

    @Override
    public byte[] getTempMedia(GetTempMediaRequest getTempMediaRequest) throws WeixinError {
        return new byte[0];
    }

    @Override
    public WeixinResponse sendcustomerServiceMessage(SendcustomerServiceMessageRequest sendcustomerServiceMessageRequest) {
        return null;
    }

    @Override
    public WeixinResponse setTyping(SetTypingRequest setTypingRequest) {
        return null;
    }

    @Override
    public UploadTempMediaResponse uploadTempMedia(UploadTempMediaRequest uploadTempMediaRequest) {
        return null;
    }

    @Override
    public WeixinResponse uniformsend(UniformsendRequest uniformsendRequest) {
        return null;
    }

    @Override
    public CreateActivityIdResponse createActivityId(CreateActivityIdRequest createActivityIdRequest) {
        return null;
    }

    @Override
    public WeixinResponse setUpdatableMsg(SetUpdatableMsgRequest setUpdatableMsgRequest) {
        return null;
    }

    @Override
    public WeixinResponse applyPlugin(ApplyPluginRequest applyPluginRequest) {
        return null;
    }

    @Override
    public GetPluginDevApplyListResponse getPluginDevApplyList(GetPluginDevApplyListRequest getPluginDevApplyListRequest) throws WeixinError {
        return null;
    }

    @Override
    public GetPluginListResponse getPluginList(GetPluginListRequest getPluginListRequest) throws WeixinError {
        return null;
    }

    @Override
    public WeixinResponse setDevPluginApplyStatus(SetDevPluginApplyStatusRequest setDevPluginApplyStatusRequest) throws WeixinError {
        return null;
    }

    @Override
    public WeixinResponse unbindPlugin(UnbindPluginRequest unbindPluginRequest) throws WeixinError {
        return null;
    }

    @Override
    public AddResponse add(AddRequest addRequest) {
        return null;
    }

    @Override
    public DeleteResponse delete(DeleteRequest deleteRequest) throws WeixinError {
        return null;
    }

    @Override
    public GetListResponse getList(GetListRequest getListRequest) throws WeixinError {
        return null;
    }

    @Override
    public WeixinResponse setShowStatus(SetShowStatusRequest setShowStatusRequest) throws WeixinError {
        return null;
    }

    @Override
    public byte[] createQRCode(CreateQRCodeRequest createQRCodeRequest) throws WeixinError {
        return new byte[0];
    }

    @Override
    public byte[] get(GetRequest getRequest) throws WeixinError {
        return new byte[0];
    }

    @Override
    public byte[] getUnlimited(GetUnlimitedRequest getUnlimitedRequest) throws WeixinError {
        return new byte[0];
    }

    @Override
    public String generate(GenerateRequest generateRequest) throws WeixinError {
        return null;
    }

    @Override
    public WeixinResponse imgSecCheck(ImgSecCheckRequest imgSecCheckRequest) {
        return null;
    }

    @Override
    public MediaCheckAsyncResponse mediaCheckAsync(MediaCheckAsyncRequest mediaCheckAsyncRequest) {
        return null;
    }

    @Override
    public WeixinResponse msgSecCheck(MsgSecCheckRequest msgSecCheckRequest) {
        return null;
    }

    @Override
    public GetOpenDataResponse getOpenData(GetOpenDataRequest getOpenDataRequest) {
        return null;
    }

    @Override
    public GetVoIPSignResponse getVoIPSign(GetVoIPSignRequest getVoIPSignRequest) {
        return null;
    }

    @Override
    public SendSmsResponse sendSms(SendSmsRequest sendSmsRequest) {
        return null;
    }

    @Override
    public WeixinResponse aiCrop(AiCropRequest aiCropRequest) {
        return null;
    }

    @Override
    public WeixinResponse scanQRCode(ScanQRCodeRequest scanQRCodeRequest) {
        return null;
    }

    @Override
    public WeixinResponse superresolution(SuperresolutionRequest superresolutionRequest) {
        return null;
    }
}
