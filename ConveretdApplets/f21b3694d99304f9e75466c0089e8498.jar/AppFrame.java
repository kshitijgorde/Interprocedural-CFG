import java.awt.Event;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class AppFrame extends Frame
{
    int currentSize;
    Component applet;
    
    AppFrame(final Component component) {
        this.applet = component;
        this.setTitle("JavaChart - Copyright (c) 2000 .");
        this.resize(800, 590);
        this.setLayout(new BorderLayout());
        this.currentSize = this.size().width * 1000 + this.size().height;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.applet.handleEvent(new Event(this, 9101, null));
            this.hide();
            return true;
        }
        if (this.currentSize != this.size().width * 1000 + this.size().height) {
            if (this.size().width < 389) {
                this.resize(389, this.size().height);
            }
            else {
                this.applet.handleEvent(new Event(this, 9102, null));
                this.currentSize = this.size().width * 1000 + this.size().height;
            }
        }
        return this.applet.handleEvent(event);
    }
    
    public void resize(final int i, final int j) {
        super.resize(i, j);
        this.applet.handleEvent(new Event(this, 9102, null));
    }
}
