// 
// Decompiled by Procyon v0.5.30
// 

package DesignARoom;

import java.awt.Rectangle;

public class AppWebCanvas extends WebCanvas
{
    AppWebApplet aWA;
    
    public AppWebCanvas(final AppWebApplet appWebApplet, final int apWidth, final int apHeight) {
        this.aWA = appWebApplet;
        super.wA = appWebApplet;
        super.apHeight = apHeight;
        super.apWidth = apWidth;
        super.apRmHeight = super.apWidth;
        super.apDivHeight = 30;
        super.apStTop = super.apRmHeight + super.apDivHeight;
        super.boundaryRect = new Rectangle(0, super.apStTop, super.apWidth - 1, super.apHeight - super.apStTop - 1);
        Webfurn.receiveConstants(super.apHeight, super.apWidth, super.apRmHeight, super.apDivHeight, super.apStTop, super.wA);
        super.furnTop = super.apStTop + 2;
    }
    
    public void decideSave() {
        this.aWA.decideSave();
    }
    
    public void updateOrigVectors(final int n) {
        this.aWA.updateOrigVectors(n);
    }
    
    public int getApWidth() {
        return super.apWidth;
    }
    
    public int getApHeight() {
        return super.apHeight;
    }
    
    public void resetSize(final int apWidth, final int apHeight) {
        super.apHeight = apHeight;
        super.apWidth = apWidth;
        super.apRmHeight = super.apWidth;
        super.apDivHeight = 35;
        super.apStTop = super.apRmHeight + super.apDivHeight;
        super.boundaryRect = new Rectangle(0, super.apStTop, super.apWidth - 1, super.apHeight - super.apStTop - 1);
        Webfurn.receiveConstants(super.apHeight, super.apWidth, super.apRmHeight, super.apDivHeight, super.apStTop, super.wA);
        super.furnTop = super.apStTop + 2;
        this.updateRoomSize(super.oW, super.oH, super.wU, super.hU);
        if (!AppWebApplet.aF.saveMI.isEnabled()) {
            this.aWA.updateOrigVectors(this.aWA.rmCurr.getSelectedIndex());
        }
        this.aWA.wtC.resize(super.roomWidth, 28);
        this.aWA.wtC.setWidth(super.roomWidth);
        this.aWA.wtC.setText(super.wA.rmName.getText());
    }
}
