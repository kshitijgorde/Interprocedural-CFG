// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

public class NewCascadeIDEntry extends AbstractCascadeIDEntry
{
    private static final long EXPIRE_TIME = 43200000L;
    
    public NewCascadeIDEntry(final CascadeIDEntry cascadeIDEntry) {
        super(cascadeIDEntry, System.currentTimeMillis() + 43200000L);
    }
}
