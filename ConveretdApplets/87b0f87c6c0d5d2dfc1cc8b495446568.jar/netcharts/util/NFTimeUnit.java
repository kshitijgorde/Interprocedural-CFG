// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.util.Date;

public class NFTimeUnit
{
    public int years;
    public int months;
    public int days;
    public int hours;
    public int minutes;
    public int seconds;
    private NFToken a;
    
    public NFTimeUnit() {
        this.years = 0;
        this.months = 0;
        this.days = 0;
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
        this.a = null;
    }
    
    public NFTimeUnit(final int years, final int months, final int days, final int hours, final int minutes, final int seconds) {
        this.years = 0;
        this.months = 0;
        this.days = 0;
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
        this.a = null;
        this.years = years;
        this.months = months;
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }
    
    public NFTimeUnit(final String s) {
        this();
        try {
            this.parse(s);
        }
        catch (Exception ex) {
            NFDebug.print("NFTimeUnit: " + ex.getMessage());
        }
    }
    
    public double getValue(final Object o, final NFDate nfDate) {
        if (o == null) {
            return 0.0;
        }
        if (o instanceof NFDate) {
            return ((NFDate)o).getDiffInSecs(nfDate) / this.getSeconds();
        }
        if (o instanceof NFTimeUnit) {
            return ((NFTimeUnit)o).getSeconds() / this.getSeconds();
        }
        if (o instanceof Number) {
            return ((Number)o).doubleValue();
        }
        return 0.0;
    }
    
    public NFDate addToDate(NFDate nfDate) {
        int n = nfDate.getYear() + this.years;
        int i = nfDate.getMonth() + this.months;
        final int date = nfDate.getDate();
        final int hours = nfDate.getHours();
        final int minutes = nfDate.getMinutes();
        final int seconds = nfDate.getSeconds();
        while (i >= 12) {
            ++n;
            i -= 12;
        }
        while (i < 0) {
            --n;
            i += 12;
        }
        final int timezoneOffset = getTimezoneOffset(nfDate);
        nfDate = new NFDate(n, i, date, hours, minutes, seconds);
        final long time = nfDate.getTime() + (86400000L * this.days + 3600000L * this.hours + 60000L * this.minutes + 1000L * this.seconds);
        nfDate.setTime(time);
        final int timezoneOffset2 = getTimezoneOffset(nfDate);
        if (timezoneOffset2 != timezoneOffset && this.days != 0) {
            nfDate.setTime(time + 60000 * (timezoneOffset2 - timezoneOffset));
        }
        return nfDate;
    }
    
    public static int getTimezoneOffset(final Date date) {
        if (NFUtil.getJDKVersion() >= 1.1) {
            return NFTimeZoneOffset.getTimeZoneOffset(date);
        }
        return date.getTimezoneOffset();
    }
    
    public NFTimeUnit mult(final double n) {
        final NFTimeUnit nfTimeUnit = new NFTimeUnit();
        final double n2 = n * this.years;
        double n3 = n * this.months;
        double n4 = n * this.days;
        double n5 = n * this.hours;
        double n6 = n * this.minutes;
        double n7 = n * this.seconds;
        nfTimeUnit.years = (int)n2;
        if ((int)n2 != n2) {
            n3 += 12.0 * (n2 - (int)n2);
        }
        nfTimeUnit.months = (int)n3;
        if ((int)n3 != n3) {
            n4 += 30.0 * (n3 - (int)n3);
        }
        nfTimeUnit.days = (int)n4;
        if ((int)n4 != n4) {
            n5 += 24.0 * (n4 - (int)n4);
        }
        nfTimeUnit.hours = (int)n5;
        if ((int)n5 != n5) {
            n6 += 60.0 * (n5 - (int)n5);
        }
        nfTimeUnit.minutes = (int)n6;
        if ((int)n6 != n6) {
            n7 += 60.0 * (n6 - (int)n6);
        }
        nfTimeUnit.seconds = (int)NFUtil.rint(n7);
        return nfTimeUnit;
    }
    
    public double getSeconds() {
        return this.years * 31536000 + this.months * 2628000 + this.days * 86400 + this.hours * 3600 + this.minutes * 60 + this.seconds;
    }
    
    public void parse(final String input) throws Exception {
        boolean b = false;
        double doubleValue = 0.0;
        if (this.a == null) {
            (this.a = new NFToken()).setCharType(3, 89, 0);
            this.a.setCharType(3, 121, 0);
            this.a.setCharType(3, 77, 0);
            this.a.setCharType(3, 109, 0);
            this.a.setCharType(3, 68, 0);
            this.a.setCharType(3, 100, 0);
            this.a.setCharType(3, 72, 0);
            this.a.setCharType(3, 104, 0);
            this.a.setCharType(3, 83, 0);
            this.a.setCharType(3, 115, 0);
        }
        this.a.setInput(input);
        final boolean b2 = false;
        this.seconds = (b2 ? 1 : 0);
        this.minutes = (b2 ? 1 : 0);
        this.hours = (b2 ? 1 : 0);
        this.days = (b2 ? 1 : 0);
        this.months = (b2 ? 1 : 0);
        this.years = (b2 ? 1 : 0);
        while (true) {
            final StringBuffer nextToken = this.a.nextToken();
            if (nextToken == null) {
                if (b) {
                    throw new Exception("Incomplete expression, expected UNIT type");
                }
            }
            else {
                switch (nextToken.charAt(0)) {
                    default: {
                        throw new Exception("Illegal syntax");
                    }
                    case '+':
                    case '-':
                    case '.':
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9': {
                        if (b) {
                            throw new Exception("Expected Unit, got <" + (Object)nextToken + ">");
                        }
                        doubleValue = Double.valueOf(nextToken.toString());
                        break;
                    }
                    case 'Y':
                    case 'y': {
                        if (!b) {
                            throw new Exception("Expected Number, got <" + (Object)nextToken + ">");
                        }
                        final int n = (int)doubleValue;
                        this.years += n;
                        if (n == doubleValue) {
                            break;
                        }
                        doubleValue = (doubleValue - n) * 12.0;
                    }
                    case 'M': {
                        if (!b) {
                            throw new Exception("Expected Number, got <" + (Object)nextToken + ">");
                        }
                        final int n2 = (int)doubleValue;
                        this.months += n2;
                        if (n2 == doubleValue) {
                            break;
                        }
                        doubleValue = (doubleValue - n2) * 365.0 / 12.0;
                    }
                    case 'D':
                    case 'd': {
                        if (!b) {
                            throw new Exception("Expected Number, got <" + (Object)nextToken + ">");
                        }
                        final int n3 = (int)doubleValue;
                        this.days += n3;
                        if (n3 == doubleValue) {
                            break;
                        }
                        doubleValue = (doubleValue - n3) * 24.0;
                    }
                    case 'H':
                    case 'h': {
                        if (!b) {
                            throw new Exception("Expected Number, got <" + (Object)nextToken + ">");
                        }
                        final int n4 = (int)doubleValue;
                        this.hours += n4;
                        if (n4 == doubleValue) {
                            break;
                        }
                        doubleValue = (doubleValue - n4) * 60.0;
                    }
                    case 'm': {
                        if (!b) {
                            throw new Exception("Expected Number, got <" + (Object)nextToken + ">");
                        }
                        final int n5 = (int)doubleValue;
                        this.minutes += n5;
                        if (n5 == doubleValue) {
                            break;
                        }
                        doubleValue = (doubleValue - n5) * 60.0;
                    }
                    case 'S':
                    case 's': {
                        if (!b) {
                            throw new Exception("Expected Number, got <" + (Object)nextToken + ">");
                        }
                        this.seconds += (int)doubleValue;
                        break;
                    }
                }
                b = !b;
            }
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        if (this.years != 0) {
            sb.append(this.years + "Y");
        }
        if (this.months != 0) {
            if (sb.length() != 0) {
                sb.append(' ');
            }
            sb.append(this.months + "M");
        }
        if (this.days != 0) {
            if (sb.length() != 0) {
                sb.append(' ');
            }
            sb.append(this.days + "D");
        }
        if (this.hours != 0) {
            if (sb.length() != 0) {
                sb.append(' ');
            }
            sb.append(this.hours + "h");
        }
        if (this.minutes != 0) {
            if (sb.length() != 0) {
                sb.append(' ');
            }
            sb.append(this.minutes + "m");
        }
        if (this.seconds != 0) {
            if (sb.length() != 0) {
                sb.append(' ');
            }
            sb.append(this.seconds + "s");
        }
        if (sb.length() == 0) {
            sb.append("0D");
        }
        return sb.toString();
    }
}
