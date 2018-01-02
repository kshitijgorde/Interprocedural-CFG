// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Event;
import java.awt.Choice;

public class q extends W
{
    private Choice a;
    private Choice b;
    private Choice c;
    private Choice d;
    private Choice e;
    private at b;
    
    public void f() {
        this.b.P = (short)this.a.getSelectedIndex();
        this.b.Q = (short)this.b.getSelectedIndex();
        this.b.R = (short)this.c.getSelectedIndex();
        this.b.S = (short)this.d.getSelectedIndex();
        this.b.T = (short)this.e.getSelectedIndex();
    }
    
    public void c() {
        this.a.select(this.b.P);
        this.b.select(this.b.Q);
        this.c.select(this.b.R);
        this.d.select(this.b.S);
        this.e.select(this.b.T);
    }
    
    public String a(final Object o) {
        if (o == this.a) {
            return ar.b("Select a sound to play when a new user arrives.");
        }
        if (o == this.b) {
            return ar.b("Select a sound to play when a new message is received.");
        }
        if (o == this.c) {
            return ar.b("Select a sound to play when a new message is received from a flagged user.");
        }
        if (o == this.d) {
            return ar.b("Select a sound to play when a private message is received.");
        }
        if (o == this.e) {
            return H.a(ar.b("Select a sound to play when a user leaves %1 or moves to another room."), new String[] { bi.Q });
        }
        return null;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Choice) {
            this.b.a(((Choice)event.target).getSelectedIndex());
            return true;
        }
        return super.action(event, o);
    }
    
    public q(final at b) {
        super(ar.b("Sounds"), b);
        this.b = b;
        this.a = new Choice();
        this.b = new Choice();
        this.c = new Choice();
        this.d = new Choice();
        this.e = new Choice();
        this.a.addItem(ar.b("None"));
        this.b.addItem(ar.b("None"));
        this.c.addItem(ar.b("None"));
        this.d.addItem(ar.b("None"));
        this.e.addItem(ar.b("None"));
        for (int i = 0; i < as.c.length; ++i) {
            this.a.addItem(as.c[i]);
            this.b.addItem(as.c[i]);
            this.c.addItem(as.c[i]);
            this.d.addItem(as.c[i]);
            this.e.addItem(as.c[i]);
        }
        this.a(ar.b("New User"), this.a);
        this.a(ar.b("New Message"), this.b);
        this.a(ar.b("New Flagged Message"), this.c);
        this.a(ar.b("Private Message"), this.d);
        this.a(ar.b("User Exit"), this.e);
    }
}
