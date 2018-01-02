// 
// Decompiled by Procyon v0.5.30
// 

package c;

import java.io.IOException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.net.URL;
import java.util.Hashtable;
import org.w3c.dom.Document;

public class a extends b
{
    private Document i;
    private Hashtable h;
    
    public a(final URL url, final int n) throws Exception {
        this.h = new Hashtable();
        this.i = this.a(url);
        this.a(n);
    }
    
    public a(final URL url, final String s) throws Exception {
        this.h = new Hashtable();
        this.i = this.a(url);
        this.a(this.if(s));
    }
    
    public String a(final String s) {
        if (this.h.containsKey(s)) {
            return this.h.get(s).toString();
        }
        return "N/A - " + s;
    }
    
    private int if(final String s) throws Exception {
        try {
            final NodeList childNodes = this.i.getElementsByTagName("Allgemein").item(0).getChildNodes();
            Node item = null;
            for (int i = 0; i < childNodes.getLength(); ++i) {
                if (childNodes.item(i).getNodeName().equals("Sprachen")) {
                    item = childNodes.item(i);
                }
            }
            if (item == null) {
                throw new Exception("Could not find node Sprachen");
            }
            for (int j = 0; j < item.getChildNodes().getLength(); ++j) {
                final Node item2 = item.getChildNodes().item(j);
                if (item2.getNodeName().equals("Sprachen") && item2.getNodeType() == 1) {
                    final int int1 = Integer.parseInt(item2.getAttributes().getNamedItem("ID").getTextContent());
                    if (s.trim().equalsIgnoreCase(item2.getAttributes().getNamedItem("LangID").getTextContent().trim())) {
                        org.kim.cadclick.common.utils.a.do("Language mapping: " + s + " --> " + int1);
                        return int1;
                    }
                }
            }
        }
        catch (Exception ex) {
            throw new Exception("Error parsing dictionary: " + ex.getMessage(), ex);
        }
        org.kim.cadclick.common.utils.a.do("Language mapping for \"" + s + "\" failed: Using " + 100 + " instead.");
        return 100;
    }
    
    private Document a(final URL url) throws Exception {
        final DocumentBuilderFactory instance = DocumentBuilderFactory.newInstance();
        instance.setIgnoringElementContentWhitespace(true);
        try {
            final Document parse = instance.newDocumentBuilder().parse(url.toString());
            if (parse.getElementsByTagName("Allgemein").getLength() < 1) {
                throw new Exception("There is no \"Allgemein\" node inside the XML document!");
            }
            return parse;
        }
        catch (ParserConfigurationException ex) {
            throw new Exception("Wrong parser configuration: " + ex.getMessage(), ex);
        }
        catch (SAXException ex2) {
            throw new Exception("Wrong XML file structure: " + ex2.getMessage(), ex2);
        }
        catch (IOException ex3) {
            throw new Exception("Could not read source file: " + ex3.getMessage(), ex3);
        }
        catch (Exception ex4) {
            throw new Exception("Error parsing document: " + ex4.getMessage(), ex4);
        }
    }
    
    private void a(final int n) {
        try {
            final NodeList childNodes = this.i.getElementsByTagName("Allgemein").item(0).getChildNodes();
            for (int i = 0; i < childNodes.getLength(); ++i) {
                if (childNodes.item(i).getNodeName().equals("GUI_Texte") && childNodes.item(i).getNodeType() == 1) {
                    final NodeList childNodes2 = childNodes.item(i).getChildNodes();
                    String textContent = null;
                    String textContent2 = null;
                    String textContent3 = null;
                    for (int j = 0; j < childNodes2.getLength(); ++j) {
                        if (childNodes2.item(j).getNodeName().equals("GUI_SprachTexte") && childNodes2.item(j).getNodeType() == 1) {
                            textContent = childNodes2.item(j).getAttributes().getNamedItem("RessourcenID").getTextContent();
                            final int int1 = Integer.parseInt(childNodes2.item(j).getAttributes().getNamedItem("Sprache").getTextContent());
                            if (int1 == n) {
                                textContent2 = childNodes2.item(j).getAttributes().getNamedItem("Text").getTextContent();
                            }
                            if (int1 == 100) {
                                textContent3 = childNodes2.item(j).getAttributes().getNamedItem("Text").getTextContent();
                            }
                        }
                    }
                    if (textContent2 != null) {
                        this.h.put(textContent, textContent2.replaceAll("\\\\n", "\n"));
                    }
                    else if (textContent3 != null) {
                        org.kim.cadclick.common.utils.a.for("Could not find acronym \"" + textContent + "\" for language " + n + ". Using default " + 100 + " instead.");
                        this.h.put(textContent, textContent3.replaceAll("\\\\n", "\n"));
                    }
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Error parsing dictionary: " + ex.getMessage());
        }
    }
}
