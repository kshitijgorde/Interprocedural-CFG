// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

import java.beans.PropertyDescriptor;
import java.awt.Image;
import java.beans.IntrospectionException;
import java.beans.EventSetDescriptor;
import java.beans.SimpleBeanInfo;

public class DatePickerBeanInfo extends SimpleBeanInfo
{
    static /* synthetic */ Class class$DatePickerBean$DatePicker;
    static /* synthetic */ Class class$DatePickerBean$DateEditor;
    static /* synthetic */ Class class$DatePickerBean$DateFormatEditor;
    static /* synthetic */ Class class$DatePickerBean$DatePickerListener;
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            return new EventSetDescriptor[] { new EventSetDescriptor((DatePickerBeanInfo.class$DatePickerBean$DatePicker != null) ? DatePickerBeanInfo.class$DatePickerBean$DatePicker : (DatePickerBeanInfo.class$DatePickerBean$DatePicker = class$("DatePickerBean.DatePicker")), "dateChanged", (DatePickerBeanInfo.class$DatePickerBean$DatePickerListener != null) ? DatePickerBeanInfo.class$DatePickerBean$DatePickerListener : (DatePickerBeanInfo.class$DatePickerBean$DatePickerListener = class$("DatePickerBean.DatePickerListener")), new String[] { "dateChanged" }, "addDatePickerListener", "removeDatePickerListener") };
        }
        catch (IntrospectionException ex) {
            throw new Error(ex.toString());
        }
    }
    
    public Image getIcon(final int n) {
        if (n == 3 || n == 1) {
            return this.loadImage("DatePickerBean16.gif");
        }
        if (n == 4 || n == 2) {
            return this.loadImage("DatePickerBean32.gif");
        }
        return null;
    }
    
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            final PropertyDescriptor propertyDescriptor = new PropertyDescriptor("DateValues", (DatePickerBeanInfo.class$DatePickerBean$DatePicker != null) ? DatePickerBeanInfo.class$DatePickerBean$DatePicker : (DatePickerBeanInfo.class$DatePickerBean$DatePicker = class$("DatePickerBean.DatePicker")));
            propertyDescriptor.setPropertyEditorClass((DatePickerBeanInfo.class$DatePickerBean$DateEditor != null) ? DatePickerBeanInfo.class$DatePickerBean$DateEditor : (DatePickerBeanInfo.class$DatePickerBean$DateEditor = class$("DatePickerBean.DateEditor")));
            final PropertyDescriptor propertyDescriptor2 = new PropertyDescriptor("background", (DatePickerBeanInfo.class$DatePickerBean$DatePicker != null) ? DatePickerBeanInfo.class$DatePickerBean$DatePicker : (DatePickerBeanInfo.class$DatePickerBean$DatePicker = class$("DatePickerBean.DatePicker")));
            final PropertyDescriptor propertyDescriptor3 = new PropertyDescriptor("foreground", (DatePickerBeanInfo.class$DatePickerBean$DatePicker != null) ? DatePickerBeanInfo.class$DatePickerBean$DatePicker : (DatePickerBeanInfo.class$DatePickerBean$DatePicker = class$("DatePickerBean.DatePicker")));
            final PropertyDescriptor propertyDescriptor4 = new PropertyDescriptor("font", (DatePickerBeanInfo.class$DatePickerBean$DatePicker != null) ? DatePickerBeanInfo.class$DatePickerBean$DatePicker : (DatePickerBeanInfo.class$DatePickerBean$DatePicker = class$("DatePickerBean.DatePicker")));
            final PropertyDescriptor propertyDescriptor5 = new PropertyDescriptor("visible", (DatePickerBeanInfo.class$DatePickerBean$DatePicker != null) ? DatePickerBeanInfo.class$DatePickerBean$DatePicker : (DatePickerBeanInfo.class$DatePickerBean$DatePicker = class$("DatePickerBean.DatePicker")));
            final PropertyDescriptor propertyDescriptor6 = new PropertyDescriptor("enabled", (DatePickerBeanInfo.class$DatePickerBean$DatePicker != null) ? DatePickerBeanInfo.class$DatePickerBean$DatePicker : (DatePickerBeanInfo.class$DatePickerBean$DatePicker = class$("DatePickerBean.DatePicker")));
            final PropertyDescriptor propertyDescriptor7 = new PropertyDescriptor("EditableYearVisible", (DatePickerBeanInfo.class$DatePickerBean$DatePicker != null) ? DatePickerBeanInfo.class$DatePickerBean$DatePicker : (DatePickerBeanInfo.class$DatePickerBean$DatePicker = class$("DatePickerBean.DatePicker")));
            final PropertyDescriptor propertyDescriptor8 = new PropertyDescriptor("CalendarTitle", (DatePickerBeanInfo.class$DatePickerBean$DatePicker != null) ? DatePickerBeanInfo.class$DatePickerBean$DatePicker : (DatePickerBeanInfo.class$DatePickerBean$DatePicker = class$("DatePickerBean.DatePicker")));
            final PropertyDescriptor propertyDescriptor9 = new PropertyDescriptor("CalendarBackground", (DatePickerBeanInfo.class$DatePickerBean$DatePicker != null) ? DatePickerBeanInfo.class$DatePickerBean$DatePicker : (DatePickerBeanInfo.class$DatePickerBean$DatePicker = class$("DatePickerBean.DatePicker")));
            final PropertyDescriptor propertyDescriptor10 = new PropertyDescriptor("ArrowColor", (DatePickerBeanInfo.class$DatePickerBean$DatePicker != null) ? DatePickerBeanInfo.class$DatePickerBean$DatePicker : (DatePickerBeanInfo.class$DatePickerBean$DatePicker = class$("DatePickerBean.DatePicker")));
            final PropertyDescriptor propertyDescriptor11 = new PropertyDescriptor("CalendarArrowColor", (DatePickerBeanInfo.class$DatePickerBean$DatePicker != null) ? DatePickerBeanInfo.class$DatePickerBean$DatePicker : (DatePickerBeanInfo.class$DatePickerBean$DatePicker = class$("DatePickerBean.DatePicker")));
            final PropertyDescriptor propertyDescriptor12 = new PropertyDescriptor("CalendarWeekdaysColor", (DatePickerBeanInfo.class$DatePickerBean$DatePicker != null) ? DatePickerBeanInfo.class$DatePickerBean$DatePicker : (DatePickerBeanInfo.class$DatePickerBean$DatePicker = class$("DatePickerBean.DatePicker")));
            final PropertyDescriptor propertyDescriptor13 = new PropertyDescriptor("CalendarDaysColor", (DatePickerBeanInfo.class$DatePickerBean$DatePicker != null) ? DatePickerBeanInfo.class$DatePickerBean$DatePicker : (DatePickerBeanInfo.class$DatePickerBean$DatePicker = class$("DatePickerBean.DatePicker")));
            final PropertyDescriptor propertyDescriptor14 = new PropertyDescriptor("CalendarMonthColor", (DatePickerBeanInfo.class$DatePickerBean$DatePicker != null) ? DatePickerBeanInfo.class$DatePickerBean$DatePicker : (DatePickerBeanInfo.class$DatePickerBean$DatePicker = class$("DatePickerBean.DatePicker")));
            final PropertyDescriptor propertyDescriptor15 = new PropertyDescriptor("CalendarYearColor", (DatePickerBeanInfo.class$DatePickerBean$DatePicker != null) ? DatePickerBeanInfo.class$DatePickerBean$DatePicker : (DatePickerBeanInfo.class$DatePickerBean$DatePicker = class$("DatePickerBean.DatePicker")));
            final PropertyDescriptor propertyDescriptor16 = new PropertyDescriptor("CalendarMouseOverYear_Color", (DatePickerBeanInfo.class$DatePickerBean$DatePicker != null) ? DatePickerBeanInfo.class$DatePickerBean$DatePicker : (DatePickerBeanInfo.class$DatePickerBean$DatePicker = class$("DatePickerBean.DatePicker")));
            final PropertyDescriptor propertyDescriptor17 = new PropertyDescriptor("CalendarSelectionColor", (DatePickerBeanInfo.class$DatePickerBean$DatePicker != null) ? DatePickerBeanInfo.class$DatePickerBean$DatePicker : (DatePickerBeanInfo.class$DatePickerBean$DatePicker = class$("DatePickerBean.DatePicker")));
            final PropertyDescriptor propertyDescriptor18 = new PropertyDescriptor("AutoCalendarPosition", (DatePickerBeanInfo.class$DatePickerBean$DatePicker != null) ? DatePickerBeanInfo.class$DatePickerBean$DatePicker : (DatePickerBeanInfo.class$DatePickerBean$DatePicker = class$("DatePickerBean.DatePicker")));
            final PropertyDescriptor propertyDescriptor19 = new PropertyDescriptor("Format", (DatePickerBeanInfo.class$DatePickerBean$DatePicker != null) ? DatePickerBeanInfo.class$DatePickerBean$DatePicker : (DatePickerBeanInfo.class$DatePickerBean$DatePicker = class$("DatePickerBean.DatePicker")));
            propertyDescriptor19.setPropertyEditorClass((DatePickerBeanInfo.class$DatePickerBean$DateFormatEditor != null) ? DatePickerBeanInfo.class$DatePickerBean$DateFormatEditor : (DatePickerBeanInfo.class$DatePickerBean$DateFormatEditor = class$("DatePickerBean.DateFormatEditor")));
            return new PropertyDescriptor[] { propertyDescriptor, propertyDescriptor2, propertyDescriptor3, propertyDescriptor4, propertyDescriptor5, propertyDescriptor6, propertyDescriptor7, propertyDescriptor8, propertyDescriptor10, propertyDescriptor19, propertyDescriptor18, propertyDescriptor9, propertyDescriptor11, propertyDescriptor12, propertyDescriptor13, propertyDescriptor14, propertyDescriptor15, propertyDescriptor16, propertyDescriptor17, new PropertyDescriptor("ShowDateRangeInCalendar", (DatePickerBeanInfo.class$DatePickerBean$DatePicker != null) ? DatePickerBeanInfo.class$DatePickerBean$DatePicker : (DatePickerBeanInfo.class$DatePickerBean$DatePicker = class$("DatePickerBean.DatePicker"))), new PropertyDescriptor("ShowTodayInCalendar", (DatePickerBeanInfo.class$DatePickerBean$DatePicker != null) ? DatePickerBeanInfo.class$DatePickerBean$DatePicker : (DatePickerBeanInfo.class$DatePickerBean$DatePicker = class$("DatePickerBean.DatePicker"))), new PropertyDescriptor("Calendar_Modal", (DatePickerBeanInfo.class$DatePickerBean$DatePicker != null) ? DatePickerBeanInfo.class$DatePickerBean$DatePicker : (DatePickerBeanInfo.class$DatePickerBean$DatePicker = class$("DatePickerBean.DatePicker"))) };
        }
        catch (IntrospectionException ex) {
            throw new Error(ex.toString());
        }
    }
}
