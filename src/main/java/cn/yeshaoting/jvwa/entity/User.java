package cn.yeshaoting.jvwa.entity;

/**
 * @description
 * @author yeshaoting
 * @date Mar 6, 2016 9:11:50 AM
 */
public class User extends Base {

    private String username;

    private int stage = 0;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

}
