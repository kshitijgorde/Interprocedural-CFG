// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.components;

import irc.channels.ChannelWindow;
import javax.swing.text.BadLocationException;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import irc.EIRC;
import irc.com.utils.MyVector;
import java.awt.Frame;
import irc.com.interfaces.Task;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class TextFieldHistory extends JTextField implements ActionListener, KeyListener, Task
{
    private static Frame frame;
    private static int history_max_size;
    private MyVector history;
    private int pos;
    private EIRC eirc;
    private String _infoBulleName;
    private boolean _mouse_over;
    
    public TextFieldHistory(final EIRC eirc) {
        this("", 0, eirc);
    }
    
    public TextFieldHistory(final Frame frame, final EIRC eirc) {
        this(0, eirc);
        TextFieldHistory.frame = frame;
    }
    
    public TextFieldHistory(final int n, final EIRC eirc) {
        this("", n, eirc);
    }
    
    public TextFieldHistory(final String s, final EIRC eirc) {
        this(s, 0, eirc);
    }
    
    public TextFieldHistory(final String s, final int n, final EIRC eirc) {
        super(s, n);
        this._infoBulleName = null;
        this._mouse_over = false;
        this.eirc = eirc;
        (this.history = new MyVector(1)).addElement("");
        this.pos = 0;
        this.setFocusTraversalKeysEnabled(false);
        this.addActionListener(this);
        this.addKeyListener(this);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        this.eirc.revenir();
        if (actionEvent.getActionCommand().length() == 0) {
            return;
        }
        this.pos = 0;
        this.history.insertElementAt(actionEvent.getActionCommand(), 1);
        if (this.history.size() > TextFieldHistory.history_max_size) {
            this.history.setSize(TextFieldHistory.history_max_size);
        }
    }
    
    public int getMaximumHistorySize() {
        return TextFieldHistory.history_max_size;
    }
    
    public void insert(final String s) {
        final int selectionStart = this.getSelectionStart();
        final int selectionEnd = this.getSelectionEnd();
        final String text = this.getText();
        if (selectionStart == selectionEnd) {
            final int caretPosition = this.getCaretPosition();
            this.setText(text.substring(0, caretPosition).concat(s).concat(text.substring(caretPosition)));
            this.setCaretPosition(caretPosition + s.length());
        }
        else {
            this.setText(text.substring(0, selectionStart).concat(s).concat(text.substring(selectionStart, selectionEnd)).concat(s).concat(text.substring(selectionEnd)));
            this.setSelectionStart(selectionStart);
            this.setSelectionEnd(selectionEnd + s.length() * 2);
        }
    }
    
    @Override
    public void keyPressed(final KeyEvent keyEvent) {
        this.eirc.revenir();
        if (!this.isEditable()) {
            return;
        }
        if (keyEvent.getModifiers() == 2) {
            switch (keyEvent.getKeyCode()) {
                case 86: {}
                case 79: {
                    this.insert(String.valueOf('\u000f'));
                    break;
                }
                case 66: {
                    this.insert(String.valueOf('\u0002'));
                    break;
                }
                case 73: {
                    this.insert(String.valueOf('\u0014'));
                    break;
                }
                case 82: {
                    this.insert(String.valueOf('\u0016'));
                    break;
                }
                case 71: {
                    this.insert(String.valueOf('\u0007'));
                    break;
                }
            }
        }
        else {
            switch (keyEvent.getKeyCode()) {
                case 38: {
                    if (this.pos == 0) {
                        this.history.setElementAt(this.getText(), 0);
                    }
                    this.loadItem(this.pos = (this.pos + 1) % this.history.size());
                    break;
                }
                case 40: {
                    if (this.pos == 0) {
                        this.history.setElementAt(this.getText(), 0);
                        this.pos = this.history.size();
                    }
                    this.loadItem(--this.pos);
                    break;
                }
                case 27: {
                    this.loadItem(this.pos = 0);
                    break;
                }
                default: {
                    this.pos = 0;
                    break;
                }
            }
        }
    }
    
    @Override
    public void keyReleased(final KeyEvent keyEvent) {
        this.eirc.revenir();
        if (keyEvent.getKeyChar() == '\t') {
            if (this.eirc.getChat_panel().getCurrentname().startsWith("#")) {
                try {
                    if (this.getText(this.getCaretPosition() - 3, 3).equalsIgnoreCase("$me")) {
                        this.select(this.getCaretPosition() - 3, this.getCaretPosition());
                        this.replaceSelection(this.eirc.getNick());
                        return;
                    }
                }
                catch (BadLocationException ex) {}
                ((ChannelWindow)this.eirc.getChat_panel().getCurrent()).completeNick();
                keyEvent.consume();
            }
            else {
                try {
                    if (this.getText(this.getCaretPosition() - 3, 3).equalsIgnoreCase("$me")) {
                        this.select(this.getCaretPosition() - 3, this.getCaretPosition());
                        this.replaceSelection(this.eirc.getNick());
                    }
                }
                catch (BadLocationException ex2) {}
            }
        }
        else if (this.eirc.getChat_panel().getCurrentname().startsWith("#")) {
            ((ChannelWindow)this.eirc.getChat_panel().getCurrent()).initcomplete_current();
        }
    }
    
    @Override
    public void keyTyped(final KeyEvent keyEvent) {
        this.eirc.revenir();
    }
    
    private void loadItem(final int n) {
        final String text = (String)this.history.elementAt(n);
        this.setText(text);
        this.setCaretPosition(text.length());
    }
    
    public void setInfoBulle(final String infoBulleName) {
        this._infoBulleName = infoBulleName;
    }
    
    public void setMaximumHistorySize(final int history_max_size) {
        TextFieldHistory.history_max_size = history_max_size;
        this.history.setSize(TextFieldHistory.history_max_size);
    }
    
    @Override
    public void setToolTipText(final String toolTipText) {
        super.setToolTipText(toolTipText);
    }
    
    @Override
    public void transferFocus() {
    }
    
    @Override
    public void updateInfo(final Object o, final Object o2) {
    }
    
    static {
        TextFieldHistory.history_max_size = 40;
    }
}
