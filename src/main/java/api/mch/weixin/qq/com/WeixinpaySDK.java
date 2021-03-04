package api.mch.weixin.qq.com;


import api.mch.weixin.qq.com.v3.WeixinpayAPI;
import api.mch.weixin.qq.com.v3.request.*;
import api.mch.weixin.qq.com.v3.response.*;
import com.qq.weixin.api.WeixinError;

public class WeixinpaySDK implements WeixinpayAPI {
    private String host;

    public  WeixinpaySDK(String host){
        this.host = host;
    }


    @Override
    public PayTransactionsJsapiResponse payTransactions(PayTransactionsJsapiRequest payRequest) throws WeixinError {
        return null;
    }

    @Override
    public PayTransactionsOrderqueryResponse payOrderquery(PayTransactionsOrderqueryRequest payTransactionsOrderqueryRequest) throws WeixinError {
        return null;
    }

    @Override
    public PayTransactionsOrderqueryResponse merchantOrderquery(MerchantOrderqueryRequest merchantOrderqueryRequest) throws WeixinError {
        return null;
    }

    @Override
    public void payTransactionsClose(PayTransactionsCloseRequest payTransactionsCloseRequest) throws WeixinError {

    }

    @Override
    public RefundResponse refund(RefundRequest refundRequest) throws WeixinError {
        return null;
    }

    @Override
    public RefundDomesticResponse refundDomestic(RefundDomesticRequest refundDomesticRequest) throws WeixinError {
        return null;
    }

    @Override
    public BillTradebillResponse billTradebill(BillTradebillRequest billTradebillRequest) throws WeixinError {
        return null;
    }

    @Override
    public BillFundflowbillResponse billFundflowbill(BillFundflowbillRequest billFundflowbillRequest) throws WeixinError {
        return null;
    }

    @Override
    public byte[] billdownload(String download_url, BilldownloadHeader billdownloadHeader) throws Exception {
        return new byte[0];
    }
}
