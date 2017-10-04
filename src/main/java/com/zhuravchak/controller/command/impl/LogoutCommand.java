package com.zhuravchak.controller.command.impl;

import com.zhuravchak.controller.exception.CommandException;
import com.zhuravchak.controller.command.ActionCommand;
import com.zhuravchak.controller.util.resource.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The Class LogoutCommand.
 */
public class LogoutCommand implements ActionCommand {

    /* (non-Javadoc)
   * @see com.zhuravchak.controller.command.ActionCommand#execute(HttpServletRequest, HttpServletResponse)
   */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String page = ConfigurationManager.getProperty("path.page.index");
        HttpSession session = request.getSession(true);
        String lang = null;
        session.invalidate();

        return page;
    }
}
