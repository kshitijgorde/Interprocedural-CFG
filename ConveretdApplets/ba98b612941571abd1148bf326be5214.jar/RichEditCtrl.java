import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class RichEditCtrl extends Panel
{
    private IRCQNetScrollBar Scroler;
    private RichEdit display;
    private IRCQNet parent;
    
    public RichEditCtrl(final IRCQNetPanel ircqNetPanel) {
        this.Scroler = new IRCQNetScrollBar(0, 0, 0, 1);
        this.display = new RichEdit(this.Scroler, ircqNetPanel);
        this.setLayout(new BorderLayout());
        this.add("East", this.Scroler);
        this.add("Center", this.display);
    }
    
    public void appendText(final String s) {
        if (s.equals("\n")) {
            return;
        }
        if (s.indexOf(10) != -1) {
            this.display.appendText(s.substring(0, s.indexOf(10)));
            return;
        }
        this.display.appendText(s);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 601:
            case 602:
            case 603:
            case 604:
            case 605: {
                this.display.SBarMove();
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
}
