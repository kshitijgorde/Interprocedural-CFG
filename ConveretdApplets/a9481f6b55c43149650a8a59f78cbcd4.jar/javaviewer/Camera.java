// 
// Decompiled by Procyon v0.5.30
// 

package javaviewer;

import java.util.Vector;
import java.awt.Component;

public class Camera extends Component
{
    protected Viewer parent;
    protected long waitTime;
    protected long controlTime;
    protected String focusMode;
    protected String response;
    protected String dZoom;
    protected String eFlip;
    protected Vector presetPositionNo;
    protected Vector presetPositionName;
    protected int zoomPos;
    protected int panPos;
    protected int tiltPos;
    public boolean ptzfMode;
    public String relPanTilt;
    public String relZoom;
    
    public Camera(final Viewer parent) {
        this.waitTime = 0L;
        this.controlTime = Long.MAX_VALUE;
        this.zoomPos = 0;
        this.panPos = 0;
        this.tiltPos = 0;
        this.parent = parent;
        final String commandSR = parent.commandSR("/command/inquiry.cgi", "inq=camera", true);
        commandSR.length();
        final int n = commandSR.indexOf("ZoomMode=") + 9;
        if (n > 8 && commandSR.substring(n, n + 7).equals("optical")) {
            this.dZoom = "optical";
        }
        else {
            this.dZoom = "full";
        }
        final int n2 = commandSR.indexOf("FocusMode=") + 10;
        if (n2 > 9 && commandSR.substring(n2, n2 + 6).equals("manual")) {
            this.focusMode = "manual";
        }
        else {
            this.focusMode = "auto";
        }
        final int index = commandSR.indexOf("Eflip=");
        if (index >= 0) {
            int n3 = commandSR.indexOf("&", index);
            if (n3 < 0) {
                n3 = commandSR.length();
            }
            this.eFlip = commandSR.substring(index + 6, n3);
        }
        final int n4 = commandSR.indexOf("PtzfMode=") + 9;
        if (n4 > 8) {
            if (commandSR.substring(n4, n4 + 4).equals("step")) {
                this.ptzfMode = true;
            }
            else {
                this.ptzfMode = false;
            }
        }
        final int n5 = commandSR.indexOf("RelPanTilt=") + 11;
        if (n5 > 10) {
            final int index2 = commandSR.indexOf("&", n5 + 1);
            if (index2 > 0) {
                this.relPanTilt = commandSR.substring(n5, index2);
                if (this.relPanTilt.length() == 1) {
                    this.relPanTilt = "0" + this.relPanTilt;
                }
            }
        }
        final int n6 = commandSR.indexOf("RelZoom=") + 8;
        if (n6 > 7) {
            final int index3 = commandSR.indexOf("&", n6 + 1);
            if (index3 > 0) {
                this.relZoom = commandSR.substring(n6, index3);
                if (this.relZoom.length() == 1) {
                    this.relZoom = "0" + this.relZoom;
                }
            }
        }
        this.presetPositionNo = new Vector();
        this.presetPositionName = new Vector();
    }
    
    private String _$911(final String s) {
        if (s.length() < 2) {
            return "0" + s;
        }
        return s;
    }
    
    public int getControl() {
        this.response = this.parent.commandSR("/command/ptzfctrlright/inquiry.cgi", "inq=dummy", false);
        this.response = this.parent.commandSR("/command/ptzfctrlright/inquiry.cgi", "inq=time", true);
        this.waitTime = Integer.valueOf(this.response.substring(this.response.indexOf("=") + 1, this.response.indexOf("&")));
        this.controlTime = Integer.valueOf(this.response.substring(this.response.indexOf("=") + 1).substring(this.response.substring(this.response.indexOf("=") + 1).indexOf("=") + 1));
        if (this.waitTime == 0L && this.controlTime == -1L) {
            return 0;
        }
        if (this.waitTime != -1L) {
            return 1;
        }
        if (this.controlTime == -1L) {
            return -2;
        }
        return -1;
    }
    
    public long getWaitTimeToControl() {
        return this.waitTime;
    }
    
    public long getControlTime() {
        return this.controlTime;
    }
    
    public int loadPresetPosition() {
        String substring = "";
        String substring2 = "";
        final String commandSR = this.parent.commandSR("/command/inquiry.cgi", "inq=presetposition", true);
        int i = commandSR.indexOf("PresetName=") + 11;
        if (i < 11) {
            return 0;
        }
        boolean b = true;
        while (i < commandSR.length()) {
            int n = commandSR.indexOf(",", i);
            if (n < 0) {
                n = commandSR.length();
            }
            if (b) {
                substring = commandSR.substring(i, n);
            }
            else {
                substring2 = commandSR.substring(i, n);
            }
            b = !b;
            if (b) {
                this.presetPositionNo.addElement(substring);
                this.presetPositionName.addElement(substring2);
            }
            i = n + 1;
        }
        return 0;
    }
    
    public String getPresetPositionNo(final int n) {
        return this.presetPositionNo.elementAt(n);
    }
    
    public String getPresetPositionName(final int n) {
        return this.presetPositionName.elementAt(n);
    }
    
    public int getPresetPositionNumber() {
        return this.presetPositionNo.size();
    }
    
    public int getPresetPositionSearchByName(final String s) {
        return this.presetPositionName.indexOf(s);
    }
    
    public void setPresetPosition(final String s) {
        synchronized (this.parent.commandSend) {
            this.parent.commandSend.setCommand("/command/presetposition.cgi", "PresetCall=" + s);
            this.parent.commandSend.notify();
        }
        if (this.response == null) {
            return;
        }
        if (this.response.indexOf("PresetName=") < 0) {
            return;
        }
    }
    
    public int toInt(final String s) {
        final char[] charArray = s.toCharArray();
        int digit = Character.digit(charArray[0], 16);
        if (digit >= 8) {
            digit -= 16;
        }
        for (int i = 1; i < s.length(); ++i) {
            digit = digit * 16 + Character.digit(charArray[i], 16);
        }
        return digit;
    }
    
    public void notifyCameraPos(final String s) {
        if (s.length() >= 15) {
            if (s.substring(0, 1).equals("1")) {
                final int int1 = this.toInt(s.substring(3, 7));
                if (this.panPos != int1) {
                    this.parent.logger.print("pan position : " + String.valueOf(this.panPos) + "->" + String.valueOf(int1));
                    this.panPos = int1;
                    this.parent.imageThread.panoramaView.repaint();
                }
            }
            if (s.substring(1, 2).equals("1")) {
                final int int2 = this.toInt(s.substring(7, 11));
                if (this.tiltPos != int2) {
                    this.parent.logger.print("tilt position : " + String.valueOf(this.tiltPos) + "->" + String.valueOf(int2));
                    this.tiltPos = int2;
                    this.parent.imageThread.panoramaView.repaint();
                }
            }
            if (s.substring(2, 3).equals("1")) {
                final int int3 = this.toInt(s.substring(11, 15));
                if (this.zoomPos != int3) {
                    this.parent.logger.print("zoom position : " + String.valueOf(this.zoomPos) + "->" + String.valueOf(int3));
                    this.zoomPos = int3;
                    this.parent.imageThread.zoomControler.setZoomIndex(this.zoomPos);
                }
            }
        }
    }
    
    public int getZoomPos() {
        this.parent.logger.print("current zoom position : " + String.valueOf(this.zoomPos));
        return this.zoomPos;
    }
    
    public int getPanPos() {
        return this.panPos;
    }
    
    public int getTiltPos() {
        return this.tiltPos;
    }
    
    public String getFocusType() {
        return this.focusMode;
    }
    
    public String getZoomType() {
        return this.dZoom;
    }
    
    public String getEFlipStatus() {
        return this.eFlip;
    }
    
    public String toPTAddress(final int n) {
        final String hexString = Integer.toHexString(n);
        String s;
        if (n >= 0) {
            s = "0000" + hexString;
        }
        else {
            s = "ffff" + hexString;
        }
        return s.substring(s.length() - 4);
    }
}
