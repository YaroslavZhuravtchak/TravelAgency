package com.zhuravchak.controller.command.factory;

import com.zhuravchak.controller.command.ActionCommand;
import com.zhuravchak.controller.command.impl.EmptyCommand;
import com.zhuravchak.controller.command.commandlist.CommandEnum;
import com.zhuravchak.controller.util.resource.ConfigurationManager;
import com.zhuravchak.controller.util.resource.MessageManager;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ActionFactory {

    private final static Logger LOG = Logger.getLogger(ActionFactory.class);

    private static final String PARAM_NAME_COMMAND = "command";

    /**
     * Define command.
     *
     * @param request the request
     * @param response the response
     * @return the action command
     */
    public ActionCommand defineCommand(HttpServletRequest request, HttpServletResponse response) {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter(PARAM_NAME_COMMAND);
        LOG.debug("Action = " + action);
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            System.out.println(currentEnum);
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            LOG.info(MessageManager.getProperty("message.wrongaction" )  + " :"+action);
        }
        return current;
    }
}
