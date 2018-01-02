// 
// Decompiled by Procyon v0.5.30
// 

package lecture;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.net.URLConnection;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.FontMetrics;
import java.awt.Label;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Enumeration;
import java.awt.Rectangle;
import java.awt.Component;
import java.util.Vector;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

class b extends Canvas implements MouseListener, MouseMotionListener
{
    AppletLecture int;
    int a;
    Vector if;
    Vector new;
    String for;
    String do;
    Vector try;
    int x;
    int y;
    
    b(final AppletLecture int1) {
        this.a = -1;
        this.int = int1;
        this.if = new Vector();
        this.new = new Vector();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.if("");
    }
    
    Component a() {
        return this;
    }
    
    private boolean if() {
        boolean b = false;
        int a = 0;
        final Enumeration<Rectangle> elements = (Enumeration<Rectangle>)this.if.elements();
        while (elements.hasMoreElements()) {
            final Rectangle rectangle = elements.nextElement();
            if (this.x >= rectangle.x && this.x <= rectangle.x + rectangle.width && this.y >= rectangle.y && this.y <= rectangle.y + rectangle.height) {
                b = true;
                this.a = a;
            }
            ++a;
        }
        return b;
    }
    
    public void a(final Graphics graphics) {
        if (this.try.isEmpty()) {
            return;
        }
        final Enumeration<String> elements = (Enumeration<String>)this.try.elements();
        while (elements.hasMoreElements()) {
            final String s = elements.nextElement();
            final int index = s.indexOf(59);
            final String substring = s.substring(0, index);
            String s2 = s.substring(index + 1);
            if (substring.equals("zonetexte")) {
                this.int.if(s2.replace('#', '\n'));
            }
            else if (substring.equals("texte")) {
                final boolean b = true;
                int n = 0;
                int a = 0;
                int a2 = 0;
                int a3 = 0;
                int a4 = 0;
                int a5 = 0;
                String s3 = "";
                String s4 = "";
                while (b) {
                    final int index2 = s2.indexOf(59);
                    if (index2 == -1) {
                        break;
                    }
                    final String substring2 = s2.substring(0, index2);
                    s2 = s2.substring(index2 + 1);
                    if (n == 0) {
                        a = this.a(substring2);
                    }
                    else if (n == 1) {
                        a2 = this.a(substring2);
                    }
                    else if (n == 2) {
                        a3 = this.a(substring2);
                    }
                    else if (n == 3) {
                        s3 = substring2;
                    }
                    else if (n == 4) {
                        a4 = this.a(substring2);
                    }
                    else if (n == 5) {
                        a5 = this.a(substring2);
                    }
                    else if (n == 6) {
                        s4 = substring2;
                    }
                    ++n;
                }
                graphics.setColor(new Color(a3));
                graphics.setFont(new Font(s3, a5, a4));
                graphics.drawString(s4, a, a2);
            }
            else if (substring.equals("textelink")) {
                final boolean b2 = true;
                int n2 = 0;
                int a6 = 0;
                int a7 = 0;
                int a8 = 0;
                int a9 = 0;
                int a10 = 0;
                String s5 = "";
                String s6 = "";
                while (b2) {
                    final int index3 = s2.indexOf(59);
                    if (index3 == -1) {
                        break;
                    }
                    final String substring3 = s2.substring(0, index3);
                    s2 = s2.substring(index3 + 1);
                    if (n2 == 0) {
                        a6 = this.a(substring3);
                    }
                    else if (n2 == 1) {
                        a7 = this.a(substring3);
                    }
                    else if (n2 == 2) {
                        a8 = this.a(substring3);
                    }
                    else if (n2 == 3) {
                        s5 = substring3;
                    }
                    else if (n2 == 4) {
                        a9 = this.a(substring3);
                    }
                    else if (n2 == 5) {
                        a10 = this.a(substring3);
                    }
                    else if (n2 == 6) {
                        s6 = substring3;
                    }
                    ++n2;
                }
                graphics.setColor(new Color(a8));
                final Font font = new Font(s5, a10, a9);
                graphics.setFont(font);
                final int index4 = s6.indexOf("###");
                final String substring4 = s6.substring(0, index4);
                this.new.addElement(new String(s6.substring(index4 + 3)));
                graphics.drawString(substring4, a6, a7);
                final Label label = new Label(s6);
                label.setFont(font);
                label.setForeground(new Color(a8));
                final FontMetrics fontMetrics = label.getFontMetrics(font);
                final int stringWidth = fontMetrics.stringWidth(substring4);
                final int ascent = fontMetrics.getAscent();
                this.if.addElement(new Rectangle(a6, a7 - ascent, stringWidth, ascent));
                graphics.setColor(Color.blue);
                graphics.fillRect(a6, a7 + 1, stringWidth, 1);
            }
            else {
                final int[] do1 = this.do(s2);
                graphics.setColor(new Color(do1[4]));
                if (substring.equals("ligne")) {
                    graphics.drawLine(do1[0], do1[1], do1[2], do1[3]);
                }
                else if (substring.equals("arc")) {
                    graphics.drawArc(do1[0], do1[1], do1[2], do1[3], do1[6], do1[7]);
                }
                else if (substring.equals("oval")) {
                    if (do1[6] == 1) {
                        graphics.drawOval(do1[0], do1[1], do1[2], do1[3]);
                    }
                    else {
                        if (do1[6] != 0) {
                            continue;
                        }
                        graphics.fillOval(do1[0], do1[1], do1[2], do1[3]);
                    }
                }
                else if (substring.equals("pin")) {
                    graphics.fillRect(do1[0], do1[1], do1[2], do1[3]);
                }
                else if (substring.equals("rectangle")) {
                    if (do1[6] == 1) {
                        graphics.drawRect(do1[0], do1[1], do1[2], do1[3]);
                    }
                    else {
                        if (do1[6] != 0) {
                            continue;
                        }
                        graphics.fillRect(do1[0], do1[1], do1[2], do1[3]);
                    }
                }
                else {
                    if (!substring.equals("triangle")) {
                        continue;
                    }
                    final int[] array = { do1[0], do1[2], do1[6] };
                    final int[] array2 = { do1[1], do1[3], do1[7] };
                    if (do1[8] == 1) {
                        graphics.drawPolygon(array, array2, 3);
                    }
                    else {
                        if (do1[8] != 0) {
                            continue;
                        }
                        graphics.fillPolygon(array, array2, 3);
                    }
                }
            }
        }
    }
    
    private int a(final String s) {
        return Integer.valueOf(s);
    }
    
    public int[] do(String substring) {
        final int[] array = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        final boolean b = true;
        int n = 0;
        while (b) {
            final int index = substring.indexOf(59);
            if (index == -1) {
                break;
            }
            final String substring2 = substring.substring(0, index);
            substring = substring.substring(index + 1);
            array[n] = this.a(substring2);
            ++n;
        }
        return array;
    }
    
    public void if(String for1) {
        this.for = this.int.a();
        this.do = this.int.do();
        if (for1 == "") {
            for1 = this.for;
        }
        if (this.try != null) {
            this.try.removeAllElements();
        }
        this.try = new Vector();
        System.getProperty("line.separator");
        URL url;
        try {
            url = new URL(for1);
        }
        catch (MalformedURLException ex) {
            System.out.println("Error: " + ex.toString());
            return;
        }
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            openConnection.setDoInput(true);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.equals("")) {
                    this.try.addElement(new String(line));
                }
            }
            bufferedReader.close();
        }
        catch (IOException ex2) {
            System.out.println("Error: " + ex2.toString());
        }
        this.repaint();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.x = mouseEvent.getX();
        this.y = mouseEvent.getY();
        if (this.if()) {
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
        else {
            this.setCursor(Cursor.getPredefinedCursor(0));
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.x = mouseEvent.getX();
        this.y = mouseEvent.getY();
        if (this.if()) {
            this.int.a(this.new.elementAt(this.a));
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.clearRect(0, 0, this.getSize().width, this.getSize().height);
        this.a(graphics);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
