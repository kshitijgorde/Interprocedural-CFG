// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zxspectrum;

import com.tn.z80.Z80Status;
import com.tn.components.PD765AStatus;
import com.tn.components.PD765ADriveStatus;
import com.tn.components.MemoryStatus;
import com.tn.components.AY38912Status;
import com.tn.components.ComponentStatus;

public class SpectrumStatus extends ComponentStatus
{
    public static final int COMPONENT_Z80 = 1;
    public static final int COMPONENT_ULA = 13;
    public static final int COMPONENT_RAM4000 = 10;
    public static final int COMPONENT_RAM8000 = 11;
    public static final int COMPONENT_RAMC000 = 12;
    public static final int COMPONENT_RAM0 = 2;
    public static final int COMPONENT_RAM1 = 3;
    public static final int COMPONENT_RAM2 = 4;
    public static final int COMPONENT_RAM3 = 5;
    public static final int COMPONENT_RAM4 = 6;
    public static final int COMPONENT_RAM5 = 7;
    public static final int COMPONENT_RAM6 = 8;
    public static final int COMPONENT_RAM7 = 9;
    public static final int COMPONENT_AY38912 = 14;
    public static final int COMPONENT_RAM8 = 100;
    public static final int COMPONENT_RAM9 = 101;
    public static final int COMPONENT_RAMA = 102;
    public static final int COMPONENT_RAMB = 103;
    public static final int COMPONENT_RAMC = 104;
    public static final int COMPONENT_RAMD = 105;
    public static final int COMPONENT_RAME = 106;
    public static final int COMPONENT_RAMF = 107;
    public static final int COMPONENT_PD765A = 300;
    public static final int COMPONENT_P3DRIVE = 301;
    public static final int COMPONENT_TYPE_SPECTRUM_16K = 15;
    public static final int COMPONENT_TYPE_SPECTRUM_48K = 16;
    public static final int COMPONENT_TYPE_SPECTRUM_128K = 17;
    public static final int COMPONENT_TYPE_SPECTRUM_128K_P2 = 18;
    public static final int COMPONENT_TYPE_SPECTRUM_128K_P3 = 19;
    public static final int COMPONENT_TYPE_SCORPION_256K = 200;
    public static final int COMPONENT_TYPE_PENTAGON_256K = 201;
    
    public AY38912Status getAY38912Status() {
        return (AY38912Status)this.getSubComponentStatus(14);
    }
    
    public MemoryStatus getMemoryStatus(final int pMemoryId) {
        return (MemoryStatus)this.getSubComponentStatus(pMemoryId);
    }
    
    public PD765ADriveStatus getPD765ADriveStatus() {
        return (PD765ADriveStatus)this.getSubComponentStatus(301);
    }
    
    public PD765AStatus getPD765AStatus() {
        return (PD765AStatus)this.getSubComponentStatus(300);
    }
    
    public SpectrumULAStatus getULAStatus() {
        return (SpectrumULAStatus)this.getSubComponentStatus(13);
    }
    
    public Z80Status getZ80Status() {
        return (Z80Status)this.getSubComponentStatus(1);
    }
    
    public void setAY38912Status(final AY38912Status pStatus) {
        this.setSubComponentStatus(14, pStatus);
    }
    
    public void setMemoryStatus(final int pMemoryId, final MemoryStatus pStatus) {
        this.setSubComponentStatus(pMemoryId, pStatus);
    }
    
    public void setPD765ADriveStatus(final PD765ADriveStatus pStatus) {
        this.setSubComponentStatus(301, pStatus);
    }
    
    public void setPD765AStatus(final PD765AStatus pStatus) {
        this.setSubComponentStatus(300, pStatus);
    }
    
    public void setULAStatus(final SpectrumULAStatus pStatus) {
        this.setSubComponentStatus(13, pStatus);
    }
    
    public void setZ80Status(final Z80Status pStatus) {
        this.setSubComponentStatus(1, pStatus);
    }
}
