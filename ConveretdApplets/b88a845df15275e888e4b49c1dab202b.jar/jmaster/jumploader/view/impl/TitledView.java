// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl;

import jmaster.util.B.A;
import javax.swing.Icon;
import java.awt.GridBagConstraints;
import java.awt.Component;
import jmaster.util.swing.GUIHelper;
import java.awt.LayoutManager;
import jmaster.jumploader.model.api.B;
import javax.swing.JPanel;
import jmaster.util.swing.view.message.InfoView;
import jmaster.util.swing.view.message.WarningView;
import jmaster.util.swing.view.message.ErrorView;
import jmaster.util.swing.label.TitleLabel;

public abstract class TitledView extends GenericView
{
    private static final long \u0115 = 2158036668180719755L;
    private static final String \u0112 = "titledView";
    protected TitleLabel \u0114;
    protected ErrorView \u0110;
    protected WarningView \u0111;
    protected InfoView \u010f;
    protected JPanel \u0113;
    
    public TitledView(final B b) {
        super(b);
        this.\u0114 = new TitleLabel();
        this.\u0110 = new ErrorView();
        this.\u0111 = new WarningView();
        this.\u010f = new InfoView();
        this.\u0113 = new JPanel();
        this.A(this, "titledView", null);
        this.A(this.\u0114, "titledView", "titleLabel");
        this.H();
        this.setLayout(this.I.newGBL());
        final GridBagConstraints gbc = this.I.newGBC();
        int n = 0;
        this.add(this.\u0114, this.A(gbc, 0, n++, 1, 1, 1, 0, 2, 18, GUIHelper.INSETS0));
        this.add(this.\u0110, this.A(gbc, 0, n++, 1, 1, 1, 0, 2, 18, GUIHelper.INSETS2));
        this.add(this.\u0111, this.A(gbc, 0, n++, 1, 1, 1, 0, 2, 18, GUIHelper.INSETS2));
        this.add(this.\u010f, this.A(gbc, 0, n++, 1, 1, 1, 0, 2, 18, GUIHelper.INSETS2));
        this.add(this.\u0113, this.A(gbc, 0, n++, 1, 1, 1, 1, 1, 18, GUIHelper.INSETS0));
        this.\u0113.setLayout(this.I.newGBL());
    }
    
    public JPanel getContentPanel() {
        return this.\u0113;
    }
    
    public ErrorView getErrorView() {
        return this.\u0110;
    }
    
    public InfoView getInfoView() {
        return this.\u010f;
    }
    
    public TitleLabel getTitleLabel() {
        return this.\u0114;
    }
    
    public WarningView getWarningView() {
        return this.\u0111;
    }
    
    public void setText(final String text) {
        this.\u0114.setText(text);
        this.H();
    }
    
    public void setIcon(final Icon icon) {
        this.\u0114.setIcon(icon);
        this.H();
    }
    
    public String getToolTipText() {
        return this.\u0114.getToolTipText();
    }
    
    public void setToolTipText(final String toolTipText) {
        this.\u0114.setToolTipText(toolTipText);
    }
    
    protected void B(final Exception error) {
        this.E.E(error, error);
        this.\u0110.setError(error);
    }
    
    protected void H() {
        this.\u0114.setVisible(this.\u0114.getIcon() != null || !A.C(this.\u0114.getText()));
    }
}
