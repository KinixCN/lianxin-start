package cn.lianxinstart.lianxinstartservernew.controller;

import cn.lianxinstart.lianxinstartservernew.GetProtocol;
import cn.lianxinstart.lianxinstartservernew.WebUtil;
import cn.lianxinstart.lianxinstartservernew.entity.URLS;
import cn.lianxinstart.lianxinstartservernew.entity.User;
import cn.lianxinstart.lianxinstartservernew.mapper.BackgroundsMapper;
import cn.lianxinstart.lianxinstartservernew.mapper.URLMapper;
import cn.lianxinstart.lianxinstartservernew.mapper.UserMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class Controller {
    @Resource
    UserMapper userMapper;
    @Resource
    URLMapper urlMapper;
    @Resource
    BackgroundsMapper backgroundsMapper;

    //添加一个用户
    /*
    * 所需：
    * user*/
    @PostMapping("/user/add")
    public List<User> addUser(@RequestBody User user) {
        List<User> users = userMapper.findUser(user);
        List<User> userResult = null;
        if(users.isEmpty()) {
            userMapper.addUser(user);
            userResult = userMapper.findUser(user);
        }else {
            userResult = users;
        }
        return userResult;
    }
    //查询一个用户
    /*
    * 所需：
    * user*/
    @PostMapping("/user/find")
    public List<User> findUser(@RequestBody User user) {
        return userMapper.findUser(user);
    }

    //查询某个用户的自定义捷径
    /*
    * 所需：
    * user*/
    @PostMapping("/target/find")
    public List<URLS> findURLSByUser(@RequestBody User user) {
        return urlMapper.findAll(user);
    }
    //添加一个自定义捷径
    /*
    * 需要：
    * name
    * url
    * underId*/
    @PostMapping("/target/add")
    public List<URLS> addURLS(@RequestBody URLS urls) {
        URLS target = urls;
        String url = target.getUrl();
        String finalUrl = null;
        finalUrl = GetProtocol.getProtocol(url);
        target.setUrl(finalUrl);
        List<URLS> urlsList = urlMapper.findOne(target);
        List<URLS> urlResult = null;
        if(urlsList.isEmpty()) {
            urlMapper.addURL(target);
        }
        urlResult = urlMapper.findOne(target);
        return urlResult;
    }
    //查询一条捷径
    /*
    * 需要：
    * url
    * underId*/
    @PostMapping("/target/findme")
    public List<URLS> findOneUrl(@RequestBody URLS urls) {
        return urlMapper.findOne(urls);
    }
    //更新一条捷径
    /*
    需要：
    id
    *url
    *name
     */
    @PostMapping("/target/upd")
    public List<URLS> updUrl(@RequestBody URLS urls) {
        urlMapper.updURL(urls);
        return urlMapper.findOne(urls);
    }
    //删除一条捷径
    /*
    * 需要：
    * id*/
    @PostMapping("/target/del")
    public String delUrl(@RequestBody URLS urls) {
        urlMapper.delURL(urls);
        return "success";
    }
}
