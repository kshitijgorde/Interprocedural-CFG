// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.res;

import org.apache.xml.res.XMLMessages;
import java.util.ListResourceBundle;
import org.apache.xpath.res.XPATHMessages;

public class XSLMessages extends XPATHMessages
{
    private static ListResourceBundle XSLTBundle;
    private static final String XSLT_ERROR_RESOURCES = "org.apache.xalan.res.XSLTErrorResources";
    
    public static final String createMessage(final String msgKey, final Object[] args) {
        if (XSLMessages.XSLTBundle == null) {
            XSLMessages.XSLTBundle = XMLMessages.loadResourceBundle("org.apache.xalan.res.XSLTErrorResources");
        }
        if (XSLMessages.XSLTBundle != null) {
            return XMLMessages.createMsg(XSLMessages.XSLTBundle, msgKey, args);
        }
        return "Could not load any resource bundles.";
    }
    
    public static final String createWarning(final String msgKey, final Object[] args) {
        if (XSLMessages.XSLTBundle == null) {
            XSLMessages.XSLTBundle = XMLMessages.loadResourceBundle("org.apache.xalan.res.XSLTErrorResources");
        }
        if (XSLMessages.XSLTBundle != null) {
            return XMLMessages.createMsg(XSLMessages.XSLTBundle, msgKey, args);
        }
        return "Could not load any resource bundles.";
    }
    
    static {
        XSLMessages.XSLTBundle = null;
    }
}
