package cn.yeshaoting.jvwa.mapper;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yeshaoting
 * @description
 * @date Mar 6, 2016 9:11:50 AM
 */
@Setter
@Getter
public class User {

    private Integer id;

    private String username;

    private int stage = 0;

    private int createTime;

    private int updateTime;

}
