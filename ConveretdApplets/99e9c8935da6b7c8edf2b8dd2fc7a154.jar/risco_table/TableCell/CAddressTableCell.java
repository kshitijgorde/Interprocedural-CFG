// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.TableCell;

import risco_table.util.Util;

public class CAddressTableCell implements ITableCell
{
    private String m_sAddressNumberValue;
    private String m_sAddressDirectionValue;
    private String m_sAddressStreetValue;
    private String m_sDisplayString;
    private String m_sComparisonString;
    
    public CAddressTableCell(final String sAddressNumberValue, final String sAddressDirectionValue, final String sAddressStreetValue) {
        this.m_sAddressNumberValue = sAddressNumberValue;
        this.m_sAddressDirectionValue = sAddressDirectionValue;
        this.m_sAddressStreetValue = sAddressStreetValue;
        final StringBuffer sb = new StringBuffer();
        if (this.m_sAddressNumberValue != null) {
            sb.append(this.m_sAddressNumberValue);
            sb.append(" ");
        }
        if (this.m_sAddressDirectionValue != null) {
            sb.append(this.m_sAddressDirectionValue);
            sb.append(" ");
        }
        sb.append(this.m_sAddressStreetValue);
        this.m_sDisplayString = sb.toString();
        sb.setLength(0);
        sb.append(Util.padNumeric(this.m_sAddressStreetValue.trim(), 8));
        sb.append('^');
        sb.append(this.m_sAddressDirectionValue.trim());
        sb.append('^');
        sb.append(Util.padNumeric(this.m_sAddressNumberValue.trim(), 8));
        this.m_sComparisonString = sb.toString().toLowerCase();
    }
    
    public String toString() {
        return this.m_sDisplayString;
    }
    
    public String toComparisonString() {
        return this.m_sComparisonString;
    }
    
    public int compare(final ITableCell oCell) {
        return this.toComparisonString().compareTo(((CAddressTableCell)oCell).toComparisonString());
    }
    
    public int compareMulti(final ITableCell oCell) {
        return this.compare(oCell);
    }
}
