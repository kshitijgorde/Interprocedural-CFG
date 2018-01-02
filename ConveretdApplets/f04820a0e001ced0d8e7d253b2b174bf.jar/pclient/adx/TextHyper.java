// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Font;
import pclient.adv.AppletSpice;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

public class TextHyper extends JLabel implements MouseListener
{
    private AppletSpice parentApplet;
    private String displayText;
    private String urlText;
    private boolean isNewFont;
    private int fontSize;
    
    public TextHyper() {
        this.isNewFont = false;
        this.fontSize = -1;
        this.displayText = null;
        this.urlText = null;
        this.setHorizontalAlignment(0);
        this.addMouseListener(this);
    }
    
    public void setApplet(final AppletSpice parentApplet) {
        this.parentApplet = parentApplet;
    }
    
    public void setFontSize(final int fontSize) {
        if (fontSize > 0) {
            this.fontSize = fontSize;
            this.changeFont();
        }
    }
    
    public void setHyper(final String displayText, final String urlText) {
        this.displayText = displayText;
        this.urlText = urlText;
        if (this.displayText != null) {
            this.setText(this.displayText);
        }
    }
    
    private void changeFont() {
        final Font font = this.getFont();
        if (font != null && !this.isNewFont) {
            int n = font.getSize();
            if (this.fontSize > 0) {
                n = this.fontSize;
            }
            final Font font2 = new Font(font.getName(), 1, n);
            if (font2 != null) {
                this.setFont(font2);
            }
            this.isNewFont = true;
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.urlText != null) {
            if (this.parentApplet != null) {
                this.parentApplet.paraConf.loadPage(this.urlText);
            }
            else {
                System.out.println("#ERROR 5642.");
            }
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.urlText != null) {
            this.setCursor(new Cursor(12));
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(new Cursor(0));
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
}
