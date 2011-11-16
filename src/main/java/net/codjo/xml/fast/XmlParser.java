/*
 * Team : AGF AM / OSI / SI / BO
 *
 * Copyright (c) 2001 AGF Asset Management.
 */
package net.codjo.xml.fast;
import net.codjo.xml.XmlException;
import net.codjo.xml.fast.factory.XMLReaderFactory;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
/**
 * Classe permettant de lancer le parsing du fichier passe en parametre. NB : Le
 * FastContentHandler doit etre implementé chez le client.
 *
 * @author MARCONA
 */
public class XmlParser {
    public void parse(String fileName, ClientContentHandler userContentHandler)
        throws XmlException, IOException {
        try {
            XMLReader xmlReader = XMLReaderFactory.getXmlReader();
            xmlReader.setContentHandler(new FastContentHandler(userContentHandler));
            xmlReader.parse(fileName);
        }
        catch (SAXException ex) {
            throw new XmlException(ex);
        }
        catch (ParserConfigurationException ex) {
            throw new XmlException(ex);
        }
    }


    public void parse(InputSource source, ClientContentHandler userContentHandler)
        throws XmlException, IOException {
        try {
            XMLReader xmlReader = XMLReaderFactory.getXmlReader();
            xmlReader.setContentHandler(new FastContentHandler(userContentHandler));
            xmlReader.parse(source);
        }
        catch (SAXException ex) {
            throw new XmlException(ex);
        }
        catch (ParserConfigurationException ex) {
            throw new XmlException(ex);
        }
    }
}
