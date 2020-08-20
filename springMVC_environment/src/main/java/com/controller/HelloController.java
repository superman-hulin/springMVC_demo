package com.controller;

import com.exception.SysException;
import com.pojo.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping(path = "/user")
public class HelloController {
    /**
    *@Description: @RequestMapping的参数
    *@Param:  path、value是等价的
     *        method 指定请求类型
     *        params 指定请求该方法时必须携带某参数（参数名一致）
     *        headers 指定请求该方法时必须含有规定的请求头
    *@return:
    *@Author: Su
    *@date: 2020/8/18
    */
    @RequestMapping(value = "/hello",method = {RequestMethod.POST},
            params = {"username"},headers = {"Accept"})
    public String sayHello() {
        System.out.println("hello springmvc");
        return "success";
    }
    /**
    *@Description: 请求参数绑定(页面参数名称和方法中的参数名称一致时)
     *  当参数名称一致时，则直接这样写，springmvc会自动帮我们封装。
     *  如果传的表单（即需要封装成javaBean），页面参数名称格式是对象.属性 方法参数就是对象
    *@Param:  
    *@Author: Su
    *@date: 2020/8/18
    */
    @RequestMapping(value = "/param1")
    public String param1(String username) {
        System.out.println("hello springmvc");
        return "success";
    }
    /**
     *@Description: 请求参数绑定(页面参数名称和方法中的参数名称不一致时,使用这些注解进行解决)
     *@Param:  @RequestParam  required属性默认值是true 代表该属性必须传
     *         @RequestBody  get请求不适用，因为无请求体，而是把请求都放到url上
     *                       post请求适用，将请求体中的数据（表单或者json）封装到对象中
     *         @PathVariable 取出restful风格的请求路径上的参数
     *@return:
     *@Author: Su
     *@date: 2020/8/18
     */
    @RequestMapping(value = "/param/{sid}")
    //name为页面参数名称，username为方法参数名称
    public String param(@RequestParam("name") String username,@RequestBody User user,
                        @PathVariable("sid") String id ) {
        System.out.println("hello springmvc");
        return "success";
    }
    /**
     *@Description: 获取servlet原生api
     *@Param:
     *@return:
     *@Author: Su
     *@date: 2020/8/18
     */
    @RequestMapping(value = "/servlet")
    public String servlet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session=request.getSession();
        System.out.println(session);
        return "success";
    }

    /**
    *@Description: @ModelAttribute注解
     *      当该注解加载方法上时，则该方法会在控制器方法执行之前先执行
    *@Param:
    *@return:
    *@Author: Su
    *@date: 2020/8/18
    */
    @ModelAttribute
    //该方法在每个请求方法执行之前执行
    public void show(){
        System.out.println("执行了");
    }

    //响应视图之字符串  将用户对象传至页面
    @RequestMapping("/testString")
    public String testString(Model model){
        User user=new User();
        user.setUserName("sh");
        model.addAttribute("user",user);
        return "success";
    }

    //响应视图之ModelAndView
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        ModelAndView mv=new ModelAndView();
        User user=new User();
        user.setUserName("sh");
        //相当于使用model来存储该对象到request域中
        mv.addObject("user",user);
        //跳转到哪个页面
        mv.setViewName("success");
        return mv;
    }

    //模拟ajax异步请求响应
    @RequestMapping("/testAjax")
    @ResponseBody  //将对象转成json返回给前端ajax请求
    public User testAjax(@RequestBody User user){
       user.setAge(20);
       return user;
    }
    /**
    *@Description: SpringMvc文件上传
    *@Param: 原理： 页面选择文件并上传的请求经过前端控制器  我们再额外配置文件解析器  这样前端控制器会调用它
     *             解析请求，返回文件项   接着执行controller中的方法  该方法需要参数MultipartFile对象来接收该文件项
     *             最后调用文件项中的方法实现上传
     *       前端上传文件表单：
     *          <form action="" method="post" enctype="multipart/form-data">
     *              <input type="file" name="upload"/>  name的值和方法参数MultipartFile对象名一致
     *              <input type="submit" value="上传"/>
     *          </form>
    *@return:
    *@Author: Su
    *@date: 2020/8/20
    */
    @RequestMapping("/upload")
    public String fileUpload(HttpServletRequest request,MultipartFile upload){
        //文件上传到的地址
        String path="";
        //获取文件名
        String fileName=upload.getOriginalFilename();
        try {
            upload.transferTo(new File(path,fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
    
    /**
    *@Description: 跨服务器文件上传 
    *@Param:
    *@return: 
    *@Author: Su
    *@date: 2020/8/20
    */
    @RequestMapping("/upload2")
    public String fileUpload2(MultipartFile upload){
        //文件服务器中存储文件的目录地址
        String path="http://localhost:8080/uploads/";
        //获取文件名
        String fileName=upload.getOriginalFilename();
        //创建客户端对象
        Client client=Client.create();
        //和图片服务器进行连接
        WebResource webResource=client.resource(path+fileName);
        //上传文件
        try {
            webResource.put(upload.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
    
    /**
    *@Description: springMVC异常处理
    *@Param:  dao中的异常抛给service service异常抛给controller controller异常抛给前端控制器
     *   如果前端控制器不调用异常处理器组件（需要我们自己配） 则会把异常抛给浏览器 这样用户体验不好
    *@return:   异常处理的步骤
     *              编写自定义异常类  做提示信息
     *              编写异常处理器
     *              配置异常处理器
     *
    *@Author: Su
    *@date: 2020/8/20
    */
    @RequestMapping("/testException")
    public String testException(){
        try{
            //模拟调用service层方法时出现异常
            int a=10/0;
        }catch (Exception e){
            e.printStackTrace();
            //根据自定义的异常类友好提示信息  将异常抛给前端控制器
            throw new SysException("查询用户错误");
        }
        return "success";
    }

    /**
    *@Description: springmvc中的拦截器
    *@Param:   拦截器：用于对处理器进行预处理和后处理，即请求经过拦截器才能到处理器（预处理），处理器处理之后的请求要经过拦截器才能到页面（后处理）
    *@return:         只有使用了springmvc框架的工程才能用
     *                只会拦截访问的控制器方法，静态资源不会进行拦截
     *                自定义拦截器，必须实现HandleInterceptor接口
     *         过滤器：是servlet规范中的一部分，任何java web工程都可以使用
     *                在url-pattern中配置了/*之后，可以对所有要访问的资源拦截
     *
    *@Author: Su
    *@date: 2020/8/20
    */
    @RequestMapping("/testInterceptor")
    public String testInterceptor(){

        return "success";
    }


}
