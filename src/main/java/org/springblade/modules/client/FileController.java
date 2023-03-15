package org.springblade.modules.client;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.img.ImgUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.CollectionUtil;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.modules.archives.entity.ActiveCodeEntity;
import org.springblade.modules.archives.entity.FileDirectoryEntity;
import org.springblade.modules.archives.entity.FileEntity;
import org.springblade.modules.archives.service.IActiveCodeService;
import org.springblade.modules.archives.service.IDownloadRecordService;
import org.springblade.modules.archives.service.IFileDirectoryService;
import org.springblade.modules.archives.service.IFileService;
import org.springblade.modules.archives.vo.DownloadVO;
import org.springblade.modules.archives.vo.FileClientVO;
import org.springblade.modules.archives.vo.FileDirectoryVO;
import org.springblade.modules.archives.vo.FileVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController("clientFileController")
@RequestMapping("/blade-client/file")
public class FileController {


	@Resource
	private IFileDirectoryService fileDirectoryService;

	@Resource
	private IFileService fileService;

	@Resource
	private IActiveCodeService activeCodeService;

	@Resource
	private IDownloadRecordService downloadRecordService;

	@PostMapping("/directory")
	public R<List<FileDirectoryVO>> directory(@RequestBody  FileDirectoryVO fileDirectoryVO) {
		activeCodeService.verify(null, fileDirectoryVO.getCode(), fileDirectoryVO.getMac());
		return fileDirectoryService.directory(fileDirectoryVO);
	}

	@PostMapping("/files")
	public R<List<FileClientVO>> files(@RequestBody FileVO fileVO) {
		activeCodeService.verify(null, fileVO.getCode(), fileVO.getMac());
		List<FileDirectoryEntity> directoryEntities = fileDirectoryService.list(Wrappers.lambdaQuery(FileDirectoryEntity.class)
			.eq(FileDirectoryEntity::getType, fileVO.getType())
			.like(StringUtil.isNotBlank(fileVO.getName()), FileDirectoryEntity::getName, fileVO.getName())
			.orderByAsc(FileDirectoryEntity::getName));
		List<FileClientVO> fileClientVOS = BeanUtil.copyToList(directoryEntities, FileClientVO.class);
		Map<Long, FileClientVO> longList = fileClientVOS.stream().collect(Collectors.toMap(FileClientVO::getId, x -> x, (k1, k2) -> k1));
		List<FileVO> entities = fileService.files(fileVO);
		entities.forEach(o -> {
			if (o.getType() != 6) {
				o.setUrl(null);
			}
		});
		Map<Long, List<FileVO>> collect = entities.stream().filter(one -> StringUtil.isNotBlank(one.getDirectoryName()))
			.collect(Collectors.groupingBy(FileVO::getDirectoryId));
		collect.forEach((key, value) -> {
			FileClientVO fileClientVO = longList.get(key);
			if(fileClientVO != null) {
				fileClientVO.setFiles(BeanUtil.copyToList(value, FileClientVO.class));
			} else {
				fileClientVO = new FileClientVO();
				fileClientVO.setName(value.get(0).getDirectoryName());
				fileClientVO.setId(value.get(0).getDirectoryId());
				fileClientVO.setFiles(BeanUtil.copyToList(value, FileClientVO.class));
				longList.put(key, fileClientVO);
			}
		});
		List<FileClientVO> collect1 = longList.values().stream().sorted(Comparator.comparing(FileClientVO::getName)).collect(Collectors.toList());
		return R.data(collect1);
	}

	@PostMapping("/download")
	public R<String> download(@RequestBody DownloadVO downloadVO) {
		ActiveCodeEntity activeCodeEntity = activeCodeService.getOne(Wrappers.lambdaQuery(ActiveCodeEntity.class)
				.eq(ActiveCodeEntity::getCode, downloadVO.getCode())
				.eq(ActiveCodeEntity::getMac, downloadVO.getMac()));
		activeCodeService.verify(activeCodeEntity, downloadVO.getCode(), downloadVO.getMac());
		FileEntity fileEntity = fileService.getById(downloadVO.getFileId());
		if(fileEntity == null) {
			throw new ServiceException("文件不存在");
		}
		downloadRecordService.limitEnable(downloadVO.getCode(), downloadVO.getFileId());
		downloadRecordService.save(downloadVO.getCode(), downloadVO.getFileId(), 1);
		return R.data(fileEntity.getUrl());
	}

	@PostMapping("/preview")
	public R<String> preview(@RequestBody DownloadVO downloadVO) {
		ActiveCodeEntity activeCodeEntity = activeCodeService.getOne(Wrappers.lambdaQuery(ActiveCodeEntity.class)
			.eq(ActiveCodeEntity::getCode, downloadVO.getCode())
			.eq(ActiveCodeEntity::getMac, downloadVO.getMac()));
		activeCodeService.verify(activeCodeEntity, downloadVO.getCode(), downloadVO.getMac());
		FileEntity fileEntity = fileService.getById(downloadVO.getFileId());
		if(fileEntity == null) {
			throw new ServiceException("文件不存在");
		}
		downloadRecordService.save(downloadVO.getCode(), downloadVO.getFileId(), 2);
		return R.data(fileEntity.getUrl());
	}

}
