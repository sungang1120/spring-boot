package com.base.spring.controller;


import org.h819.web.commons.MyServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Description : TODO(ace admin template ajax 方式导航, 本项目用到。都是点击菜单传来，所以都是 Get)
 * 参见 AceAdminAjaxMenuExampleController 说明
 * spring mvc , freeMarker : 传递 非字符串变量到页面，必须用  @ModelAttribute("model") ModelMap model
 */
         //      http://stackoverflow.com/questions/18490026/refresh-reload-the-content-in-div-using-jquery-ajax


@Controller
@RequestMapping("/menu/ajax")
//重要: 必须以 ajax 结尾，以符合 ace.js 中 content_url 的要求，menu 为前缀，可以为任意值或者没有，解释见 AceAdminAjaxMenuExampleController
public class NavigateController {

    private static Logger logger = LoggerFactory.getLogger(NavigateController.class);

    /**
     * ajax url :  http://localhost:8888/base/menu/ajax/index.html#page/index
     * 被解析为  :  http://localhost:8888/base/menu/ajax/index.html (注意没有 content)
     * 跳转到真正的页面 :  html/ajax/index.ftl
     * index.ftl 文件仅是一个导航文件，没有内容
     * index.ftl 会指向 ajax 方法，去加载 content/index.html 文件，该文件不存在，显示空白
     *
     * @return
     */
    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String home(HttpServletRequest request) {
        logger.info("request path={} ,  will go to /html/ajax/index.ftl", MyServletUtils.getFullPath(request));
        return "admin/ace/html/ajax/index";
    }


    /**
     * ajax url :  http://localhost:8888/base/menu/ajax/index.html#page/jqgrid-roles
     * 被解析为  :  http://localhost:8888/base/menu/ajax/content/jqgrid-roles.html
     * 跳转到真正的页面 :  html/ajax/content/jqgrid-roles.ftl
     *
     * @return
     */
    @RequestMapping(value = "/content/jqgrid-roles.html", method = RequestMethod.GET)  // 必须有 /content/
    public String role(@RequestParam(value = "treeType", required = true) String treeType, HttpServletRequest request, Model model) {

        logger.info("request path={} ,  will go to /html/ajax/content/jqgrid-roles.ftl", MyServletUtils.getFullPath(request));
        logger.info("treeType ={}", treeType);
        model.addAttribute("app_path", MyServletUtils.getAppPath(request));
        model.addAttribute("treeType", treeType); //对菜单进行授权
        return "admin/ace/html/ajax/content/jqgrid-roles";
    }

    /**
     * ajax url :  http://localhost:8888/base/menu/ajax/index.html#page/jqgrid-user
     * 被解析为  :  http://localhost:8888/base/menu/ajax/content/jqgrid-user.html
     * 跳转到真正的页面 :  html/ajax/content/jqgrid-user.ftl
     *
     * @return
     */
    @RequestMapping(value = "/content/jqgrid-user.html", method = RequestMethod.GET)    // 必须有 /content/
    public String user(HttpServletRequest request, Model model) {
        logger.info("request path={} ,  will go to /html/ajax/content/jqgrid-user.ftl", MyServletUtils.getFullPath(request));
        model.addAttribute("app_path", MyServletUtils.getAppPath(request));
        return "admin/ace/html/ajax/content/jqgrid-user";
    }

    /**
     * ajax url :  http://localhost:8888/base/menu/ajax/index.html#page/jqgrid-group
     * 被解析为  :  http://localhost:8888/base/menu/ajax/content/jqgrid-group.html
     * 跳转到真正的页面 :  html/ajax/content/jqgrid-group.ftl
     *
     * @return
     * @ModelAttribute("model") ModelMap model 必须是这句
     */
    @RequestMapping(value = "/content/jqgrid-group.html", method = RequestMethod.GET)    // 必须有 /content/
    public String group(HttpServletRequest request, @ModelAttribute("model") ModelMap model) { //传递非字符串对象到前端，必须通过 @ModelAttribute("model") 对 model 强制赋值，并且是 ModelMap 类型s
        logger.info("request path={} ,  will go to /html/ajax/content/jqgrid-group.ftl", MyServletUtils.getFullPath(request));
        model.addAttribute("app_path", MyServletUtils.getAppPath(request));
        return "admin/ace/html/ajax/content/jqgrid-group";
    }


    /**
     * ajax url :  http://localhost:8888/base/menu/ajax/index.html#page/ztree-type.html
     * 被解析为  :  http://localhost:8888/base/menu/ajax/content/ztree-type.html
     * 跳转到真正的页面 :  html/ajax/content/ztree-type.ftl
     *
     * @return
     */
    @RequestMapping(value = "/content/ztree-type.html", method = RequestMethod.GET)    // 必须有 /content/
    public String menuTree2(@RequestParam(value = "treeType", required = true) String treeType, HttpServletRequest request, Model model) {
        logger.info("request path={} , type={},  will go to /html/ajax/content/ztree.ftl", MyServletUtils.getFullPath(request), treeType);

        model.addAttribute("app_path", MyServletUtils.getAppPath(request));
        model.addAttribute("treeType", treeType);
        return "admin/ace/html/ajax/content/ztree-type";
    }


    /**
     * ajax url :  http://localhost:8888/base/menu/ajax/index.html#page/fuelux-tree
     * 被解析为  :  http://localhost:8888/base/menu/ajax/content/fuelux-tree.html
     * 跳转到真正的页面 :  html/ajax/content/fuelux-tree.ftl
     *
     * @return
     */
    @Deprecated // 用 zTree 代替
    @RequestMapping(value = "/content/fuelux-tree.html", method = RequestMethod.GET)    // 必须有 /content/
    public String fueluxTree(@RequestParam(value = "type", required = true) String type, HttpServletRequest request, Model model) {
        logger.info("request path={} , type={},  will go to /html/ajax/content/fuelux-tree.ftl", MyServletUtils.getFullPath(request), type);

        model.addAttribute("app_path", MyServletUtils.getAppPath(request));
        model.addAttribute("menu_type", type);
        return "admin/ace/html/ajax/content/fuelux-tree";
    }

    // ...... 其他页面的例子

}