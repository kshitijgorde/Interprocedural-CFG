// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import org.jdom.Verifier;
import org.jdom.JDOMException;
import org.jdom.Content;
import org.jdom.Element;
import java.io.File;
import org.jdom.input.SAXBuilder;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format;
import org.jdom.Document;

public class Modification
{
    private String f2;
    private String balise1;
    private String balise2;
    
    public Modification() {
        this.f2 = "ISO-8859-15";
        this.balise1 = "message";
        this.balise2 = "pseudo";
    }
    
    public void enregistreFichier(final String s, final Document doc) {
        try {
            final Format prettyFormat = Format.getPrettyFormat();
            prettyFormat.setEncoding(this.f2);
            new XMLOutputter(prettyFormat).output(doc, new FileOutputStream(s));
        }
        catch (IOException ex) {}
    }
    
    public void modifierFichier(final String text, final String s, final String s2, final Creation creation) {
        try {
            final String string = s2.toLowerCase() + ".xml";
            final Document build = new SAXBuilder().build(new File(string));
            final Element rootElement = build.getRootElement();
            final Element child = new Element(this.balise1);
            final Element child2 = new Element(this.balise2);
            rootElement.addContent(child2);
            child2.setText(text);
            rootElement.addContent(child);
            child.setText(this.verfierlemessage(s));
            this.enregistreFichier(string, build);
        }
        catch (IOException ex) {}
        catch (JDOMException ex2) {
            new File(s2.toLowerCase() + ".xml").delete();
            creation.getVectorF().removeElement(s2.toLowerCase());
        }
    }
    
    public String verfierlemessage(final String s) {
        String string = "";
        for (int i = 0, length = s.length(); i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '\u0003') {
                ++i;
                if (Character.isDigit(s.charAt(i))) {
                    ++i;
                    if (s.charAt(i) != ',') {
                        continue;
                    }
                    ++i;
                    if (Character.isDigit(s.charAt(i))) {
                        continue;
                    }
                }
            }
            if (Verifier.isHighSurrogate(char1)) {
                if (++i >= length) {
                    continue;
                }
                if (!Verifier.isLowSurrogate(s.charAt(i))) {
                    continue;
                }
            }
            if (Verifier.isXMLCharacter(char1)) {
                string += char1;
            }
        }
        return string;
    }
}
