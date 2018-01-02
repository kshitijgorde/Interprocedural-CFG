import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.util.StringTokenizer;
import java.net.MalformedURLException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Scroll extends Applet implements Runnable, MouseListener
{
    Thread t;
    String[] strHead;
    String[] strDesc;
    String[] strLink;
    String txtHead;
    String txtDesc;
    int noOfText;
    Color headBColor;
    Color descBColor;
    Color headTextColor;
    Color descTextColor;
    Font font;
    long typingSpeed;
    long delayTime;
    String targetWindow;
    int i;
    boolean first;
    boolean nextString;
    
    public Scroll() {
        this.t = null;
        this.strHead = new String[20];
        this.strDesc = new String[20];
        this.strLink = new String[20];
        this.txtHead = null;
        this.txtDesc = null;
        this.noOfText = 0;
        this.headBColor = new Color(0, 255, 0);
        this.descBColor = new Color(0, 0, 255);
        this.headTextColor = new Color(0, 0, 0);
        this.descTextColor = new Color(0, 0, 0);
        this.font = new Font("Sanserif", 1, 14);
        this.typingSpeed = 100L;
        this.delayTime = 2000L;
        this.targetWindow = new String("_blank");
        this.i = 1;
        this.first = true;
        this.nextString = false;
    }
    
    public void getParameterValue() {
        try {
            int int1 = 14;
            String parameter = "Sanserif";
            BufferedReader bufferedReader = null;
            try {
                URL url = new URL(this.getCodeBase(), "InfoText.txt");
                try {
                    url = new URL(this.getParameter("filename"));
                }
                catch (Exception ex) {
                    System.out.println(ex.toString());
                }
                bufferedReader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
            }
            catch (MalformedURLException ex14) {}
            catch (Exception ex2) {
                System.out.println(ex2.toString());
            }
            try {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    ++this.noOfText;
                    final StringTokenizer stringTokenizer = new StringTokenizer(line, "|");
                    this.strHead[this.noOfText] = new String(stringTokenizer.nextToken());
                    this.strDesc[this.noOfText] = new String(stringTokenizer.nextToken());
                    this.strLink[this.noOfText] = new String(stringTokenizer.nextToken());
                }
            }
            catch (Exception ex3) {
                System.out.println(ex3.toString());
            }
            try {
                this.typingSpeed = Long.parseLong(this.getParameter("typingspeed"));
            }
            catch (Exception ex4) {
                System.out.println(ex4.toString());
            }
            try {
                this.delayTime = Long.parseLong(this.getParameter("delaytime"));
            }
            catch (Exception ex5) {
                System.out.println(ex5.toString());
            }
            try {
                int1 = Integer.parseInt(this.getParameter("fontsize"));
            }
            catch (Exception ex6) {
                System.out.println(ex6.toString());
            }
            try {
                parameter = this.getParameter("fontname");
            }
            catch (Exception ex7) {
                System.out.println(ex7.toString());
            }
            this.font = new Font(parameter, 1, int1);
            try {
                this.targetWindow = this.getParameter("targetwindow");
            }
            catch (Exception ex8) {
                System.out.println(ex8.toString());
            }
            try {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(this.getParameter("headbcolor"), ",");
                this.headBColor = new Color(Integer.parseInt(stringTokenizer2.nextToken()), Integer.parseInt(stringTokenizer2.nextToken()), Integer.parseInt(stringTokenizer2.nextToken()));
            }
            catch (Exception ex9) {
                System.out.println(ex9.toString());
            }
            try {
                final StringTokenizer stringTokenizer3 = new StringTokenizer(this.getParameter("descbcolor"), ",");
                this.descBColor = new Color(Integer.parseInt(stringTokenizer3.nextToken()), Integer.parseInt(stringTokenizer3.nextToken()), Integer.parseInt(stringTokenizer3.nextToken()));
            }
            catch (Exception ex10) {
                System.out.println(ex10.toString());
            }
            try {
                final StringTokenizer stringTokenizer4 = new StringTokenizer(this.getParameter("headtextcolor"), ",");
                this.headTextColor = new Color(Integer.parseInt(stringTokenizer4.nextToken()), Integer.parseInt(stringTokenizer4.nextToken()), Integer.parseInt(stringTokenizer4.nextToken()));
            }
            catch (Exception ex11) {
                System.out.println(ex11.toString());
            }
            try {
                final StringTokenizer stringTokenizer5 = new StringTokenizer(this.getParameter("desctextcolor"), ",");
                this.descTextColor = new Color(Integer.parseInt(stringTokenizer5.nextToken()), Integer.parseInt(stringTokenizer5.nextToken()), Integer.parseInt(stringTokenizer5.nextToken()));
            }
            catch (Exception ex12) {
                System.out.println(ex12.toString());
            }
        }
        catch (Exception ex13) {
            System.out.println(ex13.toString());
        }
    }
    
    public void init() {
        this.getParameterValue();
        this.setBackground(this.descBColor);
        this.setCursor(new Cursor(12));
        this.addMouseListener(this);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        try {
            this.getAppletContext().showDocument(new URL(this.strLink[this.i]), this.targetWindow);
        }
        catch (MalformedURLException ex) {}
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.showStatus(this.strLink[this.i]);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.showStatus("");
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        if (this.first) {
            graphics.setColor(this.headBColor);
            graphics.fillRect(0, 0, this.size().width / 4, this.size().height);
            graphics.setColor(this.descBColor);
            graphics.fillRect(0, this.size().width / 4, this.size().width / 4 * 3, this.size().height);
        }
        graphics.setFont(this.font);
        graphics.setColor(this.headTextColor);
        try {
            graphics.drawString(this.txtHead, this.bounds().x + 2, 20);
            graphics.setColor(this.descTextColor);
            graphics.drawString(this.txtDesc, this.size().width / 4 + 2, 20);
        }
        catch (NullPointerException ex) {
            System.out.println(ex.toString());
        }
    }
    
    public void run() {
        while (true) {
            this.runApplet();
        }
    }
    
    public synchronized void runApplet() {
        this.i = 1;
        while (this.i <= this.noOfText) {
            for (int i = 1; i <= this.strDesc[this.i].length(); ++i) {
                this.nextString = false;
                if (i <= this.strHead[this.i].length()) {
                    this.txtHead = this.strHead[this.i].substring(0, i);
                }
                this.txtDesc = this.strDesc[this.i].substring(0, i);
                try {
                    Thread.sleep(this.typingSpeed);
                }
                catch (InterruptedException ex) {}
                this.repaint();
            }
            try {
                Thread.sleep(this.delayTime);
            }
            catch (InterruptedException ex2) {}
            final Graphics graphics = this.getGraphics();
            graphics.setColor(this.headBColor);
            graphics.fillRect(0, 0, this.size().width / 4, this.size().height);
            graphics.setColor(this.descBColor);
            graphics.fillRect(this.size().width / 4, 0, this.size().width / 4 * 3, this.size().height);
            ++this.i;
        }
    }
    
    public void start() {
        if (this.t == null) {
            (this.t = new Thread(this)).start();
        }
        else {
            this.t.resume();
        }
    }
    
    public void stop() {
        if (this.t != null) {
            this.t.suspend();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
