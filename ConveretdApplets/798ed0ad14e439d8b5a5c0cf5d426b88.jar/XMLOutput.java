import java.util.Enumeration;
import java.awt.Font;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Properties;

// 
// Decompiled by Procyon v0.5.30
// 

public class XMLOutput
{
    StringBuffer XMLTag;
    int append;
    int appendAttributes;
    String charAt;
    String checkCallOrder;
    String clear;
    private Properties attributes;
    String complement;
    private int line;
    public String namespaceURL;
    public Stack stack;
    public Stack namespaces;
    Attributes equals;
    Attributes espais;
    int estil;
    int exchangeProperties;
    
    public XMLOutput(final String charAt, final String checkCallOrder) {
        this.XMLTag = new StringBuffer();
        this.append = 1;
        this.appendAttributes = 0;
        this.attributes = new Properties();
        this.stack = new Stack();
        this.namespaces = new Stack();
        this.equals = new Attributes();
        this.estil = -1;
        this.exchangeProperties = -1;
        this.line = 1;
        this.charAt = charAt;
        this.checkCallOrder = checkCallOrder;
    }
    
    public final void XMLTag(final int n, final String s) {
        this.XMLTag(n, s, "mathml");
    }
    
    public final void XMLTag(final int n, final String s, final String complement) {
        switch (n) {
            case 0: {
                this.pending();
                this.espais();
                this.clear = s;
                this.attributes.clear();
                this.append = 4096;
                ++this.appendAttributes;
                break;
            }
            case 1: {
                --this.appendAttributes;
                if (s.equals(this.clear) && this.append == 4096) {
                    this.append = 12288;
                    break;
                }
                this.pending();
                this.espais();
                this.clear = s;
                this.attributes.clear();
                this.append = 8192;
                break;
            }
            case 2: {
                this.XMLTag(0, s, complement);
                this.XMLTag(1, s, complement);
                break;
            }
        }
        this.complement = complement;
    }
    
    private void espais() {
        if (this.append != 2) {
            if (this.append != 1) {
                this.newLine();
            }
            for (int i = 0; i < this.appendAttributes; ++i) {
                this.XMLTag.append(this.charAt);
            }
        }
    }
    
    public final void newLine() {
        this.pending();
        this.XMLTag.append(this.checkCallOrder);
        ++this.line;
    }
    
    final void I(final String s) {
        this.pending();
        this.XMLTag.append(s);
        this.append = 2;
    }
    
    public final void textXML(final String s) {
        this.pending();
        this.XMLTag.append(s);
        this.append = 2;
    }
    
    private void pending() {
        if ((this.append & 0x3000) != 0x0) {
            this.XMLTag.append("<");
            if (this.append == 8192) {
                this.complement = this.namespaces.pop();
                this.XMLTag.append("/");
            }
            else if (this.append != 12288) {
                this.namespaces.push(this.complement);
            }
            this.XMLTag.append(this.clear);
            appendAttributes(this.XMLTag, this.attributes);
            if (this.append == 12288) {
                this.XMLTag.append("/");
            }
            this.XMLTag.append(">");
            this.append = 0;
        }
    }
    
    public final void attributeXML(final String s, final String s2) {
        ((Hashtable<String, String>)this.attributes).put(s, s2);
    }
    
    public final String toString() {
        this.pending();
        this.XMLTag.append(this.checkCallOrder);
        return this.XMLTag.toString();
    }
    
    public final void pushFormat() {
        this.checkCallOrder(0, true);
        this.stack.push(this.equals);
    }
    
    public final void doOuterFormat_begin(final AbstractBox abstractBox, final String s) {
        this.checkCallOrder(1, true);
        this.espais = Attributes.complement(abstractBox.getAttributes(), this.equals);
        Attributes.formatXML1(this, this.espais.estil, this.espais.quins_estil, s);
        this.stack.push(this.espais);
    }
    
    public final void doInnerFormat(final AbstractBox abstractBox, final String s) {
        this.checkCallOrder(2, true);
        if (s != null && s.equals("math") && abstractBox.getParentBox() == null) {
            this.espais = new Attributes(abstractBox.estil & 0x100, abstractBox.forca_estil & 0x100);
        }
        final Font nominalFont = abstractBox.getNominalFont();
        if (nominalFont != null) {
            Attributes.formatXML2(this, this.espais, nominalFont, abstractBox.styles, s);
        }
        if (abstractBox.hasProperties()) {
            final Hashtable hashtable = new Hashtable();
            abstractBox.exchangeProperties(hashtable, 1);
            Attributes.propertiesXML(this, hashtable);
        }
    }
    
    public final void setFormat(final AbstractBox abstractBox, final String s) {
        this.checkCallOrder(3, true);
        if (s != null && s.equals("math") && abstractBox.getParentBox() == null) {
            this.equals = Attributes.join(this.equals, this.espais);
        }
        else {
            this.equals = Attributes.join(this.equals, abstractBox.getAttributes());
        }
    }
    
    public final void doOuterFormat_end(final String s) {
        this.checkCallOrder(0, false);
        this.espais = this.stack.pop();
        Attributes.formatXML1end(this, this.espais.estil, this.espais.quins_estil, s);
    }
    
    public final void popFormat() {
        this.checkCallOrder(1, false);
        this.equals = this.stack.pop();
    }
    
    private final void checkCallOrder(final int n, final boolean b) {
    }
    
    public static final int countChildren(final String s) {
        int i = 0;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        for (int length = s.length(); i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '<') {
                n3 = 1;
                if (i + 1 >= length || s.charAt(i + 1) != '/') {
                    if (n == 0) {
                        ++n2;
                    }
                    ++n;
                }
                else {
                    --n;
                }
            }
            if (n3 != 0 && char1 == '>') {
                n3 = 0;
                if (i != 0) {
                    if (s.charAt(i - 1) == '/') {
                        --n;
                    }
                }
            }
        }
        return n2;
    }
    
    final int I() {
        return this.line;
    }
    
    private static void appendAttributes(final StringBuffer sb, final Properties properties) {
        final Enumeration<String> keys = ((Hashtable<String, V>)properties).keys();
        final int size = properties.size();
        final String[] array = new String[size];
        int n = 0;
        while (keys.hasMoreElements()) {
            array[n++] = keys.nextElement();
        }
        Utils.sort(array);
        for (int i = 0; i < size; ++i) {
            sb.append(" ");
            sb.append(array[i]);
            sb.append("=\"");
            sb.append(XMLParser.string2CharData(properties.getProperty(array[i])));
            sb.append("\"");
        }
    }
}
