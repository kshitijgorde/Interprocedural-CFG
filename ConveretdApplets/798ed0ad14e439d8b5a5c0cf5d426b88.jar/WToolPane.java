import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

// 
// Decompiled by Procyon v0.5.30
// 

public class WToolPane extends WBackPanel
{
    protected int I;
    public WImage sepImage;
    public WImage glueImage;
    public static final Object ITEM;
    public static final Object SEPARATOR;
    public static final Object GLUE;
    private int nbRows;
    public Insets padding;
    public Insets itemMargin;
    public final WButton itemButtonModel;
    private GridBagConstraints bagConstraints;
    private String title;
    
    public WToolPane(final int nbRows, final int n, final WButton itemButtonModel) {
        this.sepImage = new WImage(8, 8, 0);
        this.glueImage = null;
        this.padding = new Insets(4, 4, 4, 4);
        this.itemMargin = new Insets(2, 2, 2, 2);
        if (nbRows < 1) {
            throw new IllegalArgumentException();
        }
        this.nbRows = nbRows;
        super.setLayout(new GridBagLayout());
        this.bagConstraints = new GridBagConstraints();
        this.bagConstraints.fill = 1;
        this.setBackground(null);
        if (n == 1) {
            this.I = 1;
            this.bagConstraints.gridy = -1;
            this.bagConstraints.gridx = this.nbRows;
        }
        else {
            this.I = 0;
            this.bagConstraints.gridy = this.nbRows;
            this.bagConstraints.gridx = -1;
        }
        if (itemButtonModel == null) {
            this.itemButtonModel = new WButton(null, null);
        }
        else {
            this.itemButtonModel = itemButtonModel;
        }
    }
    
    public final void setLayout(final LayoutManager layoutManager) {
    }
    
    public final Insets getInsets() {
        final Insets insets2;
        final Insets insets = insets2 = super.getInsets();
        insets2.top += this.padding.top;
        final Insets insets3 = insets;
        insets3.bottom += this.padding.bottom;
        final Insets insets4 = insets;
        insets4.left += this.padding.left;
        final Insets insets5 = insets;
        insets5.right += this.padding.right;
        return insets;
    }
    
    public final void addSeparator() {
        final WButton wButton = new WButton(null, null, 0);
        wButton.backImage = this.sepImage;
        wButton.padding = new Insets(1, 1, 1, 1);
        this.add(wButton, WToolPane.SEPARATOR);
    }
    
    public final void addGlue() {
        final WButton wButton = new WButton(null, null, 0);
        wButton.backImage = this.glueImage;
        this.add(wButton, WToolPane.GLUE);
    }
    
    public final void addEmptyItem() {
        final WButton wButton = new WButton(null, null, 0);
        wButton.enabled = false;
        this.add(wButton);
    }
    
    protected final void addImpl(final Component component, Object item, final int n) {
        if (n != -1) {
            throw new IllegalArgumentException();
        }
        if (item == null) {
            item = WToolPane.ITEM;
        }
        this.bagConstraints.insets = this.itemMargin;
        if (item == WToolPane.ITEM) {
            if (this.I == 0) {
                if (this.bagConstraints.gridy < this.nbRows - 1 && this.bagConstraints.gridheight == 1) {
                    final GridBagConstraints bagConstraints = this.bagConstraints;
                    ++bagConstraints.gridy;
                }
                else {
                    this.bagConstraints.gridy = 0;
                    final GridBagConstraints bagConstraints2 = this.bagConstraints;
                    ++bagConstraints2.gridx;
                    this.bagConstraints.gridheight = 1;
                }
                this.bagConstraints.weightx = 0.0;
            }
            else {
                if (this.bagConstraints.gridx < this.nbRows - 1 && this.bagConstraints.gridwidth == 1) {
                    final GridBagConstraints bagConstraints3 = this.bagConstraints;
                    ++bagConstraints3.gridx;
                }
                else {
                    this.bagConstraints.gridx = 0;
                    final GridBagConstraints bagConstraints4 = this.bagConstraints;
                    ++bagConstraints4.gridy;
                    this.bagConstraints.gridwidth = 1;
                }
                this.bagConstraints.weighty = 0.0;
            }
        }
        else if (this.I == 0) {
            this.bagConstraints.gridheight = this.nbRows;
            final GridBagConstraints bagConstraints5 = this.bagConstraints;
            ++bagConstraints5.gridx;
            this.bagConstraints.gridy = 0;
            if (item == WToolPane.SEPARATOR) {
                this.bagConstraints.weightx = 0.0;
            }
            else {
                this.bagConstraints.weightx = 1.0;
                this.bagConstraints.insets = new Insets(0, 0, 0, 0);
            }
        }
        else {
            this.bagConstraints.gridwidth = this.nbRows;
            final GridBagConstraints bagConstraints6 = this.bagConstraints;
            ++bagConstraints6.gridy;
            this.bagConstraints.gridx = 0;
            if (item == WToolPane.SEPARATOR) {
                this.bagConstraints.weighty = 0.0;
            }
            else {
                this.bagConstraints.weighty = 1.0;
                this.bagConstraints.insets = new Insets(0, 0, 0, 0);
            }
        }
        if (item == WToolPane.ITEM) {
            applyModel(this.itemButtonModel, component);
        }
        super.addImpl(component, this.bagConstraints, n);
    }
    
    public static final void applyModel(final WButton wButton, final Component component) {
        if (component instanceof WButton) {
            final WButton wButton2 = (WButton)component;
            wButton2.padding = wButton.padding;
            wButton2.pressDeltaX = wButton.pressDeltaX;
            wButton2.pressDeltaY = wButton.pressDeltaY;
            wButton2.backImage = wButton.backImage;
            wButton2.maskOver = wButton.maskOver;
            wButton2.backPressed = wButton.backPressed;
            wButton2.textSpacing = wButton.textSpacing;
        }
    }
    
    public final void setTitle(final String title) {
        this.title = title;
    }
    
    public final String getTitle() {
        if (this.title == null) {
            return this.getName();
        }
        return this.title;
    }
    
    static {
        ITEM = new Object();
        SEPARATOR = new Object();
        GLUE = new Object();
    }
}
