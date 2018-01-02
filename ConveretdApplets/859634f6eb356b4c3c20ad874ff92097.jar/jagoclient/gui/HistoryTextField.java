// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.gui;

import java.awt.event.KeyEvent;
import rene.util.list.ListElement;
import rene.util.list.ListClass;
import java.awt.event.KeyListener;

public class HistoryTextField extends TextFieldAction implements KeyListener
{
    ListClass H;
    ListElement A;
    
    public HistoryTextField(final DoActionListener doActionListener, final String s) {
        super(doActionListener, s);
        (this.H = new ListClass()).append(new ListElement(""));
        this.A = this.H.last();
        this.addKeyListener(this);
    }
    
    public HistoryTextField(final DoActionListener doActionListener, final String s, final int n) {
        super(doActionListener, s, n);
        (this.H = new ListClass()).append(new ListElement(""));
        this.A = this.H.last();
        this.addKeyListener(this);
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 38: {
                if (this.A.previous() != null) {
                    this.A = this.A.previous();
                    break;
                }
                break;
            }
            case 40: {
                if (this.A.next() != null) {
                    this.A = this.A.next();
                    break;
                }
                break;
            }
            default: {
                return;
            }
        }
        this.setText((String)this.A.content());
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void remember(final String s) {
        this.H.last().content(s);
        this.H.append(new ListElement(""));
        this.A = this.H.last();
    }
}
