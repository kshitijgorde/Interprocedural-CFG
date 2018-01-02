// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

import java.awt.Container;
import gjt.Util;
import java.awt.Component;

public class LoadFrame implements Runnable
{
    MudApplet ma;
    private static int lfno;
    
    public LoadFrame(final MudApplet ma) {
        this.ma = ma;
        final Thread exec = new Thread(this, "LoadFrame " + LoadFrame.lfno++);
        exec.setDaemon(true);
        exec.start();
    }
    
    static {
        LoadFrame.lfno = 0;
    }
    
    public void run() {
        final MudFrame mf = new MudFrame(this.ma, this.ma.cb_string);
        this.ma.add("Center", mf);
        (this.ma.mudframe = mf).requestFocus();
        mf.showshield = !this.ma.getParameter("Shield", "True").equals("False");
        mf.showcompass = !this.ma.getParameter("Compass", "True").equals("False");
        Util.reallyWaitForImage(this.ma, mf.mainImage);
        this.ma.showStatus("");
        this.ma.cl.show(this.ma, "Center");
        if (!this.ma.userinput) {
            mf.cbutton();
        }
    }
}
