// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

import java.awt.Font;
import java.awt.Label;

class weekdayspanel extends Label
{
    String[] weekdays;
    String[] longweekdays;
    private mainpanel parent;
    private int weekday;
    
    weekdayspanel(final int weekday, final mainpanel parent) {
        this.weekday = weekday;
        this.weekdays = new String[7];
        this.longweekdays = new String[7];
        this.longweekdays = DPLanguage.getWeekdaysTranslation(DPLanguage.LANG_ENGLISH, 1);
        this.weekdays = DPLanguage.getWeekdaysTranslation(DPLanguage.LANG_ENGLISH, 2);
        this.parent = parent;
        this.setFont(new Font("SansSerif", 0, 12));
    }
    
    void changeLanguage(final int n) {
        this.longweekdays = DPLanguage.getWeekdaysTranslation(n, 1);
        this.weekdays = DPLanguage.getWeekdaysTranslation(n, 2);
        this.refresh();
    }
    
    String getWeekDay(final int n) {
        return this.longweekdays[n];
    }
    
    public void refresh() {
        this.setForeground(this.parent.getWeekDaysColor());
        this.setBackground(this.parent.parent.getBackground());
        this.setText(this.weekdays[this.weekday]);
    }
    
    void setWeekDay(final int weekday) {
        this.weekday = weekday;
        if (weekday > 6) {
            this.weekday = weekday - 7;
        }
        this.setForeground(this.parent.getWeekDaysColor());
        this.setBackground(this.parent.parent.getBackground());
        this.setText(this.weekdays[this.weekday]);
        this.setAlignment(1);
    }
}
