// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Label;
import java.util.GregorianCalendar;
import java.awt.Panel;

final class calendarpanel extends Panel
{
    private GregorianCalendar cdate;
    private GregorianCalendar mindate;
    private GregorianCalendar maxdate;
    Label datelabel;
    private GregorianCalendar sdate;
    GregorianCalendar today;
    DatePicker DatePickerObj;
    Panel wst;
    basepanel bp;
    toppanel tp;
    mainpanel mp;
    
    calendarpanel(final DatePicker datePickerObj) {
        this.cdate = new GregorianCalendar();
        this.mindate = new GregorianCalendar();
        this.maxdate = new GregorianCalendar();
        this.datelabel = new Label("DATE", 1);
        this.sdate = new GregorianCalendar();
        this.today = new GregorianCalendar();
        this.wst = new Panel();
        this.mindate.set(this.cdate.get(1) - 1, 0, 1);
        this.maxdate.set(this.cdate.get(1) + 1, 11, 31);
        this.bp = new basepanel(this);
        this.tp = new toppanel(this);
        this.mp = new mainpanel(this);
        this.cdate.set(this.cdate.get(1), this.cdate.get(2), 1);
        this.setLayout(new BorderLayout());
        this.add(this.mp, "Center");
        this.add(this.tp, "South");
        this.add(this.bp, "North");
        this.add(this.wst, "West");
        this.mp.lastselectedday = this.cDay();
        this.bp.mylrp.myp.afterMonthChanged();
        this.showDateAfterSelection(this.cDay());
        this.sdate = new GregorianCalendar(this.cYear(), this.cMonth(), this.cDay());
        this.mp.lastselectedday = this.sDay();
        this.DatePickerObj = datePickerObj;
        this.bp.mylrp.myp.yp.setValidYearsList(this.mindate.get(1), this.maxdate.get(1));
        this.bp.mylrp.myp.yp.editableyear.requestFocus();
        this.datelabel.setFont(new Font("SansSerif", 0, 12));
    }
    
    int cDay() {
        return this.cdate.get(5);
    }
    
    int cMonth() {
        return this.cdate.get(2);
    }
    
    int cYear() {
        return this.cdate.get(1);
    }
    
    public String changeLanguage(final int n) {
        this.tp.mmp.lang = n;
        this.tp.mmp.refresh();
        this.tp.sp.lang = n;
        this.tp.sp.refresh();
        this.bp.mylrp.myp.changeLanguage(n);
        for (int i = 0; i < 7; ++i) {
            this.mp.wd[i].changeLanguage(n);
        }
        this.setDateFormat();
        return this.datelabel.getText();
    }
    
    String dateSelectionOver() {
        this.DatePickerObj.NewDateSelected(this.datelabel.getText());
        return this.datelabel.getText();
    }
    
    void decreaseMonth() {
        this.cdate.add(2, -1);
        this.bp.mylrp.myp.setMonth();
        this.bp.mylrp.myp.afterMonthChanged();
        this.bp.mylrp.myp.yp.flag = true;
        this.bp.mylrp.myp.yp.setYear();
        this.bp.mylrp.myp.yp.flag = false;
    }
    
    void enableDateSelection(final boolean b) {
        this.mp.enableDateSelection(b);
    }
    
    GregorianCalendar getDate() {
        return this.sdate;
    }
    
    String getDateFormat(final GregorianCalendar gregorianCalendar, final int n) {
        final int value = gregorianCalendar.get(5);
        final int value2 = gregorianCalendar.get(2);
        final int value3 = gregorianCalendar.get(1);
        String s = null;
        switch (n) {
            case 1: {
                s = String.valueOf(value) + " / " + (value2 + 1) + " / " + value3;
                break;
            }
            case 2: {
                s = String.valueOf(value2 + 1) + " / " + value + " / " + value3;
                break;
            }
            case 3: {
                s = String.valueOf(value) + " " + this.bp.mylrp.myp.getMonth(value2, 1) + " " + value3;
                break;
            }
            case 4: {
                s = String.valueOf(value) + " " + this.bp.mylrp.myp.getMonth(value2, 2) + ", " + value3;
                break;
            }
            case 5: {
                s = String.valueOf(this.bp.mylrp.myp.getMonth(value2, 1)) + " " + value + ", " + value3;
                break;
            }
            case 6: {
                s = String.valueOf(this.bp.mylrp.myp.getMonth(value2, 2)) + " " + value + ", " + value3;
                break;
            }
            case 7: {
                s = String.valueOf(this.mp.wd[0].getWeekDay(gregorianCalendar.get(7) - 1)) + ", " + value + " " + this.bp.mylrp.myp.getMonth(value2, 1) + " " + value3;
                break;
            }
            case 8: {
                s = String.valueOf(this.mp.wd[0].getWeekDay(gregorianCalendar.get(7) - 1)) + ", " + value + " " + this.bp.mylrp.myp.getMonth(value2, 2) + ", " + value3;
                break;
            }
            case 9: {
                s = String.valueOf(this.mp.wd[0].getWeekDay(gregorianCalendar.get(7) - 1)) + ", " + this.bp.mylrp.myp.getMonth(value2, 1) + " " + value + ", " + value3;
                break;
            }
            case 10: {
                s = String.valueOf(this.mp.wd[0].getWeekDay(gregorianCalendar.get(7) - 1)) + ", " + this.bp.mylrp.myp.getMonth(value2, 2) + " " + value + ", " + value3;
                break;
            }
            case 11: {
                s = String.valueOf(this.mp.wd[0].getWeekDay(gregorianCalendar.get(7) - 1).substring(0, 3)) + ", " + value + " " + this.bp.mylrp.myp.getMonth(value2, 1) + " " + value3;
                break;
            }
            case 12: {
                s = String.valueOf(this.mp.wd[0].getWeekDay(gregorianCalendar.get(7) - 1).substring(0, 3)) + ", " + value + " " + this.bp.mylrp.myp.getMonth(value2, 2) + ", " + value3;
                break;
            }
            case 13: {
                s = String.valueOf(this.mp.wd[0].getWeekDay(gregorianCalendar.get(7) - 1).substring(0, 3)) + ", " + this.bp.mylrp.myp.getMonth(value2, 1) + " " + value + ", " + value3;
                break;
            }
            case 14: {
                s = String.valueOf(this.mp.wd[0].getWeekDay(gregorianCalendar.get(7) - 1).substring(0, 3)) + ", " + this.bp.mylrp.myp.getMonth(value2, 2) + " " + value + ", " + value3;
                break;
            }
            default: {
                s = String.valueOf(this.mp.wd[0].getWeekDay(gregorianCalendar.get(7) - 1).substring(0, 3)) + ", " + this.bp.mylrp.myp.getMonth(value2, 1) + " " + value + " " + value3;
                break;
            }
        }
        return s;
    }
    
    GregorianCalendar getMaxDate() {
        return this.maxdate;
    }
    
    GregorianCalendar getMinDate() {
        return this.mindate;
    }
    
    String getMinMaxRange() {
        return String.valueOf(this.bp.mylrp.myp.getMonth(this.mindate.get(2), 1)) + " " + this.mindate.get(5) + ",  " + this.mindate.get(1) + " - " + this.bp.mylrp.myp.getMonth(this.maxdate.get(2), 1) + " " + this.maxdate.get(5) + ",  " + this.maxdate.get(1);
    }
    
    String getTodayFormatted() {
        return String.valueOf(this.bp.mylrp.myp.getMonth(this.today.get(2), 1)) + " " + this.today.get(5) + ",  " + this.today.get(1);
    }
    
    void increaseMonth() {
        this.cdate.add(2, 1);
        this.bp.mylrp.myp.setMonth();
        this.bp.mylrp.myp.afterMonthChanged();
        this.bp.mylrp.myp.yp.flag = true;
        this.bp.mylrp.myp.yp.setYear();
        this.bp.mylrp.myp.yp.flag = false;
    }
    
    int[] isViewMinMaxDate() {
        final int[] array = new int[4];
        if (this.cMonth() == this.mindate.get(2) && this.cYear() == this.mindate.get(1)) {
            array[array[0] = 1] = this.mindate.get(5);
        }
        if (this.cMonth() == this.maxdate.get(2) && this.cYear() == this.maxdate.get(1)) {
            array[2] = 1;
            array[3] = this.maxdate.get(5);
        }
        return array;
    }
    
    void permitIncrementDecrement() {
        if (this.cdate.before(this.mindate)) {
            this.cdate.set(this.mindate.get(1), this.mindate.get(2), this.mindate.get(5));
        }
        else if (this.cdate.after(this.maxdate)) {
            this.cdate.set(this.maxdate.get(1), this.maxdate.get(2), this.maxdate.get(5));
        }
    }
    
    void refreshCalendar() {
        this.repaint();
        this.mp.refresh();
        this.tp.mmp.refresh();
        this.tp.sp.refresh();
        this.wst.repaint();
        this.bp.mylrp.myp.repaint();
        this.bp.mylrp.myp.yp.repaint();
        this.mp.dp[this.today.get(5) - 1].refresh();
        this.mp.dp[this.mp.lastselectedday - 1].refresh();
    }
    
    int sDay() {
        return this.sdate.get(5);
    }
    
    int sMonth() {
        return this.sdate.get(2);
    }
    
    int sYear() {
        return this.sdate.get(1);
    }
    
    void setColors(final Color color, final Color arrowColor, final Color foreground, final Color daysColor, final Color foreground2, final Color foreground3, final Color yearMouseOverColor, final Color color2) {
        this.setBackground(color);
        this.bp.mylrp.myp.monthc.setBackground(color);
        this.bp.mylrp.myp.yp.yearl.setBackground(color);
        this.bp.mylrp.myp.yp.validyearlist.setBackground(color);
        this.bp.mylrp.myp.yp.editableyear.setBackground(color);
        this.datelabel.setBackground(color);
        this.bp.mylrp.mybl.setBackground(color);
        this.bp.mylrp.mybr.setBackground(color);
        this.bp.mylrp.mybr.setArrowColor(arrowColor);
        this.bp.mylrp.mybl.setArrowColor(arrowColor);
        this.bp.mylrp.myp.yp.yud.setArrowColor(arrowColor);
        this.mp.setWeekDaysColor(foreground);
        this.tp.mmp.setColor(foreground);
        this.tp.sp.setForeground(foreground);
        this.mp.setDaysColor(daysColor);
        this.bp.mylrp.myp.monthc.setForeground(foreground2);
        this.bp.mylrp.myp.yp.yearl.setForeground(foreground3);
        this.bp.mylrp.myp.yp.validyearlist.setForeground(foreground3);
        this.bp.mylrp.myp.yp.editableyear.setForeground(foreground3);
        this.bp.mylrp.myp.yp.yearMouseOverColor = yearMouseOverColor;
        this.mp.setSelectionColor(color2);
        this.datelabel.setForeground(color2);
        this.refreshCalendar();
    }
    
    void setCurrent() {
        if (this.cYear() == this.sYear() && this.cMonth() == this.sMonth()) {
            return;
        }
        this.bp.mylrp.myp.yp.editableyear.setText(Integer.toString(this.sYear()));
        this.bp.mylrp.myp.yp.setYearThroughEdit();
        this.bp.mylrp.myp.monthc.select(this.sMonth());
        this.bp.mylrp.myp.monthSelected();
    }
    
    void setCurrentView() {
        this.setDate(this.sYear(), this.sMonth(), this.sDay());
    }
    
    void setDate(final int n, final int n2, final int n3) {
        this.setcDate(n, n2 - 1, n3);
        this.bp.mylrp.myp.afterMonthChanged();
        this.showDateAfterSelection(n3);
        this.mp.repaintLastSelectedDay();
        this.mp.dp[this.today.get(5) - 1].refresh();
        this.mp.dp[this.sDay() - 1].refresh();
        this.mp.lastselectedday = this.sDay();
    }
    
    String setDateFormat() {
        int dateFormat;
        try {
            dateFormat = this.DatePickerObj.getDateFormat();
        }
        catch (NullPointerException ex) {
            dateFormat = 13;
        }
        final String dateFormat2 = this.getDateFormat(this.sdate, dateFormat);
        this.datelabel.setText(dateFormat2);
        return dateFormat2;
    }
    
    public void setEditableYearVisible(final boolean editableYearVisible) {
        this.bp.mylrp.myp.yp.setEditableYearVisible(editableYearVisible);
    }
    
    void setMaxDate(final GregorianCalendar maxdate) {
        this.maxdate = maxdate;
        this.bp.mylrp.myp.yp.setValidYearsList(this.mindate.get(1), this.maxdate.get(1));
        this.tp.mmp.refresh();
    }
    
    void setMinDate(final GregorianCalendar mindate) {
        this.mindate = mindate;
        this.bp.mylrp.myp.yp.setValidYearsList(this.mindate.get(1), this.maxdate.get(1));
        this.tp.mmp.refresh();
    }
    
    void setToday() {
        if (this.cYear() == this.today.get(1) && this.cMonth() == this.today.get(2)) {
            return;
        }
        if ((this.today.after(this.mindate) && this.today.before(this.maxdate)) || (this.sYear() == this.today.get(1) && this.sMonth() == this.today.get(2))) {
            this.bp.mylrp.myp.yp.editableyear.setText(Integer.toString(this.today.get(1)));
            this.bp.mylrp.myp.yp.setYearThroughEdit();
            this.bp.mylrp.myp.monthc.select(this.today.get(2));
            this.bp.mylrp.myp.monthSelected();
        }
    }
    
    void setcDate(final int n, final int n2, final int n3) {
        this.cdate.set(n, n2, n3);
    }
    
    void setcDay(final int n) {
        this.cdate.set(this.cdate.get(1), this.cdate.get(2), n);
    }
    
    void setcMonth(final int n) {
        this.cdate.set(this.cdate.get(1), n, this.cdate.get(5));
    }
    
    void setcYear(final int n) {
        this.cdate.set(n, this.cdate.get(2), this.cdate.get(5));
    }
    
    void showDateAfterSelection(final int n) {
        this.sdate.set(this.cYear(), this.cMonth(), n);
        this.setDateFormat();
    }
    
    public void showInCalendar(final boolean b, final boolean b2) {
        this.tp.showInCalendar(b, b2);
        this.refreshCalendar();
    }
}
