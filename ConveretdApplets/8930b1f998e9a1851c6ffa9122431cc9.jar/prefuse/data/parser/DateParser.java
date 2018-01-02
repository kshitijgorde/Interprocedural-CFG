// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.parser;

import java.sql.Date;
import java.text.ParsePosition;
import java.text.DateFormat;

public class DateParser implements DataParser
{
    protected DateFormat m_dfmt;
    protected ParsePosition m_pos;
    
    public DateParser() {
        this(DateFormat.getDateInstance(3));
    }
    
    public DateParser(final DateFormat dfmt) {
        this.m_dfmt = dfmt;
        this.m_pos = new ParsePosition(0);
    }
    
    public Class getType() {
        return Date.class;
    }
    
    public String format(final Object o) {
        return (o == null) ? null : this.m_dfmt.format(o);
    }
    
    public boolean canParse(final String s) {
        try {
            this.parseDate(s);
            return true;
        }
        catch (DataParseException ex) {
            return false;
        }
    }
    
    public Object parse(final String s) throws DataParseException {
        return this.parseDate(s);
    }
    
    public Date parseDate(final String s) throws DataParseException {
        this.m_pos.setErrorIndex(0);
        this.m_pos.setIndex(0);
        Date value;
        try {
            value = Date.valueOf(s);
            this.m_pos.setIndex(s.length());
        }
        catch (IllegalArgumentException ex) {
            value = null;
        }
        if (value == null) {
            final java.util.Date parse = this.m_dfmt.parse(s, this.m_pos);
            if (parse != null) {
                value = new Date(parse.getTime());
            }
        }
        if (value == null || this.m_pos.getIndex() < s.length()) {
            throw new DataParseException("Could not parse Date: " + s);
        }
        return value;
    }
}
