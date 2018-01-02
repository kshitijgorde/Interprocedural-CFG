// 
// Decompiled by Procyon v0.5.30
// 

package myspeedserver.applet;

import java.awt.Graphics;
import javax.swing.JPanel;

class myspeed_TX$MySpeedContentPane extends JPanel
{
    final myspeed_TX RU;
    
    private myspeed_TX$MySpeedContentPane(final myspeed_TX ru) {
        this.RU = ru;
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final String un = this.RU.un(graphics, myspeed_TX.access$0(this.RU));
        if (un != "" && un != myspeed_TX.access$0(this.RU)[3]) {
            myspeed_TX.access$1(this.RU);
            return;
        }
        if (myspeed_TX.access$2(this.RU, graphics)) {
            myspeed_TX.access$1(this.RU);
            return;
        }
        if (myspeed_TX.access$3(this.RU, graphics)) {
            myspeed_TX.access$1(this.RU);
            return;
        }
        if (myspeed_TX.access$4(this.RU, graphics)) {
            myspeed_TX.access$1(this.RU);
            return;
        }
        myspeed_TX.access$7(this.RU, (myspeed_TX.access$5(this.RU) != null && myspeed_TX.access$5(this.RU).length > 1) || "no".equals(this.RU.RC(myspeed_TX.access$6("hideonetab"))));
        if (myspeed_TX.access$8(this.RU)) {
            myspeed_TX.access$9(this.RU, graphics);
        }
    }
    
    myspeed_TX$MySpeedContentPane(final myspeed_TX myspeed_TX, final myspeed_TX$MySpeedContentPane myspeed_TX$MySpeedContentPane) {
        this(myspeed_TX);
    }
}
