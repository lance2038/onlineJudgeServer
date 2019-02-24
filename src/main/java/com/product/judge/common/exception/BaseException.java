package com.product.judge.common.exception;

/**
 * @author haiyan
 * @version v0.0.1
 * @project judgeApi
 * @describe 异常处理基类
 * @since 2018/5/10
 **/
public class BaseException extends RuntimeException
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 具体的异常类
     */
    private Throwable cause = null;

    /**
     * 父类的构造方法
     */
    public BaseException()
    {
        super();
    }

    /**
     * 外部传入的中文错误信息
     *
     * @param msg 外部传入的中文错误信息
     */
    public BaseException(String msg)
    {
        super(msg);
    }

    /**
     * 外部传入中文错误信息和异常类信息
     *
     * @param msg 外部传入的中文错误信息
     * @param e   外部传入的异常类信息
     */
    public BaseException(String msg, Throwable e)
    {
        super(msg, e);
        this.cause = e;
    }

    public BaseException(Throwable cause)
    {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * 输出异常的StackTrace
     */
    public void printStackTrace()
    {
        super.printStackTrace();
        Throwable cause = getCause();
        if (cause != null)
        {
            cause.printStackTrace();
        }
    }

    public Throwable getCause()
    {
        return cause;
    }

    public String getMessage()
    {
        String message = super.getMessage();
        Throwable cause = getCause();
        if (cause != null)
        {
            return message + "; nested exception is " + cause;
        }
        return message;
    }
}
