// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import org.apache.xml.utils.WrappedRuntimeException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XNumber;
import org.apache.xpath.objects.XString;
import java.util.Properties;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncSystemProperty extends FunctionOneArg
{
    static String XSLT_PROPERTIES;
    
    static {
        FuncSystemProperty.XSLT_PROPERTIES = "/org/apache/xalan/res/XSLTInfo.properties";
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final String fullName = super.m_arg0.execute(xctxt).str();
        final int indexOfNSSep = fullName.indexOf(58);
        String propName = "";
        final Properties xsltInfo = new Properties();
        this.loadPropertyFile(FuncSystemProperty.XSLT_PROPERTIES, xsltInfo);
        String result = null;
        Label_0232: {
            if (indexOfNSSep > 0) {
                final String prefix = (indexOfNSSep >= 0) ? fullName.substring(0, indexOfNSSep) : "";
                final String namespace = xctxt.getNamespaceContext().getNamespaceForPrefix(prefix);
                propName = ((indexOfNSSep < 0) ? fullName : fullName.substring(indexOfNSSep + 1));
                if (namespace.startsWith("http://www.w3.org/XSL/Transform") || namespace.equals("http://www.w3.org/1999/XSL/Transform")) {
                    result = xsltInfo.getProperty(propName);
                    if (result == null) {
                        this.warn(xctxt, 2, new Object[] { fullName });
                        return XString.EMPTYSTRING;
                    }
                    break Label_0232;
                }
                else {
                    this.warn(xctxt, 3, new Object[] { namespace, fullName });
                    try {
                        result = System.getProperty(propName);
                        if (result == null) {
                            return XString.EMPTYSTRING;
                        }
                        break Label_0232;
                    }
                    catch (SecurityException ex) {
                        this.warn(xctxt, 4, new Object[] { fullName });
                        return XString.EMPTYSTRING;
                    }
                }
            }
            try {
                result = System.getProperty(fullName);
                if (result == null) {
                    return XString.EMPTYSTRING;
                }
            }
            catch (SecurityException ex2) {
                this.warn(xctxt, 4, new Object[] { fullName });
                return XString.EMPTYSTRING;
            }
        }
        if (propName.equals("version") && result.length() > 0) {
            try {
                return new XNumber(1.0);
            }
            catch (Exception ex3) {
                return new XString(result);
            }
        }
        return new XString(result);
    }
    
    public void loadPropertyFile(final String file, final Properties target) {
        try {
            final InputStream is = this.getClass().getResourceAsStream(file);
            final BufferedInputStream bis = new BufferedInputStream(is);
            target.load(bis);
            bis.close();
        }
        catch (Exception e) {
            throw new WrappedRuntimeException(e);
        }
    }
}
