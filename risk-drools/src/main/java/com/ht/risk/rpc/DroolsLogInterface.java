package com.ht.risk.rpc;

import com.ht.risk.model.TestDroolsDetailLog;
import com.ht.risk.model.TestDroolsLog;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.ht.risk.model.DroolsLog;
import com.ht.risk.model.DroolsProcessLog;

@FeignClient("risk-log")
public interface DroolsLogInterface {
	
	@GetMapping("/droolsLog/save")
	public String saveLog(DroolsLog entity);
	
	@GetMapping("/droolsProcessLog/save")
	public String saveProcessLog(DroolsProcessLog entity);

	@GetMapping("/testDroolsLog/save")
	public String saveTestLog(TestDroolsLog entity);

	@GetMapping("/testDroolsDetailLog/save")
	public String saveTestDroolsDetailLog(TestDroolsDetailLog entity);
}
