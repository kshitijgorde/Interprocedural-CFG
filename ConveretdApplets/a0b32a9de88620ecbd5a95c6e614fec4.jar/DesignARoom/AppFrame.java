// 
// Decompiled by Procyon v0.5.30
// 

package DesignARoom;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Event;
import java.awt.MenuBar;
import java.awt.Menu;
import java.awt.event.WindowListener;
import java.awt.Toolkit;
import java.awt.MenuItem;
import java.awt.Dimension;
import java.awt.Frame;

public class AppFrame extends Frame
{
    AppWebApplet aWA;
    Dimension d;
    MenuItem newMI;
    MenuItem closeMI;
    MenuItem closeallMI;
    MenuItem saveMI;
    MenuItem saveasMI;
    MenuItem saveallMI;
    MenuItem printMI;
    MenuItem smallerMI;
    MenuItem largerMI;
    
    public AppFrame(final String s) {
        super(s);
        this.d = Toolkit.getDefaultToolkit().getScreenSize();
        this.newMI = new MenuItem("New              Alt+N");
        this.closeMI = new MenuItem("Close");
        this.closeallMI = new MenuItem("Close All");
        this.saveMI = new MenuItem("Save             Alt+S");
        this.saveasMI = new MenuItem("Save As...");
        this.saveallMI = new MenuItem("Save All");
        this.printMI = new MenuItem("Print...           Alt+P");
        this.smallerMI = new MenuItem("Smaller");
        this.largerMI = new MenuItem("Larger");
        this.setResizable(false);
        this.addWindowListener(new WindowCloser());
        final Menu menu = new Menu("File");
        menu.add(this.newMI);
        menu.add(new MenuItem("Open..."));
        menu.add(this.closeMI);
        menu.add(this.closeallMI);
        menu.addSeparator();
        menu.add(this.saveMI);
        menu.add(this.saveasMI);
        menu.add(this.saveallMI);
        menu.addSeparator();
        menu.add(this.printMI);
        menu.addSeparator();
        menu.add(new MenuItem("Exit"));
        final Menu menu2 = new Menu("Resize");
        menu2.add(this.smallerMI);
        menu2.add(this.largerMI);
        final Menu menu3 = new Menu("Help");
        menu3.add(new MenuItem("Instructions"));
        menu3.addSeparator();
        menu3.add(new MenuItem("About..."));
        final MenuBar menuBar = new MenuBar();
        menuBar.add(menu);
        menuBar.add(menu2);
        menuBar.add(menu3);
        this.setMenuBar(menuBar);
        this.saveMI.disable();
        this.resize(570, 600);
        this.move((this.d.width - this.bounds().width) / 2, (this.d.height - this.bounds().height) / 2);
    }
    
    public boolean action(final Event event, final Object o) {
        return event.target instanceof MenuItem && this.handleMenuChoice(event, (String)o);
    }
    
    boolean handleMenuChoice(final Event event, final String s) {
        if (event.target == this.newMI) {
            this.aWA.New();
        }
        if (s.equals("Open...")) {
            this.aWA.open();
        }
        if (event.target == this.saveMI) {
            this.aWA.save(this.aWA.rmCurr.getSelectedIndex());
        }
        if (s.equals("Save As...")) {
            this.aWA.saveAs(this.aWA.rmCurr.getSelectedIndex());
        }
        if (s.equals("Save All")) {
            this.aWA.saveAll();
        }
        if (s.equals("Close")) {
            this.aWA.close(this.aWA.rmCurr.getSelectedIndex());
        }
        if (s.equals("Close All")) {
            this.aWA.closeAll();
        }
        if (event.target == this.printMI) {
            this.aWA.print();
        }
        if (s.equals("Exit") && this.aWA.closeAll()) {
            this.dispose();
            System.exit(0);
        }
        if (s.equals("Smaller") && this.bounds().width < this.d.width && this.bounds().height < this.d.height) {
            this.largerMI.enable();
            this.aWA.aWC.resetSize(this.aWA.aWC.getApWidth() - 50, this.aWA.aWC.getApHeight() - 75);
            if (this.bounds().height == 600) {
                this.resize(this.aWA.aWC.getApWidth() + 290, this.aWA.aWC.getApHeight() + 160 + 75);
                this.resize(100, 100);
                this.resize(this.aWA.aWC.getApWidth() + 290, this.aWA.aWC.getApHeight() + 160 + 75);
            }
            else {
                this.resize(this.aWA.aWC.getApWidth() + 290, this.aWA.aWC.getApHeight() + 160);
                this.resize(100, 100);
                this.resize(this.aWA.aWC.getApWidth() + 290, this.aWA.aWC.getApHeight() + 160);
            }
            this.move((this.d.width - this.bounds().width) / 2, (this.d.height - this.bounds().height) / 2);
            if (this.bounds().width <= 520) {
                this.smallerMI.disable();
            }
        }
        if (s.equals("Larger") && this.bounds().width < this.d.width - 1 && this.bounds().height < this.d.height - 1) {
            if (this.bounds().width >= 520) {
                this.smallerMI.enable();
            }
            this.aWA.aWC.resetSize(this.aWA.aWC.getApWidth() + 50, this.aWA.aWC.getApHeight() + 75);
            this.resize(Math.min(this.d.width - 1, this.aWA.aWC.getApWidth() + 290), Math.min(this.d.height - 1, this.aWA.aWC.getApHeight() + 160));
            this.resize(100, 100);
            this.resize(Math.min(this.d.width - 1, this.aWA.aWC.getApWidth() + 290), Math.min(this.d.height - 1, this.aWA.aWC.getApHeight() + 160));
            this.move(Math.max(0, (this.d.width - this.bounds().width) / 2), Math.max(0, (this.d.height - this.bounds().height) / 2));
            if (this.bounds().width == this.d.width - 1 || this.bounds().height == this.d.height - 1) {
                this.largerMI.disable();
            }
        }
        if (s.equals("About...")) {
            new AppAboutInfoDialog("About", 275, 165).show();
        }
        if (s.equals("Instructions")) {
            new AppInstrucInfoDialog("Instructions", 375, 155).show();
        }
        return true;
    }
    
    class WindowCloser extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            if (AppFrame.this.aWA.closeAll()) {
                AppFrame.this.dispose();
                System.exit(0);
            }
        }
    }
}
