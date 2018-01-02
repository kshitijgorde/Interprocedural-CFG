// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import java.awt.Component;
import rene.gui.Global;
import java.awt.Frame;
import java.awt.Color;
import jagoclient.StopThread;

public class WoodPaint extends StopThread
{
    int W;
    int H;
    int Ox;
    int Oy;
    int D;
    Color C;
    Frame F;
    boolean Shadows;
    
    public WoodPaint(final Frame f) {
        this.F = f;
        this.setPriority(this.getPriority() - 1);
        this.start();
    }
    
    public void run() {
        EmptyPaint.createwood(this, this.F, Global.getParameter("sboardwidth", 0), Global.getParameter("sboardheight", 0), jagoclient.Global.getColor("boardcolor", 170, 120, 70), Global.getParameter("shadows", false), Global.getParameter("sboardox", 5), Global.getParameter("sboardoy", 5), Global.getParameter("sboardd", 10));
    }
}
