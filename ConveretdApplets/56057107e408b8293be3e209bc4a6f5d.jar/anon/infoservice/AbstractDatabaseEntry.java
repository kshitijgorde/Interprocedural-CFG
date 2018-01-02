// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

public abstract class AbstractDatabaseEntry
{
    public static final String XML_LAST_UPDATE = "LastUpdate";
    public static final String XML_ATTR_LAST_UPDATE = "lastUpdate";
    private long m_expireTime;
    private long m_creationTime;
    
    public AbstractDatabaseEntry(final long expireTime) {
        this.m_expireTime = expireTime;
        this.m_creationTime = System.currentTimeMillis();
    }
    
    public final boolean isNewerThan(final AbstractDatabaseEntry abstractDatabaseEntry) {
        return abstractDatabaseEntry == null || this.getVersionNumber() > abstractDatabaseEntry.getVersionNumber() || (this.getLastUpdate() > abstractDatabaseEntry.getLastUpdate() && this.getVersionNumber() == abstractDatabaseEntry.getVersionNumber());
    }
    
    public boolean isUserDefined() {
        return false;
    }
    
    public boolean isPersistanceDeletionAllowed() {
        return false;
    }
    
    public void deletePersistence() {
    }
    
    public abstract String getId();
    
    public final long getExpireTime() {
        return this.m_expireTime;
    }
    
    public abstract long getLastUpdate();
    
    public final long getCreationTime() {
        return this.m_creationTime;
    }
    
    public final void resetCreationTime() {
        this.m_creationTime = System.currentTimeMillis();
    }
    
    public abstract long getVersionNumber();
}
