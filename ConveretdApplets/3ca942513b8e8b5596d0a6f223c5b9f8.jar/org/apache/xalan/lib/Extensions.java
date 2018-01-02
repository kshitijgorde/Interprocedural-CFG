// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.lib;

import java.lang.reflect.Method;
import org.apache.xml.utils.Hashtree2Node;
import java.util.Hashtable;
import org.apache.xalan.xslt.EnvironmentCheck;
import java.util.StringTokenizer;
import org.xml.sax.SAXNotSupportedException;
import org.apache.xpath.objects.XObject;
import org.w3c.dom.NodeList;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Text;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.xml.utils.WrappedRuntimeException;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.xpath.objects.XNumber;
import org.apache.xpath.objects.XBoolean;
import org.w3c.dom.traversal.NodeIterator;
import org.apache.xpath.NodeSet;
import org.apache.xalan.extensions.ExpressionContext;

public class Extensions
{
    static /* synthetic */ Class class$java$util$Hashtable;
    static /* synthetic */ Class class$java$lang$String;
    
    public static NodeSet nodeset(final ExpressionContext myProcessor, final Object rtf) {
        if (rtf instanceof NodeIterator) {
            return new NodeSet((NodeIterator)rtf);
        }
        String textNodeValue;
        if (rtf instanceof String) {
            textNodeValue = (String)rtf;
        }
        else if (rtf instanceof Boolean) {
            textNodeValue = new XBoolean((boolean)rtf).str();
        }
        else if (rtf instanceof Double) {
            textNodeValue = new XNumber((double)rtf).str();
        }
        else {
            textNodeValue = rtf.toString();
        }
        try {
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            final DocumentBuilder db = dbf.newDocumentBuilder();
            final Document myDoc = db.newDocument();
            final Text textNode = myDoc.createTextNode(textNodeValue);
            final DocumentFragment docFrag = myDoc.createDocumentFragment();
            docFrag.appendChild(textNode);
            return new NodeSet(docFrag);
        }
        catch (ParserConfigurationException pce) {
            throw new WrappedRuntimeException(pce);
        }
    }
    
    public static NodeList intersection(final NodeList nl1, final NodeList nl2) {
        return ExsltSets.intersection(nl1, nl2);
    }
    
    public static NodeList difference(final NodeList nl1, final NodeList nl2) {
        return ExsltSets.difference(nl1, nl2);
    }
    
    public static NodeList distinct(final NodeList nl) {
        return ExsltSets.distinct(nl);
    }
    
    public static boolean hasSameNodes(final NodeList nl1, final NodeList nl2) {
        final NodeSet ns1 = new NodeSet(nl1);
        final NodeSet ns2 = new NodeSet(nl2);
        if (ns1.getLength() != ns2.getLength()) {
            return false;
        }
        for (int i = 0; i < ns1.getLength(); ++i) {
            final Node n = ns1.elementAt(i);
            if (!ns2.contains(n)) {
                return false;
            }
        }
        return true;
    }
    
    public static XObject evaluate(final ExpressionContext myContext, final String xpathExpr) throws SAXNotSupportedException {
        return ExsltDynamic.evaluate(myContext, xpathExpr);
    }
    
    public static NodeList tokenize(final String toTokenize, final String delims) {
        final Document doc = DocumentHolder.m_doc;
        final StringTokenizer lTokenizer = new StringTokenizer(toTokenize, delims);
        final NodeSet resultSet = new NodeSet();
        synchronized (doc) {
            while (lTokenizer.hasMoreTokens()) {
                resultSet.addNode(doc.createTextNode(lTokenizer.nextToken()));
            }
        }
        return resultSet;
    }
    
    public static NodeList tokenize(final String toTokenize) {
        return tokenize(toTokenize, " \t\n\r");
    }
    
    public static Node checkEnvironment(final ExpressionContext myContext) {
        Document factoryDocument;
        try {
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            final DocumentBuilder db = dbf.newDocumentBuilder();
            factoryDocument = db.newDocument();
        }
        catch (ParserConfigurationException pce) {
            throw new WrappedRuntimeException(pce);
        }
        Node resultNode = null;
        try {
            resultNode = checkEnvironmentUsingWhich(myContext, factoryDocument);
            if (null != resultNode) {
                return resultNode;
            }
            EnvironmentCheck envChecker = new EnvironmentCheck();
            final Hashtable h = envChecker.getEnvironmentHash();
            resultNode = factoryDocument.createElement("checkEnvironmentExtension");
            envChecker.appendEnvironmentReport(resultNode, factoryDocument, h);
            envChecker = null;
        }
        catch (Exception e) {
            throw new WrappedRuntimeException(e);
        }
        return resultNode;
    }
    
    private static Node checkEnvironmentUsingWhich(final ExpressionContext myContext, final Document factoryDocument) {
        final String WHICH_CLASSNAME = "org.apache.env.Which";
        final String WHICH_METHODNAME = "which";
        final Class[] WHICH_METHOD_ARGS = { (Extensions.class$java$util$Hashtable == null) ? (Extensions.class$java$util$Hashtable = class$("java.util.Hashtable")) : Extensions.class$java$util$Hashtable, (Extensions.class$java$lang$String == null) ? (Extensions.class$java$lang$String = class$("java.lang.String")) : Extensions.class$java$lang$String, (Extensions.class$java$lang$String == null) ? (Extensions.class$java$lang$String = class$("java.lang.String")) : Extensions.class$java$lang$String };
        try {
            final Class clazz = ObjectFactory.findProviderClass("org.apache.env.Which", ObjectFactory.findClassLoader(), true);
            if (null == clazz) {
                return null;
            }
            final Method method = clazz.getMethod("which", (Class[])WHICH_METHOD_ARGS);
            final Hashtable report = new Hashtable();
            final Object[] methodArgs = { report, "XmlCommons;Xalan;Xerces;Crimson;Ant", "" };
            final Object returnValue = method.invoke(null, methodArgs);
            final Node resultNode = factoryDocument.createElement("checkEnvironmentExtension");
            Hashtree2Node.appendHashToNode(report, "whichReport", resultNode, factoryDocument);
            return resultNode;
        }
        catch (Throwable t) {
            return null;
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
    
    private static class DocumentHolder
    {
        private static final Document m_doc;
        
        static {
            try {
                m_doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            }
            catch (ParserConfigurationException pce) {
                throw new WrappedRuntimeException(pce);
            }
        }
    }
}
