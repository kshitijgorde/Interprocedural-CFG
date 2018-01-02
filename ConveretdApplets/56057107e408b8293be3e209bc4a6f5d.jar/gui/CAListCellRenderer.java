// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import java.awt.Color;
import javax.swing.Icon;
import anon.crypto.CertificateInfoStructure;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.JLabel;

public final class CAListCellRenderer extends JLabel implements ListCellRenderer
{
    public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
        final CertificateInfoStructure certificateInfoStructure = (CertificateInfoStructure)o;
        String text = certificateInfoStructure.getCertificate().getSubject().getCommonName();
        if (text == null) {
            text = certificateInfoStructure.getCertificate().getSubject().toString();
        }
        this.setText(text);
        this.setEnabled(list.isEnabled());
        if (b) {
            this.setBackground(list.getSelectionBackground());
            this.setForeground(list.getSelectionForeground());
        }
        else {
            this.setBackground(list.getBackground());
            this.setForeground(list.getForeground());
        }
        if (certificateInfoStructure.isEnabled()) {
            this.setIcon(GUIUtils.loadImageIcon("cenabled.gif", false));
        }
        else {
            this.setForeground(Color.red);
            this.setIcon(GUIUtils.loadImageIcon("cdisabled.gif", false));
        }
        this.setFont(list.getFont());
        this.setOpaque(true);
        return this;
    }
}
