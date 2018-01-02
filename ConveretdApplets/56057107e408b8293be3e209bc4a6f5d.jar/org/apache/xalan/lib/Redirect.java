// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.lib;

import org.apache.xalan.templates.ElemTemplateElement;
import org.apache.xalan.templates.OutputProperties;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.File;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import org.w3c.dom.Node;
import javax.xml.transform.SourceLocator;
import org.apache.xpath.XPath;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.io.OutputStream;
import org.xml.sax.SAXException;
import javax.xml.transform.TransformerException;
import org.xml.sax.ContentHandler;
import org.apache.xalan.templates.ElemExtensionCall;
import org.apache.xalan.extensions.XSLProcessorContext;
import java.util.Hashtable;

public class Redirect
{
    protected Hashtable m_formatterListeners;
    protected Hashtable m_outputStreams;
    
    public Redirect() {
        this.m_formatterListeners = new Hashtable();
        this.m_outputStreams = new Hashtable();
    }
    
    public void close(final XSLProcessorContext context, final ElemExtensionCall elem) throws MalformedURLException, FileNotFoundException, IOException, TransformerException {
        final String fileName = this.getFilename(context, elem);
        final Object formatterObj = this.m_formatterListeners.get(fileName);
        if (formatterObj != null) {
            final ContentHandler fl = (ContentHandler)formatterObj;
            try {
                fl.endDocument();
            }
            catch (SAXException se) {
                throw new TransformerException(se);
            }
            final OutputStream ostream = this.m_outputStreams.get(fileName);
            if (ostream != null) {
                ostream.close();
                this.m_outputStreams.remove(fileName);
            }
            this.m_formatterListeners.remove(fileName);
        }
    }
    
    private String getFilename(final XSLProcessorContext context, final ElemExtensionCall elem) throws MalformedURLException, FileNotFoundException, IOException, TransformerException {
        final String fileNameExpr = elem.getAttribute("select", context.getContextNode(), context.getTransformer());
        String fileName;
        if (fileNameExpr != null) {
            final XPathContext xctxt = context.getTransformer().getXPathContext();
            final XPath myxpath = new XPath(fileNameExpr, elem, xctxt.getNamespaceContext(), 0);
            final XObject xobj = myxpath.execute(xctxt, context.getContextNode(), xctxt.getNamespaceContext());
            fileName = xobj.str();
            if (fileName == null || fileName.length() == 0) {
                fileName = elem.getAttribute("file", context.getContextNode(), context.getTransformer());
            }
        }
        else {
            fileName = elem.getAttribute("file", context.getContextNode(), context.getTransformer());
        }
        if (fileName == null) {
            context.getTransformer().getMsgMgr().error(elem, elem, context.getContextNode(), 85);
        }
        return fileName;
    }
    
    private ContentHandler makeFormatterListener(final XSLProcessorContext context, final ElemExtensionCall elem, final String fileName, final boolean shouldPutInTable, final boolean mkdirs) throws MalformedURLException, FileNotFoundException, IOException, TransformerException {
        File file = new File(fileName);
        final TransformerImpl transformer = context.getTransformer();
        if (!file.isAbsolute()) {
            final String base = this.urlToFileName(elem.getStylesheet().getSystemId());
            if (base != null) {
                final File baseFile = new File(base);
                file = new File(baseFile.getParent(), fileName);
            }
        }
        if (mkdirs) {
            final String dirStr = file.getParent();
            if (dirStr != null && dirStr.length() > 0) {
                final File dir = new File(dirStr);
                dir.mkdirs();
            }
        }
        final OutputProperties format = transformer.getOutputFormat();
        final FileOutputStream ostream = new FileOutputStream(file);
        try {
            final ContentHandler flistener = transformer.createResultContentHandler(new StreamResult(ostream), format);
            try {
                flistener.startDocument();
            }
            catch (SAXException se) {
                throw new TransformerException(se);
            }
            if (shouldPutInTable) {
                this.m_outputStreams.put(fileName, ostream);
                this.m_formatterListeners.put(fileName, flistener);
            }
            return flistener;
        }
        catch (TransformerException te) {
            throw new TransformerException(te);
        }
    }
    
    public void open(final XSLProcessorContext context, final ElemExtensionCall elem) throws MalformedURLException, FileNotFoundException, IOException, TransformerException {
        final String fileName = this.getFilename(context, elem);
        final Object flistener = this.m_formatterListeners.get(fileName);
        if (flistener == null) {
            final String mkdirsExpr = elem.getAttribute("mkdirs", context.getContextNode(), context.getTransformer());
            final boolean mkdirs = mkdirsExpr == null || mkdirsExpr.equals("true") || mkdirsExpr.equals("yes");
            this.makeFormatterListener(context, elem, fileName, true, mkdirs);
        }
    }
    
    private String urlToFileName(String base) {
        if (base != null) {
            if (base.startsWith("file:////")) {
                base = base.substring(7);
            }
            else if (base.startsWith("file:///")) {
                base = base.substring(6);
            }
            else if (base.startsWith("file://")) {
                base = base.substring(5);
            }
            else if (base.startsWith("file:/")) {
                base = base.substring(5);
            }
            else if (base.startsWith("file:")) {
                base = base.substring(4);
            }
        }
        return base;
    }
    
    public void write(final XSLProcessorContext context, final ElemExtensionCall elem) throws MalformedURLException, FileNotFoundException, IOException, TransformerException {
        final String fileName = this.getFilename(context, elem);
        final Object flObject = this.m_formatterListeners.get(fileName);
        boolean inTable = false;
        ContentHandler formatter;
        if (flObject == null) {
            final String mkdirsExpr = elem.getAttribute("mkdirs", context.getContextNode(), context.getTransformer());
            final boolean mkdirs = mkdirsExpr == null || mkdirsExpr.equals("true") || mkdirsExpr.equals("yes");
            formatter = this.makeFormatterListener(context, elem, fileName, true, mkdirs);
        }
        else {
            inTable = true;
            formatter = (ContentHandler)flObject;
        }
        final TransformerImpl transf = context.getTransformer();
        transf.executeChildTemplates(elem, context.getContextNode(), context.getMode(), formatter);
        if (!inTable) {
            final OutputStream ostream = this.m_outputStreams.get(fileName);
            if (ostream != null) {
                try {
                    formatter.endDocument();
                }
                catch (SAXException se) {
                    throw new TransformerException(se);
                }
                ostream.close();
                this.m_outputStreams.remove(fileName);
                this.m_formatterListeners.remove(fileName);
            }
        }
    }
}
