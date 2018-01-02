// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.dtd;

import java.util.Enumeration;
import org.apache.xerces.impl.dv.DatatypeValidator;
import java.util.Hashtable;

public class XML11DTDDVFactoryImpl extends DTDDVFactoryImpl
{
    static Hashtable fXML11BuiltInTypes;
    
    public DatatypeValidator getBuiltInDV(final String s) {
        if (XML11DTDDVFactoryImpl.fXML11BuiltInTypes.get(s) != null) {
            return XML11DTDDVFactoryImpl.fXML11BuiltInTypes.get(s);
        }
        return DTDDVFactoryImpl.fBuiltInTypes.get(s);
    }
    
    public Hashtable getBuiltInTypes() {
        final Hashtable hashtable = (Hashtable)DTDDVFactoryImpl.fBuiltInTypes.clone();
        final Enumeration<Object> keys = XML11DTDDVFactoryImpl.fXML11BuiltInTypes.keys();
        while (keys.hasMoreElements()) {
            final Object nextElement = keys.nextElement();
            hashtable.put(nextElement, XML11DTDDVFactoryImpl.fXML11BuiltInTypes.get(nextElement));
        }
        return hashtable;
    }
    
    static {
        (XML11DTDDVFactoryImpl.fXML11BuiltInTypes = new Hashtable()).put("XML11ID", new XML11IDDatatypeValidator());
        final XML11IDREFDatatypeValidator xml11IDREFDatatypeValidator = new XML11IDREFDatatypeValidator();
        XML11DTDDVFactoryImpl.fXML11BuiltInTypes.put("XML11IDREF", xml11IDREFDatatypeValidator);
        XML11DTDDVFactoryImpl.fXML11BuiltInTypes.put("XML11IDREFS", new ListDatatypeValidator(xml11IDREFDatatypeValidator));
        final XML11NMTOKENDatatypeValidator xml11NMTOKENDatatypeValidator = new XML11NMTOKENDatatypeValidator();
        XML11DTDDVFactoryImpl.fXML11BuiltInTypes.put("XML11NMTOKEN", xml11NMTOKENDatatypeValidator);
        XML11DTDDVFactoryImpl.fXML11BuiltInTypes.put("XML11NMTOKENS", new ListDatatypeValidator(xml11NMTOKENDatatypeValidator));
    }
}
