// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.TableCell;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CIntegerTableCell implements ITableCell
{
    private BigInteger m_iValue;
    private BigDecimal m_dCompareValue;
    
    public CIntegerTableCell(String sValue) {
        try {
            while (true) {
                final int iPos = sValue.indexOf(",");
                if (iPos <= 0) {
                    break;
                }
                sValue = String.valueOf(sValue.substring(0, iPos)) + sValue.substring(iPos + 1);
            }
            this.m_iValue = new BigInteger(sValue);
            this.m_dCompareValue = new BigDecimal(this.m_iValue.doubleValue() + Math.random());
        }
        catch (NumberFormatException e) {
            this.m_iValue = new BigInteger("0");
            this.m_dCompareValue = new BigDecimal("0");
        }
    }
    
    public String toString() {
        return this.m_iValue.toString();
    }
    
    public BigInteger toInteger() {
        return this.m_iValue;
    }
    
    public BigDecimal toDecimal() {
        return this.m_dCompareValue;
    }
    
    public int compare(final ITableCell oCell) {
        return this.m_dCompareValue.compareTo(((CIntegerTableCell)oCell).toDecimal());
    }
    
    public int compareMulti(final ITableCell oCell) {
        return this.m_iValue.compareTo(((CIntegerTableCell)oCell).toInteger());
    }
}
