// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.geneo;

import java.awt.event.AdjustmentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.MenuComponent;
import java.applet.AppletContext;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.MenuShortcut;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.Scrollbar;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Frame;

class MainFrame extends Frame implements WindowListener, ActionListener, KeyListener, AdjustmentListener, Runnable
{
    private Globals globals;
    private Geneo geneo;
    private TreeCanvas canvas;
    private Scrollbar hbar;
    private Scrollbar vbar;
    private MenuBar mb;
    private Menu sm;
    private Menu vm;
    private Menu gm;
    private Menu bm;
    private Menu fm;
    private Menu hm;
    private MenuItem find;
    private MenuItem zoomIn;
    private MenuItem zoomOut;
    private MenuItem details;
    private MenuItem center;
    private MenuItem primary;
    private MenuItem viewAll;
    private MenuItem back;
    private MenuItem forward;
    private MenuItem contents;
    private MenuItem about;
    
    public MainFrame(final Geneo geneo, final Globals globals, final int n, final int n2) {
        super("InterneTree");
        this.globals = globals;
        this.geneo = geneo;
        this.setForeground(this.globals.foregroundColor);
        this.setBackground(this.globals.backgroundColor);
        (this.mb = new MenuBar()).add(this.sm = new Menu("Search"));
        this.mb.add(this.vm = new Menu("View"));
        this.mb.add(this.gm = new Menu("Go"));
        this.mb.add(this.hm = new Menu("Help"));
        this.mb.setHelpMenu(this.hm);
        this.sm.add(this.find = new MenuItem("Find Person...", new MenuShortcut(70)));
        this.vm.add(this.zoomIn = new MenuItem("Zoom In", new MenuShortcut(73)));
        this.vm.add(this.zoomOut = new MenuItem("Zoom Out", new MenuShortcut(79)));
        this.vm.add(this.details = new MenuItem("Details", new MenuShortcut(68)));
        this.vm.add(this.center = new MenuItem("Center Tree", new MenuShortcut(36)));
        this.vm.add(this.primary = new MenuItem("Change Primary Individual", new MenuShortcut(80)));
        this.viewAll = null;
        this.gm.add(this.back = new MenuItem("<-Back", new MenuShortcut(37)));
        this.gm.add(this.forward = new MenuItem("Forward->", new MenuShortcut(39)));
        this.hm.add(this.contents = new MenuItem("Contents", new MenuShortcut(72)));
        this.hm.add(this.about = new MenuItem("About", new MenuShortcut(65)));
        this.setMenuBar(this.mb);
        this.canvas = new TreeCanvas(geneo, globals, this);
        this.hbar = new Scrollbar(0);
        this.vbar = new Scrollbar(1);
        this.setLayout(new BorderLayout(0, 0));
        this.add("Center", this.canvas);
        this.add("South", this.hbar);
        this.add("East", this.vbar);
        this.setSize(n, n2);
        this.addWindowListener(this);
        this.addKeyListener(this);
        this.find.addActionListener(this);
        this.zoomIn.addActionListener(this);
        this.zoomOut.addActionListener(this);
        this.details.addActionListener(this);
        this.center.addActionListener(this);
        this.primary.addActionListener(this);
        this.back.addActionListener(this);
        this.forward.addActionListener(this);
        this.contents.addActionListener(this);
        this.about.addActionListener(this);
        this.hbar.addAdjustmentListener(this);
        this.vbar.addAdjustmentListener(this);
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setLocation((screenSize.width - n) / 2, (screenSize.height - n2) / 2);
        this.requestFocus();
        this.show();
        this.toFront();
    }
    
    public void updateContext(final AppletContext appletContext) {
        this.canvas.updateContext(appletContext);
    }
    
    public void find() {
        new Thread(this, "MainPanel Find Thread").start();
    }
    
    public void run() {
        this.canvas.find();
    }
    
    public void zoomIn() {
        this.canvas.zoomIn();
    }
    
    public void zoomOut() {
        this.canvas.zoomOut();
    }
    
    public void home() {
        this.canvas.home();
    }
    
    public void setPrimary(final int primary) {
        this.canvas.setPrimary(primary);
    }
    
    public void setPrimary() {
        this.canvas.setPrimary();
    }
    
    public void back() {
        this.canvas.back();
    }
    
    public void forward() {
        this.canvas.forward();
    }
    
    public void notifyDone() {
        this.canvas.notifyDone();
    }
    
    public void passwordRequired(final boolean b) {
        if (b && this.viewAll == null) {
            this.viewAll = new MenuItem("View All", new MenuShortcut(86));
            this.vm.add(this.viewAll);
            this.viewAll.addActionListener(this);
            return;
        }
        if (!b && this.viewAll != null) {
            this.vm.remove(this.viewAll);
            this.viewAll = null;
        }
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.geneo.closeInstance();
        this.dispose();
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final MenuItem menuItem = (MenuItem)actionEvent.getSource();
        if (menuItem == this.find) {
            this.find();
            return;
        }
        if (menuItem == this.zoomIn) {
            this.zoomIn();
            return;
        }
        if (menuItem == this.zoomOut) {
            this.zoomOut();
            return;
        }
        if (menuItem == this.details) {
            this.canvas.showDetails();
            return;
        }
        if (menuItem == this.center) {
            this.home();
            return;
        }
        if (menuItem == this.primary) {
            this.setPrimary();
            return;
        }
        if (menuItem == this.viewAll) {
            this.canvas.viewAll();
            return;
        }
        if (menuItem == this.back) {
            this.back();
            return;
        }
        if (menuItem == this.forward) {
            this.forward();
            return;
        }
        if (menuItem == this.contents) {
            this.canvas.helpContents();
            return;
        }
        if (menuItem == this.about) {
            this.canvas.helpAbout();
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        this.canvas.keyPressed(keyEvent);
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        final Scrollbar scrollbar = (Scrollbar)adjustmentEvent.getAdjustable();
        if (scrollbar == this.hbar) {
            switch (adjustmentEvent.getAdjustmentType()) {
                case 2: {
                    this.canvas.incLeft();
                }
                case 1: {
                    this.canvas.incRight();
                }
                case 3: {
                    this.canvas.pageLeft();
                }
                case 4: {
                    this.canvas.pageRight();
                }
                case 5: {
                    this.canvas.setHorz(adjustmentEvent.getValue());
                }
            }
        }
        else if (scrollbar == this.vbar) {
            switch (adjustmentEvent.getAdjustmentType()) {
                case 2: {
                    this.canvas.incUp();
                }
                case 1: {
                    this.canvas.incDown();
                }
                case 3: {
                    this.canvas.pageUp();
                }
                case 4: {
                    this.canvas.pageDown();
                }
                case 5: {
                    this.canvas.setVert(adjustmentEvent.getValue());
                }
            }
        }
    }
    
    public void adjustScrollBars(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        this.hbar.setValues(n, n2, n3, n4);
        this.vbar.setValues(n5, n6, n7, n8);
    }
}
