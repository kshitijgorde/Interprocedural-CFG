// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.TableCell;

public class CStringTableCell implements ITableCell
{
    private String m_sValue;
    private String m_sCompareValue;
    
    public CStringTableCell(final String sValue) {
        this.m_sValue = sValue;
        this.m_sCompareValue = String.valueOf(this.m_sValue.toLowerCase()) + '\u0001' + Math.random();
    }
    
    public String toString() {
        return this.m_sValue;
    }
    
    public String toCompareString() {
        return this.m_sCompareValue;
    }
    
    public int compare(final ITableCell oCell) {
        return this.m_sCompareValue.compareTo(((CStringTableCell)oCell).toCompareString());
    }
    
    public int compareMulti(final ITableCell oCell) {
        return this.m_sValue.compareTo(((CStringTableCell)oCell).toString());
    }
}
