// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import javax.swing.JPanel;
import java.beans.PropertyChangeListener;

final class Y implements PropertyChangeListener
{
    private final /* synthetic */ JPanel a;
    
    Y(final WordleApplet wordleApplet, final JPanel a) {
        this.a = a;
    }
    
    public final void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        this.a.setBackground((Color)propertyChangeEvent.getNewValue());
    }
}
