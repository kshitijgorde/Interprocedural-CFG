// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import org.apache.xml.utils.WrappedRuntimeException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XString;
import java.util.Properties;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncSystemProperty extends FunctionOneArg
{
    static final long serialVersionUID = 3694874980992204867L;
    static final String XSLT_PROPERTIES = "org/apache/xalan/res/XSLTInfo.properties";
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final String fullName = super.m_arg0.execute(xctxt).str();
        final int indexOfNSSep = fullName.indexOf(58);
        String propName = "";
        final Properties xsltInfo = new Properties();
        this.loadPropertyFile("org/apache/xalan/res/XSLTInfo.properties", xsltInfo);
        String result;
        if (indexOfNSSep > 0) {
            final String prefix = (indexOfNSSep >= 0) ? fullName.substring(0, indexOfNSSep) : "";
            final String namespace = xctxt.getNamespaceContext().getNamespaceForPrefix(prefix);
            propName = ((indexOfNSSep < 0) ? fullName : fullName.substring(indexOfNSSep + 1));
            if (namespace.startsWith("http://www.w3.org/XSL/Transform") || namespace.equals("http://www.w3.org/1999/XSL/Transform")) {
                result = xsltInfo.getProperty(propName);
                if (null == result) {
                    this.warn(xctxt, "WG_PROPERTY_NOT_SUPPORTED", new Object[] { fullName });
                    return XString.EMPTYSTRING;
                }
            }
            else {
                this.warn(xctxt, "WG_DONT_DO_ANYTHING_WITH_NS", new Object[] { namespace, fullName });
                try {
                    result = System.getProperty(propName);
                    if (null == result) {
                        return XString.EMPTYSTRING;
                    }
                }
                catch (SecurityException se) {
                    this.warn(xctxt, "WG_SECURITY_EXCEPTION", new Object[] { fullName });
                    return XString.EMPTYSTRING;
                }
            }
        }
        else {
            try {
                result = System.getProperty(fullName);
                if (null == result) {
                    return XString.EMPTYSTRING;
                }
            }
            catch (SecurityException se2) {
                this.warn(xctxt, "WG_SECURITY_EXCEPTION", new Object[] { fullName });
                return XString.EMPTYSTRING;
            }
        }
        if (propName.equals("version") && result.length() > 0) {
            try {
                return new XString("1.0");
            }
            catch (Exception ex) {
                return new XString(result);
            }
        }
        return new XString(result);
    }
    
    public void loadPropertyFile(final String file, final Properties target) {
        try {
            final SecuritySupport ss = SecuritySupport.getInstance();
            final InputStream is = ss.getResourceAsStream(ObjectFactory.findClassLoader(), file);
            final BufferedInputStream bis = new BufferedInputStream(is);
            target.load(bis);
            bis.close();
        }
        catch (Exception ex) {
            throw new WrappedRuntimeException(ex);
        }
    }
}
