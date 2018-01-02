import java.awt.event.WindowListener;
import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Label;
import java.awt.Frame;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class TouchupQuery extends Dialog implements ActionListener
{
    boolean answerYes;
    Button yes;
    Button no;
    
    public TouchupQuery(final Frame frame, final String title, final String s, final int n, final int n2) {
        super(frame, true);
        this.answerYes = false;
        this.yes = null;
        this.no = null;
        this.setTitle(title);
        this.add("Center", new Label(s));
        this.setLayout(new FlowLayout());
        (this.yes = new Button("OK")).addActionListener(this);
        this.add(this.yes);
        (this.no = new Button("Cancel")).addActionListener(this);
        this.add(this.no);
        this.addWindowListener(new WindowAdapter() {
            {
                TouchupQuery.this.getClass();
            }
            
            public void windowClosing(final WindowEvent windowEvent) {
                TouchupQuery.this.no.dispatchEvent(new ActionEvent(TouchupQuery.this.no, 1001, "Cancel"));
            }
        });
        this.setLocation(n, n2);
        this.pack();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("OK")) {
            this.answerYes = true;
        }
        this.hide();
        this.dispose();
    }
}
