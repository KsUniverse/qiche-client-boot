package org.springblade.modules.client;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.api.R;
import org.springblade.modules.archives.entity.FileEntity;
import org.springblade.modules.archives.service.IActiveCodeService;
import org.springblade.modules.archives.service.IFileDirectoryService;
import org.springblade.modules.archives.service.IFileService;
import org.springblade.modules.archives.vo.DownloadVO;
import org.springblade.modules.archives.vo.FileDirectoryVO;
import org.springblade.modules.archives.vo.FileVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController("clientFileController")
@RequestMapping("/blade-client/file")
public class FileController {


	@Resource
	private IFileDirectoryService fileDirectoryService;

	@Resource
	private IFileService fileService;

	@Resource
	private IActiveCodeService activeCodeService;

	@PostMapping("/directory")
	public R<List<FileDirectoryVO>> directory(@RequestBody  FileDirectoryVO fileDirectoryVO) {
		activeCodeService.verify(null, fileDirectoryVO.getCode(), fileDirectoryVO.getMac());
		return fileDirectoryService.directory(fileDirectoryVO);
	}

	@PostMapping("/files")
	public R<List<FileEntity>> files(@RequestBody FileVO fileVO) {
		activeCodeService.verify(null, fileVO.getCode(), fileVO.getMac());
		return R.data(fileService.list(Wrappers.lambdaQuery(FileEntity.class)
			.eq(FileEntity::getType, fileVO.getType())
			.eq(FileEntity::getDirectoryId, fileVO.getDirectoryId())
			.likeRight(FileEntity::getName, fileVO.getName())));
	}

	@PostMapping("/download")
	public R<String> download(@RequestBody DownloadVO downloadVO) {
		activeCodeService.verify(null, downloadVO.getCode(), downloadVO.getMac());
		FileEntity fileEntity = fileService.getById(downloadVO.getFileId());
		if(fileEntity == null) {
			throw new ServiceException("文件不存在");
		}
		return R.data(fileEntity.getUrl());
	}
}
