package com.pxu.hrms.controller;

import com.pxu.hrms.domain.User;
import com.pxu.hrms.service.HrmsService;
import com.pxu.hrms.util.common.HrmsConstants;
import com.pxu.hrms.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    @Qualifier("hrmsService")
    private HrmsService hrmsService;

    @RequestMapping(value = "/login")
    public ModelAndView login(
            @RequestParam("loginname")
                    String loginname,
            @RequestParam("password")
                    String password, HttpSession session, ModelAndView mv) {
        User user = hrmsService.login(loginname, password);
        if (user != null) {
            session.setAttribute(HrmsConstants.USER_SESSION, user);
            mv.setViewName("redirect:/main");
        } else {
            mv.addObject("message", "登录名或密码错误！请重新输入");
            mv.setViewName("forward:/loginForm");
        }
        return mv;
    }

    @RequestMapping(value = "/user/selectUser")
    public String selectUser(Integer pageIndex,
                             @ModelAttribute
                                     User user, Model model) {
        System.out.println("user = " + user);
        PageModel pageModel = new PageModel();
        if (pageIndex != null) {
            pageModel.setPageIndex(pageIndex);
        }
        List<User> users = hrmsService.findUser(user, pageModel);
        model.addAttribute("users", users);
        model.addAttribute("pageModel", pageModel);
        return "user/user";
    }

    @RequestMapping("/user/removeUser")
    public ModelAndView removeUser(String ids, ModelAndView mv) {
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            hrmsService.removeUserById(Integer.parseInt(id));
        }
        mv.setViewName("redirect:/user/selectUser");
        return mv;
    }

    @RequestMapping("/user/updateUser")
    public ModelAndView updateUser(String flag,
                                   @ModelAttribute
                                           User user, ModelAndView mv) {
        if (flag.equals("1")) {
            User target = hrmsService.findUserById(user.getId());
            mv.addObject("user", target);
            mv.setViewName("user/showUpdateUser");
        } else {
            hrmsService.modifyUser(user);
            mv.setViewName("redirect:/user/selectUser");
        }
        return mv;
    }

    @RequestMapping("/user/addUser")
    public ModelAndView addUser(String flag,
                                @ModelAttribute
                                        User user, ModelAndView mv) {
        if (flag.equals("1")) {
            mv.setViewName("redirect:/user/showAddUser");
        } else {
            hrmsService.addUser(user);
            mv.setViewName("redirect:/user/selectUser");
        }
        return mv;
    }

}
