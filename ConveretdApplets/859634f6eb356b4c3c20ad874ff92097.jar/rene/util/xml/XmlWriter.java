// 
// Decompiled by Procyon v0.5.30
// 

package rene.util.xml;

import java.util.Vector;
import rene.util.parser.StringParser;
import java.io.PrintWriter;

public class XmlWriter
{
    PrintWriter Out;
    
    public XmlWriter(final PrintWriter out) {
        this.Out = out;
    }
    
    public void printTag(final String s, final String s2) {
        this.startTag(s);
        this.print(s2);
        this.endTag(s);
    }
    
    public void printTagNewLine(final String s, final String s2) {
        this.printTag(s, s2);
        this.Out.println();
    }
    
    public void printTag(final String s, final String s2, final String s3, final String s4) {
        this.startTag(s, s2, s3);
        this.print(s4);
        this.endTag(s);
    }
    
    public void printTagNewLine(final String s, final String s2, final String s3, final String s4) {
        this.printTag(s, s2, s3, s4);
        this.Out.println();
    }
    
    public void startTag(final String s) {
        this.Out.print("<");
        this.Out.print(s);
        this.Out.print(">");
    }
    
    public void startTag(final String s, final String s2, final String s3) {
        this.Out.print("<");
        this.Out.print(s);
        this.printArg(s2, s3);
        this.Out.print(">");
    }
    
    public void startTagStart(final String s) {
        this.Out.print("<");
        this.Out.print(s);
    }
    
    public void startTagEnd() {
        this.Out.print(">");
    }
    
    public void startTagEndNewLine() {
        this.Out.print(">\n");
    }
    
    public void printArg(final String s, final String s2) {
        this.Out.print(" ");
        this.print(s);
        this.Out.print("=\"");
        this.Out.print(s2);
        this.Out.print("\"");
    }
    
    public void startTagNewLine(final String s, final String s2, final String s3) {
        this.startTag(s, s2, s3);
        this.Out.println();
    }
    
    public void startTagNewLine(final String s) {
        this.startTag(s);
        this.Out.println();
    }
    
    public void endTag(final String s) {
        this.Out.print("</");
        this.Out.print(s);
        this.Out.print(">");
    }
    
    public void endTagNewLine(final String s) {
        this.endTag(s);
        this.Out.println();
    }
    
    public void println() {
        this.Out.println();
    }
    
    public void print(final String s) {
        this.Out.print(XmlTranslator.toXml(s));
    }
    
    public void println(final String s) {
        this.Out.println(XmlTranslator.toXml(s));
    }
    
    public void printEncoding(final String s) {
        this.Out.println("<?xml version=\"1.0\" encoding=\"" + s + "\"?>");
    }
    
    public void printXls(final String s) {
        this.Out.println("<?xml-stylesheet href=\"" + s + "\" type=\"text/xsl\"?>");
    }
    
    public void printParagraphs(String s, final int n) {
        final Vector wrapwords = new StringParser(s).wrapwords(n);
        for (int i = 0; i < wrapwords.size(); ++i) {
            this.startTag("P");
            s = wrapwords.elementAt(i);
            final Vector wraplines = new StringParser(s).wraplines(n);
            for (int j = 0; j < wraplines.size(); ++j) {
                if (j > 0) {
                    this.println();
                }
                s = wraplines.elementAt(j);
                this.print(s);
            }
            this.endTagNewLine("P");
        }
    }
    
    public void printEncoding() {
        this.printEncoding("utf-8");
    }
    
    public void printDoctype(final String s, final String s2) {
        this.Out.print("<!DOCTYPE ");
        this.Out.print(s);
        this.Out.print(" SYSTEM \"");
        this.Out.print(s2);
        this.Out.println("\">");
    }
}
