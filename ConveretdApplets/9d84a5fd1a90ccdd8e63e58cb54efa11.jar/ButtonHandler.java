import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class ButtonHandler extends MouseAdapter
{
    ButtonCtrl m_button;
    boolean m_bAutoRelease;
    
    public ButtonHandler(final ButtonCtrl button, final boolean bAutomaticRelease) {
        this.m_button = button;
        this.m_bAutoRelease = bAutomaticRelease;
    }
    
    public void mousePressed(final MouseEvent e) {
        if (!this.m_button.isPressed()) {
            this.m_button.setPressed(true);
            this.m_button.setCursorType();
        }
        else {
            this.m_button.setPressed(false);
            this.m_button.setCursorToDefault();
        }
        if (this.m_button.getListener() != null) {
            this.m_button.getListener().actionPerformed(new ActionEvent(this.m_button, 1001, this.m_button.getActionCommand()));
        }
    }
    
    public void mouseReleased(final MouseEvent e) {
        if (this.m_bAutoRelease) {
            this.m_button.setPressed(false);
            this.m_button.repaint();
        }
    }
}
