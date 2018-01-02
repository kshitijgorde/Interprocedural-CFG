// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import javax.swing.Icon;
import javax.swing.ButtonModel;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import de.muntjak.tinylookandfeel.controlpanel.ColorRoutines;
import javax.swing.plaf.ComponentUI;
import java.awt.Stroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import javax.swing.AbstractButton;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.InputMap;
import javax.swing.JComponent;
import java.awt.BasicStroke;
import javax.swing.plaf.metal.MetalButtonUI;

public class TinyButtonUI extends MetalButtonUI
{
    public static final int BG_CHANGE_AMOUNT = 10;
    private static final TinyButtonUI buttonUI;
    private static final BasicStroke focusStroke;
    private boolean graphicsTranslated;
    private boolean isToolBarButton;
    private boolean isFileChooserButton;
    private boolean isDefault;
    
    public void installUI(final JComponent component) {
        super.installUI(component);
        if (!Theme.buttonEnter[Theme.style]) {
            return;
        }
        if (!component.isFocusable()) {
            return;
        }
        final InputMap inputMap = (InputMap)UIManager.get(this.getPropertyPrefix() + "focusInputMap");
        if (inputMap != null) {
            inputMap.put(KeyStroke.getKeyStroke(10, 0, false), "pressed");
            inputMap.put(KeyStroke.getKeyStroke(10, 0, true), "released");
        }
    }
    
    public void installDefaults(final AbstractButton abstractButton) {
        super.installDefaults(abstractButton);
        abstractButton.setRolloverEnabled(true);
    }
    
    protected void paintFocus(final Graphics graphics, final AbstractButton abstractButton, final Rectangle rectangle, final Rectangle rectangle2, final Rectangle rectangle3) {
        if (this.isFileChooserButton || (this.isToolBarButton && !Theme.toolFocus[Theme.style]) || !Theme.buttonFocus[Theme.style]) {
            return;
        }
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final Rectangle bounds = abstractButton.getBounds();
        graphics.setColor(Color.black);
        graphics2D.setStroke(TinyButtonUI.focusStroke);
        int n = 2;
        int n2 = 2;
        int n3 = n + bounds.width - 5;
        int n4 = n2 + bounds.height - 5;
        if (!this.isToolBarButton) {
            ++n;
            ++n2;
            --n3;
            --n4;
        }
        if (this.graphicsTranslated) {
            graphics.translate(-1, -1);
        }
        graphics2D.drawLine(n, n2, n3, n2);
        graphics2D.drawLine(n, n2, n, n4);
        graphics2D.drawLine(n, n4, n3, n4);
        graphics2D.drawLine(n3, n2, n3, n4);
    }
    
    public static ComponentUI createUI(final JComponent component) {
        return TinyButtonUI.buttonUI;
    }
    
    protected void paintButtonPressed(final Graphics graphics, final AbstractButton abstractButton) {
        if (this.isToolBarButton || this.isFileChooserButton) {
            return;
        }
        Color color;
        if (!abstractButton.getBackground().equals(Theme.buttonNormalColor[Theme.style].getColor())) {
            color = ColorRoutines.darken(abstractButton.getBackground(), 10);
        }
        else {
            color = Theme.buttonPressedColor[Theme.style].getColor();
        }
        graphics.setColor(color);
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyButton(graphics, abstractButton, color);
                break;
            }
            case 1: {
                this.drawWinButton(graphics, abstractButton, color);
                break;
            }
            case 2: {
                this.drawXpButton(graphics, abstractButton, color, false);
                break;
            }
        }
        if (!(abstractButton instanceof JToggleButton) && Theme.shiftButtonText[Theme.style] && abstractButton.getText() != null && !"".equals(abstractButton.getText())) {
            graphics.translate(1, 1);
            this.graphicsTranslated = true;
        }
    }
    
    public void paintToolBarButton(final Graphics graphics, final AbstractButton abstractButton) {
        final boolean b = abstractButton.getModel().isRollover() || abstractButton.getModel().isArmed();
        Color color;
        if (this.isFileChooserButton) {
            color = abstractButton.getParent().getBackground();
        }
        else {
            color = Theme.toolButtColor[Theme.style].getColor();
        }
        Color color2;
        if (abstractButton.getModel().isPressed()) {
            if (b) {
                color2 = Theme.toolButtPressedColor[Theme.style].getColor();
            }
            else if (abstractButton.isSelected()) {
                color2 = Theme.toolButtSelectedColor[Theme.style].getColor();
            }
            else {
                color2 = color;
            }
        }
        else if (b && Theme.derivedStyle[Theme.style] == 2) {
            if (abstractButton.isSelected()) {
                color2 = Theme.toolButtSelectedColor[Theme.style].getColor();
            }
            else {
                color2 = Theme.toolButtRolloverColor[Theme.style].getColor();
            }
        }
        else if (abstractButton.isSelected()) {
            color2 = Theme.toolButtSelectedColor[Theme.style].getColor();
        }
        else {
            color2 = color;
        }
        graphics.setColor(color2);
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyToolBarButton(graphics, abstractButton, color2, false);
                break;
            }
            case 1: {
                this.drawWinToolBarButton(graphics, abstractButton, color2, false);
                break;
            }
            case 2: {
                this.drawXpToolBarButton(graphics, abstractButton, color2, false);
                break;
            }
        }
    }
    
    public void paint(final Graphics graphics, final JComponent component) {
        final AbstractButton abstractButton = (AbstractButton)component;
        if (this.isToolBarButton || this.isFileChooserButton) {
            this.paintToolBarButton(graphics, abstractButton);
            super.paint(graphics, component);
            return;
        }
        if (abstractButton instanceof JToggleButton && abstractButton.isSelected()) {
            this.paintButtonPressed(graphics, abstractButton);
            super.paint(graphics, component);
            return;
        }
        this.isDefault = (component instanceof JButton && ((JButton)component).isDefaultButton());
        final boolean b = abstractButton.getModel().isRollover() && Theme.buttonRollover[Theme.derivedStyle[Theme.style]];
        final boolean equals = component.getBackground().equals(Theme.buttonNormalColor[Theme.style].getColor());
        Color color;
        if (!abstractButton.isEnabled()) {
            color = Theme.buttonDisabledColor[Theme.style].getColor();
        }
        else if (abstractButton.getModel().isPressed()) {
            if (b) {
                if (equals) {
                    color = Theme.buttonPressedColor[Theme.style].getColor();
                }
                else {
                    color = ColorRoutines.darken(component.getBackground(), 10);
                }
            }
            else {
                color = component.getBackground();
            }
        }
        else if (b) {
            if (equals) {
                color = Theme.buttonRolloverBgColor[Theme.style].getColor();
            }
            else {
                color = ColorRoutines.lighten(component.getBackground(), 10);
            }
        }
        else {
            color = component.getBackground();
        }
        graphics.setColor(color);
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyButton(graphics, abstractButton, color);
                break;
            }
            case 1: {
                this.drawWinButton(graphics, abstractButton, color);
                break;
            }
            case 2: {
                this.drawXpButton(graphics, abstractButton, color, b);
                break;
            }
        }
        super.paint(graphics, component);
    }
    
    protected void paintIcon(final Graphics graphics, final JComponent component, final Rectangle rectangle) {
        if (component instanceof JToggleButton) {
            this.paintToggleButtonIcon(graphics, component, rectangle);
        }
        else {
            super.paintIcon(graphics, component, rectangle);
        }
    }
    
    protected void paintToggleButtonIcon(final Graphics graphics, final JComponent component, final Rectangle rectangle) {
        final AbstractButton abstractButton = (AbstractButton)component;
        final ButtonModel model = abstractButton.getModel();
        Icon icon = null;
        if (!model.isEnabled()) {
            if (model.isSelected()) {
                icon = abstractButton.getDisabledSelectedIcon();
            }
            else {
                icon = abstractButton.getDisabledIcon();
            }
        }
        else if (model.isPressed() && model.isArmed()) {
            icon = abstractButton.getPressedIcon();
            if (icon == null) {
                icon = abstractButton.getSelectedIcon();
            }
        }
        else if (model.isSelected()) {
            if (abstractButton.isRolloverEnabled() && model.isRollover()) {
                icon = abstractButton.getRolloverSelectedIcon();
                if (icon == null) {
                    icon = abstractButton.getSelectedIcon();
                }
            }
            else {
                icon = abstractButton.getSelectedIcon();
            }
        }
        else if (model.isRollover() && Theme.derivedStyle[Theme.style] == 2) {
            icon = abstractButton.getRolloverIcon();
        }
        if (icon == null) {
            icon = abstractButton.getIcon();
        }
        icon.paintIcon(abstractButton, graphics, rectangle.x, rectangle.y);
    }
    
    public void update(final Graphics graphics, final JComponent component) {
        this.isToolBarButton = Boolean.TRUE.equals(component.getClientProperty("JToolBar.isToolbarButton"));
        this.isFileChooserButton = Boolean.TRUE.equals(component.getClientProperty("JFileChooser.isFileChooserButton"));
        this.paint(graphics, component);
        this.graphicsTranslated = false;
    }
    
    private void drawTinyButton(final Graphics graphics, final AbstractButton abstractButton, final Color color) {
    }
    
    private void drawWinButton(final Graphics graphics, final AbstractButton abstractButton, final Color color) {
        final int width = abstractButton.getWidth();
        final int height = abstractButton.getHeight();
        if (abstractButton.isContentAreaFilled() && abstractButton.isOpaque()) {
            graphics.fillRect(1, 1, width - 2, height - 2);
        }
    }
    
    private void drawXpButton(final Graphics graphics, final AbstractButton abstractButton, final Color color, final boolean b) {
        if (!abstractButton.isContentAreaFilled()) {
            return;
        }
        if (!abstractButton.isOpaque()) {
            return;
        }
        final int width = abstractButton.getWidth();
        final int height = abstractButton.getHeight();
        graphics.setColor(abstractButton.getParent().getBackground());
        graphics.drawRect(0, 0, width - 1, height - 1);
        int n = Theme.buttonSpreadLight[Theme.style];
        int n2 = Theme.buttonSpreadDark[Theme.style];
        if (!abstractButton.isEnabled()) {
            n = Theme.buttonSpreadLightDisabled[Theme.style];
            n2 = Theme.buttonSpreadDarkDisabled[Theme.style];
        }
        final float n3 = 10.0f * n / (height - 3);
        final float n4 = 10.0f * n2 / (height - 3);
        final int n5 = height / 2;
        for (int i = 1; i < height - 1; ++i) {
            if (i < n5) {
                graphics.setColor(ColorRoutines.lighten(color, (int)((n5 - i) * n3)));
            }
            else if (i == n5) {
                graphics.setColor(color);
            }
            else {
                graphics.setColor(ColorRoutines.darken(color, (int)((i - n5) * n4)));
            }
            graphics.drawLine(2, i, width - 3, i);
            if (i == 1) {
                graphics.drawLine(1, 1, 1, height - 2);
                if (b || this.isDefault) {
                    graphics.drawLine(width - 2, 1, width - 2, height - 2);
                }
            }
            else if (i == height - 2 && !b && !this.isDefault) {
                graphics.drawLine(width - 2, 1, width - 2, height - 2);
            }
        }
        if (b) {
            graphics.setColor(Theme.buttonRolloverColor[Theme.style].getColor());
            graphics.drawLine(1, height - 2, 1, height - 2);
            graphics.drawLine(width - 2, height - 2, width - 2, height - 2);
        }
        else if (this.isDefault) {
            graphics.setColor(Theme.buttonDefaultColor[Theme.style].getColor());
            graphics.drawLine(1, height - 2, 1, height - 2);
            graphics.drawLine(width - 2, height - 2, width - 2, height - 2);
        }
    }
    
    private void drawTinyToolBarButton(final Graphics graphics, final AbstractButton abstractButton, final Color color, final boolean b) {
    }
    
    private void drawWinToolBarButton(final Graphics graphics, final AbstractButton abstractButton, final Color color, final boolean b) {
        final int width = abstractButton.getWidth();
        final int height = abstractButton.getHeight();
        if (abstractButton.isContentAreaFilled()) {
            graphics.fillRect(1, 1, width - 2, height - 2);
        }
    }
    
    private void drawXpToolBarButton(final Graphics graphics, final AbstractButton abstractButton, final Color color, final boolean b) {
        final int width = abstractButton.getWidth();
        final int height = abstractButton.getHeight();
        if (abstractButton.isContentAreaFilled()) {
            graphics.fillRect(1, 1, width - 2, height - 2);
        }
        graphics.setColor(abstractButton.getParent().getBackground());
        graphics.drawRect(0, 0, width - 1, height - 1);
    }
    
    static {
        buttonUI = new TinyButtonUI();
        focusStroke = new BasicStroke(1.0f, 0, 2, 1.0f, new float[] { 1.0f, 1.0f }, 0.0f);
    }
}
