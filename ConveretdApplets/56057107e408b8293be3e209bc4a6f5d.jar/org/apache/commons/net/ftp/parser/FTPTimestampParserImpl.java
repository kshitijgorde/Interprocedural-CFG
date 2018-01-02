// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net.ftp.parser;

import java.text.DateFormatSymbols;
import org.apache.commons.net.ftp.FTPClientConfig;
import java.util.TimeZone;
import java.util.Date;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import org.apache.commons.net.ftp.Configurable;

public class FTPTimestampParserImpl implements FTPTimestampParser, Configurable
{
    private SimpleDateFormat defaultDateFormat;
    private SimpleDateFormat recentDateFormat;
    
    public FTPTimestampParserImpl() {
        this.setDefaultDateFormat("MMM d yyyy");
        this.setRecentDateFormat("MMM d HH:mm");
    }
    
    public Calendar parseTimestamp(final String timestampStr) throws ParseException {
        final Calendar now = Calendar.getInstance();
        now.setTimeZone(this.getServerTimeZone());
        final Calendar working = Calendar.getInstance();
        working.setTimeZone(this.getServerTimeZone());
        ParsePosition pp = new ParsePosition(0);
        Date parsed = null;
        if (this.recentDateFormat != null) {
            parsed = this.recentDateFormat.parse(timestampStr, pp);
        }
        if (parsed != null && pp.getIndex() == timestampStr.length()) {
            working.setTime(parsed);
            working.set(1, now.get(1));
            if (working.after(now)) {
                working.add(1, -1);
            }
        }
        else {
            pp = new ParsePosition(0);
            parsed = this.defaultDateFormat.parse(timestampStr, pp);
            if (parsed == null || pp.getIndex() != timestampStr.length()) {
                throw new ParseException("Timestamp could not be parsed with older or recent DateFormat", pp.getIndex());
            }
            working.setTime(parsed);
        }
        return working;
    }
    
    public SimpleDateFormat getDefaultDateFormat() {
        return this.defaultDateFormat;
    }
    
    public String getDefaultDateFormatString() {
        return this.defaultDateFormat.toPattern();
    }
    
    private void setDefaultDateFormat(final String format) {
        if (format != null) {
            (this.defaultDateFormat = new SimpleDateFormat(format)).setLenient(false);
        }
    }
    
    public SimpleDateFormat getRecentDateFormat() {
        return this.recentDateFormat;
    }
    
    public String getRecentDateFormatString() {
        return this.recentDateFormat.toPattern();
    }
    
    private void setRecentDateFormat(final String format) {
        if (format != null) {
            (this.recentDateFormat = new SimpleDateFormat(format)).setLenient(false);
        }
    }
    
    public String[] getShortMonths() {
        return this.defaultDateFormat.getDateFormatSymbols().getShortMonths();
    }
    
    public TimeZone getServerTimeZone() {
        return this.defaultDateFormat.getTimeZone();
    }
    
    private void setServerTimeZone(final String serverTimeZoneId) {
        TimeZone serverTimeZone = TimeZone.getDefault();
        if (serverTimeZoneId != null) {
            serverTimeZone = TimeZone.getTimeZone(serverTimeZoneId);
        }
        this.defaultDateFormat.setTimeZone(serverTimeZone);
        if (this.recentDateFormat != null) {
            this.recentDateFormat.setTimeZone(serverTimeZone);
        }
    }
    
    public void configure(final FTPClientConfig config) {
        DateFormatSymbols dfs = null;
        final String languageCode = config.getServerLanguageCode();
        final String shortmonths = config.getShortMonthNames();
        if (shortmonths != null) {
            dfs = FTPClientConfig.getDateFormatSymbols(shortmonths);
        }
        else if (languageCode != null) {
            dfs = FTPClientConfig.lookupDateFormatSymbols(languageCode);
        }
        else {
            dfs = FTPClientConfig.lookupDateFormatSymbols("en");
        }
        final String recentFormatString = config.getRecentDateFormatStr();
        if (recentFormatString == null) {
            this.recentDateFormat = null;
        }
        else {
            (this.recentDateFormat = new SimpleDateFormat(recentFormatString, dfs)).setLenient(false);
        }
        final String defaultFormatString = config.getDefaultDateFormatStr();
        if (defaultFormatString == null) {
            throw new IllegalArgumentException("defaultFormatString cannot be null");
        }
        (this.defaultDateFormat = new SimpleDateFormat(defaultFormatString, dfs)).setLenient(false);
        this.setServerTimeZone(config.getServerTimeZoneId());
    }
}
