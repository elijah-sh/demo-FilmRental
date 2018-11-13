package com.hand.utils;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/8/20 17:09
 * @Description:
 */

public class SpringException extends  RuntimeException{
    private String exceptionMsg;
    public SpringException(String exceptionMsg) {
        System.out.println("-------------------------》 SpringException ： "+ exceptionMsg);
        this.exceptionMsg = exceptionMsg;
    }
    public String getExceptionMsg(){
        return this.exceptionMsg;
    }
    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

}
