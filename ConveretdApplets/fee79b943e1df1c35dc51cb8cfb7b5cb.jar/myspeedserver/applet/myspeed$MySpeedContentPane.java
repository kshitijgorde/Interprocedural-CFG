// 
// Decompiled by Procyon v0.5.30
// 

package myspeedserver.applet;

import java.awt.Graphics;
import javax.swing.JPanel;

class myspeed$MySpeedContentPane extends JPanel
{
    final myspeed RU;
    
    private myspeed$MySpeedContentPane(final myspeed ru) {
        this.RU = ru;
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final String un = this.RU.un(graphics, myspeed.access$0(this.RU));
        if (un != "" && un != myspeed.access$0(this.RU)[3]) {
            myspeed.access$1(this.RU);
            return;
        }
        if (myspeed.access$2(this.RU, graphics)) {
            myspeed.access$1(this.RU);
            return;
        }
        if (myspeed.access$3(this.RU, graphics)) {
            myspeed.access$1(this.RU);
            return;
        }
        if (myspeed.access$4(this.RU, graphics)) {
            myspeed.access$1(this.RU);
            return;
        }
        myspeed.access$7(this.RU, (myspeed.access$5(this.RU) != null && myspeed.access$5(this.RU).length > 1) || "no".equals(this.RU.RC(myspeed.access$6("^]baWXaRed"))));
        if (myspeed.access$8(this.RU)) {
            myspeed.access$9(this.RU, graphics);
        }
    }
    
    myspeed$MySpeedContentPane(final myspeed myspeed, final myspeed$MySpeedContentPane myspeed$MySpeedContentPane) {
        this(myspeed);
    }
}
