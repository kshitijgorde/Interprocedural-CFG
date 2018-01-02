// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xslt;

import java.util.Hashtable;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.parsers.SAXParser;
import org.xml.sax.XMLReader;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Document;
import org.apache.xalan.trace.TraceManager;
import javax.xml.transform.Transformer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Templates;
import java.util.Properties;
import org.apache.xml.utils.WrappedRuntimeException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import javax.xml.transform.sax.SAXSource;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import org.xml.sax.SAXException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.Result;
import org.w3c.dom.Node;
import javax.xml.transform.dom.DOMResult;
import org.apache.xalan.trace.TraceListener;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.TransformerException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import org.xml.sax.InputSource;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.Writer;
import java.io.FileWriter;
import org.xml.sax.ContentHandler;
import org.xml.sax.EntityResolver;
import javax.xml.transform.URIResolver;
import org.apache.xalan.Version;
import org.apache.xalan.trace.PrintTraceListener;
import java.util.Vector;
import javax.xml.transform.TransformerFactoryConfigurationError;
import org.apache.xalan.res.XSLMessages;
import javax.xml.transform.ErrorListener;
import org.apache.xml.utils.DefaultErrorHandler;
import javax.xml.transform.TransformerFactory;
import org.apache.xml.res.XMLMessages;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ResourceBundle;

public class Process
{
    protected static void printArgOptions(final ResourceBundle resbundle) {
        System.out.println(resbundle.getString("xslProc_option"));
        System.out.println("\n\t\t\t" + resbundle.getString("xslProc_common_options") + "\n");
        System.out.println(resbundle.getString("optionXSLTC"));
        System.out.println(resbundle.getString("optionIN"));
        System.out.println(resbundle.getString("optionXSL"));
        System.out.println(resbundle.getString("optionOUT"));
        System.out.println(resbundle.getString("optionV"));
        System.out.println(resbundle.getString("optionEDUMP"));
        System.out.println(resbundle.getString("optionXML"));
        System.out.println(resbundle.getString("optionTEXT"));
        System.out.println(resbundle.getString("optionHTML"));
        System.out.println(resbundle.getString("optionPARAM"));
        System.out.println(resbundle.getString("optionMEDIA"));
        System.out.println(resbundle.getString("optionFLAVOR"));
        System.out.println(resbundle.getString("optionDIAG"));
        System.out.println(resbundle.getString("optionURIRESOLVER"));
        System.out.println(resbundle.getString("optionENTITYRESOLVER"));
        waitForReturnKey(resbundle);
        System.out.println(resbundle.getString("optionCONTENTHANDLER"));
        System.out.println(resbundle.getString("optionSECUREPROCESSING"));
        System.out.println("\n\t\t\t" + resbundle.getString("xslProc_xalan_options") + "\n");
        System.out.println(resbundle.getString("optionQC"));
        System.out.println(resbundle.getString("optionTT"));
        System.out.println(resbundle.getString("optionTG"));
        System.out.println(resbundle.getString("optionTS"));
        System.out.println(resbundle.getString("optionTTC"));
        System.out.println(resbundle.getString("optionTCLASS"));
        System.out.println(resbundle.getString("optionLINENUMBERS"));
        System.out.println(resbundle.getString("optionINCREMENTAL"));
        System.out.println(resbundle.getString("optionNOOPTIMIMIZE"));
        System.out.println(resbundle.getString("optionRL"));
        System.out.println("\n\t\t\t" + resbundle.getString("xslProc_xsltc_options") + "\n");
        System.out.println(resbundle.getString("optionXO"));
        waitForReturnKey(resbundle);
        System.out.println(resbundle.getString("optionXD"));
        System.out.println(resbundle.getString("optionXJ"));
        System.out.println(resbundle.getString("optionXP"));
        System.out.println(resbundle.getString("optionXN"));
        System.out.println(resbundle.getString("optionXX"));
        System.out.println(resbundle.getString("optionXT"));
    }
    
    public static void main(final String[] argv) {
        boolean doStackDumpOnError = false;
        boolean setQuietMode = false;
        boolean doDiag = false;
        String msg = null;
        boolean isSecureProcessing = false;
        PrintWriter dumpWriter;
        final PrintWriter diagnosticsWriter = dumpWriter = new PrintWriter(System.err, true);
        final ResourceBundle resbundle = XMLMessages.loadResourceBundle("org.apache.xalan.res.XSLTErrorResources");
        String flavor = "s2s";
        if (argv.length < 1) {
            printArgOptions(resbundle);
        }
        else {
            boolean useXSLTC = false;
            for (int i = 0; i < argv.length; ++i) {
                if ("-XSLTC".equalsIgnoreCase(argv[i])) {
                    useXSLTC = true;
                }
            }
            if (useXSLTC) {
                final String key = "javax.xml.transform.TransformerFactory";
                final String value = "org.apache.xalan.xsltc.trax.TransformerFactoryImpl";
                final Properties props = System.getProperties();
                ((Hashtable<String, String>)props).put(key, value);
                System.setProperties(props);
            }
            TransformerFactory tfactory;
            try {
                tfactory = TransformerFactory.newInstance();
                tfactory.setErrorListener(new DefaultErrorHandler());
            }
            catch (TransformerFactoryConfigurationError pfe) {
                pfe.printStackTrace(dumpWriter);
                msg = XSLMessages.createMessage("ER_NOT_SUCCESSFUL", null);
                diagnosticsWriter.println(msg);
                tfactory = null;
                doExit(msg);
            }
            final boolean formatOutput = false;
            final boolean useSourceLocation = false;
            String inFileName = null;
            String outFileName = null;
            String dumpFileName = null;
            String xslFileName = null;
            final String treedumpFileName = null;
            PrintTraceListener tracer = null;
            String outputType = null;
            String media = null;
            final Vector params = new Vector();
            boolean quietConflictWarnings = false;
            URIResolver uriResolver = null;
            EntityResolver entityResolver = null;
            ContentHandler contentHandler = null;
            int recursionLimit = -1;
            for (int j = 0; j < argv.length; ++j) {
                if (!"-XSLTC".equalsIgnoreCase(argv[j])) {
                    if ("-TT".equalsIgnoreCase(argv[j])) {
                        if (!useXSLTC) {
                            if (null == tracer) {
                                tracer = new PrintTraceListener(diagnosticsWriter);
                            }
                            tracer.m_traceTemplates = true;
                        }
                        else {
                            printInvalidXSLTCOption("-TT");
                        }
                    }
                    else if ("-TG".equalsIgnoreCase(argv[j])) {
                        if (!useXSLTC) {
                            if (null == tracer) {
                                tracer = new PrintTraceListener(diagnosticsWriter);
                            }
                            tracer.m_traceGeneration = true;
                        }
                        else {
                            printInvalidXSLTCOption("-TG");
                        }
                    }
                    else if ("-TS".equalsIgnoreCase(argv[j])) {
                        if (!useXSLTC) {
                            if (null == tracer) {
                                tracer = new PrintTraceListener(diagnosticsWriter);
                            }
                            tracer.m_traceSelection = true;
                        }
                        else {
                            printInvalidXSLTCOption("-TS");
                        }
                    }
                    else if ("-TTC".equalsIgnoreCase(argv[j])) {
                        if (!useXSLTC) {
                            if (null == tracer) {
                                tracer = new PrintTraceListener(diagnosticsWriter);
                            }
                            tracer.m_traceElements = true;
                        }
                        else {
                            printInvalidXSLTCOption("-TTC");
                        }
                    }
                    else if ("-INDENT".equalsIgnoreCase(argv[j])) {
                        if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
                            final int indentAmount = Integer.parseInt(argv[++j]);
                        }
                        else {
                            final int indentAmount = 0;
                        }
                    }
                    else if ("-IN".equalsIgnoreCase(argv[j])) {
                        if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
                            inFileName = argv[++j];
                        }
                        else {
                            System.err.println(XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-IN" }));
                        }
                    }
                    else if ("-MEDIA".equalsIgnoreCase(argv[j])) {
                        if (j + 1 < argv.length) {
                            media = argv[++j];
                        }
                        else {
                            System.err.println(XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-MEDIA" }));
                        }
                    }
                    else if ("-OUT".equalsIgnoreCase(argv[j])) {
                        if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
                            outFileName = argv[++j];
                        }
                        else {
                            System.err.println(XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-OUT" }));
                        }
                    }
                    else if ("-XSL".equalsIgnoreCase(argv[j])) {
                        if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
                            xslFileName = argv[++j];
                        }
                        else {
                            System.err.println(XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-XSL" }));
                        }
                    }
                    else if ("-FLAVOR".equalsIgnoreCase(argv[j])) {
                        if (j + 1 < argv.length) {
                            flavor = argv[++j];
                        }
                        else {
                            System.err.println(XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-FLAVOR" }));
                        }
                    }
                    else if ("-PARAM".equalsIgnoreCase(argv[j])) {
                        if (j + 2 < argv.length) {
                            final String name = argv[++j];
                            params.addElement(name);
                            final String expression = argv[++j];
                            params.addElement(expression);
                        }
                        else {
                            System.err.println(XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-PARAM" }));
                        }
                    }
                    else if (!"-E".equalsIgnoreCase(argv[j])) {
                        if ("-V".equalsIgnoreCase(argv[j])) {
                            diagnosticsWriter.println(resbundle.getString("version") + Version.getVersion() + ", " + resbundle.getString("version2"));
                        }
                        else if ("-QC".equalsIgnoreCase(argv[j])) {
                            if (!useXSLTC) {
                                quietConflictWarnings = true;
                            }
                            else {
                                printInvalidXSLTCOption("-QC");
                            }
                        }
                        else if ("-Q".equalsIgnoreCase(argv[j])) {
                            setQuietMode = true;
                        }
                        else if ("-DIAG".equalsIgnoreCase(argv[j])) {
                            doDiag = true;
                        }
                        else if ("-XML".equalsIgnoreCase(argv[j])) {
                            outputType = "xml";
                        }
                        else if ("-TEXT".equalsIgnoreCase(argv[j])) {
                            outputType = "text";
                        }
                        else if ("-HTML".equalsIgnoreCase(argv[j])) {
                            outputType = "html";
                        }
                        else if ("-EDUMP".equalsIgnoreCase(argv[j])) {
                            doStackDumpOnError = true;
                            if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
                                dumpFileName = argv[++j];
                            }
                        }
                        else if ("-URIRESOLVER".equalsIgnoreCase(argv[j])) {
                            if (j + 1 < argv.length) {
                                try {
                                    uriResolver = (URIResolver)ObjectFactory.newInstance(argv[++j], ObjectFactory.findClassLoader(), true);
                                    tfactory.setURIResolver(uriResolver);
                                }
                                catch (ObjectFactory.ConfigurationError cnfe) {
                                    msg = XSLMessages.createMessage("ER_CLASS_NOT_FOUND_FOR_OPTION", new Object[] { "-URIResolver" });
                                    System.err.println(msg);
                                    doExit(msg);
                                }
                            }
                            else {
                                msg = XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-URIResolver" });
                                System.err.println(msg);
                                doExit(msg);
                            }
                        }
                        else if ("-ENTITYRESOLVER".equalsIgnoreCase(argv[j])) {
                            if (j + 1 < argv.length) {
                                try {
                                    entityResolver = (EntityResolver)ObjectFactory.newInstance(argv[++j], ObjectFactory.findClassLoader(), true);
                                }
                                catch (ObjectFactory.ConfigurationError cnfe) {
                                    msg = XSLMessages.createMessage("ER_CLASS_NOT_FOUND_FOR_OPTION", new Object[] { "-EntityResolver" });
                                    System.err.println(msg);
                                    doExit(msg);
                                }
                            }
                            else {
                                msg = XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-EntityResolver" });
                                System.err.println(msg);
                                doExit(msg);
                            }
                        }
                        else if ("-CONTENTHANDLER".equalsIgnoreCase(argv[j])) {
                            if (j + 1 < argv.length) {
                                try {
                                    contentHandler = (ContentHandler)ObjectFactory.newInstance(argv[++j], ObjectFactory.findClassLoader(), true);
                                }
                                catch (ObjectFactory.ConfigurationError cnfe) {
                                    msg = XSLMessages.createMessage("ER_CLASS_NOT_FOUND_FOR_OPTION", new Object[] { "-ContentHandler" });
                                    System.err.println(msg);
                                    doExit(msg);
                                }
                            }
                            else {
                                msg = XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-ContentHandler" });
                                System.err.println(msg);
                                doExit(msg);
                            }
                        }
                        else if ("-L".equalsIgnoreCase(argv[j])) {
                            if (!useXSLTC) {
                                tfactory.setAttribute("http://xml.apache.org/xalan/properties/source-location", Boolean.TRUE);
                            }
                            else {
                                printInvalidXSLTCOption("-L");
                            }
                        }
                        else if ("-INCREMENTAL".equalsIgnoreCase(argv[j])) {
                            if (!useXSLTC) {
                                tfactory.setAttribute("http://xml.apache.org/xalan/features/incremental", Boolean.TRUE);
                            }
                            else {
                                printInvalidXSLTCOption("-INCREMENTAL");
                            }
                        }
                        else if ("-NOOPTIMIZE".equalsIgnoreCase(argv[j])) {
                            if (!useXSLTC) {
                                tfactory.setAttribute("http://xml.apache.org/xalan/features/optimize", Boolean.FALSE);
                            }
                            else {
                                printInvalidXSLTCOption("-NOOPTIMIZE");
                            }
                        }
                        else if ("-RL".equalsIgnoreCase(argv[j])) {
                            if (!useXSLTC) {
                                if (j + 1 < argv.length) {
                                    recursionLimit = Integer.parseInt(argv[++j]);
                                }
                                else {
                                    System.err.println(XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-rl" }));
                                }
                            }
                            else {
                                if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
                                    ++j;
                                }
                                printInvalidXSLTCOption("-RL");
                            }
                        }
                        else if ("-XO".equalsIgnoreCase(argv[j])) {
                            if (useXSLTC) {
                                if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
                                    tfactory.setAttribute("generate-translet", "true");
                                    tfactory.setAttribute("translet-name", argv[++j]);
                                }
                                else {
                                    tfactory.setAttribute("generate-translet", "true");
                                }
                            }
                            else {
                                if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
                                    ++j;
                                }
                                printInvalidXalanOption("-XO");
                            }
                        }
                        else if ("-XD".equalsIgnoreCase(argv[j])) {
                            if (useXSLTC) {
                                if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
                                    tfactory.setAttribute("destination-directory", argv[++j]);
                                }
                                else {
                                    System.err.println(XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-XD" }));
                                }
                            }
                            else {
                                if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
                                    ++j;
                                }
                                printInvalidXalanOption("-XD");
                            }
                        }
                        else if ("-XJ".equalsIgnoreCase(argv[j])) {
                            if (useXSLTC) {
                                if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
                                    tfactory.setAttribute("generate-translet", "true");
                                    tfactory.setAttribute("jar-name", argv[++j]);
                                }
                                else {
                                    System.err.println(XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-XJ" }));
                                }
                            }
                            else {
                                if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
                                    ++j;
                                }
                                printInvalidXalanOption("-XJ");
                            }
                        }
                        else if ("-XP".equalsIgnoreCase(argv[j])) {
                            if (useXSLTC) {
                                if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
                                    tfactory.setAttribute("package-name", argv[++j]);
                                }
                                else {
                                    System.err.println(XSLMessages.createMessage("ER_MISSING_ARG_FOR_OPTION", new Object[] { "-XP" }));
                                }
                            }
                            else {
                                if (j + 1 < argv.length && argv[j + 1].charAt(0) != '-') {
                                    ++j;
                                }
                                printInvalidXalanOption("-XP");
                            }
                        }
                        else if ("-XN".equalsIgnoreCase(argv[j])) {
                            if (useXSLTC) {
                                tfactory.setAttribute("enable-inlining", "true");
                            }
                            else {
                                printInvalidXalanOption("-XN");
                            }
                        }
                        else if ("-XX".equalsIgnoreCase(argv[j])) {
                            if (useXSLTC) {
                                tfactory.setAttribute("debug", "true");
                            }
                            else {
                                printInvalidXalanOption("-XX");
                            }
                        }
                        else if ("-XT".equalsIgnoreCase(argv[j])) {
                            if (useXSLTC) {
                                tfactory.setAttribute("auto-translet", "true");
                            }
                            else {
                                printInvalidXalanOption("-XT");
                            }
                        }
                        else if ("-SECURE".equalsIgnoreCase(argv[j])) {
                            try {
                                tfactory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
                                isSecureProcessing = true;
                            }
                            catch (Throwable t) {}
                        }
                        else {
                            System.err.println(XSLMessages.createMessage("ER_INVALID_OPTION", new Object[] { argv[j] }));
                        }
                    }
                }
            }
            if (inFileName == null && xslFileName == null) {
                msg = resbundle.getString("xslProc_no_input");
                System.err.println(msg);
                doExit(msg);
            }
            try {
                final long start = System.currentTimeMillis();
                if (null != dumpFileName) {
                    dumpWriter = new PrintWriter(new FileWriter(dumpFileName));
                }
                Templates stylesheet = null;
                if (null != xslFileName) {
                    if (flavor.equals("d2d")) {
                        final DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
                        dfactory.setNamespaceAware(true);
                        if (isSecureProcessing) {
                            try {
                                dfactory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
                            }
                            catch (ParserConfigurationException ex7) {}
                        }
                        final DocumentBuilder docBuilder = dfactory.newDocumentBuilder();
                        final Node xslDOM = docBuilder.parse(new InputSource(xslFileName));
                        stylesheet = tfactory.newTemplates(new DOMSource(xslDOM, xslFileName));
                    }
                    else {
                        stylesheet = tfactory.newTemplates(new StreamSource(xslFileName));
                    }
                }
                StreamResult strResult;
                if (null != outFileName) {
                    strResult = new StreamResult(new FileOutputStream(outFileName));
                    strResult.setSystemId(outFileName);
                }
                else {
                    strResult = new StreamResult(System.out);
                }
                final SAXTransformerFactory stf = (SAXTransformerFactory)tfactory;
                if (!useXSLTC && useSourceLocation) {
                    stf.setAttribute("http://xml.apache.org/xalan/properties/source-location", Boolean.TRUE);
                }
                if (null == stylesheet) {
                    final Source source = stf.getAssociatedStylesheet(new StreamSource(inFileName), media, null, null);
                    if (null != source) {
                        stylesheet = tfactory.newTemplates(source);
                    }
                    else {
                        if (null != media) {
                            throw new TransformerException(XSLMessages.createMessage("ER_NO_STYLESHEET_IN_MEDIA", new Object[] { inFileName, media }));
                        }
                        throw new TransformerException(XSLMessages.createMessage("ER_NO_STYLESHEET_PI", new Object[] { inFileName }));
                    }
                }
                if (null != stylesheet) {
                    final Transformer transformer = flavor.equals("th") ? null : stylesheet.newTransformer();
                    transformer.setErrorListener(new DefaultErrorHandler());
                    if (null != outputType) {
                        transformer.setOutputProperty("method", outputType);
                    }
                    if (transformer instanceof TransformerImpl) {
                        final TransformerImpl impl = (TransformerImpl)transformer;
                        final TraceManager tm = impl.getTraceManager();
                        if (null != tracer) {
                            tm.addTraceListener(tracer);
                        }
                        impl.setQuietConflictWarnings(quietConflictWarnings);
                        if (useSourceLocation) {
                            impl.setProperty("http://xml.apache.org/xalan/properties/source-location", Boolean.TRUE);
                        }
                        if (recursionLimit > 0) {
                            impl.setRecursionLimit(recursionLimit);
                        }
                    }
                    for (int nParams = params.size(), k = 0; k < nParams; k += 2) {
                        transformer.setParameter(params.elementAt(k), params.elementAt(k + 1));
                    }
                    if (uriResolver != null) {
                        transformer.setURIResolver(uriResolver);
                    }
                    if (null != inFileName) {
                        if (flavor.equals("d2d")) {
                            final DocumentBuilderFactory dfactory2 = DocumentBuilderFactory.newInstance();
                            dfactory2.setCoalescing(true);
                            dfactory2.setNamespaceAware(true);
                            if (isSecureProcessing) {
                                try {
                                    dfactory2.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
                                }
                                catch (ParserConfigurationException ex8) {}
                            }
                            final DocumentBuilder docBuilder2 = dfactory2.newDocumentBuilder();
                            if (entityResolver != null) {
                                docBuilder2.setEntityResolver(entityResolver);
                            }
                            final Node xmlDoc = docBuilder2.parse(new InputSource(inFileName));
                            final Document doc = docBuilder2.newDocument();
                            final DocumentFragment outNode = doc.createDocumentFragment();
                            transformer.transform(new DOMSource(xmlDoc, inFileName), new DOMResult(outNode));
                            final Transformer serializer = stf.newTransformer();
                            serializer.setErrorListener(new DefaultErrorHandler());
                            final Properties serializationProps = stylesheet.getOutputProperties();
                            serializer.setOutputProperties(serializationProps);
                            if (contentHandler != null) {
                                final SAXResult result = new SAXResult(contentHandler);
                                serializer.transform(new DOMSource(outNode), result);
                            }
                            else {
                                serializer.transform(new DOMSource(outNode), strResult);
                            }
                        }
                        else if (flavor.equals("th")) {
                            for (int l = 0; l < 1; ++l) {
                                XMLReader reader = null;
                                try {
                                    final SAXParserFactory factory = SAXParserFactory.newInstance();
                                    factory.setNamespaceAware(true);
                                    if (isSecureProcessing) {
                                        try {
                                            factory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
                                        }
                                        catch (SAXException ex9) {}
                                    }
                                    final SAXParser jaxpParser = factory.newSAXParser();
                                    reader = jaxpParser.getXMLReader();
                                }
                                catch (ParserConfigurationException ex) {
                                    throw new SAXException(ex);
                                }
                                catch (FactoryConfigurationError ex2) {
                                    throw new SAXException(ex2.toString());
                                }
                                catch (NoSuchMethodError ex5) {}
                                catch (AbstractMethodError abstractMethodError) {}
                                if (null == reader) {
                                    reader = XMLReaderFactory.createXMLReader();
                                }
                                if (!useXSLTC) {
                                    stf.setAttribute("http://xml.apache.org/xalan/features/incremental", Boolean.TRUE);
                                }
                                final TransformerHandler th = stf.newTransformerHandler(stylesheet);
                                reader.setContentHandler(th);
                                reader.setDTDHandler(th);
                                if (th instanceof ErrorHandler) {
                                    reader.setErrorHandler((ErrorHandler)th);
                                }
                                try {
                                    reader.setProperty("http://xml.org/sax/properties/lexical-handler", th);
                                }
                                catch (SAXNotRecognizedException e) {}
                                catch (SAXNotSupportedException ex10) {}
                                try {
                                    reader.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
                                }
                                catch (SAXException ex11) {}
                                th.setResult(strResult);
                                reader.parse(new InputSource(inFileName));
                            }
                        }
                        else if (entityResolver != null) {
                            XMLReader reader2 = null;
                            try {
                                final SAXParserFactory factory2 = SAXParserFactory.newInstance();
                                factory2.setNamespaceAware(true);
                                if (isSecureProcessing) {
                                    try {
                                        factory2.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
                                    }
                                    catch (SAXException ex12) {}
                                }
                                final SAXParser jaxpParser2 = factory2.newSAXParser();
                                reader2 = jaxpParser2.getXMLReader();
                            }
                            catch (ParserConfigurationException ex3) {
                                throw new SAXException(ex3);
                            }
                            catch (FactoryConfigurationError ex4) {
                                throw new SAXException(ex4.toString());
                            }
                            catch (NoSuchMethodError ex6) {}
                            catch (AbstractMethodError abstractMethodError2) {}
                            if (null == reader2) {
                                reader2 = XMLReaderFactory.createXMLReader();
                            }
                            reader2.setEntityResolver(entityResolver);
                            if (contentHandler != null) {
                                final SAXResult result2 = new SAXResult(contentHandler);
                                transformer.transform(new SAXSource(reader2, new InputSource(inFileName)), result2);
                            }
                            else {
                                transformer.transform(new SAXSource(reader2, new InputSource(inFileName)), strResult);
                            }
                        }
                        else if (contentHandler != null) {
                            final SAXResult result3 = new SAXResult(contentHandler);
                            transformer.transform(new StreamSource(inFileName), result3);
                        }
                        else {
                            transformer.transform(new StreamSource(inFileName), strResult);
                        }
                    }
                    else {
                        final StringReader reader3 = new StringReader("<?xml version=\"1.0\"?> <doc/>");
                        transformer.transform(new StreamSource(reader3), strResult);
                    }
                }
                else {
                    msg = XSLMessages.createMessage("ER_NOT_SUCCESSFUL", null);
                    diagnosticsWriter.println(msg);
                    doExit(msg);
                }
                if (null != outFileName && strResult != null) {
                    final OutputStream out = strResult.getOutputStream();
                    final Writer writer = strResult.getWriter();
                    try {
                        if (out != null) {
                            out.close();
                        }
                        if (writer != null) {
                            writer.close();
                        }
                    }
                    catch (IOException ex13) {}
                }
                final long stop = System.currentTimeMillis();
                final long millisecondsDuration = stop - start;
                if (doDiag) {
                    final Object[] msgArgs = { inFileName, xslFileName, new Long(millisecondsDuration) };
                    msg = XSLMessages.createMessage("diagTiming", msgArgs);
                    diagnosticsWriter.println('\n');
                    diagnosticsWriter.println(msg);
                }
            }
            catch (Throwable throwable) {
                while (throwable instanceof WrappedRuntimeException) {
                    throwable = ((WrappedRuntimeException)throwable).getException();
                }
                if (throwable instanceof NullPointerException || throwable instanceof ClassCastException) {
                    doStackDumpOnError = true;
                }
                diagnosticsWriter.println();
                if (doStackDumpOnError) {
                    throwable.printStackTrace(dumpWriter);
                }
                else {
                    DefaultErrorHandler.printLocation(diagnosticsWriter, throwable);
                    diagnosticsWriter.println(XSLMessages.createMessage("ER_XSLT_ERROR", null) + " (" + throwable.getClass().getName() + "): " + throwable.getMessage());
                }
                if (null != dumpFileName) {
                    dumpWriter.close();
                }
                doExit(throwable.getMessage());
            }
            if (null != dumpFileName) {
                dumpWriter.close();
            }
            if (null != diagnosticsWriter) {}
        }
    }
    
    static void doExit(final String msg) {
        throw new RuntimeException(msg);
    }
    
    private static void waitForReturnKey(final ResourceBundle resbundle) {
        System.out.println(resbundle.getString("xslProc_return_to_continue"));
        try {
            while (System.in.read() != 10) {}
        }
        catch (IOException ex) {}
    }
    
    private static void printInvalidXSLTCOption(final String option) {
        System.err.println(XSLMessages.createMessage("xslProc_invalid_xsltc_option", new Object[] { option }));
    }
    
    private static void printInvalidXalanOption(final String option) {
        System.err.println(XSLMessages.createMessage("xslProc_invalid_xalan_option", new Object[] { option }));
    }
}
