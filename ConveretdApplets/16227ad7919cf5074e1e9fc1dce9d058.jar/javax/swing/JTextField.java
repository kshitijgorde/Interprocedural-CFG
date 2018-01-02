// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.accessibility.AccessibleState;
import javax.accessibility.AccessibleStateSet;
import javax.swing.event.ChangeEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.text.TextAction;
import javax.accessibility.AccessibleContext;
import java.awt.event.ActionEvent;
import javax.swing.text.PlainDocument;
import java.util.EventListener;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

public class JTextField extends JTextComponent implements SwingConstants
{
    public static final String notifyAction = "notify-field-accept";
    private BoundedRangeModel visibility;
    private int horizontalAlignment;
    private int columns;
    private int columnWidth;
    private String command;
    private static final Action[] defaultActions;
    private static final String uiClassID = "TextFieldUI";
    static /* synthetic */ Class class$java$awt$event$ActionListener;
    
    static {
        defaultActions = new Action[] { new NotifyAction() };
    }
    
    public JTextField() {
        this(null, null, 0);
    }
    
    public JTextField(final int n) {
        this(null, null, n);
    }
    
    public JTextField(final String s) {
        this(null, s, 0);
    }
    
    public JTextField(final String s, final int n) {
        this(null, s, n);
    }
    
    public JTextField(Document defaultModel, final String text, final int columns) {
        this.horizontalAlignment = 2;
        if (columns < 0) {
            throw new IllegalArgumentException("columns less than zero.");
        }
        (this.visibility = new DefaultBoundedRangeModel()).addChangeListener(new ScrollRepainter());
        this.columns = columns;
        if (defaultModel == null) {
            defaultModel = this.createDefaultModel();
        }
        this.setDocument(defaultModel);
        if (text != null) {
            this.setText(text);
        }
    }
    
    public synchronized void addActionListener(final ActionListener actionListener) {
        super.listenerList.add((JTextField.class$java$awt$event$ActionListener != null) ? JTextField.class$java$awt$event$ActionListener : (JTextField.class$java$awt$event$ActionListener = class$("java.awt.event.ActionListener")), actionListener);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    protected Document createDefaultModel() {
        return new PlainDocument();
    }
    
    protected void fireActionPerformed() {
        final Object[] listenerList = super.listenerList.getListenerList();
        final ActionEvent actionEvent = new ActionEvent(this, 1001, (this.command != null) ? this.command : this.getText());
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((JTextField.class$java$awt$event$ActionListener != null) ? JTextField.class$java$awt$event$ActionListener : (JTextField.class$java$awt$event$ActionListener = class$("java.awt.event.ActionListener")))) {
                ((ActionListener)listenerList[i + 1]).actionPerformed(actionEvent);
            }
        }
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJTextField();
        }
        return super.accessibleContext;
    }
    
    public Action[] getActions() {
        return TextAction.augmentList(super.getActions(), JTextField.defaultActions);
    }
    
    protected int getColumnWidth() {
        if (this.columnWidth == 0) {
            this.columnWidth = this.getFontMetrics(this.getFont()).charWidth('m');
        }
        return this.columnWidth;
    }
    
    public int getColumns() {
        return this.columns;
    }
    
    public int getHorizontalAlignment() {
        return this.horizontalAlignment;
    }
    
    public BoundedRangeModel getHorizontalVisibility() {
        return this.visibility;
    }
    
    public Dimension getPreferredSize() {
        synchronized (this.getTreeLock()) {
            final Dimension preferredSize = super.getPreferredSize();
            if (this.columns != 0) {
                preferredSize.width = this.columns * this.getColumnWidth();
            }
            // monitorexit(this.getTreeLock())
            return preferredSize;
        }
    }
    
    public int getScrollOffset() {
        return this.visibility.getValue();
    }
    
    public String getUIClassID() {
        return "TextFieldUI";
    }
    
    public boolean isValidateRoot() {
        return true;
    }
    
    protected String paramString() {
        String s;
        if (this.horizontalAlignment == 2) {
            s = "LEFT";
        }
        else if (this.horizontalAlignment == 0) {
            s = "CENTER";
        }
        else if (this.horizontalAlignment == 4) {
            s = "RIGHT";
        }
        else {
            s = "";
        }
        return String.valueOf(super.paramString()) + ",columns=" + this.columns + ",columnWidth=" + this.columnWidth + ",command=" + ((this.command != null) ? this.command : "") + ",horizontalAlignment=" + s;
    }
    
    public void postActionEvent() {
        this.fireActionPerformed();
    }
    
    public synchronized void removeActionListener(final ActionListener actionListener) {
        super.listenerList.remove((JTextField.class$java$awt$event$ActionListener != null) ? JTextField.class$java$awt$event$ActionListener : (JTextField.class$java$awt$event$ActionListener = class$("java.awt.event.ActionListener")), actionListener);
    }
    
    public void scrollRectToVisible(final Rectangle rectangle) {
        final int n = rectangle.x + this.visibility.getValue();
        if (n < this.visibility.getValue()) {
            this.visibility.setValue(n - 2);
        }
        else if (n > this.visibility.getValue() + this.visibility.getExtent()) {
            this.visibility.setValue(n - this.visibility.getExtent() + 2);
        }
    }
    
    public void setActionCommand(final String command) {
        this.command = command;
    }
    
    public void setColumns(final int columns) {
        final int columns2 = this.columns;
        if (columns < 0) {
            throw new IllegalArgumentException("columns less than zero.");
        }
        if (columns != columns2) {
            this.columns = columns;
            this.invalidate();
        }
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.columnWidth = 0;
    }
    
    public void setHorizontalAlignment(final int horizontalAlignment) {
        if (horizontalAlignment == this.horizontalAlignment) {
            return;
        }
        final int horizontalAlignment2 = this.horizontalAlignment;
        if (horizontalAlignment == 2 || horizontalAlignment == 0 || horizontalAlignment == 4) {
            this.firePropertyChange("horizontalAlignment", horizontalAlignment2, this.horizontalAlignment = horizontalAlignment);
            this.invalidate();
            this.repaint();
            return;
        }
        throw new IllegalArgumentException("horizontalAlignment");
    }
    
    public void setScrollOffset(final int value) {
        this.visibility.setValue(value);
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (super.ui != null && this.getUIClassID().equals("TextFieldUI")) {
            super.ui.installUI(this);
        }
    }
    
    static class NotifyAction extends TextAction
    {
        NotifyAction() {
            super("notify-field-accept");
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent focusedComponent = this.getFocusedComponent();
            if (focusedComponent instanceof JTextField) {
                ((JTextField)focusedComponent).postActionEvent();
            }
        }
    }
    
    class ScrollRepainter implements ChangeListener
    {
        public void stateChanged(final ChangeEvent changeEvent) {
            JTextField.this.repaint();
        }
    }
    
    protected class AccessibleJTextField extends AccessibleJTextComponent
    {
        public AccessibleStateSet getAccessibleStateSet() {
            final AccessibleStateSet accessibleStateSet = super.getAccessibleStateSet();
            accessibleStateSet.add(AccessibleState.SINGLE_LINE);
            return accessibleStateSet;
        }
    }
}
