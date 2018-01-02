// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.textarea;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import java.awt.event.MouseEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;

public class NewTextClickListener extends MouseAdapter
{
    private JTextPane textzone;
    private Element elem;
    private AttributeSet as;
    private HyperlinkTextAreaEvent hyperlinkReceiver;
    
    public NewTextClickListener(final HyperlinkTextAreaEvent hyperlinkReceiver, final JTextPane textzone) {
        this.hyperlinkReceiver = null;
        this.textzone = textzone;
        this.hyperlinkReceiver = hyperlinkReceiver;
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == 3) {
            this.hyperlinkReceiver.popupCopyPaste(mouseEvent);
            return;
        }
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
                final String replaceAll = this.textzone.getStyledDocument().getText(0, this.textzone.getStyledDocument().getLength()).substring(n, this.textzone.getStyledDocument().getText(0, this.textzone.getStyledDocument().getLength()).indexOf(" ", n + 1)).replaceAll(">", "");
                if (mouseEvent.getClickCount() == 2) {
                    this.hyperlinkReceiver.openPrivate(replaceAll.trim());
                }
                this.hyperlinkReceiver.handleHyperlink(replaceAll.trim());
            }
            else if (StyleConstants.getBidiLevel(this.as) == 21) {
                while (StyleConstants.getBidiLevel(this.as) == 21) {
                    --n;
                    this.as = this.textzone.getStyledDocument().getCharacterElement(n).getAttributes();
                }
                this.hyperlinkReceiver.handleHyperlink(this.textzone.getStyledDocument().getText(0, this.textzone.getStyledDocument().getLength()).substring(n, this.textzone.getStyledDocument().getText(0, this.textzone.getStyledDocument().getLength()).indexOf(" ", n + 1)).trim());
            }
            else if (mouseEvent.getClickCount() == 2) {
                this.hyperlinkReceiver.doubleClick();
            }
            else {
                this.hyperlinkReceiver.Click();
            }
        }
        catch (BadLocationException ex) {}
        catch (StringIndexOutOfBoundsException ex2) {}
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.hyperlinkReceiver.MouseReleased(mouseEvent);
    }
}
