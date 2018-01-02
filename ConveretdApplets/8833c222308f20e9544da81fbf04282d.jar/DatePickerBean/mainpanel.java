// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Panel;

final class mainpanel extends Panel
{
    calendarpanel parent;
    int lastselectedday;
    private Color weekdayscol;
    private Color dayscol;
    private Color selectioncol;
    dayspanel[] dp;
    weekdayspanel[] wd;
    int wkstart;
    
    mainpanel(final calendarpanel parent) {
        this.lastselectedday = 0;
        this.weekdayscol = Color.red;
        this.dayscol = Color.black;
        this.selectioncol = Color.gray;
        this.dp = new dayspanel[31];
        this.wd = new weekdayspanel[7];
        this.wkstart = -1;
        this.parent = parent;
        this.setLayout(new GridLayout(6, 7));
        for (int i = 0; i < 7; ++i) {
            this.add(this.wd[i] = new weekdayspanel(i, this));
        }
        for (int j = 0; j < 31; ++j) {
            this.add(this.dp[j] = new dayspanel(j + 1, this));
        }
    }
    
    void Show29to31() {
        this.dp[28].setDay(29);
        this.dp[28].repaint();
        this.dp[29].setDay(30);
        this.dp[29].repaint();
        this.dp[30].setDay(31);
        this.dp[30].repaint();
    }
    
    void enableDateSelection(final boolean b) {
        for (int i = 0; i < 31; ++i) {
            this.dp[i].enableDateSelection(b);
        }
    }
    
    Color getDaysColor() {
        return this.dayscol;
    }
    
    Color getSelectionColor() {
        return this.selectioncol;
    }
    
    Color getWeekDaysColor() {
        return this.weekdayscol;
    }
    
    public void refresh() {
        this.repaint();
        for (int i = 0; i < 7; ++i) {
            this.wd[i].refresh();
        }
        for (int j = 0; j < 31; ++j) {
            this.dp[j].refresh();
        }
    }
    
    void repaintLastSelectedDay() {
        if (this.lastselectedday != 0) {
            this.dp[this.lastselectedday - 1].deselect();
        }
    }
    
    void setDaysColor(final Color dayscol) {
        this.dayscol = dayscol;
    }
    
    void setSelectionColor(final Color selectioncol) {
        this.selectioncol = selectioncol;
    }
    
    void setWeekDays(int n) {
        if (this.wkstart == n) {
            return;
        }
        this.wkstart = n;
        --n;
        for (int i = 0; i < 7; ++i, ++n) {
            this.wd[i].setWeekDay(n);
            this.wd[i].repaint();
        }
    }
    
    void setWeekDaysColor(final Color weekdayscol) {
        this.weekdayscol = weekdayscol;
    }
}
