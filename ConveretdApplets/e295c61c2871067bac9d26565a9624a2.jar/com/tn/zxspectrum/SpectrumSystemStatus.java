// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zxspectrum;

import com.tn.zx.peripheral.TapFilePlayerStatus;
import com.tn.zx.peripheral.ZxPrinterStatus;
import com.tn.zx.peripheral.MicrodriveStatus;
import com.tn.zx.peripheral.KeyboardRobotStatus;
import com.tn.zx.peripheral.Interface1Status;
import com.tn.components.ComponentStatus;

public class SpectrumSystemStatus extends ComponentStatus
{
    public static final int COMPONENT_SPECTRUM = 1;
    public static final int COMPONENT_IF1 = 2;
    public static final int COMPONENT_MICRODRIVE1 = 3;
    public static final int COMPONENT_MICRODRIVE2 = 4;
    public static final int COMPONENT_MICRODRIVE3 = 5;
    public static final int COMPONENT_MICRODRIVE4 = 6;
    public static final int COMPONENT_MICRODRIVE5 = 7;
    public static final int COMPONENT_MICRODRIVE6 = 8;
    public static final int COMPONENT_MICRODRIVE7 = 9;
    public static final int COMPONENT_MICRODRIVE8 = 10;
    public static final int COMPONENT_JOYSTICK1 = 11;
    public static final int COMPONENT_JOYSTICK2 = 12;
    public static final int COMPONENT_PRINTER = 13;
    public static final int COMPONENT_TAP_FILE_PLAYER = 14;
    public static final int COMPONENT_KEYBOARD_ROBOT = 15;
    
    public Interface1Status getInterface1Status() {
        return (Interface1Status)this.getSubComponentStatus(2);
    }
    
    public KeyboardRobotStatus getKeyboardRobotStatus() {
        return (KeyboardRobotStatus)this.getSubComponentStatus(15);
    }
    
    public MicrodriveStatus getMicrodriveStatus(final int pMicrodriveId) {
        return (MicrodriveStatus)this.getSubComponentStatus(pMicrodriveId);
    }
    
    public ZxPrinterStatus getPrinterStatus() {
        return (ZxPrinterStatus)this.getSubComponentStatus(13);
    }
    
    public SpectrumStatus getSpectrumStatus() {
        return (SpectrumStatus)this.getSubComponentStatus(1);
    }
    
    public TapFilePlayerStatus getTapFilePlayerStatus() {
        return (TapFilePlayerStatus)this.getSubComponentStatus(14);
    }
    
    public void setInterface1Status(final Interface1Status pInterface1Status) {
        this.setSubComponentStatus(2, pInterface1Status);
    }
    
    public void setKeyboardRobotStatus(final KeyboardRobotStatus pKeyboardRobotStatus) {
        this.setSubComponentStatus(15, pKeyboardRobotStatus);
    }
    
    public void setMicrodriveStatus(final int pMicrodriveId, final MicrodriveStatus pMicrodriveStatus) {
        this.setSubComponentStatus(pMicrodriveId, pMicrodriveStatus);
    }
    
    public void setPrinterStatus(final ZxPrinterStatus pPrinterStatus) {
        this.setSubComponentStatus(13, pPrinterStatus);
    }
    
    public void setSpectrumStatus(final SpectrumStatus pSpectrumStatus) {
        this.setSubComponentStatus(1, pSpectrumStatus);
    }
    
    public void setTapFilePlayerStatus(final TapFilePlayerStatus pTapFilePlayerStatus) {
        this.setSubComponentStatus(14, pTapFilePlayerStatus);
    }
}
