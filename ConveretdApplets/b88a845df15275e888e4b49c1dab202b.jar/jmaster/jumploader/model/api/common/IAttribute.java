// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.api.common;

public interface IAttribute
{
    String getName();
    
    Object getValue();
    
    void setValue(final Object p0);
    
    String getStringValue();
    
    void setStringValue(final String p0);
    
    boolean isSendToServer();
    
    void setSendToServer(final boolean p0);
}
