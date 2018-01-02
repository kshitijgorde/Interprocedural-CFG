import java.awt.Color;
import java.util.Date;
import java.awt.Font;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JavaDate extends Applet implements Runnable
{
    Graphics g;
    Font font;
    Thread my_thread;
    int width;
    int height;
    Date current_time;
    boolean right;
    String time;
    
    public JavaDate() {
        this.right = true;
        this.time = " ";
    }
    
    public void init() {
        final String parameter = this.getParameter("author");
        if (parameter == null) {
            this.right = false;
        }
        if (!parameter.equals("Alan Scandella: shady_milkman@gmx.net")) {
            this.right = false;
        }
        this.g = this.getGraphics();
        this.current_time = new Date();
        this.set_defaults();
    }
    
    void set_defaults() {
        this.width = this.size().width;
        this.height = this.size().height;
        this.font = new Font("Courier", 1, 18);
        this.g.setFont(this.font);
        this.setBackground(Color.white);
    }
    
    public void start() {
        if (this.my_thread == null) {
            (this.my_thread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.my_thread.stop();
        this.my_thread = null;
    }
    
    public void run() {
        while (this.my_thread != null) {
            this.repaint();
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void update(final Graphics graphics) {
        this.setBackground(Color.white);
        if (!this.right) {
            graphics.drawString("Parameter Author not specified", 0, this.height - 5);
            return;
        }
        this.current_time = new Date();
        this.time = this.current_time.toString();
        graphics.setFont(this.font);
        graphics.clearRect(0, 0, this.size().width, this.size().height);
        graphics.setColor(Color.black);
        graphics.drawString(this.time, 0, this.height);
    }
    
    public void paint(final Graphics graphics) {
        this.setBackground(Color.white);
        this.set_defaults();
        this.update(graphics);
    }
}
