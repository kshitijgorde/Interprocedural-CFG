// 
// Decompiled by Procyon v0.5.30
// 

public class CalendarUtil
{
    public static int julianDate(int month, final int day, int year) {
        while (month < 1) {
            month += 12;
            --year;
        }
        while (month > 12) {
            month -= 12;
            ++year;
        }
        return 367 * year - 7 * (year + (month + 9) / 12) / 4 - 3 * ((year + (month - 9) / 7) / 100 + 1) / 4 + 275 * month / 9 + day + 1721029;
    }
    
    public static int daysInMonth(final int month, final int year) {
        if (month == 9 || month == 4 || month == 6 || month == 11) {
            return 30;
        }
        if (month != 2) {
            return 31;
        }
        return julianDate(3, 1, year) - julianDate(2, 1, year);
    }
}
