// 
// Decompiled by Procyon v0.5.30
// 

package buddysoft;

import buddysoft.c.e;
import buddysoft.c.c;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import buddysoft.b.d;

public class a extends buddysoft.c.a
{
    private d try;
    private SlideShow new;
    
    public void a() {
        this.try.for();
    }
    
    public a(final SlideShow new1) {
        super(8, 8, 8, 8, new1.api.do, true);
        this.new = new1;
        this.setLayout(new BorderLayout(0, 6));
        final buddysoft.c.a a = new buddysoft.c.a(3, 3, 3, 3, new1.api.do, false);
        a.setLayout(new BorderLayout(0, 0));
        a.add("Center", this.try = new d(new1));
        this.add("Center", a);
        this.add("South", new buddysoft.d.a(new1.api));
    }
    
    public void if() {
        this.try.char();
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (this.try == null) {
                    break;
                }
                if (event.target instanceof c) {
                    final String a = ((c)event.target).a();
                    if (a.equals("pause")) {
                        this.try.case();
                    }
                    else if (a.equals("play")) {
                        this.try.try();
                    }
                    else if (a.equals("stop")) {
                        this.try.goto();
                    }
                    else if (a.equals("reset")) {
                        this.try.new();
                    }
                    else if (a.equals("prev")) {
                        this.try.else();
                    }
                    else if (a.equals("next")) {
                        this.try.byte();
                    }
                }
                break;
            }
            case 504: {
                if (event.target instanceof e) {
                    this.new.showStatus(((e)event.target).if());
                }
                break;
            }
            case 505: {
                this.new.showStatus("DONE");
                break;
            }
        }
        return super.handleEvent(event);
    }
}
