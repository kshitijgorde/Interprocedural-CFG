// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.image.dialog.resize;

import java.awt.GridBagConstraints;
import java.awt.Component;
import jmaster.util.swing.GUIHelper;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.ComboBoxModel;
import java.util.StringTokenizer;
import javax.swing.DefaultComboBoxModel;
import jmaster.jumploader.model.api.B;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import jmaster.jumploader.view.impl.GenericView;

public class ResizeView extends GenericView
{
    private static final long \u00f5 = 5211891865564246133L;
    public static final String PREFIX = "resizeView";
    protected JLabel \u00f3;
    protected JComboBox \u00f4;
    
    public ResizeView(final B b) {
        super(b);
        this.\u00f3 = new JLabel();
        this.\u00f4 = new JComboBox();
        this.I.injectProperties(this, "resizeView");
        this.I.injectProperties(this.\u00f3, "resizeView", "labelResize");
        this.I.injectProperties(this.\u00f4, "resizeView", "comboResize");
        final DefaultComboBoxModel<ResizeOption> model = new DefaultComboBoxModel<ResizeOption>();
        final StringTokenizer stringTokenizer = new StringTokenizer(b.J().getResizeOptions(), ";");
        while (stringTokenizer.hasMoreTokens()) {
            final ResizeOption resizeOption = new ResizeOption();
            resizeOption.setText(stringTokenizer.nextToken());
            resizeOption.setWidth(Integer.parseInt(stringTokenizer.nextToken()));
            resizeOption.setHeight(Integer.parseInt(stringTokenizer.nextToken()));
            model.addElement(resizeOption);
        }
        this.\u00f4.setModel(model);
        final GridBagConstraints gbc = this.I.newGBC();
        this.setLayout(new GridBagLayout());
        this.add(this.\u00f3, this.A(gbc, 0, 0, 1, 1, 0, 0, 0, 18, GUIHelper.INSETS4));
        this.add(this.\u00f4, this.A(gbc, 1, 0, 1, 1, 0, 0, 2, 18, GUIHelper.INSETS4));
    }
    
    public ResizeOption getResizeOption() {
        return (ResizeOption)this.\u00f4.getModel().getSelectedItem();
    }
}
