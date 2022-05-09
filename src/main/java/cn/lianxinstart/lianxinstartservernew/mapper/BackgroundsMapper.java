package cn.lianxinstart.lianxinstartservernew.mapper;

import cn.lianxinstart.lianxinstartservernew.entity.Backgrounds;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BackgroundsMapper {
    //添加一张背景图片
    @Insert("INSERT INTO `Lianxin-DB`.`Backgrounds` (`underId`, `url`) VALUES (#{underId}, #{url})")
    void addBack(Backgrounds backgrounds);
    //替换一张背景照片
    @Update("UPDATE `Lianxin-DB`.`Backgrounds` SET `url` = #{url} WHERE `underId` = #{underId}")
    void updBack(Backgrounds backgrounds);
    //获取一张背景图片
    @Select("select * from Backgrounds where underId = #{underId}")
    List<Backgrounds> findOneBack(Backgrounds backgrounds);
}
