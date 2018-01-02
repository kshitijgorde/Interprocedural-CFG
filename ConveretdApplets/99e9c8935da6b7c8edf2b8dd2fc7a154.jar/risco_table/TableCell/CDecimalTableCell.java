// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.TableCell;

import java.math.BigDecimal;

public class CDecimalTableCell implements ITableCell
{
    private BigDecimal m_dValue;
    
    public CDecimalTableCell(final String sValue) {
        try {
            this.m_dValue = new BigDecimal(sValue);
        }
        catch (NumberFormatException e) {
            this.m_dValue = new BigDecimal("0");
        }
    }
    
    public String toString() {
        return this.m_dValue.toString();
    }
    
    public BigDecimal toDecimal() {
        return this.m_dValue;
    }
    
    public int compare(final ITableCell oCell) {
        return this.m_dValue.compareTo(((CDecimalTableCell)oCell).toDecimal());
    }
    
    public int compareMulti(final ITableCell oCell) {
        return this.compare(oCell);
    }
}
