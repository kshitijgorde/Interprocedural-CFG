// 
// Decompiled by Procyon v0.5.30
// 

package net.sf.jmimemagic;

import jmaster.util.log.B;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLReaderFactory;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;
import org.xml.sax.XMLReader;
import jmaster.util.log.A;
import org.xml.sax.ErrorHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.DefaultHandler;

public class C extends DefaultHandler implements ContentHandler, ErrorHandler
{
    private static String T;
    private static A J;
    protected static final String M = "http://xml.org/sax/features/namespaces";
    protected static final String L = "http://xml.org/sax/features/validation";
    protected static final String Q = "http://apache.org/xml/features/validation/schema";
    protected static final String W = "http://apache.org/xml/features/validation/schema-full-checking";
    protected static final String V = "org.apache.xerces.parsers.SAXParser";
    protected static final boolean O = true;
    protected static final boolean A = false;
    protected static final boolean R = false;
    protected static final boolean S = false;
    private boolean E;
    private XMLReader C;
    private ArrayList H;
    private Collection D;
    private I B;
    private F K;
    private HashMap I;
    private String N;
    private boolean P;
    private boolean F;
    private boolean G;
    private boolean U;
    static /* synthetic */ Class class$net$sf$jmimemagic$C;
    
    public C() {
        this.E = false;
        this.C = null;
        this.H = new ArrayList();
        this.D = new ArrayList();
        this.B = null;
        this.K = null;
        this.I = null;
        this.N = "";
        this.P = false;
        this.F = false;
        this.G = false;
        this.U = false;
        net.sf.jmimemagic.C.J.D("instantiated");
    }
    
    public synchronized void B() throws E {
        final boolean b = true;
        final boolean b2 = false;
        final boolean b3 = false;
        final boolean b4 = false;
        if (!this.E) {
            try {
                this.C = XMLReaderFactory.createXMLReader();
            }
            catch (Exception ex2) {
                try {
                    net.sf.jmimemagic.C.J.D("falling back to default parser: org.apache.xerces.parsers.SAXParser");
                    this.C = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
                }
                catch (Exception ex3) {
                    throw new E("unable to instantiate parser");
                }
            }
            try {
                this.C.setFeature("http://xml.org/sax/features/namespaces", b);
            }
            catch (SAXException ex4) {
                net.sf.jmimemagic.C.J.D("initialize(): warning: Parser does not support feature (http://xml.org/sax/features/namespaces)");
            }
            try {
                this.C.setFeature("http://xml.org/sax/features/validation", b2);
            }
            catch (SAXException ex5) {
                net.sf.jmimemagic.C.J.D("initialize(): warning: Parser does not support feature (http://xml.org/sax/features/validation)");
            }
            try {
                this.C.setFeature("http://apache.org/xml/features/validation/schema", b3);
            }
            catch (SAXNotRecognizedException ex6) {}
            catch (SAXNotSupportedException ex7) {
                net.sf.jmimemagic.C.J.D("initialize(): warning: Parser does not support feature (http://apache.org/xml/features/validation/schema)");
            }
            try {
                this.C.setFeature("http://apache.org/xml/features/validation/schema-full-checking", b4);
            }
            catch (SAXNotRecognizedException ex8) {}
            catch (SAXNotSupportedException ex9) {
                net.sf.jmimemagic.C.J.D("initialize(): warning: Parser does not support feature (http://apache.org/xml/features/validation/schema-full-checking)");
            }
            this.C.setErrorHandler(this);
            this.C.setContentHandler(this);
            try {
                final String string = ((net.sf.jmimemagic.C.class$net$sf$jmimemagic$C == null) ? (net.sf.jmimemagic.C.class$net$sf$jmimemagic$C = class$("net.sf.jmimemagic.C")) : net.sf.jmimemagic.C.class$net$sf$jmimemagic$C).getResource(net.sf.jmimemagic.C.T).toString();
                if (string == null) {
                    net.sf.jmimemagic.C.J.E("initialize(): couldn't load '" + string + "'");
                    throw new E("couldn't load '" + string + "'");
                }
                this.C.parse(string);
            }
            catch (SAXParseException ex10) {}
            catch (Exception ex) {
                ex.printStackTrace();
                throw new E("parse error occurred - " + ex.getMessage());
            }
            this.E = true;
        }
    }
    
    public Collection A() {
        return this.D;
    }
    
    public void startDocument() throws SAXException {
        net.sf.jmimemagic.C.J.D("startDocument()");
    }
    
    public void endDocument() throws SAXException {
        net.sf.jmimemagic.C.J.D("endDocument()");
    }
    
    public void processingInstruction(final String s, final String s2) throws SAXException {
    }
    
    public void characters(final char[] array, final int n, final int n2) throws SAXException {
        final String s = new String(array, n, n2);
        net.sf.jmimemagic.C.J.D("characters(): value is '" + s + "'");
        this.N += s;
    }
    
    public void ignorableWhitespace(final char[] array, final int n, final int n2) throws SAXException {
    }
    
    public void startElement(final String s, final String s2, final String s3, final Attributes attributes) throws SAXException {
        net.sf.jmimemagic.C.J.D("startElement()");
        net.sf.jmimemagic.C.J.D("startElement(): localName is '" + s2 + "'");
        if (s2.equals("match")) {
            net.sf.jmimemagic.C.J.D("startElement(): creating new matcher");
            this.K = new F();
            (this.B = new I()).A(this.K);
        }
        if (this.B != null) {
            if (s2.equals("mimetype")) {
                this.P = true;
            }
            else if (s2.equals("extension")) {
                this.F = true;
            }
            else if (s2.equals("description")) {
                this.G = true;
            }
            else if (s2.equals("test")) {
                this.U = true;
                for (int length = attributes.getLength(), i = 0; i < length; ++i) {
                    final String localName = attributes.getLocalName(i);
                    final String value = attributes.getValue(i);
                    if (localName.equals("offset")) {
                        if (!value.equals("")) {
                            this.K.B(new Integer(value));
                            net.sf.jmimemagic.C.J.D("startElement():   setting offset to '" + value + "'");
                        }
                    }
                    else if (localName.equals("length")) {
                        if (!value.equals("")) {
                            this.K.A(new Integer(value));
                            net.sf.jmimemagic.C.J.D("startElement():   setting length to '" + value + "'");
                        }
                    }
                    else if (localName.equals("type")) {
                        this.K.C(value);
                        net.sf.jmimemagic.C.J.D("startElement():   setting type to '" + value + "'");
                    }
                    else if (localName.equals("bitmask")) {
                        if (!value.equals("")) {
                            this.K.E(value);
                            net.sf.jmimemagic.C.J.D("startElement():   setting bitmask to '" + value + "'");
                        }
                    }
                    else if (localName.equals("comparator")) {
                        this.K.A(value);
                        net.sf.jmimemagic.C.J.D("startElement():   setting comparator to '" + value + "'");
                    }
                }
            }
            else if (s2.equals("property")) {
                final int length2 = attributes.getLength();
                String s4 = null;
                String s5 = null;
                for (int j = 0; j < length2; ++j) {
                    final String localName2 = attributes.getLocalName(j);
                    final String value2 = attributes.getValue(j);
                    if (localName2.equals("name")) {
                        if (!value2.equals("")) {
                            s4 = value2;
                        }
                    }
                    else if (localName2.equals("value") && !value2.equals("")) {
                        s5 = value2;
                    }
                }
                if (s4 != null && s5 != null) {
                    if (this.I == null) {
                        this.I = new HashMap();
                    }
                    if (!this.I.containsKey(s4)) {
                        this.I.put(s4, s5);
                        net.sf.jmimemagic.C.J.D("startElement():   setting property '" + s4 + "'='" + s5 + "'");
                    }
                    else {
                        net.sf.jmimemagic.C.J.D("startElement():   not setting property '" + s4 + "', duplicate key");
                    }
                }
            }
            else if (s2.equals("match-list")) {
                net.sf.jmimemagic.C.J.D("startElement(): found submatcher list");
                net.sf.jmimemagic.C.J.D("startElement(): pushing current matcher to stack");
                this.H.add(this.B);
            }
        }
    }
    
    public void endElement(final String s, final String s2, final String s3) throws SAXException {
        net.sf.jmimemagic.C.J.D("endElement()");
        net.sf.jmimemagic.C.J.D("endElement(): localName is '" + s2 + "'");
        if (this.P) {
            this.P = false;
            this.K.B(this.N);
            net.sf.jmimemagic.C.J.D("characters(): setting mimetype to '" + this.N + "'");
        }
        else if (this.F) {
            this.F = false;
            this.K.H(this.N);
            net.sf.jmimemagic.C.J.D("characters(): setting extension to '" + this.N + "'");
        }
        else if (this.G) {
            this.G = false;
            this.K.F(this.N);
            net.sf.jmimemagic.C.J.D("characters(): setting description to '" + this.N + "'");
        }
        else if (this.U) {
            this.U = false;
            this.K.A(this.A(this.N));
            net.sf.jmimemagic.C.J.D("characters(): setting test to '" + this.A(this.N) + "'");
        }
        this.N = "";
        if (s2.equals("match")) {
            if (this.B.A()) {
                this.K.A(this.I);
                if (this.H.size() == 0) {
                    net.sf.jmimemagic.C.J.D("endElement(): adding root matcher");
                    this.D.add(this.B);
                }
                else {
                    net.sf.jmimemagic.C.J.D("endElement(): adding sub matcher");
                    this.H.get(this.H.size() - 1).A(this.B);
                }
            }
            else {
                net.sf.jmimemagic.C.J.B("endElement(): not adding invalid matcher '" + this.K.K() + "'");
            }
            this.B = null;
            this.I = null;
        }
        else if (s2.equals("match-list")) {
            if (this.H.size() > 0) {
                net.sf.jmimemagic.C.J.D("endElement(): popping from the stack");
                this.B = this.H.get(this.H.size() - 1);
                this.H.remove(this.B);
            }
        }
        else if (s2.equals("mimetype")) {
            this.P = false;
        }
        else if (s2.equals("extension")) {
            this.F = false;
        }
        else if (s2.equals("description")) {
            this.G = false;
        }
        else if (s2.equals("test")) {
            this.U = false;
        }
    }
    
    public void warning(final SAXParseException ex) throws SAXException {
    }
    
    public void error(final SAXParseException ex) throws SAXException {
        throw ex;
    }
    
    public void fatalError(final SAXParseException ex) throws SAXException {
        throw ex;
    }
    
    private ByteBuffer A(final String s) {
        int n = 0;
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int index;
        while ((index = s.indexOf(92, n)) != -1) {
            if (s.charAt(index + 1) != '\\') {
                for (int i = n; i < index; ++i) {
                    byteArrayOutputStream.write(s.charAt(i));
                }
                if (index + 4 <= s.length()) {
                    try {
                        byteArrayOutputStream.write(Integer.parseInt(s.substring(index + 1, index + 4), 8));
                        n = (index += 4);
                    }
                    catch (NumberFormatException ex2) {
                        byteArrayOutputStream.write(92);
                        n = index + 1;
                    }
                }
                else {
                    byteArrayOutputStream.write(92);
                    n = index + 1;
                }
            }
            else {
                byteArrayOutputStream.write(92);
                n = index + 1;
            }
        }
        if (index < s.length()) {
            for (int j = n; j < s.length(); ++j) {
                byteArrayOutputStream.write(s.charAt(j));
            }
        }
        try {
            net.sf.jmimemagic.C.J.D("convertOctals(): returning buffer size '" + byteArrayOutputStream.size() + "'");
            return ByteBuffer.allocate(byteArrayOutputStream.size()).put(byteArrayOutputStream.toByteArray());
        }
        catch (Exception ex) {
            net.sf.jmimemagic.C.J.E("convertOctals(): error parsing string: " + ex);
            return ByteBuffer.allocate(0);
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        C.T = "/magic.xml";
        C.J = B.getInstance().getLog((C.class$net$sf$jmimemagic$C == null) ? (C.class$net$sf$jmimemagic$C = class$("net.sf.jmimemagic.C")) : C.class$net$sf$jmimemagic$C);
    }
}
