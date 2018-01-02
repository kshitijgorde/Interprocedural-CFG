// 
// Decompiled by Procyon v0.5.30
// 

package jstella.core;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PageAccess implements Serializable
{
    private static final long serialVersionUID = -6487146100140974640L;
    private int myDirectPeekBaseIndex;
    private int myDirectPokeBaseIndex;
    private int[] myDirectPeekMemory;
    private int[] myDirectPokeMemory;
    private IfcDevice myDevice;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public PageAccess(final IfcDevice aDevice) {
        this.myDirectPeekBaseIndex = 0;
        this.myDirectPokeBaseIndex = 0;
        this.myDirectPeekMemory = null;
        this.myDirectPokeMemory = null;
        this.myDevice = null;
        this.setDevice(aDevice);
    }
    
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
    
    public static PageAccess createDirectPeekAccess(final IfcDevice aDevice, final int[] aDirectPeekMemory, final int aDirectPeekBaseIndex) {
        final PageAccess zReturn = new PageAccess(aDevice);
        zReturn.setDirectPeekMemory(aDirectPeekMemory, aDirectPeekBaseIndex);
        return zReturn;
    }
    
    public static PageAccess createDirectPokeAccess(final IfcDevice aDevice, final int[] aDirectPokeMemory, final int aDirectPokeBaseIndex) {
        final PageAccess zReturn = new PageAccess(aDevice);
        zReturn.setDirectPokeMemory(aDirectPokeMemory, aDirectPokeBaseIndex);
        return zReturn;
    }
    
    public static PageAccess createIndirectAccess(final IfcDevice aDevice) {
        final PageAccess zReturn = new PageAccess(aDevice);
        zReturn.setIndirectMode();
        return zReturn;
    }
    
    public void copyDataFrom(final PageAccess aPA) {
        this.myDevice = aPA.myDevice;
        this.myDirectPeekMemory = aPA.myDirectPeekMemory;
        this.myDirectPokeMemory = aPA.myDirectPokeMemory;
        this.myDirectPeekBaseIndex = aPA.myDirectPeekBaseIndex;
        this.myDirectPokeBaseIndex = aPA.myDirectPokeBaseIndex;
    }
    
    public boolean usesDirectPeek() {
        return this.myDirectPeekMemory != null;
    }
    
    public boolean usesDirectPoke() {
        return this.myDirectPokeMemory != null;
    }
    
    public void directPoke(final char aPageOffset, final int aByteValue) {
        assert aByteValue >= 0 && aByteValue < 256;
        assert this.myDirectPokeMemory != null;
        this.myDirectPokeMemory[this.myDirectPokeBaseIndex + aPageOffset] = aByteValue;
    }
    
    public int directPeek(final char aPageOffset) {
        int zReturn = 0;
        assert this.myDirectPeekMemory != null;
        zReturn = this.myDirectPeekMemory[this.myDirectPeekBaseIndex + aPageOffset];
        if (zReturn < 0 && !PageAccess.$assertionsDisabled) {
            throw new AssertionError();
        }
        assert zReturn >= 0 && zReturn < 256;
        return zReturn;
    }
    
    public void setDevice(final IfcDevice aDevice) {
        this.myDevice = aDevice;
    }
    
    public IfcDevice getDevice() {
        return this.myDevice;
    }
    
    public void pagePoke(final char aAddress, final int aValue) {
        this.directPoke(aAddress, aValue);
    }
    
    public int pagePeek(final char aAddress) {
        return this.directPeek(aAddress);
    }
    
    public int peek(final int aAddress) {
        if (this.usesDirectPeek()) {
            return this.directPeek((char)(aAddress & 0x3F));
        }
        return this.getDevice().peek((char)aAddress);
    }
    
    public void poke(final int aAddress, final int aByteValue) {
        if (this.usesDirectPoke()) {
            this.directPoke((char)(aAddress & 0x3F), aByteValue);
        }
        else {
            this.getDevice().poke((char)aAddress, aByteValue);
        }
    }
    
    public void setIndirectMode() {
        this.setDirectPeekMemory(null, 0);
        this.setDirectPokeMemory(null, 0);
    }
    
    public int[] getDirectPeekMemory() {
        return this.myDirectPeekMemory;
    }
    
    public void setDirectPeekMemory(final int[] aDirectPeekMemory, final int aDirectPeekBaseIndex) {
        this.myDirectPeekMemory = aDirectPeekMemory;
        this.myDirectPeekBaseIndex = aDirectPeekBaseIndex;
    }
    
    public int[] getDirectPokeMemory() {
        return this.myDirectPokeMemory;
    }
    
    public void setDirectPokeMemory(final int[] aDirectPokeMemory, final int aDirectPokeBaseIndex) {
        this.myDirectPokeMemory = aDirectPokeMemory;
        this.myDirectPokeBaseIndex = aDirectPokeBaseIndex;
    }
}
