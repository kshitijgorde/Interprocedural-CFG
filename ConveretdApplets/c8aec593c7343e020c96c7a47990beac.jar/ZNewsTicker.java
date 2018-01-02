import java.net.MalformedURLException;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ZNewsTicker extends Applet implements Runnable
{
    Graphics offG;
    Image offI;
    Thread mythread;
    int ypos;
    int count;
    int mess;
    Font font;
    String m1;
    String m2;
    String m3;
    String m4;
    String m5;
    String m6;
    String m7;
    String m8;
    String m9;
    String m10;
    String back;
    String text;
    String str;
    String u1;
    String u2;
    String u3;
    String u4;
    String u5;
    String u6;
    String u7;
    String u8;
    String u9;
    String u10;
    Color colorb;
    Color colorm;
    StringTokenizer t;
    StringTokenizer t2;
    URL url;
    
    public void init() {
        this.back = this.getParameter("backgroundcolor");
        this.t = new StringTokenizer(this.back, ",");
        this.colorb = new Color(Integer.parseInt(this.t.nextToken()), Integer.parseInt(this.t.nextToken()), Integer.parseInt(this.t.nextToken()));
        this.text = this.getParameter("textcolor");
        this.t2 = new StringTokenizer(this.text, ",");
        this.colorm = new Color(Integer.parseInt(this.t2.nextToken()), Integer.parseInt(this.t2.nextToken()), Integer.parseInt(this.t2.nextToken()));
        this.setBackground(this.colorb);
        this.offI = this.createImage(this.size().width, this.size().height);
        this.offG = this.offI.getGraphics();
        this.ypos = this.size().height + 20;
        this.font = new Font("TimesRoman", 1, 14);
        this.offG.setFont(this.font);
        this.offG.setColor(this.colorm);
        this.m1 = this.getParameter("message1");
        this.m2 = this.getParameter("message2");
        this.m3 = this.getParameter("message3");
        this.m4 = this.getParameter("message4");
        this.m5 = this.getParameter("message5");
        this.m6 = this.getParameter("message6");
        this.m7 = this.getParameter("message7");
        this.m8 = this.getParameter("message8");
        this.m9 = this.getParameter("message9");
        this.m10 = this.getParameter("message10");
        this.u1 = this.getParameter("link1");
        this.u2 = this.getParameter("link2");
        this.u3 = this.getParameter("link3");
        this.u4 = this.getParameter("link4");
        this.u5 = this.getParameter("link5");
        this.u6 = this.getParameter("link6");
        this.u7 = this.getParameter("link7");
        this.u8 = this.getParameter("link8");
        this.u9 = this.getParameter("link9");
        this.u10 = this.getParameter("link10");
    }
    
    public void paint(final Graphics graphics) {
        this.offG.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
        graphics.drawImage(this.offI, 0, 0, this);
    }
    
    public void start() {
        (this.mythread = new Thread(this)).start();
    }
    
    public void run() {
        while (true) {
            if (this.m1.length() != 0) {
                this.message1();
            }
            if (this.m2.length() != 0) {
                this.message2();
            }
            if (this.m3.length() != 0) {
                this.message3();
            }
            if (this.m4.length() != 0) {
                this.message4();
            }
            if (this.m5.length() != 0) {
                this.message5();
            }
            if (this.m6.length() != 0) {
                this.message6();
            }
            if (this.m7.length() != 0) {
                this.message7();
            }
            if (this.m8.length() != 0) {
                this.message8();
            }
            if (this.m9.length() != 0) {
                this.message9();
            }
            if (this.m10.length() != 0) {
                this.message10();
            }
        }
    }
    
    public void stop() {
        this.mythread.stop();
    }
    
    public void pause() {
        while (this.count < 250) {
            try {
                Thread.sleep(10L);
                ++this.count;
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void message1() {
        this.mess = 1;
        this.ypos = this.size().height + 20;
        this.count = 0;
        while (this.ypos > 13) {
            try {
                Thread.sleep(10L);
                this.offG.clearRect(1, 1, this.size().width - 2, this.size().height - 2);
                this.offG.drawString(this.m1, 20, this.ypos);
                --this.ypos;
                this.repaint();
            }
            catch (InterruptedException ex) {}
        }
        this.pause();
        while (this.ypos > -10) {
            try {
                Thread.sleep(10L);
                this.offG.clearRect(1, 1, this.size().width - 2, this.size().height - 2);
                this.offG.drawString(this.m1, 20, this.ypos);
                --this.ypos;
                this.repaint();
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void message2() {
        this.mess = 2;
        this.ypos = this.size().height + 20;
        this.count = 0;
        while (this.ypos > 13) {
            try {
                Thread.sleep(10L);
                this.offG.clearRect(1, 1, this.size().width - 2, this.size().height - 2);
                this.offG.drawString(this.m2, 20, this.ypos);
                --this.ypos;
                this.repaint();
            }
            catch (InterruptedException ex) {}
        }
        this.pause();
        while (this.ypos > -10) {
            try {
                Thread.sleep(10L);
                this.offG.clearRect(1, 1, this.size().width - 2, this.size().height - 2);
                this.offG.drawString(this.m2, 20, this.ypos);
                --this.ypos;
                this.repaint();
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void message3() {
        this.mess = 3;
        this.ypos = this.size().height + 20;
        this.count = 0;
        while (this.ypos > 13) {
            try {
                Thread.sleep(10L);
                this.offG.clearRect(1, 1, this.size().width - 2, this.size().height - 2);
                this.offG.drawString(this.m3, 20, this.ypos);
                --this.ypos;
                this.repaint();
            }
            catch (InterruptedException ex) {}
        }
        this.pause();
        while (this.ypos > -10) {
            try {
                Thread.sleep(10L);
                this.offG.clearRect(1, 1, this.size().width - 2, this.size().height - 2);
                this.offG.drawString(this.m3, 20, this.ypos);
                --this.ypos;
                this.repaint();
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void message4() {
        this.mess = 4;
        this.ypos = this.size().height + 20;
        this.count = 0;
        while (this.ypos > 13) {
            try {
                Thread.sleep(10L);
                this.offG.clearRect(1, 1, this.size().width - 2, this.size().height - 2);
                this.offG.drawString(this.m4, 20, this.ypos);
                --this.ypos;
                this.repaint();
            }
            catch (InterruptedException ex) {}
        }
        this.pause();
        while (this.ypos > -10) {
            try {
                Thread.sleep(10L);
                this.offG.clearRect(1, 1, this.size().width - 2, this.size().height - 2);
                this.offG.drawString(this.m4, 20, this.ypos);
                --this.ypos;
                this.repaint();
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void message5() {
        this.mess = 5;
        this.ypos = this.size().height + 20;
        this.count = 0;
        while (this.ypos > 13) {
            try {
                Thread.sleep(10L);
                this.offG.clearRect(1, 1, this.size().width - 2, this.size().height - 2);
                this.offG.drawString(this.m5, 20, this.ypos);
                --this.ypos;
                this.repaint();
            }
            catch (InterruptedException ex) {}
        }
        this.pause();
        while (this.ypos > -10) {
            try {
                Thread.sleep(10L);
                this.offG.clearRect(1, 1, this.size().width - 2, this.size().height - 2);
                this.offG.drawString(this.m5, 20, this.ypos);
                --this.ypos;
                this.repaint();
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void message6() {
        this.mess = 6;
        this.ypos = this.size().height + 20;
        this.count = 0;
        while (this.ypos > 13) {
            try {
                Thread.sleep(10L);
                this.offG.clearRect(1, 1, this.size().width - 2, this.size().height - 2);
                this.offG.drawString(this.m6, 20, this.ypos);
                --this.ypos;
                this.repaint();
            }
            catch (InterruptedException ex) {}
        }
        this.pause();
        while (this.ypos > -10) {
            try {
                Thread.sleep(10L);
                this.offG.clearRect(1, 1, this.size().width - 1, this.size().height - 1);
                this.offG.drawString(this.m6, 20, this.ypos);
                --this.ypos;
                this.repaint();
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void message7() {
        this.mess = 7;
        this.ypos = this.size().height + 20;
        this.count = 0;
        while (this.ypos > 13) {
            try {
                Thread.sleep(10L);
                this.offG.clearRect(1, 1, this.size().width - 2, this.size().height - 2);
                this.offG.drawString(this.m7, 20, this.ypos);
                --this.ypos;
                this.repaint();
            }
            catch (InterruptedException ex) {}
        }
        this.pause();
        while (this.ypos > -10) {
            try {
                Thread.sleep(10L);
                this.offG.clearRect(1, 1, this.size().width - 2, this.size().height - 2);
                this.offG.drawString(this.m7, 20, this.ypos);
                --this.ypos;
                this.repaint();
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void message8() {
        this.mess = 8;
        this.ypos = this.size().height + 20;
        this.count = 0;
        while (this.ypos > 13) {
            try {
                Thread.sleep(10L);
                this.offG.clearRect(1, 1, this.size().width - 2, this.size().height - 2);
                this.offG.drawString(this.m8, 20, this.ypos);
                --this.ypos;
                this.repaint();
            }
            catch (InterruptedException ex) {}
        }
        this.pause();
        while (this.ypos > -10) {
            try {
                Thread.sleep(10L);
                this.offG.clearRect(1, 1, this.size().width - 2, this.size().height - 2);
                this.offG.drawString(this.m8, 20, this.ypos);
                --this.ypos;
                this.repaint();
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void message9() {
        this.mess = 9;
        this.ypos = this.size().height + 20;
        this.count = 0;
        while (this.ypos > 13) {
            try {
                Thread.sleep(10L);
                this.offG.clearRect(1, 1, this.size().width - 2, this.size().height - 2);
                this.offG.drawString(this.m9, 20, this.ypos);
                --this.ypos;
                this.repaint();
            }
            catch (InterruptedException ex) {}
        }
        this.pause();
        while (this.ypos > -10) {
            try {
                Thread.sleep(10L);
                this.offG.clearRect(1, 1, this.size().width - 2, this.size().height - 2);
                this.offG.drawString(this.m9, 20, this.ypos);
                --this.ypos;
                this.repaint();
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void message10() {
        this.mess = 10;
        this.ypos = this.size().height + 20;
        this.count = 0;
        while (this.ypos > 13) {
            try {
                Thread.sleep(10L);
                this.offG.clearRect(1, 1, this.size().width - 2, this.size().height - 2);
                this.offG.drawString(this.m10, 20, this.ypos);
                --this.ypos;
                this.repaint();
            }
            catch (InterruptedException ex) {}
        }
        this.pause();
        while (this.ypos > -10) {
            try {
                Thread.sleep(10L);
                this.offG.clearRect(1, 1, this.size().width - 2, this.size().height - 2);
                this.offG.drawString(this.m10, 20, this.ypos);
                --this.ypos;
                this.repaint();
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.setCursor(new Cursor(12));
        this.showStatus("Unregistered version");
        if (this.mess == 1) {
            this.str = this.m1;
        }
        if (this.mess == 2) {
            this.str = this.m2;
        }
        if (this.mess == 3) {
            this.str = this.m3;
        }
        if (this.mess == 4) {
            this.str = this.m4;
        }
        if (this.mess == 5) {
            this.str = this.m5;
        }
        if (this.mess == 6) {
            this.str = this.m6;
        }
        if (this.mess == 7) {
            this.str = this.m7;
        }
        if (this.mess == 8) {
            this.str = this.m8;
        }
        if (this.mess == 9) {
            this.str = this.m9;
        }
        if (this.mess == 10) {
            this.str = this.m10;
        }
        this.mythread.suspend();
        this.offG.clearRect(1, 1, this.size().width - 2, this.size().height - 2);
        this.offG.setColor(Color.blue);
        this.offG.drawString(this.str, 20, this.ypos + 1);
        this.repaint();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.setCursor(new Cursor(0));
        this.offG.setColor(this.colorm);
        this.offG.clearRect(1, 1, this.size().width - 2, this.size().height - 2);
        this.offG.drawString(this.str, 20, this.ypos + 1);
        this.repaint();
        this.mythread.resume();
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.mess == 1) {
            try {
                this.url = new URL(this.u1);
                this.getAppletContext().showDocument(this.url, "blank");
            }
            catch (MalformedURLException ex) {}
        }
        if (this.mess == 2) {
            try {
                this.url = new URL(this.u2);
                this.getAppletContext().showDocument(this.url, "blank");
            }
            catch (MalformedURLException ex2) {}
        }
        if (this.mess == 3) {
            try {
                this.url = new URL(this.u3);
                this.getAppletContext().showDocument(this.url, "blank");
            }
            catch (MalformedURLException ex3) {}
        }
        if (this.mess == 4) {
            try {
                this.url = new URL(this.u4);
                this.getAppletContext().showDocument(this.url, "blank");
            }
            catch (MalformedURLException ex4) {}
        }
        if (this.mess == 5) {
            try {
                this.url = new URL(this.u5);
                this.getAppletContext().showDocument(this.url, "blank");
            }
            catch (MalformedURLException ex5) {}
        }
        if (this.mess == 6) {
            try {
                this.url = new URL(this.u6);
                this.getAppletContext().showDocument(this.url, "blank");
            }
            catch (MalformedURLException ex6) {}
        }
        if (this.mess == 7) {
            try {
                this.url = new URL(this.u7);
                this.getAppletContext().showDocument(this.url, "blank");
            }
            catch (MalformedURLException ex7) {}
        }
        if (this.mess == 8) {
            try {
                this.url = new URL(this.u8);
                this.getAppletContext().showDocument(this.url, "blank");
            }
            catch (MalformedURLException ex8) {}
        }
        if (this.mess == 9) {
            try {
                this.url = new URL(this.u9);
                this.getAppletContext().showDocument(this.url, "blank");
            }
            catch (MalformedURLException ex9) {}
        }
        if (this.mess == 10) {
            try {
                this.url = new URL(this.u10);
                this.getAppletContext().showDocument(this.url, "blank");
            }
            catch (MalformedURLException ex10) {}
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.showStatus("Unregistered version");
        return true;
    }
}
