// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Event;
import java.awt.Choice;

public class C extends o
{
    private Choice a;
    private Choice b;
    private Choice c;
    private Choice d;
    private Choice e;
    private u c;
    
    public void c() {
        this.c.B = (short)this.a.getSelectedIndex();
        this.c.C = (short)this.b.getSelectedIndex();
        this.c.D = (short)this.c.getSelectedIndex();
        this.c.E = (short)this.d.getSelectedIndex();
        this.c.F = (short)this.e.getSelectedIndex();
    }
    
    public void d() {
        this.a.select(this.c.B);
        this.b.select(this.c.C);
        this.c.select(this.c.D);
        this.d.select(this.c.E);
        this.e.select(this.c.F);
    }
    
    public String a(final Object o) {
        if (o == this.a) {
            return ao.e("Select a sound to play when a new user arrives.");
        }
        if (o == this.b) {
            return ao.e("Select a sound to play when a new message is received.");
        }
        if (o == this.c) {
            return ao.e("Select a sound to play when a new message is received from a flagged user.");
        }
        if (o == this.d) {
            return ao.e("Select a sound to play when a private message is received.");
        }
        if (o == this.e) {
            return am.a(ao.e("Select a sound to play when a user leaves %1 or moves to another room."), new String[] { z.G });
        }
        return null;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Choice) {
            this.c.a(((Choice)event.target).getSelectedIndex());
            return true;
        }
        return super.action(event, o);
    }
    
    public C(final u c) {
        super(ao.e("Sounds"), c);
        this.c = c;
        this.a = new Choice();
        this.b = new Choice();
        this.c = new Choice();
        this.d = new Choice();
        this.e = new Choice();
        this.a.addItem(ao.e("None"));
        this.b.addItem(ao.e("None"));
        this.c.addItem(ao.e("None"));
        this.d.addItem(ao.e("None"));
        this.e.addItem(ao.e("None"));
        for (int i = 0; i < t.b.length; ++i) {
            this.a.addItem(t.b[i]);
            this.b.addItem(t.b[i]);
            this.c.addItem(t.b[i]);
            this.d.addItem(t.b[i]);
            this.e.addItem(t.b[i]);
        }
        this.a(ao.e("New User"), this.a);
        this.a(ao.e("New Message"), this.b);
        this.a(ao.e("New Flagged Message"), this.c);
        this.a(ao.e("Private Message"), this.d);
        this.a(ao.e("User Exit"), this.e);
    }
}
