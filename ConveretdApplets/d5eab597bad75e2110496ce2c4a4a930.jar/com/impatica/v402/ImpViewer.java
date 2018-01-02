// 
// Decompiled by Procyon v0.5.30
// 

package com.impatica.v402;

import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Book;
import java.awt.print.PrinterJob;
import javax.swing.JOptionPane;
import java.util.zip.ZipInputStream;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.Component;
import java.awt.MenuBar;
import java.awt.Event;
import java.awt.Rectangle;
import java.util.Enumeration;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.awt.Frame;
import java.io.IOException;
import java.io.File;
import java.io.OutputStream;
import java.io.InputStream;
import java.awt.Color;
import java.util.zip.ZipFile;
import java.util.Properties;
import java.awt.FileDialog;
import java.awt.print.Printable;

public class ImpViewer extends ImWindow implements Printable, Runnable
{
    private String add;
    private String addSeparator;
    private String append;
    FileDialog arg;
    Properties charAt;
    ZipFile close;
    
    public ImpViewer() {
        this.add = null;
        this.addSeparator = "";
        this.append = "";
    }
    
    private String C() {
        final StringBuffer sb = new StringBuffer();
        final int y = super.I.S.y;
        if (y != 0) {
            sb.append("Presentation created by:\n");
            String o = super.I.S.O;
            if (o == null) {
                o = "Unknown";
            }
            sb.append(o);
            sb.append("\nUsing Impatica for PowerPoint ");
            sb.append(y >> 8);
            sb.append('.');
            sb.append(y & 0xFF);
            sb.append('\n');
            sb.append('\n');
        }
        sb.append("PowerPoint is a registered trademark of Microsoft Corp.\nImpatica is a registered trademark of Impatica Inc.\nCopyright © 2000-2007 Impatica Inc.");
        return sb.toString();
    }
    
    private Color I(final String s, Color color) {
        try {
            color = new Color(Integer.parseInt(this.charAt.getProperty(s), 16));
        }
        catch (Throwable t) {}
        return color;
    }
    
    private void Z() {
        final Color i = this.I("BGCOLOR", Color.white);
        super.I.I = (i.getRGB() & 0xFFFFFF);
        super.I.A = i;
        super.I.S.I = super.I.I;
        if (super.I.I != 16777215) {
            super.I.E = ~super.I.I;
        }
        final ImPlayerConsole imPlayerConsole = (ImPlayerConsole)super.I;
        imPlayerConsole.N = this.I("COLOR0", imPlayerConsole.N);
        imPlayerConsole.O = this.I("COLOR0A", imPlayerConsole.O);
        imPlayerConsole.P = this.I("COLOR1", imPlayerConsole.P);
        imPlayerConsole.Q = this.I("COLOR1A", imPlayerConsole.Q);
    }
    
    private final void add(final InputStream inputStream, final OutputStream outputStream) {
        while (true) {
            final int read = inputStream.read();
            if (read == -1) {
                break;
            }
            outputStream.write((byte)read);
        }
    }
    
    private final void addSeparator(final String s) {
        this.drawImage("Error", s);
    }
    
    private final void append() {
        Properties charAt = this.charAt;
        String s;
        if (charAt != null) {
            s = System.getProperty("java.class.path");
        }
        else {
            s = super.I.K;
        }
        if (s != null && s.contains(File.pathSeparator)) {
            s = s.substring(0, s.indexOf(File.pathSeparator));
        }
        if (s == null || !s.endsWith("-imp.jar")) {
            this.addSeparator("Can only export from -imp.jar file");
            return;
        }
        try {
            System.out.println("Path: " + s + "**");
            this.close = new ZipFile(s);
            if (charAt == null) {
                charAt = new Properties();
                charAt.load(this.entries("ImpSettings.properties"));
            }
            final String property = charAt.getProperty("TITLE");
            if (property == null) {
                throw new IOException("Could not find TITLE");
            }
            final String string = property + ".html";
            final FileDialog fileDialog = new FileDialog(this, "Export Web Files", 1);
            fileDialog.setFile(string);
            fileDialog.show();
            String s2 = fileDialog.getFile();
            if (s2 == null || s2.length() == 0) {
                this.close.close();
                this.close = null;
                return;
            }
            final InputStream entries = this.entries("a.html");
            String substring = s2;
            final int index = substring.indexOf(".html");
            if (index != -1) {
                substring = substring.substring(0, index);
            }
            else {
                s2 += ".html";
            }
            final String string2 = fileDialog.getDirectory() + s2;
            final FileWriter fileWriter = new FileWriter(string2);
            while (true) {
                final int read = entries.read();
                if (read == -1) {
                    break;
                }
                final char c = (char)read;
                if (c == '_') {
                    final char c2 = (char)entries.read();
                    if (c2 == '_') {
                        fileWriter.write(substring);
                    }
                    else {
                        fileWriter.write(c2);
                    }
                }
                else {
                    fileWriter.write(c);
                }
            }
            entries.close();
            fileWriter.close();
            String s3 = charAt.getProperty("PLAYERNAME");
            if (s3 == null) {
                throw new IOException("Could not find PLAYERNAME");
            }
            final int lastIndex = string2.lastIndexOf(File.separatorChar);
            if (lastIndex != -1) {
                s3 = string2.substring(0, lastIndex + 1) + s3;
            }
            final ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(s3));
            final Enumeration<? extends ZipEntry> entries2 = this.close.entries();
            while (entries2.hasMoreElements()) {
                final ZipEntry zipEntry = (ZipEntry)entries2.nextElement();
                if (zipEntry.getName().startsWith("com/")) {
                    final InputStream inputStream = this.close.getInputStream(zipEntry);
                    zipOutputStream.putNextEntry(zipEntry);
                    this.add(inputStream, zipOutputStream);
                    inputStream.close();
                }
            }
            zipOutputStream.close();
            final String string3 = string2.substring(0, string2.indexOf(".html")) + "-imp.jar";
            if (!s.equals(string3)) {
                final FileInputStream fileInputStream = new FileInputStream(s);
                final FileOutputStream fileOutputStream = new FileOutputStream(string3);
                this.add(fileInputStream, fileOutputStream);
                fileInputStream.close();
                fileOutputStream.close();
            }
        }
        catch (IOException ex) {
            this.addSeparator("Export error " + ex);
            ex.printStackTrace();
        }
        if (this.close != null) {
            try {
                this.close.close();
            }
            catch (IOException ex2) {}
            this.close = null;
        }
    }
    
    private Rectangle arg(final Rectangle rectangle) {
        final Rectangle rectangle2 = new Rectangle();
        rectangle2.x = rectangle.x;
        rectangle2.y = rectangle.y;
        final ImRect s = super.I.S.S;
        final int n = s.M - s.L;
        final int n2 = s.O - s.N;
        final double n3 = rectangle.width / n;
        final double n4 = rectangle.height / n2;
        if (n3 < n4) {
            rectangle2.width = rectangle.width;
            rectangle2.height = (int)Math.round(n3 * n2);
        }
        else {
            rectangle2.width = (int)Math.round(n4 * n);
            rectangle2.height = rectangle.height;
        }
        return rectangle2;
    }
    
    public final boolean handleEvent(final Event event) {
        if (event.id == 1001) {
            final String add = (String)event.arg;
            if (add.equals("Exit")) {
                event.id = 201;
            }
            if (this.add == null) {
                this.add = add;
                new Thread(this).start();
            }
        }
        return super.handleEvent(event);
    }
    
    public final void charAt(final boolean b) {
        this.setMenuBar(super.C = new MenuBar());
        final Menu close = this.close(super.C, "File", new String[] { "Open...", null, "Export Web Files...", "Print..." });
        final String property = System.getProperty("os.name");
        if (property == null || !property.startsWith("Mac")) {
            this.contains(close, new String[] { null, "Exit" });
        }
        this.close(super.C, "View", new String[] { "Zoom in", "Zoom out", "Zoom 1:1" });
        this.close(super.C, "Help", new String[] { "About ImpViewer" });
        if (!b) {
            super.I = new ImPlayer();
        }
        else {
            super.I = new ImPlayerConsole();
        }
        super.I.C = this;
        super.I.S.I = 0;
        this.add(super.I);
    }
    
    public static final void main(final String[] array) {
        final ImpViewer impViewer = new ImpViewer();
        impViewer.Z = true;
        final Class<? extends ImpViewer> class1 = impViewer.getClass();
        InputStream resourceAsStream;
        try {
            resourceAsStream = class1.getResourceAsStream("/a.imp");
        }
        catch (Throwable t) {
            resourceAsStream = null;
        }
        boolean b = true;
        if (resourceAsStream != null) {
            try {
                final InputStream resourceAsStream2 = class1.getResourceAsStream("/ImpSettings.properties");
                if (resourceAsStream2 != null) {
                    final Properties charAt = new Properties();
                    (impViewer.charAt = charAt).load(resourceAsStream2);
                    resourceAsStream2.close();
                    if ("false".equals(charAt.getProperty("USECONSOLE"))) {
                        b = false;
                    }
                    final String property = charAt.getProperty("TITLE");
                    if (property != null) {
                        impViewer.setTitle(property);
                    }
                }
            }
            catch (Throwable t2) {
                impViewer.charAt = null;
            }
        }
        impViewer.charAt(b);
        if (resourceAsStream != null) {
            if (b) {
                impViewer.Z();
            }
            impViewer.defaultPage(resourceAsStream);
        }
        else if (array.length != 0) {
            impViewer.I(array[0]);
        }
        else {
            impViewer.setSize(320, 240);
            impViewer.setTitle("ImpViewer");
            impViewer.show();
        }
    }
    
    private final Menu close(final MenuBar menuBar, final String s, final String[] array) {
        final Menu menu = new Menu(s);
        menuBar.add(menu);
        this.contains(menu, array);
        return menu;
    }
    
    private final void contains(final Menu menu, final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            final String s = array[i];
            if (s == null) {
                menu.addSeparator();
            }
            else {
                menu.add(new MenuItem(s));
            }
        }
    }
    
    final void I(String string) {
        try {
            final boolean b = string.endsWith(".jar") || string.endsWith(".JAR");
            if (!b && !string.endsWith(".imp") && !string.endsWith(".IMP")) {
                string += ".imp";
            }
            super.I.J = 0;
            InputStream inputStream = new FileInputStream(string);
            this.charAt = null;
            if (b) {
                final ZipInputStream zipInputStream = new ZipInputStream(inputStream);
                zipInputStream.getNextEntry();
                inputStream = zipInputStream;
            }
            this.setTitle(string);
            this.defaultPage(inputStream);
            super.I.K = string;
        }
        catch (IOException ex) {
            this.addSeparator("Could not open file");
        }
    }
    
    public final void defaultPage(final InputStream inputStream) {
        final ImIstreamer imIstreamer = new ImIstreamer();
        if (imIstreamer.I(inputStream) && super.I.I(imIstreamer)) {
            final Menu menu = super.C.getMenu(1);
            menu.removeAll();
            this.contains(menu, new String[] { "Zoom in", "Zoom out", "Zoom 1:1" });
            if (super.I.M != null) {
                this.contains(menu, new String[] { null });
                this.contains(menu, super.I.M);
            }
            this.pack();
            this.show();
            super.I.start();
        }
    }
    
    private final void drawImage(final String s, final String s2) {
        JOptionPane.showMessageDialog(this, s2, s, -1);
    }
    
    public final void run() {
        final String add = this.add;
        if (add.equals("About ImpViewer")) {
            this.drawImage("About ImpViewer", this.C());
        }
        else if (add.equals("Export Web Files...")) {
            this.append();
        }
        else if (add.equals("Full screen")) {
            super.I.I(true);
        }
        else if (add.equals("Open...")) {
            if (this.arg == null) {
                this.arg = new FileDialog(this, "Open", 0);
            }
            this.arg.show();
            final String file = this.arg.getFile();
            if (file != null && file.length() != 0) {
                super.I.stop();
                this.I(this.arg.getDirectory() + file);
            }
        }
        else if (add.equals("Print...")) {
            this.endsWith();
        }
        else if (add.equals("Zoom in")) {
            super.I.S.Z(49);
        }
        else if (add.equals("Zoom out")) {
            super.I.S.Z(51);
        }
        else if (add.equals("Zoom 1:1")) {
            super.I.S.FI = 0;
        }
        else {
            int n = 0;
            for (int i = 0; i < add.length(); ++i) {
                final char char1 = add.charAt(i);
                if (char1 < '0' || char1 > '9') {
                    break;
                }
                n = n * 10 + (char1 - '0');
            }
            if (n > 0) {
                super.I.S.C = super.I.H[n - 1];
            }
        }
        this.add = null;
    }
    
    private void endsWith() {
        if (super.I.S.M == null) {
            return;
        }
        super.I.stop();
        final int z = super.I.S.Z;
        try {
            final PrinterJob printerJob = PrinterJob.getPrinterJob();
            printerJob.setJobName("ImpViewer");
            final Book pageable = new Book();
            printerJob.setPageable(pageable);
            final PageFormat defaultPage = printerJob.defaultPage();
            defaultPage.setOrientation(0);
            final PageFormat pageDialog = printerJob.pageDialog(defaultPage);
            if (super.I.H == null) {
                super.I.I();
            }
            if (pageDialog != defaultPage && super.I.H != null) {
                for (int length = super.I.H.length, i = 0; i < length; ++i) {
                    pageable.append(this, printerJob.validatePage(pageDialog));
                }
                if (printerJob.printDialog()) {
                    try {
                        printerJob.print();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace(System.out);
                    }
                }
            }
        }
        finally {
            super.I.S.I(z);
            super.I.start();
        }
    }
    
    public final int print(final Graphics graphics, final PageFormat pageFormat, final int n) {
        if (n < 0 || n >= super.I.H.length) {
            return 1;
        }
        int i;
        if (n < super.I.H.length - 1) {
            i = super.I.H[n + 1] - 1;
        }
        else {
            i = super.I.S.I(new StringBuffer("END-1"));
            if (i < 0) {
                i = super.I.H[super.I.H.length - 1];
            }
        }
        final ImIplayer s = super.I.S;
        s.I(i);
        s.B();
        final ImRect s2 = s.S;
        final int n2 = s2.M - s2.L;
        final int n3 = s2.O - s2.N;
        final BufferedImage bufferedImage = new BufferedImage(n2, n3, 1);
        final int q = s.q;
        bufferedImage.setRGB(0, 0, n2, n3, super.I.S.M.C, s2.N * q + s2.L, q);
        final Rectangle arg = this.arg(graphics.getClipBounds());
        if (!graphics.drawImage(bufferedImage, arg.x, arg.y, arg.width, arg.height, null)) {
            try {
                synchronized (this) {
                    this.wait(5000L);
                }
            }
            catch (InterruptedException ex) {}
        }
        return 0;
    }
    
    public final void setTitle(String title) {
        final int lastIndex = title.lastIndexOf(File.separatorChar);
        if (lastIndex != -1) {
            title = title.substring(lastIndex + 1);
        }
        if (title.endsWith(".imp")) {
            title = title.substring(0, title.length() - 4);
        }
        else if (title.endsWith("-imp.jar")) {
            title = title.substring(0, title.length() - 8);
        }
        super.setTitle(title);
    }
    
    private final InputStream entries(final String s) {
        final ZipEntry entry = this.close.getEntry(s);
        if (entry == null) {
            throw new IOException(s + " not found");
        }
        return this.close.getInputStream(entry);
    }
}
