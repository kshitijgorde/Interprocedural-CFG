// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.TableCell;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CDateTableCell implements ITableCell
{
    private Date m_dValue;
    private String m_sValue;
    
    public CDateTableCell(final String sValue, final String sFormat) {
        this.m_sValue = sValue;
        try {
            this.m_dValue = new SimpleDateFormat(sFormat).parse(sValue);
        }
        catch (Exception e) {
            this.m_dValue = new Date();
        }
    }
    
    public String toString() {
        return this.m_sValue;
    }
    
    public Date toDate() {
        return this.m_dValue;
    }
    
    public int compare(final ITableCell oCell) {
        if (this.m_dValue.after(((CDateTableCell)oCell).toDate())) {
            return 1;
        }
        if (this.m_dValue.before(((CDateTableCell)oCell).toDate())) {
            return -1;
        }
        return 0;
    }
    
    public int compareMulti(final ITableCell oCell) {
        return this.compare(oCell);
    }
}
