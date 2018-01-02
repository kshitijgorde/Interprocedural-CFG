// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.TextArea;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class va0 implements TextListener
{
    private int a;
    private int b;
    
    public va0(final int a, final int b) {
        this.a = a;
        this.b = b;
    }
    
    public void textValueChanged(final TextEvent textEvent) {
        final Object source = textEvent.getSource();
        if (source instanceof TextArea) {
            final TextArea textArea = (TextArea)source;
            final int length = textArea.getText().length();
            if (length > this.a) {
                textArea.replaceRange("", 0, this.b + (length - this.a));
            }
        }
    }
}
