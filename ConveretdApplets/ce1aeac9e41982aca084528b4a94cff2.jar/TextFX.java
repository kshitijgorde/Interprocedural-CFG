import java.io.IOException;
import java.net.URL;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Color;
import java.io.InputStream;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TextFX extends Applet implements Runnable, MouseListener
{
    int regnum;
    String companyname;
    String registered;
    boolean reg;
    InputStream input;
    String text;
    int sign;
    int bluesign;
    int redsign;
    int greensign;
    int a;
    int pixely;
    int fontsize;
    boolean started;
    Color kleur;
    Color bgkleur;
    boolean upsidedown;
    int blue;
    int green;
    int red;
    int bgblue;
    String cyclecolor;
    int bggreen;
    int bgred;
    int cyclespeed;
    FontMetrics fontmetrics;
    int fontwidth;
    Thread runThread;
    int changespeed;
    Image itb;
    Graphics gtb;
    Image[] line;
    Graphics[] lineGraph;
    Font nl;
    boolean running;
    boolean stopclick;
    boolean alive;
    int netscapebug;
    
    public void init() {
        System.out.println("---------Start Of TextFX Info --------");
        System.out.println("");
        System.out.println("(This info is for debugging purposes only)");
        System.out.println("(Things get messy in Netscape)");
        try {
            this.reg = this.registration();
        }
        catch (Exception ex2) {
            System.out.println("TextFX error ocurred in Registration");
        }
        System.out.println("Registration method passed");
        try {
            this.addMouseListener(this);
            if (this.getParameter("stopclick").toUpperCase().equals("TRUE")) {
                this.stopclick = true;
            }
            else {
                this.stopclick = false;
            }
            this.cyclecolor = this.getParameter("cyclecolor");
            this.text = this.getParameter("Text");
            System.out.println("Using String: " + this.text);
            this.cyclespeed = Integer.parseInt(this.getParameter("cyclespeed"));
            this.fontsize = Integer.parseInt(this.getParameter("Fontsize"));
            this.red = Integer.parseInt(this.getParameter("fontred"));
            this.green = Integer.parseInt(this.getParameter("fontgreen"));
            this.blue = Integer.parseInt(this.getParameter("fontblue"));
            this.kleur = new Color(this.red, this.green, this.blue);
            this.bgred = Integer.parseInt(this.getParameter("bgred"));
            this.bggreen = Integer.parseInt(this.getParameter("bggreen"));
            this.bgblue = Integer.parseInt(this.getParameter("bgblue"));
            this.changespeed = Integer.parseInt(this.getParameter("changespeed"));
            this.bgkleur = new Color(this.bgred, this.bggreen, this.bgblue);
            if (this.getParameter("Style").toUpperCase().equals("BOLD")) {
                this.nl = new Font(this.getParameter("Font").toUpperCase(), 1, this.fontsize);
            }
            if (this.getParameter("Style").toUpperCase().equals("PLAIN")) {
                this.nl = new Font(this.getParameter("Font").toUpperCase(), 0, this.fontsize);
            }
            if (this.getParameter("Style").toUpperCase().equals("ITALIC")) {
                this.nl = new Font(this.getParameter("Font").toUpperCase(), 2, this.fontsize);
            }
            this.fontwidth = this.getFontMetrics(this.nl).stringWidth(this.text);
            this.fontsize += 50;
            this.line = new Image[this.fontsize + this.fontsize / 2];
            this.lineGraph = new Graphics[this.fontsize + this.fontsize / 2];
            if (this.getParameter("upsidedown").equals("true")) {
                this.upsidedown = true;
            }
            this.setBackground(this.bgkleur);
            this.itb = this.createImage(this.fontwidth + 30, this.fontsize + this.fontsize / 2);
            this.gtb = this.itb.getGraphics();
            for (int i = 0; i < this.fontsize + this.fontsize / 2; ++i) {
                this.line[i] = this.createImage(this.fontwidth + 30, 1);
                this.lineGraph[i] = this.line[i].getGraphics();
            }
        }
        catch (Exception ex3) {
            System.out.println("TextFX error ocurred in init");
        }
        try {
            if (this.runThread == null) {
                (this.runThread = new Thread(this)).start();
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
            System.out.println("TextFX error ocurred in Threadstart");
        }
    }
    
    public void paint(final Graphics graphics) {
        try {
            if (this.started) {
                for (int i = 0; i < this.fontsize + this.fontsize / 2; ++i) {
                    if (this.cyclecolor.equals("red")) {
                        this.red += this.sign * this.cyclespeed;
                        if (this.red > 255 || this.red < 1) {
                            this.sign *= -1;
                            this.red += this.sign * this.cyclespeed;
                        }
                    }
                    else if (this.cyclecolor.equals("green")) {
                        this.green += this.sign * this.cyclespeed;
                        if (this.green > 255 || this.green < 1) {
                            this.sign *= -1;
                            this.green += this.sign * this.cyclespeed;
                        }
                    }
                    else if (this.cyclecolor.equals("blue")) {
                        this.blue += this.sign * this.cyclespeed;
                        if (this.blue > 255 || this.blue < 1) {
                            this.sign *= -1;
                            this.blue += this.sign * this.cyclespeed;
                        }
                    }
                    else if (this.cyclecolor.equals("all")) {
                        this.blue += this.bluesign * this.cyclespeed;
                        this.red += this.redsign * this.cyclespeed;
                        this.green += this.greensign * this.cyclespeed;
                        if (this.blue > 255 || this.blue < 1) {
                            this.bluesign *= -1;
                            this.blue += this.bluesign * this.cyclespeed;
                        }
                        if (this.red > 255 || this.red < 1) {
                            this.redsign *= -1;
                            this.red += this.redsign * this.cyclespeed;
                        }
                        if (this.green > 255 || this.green < 1) {
                            this.greensign *= -1;
                            this.green += this.greensign * this.cyclespeed;
                        }
                    }
                    this.kleur = new Color(this.red, this.green, this.blue);
                    this.lineGraph[i].setColor(this.kleur);
                    this.setBackground(this.bgkleur);
                    this.lineGraph[i].setFont(this.nl);
                }
            }
            this.started = false;
            if (this.upsidedown) {
                for (int j = 0; j < this.fontsize + this.fontsize / 2 - 1; ++j) {
                    this.lineGraph[j].drawString(this.text, 20, this.a);
                    this.gtb.drawImage(this.line[j], 0, this.pixely, this);
                    ++this.a;
                    ++this.pixely;
                }
            }
            else {
                for (int k = 0; k < this.fontsize + this.fontsize / 2 - 1; ++k) {
                    this.lineGraph[k].drawString(this.text, 10, this.a);
                    this.gtb.drawImage(this.line[this.fontsize + this.fontsize / 2 - 1 - k], 0, this.pixely - this.fontsize, this);
                    ++this.a;
                    ++this.pixely;
                }
            }
            this.a = -20;
            graphics.drawImage(this.itb, 20, 20, this);
            this.pixely = 10;
            for (int l = 0; l < this.fontsize + this.fontsize / 2; ++l) {
                if (l < this.fontsize + this.fontsize / 2 - 1) {
                    this.lineGraph[this.fontsize + this.fontsize / 2 - 1 - l].setColor(this.lineGraph[this.fontsize + this.fontsize / 2 - 2 - l].getColor());
                }
                else {
                    this.lineGraph[0].setColor(this.lineGraph[this.fontsize + this.fontsize / 2 - 1].getColor());
                }
            }
        }
        catch (Exception ex) {
            System.out.println("TextFX error ocurred");
            System.out.println("PaintException: " + ex);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void run() {
        System.out.println("Thread Started");
        while (this.alive) {
            while (this.running) {
                ++this.netscapebug;
                try {
                    Thread.sleep(this.changespeed);
                }
                catch (Exception ex) {
                    System.out.println("TextFX error ocurred");
                    System.out.println("ThreadException: " + ex);
                }
                this.repaint();
            }
        }
    }
    
    public void stop() {
        if (this.netscapebug > 30) {
            System.out.println("Stop has been called after 3 seconds, quitting");
            this.stop2();
            System.out.println("---------Netscape End Of TextFX Info --------");
            System.out.println("");
            return;
        }
        System.out.println("Stop() has been called, performing no action.");
    }
    
    public void stop2() {
        System.out.println("Starting Cleanup");
        this.clean();
        System.out.println("Finished Cleaning");
    }
    
    public void destroy() {
        System.out.println("Destroy Function called");
        System.out.println("---------IE End Of TextFX Info --------");
        System.out.println("");
    }
    
    public void clean() {
        System.out.println(this.runThread.toString());
        this.running = false;
        this.alive = false;
        try {
            this.runThread.stop();
        }
        catch (Exception ex) {
            System.out.println("Thread has already stopped ot cant be stopped");
        }
        System.out.println("Memory is clean.");
        System.gc();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (!this.reg) {
            this.getAppletContext().showStatus("Unregistered Applet");
        }
        else {
            this.getAppletContext().showStatus("Registered to " + this.companyname);
        }
        this.setCursor(new Cursor(12));
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.stopclick) {
            if (this.running) {
                this.running = false;
                return;
            }
            this.running = true;
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(new Cursor(0));
    }
    
    public boolean registration() {
        try {
            this.input = new URL(this.getDocumentBase(), "License.txt").openStream();
        }
        catch (Exception ex) {
            System.out.println("TextFX error ocurred");
            System.out.println(ex);
            System.out.println("(IOException is normal when local)");
            System.out.println("Corrupted License file or file is missing");
            return false;
        }
        try {
            char c = 'a';
            while (c != '\n') {
                if (c <= '\0') {
                    break;
                }
                c = (char)this.input.read();
                if (c == '\n' || c == '\r') {
                    continue;
                }
                this.companyname = String.valueOf(this.companyname) + c;
            }
        }
        catch (Exception ex2) {
            System.out.println("TextFX error ocurred");
            System.out.println(ex2);
            return false;
        }
        try {
            char c2 = 'a';
            while (c2 != '\n' && c2 > '\0') {
                if (c2 == '\r') {
                    break;
                }
                c2 = (char)this.input.read();
                if (c2 == '\n' || c2 == '\r') {
                    continue;
                }
                this.registered = String.valueOf(this.registered) + c2;
            }
        }
        catch (Exception ex3) {
            System.out.println(ex3);
            System.out.println("TextFX error ocurred");
            return false;
        }
        try {
            this.input.close();
            this.input = null;
        }
        catch (IOException ex4) {
            System.out.println("Input Exception occurred");
            System.out.println(ex4);
        }
        for (int i = 0; i < this.companyname.length(); ++i) {
            this.regnum += this.companyname.charAt(i);
        }
        try {
            if (Integer.parseInt(this.registered) == this.regnum) {
                System.out.println("Registered to " + this.companyname);
                this.reg = true;
            }
            else {
                System.out.println("Unregistered Version");
                this.reg = false;
            }
        }
        catch (Exception ex5) {
            System.out.println("Registration number is corrupted");
            return false;
        }
        return this.reg;
    }
    
    public TextFX() {
        this.companyname = "";
        this.registered = "";
        this.reg = false;
        this.sign = 1;
        this.bluesign = 1;
        this.redsign = 1;
        this.greensign = 1;
        this.a = -20;
        this.pixely = 10;
        this.started = true;
        this.upsidedown = false;
        this.running = true;
        this.alive = true;
    }
}
