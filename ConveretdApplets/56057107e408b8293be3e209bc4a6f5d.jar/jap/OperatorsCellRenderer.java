// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import javax.swing.Icon;
import gui.GUIUtils;
import java.awt.Color;
import anon.infoservice.ServiceOperator;
import javax.swing.table.DefaultTableCellRenderer;

public class OperatorsCellRenderer extends DefaultTableCellRenderer
{
    private static final long serialVersionUID = 1L;
    
    public void setValue(final Object o) {
        if (o == null) {
            this.setText("");
            return;
        }
        if (o instanceof ServiceOperator) {
            final ServiceOperator serviceOperator = (ServiceOperator)o;
            this.setForeground(Color.black);
            if (serviceOperator.getCertificate() == null) {
                this.setForeground(Color.gray);
            }
            this.setText(serviceOperator.getOrganization());
            this.setIcon(GUIUtils.loadImageIcon("flags/" + serviceOperator.getCountryCode() + ".png"));
        }
    }
}
