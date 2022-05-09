package cn.lianxinstart.lianxinstartservernew.mapper;

import cn.lianxinstart.lianxinstartservernew.entity.URLS;
import cn.lianxinstart.lianxinstartservernew.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface URLMapper {
    //添加一条捷径
    @Insert("INSERT INTO `Lianxin-DB`.`URLS` (`name`, `url`, `underId`) VALUES (#{name}, #{url}, #{underId})")
    void addURL(URLS urls);
    //删除一条捷径
    @Delete("DELETE FROM `Lianxin-DB`.`URLS` WHERE id=#{id}")
    void delURL(URLS urls);
    //更新一条捷径
    @Update("UPDATE `Lianxin-DB`.`URLS` SET `name` = #{name}, `url` = #{url} WHERE `id` = #{id}")
    void updURL(URLS urls);
    //查询某个用户的自定义捷径
    @Select("select * from URLS where underId=#{user}")
    List<URLS> findAll(User user);
    //查询一条自定义捷径
    @Select("select * from URLS where url=#{url} and underId=#{underId}")
    List<URLS> findOne(URLS urls);
}
