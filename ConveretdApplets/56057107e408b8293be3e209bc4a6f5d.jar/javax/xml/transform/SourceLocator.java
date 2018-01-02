// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.transform;

public interface SourceLocator
{
    String getPublicId();
    
    String getSystemId();
    
    int getLineNumber();
    
    int getColumnNumber();
}
