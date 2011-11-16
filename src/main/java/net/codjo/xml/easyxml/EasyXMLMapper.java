/*
 * Team : AGF AM / OSI / SI / BO
 *
 * Copyright (c) 2001 AGF Asset Management.
 */
package net.codjo.xml.easyxml;
import net.codjo.xml.XmlException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.apache.log4j.Logger;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
/**
 * Classe utilitaire permettant de charger un fichier XML en une structure objet.
 *
 * @version $Revision: 1.10 $
 */
public class EasyXMLMapper {
    private static final Logger APP = Logger.getLogger(EasyXMLMapper.class);
    private URL urlXml;
    private Digester digester = null;
    private InputSource inputSource = null;
    private InputStream inputStream = null;

    public EasyXMLMapper(URL xmlFile, URL rulesFile) {
        this.urlXml = xmlFile;
        digester = newDigester(rulesFile);
    }


    public EasyXMLMapper(InputSource inputSource, URL rulesFile) {
        this.inputSource = inputSource;
        digester = newDigester(rulesFile);
    }


    public EasyXMLMapper(InputStream inputStream, URL rulesFile) {
        this.inputStream = inputStream;
        digester = newDigester(rulesFile);
    }

    /**
     * A ne pas utiliser.
     *
     * @return
     *
     * @throws Exception erreur :)
     *
     * @deprecated utilisez la méthode load.
     */
    public Object map() throws Exception {
        return load();
    }


    public Object load() throws IOException, XmlException {
        if (APP.isDebugEnabled()) {
            APP.debug("Parse(" + inputSource + ")");
        }

        try {
            if (inputSource != null) {
                return digester.parse(inputSource);
            }
            else if (inputStream != null) {
                return digester.parse(inputStream);
            }
            else if (urlXml != null) {
                InputStream is = urlXml.openStream();
                try {
                    return digester.parse(is);
                }
                finally {
                    is.close();
                }
            }
            else {
                throw new IllegalStateException("Flux XML non précisé.");
            }
        }
        catch (SAXException ex) {
            throw new XmlException(ex);
        }
    }


    public void setEntityResolver(EntityResolver entityResolver) {
        digester.setEntityResolver(new RulesDtdResolver(entityResolver));
    }


    private static Digester newDigester(URL rulesFile) {
        Digester dig = new Digester();
        dig.setValidating(false);
        dig.setEntityResolver(new RulesDtdResolver(null));
        dig = DigesterLoader.createDigester(rulesFile, dig);
        dig.setEntityResolver(new RulesDtdResolver(null));
        return dig;
    }

    /**
     * Classe permettant de charger la dtd d'un fichier scenarii à partir du fichier jar.
     *
     * @author $Author: gonnot $
     * @version $Revision: 1.10 $
     */
    private static class RulesDtdResolver implements EntityResolver {
        private EntityResolver subResovler;

        public RulesDtdResolver(EntityResolver subResovler) {
            this.subResovler = subResovler;
        }

        public InputSource resolveEntity(String publicId, String systemId)
            throws SAXException, IOException {
            if (APP.isDebugEnabled()) {
                APP.debug("resolveEntity : publicId(" + publicId + ") systemId ("
                    + systemId + ")");
            }
            if (systemId.toLowerCase().endsWith("digester-rules.dtd")) {
                return new InputSource(EasyXMLMapper.class.getResourceAsStream(
                        "conf/digester-rules.dtd"));
            }
            if (this.subResovler != null) {
                return subResovler.resolveEntity(publicId, systemId);
            }
            else {
                return null;
            }
        }
    }
}
