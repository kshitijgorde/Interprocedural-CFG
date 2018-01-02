// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

public class InfoServiceIDEntry extends AbstractIDEntry
{
    private static final long EXPIRE_TIME = 43200000L;
    
    public InfoServiceIDEntry(final InfoServiceDBEntry infoServiceDBEntry) {
        super(infoServiceDBEntry, infoServiceDBEntry.getLastUpdate() + 43200000L);
    }
}
