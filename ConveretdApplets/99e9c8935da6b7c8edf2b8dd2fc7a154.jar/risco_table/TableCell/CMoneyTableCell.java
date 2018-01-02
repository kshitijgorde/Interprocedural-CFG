// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.TableCell;

import java.math.BigDecimal;

public class CMoneyTableCell implements ITableCell
{
    private BigDecimal m_dValue;
    private String m_sValue;
    
    public CMoneyTableCell(final String sValue) {
        this.m_sValue = sValue;
        try {
            this.m_dValue = new BigDecimal(this.unformatDollar(sValue));
        }
        catch (NumberFormatException e) {
            this.m_dValue = new BigDecimal("0");
        }
    }
    
    public String toString() {
        return this.m_sValue;
    }
    
    public BigDecimal toDecimal() {
        return this.m_dValue;
    }
    
    public int compare(final ITableCell oCell) {
        return this.m_dValue.compareTo(((CMoneyTableCell)oCell).toDecimal());
    }
    
    public int compareMulti(final ITableCell oCell) {
        return this.compare(oCell);
    }
    
    private String unformatDollar(String sVal) {
        sVal = sVal.trim();
        if (sVal.startsWith("$") || sVal.startsWith("Private") || sVal.indexOf(",") > 0) {
            if (sVal.startsWith("$")) {
                sVal = sVal.substring(1);
            }
            else if (sVal.startsWith("Private")) {
                sVal = sVal.substring(7);
            }
            while (true) {
                final int iPos = sVal.indexOf(",");
                if (iPos <= 0) {
                    break;
                }
                sVal = String.valueOf(sVal.substring(0, iPos)) + sVal.substring(iPos + 1);
            }
        }
        return sVal;
    }
}
