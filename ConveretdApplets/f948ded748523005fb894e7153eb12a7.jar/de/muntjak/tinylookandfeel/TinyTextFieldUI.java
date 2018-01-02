// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import javax.swing.text.JTextComponent;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import javax.swing.plaf.metal.MetalTextFieldUI;

public class TinyTextFieldUI extends MetalTextFieldUI
{
    public static ComponentUI createUI(final JComponent component) {
        return new TinyTextFieldUI();
    }
    
    protected void paintBackground(final Graphics graphics) {
        final JTextComponent component = this.getComponent();
        if (component.isEnabled()) {
            if (component.isEditable()) {
                graphics.setColor(component.getBackground());
            }
            else if (component.getBackground().equals(Theme.textBgColor[Theme.style].getColor())) {
                graphics.setColor(Theme.backColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(component.getBackground());
            }
            graphics.fillRect(0, 0, component.getWidth(), component.getHeight());
        }
        else {
            if (component.getBackground().equals(Theme.textBgColor[Theme.style].getColor())) {
                graphics.setColor(Theme.textDisabledBgColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(component.getBackground());
            }
            graphics.fillRect(0, 0, component.getWidth(), component.getHeight());
            if (Theme.style != 2) {
                return;
            }
            graphics.setColor(Theme.backColor[Theme.style].getColor());
            graphics.drawRect(1, 1, component.getWidth() - 3, component.getHeight() - 3);
            graphics.drawRect(2, 2, component.getWidth() - 5, component.getHeight() - 5);
        }
    }
}
