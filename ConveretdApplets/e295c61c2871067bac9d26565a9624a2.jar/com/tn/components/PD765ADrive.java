// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.components;

public class PD765ADrive
{
    private PD765ADisk ivDisk;
    private int ivTrack;
    
    public PD765ADrive() {
        this.ivDisk = null;
        this.ivTrack = 0;
    }
    
    public boolean containsDisk() {
        return this.ivDisk != null;
    }
    
    public void deleteSector(final int pHead, final int pIndex) {
        if (this.ivDisk != null) {
            this.ivDisk.setSector(this.ivTrack, pHead, pIndex, null);
        }
    }
    
    public PD765ADriveStatus getStatus() {
        final PD765ADriveStatus result = new PD765ADriveStatus();
        result.ivDisk = this.ivDisk;
        result.ivTrack = this.ivTrack;
        return result;
    }
    
    public int getTrack() {
        return this.ivTrack;
    }
    
    public boolean getWriteProtect() {
        return this.ivDisk != null && this.ivDisk.isWriteProtected();
    }
    
    public void init() {
        this.ivTrack = 0;
    }
    
    public void insertDisk(final PD765ADisk pDisk) {
        this.ivDisk = pDisk;
    }
    
    public PD765ASector readSector(final int pHead, final int pIndex) {
        if (this.ivDisk != null) {
            return this.ivDisk.getSector(this.ivTrack, pHead, pIndex);
        }
        return null;
    }
    
    public PD765ADisk removeDisk() {
        final PD765ADisk disk = this.ivDisk;
        this.ivDisk = null;
        return disk;
    }
    
    public void setStatus(final PD765ADriveStatus pStatus) {
        this.ivTrack = pStatus.ivTrack;
        this.ivDisk = pStatus.ivDisk;
    }
    
    public void setTrack(final int pTrack) {
        this.ivTrack = pTrack;
    }
    
    public void writeSector(final int pHead, final int pIndex, final PD765ASector pSector) {
        if (this.ivDisk != null) {
            this.ivDisk.setSector(this.ivTrack, pHead, pIndex, pSector);
        }
    }
}
