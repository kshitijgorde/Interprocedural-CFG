// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.accessibility.AccessibleRole;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.LabelUI;
import javax.accessibility.AccessibleContext;
import java.awt.Component;
import javax.accessibility.Accessible;

public class JLabel extends JComponent implements SwingConstants, Accessible
{
    private static final String uiClassID = "LabelUI";
    private int mnemonic;
    private String text;
    private Icon defaultIcon;
    private Icon disabledIcon;
    private boolean disabledIconSet;
    private int verticalAlignment;
    private int horizontalAlignment;
    private int verticalTextPosition;
    private int horizontalTextPosition;
    private int iconTextGap;
    protected Component labelFor;
    static final String LABELED_BY_PROPERTY = "labeledBy";
    
    public JLabel() {
        this("", null, 10);
    }
    
    public JLabel(final String s) {
        this(s, null, 10);
    }
    
    public JLabel(final String s, final int n) {
        this(s, null, n);
    }
    
    public JLabel(final String text, final Icon icon, final int horizontalAlignment) {
        this.mnemonic = 0;
        this.text = "";
        this.defaultIcon = null;
        this.disabledIcon = null;
        this.disabledIconSet = false;
        this.verticalAlignment = 0;
        this.horizontalAlignment = 10;
        this.verticalTextPosition = 0;
        this.horizontalTextPosition = 11;
        this.iconTextGap = 4;
        this.labelFor = null;
        this.setText(text);
        this.setIcon(icon);
        this.setHorizontalAlignment(horizontalAlignment);
        this.updateUI();
        this.setAlignmentX(0.0f);
    }
    
    public JLabel(final Icon icon) {
        this(null, icon, 0);
    }
    
    public JLabel(final Icon icon, final int n) {
        this(null, icon, n);
    }
    
    protected int checkHorizontalKey(final int n, final String s) {
        if (n == 2 || n == 0 || n == 4 || n == 10 || n == 11) {
            return n;
        }
        throw new IllegalArgumentException(s);
    }
    
    protected int checkVerticalKey(final int n, final String s) {
        if (n == 1 || n == 0 || n == 3) {
            return n;
        }
        throw new IllegalArgumentException(s);
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJLabel();
        }
        return super.accessibleContext;
    }
    
    public Icon getDisabledIcon() {
        if (!this.disabledIconSet && this.disabledIcon == null && this.defaultIcon != null && this.defaultIcon instanceof ImageIcon) {
            this.firePropertyChange("disabledIcon", null, this.disabledIcon = new ImageIcon(GrayFilter.createDisabledImage(((ImageIcon)this.defaultIcon).getImage())));
        }
        return this.disabledIcon;
    }
    
    public int getDisplayedMnemonic() {
        return this.mnemonic;
    }
    
    public int getHorizontalAlignment() {
        return this.horizontalAlignment;
    }
    
    public int getHorizontalTextPosition() {
        return this.horizontalTextPosition;
    }
    
    public Icon getIcon() {
        return this.defaultIcon;
    }
    
    public int getIconTextGap() {
        return this.iconTextGap;
    }
    
    public Component getLabelFor() {
        return this.labelFor;
    }
    
    public String getText() {
        return this.text;
    }
    
    public LabelUI getUI() {
        return (LabelUI)super.ui;
    }
    
    public String getUIClassID() {
        return "LabelUI";
    }
    
    public int getVerticalAlignment() {
        return this.verticalAlignment;
    }
    
    public int getVerticalTextPosition() {
        return this.verticalTextPosition;
    }
    
    protected String paramString() {
        final String s = (this.text != null) ? this.text : "";
        final String s2 = (this.defaultIcon != null) ? this.defaultIcon.toString() : "";
        final String s3 = (this.disabledIcon != null) ? this.disabledIcon.toString() : "";
        final String s4 = (this.labelFor != null) ? this.labelFor.toString() : "";
        String s5;
        if (this.verticalAlignment == 1) {
            s5 = "TOP";
        }
        else if (this.verticalAlignment == 0) {
            s5 = "CENTER";
        }
        else if (this.verticalAlignment == 3) {
            s5 = "BOTTOM";
        }
        else {
            s5 = "";
        }
        String s6;
        if (this.horizontalAlignment == 2) {
            s6 = "LEFT";
        }
        else if (this.horizontalAlignment == 0) {
            s6 = "CENTER";
        }
        else if (this.horizontalAlignment == 4) {
            s6 = "RIGHT";
        }
        else {
            s6 = "";
        }
        String s7;
        if (this.verticalTextPosition == 1) {
            s7 = "TOP";
        }
        else if (this.verticalTextPosition == 0) {
            s7 = "CENTER";
        }
        else if (this.verticalTextPosition == 3) {
            s7 = "BOTTOM";
        }
        else {
            s7 = "";
        }
        String s8;
        if (this.horizontalTextPosition == 2) {
            s8 = "LEFT";
        }
        else if (this.horizontalTextPosition == 0) {
            s8 = "CENTER";
        }
        else if (this.horizontalTextPosition == 4) {
            s8 = "RIGHT";
        }
        else {
            s8 = "";
        }
        return String.valueOf(super.paramString()) + ",defaultIcon=" + s2 + ",disabledIcon=" + s3 + ",horizontalAlignment=" + s6 + ",horizontalTextPosition=" + s8 + ",iconTextGap=" + this.iconTextGap + ",labelFor=" + s4 + ",text=" + s + ",verticalAlignment=" + s5 + ",verticalTextPosition=" + s7;
    }
    
    public void setDisabledIcon(final Icon disabledIcon) {
        final Icon disabledIcon2 = this.disabledIcon;
        this.disabledIcon = disabledIcon;
        this.disabledIconSet = true;
        this.firePropertyChange("disabledIcon", disabledIcon2, disabledIcon);
        if (disabledIcon != disabledIcon2) {
            if (disabledIcon == null || disabledIcon2 == null || disabledIcon.getIconWidth() != disabledIcon2.getIconWidth() || disabledIcon.getIconHeight() != disabledIcon2.getIconHeight()) {
                this.revalidate();
            }
            if (!this.isEnabled()) {
                this.repaint();
            }
        }
    }
    
    public void setDisplayedMnemonic(final char c) {
        int displayedMnemonic = c;
        if (displayedMnemonic >= 97 && displayedMnemonic <= 122) {
            displayedMnemonic -= 32;
        }
        this.setDisplayedMnemonic(displayedMnemonic);
    }
    
    public void setDisplayedMnemonic(final int mnemonic) {
        final int mnemonic2 = this.mnemonic;
        this.firePropertyChange("displayedMnemonic", mnemonic2, this.mnemonic = mnemonic);
        if (mnemonic != mnemonic2) {
            this.revalidate();
            this.repaint();
        }
    }
    
    public void setHorizontalAlignment(final int n) {
        if (n == this.horizontalAlignment) {
            return;
        }
        this.firePropertyChange("horizontalAlignment", this.horizontalAlignment, this.horizontalAlignment = this.checkHorizontalKey(n, "horizontalAlignment"));
        this.repaint();
    }
    
    public void setHorizontalTextPosition(final int n) {
        this.firePropertyChange("horizontalTextPosition", this.horizontalTextPosition, this.horizontalTextPosition = this.checkHorizontalKey(n, "horizontalTextPosition"));
        this.repaint();
    }
    
    public void setIcon(final Icon defaultIcon) {
        final Icon defaultIcon2 = this.defaultIcon;
        this.defaultIcon = defaultIcon;
        if (this.defaultIcon != defaultIcon2 && !this.disabledIconSet) {
            this.disabledIcon = null;
        }
        this.firePropertyChange("icon", defaultIcon2, this.defaultIcon);
        if (super.accessibleContext != null && defaultIcon2 != this.defaultIcon) {
            super.accessibleContext.firePropertyChange("AccessibleVisibleData", defaultIcon2, this.defaultIcon);
        }
        if (this.defaultIcon != defaultIcon2) {
            if (this.defaultIcon == null || defaultIcon2 == null || this.defaultIcon.getIconWidth() != defaultIcon2.getIconWidth() || this.defaultIcon.getIconHeight() != defaultIcon2.getIconHeight()) {
                this.revalidate();
            }
            this.repaint();
        }
    }
    
    public void setIconTextGap(final int iconTextGap) {
        final int iconTextGap2 = this.iconTextGap;
        this.firePropertyChange("iconTextGap", iconTextGap2, this.iconTextGap = iconTextGap);
        if (iconTextGap != iconTextGap2) {
            this.revalidate();
            this.repaint();
        }
    }
    
    public void setLabelFor(final Component labelFor) {
        final Component labelFor2 = this.labelFor;
        this.firePropertyChange("labelFor", labelFor2, this.labelFor = labelFor);
        if (labelFor2 instanceof JComponent) {
            ((JComponent)labelFor2).putClientProperty("labeledBy", null);
        }
        if (labelFor instanceof JComponent) {
            ((JComponent)labelFor).putClientProperty("labeledBy", this);
        }
    }
    
    public void setText(final String text) {
        String accessibleName = null;
        if (super.accessibleContext != null) {
            accessibleName = super.accessibleContext.getAccessibleName();
        }
        final String text2 = this.text;
        this.firePropertyChange("text", text2, this.text = text);
        if (super.accessibleContext != null && super.accessibleContext.getAccessibleName() != accessibleName) {
            super.accessibleContext.firePropertyChange("AccessibleVisibleData", accessibleName, super.accessibleContext.getAccessibleName());
        }
        if (text == null || text2 == null || !text.equals(text2)) {
            this.revalidate();
            this.repaint();
        }
    }
    
    public void setUI(final LabelUI ui) {
        super.setUI(ui);
    }
    
    public void setVerticalAlignment(final int n) {
        if (n == this.verticalAlignment) {
            return;
        }
        this.firePropertyChange("verticalAlignment", this.verticalAlignment, this.verticalAlignment = this.checkVerticalKey(n, "verticalAlignment"));
        this.repaint();
    }
    
    public void setVerticalTextPosition(final int n) {
        if (n == this.verticalTextPosition) {
            return;
        }
        this.firePropertyChange("verticalTextPosition", this.verticalTextPosition, this.verticalTextPosition = this.checkVerticalKey(n, "verticalTextPosition"));
        this.repaint();
    }
    
    public void updateUI() {
        this.setUI((LabelUI)UIManager.getUI(this));
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (super.ui != null && this.getUIClassID().equals("LabelUI")) {
            super.ui.installUI(this);
        }
    }
    
    protected class AccessibleJLabel extends AccessibleJComponent
    {
        public String getAccessibleName() {
            if (super.accessibleName != null) {
                return super.accessibleName;
            }
            if (JLabel.this.getText() == null) {
                return super.getAccessibleName();
            }
            return JLabel.this.getText();
        }
        
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.LABEL;
        }
    }
}
