// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.lib;

import javax.xml.transform.stream.StreamResult;
import org.apache.xml.serializer.SerializationHandler;
import org.apache.xalan.templates.OutputProperties;
import javax.xml.transform.Result;
import java.io.FileOutputStream;
import java.io.File;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import org.w3c.dom.Node;
import org.apache.xml.utils.PrefixResolver;
import javax.xml.transform.SourceLocator;
import org.apache.xpath.XPath;
import org.apache.xalan.transformer.TransformerImpl;
import org.xml.sax.SAXException;
import java.io.OutputStream;
import org.apache.xalan.templates.ElemTemplateElement;
import org.xml.sax.ContentHandler;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import org.apache.xalan.templates.ElemExtensionCall;
import org.apache.xalan.extensions.XSLProcessorContext;
import java.util.Hashtable;

public class Redirect
{
    protected Hashtable m_formatterListeners;
    protected Hashtable m_outputStreams;
    public static final boolean DEFAULT_APPEND_OPEN = false;
    public static final boolean DEFAULT_APPEND_WRITE = false;
    
    public Redirect() {
        this.m_formatterListeners = new Hashtable();
        this.m_outputStreams = new Hashtable();
    }
    
    public void open(final XSLProcessorContext context, final ElemExtensionCall elem) throws MalformedURLException, FileNotFoundException, IOException, TransformerException {
        final String fileName = this.getFilename(context, elem);
        final Object flistener = this.m_formatterListeners.get(fileName);
        if (null == flistener) {
            final String mkdirsExpr = elem.getAttribute("mkdirs", context.getContextNode(), context.getTransformer());
            final boolean mkdirs = mkdirsExpr == null || (mkdirsExpr.equals("true") || mkdirsExpr.equals("yes"));
            final String appendExpr = elem.getAttribute("append", context.getContextNode(), context.getTransformer());
            final boolean append = appendExpr != null && (appendExpr.equals("true") || appendExpr.equals("yes"));
            final Object ignored = this.makeFormatterListener(context, elem, fileName, true, mkdirs, append);
        }
    }
    
    public void write(final XSLProcessorContext context, final ElemExtensionCall elem) throws MalformedURLException, FileNotFoundException, IOException, TransformerException {
        final String fileName = this.getFilename(context, elem);
        final Object flObject = this.m_formatterListeners.get(fileName);
        boolean inTable = false;
        ContentHandler formatter;
        if (null == flObject) {
            final String mkdirsExpr = elem.getAttribute("mkdirs", context.getContextNode(), context.getTransformer());
            final boolean mkdirs = mkdirsExpr == null || (mkdirsExpr.equals("true") || mkdirsExpr.equals("yes"));
            final String appendExpr = elem.getAttribute("append", context.getContextNode(), context.getTransformer());
            final boolean append = appendExpr != null && (appendExpr.equals("true") || appendExpr.equals("yes"));
            formatter = this.makeFormatterListener(context, elem, fileName, true, mkdirs, append);
        }
        else {
            inTable = true;
            formatter = (ContentHandler)flObject;
        }
        final TransformerImpl transf = context.getTransformer();
        this.startRedirection(transf, formatter);
        transf.executeChildTemplates(elem, context.getContextNode(), context.getMode(), formatter);
        this.endRedirection(transf);
        if (!inTable) {
            final OutputStream ostream = this.m_outputStreams.get(fileName);
            if (null != ostream) {
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
    
    public void close(final XSLProcessorContext context, final ElemExtensionCall elem) throws MalformedURLException, FileNotFoundException, IOException, TransformerException {
        final String fileName = this.getFilename(context, elem);
        final Object formatterObj = this.m_formatterListeners.get(fileName);
        if (null != formatterObj) {
            final ContentHandler fl = (ContentHandler)formatterObj;
            try {
                fl.endDocument();
            }
            catch (SAXException se) {
                throw new TransformerException(se);
            }
            final OutputStream ostream = this.m_outputStreams.get(fileName);
            if (null != ostream) {
                ostream.close();
                this.m_outputStreams.remove(fileName);
            }
            this.m_formatterListeners.remove(fileName);
        }
    }
    
    private String getFilename(final XSLProcessorContext context, final ElemExtensionCall elem) throws MalformedURLException, FileNotFoundException, IOException, TransformerException {
        final String fileNameExpr = elem.getAttribute("select", context.getContextNode(), context.getTransformer());
        String fileName;
        if (null != fileNameExpr) {
            final XPathContext xctxt = context.getTransformer().getXPathContext();
            final XPath myxpath = new XPath(fileNameExpr, elem, xctxt.getNamespaceContext(), 0);
            final XObject xobj = myxpath.execute(xctxt, context.getContextNode(), elem);
            fileName = xobj.str();
            if (null == fileName || fileName.length() == 0) {
                fileName = elem.getAttribute("file", context.getContextNode(), context.getTransformer());
            }
        }
        else {
            fileName = elem.getAttribute("file", context.getContextNode(), context.getTransformer());
        }
        if (null == fileName) {
            context.getTransformer().getMsgMgr().error(elem, elem, context.getContextNode(), "ER_REDIRECT_COULDNT_GET_FILENAME");
        }
        return fileName;
    }
    
    private String urlToFileName(String base) {
        if (null != base) {
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
    
    private ContentHandler makeFormatterListener(final XSLProcessorContext context, final ElemExtensionCall elem, final String fileName, final boolean shouldPutInTable, final boolean mkdirs, final boolean append) throws MalformedURLException, FileNotFoundException, IOException, TransformerException {
        File file = new File(fileName);
        final TransformerImpl transformer = context.getTransformer();
        if (!file.isAbsolute()) {
            final Result outputTarget = transformer.getOutputTarget();
            String base;
            if (null != outputTarget && (base = outputTarget.getSystemId()) != null) {
                base = this.urlToFileName(base);
            }
            else {
                base = this.urlToFileName(transformer.getBaseURLOfSource());
            }
            if (null != base) {
                final File baseFile = new File(base);
                file = new File(baseFile.getParent(), fileName);
            }
        }
        if (mkdirs) {
            final String dirStr = file.getParent();
            if (null != dirStr && dirStr.length() > 0) {
                final File dir = new File(dirStr);
                dir.mkdirs();
            }
        }
        final OutputProperties format = transformer.getOutputFormat();
        final FileOutputStream ostream = new FileOutputStream(file.getPath(), append);
        try {
            final SerializationHandler flistener = this.createSerializationHandler(transformer, ostream, file, format);
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
    
    public void startRedirection(final TransformerImpl transf, final ContentHandler formatter) {
    }
    
    public void endRedirection(final TransformerImpl transf) {
    }
    
    public SerializationHandler createSerializationHandler(final TransformerImpl transformer, final FileOutputStream ostream, final File file, final OutputProperties format) throws IOException, TransformerException {
        final SerializationHandler serializer = transformer.createSerializationHandler(new StreamResult(ostream), format);
        return serializer;
    }
}
