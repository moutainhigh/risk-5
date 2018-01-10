package org.ht.risk.log.service.impl;

import javax.annotation.Resource;

import org.ht.risk.log.entity.DroolsLog;
import org.ht.risk.log.mapper.DroolsLogMapper;
import org.ht.risk.log.service.DroolsLogService;
import org.springframework.stereotype.Service;

import com.ht.risk.common.service.impl.BaseServiceImpl;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张鹏
 * @since 2018-01-05
 */
@Service
public class DroolsLogServiceImpl extends BaseServiceImpl<DroolsLogMapper, DroolsLog> implements DroolsLogService {
	
		@Resource
	    private DroolsLogMapper droolsLogMapper;

		@Override
		public String saveLog(DroolsLog entity) {
			return null;
		}
		
		
}
