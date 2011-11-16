/*
 * Team : AGF AM / OSI / SI / BO
 *
 * Copyright (c) 2001 AGF Asset Management.
 */
package net.codjo.xml.fast.factory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
/**
 * Classe Factory pour creer un XML reader.
 *
 * @author MARCONA
 */
public final class XMLReaderFactory {
    private XMLReaderFactory() {}

    public static XMLReader getXmlReader()
        throws SAXException, ParserConfigurationException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(false);

        return factory.newSAXParser().getXMLReader();
    }
}
