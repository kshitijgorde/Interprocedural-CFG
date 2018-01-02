// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleAction;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.plaf.ComponentUI;
import java.awt.event.KeyEvent;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.plaf.ComboBoxUI;
import javax.accessibility.AccessibleContext;
import java.awt.event.ItemEvent;
import javax.swing.event.ListDataEvent;
import java.awt.event.ItemListener;
import java.util.EventListener;
import java.awt.event.ActionEvent;
import java.util.Vector;
import javax.accessibility.Accessible;
import java.awt.event.ActionListener;
import javax.swing.event.ListDataListener;
import java.awt.ItemSelectable;

public class JComboBox extends JComponent implements ItemSelectable, ListDataListener, ActionListener, Accessible
{
    private static final String uiClassID = "ComboBoxUI";
    protected ComboBoxModel dataModel;
    protected ListCellRenderer renderer;
    protected ComboBoxEditor editor;
    protected int maximumRowCount;
    protected boolean isEditable;
    protected Object selectedItemReminder;
    protected KeySelectionManager keySelectionManager;
    protected String actionCommand;
    protected boolean lightWeightPopupEnabled;
    boolean firedActionEventOnContentsChanged;
    boolean firingActionEvent;
    static /* synthetic */ Class class$java$awt$event$ItemListener;
    static /* synthetic */ Class class$java$awt$event$ActionListener;
    
    public JComboBox() {
        this.maximumRowCount = 8;
        this.isEditable = false;
        this.selectedItemReminder = null;
        this.keySelectionManager = null;
        this.actionCommand = "comboBoxChanged";
        this.lightWeightPopupEnabled = JPopupMenu.getDefaultLightWeightPopupEnabled();
        this.firedActionEventOnContentsChanged = false;
        this.firingActionEvent = false;
        this.setModel(new DefaultComboBoxModel());
        this.init();
    }
    
    public JComboBox(final Vector vector) {
        this.maximumRowCount = 8;
        this.isEditable = false;
        this.selectedItemReminder = null;
        this.keySelectionManager = null;
        this.actionCommand = "comboBoxChanged";
        this.lightWeightPopupEnabled = JPopupMenu.getDefaultLightWeightPopupEnabled();
        this.firedActionEventOnContentsChanged = false;
        this.firingActionEvent = false;
        this.setModel(new DefaultComboBoxModel(vector));
        this.init();
    }
    
    public JComboBox(final ComboBoxModel model) {
        this.maximumRowCount = 8;
        this.isEditable = false;
        this.selectedItemReminder = null;
        this.keySelectionManager = null;
        this.actionCommand = "comboBoxChanged";
        this.lightWeightPopupEnabled = JPopupMenu.getDefaultLightWeightPopupEnabled();
        this.firedActionEventOnContentsChanged = false;
        this.firingActionEvent = false;
        this.setModel(model);
        this.init();
    }
    
    public JComboBox(final Object[] array) {
        this.maximumRowCount = 8;
        this.isEditable = false;
        this.selectedItemReminder = null;
        this.keySelectionManager = null;
        this.actionCommand = "comboBoxChanged";
        this.lightWeightPopupEnabled = JPopupMenu.getDefaultLightWeightPopupEnabled();
        this.firedActionEventOnContentsChanged = false;
        this.firingActionEvent = false;
        this.setModel(new DefaultComboBoxModel(array));
        this.init();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object item = this.getEditor().getItem();
        this.firedActionEventOnContentsChanged = false;
        this.getUI().setPopupVisible(this, false);
        this.getModel().setSelectedItem(item);
        if (!this.firedActionEventOnContentsChanged) {
            this.fireActionEvent();
        }
        else {
            this.firedActionEventOnContentsChanged = false;
        }
    }
    
    public void addActionListener(final ActionListener actionListener) {
        super.listenerList.add((JComboBox.class$java$awt$event$ActionListener != null) ? JComboBox.class$java$awt$event$ActionListener : (JComboBox.class$java$awt$event$ActionListener = class$("java.awt.event.ActionListener")), actionListener);
    }
    
    public void addItem(final Object o) {
        this.checkMutableComboBoxModel();
        ((MutableComboBoxModel)this.dataModel).addElement(o);
    }
    
    public void addItemListener(final ItemListener itemListener) {
        super.listenerList.add((JComboBox.class$java$awt$event$ItemListener != null) ? JComboBox.class$java$awt$event$ItemListener : (JComboBox.class$java$awt$event$ItemListener = class$("java.awt.event.ItemListener")), itemListener);
    }
    
    void checkMutableComboBoxModel() {
        if (!(this.dataModel instanceof MutableComboBoxModel)) {
            throw new RuntimeException("Cannot use this method with a non-Mutable data model.");
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public void configureEditor(final ComboBoxEditor comboBoxEditor, final Object item) {
        comboBoxEditor.setItem(item);
    }
    
    public void contentsChanged(final ListDataEvent listDataEvent) {
        final ComboBoxModel model = this.getModel();
        final Object selectedItem = model.getSelectedItem();
        if (this.selectedItemReminder == null) {
            if (selectedItem != null) {
                this.selectedItemChanged();
            }
        }
        else if (!this.selectedItemReminder.equals(selectedItem)) {
            this.selectedItemChanged();
        }
        if (!this.isEditable() && selectedItem != null) {
            boolean b = true;
            final Object selectedItem2 = model.getSelectedItem();
            for (int i = 0; i < model.getSize(); ++i) {
                if (model.getElementAt(i).equals(selectedItem2)) {
                    b = false;
                    break;
                }
            }
            if (b) {
                if (model.getSize() > 0) {
                    this.setSelectedIndex(0);
                }
                else {
                    this.setSelectedItem(null);
                }
            }
        }
    }
    
    protected KeySelectionManager createDefaultKeySelectionManager() {
        return (KeySelectionManager)new DefaultKeySelectionManager();
    }
    
    protected void fireActionEvent() {
        if (!this.firingActionEvent) {
            this.firingActionEvent = true;
            ActionEvent actionEvent = null;
            final Object[] listenerList = super.listenerList.getListenerList();
            for (int i = listenerList.length - 2; i >= 0; i -= 2) {
                if (listenerList[i] == ((JComboBox.class$java$awt$event$ActionListener != null) ? JComboBox.class$java$awt$event$ActionListener : (JComboBox.class$java$awt$event$ActionListener = class$("java.awt.event.ActionListener")))) {
                    if (actionEvent == null) {
                        actionEvent = new ActionEvent(this, 1001, this.getActionCommand());
                    }
                    ((ActionListener)listenerList[i + 1]).actionPerformed(actionEvent);
                }
            }
            this.firingActionEvent = false;
        }
    }
    
    protected void fireItemStateChanged(final ItemEvent itemEvent) {
        final Object[] listenerList = super.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((JComboBox.class$java$awt$event$ItemListener != null) ? JComboBox.class$java$awt$event$ItemListener : (JComboBox.class$java$awt$event$ItemListener = class$("java.awt.event.ItemListener")))) {
                ((ItemListener)listenerList[i + 1]).itemStateChanged(itemEvent);
            }
        }
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJComboBox();
        }
        return super.accessibleContext;
    }
    
    public String getActionCommand() {
        return this.actionCommand;
    }
    
    public ComboBoxEditor getEditor() {
        return this.editor;
    }
    
    public Object getItemAt(final int n) {
        return this.dataModel.getElementAt(n);
    }
    
    public int getItemCount() {
        return this.dataModel.getSize();
    }
    
    public KeySelectionManager getKeySelectionManager() {
        return this.keySelectionManager;
    }
    
    public int getMaximumRowCount() {
        return this.maximumRowCount;
    }
    
    public ComboBoxModel getModel() {
        return this.dataModel;
    }
    
    public ListCellRenderer getRenderer() {
        return this.renderer;
    }
    
    public int getSelectedIndex() {
        final Object selectedItem = this.dataModel.getSelectedItem();
        for (int i = 0; i < this.dataModel.getSize(); ++i) {
            if (this.dataModel.getElementAt(i).equals(selectedItem)) {
                return i;
            }
        }
        return -1;
    }
    
    public Object getSelectedItem() {
        return this.dataModel.getSelectedItem();
    }
    
    public Object[] getSelectedObjects() {
        final Object selectedItem = this.getSelectedItem();
        if (selectedItem == null) {
            return new Object[0];
        }
        return new Object[] { selectedItem };
    }
    
    public ComboBoxUI getUI() {
        return (ComboBoxUI)super.ui;
    }
    
    public String getUIClassID() {
        return "ComboBoxUI";
    }
    
    public void hidePopup() {
        this.setPopupVisible(false);
    }
    
    private void init() {
        this.installAncestorListener();
        this.setOpaque(true);
        this.setAlignmentX(0.0f);
        this.setAlignmentY(0.5f);
        this.updateUI();
    }
    
    public void insertItemAt(final Object o, final int n) {
        this.checkMutableComboBoxModel();
        ((MutableComboBoxModel)this.dataModel).insertElementAt(o, n);
    }
    
    protected void installAncestorListener() {
        this.addAncestorListener(new AncestorListener() {
            public void ancestorAdded(final AncestorEvent ancestorEvent) {
                JComboBox.this.hidePopup();
            }
            
            public void ancestorMoved(final AncestorEvent ancestorEvent) {
                JComboBox.this.hidePopup();
            }
            
            public void ancestorRemoved(final AncestorEvent ancestorEvent) {
                JComboBox.this.hidePopup();
            }
        });
    }
    
    public void intervalAdded(final ListDataEvent listDataEvent) {
        this.contentsChanged(listDataEvent);
    }
    
    public void intervalRemoved(final ListDataEvent listDataEvent) {
        this.contentsChanged(listDataEvent);
    }
    
    public boolean isEditable() {
        return this.isEditable;
    }
    
    public boolean isFocusTraversable() {
        return this.getUI().isFocusTraversable(this);
    }
    
    public boolean isLightWeightPopupEnabled() {
        return this.lightWeightPopupEnabled;
    }
    
    public boolean isPopupVisible() {
        return this.getUI().isPopupVisible(this);
    }
    
    protected String paramString() {
        return String.valueOf(super.paramString()) + ",isEditable=" + (this.isEditable ? "true" : "false") + ",lightWeightPopupEnabled=" + (this.lightWeightPopupEnabled ? "true" : "false") + ",maximumRowCount=" + this.maximumRowCount + ",selectedItemReminder=" + ((this.selectedItemReminder != null) ? this.selectedItemReminder.toString() : "");
    }
    
    public void processKeyEvent(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 9) {
            this.hidePopup();
        }
        super.processKeyEvent(keyEvent);
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        super.listenerList.remove((JComboBox.class$java$awt$event$ActionListener != null) ? JComboBox.class$java$awt$event$ActionListener : (JComboBox.class$java$awt$event$ActionListener = class$("java.awt.event.ActionListener")), actionListener);
    }
    
    public void removeAllItems() {
        this.checkMutableComboBoxModel();
        final MutableComboBoxModel mutableComboBoxModel = (MutableComboBoxModel)this.dataModel;
        final int size = mutableComboBoxModel.getSize();
        if (mutableComboBoxModel instanceof DefaultComboBoxModel) {
            ((DefaultComboBoxModel)mutableComboBoxModel).removeAllElements();
        }
        else {
            for (int i = 0; i < size; ++i) {
                mutableComboBoxModel.removeElement(mutableComboBoxModel.getElementAt(0));
            }
        }
    }
    
    public void removeItem(final Object o) {
        this.checkMutableComboBoxModel();
        ((MutableComboBoxModel)this.dataModel).removeElement(o);
    }
    
    public void removeItemAt(final int n) {
        this.checkMutableComboBoxModel();
        ((MutableComboBoxModel)this.dataModel).removeElementAt(n);
    }
    
    public void removeItemListener(final ItemListener itemListener) {
        super.listenerList.remove((JComboBox.class$java$awt$event$ItemListener != null) ? JComboBox.class$java$awt$event$ItemListener : (JComboBox.class$java$awt$event$ItemListener = class$("java.awt.event.ItemListener")), itemListener);
    }
    
    public boolean selectWithKeyChar(final char c) {
        if (this.keySelectionManager == null) {
            this.keySelectionManager = this.createDefaultKeySelectionManager();
        }
        final int selectionForKey = this.keySelectionManager.selectionForKey(c, this.getModel());
        if (selectionForKey != -1) {
            this.setSelectedIndex(selectionForKey);
            return true;
        }
        return false;
    }
    
    protected void selectedItemChanged() {
        if (this.selectedItemReminder != null) {
            this.fireItemStateChanged(new ItemEvent(this, 701, this.selectedItemReminder, 2));
        }
        this.selectedItemReminder = this.getModel().getSelectedItem();
        if (this.selectedItemReminder != null) {
            this.fireItemStateChanged(new ItemEvent(this, 701, this.selectedItemReminder, 1));
        }
        this.fireActionEvent();
        this.firedActionEventOnContentsChanged = true;
    }
    
    public void setActionCommand(final String actionCommand) {
        this.actionCommand = actionCommand;
    }
    
    public void setEditable(final boolean isEditable) {
        final boolean b = isEditable != this.isEditable;
        this.isEditable = isEditable;
        if (b) {
            this.firePropertyChange("editable", this.isEditable ^ true, this.isEditable);
        }
    }
    
    public void setEditor(final ComboBoxEditor editor) {
        final ComboBoxEditor editor2 = this.editor;
        if (this.editor != null) {
            this.editor.removeActionListener(this);
        }
        this.editor = editor;
        if (this.editor != null) {
            this.editor.addActionListener(this);
        }
        this.firePropertyChange("editor", editor2, this.editor);
    }
    
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        this.firePropertyChange("enabled", this.isEnabled() ^ true, this.isEnabled());
    }
    
    public void setKeySelectionManager(final KeySelectionManager keySelectionManager) {
        this.keySelectionManager = keySelectionManager;
    }
    
    public void setLightWeightPopupEnabled(final boolean lightWeightPopupEnabled) {
        this.lightWeightPopupEnabled = lightWeightPopupEnabled;
    }
    
    public void setMaximumRowCount(final int maximumRowCount) {
        this.firePropertyChange("maximumRowCount", this.maximumRowCount, this.maximumRowCount = maximumRowCount);
    }
    
    public void setModel(final ComboBoxModel dataModel) {
        final ComboBoxModel dataModel2 = this.dataModel;
        if (this.dataModel != null) {
            this.dataModel.removeListDataListener(this);
        }
        this.firePropertyChange("model", dataModel2, this.dataModel = dataModel);
        this.dataModel.addListDataListener(this);
        this.invalidate();
    }
    
    public void setPopupVisible(final boolean b) {
        this.getUI().setPopupVisible(this, b);
    }
    
    public void setRenderer(final ListCellRenderer renderer) {
        this.firePropertyChange("renderer", this.renderer, this.renderer = renderer);
        this.invalidate();
    }
    
    public void setSelectedIndex(final int n) {
        final int size = this.dataModel.getSize();
        if (n == -1) {
            this.setSelectedItem(null);
        }
        else {
            if (n < -1 || n >= size) {
                throw new IllegalArgumentException("setSelectedIndex: " + n + " out of bounds");
            }
            this.setSelectedItem(this.dataModel.getElementAt(n));
        }
    }
    
    public void setSelectedItem(final Object selectedItem) {
        this.firedActionEventOnContentsChanged = false;
        this.dataModel.setSelectedItem(selectedItem);
        if (!this.firedActionEventOnContentsChanged) {
            this.fireActionEvent();
        }
        else {
            this.firedActionEventOnContentsChanged = false;
        }
    }
    
    public void setUI(final ComboBoxUI ui) {
        super.setUI(ui);
    }
    
    public void showPopup() {
        this.setPopupVisible(true);
    }
    
    public void updateUI() {
        this.setUI((ComboBoxUI)UIManager.getUI(this));
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (super.ui != null && this.getUIClassID().equals("ComboBoxUI")) {
            super.ui.installUI(this);
        }
    }
    
    class DefaultKeySelectionManager implements KeySelectionManager, Serializable
    {
        public int selectionForKey(final char c, final ComboBoxModel comboBoxModel) {
            int n = -1;
            final Object selectedItem = comboBoxModel.getSelectedItem();
            if (selectedItem != null) {
                final String string = selectedItem.toString();
                for (int i = 0; i < comboBoxModel.getSize(); ++i) {
                    if (string.equals(comboBoxModel.getElementAt(i).toString())) {
                        n = i;
                        break;
                    }
                }
            }
            final char char1 = String.valueOf(c).toLowerCase().charAt(0);
            for (int j = ++n; j < comboBoxModel.getSize(); ++j) {
                final String lowerCase = comboBoxModel.getElementAt(j).toString().toLowerCase();
                if (lowerCase.length() > 0 && lowerCase.charAt(0) == char1) {
                    return j;
                }
            }
            for (int k = 0; k < n; ++k) {
                final String lowerCase2 = comboBoxModel.getElementAt(k).toString().toLowerCase();
                if (lowerCase2.length() > 0 && lowerCase2.charAt(0) == char1) {
                    return k;
                }
            }
            return -1;
        }
    }
    
    protected class AccessibleJComboBox extends AccessibleJComponent implements AccessibleAction
    {
        public boolean doAccessibleAction(final int n) {
            if (n == 0) {
                JComboBox.this.setPopupVisible(JComboBox.this.isPopupVisible() ^ true);
                return true;
            }
            return false;
        }
        
        public AccessibleAction getAccessibleAction() {
            return this;
        }
        
        public int getAccessibleActionCount() {
            return 1;
        }
        
        public String getAccessibleActionDescription(final int n) {
            if (n == 0) {
                return new String("togglePopup");
            }
            return null;
        }
        
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.COMBO_BOX;
        }
    }
    
    public interface KeySelectionManager
    {
        int selectionForKey(final char p0, final ComboBoxModel p1);
    }
}
