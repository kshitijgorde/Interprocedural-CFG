// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Vector;

public class XMLDuration
{
    public static final int DURATION = 0;
    public static final int DURATION_DAYTIME = 1;
    public static final int DURATION_YEARMONTH = 2;
    public static final int LESSER = -1;
    public static final int EQUAL = 0;
    public static final int GREATER = 1;
    public static final int INDETERMINATE = 2;
    private static final int YEARS = 1;
    private static final int MONTHS = 2;
    private static final int DAYS = 3;
    private static final int HOURS = 4;
    private static final int MINUTES = 5;
    private static final int SECONDS = 6;
    private static final String[] NAMES;
    private long m_years;
    private long m_months;
    private long m_days;
    private long m_hours;
    private long m_minutes;
    private double m_seconds;
    private boolean m_bNegativeSign;
    private String m_theDuration;
    private Vector m_setFields;
    private long m_calcYears;
    private long m_calcMonths;
    private long m_calcDays;
    private long m_calcHours;
    private long m_calcMinutes;
    private double m_calcSeconds;
    private int m_hashCode;
    static /* synthetic */ Class class$java$math$BigDecimal;
    
    public XMLDuration() {
        this.m_years = 0L;
        this.m_months = 0L;
        this.m_days = 0L;
        this.m_hours = 0L;
        this.m_minutes = 0L;
        this.m_seconds = 0.0;
        this.m_bNegativeSign = false;
        this.m_theDuration = "P0Y";
        (this.m_setFields = new Vector()).addElement(new Integer(1));
        this.init();
    }
    
    public XMLDuration(final XMLDuration xmlDuration) {
        if (xmlDuration == null) {
            throw new NullPointerException();
        }
        this.m_years = xmlDuration.m_years;
        this.m_months = xmlDuration.m_months;
        this.m_days = xmlDuration.m_days;
        this.m_hours = xmlDuration.m_hours;
        this.m_minutes = xmlDuration.m_minutes;
        this.m_seconds = xmlDuration.m_seconds;
        this.m_bNegativeSign = xmlDuration.m_bNegativeSign;
        this.m_theDuration = xmlDuration.m_theDuration;
        this.m_setFields = xmlDuration.m_setFields;
        this.init();
    }
    
    public XMLDuration(String theDuration) throws XMLParseException {
        if (theDuration == null) {
            throw new XMLParseException("##__null__##");
        }
        this.m_theDuration = theDuration;
        if (theDuration.length() == 0) {
            return;
        }
        theDuration = theDuration.trim();
        if (theDuration.length() < 3) {
            throw new XMLParseException("Duration string is too short to parse: " + this.m_theDuration);
        }
        if (theDuration.startsWith("-")) {
            this.m_bNegativeSign = true;
            theDuration = theDuration.substring(1, theDuration.length());
        }
        if (!theDuration.startsWith("P")) {
            throw new XMLParseException("Duration string has invalid format: " + this.m_theDuration);
        }
        this.m_setFields = new Vector();
        String s;
        theDuration = (s = theDuration.substring(1, theDuration.length()));
        theDuration = this.parseXMLSchemaPart(1, theDuration);
        theDuration = this.parseXMLSchemaPart(2, theDuration);
        theDuration = this.parseXMLSchemaPart(3, theDuration);
        if (theDuration.startsWith("T")) {
            theDuration = (s = theDuration.substring(1, theDuration.length()));
            theDuration = this.parseXMLSchemaPart(4, theDuration);
            theDuration = this.parseXMLSchemaPart(5, theDuration);
            theDuration = this.parseXMLSchemaPart(6, theDuration);
        }
        else if (theDuration.length() > 0) {
            throw new XMLParseException("Duration string has invalid format (T): " + this.m_theDuration);
        }
        if (theDuration.equals(s)) {
            throw new XMLParseException("Duration string has invalid format: " + this.m_theDuration);
        }
        this.init();
    }
    
    private void setField(final int n, final Number n2) {
        if (n == 1) {
            this.m_years = n2.intValue();
        }
        else if (n == 2) {
            this.m_months = n2.intValue();
        }
        else if (n == 3) {
            this.m_days = n2.intValue();
        }
        else if (n == 4) {
            this.m_hours = n2.intValue();
        }
        else if (n == 5) {
            this.m_minutes = n2.intValue();
        }
        else if (n == 6) {
            this.m_seconds = n2.doubleValue();
        }
    }
    
    private String parseXMLSchemaPart(final int n, String substring) throws XMLParseException {
        String s = "";
        if (n == 1) {
            s = "Y";
        }
        else if (n == 2) {
            s = "M";
        }
        else if (n == 3) {
            s = "D";
        }
        else if (n == 4) {
            s = "H";
        }
        else if (n == 5) {
            s = "M";
        }
        else if (n == 6) {
            s = "S";
        }
        final int index = substring.indexOf(s);
        if (index > 0) {
            final String substring2 = substring.substring(0, index);
            if (n == 2 && substring2.indexOf("T") >= 0) {
                return substring;
            }
            this.m_setFields.addElement(new Integer(n));
            try {
                if (n == 6) {
                    this.setField(n, Double.valueOf(substring2));
                }
                else {
                    this.setField(n, Integer.valueOf(substring2));
                }
                if (substring.length() > index) {
                    substring = substring.substring(index + 1, substring.length());
                }
                else {
                    substring = "";
                }
            }
            catch (NumberFormatException ex) {
                throw new XMLParseException("Duration string has invalid format (" + s + ", " + "NumberFormatException: " + ex.getMessage() + "): " + this.m_theDuration);
            }
        }
        return substring;
    }
    
    public String getXMLSchema() {
        return this.m_theDuration;
    }
    
    public int getXMLSchemaType() throws IllegalStateException {
        final boolean set = this.isSet(1);
        final boolean set2 = this.isSet(2);
        final boolean set3 = this.isSet(3);
        final boolean set4 = this.isSet(4);
        final boolean set5 = this.isSet(5);
        final boolean set6 = this.isSet(6);
        if (set && set2 && set3 && set4 && set5 && set6) {
            return 0;
        }
        if (!set && !set2 && set3 && set4 && set5 && set6) {
            return 1;
        }
        if (set && set2 && !set3 && !set4 && !set5 && !set6) {
            return 2;
        }
        throw new IllegalStateException("This Duration does not match one of the XML Schema date/time datatypes: year set = " + set + " month set = " + set2 + " day set = " + set3 + " hour set = " + set4 + " minute set = " + set5 + " second set = " + set6);
    }
    
    public int getSign() {
        if (this.m_bNegativeSign) {
            return -1;
        }
        return 1;
    }
    
    public long getYears() {
        return this.m_years;
    }
    
    public long getMonths() {
        return this.m_months;
    }
    
    public long getDays() {
        return this.m_days;
    }
    
    public long getHours() {
        return this.m_hours;
    }
    
    public long getMinutes() {
        return this.m_minutes;
    }
    
    public double getSeconds() {
        return this.m_seconds;
    }
    
    public static String getFieldName(final Object o) {
        if (o == null || !(o instanceof Integer)) {
            return null;
        }
        return getFieldName((int)o);
    }
    
    public static String getFieldName(final int n) {
        if (n < 1 || n > 6) {
            return null;
        }
        return XMLDuration.NAMES[n - 1];
    }
    
    public Enumeration getFields() {
        return this.m_setFields.elements();
    }
    
    public Number getField(final Object o) {
        if (o == null || !(o instanceof Integer)) {
            return null;
        }
        return this.getField((int)o);
    }
    
    public Number getField(final int n) {
        if (n == 1) {
            return BigInteger.valueOf(this.m_years);
        }
        if (n == 2) {
            return BigInteger.valueOf(this.m_months);
        }
        if (n == 3) {
            return BigInteger.valueOf(this.m_days);
        }
        if (n == 4) {
            return BigInteger.valueOf(this.m_hours);
        }
        if (n == 5) {
            return BigInteger.valueOf(this.m_minutes);
        }
        if (n == 6) {
            return new BigDecimal(this.m_seconds);
        }
        return null;
    }
    
    public boolean isSet(final int n) {
        return this.m_setFields.contains(new Integer(n));
    }
    
    public XMLDuration negate() {
        final XMLDuration xmlDuration = new XMLDuration(this);
        xmlDuration.m_bNegativeSign = !this.m_bNegativeSign;
        return xmlDuration;
    }
    
    public int compare(final XMLDuration xmlDuration) {
        if (this.m_calcYears > xmlDuration.m_calcYears) {
            return 1;
        }
        if (this.m_calcYears < xmlDuration.m_calcYears) {
            return -1;
        }
        if (this.m_calcMonths > xmlDuration.m_calcMonths) {
            return 1;
        }
        if (this.m_calcMonths < xmlDuration.m_calcMonths) {
            return -1;
        }
        if (this.m_calcDays > xmlDuration.m_calcDays) {
            return 1;
        }
        if (this.m_calcDays < xmlDuration.m_calcDays) {
            return -1;
        }
        if (this.m_calcHours > xmlDuration.m_calcHours) {
            return 1;
        }
        if (this.m_calcHours < xmlDuration.m_calcHours) {
            return -1;
        }
        if (this.m_calcMinutes > xmlDuration.m_calcMinutes) {
            return 1;
        }
        if (this.m_calcMinutes < xmlDuration.m_calcMinutes) {
            return -1;
        }
        if (this.m_calcSeconds > xmlDuration.m_calcSeconds) {
            return 1;
        }
        if (this.m_calcSeconds < xmlDuration.m_calcSeconds) {
            return -1;
        }
        return 0;
    }
    
    public boolean equals(final Object o) {
        return o != null && o instanceof XMLDuration && this.compare((XMLDuration)o) == 0;
    }
    
    public int hashCode() {
        return this.m_hashCode;
    }
    
    public boolean isLongerThan(final XMLDuration xmlDuration) {
        return this.compare(xmlDuration) == 1;
    }
    
    public boolean isShorterThan(final XMLDuration xmlDuration) {
        return this.compare(xmlDuration) == -1;
    }
    
    public int getLastFieldSet() {
        if (this.isSet(6)) {
            return 6;
        }
        if (this.isSet(5)) {
            return 5;
        }
        if (this.isSet(4)) {
            return 4;
        }
        if (this.isSet(3)) {
            return 3;
        }
        if (this.isSet(2)) {
            return 2;
        }
        return 1;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        if (this.getSign() < 0) {
            sb.append('-');
        }
        sb.append('P');
        final BigInteger bigInteger = (BigInteger)this.getField(1);
        if (bigInteger != null) {
            sb.append(bigInteger + "Y");
        }
        final BigInteger bigInteger2 = (BigInteger)this.getField(2);
        if (bigInteger2 != null) {
            sb.append(bigInteger2 + "M");
        }
        final BigInteger bigInteger3 = (BigInteger)this.getField(3);
        if (bigInteger3 != null) {
            sb.append(bigInteger3 + "D");
        }
        final BigInteger bigInteger4 = (BigInteger)this.getField(4);
        final BigInteger bigInteger5 = (BigInteger)this.getField(5);
        final BigDecimal bigDecimal = (BigDecimal)this.getField(6);
        if (bigInteger4 != null || bigInteger5 != null || bigDecimal != null) {
            sb.append('T');
            if (bigInteger4 != null) {
                sb.append(bigInteger4 + "H");
            }
            if (bigInteger5 != null) {
                sb.append(bigInteger5 + "M");
            }
            if (bigDecimal != null) {
                sb.append(this.toString(bigDecimal) + "S");
            }
        }
        return sb.toString();
    }
    
    private String toString(final BigDecimal bigDecimal) {
        String s = this.toStringJDK5(bigDecimal);
        if (s == null) {
            s = bigDecimal.toString();
        }
        return s;
    }
    
    private String toStringJDK5(final BigDecimal bigDecimal) {
        BigInteger bigInteger;
        try {
            bigInteger = (BigInteger)((XMLDuration.class$java$math$BigDecimal == null) ? (XMLDuration.class$java$math$BigDecimal = class$("java.math.BigDecimal")) : XMLDuration.class$java$math$BigDecimal).getMethod("unscaledValue", (Class[])null).invoke(bigDecimal, (Object[])null);
        }
        catch (Exception ex) {
            return null;
        }
        final String string = bigInteger.toString();
        final int scale = bigDecimal.scale();
        if (scale == 0) {
            return string;
        }
        final int n = string.length() - scale;
        if (n == 0) {
            return "0." + string;
        }
        StringBuffer sb;
        if (n > 0) {
            sb = new StringBuffer(string);
            sb.insert(n, '.');
        }
        else {
            sb = new StringBuffer(3 - n + string.length());
            sb.append("0.");
            for (int i = 0; i < -n; ++i) {
                sb.append('0');
            }
            sb.append(string);
        }
        return sb.toString();
    }
    
    private void init() {
        this.m_calcSeconds = this.m_seconds;
        this.m_calcMinutes = this.m_minutes;
        this.m_calcHours = this.m_hours;
        this.m_calcDays = this.m_days;
        this.m_calcMonths = this.m_months;
        this.m_calcYears = this.m_years;
        this.m_calcMinutes += (int)this.m_calcSeconds / 60;
        this.m_calcSeconds -= (int)this.m_calcSeconds / 60 * 60;
        this.m_calcHours += this.m_calcMinutes / 60L;
        this.m_calcMinutes %= 60L;
        this.m_calcDays += this.m_calcHours / 24L;
        this.m_calcHours %= 24L;
        this.m_calcYears += this.m_calcDays / 365L;
        this.m_calcDays %= 365L;
        this.m_calcMonths += 5L * (this.m_calcDays / 150L);
        this.m_calcDays %= 150L;
        this.m_calcMonths += this.m_calcDays / 28L;
        this.m_calcDays %= 28L;
        this.m_calcYears += this.m_calcMonths / 12L;
        this.m_calcMonths %= 12L;
        this.m_hashCode = (int)((long)this.m_calcSeconds + this.m_calcMinutes + this.m_calcHours + this.m_calcDays + this.m_calcMonths + this.m_calcYears) * this.getSign();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        NAMES = new String[] { "years", "months", "days", "hours", "minutes", "seconds" };
    }
}
