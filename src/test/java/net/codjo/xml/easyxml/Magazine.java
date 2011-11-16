/*
 * Team : AGF AM / OSI / SI / BO
 *
 * Copyright (c) 2001 AGF Asset Management.
 */
package net.codjo.xml.easyxml;
import java.util.Vector;
/**
 * DOCUMENT ME!
 *
 * @version $Revision: 1.4 $
 */
public class Magazine {
    private String name;
    private Vector articles;

    public Magazine() {
        articles = new Vector();
    }

    public String getName() {
        return name;
    }


    public Vector getArticles() {
        return articles;
    }


    public void setName(String rhs) {
        name = rhs;
    }


    public void addArticle(Article a) {
        articles.addElement(a);
    }


    public void addArticle(String page, String headline) {
        Article article = new Article();
        article.setPage(page);
        article.setHeadline(headline);
        articles.addElement(article);
    }


    public String toString() {
        StringBuffer buf = new StringBuffer("Magazine: Name='" + name + "' ");
        for (int i = 0; i < articles.size(); i++) {
            buf.append(articles.elementAt(i).toString());
        }
        return buf.toString();
    }
}
