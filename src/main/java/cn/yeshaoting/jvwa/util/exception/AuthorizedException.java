package cn.yeshaoting.jvwa.util.exception;

/**
 * @description
 * @author yeshaoting
 * @date Mar 6, 2016 2:12:47 PM
 */
@SuppressWarnings("serial")
public class AuthorizedException extends IllegalStateException {

    public AuthorizedException(String message) {
        super(message);
    }
    
    public AuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

}
