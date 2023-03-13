/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.modules.archives.service.impl;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import javax.annotation.Resource;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.modules.archives.entity.ActiveCodeEntity;
import org.springblade.modules.archives.entity.DownloadRecordEntity;
import org.springblade.modules.archives.service.IActiveCodeService;
import org.springblade.modules.archives.vo.DownloadRecordVO;
import org.springblade.modules.archives.mapper.DownloadRecordMapper;
import org.springblade.modules.archives.service.IDownloadRecordService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * 文件下载记录 服务实现类
 *
 * @author BladeX
 * @since 2023-03-11
 */
@Service
public class DownloadRecordServiceImpl extends BaseServiceImpl<DownloadRecordMapper, DownloadRecordEntity> implements
        IDownloadRecordService {

    @Resource
    private IActiveCodeService activeCodeService;
    @Override
    public IPage<DownloadRecordVO> selectDownloadRecordPage(IPage<DownloadRecordVO> page,
            DownloadRecordVO downloadRecord) {
        return page.setRecords(baseMapper.selectDownloadRecordPage(page, downloadRecord));
    }


    @Override
    public void limitEnableAndSave(String code, Long fileId) {
        long time = LocalDate.now().atStartOfDay()
                .toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        Long count = baseMapper.limitEnable(time, code, fileId);

        ActiveCodeEntity codeEntity = activeCodeService.getOne(Wrappers.lambdaQuery(ActiveCodeEntity.class)
                .eq(ActiveCodeEntity::getCode, code));
        if(codeEntity.getDownloadTimes() <= count) {
            throw new ServiceException("今日下载次数已用尽");
        }
        DownloadRecordEntity downloadRecordEntity = new DownloadRecordEntity();
        downloadRecordEntity.setCode(code);
        downloadRecordEntity.setFileId(fileId);
        downloadRecordEntity.setCreateTime(new Date());
        baseMapper.insert(downloadRecordEntity);
    }
}
