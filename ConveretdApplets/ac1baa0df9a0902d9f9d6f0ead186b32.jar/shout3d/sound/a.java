// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.sound;

import java.applet.Applet;
import java.net.URL;
import shout3d.core.Shout3DViewer;
import java.applet.AudioClip;
import shout3d.core.c;

class a implements c
{
    protected AudioClip a;
    protected boolean b;
    private Shout3DViewer c;
    private URL d;
    
    public Object a() {
        return this;
    }
    
    public a(final Shout3DViewer c, final URL d) {
        this.a = null;
        this.b = false;
        this.c = c;
        this.d = d;
    }
    
    public int b() {
        return 3;
    }
    
    public void c() {
        this.b = true;
        if (this.c != null) {
            this.c.a().a(this.c, this);
        }
    }
    
    public void d() {
        if (this.c != null && this.c.b() instanceof Applet) {
            final Applet applet = (Applet)this.c.b();
            if (applet != null) {
                this.a = applet.getAudioClip(this.e());
            }
        }
    }
    
    public AudioClip f() {
        return this.a;
    }
    
    public URL e() {
        return this.d;
    }
    
    public void a(final Object o) {
        this.b = false;
        System.out.println("JavaSound.returnResource");
    }
}
