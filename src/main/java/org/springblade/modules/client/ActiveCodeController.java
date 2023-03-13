package org.springblade.modules.client;

import lombok.Data;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.archives.entity.ActiveCodeEntity;
import org.springblade.modules.archives.service.IActiveCodeService;
import org.springblade.modules.archives.vo.ActiveCodeInfomationVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

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

	@PostMapping("/information")
	public R<ActiveCodeInfomationVO> information(@RequestBody BindVO bindVO) {
		activeCodeService.verify(null, bindVO.getCode(), bindVO.getMac());
		ActiveCodeEntity activeCodeEntity = activeCodeService.getOne(Wrappers.lambdaQuery(ActiveCodeEntity.class)
				.eq(ActiveCodeEntity::getCode, bindVO.getCode())
				.eq(ActiveCodeEntity::getMac, bindVO.getMac()));
		if(activeCodeEntity == null) {
			throw new ServiceException("不存在的绑定信息");
		}
		return R.data(BeanUtil.copy(activeCodeEntity, ActiveCodeInfomationVO.class));
	}

	@Data
	public static class BindVO {
		private String code;

		private String mac;
	}
}
