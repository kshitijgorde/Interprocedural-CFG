// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.gui.lwcomp;

import java.awt.Point;
import java.awt.AWTEvent;
import com.objectbox.runner.gui.tree.Node;
import java.awt.event.WindowEvent;
import java.util.Enumeration;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import com.objectbox.runner.util.JBLogger;
import java.awt.event.ActionEvent;
import com.objectbox.runner.gui.JBee;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Component;
import java.util.Hashtable;
import com.objectbox.runner.util.MenuGridLayout;
import java.awt.Panel;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.Window;

public class JBPopupMenu extends Window implements OnActiveListener, ActionListener, WindowListener
{
    private Panel menupanel;
    private int nitems;
    private int itemheight;
    private MenuGridLayout grid;
    private Hashtable submenuhash;
    protected JBPopupMenu opensubmenu;
    protected JBPopupMenu parent;
    private Component comp;
    private boolean istoplevel;
    protected boolean dir;
    protected boolean focus;
    protected int maxwidth;
    final int textspace = 8;
    protected transient PopupItemSelectedListener aPopupItemSelectedListener;
    private static MenuCloser closerThread;
    protected boolean floating;
    protected boolean master;
    public static String platform;
    static Class class$java$awt$Window;
    
    public JBPopupMenu(final Frame frame) {
        super(frame);
        this.menupanel = new DoubleBufferPanel();
        this.nitems = 0;
        this.itemheight = 28;
        this.grid = new MenuGridLayout(0, 1, 0, 0);
        this.submenuhash = new Hashtable();
        this.opensubmenu = null;
        this.parent = null;
        this.comp = null;
        this.istoplevel = false;
        this.dir = true;
        this.focus = false;
        this.maxwidth = 50;
        this.aPopupItemSelectedListener = null;
        this.floating = false;
        this.master = false;
        this.initialize();
        this.setSize(0, 0);
    }
    
    public JBPopupMenu(final Frame frame, final Component component, final String s) {
        this(frame, component, s, false);
    }
    
    public JBPopupMenu(final Frame frame, final Component comp, final String name, final boolean istoplevel) {
        super(frame);
        this.menupanel = new DoubleBufferPanel();
        this.nitems = 0;
        this.itemheight = 28;
        this.grid = new MenuGridLayout(0, 1, 0, 0);
        this.submenuhash = new Hashtable();
        this.opensubmenu = null;
        this.parent = null;
        this.comp = null;
        this.istoplevel = false;
        this.dir = true;
        this.focus = false;
        this.maxwidth = 50;
        this.aPopupItemSelectedListener = null;
        this.floating = false;
        this.master = false;
        this.enableEvents(1007L);
        this.istoplevel = istoplevel;
        this.comp = comp;
        this.setName(name);
        this.setLayout(new BorderLayout(0, 0));
        this.add(this.menupanel, "Center");
        this.menupanel.setLayout(this.grid);
        this.setBackground(JBee.appcolor);
    }
    
    public JBPopupMenu(final Frame frame, final Component comp, final String name, final boolean istoplevel, final boolean floating) {
        super(frame);
        this.menupanel = new DoubleBufferPanel();
        this.nitems = 0;
        this.itemheight = 28;
        this.grid = new MenuGridLayout(0, 1, 0, 0);
        this.submenuhash = new Hashtable();
        this.opensubmenu = null;
        this.parent = null;
        this.comp = null;
        this.istoplevel = false;
        this.dir = true;
        this.focus = false;
        this.maxwidth = 50;
        this.aPopupItemSelectedListener = null;
        this.floating = false;
        this.master = false;
        this.enableEvents(1007L);
        this.istoplevel = istoplevel;
        this.comp = comp;
        this.floating = floating;
        this.setName(name);
        this.setLayout(new BorderLayout(0, 0));
        this.add(this.menupanel, "Center");
        this.menupanel.setLayout(this.grid);
        this.setBackground(JBee.appcolor);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.setVisible(false);
        for (JBPopupMenu jbPopupMenu = this.parent; jbPopupMenu != null; jbPopupMenu = jbPopupMenu.parent) {
            jbPopupMenu.setVisible(false);
        }
        try {
            final PopupItemSelectedEvent popupItemSelectedEvent = new PopupItemSelectedEvent(((FlatButton)actionEvent.getSource()).getUserObject());
            popupItemSelectedEvent.setComponent((Component)actionEvent.getSource());
            this.fireHandlePopupSelection(popupItemSelectedEvent);
        }
        catch (Throwable t) {
            JBLogger.log("Exception in JBPopupMenu::actionPerformed: " + t);
        }
    }
    
    public void addLogo(final String s) {
        final FlatButton flatButton = new FlatButton("");
        flatButton.setInset(new Dimension(0, 0));
        flatButton.setBackground(Color.black);
        flatButton.setImageResource("/images/beemenulogo.gif", 0);
        this.add(flatButton, "West");
        this.addMenuItem("Help", "/images/Question.gif");
        this.addMenuItem("Preferences", "/images/ListView.gif");
        this.addMenuItem("Feedback", "/images/mail.gif");
        this.addMenuItem("Kill all", "/images/die.gif");
        this.addMenuItem("Search and Admin", "/images/earth_small.gif");
        this.addSeparator();
    }
    
    public void addMenuItem(final String s) {
        final FlatButton flatButton = new FlatButton(s);
        flatButton.addActionListener(this);
        flatButton.setImageResource("/images/beesmile.gif", 3);
        flatButton.addOnActiveListener(this);
        this.maxwidth = Math.max(this.maxwidth, s.length() * 8);
        this.menupanel.add(flatButton);
        this.addone(0);
    }
    
    public void addMenuItem(final String s, final String s2) {
        final FlatButton flatButton = new FlatButton(s);
        flatButton.setImageResource(s2, 3);
        flatButton.addActionListener(this);
        flatButton.addOnActiveListener(this);
        this.maxwidth = Math.max(this.maxwidth, s.length() * 8);
        this.menupanel.add(flatButton);
        this.addone(0);
    }
    
    public void addMenuItem(final String s, final boolean enabled) {
        final FlatButton flatButton = new FlatButton(s);
        flatButton.setEnabled(enabled);
        flatButton.addActionListener(this);
        flatButton.setImageResource("/images/beesmile.gif", 3);
        flatButton.addOnActiveListener(this);
        this.maxwidth = Math.max(this.maxwidth, s.length() * 8);
        this.menupanel.add(flatButton);
        this.addone(0);
    }
    
    public void addMenuItemWithObject(final String s, final Object userObject) {
        final FlatButton flatButton = new FlatButton(s);
        flatButton.setUserObject(userObject);
        flatButton.setImageResource("/images/beesmile.gif", 3);
        flatButton.addActionListener(this);
        flatButton.addOnActiveListener(this);
        this.maxwidth = Math.max(this.maxwidth, s.length() * 8);
        this.menupanel.add(flatButton);
        this.addone(0);
    }
    
    public void addMenuItemWithObject(final String s, final Object userObject, final String s2) {
        final FlatButton flatButton = new FlatButton(s);
        flatButton.setUserObject(userObject);
        flatButton.setImageResource(s2, 3);
        flatButton.addActionListener(this);
        flatButton.addOnActiveListener(this);
        this.maxwidth = Math.max(this.maxwidth, s.length() * 8);
        this.menupanel.add(flatButton);
        this.addone(0);
    }
    
    private void addone(final int n) {
        ++this.nitems;
        this.maxwidth = 170;
        final int n2 = Toolkit.getDefaultToolkit().getScreenSize().height / this.itemheight;
        if (this.nitems > n2) {
            final int columns = this.nitems / n2 + 1;
            this.grid.setColumns(columns);
            this.setSize(new Dimension(columns * this.maxwidth, this.nitems * this.itemheight / columns));
            this.doLayout();
        }
        else {
            this.setSize(new Dimension(this.maxwidth, this.nitems * this.itemheight));
        }
    }
    
    public void addPopupItemSelectedListener(final PopupItemSelectedListener popupItemSelectedListener) {
        this.aPopupItemSelectedListener = PopupItemSelectedEventMulticaster.add(this.aPopupItemSelectedListener, popupItemSelectedListener);
    }
    
    public void addPopupMenu(final String s, final JBPopupMenu jbPopupMenu) throws Exception {
        if (jbPopupMenu == this) {
            throw new Exception("Cannot add this sub to itself");
        }
        jbPopupMenu.setParentMenu(this);
        final FlatButton locationComponent = new FlatButton(s);
        locationComponent.setIsSubmenu(true);
        locationComponent.setImageResource("/images/smallfolder.gif", 3);
        locationComponent.addOnActiveListener(this);
        jbPopupMenu.setLocationComponent(locationComponent);
        this.maxwidth = Math.max(this.maxwidth, s.length() * 8);
        this.menupanel.add(locationComponent);
        this.submenuhash.put(locationComponent, jbPopupMenu);
        this.addone(0);
    }
    
    public void addPopupMenu(final String s, final JBPopupMenu jbPopupMenu, final Object userObject) throws Exception {
        if (jbPopupMenu == this) {
            throw new Exception("Cannot add this sub to itself");
        }
        jbPopupMenu.setParentMenu(this);
        final FlatButton locationComponent = new FlatButton(s);
        locationComponent.setUserObject(userObject);
        locationComponent.setIsSubmenu(true);
        locationComponent.setImageResource("/images/smallfolder.gif", 3);
        locationComponent.addActionListener(this);
        locationComponent.addOnActiveListener(this);
        jbPopupMenu.setLocationComponent(locationComponent);
        this.maxwidth = Math.max(this.maxwidth, s.length() * 8);
        this.menupanel.add(locationComponent);
        this.submenuhash.put(locationComponent, jbPopupMenu);
        this.addone(0);
    }
    
    public void addSeparator() {
        this.addone(0);
        final LWSeparator lwSeparator = new LWSeparator();
        lwSeparator.setDirection(true);
        this.menupanel.add(lwSeparator);
    }
    
    public void cleanup() {
        this.removeNotify();
        final Enumeration<JBPopupMenu> elements = this.submenuhash.elements();
        while (elements.hasMoreElements()) {
            final JBPopupMenu jbPopupMenu = elements.nextElement();
            jbPopupMenu.cleanup();
            this.submenuhash.remove(jbPopupMenu);
        }
        this.submenuhash.clear();
        this.submenuhash = null;
        this.dispose();
    }
    
    private void connEtoC1(final WindowEvent windowEvent) {
        try {
            this.dispose();
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    public void deleteMenuItem(final Object o) {
        try {
            for (int i = 0; i < this.menupanel.getComponentCount(); ++i) {
                final Component component = this.menupanel.getComponent(i);
                if (component instanceof FlatButton) {
                    final FlatButton flatButton = (FlatButton)component;
                    final Object userObject = flatButton.getUserObject();
                    if (userObject != null && userObject == o) {
                        this.menupanel.remove(flatButton);
                        final JBPopupMenu jbPopupMenu = this.submenuhash.get(flatButton);
                        if (jbPopupMenu != null) {
                            jbPopupMenu.cleanup();
                            this.submenuhash.remove(flatButton);
                        }
                        this.removeone(0);
                        break;
                    }
                }
            }
        }
        catch (Throwable t) {
            JBLogger.log("Exception in JBPopupMenu::deleteMenuItem: " + t.toString());
        }
    }
    
    public Object detachMenuItem(final Object o) {
        Object o2 = null;
        try {
            final Node node = (Node)o;
            for (int i = 0; i < this.menupanel.getComponentCount(); ++i) {
                final Component component = this.menupanel.getComponent(i);
                if (component instanceof FlatButton) {
                    final FlatButton flatButton = (FlatButton)component;
                    final Object userObject = flatButton.getUserObject();
                    if (userObject != null && userObject == o) {
                        this.menupanel.remove(flatButton);
                        o2 = flatButton;
                        final JBPopupMenu jbPopupMenu = this.submenuhash.get(flatButton);
                        if (jbPopupMenu != null) {
                            o2 = jbPopupMenu;
                            this.submenuhash.remove(flatButton);
                        }
                        this.removeone(0);
                        break;
                    }
                }
            }
        }
        catch (Throwable t) {
            JBLogger.log("Exception in JBPopupMenu::detachMenuItem: " + t.toString());
        }
        return o2;
    }
    
    public void finalize() {
    }
    
    protected void fireHandlePopupSelection(final PopupItemSelectedEvent popupItemSelectedEvent) {
        if (this.aPopupItemSelectedListener == null) {
            return;
        }
        this.aPopupItemSelectedListener.handlePopupSelection(popupItemSelectedEvent);
    }
    
    public int getItemheight() {
        return this.itemheight;
    }
    
    public JBPopupMenu getParentMenu() {
        return this.parent;
    }
    
    private void handleException(final Throwable t) {
    }
    
    private void initConnections() {
        this.addWindowListener(this);
    }
    
    private void initialize() {
        this.setName("JBPopupMenu");
        this.setLayout(null);
        this.setSize(426, 240);
        this.initConnections();
    }
    
    public static void main(final String[] array) {
        try {
            final JBPopupMenu jbPopupMenu = new JBPopupMenu(new Frame());
            try {
                final Class<?> forName = Class.forName("com.ibm.uvm.abt.edit.WindowCloser");
                final Class[] array2 = { null };
                final int n = 0;
                Class class$java$awt$Window;
                if ((class$java$awt$Window = JBPopupMenu.class$java$awt$Window) == null) {
                    try {
                        class$java$awt$Window = (JBPopupMenu.class$java$awt$Window = Class.forName("java.awt.Window"));
                    }
                    catch (ClassNotFoundException ex) {
                        throw new NoClassDefFoundError(ex.getMessage());
                    }
                }
                array2[n] = class$java$awt$Window;
                forName.getConstructor((Class<?>[])array2).newInstance(jbPopupMenu);
            }
            catch (Throwable t) {}
            jbPopupMenu.setVisible(true);
        }
        catch (Throwable t2) {
            JBLogger.log("Exception occurred in main() of java.awt.Window");
        }
    }
    
    public void onActive(final OnActiveEvent onActiveEvent) {
        final JBPopupMenu opensubmenu = this.submenuhash.get(onActiveEvent.getSource());
        if (opensubmenu == null) {
            if (this.opensubmenu != null) {
                this.opensubmenu.setVisible(false);
                this.requestFocus();
            }
        }
        else {
            if (this.opensubmenu != null && this.opensubmenu != opensubmenu) {
                this.opensubmenu.setVisible(false);
                this.requestFocus();
            }
            (this.opensubmenu = opensubmenu).setVisible(true);
        }
    }
    
    public void processEvent(final AWTEvent awtEvent) {
        if (JBee.OS_type == 1) {
            switch (awtEvent.getID()) {
                case 1004: {
                    JBLogger.log("got focus" + System.getProperty("os.name"));
                    this.focus = true;
                    if (JBPopupMenu.closerThread != null) {
                        JBPopupMenu.closerThread.isFresh = false;
                    }
                    break;
                }
                case 1005: {
                    JBLogger.log("lost focus");
                    this.focus = false;
                    if (JBPopupMenu.closerThread != null) {
                        JBPopupMenu.closerThread.isFresh = false;
                        break;
                    }
                    break;
                }
            }
        }
        else {
            switch (awtEvent.getID()) {
                case 205: {
                    JBLogger.log("win activated");
                    this.focus = true;
                    if (JBPopupMenu.closerThread != null) {
                        JBPopupMenu.closerThread.isFresh = false;
                    }
                    break;
                }
                case 206: {
                    JBLogger.log("win deactivated");
                    this.focus = false;
                    if (JBPopupMenu.closerThread != null) {
                        JBPopupMenu.closerThread.isFresh = false;
                        break;
                    }
                    break;
                }
            }
        }
        super.processEvent(awtEvent);
    }
    
    private void removeone(final int n) {
        --this.nitems;
        this.maxwidth = 170;
        final int columns = this.nitems / (Toolkit.getDefaultToolkit().getScreenSize().height / this.itemheight) + 1;
        this.grid.setColumns(columns);
        this.setSize(new Dimension(columns * this.maxwidth, this.nitems * this.itemheight / columns));
        this.doLayout();
    }
    
    public void removePopupItemSelectedListener(final PopupItemSelectedListener popupItemSelectedListener) {
        this.aPopupItemSelectedListener = PopupItemSelectedEventMulticaster.remove(this.aPopupItemSelectedListener, popupItemSelectedListener);
    }
    
    public void setFocus(final boolean focus) {
        this.focus = focus;
    }
    
    public void setItemheight(final int itemheight) {
        this.itemheight = itemheight;
    }
    
    public void setItemText(final String label, final Object o) {
        try {
            for (int i = 0; i < this.menupanel.getComponentCount(); ++i) {
                final Component component = this.menupanel.getComponent(i);
                if (component instanceof FlatButton) {
                    final FlatButton flatButton = (FlatButton)component;
                    final Object userObject = flatButton.getUserObject();
                    if (userObject != null && userObject == o) {
                        flatButton.setLabel(label);
                    }
                }
            }
        }
        catch (Throwable t) {
            JBLogger.log("JBPopupMenu::setItemText: " + t.toString());
        }
    }
    
    public void setLocationComponent(final Component comp) {
        this.comp = comp;
    }
    
    public void setParentMenu(final JBPopupMenu parent) {
        this.parent = parent;
    }
    
    public void setRelativeLocation(final Point location) {
        if (this.comp != null) {
            final Point locationOnScreen = this.comp.getLocationOnScreen();
            this.setLocation(new Point(locationOnScreen.x + location.x, locationOnScreen.y + location.y));
        }
        else {
            this.setLocation(location);
        }
    }
    
    public void setText(final String label) {
        if (this.comp instanceof FlatButton) {
            ((FlatButton)this.comp).setLabel(label);
        }
    }
    
    public void setVisible(final boolean b) {
        if (!b) {
            if (this.opensubmenu != null) {
                this.opensubmenu.setVisible(false);
            }
            super.setVisible(b);
        }
        else {
            if (this.parent != null) {
                this.dir = this.parent.dir;
            }
            if (!this.floating) {
                final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
                final int height = Toolkit.getDefaultToolkit().getScreenSize().height;
                final int height2 = this.getSize().height;
                final int width2 = this.getSize().width;
                int n = this.dir ? (this.comp.getLocationOnScreen().x + this.comp.getSize().width) : (this.comp.getLocationOnScreen().x - width2);
                int y = this.comp.getLocationOnScreen().y;
                if (y + height2 > height) {
                    y -= y + height2 - height;
                }
                if (n + width2 > width) {
                    n = this.comp.getLocationOnScreen().x - width2;
                    if (this.parent == null) {
                        this.dir = false;
                    }
                }
                else if (this.parent == null) {
                    this.dir = true;
                }
                this.setLocation(n, y);
            }
            this.requestFocus();
            super.setVisible(b);
            if (this.istoplevel) {
                this.startCloser();
            }
        }
    }
    
    public void startCloser() {
        JBPopupMenu.closerThread = null;
        JBLogger.log("starter");
        (JBPopupMenu.closerThread = new MenuCloser(this)).start();
    }
    
    public void stopCloser() {
        JBLogger.log("stopper");
        if (JBPopupMenu.closerThread != null) {
            JBPopupMenu.closerThread.isRunning = false;
            JBPopupMenu.closerThread = null;
        }
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        if (windowEvent.getSource() == this) {
            this.connEtoC1(windowEvent);
        }
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
}
