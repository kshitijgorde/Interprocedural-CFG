// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.control;

import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Frame;
import COM.objectspace.jgl.Array;
import borland.jbcl.util.ActionMulticaster;
import java.awt.Panel;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.Dialog;

public class ButtonDialog extends Dialog implements ActionListener, KeyListener
{
    public static final String NONE_COMMAND = "none";
    public static final String OK_COMMAND = "ok";
    public static final String YES_COMMAND = "yes";
    public static final String NO_COMMAND = "no";
    public static final String CANCEL_COMMAND = "cancel";
    public static final String DONE_COMMAND = "done";
    public static final String HELP_COMMAND = "help";
    public static final String APPLY_COMMAND = "apply";
    public static final String NEXT_COMMAND = "next";
    public static final String PREVIOUS_COMMAND = "previous";
    public static final String DETAILS_COMMAND = "details";
    public static final int NONE = 0;
    public static final int OK = 1;
    public static final int YES = 2;
    public static final int NO = 4;
    public static final int CANCEL = 8;
    public static final int DONE = 16;
    public static final int HELP = 32;
    public static final int APPLY = 64;
    public static final int NEXT = 128;
    public static final int PREVIOUS = 256;
    public static final int DETAILS = 512;
    public static final int OK_CANCEL = 9;
    public static final int YES_NO = 6;
    public static final int YES_NO_CANCEL = 14;
    public static final int OK_CANCEL_APPLY = 73;
    public static final int APPLY_DONE = 80;
    public static final int ALL = 1023;
    protected Panel buttonPanel;
    protected ActionMulticaster actionMulticaster;
    protected int buttonSet;
    protected boolean centered;
    protected boolean enterOK;
    protected boolean escapeCancel;
    protected ButtonDescriptor[] buttonDescriptors;
    protected String[] labels;
    protected Panel buttonPanelHolder;
    protected int buttonAlignment;
    protected ButtonDescriptor result;
    
    protected static final ButtonDescriptor[] buttonSetToButtonDescriptors(final int buttonSet) {
        final Array array = new Array();
        if ((buttonSet & 0x100) != 0x0) {
            array.add(new ButtonDescriptor("previous", Res.getString(11), 256, false));
        }
        if ((buttonSet & 0x80) != 0x0) {
            array.add(new ButtonDescriptor("next", Res.getString(10), 128, false));
        }
        if ((buttonSet & 0x200) != 0x0) {
            array.add(new ButtonDescriptor("details", Res.getString(12), 512, false));
        }
        if ((buttonSet & 0x1) != 0x0) {
            array.add(new ButtonDescriptor("ok", Res.getString(0), 1, true));
        }
        if ((buttonSet & 0x2) != 0x0) {
            array.add(new ButtonDescriptor("yes", Res.getString(2), 2, true));
        }
        if ((buttonSet & 0x4) != 0x0) {
            array.add(new ButtonDescriptor("no", Res.getString(4), 4, true));
        }
        if ((buttonSet & 0x8) != 0x0) {
            array.add(new ButtonDescriptor("cancel", Res.getString(6), 8, true));
        }
        if ((buttonSet & 0x10) != 0x0) {
            array.add(new ButtonDescriptor("done", Res.getString(9), 16, true));
        }
        if ((buttonSet & 0x20) != 0x0) {
            array.add(new ButtonDescriptor("help", Res.getString(7), 32, false));
        }
        if ((buttonSet & 0x40) != 0x0) {
            array.add(new ButtonDescriptor("apply", Res.getString(8), 64, false));
        }
        final ButtonDescriptor[] list = new ButtonDescriptor[array.size()];
        array.copyTo(list);
        return list;
    }
    
    protected ButtonDialog(final Frame frame, final String title, final boolean modal, final Component centerPanel, final Panel buttonPanel, final ButtonDescriptor[] buttonDescriptors) {
        super(frame, title, modal);
        this.centered = true;
        this.enterOK = true;
        this.escapeCancel = true;
        this.buttonAlignment = 1;
        this.buttonPanel = buttonPanel;
        if (buttonPanel == null) {
            (this.buttonPanelHolder = new Panel()).setLayout(new FlowLayout(1));
            (this.buttonPanel = new Panel()).setLayout(new GridLayout(1, 0, 6, 0));
            this.buttonPanelHolder.add(this.buttonPanel);
        }
        if (centerPanel != null) {
            this.setLayout(new BorderLayout());
            this.setBackground(SystemColor.control);
            this.add(centerPanel, "Center");
            if (this.buttonPanelHolder != null) {
                this.add(this.buttonPanelHolder, "South");
            }
            else {
                this.add(this.buttonPanel, "South");
            }
        }
        this.buttonSet = -1;
        this.setButtonSet(buttonDescriptors);
        this.listenForKeys(this);
        this.enableEvents(64L);
    }
    
    protected ButtonDialog(final Frame frame, final String title, final Component centerPanel, final int buttonSet) {
        this(frame, title, true, centerPanel, null, buttonSetToButtonDescriptors(buttonSet));
    }
    
    public ButtonDialog(final Frame frame, final String title, final Component centerPanel) {
        this(frame, title, centerPanel, 1);
    }
    
    protected void setCenterPanel(final Component centerPanel) {
        if (centerPanel != null) {
            this.setLayout(new BorderLayout());
            this.setBackground(SystemColor.control);
            this.add(centerPanel, "Center");
            if (this.buttonPanelHolder != null) {
                this.add(this.buttonPanelHolder, "South");
            }
            else {
                this.add(this.buttonPanel, "South");
            }
            this.listenForKeys(this);
        }
    }
    
    protected ButtonDialog(final Frame frame, final String title) {
        this(frame, title, null, 1);
    }
    
    protected ButtonDialog(final Frame frame) {
        this(frame, "", null, 1);
    }
    
    public void setButtonSet(final int bs) {
        if ((bs & 0xFFFFFC00) != 0x0) {
            throw new IllegalArgumentException(Res.format(33, new String[] { Integer.toString(bs & 0xFFFFFC00) }));
        }
        if (bs != this.buttonSet) {
            this.buttonSet = bs;
            this.setButtonSet(buttonSetToButtonDescriptors(bs));
        }
    }
    
    private void setButtonSet(final ButtonDescriptor[] buttonDescriptors) {
        this.buttonDescriptors = buttonDescriptors;
        this.buttonPanel.removeAll();
        for (int i = 0; i < buttonDescriptors.length; ++i) {
            final Button b = new Button();
            (buttonDescriptors[i].button = b).setActionCommand(buttonDescriptors[i].command);
            b.addActionListener(this);
            this.buttonPanel.add(b);
        }
        this.setDefaultLabels();
        this.setupButtonLabels();
    }
    
    public int getButtonSet() {
        return this.buttonSet;
    }
    
    public void setCentered(final boolean c) {
        this.centered = c;
    }
    
    public boolean isCentered() {
        return this.centered;
    }
    
    public void setEnterOK(final boolean e) {
        this.enterOK = e;
    }
    
    public boolean isEnterOK() {
        return this.enterOK;
    }
    
    public void setEscapeCancel(final boolean c) {
        this.escapeCancel = c;
    }
    
    public boolean isEscapeCancel() {
        return this.escapeCancel;
    }
    
    public void setButtonAlignment(final int alignment) {
        if (alignment == this.buttonAlignment || this.buttonPanelHolder == null) {
            return;
        }
        final FlowLayout flow = (FlowLayout)this.buttonPanelHolder.getLayout();
        if (alignment == 0 || alignment == 1 || alignment == 2) {
            flow.setAlignment(alignment);
            this.buttonAlignment = alignment;
            this.invalidate();
            this.repaint(100L);
            return;
        }
        throw new IllegalArgumentException();
    }
    
    public int getButtonAlignment() {
        return this.buttonAlignment;
    }
    
    public synchronized void addActionListener(final ActionListener l) {
        if (this.actionMulticaster == null) {
            this.actionMulticaster = new ActionMulticaster();
        }
        this.actionMulticaster.add(l);
    }
    
    public synchronized void removeActionListener(final ActionListener l) {
        if (this.actionMulticaster != null) {
            this.actionMulticaster.remove(l);
        }
    }
    
    protected void processActionEvent(final ActionEvent e) {
        this.result = this.buttonFromCommand(e.getActionCommand());
        if (this.actionMulticaster != null) {
            try {
                this.actionMulticaster.dispatch(e);
            }
            catch (Exception ex) {
                if (this.result != null && this.result.closeDialog) {
                    return;
                }
            }
        }
        if ((this.result != null && this.result.closeDialog) || e.getActionCommand().equals("cancel")) {
            this.setVisible(false);
            this.dispose();
        }
    }
    
    public void setLabels(final String[] l) {
        if (l != null) {
            for (int i = 0; i < this.labels.length && i < l.length; ++i) {
                this.labels[i] = l[i];
            }
        }
        else {
            this.setDefaultLabels();
        }
        this.setupButtonLabels();
    }
    
    private void setDefaultLabels() {
        this.labels = new String[this.buttonDescriptors.length];
        for (int i = 0; i < this.labels.length; ++i) {
            this.labels[i] = this.buttonDescriptors[i].label;
        }
    }
    
    protected void setupButtonLabels() {
        if (this.labels.length == this.buttonDescriptors.length) {
            for (int i = 0; i < this.labels.length; ++i) {
                this.buttonDescriptors[i].button.setLabel(this.labels[i]);
            }
        }
        else {
            for (int i = 0; i < this.buttonPanel.getComponentCount(); ++i) {
                ((Button)this.buttonPanel.getComponent(i)).setLabel(this.labels[i]);
            }
        }
    }
    
    public String[] getLabels() {
        return this.labels;
    }
    
    public void setResult(final int id) {
        this.result = this.buttonFromID(id);
    }
    
    public int getResult() {
        return (this.result != null) ? this.result.getID() : 0;
    }
    
    public ButtonDescriptor buttonFromID(final int id) {
        for (int i = 0; i < this.buttonDescriptors.length; ++i) {
            if (this.buttonDescriptors[i].id == id) {
                return this.buttonDescriptors[i];
            }
        }
        return null;
    }
    
    public ButtonDescriptor buttonFromCommand(final String actionCommand) {
        for (int i = 0; i < this.buttonDescriptors.length; ++i) {
            if (this.buttonDescriptors[i].command.equals(actionCommand)) {
                return this.buttonDescriptors[i];
            }
        }
        return null;
    }
    
    public void enableButton(final String actionCommand, final boolean enableState) {
        final ButtonDescriptor bd = this.buttonFromCommand(actionCommand);
        if (bd != null) {
            bd.button.setEnabled(enableState);
        }
    }
    
    public void show() {
        this.result = null;
        this.pack();
        if (this.centered) {
            this.centerOnScreen();
        }
        else {
            this.assureOnScreen();
        }
        super.show();
    }
    
    public void actionPerformed(final ActionEvent e) {
        this.processActionEvent(e);
    }
    
    public Dimension getPreferredSize() {
        final Dimension ps = super.getPreferredSize();
        return ps;
    }
    
    protected void processWindowEvent(final WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == 201) {
            this.processActionEvent(new ActionEvent(this, 1001, "cancel"));
        }
        else if (e.getID() == 200 && this.getComponentCount() > 0) {
            this.getComponent(this.getComponentCount() - 1).transferFocus();
        }
    }
    
    protected void centerOnScreen() {
        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension s = this.getSize();
        this.setLocation((d.width - s.width) / 2, (d.height - s.height) / 2);
    }
    
    protected void assureOnScreen() {
        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        final Rectangle b = this.getBounds();
        if (b == null) {
            return;
        }
        if (b.x + b.width > d.width || b.y + b.height > d.height) {
            if (b.x + b.width > d.width) {
                final Rectangle rectangle = b;
                rectangle.x -= b.x + b.width - d.width;
            }
            if (b.y + b.height > d.height) {
                final Rectangle rectangle2 = b;
                rectangle2.y -= b.y + b.height - d.height;
            }
            this.setLocation(b.x, b.y);
        }
    }
    
    protected void listenForKeys(final Component comp) {
        comp.removeKeyListener(this);
        comp.addKeyListener(this);
        if (comp instanceof Container) {
            final Container c = (Container)comp;
            for (int count = c.getComponentCount(), i = 0; i < count; ++i) {
                this.listenForKeys(c.getComponent(i));
            }
        }
    }
    
    public void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == 27 && this.escapeCancel && (e.getModifiers() & 0xB) == 0x0) {
            this.processActionEvent(new ActionEvent(e.getSource(), 1001, "cancel"));
        }
        else if (e.getKeyCode() == 10 && (e.getModifiers() & 0x9) == 0x0) {
            final boolean isCtrl = (e.getModifiers() & 0x2) != 0x0;
            final boolean needsCtrl = !this.enterOK || e.getSource() instanceof TextArea;
            if (isCtrl == needsCtrl) {
                this.processActionEvent(new ActionEvent(e.getSource(), 1001, "ok"));
            }
        }
        else if (e.getKeyCode() == 112 && (e.getModifiers() & 0xB) == 0x0) {
            this.processActionEvent(new ActionEvent(e.getSource(), 1001, "help"));
        }
    }
    
    public void keyTyped(final KeyEvent e) {
    }
    
    public void keyReleased(final KeyEvent e) {
    }
}
