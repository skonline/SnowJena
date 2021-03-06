package cn.yueshutong.currentlimitingticketserver.monitor;

import cn.yueshutong.monitor.service.MonitorService;
import cn.yueshutong.monitor.entity.MonitorBean;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MonitorController {
    @Autowired
    MonitorService monitorService;

    @RequestMapping(value = "/monitor", method = RequestMethod.POST)
    @ResponseBody
    public String monitor(@RequestParam("data") String rule) {
        List<MonitorBean> monitorBeans = JSON.parseArray(rule, MonitorBean.class);
        monitorService.save(monitorBeans);
        return "OK";
    }

    @RequestMapping(value = "/monitor/json", method = RequestMethod.GET)
    @ResponseBody
    public List<MonitorBean> query(String app, String id, String name) {
        List<MonitorBean> monitorBeans = monitorService.getAll(app, id, name);
        monitorBeans.sort(null);
        return monitorBeans;
    }
}
