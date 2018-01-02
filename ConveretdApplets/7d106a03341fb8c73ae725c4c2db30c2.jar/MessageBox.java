import java.awt.Event;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Label;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Button;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class MessageBox extends Dialog
{
    static final String BUTTON = "OK";
    Button button;
    
    public MessageBox(final String s) {
        super(new Frame(), true);
        this.addNotify();
        this.setFont(new Font("Helvetica", 1, 12));
        this.setLayout(new BorderLayout());
        final Panel panel = new Panel();
        panel.add(new Label(s));
        this.add("North", panel);
        final Panel panel2 = new Panel();
        panel2.add(this.button = new Button("OK"));
        this.add("South", panel2);
        this.resize(2 * panel.preferredSize().width, 2 * (panel2.preferredSize().height + panel.preferredSize().height));
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.move((screenSize.width - this.size().width) / 2, (screenSize.height - this.size().height) / 2);
    }
    
    public boolean action(final Event event, final Object o) {
        if (o.equals("OK")) {
            this.hide();
            return true;
        }
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201 || (event.id == 401 && event.key == 27)) {
            this.hide();
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void show() {
        super.show();
        this.button.requestFocus();
    }
}
