// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.Component;
import java.awt.TextArea;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

public class vaz extends ComponentAdapter
{
    public void componentResized(final ComponentEvent componentEvent) {
        final Component component = componentEvent.getComponent();
        if (component instanceof TextArea) {
            final TextArea textArea = (TextArea)component;
            textArea.setCaretPosition(textArea.getText().length());
        }
    }
}
