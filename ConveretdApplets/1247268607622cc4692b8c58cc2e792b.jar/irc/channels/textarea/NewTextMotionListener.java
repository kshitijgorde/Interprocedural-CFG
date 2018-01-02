// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.textarea;

import java.awt.Cursor;
import irc.com.nick.NickInfos;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import java.awt.event.MouseEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.JTextPane;
import javax.swing.event.MouseInputAdapter;

public class NewTextMotionListener extends MouseInputAdapter
{
    private JTextPane textzone;
    private Element elem;
    private AttributeSet as;
    private HyperlinkTextAreaEvent hyperlinkReceiver;
    
    public NewTextMotionListener(final HyperlinkTextAreaEvent hyperlinkReceiver, final JTextPane textzone) {
        this.hyperlinkReceiver = null;
        this.textzone = textzone;
        this.hyperlinkReceiver = hyperlinkReceiver;
    }
    
    @Override
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.hyperlinkReceiver.autoAway();
        try {
            final int viewToModel = this.textzone.viewToModel(mouseEvent.getPoint());
            this.elem = this.textzone.getStyledDocument().getCharacterElement(viewToModel);
            this.as = this.elem.getAttributes();
            int n = viewToModel;
            if (StyleConstants.getBidiLevel(this.as) == 20) {
                while (StyleConstants.getBidiLevel(this.as) == 20) {
                    --n;
                    this.as = this.textzone.getStyledDocument().getCharacterElement(n).getAttributes();
                }
                String substring = null;
                try {
                    substring = this.textzone.getDocument().getText(0, this.textzone.getDocument().getLength()).substring(n, this.textzone.getDocument().getText(0, this.textzone.getDocument().getLength()).indexOf(" ", n + 1));
                }
                catch (BadLocationException ex) {}
                if (substring == null) {
                    return;
                }
                final String trim = substring.replaceAll(">", "").trim();
                String s = " F ";
                if (NickInfos.getSex(trim) == 1) {
                    s = " H ";
                }
                if (NickInfos.getAge(trim) != 0) {
                    this.hyperlinkReceiver.popupInfos(trim, s, mouseEvent);
                }
                this.textzone.setCursor(new Cursor(12));
            }
            else if (StyleConstants.getBidiLevel(this.as) == 21) {
                this.textzone.setCursor(new Cursor(12));
            }
            else {
                this.hyperlinkReceiver.hidePopupInfos();
                this.textzone.setCursor(new Cursor(0));
            }
        }
        catch (Exception ex2) {}
    }
}
