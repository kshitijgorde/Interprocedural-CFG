import java.awt.event.ComponentEvent;
import java.awt.Label;
import java.awt.GridLayout;
import java.util.StringTokenizer;
import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.TextField;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Font;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.WindowListener;
import java.util.Stack;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ComponentListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class MessageBox extends Dialog implements ActionListener, KeyListener, ComponentListener
{
    private String lastButton;
    private String okString;
    private String cancelString;
    private Frame parent;
    private Container buttons;
    private Container mainPanel;
    private Component okButton;
    private GridBagConstraints bagCons;
    private Component focusComp;
    public boolean isSemiModal;
    private boolean inRow;
    private boolean closed;
    private boolean autoSize;
    private Stack stack;
    public static final Object EXPAND;
    public static final Object ITEM;
    
    public MessageBox(final Frame frame, final String s, final String s2) {
        this(frame, s, s2, 0);
    }
    
    public MessageBox(final Frame frame, final String s, final String s2, final int n) {
        this(frame, s, labelComponent(s2), n);
    }
    
    public MessageBox(final Frame frame, final String s, final int n) {
        this(frame, s, (Component)null, n);
    }
    
    public MessageBox(final Frame frame, final String title, final Component component, final int n) {
        super((frame == null) ? OmegaSystem.context.getMainFrame() : frame, title);
        this.lastButton = "";
        this.okString = "Ok";
        this.cancelString = "Cancel";
        this.isSemiModal = false;
        this.inRow = false;
        this.closed = false;
        this.autoSize = true;
        this.stack = new Stack();
        this.addComponentListener(this);
        this.parent = (Frame)this.getParent();
        this.addWindowListener(new WindowListenerWithClose());
        this.addKeyListener(this);
        this.setResizable(false);
        this.setModal(true);
        this.setTitle(title);
        Font dialog_font = null;
        if (OmegaSystem.context != null) {
            dialog_font = OmegaSystem.context.dialog_font();
        }
        if (dialog_font != null) {
            this.setFont(dialog_font);
        }
        this.setLayout(new BorderLayout(8, 8));
        if (component == null) {
            this.add(this.mainPanel = new Panel(new GridBagLayout()), "Center");
        }
        else {
            this.add(component, "Center");
        }
        this.bagCons = new GridBagConstraints();
        this.add(this.buttons = new Panel(new FlowLayout(1, 4, 4)), "South");
        this.bagCons.gridx = 0;
        this.bagCons.gridy = 0;
        this.bagCons.insets = new Insets(4, 4, 4, 4);
        this.bagCons.weightx = 1.0;
        this.bagCons.anchor = 17;
        this.bagCons.fill = 2;
        if ((n & 0x4) != 0x0) {
            this.addButton("Yes", true);
        }
        if ((n & 0x1) != 0x0) {
            this.okButton = this.addButton("Ok", true);
        }
        if ((n & 0x8) != 0x0) {
            this.addButton("No", false);
        }
        if ((n & 0x2) != 0x0) {
            this.addButton("Cancel", false);
        }
    }
    
    public static final String showMessageBox(final String s, final String s2) {
        return showMessageBox(s, s2, 1);
    }
    
    public static final String showMessageBox(final String s, final String s2, final int n) {
        return new MessageBox(null, s, s2, n).showDialog();
    }
    
    private Component addButton(final String s, final boolean b) {
        final Component addButton = this.addButton(s);
        if (b) {
            this.okString = s;
        }
        else {
            this.cancelString = s;
        }
        return addButton;
    }
    
    public final Component addButton(final String s) {
        final Button button = new Button(s);
        button.addActionListener(this);
        button.addKeyListener(this);
        final Button button2 = button;
        this.buttons.add(button2);
        return button2;
    }
    
    protected final void addImpl(final Component focusComp, final Object o, final int n) {
        if (focusComp instanceof Button || focusComp instanceof Choice || focusComp instanceof Checkbox || focusComp instanceof TextField) {
            focusComp.addKeyListener(this);
            if (this.focusComp == null) {
                this.focusComp = focusComp;
            }
        }
        if (o == null || o == MessageBox.ITEM) {
            this.bagCons.weighty = 0.0;
            this.bagCons.fill = 2;
            if (!this.inRow) {
                this.bagCons.gridwidth = 0;
            }
            else {
                this.bagCons.gridwidth = 1;
            }
            this.mainPanel.add(focusComp, this.bagCons);
            final GridBagConstraints bagCons = this.bagCons;
            ++bagCons.gridx;
            if (!this.inRow) {
                this.nextRow();
            }
        }
        else if (o == MessageBox.EXPAND) {
            this.bagCons.weighty = 1.0;
            this.bagCons.gridwidth = 0;
            this.bagCons.fill = 1;
            this.nextRow();
            this.mainPanel.add(focusComp, this.bagCons);
            this.nextRow();
        }
        else {
            super.addImpl(focusComp, o, n);
        }
    }
    
    public final Component add(final String s, final Component component) {
        this.beginRow();
        this.add(s);
        this.add(component);
        this.endRow();
        return component;
    }
    
    public final Component add(final String s) {
        return this.add(labelComponent(s));
    }
    
    public final void beginRow() {
        if (!this.inRow) {
            this.nextRow();
        }
        this.stack.push(this.inRow);
        this.inRow = true;
    }
    
    public final void endRow() {
        if (!(this.inRow = this.stack.pop())) {
            this.nextRow();
        }
    }
    
    private void nextRow() {
        if (this.bagCons.gridx != 0) {
            this.bagCons.gridx = 0;
            final GridBagConstraints bagCons = this.bagCons;
            ++bagCons.gridy;
        }
    }
    
    public final void keyReleased(final KeyEvent keyEvent) {
    }
    
    public final void keyTyped(final KeyEvent keyEvent) {
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 27) {
            this.lastButton = this.cancelString;
            this.hide();
        }
        if (keyEvent.getKeyCode() == 10) {
            final Object source = keyEvent.getSource();
            if (source instanceof Button) {
                this.lastButton = ((Button)source).getActionCommand();
            }
            else {
                this.lastButton = this.okString;
            }
            this.hide();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("_ok_")) {
            this.lastButton = this.okString;
            this.closed = true;
        }
        else {
            this.lastButton = actionEvent.getActionCommand();
        }
        this.hide();
    }
    
    public final void show() {
        if (this.parent == null) {
            return;
        }
        if (this.getParent().isVisible()) {
            if (this.autoSize) {
                this.pack();
                final Point locationOnScreen = this.parent.getLocationOnScreen();
                final Dimension size = this.parent.getSize();
                final Dimension size2 = this.getSize();
                final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                locationOnScreen.translate((size.width - size2.width) / 2, (size.height - size2.height) / 2);
                if (locationOnScreen.x < 50) {
                    locationOnScreen.x = 50;
                }
                if (locationOnScreen.y < 50) {
                    locationOnScreen.y = 50;
                }
                if (locationOnScreen.x + size2.width > screenSize.width - 50) {
                    locationOnScreen.x = screenSize.width - 50 - size2.width;
                }
                if (locationOnScreen.y + size2.height > screenSize.height - 50) {
                    locationOnScreen.y = screenSize.height - 50 - size2.height;
                }
                this.setLocation(locationOnScreen);
            }
            super.show();
        }
        else {
            try {
                while (!this.closed) {
                    final AWTEvent nextEvent = Toolkit.getDefaultToolkit().getSystemEventQueue().getNextEvent();
                    final Object source = nextEvent.getSource();
                    if (((Component)source).getClass().getName().endsWith("AWTAutoShutdown")) {
                        this.closed = true;
                    }
                    this.autoDispatch(nextEvent);
                    this.autoDispatch(source);
                    if (source instanceof Component) {
                        ((Component)source).dispatchEvent(nextEvent);
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        this.dispose();
        try {
            this.finalize();
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    private void autoDispatch(final Object o) {
        try {
            o.getClass().getMethod("dispatch", (Class<?>[])null).invoke(o, (Object[])null);
        }
        catch (Exception ex) {}
    }
    
    public final String showDialog() {
        this.lastButton = this.cancelString;
        this.show();
        return this.lastButton;
    }
    
    public final void close() {
        this.closed = true;
        this.dispose();
    }
    
    public static final Component labelComponent(String firstUpperCase) {
        firstUpperCase = Utils.toFirstUpperCase(firstUpperCase);
        final StringTokenizer stringTokenizer = new StringTokenizer(firstUpperCase, "\n");
        Component component = null;
        Container container = null;
        while (stringTokenizer.hasMoreElements()) {
            if (component != null) {
                container = new Panel(new GridLayout(0, 1, 0, 0));
                container.add(component);
            }
            component = new Label((String)stringTokenizer.nextElement());
            if (container != null) {
                container.add(component);
            }
        }
        if (container != null) {
            return container;
        }
        if (component != null) {
            return component;
        }
        return new Label("");
    }
    
    public final void setTitle(final String s) {
        super.setTitle(Utils.toFirstUpperCase(s));
    }
    
    public final void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public final void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public final void componentResized(final ComponentEvent componentEvent) {
    }
    
    public final void componentShown(final ComponentEvent componentEvent) {
        if (this.focusComp != null) {
            this.focusComp.requestFocus();
        }
        else if (this.okButton != null) {
            this.okButton.requestFocus();
        }
    }
    
    static {
        EXPAND = new Object();
        ITEM = new Object();
    }
}
