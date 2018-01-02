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
import javax.swing.AbstractButton;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import java.awt.BasicStroke;
import javax.swing.plaf.metal.MetalCheckBoxUI;

public class TinyCheckBoxUI extends MetalCheckBoxUI
{
    private static final TinyCheckBoxUI checkBoxUI;
    static TinyCheckBoxIcon checkIcon;
    static BasicStroke focusStroke;
    
    public static ComponentUI createUI(final JComponent component) {
        return TinyCheckBoxUI.checkBoxUI;
    }
    
    public void installDefaults(final AbstractButton abstractButton) {
        super.installDefaults(abstractButton);
        this.icon = TinyCheckBoxUI.checkIcon;
        abstractButton.setRolloverEnabled(true);
        if (!Theme.buttonEnter[Theme.style]) {
            return;
        }
        if (!abstractButton.isFocusable()) {
            return;
        }
        final InputMap inputMap = (InputMap)UIManager.get(this.getPropertyPrefix() + "focusInputMap");
        if (inputMap != null) {
            inputMap.put(KeyStroke.getKeyStroke(10, 0, false), "pressed");
            inputMap.put(KeyStroke.getKeyStroke(10, 0, true), "released");
        }
    }
    
    protected void paintFocus(final Graphics graphics, final Rectangle rectangle, final Dimension dimension) {
        if (!Theme.buttonFocus[Theme.style]) {
            return;
        }
        final Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setColor(Color.black);
        graphics2D.setStroke(TinyCheckBoxUI.focusStroke);
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
        checkBoxUI = new TinyCheckBoxUI();
        TinyCheckBoxUI.checkIcon = new TinyCheckBoxIcon();
        TinyCheckBoxUI.focusStroke = new BasicStroke(1.0f, 0, 2, 1.0f, new float[] { 1.0f }, 1.0f);
    }
}
