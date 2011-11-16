/*
 * Team : AGF AM / OSI / SI / BO
 *
 * Copyright (c) 2001 AGF Asset Management.
 */
package net.codjo.xml.easyxml;
/**
 * DOCUMENT ME!
 *
 * @version $Revision: 1.2 $
 */
public class Article {
    private String headline;
    private String page;

    public Article() {}

    public String getHeadline() {
        return headline;
    }


    public void setHeadline(String rhs) {
        headline = rhs;
    }


    public String getPage() {
        return page;
    }


    public void setPage(String rhs) {
        page = rhs;
    }


    public String toString() {
        return "Article: Headline='" + headline + "' on page='" + page + "' ";
    }
}
