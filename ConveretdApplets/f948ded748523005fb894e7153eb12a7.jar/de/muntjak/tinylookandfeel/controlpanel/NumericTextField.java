// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.controlpanel;

import javax.swing.text.BadLocationException;
import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import java.awt.event.KeyAdapter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.util.Iterator;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.util.Vector;
import javax.swing.JTextField;

public class NumericTextField extends JTextField
{
    private Vector listeners;
    private ActionEvent actionEvent;
    private int min;
    private int max;
    private int columns;
    private boolean resistUpdate;
    
    public NumericTextField(final int columns, final int n, final int min, final int max) {
        super(columns);
        this.columns = columns;
        this.min = min;
        this.max = max;
        this.setHorizontalAlignment(4);
        this.setText("" + n);
        this.addKeyListener(new ArrowKeyAction(this, min, max));
        this.actionEvent = new ActionEvent(this, 1001, "");
    }
    
    public int getValue() {
        if (this.getText().length() == 0) {
            return 0;
        }
        return Integer.parseInt(this.getText());
    }
    
    public void setValue(final int n) {
        if (this.resistUpdate) {
            return;
        }
        this.setText(String.valueOf(n));
    }
    
    public void addActionListener(final ActionListener actionListener) {
        if (this.listeners == null) {
            this.listeners = new Vector();
        }
        if (this.listeners.contains(actionListener)) {
            return;
        }
        this.listeners.add(actionListener);
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        if (this.listeners == null) {
            return;
        }
        if (!this.listeners.contains(actionListener)) {
            return;
        }
        this.listeners.remove(actionListener);
    }
    
    public void notifyActionListeners() {
        if (this.listeners == null) {
            return;
        }
        this.resistUpdate = true;
        final Iterator<ActionListener> iterator = this.listeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().actionPerformed(this.actionEvent);
        }
        this.resistUpdate = false;
    }
    
    protected Document createDefaultModel() {
        return new NumericDocument();
    }
    
    class KeyInputListener implements DocumentListener
    {
        public void changedUpdate(final DocumentEvent documentEvent) {
        }
        
        public void insertUpdate(final DocumentEvent documentEvent) {
            NumericTextField.this.notifyActionListeners();
        }
        
        public void removeUpdate(final DocumentEvent documentEvent) {
            NumericTextField.this.notifyActionListeners();
        }
    }
    
    class ArrowKeyAction extends KeyAdapter implements ActionListener
    {
        private JTextField theField;
        private Timer keyTimer;
        private int step;
        
        ArrowKeyAction(final JTextField theField, final int n, final int n2) {
            this.theField = theField;
            this.keyTimer = new Timer(20, this);
        }
        
        public void keyPressed(final KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == 38) {
                this.step = 1;
                if (keyEvent.getModifiers() == 1) {
                    this.step = 10;
                }
                this.changeVal();
                this.keyTimer.setInitialDelay(300);
                this.keyTimer.start();
            }
            else if (keyEvent.getKeyCode() == 40) {
                this.step = -1;
                if (keyEvent.getModifiers() == 1) {
                    this.step = -10;
                }
                this.changeVal();
                this.keyTimer.setInitialDelay(300);
                this.keyTimer.start();
            }
        }
        
        public void keyReleased(final KeyEvent keyEvent) {
            this.keyTimer.stop();
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            this.changeVal();
        }
        
        private void changeVal() {
            int n = Integer.parseInt(this.theField.getText()) + this.step;
            if (n > NumericTextField.this.max) {
                n = NumericTextField.this.max;
            }
            else if (n < NumericTextField.this.min) {
                n = NumericTextField.this.min;
            }
            this.theField.setText("" + n);
        }
    }
    
    protected class NumericDocument extends PlainDocument
    {
        NumericDocument() {
            this.addDocumentListener(new KeyInputListener());
        }
        
        public void insertString(final int n, final String s, final AttributeSet set) throws BadLocationException {
            if (s == null || s.length() == 0) {
                return;
            }
            if (this.getLength() + s.length() > NumericTextField.this.columns) {
                return;
            }
            if (!this.checkInput(s)) {
                Toolkit.getDefaultToolkit().beep();
                return;
            }
            final String text = this.getText(0, this.getLength());
            String s2;
            if (n == 0) {
                s2 = s + text;
            }
            else if (n >= text.length()) {
                s2 = text + s;
            }
            else {
                s2 = text.substring(0, n) + s + text.substring(n);
            }
            int n2 = Integer.parseInt(s2);
            boolean b = false;
            if (n2 < NumericTextField.this.min) {
                n2 = NumericTextField.this.min;
                b = true;
            }
            else if (n2 > NumericTextField.this.max) {
                n2 = NumericTextField.this.max;
                b = true;
            }
            if (b) {
                this.remove(0, this.getLength());
                super.insertString(0, String.valueOf(n2), set);
            }
            else {
                super.insertString(n, s, set);
            }
        }
        
        private boolean checkInput(final String s) {
            for (int i = 0; i < s.length(); ++i) {
                if (!Character.isDigit(s.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }
}
