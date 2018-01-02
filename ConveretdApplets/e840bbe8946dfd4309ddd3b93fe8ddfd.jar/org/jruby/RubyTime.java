// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import java.util.HashMap;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.IllegalFieldValueException;
import org.jruby.runtime.builtin.InstanceVariables;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.Calendar;
import org.jruby.runtime.Visibility;
import org.jruby.runtime.Block;
import org.joda.time.ReadableInstant;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.ThreadContext;
import org.jruby.util.RubyDateFormat;
import java.util.Locale;
import org.jruby.anno.JRubyMethod;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.TimeZone;
import org.joda.time.DateTimeZone;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import java.util.Map;
import org.jruby.util.ByteList;
import java.util.regex.Pattern;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.DateTime;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Time" }, include = { "Comparable" })
public class RubyTime extends RubyObject
{
    public static final String UTC = "UTC";
    private DateTime dt;
    private long usec;
    private static final DateTimeFormatter ONE_DAY_CTIME_FORMATTER;
    private static final DateTimeFormatter TWO_DAY_CTIME_FORMATTER;
    private static final DateTimeFormatter TO_S_FORMATTER;
    private static final DateTimeFormatter TO_S_UTC_FORMATTER;
    private static final DateTimeFormatter TO_S_FORMATTER_19;
    private static final DateTimeFormatter TO_S_UTC_FORMATTER_19;
    private static final Pattern TZ_PATTERN;
    private static final Pattern TIME_OFFSET_PATTERN;
    private static final ByteList TZ_STRING;
    private static final Map<String, String> LONG_TZNAME;
    private static final Map<String, String> SHORT_STD_TZNAME;
    private static final Map<String, String> SHORT_DL_TZNAME;
    private static ObjectAllocator TIME_ALLOCATOR;
    private static final String[] MONTHS;
    private static final Map<String, Integer> MONTHS_MAP;
    private static final int[] time_min;
    private static final int[] time_max;
    private static final int ARG_SIZE = 7;
    
    public int getNativeTypeIndex() {
        return 19;
    }
    
    private static IRubyObject getEnvTimeZone(final Ruby runtime) {
        final RubyString tzVar = runtime.newString(RubyTime.TZ_STRING);
        final RubyHash h = (RubyHash)runtime.getObject().fastGetConstant("ENV");
        final IRubyObject tz = h.op_aref(runtime.getCurrentContext(), tzVar);
        return tz;
    }
    
    public static DateTimeZone getLocalTimeZone(final Ruby runtime) {
        final IRubyObject tz = getEnvTimeZone(runtime);
        if (tz == null || !(tz instanceof RubyString)) {
            return DateTimeZone.getDefault();
        }
        return getTimeZone(runtime, tz.toString());
    }
    
    public static DateTimeZone getTimeZone(final Ruby runtime, String zone) {
        final DateTimeZone cachedZone = runtime.getTimezoneCache().get(zone);
        if (cachedZone != null) {
            return cachedZone;
        }
        final String originalZone = zone;
        TimeZone tz = TimeZone.getTimeZone(getEnvTimeZone(runtime).toString());
        final Matcher tzMatcher = RubyTime.TZ_PATTERN.matcher(zone);
        if (tzMatcher.matches()) {
            String sign = tzMatcher.group(2);
            final String hours = tzMatcher.group(3);
            final String minutes = tzMatcher.group(4);
            if (("00".equals(hours) || "0".equals(hours)) && (minutes == null || ":00".equals(minutes) || ":0".equals(minutes))) {
                zone = "Etc/GMT";
            }
            else {
                sign = ("-".equals(sign) ? "+" : "-");
                zone = "GMT" + sign + hours;
                if (minutes != null) {
                    zone += minutes;
                }
            }
            tz = TimeZone.getTimeZone(zone);
        }
        else if (RubyTime.LONG_TZNAME.containsKey(zone)) {
            tz.setID(RubyTime.LONG_TZNAME.get(zone.toUpperCase()));
        }
        if ("GMT".equalsIgnoreCase(zone) || "UTC".equalsIgnoreCase(zone)) {
            zone = "Etc/" + zone;
            tz = TimeZone.getTimeZone(zone);
        }
        final DateTimeZone dtz = DateTimeZone.forTimeZone(tz);
        runtime.getTimezoneCache().put(originalZone, dtz);
        return dtz;
    }
    
    public RubyTime(final Ruby runtime, final RubyClass rubyClass) {
        super(runtime, rubyClass);
    }
    
    public RubyTime(final Ruby runtime, final RubyClass rubyClass, final DateTime dt) {
        super(runtime, rubyClass);
        this.dt = dt;
    }
    
    public static RubyClass createTimeClass(final Ruby runtime) {
        final RubyClass timeClass = runtime.defineClass("Time", runtime.getObject(), RubyTime.TIME_ALLOCATOR);
        timeClass.index = 19;
        timeClass.setReifiedClass(RubyTime.class);
        runtime.setTime(timeClass);
        timeClass.includeModule(runtime.getComparable());
        timeClass.defineAnnotatedMethods(RubyTime.class);
        return timeClass;
    }
    
    public void setUSec(final long usec) {
        this.usec = usec;
    }
    
    public long getUSec() {
        return this.usec;
    }
    
    public void updateCal(final DateTime dt) {
        this.dt = dt;
    }
    
    protected long getTimeInMillis() {
        return this.dt.getMillis();
    }
    
    public static RubyTime newTime(final Ruby runtime, final long milliseconds) {
        return newTime(runtime, new DateTime(milliseconds));
    }
    
    public static RubyTime newTime(final Ruby runtime, final DateTime dt) {
        return new RubyTime(runtime, runtime.getTime(), dt);
    }
    
    public static RubyTime newTime(final Ruby runtime, final DateTime dt, final long usec) {
        final RubyTime t = new RubyTime(runtime, runtime.getTime(), dt);
        t.setUSec(usec);
        return t;
    }
    
    public Class<?> getJavaClass() {
        return Date.class;
    }
    
    @JRubyMethod(name = { "initialize_copy" }, required = 1)
    public IRubyObject initialize_copy(final IRubyObject original) {
        if (!(original instanceof RubyTime)) {
            throw this.getRuntime().newTypeError("Expecting an instance of class Time");
        }
        final RubyTime originalTime = (RubyTime)original;
        this.dt = originalTime.dt;
        this.usec = originalTime.usec;
        return this;
    }
    
    @JRubyMethod(name = { "succ" })
    public RubyTime succ() {
        return newTime(this.getRuntime(), this.dt.plusSeconds(1));
    }
    
    @JRubyMethod(name = { "gmtime", "utc" })
    public RubyTime gmtime() {
        this.dt = this.dt.withZone(DateTimeZone.UTC);
        return this;
    }
    
    @JRubyMethod(name = { "localtime" })
    public RubyTime localtime() {
        this.dt = this.dt.withZone(getLocalTimeZone(this.getRuntime()));
        return this;
    }
    
    @JRubyMethod(name = { "gmt?", "utc?", "gmtime?" })
    public RubyBoolean gmt() {
        return this.getRuntime().newBoolean(this.dt.getZone().getID().equals("UTC"));
    }
    
    @JRubyMethod(name = { "getgm", "getutc" })
    public RubyTime getgm() {
        return newTime(this.getRuntime(), this.dt.withZone(DateTimeZone.UTC), this.getUSec());
    }
    
    @JRubyMethod(name = { "getlocal" })
    public RubyTime getlocal() {
        return newTime(this.getRuntime(), this.dt.withZone(getLocalTimeZone(this.getRuntime())), this.getUSec());
    }
    
    @JRubyMethod(name = { "strftime" }, required = 1)
    public RubyString strftime(final IRubyObject format) {
        final RubyDateFormat rubyDateFormat = new RubyDateFormat("-", Locale.US, this.getRuntime().is1_9());
        rubyDateFormat.applyPattern(format.convertToString().getUnicodeValue());
        rubyDateFormat.setDateTime(this.dt);
        final String result = rubyDateFormat.format(null);
        return this.getRuntime().newString(result);
    }
    
    @JRubyMethod(name = { "==" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_equal(final ThreadContext context, final IRubyObject other) {
        if (other.isNil()) {
            return RubyBoolean.newBoolean(this.getRuntime(), false);
        }
        if (other instanceof RubyTime) {
            return this.getRuntime().newBoolean(this.cmp((RubyTime)other) == 0);
        }
        return RubyComparable.op_equal(context, this, other);
    }
    
    @JRubyMethod(name = { ">=" }, required = 1)
    public IRubyObject op_ge(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyTime) {
            return this.getRuntime().newBoolean(this.cmp((RubyTime)other) >= 0);
        }
        return RubyComparable.op_ge(context, this, other);
    }
    
    @JRubyMethod(name = { ">" }, required = 1)
    public IRubyObject op_gt(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyTime) {
            return this.getRuntime().newBoolean(this.cmp((RubyTime)other) > 0);
        }
        return RubyComparable.op_gt(context, this, other);
    }
    
    @JRubyMethod(name = { "<=" }, required = 1)
    public IRubyObject op_le(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyTime) {
            return this.getRuntime().newBoolean(this.cmp((RubyTime)other) <= 0);
        }
        return RubyComparable.op_le(context, this, other);
    }
    
    @JRubyMethod(name = { "<" }, required = 1)
    public IRubyObject op_lt(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyTime) {
            return this.getRuntime().newBoolean(this.cmp((RubyTime)other) < 0);
        }
        return RubyComparable.op_lt(context, this, other);
    }
    
    private int cmp(final RubyTime other) {
        final long millis = this.getTimeInMillis();
        final long millis_other = other.getTimeInMillis();
        final long usec_other = other.usec;
        if (millis > millis_other || (millis == millis_other && this.usec > usec_other)) {
            return 1;
        }
        if (millis < millis_other || (millis == millis_other && this.usec < usec_other)) {
            return -1;
        }
        return 0;
    }
    
    @JRubyMethod(name = { "+" }, required = 1, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_plus(final IRubyObject other) {
        if (other instanceof RubyTime) {
            throw this.getRuntime().newTypeError("time + time ?");
        }
        final long adjustment = Math.round(RubyNumeric.num2dbl(other) * 1000000.0);
        return this.opPlusCommon(adjustment);
    }
    
    @JRubyMethod(name = { "+" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_plus19(final ThreadContext context, IRubyObject other) {
        this.checkOpCoercion(context, other);
        if (other instanceof RubyTime) {
            throw this.getRuntime().newTypeError("time + time ?");
        }
        other = other.callMethod(context, "to_r");
        final long adjustment = (long)(Object)new Double(RubyNumeric.num2dbl(other) * 1000000.0);
        return this.opPlusCommon(adjustment);
    }
    
    private IRubyObject opPlusCommon(long adjustment) {
        long micro = adjustment % 1000L;
        adjustment /= 1000L;
        long time = this.getTimeInMillis();
        time += adjustment;
        if (this.getUSec() + micro >= 1000L) {
            ++time;
            micro = this.getUSec() + micro - 1000L;
        }
        else {
            micro += this.getUSec();
        }
        final RubyTime newTime = new RubyTime(this.getRuntime(), this.getMetaClass());
        newTime.dt = new DateTime(time).withZone(this.dt.getZone());
        newTime.setUSec(micro);
        return newTime;
    }
    
    private void checkOpCoercion(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyString) {
            throw context.getRuntime().newTypeError("no implicit conversion to rational from string");
        }
        if (other.isNil()) {
            throw context.getRuntime().newTypeError("no implicit conversion to rational from nil");
        }
        if (!other.respondsTo("to_r")) {
            throw context.getRuntime().newTypeError("can't convert " + other.getMetaClass().getBaseName() + " into Rational");
        }
    }
    
    private IRubyObject opMinus(final RubyTime other) {
        long time = this.getTimeInMillis() * 1000L + this.getUSec();
        time -= other.getTimeInMillis() * 1000L + other.getUSec();
        return RubyFloat.newFloat(this.getRuntime(), time / 1000000.0);
    }
    
    @JRubyMethod(name = { "-" }, required = 1, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_minus(final IRubyObject other) {
        if (other instanceof RubyTime) {
            return this.opMinus((RubyTime)other);
        }
        return this.opMinusCommon(other);
    }
    
    @JRubyMethod(name = { "-" }, required = 1, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_minus19(final ThreadContext context, final IRubyObject other) {
        this.checkOpCoercion(context, other);
        if (other instanceof RubyTime) {
            return this.opMinus((RubyTime)other);
        }
        return this.opMinusCommon(other.callMethod(context, "to_r"));
    }
    
    private IRubyObject opMinusCommon(final IRubyObject other) {
        long time = this.getTimeInMillis();
        long adjustment = Math.round(RubyNumeric.num2dbl(other) * 1000000.0);
        long micro = adjustment % 1000L;
        adjustment /= 1000L;
        time -= adjustment;
        if (this.getUSec() < micro) {
            --time;
            micro = 1000L - (micro - this.getUSec());
        }
        else {
            micro = this.getUSec() - micro;
        }
        final RubyTime newTime = new RubyTime(this.getRuntime(), this.getMetaClass());
        newTime.dt = new DateTime(time).withZone(this.dt.getZone());
        newTime.setUSec(micro);
        return newTime;
    }
    
    @JRubyMethod(name = { "===" }, required = 1)
    public IRubyObject op_eqq(final ThreadContext context, final IRubyObject other) {
        return (RubyNumeric.fix2int(RuntimeHelpers.invokedynamic(context, this, 4, other)) == 0) ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "<=>" }, required = 1)
    public IRubyObject op_cmp(final ThreadContext context, final IRubyObject other) {
        if (other instanceof RubyTime) {
            return context.getRuntime().newFixnum(this.cmp((RubyTime)other));
        }
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "eql?" }, required = 1)
    public IRubyObject eql_p(final IRubyObject other) {
        if (other instanceof RubyTime) {
            final RubyTime otherTime = (RubyTime)other;
            return (this.usec == otherTime.usec && this.getTimeInMillis() == otherTime.getTimeInMillis()) ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
        }
        return this.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "asctime", "ctime" })
    public RubyString asctime() {
        DateTimeFormatter simpleDateFormat;
        if (this.dt.getDayOfMonth() < 10) {
            simpleDateFormat = RubyTime.ONE_DAY_CTIME_FORMATTER;
        }
        else {
            simpleDateFormat = RubyTime.TWO_DAY_CTIME_FORMATTER;
        }
        final String result = simpleDateFormat.print(this.dt);
        return this.getRuntime().newString(result);
    }
    
    @JRubyMethod(name = { "to_s", "inspect" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject to_s() {
        return this.inspectCommon(RubyTime.TO_S_FORMATTER, RubyTime.TO_S_UTC_FORMATTER);
    }
    
    @JRubyMethod(name = { "to_s", "inspect" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject to_s19() {
        return this.inspectCommon(RubyTime.TO_S_FORMATTER_19, RubyTime.TO_S_UTC_FORMATTER_19);
    }
    
    private IRubyObject inspectCommon(final DateTimeFormatter formatter, final DateTimeFormatter utcFormatter) {
        DateTimeFormatter simpleDateFormat;
        if (this.dt.getZone() == DateTimeZone.UTC) {
            simpleDateFormat = utcFormatter;
        }
        else {
            simpleDateFormat = formatter;
        }
        final String result = simpleDateFormat.print(this.dt);
        return this.getRuntime().newString(result);
    }
    
    @JRubyMethod(name = { "to_a" })
    public RubyArray to_a() {
        return this.getRuntime().newArrayNoCopy(this.sec(), this.min(), this.hour(), this.mday(), this.month(), this.year(), this.wday(), this.yday(), this.isdst(), this.zone());
    }
    
    @JRubyMethod(name = { "to_f" })
    public RubyFloat to_f() {
        long time = this.getTimeInMillis();
        time = time * 1000L + this.usec;
        return RubyFloat.newFloat(this.getRuntime(), time / 1000000.0);
    }
    
    @JRubyMethod(name = { "to_i", "tv_sec" })
    public RubyInteger to_i() {
        return this.getRuntime().newFixnum(this.getTimeInMillis() / 1000L);
    }
    
    @JRubyMethod(name = { "nsec", "tv_nsec" }, compat = CompatVersion.RUBY1_9)
    public RubyInteger nsec() {
        return this.getRuntime().newFixnum(0);
    }
    
    @JRubyMethod(name = { "to_r" }, backtrace = true, compat = CompatVersion.RUBY1_9)
    public IRubyObject to_r(final ThreadContext context) {
        final IRubyObject rational = this.to_f().to_r(context);
        if (rational instanceof RubyRational) {
            final IRubyObject denominator = ((RubyRational)rational).denominator(context);
            if (RubyNumeric.num2long(denominator) == 1L) {
                return ((RubyRational)rational).numerator(context);
            }
        }
        return rational;
    }
    
    @JRubyMethod(name = { "usec", "tv_usec" })
    public RubyInteger usec() {
        return this.getRuntime().newFixnum(this.dt.getMillisOfSecond() * 1000 + this.getUSec());
    }
    
    public void setMicroseconds(final long mic) {
        final long millis = this.getTimeInMillis() % 1000L;
        long withoutMillis = this.getTimeInMillis() - millis;
        withoutMillis += mic / 1000L;
        this.dt = this.dt.withMillis(withoutMillis);
        this.usec = mic % 1000L;
    }
    
    public long microseconds() {
        return this.getTimeInMillis() % 1000L * 1000L + this.usec;
    }
    
    @JRubyMethod(name = { "sec" })
    public RubyInteger sec() {
        return this.getRuntime().newFixnum(this.dt.getSecondOfMinute());
    }
    
    @JRubyMethod(name = { "min" })
    public RubyInteger min() {
        return this.getRuntime().newFixnum(this.dt.getMinuteOfHour());
    }
    
    @JRubyMethod(name = { "hour" })
    public RubyInteger hour() {
        return this.getRuntime().newFixnum(this.dt.getHourOfDay());
    }
    
    @JRubyMethod(name = { "mday", "day" })
    public RubyInteger mday() {
        return this.getRuntime().newFixnum(this.dt.getDayOfMonth());
    }
    
    @JRubyMethod(name = { "month", "mon" })
    public RubyInteger month() {
        return this.getRuntime().newFixnum(this.dt.getMonthOfYear());
    }
    
    @JRubyMethod(name = { "year" })
    public RubyInteger year() {
        return this.getRuntime().newFixnum(this.dt.getYear());
    }
    
    @JRubyMethod(name = { "wday" })
    public RubyInteger wday() {
        return this.getRuntime().newFixnum(this.dt.getDayOfWeek() % 7);
    }
    
    @JRubyMethod(name = { "yday" })
    public RubyInteger yday() {
        return this.getRuntime().newFixnum(this.dt.getDayOfYear());
    }
    
    @JRubyMethod(name = { "subsec" }, compat = CompatVersion.RUBY1_9)
    public RubyRational subsec() {
        return this.getRuntime().newRational(this.dt.getMillisOfSecond(), 1000L);
    }
    
    @JRubyMethod(name = { "gmt_offset", "gmtoff", "utc_offset" })
    public RubyInteger gmt_offset() {
        final int offset = this.dt.getZone().getOffset(this.dt.getMillis());
        return this.getRuntime().newFixnum(offset / 1000);
    }
    
    @JRubyMethod(name = { "isdst", "dst?" })
    public RubyBoolean isdst() {
        return this.getRuntime().newBoolean(!this.dt.getZone().isStandardOffset(this.dt.getMillis()));
    }
    
    @JRubyMethod(name = { "zone" })
    public RubyString zone() {
        final Ruby runtime = this.getRuntime();
        final String envTZ = getEnvTimeZone(runtime).toString();
        if (RubyTime.SHORT_STD_TZNAME.containsKey(envTZ) && !this.dt.getZone().toTimeZone().inDaylightTime(this.dt.toDate())) {
            return runtime.newString(RubyTime.SHORT_STD_TZNAME.get(envTZ));
        }
        if (RubyTime.SHORT_DL_TZNAME.containsKey(envTZ) && this.dt.getZone().toTimeZone().inDaylightTime(this.dt.toDate())) {
            return runtime.newString(RubyTime.SHORT_DL_TZNAME.get(envTZ));
        }
        String zone = this.dt.getZone().getShortName(this.dt.getMillis());
        final Matcher offsetMatcher = RubyTime.TIME_OFFSET_PATTERN.matcher(zone);
        if (offsetMatcher.matches()) {
            final boolean minus_p = offsetMatcher.group(1).toString().equals("-");
            final int hourOffset = Integer.valueOf(offsetMatcher.group(2));
            if (zone.equals("+00:00")) {
                zone = "GMT";
            }
            else {
                zone = this.dt.getZone().getNameKey(this.dt.getMillis());
                if (zone == null) {
                    final char sign = minus_p ? '+' : '-';
                    zone = "GMT" + sign + hourOffset;
                }
            }
        }
        return runtime.newString(zone);
    }
    
    public void setDateTime(final DateTime dt) {
        this.dt = dt;
    }
    
    public DateTime getDateTime() {
        return this.dt;
    }
    
    public Date getJavaDate() {
        return this.dt.toDate();
    }
    
    @JRubyMethod(name = { "hash" })
    public RubyFixnum hash() {
        return this.getRuntime().newFixnum((int)((this.dt.getMillis() / 1000L ^ this.microseconds()) << 1) >> 1);
    }
    
    @JRubyMethod(name = { "_dump" }, optional = 1)
    public RubyString dump(final IRubyObject[] args, final Block unusedBlock) {
        final RubyString str = (RubyString)this.mdump();
        str.syncVariables(this);
        return str;
    }
    
    public RubyObject mdump() {
        final DateTime dateTime = this.dt.toDateTime(DateTimeZone.UTC);
        final byte[] dumpValue = new byte[8];
        int pe = Integer.MIN_VALUE | (this.gmt().isTrue() ? 1 : 0) << 30 | dateTime.getYear() - 1900 << 14 | dateTime.getMonthOfYear() - 1 << 10 | dateTime.getDayOfMonth() << 5 | dateTime.getHourOfDay();
        int se = dateTime.getMinuteOfHour() << 26 | dateTime.getSecondOfMinute() << 20 | dateTime.getMillisOfSecond() * 1000 + (int)this.usec;
        for (int i = 0; i < 4; ++i) {
            dumpValue[i] = (byte)(pe & 0xFF);
            pe >>>= 8;
        }
        for (int i = 4; i < 8; ++i) {
            dumpValue[i] = (byte)(se & 0xFF);
            se >>>= 8;
        }
        return RubyString.newString(this.getRuntime(), new ByteList(dumpValue));
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final Block block) {
        return this;
    }
    
    public static IRubyObject s_new(final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final Ruby runtime = recv.getRuntime();
        final RubyTime time = new RubyTime(runtime, (RubyClass)recv, new DateTime(getLocalTimeZone(runtime)));
        time.callInit(args, block);
        return time;
    }
    
    @Deprecated
    public static IRubyObject newInstance(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        return newInstance(context, recv);
    }
    
    @JRubyMethod(name = { "times" }, meta = true, compat = CompatVersion.RUBY1_8)
    public static IRubyObject times(final ThreadContext context, final IRubyObject recv) {
        context.getRuntime().getWarnings().warn("obsolete method Time::times; use Process::times");
        return RubyProcess.times(context, recv, Block.NULL_BLOCK);
    }
    
    @JRubyMethod(name = { "now" }, backtrace = true, meta = true)
    public static IRubyObject newInstance(final ThreadContext context, final IRubyObject recv) {
        final IRubyObject obj = ((RubyClass)recv).allocate();
        obj.getMetaClass().getBaseCallSites()[0].call(context, recv, obj);
        return obj;
    }
    
    @JRubyMethod(name = { "at" }, meta = true)
    public static IRubyObject at(final ThreadContext context, final IRubyObject recv, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        RubyTime time;
        if (arg instanceof RubyTime) {
            final RubyTime other = (RubyTime)arg;
            time = new RubyTime(runtime, (RubyClass)recv, other.dt);
            time.setUSec(other.getUSec());
        }
        else {
            time = new RubyTime(runtime, (RubyClass)recv, new DateTime(0L, getLocalTimeZone(runtime)));
            final long seconds = RubyNumeric.num2long(arg);
            long millisecs = 0L;
            long microsecs = 0L;
            if (arg instanceof RubyFloat || arg instanceof RubyRational) {
                final double dbl = RubyNumeric.num2dbl(arg);
                long micro = Math.round((dbl - seconds) * 1000000.0);
                if (dbl < 0.0 && micro != 0L) {
                    micro += 1000000L;
                }
                millisecs = micro / 1000L;
                microsecs = micro % 1000L;
            }
            time.setUSec(microsecs);
            time.dt = time.dt.withMillis(seconds * 1000L + millisecs);
        }
        time.getMetaClass().getBaseCallSites()[0].call(context, recv, time);
        return time;
    }
    
    @JRubyMethod(name = { "at" }, meta = true)
    public static IRubyObject at(final ThreadContext context, final IRubyObject recv, final IRubyObject arg1, final IRubyObject arg2) {
        final Ruby runtime = context.getRuntime();
        final RubyTime time = new RubyTime(runtime, (RubyClass)recv, new DateTime(0L, getLocalTimeZone(runtime)));
        final long seconds = RubyNumeric.num2long(arg1);
        long millisecs = 0L;
        long microsecs = 0L;
        final long tmp = RubyNumeric.num2long(arg2);
        millisecs = tmp / 1000L;
        microsecs = tmp % 1000L;
        time.setUSec(microsecs);
        time.dt = time.dt.withMillis(seconds * 1000L + millisecs);
        time.getMetaClass().getBaseCallSites()[0].call(context, recv, time);
        return time;
    }
    
    @JRubyMethod(name = { "local", "mktime" }, required = 1, optional = 9, meta = true)
    public static RubyTime new_local(final IRubyObject recv, final IRubyObject[] args) {
        return createTime(recv, args, false);
    }
    
    @JRubyMethod(name = { "new" }, optional = 10, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject new19(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args) {
        if (args.length == 0) {
            return newInstance(context, recv);
        }
        return createTime(recv, args, false);
    }
    
    @JRubyMethod(name = { "utc", "gm" }, required = 1, optional = 9, meta = true)
    public static RubyTime new_utc(final IRubyObject recv, final IRubyObject[] args) {
        return createTime(recv, args, true);
    }
    
    @JRubyMethod(name = { "_load" }, meta = true)
    public static RubyTime load(final IRubyObject recv, final IRubyObject from, final Block block) {
        return s_mload(recv, (RubyTime)((RubyClass)recv).allocate(), from);
    }
    
    public Object toJava(final Class target) {
        if (target.equals(Date.class)) {
            return this.getJavaDate();
        }
        if (target.equals(Calendar.class)) {
            final Calendar cal = Calendar.getInstance();
            cal.setTime(this.getJavaDate());
            return cal;
        }
        if (target.equals(DateTime.class)) {
            return this.dt;
        }
        if (target.equals(java.sql.Date.class)) {
            return new java.sql.Date(this.dt.getMillis());
        }
        if (target.equals(Time.class)) {
            return new Time(this.dt.getMillis());
        }
        if (target.equals(Timestamp.class)) {
            return new Timestamp(this.dt.getMillis());
        }
        if (target.isAssignableFrom(Date.class)) {
            return this.getJavaDate();
        }
        return super.toJava(target);
    }
    
    protected static RubyTime s_mload(final IRubyObject recv, final RubyTime time, final IRubyObject from) {
        final Ruby runtime = recv.getRuntime();
        DateTime dt = new DateTime(DateTimeZone.UTC);
        byte[] fromAsBytes = null;
        fromAsBytes = from.convertToString().getBytes();
        if (fromAsBytes.length != 8) {
            throw runtime.newTypeError("marshaled time format differ");
        }
        int p = 0;
        int s = 0;
        for (int i = 0; i < 4; ++i) {
            p |= (fromAsBytes[i] & 0xFF) << 8 * i;
        }
        for (int i = 4; i < 8; ++i) {
            s |= (fromAsBytes[i] & 0xFF) << 8 * (i - 4);
        }
        boolean utc = false;
        if ((p & Integer.MIN_VALUE) == 0x0) {
            dt = dt.withMillis(p * 1000L);
            time.setUSec((s & 0xFFFFF) % 1000);
        }
        else {
            p &= Integer.MAX_VALUE;
            utc = ((p >>> 30 & 0x1) == 0x1);
            dt = dt.withYear((p >>> 14 & 0xFFFF) + 1900);
            dt = dt.withMonthOfYear((p >>> 10 & 0xF) + 1);
            dt = dt.withDayOfMonth(p >>> 5 & 0x1F);
            dt = dt.withHourOfDay(p & 0x1F);
            dt = dt.withMinuteOfHour(s >>> 26 & 0x3F);
            dt = dt.withSecondOfMinute(s >>> 20 & 0x3F);
            dt = dt.withMillisOfSecond((s & 0xFFFFF) / 1000);
            time.setUSec((s & 0xFFFFF) % 1000);
        }
        time.setDateTime(dt);
        if (!utc) {
            time.localtime();
        }
        from.getInstanceVariables().copyInstanceVariablesInto(time);
        return time;
    }
    
    private static RubyTime createTime(final IRubyObject recv, IRubyObject[] args, final boolean gmt) {
        final Ruby runtime = recv.getRuntime();
        int len = 7;
        Boolean isDst = null;
        DateTimeZone dtz;
        if (gmt) {
            dtz = DateTimeZone.UTC;
        }
        else if (args.length == 10 && args[9] instanceof RubyString) {
            dtz = getTimeZone(runtime, ((RubyString)args[9]).toString());
        }
        else {
            dtz = getLocalTimeZone(runtime);
        }
        if (args.length == 10) {
            if (args[8] instanceof RubyBoolean) {
                isDst = ((RubyBoolean)args[8]).isTrue();
            }
            args = new IRubyObject[] { args[5], args[4], args[3], args[2], args[1], args[0], runtime.getNil() };
        }
        else {
            len = args.length;
            if (len < 7) {
                final IRubyObject[] newArgs = new IRubyObject[7];
                System.arraycopy(args, 0, newArgs, 0, args.length);
                for (int i = len; i < 7; ++i) {
                    newArgs[i] = runtime.getNil();
                }
                args = newArgs;
                len = 7;
            }
        }
        if (args[0] instanceof RubyString) {
            args[0] = RubyNumeric.str2inum(runtime, (RubyString)args[0], 10, false);
        }
        int year = (int)RubyNumeric.num2long(args[0]);
        int month = 1;
        if (len > 1) {
            if (!args[1].isNil()) {
                final IRubyObject tmp = args[1].checkStringType();
                if (!tmp.isNil()) {
                    final String monthString = tmp.toString().toLowerCase();
                    final Integer monthInt = RubyTime.MONTHS_MAP.get(monthString);
                    if (monthInt != null) {
                        month = monthInt;
                    }
                    else {
                        try {
                            month = Integer.parseInt(monthString);
                        }
                        catch (NumberFormatException nfExcptn) {
                            throw runtime.newArgumentError("Argument out of range.");
                        }
                    }
                }
                else {
                    month = (int)RubyNumeric.num2long(args[1]);
                }
            }
            if (1 > month || month > 12) {
                throw runtime.newArgumentError("Argument out of range: for month: " + month);
            }
        }
        final int[] int_args = { 1, 0, 0, 0, 0, 0 };
        for (int j = 0; int_args.length >= j + 2; ++j) {
            if (!args[j + 2].isNil()) {
                if (!(args[j + 2] instanceof RubyNumeric)) {
                    args[j + 2] = args[j + 2].callMethod(runtime.getCurrentContext(), "to_i");
                }
                final long value = RubyNumeric.num2long(args[j + 2]);
                if (RubyTime.time_min[j] > value || value > RubyTime.time_max[j]) {
                    throw runtime.newArgumentError("argument out of range.");
                }
                int_args[j] = (int)value;
            }
        }
        if (!runtime.is1_9()) {
            if (0 <= year && year < 39) {
                year += 2000;
            }
            else if (69 <= year && year < 139) {
                year += 1900;
            }
        }
        DateTime dt;
        try {
            dt = new DateTime(year, 1, 1, 0, 0, 0, 0, DateTimeZone.UTC);
            dt = dt.plusMonths(month - 1).plusDays(int_args[0] - 1).plusHours(int_args[1]).plusMinutes(int_args[2]).plusSeconds(int_args[3]);
            if (runtime.is1_9() && !args[5].isNil()) {
                final double millis = RubyNumeric.num2dbl(args[5]);
                final int int_millis = (int)(millis * 1000.0) % 1000;
                dt = dt.plusMillis(int_millis);
            }
            dt = dt.withZoneRetainFields(dtz);
            if (isDst != null) {
                long offsetCalculationInstant = dt.getMillis();
                if (dtz.isStandardOffset(dt.getMillis())) {
                    offsetCalculationInstant = dtz.previousTransition(offsetCalculationInstant);
                }
                final int offset = dtz.getStandardOffset(offsetCalculationInstant) - dtz.getOffset(offsetCalculationInstant);
                if (!isDst && !dtz.isStandardOffset(dt.getMillis())) {
                    dt = dt.minusMillis(offset);
                }
                if (isDst && dtz.isStandardOffset(dt.getMillis())) {
                    dt = dt.plusMillis(offset);
                }
            }
        }
        catch (IllegalFieldValueException e) {
            throw runtime.newArgumentError("time out of range");
        }
        final RubyTime time = new RubyTime(runtime, (RubyClass)recv, dt);
        if (args.length != 8 && !args[6].isNil()) {
            int usec = int_args[4] % 1000;
            int msec = int_args[4] / 1000;
            if (int_args[4] < 0) {
                --msec;
                usec += 1000;
            }
            time.dt = dt.withMillis(dt.getMillis() + msec);
            time.setUSec(usec);
        }
        time.callInit(IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
        return time;
    }
    
    static {
        ONE_DAY_CTIME_FORMATTER = DateTimeFormat.forPattern("EEE MMM  d HH:mm:ss yyyy").withLocale(Locale.ENGLISH);
        TWO_DAY_CTIME_FORMATTER = DateTimeFormat.forPattern("EEE MMM dd HH:mm:ss yyyy").withLocale(Locale.ENGLISH);
        TO_S_FORMATTER = DateTimeFormat.forPattern("EEE MMM dd HH:mm:ss Z yyyy").withLocale(Locale.ENGLISH);
        TO_S_UTC_FORMATTER = DateTimeFormat.forPattern("EEE MMM dd HH:mm:ss 'UTC' yyyy").withLocale(Locale.ENGLISH);
        TO_S_FORMATTER_19 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss Z").withLocale(Locale.ENGLISH);
        TO_S_UTC_FORMATTER_19 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss 'UTC'").withLocale(Locale.ENGLISH);
        TZ_PATTERN = Pattern.compile("(\\D+?)([\\+-]?)(\\d+)(:\\d+)?(:\\d+)?");
        TIME_OFFSET_PATTERN = Pattern.compile("([\\+-])(\\d\\d):\\d\\d");
        TZ_STRING = ByteList.create("TZ");
        LONG_TZNAME = new HashMap<String, String>() {
            {
                this.put("MET", "CET");
                this.put("ROC", "Asia/Taipei");
                this.put("WET", "Europe/Lisbon");
            }
        };
        SHORT_STD_TZNAME = new HashMap<String, String>() {
            {
                this.put("Etc/UCT", "UCT");
                this.put("MET", "MET");
                this.put("UCT", "UCT");
            }
        };
        SHORT_DL_TZNAME = new HashMap<String, String>() {
            {
                this.put("Etc/UCT", "UCT");
                this.put("MET", "MEST");
                this.put("UCT", "UCT");
            }
        };
        RubyTime.TIME_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                final DateTimeZone dtz = RubyTime.getLocalTimeZone(runtime);
                final DateTime dt = new DateTime(dtz);
                final RubyTime rt = new RubyTime(runtime, klass, dt);
                rt.setUSec(0L);
                return rt;
            }
        };
        MONTHS = new String[] { "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec" };
        MONTHS_MAP = new HashMap<String, Integer>();
        for (int i = 0; i < RubyTime.MONTHS.length; ++i) {
            RubyTime.MONTHS_MAP.put(RubyTime.MONTHS[i], i + 1);
        }
        time_min = new int[] { 1, 0, 0, 0, Integer.MIN_VALUE };
        time_max = new int[] { 31, 23, 59, 60, Integer.MAX_VALUE };
    }
}
