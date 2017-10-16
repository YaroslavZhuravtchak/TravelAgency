package com.zhuravchak.controller.command.impl;

import com.zhuravchak.controller.command.ActionCommand;
import com.zhuravchak.controller.util.resource.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class EmptyCommand implements ActionCommand {



    /* (non-Javadoc)
         * @see ActionCommand#execute(HttpServletRequest, HttpServletResponse)
         */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        String role = (String)request.getSession().getAttribute("role");

        if(role == null) {
            page = ConfigurationManager.getProperty("path.page.login");
        } else if (role.equals("USER")){
            page = ConfigurationManager.getProperty("path.page.main");
        } else if (role.equals("ADMIN")){
            page = ConfigurationManager.getProperty("path.page.main.admin");
        }
        return page;
    }
}
