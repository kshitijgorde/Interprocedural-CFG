// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import de.muntjak.tinylookandfeel.controlpanel.ColorRoutines;
import javax.swing.plaf.ColorUIResource;
import java.awt.Shape;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import java.awt.TexturePaint;
import java.awt.Graphics2D;
import java.awt.Container;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.UIManager;
import javax.swing.ButtonModel;
import javax.swing.DefaultButtonModel;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.CellRendererPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class TinyComboBoxButton extends JButton
{
    protected JComboBox comboBox;
    protected JList listBox;
    protected CellRendererPane rendererPane;
    protected Icon comboIcon;
    protected boolean iconOnly;
    private static BufferedImage focusImg;
    
    public final JComboBox getComboBox() {
        return this.comboBox;
    }
    
    public final void setComboBox(final JComboBox comboBox) {
        this.comboBox = comboBox;
    }
    
    public final Icon getComboIcon() {
        return this.comboIcon;
    }
    
    public final void setComboIcon(final Icon comboIcon) {
        this.comboIcon = comboIcon;
    }
    
    public final boolean isIconOnly() {
        return this.iconOnly;
    }
    
    public final void setIconOnly(final boolean iconOnly) {
        this.iconOnly = iconOnly;
    }
    
    TinyComboBoxButton() {
        super("");
        this.iconOnly = false;
        this.setModel(new DefaultButtonModel() {
            public void setArmed(final boolean b) {
                super.setArmed(this.isPressed() || b);
            }
        });
        this.setBackground(UIManager.getColor("ComboBox.background"));
        this.setForeground(UIManager.getColor("ComboBox.foreground"));
        if (TinyComboBoxButton.focusImg == null) {
            final ImageIcon loadIcon = TinyLookAndFeel.loadIcon("ComboBoxFocus.png", this);
            if (loadIcon != null) {
                TinyComboBoxButton.focusImg = new BufferedImage(2, 2, 1);
                loadIcon.paintIcon(this, TinyComboBoxButton.focusImg.getGraphics(), 0, 0);
            }
        }
    }
    
    public TinyComboBoxButton(final JComboBox comboBox, final Icon comboIcon, final boolean b, final CellRendererPane rendererPane, final JList listBox) {
        this();
        this.comboBox = comboBox;
        this.comboIcon = comboIcon;
        this.rendererPane = rendererPane;
        this.listBox = listBox;
        this.setEnabled(this.comboBox.isEnabled());
    }
    
    public void paintComponent(final Graphics graphics) {
        final boolean leftToRight = this.getComponentOrientation().isLeftToRight();
        if (this.comboBox.isEnabled()) {
            if (this.comboBox.isEditable()) {
                graphics.setColor(Theme.textBgColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(this.comboBox.getBackground());
            }
        }
        else {
            graphics.setColor(Theme.textDisabledBgColor[Theme.style].getColor());
        }
        graphics.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
        graphics.setColor(this.getParent().getParent().getBackground());
        graphics.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
        ColorUIResource color;
        if (!this.isEnabled()) {
            color = Theme.comboButtDisabledColor[Theme.style].getColor();
        }
        else if (this.model.isPressed()) {
            color = Theme.comboButtPressedColor[Theme.style].getColor();
        }
        else if (this.model.isRollover()) {
            color = Theme.comboButtRolloverColor[Theme.style].getColor();
        }
        else {
            color = Theme.comboButtColor[Theme.style].getColor();
        }
        graphics.setColor(color);
        final Rectangle rectangle = new Rectangle(this.getWidth() - Theme.comboButtonWidth[Theme.derivedStyle[Theme.style]], 1, Theme.comboButtonWidth[Theme.derivedStyle[Theme.style]], this.getHeight() - 2);
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyButton(graphics, rectangle);
                break;
            }
            case 1: {
                this.drawWinButton(graphics, rectangle);
                break;
            }
            case 2: {
                this.drawXpButton(graphics, rectangle, color);
                break;
            }
        }
        if (this.isEnabled()) {
            graphics.setColor(Theme.comboArrowColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.comboArrowDisabledColor[Theme.style].getColor());
        }
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyArrow(graphics, rectangle);
                break;
            }
            case 1: {
                this.drawWinArrow(graphics, rectangle);
                break;
            }
            case 2: {
                this.drawXpArrow(graphics, rectangle);
                break;
            }
        }
        final Insets insets = new Insets(Theme.comboInsets[Theme.style].top, Theme.comboInsets[Theme.style].left, Theme.comboInsets[Theme.style].bottom, 0);
        final int n = this.getWidth() - (insets.left + insets.right);
        final int n2 = this.getHeight() - (insets.top + insets.bottom);
        if (n2 <= 0 || n <= 0) {
            return;
        }
        final int left = insets.left;
        final int top = insets.top;
        final int n3 = left + (n - 1);
        final int n4 = Theme.comboButtonWidth[Theme.derivedStyle[Theme.style]];
        final int n5 = leftToRight ? n3 : left;
        Component listCellRendererComponent = null;
        boolean b = false;
        boolean opaque = false;
        boolean b2 = false;
        if (!this.iconOnly && this.comboBox != null) {
            listCellRendererComponent = this.comboBox.getRenderer().getListCellRendererComponent(this.listBox, this.comboBox.getSelectedItem(), -1, this.getModel().isPressed(), false);
            listCellRendererComponent.setFont(this.rendererPane.getFont());
            if (this.model.isArmed() && this.model.isPressed()) {
                if (this.isOpaque()) {
                    listCellRendererComponent.setBackground(UIManager.getColor("Button.select"));
                }
                listCellRendererComponent.setForeground(this.comboBox.getForeground());
            }
            else if (!this.comboBox.isEnabled()) {
                if (this.isOpaque()) {
                    listCellRendererComponent.setBackground(Theme.textDisabledBgColor[Theme.style].getColor());
                }
                else {
                    this.comboBox.setBackground(Theme.textDisabledBgColor[Theme.style].getColor());
                }
                listCellRendererComponent.setForeground(UIManager.getColor("ComboBox.disabledForeground"));
            }
            else if (this.comboBox.hasFocus() && !this.comboBox.isPopupVisible()) {
                if (this.comboBox.isEditable()) {
                    listCellRendererComponent.setForeground(Theme.mainColor[Theme.style].getColor());
                }
                else {
                    listCellRendererComponent.setForeground(UIManager.getColor("ComboBox.selectionForeground"));
                }
                listCellRendererComponent.setBackground(UIManager.getColor("ComboBox.focusBackground"));
                if (listCellRendererComponent instanceof JComponent) {
                    b = true;
                    final JComponent component = (JComponent)listCellRendererComponent;
                    opaque = component.isOpaque();
                    component.setOpaque(true);
                    b2 = true;
                }
            }
            else {
                listCellRendererComponent.setForeground(this.comboBox.getForeground());
                listCellRendererComponent.setBackground(this.comboBox.getBackground());
            }
            final int n6 = n - (insets.right + n4);
            boolean b3 = false;
            if (listCellRendererComponent instanceof JPanel) {
                b3 = true;
            }
            if (leftToRight) {
                this.rendererPane.paintComponent(graphics, listCellRendererComponent, this, left, top, n6, n2, b3);
            }
            else {
                this.rendererPane.paintComponent(graphics, listCellRendererComponent, this, left + n4, top, n6, n2, b3);
            }
            if (b2 && Theme.derivedStyle[Theme.style] == 2 && Theme.comboFocus[Theme.style]) {
                graphics.setColor(Color.black);
                final Graphics2D graphics2D = (Graphics2D)graphics;
                graphics2D.setPaint(new TexturePaint(TinyComboBoxButton.focusImg, new Rectangle(left, top, 2, 2)));
                graphics2D.draw(new Rectangle(left, top, n6, n2));
            }
        }
        if (b) {
            ((JComponent)listCellRendererComponent).setOpaque(opaque);
        }
    }
    
    private void drawTinyButton(final Graphics graphics, final Rectangle rectangle) {
    }
    
    private void drawWinButton(final Graphics graphics, final Rectangle rectangle) {
        final int n = rectangle.x + rectangle.width - 1;
        final int n2 = rectangle.y + rectangle.height - 1;
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width - 2, rectangle.height - 2);
        if (this.model.isPressed()) {
            graphics.setColor(Theme.comboButtDarkColor[Theme.style].getColor());
            graphics.drawRect(rectangle.x, rectangle.y + 1, rectangle.width - 2, rectangle.height - 3);
        }
        else {
            if (!this.isEnabled()) {
                graphics.setColor(Theme.comboButtLightDisabledColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(Theme.comboButtLightColor[Theme.style].getColor());
            }
            graphics.drawLine(rectangle.x + 1, rectangle.y + 2, n - 3, rectangle.y + 2);
            graphics.drawLine(rectangle.x + 1, rectangle.y + 2, rectangle.x + 1, n2 - 3);
            if (!this.isEnabled()) {
                graphics.setColor(Theme.comboButtDarkDisabledColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(Theme.comboButtDarkColor[Theme.style].getColor());
            }
            graphics.drawLine(n - 2, rectangle.y + 2, n - 2, n2 - 3);
            graphics.drawLine(rectangle.x + 1, n2 - 2, n - 2, n2 - 2);
            if (!this.isEnabled()) {
                graphics.setColor(Theme.comboButtBorderDisabledColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(Theme.comboButtBorderColor[Theme.style].getColor());
            }
            graphics.drawLine(n - 1, rectangle.y + 1, n - 1, n2 - 2);
            graphics.drawLine(rectangle.x, n2 - 1, n - 1, n2 - 1);
        }
    }
    
    private void drawXpButton(final Graphics graphics, final Rectangle rectangle, final Color color) {
        final int n = rectangle.x + rectangle.width;
        final int n2 = rectangle.y + rectangle.height;
        int n3 = Theme.comboSpreadLight[Theme.style];
        int n4 = Theme.comboSpreadDark[Theme.style];
        if (!this.isEnabled()) {
            n3 = Theme.comboSpreadLightDisabled[Theme.style];
            n4 = Theme.comboSpreadDarkDisabled[Theme.style];
        }
        final int n5 = rectangle.height - 2;
        final float n6 = 10.0f * n3 / (n5 - 3);
        final float n7 = 10.0f * n4 / (n5 - 3);
        final int n8 = n5 / 2;
        for (int i = 1; i < n5 - 1; ++i) {
            if (i < n8) {
                graphics.setColor(ColorRoutines.lighten(color, (int)((n8 - i) * n6)));
            }
            else if (i == n8) {
                graphics.setColor(color);
            }
            else {
                graphics.setColor(ColorRoutines.darken(color, (int)((i - n8) * n7)));
            }
            graphics.drawLine(rectangle.x + 1, rectangle.y + i + 1, rectangle.x + rectangle.width - 3, rectangle.y + i + 1);
        }
        ColorUIResource color2;
        if (!this.isEnabled()) {
            color2 = Theme.comboButtBorderDisabledColor[Theme.style].getColor();
        }
        else {
            color2 = Theme.comboButtBorderColor[Theme.style].getColor();
        }
        graphics.setColor(color2);
        graphics.drawLine(rectangle.x + 2, rectangle.y + 1, n - 4, rectangle.y + 1);
        graphics.drawLine(rectangle.x + 1, rectangle.y + 2, rectangle.x + 1, n2 - 3);
        graphics.drawLine(n - 3, rectangle.y + 2, n - 3, n2 - 3);
        graphics.drawLine(rectangle.x + 2, n2 - 2, n - 4, n2 - 2);
        graphics.setColor(new Color(color2.getRed(), color2.getGreen(), color2.getBlue(), 128));
        graphics.drawLine(rectangle.x + 1, rectangle.y + 1, rectangle.x + 1, rectangle.y + 1);
        graphics.drawLine(n - 3, rectangle.y + 1, n - 3, rectangle.y + 1);
        graphics.drawLine(rectangle.x + 1, n2 - 2, rectangle.x + 1, n2 - 2);
        graphics.drawLine(n - 3, n2 - 2, n - 3, n2 - 2);
    }
    
    private void drawTinyArrow(final Graphics graphics, final Rectangle rectangle) {
    }
    
    private void drawWinArrow(final Graphics graphics, final Rectangle rectangle) {
        int n = rectangle.x + (rectangle.width - 6) / 2 - 2;
        int n2 = rectangle.y + (rectangle.height - 4) / 2;
        if (this.model.isPressed()) {
            ++n;
            ++n2;
        }
        graphics.drawLine(n, n2, n + 6, n2);
        graphics.drawLine(n + 1, n2 + 1, n + 5, n2 + 1);
        graphics.drawLine(n + 2, n2 + 2, n + 4, n2 + 2);
        graphics.drawLine(n + 3, n2 + 3, n + 3, n2 + 3);
        if (!this.isEnabled()) {
            graphics.setColor(ColorRoutines.lighten(Theme.comboArrowDisabledColor[Theme.style].getColor(), 60));
            graphics.drawLine(n + 4, n2 + 4, n + 4, n2 + 4);
            graphics.drawLine(n + 4, n2 + 3, n + 5, n2 + 3);
            graphics.drawLine(n + 5, n2 + 2, n + 6, n2 + 2);
            graphics.drawLine(n + 6, n2 + 1, n + 7, n2 + 1);
        }
    }
    
    private void drawXpArrow(final Graphics graphics, final Rectangle rectangle) {
        final int n = rectangle.x + (rectangle.width - 8) / 2 - 1;
        final int n2 = rectangle.y + (rectangle.height - 6) / 2 + 1;
        graphics.drawLine(n + 1, n2, n + 1, n2);
        graphics.drawLine(n + 7, n2, n + 7, n2);
        graphics.drawLine(n, n2 + 1, n + 2, n2 + 1);
        graphics.drawLine(n + 6, n2 + 1, n + 8, n2 + 1);
        graphics.drawLine(n + 1, n2 + 2, n + 3, n2 + 2);
        graphics.drawLine(n + 5, n2 + 2, n + 7, n2 + 2);
        graphics.drawLine(n + 2, n2 + 3, n + 6, n2 + 3);
        graphics.drawLine(n + 3, n2 + 4, n + 5, n2 + 4);
        graphics.drawLine(n + 4, n2 + 5, n + 4, n2 + 5);
    }
}
