// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.parser;

import java.util.Date;
import java.sql.Time;
import java.text.DateFormat;

public class TimeParser extends DateParser
{
    public TimeParser() {
        this(DateFormat.getTimeInstance(3));
    }
    
    public TimeParser(final DateFormat dateFormat) {
        super(dateFormat);
    }
    
    public Class getType() {
        return Time.class;
    }
    
    public boolean canParse(final String s) {
        try {
            this.parseTime(s);
            return true;
        }
        catch (DataParseException ex) {
            return false;
        }
    }
    
    public Object parse(final String s) throws DataParseException {
        return this.parseTime(s);
    }
    
    public Time parseTime(final String s) throws DataParseException {
        this.m_pos.setErrorIndex(0);
        this.m_pos.setIndex(0);
        Time value;
        try {
            value = Time.valueOf(s);
            this.m_pos.setIndex(s.length());
        }
        catch (IllegalArgumentException ex) {
            value = null;
        }
        if (value == null) {
            final Date parse = this.m_dfmt.parse(s, this.m_pos);
            if (parse != null) {
                value = new Time(parse.getTime());
            }
        }
        if (value == null || this.m_pos.getIndex() < s.length()) {
            throw new DataParseException("Could not parse Date: " + s);
        }
        return value;
    }
}
