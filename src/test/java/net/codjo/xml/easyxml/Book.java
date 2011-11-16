/*
 * Team : AGF AM / OSI / SI / BO
 *
 * Copyright (c) 2001 AGF Asset Management.
 */
package net.codjo.xml.easyxml;
/**
 * DOCUMENT ME!
 *
 * @version $Revision: 1.3 $
 */
public class Book {
    private String author;
    private String title;

    public Book() {}

    public void setAuthor(String rhs) {
        author = rhs;
    }


    public void setTitle(String rhs) {
        title = rhs;
    }


    public String getAuthor() {
        return author;
    }


    public String toString() {
        return "Book: Author='" + author + "' Title='" + title + "'";
    }
}
