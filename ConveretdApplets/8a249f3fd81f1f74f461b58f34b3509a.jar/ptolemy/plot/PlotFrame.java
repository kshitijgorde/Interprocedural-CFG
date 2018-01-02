// 
// Decompiled by Procyon v0.5.30
// 

package ptolemy.plot;

import java.awt.event.ActionEvent;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.util.Properties;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.awt.FileDialog;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.MenuShortcut;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.Frame;

public class PlotFrame extends Frame
{
    public Plot plot;
    protected MenuBar _menubar;
    protected Menu _fileMenu;
    protected Menu _specialMenu;
    protected String _directory;
    protected String _filename;
    
    public PlotFrame() {
        this("Ptolemy Plot Frame");
    }
    
    public PlotFrame(final String title) {
        super(title);
        this.plot = new Plot();
        this._menubar = new MenuBar();
        this._fileMenu = new Menu("File");
        this._specialMenu = new Menu("Special");
        final MenuItem[] fileMenuItems = { new MenuItem("Open", new MenuShortcut(79)), new MenuItem("Save", new MenuShortcut(83)), new MenuItem("SaveAs", new MenuShortcut(65)), new MenuItem("Print", new MenuShortcut(80)), new MenuItem("Close", new MenuShortcut(87)) };
        final FileMenuListener fml = new FileMenuListener();
        for (int i = 0; i < fileMenuItems.length; ++i) {
            fileMenuItems[i].setActionCommand(fileMenuItems[i].getLabel());
            fileMenuItems[i].addActionListener(fml);
            this._fileMenu.add(fileMenuItems[i]);
        }
        this._menubar.add(this._fileMenu);
        final MenuItem[] specialMenuItems = { new MenuItem("About", null), new MenuItem("Help", new MenuShortcut(72)), new MenuItem("Clear", new MenuShortcut(67)), new MenuItem("Fill", new MenuShortcut(70)), new MenuItem("Sample plot", null) };
        final SpecialMenuListener sml = new SpecialMenuListener();
        for (int j = 0; j < specialMenuItems.length; ++j) {
            specialMenuItems[j].setActionCommand(specialMenuItems[j].getLabel());
            specialMenuItems[j].addActionListener(sml);
            this._specialMenu.add(specialMenuItems[j]);
        }
        this._menubar.add(this._specialMenu);
        this.setMenuBar(this._menubar);
        this.add("Center", this.plot);
        this.setSize(500, 300);
        this.setVisible(true);
    }
    
    public void samplePlot() {
        this.plot.clear(true);
        this._filename = null;
        this._directory = null;
        this.plot.setTitle("Sample plot");
        this.plot.setYRange(-4.0, 4.0);
        this.plot.setXRange(0.0, 100.0);
        this.plot.setXLabel("time");
        this.plot.setYLabel("value");
        this.plot.addYTick("-PI", -3.141592653589793);
        this.plot.addYTick("-PI/2", -1.5707963267948966);
        this.plot.addYTick("0", 0.0);
        this.plot.addYTick("PI/2", 1.5707963267948966);
        this.plot.addYTick("PI", 3.141592653589793);
        this.plot.setNumSets(10);
        this.plot.setMarksStyle("none");
        this.plot.setImpulses(true);
        boolean first = true;
        for (int i = 0; i <= 100; ++i) {
            this.plot.addPoint(0, i, 5.0 * Math.cos(3.141592653589793 * i / 20.0), !first);
            this.plot.addPoint(1, i, 4.5 * Math.cos(3.141592653589793 * i / 25.0), !first);
            this.plot.addPoint(2, i, 4.0 * Math.cos(3.141592653589793 * i / 30.0), !first);
            this.plot.addPoint(3, i, 3.5 * Math.cos(3.141592653589793 * i / 35.0), !first);
            this.plot.addPoint(4, i, 3.0 * Math.cos(3.141592653589793 * i / 40.0), !first);
            this.plot.addPoint(5, i, 2.5 * Math.cos(3.141592653589793 * i / 45.0), !first);
            this.plot.addPoint(6, i, 2.0 * Math.cos(3.141592653589793 * i / 50.0), !first);
            this.plot.addPoint(7, i, 1.5 * Math.cos(3.141592653589793 * i / 55.0), !first);
            this.plot.addPoint(8, i, Math.cos(3.141592653589793 * i / 60.0), !first);
            this.plot.addPoint(9, i, 0.5 * Math.cos(3.141592653589793 * i / 65.0), !first);
            first = false;
        }
        this.plot.repaint();
    }
    
    protected void _about() {
        final Message message = new Message("Ptolemy plot frame\nBy: Edward A. Lee, eal@eecs.berkeley.edu\nand Christopher Hylands, cxh@eecs.berkeley.edu\nVersion 2.0, Build: $Id: PlotFrame.java,v 1.6 1998/11/21 02:09:06 lmuliadi Exp $\n\nFor more information, see\nhttp://ptolemy.eecs.berkeley.edu/java/ptplot\n");
        message.setTitle("About Ptolemy Plot");
    }
    
    protected void _close() {
        this.dispose();
    }
    
    protected void _help() {
        final Message message = new Message("PlotFrame is a versatile two-dimensional data plotter that runs as part of an application, but in its own window. It can read files compatible with the Ptolemy plot file format (currently only ASCII), or the application can interact directly with the contained Plot object, which is visible as a public member. For a description of the file format, see the Plot and PlotBox classes.");
        message.setTitle("Plot frame");
    }
    
    protected void _open() {
        final FileDialog filedialog = new FileDialog(this, "Select a plot file");
        filedialog.setFilenameFilter(new PlotFilenameFilter());
        if (this._directory != null) {
            filedialog.setDirectory(this._directory);
        }
        filedialog.setVisible(true);
        final String filename = filedialog.getFile();
        if (filename == null) {
            return;
        }
        this._directory = filedialog.getDirectory();
        final File file = new File(this._directory, filename);
        this._filename = null;
        try {
            this.plot.clear(true);
            this.plot.read(new FileInputStream(file));
        }
        catch (FileNotFoundException ex) {
            final Message msg = new Message("File not found: " + ex);
        }
        catch (IOException ex2) {
            final Message message = new Message("Error reading input: " + ex2);
        }
        this._filename = filename;
    }
    
    protected void _print() {
        final PrintJob printjob = this.getToolkit().getPrintJob(this, this.getTitle(), null);
        if (printjob != null) {
            try {
                final Graphics printgraphics = printjob.getGraphics();
                if (printgraphics != null) {
                    try {
                        this.plot.printAll(printgraphics);
                    }
                    finally {
                        printgraphics.dispose();
                    }
                }
            }
            finally {
                printjob.end();
            }
        }
    }
    
    protected void _save() {
        if (this._filename != null) {
            final File file = new File(this._directory, this._filename);
            try {
                final FileOutputStream fout = new FileOutputStream(file);
                this.plot.write(fout);
            }
            catch (IOException ex) {
                final Message msg = new Message("Error writing file: " + ex);
            }
        }
        else {
            this._saveAs();
        }
    }
    
    protected void _saveAs() {
        final FileDialog filedialog = new FileDialog(this, "Save plot as...");
        filedialog.setFilenameFilter(new PlotFilenameFilter());
        if (this._directory != null) {
            filedialog.setDirectory(this._directory);
        }
        filedialog.setFile("plot.plt");
        filedialog.setVisible(true);
        this._filename = filedialog.getFile();
        if (this._filename == null) {
            return;
        }
        this._directory = filedialog.getDirectory();
        this._save();
    }
    
    class FileMenuListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent e) {
            final MenuItem target = (MenuItem)e.getSource();
            final String actionCommand = target.getActionCommand();
            if (actionCommand.equals("Open")) {
                PlotFrame.this._open();
            }
            else if (actionCommand.equals("Save")) {
                PlotFrame.this._save();
            }
            else if (actionCommand.equals("SaveAs")) {
                PlotFrame.this._saveAs();
            }
            else if (actionCommand.equals("Print")) {
                PlotFrame.this._print();
            }
            else if (actionCommand.equals("Close")) {
                PlotFrame.this._close();
            }
        }
    }
    
    class SpecialMenuListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent e) {
            final MenuItem target = (MenuItem)e.getSource();
            final String actionCommand = target.getActionCommand();
            if (actionCommand.equals("About")) {
                PlotFrame.this._about();
            }
            else if (actionCommand.equals("Help")) {
                PlotFrame.this._help();
            }
            else if (actionCommand.equals("Fill")) {
                PlotFrame.this.plot.fillPlot();
            }
            else if (actionCommand.equals("Clear")) {
                PlotFrame.this.plot.clear(false);
                PlotFrame.this.plot.repaint();
            }
            else if (actionCommand.equals("Sample plot")) {
                PlotFrame.this.plot.clear(true);
                PlotFrame.this.samplePlot();
            }
        }
    }
    
    class PlotFilenameFilter implements FilenameFilter
    {
        public boolean accept(final File dir, String name) {
            name = name.toLowerCase();
            return name.endsWith(".plt");
        }
    }
}
