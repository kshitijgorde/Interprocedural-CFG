// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.Label;

class dayspanel extends Label implements MouseListener
{
    private mainpanel parent;
    private int day;
    int flag;
    private int[] trackofview;
    Point sp;
    Dimension sd;
    
    dayspanel(final int day, final mainpanel parent) {
        super(Integer.toString(day), 1);
        this.day = 12;
        this.flag = 0;
        this.trackofview = new int[2];
        this.parent = parent;
        this.day = day;
        this.refresh();
        this.addMouseListener(this);
    }
    
    void deselect() {
        this.setForeground(this.parent.getDaysColor());
        if (this.day == this.parent.parent.today.get(5) && this.parent.parent.cMonth() == this.parent.parent.today.get(2) && this.parent.parent.cYear() == this.parent.parent.today.get(1)) {
            this.setFont(new Font("SansSerif", 3, 16));
        }
        else {
            this.setFont(new Font("SansSerif", 0, 12));
        }
    }
    
    void enableDateSelection(final boolean b) {
        if (!b) {
            this.removeMouseListener(this);
        }
        else {
            this.addMouseListener(this);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.day == 0) {
            return;
        }
        this.trackofview = this.parent.parent.isViewMinMaxDate();
        if (this.trackofview[0] == 0 && this.trackofview[2] == 0) {
            this.selectNewDay();
            this.parent.parent.dateSelectionOver();
        }
        else if (this.trackofview[0] == 1) {
            if (this.trackofview[2] == 1) {
                if (this.day >= this.trackofview[1] && this.day <= this.trackofview[3]) {
                    this.selectNewDay();
                    this.parent.parent.dateSelectionOver();
                }
            }
            else if (this.day >= this.trackofview[1]) {
                this.selectNewDay();
                this.parent.parent.dateSelectionOver();
            }
        }
        else if (this.trackofview[2] == 1 && this.day <= this.trackofview[3]) {
            this.selectNewDay();
            this.parent.parent.dateSelectionOver();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    void refresh() {
        this.setBackground(this.parent.parent.getBackground());
        boolean b = false;
        if (this.day == this.parent.parent.today.get(5) && this.parent.parent.cMonth() == this.parent.parent.today.get(2) && this.parent.parent.cYear() == this.parent.parent.today.get(1)) {
            b = true;
        }
        if (this.day == this.parent.parent.sDay() && this.parent.parent.cMonth() == this.parent.parent.sMonth() && this.parent.parent.cYear() == this.parent.parent.sYear()) {
            this.setForeground(this.parent.getSelectionColor());
            if (b) {
                this.setFont(new Font("SansSerif", 3, 16));
            }
            else {
                this.setFont(new Font("SansSerif", 1, 15));
            }
        }
        else {
            this.setForeground(this.parent.getDaysColor());
            if (b) {
                this.setFont(new Font("SansSerif", 3, 16));
            }
            else {
                this.setFont(new Font("SansSerif", 0, 12));
            }
        }
    }
    
    public void selectNewDay() {
        if (this.day != 0 && (this.day != this.parent.parent.sDay() || this.parent.parent.cMonth() != this.parent.parent.sMonth() || this.parent.parent.cYear() != this.parent.parent.sYear())) {
            this.parent.parent.showDateAfterSelection(this.day);
            this.parent.repaintLastSelectedDay();
            this.parent.lastselectedday = this.day;
            this.refresh();
        }
    }
    
    void setDay(final int day) {
        this.day = day;
        if (this.day == 0) {
            this.setText("");
        }
        else {
            this.setText(Integer.toString(this.day));
        }
    }
}
