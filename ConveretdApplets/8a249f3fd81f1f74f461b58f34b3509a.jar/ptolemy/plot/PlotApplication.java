// 
// Decompiled by Procyon v0.5.30
// 

package ptolemy.plot;

import java.awt.Point;
import java.util.StringTokenizer;
import java.awt.Color;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class PlotApplication extends PlotFrame
{
    private static boolean _test;
    
    public PlotApplication() {
        this((String[])null);
    }
    
    public PlotApplication(final String[] args) {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                System.exit(0);
            }
        });
        if (args != null && args.length != 0) {
            try {
                this._parseArgs(args);
            }
            catch (FileNotFoundException ex) {
                System.err.println("File not found: " + ex + "\n" + this._usage());
                throw new RuntimeException("cancelled");
            }
            catch (IOException ex2) {
                System.err.println("Error reading input: " + ex2 + "\n" + this._usage());
                throw new RuntimeException("cancelled");
            }
            catch (CmdLineArgException ex3) {
                System.err.println("Command line format error: " + ex3 + "\n" + this._usage());
                throw new RuntimeException("cancelled");
            }
            final String _cmdfile = super.plot.getCmdLineFilename();
            if (_cmdfile != null) {
                super._filename = _cmdfile;
            }
        }
        else {
            this.samplePlot();
        }
    }
    
    public static void main(final String[] args) {
        final PlotApplication plot = new PlotApplication(args);
        if (PlotApplication._test) {
            try {
                Thread.currentThread();
                Thread.sleep(2000L);
            }
            catch (InterruptedException ex) {}
            System.exit(0);
        }
    }
    
    protected void _about() {
        final Message message = new Message("Ptplot\nBy: Edward A. Lee, eal@eecs.berkeley.edu\nand Christopher Hylands, cxh@eecs.berkeley.edu\nVersion 2.0, Build: $Id: PlotApplication.java,v 1.6 1998/11/20 01:12:09 cxh Exp $\n\nFor more information, see\nhttp://ptolemy.eecs.berkeley.edu/java/ptplot\nCopyright (c) 1997-1998,\nThe Regents of the University of California.");
        message.setTitle("About Ptolemy Plot");
    }
    
    protected void _close() {
        System.exit(0);
    }
    
    protected void _help() {
        final Message message = new Message("PlotApplication is a standalone Java 2D plot application\nIt can read files compatible with the Ptolemy plot\nfile format (currently only ASCII).  For a description\n of the file format, see the Plot and PlotBox classes.\nCommand-line options include:\n" + this._usage(), null, null, 20, 40, 0);
        message.setTitle("Usage of Ptolemy Plot");
    }
    
    protected int _parseArgs(final String[] args) throws CmdLineArgException, FileNotFoundException, IOException {
        int i = 0;
        String title = "Ptolemy plot";
        int width = 400;
        int height = 300;
        while (i < args.length) {
            final String arg = args[i++];
            if (arg.equals("-help")) {
                System.out.println(this._usage());
            }
            else if (arg.equals("-test")) {
                PlotApplication._test = true;
            }
            else if (arg.equals("-t")) {
                title = args[i++];
            }
            else if (arg.equals("-v") || arg.equals("-version")) {
                this._about();
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
        super.plot.parseArgs(args);
        return argsread;
    }
    
    protected String _usage() {
        final String[][] commandOptions = { { "-bd", "<color>", "Border", "White", "(Unsupported)" }, { "-bg", "<color>", "BackGround", "White", "" }, { "-brb", "<base>", "BarBase", "0", "(Unsupported)" }, { "-brw", "<width>", "BarWidth", "1", "" }, { "-bw", "<size>", "BorderSize", "1", "(Unsupported)" }, { "-fg", "<color>", "Foreground", "Black", "" }, { "-gw", "<pixels>", "GridStyle", "1", "(Unsupported)" }, { "-lf", "<fontname>", "LabelFont", "helvetica-12", "" }, { "-lw", "<width>", "LineWidth", "0", "(Unsupported)" }, { "-lx", "<xl,xh>", "XLowLimit, XHighLimit", "0", "" }, { "-ly", "<yl,yh>", "YLowLimit, YHighLimit", "0", "" }, { "-o", "<output filename>", "", "/tmp/t.ps", "" }, { "-t", "<title>", "TitleText", "An X Graph", "" }, { "-tf", "<fontname>", "TitleFont", "helvetica-b-14", "" }, { "-x", "<unitName>", "XUnitText", "X", "" }, { "-y", "<unitName>", "YUnitText", "Y", "" }, { "-zg", "<color>", "ZeroColor", "Black", "(Unsupported)" }, { "-zw", "<width>", "ZeroWidth", "0", "(Unsupported)" } };
        final String[][] commandFlags = { { "-bar", "BarGraph", "" }, { "-bb", "BoundBox", "(Ignored)" }, { "-binary", "Binary", "" }, { "-impulses", "Impulses", "" }, { "-lnx", "XLog", "" }, { "-lny", "YLog", "" }, { "-m", "Markers", "" }, { "-M", "StyleMarkers", "" }, { "-nl", "NoLines", "" }, { "-p", "PixelMarkers", "" }, { "-P", "LargePixel", "" }, { "-rv", "ReverseVideo", "" }, { "-test", "Test", "" }, { "-tk", "Ticks", "" }, { "-v", "Version", "" }, { "-version", "Version", "" } };
        String result = "Usage: plot [ options ] [=WxH+X+Y] [file ...]\n\n options that take values as second args:\n";
        for (int i = 0; i < commandOptions.length; ++i) {
            result = String.valueOf(result) + " " + commandOptions[i][0] + " " + commandOptions[i][1] + " " + commandOptions[i][4] + "\n";
        }
        result = String.valueOf(result) + "\nBoolean flags:\n";
        for (int i = 0; i < commandFlags.length; ++i) {
            result = String.valueOf(result) + " " + commandFlags[i][0] + " " + commandFlags[i][2] + "\n";
        }
        result = String.valueOf(result) + "\nThe following pxgraph features are not supported:\n * Directives in pxgraph input files\n * Xresources\nFor complete documentation, see the pxgraph program docs.";
        return result;
    }
}
