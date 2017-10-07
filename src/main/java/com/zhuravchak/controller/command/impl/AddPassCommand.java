package com.zhuravchak.controller.command.impl;

import com.zhuravchak.controller.command.ActionCommand;
import com.zhuravchak.controller.exception.CommandException;
import com.zhuravchak.controller.util.resource.ConfigurationManager;
import com.zhuravchak.domain.Pass;
import com.zhuravchak.model.connection.ConnectionPool;
import com.zhuravchak.model.dao.factory.DAOFactory;
import com.zhuravchak.model.exception.DAOException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.time.LocalDate;

/**
 * Created by Yaroslav on 07-Oct-17.
 */
public class AddPassCommand implements ActionCommand{

    private final static Logger LOG = Logger.getLogger(AddPassCommand .class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String page = ConfigurationManager.getProperty("path.page.redirect");
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        request.setAttribute("redir", request.getParameter("redir"));

        Integer quantity = Integer.valueOf(request.getParameter("quantity"));
        Double price = Double.valueOf(request.getParameter("price"));
        Long tourId = Long.valueOf(request.getParameter("tourId"));

        Connection cn = null;

        try{
            cn = ConnectionPool.getConnection();
            DAOFactory df = DAOFactory.getDAOFactory("MYSQL");
            Pass pass = new Pass();
            pass.setTourId(tourId);
            pass.setLeavingDate(date);
            pass.setPrice(price);
            pass.setQuantityAvailable(quantity);

            df.getPassDAO(cn).create(pass);

        } catch (DAOException e) {
            LOG.error(e);
            throw new CommandException(e);
        } finally {
            if(cn != null){
                ConnectionPool.closeConnection(cn);
            }
        }

        return page;
    }
}
