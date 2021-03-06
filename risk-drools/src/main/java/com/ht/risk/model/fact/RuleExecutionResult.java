package com.ht.risk.model.fact;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：规则全局对象
 * CLASSPATH: com.sky.RuleExecutionResult
 * VERSION:   1.0
 * Created by lihao
 * DATE:      2017/7/25
 */
public class RuleExecutionResult implements Serializable {

    //规则执行中需要保存的数据
    private Map<String,Object> map = new HashMap<>();

    // 全局统计结果
    private int total;
    
    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
    
    
}
