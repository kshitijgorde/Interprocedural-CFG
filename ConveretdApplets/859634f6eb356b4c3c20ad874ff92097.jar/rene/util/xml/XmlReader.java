// 
// Decompiled by Procyon v0.5.30
// 

package rene.util.xml;

import rene.util.list.ListElement;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import rene.util.list.Tree;
import rene.util.SimpleStringBuffer;
import java.io.BufferedReader;

public class XmlReader
{
    BufferedReader In;
    SimpleStringBuffer buf;
    String Line;
    int LinePos;
    
    public XmlReader(final BufferedReader in) {
        this.buf = new SimpleStringBuffer(10000);
        this.In = in;
    }
    
    public XmlTree scan() throws XmlReaderException {
        int read;
        do {
            read = this.read();
            if (read == -1) {
                return null;
            }
        } while (read != 60 || !this.found("?xml"));
        final String scan = this.scanFor("?>");
        if (scan == null) {
            return null;
        }
        final XmlTree xmlTree = new XmlTree(new XmlTagRoot());
        xmlTree.addchild(new XmlTree(new XmlTagPI(scan)));
        this.scanContent(xmlTree);
        return xmlTree;
    }
    
    public void scanContent(final XmlTree xmlTree) throws XmlReaderException {
        while (true) {
            final String scan = this.scanFor('<');
            if (scan == null) {
                if (xmlTree.getTag() instanceof XmlTagRoot) {
                    return;
                }
                this.exception("File ended surprisingly");
            }
            if (!this.empty(scan)) {
                xmlTree.addchild(new XmlTree(new XmlTagText(XmlTranslator.toText(scan))));
            }
            if (this.found("!--")) {
                this.scanFor("-->");
            }
            else if (this.found("!")) {
                this.scanTagFor('>');
            }
            else if (this.found("?")) {
                xmlTree.addchild(new XmlTree(new XmlTagPI(this.scanTagFor("?>"))));
            }
            else {
                final String scanTag = this.scanTagFor('>');
                if (scanTag == null) {
                    this.exception("> missing");
                }
                if (scanTag.startsWith("/")) {
                    if (scanTag.substring(1).equals(xmlTree.getTag().name())) {
                        return;
                    }
                    this.exception("End tag without start tag");
                }
                if (scanTag.endsWith("/")) {
                    xmlTree.addchild(new XmlTree(new XmlTag(scanTag.substring(0, scanTag.length() - 1))));
                }
                else {
                    final XmlTree xmlTree2 = new XmlTree(new XmlTag(scanTag));
                    this.scanContent(xmlTree2);
                    xmlTree.addchild(xmlTree2);
                }
            }
        }
    }
    
    public boolean empty(final String s) {
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 != ' ' && char1 != '\n' && char1 != '\t') {
                return false;
            }
        }
        return true;
    }
    
    public int skipBlanks() throws XmlReaderException {
        int read;
        while (true) {
            read = this.read();
            if (read != 32 && read != 9) {
                if (read == 10) {
                    continue;
                }
                break;
            }
        }
        return read;
    }
    
    public String scanFor(final char c) throws XmlReaderException {
        this.buf.clear();
        int i = this.read();
        while (i != c) {
            this.buf.append((char)i);
            i = this.read();
            if (i < '\0') {
                return null;
            }
        }
        return this.buf.toString();
    }
    
    public String scanFor(final String s) throws XmlReaderException {
        this.buf.clear();
        while (!this.found(s)) {
            final int read = this.read();
            if (read < 0) {
                return null;
            }
            this.buf.append((char)read);
        }
        for (int i = 0; i < s.length(); ++i) {
            this.read();
        }
        return this.buf.toString();
    }
    
    public String scanTagFor(final char c) throws XmlReaderException {
        this.buf.clear();
        int i = this.read();
        while (i != c) {
            if (i == '\"') {
                this.buf.append((char)i);
                while (true) {
                    final int read = this.read();
                    if (read < 0) {
                        return null;
                    }
                    if (read == 34) {
                        this.buf.append((char)read);
                        break;
                    }
                    this.buf.append((char)read);
                }
            }
            else if (i == '\'') {
                this.buf.append((char)i);
                while (true) {
                    final int read2 = this.read();
                    if (read2 < 0) {
                        return null;
                    }
                    if (read2 == 39) {
                        this.buf.append((char)read2);
                        break;
                    }
                    this.buf.append((char)read2);
                }
            }
            else {
                this.buf.append((char)i);
            }
            i = this.read();
            if (i < '\0') {
                return null;
            }
        }
        return this.buf.toString();
    }
    
    public String scanTagFor(final String s) throws XmlReaderException {
        this.buf.clear();
        while (!this.found(s)) {
            final int read = this.read();
            if (read < 0) {
                return null;
            }
            if (read == 34) {
                this.buf.append((char)read);
                while (true) {
                    final int read2 = this.read();
                    if (read2 < 0) {
                        return null;
                    }
                    if (read2 == 34) {
                        this.buf.append((char)read2);
                        break;
                    }
                    this.buf.append((char)read2);
                }
            }
            else if (read == 39) {
                this.buf.append((char)read);
                while (true) {
                    final int read3 = this.read();
                    if (read3 < 0) {
                        return null;
                    }
                    if (read3 == 39) {
                        this.buf.append((char)read3);
                        break;
                    }
                    this.buf.append((char)read3);
                }
            }
            else {
                this.buf.append((char)read);
            }
        }
        for (int i = 0; i < s.length(); ++i) {
            this.read();
        }
        return this.buf.toString();
    }
    
    public int read() throws XmlReaderException {
        try {
            if (this.Line == null) {
                this.Line = this.In.readLine();
                this.LinePos = 0;
            }
            if (this.Line == null) {
                return -1;
            }
            if (this.LinePos >= this.Line.length()) {
                this.Line = null;
                return 10;
            }
            return this.Line.charAt(this.LinePos++);
        }
        catch (Exception ex) {
            return -1;
        }
    }
    
    public boolean found(final String s) {
        final int length = s.length();
        if (this.LinePos + length > this.Line.length()) {
            return false;
        }
        for (int i = 0; i < length; ++i) {
            if (s.charAt(i) != this.Line.charAt(this.LinePos + i)) {
                return false;
            }
        }
        return true;
    }
    
    public void exception(final String s) throws XmlReaderException {
        throw new XmlReaderException(s, this.Line, this.LinePos);
    }
    
    public static void main(final String[] array) {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("rene\\util\\xml\\test.xml"), "UTF8"));
            final XmlTree scan = new XmlReader(bufferedReader).scan();
            bufferedReader.close();
            print(scan);
        }
        catch (XmlReaderException ex) {
            System.out.println(String.valueOf(ex.toString()) + "\n" + ex.getLine() + "\n" + "Position : " + ex.getPos());
        }
        catch (IOException ex2) {
            System.out.println(ex2);
        }
    }
    
    public static void print(final XmlTree xmlTree) {
        final XmlTag tag = xmlTree.getTag();
        System.out.print("<" + tag.name());
        for (int i = 0; i < tag.countParams(); ++i) {
            System.out.print(" " + tag.getParam(i) + "=\"" + tag.getValue(i) + "\"");
        }
        System.out.println(">");
        for (ListElement listElement = xmlTree.children().first(); listElement != null; listElement = listElement.next()) {
            print((XmlTree)listElement.content());
        }
        System.out.println("</" + tag.name() + ">");
    }
    
    public static boolean testXml(final String s) {
        int n;
        for (n = 0; n < s.length() && s.charAt(n) != '<'; ++n) {}
        return n < s.length() && s.substring(n).startsWith("<?xml");
    }
}
