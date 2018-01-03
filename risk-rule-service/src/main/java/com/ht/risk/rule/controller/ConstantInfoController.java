package com.ht.risk.rule.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.result.PageResult;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.ConstantInfo;
import com.ht.risk.rule.service.ConstantInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* @ClassName: ConstantInfoController
* @Description: 常量相关接口
* @author dyb
* @date 2018年1月2日 上午11:27:59
* 
*/
@RestController
@RequestMapping("/constantInfo")
@Api(tags = "ConstantInfoController", description = "常量对象相关api描述", hidden = true)
public class ConstantInfoController {

	@Autowired
	private ConstantInfoService constantInfoService;

	@GetMapping("page")
	@ApiOperation(value = "分页查询")
	public PageResult<List<ConstantInfo>> page(String key, Integer page, Integer limit) {
		Wrapper<ConstantInfo> wrapper = new EntityWrapper<>();
		if (StringUtils.isNotBlank(key)) {
			 wrapper.like("con_key", key);
			 wrapper.or().like("con_name", key);
			 wrapper.or().like("con_code", key);
		}
		wrapper.andNew("con_type<>1");
		Page<ConstantInfo> pages = new Page<>();
		pages.setCurrent(page);
		pages.setSize(limit);
		pages = constantInfoService.selectPage(pages, wrapper);
		return PageResult.success(pages.getRecords(), pages.getTotal());
	}

	@GetMapping("getAll")
	@ApiOperation(value = "查询所有的对象")
	public Result<List<ConstantInfo>> getAll(String key) {
		List<ConstantInfo> list = constantInfoService.selectList(null);
		// Page<EntityInfo> page = new Page<>();
		// page = entityInfoService.selectPage(page);

		return Result.success(list);
	}

	@GetMapping("delete/{id}")
	@ApiOperation(value = "通过id删除信息")
	public Result<Integer> delete(@PathVariable(name = "id") Long id) {
		constantInfoService.deleteById(id);
		return Result.success(0);
	}

	@GetMapping("getInfoById/{id}")
	@ApiOperation(value = "通过id查询详细信息")
	public Result<ConstantInfo> getDateById(@PathVariable(name = "id") Long id) {
		ConstantInfo entityInfo = constantInfoService.selectById(id);
		return Result.success(entityInfo);
	}

	@PostMapping("edit")
	@ApiOperation(value = "新增")
	@Transactional()
	public Result<Integer> edit(ConstantInfo entityInfo) {
		entityInfo.setCreTime(new Date());
		entityInfo.setIsEffect(1);
		constantInfoService.insertOrUpdate(entityInfo);
		return Result.success(0);
	}
}