// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import javax.swing.JComponent;
import java.awt.Insets;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.UIResource;
import de.muntjak.tinylookandfeel.controlpanel.DrawRoutines;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import javax.swing.border.Border;
import javax.swing.JTextField;
import javax.swing.plaf.metal.MetalComboBoxEditor;

public class TinyComboBoxEditor extends MetalComboBoxEditor
{
    public TinyComboBoxEditor() {
        (this.editor = new JTextField("", 9) {
            public void setText(final String text) {
                if (this.getText().equals(text)) {
                    return;
                }
                super.setText(text);
            }
        }).setBorder(new EditorBorder());
    }
    
    private void drawTinyBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (!component.isEnabled()) {
            graphics.setColor(Theme.textBorderDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.textBorderColor[Theme.style].getColor());
        }
        graphics.drawRect(n + 1, n2 + 1, n3 - 3, n4 - 3);
        if (!component.isEnabled()) {
            graphics.setColor(Theme.textBorderDarkDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.textBorderDarkColor[Theme.style].getColor());
        }
        graphics.drawLine(n, n2, n + n3 - 2, n2);
        graphics.drawLine(n, n2 + 1, n, n2 + n4 - 2);
        if (!component.isEnabled()) {
            graphics.setColor(Theme.textBorderLightDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.textBorderLightColor[Theme.style].getColor());
        }
        graphics.drawLine(n, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
        graphics.drawLine(n + n3 - 1, n2, n + n3 - 1, n2 + n4 - 2);
    }
    
    private void drawWinBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (!component.isEnabled()) {
            graphics.setColor(Theme.textBorderDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.textBorderColor[Theme.style].getColor());
        }
        graphics.drawLine(n + 1, n2 + 1, n + n3 - 1, n2 + 1);
        graphics.drawLine(n + 1, n2 + 2, n + 1, n2 + n4 - 3);
        if (!component.isEnabled()) {
            graphics.setColor(Theme.textBorderDarkDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.textBorderDarkColor[Theme.style].getColor());
        }
        graphics.drawLine(n, n2, n + n3 - 1, n2);
        graphics.drawLine(n, n2 + 1, n, n2 + n4 - 2);
        graphics.setColor(Theme.backColor[Theme.style].getColor());
        graphics.drawLine(n + 1, n2 + n4 - 2, n + n3 - 1, n2 + n4 - 2);
        if (!component.isEnabled()) {
            graphics.setColor(Theme.textBorderLightDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.textBorderLightColor[Theme.style].getColor());
        }
        graphics.drawLine(n, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
    }
    
    private void drawXpBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.setColor(component.getParent().getParent().getBackground());
        graphics.drawLine(n, n2, n + n3 - 1, n2);
        graphics.drawLine(n, n2, n, n2 + n4 - 1);
        graphics.drawLine(n, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
        if (!component.isEnabled()) {
            DrawRoutines.drawEditableComboBorder(graphics, Theme.comboBorderDisabledColor[Theme.style].getColor(), 0, 0, n3, n4);
        }
        else {
            DrawRoutines.drawEditableComboBorder(graphics, Theme.comboBorderColor[Theme.style].getColor(), 0, 0, n3, n4);
        }
    }
    
    public static class UIResource extends TinyComboBoxEditor implements javax.swing.plaf.UIResource
    {
    }
    
    class EditorBorder extends AbstractBorder
    {
        public Insets getBorderInsets(final Component component) {
            return new Insets(1, Theme.comboInsets[Theme.style].left + 1, 1, 0);
        }
        
        public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
            if (((JComponent)TinyComboBoxEditor.this.editor.getParent()).getBorder() == null) {
                return;
            }
            switch (Theme.derivedStyle[Theme.style]) {
                case 0: {
                    TinyComboBoxEditor.this.drawTinyBorder(component, graphics, n, n2, n3, n4);
                    break;
                }
                case 1: {
                    TinyComboBoxEditor.this.drawWinBorder(component, graphics, n, n2, n3, n4);
                    break;
                }
                case 2: {
                    TinyComboBoxEditor.this.drawXpBorder(component, graphics, n, n2, n3, n4);
                    break;
                }
            }
        }
    }
}
