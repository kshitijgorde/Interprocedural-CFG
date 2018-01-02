// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicPasswordFieldUI;

public class TinyPasswordFieldUI extends BasicPasswordFieldUI
{
    JComponent editor;
    
    public static ComponentUI createUI(final JComponent component) {
        return new TinyPasswordFieldUI();
    }
    
    public void installUI(final JComponent editor) {
        super.installUI(editor);
        this.editor = editor;
    }
    
    protected void paintBackground(final Graphics graphics) {
        if (this.editor.isEnabled()) {
            graphics.setColor(this.editor.getBackground());
        }
        else {
            graphics.setColor(Theme.textDisabledBgColor[Theme.style].getColor());
        }
        graphics.fillRect(0, 0, this.editor.getWidth(), this.editor.getHeight());
    }
}
