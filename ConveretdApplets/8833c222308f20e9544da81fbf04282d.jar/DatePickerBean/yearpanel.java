// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Choice;
import java.awt.Label;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.Panel;

final class yearpanel extends Panel implements MouseListener, ItemListener
{
    boolean flag;
    private monthyearpanel parent;
    int yearval;
    Label yearl;
    NumericTextField editableyear;
    yearupdown yud;
    Choice validyearlist;
    int first;
    int last;
    Panel year_updownchoice;
    Panel yearchoice;
    boolean yearchoice_view;
    Color yearMouseOverColor;
    Color year_normalcolor;
    
    yearpanel(final monthyearpanel parent) {
        this.flag = false;
        this.yearval = 1977;
        this.yearl = new Label(Integer.toString(this.yearval), 2);
        this.editableyear = new NumericTextField(this, Integer.toString(this.yearval), 4);
        this.validyearlist = new Choice();
        this.year_updownchoice = new Panel();
        this.yearchoice = new Panel();
        this.yearchoice_view = false;
        this.yearMouseOverColor = Color.red;
        this.parent = parent;
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        this.year_updownchoice.setLayout(new BorderLayout());
        this.year_updownchoice.add(this.yearl);
        this.yud = new yearupdown(this);
        this.year_updownchoice.add(this.yud, "East");
        this.yearchoice.setLayout(new CardLayout());
        try {
            this.yearchoice.add(this.year_updownchoice, "year_updownchoice");
            this.yearchoice.add(this.validyearlist, "validyearlist");
        }
        catch (Exception ex) {
            System.out.print("111");
        }
        panel.add(this.yearchoice);
        this.yearl.addMouseListener(this);
        this.validyearlist.addItemListener(this);
        this.add(panel);
        this.add(this.editableyear);
        this.yearl.setFont(new Font("SansSerif", 0, 15));
        this.yearl.setAlignment(1);
        this.validyearlist.setFont(new Font("SansSerif", 0, 15));
        this.yearval = 1977;
        this.yearl.setText(Integer.toString(this.yearval));
        this.editableyear.requestFocus();
    }
    
    void decreaseValue() {
        --this.yearval;
        this.yearl.setText(Integer.toString(this.yearval));
        this.editableyear.setText(this.yearl.getText());
        this.parent.parent.parent.parent.setcYear(this.yearval);
        this.parent.afterMonthChanged();
    }
    
    void increaseValue() {
        ++this.yearval;
        this.yearl.setText(Integer.toString(this.yearval));
        this.editableyear.setText(this.yearl.getText());
        this.parent.parent.parent.parent.setcYear(this.yearval);
        this.parent.afterMonthChanged();
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        ((CardLayout)this.yearchoice.getLayout()).show(this.yearchoice, "year_updownchoice");
        this.yearchoice_view = false;
        this.editableyear.setText(this.validyearlist.getSelectedItem());
        this.setYearThroughEdit();
        this.editableyear.requestFocus();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.year_normalcolor = this.yearl.getForeground();
        this.yearl.setForeground(this.yearMouseOverColor);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.yearMouseOverColor = this.yearl.getForeground();
        this.yearl.setForeground(this.year_normalcolor);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        ((CardLayout)this.yearchoice.getLayout()).show(this.yearchoice, "validyearlist");
        this.yearchoice_view = true;
        this.editableyear.requestFocus();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        this.editableyear.setText(this.yearl.getText());
    }
    
    public void setEditableYearVisible(final boolean b) {
        if (b) {
            this.add(this.editableyear);
        }
        else {
            this.remove(this.editableyear);
        }
    }
    
    public void setValidYearsList(final int first, final int last) {
        if (this.first == first && this.last == last) {
            return;
        }
        this.first = first;
        this.last = last;
        if (this.validyearlist.getItemCount() > 0) {
            this.validyearlist.removeAll();
        }
        for (int i = first; i <= last; ++i) {
            this.validyearlist.add(Integer.toString(i));
        }
        this.validyearlist.select(this.yearl.getText());
    }
    
    void setYear() {
        this.yearval = this.parent.parent.parent.parent.cYear();
        this.yearl.setText(Integer.toString(this.yearval));
        this.validyearlist.select(this.yearl.getText());
        if (this.flag) {
            this.editableyear.setText(this.yearl.getText());
        }
    }
    
    void setYearThroughEdit() {
        final String text = this.editableyear.getText();
        final int length = text.length();
        if (length == 4) {
            this.validyearlist.select(text);
            if (this.validyearlist.getSelectedItem().equals(text)) {
                this.yearl.setText(text);
                this.yearval = Integer.parseInt(this.yearl.getText());
                this.parent.parent.parent.parent.setcYear(this.yearval);
                this.flag = false;
                this.parent.afterMonthChanged();
                this.flag = true;
            }
            return;
        }
        for (int i = 0; i < this.validyearlist.getItemCount(); ++i) {
            final String item = this.validyearlist.getItem(i);
            if (item.length() >= length && item.substring(0, length).equals(text)) {
                this.yearl.setText(this.validyearlist.getItem(i));
                this.validyearlist.select(i);
                this.yearval = Integer.parseInt(this.yearl.getText());
                this.parent.parent.parent.parent.setcYear(this.yearval);
                this.flag = false;
                this.parent.afterMonthChanged();
                this.flag = true;
                break;
            }
        }
    }
}
