// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.components;

import java.util.Hashtable;

public class PD765ADiskImpl implements PD765ADisk
{
    private Hashtable ivSectors;
    private boolean ivWriteProtected;
    
    public PD765ADiskImpl() {
        this.ivSectors = new Hashtable();
        this.ivWriteProtected = false;
    }
    
    @Override
    public PD765ASector getSector(final int pTrack, final int pHead, final int pIndex) {
        return this.ivSectors.get(getSectorKey(pTrack, pHead, pIndex));
    }
    
    private static Integer getSectorKey(final int pTrack, final int pHead, final int pIndex) {
        return new Integer(pTrack << 16 | pHead << 8 | pIndex);
    }
    
    @Override
    public boolean isWriteProtected() {
        return this.ivWriteProtected;
    }
    
    @Override
    public void setSector(final int pTrack, final int pHead, final int pIndex, final PD765ASector pSector) {
        if (pSector != null) {
            this.ivSectors.put(getSectorKey(pTrack, pHead, pIndex), pSector);
        }
        else {
            this.ivSectors.remove(getSectorKey(pTrack, pHead, pIndex));
        }
    }
    
    public void setWriteProtected(final boolean pWriteProtected) {
        this.ivWriteProtected = pWriteProtected;
    }
}
