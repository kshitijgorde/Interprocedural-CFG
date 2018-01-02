// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter;

import java.awt.Image;
import java.util.zip.ZipEntry;
import java.net.URLConnection;
import java.io.IOException;
import java.awt.image.ImageObserver;
import java.awt.Toolkit;
import java.io.InputStream;
import java.util.zip.ZipInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.util.HashMap;
import javax.swing.JProgressBar;
import java.util.Map;
import java.net.URL;
import javax.swing.JPanel;

abstract class x extends JPanel implements Runnable
{
    private int d;
    private int c;
    private URL e;
    private Thread b;
    private Map g;
    private JProgressBar a;
    
    x(final URL e) {
        this.d = 0;
        this.c = 0;
        this.a = new JProgressBar();
        this.e = e;
        this.b = new Thread(this, "Pok's Thread");
        this.g = new HashMap();
        this.a();
    }
    
    private void a() {
        this.setBackground(Color.blue);
        this.setBackground(Color.white);
        this.a.setBounds(100, 350, 400, 55);
        this.setLayout(null);
        this.add(this.a);
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setFont(graphics.getFont().deriveFont(1, 16.0f));
        graphics.drawString("Loading Game, Please Wait", (this.getBounds().width - graphics.getFontMetrics().stringWidth("Loading Game, Please Wait")) / 2, 350 - graphics.getFontMetrics().getHeight());
    }
    
    public void b() {
        this.b.start();
    }
    
    public void run() {
        try {
            final URLConnection openConnection = this.e.openConnection();
            openConnection.connect();
            final InputStream inputStream = openConnection.getInputStream();
            this.a.setMaximum(openConnection.getContentLength());
            this.setBounds(50, 400, 500, 50);
            this.a.setString("Downloading:  " + this.d + "/" + this.c + " KBs");
            this.a.setStringPainted(true);
            this.c = this.a.getMaximum() / 1024;
            final byte[] array = new byte[1024];
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int read;
            while ((read = inputStream.read(array)) != -1) {
                byteArrayOutputStream.write(array, 0, read);
                this.d += read;
                this.a.setValue(this.d);
                this.a.setString("Downloading:  " + this.d / 1024 + "/" + this.c + " KBs");
                Thread.yield();
            }
            byteArrayOutputStream.close();
            inputStream.close();
            this.a.setString("Reading data....");
            final ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            ZipEntry nextEntry;
            while ((nextEntry = zipInputStream.getNextEntry()) != null) {
                final String name = nextEntry.getName();
                final ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                int read2;
                while ((read2 = zipInputStream.read(array)) != -1) {
                    byteArrayOutputStream2.write(array, 0, read2);
                }
                byteArrayOutputStream2.close();
                if (name.endsWith(".gif")) {
                    final byte[] byteArray = byteArrayOutputStream2.toByteArray();
                    if (name.endsWith("core/logo.gif") && byteArray.length != 7741) {
                        throw new Error("ERROR WITH ZIP FILE");
                    }
                    final Image image = Toolkit.getDefaultToolkit().createImage(byteArrayOutputStream2.toByteArray());
                    this.prepareImage(image, this);
                    this.g.put(name, image);
                }
                else {
                    this.g.put(name, byteArrayOutputStream2);
                }
            }
            zipInputStream.close();
        }
        catch (IOException ex) {
            n.a(ex);
        }
        com.pokw.shooter.y.a(this.g);
        this.c();
    }
    
    public abstract void c();
}
