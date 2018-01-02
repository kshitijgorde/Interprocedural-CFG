import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.io.File;
import java.awt.Panel;
import java.awt.Color;
import java.awt.List;
import java.awt.Canvas;
import java.awt.Button;
import java.awt.Label;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class options extends Frame implements ActionListener, ItemListener
{
    TextField t2;
    TextField t3;
    TextField t4;
    Checkbox cb1;
    Checkbox cb2;
    Checkbox cb3;
    Checkbox cb4;
    Label l0;
    Label l4;
    Label l5;
    jigsaw j;
    thumbnail thumb;
    Button b1;
    Button b2;
    Button b3;
    Canvas c1;
    colorchooser cc;
    List lfiles;
    boolean imset;
    String percentinit;
    String newpage;
    
    options(final jigsaw j, final boolean b, final boolean b2, final boolean b3, final boolean b4, final Color background, final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        this.imset = false;
        this.setTitle("Options");
        this.percentinit = s3;
        this.j = j;
        final Panel panel = new Panel();
        this.c1 = new Canvas();
        this.lfiles = new List(1);
        final Label label = new Label("Number of Pieces Across");
        this.t2 = new TextField(s);
        final Label label2 = new Label("Number of Pieces Down");
        this.t3 = new TextField(s2);
        this.cb1 = new Checkbox("Show Faded Original as Guide", b4);
        this.cb2 = new Checkbox("Thumbnail Guide [right-click reshows]", b3);
        this.cb3 = new Checkbox("Allow Rotations [forces pieces to be square]", b);
        this.b1 = new Button("Scramble");
        (this.l5 = new Label()).setForeground(Color.red);
        this.b3 = new Button("Choose Background Color");
        this.c1.setBackground(background);
        this.cc = new colorchooser(this);
        this.cb4 = new Checkbox("Scale picture to required size", b2);
        this.l4 = new Label("");
        this.t4 = new TextField("");
        if (b2) {
            this.t4.setEditable(true);
            this.t4.setText(s3);
            this.l4.setText("Percentage of Area taken by picture?");
        }
        else {
            this.t4.setEditable(false);
        }
        if (b) {
            this.t3.setText("");
            this.t3.setEditable(false);
        }
        final String[] array = { "" };
        String[] list;
        if (j.getCodeBase().getProtocol().equals("file")) {
            list = new File(j.getCodeBase().getPath(), s4).list();
        }
        else {
            int n = 0;
            int n2 = -1;
            for (int i = 0; i < s6.length(); ++i) {
                if (s6.substring(i, i + 1).equals(",")) {
                    ++n;
                }
            }
            list = new String[n + 1];
            for (int k = 0; k < n; ++k) {
                final int index = s6.indexOf(44, n2 + 1);
                list[k] = s6.substring(n2 + 1, index);
                n2 = index;
            }
            list[n] = s6.substring(n2 + 1);
        }
        boolean b5 = false;
        for (int l = 0; l < list.length; ++l) {
            final String substring = list[l].substring(list[l].lastIndexOf("."), list[l].length());
            if (substring.equals(".jpg") || substring.equals(".JPG") || substring.equals(".jpeg") || substring.equals(".JPEG") || substring.equals(".PNG") || substring.equals(".png") || substring.equals(".GIF") || substring.equals(".gif")) {
                this.lfiles.add(list[l]);
            }
            if (list[l].equals(s5)) {
                b5 = true;
            }
        }
        if (b5) {
            this.l0 = new Label(s5);
            this.loadup();
        }
        else if (list.length > 0) {
            this.l0 = new Label(list[0]);
        }
        else {
            this.l0 = new Label("");
            this.l5.setText("No valid images found");
        }
        this.b1.addActionListener(this);
        this.b3.addActionListener(this);
        this.lfiles.addItemListener(this);
        this.cb2.addItemListener(this);
        this.cb3.addItemListener(this);
        this.cb4.addItemListener(this);
        panel.setLayout(new GridLayout(8, 2));
        panel.add(this.lfiles);
        panel.add(this.l0);
        panel.add(this.b3);
        panel.add(this.c1);
        panel.add(label);
        panel.add(this.t2);
        panel.add(label2);
        panel.add(this.t3);
        panel.add(this.cb1);
        panel.add(this.cb2);
        panel.add(this.cb3);
        panel.add(this.cb4);
        panel.add(this.l4);
        panel.add(this.t4);
        panel.add(this.l5);
        panel.add(this.b1);
        this.add(panel);
    }
    
    void scramble() {
        this.actionPerformed(new ActionEvent(this.b1, 2000, ""));
    }
    
    void loadup() {
        if (!this.imset) {
            this.imset = this.j.setim(this.l0.getText());
        }
        if (this.imset && this.cb2.getState()) {
            if (this.thumb != null) {
                this.thumb.setVisible(false);
            }
            (this.thumb = new thumbnail(itransform.custom(this.j.bmain, 200, 200 * this.j.bmain.getHeight() / this.j.bmain.getWidth()))).setVisible(true);
            this.thumb.resetsize();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.b1)) {
            Integer n = new Integer(0);
            boolean b = true;
            int intValue = 2;
            int intValue2 = 2;
            this.l5.setText("");
            this.loadup();
            try {
                n = new Integer(this.t2.getText().trim());
            }
            catch (NumberFormatException ex) {
                b = false;
            }
            if (b) {
                intValue2 = n;
            }
            boolean b2 = true;
            if (this.imset && this.cb3.getState()) {
                intValue = this.j.bmain.getHeight() / (this.j.bmain.getWidth() / intValue2);
                cluster.ifrot = true;
            }
            if (this.imset && !this.cb3.getState()) {
                try {
                    n = new Integer(this.t3.getText().trim());
                }
                catch (NumberFormatException ex2) {
                    b2 = false;
                    intValue = intValue2 * this.j.bmain.getHeight() / this.j.bmain.getWidth();
                }
                if (b2) {
                    intValue = n;
                }
                cluster.ifrot = false;
            }
            if (!this.imset || intValue2 <= 0 || intValue2 > this.j.bmain.getWidth() / 5) {
                intValue2 = 2;
            }
            if (!this.imset || intValue <= 0 || intValue > this.j.bmain.getHeight() / 5) {
                intValue = 2;
            }
            boolean b3 = true;
            int intValue3 = 30;
            if (this.imset && this.cb4.getState()) {
                try {
                    n = new Integer(this.t4.getText().trim());
                }
                catch (NumberFormatException ex3) {
                    b3 = false;
                }
                if (b3) {
                    intValue3 = n;
                    if (intValue3 <= 0 || intValue3 > 100) {
                        intValue3 = 30;
                    }
                }
                final float n2 = (float)Math.sqrt(intValue3 * this.j.getSize().width * this.j.getSize().height / this.j.bmain.getWidth() / this.j.bmain.getHeight() / 100.0f);
                final int n3 = (int)(this.j.bmain.getWidth() * n2) / intValue2 * intValue2;
                int n4;
                if (!this.cb3.getState()) {
                    n4 = (int)(this.j.bmain.getHeight() * n2) / intValue * intValue;
                }
                else {
                    n4 = n3 / intValue2 * intValue;
                }
                this.j.bmain = itransform.custom(this.j.bmain, n3, n4);
            }
            if (this.imset) {
                this.setState(1);
                this.j.requestFocus();
                if (this.cc != null && this.cc.isVisible()) {
                    this.cc.setVisible(false);
                }
            }
            if (!this.imset) {
                this.l5.setText("Invalid image");
            }
            this.j.transbmain();
            if (this.imset) {
                this.j.scramble(intValue2, intValue);
            }
        }
        if (actionEvent.getSource().equals(this.b3)) {
            if (this.cc.isVisible()) {
                this.cc.setVisible(false);
            }
            else {
                this.cc.setVisible(true);
                this.cc.repaint();
            }
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (!this.cb3.getState()) {
            this.t3.setEditable(true);
        }
        if (this.cb3.getState()) {
            this.t3.setText("");
            this.t3.setEditable(false);
        }
        if (this.cb4.getState()) {
            this.t4.setEditable(true);
            this.l4.setText("Percentage of Area taken by picture?");
        }
        if (!this.cb4.getState()) {
            this.t4.setText("");
            this.t4.setEditable(false);
            this.l4.setText("");
        }
        if (itemEvent.getSource().equals(this.lfiles)) {
            this.l0.setText(this.lfiles.getSelectedItem());
            this.imset = false;
            this.loadup();
        }
        if (itemEvent.getSource().equals(this.cb2)) {
            if (this.cb2.getState()) {
                this.loadup();
            }
            else if (this.thumb != null) {
                this.thumb.setVisible(false);
            }
        }
    }
    
    void movethumb() {
        if (this.thumb != null && this.thumb.isVisible()) {
            this.thumb.toFront();
        }
    }
    
    void toggleoptions() {
        if (this.getState() == 1) {
            this.setState(0);
        }
        else if (this.getState() == 0) {
            this.setState(1);
        }
    }
    
    void setcol(final Color color) {
        this.j.bgcol = color;
        this.c1.setBackground(color);
        this.repaint();
    }
}
