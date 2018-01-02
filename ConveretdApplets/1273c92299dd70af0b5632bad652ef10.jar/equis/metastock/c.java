// 
// Decompiled by Procyon v0.5.30
// 

package equis.metastock;

import java.net.URL;
import java.awt.Event;
import java.awt.Button;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.util.Vector;
import java.awt.Label;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Panel;

class c extends Panel
{
    public TextField a;
    public TextField b;
    public TextField c;
    public Choice d;
    public Choice e;
    public Label f;
    private Vector g;
    private Vector h;
    private int i;
    private MS4Java j;
    
    public void a(final String s) {
        this.d.select(s);
    }
    
    public String a() {
        return this.d.getSelectedItem();
    }
    
    public void b(final String text) {
        if (text == null || text == "") {
            return;
        }
        this.f.setText(text);
        this.f.resize(MS4Java.af, this.f.size().height);
    }
    
    private void c(final String s) {
        if (MS4Java.av.indexOf(s.toUpperCase()) < 0) {
            this.e.addItem(s);
        }
    }
    
    public c(final b b, final MS4Java j) {
        this.i = -1;
        this.j = j;
        this.setBackground(Color.lightGray);
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(gridBagLayout);
        final Panel panel = new Panel();
        panel.setBackground(MS4Java.v);
        panel.setLayout(gridBagLayout);
        String text;
        if (!MS4Java.f().toUpperCase().equals("WEST")) {
            text = "                                                                         ";
        }
        else {
            text = "                          ";
        }
        (this.f = new Label(text, 0)).setText(text);
        this.f.setFont(new Font("Helvetica", 0, MS4Java.e()));
        if (!MS4Java.f().toUpperCase().equals("WEST")) {
            gridBagConstraints.ipady = -10;
        }
        else {
            gridBagConstraints.weightx = 10.0;
        }
        gridBagConstraints.anchor = 10;
        gridBagLayout.setConstraints(this.f, gridBagConstraints);
        panel.add(this.f);
        this.f.paint(this.getGraphics());
        gridBagConstraints.ipady = 0;
        gridBagConstraints.fill = 2;
        if (!MS4Java.f().toUpperCase().equals("WEST")) {
            gridBagConstraints.gridwidth = 0;
        }
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.1;
        gridBagLayout.setConstraints(this.a = new TextField("", 10), gridBagConstraints);
        this.a.setBackground(Color.white);
        if (MS4Java.s()) {
            this.add(this.a);
        }
        (this.d = new Choice()).addItem(MS4Java.bf[0]);
        if (MS4Java.bc >= 20000) {
            this.d.addItem(MS4Java.bf[1]);
        }
        this.d.addItem(MS4Java.bf[2]);
        this.d.addItem(MS4Java.bf[3]);
        this.d.setBackground(Color.white);
        if (MS4Java.r()) {
            this.add(this.d);
        }
        (this.e = new Choice()).addItem(MS4Java.bf[4]);
        this.c(MS4Java.bf[5]);
        this.c(MS4Java.bf[6]);
        this.c(MS4Java.bf[7]);
        this.c(MS4Java.bf[50]);
        this.c(MS4Java.bf[49]);
        this.c(MS4Java.bf[8]);
        this.c(MS4Java.bf[9]);
        this.c(MS4Java.bf[51]);
        this.c(MS4Java.bf[10]);
        this.c(MS4Java.bf[11]);
        this.c(MS4Java.bf[12]);
        this.c(MS4Java.bf[13]);
        this.c(MS4Java.bf[52]);
        this.c(MS4Java.bf[14]);
        this.c(MS4Java.bf[15]);
        this.c(MS4Java.bf[53]);
        this.c(MS4Java.bf[16]);
        this.c(MS4Java.bf[17]);
        this.c(MS4Java.bf[54]);
        this.c(MS4Java.bf[18]);
        this.c(MS4Java.bf[55]);
        final int itemCount = this.e.getItemCount();
        if (itemCount > 0) {
            (this.g = new Vector(itemCount)).setSize(itemCount);
            (this.h = new Vector(itemCount)).setSize(itemCount);
            for (int i = 0; i < itemCount; ++i) {
                this.g.setElementAt("", i);
                this.h.setElementAt("", i);
            }
        }
        this.e.setBackground(Color.white);
        gridBagConstraints.weightx = 0.0;
        this.add(this.e);
        gridBagConstraints.fill = 2;
        gridBagLayout.setConstraints(this.b = new TextField("", 2), gridBagConstraints);
        this.b.setBackground(Color.white);
        this.add(this.b);
        gridBagLayout.setConstraints(this.c = new TextField("", 2), gridBagConstraints);
        this.c.setBackground(Color.white);
        this.add(this.c);
        this.add(new Button(MS4Java.bf[19]));
        if (MS4Java.p()) {
            this.add(new Button(MS4Java.bf[21]));
        }
        if (MS4Java.o()) {
            this.add(new Button(MS4Java.bf[20]));
        }
    }
    
    private void a(final boolean b, final TextField textField, final boolean b2) {
        if (b2) {
            this.a.select(0, 0);
        }
        if (b) {
            int int1;
            try {
                int1 = Integer.parseInt(textField.getText().trim());
            }
            catch (Exception ex) {
                int1 = 0;
            }
            if (textField.isEnabled()) {
                if (int1 < 1) {
                    int1 = 1;
                }
                if (int1 > MS4Java.d[0].k.b() - 1) {
                    int1 = MS4Java.d[0].k.b() - 1;
                }
                textField.setText(Integer.toString(int1, 1));
                if (b2) {
                    textField.selectAll();
                }
            }
        }
        else if (MS4Java.ae) {
            textField.setText(textField.getText().toUpperCase());
            if (b2) {
                textField.selectAll();
            }
        }
    }
    
    private void b() {
        if (MS4Java.d[0].k.b() > 0) {
            this.a(MS4Java.f.e.getSelectedItem() != MS4Java.bf[49], this.b, true);
            this.a(true, this.c, false);
            int int1;
            try {
                int1 = Integer.parseInt(this.b.getText().trim());
            }
            catch (Exception ex) {
                int1 = 0;
            }
            int int2;
            try {
                int2 = Integer.parseInt(this.c.getText().trim());
            }
            catch (Exception ex2) {
                int2 = 0;
            }
            MS4Java.a.a(this.e.getSelectedItem(), MS4Java.d[0], MS4Java.b, MS4Java.c, this.b.getText().trim(), int1, int2);
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 403 && event.key == 1008) {
            MS4Java.a();
        }
        return super.handleEvent(event);
    }
    
    public boolean action(final Event event, final Object o) {
        boolean b = false;
        String text = "";
        String text2 = "";
        final String selectedItem = MS4Java.f.e.getSelectedItem();
        if (event.target instanceof Choice) {
            MS4Java.a("UpperControlPanel::action. CHOICE, arg = " + (String)o);
            if (event.target == this.e) {
                final int selectedIndex = this.e.getSelectedIndex();
                if (MS4Java.m()) {
                    MS4Java.i.a();
                }
                if (this.i != -1 && this.b.isEnabled()) {
                    this.g.setElementAt(this.b.getText(), this.i);
                }
                if (this.i != -1 && this.c.isEnabled()) {
                    this.h.setElementAt(this.c.getText(), this.i);
                }
                if (selectedItem == MS4Java.bf[6]) {
                    text = (String)this.g.elementAt(selectedIndex);
                    if (text.equals("")) {
                        text = "20";
                    }
                    text2 = (String)this.h.elementAt(selectedIndex);
                    if (text2.equals("")) {
                        text2 = "2";
                    }
                }
                else if (selectedItem == MS4Java.bf[7]) {
                    text = (String)this.g.elementAt(selectedIndex);
                    if (text.equals("")) {
                        text = "14";
                    }
                }
                else if (selectedItem == MS4Java.bf[8]) {
                    text = (String)this.g.elementAt(selectedIndex);
                    if (text.equals("")) {
                        text = "25";
                    }
                    text2 = (String)this.h.elementAt(selectedIndex);
                    if (text2.equals("")) {
                        text2 = "5";
                    }
                }
                else if (selectedItem == MS4Java.bf[9]) {
                    text = (String)this.g.elementAt(selectedIndex);
                    if (text.equals("")) {
                        text = "9";
                    }
                }
                else if (selectedItem == MS4Java.bf[10]) {
                    text = (String)this.g.elementAt(selectedIndex);
                    if (text.equals("")) {
                        text = "12";
                    }
                }
                else if (selectedItem == MS4Java.bf[11]) {
                    text = (String)this.g.elementAt(selectedIndex);
                    if (text.equals("")) {
                        text = "15";
                    }
                }
                else if (selectedItem == MS4Java.bf[12]) {
                    text = (String)this.g.elementAt(selectedIndex);
                    if (text.equals("")) {
                        text = "20";
                    }
                    text2 = (String)this.h.elementAt(selectedIndex);
                    if (text2.equals("")) {
                        text2 = "50";
                    }
                }
                else if (selectedItem != MS4Java.bf[13]) {
                    if (selectedItem == MS4Java.bf[14]) {
                        text = (String)this.g.elementAt(selectedIndex);
                        if (text.equals("")) {
                            text = "1";
                        }
                        text2 = (String)this.h.elementAt(selectedIndex);
                        if (text2.equals("")) {
                            text2 = "25";
                        }
                    }
                    else if (selectedItem == MS4Java.bf[15]) {
                        text = (String)this.g.elementAt(selectedIndex);
                        if (text.equals("")) {
                            text = "12";
                        }
                    }
                    else if (selectedItem == MS4Java.bf[16]) {
                        text = (String)this.g.elementAt(selectedIndex);
                        if (text.equals("")) {
                            text = "14";
                        }
                    }
                    else if (selectedItem == MS4Java.bf[49]) {
                        text = MS4Java.a.a();
                        if (text.equals("")) {
                            text = MS4Java.au;
                        }
                    }
                    else if (selectedItem == MS4Java.bf[17]) {
                        text = (String)this.g.elementAt(selectedIndex);
                        if (text.equals("")) {
                            text = "5";
                        }
                        text2 = (String)this.h.elementAt(selectedIndex);
                        if (text2.equals("")) {
                            text2 = "3";
                        }
                    }
                    else if (selectedItem == MS4Java.bf[18]) {
                        text = (String)this.g.elementAt(selectedIndex);
                        if (text.equals("")) {
                            text = "1";
                        }
                        text2 = (String)this.h.elementAt(selectedIndex);
                        if (text2.equals("")) {
                            text2 = "25";
                        }
                    }
                    else if (selectedItem == MS4Java.bf[55]) {
                        text = (String)this.g.elementAt(selectedIndex);
                        if (text.equals("")) {
                            text = "14";
                        }
                    }
                }
                this.i = selectedIndex;
                this.b.setText(text);
                this.c.setText(text2);
                if (text.equals("")) {
                    this.b.disable();
                }
                else {
                    this.b.enable();
                }
                if (text2.equals("")) {
                    this.c.disable();
                }
                else {
                    this.c.enable();
                }
                if (this.b.isEnabled()) {
                    this.b.requestFocus();
                    this.b.selectAll();
                }
                b = true;
            }
        }
        if (event.target instanceof TextField) {
            if (event.x == this.b.bounds().x) {
                this.b.selectAll();
            }
            else if (event.x == this.c.bounds().x) {
                this.c.selectAll();
            }
            else if (event.x == this.a.bounds().x) {
                if (MS4Java.m()) {
                    MS4Java.i.a();
                }
                try {
                    this.j.LoadDataInternal();
                }
                catch (Exception ex3) {}
            }
            b = true;
        }
        if (event.target instanceof Button) {
            if (((String)o).equals(MS4Java.bf[19])) {
                try {
                    this.j.LoadDataInternal();
                }
                catch (Exception ex4) {}
                b = true;
            }
            if (((String)o).equals(MS4Java.bf[21])) {
                try {
                    MS4Java.k.getAppletContext().showDocument(new URL(String.valueOf(MS4Java.c()) + this.a.getText()), MS4Java.ay);
                }
                catch (Exception ex) {
                    MS4Java.b(String.valueOf(MS4Java.bf[43]) + "Exception jumping to News: " + ex.toString());
                    ex.printStackTrace();
                }
            }
            if (((String)o).equals(MS4Java.bf[20])) {
                try {
                    MS4Java.k.getAppletContext().showDocument(MS4Java.b(), MS4Java.ax);
                }
                catch (Exception ex2) {
                    MS4Java.b(String.valueOf(MS4Java.bf[43]) + "Exception jumping to Help: " + ex2.toString());
                    ex2.printStackTrace();
                }
            }
        }
        if (b) {
            this.b();
        }
        MS4Java.e.repaint();
        return true;
    }
    
    void a(final String s, final String text, final String text2) {
        this.e.select(s);
        this.i = this.e.getSelectedIndex();
        if (!text.equals("")) {
            this.b.setText(text);
            this.b.enable();
        }
        else {
            this.b.disable();
        }
        if (!text2.equals("")) {
            this.c.setText(text2);
            this.c.enable();
            return;
        }
        this.c.disable();
    }
}
