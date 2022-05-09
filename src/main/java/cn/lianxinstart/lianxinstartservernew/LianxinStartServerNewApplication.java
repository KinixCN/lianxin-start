package cn.lianxinstart.lianxinstartservernew;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.lianxinstart.lianxinstartservernew.mapper")
public class LianxinStartServerNewApplication {
    public static void main(String[] args) {
        SpringApplication.run(LianxinStartServerNewApplication.class, args);
    }
}
