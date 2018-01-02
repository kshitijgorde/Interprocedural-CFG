// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.cmdline;

import org.apache.xml.dtm.DTMWSFilter;
import org.xml.sax.XMLReader;
import javax.xml.parsers.SAXParser;
import org.xml.sax.SAXException;
import java.net.UnknownHostException;
import java.net.MalformedURLException;
import java.io.FileNotFoundException;
import org.apache.xalan.xsltc.TransletException;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import org.apache.xalan.xsltc.runtime.output.TransletOutputHandlerFactory;
import org.apache.xalan.xsltc.runtime.Parameter;
import org.apache.xalan.xsltc.DOM;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import org.xml.sax.InputSource;
import org.apache.xalan.xsltc.DOMEnhancedForDTM;
import org.apache.xalan.xsltc.dom.DOMWSFilter;
import org.apache.xalan.xsltc.StripFilter;
import org.apache.xalan.xsltc.dom.XSLTCDTMManager;
import javax.xml.parsers.SAXParserFactory;
import org.apache.xalan.xsltc.runtime.AbstractTranslet;
import java.util.Vector;
import org.apache.xml.serializer.SerializationHandler;

public final class Transform
{
    private SerializationHandler _handler;
    private String _fileName;
    private String _className;
    private String _jarFileSrc;
    private boolean _isJarFileSpecified;
    private Vector _params;
    private boolean _uri;
    private boolean _debug;
    private int _iterations;
    private static boolean _allowExit;
    
    public Transform(final String className, final String fileName, final boolean uri, final boolean debug, final int iterations) {
        this._isJarFileSpecified = false;
        this._params = null;
        this._fileName = fileName;
        this._className = className;
        this._uri = uri;
        this._debug = debug;
        this._iterations = iterations;
    }
    
    public String getFileName() {
        return this._fileName;
    }
    
    public String getClassName() {
        return this._className;
    }
    
    public void setParameters(final Vector params) {
        this._params = params;
    }
    
    private void setJarFileInputSrc(final boolean flag, final String jarFile) {
        this._isJarFileSpecified = flag;
        this._jarFileSrc = jarFile;
    }
    
    private void doTransform() {
        try {
            final Class clazz = ObjectFactory.findProviderClass(this._className, ObjectFactory.findClassLoader(), true);
            final AbstractTranslet translet = clazz.newInstance();
            translet.postInitialization();
            final SAXParserFactory factory = SAXParserFactory.newInstance();
            try {
                factory.setFeature("http://xml.org/sax/features/namespaces", true);
            }
            catch (Exception e9) {
                factory.setNamespaceAware(true);
            }
            final SAXParser parser = factory.newSAXParser();
            final XMLReader reader = parser.getXMLReader();
            final XSLTCDTMManager dtmManager = XSLTCDTMManager.getDTMManagerClass().newInstance();
            DTMWSFilter wsfilter;
            if (translet != null && translet instanceof StripFilter) {
                wsfilter = new DOMWSFilter(translet);
            }
            else {
                wsfilter = null;
            }
            final DOMEnhancedForDTM dom = (DOMEnhancedForDTM)dtmManager.getDTM(new SAXSource(reader, new InputSource(this._fileName)), false, wsfilter, true, false, translet.hasIdCall());
            dom.setDocumentURI(this._fileName);
            translet.prepassDocument(dom);
            for (int n = this._params.size(), i = 0; i < n; ++i) {
                final Parameter param = this._params.elementAt(i);
                translet.addParameter(param._name, param._value);
            }
            final TransletOutputHandlerFactory tohFactory = TransletOutputHandlerFactory.newInstance();
            tohFactory.setOutputType(0);
            tohFactory.setEncoding(translet._encoding);
            tohFactory.setOutputMethod(translet._method);
            if (this._iterations == -1) {
                translet.transform(dom, tohFactory.getSerializationHandler());
            }
            else if (this._iterations > 0) {
                long mm = System.currentTimeMillis();
                for (int j = 0; j < this._iterations; ++j) {
                    translet.transform(dom, tohFactory.getSerializationHandler());
                }
                mm = System.currentTimeMillis() - mm;
                System.err.println("\n<!--");
                System.err.println("  transform  = " + mm / this._iterations + " ms");
                System.err.println("  throughput = " + 1000.0 / (mm / this._iterations) + " tps");
                System.err.println("-->");
            }
        }
        catch (TransletException e) {
            if (this._debug) {
                e.printStackTrace();
            }
            System.err.println(new ErrorMsg("RUNTIME_ERROR_KEY") + e.getMessage());
            if (Transform._allowExit) {
                System.exit(-1);
            }
        }
        catch (RuntimeException e2) {
            if (this._debug) {
                e2.printStackTrace();
            }
            System.err.println(new ErrorMsg("RUNTIME_ERROR_KEY") + e2.getMessage());
            if (Transform._allowExit) {
                System.exit(-1);
            }
        }
        catch (FileNotFoundException e3) {
            if (this._debug) {
                e3.printStackTrace();
            }
            final ErrorMsg err = new ErrorMsg("FILE_NOT_FOUND_ERR", this._fileName);
            System.err.println(new ErrorMsg("RUNTIME_ERROR_KEY") + err.toString());
            if (Transform._allowExit) {
                System.exit(-1);
            }
        }
        catch (MalformedURLException e4) {
            if (this._debug) {
                e4.printStackTrace();
            }
            final ErrorMsg err2 = new ErrorMsg("INVALID_URI_ERR", this._fileName);
            System.err.println(new ErrorMsg("RUNTIME_ERROR_KEY") + err2.toString());
            if (Transform._allowExit) {
                System.exit(-1);
            }
        }
        catch (ClassNotFoundException e5) {
            if (this._debug) {
                e5.printStackTrace();
            }
            final ErrorMsg err3 = new ErrorMsg("CLASS_NOT_FOUND_ERR", this._className);
            System.err.println(new ErrorMsg("RUNTIME_ERROR_KEY") + err3.toString());
            if (Transform._allowExit) {
                System.exit(-1);
            }
        }
        catch (UnknownHostException e6) {
            if (this._debug) {
                e6.printStackTrace();
            }
            final ErrorMsg err4 = new ErrorMsg("INVALID_URI_ERR", this._fileName);
            System.err.println(new ErrorMsg("RUNTIME_ERROR_KEY") + err4.toString());
            if (Transform._allowExit) {
                System.exit(-1);
            }
        }
        catch (SAXException e7) {
            final Exception ex = e7.getException();
            if (this._debug) {
                if (ex != null) {
                    ex.printStackTrace();
                }
                e7.printStackTrace();
            }
            System.err.print(new ErrorMsg("RUNTIME_ERROR_KEY"));
            if (ex != null) {
                System.err.println(ex.getMessage());
            }
            else {
                System.err.println(e7.getMessage());
            }
            if (Transform._allowExit) {
                System.exit(-1);
            }
        }
        catch (Exception e8) {
            if (this._debug) {
                e8.printStackTrace();
            }
            System.err.println(new ErrorMsg("RUNTIME_ERROR_KEY") + e8.getMessage());
            if (Transform._allowExit) {
                System.exit(-1);
            }
        }
    }
    
    public static void printUsage() {
        System.err.println(new ErrorMsg("TRANSFORM_USAGE_STR"));
        if (Transform._allowExit) {
            System.exit(-1);
        }
    }
    
    public static void main(final String[] args) {
        try {
            if (args.length > 0) {
                int iterations = -1;
                boolean uri = false;
                boolean debug = false;
                boolean isJarFileSpecified = false;
                String jarFile = null;
                int i;
                for (i = 0; i < args.length && args[i].charAt(0) == '-'; ++i) {
                    if (args[i].equals("-u")) {
                        uri = true;
                    }
                    else if (args[i].equals("-x")) {
                        debug = true;
                    }
                    else if (args[i].equals("-s")) {
                        Transform._allowExit = false;
                    }
                    else if (args[i].equals("-j")) {
                        isJarFileSpecified = true;
                        jarFile = args[++i];
                    }
                    else if (args[i].equals("-n")) {
                        try {
                            iterations = Integer.parseInt(args[++i]);
                        }
                        catch (NumberFormatException ex) {}
                    }
                    else {
                        printUsage();
                    }
                }
                if (args.length - i < 2) {
                    printUsage();
                }
                final Transform handler = new Transform(args[i + 1], args[i], uri, debug, iterations);
                handler.setJarFileInputSrc(isJarFileSpecified, jarFile);
                final Vector params = new Vector();
                for (i += 2; i < args.length; ++i) {
                    final int equal = args[i].indexOf(61);
                    if (equal > 0) {
                        final String name = args[i].substring(0, equal);
                        final String value = args[i].substring(equal + 1);
                        params.addElement(new Parameter(name, value));
                    }
                    else {
                        printUsage();
                    }
                }
                if (i == args.length) {
                    handler.setParameters(params);
                    handler.doTransform();
                    if (Transform._allowExit) {
                        System.exit(0);
                    }
                }
            }
            else {
                printUsage();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static {
        Transform._allowExit = true;
    }
}
