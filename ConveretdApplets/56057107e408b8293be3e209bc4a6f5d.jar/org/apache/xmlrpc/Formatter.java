// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xmlrpc;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

class Formatter
{
    private DateFormat f;
    
    public Formatter() {
        this.f = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ss");
    }
    
    public synchronized String format(final Date date) {
        return this.f.format(date);
    }
    
    public synchronized Date parse(final String s) throws ParseException {
        return this.f.parse(s);
    }
}
