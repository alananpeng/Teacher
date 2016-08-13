package com.hanboard.teacherhd.lib.common.exception;

/**
 * 项目名称：TeacherHD
 * 类描述：DataException
 * 创建人：peng.an@hanboard.com
 * 作者单位：四川汉博德信息技术有限公司
 * 创建时间：2016/7/28 0028 12:52
 */
public class DataException extends Exception{
    private String retCd ;  //异常对应的返回码
    private String msgDes;  //异常对应的描述信息
    public DataException() {
        super();
    }

    public DataException(String message) {
        super(message);
        msgDes = message;
    }

    public DataException(String retCd, String msgDes) {
        super();
        this.retCd = retCd;
        this.msgDes = msgDes;
    }

    public String getRetCd() {
        return retCd;
    }

    public String getMsgDes() {
        return msgDes;
    }
}
