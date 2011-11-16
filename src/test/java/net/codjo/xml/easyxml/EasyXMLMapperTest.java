/*
 * Team : AGF AM / OSI / SI / BO
 *
 * Copyright (c) 2001 AGF Asset Management.
 */
package net.codjo.xml.easyxml;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import junit.framework.TestCase;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
/**
 * DOCUMENT ME!
 *
 * @version $Revision: 1.7 $
 */
public class EasyXMLMapperTest extends TestCase {
    private EasyXMLMapper easyXMLMapper = null;

    public void testMapping() throws Exception {
        URL xmlFile = EasyXMLMapperTest.class.getResource("conf/test-xml-entry.xml");
        URL rulesFile = EasyXMLMapperTest.class.getResource("conf/test-xml-rules.xml");

        easyXMLMapper = new EasyXMLMapper(xmlFile, rulesFile);

        Catalog catalog = (Catalog)easyXMLMapper.load();
        Book book = (Book)catalog.getBooks().elementAt(0);
        assertEquals("Author 1", book.getAuthor());

        assertEquals(3, catalog.getBooks().size());
        assertEquals(2, catalog.getMagazines().size());
        Magazine theFirstMag = (Magazine)catalog.getMagazines().elementAt(0);
        assertEquals("Mag Title 1", theFirstMag.getName());
        assertEquals(2, theFirstMag.getArticles().size());
        Article art1 = (Article)theFirstMag.getArticles().elementAt(0);
        assertEquals("Some Headline", art1.getHeadline());
        Article art2 = (Article)theFirstMag.getArticles().elementAt(1);
        assertEquals("Another Headline", art2.getHeadline());
        assertEquals("5", art1.getPage());
    }


    public void testMapping_BugEntityResolver() throws Exception {
        URL rulesFile = EasyXMLMapperTest.class.getResource("conf/test-xml-rules.xml");
        InputStream xmlFile =
            EasyXMLMapperTest.class.getResourceAsStream(
                "conf/test-xml-entry-with-entity.xml");

        easyXMLMapper = new EasyXMLMapper(xmlFile, rulesFile);

        easyXMLMapper.setEntityResolver(new EntityResolver() {
                public InputSource resolveEntity(String publicId, String systemId) {
                    if ("file:name.txt".equals(systemId)) {
                        return new InputSource(new StringReader("entite resolu"));
                    }
                    return null;
                }
            });

        Catalog catalog = (Catalog)easyXMLMapper.load();
        assertEquals(1, catalog.getBooks().size());
        Book book = (Book)catalog.getBooks().elementAt(0);
        assertEquals("entite resolu", book.getAuthor());
    }
}
