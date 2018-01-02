// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import jagoclient.Global;
import java.awt.Frame;

public class LocalGoFrame extends GoFrame
{
    public LocalGoFrame(final Frame frame, final String s) {
        super(frame, s);
    }
    
    public void doclose() {
        super.doclose();
        Global.writeparamter(String.valueOf(Global.home()) + "go.cfg");
        if (!Global.useurl()) {
            System.exit(0);
        }
    }
}
