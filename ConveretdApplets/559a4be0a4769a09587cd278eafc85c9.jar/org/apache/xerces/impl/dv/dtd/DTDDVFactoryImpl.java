// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.dtd;

import org.apache.xerces.impl.dv.DatatypeValidator;
import java.util.Hashtable;
import org.apache.xerces.impl.dv.DTDDVFactory;

public class DTDDVFactoryImpl extends DTDDVFactory
{
    static Hashtable fBuiltInTypes;
    
    public DatatypeValidator getBuiltInDV(final String name) {
        return DTDDVFactoryImpl.fBuiltInTypes.get(name);
    }
    
    public Hashtable getBuiltInTypes() {
        return (Hashtable)DTDDVFactoryImpl.fBuiltInTypes.clone();
    }
    
    static void createBuiltInTypes() {
        DTDDVFactoryImpl.fBuiltInTypes.put("string", new StringDatatypeValidator());
        DTDDVFactoryImpl.fBuiltInTypes.put("ID", new IDDatatypeValidator());
        DatatypeValidator dvTemp = new IDREFDatatypeValidator();
        DTDDVFactoryImpl.fBuiltInTypes.put("IDREF", dvTemp);
        DTDDVFactoryImpl.fBuiltInTypes.put("IDREFS", new ListDatatypeValidator(dvTemp));
        dvTemp = new ENTITYDatatypeValidator();
        DTDDVFactoryImpl.fBuiltInTypes.put("ENTITY", new ENTITYDatatypeValidator());
        DTDDVFactoryImpl.fBuiltInTypes.put("ENTITIES", new ListDatatypeValidator(dvTemp));
        DTDDVFactoryImpl.fBuiltInTypes.put("NOTATION", new NOTATIONDatatypeValidator());
        dvTemp = new NMTOKENDatatypeValidator();
        DTDDVFactoryImpl.fBuiltInTypes.put("NMTOKEN", dvTemp);
        DTDDVFactoryImpl.fBuiltInTypes.put("NMTOKENS", new ListDatatypeValidator(dvTemp));
    }
    
    static {
        DTDDVFactoryImpl.fBuiltInTypes = new Hashtable();
        createBuiltInTypes();
    }
}
