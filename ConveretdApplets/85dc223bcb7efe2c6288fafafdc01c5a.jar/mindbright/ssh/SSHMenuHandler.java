// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.awt.PopupMenu;
import java.awt.MenuBar;
import mindbright.terminal.TerminalWin;
import java.awt.Frame;
import mindbright.application.MindTerm;
import mindbright.terminal.TerminalMenuListener;

public class SSHMenuHandler implements TerminalMenuListener
{
    boolean havePopupMenu;
    
    public SSHMenuHandler() {
        this.havePopupMenu = false;
    }
    
    public void init(final MindTerm mindterm, final SSHInteractiveClient client, final Frame parent, final TerminalWin term) {
    }
    
    public void update() {
    }
    
    public void setPopupButton(final int popButtonNum) {
    }
    
    public void prepareMenuBar(final MenuBar mb) {
    }
    
    public void preparePopupMenu(final PopupMenu popupmenu) {
    }
    
    public int getPopupButton() {
        return 0;
    }
    
    public boolean confirmDialog(final String message, final boolean defAnswer) {
        return false;
    }
    
    public void alertDialog(final String message) {
    }
    
    public void textDialog(final String head, final String text, final int rows, final int cols, final boolean scrollbar) {
    }
}
