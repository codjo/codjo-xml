/*
 * Team : AGF AM / OSI / SI / BO
 *
 * Copyright (c) 2001 AGF Asset Management.
 */
package net.codjo.xml.fast;
import java.util.HashMap;
import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * Wrapper sur un ClientContentHandler.
 *
 * @author MARCONA
 */
class FastContentHandler extends DefaultHandler {
    private ClientContentHandler userContentHandler;
    private StringBuffer buf = new StringBuffer();

    FastContentHandler(ClientContentHandler usrHandler) {
        this.userContentHandler = usrHandler;
    }

    public void characters(char[] ch, int start, int length)
        throws SAXException {
        buf.append(ch, start, length);
    }


    public void ignorableWhitespace(char[] ch, int start, int length)
        throws SAXException {
        buf.append(ch, start, length);
    }


    public void endElement(java.lang.String uri, java.lang.String localName,
        java.lang.String qName) throws SAXException {
        userContentHandler.endElement(qName, buf.toString().trim());
        buf.setLength(0);
    }


    public void processingInstruction(String target, String data)
        throws SAXException {}


    public void startElement(java.lang.String uri, java.lang.String localName,
        java.lang.String qName, Attributes attr) throws SAXException {
        userContentHandler.startElement(qName, getAttributesAsMap(attr));
    }


    /**
     * retourne la liste des Attributs sous la forme d'une Map clé/valeur
     *
     * @param attr
     *
     * @return
     */
    private Map getAttributesAsMap(Attributes attr) {
        HashMap attributes = new HashMap();
        int len = attr.getLength();
        for (int i = 0; i < len; i++) {
            String key = attr.getQName(i);
            String value = attr.getValue(i);
            attributes.put(key, value);
        }
        return attributes;
    }
}
