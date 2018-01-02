// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion.mmrdescription;

import anon.infoservice.InfoServiceHolder;

public final class InfoServiceMMRListFetcher implements MMRListFetcher
{
    public byte[] getMMRList() {
        return InfoServiceHolder.getInstance().getMixminionNodesList();
    }
}
