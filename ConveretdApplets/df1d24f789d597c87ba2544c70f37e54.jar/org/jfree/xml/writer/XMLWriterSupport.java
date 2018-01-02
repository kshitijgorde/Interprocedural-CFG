// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.writer;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Enumeration;
import java.util.Properties;
import java.io.Writer;
import java.io.IOException;

public class XMLWriterSupport
{
    public static final int OPEN_TAG_INCREASE = 1;
    public static final int CLOSE_TAG_DECREASE = 2;
    public static final int INDENT_ONLY = 3;
    public static final boolean CLOSE = true;
    public static final boolean OPEN = false;
    private static String lineSeparator;
    private SafeTagList safeTags;
    private int indentLevel;
    private String indentString;
    private boolean newLineOk;
    
    public XMLWriterSupport() {
        this(new SafeTagList(), 0);
    }
    
    public XMLWriterSupport(final SafeTagList list, final int n) {
        this(list, n, "    ");
    }
    
    public XMLWriterSupport(final SafeTagList safeTags, final int indentLevel, final String indentString) {
        if (indentString == null) {
            throw new NullPointerException("IndentString must not be null");
        }
        this.safeTags = safeTags;
        this.indentLevel = indentLevel;
        this.indentString = indentString;
    }
    
    public void startBlock() throws IOException {
        ++this.indentLevel;
        this.allowLineBreak();
    }
    
    public void endBlock() throws IOException {
        --this.indentLevel;
        this.allowLineBreak();
    }
    
    public void allowLineBreak() throws IOException {
        this.newLineOk = true;
    }
    
    public static String getLineSeparator() {
        if (XMLWriterSupport.lineSeparator == null) {
            try {
                XMLWriterSupport.lineSeparator = System.getProperty("line.separator", "\n");
            }
            catch (SecurityException ex) {
                XMLWriterSupport.lineSeparator = "\n";
            }
        }
        return XMLWriterSupport.lineSeparator;
    }
    
    public void writeTag(final Writer writer, final String s) throws IOException {
        if (this.newLineOk) {
            writer.write(getLineSeparator());
        }
        this.indent(writer, 1);
        writer.write("<");
        writer.write(s);
        writer.write(">");
        if (this.getSafeTags().isSafeForOpen(s)) {
            writer.write(getLineSeparator());
        }
    }
    
    public void writeCloseTag(final Writer writer, final String s) throws IOException {
        if (this.newLineOk || this.getSafeTags().isSafeForOpen(s)) {
            if (this.newLineOk) {
                writer.write(getLineSeparator());
            }
            this.indent(writer, 2);
        }
        else {
            this.decreaseIndent();
        }
        writer.write("</");
        writer.write(s);
        writer.write(">");
        if (this.getSafeTags().isSafeForClose(s)) {
            writer.write(getLineSeparator());
        }
        this.newLineOk = false;
    }
    
    public void writeTag(final Writer writer, final String s, final String s2, final String s3, final boolean b) throws IOException {
        final AttributeList list = new AttributeList();
        if (s2 != null) {
            list.setAttribute(s2, s3);
        }
        this.writeTag(writer, s, list, b);
    }
    
    public void writeTag(final Writer writer, final String s, final Properties properties, final boolean b) throws IOException {
        final AttributeList list = new AttributeList();
        final Enumeration<String> keys = ((Hashtable<String, V>)properties).keys();
        while (keys.hasMoreElements()) {
            final String s2 = keys.nextElement();
            list.setAttribute(s2, properties.getProperty(s2));
        }
        this.writeTag(writer, s, list, b);
    }
    
    public void writeTag(final Writer writer, final String s, final AttributeList list, final boolean b) throws IOException {
        if (this.newLineOk) {
            writer.write(getLineSeparator());
            this.newLineOk = false;
        }
        this.indent(writer, 1);
        writer.write("<");
        writer.write(s);
        final Iterator keys = list.keys();
        while (keys.hasNext()) {
            final String s2 = keys.next();
            final String attribute = list.getAttribute(s2);
            writer.write(" ");
            writer.write(s2);
            writer.write("=\"");
            writer.write(normalize(attribute));
            writer.write("\"");
        }
        if (b) {
            writer.write("/>");
            if (this.getSafeTags().isSafeForClose(s)) {
                writer.write(getLineSeparator());
            }
            this.decreaseIndent();
        }
        else {
            writer.write(">");
            if (this.getSafeTags().isSafeForOpen(s)) {
                writer.write(getLineSeparator());
            }
        }
    }
    
    public static String normalize(final String s) {
        if (s == null) {
            return "";
        }
        final StringBuffer sb = new StringBuffer();
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            switch (char1) {
                case 60: {
                    sb.append("&lt;");
                    break;
                }
                case 62: {
                    sb.append("&gt;");
                    break;
                }
                case 38: {
                    sb.append("&amp;");
                    break;
                }
                case 34: {
                    sb.append("&quot;");
                    break;
                }
                case 10: {
                    if (i <= 0) {
                        sb.append(getLineSeparator());
                        break;
                    }
                    if (sb.charAt(sb.length() - 1) != '\r') {
                        sb.append(getLineSeparator());
                        break;
                    }
                    sb.append('\n');
                    break;
                }
                default: {
                    sb.append(char1);
                    break;
                }
            }
        }
        return sb.toString();
    }
    
    public void indent(final Writer writer, final int n) throws IOException {
        if (n == 2) {
            this.decreaseIndent();
        }
        for (int i = 0; i < this.indentLevel; ++i) {
            writer.write(this.indentString);
        }
        if (n == 1) {
            this.increaseIndent();
        }
    }
    
    public int getIndentLevel() {
        return this.indentLevel;
    }
    
    protected void increaseIndent() {
        ++this.indentLevel;
    }
    
    protected void decreaseIndent() {
        --this.indentLevel;
    }
    
    public SafeTagList getSafeTags() {
        return this.safeTags;
    }
}
