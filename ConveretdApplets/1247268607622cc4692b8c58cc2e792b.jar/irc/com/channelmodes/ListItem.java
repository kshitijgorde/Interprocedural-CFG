// 
// Decompiled by Procyon v0.5.30
// 

package irc.com.channelmodes;

import java.util.Date;

public class ListItem
{
    private String mask;
    private String op;
    private Date date;
    
    public ListItem(final String mask, final String op, final Date date) {
        this.mask = mask;
        this.op = op;
        this.date = date;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public String getMask() {
        return this.mask;
    }
    
    public String getOp() {
        return this.op;
    }
}
