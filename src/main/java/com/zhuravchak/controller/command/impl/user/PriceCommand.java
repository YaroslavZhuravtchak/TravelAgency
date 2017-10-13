package com.zhuravchak.controller.command.impl.user;

import com.zhuravchak.controller.exception.CommandException;
import com.zhuravchak.controller.command.ActionCommand;
import com.zhuravchak.domain.Pass;
import com.zhuravchak.controller.util.resource.ConfigurationManager;
import com.zhuravchak.model.service.OrderService;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Class PriceCommand.
 */
public class PriceCommand implements ActionCommand{

    private final static Logger LOG = Logger.getLogger(PriceCommand.class);

    /* (non-Javadoc)
       * @see com.zhuravchak.controller.command.ActionCommand#execute(HttpServletRequest, HttpServletResponse)
       */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String page = ConfigurationManager.getProperty("path.page.price");
        Boolean isRegular = Boolean.valueOf((String)request.getSession().getAttribute("isRegular"));

        Pass pass = (Pass) request.getSession().getAttribute("pass");

        Integer quantity = Integer.valueOf(request.getParameter("quantity"));

        Double price = OrderService.getInstance().calculatePrice(isRegular, pass, quantity);

        LOG.info("Pass (id = " + pass.getId() + "): calculated price = " + price );

        request.getSession().setAttribute("quantity", quantity);
        request.getSession().setAttribute("price", price);

        return page;
    }
}
