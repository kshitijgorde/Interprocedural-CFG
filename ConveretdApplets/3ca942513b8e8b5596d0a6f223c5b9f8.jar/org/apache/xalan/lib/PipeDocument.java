// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.lib;

import org.xml.sax.SAXNotRecognizedException;
import org.apache.xml.serializer.Serializer;
import java.util.Properties;
import org.xml.sax.XMLReader;
import java.io.OutputStream;
import java.io.FileOutputStream;
import org.apache.xml.serializer.SerializerFactory;
import javax.xml.transform.Result;
import javax.xml.transform.sax.SAXResult;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.xml.sax.SAXException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.Templates;
import org.apache.xalan.templates.AVT;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.apache.xpath.XPathContext;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xalan.templates.ElemLiteralResult;
import org.w3c.dom.Element;
import java.util.Vector;
import org.apache.xml.utils.SystemIDResolver;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import org.apache.xalan.templates.ElemExtensionCall;
import org.apache.xalan.extensions.XSLProcessorContext;

public class PipeDocument
{
    public void pipeDocument(final XSLProcessorContext context, final ElemExtensionCall elem) throws TransformerException, TransformerConfigurationException, SAXException, IOException, FileNotFoundException {
        final SAXTransformerFactory saxTFactory = (SAXTransformerFactory)TransformerFactory.newInstance();
        final String source = elem.getAttribute("source", context.getContextNode(), context.getTransformer());
        final TransformerImpl transImpl = context.getTransformer();
        final String baseURLOfSource = transImpl.getBaseURLOfSource();
        final String absSourceURL = SystemIDResolver.getAbsoluteURI(source, baseURLOfSource);
        final String target = elem.getAttribute("target", context.getContextNode(), context.getTransformer());
        final XPathContext xctxt = context.getTransformer().getXPathContext();
        final int xt = xctxt.getDTMHandleFromNode(context.getContextNode());
        final String sysId = elem.getSystemId();
        NodeList ssNodes = null;
        NodeList paramNodes = null;
        Node ssNode = null;
        Node paramNode = null;
        if (elem.hasChildNodes()) {
            ssNodes = elem.getChildNodes();
            final Vector vTHandler = new Vector(ssNodes.getLength());
            for (int i = 0; i < ssNodes.getLength(); ++i) {
                ssNode = ssNodes.item(i);
                if (ssNode.getNodeType() == 1 && ((Element)ssNode).getTagName().equals("stylesheet") && ssNode instanceof ElemLiteralResult) {
                    AVT avt = ((ElemLiteralResult)ssNode).getLiteralResultAttribute("href");
                    final String href = avt.evaluate(xctxt, xt, elem);
                    final String absURI = SystemIDResolver.getAbsoluteURI(href, sysId);
                    final Templates tmpl = saxTFactory.newTemplates(new StreamSource(absURI));
                    final TransformerHandler tHandler = saxTFactory.newTransformerHandler(tmpl);
                    final Transformer trans = tHandler.getTransformer();
                    vTHandler.addElement(tHandler);
                    paramNodes = ssNode.getChildNodes();
                    for (int j = 0; j < paramNodes.getLength(); ++j) {
                        paramNode = paramNodes.item(j);
                        if (paramNode.getNodeType() == 1 && ((Element)paramNode).getTagName().equals("param") && paramNode instanceof ElemLiteralResult) {
                            avt = ((ElemLiteralResult)paramNode).getLiteralResultAttribute("name");
                            final String pName = avt.evaluate(xctxt, xt, elem);
                            avt = ((ElemLiteralResult)paramNode).getLiteralResultAttribute("value");
                            final String pValue = avt.evaluate(xctxt, xt, elem);
                            trans.setParameter(pName, pValue);
                        }
                    }
                }
            }
            this.usePipe(vTHandler, absSourceURL, target);
        }
    }
    
    public void usePipe(final Vector vTHandler, final String source, final String target) throws TransformerException, TransformerConfigurationException, FileNotFoundException, IOException, SAXException, SAXNotRecognizedException {
        final XMLReader reader = XMLReaderFactory.createXMLReader();
        final TransformerHandler tHFirst = vTHandler.firstElement();
        reader.setContentHandler(tHFirst);
        reader.setProperty("http://xml.org/sax/properties/lexical-handler", tHFirst);
        for (int i = 1; i < vTHandler.size(); ++i) {
            final TransformerHandler tHFrom = vTHandler.elementAt(i - 1);
            final TransformerHandler tHTo = vTHandler.elementAt(i);
            tHFrom.setResult(new SAXResult(tHTo));
        }
        final TransformerHandler tHLast = vTHandler.lastElement();
        final Transformer trans = tHLast.getTransformer();
        final Properties outputProps = trans.getOutputProperties();
        final Serializer serializer = SerializerFactory.getSerializer(outputProps);
        final FileOutputStream out = new FileOutputStream(target);
        try {
            serializer.setOutputStream(out);
            tHLast.setResult(new SAXResult(serializer.asContentHandler()));
            reader.parse(source);
        }
        finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
