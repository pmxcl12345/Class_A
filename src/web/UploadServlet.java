package web;


import org.apache.catalina.core.ApplicationPart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by hp on 2017/6/4.
 */
@WebServlet(name = "UploadServlet",urlPatterns = "/web/UploadServlet")
public class UploadServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //  super.doPost(req, resp);
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String path = this.getServletContext().getRealPath("/");	//获取服务器地址
        Part p = req.getPart("file1");							//获取用户选择的上传文件
        if (p.getContentType().contains("image")) {					// 仅处理上传的图像文件
            ApplicationPart ap =  (ApplicationPart)p;
            String fname1 = ap.getFilename();						//获取上传文件名
            int path_idx = fname1.lastIndexOf("\\") + 1;			//对上传文件名进行截取
            String fname2 = fname1.substring(path_idx, fname1.length());
            p.write(path + "/upload/" + fname2);					// 写入 web 项目根路径下的upload文件夹中
            out.write("文件上传成功");
        }
        else{
            out.write("请选择图片文件！！！");
        }
    }
}
