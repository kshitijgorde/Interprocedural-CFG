// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import anon.crypto.XMLSignature;
import anon.crypto.MultiCertPath;

public interface ICertifiedDatabaseEntry
{
    MultiCertPath getCertPath();
    
    XMLSignature getSignature();
    
    boolean isVerified();
    
    boolean isValid();
    
    boolean checkId();
}
