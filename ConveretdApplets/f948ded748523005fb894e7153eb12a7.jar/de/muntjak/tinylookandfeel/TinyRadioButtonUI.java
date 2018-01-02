// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.awt.Stroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.InputMap;
import javax.swing.JRadioButton;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import java.awt.BasicStroke;
import java.awt.Component;
import javax.swing.plaf.metal.MetalRadioButtonUI;

public class TinyRadioButtonUI extends MetalRadioButtonUI
{
    Component c;
    private static final TinyRadioButtonUI radioButtonUI;
    private static BasicStroke focusStroke;
    private static TinyRadioButtonIcon radioButton;
    
    public static ComponentUI createUI(final JComponent component) {
        if (component instanceof JRadioButton) {
            ((JRadioButton)component).setRolloverEnabled(true);
        }
        return TinyRadioButtonUI.radioButtonUI;
    }
    
    public void installUI(final JComponent component) {
        super.installUI(component);
        this.icon = this.getRadioButton();
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
    
    protected TinyRadioButtonIcon getRadioButton() {
        if (TinyRadioButtonUI.radioButton == null) {
            TinyRadioButtonUI.radioButton = new TinyRadioButtonIcon();
        }
        return TinyRadioButtonUI.radioButton;
    }
    
    protected void paintFocus(final Graphics graphics, final Rectangle rectangle, final Dimension dimension) {
        if (!Theme.buttonFocus[Theme.style]) {
            return;
        }
        final Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setColor(Color.black);
        graphics2D.setStroke(TinyRadioButtonUI.focusStroke);
        final int n = rectangle.x - 1;
        final int n2 = rectangle.y - 1;
        final int n3 = n + rectangle.width + 1;
        final int n4 = n2 + rectangle.height + 1;
        graphics2D.drawLine(n, n2, n3, n2);
        graphics2D.drawLine(n, n2, n, n4);
        graphics2D.drawLine(n, n4, n3, n4);
        graphics2D.drawLine(n3, n2, n3, n4);
    }
    
    static {
        radioButtonUI = new TinyRadioButtonUI();
        TinyRadioButtonUI.focusStroke = new BasicStroke(1.0f, 0, 2, 1.0f, new float[] { 1.0f, 1.0f }, 0.0f);
    }
}
