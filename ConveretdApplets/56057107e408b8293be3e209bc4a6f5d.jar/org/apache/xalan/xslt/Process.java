// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xslt;

import java.util.Properties;
import org.w3c.dom.Document;
import org.apache.xalan.trace.TraceManager;
import javax.xml.transform.Transformer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Templates;
import org.apache.xml.utils.WrappedRuntimeException;
import java.io.Reader;
import java.io.StringReader;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.Result;
import org.w3c.dom.Node;
import javax.xml.transform.dom.DOMResult;
import org.apache.xalan.trace.TraceListener;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.TransformerException;
import javax.xml.transform.sax.SAXTransformerFactory;
import java.io.FileOutputStream;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.Writer;
import java.io.FileWriter;
import org.apache.xalan.processor.XSLProcessorVersion;
import org.apache.xalan.trace.PrintTraceListener;
import java.util.Vector;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.TransformerFactory;
import org.apache.xalan.res.XSLMessages;
import org.apache.xalan.res.XSLTErrorResources;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Process
{
    static String XSLT_PROPERTIES;
    
    static {
        Process.XSLT_PROPERTIES = "/org/apache/xalan/res/XSLTInfo.properties";
    }
    
    public static void main(final String[] argv) {
        boolean doStackDumpOnError = false;
        boolean setQuietMode = false;
        boolean doDiag = false;
        PrintWriter dumpWriter;
        final PrintWriter diagnosticsWriter = dumpWriter = new PrintWriter(System.err, true);
        final XSLTErrorResources resbundle = (XSLTErrorResources)XSLMessages.loadResourceBundle("org.apache.xalan.res.XSLTErrorResources");
        String flavor = "s2s";
        if (argv.length < 1) {
            printArgOptions(resbundle);
        }
        else {
            TransformerFactory tfactory;
            try {
                tfactory = TransformerFactory.newInstance();
            }
            catch (TransformerFactoryConfigurationError pfe) {
                pfe.printStackTrace(dumpWriter);
                diagnosticsWriter.println(XSLMessages.createMessage(27, null));
                tfactory = null;
                System.exit(-1);
            }
            boolean formatOutput = false;
            String inFileName = null;
            String outFileName = null;
            String dumpFileName = null;
            String xslFileName = null;
            String treedumpFileName = null;
            PrintTraceListener tracer = null;
            String outputType = null;
            String media = null;
            final Vector params = new Vector();
            boolean quietConflictWarnings = false;
            for (int i = 0; i < argv.length; ++i) {
                if ("-TT".equalsIgnoreCase(argv[i])) {
                    if (tracer == null) {
                        tracer = new PrintTraceListener(diagnosticsWriter);
                    }
                    tracer.m_traceTemplates = true;
                }
                else if ("-TG".equalsIgnoreCase(argv[i])) {
                    if (tracer == null) {
                        tracer = new PrintTraceListener(diagnosticsWriter);
                    }
                    tracer.m_traceGeneration = true;
                }
                else if ("-TS".equalsIgnoreCase(argv[i])) {
                    if (tracer == null) {
                        tracer = new PrintTraceListener(diagnosticsWriter);
                    }
                    tracer.m_traceSelection = true;
                }
                else if ("-TTC".equalsIgnoreCase(argv[i])) {
                    if (tracer == null) {
                        tracer = new PrintTraceListener(diagnosticsWriter);
                    }
                    tracer.m_traceElements = true;
                }
                else if ("-INDENT".equalsIgnoreCase(argv[i])) {
                    if (i + 1 < argv.length && argv[i + 1].charAt(0) != '-') {
                        final int indentAmount = Integer.parseInt(argv[++i]);
                    }
                    else {
                        final int indentAmount = 0;
                    }
                }
                else if ("-IN".equalsIgnoreCase(argv[i])) {
                    if (i + 1 < argv.length) {
                        inFileName = argv[++i];
                    }
                    else {
                        System.err.println(XSLMessages.createMessage(89, new Object[] { "-IN" }));
                    }
                }
                else if ("-MEDIA".equalsIgnoreCase(argv[i])) {
                    if (i + 1 < argv.length) {
                        media = argv[++i];
                    }
                    else {
                        System.err.println(XSLMessages.createMessage(89, new Object[] { "-MEDIA" }));
                    }
                }
                else if ("-OUT".equalsIgnoreCase(argv[i])) {
                    if (i + 1 < argv.length) {
                        outFileName = argv[++i];
                    }
                    else {
                        System.err.println(XSLMessages.createMessage(89, new Object[] { "-OUT" }));
                    }
                }
                else if ("-XSL".equalsIgnoreCase(argv[i])) {
                    if (i + 1 < argv.length) {
                        xslFileName = argv[++i];
                    }
                    else {
                        System.err.println(XSLMessages.createMessage(89, new Object[] { "-XSL" }));
                    }
                }
                else if ("-FLAVOR".equalsIgnoreCase(argv[i])) {
                    if (i + 1 < argv.length) {
                        flavor = argv[++i];
                    }
                    else {
                        System.err.println(XSLMessages.createMessage(89, new Object[] { "-FLAVOR" }));
                    }
                }
                else if ("-PARAM".equalsIgnoreCase(argv[i])) {
                    if (i + 2 < argv.length) {
                        final String name = argv[++i];
                        params.addElement(name);
                        final String expression = argv[++i];
                        params.addElement(expression);
                    }
                    else {
                        System.err.println(XSLMessages.createMessage(89, new Object[] { "-PARAM" }));
                    }
                }
                else if ("-treedump".equalsIgnoreCase(argv[i])) {
                    if (i + 1 < argv.length) {
                        treedumpFileName = argv[++i];
                    }
                    else {
                        System.err.println(XSLMessages.createMessage(89, new Object[] { "-treedump" }));
                    }
                }
                else if ("-F".equalsIgnoreCase(argv[i])) {
                    formatOutput = true;
                }
                else if (!"-E".equalsIgnoreCase(argv[i])) {
                    if ("-V".equalsIgnoreCase(argv[i])) {
                        diagnosticsWriter.println(String.valueOf(resbundle.getString("version")) + XSLProcessorVersion.S_VERSION + ", " + resbundle.getString("version2"));
                    }
                    else if ("-QC".equalsIgnoreCase(argv[i])) {
                        quietConflictWarnings = true;
                    }
                    else if ("-Q".equalsIgnoreCase(argv[i])) {
                        setQuietMode = true;
                    }
                    else if ("-DIAG".equalsIgnoreCase(argv[i])) {
                        doDiag = true;
                    }
                    else if ("-XML".equalsIgnoreCase(argv[i])) {
                        outputType = "xml";
                    }
                    else if ("-TEXT".equalsIgnoreCase(argv[i])) {
                        outputType = "text";
                    }
                    else if ("-HTML".equalsIgnoreCase(argv[i])) {
                        outputType = "html";
                    }
                    else if ("-EDUMP".equalsIgnoreCase(argv[i])) {
                        doStackDumpOnError = true;
                        if (i + 1 < argv.length && argv[i + 1].charAt(0) != '-') {
                            dumpFileName = argv[++i];
                        }
                    }
                    else {
                        System.err.println(XSLMessages.createMessage(90, new Object[] { argv[i] }));
                    }
                }
            }
            try {
                final long start = System.currentTimeMillis();
                if (dumpFileName != null) {
                    dumpWriter = new PrintWriter(new FileWriter(dumpFileName));
                }
                Templates stylesheet = null;
                if (xslFileName != null) {
                    if (flavor.equals("d2d")) {
                        final DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
                        dfactory.setNamespaceAware(true);
                        final DocumentBuilder docBuilder = dfactory.newDocumentBuilder();
                        final Node xslDOM = docBuilder.parse(new InputSource(xslFileName));
                        stylesheet = tfactory.newTemplates(new DOMSource(xslDOM, xslFileName));
                    }
                    else {
                        stylesheet = tfactory.newTemplates(new StreamSource(xslFileName));
                    }
                }
                final OutputStream outputStream = (outFileName != null) ? new FileOutputStream(outFileName) : System.out;
                final SAXTransformerFactory stf = (SAXTransformerFactory)tfactory;
                if (stylesheet == null) {
                    final Source source = stf.getAssociatedStylesheet(new StreamSource(inFileName), media, null, null);
                    if (source != null) {
                        stylesheet = tfactory.newTemplates(source);
                    }
                    else {
                        if (media != null) {
                            throw new TransformerException("No stylesheet found in: " + inFileName + ", media=" + media);
                        }
                        throw new TransformerException("No xml-stylesheet PI found in: " + inFileName);
                    }
                }
                if (stylesheet != null) {
                    final Transformer transformer = stylesheet.newTransformer();
                    if (outputType != null) {
                        transformer.setOutputProperty("method", outputType);
                    }
                    if (transformer instanceof TransformerImpl) {
                        final TransformerImpl impl = (TransformerImpl)transformer;
                        final TraceManager tm = impl.getTraceManager();
                        if (tracer != null) {
                            tm.addTraceListener(tracer);
                        }
                        impl.setQuietConflictWarnings(quietConflictWarnings);
                    }
                    for (int nParams = params.size(), j = 0; j < nParams; j += 2) {
                        transformer.setParameter(params.elementAt(j), params.elementAt(j + 1));
                    }
                    if (inFileName != null) {
                        if (flavor.equals("d2d")) {
                            final DocumentBuilderFactory dfactory2 = DocumentBuilderFactory.newInstance();
                            dfactory2.setCoalescing(true);
                            dfactory2.setNamespaceAware(true);
                            final DocumentBuilder docBuilder2 = dfactory2.newDocumentBuilder();
                            final Node xmlDoc = docBuilder2.parse(new InputSource(inFileName));
                            final Document outNode = docBuilder2.newDocument();
                            transformer.transform(new DOMSource(xmlDoc, inFileName), new DOMResult(outNode));
                            final Transformer serializer = stf.newTransformer();
                            final Properties serializationProps = stylesheet.getOutputProperties();
                            serializer.setOutputProperties(serializationProps);
                            serializer.transform(new DOMSource(outNode), new StreamResult(outputStream));
                        }
                        else {
                            transformer.transform(new StreamSource(inFileName), new StreamResult(outputStream));
                        }
                    }
                    else {
                        final StringReader reader = new StringReader("<?xml version=\"1.0\"?> <doc/>");
                        transformer.transform(new StreamSource(reader), new StreamResult(outputStream));
                    }
                }
                else {
                    diagnosticsWriter.println(XSLMessages.createMessage(27, null));
                    System.exit(-1);
                }
                final long stop = System.currentTimeMillis();
                final long millisecondsDuration = stop - start;
                if (doDiag) {
                    diagnosticsWriter.println("\n\n========\nTransform of " + inFileName + " via " + xslFileName + " took " + millisecondsDuration + " ms");
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
                    diagnosticsWriter.println(String.valueOf(XSLMessages.createMessage(81, null)) + " (" + throwable.getClass().getName() + "): " + throwable.getMessage());
                }
                if (dumpFileName != null) {
                    dumpWriter.close();
                }
                System.exit(-1);
            }
            if (dumpFileName != null) {
                dumpWriter.close();
            }
            diagnosticsWriter.println("");
        }
    }
    
    protected static void printArgOptions(final XSLTErrorResources resbundle) {
        System.out.println(resbundle.getString("xslProc_option"));
        System.out.println(resbundle.getString("optionIN"));
        System.out.println(resbundle.getString("optionXSL"));
        System.out.println(resbundle.getString("optionOUT"));
        System.out.println(resbundle.getString("optionV"));
        System.out.println(resbundle.getString("optionQC"));
        System.out.println(resbundle.getString("optionQ"));
        System.out.println(resbundle.getString("optionTT"));
        System.out.println(resbundle.getString("optionTG"));
        System.out.println(resbundle.getString("optionTS"));
        System.out.println(resbundle.getString("optionTTC"));
        System.out.println(resbundle.getString("optionTCLASS"));
        System.out.println(resbundle.getString("optionEDUMP"));
        System.out.println(resbundle.getString("optionXML"));
        System.out.println(resbundle.getString("optionTEXT"));
        System.out.println(resbundle.getString("optionHTML"));
        System.out.println(resbundle.getString("optionPARAM"));
        System.out.println("[-MEDIA use media attribute to find stylesheet associated with a document.]");
    }
}
