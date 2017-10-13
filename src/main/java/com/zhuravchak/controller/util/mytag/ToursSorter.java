package com.zhuravchak.controller.util.mytag;

import com.zhuravchak.domain.Tour;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ToursSorter extends TagSupport {
    List<Tour> list;

    public void setList(List<Tour> list) {
        this.list = list;
    }

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

            if (lang != null &&  lang.equals("en_US")) {
                Collections.sort(list, new Comparator<Tour>() {
                    @Override
                    public int compare(Tour o1, Tour o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
            }
            else if  (lang != null &&  lang.equals("uk_UA")) {
                Collections.sort(list, new Comparator<Tour>() {
                    @Override
                    public int compare(Tour o1, Tour o2) {
                        return o1.getNameUA().compareTo(o2.getNameUA());
                    }
                });
            }

        return SKIP_BODY;
    }
}
