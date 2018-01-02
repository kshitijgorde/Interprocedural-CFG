// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.input;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ErrorHandler;

public class BuilderErrorHandler implements ErrorHandler
{
    private static final String CVS_ID = "@(#) $RCSfile: BuilderErrorHandler.java,v $ $Revision: 1.7 $ $Date: 2002/01/08 09:17:10 $ $Name: jdom_1_0_b8 $";
    
    public void warning(final SAXParseException exception) throws SAXException {
    }
    
    public void error(final SAXParseException exception) throws SAXException {
        throw exception;
    }
    
    public void fatalError(final SAXParseException exception) throws SAXException {
        throw exception;
    }
}
