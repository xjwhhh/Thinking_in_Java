package web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Greet extends HttpServlet {
    //request和response分别是为请求对象和响应对象选择的引用变量名。
    // HttpServletRequest对象参数request包含了客户端的请求
    //HttpServletResponse对象参数response提供了传送由servlet发送给客户端的响应的方法
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //返回给发出请求的客户端的servlet输出通常是通过定义printwriter对象来创建的，而printwriter对象是通过调用相应对象的getwriter方法创建的
        //在使用printwriter的方法创建任何输出之前需要设置返回文档的内容类型
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>");
        out.println("A simple GET servlet");
        out.println("</title></head><body>");
        out.println("<h2>This is your servlet answering - hi!</h2>");
        out.println("</body></html>");
    }
}

