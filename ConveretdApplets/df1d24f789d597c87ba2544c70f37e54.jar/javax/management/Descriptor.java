// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import java.io.Serializable;

public interface Descriptor extends Serializable, Cloneable
{
    Object getFieldValue(final String p0) throws RuntimeOperationsException;
    
    void setField(final String p0, final Object p1) throws RuntimeOperationsException;
    
    String[] getFields();
    
    String[] getFieldNames();
    
    Object[] getFieldValues(final String[] p0);
    
    void removeField(final String p0);
    
    void setFields(final String[] p0, final Object[] p1) throws RuntimeOperationsException;
    
    Object clone() throws RuntimeOperationsException;
    
    boolean isValid() throws RuntimeOperationsException;
}
