// 
// Decompiled by Procyon v0.5.30
// 

package irc.gui.common;

import irc.EventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.awt.event.FocusEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.TextField;
import irc.ListenerGroup;
import java.util.Vector;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.Container;

public class AWTIrcTextField extends Container implements ActionListener, KeyListener, FocusListener
{
    private int _index;
    private int _tabCount;
    private String _completing;
    private String[] _completeList;
    private Vector _historic;
    private ListenerGroup _listeners;
    private TextField _field;
    private boolean _useEnterTextHere;
    private String _enterTextHere;
    
    public AWTIrcTextField() {
        this._useEnterTextHere = false;
        this.setLayout(new GridLayout(1, 1));
        this.add(this._field = new TextField());
        this._completeList = new String[0];
        this._field.setFont(new Font("Monospaced", 0, 12));
        this._tabCount = 0;
        this._completing = "";
        this._index = 0;
        this._listeners = new ListenerGroup();
        this._historic = new Vector();
        this._field.addActionListener(this);
        this._field.addKeyListener(this);
        try {
            this._field.getClass().getMethod("setFocusTraversalKeysEnabled", Boolean.TYPE).invoke(this._field, new Boolean(false));
        }
        catch (Exception ex) {}
        this.addFocusListener(this);
    }
    
    public void release() {
        this.removeFocusListener(this);
        this._field.removeActionListener(this);
        this._field.removeKeyListener(this);
        this._historic = new Vector();
        this._field = null;
        this.removeAll();
    }
    
    public void setEnterTextHere(final boolean useEnterTextHere, final String enterTextHere) {
        this._useEnterTextHere = useEnterTextHere;
        this._enterTextHere = enterTextHere;
        if (this._useEnterTextHere) {
            this._field.setText(this._enterTextHere);
            this._field.setSelectionStart(0);
            this._field.setSelectionEnd(this._field.getText().length() + 1);
        }
    }
    
    public void setBackground(final Color color) {
        super.setBackground(color);
        this._field.setBackground(color);
    }
    
    public void setForeground(final Color color) {
        super.setForeground(color);
        this._field.setForeground(color);
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        this._field.requestFocus();
    }
    
    public void focusLost(final FocusEvent focusEvent) {
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this._listeners.addListener(actionListener);
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this._listeners.removeListener(actionListener);
    }
    
    public void setCompleteList(final String[] array) {
        this._completeList = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            this._completeList[i] = array[i];
        }
    }
    
    public void setText(final String text) {
        this._field.setText(text);
    }
    
    public String getText() {
        return this._field.getText();
    }
    
    public int getCaretPosition() {
        return this._field.getCaretPosition();
    }
    
    public void setCaretPosition(final int caretPosition) {
        this._field.setCaretPosition(caretPosition);
    }
    
    private void type(final int n) {
        final int selectionStart = this._field.getSelectionStart();
        final int selectionEnd = this._field.getSelectionEnd();
        String s = this._field.getText();
        if (selectionStart != selectionEnd) {
            s = s.substring(0, selectionStart) + s.substring(selectionEnd);
            this._field.setCaretPosition(selectionStart);
        }
        final int caretPosition = this._field.getCaretPosition();
        this._field.setText(s.substring(0, caretPosition) + (char)n + s.substring(caretPosition));
        this._field.setCaretPosition(caretPosition + 1);
    }
    
    private void getCompleting() {
        this._completing = "";
        final String text = this._field.getText();
        if (this._field.getCaretPosition() == text.length() || text.charAt(this._field.getCaretPosition()) == ' ') {
            for (int i = this._field.getCaretPosition() - 1; i >= 0; --i) {
                if (text.charAt(i) == ' ') {
                    break;
                }
                this._completing = text.charAt(i) + this._completing;
            }
        }
    }
    
    private void complete() {
        if (this._completing.length() == 0) {
            return;
        }
        final String lowerCase = this._completing.toLowerCase(Locale.ENGLISH);
        final Vector vector = new Vector<String>();
        for (int i = 0; i < this._completeList.length; ++i) {
            if (this._completeList[i].toLowerCase(Locale.ENGLISH).startsWith(lowerCase)) {
                vector.insertElementAt(this._completeList[i], vector.size());
            }
        }
        if (vector.size() > 0) {
            final String s = vector.elementAt(this._tabCount % vector.size());
            final int caretPosition = this._field.getCaretPosition();
            final String text = this._field.getText();
            final String substring = text.substring(0, caretPosition);
            final String substring2 = text.substring(caretPosition);
            final int lastIndex = substring.lastIndexOf(32);
            String substring3;
            if (lastIndex == -1) {
                substring3 = "";
            }
            else {
                substring3 = substring.substring(0, lastIndex + 1);
            }
            final String string = substring3 + s;
            this._field.setText(string + substring2);
            this._field.setCaretPosition(string.length());
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 9 || keyEvent.getKeyCode() == 34) {
            if (this._tabCount == 0) {
                this.getCompleting();
            }
            this.complete();
            ++this._tabCount;
            keyEvent.consume();
        }
        else {
            this._tabCount = 0;
        }
        if (keyEvent.getKeyCode() == 38) {
            if (this._historic.size() > 0) {
                --this._index;
                if (this._index == -1) {
                    this._index = 0;
                }
                this._field.setText(this._historic.elementAt(this._index));
                this.setCaretPosition(this.getText().length());
            }
            keyEvent.consume();
        }
        else if (keyEvent.getKeyCode() == 40) {
            if (this._historic.size() > 0) {
                ++this._index;
                if (this._index > this._historic.size()) {
                    this._index = this._historic.size();
                }
                if (this._index < this._historic.size()) {
                    this._field.setText(this._historic.elementAt(this._index));
                }
                else {
                    this._field.setText("");
                }
                this.setCaretPosition(this.getText().length());
            }
            keyEvent.consume();
        }
        else if (keyEvent.getKeyCode() == 75 && keyEvent.isControlDown()) {
            this.type(3);
            keyEvent.consume();
        }
        else if (keyEvent.getKeyCode() == 66 && keyEvent.isControlDown()) {
            this.type(2);
            keyEvent.consume();
        }
        else if (keyEvent.getKeyCode() == 85 && keyEvent.isControlDown()) {
            this.type(31);
            keyEvent.consume();
        }
        else if (keyEvent.getKeyCode() == 82 && keyEvent.isControlDown()) {
            this.type(22);
            keyEvent.consume();
        }
        else if (keyEvent.getKeyCode() == 79 && keyEvent.isControlDown()) {
            this.type(15);
            keyEvent.consume();
        }
        else if (keyEvent.getKeyCode() == 87 && keyEvent.isControlDown()) {
            this.type(30);
            keyEvent.consume();
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void validateText() {
        if (this.getText().length() > 0) {
            this._historic.insertElementAt(this.getText(), this._historic.size());
            this._index = this._historic.size();
        }
        this._listeners.sendEvent("actionPerformed", new Object[] { new ActionEvent(this, 0, "validate") });
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        EventDispatcher.dispatchEventAsync(this, "validateText", new Object[0]);
    }
}
