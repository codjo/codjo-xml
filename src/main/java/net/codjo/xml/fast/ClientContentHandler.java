/*
 * Team : AGF AM / OSI / SI / BO
 *
 * Copyright (c) 2001 AGF Asset Management.
 */
package net.codjo.xml.fast;
import java.util.Map;
/**
 * Interface devant etre implementée par le client du parser
 *
 * @author MARCONA et Mehdi
 */
public interface ClientContentHandler {
    /**
     * Methode appelée en fin de balise
     *
     * @param name : nom de la balise
     * @param value : valeur de la balise
     */
    public void endElement(String name, String value);


    /**
     * Methode appelée en début de balise
     *
     * @param name : nom de la balise
     * @param attributes : Map des attributs de la balise
     */
    public void startElement(String name, Map attributes);
}
