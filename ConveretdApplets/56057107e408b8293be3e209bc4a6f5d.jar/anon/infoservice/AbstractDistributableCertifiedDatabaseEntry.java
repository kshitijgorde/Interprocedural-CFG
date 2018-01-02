// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import anon.crypto.MultiCertPath;
import logging.LogHolder;
import logging.LogType;
import anon.crypto.XMLSignature;

public abstract class AbstractDistributableCertifiedDatabaseEntry extends AbstractDistributableDatabaseEntry implements ICertifiedDatabaseEntry
{
    public AbstractDistributableCertifiedDatabaseEntry(final long n) {
        super(n);
    }
    
    public abstract XMLSignature getSignature();
    
    public abstract boolean isVerified();
    
    public boolean checkId() {
        final XMLSignature signature = this.getSignature();
        if (signature == null) {
            LogHolder.log(6, LogType.CRYPTO, "AbstractDistributableCertifiedDatabaseEntry.checkId() -- Signature is NULL!");
            return false;
        }
        return this.getId() != null && this.getId().equalsIgnoreCase(signature.getXORofSKIs());
    }
    
    public abstract boolean isValid();
    
    public abstract MultiCertPath getCertPath();
}
