// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter;

import java.awt.Component;
import javax.swing.JOptionPane;
import java.net.URLConnection;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Rectangle;
import java.net.URL;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComponent;

public class f extends JComponent
{
    private static final Color b;
    private static final Color k;
    private static final Font a;
    private URL j;
    private int[] f;
    private String[] o;
    private int h;
    private int d;
    private int g;
    private int e;
    private int l;
    private Rectangle i;
    private Rectangle c;
    private int n;
    
    public f(final URL j) {
        this.h = 0;
        this.d = 0;
        this.g = 0;
        this.e = 0;
        this.l = -1;
        this.i = new Rectangle();
        this.c = new Rectangle();
        this.n = 0;
        this.j = j;
        this.f = new int[20];
        this.a(this.o = new String[20]);
        this.addKeyListener(new KeyAdapter() {
            private final f a = a;
            
            public void keyPressed(final KeyEvent keyEvent) {
                final int keyCode = keyEvent.getKeyCode();
                if (keyCode == 39) {
                    com.pokw.shooter.y.a();
                    com.pokw.shooter.f.c(this.a);
                    if (com.pokw.shooter.f.b(this.a) > 1) {
                        com.pokw.shooter.f.a(this.a, 0);
                    }
                }
                else if (keyCode == 37) {
                    com.pokw.shooter.y.a();
                    com.pokw.shooter.f.e(this.a);
                    if (com.pokw.shooter.f.b(this.a) < 0) {
                        com.pokw.shooter.f.a(this.a, 1);
                    }
                }
                else if (keyCode == 10) {
                    com.pokw.shooter.y.a();
                    com.pokw.shooter.f.d(this.a);
                }
                this.a.repaint();
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            private final f a = a;
            
            public void mouseMoved(final MouseEvent mouseEvent) {
            }
        });
        this.addMouseListener(new MouseAdapter() {
            private final f a = a;
            
            public void mouseClicked(final MouseEvent mouseEvent) {
                this.a.requestFocus();
            }
        });
        this.setBackground(com.pokw.shooter.f.b);
        this.setForeground(com.pokw.shooter.f.k);
        this.setFont(com.pokw.shooter.f.a);
        this.c();
    }
    
    public void c() {
        if (this.j == null) {
            return;
        }
        new Thread() {
            private final f a = a;
            
            public void run() {
                try {
                    com.pokw.shooter.f.a(this.a, com.pokw.shooter.f.a(this.a, com.pokw.shooter.f.a(this.a)));
                    this.a.repaint();
                }
                catch (Exception ex) {
                    com.pokw.shooter.n.a(ex);
                }
            }
        }.start();
    }
    
    public void a(final int l) {
        if (this.j == null) {
            return;
        }
        this.l = l;
        String s = this.a("Submit Score", "Please enter your name for the high scores");
        if (s != null) {
            while (!this.a(s)) {
                s = this.a("Submit Score", "Invalid name, try again");
                if (s == null) {
                    return;
                }
            }
        }
        this.a(s, l);
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.d = 0;
        this.h = 0;
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final Rectangle bounds = this.getBounds();
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, bounds.width, bounds.height);
        graphics.setColor(this.getForeground());
        if (this.g != bounds.width || this.e != bounds.height) {
            this.g = bounds.width;
            this.e = bounds.height;
            this.i.x = (this.g - 450) / 2;
            this.i.y = this.e - 60;
            this.i.width = 150;
            this.i.height = 30;
            this.c.x = this.i.x + 300;
            this.c.y = this.i.y;
            this.c.width = 150;
            this.c.height = 30;
        }
        if (this.d == 0 || this.h == 0) {
            this.d = graphics.getFontMetrics().stringWidth(".");
            this.h = graphics.getFontMetrics().getHeight();
            if (this.d <= 0) {
                this.d = 1;
            }
        }
        int n = 100;
        int n2 = 25;
        final int n3 = bounds.width - 80 - this.d * 10;
        for (int i = 0; i < 20; ++i) {
            graphics.drawString(this.o[i] + " ", n, n2);
            int j;
            for (j = n + (this.o[i].length() + 1) * this.d; j < n3; j += this.d) {
                graphics.drawString(".", j, n2);
            }
            graphics.drawString(this.f[i] + "", j, n2);
            n = 100;
            n2 += this.h + 5;
        }
        if (this.n == 0) {
            graphics.drawImage(com.pokw.shooter.y.F, this.i.x, this.i.y, this);
            graphics.drawImage(com.pokw.shooter.y.N, this.c.x, this.c.y, this);
        }
        else {
            graphics.drawImage(com.pokw.shooter.y.w, this.i.x, this.i.y, this);
            graphics.drawImage(com.pokw.shooter.y.v, this.c.x, this.c.y, this);
        }
    }
    
    public void b() {
        throw new UnsupportedOperationException("HighScoreList.goBack() method not supported");
    }
    
    private void b(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\r\n");
        for (int i = 0; i < 20; ++i) {
            String substring = "N/A";
            int int1 = -1;
            if (stringTokenizer.hasMoreTokens()) {
                final String trim = stringTokenizer.nextToken().trim();
                final int index = trim.indexOf("=");
                if (index > 0) {
                    substring = trim.substring(0, index);
                    try {
                        int1 = Integer.parseInt(trim.substring(index + 1));
                    }
                    catch (NumberFormatException ex) {
                        com.pokw.shooter.n.a(ex);
                    }
                }
            }
            this.o[i] = substring;
            this.f[i] = int1;
        }
    }
    
    private void a(final String s, final int n) {
        try {
            final URLConnection openConnection = this.j.openConnection();
            openConnection.setDoInput(true);
            openConnection.setDoOutput(true);
            openConnection.setUseCaches(false);
            final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openConnection.getOutputStream());
            outputStreamWriter.write("name=" + s + "&score=" + n);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
            final StringBuffer sb = new StringBuffer(1024);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
            this.b(sb.toString());
            this.repaint();
        }
        catch (IOException ex) {
            System.err.println("Failed to post scores");
        }
    }
    
    private String a(final URL url) throws IOException {
        final URLConnection openConnection = url.openConnection();
        openConnection.connect();
        int contentLength = openConnection.getContentLength();
        if (contentLength <= 0) {
            contentLength = 512;
        }
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
        final StringBuffer sb = new StringBuffer(contentLength);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
            sb.append('\n');
        }
        return sb.toString();
    }
    
    private String a(final String s, final String s2) {
        return JOptionPane.showInputDialog(this, s2, s, 3);
    }
    
    private boolean a(final String s) {
        for (int i = 0; i < s.length(); ++i) {
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    private void a(final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            array[i] = "(Not enabled on server)";
        }
    }
    
    private void a() {
        if (this.n == 0) {
            this.b();
        }
        else if (this.n == 1) {
            this.c();
        }
    }
    
    static URL a(final f f) {
        return f.j;
    }
    
    static String a(final f f, final URL url) throws IOException {
        return f.a(url);
    }
    
    static void a(final f f, final String s) {
        f.b(s);
    }
    
    static int c(final f f) {
        return f.n++;
    }
    
    static int b(final f f) {
        return f.n;
    }
    
    static int a(final f f, final int n) {
        return f.n = n;
    }
    
    static int e(final f f) {
        return f.n--;
    }
    
    static void d(final f f) {
        f.a();
    }
    
    static {
        b = new Color(0, 0, 192);
        k = Color.WHITE;
        a = new Font("Monospaced", 0, 11);
    }
}
