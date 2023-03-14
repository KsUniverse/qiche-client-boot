package org.springblade.modules.archives.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @Author wxy
 * @Date 2023/3/13 15:40
 * @Version 1.0
 **/
@Data
public class ActiveCodeInfomationVO {

    private String code;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

	private String phone;

	private String information;
}
