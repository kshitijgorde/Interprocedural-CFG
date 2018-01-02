// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer.dom3;

import org.w3c.dom.DOMError;
import org.w3c.dom.DOMErrorHandler;

final class DOMErrorHandlerImpl implements DOMErrorHandler
{
    public boolean handleError(final DOMError error) {
        boolean fail = true;
        String severity = null;
        if (error.getSeverity() == 1) {
            fail = false;
            severity = "[Warning]";
        }
        else if (error.getSeverity() == 2) {
            severity = "[Error]";
        }
        else if (error.getSeverity() == 3) {
            severity = "[Fatal Error]";
        }
        System.err.println(severity + ": " + error.getMessage() + "\t");
        System.err.println("Type : " + error.getType() + "\t" + "Related Data: " + error.getRelatedData() + "\t" + "Related Exception: " + error.getRelatedException());
        return fail;
    }
}
