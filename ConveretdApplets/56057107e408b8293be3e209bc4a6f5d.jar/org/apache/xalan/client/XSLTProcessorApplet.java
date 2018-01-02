// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.client;

import javax.xml.transform.Templates;
import java.io.Reader;
import java.io.StringReader;
import javax.xml.transform.TransformerConfigurationException;
import java.awt.Graphics;
import java.io.IOException;
import javax.xml.transform.Transformer;
import java.net.MalformedURLException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.xalan.res.XSLMessages;
import javax.xml.transform.TransformerException;
import java.net.URL;
import javax.xml.transform.TransformerFactory;
import java.applet.Applet;

public class XSLTProcessorApplet extends Applet
{
    TransformerFactory m_tfactory;
    private String m_styleURL;
    private String m_documentURL;
    private final String PARAM_styleURL = "styleURL";
    private final String PARAM_documentURL = "documentURL";
    private String m_styleURLOfCached;
    private String m_documentURLOfCached;
    private URL m_codeBase;
    private String m_treeURL;
    private URL m_documentBase;
    private transient Thread m_callThread;
    private transient TrustedAgent m_trustedAgent;
    private transient Thread m_trustedWorker;
    private transient String m_htmlText;
    private transient String m_sourceText;
    private transient String m_nameOfIDAttrOfElemToModify;
    private transient String m_elemIdToModify;
    private transient String m_attrNameToSet;
    private transient String m_attrValueToSet;
    transient String m_key;
    transient String m_expression;
    
    public XSLTProcessorApplet() {
        this.m_tfactory = null;
        this.m_styleURLOfCached = null;
        this.m_documentURLOfCached = null;
        this.m_codeBase = null;
        this.m_treeURL = null;
        this.m_documentBase = null;
        this.m_callThread = null;
        this.m_trustedAgent = null;
        this.m_trustedWorker = null;
        this.m_htmlText = null;
        this.m_sourceText = null;
        this.m_nameOfIDAttrOfElemToModify = null;
        this.m_elemIdToModify = null;
        this.m_attrNameToSet = null;
        this.m_attrValueToSet = null;
    }
    
    static /* synthetic */ void access$2(final XSLTProcessorApplet $0, final String $1) {
        $0.m_htmlText = $1;
    }
    
    static /* synthetic */ void access$4(final XSLTProcessorApplet $0, final String $1) {
        $0.m_sourceText = $1;
    }
    
    public void destroy() {
        if (this.m_trustedWorker != null) {
            this.m_trustedWorker.stop();
            this.m_trustedWorker = null;
        }
        this.m_styleURLOfCached = null;
        this.m_documentURLOfCached = null;
    }
    
    public String escapeString(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char ch = s.charAt(i);
            if (ch == '<') {
                sb.append("&lt;");
            }
            else if (ch == '>') {
                sb.append("&gt;");
            }
            else if (ch == '&') {
                sb.append("&amp;");
            }
            else if (ch >= '\ud800' && ch < '\udc00') {
                if (i + 1 >= length) {
                    throw new RuntimeException(XSLMessages.createMessage(100, new Object[] { Integer.toHexString(ch) }));
                }
                int next = s.charAt(++i);
                if (next < 56320 || next >= 57344) {
                    throw new RuntimeException(XSLMessages.createMessage(100, new Object[] { String.valueOf(Integer.toHexString(ch)) + " " + Integer.toHexString(next) }));
                }
                next = (ch - '\ud800' << 10) + next - 56320 + 65536;
                sb.append("&#x");
                sb.append(Integer.toHexString(next));
                sb.append(";");
            }
            else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
    
    public void freeCache() {
        this.m_styleURLOfCached = null;
        this.m_documentURLOfCached = null;
    }
    
    public String getAppletInfo() {
        return "Name: XSLTProcessorApplet\r\nAuthor: Scott Boag";
    }
    
    public String getHtmlText() {
        this.m_trustedAgent.m_getData = true;
        this.m_callThread = Thread.currentThread();
        try {
            synchronized (this.m_callThread) {
                this.m_callThread.wait();
            }
            // monitorexit(this.m_callThread)
        }
        catch (InterruptedException ie) {
            System.out.println(ie.getMessage());
        }
        return this.m_htmlText;
    }
    
    public String[][] getParameterInfo() {
        final String[][] info = { { "styleURL", "String", "URL to an XSL stylesheet" }, { "documentURL", "String", "URL to an XML document" } };
        return info;
    }
    
    public String getResultTreeAsText() throws Exception {
        return this.escapeString(this.getHtmlText());
    }
    
    private String getSource() throws TransformerException {
        final StringWriter osw = new StringWriter();
        final PrintWriter pw = new PrintWriter(osw, false);
        String text = "";
        try {
            final URL docURL = new URL(this.m_documentBase, this.m_treeURL);
            synchronized (this.m_tfactory) {
                final Transformer transformer = this.m_tfactory.newTransformer();
                final StreamSource source = new StreamSource(docURL.toString());
                final StreamResult result = new StreamResult(pw);
                transformer.transform(source, result);
                text = osw.toString();
            }
            // monitorexit(this.m_tfactory)
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        catch (Exception any_error) {
            any_error.printStackTrace();
        }
        return text;
    }
    
    public String getSourceTreeAsText() throws Exception {
        return this.getTreeAsText(this.m_documentURL);
    }
    
    public String getStyleTreeAsText() throws Exception {
        return this.getTreeAsText(this.m_styleURL);
    }
    
    public String getTreeAsText(final String treeURL) throws IOException {
        this.m_treeURL = treeURL;
        this.m_trustedAgent.m_getData = true;
        this.m_trustedAgent.m_getSource = true;
        this.m_callThread = Thread.currentThread();
        try {
            synchronized (this.m_callThread) {
                this.m_callThread.wait();
            }
            // monitorexit(this.m_callThread)
        }
        catch (InterruptedException ie) {
            System.out.println(ie.getMessage());
        }
        return this.m_sourceText;
    }
    
    public void init() {
        String param = this.getParameter("styleURL");
        if (param != null) {
            this.setStyleURL(param);
        }
        param = this.getParameter("documentURL");
        if (param != null) {
            this.setDocumentURL(param);
        }
        this.m_codeBase = this.getCodeBase();
        this.m_documentBase = this.getDocumentBase();
        this.resize(320, 240);
    }
    
    public void paint(final Graphics g) {
    }
    
    private String processTransformation() throws TransformerException {
        String htmlData = null;
        this.showStatus("Waiting for Transformer and Parser to finish loading and JITing...");
        synchronized (this.m_tfactory) {
            URL documentURL = null;
            URL styleURL = null;
            final StringWriter osw = new StringWriter();
            final PrintWriter pw = new PrintWriter(osw, false);
            final StreamResult result = new StreamResult(pw);
            this.showStatus("Begin Transformation...");
            try {
                documentURL = new URL(this.m_codeBase, this.m_documentURL);
                final StreamSource xmlSource = new StreamSource(documentURL.toString());
                styleURL = new URL(this.m_codeBase, this.m_styleURL);
                final StreamSource xslSource = new StreamSource(styleURL.toString());
                final Transformer transformer = this.m_tfactory.newTransformer(xslSource);
                if (this.m_key != null) {
                    transformer.setParameter(this.m_key, this.m_expression);
                }
                transformer.transform(xmlSource, result);
            }
            catch (TransformerConfigurationException tfe) {
                tfe.printStackTrace();
                System.exit(-1);
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
                System.exit(-1);
            }
            this.showStatus("Transformation Done!");
            htmlData = osw.toString();
        }
        // monitorexit(this.m_tfactory)
        return htmlData;
    }
    
    public void setDocumentURL(final String urlString) {
        this.m_documentURL = urlString;
    }
    
    public void setStyleSheetAttribute(final String nameOfIDAttrOfElemToModify, final String elemId, final String attrName, final String value) {
        this.m_nameOfIDAttrOfElemToModify = nameOfIDAttrOfElemToModify;
        this.m_elemIdToModify = elemId;
        this.m_attrNameToSet = attrName;
        this.m_attrValueToSet = value;
    }
    
    public void setStyleURL(final String urlString) {
        this.m_styleURL = urlString;
    }
    
    public void setStylesheetParam(final String key, final String expr) {
        this.m_key = key;
        this.m_expression = expr;
    }
    
    public void start() {
        this.m_trustedAgent = new TrustedAgent();
        final Thread currentThread = Thread.currentThread();
        (this.m_trustedWorker = new Thread(currentThread.getThreadGroup(), this.m_trustedAgent)).start();
        try {
            this.m_tfactory = TransformerFactory.newInstance();
            this.showStatus("Causing Transformer and Parser to Load and JIT...");
            final StringReader xmlbuf = new StringReader("<?xml version='1.0'?><foo/>");
            final StringReader xslbuf = new StringReader("<?xml version='1.0'?><xsl:stylesheet xmlns:xsl='http://www.w3.org/1999/XSL/Transform' version='1.0'><xsl:template match='foo'><out/></xsl:template></xsl:stylesheet>");
            final PrintWriter pw = new PrintWriter(new StringWriter());
            synchronized (this.m_tfactory) {
                final Templates templates = this.m_tfactory.newTemplates(new StreamSource(xslbuf));
                final Transformer transformer = templates.newTransformer();
                transformer.transform(new StreamSource(xmlbuf), new StreamResult(pw));
            }
            // monitorexit(this.m_tfactory)
            System.out.println("Primed the pump!");
            this.showStatus("Ready to go!");
        }
        catch (Exception e) {
            this.showStatus("Could not prime the pump!");
            System.out.println("Could not prime the pump!");
            e.printStackTrace();
        }
    }
    
    public void stop() {
        if (this.m_trustedWorker != null) {
            this.m_trustedWorker.stop();
            this.m_trustedWorker = null;
        }
        this.m_styleURLOfCached = null;
        this.m_documentURLOfCached = null;
    }
    
    public String transformToHtml(final String doc) {
        if (doc != null) {
            this.m_documentURL = doc;
        }
        this.m_styleURL = null;
        return this.getHtmlText();
    }
    
    public String transformToHtml(final String doc, final String style) {
        if (doc != null) {
            this.m_documentURL = doc;
        }
        if (style != null) {
            this.m_styleURL = style;
        }
        return this.getHtmlText();
    }
    
    class TrustedAgent implements Runnable
    {
        public boolean m_getData;
        public boolean m_getSource;
        
        TrustedAgent() {
            this.m_getData = false;
            this.m_getSource = false;
        }
        
        public void run() {
            while (true) {
                Thread.yield();
                if (this.m_getData) {
                    try {
                        try {
                            this.m_getData = false;
                            XSLTProcessorApplet.access$2(XSLTProcessorApplet.this, null);
                            XSLTProcessorApplet.access$4(XSLTProcessorApplet.this, null);
                            if (this.m_getSource) {
                                this.m_getSource = false;
                                XSLTProcessorApplet.access$4(XSLTProcessorApplet.this, XSLTProcessorApplet.this.getSource());
                            }
                            else {
                                XSLTProcessorApplet.access$2(XSLTProcessorApplet.this, XSLTProcessorApplet.this.processTransformation());
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                    finally {
                        synchronized (XSLTProcessorApplet.this.m_callThread) {
                            XSLTProcessorApplet.this.m_callThread.notify();
                        }
                        // monitorexit(XSLTProcessorApplet.access$7(this.this$0))
                    }
                }
                try {
                    Thread.sleep(50L);
                }
                catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }
}
