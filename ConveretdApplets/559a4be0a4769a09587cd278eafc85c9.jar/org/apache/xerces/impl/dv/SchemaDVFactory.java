// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv;

import org.apache.xerces.util.SymbolHash;
import org.apache.xerces.util.ObjectFactory;

public abstract class SchemaDVFactory
{
    private static final String DEFAULT_FACTORY_CLASS = "org.apache.xerces.impl.dv.xs.SchemaDVFactoryImpl";
    
    public static final synchronized SchemaDVFactory getInstance() throws DVFactoryException {
        return getInstance("org.apache.xerces.impl.dv.xs.SchemaDVFactoryImpl");
    }
    
    public static final synchronized SchemaDVFactory getInstance(final String factoryClass) throws DVFactoryException {
        try {
            return (SchemaDVFactory)ObjectFactory.newInstance(factoryClass, ObjectFactory.findClassLoader(), true);
        }
        catch (ClassCastException e4) {
            throw new DVFactoryException("Schema factory class " + factoryClass + " does not extend from SchemaDVFactory.");
        }
    }
    
    public abstract XSSimpleType getBuiltInType(final String p0);
    
    public abstract SymbolHash getBuiltInTypes();
    
    public abstract XSSimpleType createTypeRestriction(final String p0, final String p1, final short p2, final XSSimpleType p3);
    
    public abstract XSSimpleType createTypeList(final String p0, final String p1, final short p2, final XSSimpleType p3);
    
    public abstract XSSimpleType createTypeUnion(final String p0, final String p1, final short p2, final XSSimpleType[] p3);
}
