// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Component;
import java.awt.Event;
import com.diginet.digichat.util.ap;
import com.esial.util.d;
import java.awt.Choice;
import com.diginet.digichat.awt.ag;

public class b9 extends ag
{
    private Choice a;
    private Choice b;
    private Choice c;
    private Choice d;
    private Choice e;
    private h f;
    
    public void a() {
        this.f.bt = (short)this.a.getSelectedIndex();
        this.f.bu = (short)this.b.getSelectedIndex();
        this.f.bv = (short)this.c.getSelectedIndex();
        this.f.bw = (short)this.d.getSelectedIndex();
        this.f.bx = (short)this.e.getSelectedIndex();
    }
    
    public void b() {
        this.a.select(this.f.bt);
        this.b.select(this.f.bu);
        this.c.select(this.f.bv);
        this.d.select(this.f.bw);
        this.e.select(this.f.bx);
    }
    
    public String a(final Object o) {
        if (o == this.a) {
            return com.esial.util.d.a("Select a sound to play when a new user arrives.");
        }
        if (o == this.b) {
            return com.esial.util.d.a("Select a sound to play when a new message is received.");
        }
        if (o == this.c) {
            return com.esial.util.d.a("Select a sound to play when a new message is received from a flagged user.");
        }
        if (o == this.d) {
            return com.esial.util.d.a("Select a sound to play when a private message is received.");
        }
        if (o == this.e) {
            return ap.a(com.esial.util.d.a("Select a sound to play when a user leaves %1 or moves to another room."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
        }
        return null;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Choice) {
            this.f.a(((Choice)event.target).getSelectedIndex());
            return true;
        }
        return super.action(event, o);
    }
    
    public b9(final h f) {
        super(com.esial.util.d.a("Sounds"), f);
        this.f = f;
        this.a = new Choice();
        this.b = new Choice();
        this.c = new Choice();
        this.d = new Choice();
        this.e = new Choice();
        this.a.addItem(com.esial.util.d.a("None"));
        this.b.addItem(com.esial.util.d.a("None"));
        this.c.addItem(com.esial.util.d.a("None"));
        this.d.addItem(com.esial.util.d.a("None"));
        this.e.addItem(com.esial.util.d.a("None"));
        for (int i = 0; i < i.a.length; ++i) {
            this.a.addItem(i.a[i]);
            this.b.addItem(i.a[i]);
            this.c.addItem(i.a[i]);
            this.d.addItem(i.a[i]);
            this.e.addItem(i.a[i]);
        }
        this.a(com.esial.util.d.a("New User"), this.a);
        this.a(com.esial.util.d.a("New Message"), this.b);
        this.a(com.esial.util.d.a("New Flagged Message"), this.c);
        this.a(com.esial.util.d.a("Private Message"), this.d);
        this.a(com.esial.util.d.a("User Exit"), this.e);
    }
}
