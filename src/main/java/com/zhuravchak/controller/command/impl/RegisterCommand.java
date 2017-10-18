package com.zhuravchak.controller.command.impl;

import com.zhuravchak.controller.exception.CommandException;
import com.zhuravchak.controller.command.ActionCommand;
import com.zhuravchak.model.exception.DAOException;
import com.zhuravchak.model.dao.factory.DAOFactory;
import com.zhuravchak.domain.User;
import com.zhuravchak.domain.enums.UserRole;
import com.zhuravchak.model.connection.ConnectionPool;
import com.zhuravchak.controller.util.resource.ConfigurationManager;
import com.zhuravchak.controller.util.resource.MessageManager;
import com.zhuravchak.model.service.UserService;
import com.zhuravchak.model.exception.ServiceException;
import com.zhuravchak.model.utill.Hashing;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

/**
 * The Class RegisterCommand.
 */
public class RegisterCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(RegisterCommand.class);

    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_FIRSTNAME = "firstname";
    private static final String PARAM_LASTNAME = "lastname";
    private static final String PARAM_PHONE = "phone";

    /* (non-Javadoc)
      * @see com.zhuravchak.controller.command.ActionCommand#execute(HttpServletRequest, HttpServletResponse)
      */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String page = null;
        String login = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);
        String email = request.getParameter(PARAM_EMAIL);
        String firstname = request.getParameter(PARAM_FIRSTNAME);
        String lastname = request.getParameter(PARAM_LASTNAME);
        String phone = request.getParameter(PARAM_PHONE);

        LOG.debug("Encoding (Register): " + request.getCharacterEncoding());
        Connection  cn = null;

        try {
            cn = ConnectionPool.getConnection();
            DAOFactory df = DAOFactory.getDAOFactory("MYSQL");

            if (UserService.getInstance().checkLogin(login)) {
               byte[] salt = Hashing.generateSalt();

               User user  =  new User();
               user.setLogin(login);
               user.setSalt(Hashing.byteToHexString(salt));
               user.setPassword(Hashing.sha256(Hashing.saltPassword(password,salt)));
               user.setFirstName(firstname);
               user.setLastname(lastname);
               user.setEmail(email);
               user.setPhoneNumber(phone);
               user.setRole(UserRole.USER);
               user.setRegular(false);

                if(df.getUserDAO(cn).create(user)){
                    LOG.info("User (login = "+ user.getLogin() +") is registered");
                }
                page = ConfigurationManager.getProperty("path.page.login");
            }else {
               request.setAttribute("errorRegisterPassMessage",
                        MessageManager.getProperty("message.registererror"));
                page = ConfigurationManager.getProperty("path.page.register");
            }
        } catch (ServiceException|DAOException e) {
            LOG.error(e + " Can not create user: ");
            throw new CommandException(e);
        } finally {
            if(cn != null){
                ConnectionPool.closeConnection(cn);
            }
        }
        return page;
   }

}
