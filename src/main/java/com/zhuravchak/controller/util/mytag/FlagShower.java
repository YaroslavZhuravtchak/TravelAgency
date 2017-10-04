package com.zhuravchak.controller.util.mytag;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;


public class FlagShower extends TagSupport {

    /**
     * Do start tag.
     *
     * @return the int
     * @throws JspTagException the jsp tag exception
     */
    @Override
    public int doStartTag() throws JspException {

        JspWriter out =  pageContext.getOut();
        HttpSession session = pageContext.getSession();
        String lang = (String) session.getAttribute("lang");
        System.out.println(lang);

        try {
            if (lang.equals("en_UK")) {
                out.write("<div id = \"flag\"> <img src=\"../images/en.png\" width=\"30px\"/></div>");
            }
            else  {
                out.write("<div id = \"flag\"> <img src=\"../images/ua.png\" width=\"30px\"/></div>");
            }
        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }
}
