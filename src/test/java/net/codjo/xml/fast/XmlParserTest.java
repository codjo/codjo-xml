/*
 * Team : AGF AM / OSI / SI / BO
 *
 * Copyright (c) 2001 AGF Asset Management.
 */
package net.codjo.xml.fast;
import net.codjo.xml.XmlException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;
import org.xml.sax.InputSource;
/**
 * Test du parsing avec Piccolo et la librairie agf-xml.
 *
 * @author MARCONA
 */
public class XmlParserTest extends TestCase {
    private XmlParser xmlParser = null;
    private String fileName = "test.xml";

    public XmlParserTest(String name) {
        super(name);
    }

    public void testParse() throws XmlException, IOException {
        Map resMap = new HashMap();
        ClientContentHandler fastContentHandler = new tinyImpl(resMap);
        xmlParser.parse(new InputSource(XmlParserTest.class.getResourceAsStream(fileName)),
            fastContentHandler);

        //balise data
        assertTrue("", ((TestObject)resMap.get("data")).value.equals(""));
        assertTrue("", ((TestObject)resMap.get("data")).depth == 1);
        assertTrue("", ((TestObject)resMap.get("data")).attributes.size() == 0);
        //configuration
        assertTrue("", ((TestObject)resMap.get("configuration")).value.equals(""));
        assertTrue("", ((TestObject)resMap.get("configuration")).depth == 2);
        assertTrue("", ((TestObject)resMap.get("configuration")).attributes.size() == 1);
        assertTrue("",
            ((TestObject)resMap.get("configuration")).attributes.get("medhi").equals("mehdi"));
        //project
        assertTrue("", ((TestObject)resMap.get("project")).value.equals(""));
        assertTrue("", ((TestObject)resMap.get("project")).depth == 3);
        assertTrue("", ((TestObject)resMap.get("project")).attributes.size() == 0);
        //name
        assertTrue("", ((TestObject)resMap.get("name")).value.equals("Roses"));
        assertTrue("", ((TestObject)resMap.get("name")).depth == 4);
        assertTrue("", ((TestObject)resMap.get("name")).attributes.size() == 1);
    }


    protected void setUp() throws Exception {
        super.setUp();
        xmlParser = new XmlParser();
    }


    protected void tearDown() throws Exception {
        xmlParser = null;
        super.tearDown();
    }

    /**
     * Implementation de l'interface ClientContentHandler qui decrit le comportement a
     * adopter pour le parsing.
     *
     * @author MARCONA
     */
    private class tinyImpl implements ClientContentHandler {
        private Map tinyMap;
        private Map tempMap = new HashMap();
        private int depth = 0;

        public tinyImpl(Map resMap) {
            tinyMap = resMap;
        }

        public void endElement(String qName, String value) {
            ((TestObject)tempMap.get(qName)).value = value;
            tinyMap.put(qName, tempMap.get(qName));
            tempMap.remove(qName);
            depth--;
        }


        public void startElement(String qName, Map attributes) {
            depth++;
            tempMap.put(qName, new TestObject(qName, attributes, null, depth));
        }
    }
    /**
     * Objet de test resumant le contenu d'un balise dans ses attributs.
     *
     * @author MARCONA
     */
    private class TestObject {
        protected String tagName;
        protected Map attributes;
        protected String value;
        protected int depth;

        TestObject(String tagName, Map attributes, String value, int depth) {
            this.tagName = tagName;
            this.attributes = attributes;
            this.value = value;
            this.depth = depth;
        }

        public String toString() {
            return " TAG (" + depth + ") --> " + tagName + "\n VALUE --> " + value
            + "\n ATTRIBUTES --> " + attributes;
        }
    }
}
