import java.awt.TextArea;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class j extends Dialog
{
    protected static Frame a;
    
    public static void createInfo(final String s, final String s2) {
        if (j.a == null) {
            j.a = new Frame("Chemis3D");
        }
        final j j = new j(j.a, s, s2);
        j.a.resize(400, 300);
        j.show();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            this.hide();
            if (j.a != null) {
                j.a.hide();
            }
        }
        return true;
    }
    
    public j(final Frame frame, final String s, final String s2) {
        super(frame, false);
        final Panel panel = new Panel();
        panel.setFont(new Font("Helvetica", 0, 18));
        panel.setLayout(new FlowLayout(1));
        panel.add(new Label(s));
        this.add("North", panel);
        final Panel panel2 = new Panel();
        panel2.setFont(new Font("Helvetica", 0, 14));
        panel2.setLayout(new FlowLayout(1));
        final TextArea textArea = new TextArea(s2, 11, 40);
        panel2.add("Center", textArea);
        textArea.setEditable(false);
        this.add("Center", panel2);
        final Panel panel3 = new Panel();
        panel3.setLayout(new FlowLayout(1));
        panel3.add("South", new Button("Close"));
        this.add("South", panel3);
        this.pack();
    }
}
