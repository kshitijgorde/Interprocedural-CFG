// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import jap.JAPUtil;
import java.sql.Timestamp;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TimestampCellRenderer extends DefaultTableCellRenderer
{
    private boolean m_bWithTime;
    
    public TimestampCellRenderer(final boolean bWithTime) {
        this.m_bWithTime = bWithTime;
        this.setHorizontalAlignment(4);
    }
    
    public Component getTableCellRendererComponent(final JTable table, final Object o, final boolean b, final boolean b2, final int n, final int n2) {
        if (b) {
            super.setForeground(table.getSelectionForeground());
            super.setBackground(table.getSelectionBackground());
        }
        else {
            super.setForeground(table.getForeground());
            super.setBackground(table.getBackground());
        }
        if (!(o instanceof Timestamp)) {
            this.setText("Error - not a Timestamp!");
            return this;
        }
        this.setFont(table.getFont());
        this.setText(JAPUtil.formatTimestamp((Timestamp)o, this.m_bWithTime));
        return this;
    }
}
