// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.accessibility.AccessibleStateSet;
import javax.accessibility.AccessibleValue;
import javax.accessibility.AccessibleAction;
import java.io.Serializable;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import java.awt.Graphics;
import javax.accessibility.AccessibleState;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.plaf.ButtonUI;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.EventListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeListener;
import java.awt.Insets;
import java.awt.ItemSelectable;

public abstract class AbstractButton extends JComponent implements ItemSelectable, SwingConstants
{
    public static final String MODEL_CHANGED_PROPERTY = "model";
    public static final String TEXT_CHANGED_PROPERTY = "text";
    public static final String MNEMONIC_CHANGED_PROPERTY = "mnemonic";
    public static final String MARGIN_CHANGED_PROPERTY = "margin";
    public static final String VERTICAL_ALIGNMENT_CHANGED_PROPERTY = "verticalAlignment";
    public static final String HORIZONTAL_ALIGNMENT_CHANGED_PROPERTY = "horizontalAlignment";
    public static final String VERTICAL_TEXT_POSITION_CHANGED_PROPERTY = "verticalTextPosition";
    public static final String HORIZONTAL_TEXT_POSITION_CHANGED_PROPERTY = "horizontalTextPosition";
    public static final String BORDER_PAINTED_CHANGED_PROPERTY = "borderPainted";
    public static final String FOCUS_PAINTED_CHANGED_PROPERTY = "focusPainted";
    public static final String ROLLOVER_ENABLED_CHANGED_PROPERTY = "rolloverEnabled";
    public static final String CONTENT_AREA_FILLED_CHANGED_PROPERTY = "contentAreaFilled";
    public static final String ICON_CHANGED_PROPERTY = "icon";
    public static final String PRESSED_ICON_CHANGED_PROPERTY = "pressedIcon";
    public static final String SELECTED_ICON_CHANGED_PROPERTY = "selectedIcon";
    public static final String ROLLOVER_ICON_CHANGED_PROPERTY = "rolloverIcon";
    public static final String ROLLOVER_SELECTED_ICON_CHANGED_PROPERTY = "rolloverSelectedIcon";
    public static final String DISABLED_ICON_CHANGED_PROPERTY = "disabledIcon";
    public static final String DISABLED_SELECTED_ICON_CHANGED_PROPERTY = "disabledSelectedIcon";
    protected ButtonModel model;
    private String text;
    private Insets margin;
    private Insets defaultMargin;
    private Icon defaultIcon;
    private Icon pressedIcon;
    private Icon disabledIcon;
    private Icon selectedIcon;
    private Icon disabledSelectedIcon;
    private Icon rolloverIcon;
    private Icon rolloverSelectedIcon;
    private boolean paintBorder;
    private boolean paintFocus;
    private boolean rolloverEnabled;
    private boolean contentAreaFilled;
    private int verticalAlignment;
    private int horizontalAlignment;
    private int verticalTextPosition;
    private int horizontalTextPosition;
    protected ChangeListener changeListener;
    protected ActionListener actionListener;
    protected ItemListener itemListener;
    protected transient ChangeEvent changeEvent;
    static /* synthetic */ Class class$javax$swing$event$ChangeListener;
    static /* synthetic */ Class class$java$awt$event$ActionListener;
    static /* synthetic */ Class class$java$awt$event$ItemListener;
    
    public AbstractButton() {
        this.model = null;
        this.text = "";
        this.margin = null;
        this.defaultMargin = null;
        this.defaultIcon = null;
        this.pressedIcon = null;
        this.disabledIcon = null;
        this.selectedIcon = null;
        this.disabledSelectedIcon = null;
        this.rolloverIcon = null;
        this.rolloverSelectedIcon = null;
        this.paintBorder = true;
        this.paintFocus = true;
        this.rolloverEnabled = false;
        this.contentAreaFilled = true;
        this.verticalAlignment = 0;
        this.horizontalAlignment = 0;
        this.verticalTextPosition = 0;
        this.horizontalTextPosition = 11;
        this.changeListener = null;
        this.actionListener = null;
        this.itemListener = null;
    }
    
    public void addActionListener(final ActionListener actionListener) {
        super.listenerList.add((AbstractButton.class$java$awt$event$ActionListener != null) ? AbstractButton.class$java$awt$event$ActionListener : (AbstractButton.class$java$awt$event$ActionListener = class$("java.awt.event.ActionListener")), actionListener);
    }
    
    public void addChangeListener(final ChangeListener changeListener) {
        super.listenerList.add((AbstractButton.class$javax$swing$event$ChangeListener != null) ? AbstractButton.class$javax$swing$event$ChangeListener : (AbstractButton.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")), changeListener);
    }
    
    public void addItemListener(final ItemListener itemListener) {
        super.listenerList.add((AbstractButton.class$java$awt$event$ItemListener != null) ? AbstractButton.class$java$awt$event$ItemListener : (AbstractButton.class$java$awt$event$ItemListener = class$("java.awt.event.ItemListener")), itemListener);
    }
    
    protected int checkHorizontalKey(final int n, final String s) {
        if (n == 2 || n == 0 || n == 4 || n == 10 || n == 11) {
            return n;
        }
        throw new IllegalArgumentException(s);
    }
    
    protected int checkVerticalKey(final int n, final String s) {
        if (n == 1 || n == 0 || n == 3) {
            return n;
        }
        throw new IllegalArgumentException(s);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    protected ActionListener createActionListener() {
        return new ForwardActionEvents();
    }
    
    protected ChangeListener createChangeListener() {
        return new ButtonChangeListener();
    }
    
    protected ItemListener createItemListener() {
        return new ForwardItemEvents();
    }
    
    public void doClick() {
        this.doClick(68);
    }
    
    public void doClick(final int n) {
        final Dimension size = this.getSize();
        this.model.setArmed(true);
        this.model.setPressed(true);
        this.paintImmediately(new Rectangle(0, 0, size.width, size.height));
        try {
            Thread.currentThread();
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
        this.model.setPressed(false);
        this.model.setArmed(false);
    }
    
    protected void fireActionPerformed(final ActionEvent actionEvent) {
        final Object[] listenerList = super.listenerList.getListenerList();
        ActionEvent actionEvent2 = null;
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((AbstractButton.class$java$awt$event$ActionListener != null) ? AbstractButton.class$java$awt$event$ActionListener : (AbstractButton.class$java$awt$event$ActionListener = class$("java.awt.event.ActionListener")))) {
                if (actionEvent2 == null) {
                    String s = actionEvent.getActionCommand();
                    if (s == null) {
                        s = this.getActionCommand();
                    }
                    actionEvent2 = new ActionEvent(this, 1001, s, actionEvent.getModifiers());
                }
                ((ActionListener)listenerList[i + 1]).actionPerformed(actionEvent2);
            }
        }
    }
    
    protected void fireItemStateChanged(final ItemEvent itemEvent) {
        final Object[] listenerList = super.listenerList.getListenerList();
        ItemEvent itemEvent2 = null;
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((AbstractButton.class$java$awt$event$ItemListener != null) ? AbstractButton.class$java$awt$event$ItemListener : (AbstractButton.class$java$awt$event$ItemListener = class$("java.awt.event.ItemListener")))) {
                if (itemEvent2 == null) {
                    itemEvent2 = new ItemEvent(this, 701, this, itemEvent.getStateChange());
                }
                ((ItemListener)listenerList[i + 1]).itemStateChanged(itemEvent2);
            }
        }
    }
    
    protected void fireStateChanged() {
        final Object[] listenerList = super.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((AbstractButton.class$javax$swing$event$ChangeListener != null) ? AbstractButton.class$javax$swing$event$ChangeListener : (AbstractButton.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")))) {
                if (this.changeEvent == null) {
                    this.changeEvent = new ChangeEvent(this);
                }
                ((ChangeListener)listenerList[i + 1]).stateChanged(this.changeEvent);
            }
        }
    }
    
    public String getActionCommand() {
        String s = this.getModel().getActionCommand();
        if (s == null) {
            s = this.getText();
        }
        return s;
    }
    
    public Icon getDisabledIcon() {
        if (this.disabledIcon == null && this.defaultIcon != null && this.defaultIcon instanceof ImageIcon) {
            this.disabledIcon = new ImageIcon(GrayFilter.createDisabledImage(((ImageIcon)this.defaultIcon).getImage()));
        }
        return this.disabledIcon;
    }
    
    public Icon getDisabledSelectedIcon() {
        if (this.disabledSelectedIcon == null) {
            if (this.selectedIcon == null || !(this.selectedIcon instanceof ImageIcon)) {
                return this.disabledIcon;
            }
            this.disabledSelectedIcon = new ImageIcon(GrayFilter.createDisabledImage(((ImageIcon)this.selectedIcon).getImage()));
        }
        return this.disabledSelectedIcon;
    }
    
    public int getHorizontalAlignment() {
        return this.horizontalAlignment;
    }
    
    public int getHorizontalTextPosition() {
        return this.horizontalTextPosition;
    }
    
    public Icon getIcon() {
        return this.defaultIcon;
    }
    
    public String getLabel() {
        return this.getText();
    }
    
    public Insets getMargin() {
        return this.margin;
    }
    
    public int getMnemonic() {
        return this.model.getMnemonic();
    }
    
    public ButtonModel getModel() {
        return this.model;
    }
    
    public Icon getPressedIcon() {
        return this.pressedIcon;
    }
    
    public Icon getRolloverIcon() {
        return this.rolloverIcon;
    }
    
    public Icon getRolloverSelectedIcon() {
        return this.rolloverSelectedIcon;
    }
    
    public Icon getSelectedIcon() {
        return this.selectedIcon;
    }
    
    public synchronized Object[] getSelectedObjects() {
        if (!this.isSelected()) {
            return null;
        }
        return new Object[] { this.getText() };
    }
    
    public String getText() {
        return this.text;
    }
    
    public ButtonUI getUI() {
        return (ButtonUI)super.ui;
    }
    
    public int getVerticalAlignment() {
        return this.verticalAlignment;
    }
    
    public int getVerticalTextPosition() {
        return this.verticalTextPosition;
    }
    
    protected void init(final String text, final Icon icon) {
        this.setLayout(new OverlayLayout(this));
        if (text != null) {
            this.setText(text);
        }
        if (icon != null) {
            this.setIcon(icon);
        }
        this.updateUI();
        this.addFocusListener(new FocusListener() {
            public void focusGained(final FocusEvent focusEvent) {
                if (AbstractButton.this.accessibleContext != null) {
                    AbstractButton.this.accessibleContext.firePropertyChange("AccessibleState", null, AccessibleState.FOCUSED);
                }
            }
            
            public void focusLost(final FocusEvent focusEvent) {
                if (AbstractButton.this.accessibleContext != null) {
                    AbstractButton.this.accessibleContext.firePropertyChange("AccessibleState", null, AccessibleState.FOCUSED);
                }
                if (AbstractButton.this.isFocusPainted()) {
                    AbstractButton.this.repaint();
                }
            }
        });
        this.setAlignmentX(0.0f);
        this.setAlignmentY(0.5f);
    }
    
    public boolean isBorderPainted() {
        return this.paintBorder;
    }
    
    public boolean isContentAreaFilled() {
        return this.contentAreaFilled;
    }
    
    public boolean isFocusPainted() {
        return this.paintFocus;
    }
    
    public boolean isRolloverEnabled() {
        return this.rolloverEnabled;
    }
    
    public boolean isSelected() {
        return this.model.isSelected();
    }
    
    protected void paintBorder(final Graphics graphics) {
        if (this.isBorderPainted()) {
            super.paintBorder(graphics);
        }
    }
    
    protected String paramString() {
        return String.valueOf(super.paramString()) + ",defaultIcon=" + ((this.defaultIcon != null) ? this.defaultIcon.toString() : "") + ",disabledIcon=" + ((this.disabledIcon != null) ? this.disabledIcon.toString() : "") + ",disabledSelectedIcon=" + ((this.disabledSelectedIcon != null) ? this.disabledSelectedIcon.toString() : "") + ",margin=" + this.margin + ",paintBorder=" + (this.paintBorder ? "true" : "false") + ",paintFocus=" + (this.paintFocus ? "true" : "false") + ",pressedIcon=" + ((this.pressedIcon != null) ? this.pressedIcon.toString() : "") + ",rolloverEnabled=" + (this.rolloverEnabled ? "true" : "false") + ",rolloverIcon=" + ((this.rolloverIcon != null) ? this.rolloverIcon.toString() : "") + ",rolloverSelectedIcon=" + ((this.rolloverSelectedIcon != null) ? this.rolloverSelectedIcon.toString() : "") + ",selectedIcon=" + ((this.selectedIcon != null) ? this.selectedIcon.toString() : "") + ",text=" + this.text;
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        super.listenerList.remove((AbstractButton.class$java$awt$event$ActionListener != null) ? AbstractButton.class$java$awt$event$ActionListener : (AbstractButton.class$java$awt$event$ActionListener = class$("java.awt.event.ActionListener")), actionListener);
    }
    
    public void removeChangeListener(final ChangeListener changeListener) {
        super.listenerList.remove((AbstractButton.class$javax$swing$event$ChangeListener != null) ? AbstractButton.class$javax$swing$event$ChangeListener : (AbstractButton.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")), changeListener);
    }
    
    public void removeItemListener(final ItemListener itemListener) {
        super.listenerList.remove((AbstractButton.class$java$awt$event$ItemListener != null) ? AbstractButton.class$java$awt$event$ItemListener : (AbstractButton.class$java$awt$event$ItemListener = class$("java.awt.event.ItemListener")), itemListener);
    }
    
    public void setActionCommand(final String actionCommand) {
        this.getModel().setActionCommand(actionCommand);
    }
    
    public void setBorderPainted(final boolean paintBorder) {
        final boolean paintBorder2 = this.paintBorder;
        this.firePropertyChange("borderPainted", paintBorder2, this.paintBorder = paintBorder);
        if (paintBorder != paintBorder2) {
            this.revalidate();
            this.repaint();
        }
    }
    
    public void setContentAreaFilled(final boolean contentAreaFilled) {
        final boolean contentAreaFilled2 = this.contentAreaFilled;
        this.firePropertyChange("contentAreaFilled", contentAreaFilled2, this.contentAreaFilled = contentAreaFilled);
        if (contentAreaFilled != contentAreaFilled2) {
            this.repaint();
        }
    }
    
    public void setDisabledIcon(final Icon disabledIcon) {
        final Icon disabledIcon2 = this.disabledIcon;
        this.firePropertyChange("disabledIcon", disabledIcon2, this.disabledIcon = disabledIcon);
        if (super.accessibleContext != null) {
            super.accessibleContext.firePropertyChange("AccessibleVisibleData", disabledIcon2, disabledIcon);
        }
        if (disabledIcon != disabledIcon2 && !this.isEnabled()) {
            this.repaint();
        }
    }
    
    public void setDisabledSelectedIcon(final Icon disabledSelectedIcon) {
        final Icon disabledSelectedIcon2 = this.disabledSelectedIcon;
        this.firePropertyChange("disabledSelectedIcon", disabledSelectedIcon2, this.disabledSelectedIcon = disabledSelectedIcon);
        if (super.accessibleContext != null) {
            super.accessibleContext.firePropertyChange("AccessibleVisibleData", disabledSelectedIcon2, disabledSelectedIcon);
        }
        if (disabledSelectedIcon != disabledSelectedIcon2) {
            if (disabledSelectedIcon == null || disabledSelectedIcon2 == null || disabledSelectedIcon.getIconWidth() != disabledSelectedIcon2.getIconWidth() || disabledSelectedIcon.getIconHeight() != disabledSelectedIcon2.getIconHeight()) {
                this.revalidate();
            }
            if (!this.isEnabled() && this.isSelected()) {
                this.repaint();
            }
        }
    }
    
    public void setEnabled(final boolean b) {
        super.setEnabled(b);
        this.model.setEnabled(b);
    }
    
    public void setFocusPainted(final boolean paintFocus) {
        final boolean paintFocus2 = this.paintFocus;
        this.firePropertyChange("focusPainted", paintFocus2, this.paintFocus = paintFocus);
        if (paintFocus != paintFocus2 && this.hasFocus()) {
            this.revalidate();
            this.repaint();
        }
    }
    
    public void setHorizontalAlignment(final int n) {
        if (n == this.horizontalAlignment) {
            return;
        }
        this.firePropertyChange("horizontalAlignment", this.horizontalAlignment, this.horizontalAlignment = this.checkHorizontalKey(n, "horizontalAlignment"));
        this.repaint();
    }
    
    public void setHorizontalTextPosition(final int n) {
        if (n == this.horizontalTextPosition) {
            return;
        }
        this.firePropertyChange("horizontalTextPosition", this.horizontalTextPosition, this.horizontalTextPosition = this.checkHorizontalKey(n, "horizontalTextPosition"));
        this.repaint();
    }
    
    public void setIcon(final Icon defaultIcon) {
        final Icon defaultIcon2 = this.defaultIcon;
        this.firePropertyChange("icon", defaultIcon2, this.defaultIcon = defaultIcon);
        if (super.accessibleContext != null) {
            super.accessibleContext.firePropertyChange("AccessibleVisibleData", defaultIcon2, defaultIcon);
        }
        if (defaultIcon != defaultIcon2) {
            if (defaultIcon == null || defaultIcon2 == null || defaultIcon.getIconWidth() != defaultIcon2.getIconWidth() || defaultIcon.getIconHeight() != defaultIcon2.getIconHeight()) {
                this.revalidate();
            }
            this.repaint();
        }
    }
    
    public void setLabel(final String text) {
        this.setText(text);
    }
    
    public void setMargin(Insets defaultMargin) {
        if (defaultMargin instanceof UIResource) {
            this.defaultMargin = defaultMargin;
        }
        else if (this.margin instanceof UIResource) {
            this.defaultMargin = this.margin;
        }
        if (defaultMargin == null && this.defaultMargin != null) {
            defaultMargin = this.defaultMargin;
        }
        final Insets margin = this.margin;
        this.firePropertyChange("margin", margin, this.margin = defaultMargin);
        if (margin == null || !defaultMargin.equals(margin)) {
            this.revalidate();
            this.repaint();
        }
    }
    
    public void setMnemonic(final char c) {
        int mnemonic = c;
        if (mnemonic >= 97 && mnemonic <= 122) {
            mnemonic -= 32;
        }
        this.setMnemonic(mnemonic);
    }
    
    public void setMnemonic(final int mnemonic) {
        final int mnemonic2 = this.getMnemonic();
        this.model.setMnemonic(mnemonic);
        this.firePropertyChange("mnemonic", mnemonic2, mnemonic);
        if (mnemonic != mnemonic2) {
            this.revalidate();
            this.repaint();
        }
    }
    
    public void setModel(final ButtonModel model) {
        final ButtonModel model2 = this.getModel();
        if (model2 != null) {
            model2.removeChangeListener(this.changeListener);
            model2.removeActionListener(this.actionListener);
            this.changeListener = null;
            this.actionListener = null;
        }
        if ((this.model = model) != null) {
            this.changeListener = this.createChangeListener();
            this.actionListener = this.createActionListener();
            this.itemListener = this.createItemListener();
            model.addChangeListener(this.changeListener);
            model.addActionListener(this.actionListener);
            model.addItemListener(this.itemListener);
        }
        this.firePropertyChange("model", model2, model);
        if (model != model2) {
            this.revalidate();
            this.repaint();
        }
    }
    
    public void setPressedIcon(final Icon pressedIcon) {
        final Icon pressedIcon2 = this.pressedIcon;
        this.firePropertyChange("pressedIcon", pressedIcon2, this.pressedIcon = pressedIcon);
        if (super.accessibleContext != null) {
            super.accessibleContext.firePropertyChange("AccessibleVisibleData", pressedIcon2, this.defaultIcon);
        }
        if (pressedIcon != pressedIcon2 && this.getModel().isPressed()) {
            this.repaint();
        }
    }
    
    public void setRolloverEnabled(final boolean rolloverEnabled) {
        final boolean rolloverEnabled2 = this.rolloverEnabled;
        this.firePropertyChange("rolloverEnabled", rolloverEnabled2, this.rolloverEnabled = rolloverEnabled);
        if (rolloverEnabled != rolloverEnabled2) {
            this.repaint();
        }
    }
    
    public void setRolloverIcon(final Icon rolloverIcon) {
        final Icon rolloverIcon2 = this.rolloverIcon;
        this.firePropertyChange("rolloverIcon", rolloverIcon2, this.rolloverIcon = rolloverIcon);
        if (super.accessibleContext != null) {
            super.accessibleContext.firePropertyChange("AccessibleVisibleData", rolloverIcon2, rolloverIcon);
        }
        this.setRolloverEnabled(true);
        if (rolloverIcon != rolloverIcon2) {
            this.repaint();
        }
    }
    
    public void setRolloverSelectedIcon(final Icon rolloverSelectedIcon) {
        final Icon rolloverSelectedIcon2 = this.rolloverSelectedIcon;
        this.firePropertyChange("rolloverSelectedIcon", rolloverSelectedIcon2, this.rolloverSelectedIcon = rolloverSelectedIcon);
        if (super.accessibleContext != null) {
            super.accessibleContext.firePropertyChange("AccessibleVisibleData", rolloverSelectedIcon2, rolloverSelectedIcon);
        }
        if (rolloverSelectedIcon != rolloverSelectedIcon2 && this.isSelected()) {
            this.repaint();
        }
    }
    
    public void setSelected(final boolean selected) {
        final boolean selected2 = this.isSelected();
        if (super.accessibleContext != null && selected2 != selected) {
            if (selected) {
                super.accessibleContext.firePropertyChange("AccessibleState", null, AccessibleState.SELECTED);
            }
            else {
                super.accessibleContext.firePropertyChange("AccessibleState", AccessibleState.SELECTED, null);
            }
        }
        this.model.setSelected(selected);
    }
    
    public void setSelectedIcon(final Icon selectedIcon) {
        final Icon selectedIcon2 = this.selectedIcon;
        this.firePropertyChange("selectedIcon", selectedIcon2, this.selectedIcon = selectedIcon);
        if (super.accessibleContext != null) {
            super.accessibleContext.firePropertyChange("AccessibleVisibleData", selectedIcon2, selectedIcon);
        }
        if (selectedIcon != selectedIcon2 && this.isSelected()) {
            this.repaint();
        }
    }
    
    public void setText(final String text) {
        final String text2 = this.text;
        this.firePropertyChange("text", text2, this.text = text);
        if (super.accessibleContext != null) {
            super.accessibleContext.firePropertyChange("AccessibleVisibleData", text2, text);
        }
        if (text == null || text2 == null || !text.equals(text2)) {
            this.revalidate();
            this.repaint();
        }
    }
    
    public void setUI(final ButtonUI ui) {
        super.setUI(ui);
    }
    
    public void setVerticalAlignment(final int n) {
        if (n == this.verticalAlignment) {
            return;
        }
        this.firePropertyChange("verticalAlignment", this.verticalAlignment, this.verticalAlignment = this.checkVerticalKey(n, "verticalAlignment"));
        this.repaint();
    }
    
    public void setVerticalTextPosition(final int n) {
        if (n == this.verticalTextPosition) {
            return;
        }
        this.firePropertyChange("verticalTextPosition", this.verticalTextPosition, this.verticalTextPosition = this.checkVerticalKey(n, "verticalTextPosition"));
        this.repaint();
    }
    
    public void updateUI() {
    }
    
    protected class ButtonChangeListener implements ChangeListener, Serializable
    {
        public void stateChanged(final ChangeEvent changeEvent) {
            AbstractButton.this.fireStateChanged();
            AbstractButton.this.repaint();
        }
    }
    
    private class ForwardActionEvents implements ActionListener, Serializable
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            AbstractButton.this.fireActionPerformed(actionEvent);
        }
    }
    
    private class ForwardItemEvents implements ItemListener, Serializable
    {
        public void itemStateChanged(final ItemEvent itemEvent) {
            AbstractButton.this.fireItemStateChanged(itemEvent);
        }
    }
    
    protected abstract class AccessibleAbstractButton extends AccessibleJComponent implements AccessibleAction, AccessibleValue
    {
        public boolean doAccessibleAction(final int n) {
            if (n == 0) {
                AbstractButton.this.doClick();
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
                return new String("click");
            }
            return null;
        }
        
        public String getAccessibleName() {
            if (super.accessibleName != null) {
                return super.accessibleName;
            }
            if (AbstractButton.this.getText() == null) {
                return super.getAccessibleName();
            }
            return AbstractButton.this.getText();
        }
        
        public AccessibleStateSet getAccessibleStateSet() {
            final AccessibleStateSet accessibleStateSet = super.getAccessibleStateSet();
            if (AbstractButton.this.getModel().isArmed()) {
                accessibleStateSet.add(AccessibleState.ARMED);
            }
            if (AbstractButton.this.hasFocus()) {
                accessibleStateSet.add(AccessibleState.FOCUSED);
            }
            if (AbstractButton.this.getModel().isPressed()) {
                accessibleStateSet.add(AccessibleState.PRESSED);
            }
            if (AbstractButton.this.isSelected()) {
                accessibleStateSet.add(AccessibleState.CHECKED);
            }
            return accessibleStateSet;
        }
        
        public AccessibleValue getAccessibleValue() {
            return this;
        }
        
        public Number getCurrentAccessibleValue() {
            if (AbstractButton.this.isSelected()) {
                return new Integer(1);
            }
            return new Integer(0);
        }
        
        public Number getMaximumAccessibleValue() {
            return new Integer(1);
        }
        
        public Number getMinimumAccessibleValue() {
            return new Integer(0);
        }
        
        public boolean setCurrentAccessibleValue(final Number n) {
            if (n instanceof Integer) {
                if (n.intValue() == 0) {
                    AbstractButton.this.setSelected(false);
                }
                else {
                    AbstractButton.this.setSelected(true);
                }
                return true;
            }
            return false;
        }
    }
}
