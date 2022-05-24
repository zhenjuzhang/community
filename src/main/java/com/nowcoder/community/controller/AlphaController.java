//项目依赖注入整体流程：由控制器AlphaController处理浏览器的请求，该过程中会调用业务组件AlphaService处理当前业务，业务组件又调用数据访问组件AlphaDao访问数据库（向service注入dao），
// 三者之间相互依赖，此依赖关系可通过依赖注入实现，通过注解的方式将它们委托给Spring容器
package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOError;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

//把四个注解（Controller（开发处理请求的组件）、Service（开发业务组件）、Repository（开发数据库访问组件）、Component（通用的））中的任意一个加到类/Bean上，则该类/Bean就能被容器扫描
@Controller
@RequestMapping("/alpha")//访问名，以便能够被浏览器访问到(在浏览器中通过“localhost:8080/alpha/hello”就可访问类的方法（tomcat启动后默认端口号为8080）)
public class AlphaController {

    //将service注入
    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")//在本程序停止运行时，服务终止，就不能再通过端口访问类的方法
    @ResponseBody
    public String sayHello(){
        return "Hello Spring Boot.";
    }

    //处理查询请求
    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    //Spring MVC框架下获得请求对象和相应对象（底层）
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) throws IOException {//请求和相应对象接口
        //利用request对象，获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();//迭代器对象
        //本循环是中间消息头的若干行数据
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();//获取请求行的名字（key）
            String value = request.getHeader(name);//key对应的value
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));//简单请求体，包含业务数据

        //利用response获取给浏览器返回响应数据
        response.setContentType("text/html;charset=utf-8");//返回响应的类型
//        try(
//                PrintWriter writer = response.getWriter();//获取输出流
//        ){
//            writer.write("<h1>牛客网</h1>");//向浏览器打印一个网页
//        } catch (IOException e){
//            e.printStackTrace();
//        }
        PrintWriter writer = response.getWriter();//获取输出流
        writer.write("<h1>牛客网</h1>");//向浏览器打印一个网页
    }

    //GET和POST区别：GET通过地址栏传输，产生一个TCP包，建议在数据查询时使用。POST通过报文传输，产生两个TCP数据包，建议在数据增删改时使用。
    // GET请求（默认请求），用于向服务器获取某些数据
    // 如通过“/students?current=1&limit=20”
    @RequestMapping(path = "/students", method = RequestMethod.GET)//GET请求用于向服务器获取数据。在String MVC框架下，获取浏览器中的一些参数在Get中
    @ResponseBody//响应
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,//对参数加上注解，处理首次访问没有“?current=1&limit=20”的情况
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    //根据学生编号查询一个学生，/student/123
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody//响应
    public String getStudent(@PathVariable("id") int id){//注解传参，获取浏览器参数
        System.out.println(id);
        return "a student";
    }

    //POST请求用于浏览器向服务器提交数据
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

//    //向浏览器返回响应数据（向浏览器响应html数据）
//    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
//    //不加@ResponseBody默认返回html
//    public ModelAndView modelAndView(){
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("name", "张三");
//        mav.addObject("age", 30);
//        mav.setViewName("/demo/view");//放在了templates文件下
//        return mav;
//    }

    //向浏览器返回响应数据（向浏览器响应html格式的数据）
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    //不加@ResponseBody时默认返回html
    public String getSchool(Model model){
        model.addAttribute("name", "xidian");
        model.addAttribute("age", 90);
        return "/demo/view";
    }

    //响应JSON数据（异步请求），Java对象 -> JSON字符串 -> JS对象
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "zhangsan");
        emp.put("age", 23);
        return emp;
    }
}

