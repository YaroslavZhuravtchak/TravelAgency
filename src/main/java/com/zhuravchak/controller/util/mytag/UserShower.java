package com.zhuravchak.controller.util.mytag;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


public class UserShower extends TagSupport {

    /**
     * Do start tag.
     *
     * @return the int
     * @throws JspTagException the jsp tag exception
     */
    @Override
    public int doStartTag() throws JspException{
        JspWriter out =  pageContext.getOut();
        HttpSession session = pageContext.getSession();
        String message = null;
        String user = (String) session.getAttribute("user");
        String role = (String) session.getAttribute("role");
        String isRegular = (String) session.getAttribute("isRegular");

        try {
            if(user != null && role != null && role.equals("USER") && isRegular.equals("true")){
                out.write("<div id = \"status\" style =\"position: absolute;top: 15px; right: 80px; width: 150px; " +
                        "color: aquamarine;\" ><p>" + user + " (Regular " + role + ")</p></div>");
            }
            else if(user != null && role != null){
                out.write("<div id = \"status\" style =\"position: absolute;top: 15px; right: 80px; width: 150px; " +
                        "color: aquamarine;\" ><p>" + user + " (" + role + ")</p></div>");
            }
        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }
}