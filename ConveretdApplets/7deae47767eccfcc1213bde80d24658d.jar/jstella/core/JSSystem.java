// 
// Decompiled by Procyon v0.5.30
// 

package jstella.core;

import java.util.Iterator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import jstella.j6507.J6507;
import java.util.List;
import java.io.Serializable;
import jstella.j6507.IfcSystem;

public class JSSystem implements IfcSystem, Serializable
{
    private static final long serialVersionUID = 258470027807684384L;
    private PageAccess[] myPageAccessTable;
    private List<IfcDevice> myDeviceList;
    private int myNumberOfDevices;
    private J6507 myCPU;
    private JSConsole myConsole;
    private int myCycles;
    private NullDevice myNullDevice;
    private int myDataBusState;
    private boolean myDataBusLocked;
    
    private static final char pageOffset(final char aAddress) {
        return (char)(aAddress & '?');
    }
    
    public JSSystem(final JSConsole aConsole) {
        this.myPageAccessTable = new PageAccess[128];
        this.myDeviceList = new ArrayList<IfcDevice>();
        this.myNumberOfDevices = 0;
        this.myCPU = null;
        this.myConsole = null;
        this.myCycles = 0;
        this.myNullDevice = new NullDevice();
        this.myDataBusState = 0;
        this.myDataBusLocked = false;
        this.myConsole = aConsole;
        this.clearPageAccesses();
        this.myDataBusLocked = false;
        this.attach(new J6507(this));
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
    
    public void processorCycle(final int aCyclesElapsed) {
        this.incrementCycles(aCyclesElapsed);
    }
    
    public int getCycles() {
        return this.myCycles;
    }
    
    public void incrementCycles(final int amount) {
        this.myCycles += amount;
    }
    
    public int getDataBusState() {
        return this.myDataBusState;
    }
    
    public void stopCPU() {
        this.getCPU().stop();
    }
    
    public J6507 getCPU() {
        return this.myCPU;
    }
    
    private NullDevice getNullDevice() {
        return this.myNullDevice;
    }
    
    public void clearPageAccesses() {
        final PageAccess access = new PageAccess(this.myNullDevice);
        for (int page = 0; page < 128; ++page) {
            this.myPageAccessTable[page] = new PageAccess(this.myNullDevice);
            this.setPageAccess((char)page, access);
        }
    }
    
    public int executeCPU(final int aInstructionCount) throws JSException {
        int zReturn = 0;
        try {
            zReturn = this.myCPU.execute(aInstructionCount);
        }
        catch (J6507.J6507Exception e) {
            if (e.myExceptionType == J6507.J6507Exception.ExceptionType.INSTRUCTION_NOT_RECOGNIZED) {
                throw new JSException(JSException.ExceptionType.INSTRUCTION_NOT_RECOGNIZED, e.myMessage);
            }
        }
        return zReturn;
    }
    
    public void reset() {
        this.resetCycles();
        for (final IfcDevice zDev : this.myDeviceList) {
            zDev.reset();
        }
        if (this.myCPU != null) {
            this.myCPU.reset();
        }
    }
    
    public void attach(final IfcDevice aDevice) {
        assert this.myNumberOfDevices < 100;
        if (!this.myDeviceList.contains(aDevice)) {
            this.myDeviceList.add(aDevice);
        }
        aDevice.install(this);
    }
    
    public void unattach(final IfcDevice aDevice) {
        boolean zFound = false;
        do {
            zFound = this.myDeviceList.remove(aDevice);
        } while (zFound);
        final PageAccess zPA = new PageAccess(this.getNullDevice());
        for (int i = 0; i < this.myPageAccessTable.length; ++i) {
            if (aDevice.equals(this.myPageAccessTable[i].getDevice())) {
                this.setPageAccess(i, zPA);
            }
        }
    }
    
    public void attach(final J6507 a6507) {
        (this.myCPU = a6507).install(this);
    }
    
    public void resetCycles() {
        for (final IfcDevice zDev : this.myDeviceList) {
            zDev.systemCyclesReset();
        }
        this.myCycles = 0;
    }
    
    public void setPageAccess(final int page, final PageAccess access) {
        assert page <= 128;
        assert access.getDevice() != null;
        this.myPageAccessTable[page].copyDataFrom(access);
    }
    
    public PageAccess getPageAccess(final int page) {
        assert page <= 128;
        return this.myPageAccessTable[page];
    }
    
    public int peek(final int addr) {
        assert addr >= 0;
        int result = 0;
        result = this.pageAccessAtAddress(addr).peek(addr);
        return this.myDataBusState = result;
    }
    
    public void poke(final int addr, final int aByteValue) {
        assert aByteValue >= 0 && aByteValue < 256;
        this.pageAccessAtAddress(addr).poke(addr, aByteValue);
        this.myDataBusState = aByteValue;
    }
    
    private PageAccess pageAccessAtAddress(final int aAddress) {
        return this.myPageAccessTable[(aAddress & 0x1FFF) >>> 6];
    }
    
    public char getResetPC() {
        final int zReturn = this.peek(65532) | this.peek(65533) << 8;
        return (char)zReturn;
    }
}
