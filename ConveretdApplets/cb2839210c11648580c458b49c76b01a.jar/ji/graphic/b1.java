// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.FocusEvent;
import ji.io.h;
import java.awt.Container;
import ji.awt.jiAttachmentDataCheckbox;
import java.awt.event.FocusListener;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.Color;
import ji.awt.gv;
import java.awt.ScrollPane;
import java.awt.event.ItemListener;
import java.awt.CheckboxGroup;
import ji.res.s;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.WindowListener;
import ji.document.gm;
import java.awt.Frame;
import java.awt.Dialog;
import java.awt.Component;
import java.awt.TextField;
import ji.awt.fs;
import java.util.Vector;
import ji.document.b2;
import ji.v1base.bl;

public class b1 extends bl
{
    private bj a;
    private String b;
    private b2 c;
    private Vector d;
    private fs e;
    private TextField f;
    private Component g;
    private String h;
    
    public b1(final Dialog dialog, final String s, final boolean b) {
        super(dialog, s, b);
        this.a(dialog);
    }
    
    public b1(final Frame frame, final String s, final boolean b) {
        super(frame, s, b);
        this.a(frame);
    }
    
    public b2 a(final gm[] array, final String s) {
        this.b(array, s);
        return this.c;
    }
    
    public void a() {
        this.c.c();
        this.f();
    }
    
    void a(final bj a, final String b, final String h) {
        this.a = a;
        this.b = b;
        this.h = h;
    }
    
    private void a(final Component g) {
        this.g = g;
        this.c = new b2();
        this.d = new Vector();
        this.addWindowListener(new xz(this));
    }
    
    private void b(final gm[] array, final String s) {
        final Panel panel = new Panel();
        final BorderLayout layout = new BorderLayout();
        layout.setHgap(5);
        layout.setVgap(5);
        panel.setLayout(layout);
        final Panel panel2 = new Panel(new BorderLayout());
        panel2.setName("attachmentSelectionPanel");
        final Panel panel3 = new Panel(new BorderLayout());
        panel3.setName("selectAllPanel");
        (this.e = new fs(null, s.a(1311, this.h), true, null)).a(new x0());
        panel3.add(this.e, "West");
        final ScrollPane scrollPane = new ScrollPane();
        scrollPane.setName("attachmentScrollPanel");
        final Panel panel4 = new Panel(new gv(10));
        panel4.setName("controlPanel");
        final Panel panel5 = new Panel(new gv(-10));
        panel5.setName("attachmentPanel");
        panel5.setBackground(Color.WHITE);
        final Button button = new Button(s.a(1320, this.h));
        final Button button2 = new Button(s.a(1321, this.h));
        final Button button3 = new Button(s.a(1312, this.h));
        button.addActionListener(new x1());
        button2.addActionListener(new x2());
        button3.addActionListener(new x3());
        final Panel panel6 = new Panel(new BorderLayout());
        panel6.setName("directoryPanel");
        final Label label = new Label(s.a(1313, this.h));
        (this.f = new TextField(s)).addFocusListener(new x4());
        panel6.add(label, "West");
        panel6.add(this.f, "Center");
        panel6.add(button3, "East");
        final Panel panel7 = new Panel();
        panel7.setName("saveCancelPanel");
        panel7.add(button);
        panel7.add(button2);
        final x5 x5 = new x5();
        int i = 0;
        while (i < array.length) {
            final jiAttachmentDataCheckbox jiAttachmentDataCheckbox = new jiAttachmentDataCheckbox(null, array[i++], true);
            jiAttachmentDataCheckbox.setBackground(Color.WHITE);
            jiAttachmentDataCheckbox.a(x5);
            panel5.add(jiAttachmentDataCheckbox);
            this.d.add(jiAttachmentDataCheckbox);
        }
        panel4.add(panel6, "JUSTIFY");
        panel4.add(panel7, "RIGHT");
        scrollPane.add(panel5);
        panel2.add(panel3, "North");
        panel2.add(scrollPane, "Center");
        panel.add(panel2, "Center");
        panel.add(panel4, "South");
        final Container parent = this.getParent();
        this.add(panel, "Center");
        this.pack();
        final int n = parent.getX() + (parent.getWidth() / 2 - this.getWidth() / 2);
        final int n2 = parent.getY() + (parent.getHeight() / 2 - this.getHeight() / 2);
        if (this.getWidth() < 300) {
            this.setBounds(n, n2, 300, this.getHeight());
        }
        else {
            this.setBounds(n, n2, this.getWidth(), this.getHeight());
        }
        this.show();
    }
    
    private void b() {
        try {
            this.a.a(this.b, this.g, this.h);
            this.b = this.a.g();
            this.f.setText(this.b);
        }
        catch (Exception ex) {
            ji.io.h.a(this.h, ex);
        }
    }
    
    private void c() {
        this.c.c();
        for (int size = this.d.size(), i = 0; i < size; ++i) {
            final jiAttachmentDataCheckbox jiAttachmentDataCheckbox = this.d.elementAt(i);
            if (jiAttachmentDataCheckbox.a().getState()) {
                this.c.a(jiAttachmentDataCheckbox.a);
            }
        }
        this.b = this.f.getText();
        this.c.a(this.b);
        this.f();
    }
    
    private void f() {
        try {
            this.dispose();
        }
        catch (Exception ex) {}
    }
    
    private void a(final boolean b) {
        for (int size = this.d.size(), i = 0; i < size; ++i) {
            ((jiAttachmentDataCheckbox)this.d.elementAt(i)).a(b);
        }
    }
    
    private void b(final boolean b) {
        this.e.a(b);
    }
    
    class x4 implements FocusListener
    {
        x4(final b1 b1) {
        }
        
        public void focusGained(final FocusEvent focusEvent) {
        }
        
        public void focusLost(final FocusEvent focusEvent) {
        }
    }
    
    class x5 implements ItemListener
    {
        public void itemStateChanged(final ItemEvent itemEvent) {
            if (itemEvent.getStateChange() == 2) {
                b1.this.b(false);
            }
        }
    }
    
    class x0 implements ItemListener
    {
        public void itemStateChanged(final ItemEvent itemEvent) {
            if (itemEvent.getStateChange() == 1) {
                b1.this.a(true);
            }
            else if (itemEvent.getStateChange() == 2) {
                b1.this.a(false);
            }
        }
    }
    
    class x3 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            b1.this.b();
        }
    }
    
    class x2 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            b1.this.a();
        }
    }
    
    public class x1 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            b1.this.c();
        }
    }
}
