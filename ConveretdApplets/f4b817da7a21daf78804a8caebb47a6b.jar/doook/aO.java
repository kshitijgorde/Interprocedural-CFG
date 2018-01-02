// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Event;
import java.awt.Choice;

public class aO extends aZ
{
    private Choice a;
    private Choice b;
    private Choice c;
    private Choice d;
    private Choice e;
    private aW h;
    
    public void a() {
        this.h.N = (short)this.a.getSelectedIndex();
        this.h.O = (short)this.b.getSelectedIndex();
        this.h.P = (short)this.c.getSelectedIndex();
        this.h.Q = (short)this.d.getSelectedIndex();
        this.h.R = (short)this.e.getSelectedIndex();
    }
    
    public void c() {
        this.a.select(this.h.N);
        this.b.select(this.h.O);
        this.c.select(this.h.P);
        this.d.select(this.h.Q);
        this.e.select(this.h.R);
    }
    
    public String a(final Object o) {
        if (o == this.a) {
            return aG.a("Select a sound to play when a new user arrives.");
        }
        if (o == this.b) {
            return aG.a("Select a sound to play when a new message is received.");
        }
        if (o == this.c) {
            return aG.a("Select a sound to play when a new message is received from a flagged user.");
        }
        if (o == this.d) {
            return aG.a("Select a sound to play when a private message is received.");
        }
        if (o == this.e) {
            return aC.a(aG.a("Select a sound to play when a user leaves %1 or moves to another room."), new String[] { t.a });
        }
        return null;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Choice) {
            this.h.e(((Choice)event.target).getSelectedIndex());
            return true;
        }
        return super.action(event, o);
    }
    
    public aO(final aW h) {
        super(aG.a("Sounds"), h);
        this.h = h;
        this.a = new Choice();
        this.b = new Choice();
        this.c = new Choice();
        this.d = new Choice();
        this.e = new Choice();
        this.a.addItem(aG.a("None"));
        this.b.addItem(aG.a("None"));
        this.c.addItem(aG.a("None"));
        this.d.addItem(aG.a("None"));
        this.e.addItem(aG.a("None"));
        for (int i = 0; i < be.e.length; ++i) {
            this.a.addItem(be.e[i]);
            this.b.addItem(be.e[i]);
            this.c.addItem(be.e[i]);
            this.d.addItem(be.e[i]);
            this.e.addItem(be.e[i]);
        }
        this.a(aG.a("New User"), this.a);
        this.a(aG.a("New Message"), this.b);
        this.a(aG.a("New Flagged Message"), this.c);
        this.a(aG.a("Private Message"), this.d);
        this.a(aG.a("User Exit"), this.e);
    }
}
