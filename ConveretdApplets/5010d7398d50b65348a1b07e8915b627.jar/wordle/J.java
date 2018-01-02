// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.Action;
import javax.swing.JButton;

final class J extends JButton
{
    public J(final Action action, final String s) {
        super(action);
        this.putClientProperty("AATextPropertyKey", true);
        this.setFont(this.getFont().deriveFont(16.0f));
        this.setIcon(new ImageIcon(WordleApplet.class.getResource(s)));
    }
}
