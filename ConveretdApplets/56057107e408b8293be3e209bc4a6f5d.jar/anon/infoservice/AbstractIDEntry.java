// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

public abstract class AbstractIDEntry extends AbstractDatabaseEntry
{
    private long m_lastUpdate;
    private long m_versionNumber;
    private String m_id;
    
    public AbstractIDEntry(final AbstractDatabaseEntry abstractDatabaseEntry, final long n) {
        super(n);
        if (abstractDatabaseEntry == null) {
            throw new IllegalArgumentException("No database entry given.");
        }
        this.m_lastUpdate = abstractDatabaseEntry.getLastUpdate();
        this.m_versionNumber = abstractDatabaseEntry.getVersionNumber();
        this.m_id = abstractDatabaseEntry.getId();
    }
    
    public long getLastUpdate() {
        return this.m_lastUpdate;
    }
    
    public long getVersionNumber() {
        return this.m_versionNumber;
    }
    
    public String getId() {
        return this.m_id;
    }
}
