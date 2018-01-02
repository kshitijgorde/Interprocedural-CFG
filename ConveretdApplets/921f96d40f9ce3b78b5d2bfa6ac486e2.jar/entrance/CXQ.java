// 
// Decompiled by Procyon v0.5.30
// 

package entrance;

import java.applet.Applet;
import screen.GameClient;

public class CXQ extends GameClient
{
    boolean p;
    
    public CXQ() {
        this.p = false;
        dl.p = new d();
    }
    
    public final void init() {
        super.init();
        this.p = !h.p(this, this.a());
        if (this.p) {
            this.p().l("<4>ERROR Unable to load chess pieces!!!");
        }
    }
    
    public final boolean p() {
        return this.p;
    }
    
    public final k p(String s) {
        if (s == null) {
            s = "Xiangqi";
        }
        if (s.equalsIgnoreCase("Xiangqi")) {
            return new h((y)this);
        }
        if (s.equalsIgnoreCase("Gomoku")) {
            return new j((y)this);
        }
        return null;
    }
    
    public final boolean i() {
        return true;
    }
}
