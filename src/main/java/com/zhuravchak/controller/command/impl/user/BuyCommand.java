package com.zhuravchak.controller.command.impl.user;

import com.zhuravchak.controller.command.ActionCommand;
import com.zhuravchak.controller.exception.CommandException;
import com.zhuravchak.domain.Order;
import com.zhuravchak.model.dao.abstr.OrderDAO;
import com.zhuravchak.model.exception.DAOException;
import com.zhuravchak.model.dao.factory.DAOFactory;
import com.zhuravchak.domain.Pass;
import com.zhuravchak.domain.User;
import com.zhuravchak.model.connection.ConnectionPool;
import com.zhuravchak.controller.util.resource.ConfigurationManager;
import com.zhuravchak.model.service.OrderService;
import com.zhuravchak.model.exception.ServiceException;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.List;

/**
 * The Class BuyCommand.
 */
public class BuyCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(BuyCommand.class);

    /* (non-Javadoc)
       * @see com.zhuravchak.controller.command.ActionCommand#execute(HttpServletRequest, HttpServletResponse)
       */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = ConfigurationManager.getProperty("path.page.receipt");
        boolean result = false;
        Pass passFromSession = (Pass) request.getSession().getAttribute("pass");
        int quantity =  (int)request.getSession().getAttribute("quantity");
        double price = (double)request.getSession().getAttribute("price");
        long userId = (long)request.getSession().getAttribute("iduser");

        Connection cn = null;

        try {
            cn = ConnectionPool.getConnection();
            DAOFactory df = DAOFactory.getDAOFactory("MYSQL");
            long passId = Long.valueOf(request.getParameter("passId"));

            Pass passByID = df.getPassDAO(cn).findEntityById(passId);
        if (passFromSession.getId()==Long.valueOf(request.getParameter("passId")) &&
                 Integer.valueOf(request.getParameter("quantity")) == quantity &&
                 Double.valueOf(request.getParameter("price")) == price &&
                 passFromSession.getQuantityAvailable() == passByID.getQuantityAvailable()) {

                result = OrderService.getInstance().buyPass(passByID, userId, quantity, price);
            if(result){

                LOG.info("user (id = "+ userId + ")"+ " made a purchase for the amount " + price +"$");

                HttpSession session = request.getSession();
                User user = df.getUserDAO(cn).findEntityById(userId);
                session.setAttribute("isRegular",String.valueOf(user.isRegular()));

                OrderDAO orderDAO = df.getOrderDAO(cn);
                Order order = orderDAO.findEntityById(orderDAO.findLastIdForUser(user));

                request.setAttribute("order", order);
                request.setAttribute("user", user);
                request.setAttribute("pass", session.getAttribute("pass"));
                request.setAttribute("tour", session.getAttribute("tour"));

                session.setAttribute("pass", null);
                session.setAttribute("tour", null);
            }
        }
        } catch (ServiceException | DAOException e) {
            LOG.error(e);
            throw new CommandException(e);
        } finally {
            if(cn != null){
                ConnectionPool.closeConnection(cn);
            }
        }
        request.setAttribute("isBougth", result);

        return page;
    }
}
