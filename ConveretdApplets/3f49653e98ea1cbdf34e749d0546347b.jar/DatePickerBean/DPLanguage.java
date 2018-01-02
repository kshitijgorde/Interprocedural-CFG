// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

public class DPLanguage
{
    static int languageStart;
    static int languageEnd;
    public static int LANG_ENGLISH;
    public static int LANG_FRENCH;
    public static int LANG_FINNISH;
    
    static {
        DPLanguage.languageStart = 101;
        DPLanguage.languageEnd = 103;
        DPLanguage.LANG_ENGLISH = 101;
        DPLanguage.LANG_FRENCH = 102;
        DPLanguage.LANG_FINNISH = 103;
    }
    
    public static String getCurrentDateTranslation(final int n) {
        if (n < DPLanguage.languageStart || n > DPLanguage.languageEnd) {
            return "Unsupported Language.";
        }
        String s = new String();
        if (n == DPLanguage.LANG_ENGLISH) {
            s = "Current Date";
        }
        else if (n == DPLanguage.LANG_FRENCH) {
            s = "Selectionn\u00e9 Date";
        }
        else if (n == DPLanguage.LANG_FINNISH) {
            s = "Nykyinen P\u00e4iv\u00e4m\u00e4\u00e4r\u00e4";
        }
        return s;
    }
    
    public static String getDateRangeTranslation(final int n) {
        if (n < DPLanguage.languageStart || n > DPLanguage.languageEnd) {
            return "Unsupported Language.";
        }
        String s = new String();
        if (n == DPLanguage.LANG_ENGLISH) {
            s = "Date Range";
        }
        else if (n == DPLanguage.LANG_FRENCH) {
            s = "P\u00e9riode";
        }
        else if (n == DPLanguage.LANG_FINNISH) {
            s = "Sallitut P\u00e4iv\u00e4m\u00e4\u00e4r\u00e4t";
        }
        return s;
    }
    
    public static String getMaxDateTranslation(final int n) {
        if (n < DPLanguage.languageStart || n > DPLanguage.languageEnd) {
            return "Unsupported Language.";
        }
        String s = new String();
        if (n == DPLanguage.LANG_ENGLISH) {
            s = "Max Date";
        }
        else if (n == DPLanguage.LANG_FRENCH) {
            s = "Max Date";
        }
        else if (n == DPLanguage.LANG_FINNISH) {
            s = "Suurin P\u00e4iv\u00e4m\u00e4\u00e4r\u00e4";
        }
        return s;
    }
    
    public static String getMinDateTranslation(final int n) {
        if (n < DPLanguage.languageStart || n > DPLanguage.languageEnd) {
            return "Unsupported Language.";
        }
        String s = new String();
        if (n == DPLanguage.LANG_ENGLISH) {
            s = "Min Date";
        }
        else if (n == DPLanguage.LANG_FRENCH) {
            s = "Min Date";
        }
        else if (n == DPLanguage.LANG_FINNISH) {
            s = "Pienin P\u00e4iv\u00e4m\u00e4\u00e4r\u00e4";
        }
        return s;
    }
    
    public static String[] getMonthsTranslation(final int n, final int n2) {
        if (n < DPLanguage.languageStart || n > DPLanguage.languageEnd) {
            return null;
        }
        final String[] array = new String[12];
        final String[] array2 = new String[12];
        if (n == DPLanguage.LANG_ENGLISH) {
            if (n2 == 1) {
                array[0] = "January";
                array[1] = "February";
                array[2] = "March";
                array[3] = "April";
                array[4] = "May";
                array[5] = "June";
                array[6] = "July";
                array[7] = "August";
                array[8] = "September";
                array[9] = "October";
                array[10] = "November";
                array[11] = "December";
            }
            else {
                array2[0] = "Jan";
                array2[1] = "Feb";
                array2[2] = "Mar";
                array2[3] = "Apr";
                array2[4] = "May";
                array2[5] = "Jun";
                array2[6] = "Jul";
                array2[7] = "Aug";
                array2[8] = "Sep";
                array2[9] = "Oct";
                array2[10] = "Nov";
                array2[11] = "Dec";
            }
        }
        else if (n == DPLanguage.LANG_FRENCH) {
            if (n2 == 1) {
                array[0] = "Janvier";
                array[1] = "F\u00e9vrier";
                array[2] = "Mars";
                array[3] = "Avril";
                array[4] = "Mai";
                array[5] = "Juin";
                array[6] = "Juillet";
                array[7] = "Ao\u00fbt";
                array[8] = "Septembre";
                array[9] = "Octobre";
                array[10] = "Novembre";
                array[11] = "Decembre";
            }
            else {
                array2[0] = "Jan";
                array2[1] = "Fev";
                array2[2] = "Mar";
                array2[3] = "Avr";
                array2[4] = "Mai";
                array2[5] = "Jun";
                array2[6] = "Jul";
                array2[7] = "Aou";
                array2[8] = "Sep";
                array2[9] = "Oct";
                array2[10] = "Nov";
                array2[11] = "Dec";
            }
        }
        else if (n == DPLanguage.LANG_FINNISH) {
            if (n2 == 1) {
                array[0] = "Tammikuu";
                array[1] = "Helmikuu";
                array[2] = "Maaliskuu";
                array[3] = "Huhtikuu";
                array[4] = "Toukokuu";
                array[5] = "Kes\u00e4kuu";
                array[6] = "Hein\u00e4kuu";
                array[7] = "Elokuu";
                array[8] = "Syyskuu";
                array[9] = "Lokakuu";
                array[10] = "Marraskuu";
                array[11] = "Joulukuu";
            }
            else {
                array2[0] = "Tam";
                array2[1] = "Hel";
                array2[2] = "Maa";
                array2[3] = "Huh";
                array2[4] = "Tou";
                array2[5] = "Kes";
                array2[6] = "Hei";
                array2[7] = "Elo";
                array2[8] = "Syy";
                array2[9] = "Lok";
                array2[10] = "Mar";
                array2[11] = "Jou";
            }
        }
        if (n2 == 1) {
            return array;
        }
        return array2;
    }
    
    public static String getSelectDateTranslation(final int n) {
        if (n < DPLanguage.languageStart || n > DPLanguage.languageEnd) {
            return "Unsupported Language.";
        }
        String s = new String();
        if (n == DPLanguage.LANG_ENGLISH) {
            s = "Select Date";
        }
        else if (n == DPLanguage.LANG_FRENCH) {
            s = "Slectionner Une Date";
        }
        else if (n == DPLanguage.LANG_FINNISH) {
            s = "Valitse P\u00e4iv\u00e4m\u00e4\u00e4r\u00e4";
        }
        return s;
    }
    
    public static String getTodayTranslation(final int n) {
        if (n < DPLanguage.languageStart || n > DPLanguage.languageEnd) {
            return "Unsupported Language.";
        }
        String s = new String();
        if (n == DPLanguage.LANG_ENGLISH) {
            s = "Today";
        }
        else if (n == DPLanguage.LANG_FRENCH) {
            s = "Aujourd'hui";
        }
        else if (n == DPLanguage.LANG_FINNISH) {
            s = "T\u00e4n\u00e4\u00e4n";
        }
        return s;
    }
    
    public static String[] getWeekdaysTranslation(final int n, final int n2) {
        if (n < DPLanguage.languageStart || n > DPLanguage.languageEnd) {
            return null;
        }
        final String[] array = new String[7];
        final String[] array2 = new String[7];
        if (n == DPLanguage.LANG_ENGLISH) {
            if (n2 == 1) {
                array[0] = "Sunday";
                array[1] = "Monday";
                array[2] = "Tuesday";
                array[3] = "Wednesday";
                array[4] = "Thursday";
                array[5] = "Friday";
                array[6] = "Saturday";
            }
            else {
                array2[0] = "Sun";
                array2[1] = "Mon";
                array2[2] = "Tue";
                array2[3] = "Wed";
                array2[4] = "Thu";
                array2[5] = "Fri";
                array2[6] = "Sat";
            }
        }
        else if (n == DPLanguage.LANG_FRENCH) {
            if (n2 == 1) {
                array[0] = "Dimanche";
                array[1] = "Lundi";
                array[2] = "Mardi";
                array[3] = "Mercredi";
                array[4] = "Jeudi";
                array[5] = "Vendredi";
                array[6] = "Samedi";
            }
            else {
                array2[0] = "Dim";
                array2[1] = "Lun";
                array2[2] = "Mar";
                array2[3] = "Mer";
                array2[4] = "Jeu";
                array2[5] = "Ven";
                array2[6] = "Sam";
            }
        }
        else if (n == DPLanguage.LANG_FINNISH) {
            if (n2 == 1) {
                array[0] = "Sunnuntai";
                array[1] = "Maanantai";
                array[2] = "Tiistai";
                array[3] = "Keskiviikko";
                array[4] = "Torstai";
                array[5] = "Perjantai";
                array[6] = "Lauantai";
            }
            else {
                array2[0] = "Su";
                array2[1] = "Ma";
                array2[2] = "Ti";
                array2[3] = "Ke";
                array2[4] = "To";
                array2[5] = "Pe";
                array2[6] = "La";
            }
        }
        if (n2 == 1) {
            return array;
        }
        return array2;
    }
}
