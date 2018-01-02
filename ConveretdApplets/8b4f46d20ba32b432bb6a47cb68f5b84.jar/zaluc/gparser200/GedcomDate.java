// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.gparser200;

import java.util.StringTokenizer;

class GedcomDate
{
    private String date;
    private int day;
    private int month;
    private int year;
    private static final int UNUSED = 3000;
    private static final int DAY_FOUND = 1;
    private static final int MONTH_FOUND = 2;
    private static final int YEAR_FOUND = 4;
    private static final int ALL_FOUND = 7;
    private static final int NONE_FOUND = 0;
    
    public GedcomDate(final GedcomDate gedcomDate) {
        if (gedcomDate.date != null) {
            this.date = new String(gedcomDate.date);
        }
        this.day = gedcomDate.day;
        this.month = gedcomDate.month;
        this.year = gedcomDate.year;
    }
    
    public GedcomDate(final String date) {
        int n = 0;
        this.date = date;
        final int day = 3000;
        this.year = day;
        this.month = day;
        this.day = day;
        if (date != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(date, "\n ", false);
            while ((n & 0x7) != 0x7 && stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                final String int1 = this.getInt(nextToken);
                if (int1 != null) {
                    final int int2 = Integer.parseInt(int1);
                    if ((n & 0x4) == 0x0 && int2 > 31) {
                        n |= 0x4;
                        this.year = int2;
                    }
                    else {
                        if ((n & 0x1) != 0x0) {
                            continue;
                        }
                        n |= 0x1;
                        this.day = int2;
                    }
                }
                else {
                    if ((n & 0x2) != 0x0) {
                        continue;
                    }
                    final String lowerCase = nextToken.toLowerCase();
                    if (lowerCase.startsWith("jan")) {
                        n |= 0x2;
                        this.month = 0;
                    }
                    else if (lowerCase.startsWith("feb")) {
                        n |= 0x2;
                        this.month = 1;
                    }
                    else if (lowerCase.startsWith("mar")) {
                        n |= 0x2;
                        this.month = 2;
                    }
                    else if (lowerCase.startsWith("apr")) {
                        n |= 0x2;
                        this.month = 3;
                    }
                    else if (lowerCase.startsWith("may")) {
                        n |= 0x2;
                        this.month = 4;
                    }
                    else if (lowerCase.startsWith("jun")) {
                        n |= 0x2;
                        this.month = 5;
                    }
                    else if (lowerCase.startsWith("jul")) {
                        n |= 0x2;
                        this.month = 6;
                    }
                    else if (lowerCase.startsWith("aug")) {
                        n |= 0x2;
                        this.month = 7;
                    }
                    else if (lowerCase.startsWith("sep")) {
                        n |= 0x2;
                        this.month = 8;
                    }
                    else if (lowerCase.startsWith("oct")) {
                        n |= 0x2;
                        this.month = 9;
                    }
                    else if (lowerCase.startsWith("nov")) {
                        n |= 0x2;
                        this.month = 10;
                    }
                    else {
                        if (!lowerCase.startsWith("dec")) {
                            continue;
                        }
                        n |= 0x2;
                        this.month = 11;
                    }
                }
            }
        }
    }
    
    public int compareTo(final GedcomDate gedcomDate) {
        if (gedcomDate == null) {
            return -1;
        }
        if (this.year != gedcomDate.year) {
            return this.year - gedcomDate.year;
        }
        if (this.month != gedcomDate.month) {
            return this.month - gedcomDate.month;
        }
        return this.day - gedcomDate.day;
    }
    
    public boolean hasYear() {
        return this.year != 3000;
    }
    
    public int getYear() {
        if (this.year != 3000) {
            return this.year;
        }
        return 0;
    }
    
    public String toString() {
        if (this.date != null) {
            return this.date;
        }
        return "???";
    }
    
    private String getInt(final String s) {
        String s2 = null;
        final char[] array = new char[4];
        int n;
        int i = n = 0;
        try {
            while (i < 4) {
                if (n >= s.length()) {
                    break;
                }
                array[i] = s.charAt(n);
                if (array[i] >= '0' && array[i] <= '9') {
                    ++i;
                }
                ++n;
            }
        }
        catch (StringIndexOutOfBoundsException ex) {}
        if (i > 0) {
            s2 = new String(array, 0, i);
        }
        return s2;
    }
}
