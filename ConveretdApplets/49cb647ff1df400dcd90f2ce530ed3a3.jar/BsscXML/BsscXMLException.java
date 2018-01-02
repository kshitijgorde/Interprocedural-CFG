// 
// Decompiled by Procyon v0.5.30
// 

package BsscXML;

public class BsscXMLException extends Exception
{
    static final String TYPE_MISMATCH = "Type Mismatch!";
    static final String NOT_ONE_ROOT = "Only one Root element is allowed!";
    static final String INTERNAL_ERROR = "Internal Error!";
    static final String DOCUMENTEND_INPROPERLY = "Wrong XML Format!";
    static final String BAD_XML_FORMAT = "Bad XML Format!";
    
    BsscXMLException() {
    }
    
    BsscXMLException(final String s) {
        super(s);
    }
}
