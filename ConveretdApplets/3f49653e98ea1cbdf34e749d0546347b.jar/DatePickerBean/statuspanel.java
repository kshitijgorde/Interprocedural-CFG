// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.Label;

final class statuspanel extends Label implements MouseListener
{
    toppanel parent;
    int lang;
    
    statuspanel(final toppanel parent) {
        this.parent = parent;
        this.setFont(new Font("SansSerif", 0, 12));
        this.setAlignment(1);
        this.lang = DPLanguage.LANG_ENGLISH;
        this.addMouseListener(this);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.parent.parent.setToday();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        this.refresh();
    }
    
    public void refresh() {
        this.setBackground(this.parent.parent.getBackground());
        this.setText(String.valueOf(DPLanguage.getTodayTranslation(this.lang)) + " : " + this.parent.parent.getTodayFormatted());
    }
}
