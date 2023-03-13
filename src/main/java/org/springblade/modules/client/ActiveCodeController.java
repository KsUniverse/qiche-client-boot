package org.springblade.modules.client;

import lombok.Data;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.api.R;
import org.springblade.modules.archives.service.IActiveCodeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("clientActiveCodeController")
@RequestMapping("/blade-client/activeCode")
public class ActiveCodeController {


	@Resource
	private IActiveCodeService activeCodeService;
	@PostMapping("/bind")
	public R<Boolean> bind(@RequestBody BindVO bindVO) {
		return R.status(activeCodeService.bind(bindVO.getCode(), bindVO.getMac()));
	}

	@PostMapping("/verify")
	public R<Boolean> verify(@RequestBody BindVO bindVO) {
		return R.status(activeCodeService.verify(null, bindVO.getCode(), bindVO.getMac()));
	}

	@Data
	public static class BindVO {
		private String code;

		private String mac;
	}
}
