// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

public interface NFDataBeanFactory
{
    String getNameForParam(final String p0);
    
    String getNameForKeyword(final String p0);
    
    NFDataBean createDataBean(final String p0);
}
