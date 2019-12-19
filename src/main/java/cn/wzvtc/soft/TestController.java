package cn.wzvtc.soft;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://127.0.0.1:5500"})
@RestController
public class TestController {

    List<String> list = new ArrayList<>();

    @RequestMapping(value = "/lvli")
    public void lvli(String lvli) {
        this.list.add(lvli);
    }
    @RequestMapping(value = "/lvlilist")
    public List<String> lvli() {
        return this.list;
    }


    @RequestMapping(value = "/userinfo")
    public Map<String, String> userinfo(HttpServletRequest httpServletRequest) {

        Map<String, String> resultMap = new HashMap<>();
        //判断是否已经登陆,判断SESSION里面是否有我的用户名信息
        if (httpServletRequest.getSession().getAttribute("loginnumber") != null) {
            resultMap.put("myname", (String) httpServletRequest.getSession().getAttribute("loginnumber"));
            resultMap.put("mynumber", (String) httpServletRequest.getSession().getAttribute("username"));
        }
        return resultMap;
    }

    @RequestMapping(value = "/login")
    public Map<String, String> login(@RequestBody Map<String, String> map, HttpServletRequest httpServletRequest) {
        String number = map.get("number");
        String password = map.get("password");
        Map<String, String> resultMap = new HashMap<>();
        if (password.equals(number)) {
            //将用户名存入到session中
            httpServletRequest.getSession().setAttribute("loginnumber", number);
            httpServletRequest.getSession().setAttribute("username", number);
            resultMap.put("result", "success");
        }
        return resultMap;
    }
    @RequestMapping(value = "/logout")
    public void logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().removeAttribute("loginnumber");
    }


}