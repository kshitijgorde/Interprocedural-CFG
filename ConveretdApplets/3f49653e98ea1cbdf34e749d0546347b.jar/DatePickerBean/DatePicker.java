// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

import java.awt.Dialog;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.Frame;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Button;

public class DatePicker extends Button
{
    public static final int dd_mm_yyyy = 1;
    public static final int mm_dd_yyyy = 2;
    public static final int dd_mmm_yyyy = 3;
    public static final int dd_mmmm_yyyy = 4;
    public static final int mmm_dd_yyyy = 5;
    public static final int mmmm_dd_yyyy = 6;
    public static final int ldow_dd_mmm_yyyy = 7;
    public static final int ldow_dd_mmmm_yyyy = 8;
    public static final int ldow_mmm_dd_yyyy = 9;
    public static final int ldow_mmmm_dd_yyyy = 10;
    public static final int sdow_dd_mmm_yyyy = 11;
    public static final int sdow_dd_mmmm_yyyy = 12;
    public static final int sdow_mmm_dd_yyyy = 13;
    public static final int sdow_mmmm_dd_yyyy = 14;
    private int dateformat;
    public static final int LANG_ENGLISH = 101;
    public static final int LANG_FRENCH = 102;
    public static final int LANG_FINNISH = 103;
    int language;
    calendarpanel calpan;
    boolean CalModal;
    CalWin cwin;
    boolean showtoday;
    boolean show_date_range;
    int alterheight;
    private Color ArrowColor;
    private Color CalendarBackground;
    private Color CalendarArrowColor;
    private Color CalendarWeekdaysColor;
    private Color CalendarDaysColor;
    private Color CalendarMonthColor;
    private Color CalendarYearColor;
    private Color CalendarMouseOverYearColor;
    private Color CalendarSelectionColor;
    private boolean EditableYearVisible;
    private int CalWinWidth_EditableYearVisible;
    private int CalWinWidth_EditableYearNotVisible;
    private int CalWinWidth;
    private int CalWinHeightNormal;
    private int CalWinHeightApplet;
    private int CalWinHeight;
    String calendarTitle;
    private Rectangle parentrect;
    private Rectangle rect;
    private boolean appletstatus;
    private boolean browserstatus;
    private GregorianCalendar vdt;
    private boolean AutoCalendarPosition;
    DatePickerListenerHandler dphl;
    int calendar_x;
    int calendar_y;
    private boolean autoresize_dpbutton;
    boolean dateSelection;
    
    public DatePicker() {
        this.dateformat = 13;
        this.calpan = new calendarpanel(this);
        this.CalModal = false;
        this.showtoday = true;
        this.show_date_range = true;
        this.alterheight = 0;
        this.ArrowColor = Color.lightGray;
        this.CalendarBackground = Color.white;
        this.CalendarArrowColor = Color.gray;
        this.CalendarWeekdaysColor = Color.blue;
        this.CalendarDaysColor = Color.black;
        this.CalendarMonthColor = Color.black;
        this.CalendarYearColor = Color.black;
        this.CalendarMouseOverYearColor = Color.red;
        this.CalendarSelectionColor = Color.red;
        this.EditableYearVisible = true;
        this.CalWinWidth_EditableYearVisible = 290;
        this.CalWinWidth_EditableYearNotVisible = 260;
        this.CalWinWidth = this.CalWinWidth_EditableYearVisible;
        this.CalWinHeightNormal = 290;
        this.CalWinHeightApplet = 310;
        this.CalWinHeight = this.CalWinHeightNormal;
        this.appletstatus = false;
        this.browserstatus = false;
        this.vdt = new GregorianCalendar();
        this.AutoCalendarPosition = true;
        this.calendar_x = 0;
        this.calendar_y = 0;
        this.autoresize_dpbutton = false;
        this.dateSelection = true;
        this.init();
    }
    
    public DatePicker(final int n, final int n2, final int n3) throws DatePickerException {
        this.dateformat = 13;
        this.calpan = new calendarpanel(this);
        this.CalModal = false;
        this.showtoday = true;
        this.show_date_range = true;
        this.alterheight = 0;
        this.ArrowColor = Color.lightGray;
        this.CalendarBackground = Color.white;
        this.CalendarArrowColor = Color.gray;
        this.CalendarWeekdaysColor = Color.blue;
        this.CalendarDaysColor = Color.black;
        this.CalendarMonthColor = Color.black;
        this.CalendarYearColor = Color.black;
        this.CalendarMouseOverYearColor = Color.red;
        this.CalendarSelectionColor = Color.red;
        this.EditableYearVisible = true;
        this.CalWinWidth_EditableYearVisible = 290;
        this.CalWinWidth_EditableYearNotVisible = 260;
        this.CalWinWidth = this.CalWinWidth_EditableYearVisible;
        this.CalWinHeightNormal = 290;
        this.CalWinHeightApplet = 310;
        this.CalWinHeight = this.CalWinHeightNormal;
        this.appletstatus = false;
        this.browserstatus = false;
        this.vdt = new GregorianCalendar();
        this.AutoCalendarPosition = true;
        this.calendar_x = 0;
        this.calendar_y = 0;
        this.autoresize_dpbutton = false;
        this.dateSelection = true;
        this.setDate(n, n2, n3);
        this.init();
    }
    
    public DatePicker(final Date date) throws DatePickerException {
        this.dateformat = 13;
        this.calpan = new calendarpanel(this);
        this.CalModal = false;
        this.showtoday = true;
        this.show_date_range = true;
        this.alterheight = 0;
        this.ArrowColor = Color.lightGray;
        this.CalendarBackground = Color.white;
        this.CalendarArrowColor = Color.gray;
        this.CalendarWeekdaysColor = Color.blue;
        this.CalendarDaysColor = Color.black;
        this.CalendarMonthColor = Color.black;
        this.CalendarYearColor = Color.black;
        this.CalendarMouseOverYearColor = Color.red;
        this.CalendarSelectionColor = Color.red;
        this.EditableYearVisible = true;
        this.CalWinWidth_EditableYearVisible = 290;
        this.CalWinWidth_EditableYearNotVisible = 260;
        this.CalWinWidth = this.CalWinWidth_EditableYearVisible;
        this.CalWinHeightNormal = 290;
        this.CalWinHeightApplet = 310;
        this.CalWinHeight = this.CalWinHeightNormal;
        this.appletstatus = false;
        this.browserstatus = false;
        this.vdt = new GregorianCalendar();
        this.AutoCalendarPosition = true;
        this.calendar_x = 0;
        this.calendar_y = 0;
        this.autoresize_dpbutton = false;
        this.dateSelection = true;
        this.setDate(date);
        this.init();
    }
    
    public DatePicker(final Date maxDate, final Date date, final Date date2) throws DatePickerException {
        this.dateformat = 13;
        this.calpan = new calendarpanel(this);
        this.CalModal = false;
        this.showtoday = true;
        this.show_date_range = true;
        this.alterheight = 0;
        this.ArrowColor = Color.lightGray;
        this.CalendarBackground = Color.white;
        this.CalendarArrowColor = Color.gray;
        this.CalendarWeekdaysColor = Color.blue;
        this.CalendarDaysColor = Color.black;
        this.CalendarMonthColor = Color.black;
        this.CalendarYearColor = Color.black;
        this.CalendarMouseOverYearColor = Color.red;
        this.CalendarSelectionColor = Color.red;
        this.EditableYearVisible = true;
        this.CalWinWidth_EditableYearVisible = 290;
        this.CalWinWidth_EditableYearNotVisible = 260;
        this.CalWinWidth = this.CalWinWidth_EditableYearVisible;
        this.CalWinHeightNormal = 290;
        this.CalWinHeightApplet = 310;
        this.CalWinHeight = this.CalWinHeightNormal;
        this.appletstatus = false;
        this.browserstatus = false;
        this.vdt = new GregorianCalendar();
        this.AutoCalendarPosition = true;
        this.calendar_x = 0;
        this.calendar_y = 0;
        this.autoresize_dpbutton = false;
        this.dateSelection = true;
        this.setDate(maxDate);
        this.setMinDate(maxDate);
        this.setMaxDate(maxDate);
        this.init();
    }
    
    public DatePicker(final GregorianCalendar date) throws DatePickerException {
        this.dateformat = 13;
        this.calpan = new calendarpanel(this);
        this.CalModal = false;
        this.showtoday = true;
        this.show_date_range = true;
        this.alterheight = 0;
        this.ArrowColor = Color.lightGray;
        this.CalendarBackground = Color.white;
        this.CalendarArrowColor = Color.gray;
        this.CalendarWeekdaysColor = Color.blue;
        this.CalendarDaysColor = Color.black;
        this.CalendarMonthColor = Color.black;
        this.CalendarYearColor = Color.black;
        this.CalendarMouseOverYearColor = Color.red;
        this.CalendarSelectionColor = Color.red;
        this.EditableYearVisible = true;
        this.CalWinWidth_EditableYearVisible = 290;
        this.CalWinWidth_EditableYearNotVisible = 260;
        this.CalWinWidth = this.CalWinWidth_EditableYearVisible;
        this.CalWinHeightNormal = 290;
        this.CalWinHeightApplet = 310;
        this.CalWinHeight = this.CalWinHeightNormal;
        this.appletstatus = false;
        this.browserstatus = false;
        this.vdt = new GregorianCalendar();
        this.AutoCalendarPosition = true;
        this.calendar_x = 0;
        this.calendar_y = 0;
        this.autoresize_dpbutton = false;
        this.dateSelection = true;
        this.setDate(date);
        this.init();
    }
    
    public DatePicker(final GregorianCalendar maxDate, final GregorianCalendar gregorianCalendar, final GregorianCalendar gregorianCalendar2) throws DatePickerException {
        this.dateformat = 13;
        this.calpan = new calendarpanel(this);
        this.CalModal = false;
        this.showtoday = true;
        this.show_date_range = true;
        this.alterheight = 0;
        this.ArrowColor = Color.lightGray;
        this.CalendarBackground = Color.white;
        this.CalendarArrowColor = Color.gray;
        this.CalendarWeekdaysColor = Color.blue;
        this.CalendarDaysColor = Color.black;
        this.CalendarMonthColor = Color.black;
        this.CalendarYearColor = Color.black;
        this.CalendarMouseOverYearColor = Color.red;
        this.CalendarSelectionColor = Color.red;
        this.EditableYearVisible = true;
        this.CalWinWidth_EditableYearVisible = 290;
        this.CalWinWidth_EditableYearNotVisible = 260;
        this.CalWinWidth = this.CalWinWidth_EditableYearVisible;
        this.CalWinHeightNormal = 290;
        this.CalWinHeightApplet = 310;
        this.CalWinHeight = this.CalWinHeightNormal;
        this.appletstatus = false;
        this.browserstatus = false;
        this.vdt = new GregorianCalendar();
        this.AutoCalendarPosition = true;
        this.calendar_x = 0;
        this.calendar_y = 0;
        this.autoresize_dpbutton = false;
        this.dateSelection = true;
        this.setDate(maxDate);
        this.setMinDate(maxDate);
        this.setMaxDate(maxDate);
        this.init();
    }
    
    public static int CompareDates(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        if (n < n4) {
            return -1;
        }
        if (n > n4) {
            return 1;
        }
        if (n2 < n5) {
            return -1;
        }
        if (n2 > n5) {
            return 1;
        }
        if (n3 < n6) {
            return -1;
        }
        if (n3 > n6) {
            return 1;
        }
        return 0;
    }
    
    public static int CompareDates(final GregorianCalendar gregorianCalendar, final GregorianCalendar gregorianCalendar2) {
        return CompareDates(gregorianCalendar.get(1), gregorianCalendar.get(2), gregorianCalendar.get(5), gregorianCalendar2.get(1), gregorianCalendar2.get(2), gregorianCalendar2.get(5));
    }
    
    void NewDateSelected(final String s) {
        this.showDate(s);
        this.dphl.FireNewDateSelected();
    }
    
    public int addDatePickerListener(final DatePickerListener datePickerListener) {
        return this.dphl.addDatePickerListener(datePickerListener);
    }
    
    public boolean autoResizeDPButton() {
        return this.autoresize_dpbutton;
    }
    
    public void autoResizeDPButton(final boolean autoresize_dpbutton) {
        this.autoresize_dpbutton = autoresize_dpbutton;
    }
    
    public void enableDateSelection(final boolean dateSelection) {
        this.dateSelection = dateSelection;
        this.calpan.enableDateSelection(dateSelection);
    }
    
    public Color getArrowColor() {
        return this.ArrowColor;
    }
    
    public boolean getAutoCalendarPosition() {
        return this.AutoCalendarPosition;
    }
    
    public Color getCalendarArrowColor() {
        return this.CalendarArrowColor;
    }
    
    public Color getCalendarBackground() {
        return this.CalendarBackground;
    }
    
    public Color getCalendarDaysColor() {
        return this.CalendarDaysColor;
    }
    
    public int getCalendarHeight() {
        return this.CalWinHeight - this.alterheight;
    }
    
    public Color getCalendarMonthColor() {
        return this.CalendarMonthColor;
    }
    
    public Color getCalendarMouseOverYear_Color() {
        return this.CalendarMouseOverYearColor;
    }
    
    public Color getCalendarSelectionColor() {
        return this.CalendarSelectionColor;
    }
    
    public String getCalendarTitle() {
        return this.cwin.getTitle();
    }
    
    public Color getCalendarWeekdaysColor() {
        return this.CalendarWeekdaysColor;
    }
    
    public int getCalendarWidth() {
        return this.CalWinWidth;
    }
    
    public Color getCalendarYearColor() {
        return this.CalendarYearColor;
    }
    
    public boolean getCalendar_Modal() {
        return this.CalModal;
    }
    
    public String getDOW(final GregorianCalendar gregorianCalendar) {
        return this.calpan.mp.wd[0].getWeekDay(gregorianCalendar.get(7) - 1);
    }
    
    public GregorianCalendar getDate() {
        final GregorianCalendar date = this.calpan.getDate();
        return new GregorianCalendar(date.get(1), date.get(2), date.get(5));
    }
    
    public int getDateFormat() {
        return this.dateformat;
    }
    
    public DatePicker getDateValues() {
        return this;
    }
    
    public int getDay() {
        return this.getDayFrom(this.getDate());
    }
    
    public int getDayFrom(final GregorianCalendar gregorianCalendar) {
        return gregorianCalendar.get(5);
    }
    
    public static int getDaysBetween(final GregorianCalendar gregorianCalendar, final GregorianCalendar gregorianCalendar2) {
        final int compareDates = CompareDates(gregorianCalendar, gregorianCalendar2);
        int n4;
        int n3;
        int n2;
        int n = n2 = (n3 = (n4 = 0));
        if (compareDates == -1) {
            n2 = gregorianCalendar.get(1);
            n3 = gregorianCalendar.get(6);
            n = gregorianCalendar2.get(1);
            n4 = gregorianCalendar2.get(6);
        }
        else {
            if (compareDates == 0) {
                return 0;
            }
            if (compareDates == 1) {
                n2 = gregorianCalendar2.get(1);
                n3 = gregorianCalendar2.get(6);
                n = gregorianCalendar.get(1);
                n4 = gregorianCalendar.get(6);
            }
        }
        int n5 = 0;
        if (n2 == n) {
            n5 = n4 - n3;
        }
        else if (n - n2 == 1) {
            n5 = getNumberOfDaysInYear(n2) - n3 + n4;
        }
        else if (n - n2 > 1) {
            int n6 = getNumberOfDaysInYear(n2) - n3;
            for (int i = n2 + 1; i < n; ++i) {
                n6 += getNumberOfDaysInYear(i);
            }
            n5 = n6 + n4;
        }
        return n5;
    }
    
    public int getDisplayLanguage() {
        return this.language;
    }
    
    public boolean getEditableYearVisible() {
        return this.EditableYearVisible;
    }
    
    public String getFormat() {
        String s = null;
        switch (this.dateformat) {
            case 1: {
                s = "dd_mm_yyyy";
                break;
            }
            case 2: {
                s = "mm_dd_yyyy";
                break;
            }
            case 3: {
                s = "dd_mmm_yyyy";
                break;
            }
            case 4: {
                s = "dd_mmmm_yyyy";
                break;
            }
            case 5: {
                s = "mmm_dd_yyyy";
                break;
            }
            case 6: {
                s = "mmmm_dd_yyyy";
                break;
            }
            case 7: {
                s = "ldow_dd_mmm_yyyy";
                break;
            }
            case 8: {
                s = "ldow_dd_mmmm_yyyy";
                break;
            }
            case 9: {
                s = "ldow_mmm_dd_yyyy";
                break;
            }
            case 10: {
                s = "ldow_mmmm_dd_yyyy";
                break;
            }
            case 11: {
                s = "sdow_dd_mmm_yyyy";
                break;
            }
            case 12: {
                s = "sdow_dd_mmmm_yyyy";
                break;
            }
            case 13: {
                s = "sdow_mmm_dd_yyyy";
                break;
            }
            case 14: {
                s = "sdow_mmmm_dd_yyyy";
                break;
            }
            default: {
                s = "sdow_mmm_dd_yyyy";
                break;
            }
        }
        if (this.getDateFormat() <= 9) {
            return "0" + this.getDateFormat() + ". " + s;
        }
        return this.getDateFormat() + ". " + s;
    }
    
    public String getFormattedDate() {
        return this.calpan.getDateFormat(this.getDate(), this.dateformat);
    }
    
    public String getFormattedDate(final GregorianCalendar gregorianCalendar, final int n) {
        return this.calpan.getDateFormat(gregorianCalendar, n);
    }
    
    public GregorianCalendar getMaxDate() {
        final GregorianCalendar maxDate = this.calpan.getMaxDate();
        return new GregorianCalendar(maxDate.get(1), maxDate.get(2), maxDate.get(5));
    }
    
    public GregorianCalendar getMinDate() {
        final GregorianCalendar minDate = this.calpan.getMinDate();
        return new GregorianCalendar(minDate.get(1), minDate.get(2), minDate.get(5));
    }
    
    public int getMonth() {
        return this.getMonthFrom(this.getDate());
    }
    
    public int getMonthFrom(final GregorianCalendar gregorianCalendar) {
        return gregorianCalendar.get(2) + 1;
    }
    
    public String getMonthStr(final GregorianCalendar gregorianCalendar) {
        return this.calpan.bp.mylrp.myp.getMonth(gregorianCalendar.get(2), 2);
    }
    
    public static int getNumberOfDaysInYear(final int n) {
        if (n % 4 == 0 && (n % 100 != 0 || n % 400 == 0)) {
            return 366;
        }
        return 365;
    }
    
    public boolean getShowDateRangeInCalendar() {
        return this.show_date_range;
    }
    
    public boolean getShowTodayInCalendar() {
        return this.showtoday;
    }
    
    public int getYear() {
        return this.getYearFrom(this.getDate());
    }
    
    public int getYearFrom(final GregorianCalendar gregorianCalendar) {
        return gregorianCalendar.get(1);
    }
    
    public boolean inApplet() {
        return this.appletstatus;
    }
    
    public void inApplet(final boolean appletstatus) {
        this.appletstatus = appletstatus;
        if (appletstatus) {
            this.CalWinHeight = this.CalWinHeightApplet;
        }
        else {
            this.CalWinHeight = this.CalWinHeightNormal;
        }
    }
    
    public boolean inBrowser() {
        return this.browserstatus;
    }
    
    public void inBrowser(final boolean browserstatus) {
        this.browserstatus = browserstatus;
    }
    
    private void init() {
        this.cwin = new CalWin(new Frame(), this.CalModal, this.calpan);
        this.setBackground(Color.white);
        this.setForeground(Color.gray);
        this.language = 101;
        final String string = String.valueOf((new String[] { "Sun", "Mon", "Tue", "Wed", " Thu", " Fri", "Sat" })[this.getDate().get(7) - 1]) + ", " + (new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" })[this.getMonth() - 1] + " " + this.getDay() + ", " + this.getYear();
        this.dateformat = 13;
        this.showDate(string);
        this.addActionListener(this.dphl = new DatePickerListenerHandler(this));
    }
    
    private boolean isDateValid(final int n, int n2, final int n3) {
        --n2;
        this.vdt.set(n, n2, n3);
        return n == this.vdt.get(1) && n2 == this.vdt.get(2) && n3 == this.vdt.get(5);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.ArrowColor);
        final Dimension size = this.getSize();
        final int width = size.width;
        final int height = size.height;
        graphics.fillPolygon(new int[] { width * 80 / 100, width * 94 / 100, width * 87 / 100 }, new int[] { height * 32 / 100, height * 32 / 100, height * 70 / 100 }, 3);
    }
    
    void refreshCalendar() {
        final Point locationOnScreen = this.cwin.getLocationOnScreen();
        this.calendar_x = locationOnScreen.x;
        this.calendar_y = locationOnScreen.y;
        this.dphl.showCalendar(this.calendar_x, this.calendar_y);
    }
    
    public int removeDatePickerListener(final DatePickerListener datePickerListener) {
        return this.dphl.removeDatePickerListener(datePickerListener);
    }
    
    public void setArrowColor(final Color arrowColor) {
        this.ArrowColor = arrowColor;
        this.repaint();
    }
    
    public void setAutoCalendarPosition(final boolean autoCalendarPosition) {
        this.AutoCalendarPosition = autoCalendarPosition;
    }
    
    public void setCalendarArrowColor(final Color calendarArrowColor) {
        this.CalendarArrowColor = calendarArrowColor;
    }
    
    public void setCalendarBackground(final Color calendarBackground) {
        this.CalendarBackground = calendarBackground;
    }
    
    public void setCalendarDaysColor(final Color calendarDaysColor) {
        this.CalendarDaysColor = calendarDaysColor;
    }
    
    public void setCalendarMonthColor(final Color calendarMonthColor) {
        this.CalendarMonthColor = calendarMonthColor;
    }
    
    public void setCalendarMouseOverYear_Color(final Color calendarMouseOverYearColor) {
        this.CalendarMouseOverYearColor = calendarMouseOverYearColor;
    }
    
    public void setCalendarOwner(final Dialog dialog) {
        if (this.cwin.getOwner() != dialog) {
            this.calendarTitle = this.cwin.getTitle();
            this.cwin.dispose();
            this.cwin = null;
            (this.cwin = new CalWin(dialog, this.CalModal, this.calpan)).setSize(this.getCalendarWidth(), this.getCalendarHeight());
            this.cwin.setTitle(this.calendarTitle);
            this.dphl.dp = this;
        }
    }
    
    public void setCalendarOwner(final Frame frame) {
        if (this.cwin.getOwner() != frame) {
            this.calendarTitle = this.cwin.getTitle();
            this.cwin.dispose();
            this.cwin = null;
            (this.cwin = new CalWin(frame, this.CalModal, this.calpan)).setSize(this.getCalendarWidth(), this.getCalendarHeight());
            this.cwin.setTitle(this.calendarTitle);
            this.dphl.dp = this;
        }
    }
    
    public void setCalendarSelectionColor(final Color calendarSelectionColor) {
        this.CalendarSelectionColor = calendarSelectionColor;
    }
    
    public void setCalendarTitle(final String title) {
        this.cwin.setTitle(title);
    }
    
    public void setCalendarWeekdaysColor(final Color calendarWeekdaysColor) {
        this.CalendarWeekdaysColor = calendarWeekdaysColor;
    }
    
    public void setCalendarYearColor(final Color calendarYearColor) {
        this.CalendarYearColor = calendarYearColor;
    }
    
    public void setCalendar_Modal(final boolean calModal) {
        this.CalModal = calModal;
        this.cwin.setModal(this.CalModal);
        this.dphl.dp = this;
    }
    
    public void setDate(final int n, final int n2, final int n3) throws DatePickerException {
        if (!this.isDateValid(n, n2, n3)) {
            throw new InvalidDateException();
        }
        if (CompareDates(new GregorianCalendar(n, n2 - 1, n3), this.getDate()) == 0) {
            return;
        }
        if (CompareDates(new GregorianCalendar(n, n2 - 1, n3), this.calpan.getMinDate()) == -1) {
            throw new LessThanMinDateException();
        }
        if (CompareDates(new GregorianCalendar(n, n2 - 1, n3), this.calpan.getMaxDate()) == 1) {
            throw new GreaterThanMaxDateException();
        }
        this.calpan.setDate(n, n2, n3);
        this.NewDateSelected(this.calpan.dateSelectionOver());
    }
    
    public void setDate(final Date time) throws DatePickerException {
        final GregorianCalendar date = new GregorianCalendar();
        date.setTime(time);
        this.setDate(date);
    }
    
    public void setDate(final GregorianCalendar gregorianCalendar) throws DatePickerException {
        final GregorianCalendar gregorianCalendar2 = new GregorianCalendar(gregorianCalendar.get(1), gregorianCalendar.get(2), gregorianCalendar.get(5));
        this.setDate(gregorianCalendar2.get(1), gregorianCalendar2.get(2) + 1, gregorianCalendar2.get(5));
    }
    
    public void setDateFormat(final int dateformat) {
        this.dateformat = dateformat;
        this.showDate(this.calpan.setDateFormat());
    }
    
    public void setDateValues(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        try {
            this.setDate(n, n2, n3);
            this.setMinDate(n4, n5, n6);
            this.setMaxDate(n7, n8, n9);
        }
        catch (DatePickerException ex) {}
    }
    
    public void setDateValues(final DatePicker datePicker) {
        try {
            this.setDate(datePicker.getDate());
            this.setMinDate(datePicker.getMinDate());
            this.setMaxDate(datePicker.getMaxDate());
        }
        catch (DatePickerException ex) {}
    }
    
    public boolean setDisplayLanguage(final int language) {
        if (this.language == language) {
            return true;
        }
        if (language >= 101 && language <= 103) {
            this.language = language;
            this.setCalendarTitle(DPLanguage.getSelectDateTranslation(this.language));
            this.showDate(this.calpan.changeLanguage(this.language));
            return true;
        }
        return false;
    }
    
    public void setEditableYearVisible(final boolean b) {
        if (this.EditableYearVisible != b) {
            this.EditableYearVisible = b;
            this.calpan.setEditableYearVisible(b);
            if (b) {
                this.CalWinWidth = this.CalWinWidth_EditableYearVisible;
            }
            else {
                this.CalWinWidth = this.CalWinWidth_EditableYearNotVisible;
            }
        }
    }
    
    public void setFormat(final String s) {
        try {
            this.dateformat = Integer.parseInt(s.substring(0, 2));
            this.showDate(this.calpan.setDateFormat());
        }
        catch (NumberFormatException ex) {}
    }
    
    public void setMaxDate(final int n, final int n2, final int n3) throws DatePickerException {
        if (!this.isDateValid(n, n2, n3)) {
            throw new InvalidDateException();
        }
        this.setMaxDate(new GregorianCalendar(n, n2 - 1, n3));
    }
    
    public void setMaxDate(final Date time) throws LessThanMinDateException {
        final GregorianCalendar maxDate = new GregorianCalendar();
        maxDate.setTime(time);
        this.setMaxDate(maxDate);
    }
    
    public void setMaxDate(final GregorianCalendar gregorianCalendar) throws LessThanMinDateException {
        final GregorianCalendar gregorianCalendar2 = new GregorianCalendar(gregorianCalendar.get(1), gregorianCalendar.get(2), gregorianCalendar.get(5));
        if (CompareDates(gregorianCalendar2, this.getMaxDate()) == 0) {
            return;
        }
        if (CompareDates(gregorianCalendar2, this.calpan.getMinDate()) == -1) {
            throw new LessThanMinDateException();
        }
        this.calpan.setMaxDate(gregorianCalendar2);
        if (CompareDates(gregorianCalendar2, this.calpan.getDate()) == -1) {
            try {
                this.setDate(gregorianCalendar2);
            }
            catch (DatePickerException ex) {}
        }
    }
    
    public void setMinDate(final int n, final int n2, final int n3) throws DatePickerException {
        if (!this.isDateValid(n, n2, n3)) {
            throw new InvalidDateException();
        }
        this.setMinDate(new GregorianCalendar(n, n2 - 1, n3));
    }
    
    public void setMinDate(final Date time) throws GreaterThanMaxDateException {
        final GregorianCalendar minDate = new GregorianCalendar();
        minDate.setTime(time);
        this.setMinDate(minDate);
    }
    
    public void setMinDate(final GregorianCalendar gregorianCalendar) throws GreaterThanMaxDateException {
        final GregorianCalendar gregorianCalendar2 = new GregorianCalendar(gregorianCalendar.get(1), gregorianCalendar.get(2), gregorianCalendar.get(5));
        if (CompareDates(gregorianCalendar2, this.getMinDate()) == 0) {
            return;
        }
        if (CompareDates(gregorianCalendar2, this.calpan.getMaxDate()) == 1) {
            throw new GreaterThanMaxDateException();
        }
        this.calpan.setMinDate(gregorianCalendar2);
        if (CompareDates(gregorianCalendar2, this.calpan.getDate()) == 1) {
            try {
                this.setDate(gregorianCalendar2);
            }
            catch (DatePickerException ex) {}
        }
    }
    
    public void setShowDateRangeInCalendar(final boolean b) {
        this.showDateRangeInCalendar(b);
    }
    
    public void setShowTodayInCalendar(final boolean b) {
        this.showTodayInCalendar(b);
    }
    
    public void showCalendar() {
        this.dphl.showCalendar();
    }
    
    public void showCalendar(final int n, final int n2) {
        this.dphl.showCalendar(n, n2);
    }
    
    private void showDate(final String s) {
        if (this.autoresize_dpbutton) {
            if (this.dateformat == 1 || this.dateformat == 2 || this.dateformat == 3 || this.dateformat == 5) {
                this.setLabel("  " + s + "        ");
            }
            else if (this.dateformat == 4 || this.dateformat == 6 || this.dateformat == 11 || this.dateformat == 13) {
                this.setLabel("  " + s + "          ");
            }
            else {
                this.setLabel("  " + s + "                ");
            }
        }
        else {
            this.setLabel("  " + s + "                ");
        }
        this.cwin.setVisible(false);
    }
    
    public void showDateRangeInCalendar(final boolean show_date_range) {
        if (this.show_date_range == show_date_range) {
            return;
        }
        this.show_date_range = show_date_range;
        this.alterheight = 0;
        if (!this.showtoday) {
            this.alterheight = 30;
        }
        if (!this.show_date_range) {
            this.alterheight += 30;
        }
        this.calpan.showInCalendar(this.showtoday, this.show_date_range);
    }
    
    public void showTodayInCalendar(final boolean showtoday) {
        if (this.showtoday == showtoday) {
            return;
        }
        this.showtoday = showtoday;
        this.alterheight = 0;
        if (!this.showtoday) {
            this.alterheight = 30;
        }
        if (!this.show_date_range) {
            this.alterheight += 30;
        }
        this.calpan.showInCalendar(this.showtoday, this.show_date_range);
    }
    
    public String toString() {
        return String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(DPLanguage.getCurrentDateTranslation(this.language))).append(" :").append(this.getFormattedDate(this.getDate(), 10)).append("\n").toString())).append(DPLanguage.getMinDateTranslation(this.language)).append(" :").append(this.getFormattedDate(this.getMinDate(), 10)).append("\n").toString()) + DPLanguage.getMaxDateTranslation(this.language) + " :" + this.getFormattedDate(this.getMaxDate(), 10) + "\n";
    }
}
