// 
// Decompiled by Procyon v0.5.30
// 

package buddysoft;

import buddysoft.c.e;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import java.awt.MenuItem;
import buddysoft.a.b;
import buddysoft.a.a;
import java.applet.Applet;

public class SlideShow extends Applet
{
    public a api;
    private b a;
    private buddysoft.a do;
    private MenuItem if;
    private JSObject for;
    
    public void stop() {
        if (this.a != null && this.a.isAlive()) {
            this.a.stop();
            this.a = null;
        }
        this.do.a();
    }
    
    public void start() {
        if (this.a == null) {
            (this.a = new b(this)).setPriority(1);
            this.a.setDaemon(true);
            this.a.start();
        }
        this.do.if();
    }
    
    public String getAppletInfo() {
        return "BuddySoft Slide Show 1.5. Author: plamen_matanski@yahoo.com";
    }
    
    public String getAppletVersion() {
        return "BuddySoft Slide Show - ver 1.5";
    }
    
    public void init() {
        try {
            this.for = JSObject.getWindow((Applet)this);
        }
        catch (JSException ex) {}
        this.api = new a(this);
        this.setBackground(this.api.byte);
        this.setForeground(this.api.g);
        this.setLayout(new BorderLayout());
        this.add("Center", this.do = new buddysoft.a(this));
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 504: {
                if (event.target instanceof e) {
                    this.showStatus(((e)event.target).if());
                }
                break;
            }
            case 505: {
                this.showStatus("DONE");
                break;
            }
            case 401: {
                if (event.controlDown()) {
                    if (event.key == 1) {
                        this.alert(this.getAppletInfo());
                    }
                    else if (event.key == 22) {
                        this.alert(this.getAppletVersion());
                    }
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void alert(final String s) {
        System.out.println(s);
        if (this.for != null) {
            try {
                this.for.eval("alert('" + s + "');");
            }
            catch (Exception ex) {}
        }
        else {
            this.showStatus(s);
        }
    }
}
