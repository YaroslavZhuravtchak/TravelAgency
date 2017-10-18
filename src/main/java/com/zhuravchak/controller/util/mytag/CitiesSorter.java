package com.zhuravchak.controller.util.mytag;

import com.zhuravchak.domain.City;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CitiesSorter  extends TagSupport {
    List<City> list;

    public void setList(List<City> list) {
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

        HttpSession session = pageContext.getSession();
        String lang = (String) session.getAttribute("lang");

        if (lang != null && lang.equals("en_US")) {
            Collections.sort(list, new Comparator<City>() {
                @Override
                public int compare(City o1, City o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
        }
        else if (lang != null && lang.equals("uk_UA")){
            Collections.sort(list, new Comparator<City>() {
                @Override
                public int compare(City o1, City o2) {
                    return o1.getNameUA().compareTo(o2.getNameUA());
                }
            });
        }
        return SKIP_BODY;
    }
}