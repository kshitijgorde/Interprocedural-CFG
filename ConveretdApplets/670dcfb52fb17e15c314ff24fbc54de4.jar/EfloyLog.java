import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.Date;
import java.awt.TextArea;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class EfloyLog extends Frame
{
    TextArea ta;
    Date StartDate;
    Date EndDate;
    
    public void showMsg(final Object obj) {
        if (Efloys.InLog) {
            this.ta.appendText(String.valueOf(obj) + '\n');
        }
    }
    
    public void showMsg(final int n) {
        if (Efloys.InLog) {
            this.ta.appendText(String.valueOf(n) + '\n');
        }
    }
    
    public void start() {
        this.StartDate = new Date();
        if (Efloys.InLog) {
            this.ta.appendText("Session Started at: " + this.StartDate.toString() + "\n\n");
        }
    }
    
    public void clear() {
        if (Efloys.InLog) {
            this.ta.setText("");
        }
    }
    
    public void clear(final String msg) {
        if (Efloys.InLog) {
            this.ta.setText(msg);
        }
    }
    
    EfloyLog(final String title) {
        super(title);
        (this.ta = new TextArea(50, 100)).setText("");
        this.setLayout(new BorderLayout());
        this.add("Center", this.ta);
        this.pack();
    }
    
    public boolean handleEvent(final Event ev) {
        if (ev.id == 201) {
            this.hide();
            return true;
        }
        return super.handleEvent(ev);
    }
    
    public void end() {
        this.EndDate = new Date();
        if (Efloys.InLog) {
            this.ta.appendText("End at: " + this.EndDate.toString());
        }
    }
    
    public void showMsg(final String msg) {
        if (Efloys.InLog) {
            this.ta.appendText(msg + '\n');
        }
    }
}
