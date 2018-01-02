// 
// Decompiled by Procyon v0.5.30
// 

package buddysoft.a;

import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.MediaTracker;
import buddysoft.SlideShow;

public class b extends Thread
{
    private c[] for;
    private SlideShow if;
    private MediaTracker int;
    private a do;
    private Thread a;
    
    public b(final SlideShow if1) {
        this.if = if1;
        this.do = if1.api;
        this.for = if1.api.b;
        this.int = new MediaTracker(if1);
        this.a();
    }
    
    private void a() {
        if (this.do.char.equals("")) {
            buddysoft.a.a.f = true;
            return;
        }
        if (this.do.for != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.do.for, "+");
            while (stringTokenizer.hasMoreTokens()) {
                if (this.do.a(this.do.char).equals(stringTokenizer.nextToken().trim().substring(3))) {
                    buddysoft.a.a.f = true;
                    return;
                }
            }
        }
        buddysoft.a.a.f = false;
    }
    
    public void run() {
        for (int i = 0; i < this.for.length; ++i) {
            this.int.addImage(this.for[i].for, i);
            if (!this.int.checkID(i)) {
                this.if.showStatus("Loading " + this.for[i].try);
                try {
                    this.int.waitForID(i);
                }
                catch (InterruptedException ex) {}
            }
            this.for[i].int = this.int.checkID(i);
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex2) {}
        }
        this.if.showStatus("DONE");
    }
}
