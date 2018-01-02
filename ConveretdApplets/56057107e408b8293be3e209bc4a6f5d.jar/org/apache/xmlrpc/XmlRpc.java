// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xmlrpc;

import java.text.ParseException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Date;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import org.xml.sax.SAXParseException;
import org.xml.sax.AttributeList;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import org.xml.sax.ErrorHandler;
import org.xml.sax.DocumentHandler;
import org.xml.sax.Parser;
import java.io.InputStream;
import java.util.Properties;
import java.util.Stack;
import java.util.Hashtable;
import org.xml.sax.HandlerBase;

public abstract class XmlRpc extends HandlerBase
{
    public static final String version = "Apache XML-RPC 1.0";
    private static final String DEFAULT_PARSER;
    private static int maxThreads;
    String methodName;
    private static Class parserClass;
    private static Hashtable saxDrivers;
    Stack values;
    Value currentValue;
    static Formatter dateformat;
    StringBuffer cdata;
    boolean readCdata;
    static final int STRING = 0;
    static final int INTEGER = 1;
    static final int BOOLEAN = 2;
    static final int DOUBLE = 3;
    static final int DATE = 4;
    static final int BASE64 = 5;
    static final int STRUCT = 6;
    static final int ARRAY = 7;
    int errorLevel;
    String errorMsg;
    static final int NONE = 0;
    static final int RECOVERABLE = 1;
    static final int FATAL = 2;
    static boolean keepalive;
    public static boolean debug;
    static final String[] types;
    static String encoding;
    static Properties encodings;
    static /* synthetic */ Class class$uk$co$wilson$xml$MinML;
    
    public static void setDriver(final String s) throws ClassNotFoundException {
        String s2 = null;
        try {
            s2 = XmlRpc.saxDrivers.get(s);
            if (s2 == null) {
                s2 = s;
            }
            XmlRpc.parserClass = Class.forName(s2);
        }
        catch (ClassNotFoundException ex) {
            throw new ClassNotFoundException("SAX driver not found: " + s2);
        }
    }
    
    public static void setDriver(final Class parserClass) {
        XmlRpc.parserClass = parserClass;
    }
    
    public static void setEncoding(final String encoding) {
        XmlRpc.encoding = encoding;
    }
    
    public String getEncoding() {
        return XmlRpc.encodings.getProperty(XmlRpc.encoding, XmlRpc.encoding);
    }
    
    public static int getMaxThreads() {
        return XmlRpc.maxThreads;
    }
    
    public static void setMaxThreads(final int maxThreads) {
        XmlRpc.maxThreads = maxThreads;
    }
    
    public static void setDebug(final boolean debug) {
        XmlRpc.debug = debug;
    }
    
    public static void setKeepAlive(final boolean keepalive) {
        XmlRpc.keepalive = keepalive;
    }
    
    public static boolean getKeepAlive() {
        return XmlRpc.keepalive;
    }
    
    synchronized void parse(final InputStream byteStream) throws Exception {
        this.errorLevel = 0;
        this.errorMsg = null;
        this.values = new Stack();
        if (this.cdata == null) {
            this.cdata = new StringBuffer(128);
        }
        else {
            this.cdata.setLength(0);
        }
        this.readCdata = false;
        this.currentValue = null;
        final long currentTimeMillis = System.currentTimeMillis();
        if (XmlRpc.parserClass == null) {
            String driver;
            try {
                driver = System.getProperty("sax.driver", XmlRpc.DEFAULT_PARSER);
            }
            catch (SecurityException ex) {
                driver = XmlRpc.DEFAULT_PARSER;
            }
            setDriver(driver);
        }
        Parser parser;
        try {
            parser = XmlRpc.parserClass.newInstance();
        }
        catch (NoSuchMethodError noSuchMethodError) {
            throw new Exception("Can't create Parser: " + XmlRpc.parserClass);
        }
        parser.setDocumentHandler(this);
        parser.setErrorHandler(this);
        if (XmlRpc.debug) {
            System.err.println("Beginning parsing XML input stream");
        }
        parser.parse(new InputSource(byteStream));
        if (XmlRpc.debug) {
            System.err.println("Spent " + (System.currentTimeMillis() - currentTimeMillis) + " millis parsing");
        }
    }
    
    abstract void objectParsed(final Object p0);
    
    public void characters(final char[] array, final int n, final int n2) throws SAXException {
        if (this.readCdata) {
            this.cdata.append(array, n, n2);
        }
    }
    
    public void endElement(final String s) throws SAXException {
        if (XmlRpc.debug) {
            System.err.println("endElement: " + s);
        }
        if (this.currentValue != null && this.readCdata) {
            this.currentValue.characterData(this.cdata.toString());
            this.cdata.setLength(0);
            this.readCdata = false;
        }
        if ("value".equals(s)) {
            final int size = this.values.size();
            if (size < 2 || this.values.elementAt(size - 2).hashCode() != 6) {
                final Value currentValue = this.currentValue;
                this.values.pop();
                if (size < 2) {
                    this.objectParsed(currentValue.value);
                    this.currentValue = null;
                }
                else {
                    (this.currentValue = this.values.peek()).endElement(currentValue);
                }
            }
        }
        if ("member".equals(s)) {
            final Value currentValue2 = this.currentValue;
            this.values.pop();
            (this.currentValue = this.values.peek()).endElement(currentValue2);
        }
        else if ("methodName".equals(s)) {
            this.methodName = this.cdata.toString();
            this.cdata.setLength(0);
            this.readCdata = false;
        }
    }
    
    public void startElement(final String s, final AttributeList list) throws SAXException {
        if (XmlRpc.debug) {
            System.err.println("startElement: " + s);
        }
        if ("value".equals(s)) {
            final Value currentValue = new Value();
            this.values.push(currentValue);
            this.currentValue = currentValue;
            this.cdata.setLength(0);
            this.readCdata = true;
        }
        else if ("methodName".equals(s)) {
            this.cdata.setLength(0);
            this.readCdata = true;
        }
        else if ("name".equals(s)) {
            this.cdata.setLength(0);
            this.readCdata = true;
        }
        else if ("string".equals(s)) {
            this.cdata.setLength(0);
            this.readCdata = true;
        }
        else if ("i4".equals(s) || "int".equals(s)) {
            this.currentValue.setType(1);
            this.cdata.setLength(0);
            this.readCdata = true;
        }
        else if ("boolean".equals(s)) {
            this.currentValue.setType(2);
            this.cdata.setLength(0);
            this.readCdata = true;
        }
        else if ("double".equals(s)) {
            this.currentValue.setType(3);
            this.cdata.setLength(0);
            this.readCdata = true;
        }
        else if ("dateTime.iso8601".equals(s)) {
            this.currentValue.setType(4);
            this.cdata.setLength(0);
            this.readCdata = true;
        }
        else if ("base64".equals(s)) {
            this.currentValue.setType(5);
            this.cdata.setLength(0);
            this.readCdata = true;
        }
        else if ("struct".equals(s)) {
            this.currentValue.setType(6);
        }
        else if ("array".equals(s)) {
            this.currentValue.setType(7);
        }
    }
    
    public void error(final SAXParseException ex) throws SAXException {
        System.err.println("Error parsing XML: " + ex);
        this.errorLevel = 1;
        this.errorMsg = ex.toString();
    }
    
    public void fatalError(final SAXParseException ex) throws SAXException {
        System.err.println("Fatal error parsing XML: " + ex);
        this.errorLevel = 2;
        this.errorMsg = ex.toString();
    }
    
    static {
        DEFAULT_PARSER = ((XmlRpc.class$uk$co$wilson$xml$MinML == null) ? (XmlRpc.class$uk$co$wilson$xml$MinML = class$("uk.co.wilson.xml.MinML")) : XmlRpc.class$uk$co$wilson$xml$MinML).getName();
        XmlRpc.maxThreads = 100;
        (XmlRpc.saxDrivers = new Hashtable(8)).put("xerces", "org.apache.xerces.parsers.SAXParser");
        XmlRpc.saxDrivers.put("xp", "com.jclark.xml.sax.Driver");
        XmlRpc.saxDrivers.put("ibm1", "com.ibm.xml.parser.SAXDriver");
        XmlRpc.saxDrivers.put("ibm2", "com.ibm.xml.parsers.SAXParser");
        XmlRpc.saxDrivers.put("aelfred", "com.microstar.xml.SAXDriver");
        XmlRpc.saxDrivers.put("oracle1", "oracle.xml.parser.XMLParser");
        XmlRpc.saxDrivers.put("oracle2", "oracle.xml.parser.v2.SAXParser");
        XmlRpc.saxDrivers.put("openxml", "org.openxml.parser.XMLSAXParser");
        XmlRpc.dateformat = new Formatter();
        XmlRpc.keepalive = false;
        XmlRpc.debug = false;
        types = new String[] { "String", "Integer", "Boolean", "Double", "Date", "Base64", "Struct", "Array" };
        XmlRpc.encoding = "ISO8859_1";
        ((Hashtable<String, String>)(XmlRpc.encodings = new Properties())).put("UTF8", "UTF-8");
        ((Hashtable<String, String>)XmlRpc.encodings).put("ISO8859_1", "ISO-8859-1");
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    class XmlWriter extends OutputStreamWriter
    {
        protected static final String PROLOG_START = "<?xml version=\"1.0\" encoding=\"";
        protected static final String PROLOG_END = "\"?>";
        protected static final String CLOSING_TAG_START = "</";
        protected static final String SINGLE_TAG_END = "/>";
        protected static final String LESS_THAN_ENTITY = "&lt;";
        protected static final String GREATER_THAN_ENTITY = "&gt;";
        protected static final String AMPERSAND_ENTITY = "&amp;";
        
        public XmlWriter(final XmlRpc xmlRpc, final OutputStream outputStream) throws UnsupportedEncodingException, IOException {
            this(xmlRpc, outputStream, XmlRpc.encoding);
        }
        
        public XmlWriter(final OutputStream outputStream, final String s) throws UnsupportedEncodingException, IOException {
            super(outputStream, s);
            this.write("<?xml version=\"1.0\" encoding=\"");
            this.write(XmlRpc.encodings.getProperty(s, s));
            this.write("\"?>");
        }
        
        public void writeObject(final Object o) throws IOException {
            this.startElement("value");
            if (o == null) {
                throw new RuntimeException("null value not supported by XML-RPC");
            }
            if (o instanceof String) {
                this.chardata(o.toString());
            }
            else if (o instanceof Integer) {
                this.startElement("int");
                this.write(o.toString());
                this.endElement("int");
            }
            else if (o instanceof Boolean) {
                this.startElement("boolean");
                this.write(((boolean)o) ? "1" : "0");
                this.endElement("boolean");
            }
            else if (o instanceof Double || o instanceof Float) {
                this.startElement("double");
                this.write(o.toString());
                this.endElement("double");
            }
            else if (o instanceof Date) {
                this.startElement("dateTime.iso8601");
                this.write(XmlRpc.dateformat.format((Date)o));
                this.endElement("dateTime.iso8601");
            }
            else if (o instanceof byte[]) {
                this.startElement("base64");
                this.write(new String(Base64.encode((byte[])o)).toCharArray());
                this.endElement("base64");
            }
            else if (o instanceof Vector) {
                this.startElement("array");
                this.startElement("data");
                final Vector vector = (Vector)o;
                for (int size = vector.size(), i = 0; i < size; ++i) {
                    this.writeObject(vector.elementAt(i));
                }
                this.endElement("data");
                this.endElement("array");
            }
            else {
                if (!(o instanceof Hashtable)) {
                    throw new RuntimeException("unsupported Java type: " + o.getClass());
                }
                this.startElement("struct");
                final Hashtable hashtable = (Hashtable)o;
                final Enumeration<String> keys = hashtable.keys();
                while (keys.hasMoreElements()) {
                    final String s = keys.nextElement();
                    final Object value = hashtable.get(s);
                    this.startElement("member");
                    this.startElement("name");
                    this.write(s);
                    this.endElement("name");
                    this.writeObject(value);
                    this.endElement("member");
                }
                this.endElement("struct");
            }
            this.endElement("value");
        }
        
        protected void startElement(final String s) throws IOException {
            this.write(60);
            this.write(s);
            this.write(62);
        }
        
        protected void endElement(final String s) throws IOException {
            this.write("</");
            this.write(s);
            this.write(62);
        }
        
        protected void emptyElement(final String s) throws IOException {
            this.write(60);
            this.write(s);
            this.write("/>");
        }
        
        protected void chardata(final String s) throws IOException {
            for (int length = s.length(), i = 0; i < length; ++i) {
                final char char1 = s.charAt(i);
                switch (char1) {
                    case 60: {
                        this.write("&lt;");
                        break;
                    }
                    case 62: {
                        this.write("&gt;");
                        break;
                    }
                    case 38: {
                        this.write("&amp;");
                        break;
                    }
                    default: {
                        this.write(char1);
                        break;
                    }
                }
            }
        }
    }
    
    class Value
    {
        int type;
        Object value;
        String nextMemberName;
        Hashtable struct;
        Vector array;
        
        public Value() {
            this.type = 0;
        }
        
        public void endElement(final Value value) {
            switch (this.type) {
                case 7: {
                    this.array.addElement(value.value);
                    break;
                }
                case 6: {
                    this.struct.put(this.nextMemberName, value.value);
                    break;
                }
            }
        }
        
        public void setType(final int type) {
            switch (this.type = type) {
                case 7: {
                    final Vector vector = new Vector();
                    this.array = vector;
                    this.value = vector;
                    break;
                }
                case 6: {
                    final Hashtable hashtable = new Hashtable();
                    this.struct = hashtable;
                    this.value = hashtable;
                    break;
                }
            }
        }
        
        public void characterData(final String s) {
            switch (this.type) {
                case 1: {
                    this.value = new Integer(s.trim());
                    break;
                }
                case 2: {
                    this.value = ("1".equals(s.trim()) ? Boolean.TRUE : Boolean.FALSE);
                    break;
                }
                case 3: {
                    this.value = new Double(s.trim());
                    break;
                }
                case 4: {
                    try {
                        this.value = XmlRpc.dateformat.parse(s.trim());
                        break;
                    }
                    catch (ParseException ex) {
                        throw new RuntimeException(ex.getMessage());
                    }
                }
                case 5: {
                    this.value = Base64.decode(s.getBytes());
                    break;
                }
                case 0: {
                    this.value = s;
                    break;
                }
                case 6: {
                    this.nextMemberName = s;
                    break;
                }
            }
        }
        
        public int hashCode() {
            return this.type;
        }
        
        public String toString() {
            return XmlRpc.types[this.type] + " element " + this.value;
        }
    }
}
