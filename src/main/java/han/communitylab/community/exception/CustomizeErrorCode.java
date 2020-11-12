package han.communitylab.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"你找的问题不在啦~换一个试试吧~"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行恢复"),
    NP_LOGIN(2003,"请登录！"),
    SYS_ERROR(2004,"服务器冒烟啦~要不您稍后试试~~"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "回复的评论不存在了");
    private String message;
    private Integer code;
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    CustomizeErrorCode(Integer code, String message){
        this.message=message;
        this.code=code;
    }
}
