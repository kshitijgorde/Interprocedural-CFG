// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.dtm.ref;

import org.apache.xml.utils.WrappedRuntimeException;
import org.apache.xml.serialize.OutputFormat;
import java.io.OutputStream;
import org.apache.xml.serialize.XMLSerializer;
import java.lang.reflect.InvocationTargetException;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.apache.xml.res.XMLMessages;
import org.xml.sax.InputSource;
import org.xml.sax.DTDHandler;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.XMLReader;
import org.apache.xerces.parsers.SAXParser;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class IncrementalSAXSource_Xerces implements IncrementalSAXSource
{
    Method fParseSomeSetup;
    Method fParseSome;
    Object fPullParserConfig;
    Method fConfigSetInput;
    Method fConfigParse;
    Method fSetInputSource;
    Constructor fConfigInputSourceCtor;
    Method fConfigSetByteStream;
    Method fConfigSetCharStream;
    Method fConfigSetEncoding;
    Method fReset;
    SAXParser fIncrementalParser;
    private boolean fParseInProgress;
    private static final Object[] noparms;
    private static final Object[] parmsfalse;
    static /* synthetic */ Class class$org$apache$xerces$parsers$SAXParser;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$java$io$InputStream;
    static /* synthetic */ Class class$java$io$Reader;
    static /* synthetic */ Class class$org$xml$sax$InputSource;
    
    public IncrementalSAXSource_Xerces() throws NoSuchMethodException {
        this.fParseSomeSetup = null;
        this.fParseSome = null;
        this.fPullParserConfig = null;
        this.fConfigSetInput = null;
        this.fConfigParse = null;
        this.fSetInputSource = null;
        this.fConfigInputSourceCtor = null;
        this.fConfigSetByteStream = null;
        this.fConfigSetCharStream = null;
        this.fConfigSetEncoding = null;
        this.fReset = null;
        this.fParseInProgress = false;
        try {
            final Class xniConfigClass = ObjectFactory.findProviderClass("org.apache.xerces.xni.parser.XMLParserConfiguration", ObjectFactory.findClassLoader(), true);
            final Class[] args1 = { xniConfigClass };
            final Constructor ctor = ((IncrementalSAXSource_Xerces.class$org$apache$xerces$parsers$SAXParser == null) ? (IncrementalSAXSource_Xerces.class$org$apache$xerces$parsers$SAXParser = class$("org.apache.xerces.parsers.SAXParser")) : IncrementalSAXSource_Xerces.class$org$apache$xerces$parsers$SAXParser).getConstructor((Class[])args1);
            final Class xniStdConfigClass = ObjectFactory.findProviderClass("org.apache.xerces.parsers.StandardParserConfiguration", ObjectFactory.findClassLoader(), true);
            this.fPullParserConfig = xniStdConfigClass.newInstance();
            final Object[] args2 = { this.fPullParserConfig };
            this.fIncrementalParser = ctor.newInstance(args2);
            final Class fXniInputSourceClass = ObjectFactory.findProviderClass("org.apache.xerces.xni.parser.XMLInputSource", ObjectFactory.findClassLoader(), true);
            final Class[] args3 = { fXniInputSourceClass };
            this.fConfigSetInput = xniStdConfigClass.getMethod("setInputSource", (Class[])args3);
            final Class[] args4 = { (IncrementalSAXSource_Xerces.class$java$lang$String == null) ? (IncrementalSAXSource_Xerces.class$java$lang$String = class$("java.lang.String")) : IncrementalSAXSource_Xerces.class$java$lang$String, (IncrementalSAXSource_Xerces.class$java$lang$String == null) ? (IncrementalSAXSource_Xerces.class$java$lang$String = class$("java.lang.String")) : IncrementalSAXSource_Xerces.class$java$lang$String, (IncrementalSAXSource_Xerces.class$java$lang$String == null) ? (IncrementalSAXSource_Xerces.class$java$lang$String = class$("java.lang.String")) : IncrementalSAXSource_Xerces.class$java$lang$String };
            this.fConfigInputSourceCtor = fXniInputSourceClass.getConstructor((Class[])args4);
            final Class[] args5 = { (IncrementalSAXSource_Xerces.class$java$io$InputStream == null) ? (IncrementalSAXSource_Xerces.class$java$io$InputStream = class$("java.io.InputStream")) : IncrementalSAXSource_Xerces.class$java$io$InputStream };
            this.fConfigSetByteStream = fXniInputSourceClass.getMethod("setByteStream", (Class[])args5);
            final Class[] args6 = { (IncrementalSAXSource_Xerces.class$java$io$Reader == null) ? (IncrementalSAXSource_Xerces.class$java$io$Reader = class$("java.io.Reader")) : IncrementalSAXSource_Xerces.class$java$io$Reader };
            this.fConfigSetCharStream = fXniInputSourceClass.getMethod("setCharacterStream", (Class[])args6);
            final Class[] args7 = { (IncrementalSAXSource_Xerces.class$java$lang$String == null) ? (IncrementalSAXSource_Xerces.class$java$lang$String = class$("java.lang.String")) : IncrementalSAXSource_Xerces.class$java$lang$String };
            this.fConfigSetEncoding = fXniInputSourceClass.getMethod("setEncoding", (Class[])args7);
            final Class[] argsb = { Boolean.TYPE };
            this.fConfigParse = xniStdConfigClass.getMethod("parse", (Class[])argsb);
            final Class[] noargs = new Class[0];
            this.fReset = this.fIncrementalParser.getClass().getMethod("reset", (Class<?>[])noargs);
        }
        catch (Exception e) {
            final IncrementalSAXSource_Xerces dummy = new IncrementalSAXSource_Xerces(new SAXParser());
            this.fParseSomeSetup = dummy.fParseSomeSetup;
            this.fParseSome = dummy.fParseSome;
            this.fIncrementalParser = dummy.fIncrementalParser;
        }
    }
    
    public IncrementalSAXSource_Xerces(final SAXParser parser) throws NoSuchMethodException {
        this.fParseSomeSetup = null;
        this.fParseSome = null;
        this.fPullParserConfig = null;
        this.fConfigSetInput = null;
        this.fConfigParse = null;
        this.fSetInputSource = null;
        this.fConfigInputSourceCtor = null;
        this.fConfigSetByteStream = null;
        this.fConfigSetCharStream = null;
        this.fConfigSetEncoding = null;
        this.fReset = null;
        this.fParseInProgress = false;
        this.fIncrementalParser = parser;
        final Class me = parser.getClass();
        Class[] parms = { (IncrementalSAXSource_Xerces.class$org$xml$sax$InputSource == null) ? (IncrementalSAXSource_Xerces.class$org$xml$sax$InputSource = class$("org.xml.sax.InputSource")) : IncrementalSAXSource_Xerces.class$org$xml$sax$InputSource };
        this.fParseSomeSetup = me.getMethod("parseSomeSetup", (Class[])parms);
        parms = new Class[0];
        this.fParseSome = me.getMethod("parseSome", (Class[])parms);
    }
    
    public static IncrementalSAXSource createIncrementalSAXSource() {
        try {
            return new IncrementalSAXSource_Xerces();
        }
        catch (NoSuchMethodException e) {
            final IncrementalSAXSource_Filter iss = new IncrementalSAXSource_Filter();
            iss.setXMLReader(new SAXParser());
            return iss;
        }
    }
    
    public static IncrementalSAXSource createIncrementalSAXSource(final SAXParser parser) {
        try {
            return new IncrementalSAXSource_Xerces(parser);
        }
        catch (NoSuchMethodException e) {
            final IncrementalSAXSource_Filter iss = new IncrementalSAXSource_Filter();
            iss.setXMLReader(parser);
            return iss;
        }
    }
    
    public void setContentHandler(final ContentHandler handler) {
        this.fIncrementalParser.setContentHandler(handler);
    }
    
    public void setLexicalHandler(final LexicalHandler handler) {
        try {
            this.fIncrementalParser.setProperty("http://xml.org/sax/properties/lexical-handler", handler);
        }
        catch (SAXNotRecognizedException e) {}
        catch (SAXNotSupportedException ex) {}
    }
    
    public void setDTDHandler(final DTDHandler handler) {
        this.fIncrementalParser.setDTDHandler(handler);
    }
    
    public void startParse(final InputSource source) throws SAXException {
        if (this.fIncrementalParser == null) {
            throw new SAXException(XMLMessages.createXMLMessage("ER_STARTPARSE_NEEDS_SAXPARSER", null));
        }
        if (this.fParseInProgress) {
            throw new SAXException(XMLMessages.createXMLMessage("ER_STARTPARSE_WHILE_PARSING", null));
        }
        boolean ok = false;
        try {
            ok = this.parseSomeSetup(source);
        }
        catch (Exception ex) {
            throw new SAXException(ex);
        }
        if (!ok) {
            throw new SAXException(XMLMessages.createXMLMessage("ER_COULD_NOT_INIT_PARSER", null));
        }
    }
    
    public Object deliverMoreNodes(final boolean parsemore) {
        if (!parsemore) {
            this.fParseInProgress = false;
            return Boolean.FALSE;
        }
        Object arg;
        try {
            final boolean keepgoing = this.parseSome();
            arg = (keepgoing ? Boolean.TRUE : Boolean.FALSE);
        }
        catch (SAXException ex) {
            arg = ex;
        }
        catch (IOException ex2) {
            arg = ex2;
        }
        catch (Exception ex3) {
            arg = new SAXException(ex3);
        }
        return arg;
    }
    
    private boolean parseSomeSetup(final InputSource source) throws SAXException, IOException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (this.fConfigSetInput != null) {
            final Object[] parms1 = { source.getPublicId(), source.getSystemId(), null };
            final Object xmlsource = this.fConfigInputSourceCtor.newInstance(parms1);
            final Object[] parmsa = { source.getByteStream() };
            this.fConfigSetByteStream.invoke(xmlsource, parmsa);
            parmsa[0] = source.getCharacterStream();
            this.fConfigSetCharStream.invoke(xmlsource, parmsa);
            parmsa[0] = source.getEncoding();
            this.fConfigSetEncoding.invoke(xmlsource, parmsa);
            final Object[] noparms = new Object[0];
            this.fReset.invoke(this.fIncrementalParser, noparms);
            parmsa[0] = xmlsource;
            this.fConfigSetInput.invoke(this.fPullParserConfig, parmsa);
            return this.parseSome();
        }
        final Object[] parm = { source };
        final Object ret = this.fParseSomeSetup.invoke(this.fIncrementalParser, parm);
        return (boolean)ret;
    }
    
    private boolean parseSome() throws SAXException, IOException, IllegalAccessException, InvocationTargetException {
        if (this.fConfigSetInput != null) {
            final Object ret = this.fConfigParse.invoke(this.fPullParserConfig, IncrementalSAXSource_Xerces.parmsfalse);
            return (boolean)ret;
        }
        final Object ret = this.fParseSome.invoke(this.fIncrementalParser, IncrementalSAXSource_Xerces.noparms);
        return (boolean)ret;
    }
    
    public static void main(final String[] args) {
        System.out.println("Starting...");
        final CoroutineManager co = new CoroutineManager();
        final int appCoroutineID = co.co_joinCoroutineSet(-1);
        if (appCoroutineID == -1) {
            System.out.println("ERROR: Couldn't allocate coroutine number.\n");
            return;
        }
        final IncrementalSAXSource parser = createIncrementalSAXSource();
        final XMLSerializer trace = new XMLSerializer(System.out, null);
        parser.setContentHandler(trace);
        parser.setLexicalHandler(trace);
        for (int arg = 0; arg < args.length; ++arg) {
            try {
                final InputSource source = new InputSource(args[arg]);
                Object result = null;
                boolean more = true;
                parser.startParse(source);
                for (result = parser.deliverMoreNodes(more); result == Boolean.TRUE; result = parser.deliverMoreNodes(more)) {
                    System.out.println("\nSome parsing successful, trying more.\n");
                    if (arg + 1 < args.length && "!".equals(args[arg + 1])) {
                        ++arg;
                        more = false;
                    }
                }
                if (result instanceof Boolean && result == Boolean.FALSE) {
                    System.out.println("\nParser ended (EOF or on request).\n");
                }
                else if (result == null) {
                    System.out.println("\nUNEXPECTED: Parser says shut down prematurely.\n");
                }
                else if (result instanceof Exception) {
                    throw new WrappedRuntimeException((Exception)result);
                }
            }
            catch (SAXException e) {
                e.printStackTrace();
            }
        }
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        noparms = new Object[0];
        parmsfalse = new Object[] { Boolean.FALSE };
    }
}
