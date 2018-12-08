package com.itheima.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/upload")
public class FileUpload {
    /**
     * 使用springmvc的方式进行文件的上传
     *
     * @param request
     * @param upload
     * @return
     */
    @RequestMapping("/uploadTest")
    public String uploadTest(HttpServletRequest request, MultipartFile upload) {
        System.out.println("springmvc上传方式");

        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        //String path = request.getSession().getServletContext().getRealPath("/uploads/");
        System.out.println(path);
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        } else {
            try {
                //得到文件名
                String filename = upload.getOriginalFilename();
                String uuid = UUID.randomUUID().toString().replace("-", "");

                filename = uuid + "_" + filename;
                //上传
                upload.transferTo(new File(path, filename));
                System.out.println("上传方式成功");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return "success";
    }

    /**
     * 使用springmvc方式实现跨服务器的文件上传
     */

    @RequestMapping("/fileUploads")
    public String fileUploads(MultipartFile upload) throws IOException {

        System.out.println("使用跨服务器的方式进行文件的上传");
        /*定义文件上传的路径*/
        String path = "http://localhost:9090/uploads/";

        //获取上传的文件的名字
        String filename = upload.getOriginalFilename();
        String s = UUID.randomUUID().toString().replace("-", "");
        filename = s+"_"+filename;
        //创建clint对象
        Client client = Client.create();
        WebResource resource = client.resource(path + filename);

        System.out.println(path + filename);
        resource.put(upload.getBytes());
        return "success";
    }
}
