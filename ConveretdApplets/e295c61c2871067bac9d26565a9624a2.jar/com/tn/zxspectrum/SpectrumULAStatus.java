// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zxspectrum;

import com.tn.components.ComponentStatus;

public class SpectrumULAStatus extends ComponentStatus
{
    private int ivPortFE;
    private int ivPort1FFD;
    private int ivPort7FFD;
    private int ivCycle;
    
    public int getCycle() {
        return this.ivCycle;
    }
    
    public int getPort1FFD() {
        return this.ivPort1FFD;
    }
    
    public int getPort7FFD() {
        return this.ivPort7FFD;
    }
    
    public int getPortFE() {
        return this.ivPortFE;
    }
    
    public void setCycle(final int pCycle) {
        this.ivCycle = pCycle;
    }
    
    public void setPort1FFD(final int pPort1FFD) {
        this.ivPort1FFD = pPort1FFD;
    }
    
    public void setPort7FFD(final int pPort7FFD) {
        this.ivPort7FFD = pPort7FFD;
    }
    
    public void setPortFE(final int pPortFE) {
        this.ivPortFE = pPortFE;
    }
}
