import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Button;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class TouchupQuery3 extends Dialog
{
    public static final int NO = 0;
    public static final int YES = 1;
    public static final int CANCEL = -1;
    Button yes;
    Button no;
    Button cancel;
    Label label;
    
    protected void yes() {
    }
    
    protected void no() {
    }
    
    public TouchupQuery3(final Frame parent, final String title, final String message, final String yes_label, final String no_label, final String cancel_label) {
        super(parent, title, true);
        this.yes = null;
        this.no = null;
        this.cancel = null;
        this.setLayout(new BorderLayout(15, 15));
        this.add("Center", this.label = new Label(message));
        final Panel p = new Panel();
        p.setLayout(new FlowLayout(1, 15, 15));
        if (yes_label != null) {
            p.add(this.yes = new Button(yes_label));
        }
        if (no_label != null) {
            p.add(this.no = new Button(no_label));
        }
        if (cancel_label != null) {
            p.add(this.cancel = new Button(cancel_label));
        }
        this.add("South", p);
        this.pack();
    }
    
    protected void answer(final int answer) {
        switch (answer) {
            case 1: {
                this.yes();
                break;
            }
            case 0: {
                this.no();
                break;
            }
            case -1: {
                this.cancel();
                break;
            }
        }
    }
    
    public boolean action(final Event e, final Object arg) {
        if (e.target instanceof Button) {
            this.hide();
            this.dispose();
            if (e.target == this.yes) {
                this.answer(1);
            }
            else if (e.target == this.no) {
                this.answer(0);
            }
            else {
                this.answer(-1);
            }
            return true;
        }
        return false;
    }
    
    protected void cancel() {
    }
}
