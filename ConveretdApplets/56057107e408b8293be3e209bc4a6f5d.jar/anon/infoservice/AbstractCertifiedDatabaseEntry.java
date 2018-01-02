// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import logging.LogHolder;
import logging.LogType;
import anon.crypto.XMLSignature;
import anon.crypto.MultiCertPath;

public abstract class AbstractCertifiedDatabaseEntry extends AbstractDatabaseEntry implements ICertifiedDatabaseEntry
{
    public AbstractCertifiedDatabaseEntry(final long n) {
        super(n);
    }
    
    public abstract MultiCertPath getCertPath();
    
    public abstract XMLSignature getSignature();
    
    public abstract boolean isVerified();
    
    public boolean checkId() {
        final XMLSignature signature = this.getSignature();
        if (signature == null) {
            LogHolder.log(6, LogType.CRYPTO, "Signature is NULL!");
            return false;
        }
        return this.getId() != null && this.getId().equalsIgnoreCase(signature.getXORofSKIs());
    }
    
    public abstract boolean isValid();
}
