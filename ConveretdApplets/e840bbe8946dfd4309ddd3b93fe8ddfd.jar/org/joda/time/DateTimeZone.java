// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import org.joda.time.format.DateTimeFormat;
import java.util.Locale;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.FormatUtils;
import org.joda.time.chrono.BaseChronology;
import org.joda.time.tz.DefaultNameProvider;
import org.joda.time.tz.UTCProvider;
import org.joda.time.tz.ZoneInfoProvider;
import java.lang.ref.SoftReference;
import org.joda.time.tz.FixedDateTimeZone;
import java.lang.ref.Reference;
import java.util.HashMap;
import org.joda.time.field.FieldUtils;
import java.security.Permission;
import java.util.TimeZone;
import java.util.Map;
import org.joda.time.format.DateTimeFormatter;
import java.util.Set;
import org.joda.time.tz.NameProvider;
import org.joda.time.tz.Provider;
import java.io.Serializable;

public abstract class DateTimeZone implements Serializable
{
    private static final long serialVersionUID = 5546345482340108586L;
    public static final DateTimeZone UTC;
    private static Provider cProvider;
    private static NameProvider cNameProvider;
    private static Set cAvailableIDs;
    private static volatile DateTimeZone cDefault;
    private static DateTimeFormatter cOffsetFormatter;
    private static Map iFixedOffsetCache;
    private static Map cZoneIdConversion;
    private final String iID;
    static /* synthetic */ Class class$org$joda$time$DateTimeZone;
    
    public static DateTimeZone getDefault() {
        DateTimeZone dateTimeZone = DateTimeZone.cDefault;
        if (dateTimeZone == null) {
            Class class$;
            Class class$org$joda$time$DateTimeZone;
            if (DateTimeZone.class$org$joda$time$DateTimeZone == null) {
                class$org$joda$time$DateTimeZone = (DateTimeZone.class$org$joda$time$DateTimeZone = (class$ = class$("org.joda.time.DateTimeZone")));
            }
            else {
                class$ = (class$org$joda$time$DateTimeZone = DateTimeZone.class$org$joda$time$DateTimeZone);
            }
            final Class clazz = class$org$joda$time$DateTimeZone;
            synchronized (class$) {
                dateTimeZone = DateTimeZone.cDefault;
                if (dateTimeZone == null) {
                    DateTimeZone cDefault = null;
                    try {
                        try {
                            final String property = System.getProperty("user.timezone");
                            if (property != null) {
                                cDefault = forID(property);
                            }
                        }
                        catch (RuntimeException ex) {}
                        if (cDefault == null) {
                            cDefault = forTimeZone(TimeZone.getDefault());
                        }
                    }
                    catch (IllegalArgumentException ex2) {}
                    if (cDefault == null) {
                        cDefault = DateTimeZone.UTC;
                    }
                    dateTimeZone = (DateTimeZone.cDefault = cDefault);
                }
            }
        }
        return dateTimeZone;
    }
    
    public static void setDefault(final DateTimeZone cDefault) throws SecurityException {
        final SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new JodaTimePermission("DateTimeZone.setDefault"));
        }
        if (cDefault == null) {
            throw new IllegalArgumentException("The datetime zone must not be null");
        }
        Class class$;
        Class class$org$joda$time$DateTimeZone;
        if (DateTimeZone.class$org$joda$time$DateTimeZone == null) {
            class$org$joda$time$DateTimeZone = (DateTimeZone.class$org$joda$time$DateTimeZone = (class$ = class$("org.joda.time.DateTimeZone")));
        }
        else {
            class$ = (class$org$joda$time$DateTimeZone = DateTimeZone.class$org$joda$time$DateTimeZone);
        }
        final Class clazz = class$org$joda$time$DateTimeZone;
        synchronized (class$) {
            DateTimeZone.cDefault = cDefault;
        }
    }
    
    public static DateTimeZone forID(String printOffset) {
        if (printOffset == null) {
            return getDefault();
        }
        if (printOffset.equals("UTC")) {
            return DateTimeZone.UTC;
        }
        final DateTimeZone zone = DateTimeZone.cProvider.getZone(printOffset);
        if (zone != null) {
            return zone;
        }
        if (!printOffset.startsWith("+") && !printOffset.startsWith("-")) {
            throw new IllegalArgumentException("The datetime zone id '" + printOffset + "' is not recognised");
        }
        final int offset = parseOffset(printOffset);
        if (offset == 0L) {
            return DateTimeZone.UTC;
        }
        printOffset = printOffset(offset);
        return fixedOffsetZone(printOffset, offset);
    }
    
    public static DateTimeZone forOffsetHours(final int n) throws IllegalArgumentException {
        return forOffsetHoursMinutes(n, 0);
    }
    
    public static DateTimeZone forOffsetHoursMinutes(final int n, int n2) throws IllegalArgumentException {
        if (n == 0 && n2 == 0) {
            return DateTimeZone.UTC;
        }
        if (n2 < 0 || n2 > 59) {
            throw new IllegalArgumentException("Minutes out of range: " + n2);
        }
        int safeMultiply2;
        try {
            final int safeMultiply = FieldUtils.safeMultiply(n, 60);
            if (safeMultiply < 0) {
                n2 = FieldUtils.safeAdd(safeMultiply, -n2);
            }
            else {
                n2 = FieldUtils.safeAdd(safeMultiply, n2);
            }
            safeMultiply2 = FieldUtils.safeMultiply(n2, 60000);
        }
        catch (ArithmeticException ex) {
            throw new IllegalArgumentException("Offset is too large");
        }
        return forOffsetMillis(safeMultiply2);
    }
    
    public static DateTimeZone forOffsetMillis(final int n) {
        return fixedOffsetZone(printOffset(n), n);
    }
    
    public static DateTimeZone forTimeZone(final TimeZone timeZone) {
        if (timeZone == null) {
            return getDefault();
        }
        final String id = timeZone.getID();
        if (id.equals("UTC")) {
            return DateTimeZone.UTC;
        }
        DateTimeZone dateTimeZone = null;
        final String convertedId = getConvertedId(id);
        if (convertedId != null) {
            dateTimeZone = DateTimeZone.cProvider.getZone(convertedId);
        }
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.cProvider.getZone(id);
        }
        if (dateTimeZone != null) {
            return dateTimeZone;
        }
        if (convertedId == null) {
            final String displayName = timeZone.getDisplayName();
            if (displayName.startsWith("GMT+") || displayName.startsWith("GMT-")) {
                final int offset = parseOffset(displayName.substring(3));
                if (offset == 0L) {
                    return DateTimeZone.UTC;
                }
                return fixedOffsetZone(printOffset(offset), offset);
            }
        }
        throw new IllegalArgumentException("The datetime zone id '" + id + "' is not recognised");
    }
    
    private static synchronized DateTimeZone fixedOffsetZone(final String s, final int n) {
        if (n == 0) {
            return DateTimeZone.UTC;
        }
        if (DateTimeZone.iFixedOffsetCache == null) {
            DateTimeZone.iFixedOffsetCache = new HashMap();
        }
        final Reference<DateTimeZone> reference = DateTimeZone.iFixedOffsetCache.get(s);
        if (reference != null) {
            final DateTimeZone dateTimeZone = reference.get();
            if (dateTimeZone != null) {
                return dateTimeZone;
            }
        }
        final FixedDateTimeZone fixedDateTimeZone = new FixedDateTimeZone(s, null, n, n);
        DateTimeZone.iFixedOffsetCache.put(s, new SoftReference<DateTimeZone>(fixedDateTimeZone));
        return fixedDateTimeZone;
    }
    
    public static Set getAvailableIDs() {
        return DateTimeZone.cAvailableIDs;
    }
    
    public static Provider getProvider() {
        return DateTimeZone.cProvider;
    }
    
    public static void setProvider(final Provider provider0) throws SecurityException {
        final SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new JodaTimePermission("DateTimeZone.setProvider"));
        }
        setProvider0(provider0);
    }
    
    private static void setProvider0(Provider defaultProvider) {
        if (defaultProvider == null) {
            defaultProvider = getDefaultProvider();
        }
        final Set availableIDs = defaultProvider.getAvailableIDs();
        if (availableIDs == null || availableIDs.size() == 0) {
            throw new IllegalArgumentException("The provider doesn't have any available ids");
        }
        if (!availableIDs.contains("UTC")) {
            throw new IllegalArgumentException("The provider doesn't support UTC");
        }
        if (!DateTimeZone.UTC.equals(defaultProvider.getZone("UTC"))) {
            throw new IllegalArgumentException("Invalid UTC zone provided");
        }
        DateTimeZone.cProvider = defaultProvider;
        DateTimeZone.cAvailableIDs = availableIDs;
    }
    
    private static Provider getDefaultProvider() {
        Provider provider = null;
        try {
            final String property = System.getProperty("org.joda.time.DateTimeZone.Provider");
            if (property != null) {
                try {
                    provider = (Provider)Class.forName(property).newInstance();
                }
                catch (Exception ex) {
                    final Thread currentThread = Thread.currentThread();
                    currentThread.getThreadGroup().uncaughtException(currentThread, ex);
                }
            }
        }
        catch (SecurityException ex3) {}
        if (provider == null) {
            try {
                provider = new ZoneInfoProvider("org/joda/time/tz/data");
            }
            catch (Exception ex2) {
                final Thread currentThread2 = Thread.currentThread();
                currentThread2.getThreadGroup().uncaughtException(currentThread2, ex2);
            }
        }
        if (provider == null) {
            provider = new UTCProvider();
        }
        return provider;
    }
    
    public static NameProvider getNameProvider() {
        return DateTimeZone.cNameProvider;
    }
    
    public static void setNameProvider(final NameProvider nameProvider0) throws SecurityException {
        final SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new JodaTimePermission("DateTimeZone.setNameProvider"));
        }
        setNameProvider0(nameProvider0);
    }
    
    private static void setNameProvider0(NameProvider defaultNameProvider) {
        if (defaultNameProvider == null) {
            defaultNameProvider = getDefaultNameProvider();
        }
        DateTimeZone.cNameProvider = defaultNameProvider;
    }
    
    private static NameProvider getDefaultNameProvider() {
        NameProvider nameProvider = null;
        try {
            final String property = System.getProperty("org.joda.time.DateTimeZone.NameProvider");
            if (property != null) {
                try {
                    nameProvider = (NameProvider)Class.forName(property).newInstance();
                }
                catch (Exception ex) {
                    final Thread currentThread = Thread.currentThread();
                    currentThread.getThreadGroup().uncaughtException(currentThread, ex);
                }
            }
        }
        catch (SecurityException ex2) {}
        if (nameProvider == null) {
            nameProvider = new DefaultNameProvider();
        }
        return nameProvider;
    }
    
    private static synchronized String getConvertedId(final String s) {
        Map<String, String> cZoneIdConversion = (Map<String, String>)DateTimeZone.cZoneIdConversion;
        if (cZoneIdConversion == null) {
            cZoneIdConversion = new HashMap<String, String>();
            cZoneIdConversion.put("GMT", "UTC");
            cZoneIdConversion.put("MIT", "Pacific/Apia");
            cZoneIdConversion.put("HST", "Pacific/Honolulu");
            cZoneIdConversion.put("AST", "America/Anchorage");
            cZoneIdConversion.put("PST", "America/Los_Angeles");
            cZoneIdConversion.put("MST", "America/Denver");
            cZoneIdConversion.put("PNT", "America/Phoenix");
            cZoneIdConversion.put("CST", "America/Chicago");
            cZoneIdConversion.put("EST", "America/New_York");
            cZoneIdConversion.put("IET", "America/Indianapolis");
            cZoneIdConversion.put("PRT", "America/Puerto_Rico");
            cZoneIdConversion.put("CNT", "America/St_Johns");
            cZoneIdConversion.put("AGT", "America/Buenos_Aires");
            cZoneIdConversion.put("BET", "America/Sao_Paulo");
            cZoneIdConversion.put("WET", "Europe/London");
            cZoneIdConversion.put("ECT", "Europe/Paris");
            cZoneIdConversion.put("ART", "Africa/Cairo");
            cZoneIdConversion.put("CAT", "Africa/Harare");
            cZoneIdConversion.put("EET", "Europe/Bucharest");
            cZoneIdConversion.put("EAT", "Africa/Addis_Ababa");
            cZoneIdConversion.put("MET", "Asia/Tehran");
            cZoneIdConversion.put("NET", "Asia/Yerevan");
            cZoneIdConversion.put("PLT", "Asia/Karachi");
            cZoneIdConversion.put("IST", "Asia/Calcutta");
            cZoneIdConversion.put("BST", "Asia/Dhaka");
            cZoneIdConversion.put("VST", "Asia/Saigon");
            cZoneIdConversion.put("CTT", "Asia/Shanghai");
            cZoneIdConversion.put("JST", "Asia/Tokyo");
            cZoneIdConversion.put("ACT", "Australia/Darwin");
            cZoneIdConversion.put("AET", "Australia/Sydney");
            cZoneIdConversion.put("SST", "Pacific/Guadalcanal");
            cZoneIdConversion.put("NST", "Pacific/Auckland");
            DateTimeZone.cZoneIdConversion = cZoneIdConversion;
        }
        return cZoneIdConversion.get(s);
    }
    
    private static int parseOffset(final String s) {
        return -(int)offsetFormatter().withChronology(new BaseChronology() {
            public DateTimeZone getZone() {
                return null;
            }
            
            public Chronology withUTC() {
                return this;
            }
            
            public Chronology withZone(final DateTimeZone dateTimeZone) {
                return this;
            }
            
            public String toString() {
                return this.getClass().getName();
            }
        }).parseMillis(s);
    }
    
    private static String printOffset(int n) {
        final StringBuffer sb = new StringBuffer();
        if (n >= 0) {
            sb.append('+');
        }
        else {
            sb.append('-');
            n = -n;
        }
        final int n2 = n / 3600000;
        FormatUtils.appendPaddedInteger(sb, n2, 2);
        n -= n2 * 3600000;
        final int n3 = n / 60000;
        sb.append(':');
        FormatUtils.appendPaddedInteger(sb, n3, 2);
        n -= n3 * 60000;
        if (n == 0) {
            return sb.toString();
        }
        final int n4 = n / 1000;
        sb.append(':');
        FormatUtils.appendPaddedInteger(sb, n4, 2);
        n -= n4 * 1000;
        if (n == 0) {
            return sb.toString();
        }
        sb.append('.');
        FormatUtils.appendPaddedInteger(sb, n, 3);
        return sb.toString();
    }
    
    private static synchronized DateTimeFormatter offsetFormatter() {
        if (DateTimeZone.cOffsetFormatter == null) {
            DateTimeZone.cOffsetFormatter = new DateTimeFormatterBuilder().appendTimeZoneOffset(null, true, 2, 4).toFormatter();
        }
        return DateTimeZone.cOffsetFormatter;
    }
    
    protected DateTimeZone(final String iid) {
        if (iid == null) {
            throw new IllegalArgumentException("Id must not be null");
        }
        this.iID = iid;
    }
    
    public final String getID() {
        return this.iID;
    }
    
    public abstract String getNameKey(final long p0);
    
    public final String getShortName(final long n) {
        return this.getShortName(n, null);
    }
    
    public String getShortName(final long n, Locale default1) {
        if (default1 == null) {
            default1 = Locale.getDefault();
        }
        final String nameKey = this.getNameKey(n);
        if (nameKey == null) {
            return this.iID;
        }
        final String shortName = DateTimeZone.cNameProvider.getShortName(default1, this.iID, nameKey);
        if (shortName != null) {
            return shortName;
        }
        return printOffset(this.getOffset(n));
    }
    
    public final String getName(final long n) {
        return this.getName(n, null);
    }
    
    public String getName(final long n, Locale default1) {
        if (default1 == null) {
            default1 = Locale.getDefault();
        }
        final String nameKey = this.getNameKey(n);
        if (nameKey == null) {
            return this.iID;
        }
        final String name = DateTimeZone.cNameProvider.getName(default1, this.iID, nameKey);
        if (name != null) {
            return name;
        }
        return printOffset(this.getOffset(n));
    }
    
    public abstract int getOffset(final long p0);
    
    public final int getOffset(final ReadableInstant readableInstant) {
        if (readableInstant == null) {
            return this.getOffset(DateTimeUtils.currentTimeMillis());
        }
        return this.getOffset(readableInstant.getMillis());
    }
    
    public abstract int getStandardOffset(final long p0);
    
    public boolean isStandardOffset(final long n) {
        return this.getOffset(n) == this.getStandardOffset(n);
    }
    
    public int getOffsetFromLocal(final long n) {
        final int offset = this.getOffset(n);
        final int offset2 = this.getOffset(n - offset);
        if (offset != offset2 && offset - offset2 < 0 && this.nextTransition(n - offset) != this.nextTransition(n - offset2)) {
            return offset;
        }
        return offset2;
    }
    
    public long convertUTCToLocal(final long n) {
        final int offset = this.getOffset(n);
        final long n2 = n + offset;
        if ((n ^ n2) < 0L && (n ^ offset) >= 0L) {
            throw new ArithmeticException("Adding time zone offset caused overflow");
        }
        return n2;
    }
    
    public long convertLocalToUTC(final long n, final boolean b) {
        final int offset = this.getOffset(n);
        int offset2 = this.getOffset(n - offset);
        if (offset != offset2 && (b || offset < 0)) {
            long nextTransition = this.nextTransition(n - offset);
            if (nextTransition == n - offset) {
                nextTransition = Long.MAX_VALUE;
            }
            long nextTransition2 = this.nextTransition(n - offset2);
            if (nextTransition2 == n - offset2) {
                nextTransition2 = Long.MAX_VALUE;
            }
            if (nextTransition != nextTransition2) {
                if (b) {
                    throw new IllegalArgumentException("Illegal instant due to time zone offset transition: " + DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS").print(new Instant(n)) + " (" + this.getID() + ")");
                }
                offset2 = offset;
            }
        }
        final long n2 = n - offset2;
        if ((n ^ n2) < 0L && (n ^ offset2) < 0L) {
            throw new ArithmeticException("Subtracting time zone offset caused overflow");
        }
        return n2;
    }
    
    public long getMillisKeepLocal(DateTimeZone default1, final long n) {
        if (default1 == null) {
            default1 = getDefault();
        }
        if (default1 == this) {
            return n;
        }
        final long n2 = n + this.getOffset(n);
        return n2 - default1.getOffsetFromLocal(n2);
    }
    
    public boolean isLocalDateTimeGap(final LocalDateTime localDateTime) {
        if (this.isFixed()) {
            return false;
        }
        try {
            localDateTime.toDateTime(this);
            return false;
        }
        catch (IllegalArgumentException ex) {
            return true;
        }
    }
    
    public abstract boolean isFixed();
    
    public abstract long nextTransition(final long p0);
    
    public abstract long previousTransition(final long p0);
    
    public TimeZone toTimeZone() {
        return TimeZone.getTimeZone(this.iID);
    }
    
    public abstract boolean equals(final Object p0);
    
    public int hashCode() {
        return 57 + this.getID().hashCode();
    }
    
    public String toString() {
        return this.getID();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return new Stub(this.iID);
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
        UTC = new FixedDateTimeZone("UTC", "UTC", 0, 0);
        setProvider0(null);
        setNameProvider0(null);
    }
    
    private static final class Stub implements Serializable
    {
        private static final long serialVersionUID = -6471952376487863581L;
        private transient String iID;
        
        Stub(final String iid) {
            this.iID = iid;
        }
        
        private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.writeUTF(this.iID);
        }
        
        private void readObject(final ObjectInputStream objectInputStream) throws IOException {
            this.iID = objectInputStream.readUTF();
        }
        
        private Object readResolve() throws ObjectStreamException {
            return DateTimeZone.forID(this.iID);
        }
    }
}
