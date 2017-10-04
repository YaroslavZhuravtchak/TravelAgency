package com.zhuravchak.controller.command.impl;

import com.zhuravchak.controller.command.ActionCommand;
import com.zhuravchak.controller.util.resource.ConfigurationManager;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(ForwardCommand.class);

    private static final String PARAM_NAME_PAGE = "page";

    /* (non-Javadoc)
      * @see com.zhuravchak.controller.command.ActionCommand#execute(HttpServletRequest, HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        String requestPage = request.getParameter(PARAM_NAME_PAGE);
        switch (requestPage){
            case "login":
                page = ConfigurationManager.getProperty("path.page.login");
                break;
            case "register":
                page = ConfigurationManager.getProperty("path.page.register");
                break;
            case "main":
                page = ConfigurationManager.getProperty("path.page.main");
                break;
            case "tours":
                page = ConfigurationManager.getProperty("path.page.tours");
                break;
            case "tours_admin":
                page = ConfigurationManager.getProperty("path.page.tours.admin");
                break;
            case "buy":
                page = ConfigurationManager.getProperty("path.page.buy");
                break;
            case "order":
                page = ConfigurationManager.getProperty("path.page.order");
                break;
            case "price":
                page = ConfigurationManager.getProperty("path.page.price");
                break;
            case "redirect":
                page = ConfigurationManager.getProperty("path.page.redirect");
                break;
            default:
                page = ConfigurationManager.getProperty("path.page.login");
                break;
        }
        return page;
    }
}
