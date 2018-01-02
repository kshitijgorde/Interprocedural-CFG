// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.Applets;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Vector;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Choice;
import java.util.List;
import java.awt.Panel;
import java.awt.event.ItemListener;
import java.awt.ItemSelectable;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

final class b implements KeyListener
{
    private /* synthetic */ LabeledChoice a;
    
    b(final LabeledChoice a) {
        this.a = a;
    }
    
    public final void keyTyped(final KeyEvent keyEvent) {
        final char keyChar;
        if ((keyChar = keyEvent.getKeyChar()) == '\b') {
            if (this.a._keyBuffer.length() > 0) {
                this.a._keyBuffer = this.a._keyBuffer.substring(0, this.a._keyBuffer.length() - 1);
            }
        }
        else {
            if (keyChar == '\n') {
                this.a(this.a._choice.getSelectedIndex());
                LabeledChoice.a(this.a);
                return;
            }
            this.a._keyBuffer = (this.a._keyBuffer + keyChar).toLowerCase();
        }
        if (!this.a()) {
            this.a._keyBuffer = ("" + keyChar).toLowerCase();
            this.a();
        }
    }
    
    private boolean a() {
        boolean b = false;
        int i = 0;
        while (i < this.a._choice.getItemCount()) {
            if (this.a._choice.getItem(i).toLowerCase().indexOf(this.a._keyBuffer) == 0) {
                b = true;
                this.a._choice.select(i);
                boolean b2 = false;
                for (int j = i + 1; j < this.a._choice.getItemCount(); ++j) {
                    if (this.a._choice.getItem(j).toLowerCase().indexOf(this.a._keyBuffer) == 0) {
                        b2 = true;
                        break;
                    }
                }
                if (!b2) {
                    this.a(i);
                    break;
                }
                break;
            }
            else {
                ++i;
            }
        }
        return b;
    }
    
    private void a(final int n) {
        final ItemEvent itemEvent = new ItemEvent(this.a._choice, n, null, 1);
        final ItemListener[] a = this.a.a();
        for (int i = 0; i < a.length; ++i) {
            a[i].itemStateChanged(itemEvent);
        }
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
    }
    
    public final void keyReleased(final KeyEvent keyEvent) {
    }
}
