package cn.lianxinstart.lianxinstartservernew.mapper;

import cn.lianxinstart.lianxinstartservernew.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    //添加一个用户
    @Insert("INSERT INTO `Lianxin-DB`.`User` (`user`) VALUES (#{user})")
    void addUser(User user);
    //查询一个用户
    @Select("select * from User where user=#{user}")
    List<User> findUser(User user);
}
