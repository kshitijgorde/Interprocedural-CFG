import java.net.MalformedURLException;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.DataOutputStream;
import java.net.Socket;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class hitCounter extends Applet implements Runnable
{
    Thread t;
    Graphics grBuffer;
    Image imgBuffer;
    int height;
    int width;
    int paintDelay;
    int numHits;
    int ones;
    int tens;
    int hund;
    int thou;
    int tenthou;
    int hunthou;
    int mil;
    int rot;
    int rot2;
    int rot3;
    int rot4;
    int rot5;
    int rot6;
    int rot7;
    boolean flipped;
    
    public hitCounter() {
        this.paintDelay = 0;
        this.numHits = 0;
        this.ones = 0;
        this.tens = 0;
        this.hund = 0;
        this.thou = 0;
        this.tenthou = 0;
        this.hunthou = 0;
        this.mil = 0;
        this.rot = 0;
        this.flipped = false;
    }
    
    static int randomNumber(final int n, final int n2) {
        return (int)(Math.random() * (n2 - n + 1) + n);
    }
    
    public void init() {
        this.height = 30;
        this.width = 125;
        this.imgBuffer = this.createImage(this.width, this.height);
        (this.grBuffer = this.imgBuffer.getGraphics()).setFont(new Font("Courier", 1, 25));
        this.sendData();
        this.newView();
    }
    
    public void paint(final Graphics graphics) {
        this.grBuffer.setColor(Color.black);
        this.grBuffer.fillRect(0, 0, this.width, this.height);
        this.grBuffer.setColor(Color.white);
        this.grBuffer.drawRect(0, 0, this.width, this.height);
        for (int i = 0; i < 6; ++i) {
            this.grBuffer.drawLine(i * (this.width / 6), 0, i * (this.width / 6), 30);
        }
        if (this.mil * 1000000 + this.hunthou * 100000 + this.tenthou * 10000 + this.thou * 1000 + this.hund * 100 + this.tens * 10 + this.ones < this.numHits) {
            this.grBuffer.drawString("" + this.ones, 105, 45 - this.rot);
            int n = this.rot - 30;
            if (n < 0) {
                n = 0;
            }
            this.grBuffer.drawString("" + (this.ones + 1), 105, 45 - n);
            this.rot += 2;
            if (this.rot >= 40) {
                this.flipped = true;
                this.rot = 10;
            }
            int n2 = this.rot2 - 20;
            if (n2 < 0) {
                n2 = 0;
            }
            this.grBuffer.drawString("" + this.tens, 83, 30 - this.rot2);
            this.grBuffer.drawString("" + (this.tens + 1), 83, 45 - n2);
            this.rot2 = this.ones * 4;
            int n3 = this.rot3 - 20;
            if (n3 < 0) {
                n3 = 0;
            }
            this.grBuffer.drawString("" + this.hund, 63, 30 - this.rot3);
            this.grBuffer.drawString("" + (this.hund + 1), 63, 45 - n3);
            this.rot3 = this.tens * 4;
            int n4 = this.rot4 - 20;
            if (n4 < 0) {
                n4 = 0;
            }
            this.grBuffer.drawString("" + this.thou, 43, 30 - this.rot4);
            this.grBuffer.drawString("" + (this.thou + 1), 43, 45 - n4);
            this.rot4 = this.hund * 4;
            int n5 = this.rot5 - 20;
            if (n5 < 0) {
                n5 = 0;
            }
            this.grBuffer.drawString("" + this.tenthou, 23, 30 - this.rot5);
            this.grBuffer.drawString("" + (this.tenthou + 1), 23, 45 - n5);
            this.rot5 = this.thou * 4;
            int n6 = this.rot6 - 20;
            if (n6 < 0) {
                n6 = 0;
            }
            this.grBuffer.drawString("" + this.hunthou, 3, 30 - this.rot6);
            this.grBuffer.drawString("" + (this.hunthou + 1), 3, 45 - n6);
            this.rot6 = this.tenthou * 4;
        }
        else {
            this.grBuffer.drawString("" + this.ones, 105, 25);
            this.grBuffer.drawString("" + this.tens, 83, 25);
            this.grBuffer.drawString("" + this.hund, 63, 25);
            this.grBuffer.drawString("" + this.thou, 43, 25);
            this.grBuffer.drawString("" + this.tenthou, 23, 25);
            this.grBuffer.drawString("" + this.hunthou, 3, 25);
        }
        graphics.drawImage(this.imgBuffer, 0, 0, this);
        this.paintDelay = 0;
    }
    
    private void sendData() {
        System.out.println("sending...");
        final String parameter = this.getParameter("IPAdd");
        final int int1 = Integer.parseInt(this.getParameter("PortNumber"));
        try {
            final DataOutputStream dataOutputStream = new DataOutputStream(new Socket(parameter, int1).getOutputStream());
            dataOutputStream.writeUTF("new hit");
            dataOutputStream.close();
        }
        catch (Exception ex) {
            System.out.println("Couldnt connect to server");
        }
    }
    
    private void newView() {
        final String parameter = this.getParameter("fileName");
        String string = "";
        try {
            final URL url = new URL(this.getCodeBase(), parameter);
            try {
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                int i;
                do {
                    i = bufferedReader.read();
                    final char c = (char)i;
                    if (c == 'X') {
                        this.numHits = Integer.parseInt(string);
                    }
                    string += c;
                } while (i != -1);
                bufferedReader.close();
            }
            catch (IOException ex) {}
        }
        catch (MalformedURLException ex2) {}
    }
    
    public void run() {
        while (true) {
            if (this.mil * 1000000 + this.hunthou * 100000 + this.tenthou * 10000 + this.thou * 1000 + this.hund * 100 + this.tens * 10 + this.ones < this.numHits) {
                if (this.flipped) {
                    this.flipped = false;
                    this.ones += 2;
                    if (this.numHits > 1000) {
                        ++this.tens;
                    }
                    if (this.numHits > 10000) {
                        ++this.hund;
                    }
                    if (this.numHits > 100000) {
                        ++this.thou;
                    }
                    if (this.ones > 9) {
                        ++this.tens;
                        this.ones = 0;
                    }
                    if (this.tens > 9) {
                        ++this.hund;
                        this.tens = 0;
                    }
                    if (this.hund > 9) {
                        ++this.thou;
                        this.hund = 0;
                    }
                    if (this.thou > 9) {
                        ++this.tenthou;
                        this.thou = 0;
                    }
                    if (this.tenthou > 9) {
                        ++this.hunthou;
                        this.tenthou = 0;
                    }
                    if (this.hunthou > 9) {
                        ++this.mil;
                        this.hunthou = 0;
                    }
                }
                this.repaint();
            }
            else {
                this.ones = this.numHits - this.numHits / 10 * 10;
                this.tens = (this.numHits - this.numHits / 100 * 100) / 10;
                this.hund = (this.numHits - this.numHits / 1000 * 1000) / 100;
                this.thou = (this.numHits - this.numHits / 10000 * 10000) / 1000;
                this.tenthou = (this.numHits - this.numHits / 100000 * 100000) / 10000;
                this.hunthou = this.numHits / 100000;
                ++this.paintDelay;
                if (this.paintDelay >= 4) {
                    this.repaint();
                    this.paintDelay = 0;
                }
            }
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void start() {
        if (this.t == null) {
            this.t = new Thread(this);
        }
        this.t.start();
    }
    
    public void stop() {
        this.t = null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
