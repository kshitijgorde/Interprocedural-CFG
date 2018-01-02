// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.awt.Frame;
import java.util.GregorianCalendar;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.awt.event.KeyListener;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import COM.NextBus.AdminMap.Toolbar.AgencyToolbarPanel;
import java.awt.Label;
import java.awt.Choice;
import COM.NextBus.Applets.a;
import java.awt.Container;
import java.awt.Image;
import java.awt.Dialog;
import java.awt.Panel;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

final class C extends KeyAdapter
{
    private /* synthetic */ N a;
    
    C(final N a) {
        this.a = a;
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 39: {
                this.a.w.a("East");
            }
            case 37: {
                this.a.w.a("West");
            }
            case 38: {
                this.a.w.a("North");
            }
            case 40: {
                this.a.w.a("South");
            }
            case 86: {
                this.a.x.actionPerformed(new ActionEvent(this.a.e, 0, ""));
            }
            case 69: {
                this.a.x.actionPerformed(new ActionEvent(this.a.d, 0, ""));
            }
            case 80: {
                this.a.actionPerformed(new ActionEvent(this.a.i, 0, ""));
            }
            case 72: {
                this.a.actionPerformed(new ActionEvent(this.a.j, 0, ""));
            }
            case 83:
            case 84: {
                if (this.a.g.isVisible()) {
                    this.a.c();
                    return;
                }
                N.a(this.a);
            }
            case 68: {}
            default: {
                switch (keyEvent.getKeyChar()) {
                    case '+':
                    case '=': {
                        this.a.v.f.d();
                        return;
                    }
                    case '-': {
                        this.a.v.f.e();
                        return;
                    }
                    default: {
                        if (this.a.v.k().toUpperCase().startsWith(("" + keyEvent.getKeyChar()).toUpperCase())) {
                            this.a.x.actionPerformed(new ActionEvent(this.a.c, 0, ""));
                        }
                        return;
                    }
                }
                break;
            }
        }
    }
    
    public final void keyReleased(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 37:
            case 38:
            case 39:
            case 40: {
                this.a.v.b();
                break;
            }
        }
    }
}
