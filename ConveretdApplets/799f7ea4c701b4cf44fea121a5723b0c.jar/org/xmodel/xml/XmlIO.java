// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xml;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.HashSet;
import org.xmodel.xpath.TextNode;
import org.xmodel.xpath.AttributeNode;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.File;
import org.xml.sax.InputSource;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.io.Reader;
import java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import org.xmodel.ModelObjectFactory;
import org.xml.sax.SAXParseException;
import org.xmodel.Xlate;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xmodel.log.Log;
import org.xml.sax.Locator;
import java.util.Set;
import java.util.List;
import java.io.ByteArrayOutputStream;
import org.xmodel.IModelObject;
import org.xmodel.IModelObjectFactory;
import org.xml.sax.ErrorHandler;
import javax.xml.parsers.SAXParser;
import org.xml.sax.helpers.DefaultHandler;

public class XmlIO implements IXmlIO
{
    DefaultHandler W;
    public static final byte[] header;
    public static final byte[] space;
    public static final byte[] less;
    public static final byte[] greater;
    public static final byte[] singleQuote;
    public static final byte[] doubleQuote;
    public static final byte[] equals;
    public static final byte[] slash;
    public static final byte[] text;
    public static final byte[] at;
    public static final byte[] qmark;
    public static final byte[] cr;
    public static final byte[] ellipsis;
    public static final byte[] unexpanded;
    private SAXParser P;
    private Whitespace O;
    private ErrorHandler Q;
    private IModelObjectFactory N;
    private IModelObject M;
    private IModelObject H;
    private IModelObject D;
    private ByteArrayOutputStream K;
    private List<String> S;
    private List<String> L;
    private Style V;
    private Set<IModelObject> I;
    private int B;
    private int G;
    private List<IModelObject> R;
    private Locator J;
    private boolean T;
    private boolean F;
    private boolean U;
    private boolean C;
    private boolean A;
    private static Log E;
    
    static {
        header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>".getBytes();
        space = " ".getBytes();
        less = "<".getBytes();
        greater = ">".getBytes();
        singleQuote = "'".getBytes();
        doubleQuote = "\"".getBytes();
        equals = "=".getBytes();
        slash = "/".getBytes();
        text = "text()".getBytes();
        at = "@".getBytes();
        qmark = "?".getBytes();
        cr = "\n".getBytes();
        ellipsis = "...".getBytes();
        unexpanded = "(unexpanded reference)".getBytes();
        XmlIO.E = Log.getLog("org.xmodel.xml");
    }
    
    public XmlIO() {
        this.W = new DefaultHandler() {
            @Override
            public void startDocument() throws SAXException {
                XmlIO.C(XmlIO.this, null);
                XmlIO.B(XmlIO.this, null);
                XmlIO.this.R.clear();
            }
            
            @Override
            public void endDocument() throws SAXException {
            }
            
            @Override
            public void setDocumentLocator(final Locator locator) {
                XmlIO.A(XmlIO.this, locator);
            }
            
            @Override
            public void startElement(final String s, final String s2, final String s3, final Attributes attributes) throws SAXException {
                if (XmlIO.this.S.size() > 0) {
                    final int index = s3.indexOf(":");
                    if (XmlIO.this.S.contains((index >= 0) ? s3.substring(0, index) : "")) {
                        return;
                    }
                }
                XmlIO.A(XmlIO.this, XmlIO.this.N.createObject(XmlIO.this.H, attributes, s3));
                final String value = attributes.getValue("id");
                if (value != null) {
                    XmlIO.this.D.setID(value);
                }
                if (XmlIO.this.H != null && XmlIO.this.U) {
                    final StringBuilder sb = (StringBuilder)XmlIO.this.H.getValue();
                    XmlIO.this.D.setAttribute("!position", (sb != null) ? sb.length() : 0);
                }
                if (XmlIO.this.C) {
                    final int n = XmlIO.this.J.getLineNumber() - 1;
                    for (int i = XmlIO.this.R.size(); i < n + 1; ++i) {
                        XmlIO.this.R.add(null);
                    }
                    XmlIO.this.R.set(n, XmlIO.this.D);
                }
                for (int length = attributes.getLength(), j = 0; j < length; ++j) {
                    final String qName = attributes.getQName(j);
                    final String value2 = attributes.getValue(j);
                    if (XmlIO.this.S.size() > 0) {
                        final int index2 = qName.indexOf(":");
                        if (XmlIO.this.S.contains((index2 >= 0) ? qName.substring(0, index2) : "")) {
                            continue;
                        }
                    }
                    XmlIO.this.D.setAttribute(qName, value2);
                }
                if (XmlIO.this.H != null) {
                    XmlIO.this.H.addChild(XmlIO.this.D);
                }
                XmlIO.B(XmlIO.this, XmlIO.this.D);
            }
            
            @Override
            public void characters(final char[] array, final int n, final int n2) throws SAXException {
                if (XmlIO.this.D != null) {
                    StringBuilder value = (StringBuilder)XmlIO.this.D.getValue();
                    if (value == null) {
                        value = new StringBuilder();
                        XmlIO.this.D.setValue(value);
                    }
                    value.append(array, n, n2);
                }
            }
            
            @Override
            public void endElement(final String s, final String s2, final String s3) throws SAXException {
                if (XmlIO.this.O == Whitespace.trim) {
                    final String trim = Xlate.get(XmlIO.this.H, "").trim();
                    if (trim.length() > 0) {
                        XmlIO.this.H.setValue(trim);
                    }
                    else {
                        XmlIO.this.H.removeAttribute("");
                    }
                }
                XmlIO.A(XmlIO.this, (IModelObject)null);
                if (XmlIO.this.H.getParent() == null) {
                    XmlIO.C(XmlIO.this, XmlIO.this.H);
                }
                XmlIO.B(XmlIO.this, XmlIO.this.H.getParent());
            }
            
            @Override
            public void processingInstruction(final String s, final String value) throws SAXException {
                if (XmlIO.this.H != null) {
                    final IModelObject object = XmlIO.this.N.createObject(XmlIO.this.H, "?" + s);
                    final int n = XmlIO.this.J.getLineNumber() - 1;
                    for (int i = XmlIO.this.R.size(); i < n + 1; ++i) {
                        XmlIO.this.R.add(null);
                    }
                    XmlIO.this.R.set(n, object);
                    object.setValue(value);
                    XmlIO.this.H.addChild(object);
                }
            }
            
            @Override
            public void warning(final SAXParseException e) throws SAXException {
                if (XmlIO.this.Q != null) {
                    XmlIO.this.Q.warning(e);
                }
                super.warning(e);
            }
            
            @Override
            public void error(final SAXParseException e) throws SAXException {
                if (XmlIO.this.Q != null) {
                    XmlIO.this.Q.error(e);
                }
                super.error(e);
            }
            
            @Override
            public void fatalError(final SAXParseException e) throws SAXException {
                if (XmlIO.this.Q != null) {
                    XmlIO.this.Q.fatalError(e);
                }
                super.fatalError(e);
            }
        };
        this.N = new ModelObjectFactory();
        this.S = new ArrayList<String>(3);
        this.L = new ArrayList<String>(3);
        this.R = new ArrayList<IModelObject>();
        this.B = 0;
        this.O = Whitespace.trim;
        this.F = true;
        this.C = true;
        this.A = false;
        try {
            final SAXParserFactory instance = SAXParserFactory.newInstance();
            instance.setValidating(false);
            instance.setFeature("http://xml.org/sax/features/namespaces", false);
            instance.setFeature("http://xml.org/sax/features/validation", false);
            instance.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
            instance.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            this.P = instance.newSAXParser();
        }
        catch (SAXException ex) {
            XmlIO.E.exception(ex);
        }
        catch (ParserConfigurationException ex2) {
            XmlIO.E.exception(ex2);
        }
    }
    
    @Override
    public void setWhitespace(final Whitespace o) {
        this.O = o;
    }
    
    @Override
    public void setFactory(final IModelObjectFactory n) {
        this.N = n;
    }
    
    @Override
    public void setMaxLines(final int b) {
        this.B = b;
    }
    
    public void setOneLineElements(final boolean f) {
        this.F = f;
    }
    
    public void setRecordElementPosition(final boolean u) {
        this.U = u;
        this.O = Whitespace.keep;
    }
    
    public void setLineNumberTracking(final boolean c) {
        this.C = c;
    }
    
    public void setCycleBreaking(final boolean a) {
        this.A = a;
    }
    
    public void setErrorHandler(final ErrorHandler q) {
        this.Q = q;
    }
    
    @Override
    public IModelObject read(final String s) throws XmlException {
        return this.read(new StringReader(s));
    }
    
    @Override
    public IModelObject read(final URL url) throws XmlException {
        try {
            final InputStream openStream = url.openStream();
            this.read(openStream);
            openStream.close();
            return this.M;
        }
        catch (IOException ex) {
            throw new XmlException("Unable to parse document from stream.", ex);
        }
    }
    
    @Override
    public IModelObject read(final InputStream is) throws XmlException {
        try {
            this.M = null;
            this.P.parse(is, this.W);
            return this.M;
        }
        catch (Exception ex) {
            throw new XmlException("Unable to parse document from stream.", ex);
        }
    }
    
    protected IModelObject read(final Reader characterStream) throws XmlException {
        try {
            this.M = null;
            this.P.parse(new InputSource(characterStream), this.W);
            return this.M;
        }
        catch (Exception ex) {
            throw new XmlException("Unable to parse document from stream.", ex);
        }
    }
    
    @Override
    public List<IModelObject> getLineInformation() {
        return this.R;
    }
    
    @Override
    public String write(final IModelObject modelObject) {
        return this.write(0, modelObject);
    }
    
    @Override
    public void write(final IModelObject modelObject, final File file) throws XmlException {
        this.write(0, modelObject, file);
    }
    
    @Override
    public void write(final IModelObject modelObject, final OutputStream outputStream) throws XmlException {
        this.write(0, modelObject, outputStream);
    }
    
    @Override
    public void write(final int n, final IModelObject modelObject, final OutputStream outputStream) throws XmlException {
        try {
            this.G = 0;
            this.R.clear();
            this.output(n, modelObject, outputStream);
        }
        catch (IOException ex) {
            throw new XmlException("Unable to write xml to stream: " + modelObject, ex);
        }
    }
    
    @Override
    public void write(final int n, final IModelObject modelObject, final File file) throws XmlException {
        try {
            this.G = 0;
            this.R.clear();
            this.output(n, modelObject, new FileOutputStream(file));
        }
        catch (IOException ex) {
            throw new XmlException("Unable to write xml to file: " + file + ", " + modelObject, ex);
        }
    }
    
    @Override
    public String write(final int n, final IModelObject modelObject) {
        if (this.K == null) {
            this.K = new ByteArrayOutputStream(32768);
        }
        this.K.reset();
        try {
            this.G = 0;
            this.R.clear();
            this.write(n, modelObject, this.K);
            return this.K.toString();
        }
        catch (XmlException ex) {
            XmlIO.E.exception(ex);
            return null;
        }
    }
    
    protected void output(final int n, final IModelObject modelObject, final OutputStream outputStream) throws IOException {
        if (this.T) {
            outputStream.write(XmlIO.header);
            outputStream.write(XmlIO.cr);
        }
        if (this.B > 0 && this.R.size() > this.B) {
            outputStream.write(XmlIO.ellipsis);
            return;
        }
        for (int i = this.R.size(); i < this.G + 1; ++i) {
            this.R.add(null);
        }
        this.R.set(this.G, modelObject);
        if (modelObject instanceof AttributeNode) {
            if (this.V != Style.compact) {
                for (int j = 0; j < n; ++j) {
                    outputStream.write(XmlIO.space);
                }
            }
            final String type = modelObject.getType();
            if (type.charAt(0) != '!') {
                outputStream.write(XmlIO.at);
                outputStream.write(type.getBytes());
                outputStream.write(XmlIO.equals);
                final Object value = modelObject.getValue();
                outputStream.write(XmlIO.doubleQuote);
                if (value != null) {
                    outputStream.write(value.toString().getBytes());
                }
                outputStream.write(XmlIO.doubleQuote);
            }
            return;
        }
        if (modelObject instanceof TextNode) {
            this.output(n, modelObject.getParent(), outputStream);
            return;
        }
        if (modelObject.getType().length() > 0 && modelObject.getType().charAt(0) == '?') {
            if (this.V != Style.compact) {
                for (int k = 0; k < n; ++k) {
                    outputStream.write(XmlIO.space);
                }
            }
            outputStream.write(XmlIO.less);
            outputStream.write(modelObject.getType().getBytes());
            final String value2 = Xlate.get(modelObject, "");
            if (value2.length() > 0) {
                outputStream.write(XmlIO.space);
                this.writeAttributes(modelObject, outputStream);
                outputStream.write(XmlIO.space);
                outputStream.write(value2.getBytes());
            }
            else {
                this.writeAttributes(modelObject, outputStream);
            }
            outputStream.write(XmlIO.qmark);
            outputStream.write(XmlIO.greater);
            if (this.V != Style.compact) {
                this.writeCR(outputStream);
            }
            return;
        }
        if (this.A) {
            if (this.I == null) {
                this.I = new HashSet<IModelObject>();
            }
            if (this.I.contains(modelObject)) {
                if (this.V != Style.compact) {
                    for (int l = 0; l < n; ++l) {
                        outputStream.write(XmlIO.space);
                    }
                }
                outputStream.write(XmlIO.less);
                final String type2 = modelObject.getType();
                outputStream.write(type2.getBytes());
                this.writeAttributes(modelObject, outputStream);
                outputStream.write(XmlIO.greater);
                outputStream.write(XmlIO.unexpanded);
                outputStream.write(XmlIO.less);
                outputStream.write(XmlIO.slash);
                outputStream.write(type2.getBytes());
                outputStream.write(XmlIO.greater);
                if (this.V != Style.compact) {
                    this.writeCR(outputStream);
                }
                return;
            }
            if (modelObject.getReferent() != modelObject) {
                this.I.add(modelObject);
            }
        }
        try {
            final String type3 = modelObject.getType();
            if (this.L.size() > 0) {
                final int index = type3.indexOf(":");
                if (this.L.contains((index >= 0) ? type3.substring(0, index) : "")) {
                    return;
                }
            }
            final String value3 = Xlate.get(modelObject, (String)null);
            final List<IModelObject> children = modelObject.getChildren();
            if (children.size() > 0) {
                if (this.V != Style.compact) {
                    for (int n2 = 0; n2 < n; ++n2) {
                        outputStream.write(XmlIO.space);
                    }
                }
                outputStream.write(XmlIO.less);
                outputStream.write(type3.getBytes());
                this.writeAttributes(modelObject, outputStream);
                outputStream.write(XmlIO.greater);
                if (this.O == Whitespace.trim) {
                    if (value3 != null) {
                        outputStream.write(encodeEntityReferences(value3, "\"'").getBytes());
                    }
                    if (this.V != Style.compact) {
                        this.writeCR(outputStream);
                    }
                    final Iterator<IModelObject> iterator = children.iterator();
                    while (iterator.hasNext()) {
                        this.output(n + 2, iterator.next(), outputStream);
                    }
                }
                else if (value3 != null) {
                    final String encodeEntityReferences = encodeEntityReferences(value3, "\"'");
                    int n3 = 0;
                    for (final IModelObject modelObject2 : children) {
                        int n4 = Xlate.get(modelObject2, "!position", -1);
                        if (n4 < 0) {
                            n4 = encodeEntityReferences.length();
                        }
                        outputStream.write(encodeEntityReferences.substring(n3, n4).getBytes());
                        n3 = n4;
                        this.output(n + 2, modelObject2, outputStream);
                    }
                    outputStream.write(encodeEntityReferences.substring(n3).getBytes());
                }
                else {
                    final Iterator<IModelObject> iterator3 = children.iterator();
                    while (iterator3.hasNext()) {
                        this.output(n + 2, iterator3.next(), outputStream);
                    }
                }
                if (this.V != Style.compact) {
                    for (int n5 = 0; n5 < n; ++n5) {
                        outputStream.write(XmlIO.space);
                    }
                }
                outputStream.write(XmlIO.less);
                outputStream.write(XmlIO.slash);
                outputStream.write(type3.getBytes());
                outputStream.write(XmlIO.greater);
                if (this.V != Style.compact) {
                    this.writeCR(outputStream);
                }
            }
            else if ((value3 != null && value3.length() > 0) || !this.F) {
                if (this.V != Style.compact) {
                    for (int n6 = 0; n6 < n; ++n6) {
                        outputStream.write(XmlIO.space);
                    }
                }
                outputStream.write(XmlIO.less);
                outputStream.write(type3.getBytes());
                this.writeAttributes(modelObject, outputStream);
                outputStream.write(XmlIO.greater);
                if (value3 != null) {
                    outputStream.write(encodeEntityReferences(value3, "\"'").getBytes());
                }
                if (value3 != null && value3.length() > 0 && value3.charAt(value3.length() - 1) == '\n' && this.V != Style.compact) {
                    for (int n7 = 0; n7 < n; ++n7) {
                        outputStream.write(XmlIO.space);
                    }
                }
                outputStream.write(XmlIO.less);
                outputStream.write(XmlIO.slash);
                outputStream.write(type3.getBytes());
                outputStream.write(XmlIO.greater);
                if (this.V != Style.compact) {
                    this.writeCR(outputStream);
                }
            }
            else {
                if (this.V != Style.compact) {
                    for (int n8 = 0; n8 < n; ++n8) {
                        outputStream.write(XmlIO.space);
                    }
                }
                outputStream.write(XmlIO.less);
                outputStream.write(type3.getBytes());
                this.writeAttributes(modelObject, outputStream);
                outputStream.write(XmlIO.slash);
                outputStream.write(XmlIO.greater);
                if (this.V != Style.compact) {
                    this.writeCR(outputStream);
                }
            }
        }
        finally {
            if (this.I != null && modelObject.getReferent() != modelObject) {
                this.I.remove(modelObject);
            }
        }
        if (this.I != null && modelObject.getReferent() != modelObject) {
            this.I.remove(modelObject);
        }
    }
    
    private void A(final OutputStream outputStream, final String s) throws IOException {
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '\'') {
                break;
            }
            if (char1 == '\"') {
                outputStream.write(XmlIO.singleQuote);
                outputStream.write(encodeEntityReferences(s, "'\"").getBytes());
                outputStream.write(XmlIO.singleQuote);
                return;
            }
        }
        outputStream.write(XmlIO.doubleQuote);
        outputStream.write(encodeEntityReferences(s, "'\"").getBytes());
        outputStream.write(XmlIO.doubleQuote);
    }
    
    protected int writeCR(final OutputStream outputStream) throws IOException {
        outputStream.write(XmlIO.cr);
        return this.G++;
    }
    
    protected void writeAttributes(final IModelObject modelObject, final OutputStream outputStream) throws IOException {
        for (final String s : modelObject.getAttributeNames()) {
            if (s.length() != 0) {
                if (s.charAt(0) == '!') {
                    continue;
                }
                if (this.L.size() > 0) {
                    final int index = s.indexOf(":");
                    if (this.L.contains((index >= 0) ? s.substring(0, index) : "")) {
                        continue;
                    }
                }
                final String value = Xlate.get(modelObject, s, (String)null);
                if (value == null) {
                    continue;
                }
                outputStream.write(XmlIO.space);
                outputStream.write(s.getBytes());
                outputStream.write(XmlIO.equals);
                this.A(outputStream, value);
            }
        }
    }
    
    @Override
    public void skipInputPrefix(final String s) {
        this.S.add(s);
    }
    
    @Override
    public void skipOutputPrefix(final String s) {
        this.L.add(s);
    }
    
    @Override
    public void setOutputStyle(final Style v) {
        this.V = v;
    }
    
    public static String encodeEntityReferences(final String s, final String s2) {
        if (s == null) {
            return s;
        }
        final int length = s.length();
        final StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (s2 == null || s2.indexOf(char1) == -1) {
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
                    case 39: {
                        sb.append("&apos;");
                        break;
                    }
                    case 34: {
                        sb.append("&quot;");
                        break;
                    }
                    default: {
                        sb.append(char1);
                        break;
                    }
                }
            }
            else {
                sb.append(char1);
            }
        }
        return sb.toString();
    }
    
    public static String toString(final IModelObject modelObject) {
        final XmlIO xmlIO = new XmlIO();
        xmlIO.setOutputStyle(Style.printable);
        return xmlIO.write(modelObject);
    }
    
    public static void main(final String[] array) throws Exception {
        final String s = "<html>\n  <head>\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n    <meta http-equiv=\"Content-Style-Type\" content=\"text/css\"/>\n    <title>Xidgets</title>\n    <link href=\"css/global.css\" rel=\"stylesheet\" type=\"text/css\"/>\n    <script type=\"text/javascript\" src=\"javascript/global.js\"></script>\n  </head>\n  \n  <body>\n    <table class=\"panes\">\n      <tr>\n        <td class=\"toppane\" colspan=\"2\">\n          <img class=\"banner\" src=\"images/xidgets.png\" width=\"600\"/>\n        </td>\n      </tr>\n    \n      <tr>\n        <td class=\"leftpane\" valign=\"top\">\n          <div class=\"menuitem selected\" onclick=\"go('index.html');\">Home</div>\n          <div class=\"menuitem\" onclick=\"go('Downloads.html');\">Download</div>\n          <div class=\"menuitem\" onclick=\"go('Xidget Overview.html');\">Overview</div>\n          <div class=\"menuitem\" onclick=\"go('Reference.html');\">Reference</div>\n          <div class=\"menuitem\" onclick=\"go('About.html');\">About</div>\n        </td>\n    \n        <td class=\"rightpane\">\n          <h4>What is a Xidget?</h4>\n          <p>A xidget is an fragment of XML that describes a graphical user-interface component (widget). Every visual and behavioral\n             characteristic of a widget is defined by an XPath expression, and is updated whenever the result of the XPath expression\n             changes.</p>\n             \n          <h4>What user-interface toolkits are supported?</h4>\n          <p>The reference implementation of xidgets was programmed with Java 1.5 with a binding for the Swing widget toolkit. Bindings\n             for C#/WinForms and C++/Qt are in progress.</p>\n             \n          <h4>What is required to begin using xidgets?</h4>\n          <p> Eclipse Java IDE, Java 1.5+ and Subversion 1.6.</p>\n          \n          <h4>Is the xidget framework extensible?</h4>\n          <p>Yes. The xidget framework was designed to be extensible in three areas: widgets, scripting and data-model.</p>\n          \n          <h4>What are the terms of the software license?</h4>\n          <p>Xidgets are distributed under the <a href=\"http://www.apache.org/licenses/LICENSE-2.0.html\"><u>Apache 2.0 software license agreement</u></a> \n             which allows individuals and companies to develop and market proprietary software that uses the xidget libraries. However, any \n             modifications to the xidget libraries are open-source.</p>\n        </td>\n      </tr>\n      \n      <tr>\n        <td class=\"bottompane\" colspan=\"2\" align=\"center\">\n          <span class=\"copyright\">Created by Bob Dunnagan &copy;2011</span>\n        </td>\n      </tr>\n    </table>\n  </body>\n</html>";
        final XmlIO xmlIO = new XmlIO();
        System.out.println(xmlIO.write(xmlIO.read(new StringReader(s))));
    }
    
    static /* synthetic */ void C(final XmlIO xmlIO, final IModelObject m) {
        xmlIO.M = m;
    }
    
    static /* synthetic */ void B(final XmlIO xmlIO, final IModelObject h) {
        xmlIO.H = h;
    }
    
    static /* synthetic */ void A(final XmlIO xmlIO, final Locator j) {
        xmlIO.J = j;
    }
    
    static /* synthetic */ void A(final XmlIO xmlIO, final IModelObject d) {
        xmlIO.D = d;
    }
}
