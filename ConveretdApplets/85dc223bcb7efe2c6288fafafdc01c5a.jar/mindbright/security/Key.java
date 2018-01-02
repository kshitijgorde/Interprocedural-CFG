// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.security;

import java.io.Serializable;

public interface Key extends Serializable
{
    String getAlgorithm();
    
    String getFormat();
    
    byte[] getEncoded();
}
