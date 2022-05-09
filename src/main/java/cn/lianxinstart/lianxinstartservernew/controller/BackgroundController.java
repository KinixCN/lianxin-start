package cn.lianxinstart.lianxinstartservernew.controller;

import cn.lianxinstart.lianxinstartservernew.entity.Backgrounds;
import cn.lianxinstart.lianxinstartservernew.mapper.BackgroundsMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BackgroundController {
    SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");

    @Resource
    BackgroundsMapper backgroundsMapper;

    @PostMapping("/upload")
    public Map<String, Object> imgUpload(MultipartFile file, HttpServletRequest req, String sduId) {
        Map<String, Object> result = new HashMap<>();
        String originName = file.getOriginalFilename();
        if(!originName.endsWith(".jpg") && !originName.endsWith(".jpeg") && !originName.endsWith(".png") && !originName.endsWith(".bmp")) {
            result.put("status", "error");
            result.put("msg", "文件类型不对");
            return result;
        }
//        String format = sdf.format(new Date());
//        System.out.println(format);
        String realPath = req.getServletContext().getRealPath("/") + "/backgrounds/";
        File folder = new File(realPath);
        if(!folder.exists()) {
            folder.mkdirs();
        }
        String endWith = null;
        if(originName.endsWith(".jpg")) {
            endWith = ".jpg";
        }else if(originName.endsWith(".jpeg")) {
            endWith = ".jpeg";
        }else if(originName.endsWith(".png")) {
            endWith = ".png";
        }else if(originName.endsWith(".bmp")) {
            endWith = ".bmp";
        }
        String newName = sduId + endWith;
        try {
            file.transferTo(new File(folder, newName));
            String url = "backgrounds/" + newName;
            result.put("status","success");
            result.put("url",url);
            Backgrounds backgrounds = new Backgrounds();
            backgrounds.setUnderId(sduId);
            backgrounds.setUrl(url);
            List<Backgrounds> originBack = backgroundsMapper.findOneBack(backgrounds);
            if(originBack.isEmpty()) {
                backgroundsMapper.addBack(backgrounds);
            } else {
                backgroundsMapper.updBack(backgrounds);
            }
        } catch (IOException e) {
            result.put("status", "error");
            result.put("msg", e.getMessage());
        }
        return result;
    }

    @PostMapping("/back/get")
    public List<Backgrounds> getBackGrounds (@RequestBody Backgrounds backgrounds) {
        return backgroundsMapper.findOneBack(backgrounds);
    }
}
