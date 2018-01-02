import com.daysofwonder.applet.aC;
import java.awt.Color;
import com.daysofwonder.applet.aE;
import java.awt.Point;
import com.daysofwonder.util.t;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import com.daysofwonder.b.a;
import com.daysofwonder.applet.aL;
import com.daysofwonder.util.UIProperties;
import com.daysofwonder.applet.ap;
import java.awt.Rectangle;
import com.daysofwonder.b.b;
import com.daysofwonder.applet.am;
import com.daysofwonder.tt.i;

// 
// Decompiled by Procyon v0.5.30
// 

class j implements Runnable
{
    final /* synthetic */ i a;
    final /* synthetic */ O b;
    
    j(final O b, final i a) {
        this.b = b;
        this.a = a;
    }
    
    public void run() {
        if (this.a != i.a) {
            this.b.a.b(this.a);
        }
    }
}
