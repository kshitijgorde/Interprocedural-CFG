// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a.b;

import java.applet.AppletContext;
import java.net.URL;
import java.applet.Applet;
import a.a.a.a.a.b;

public class c implements a
{
    private a.a.a.a.e.a a;
    private String if;
    
    public c(final a.a.a.a.e.a a, final String if1) {
        this.a = a;
        this.if = if1;
    }
    
    public void a() {
        final a.a.a.a.c.a.a a = (a.a.a.a.c.a.a)this.a.byte("statemachine");
        if (a != null) {
            a.new();
        }
        if (this.if.equals("reset")) {
            final b b = (b)this.a.byte("player");
            if (b != null) {
                b.p();
            }
            if (a != null) {
                a.int();
            }
        }
        else if (this.if.equals("rotateleft")) {
            final b b2 = (b)this.a.byte("player");
            if (b2 != null) {
                b2.x();
            }
        }
        else if (this.if.equals("pause")) {
            final b b3 = (b)this.a.byte("player");
            if (b3 != null) {
                b3.w();
            }
        }
        else if (this.if.equals("rotateright")) {
            final b b4 = (b)this.a.byte("player");
            if (b4 != null) {
                b4.c();
            }
        }
        else if (this.if.equals("info")) {
            final AppletContext appletContext = ((Applet)this.a.byte("applet")).getAppletContext();
            if (appletContext != null) {
                final String trim = ((String)this.a.byte("resource/info.link")).trim();
                if (trim != null) {
                    try {
                        appletContext.showDocument(new URL(trim), "_blank");
                    }
                    catch (Exception ex) {}
                }
            }
        }
        else if (this.if.equals("zoomin")) {
            final b b5 = (b)this.a.byte("player");
            if (b5 != null) {
                b5.q();
            }
        }
        else if (this.if.equals("zoomout")) {
            final b b6 = (b)this.a.byte("player");
            if (b6 != null) {
                b6.o();
            }
        }
    }
}
