// 
// Decompiled by Procyon v0.5.30
// 

package processing.xml;

import java.util.Enumeration;
import java.util.Vector;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.io.PrintWriter;

public class XMLWriter
{
    private PrintWriter writer;
    
    public XMLWriter(final Writer writer) {
        if (writer instanceof PrintWriter) {
            this.writer = (PrintWriter)writer;
        }
        else {
            this.writer = new PrintWriter(writer);
        }
    }
    
    public XMLWriter(final OutputStream outputStream) {
        this.writer = new PrintWriter(outputStream);
    }
    
    protected void finalize() throws Throwable {
        this.writer = null;
        super.finalize();
    }
    
    public void write(final XMLElement xmlElement) throws IOException {
        this.write(xmlElement, false, 0, true);
    }
    
    public void write(final XMLElement xmlElement, final boolean b) throws IOException {
        this.write(xmlElement, b, 0, true);
    }
    
    public void write(final XMLElement xmlElement, final boolean b, final int n) throws IOException {
        this.write(xmlElement, b, n, true);
    }
    
    public void write(final XMLElement xmlElement, final boolean b, final int n, final boolean b2) throws IOException {
        if (b) {
            for (int i = 0; i < n; ++i) {
                this.writer.print(' ');
            }
        }
        if (xmlElement.getLocalName() == null) {
            if (xmlElement.getContent() != null) {
                if (b) {
                    this.writeEncoded(xmlElement.getContent().trim());
                    this.writer.println();
                }
                else {
                    this.writeEncoded(xmlElement.getContent());
                }
            }
        }
        else {
            this.writer.print('<');
            this.writer.print(xmlElement.getName());
            final Vector<String> vector = new Vector<String>();
            if (xmlElement.getNamespace() != null) {
                if (xmlElement.getLocalName().equals(xmlElement.getName())) {
                    this.writer.print(" xmlns=\"" + xmlElement.getNamespace() + '\"');
                }
                else {
                    final String name = xmlElement.getName();
                    final String substring = name.substring(0, name.indexOf(58));
                    vector.addElement(substring);
                    this.writer.print(" xmlns:" + substring);
                    this.writer.print("=\"" + xmlElement.getNamespace() + "\"");
                }
            }
            final Enumeration<String> enumerateAttributeNames = xmlElement.enumerateAttributeNames();
            while (enumerateAttributeNames.hasMoreElements()) {
                final String s = enumerateAttributeNames.nextElement();
                final int index = s.indexOf(58);
                if (index >= 0) {
                    final String attributeNamespace = xmlElement.getAttributeNamespace(s);
                    if (attributeNamespace == null) {
                        continue;
                    }
                    final String substring2 = s.substring(0, index);
                    if (vector.contains(substring2)) {
                        continue;
                    }
                    this.writer.print(" xmlns:" + substring2);
                    this.writer.print("=\"" + attributeNamespace + '\"');
                    vector.addElement(substring2);
                }
            }
            final Enumeration<String> enumerateAttributeNames2 = xmlElement.enumerateAttributeNames();
            while (enumerateAttributeNames2.hasMoreElements()) {
                final String s2 = enumerateAttributeNames2.nextElement();
                final String attribute = xmlElement.getAttribute(s2, null);
                this.writer.print(" " + s2 + "=\"");
                this.writeEncoded(attribute);
                this.writer.print('\"');
            }
            if (xmlElement.getContent() != null && xmlElement.getContent().length() > 0) {
                this.writer.print('>');
                this.writeEncoded(xmlElement.getContent());
                this.writer.print("</" + xmlElement.getName() + '>');
                if (b) {
                    this.writer.println();
                }
            }
            else if (xmlElement.hasChildren() || !b2) {
                this.writer.print('>');
                if (b) {
                    this.writer.println();
                }
                final Enumeration<XMLElement> enumerateChildren = xmlElement.enumerateChildren();
                while (enumerateChildren.hasMoreElements()) {
                    this.write(enumerateChildren.nextElement(), b, n + 4, b2);
                }
                if (b) {
                    for (int j = 0; j < n; ++j) {
                        this.writer.print(' ');
                    }
                }
                this.writer.print("</" + xmlElement.getName() + ">");
                if (b) {
                    this.writer.println();
                }
            }
            else {
                this.writer.print("/>");
                if (b) {
                    this.writer.println();
                }
            }
        }
        this.writer.flush();
    }
    
    private void writeEncoded(final String s) {
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            switch (char1) {
                case 10: {
                    this.writer.print(char1);
                    break;
                }
                case 60: {
                    this.writer.print("&lt;");
                    break;
                }
                case 62: {
                    this.writer.print("&gt;");
                    break;
                }
                case 38: {
                    this.writer.print("&amp;");
                    break;
                }
                case 39: {
                    this.writer.print("&apos;");
                    break;
                }
                case 34: {
                    this.writer.print("&quot;");
                    break;
                }
                default: {
                    if (char1 < ' ' || char1 > '~') {
                        this.writer.print("&#x");
                        this.writer.print(Integer.toString(char1, 16));
                        this.writer.print(';');
                        break;
                    }
                    this.writer.print(char1);
                    break;
                }
            }
        }
    }
}
