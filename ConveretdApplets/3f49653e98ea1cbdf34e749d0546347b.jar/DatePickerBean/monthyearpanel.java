// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

import java.awt.event.ItemEvent;
import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Choice;
import java.util.GregorianCalendar;
import java.awt.event.ItemListener;
import java.awt.Panel;

final class monthyearpanel extends Panel implements ItemListener
{
    private boolean flag;
    monthyearleftrightpanel parent;
    private GregorianCalendar tmpdate;
    Choice monthc;
    private String[] months;
    private String[] shortmonths;
    yearpanel yp;
    
    monthyearpanel(final monthyearleftrightpanel parent) {
        this.flag = false;
        this.tmpdate = new GregorianCalendar();
        this.monthc = new Choice();
        this.yp = new yearpanel(this);
        this.parent = parent;
        this.months = new String[12];
        this.shortmonths = new String[12];
        this.months = DPLanguage.getMonthsTranslation(DPLanguage.LANG_ENGLISH, 1);
        this.shortmonths = DPLanguage.getMonthsTranslation(DPLanguage.LANG_ENGLISH, 2);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        this.setLayout(layout);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 1;
        layout.setConstraints(this.monthc, gridBagConstraints);
        this.add(this.monthc);
        for (int i = 0; i < 12; ++i) {
            this.monthc.addItem(this.months[i]);
        }
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        layout.setConstraints(this.yp, gridBagConstraints);
        this.add(this.yp);
        this.monthc.setFont(new Font("SansSerif", 0, 14));
        this.setMonth();
        this.monthc.addItemListener(this);
    }
    
    void afterMonthChanged() {
        this.parent.parent.parent.permitIncrementDecrement();
        this.setMonth();
        this.yp.setYear();
        this.tmpdate.set(this.parent.parent.parent.cYear(), this.parent.parent.parent.cMonth(), 1);
        final int value = this.tmpdate.get(7);
        this.tmpdate.add(2, 1);
        this.tmpdate.add(5, -1);
        final int n = 31 - this.tmpdate.get(5);
        this.parent.parent.parent.mp.Show29to31();
        switch (n) {
            case 3: {
                this.parent.parent.parent.mp.dp[28].setDay(0);
            }
            case 2: {
                this.parent.parent.parent.mp.dp[29].setDay(0);
            }
            case 1: {
                this.parent.parent.parent.mp.dp[30].setDay(0);
                break;
            }
        }
        this.parent.parent.parent.mp.setWeekDays(value);
        this.parent.parent.parent.mp.dp[this.parent.parent.parent.today.get(5) - 1].refresh();
        this.parent.parent.parent.mp.dp[this.parent.parent.parent.mp.lastselectedday - 1].refresh();
        this.parent.parent.parent.mp.dp[this.parent.parent.parent.sDay() - 1].refresh();
    }
    
    void changeLanguage(final int n) {
        final int selectedIndex = this.monthc.getSelectedIndex();
        this.monthc.removeAll();
        this.months = DPLanguage.getMonthsTranslation(n, 1);
        this.shortmonths = DPLanguage.getMonthsTranslation(n, 2);
        for (int i = 0; i < 12; ++i) {
            this.monthc.addItem(this.months[i]);
        }
        this.monthc.select(selectedIndex);
    }
    
    String getMonth(final int n, final int n2) {
        if (n2 == 1) {
            return this.shortmonths[n];
        }
        if (n2 == 2) {
            return this.months[n];
        }
        return "Invalid form";
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.monthSelected();
    }
    
    public void monthSelected() {
        this.parent.parent.parent.setcMonth(this.monthc.getSelectedIndex());
        this.afterMonthChanged();
    }
    
    void setMonth() {
        if (!this.flag) {
            this.monthc.select(4);
            this.flag = true;
        }
        else {
            this.monthc.select(this.parent.parent.parent.cMonth());
        }
    }
}
