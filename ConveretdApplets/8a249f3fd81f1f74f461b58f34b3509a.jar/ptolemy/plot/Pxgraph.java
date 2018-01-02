// 
// Decompiled by Procyon v0.5.30
// 

package ptolemy.plot;

import java.util.Hashtable;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.util.Properties;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Dimension;
import java.util.StringTokenizer;
import java.io.OutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.awt.Component;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Button;
import java.awt.Frame;

public class Pxgraph extends Frame
{
    private transient Button _exitButton;
    private transient Button _printButton;
    private transient Button _HTMLButton;
    private transient Button _aboutButton;
    private String[] _cmdLineArgs;
    private static int _debug;
    private transient String _outputFile;
    private Plot _plotPanel;
    private transient boolean _printDialog;
    private static boolean _test;
    
    public Pxgraph(final String[] args) {
        this._outputFile = "/tmp/t.ps";
        this._printDialog = false;
        this._plotPanel = new Plot();
        this._makeButtons();
        Pxgraph._debug = 0;
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                System.exit(0);
            }
        });
        this._plotPanel.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent event) {
                final char c = event.getKeyChar();
                System.out.println("Pxgraph: got '" + c + "'");
                switch (c) {
                    case '\u0003':
                    case '\u0004':
                    case 'q': {
                        System.exit(0);
                        break;
                    }
                    case 'x': {
                        Pxgraph.this._pxgraphX11();
                        break;
                    }
                }
            }
        });
        this.pack();
        this.add("Center", this._plotPanel);
        try {
            this._parseArgs(args);
        }
        catch (CmdLineArgException e) {
            System.err.println("Failed to parse command line arguments: " + e);
        }
        catch (FileNotFoundException e2) {
            System.err.println("File not found in command line: " + e2);
        }
        catch (IOException e3) {
            System.err.println("Error while reading input file: " + e3);
        }
        this.show();
        this._plotPanel.setButtons(true);
        if (this._printDialog) {
            this._print();
        }
    }
    
    public static void main(final String[] args) {
        final Pxgraph pxgraph = new Pxgraph(args);
        if (Pxgraph._test) {
            if (Pxgraph._debug > 4) {
                System.out.println("Sleeping for 2 seconds");
            }
            try {
                Thread.currentThread();
                Thread.sleep(2000L);
            }
            catch (InterruptedException ex) {}
            System.exit(0);
        }
    }
    
    public void write(final OutputStream out) {
        this._plotPanel.write(out);
    }
    
    private void _about() {
        final Message message = new Message("               Pxgraph\n        A Java Plotting Tool\n\nBy: Edward A. Lee, eal@eecs.berkeley.edu and\n    Christopher Hylands, cxh@eecs.berkeley.edu\nVersion 2.0, Build: $Id: Pxgraph.java,v 1.108a 1998/12/11 03:52:20 cxh Exp $\n\nFor help, type 'pxgraph -help', or see \nthe Pxgraph class documentation.\nFor more information, see\nhttp://ptolemy.eecs.berkeley.edu/java/ptplot\n");
        message.setTitle("About Pxgraph");
        message.pack();
        message.show();
    }
    
    private void _help() {
        final String[][] commandOptions = { { "-bd", "<color>", "Border", "White", "(Unsupported)" }, { "-bg", "<color>", "BackGround", "White", "" }, { "-brb", "<base>", "BarBase", "0", "(Unsupported)" }, { "-brw", "<width>", "BarWidth", "1", "" }, { "-bw", "<size>", "BorderSize", "1", "(Unsupported)" }, { "-fg", "<color>", "Foreground", "Black", "" }, { "-gw", "<pixels>", "GridStyle", "1", "(Unsupported)" }, { "-lf", "<fontname>", "LabelFont", "helvetica-12", "" }, { "-lw", "<width>", "LineWidth", "0", "(Unsupported)" }, { "-lx", "<xl,xh>", "XLowLimit, XHighLimit", "0", "" }, { "-ly", "<yl,yh>", "YLowLimit, YHighLimit", "0", "" }, { "-o", "<output filename>", "", "/tmp/t.ps", "" }, { "-t", "<title>", "TitleText", "An X Graph", "" }, { "-tf", "<fontname>", "TitleFont", "helvetica-b-14", "" }, { "-x", "<unitName>", "XUnitText", "X", "" }, { "-y", "<unitName>", "YUnitText", "Y", "" }, { "-zg", "<color>", "ZeroColor", "Black", "(Unsupported)" }, { "-zw", "<width>", "ZeroWidth", "0", "(Unsupported)" } };
        final String[][] commandFlags = { { "-bar", "BarGraph", "" }, { "-bb", "BoundBox", "(Ignored)" }, { "-binary", "Binary", "" }, { "-db", "Debug", "" }, { "-help", "Help", "" }, { "-impulses", "Impulses", "" }, { "-lnx", "XLog", "" }, { "-lny", "YLog", "" }, { "-m", "Markers", "" }, { "-M", "StyleMarkers", "" }, { "-nl", "NoLines", "" }, { "-p", "PixelMarkers", "" }, { "-P", "LargePixel", "" }, { "-print", "Print", "" }, { "-rv", "ReverseVideo", "" }, { "-test", "Test", "" }, { "-tk", "Ticks", "" }, { "-v", "Version", "" }, { "-version", "Version", "" } };
        System.out.println("Usage: pxgraph [ options ] [=WxH+X+Y] [file ...]");
        System.out.println(" options that take values as second args:");
        for (int i = 0; i < commandOptions.length; ++i) {
            System.out.println(" " + commandOptions[i][0] + " " + commandOptions[i][1] + " " + commandOptions[i][4]);
        }
        System.out.println(" Boolean flags:");
        for (int i = 0; i < commandFlags.length; ++i) {
            System.out.println(" " + commandFlags[i][0] + " " + commandFlags[i][2]);
        }
        System.out.println("The following pxgraph features are not supported:");
        System.out.println(" * Directives in pxgraph input files");
        System.out.println(" * Xresources");
        System.out.println(" For complete documentation, see the Pxgraph Java class documentation.");
        System.exit(1);
    }
    
    private void _HTML() {
        final Dimension dim = this.getSize();
        final StringTokenizer stoken = new StringTokenizer(System.getProperty("java.class.path"), ";:");
        String plotclassdir = new String("");
        if (stoken.hasMoreTokens()) {
            plotclassdir = stoken.nextToken();
        }
        final StringBuffer applettag = new StringBuffer("<!-- Automatically generated by pxgraph. -->\n<!-- See http://ptolemy.eecs.berkeley.edu/java/ptplot for more information. -->\n<html>\n<head>\n<title>" + this.getTitle() + "</title>\n<body>\n" + "<!-- You will need to edit the codebase tag\n" + "     below.  To use the most recent version\n" + "     from over the network set it to:\n" + "     http://ptolemy.eecs.berkeley.edu/java\n" + "-->\n" + "<applet name =\"" + this.getTitle() + "\"" + " code=\"ptplot.PlotApplet\"" + " width=" + dim.width + " height=" + dim.height + "\n" + "    codebase=\"" + plotclassdir + "\"\n" + "    archive=\"ptplot/ptplot.jar\"\n" + "    alt=\"If you had a java-enabled " + "browser, you would see an applet here.\"\n>\n" + "<param name=\"pxgraphargs\" value=\"");
        if (this._cmdLineArgs.length > 0) {
            for (int i = 0; i < this._cmdLineArgs.length - 1; ++i) {
                if (this._cmdLineArgs[i].indexOf(" ") != -1) {
                    applettag.append("'" + this._cmdLineArgs[i] + "' ");
                }
                else if (this._cmdLineArgs[i].length() == 0) {
                    applettag.append("'' ");
                }
                else {
                    applettag.append(String.valueOf(this._cmdLineArgs[i]) + " ");
                }
            }
            applettag.append(this._cmdLineArgs[this._cmdLineArgs.length - 1]);
        }
        applettag.append("\">\n</applet>\n</body>\n</html>");
        System.out.println(applettag.toString());
    }
    
    private void _setButtons(final boolean vis) {
        this._plotPanel.setButtons(vis);
        this._exitButton.setVisible(vis);
        this._printButton.setVisible(vis);
        this._HTMLButton.setVisible(vis);
        this._aboutButton.setVisible(vis);
    }
    
    private void _makeButtons() {
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(0));
        if (this._exitButton == null) {
            (this._exitButton = new Button("Exit")).addActionListener(new ExitButtonListener());
            panel.add(this._exitButton);
        }
        if (this._printButton == null) {
            (this._printButton = new Button("Print")).addActionListener(new PrintButtonListener());
            panel.add(this._printButton);
        }
        if (this._HTMLButton == null) {
            (this._HTMLButton = new Button("HTML")).addActionListener(new HTMLButtonListener());
            panel.add(this._HTMLButton);
        }
        if (this._aboutButton == null) {
            (this._aboutButton = new Button("About")).addActionListener(new AboutButtonListener());
            panel.add(this._aboutButton);
        }
        this.add("South", panel);
    }
    
    private int _parseArgs(final String[] args) throws CmdLineArgException, FileNotFoundException, IOException {
        int i = 0;
        String title = "A plot";
        int width = 400;
        int height = 400;
        this._cmdLineArgs = new String[args.length];
        try {
            System.arraycopy(args, 0, this._cmdLineArgs, 0, args.length);
        }
        catch (ArrayIndexOutOfBoundsException ex) {}
        catch (ArrayStoreException ex2) {}
        while (i < args.length) {
            final String arg = args[i++];
            if (Pxgraph._debug > 2) {
                System.out.print("Pxgraph: arg = " + arg + "\n");
            }
            if (arg.startsWith("-")) {
                if (arg.equals("-bg")) {
                    this.setBackground(PlotBox.getColorByName(args[i++]));
                }
                else if (arg.equals("-db")) {
                    Pxgraph._debug = 10;
                }
                else if (arg.equals("-debug")) {
                    Pxgraph._debug = Integer.valueOf(args[i++]);
                }
                else if (arg.equals("-fg")) {
                    this.setForeground(PlotBox.getColorByName(args[i++]));
                }
                else if (arg.equals("-help")) {
                    this._help();
                }
                else if (arg.equals("-o")) {
                    this._outputFile = args[i++];
                }
                else if (arg.equals("-print")) {
                    this._printDialog = true;
                }
                else if (arg.equals("-test")) {
                    Pxgraph._test = true;
                }
                else if (arg.equals("-t")) {
                    title = args[i++];
                }
                else {
                    if (!arg.equals("-v") && !arg.equals("-version")) {
                        continue;
                    }
                    this._about();
                }
            }
            else {
                if (!arg.startsWith("=")) {
                    continue;
                }
                int xscreen = 1;
                int yscreen = 1;
                boolean screenlocationgiven = false;
                final StringTokenizer stoken = new StringTokenizer(arg.substring(1, arg.length()), "=x-+");
                if (stoken.hasMoreTokens()) {
                    width = Integer.valueOf(stoken.nextToken());
                }
                if (stoken.hasMoreTokens()) {
                    height = Integer.valueOf(stoken.nextToken());
                }
                if (stoken.hasMoreTokens()) {
                    xscreen = Integer.valueOf(stoken.nextToken());
                    screenlocationgiven = true;
                }
                if (stoken.hasMoreTokens()) {
                    yscreen = Integer.valueOf(stoken.nextToken());
                    screenlocationgiven = true;
                }
                if (!screenlocationgiven) {
                    continue;
                }
                this.setLocation(new Point(xscreen + 1, yscreen + 1));
            }
        }
        this.setSize(width, height);
        this.setTitle(title);
        final int argsread = i++;
        if (Pxgraph._debug > 2) {
            System.err.println("Pxgraph: title = " + title);
            System.err.println("Pxgraph: width = " + width + " height = " + height + " _debug = " + Pxgraph._debug);
        }
        this._plotPanel.parseArgs(args);
        return argsread;
    }
    
    private void _print() {
        final Properties newprops = new Properties();
        ((Hashtable<String, String>)newprops).put("awt.print.destination", "file");
        ((Hashtable<String, String>)newprops).put("awt.print.fileName", this._outputFile);
        final PrintJob printjob = this.getToolkit().getPrintJob(this, this.getTitle(), newprops);
        if (printjob != null) {
            final Graphics printgraphics = printjob.getGraphics();
            if (printgraphics != null) {
                final Dimension dim = this.getSize();
                this._setButtons(false);
                this.printAll(printgraphics);
                this._setButtons(true);
                this.setSize(dim.width, dim.height);
                this.show();
                printgraphics.dispose();
                printjob.end();
                if (this._printDialog) {
                    System.exit(0);
                }
            }
            else {
                printjob.end();
            }
        }
    }
    
    private void _pxgraphX11() {
        try {
            final boolean sawdebug = false;
            final String[] command = new String[this._cmdLineArgs.length + 1];
            command[0] = new String("pxgraph.x11");
            System.out.print("Pxgraph: about to execute: " + command[0] + " ");
            int j = 1;
            for (int i = 0; i < this._cmdLineArgs.length; ++i) {
                if (this._cmdLineArgs[i].equals("-debug")) {
                    ++i;
                }
                else {
                    command[j++] = this._cmdLineArgs[i];
                }
                System.out.print(String.valueOf(command[j - 1]) + " ");
            }
            System.out.println("");
            final Runtime runtime = Runtime.getRuntime();
            final Process browser = runtime.exec(command);
        }
        catch (SecurityException e) {
            System.out.println("Pxgraph: _pxgraphX11: " + e);
        }
        catch (IOException e2) {
            System.out.println("Pxgraph: _pxgraphX11: " + e2);
        }
    }
    
    class AboutButtonListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent event) {
            Pxgraph.this._about();
        }
    }
    
    class ExitButtonListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent event) {
            System.exit(1);
        }
    }
    
    class HTMLButtonListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent event) {
            Pxgraph.this._HTML();
        }
    }
    
    class PrintButtonListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent event) {
            Pxgraph.this._print();
        }
    }
}
