import java.awt.event.ActionEvent;
import java.util.StringTokenizer;
import java.awt.event.MouseEvent;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.io.IOException;
import java.net.UnknownHostException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.awt.MediaTracker;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.awt.Color;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class eGraffiti extends Applet implements MouseMotionListener, MouseListener, Runnable, ActionListener
{
    Thread t;
    Graphics grBuffer;
    int height;
    int width;
    Image imgBuffer;
    Image tools;
    Image imgCanvas;
    Graphics canvas;
    Image imgTopLayer;
    Graphics topLayer;
    Button btnEnter;
    TextField txtNick;
    Label lblPrompt;
    boolean sprFlip;
    int whereMX;
    int whereMY;
    Color palC;
    int tool;
    private Socket clientsocket;
    private DataOutputStream out;
    private DataInputStream in;
    String userInput;
    BufferedReader stdIn;
    String msg;
    clientThread cT;
    boolean cts;
    boolean mouseDownn;
    boolean drawingLine;
    boolean drawingSquare;
    boolean drawingSquareF;
    boolean drawingCircle;
    boolean drawingCircleF;
    int mouseX;
    int mouseY;
    int lineSLX;
    int lineSLY;
    int squareSX;
    int squareSY;
    boolean getNick;
    String myNick;
    String[] nicks;
    int numNicks;
    
    public eGraffiti() {
        this.sprFlip = false;
        this.getNick = true;
        this.myNick = "";
        this.nicks = new String[1000];
        this.numNicks = 0;
    }
    
    static int randomNumber(final int n, final int n2) {
        return (int)(Math.random() * (n2 - n + 1) + n);
    }
    
    public void init() {
        this.tool = 0;
        this.mouseDownn = false;
        this.height = this.size().height;
        this.width = this.size().width;
        final String parameter = this.getParameter("serverIP");
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.add(this.lblPrompt = new Label("Enter your nickname:"));
        this.add(this.txtNick = new TextField(15));
        this.add(this.btnEnter = new Button("Enter"));
        this.btnEnter.addActionListener(this);
        this.imgBuffer = this.createImage(this.width, this.height);
        this.grBuffer = this.imgBuffer.getGraphics();
        this.imgCanvas = this.createImage(600, 600);
        this.canvas = this.imgCanvas.getGraphics();
        this.imgTopLayer = this.createImage(600, 600);
        this.topLayer = this.imgTopLayer.getGraphics();
        this.canvas.setColor(Color.white);
        this.canvas.fillRect(0, 0, 600, 600);
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(this.tools = this.getImage(this.getCodeBase(), "tools.gif"), 1);
        try {
            mediaTracker.waitForID(1);
        }
        catch (InterruptedException ex2) {
            return;
        }
        this.palC = Color.green;
        try {
            this.clientsocket = new Socket(parameter, 8754);
            this.out = new DataOutputStream(this.clientsocket.getOutputStream());
            this.in = new DataInputStream(this.clientsocket.getInputStream());
            this.stdIn = new BufferedReader(new InputStreamReader(System.in));
            (this.cT = new clientThread(this)).start();
        }
        catch (UnknownHostException ex3) {
            System.err.println("Don't know about host: chester.");
        }
        catch (IOException ex4) {
            System.err.println("Couldn't get I/O for the connection to: chester.");
        }
        catch (SecurityException ex) {
            System.out.println("security exceptoin" + ex);
        }
        this.getNick = true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.getNick) {
            this.grBuffer.setFont(new Font("Caligula", 1, 23));
            this.grBuffer.setColor(Color.blue);
            this.grBuffer.drawString("eGraffiti by XdebugX", 280, 220);
            this.grBuffer.drawString("xdebugx@hotmail.com", 270, 245);
            this.grBuffer.drawString("http://xdebugx.deltaarc.net", 249, 270);
        }
        else {
            this.grBuffer.setColor(Color.white);
            this.grBuffer.fillRect(600, 0, 200, 600);
            this.grBuffer.setFont(new Font("Caligula", 1, 12));
            this.grBuffer.drawImage(this.tools, 0, 0, this);
            this.grBuffer.setColor(Color.red);
            this.grBuffer.fillRect(0, 100, 25, 20);
            this.grBuffer.setColor(Color.blue);
            this.grBuffer.fillRect(25, 100, 25, 20);
            this.grBuffer.setColor(Color.white);
            this.grBuffer.fillRect(0, 120, 25, 20);
            this.grBuffer.setColor(Color.gray);
            this.grBuffer.fillRect(25, 120, 25, 20);
            this.grBuffer.setColor(Color.black);
            this.grBuffer.fillRect(0, 140, 25, 20);
            this.grBuffer.setColor(Color.pink);
            this.grBuffer.fillRect(25, 140, 25, 20);
            this.grBuffer.setColor(Color.orange);
            this.grBuffer.fillRect(0, 160, 25, 20);
            this.grBuffer.setColor(Color.yellow);
            this.grBuffer.fillRect(25, 160, 25, 20);
            this.grBuffer.setColor(Color.green);
            this.grBuffer.fillRect(0, 180, 25, 20);
            this.grBuffer.setColor(Color.magenta);
            this.grBuffer.fillRect(25, 180, 25, 20);
            this.grBuffer.setColor(Color.cyan);
            this.grBuffer.fillRect(0, 200, 25, 20);
            this.grBuffer.setColor(Color.lightGray);
            this.grBuffer.fillRect(25, 200, 25, 20);
            this.grBuffer.setColor(Color.black);
            this.grBuffer.drawRect(12, 225, 26, 21);
            this.grBuffer.setColor(this.palC);
            this.grBuffer.fillRect(13, 226, 25, 20);
            this.grBuffer.drawImage(this.imgTopLayer, 50, 0, this);
            this.grBuffer.setColor(Color.green);
            this.grBuffer.drawRect(49, 0, 601, 599);
            this.grBuffer.setColor(Color.black);
            for (int i = 0; i < this.numNicks; ++i) {
                this.grBuffer.drawString(this.nicks[i], 655, 15 + i * 15);
            }
            this.topLayer.drawImage(this.imgCanvas, 0, 0, this);
        }
        graphics.drawImage(this.imgBuffer, 0, 0, this);
    }
    
    public void run() {
        while (this.t != null) {
            if (!this.getNick) {
                if (this.tool == 2 && this.mouseDownn) {
                    this.canvas.setColor(this.palC);
                }
                if (this.drawingLine && this.mouseX > 50 && this.mouseX < 650) {
                    this.topLayer.setColor(this.palC);
                    this.topLayer.drawLine(this.lineSLX, this.lineSLY, this.mouseX - 50, this.mouseY);
                }
                if (this.drawingSquare && this.mouseX > 50 && this.mouseX < 650) {
                    this.topLayer.setColor(this.palC);
                    this.topLayer.drawRect(this.squareSX, this.squareSY, this.mouseX - 50 - this.squareSX, this.mouseY - this.squareSY);
                }
                if (this.drawingSquareF && this.mouseX > 50 && this.mouseX < 650) {
                    this.topLayer.setColor(this.palC);
                    this.topLayer.fillRect(this.squareSX, this.squareSY, this.mouseX - 50 - this.squareSX, this.mouseY - this.squareSY);
                }
                if (this.drawingCircle && this.mouseX > 50 && this.mouseX < 650) {
                    this.topLayer.setColor(this.palC);
                    this.topLayer.drawOval(this.squareSX, this.squareSY, this.mouseX - 50 - this.squareSX, this.mouseY - this.squareSY);
                }
                if (this.drawingCircleF && this.mouseX > 50 && this.mouseX < 650) {
                    this.topLayer.setColor(this.palC);
                    this.topLayer.fillOval(this.squareSX, this.squareSY, this.mouseX - 50 - this.squareSX, this.mouseY - this.squareSY);
                }
            }
            if (this.cts) {
                this.cT.start();
                this.cts = false;
            }
            this.repaint();
            try {
                Thread.sleep(50L);
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
        try {
            this.out.close();
            this.in.close();
            this.stdIn.close();
            this.clientsocket.close();
        }
        catch (UnknownHostException ex) {
            System.err.println("Don't know about host: chester.");
        }
        catch (IOException ex2) {
            System.err.println("Couldn't get I/O for the connection to: chester.");
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        return true;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.mouseX = mouseEvent.getX();
        this.mouseY = mouseEvent.getY();
        if (!this.getNick) {
            if (this.mouseX >= 0 && this.mouseX <= 50) {
                if (this.mouseX >= 0 && this.mouseX < 25 && this.mouseY >= 100 && this.mouseY <= 120) {
                    this.palC = Color.red;
                }
                if (this.mouseX >= 25 && this.mouseX < 50 && this.mouseY >= 100 && this.mouseY < 140) {
                    this.palC = Color.blue;
                }
                if (this.mouseX >= 0 && this.mouseX < 25 && this.mouseY >= 120 && this.mouseY < 140) {
                    this.palC = Color.white;
                }
                if (this.mouseX >= 25 && this.mouseX < 50 && this.mouseY >= 120 && this.mouseY < 140) {
                    this.palC = Color.gray;
                }
                if (this.mouseX >= 0 && this.mouseX < 25 && this.mouseY >= 140 && this.mouseY < 160) {
                    this.palC = Color.black;
                }
                if (this.mouseX >= 25 && this.mouseX < 50 && this.mouseY >= 140 && this.mouseY < 160) {
                    this.palC = Color.pink;
                }
                if (this.mouseX >= 0 && this.mouseX < 25 && this.mouseY >= 160 && this.mouseY < 180) {
                    this.palC = Color.orange;
                }
                if (this.mouseX >= 25 && this.mouseX < 50 && this.mouseY >= 160 && this.mouseY < 180) {
                    this.palC = Color.yellow;
                }
                if (this.mouseX >= 0 && this.mouseX < 25 && this.mouseY >= 180 && this.mouseY < 200) {
                    this.palC = Color.green;
                }
                if (this.mouseX >= 25 && this.mouseX < 50 && this.mouseY >= 180 && this.mouseY < 200) {
                    this.palC = Color.magenta;
                }
                if (this.mouseX >= 0 && this.mouseX < 25 && this.mouseY >= 200 && this.mouseY < 220) {
                    this.palC = Color.cyan;
                }
                if (this.mouseX >= 25 && this.mouseX < 50 && this.mouseY >= 200 && this.mouseY < 220) {
                    this.palC = Color.lightGray;
                }
                if (this.mouseX >= 0 && this.mouseX < 25 && this.mouseY >= 0 && this.mouseY < 25) {
                    this.tool = 1;
                }
                if (this.mouseX >= 25 && this.mouseX < 50 && this.mouseY >= 0 && this.mouseY < 25) {
                    this.tool = 0;
                }
                if (this.mouseX >= 0 && this.mouseX < 25 && this.mouseY >= 25 && this.mouseY < 50) {
                    this.tool = 2;
                }
                if (this.mouseX >= 25 && this.mouseX < 50 && this.mouseY >= 25 && this.mouseY < 50) {
                    this.tool = 3;
                }
                if (this.mouseX >= 0 && this.mouseX < 25 && this.mouseY >= 50 && this.mouseY < 75) {
                    this.tool = 4;
                }
                if (this.mouseX >= 25 && this.mouseX < 50 && this.mouseY >= 50 && this.mouseY < 75) {
                    this.tool = 5;
                }
                if (this.mouseX >= 0 && this.mouseX < 25 && this.mouseY >= 75 && this.mouseY < 100) {
                    this.tool = 6;
                }
                if (this.mouseX >= 25 && this.mouseX < 50 && this.mouseY >= 75 && this.mouseY < 100) {
                    this.tool = 7;
                }
            }
            if (this.mouseX > 50 && this.mouseX < 650) {
                if (this.tool == 0) {
                    this.canvas.setColor(this.palC);
                    this.canvas.fillRect(this.mouseX - 50, this.mouseY, 1, 1);
                    this.output("2^" + (this.mouseX - 50) + "^" + this.mouseY + "^1^1^" + this.giveCI(this.palC));
                }
                if (this.tool == 2) {
                    this.output("7^" + this.mouseX + "^" + this.mouseY + "^" + this.giveCI(this.palC));
                }
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.mouseX = mouseEvent.getX();
        this.mouseY = mouseEvent.getY();
        if (!this.getNick && this.mouseX > 50 && this.mouseX < 650) {
            if (this.tool == 1) {
                this.canvas.setColor(this.palC);
                this.canvas.fillRect(this.mouseX - 55, this.mouseY - 5, 10, 10);
                this.output("2^" + (this.mouseX - 55) + "^" + (this.mouseY - 5) + "^10^10^" + this.giveCI(this.palC));
            }
            if (this.tool == 0) {
                this.canvas.setColor(this.palC);
                this.canvas.drawLine(this.mouseX - 50, this.mouseY, this.whereMX - 50, this.whereMY);
                this.output("6^" + (this.mouseX - 50) + "^" + this.mouseY + "^" + (this.whereMX - 50) + "^" + this.whereMY + "^" + this.giveCI(this.palC));
                this.whereMX = this.mouseX;
                this.whereMY = this.mouseY;
            }
            if (this.tool == 2) {
                if (!this.sprFlip) {
                    this.sprFlip = true;
                }
                else {
                    this.sprFlip = false;
                }
                if (this.sprFlip) {
                    this.output("7^" + this.mouseX + "^" + this.mouseY + "^" + this.giveCI(this.palC));
                }
            }
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.mouseDownn = true;
        this.mouseX = mouseEvent.getX();
        this.mouseY = mouseEvent.getY();
        this.whereMX = mouseEvent.getX();
        this.whereMY = mouseEvent.getY();
        if (!this.getNick && this.mouseX > 50 && this.mouseX < 650) {
            if (this.tool == 1) {
                this.canvas.setColor(this.palC);
                this.canvas.fillRect(this.mouseX - 55, this.mouseY - 5, 10, 10);
                this.output("2^" + (this.mouseX - 55) + "^" + (this.mouseY - 5) + "^10^10^" + this.giveCI(this.palC));
            }
            if (this.tool == 3) {
                this.lineSLX = this.mouseX - 50;
                this.lineSLY = this.mouseY;
                this.drawingLine = true;
            }
            if (this.tool == 4) {
                this.squareSX = this.mouseX - 50;
                this.squareSY = this.mouseY;
                this.drawingSquare = true;
            }
            if (this.tool == 5) {
                this.squareSX = this.mouseX - 50;
                this.squareSY = this.mouseY;
                this.drawingSquareF = true;
            }
            if (this.tool == 6) {
                this.squareSX = this.mouseX - 50;
                this.squareSY = this.mouseY;
                this.drawingCircle = true;
            }
            if (this.tool == 7) {
                this.squareSX = this.mouseX - 50;
                this.squareSY = this.mouseY;
                this.drawingCircleF = true;
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.mouseDownn = false;
        this.mouseX = mouseEvent.getX();
        this.mouseY = mouseEvent.getY();
        if (!this.getNick) {
            if (this.drawingLine && this.mouseX > 50 && this.mouseX < 650) {
                this.canvas.setColor(this.palC);
                this.canvas.drawLine(this.lineSLX, this.lineSLY, this.mouseX - 50, this.mouseY);
                this.output("6^" + this.lineSLX + "^" + this.lineSLY + "^" + (this.mouseX - 50) + "^" + this.mouseY + "^" + this.giveCI(this.palC));
                this.drawingLine = false;
            }
            if (this.drawingSquare && this.mouseX > 50 && this.mouseX < 650) {
                this.canvas.setColor(this.palC);
                this.canvas.drawRect(this.squareSX, this.squareSY, this.mouseX - 50 - this.squareSX, this.mouseY - this.squareSY);
                this.drawingSquare = false;
                this.output("3^" + this.squareSX + "^" + this.squareSY + "^" + (this.mouseX - 50 - this.squareSX) + "^" + (this.mouseY - this.squareSY) + "^" + this.giveCI(this.palC));
            }
            if (this.drawingSquareF && this.mouseX > 50 && this.mouseX < 650) {
                this.canvas.setColor(this.palC);
                this.canvas.fillRect(this.squareSX, this.squareSY, this.mouseX - 50 - this.squareSX, this.mouseY - this.squareSY);
                this.drawingSquareF = false;
                this.output("2^" + this.squareSX + "^" + this.squareSY + "^" + (this.mouseX - 50 - this.squareSX) + "^" + (this.mouseY - this.squareSY) + "^" + this.giveCI(this.palC));
            }
            if (this.drawingCircle && this.mouseX > 50 && this.mouseX < 650) {
                this.canvas.setColor(this.palC);
                this.canvas.drawOval(this.squareSX, this.squareSY, this.mouseX - 50 - this.squareSX, this.mouseY - this.squareSY);
                this.drawingCircle = false;
                this.output("4^" + this.squareSX + "^" + this.squareSY + "^" + (this.mouseX - 50 - this.squareSX) + "^" + (this.mouseY - this.squareSY) + "^" + this.giveCI(this.palC));
            }
            if (this.drawingCircleF && this.mouseX > 50 && this.mouseX < 650) {
                this.canvas.setColor(this.palC);
                this.canvas.fillOval(this.squareSX, this.squareSY, this.mouseX - 50 - this.squareSX, this.mouseY - this.squareSY);
                this.drawingCircleF = false;
                this.output("5^" + this.squareSX + "^" + this.squareSY + "^" + (this.mouseX - 50 - this.squareSX) + "^" + (this.mouseY - this.squareSY) + "^" + this.giveCI(this.palC));
            }
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void output(final String s) {
        try {
            this.out.writeUTF(s);
        }
        catch (UnknownHostException ex2) {
            System.err.println("Don't know about host: chester.");
            System.exit(1);
        }
        catch (IOException ex3) {
            System.err.println("Couldn't get I/O for the connection to: chester.");
        }
        catch (NullPointerException ex) {
            System.err.println(ex);
        }
    }
    
    public void readi() {
        boolean b = false;
        String utf = "";
        final String[] array = new String[5000];
        try {
            utf = this.in.readUTF();
        }
        catch (UnknownHostException ex2) {
            System.err.println("Don't know about host: chester.");
        }
        catch (IOException ex3) {
            System.err.println("Couldn't get I/O for the connection to: chester.");
            b = true;
        }
        catch (IndexOutOfBoundsException ex4) {
            b = true;
            this.cts = true;
        }
        if (!b) {
            final StringTokenizer stringTokenizer = new StringTokenizer(utf, "^", false);
            int n = 0;
            while (stringTokenizer.hasMoreTokens()) {
                array[n] = stringTokenizer.nextToken();
                ++n;
            }
            final int n2 = n;
            if (n2 > 1) {
                if (array[0].equals("1")) {
                    int i;
                    for (i = 1; i < n2; ++i) {
                        this.nicks[i - 1] = array[i];
                    }
                    this.numNicks = i - 1;
                }
                try {
                    int int1 = 0;
                    int int2 = 0;
                    int int3 = 0;
                    int int4 = 0;
                    int int5 = 0;
                    int int6 = 0;
                    int int7 = 0;
                    int int8 = 0;
                    if (array[0].equals("2") || array[0].equals("3") || array[0].equals("4") || array[0].equals("5") || array[0].equals("6") || array[0].equals("7")) {
                        if (array[1] == null) {
                            array[1] = "0";
                        }
                        if (array[2] == null) {
                            array[2] = "0";
                        }
                        if (array[3] == null) {
                            array[3] = "0";
                        }
                        if (array[4] == null) {
                            array[4] = "0";
                        }
                        if (array[5] == null) {
                            array[5] = "0";
                        }
                        int1 = Integer.parseInt(array[1]);
                        int2 = Integer.parseInt(array[2]);
                        int3 = Integer.parseInt(array[3]);
                        int4 = Integer.parseInt(array[4]);
                        int5 = Integer.parseInt(array[1]);
                        int6 = Integer.parseInt(array[2]);
                        int7 = Integer.parseInt(array[3]);
                        int8 = Integer.parseInt(array[5]);
                        if (int1 < 0 || int1 > 600) {
                            int1 = 0;
                        }
                        if (int2 < 0 || int2 > 600) {
                            int2 = 0;
                        }
                        if (int3 < 0 || int3 > 600) {
                            int3 = 0;
                        }
                        if (int4 < 0 || int3 > 600) {
                            int4 = 0;
                        }
                        if (int5 < 0 || int5 > 600) {
                            int5 = 0;
                        }
                        if (int6 < 0 || int6 > 600) {
                            int6 = 0;
                        }
                    }
                    if (array[0].equals("2")) {
                        this.canvas.setColor(this.returnCI(int8));
                        this.canvas.fillRect(int1, int2, int3, int4);
                    }
                    if (array[0].equals("3")) {
                        this.canvas.setColor(this.returnCI(int8));
                        this.canvas.drawRect(int1, int2, int3, int4);
                    }
                    if (array[0].equals("4")) {
                        this.canvas.setColor(this.returnCI(int8));
                        this.canvas.drawOval(int1, int2, int3, int4);
                    }
                    if (array[0].equals("5")) {
                        this.canvas.setColor(this.returnCI(int8));
                        this.canvas.fillOval(int1, int2, int3, int4);
                    }
                    if (array[0].equals("6")) {
                        this.canvas.setColor(this.returnCI(int8));
                        this.canvas.drawLine(int1, int2, int3, int4);
                    }
                    if (array[0].equals("7")) {
                        this.canvas.setColor(this.returnCI(int7));
                        for (int j = 0; j < 40; ++j) {
                            int n3;
                            if (randomNumber(0, 1) == 0) {
                                n3 = randomNumber(15, 35);
                            }
                            else {
                                n3 = randomNumber(0, 50);
                            }
                            int n4;
                            if (randomNumber(0, 1) == 0) {
                                n4 = randomNumber(15, 35);
                            }
                            else {
                                n4 = randomNumber(0, 50);
                            }
                            this.canvas.fillRect(int5 - 75 + n3, int6 - 25 + n4, 1, 1);
                        }
                    }
                }
                catch (Exception ex) {
                    System.out.println(ex);
                    this.cts = true;
                }
            }
        }
    }
    
    public int giveCI(final Color color) {
        int n = 0;
        if (color == Color.red) {
            n = 0;
        }
        if (color == Color.blue) {
            n = 1;
        }
        if (color == Color.white) {
            n = 2;
        }
        if (color == Color.green) {
            n = 3;
        }
        if (color == Color.yellow) {
            n = 4;
        }
        if (color == Color.cyan) {
            n = 5;
        }
        if (color == Color.magenta) {
            n = 6;
        }
        if (color == Color.lightGray) {
            n = 7;
        }
        if (color == Color.black) {
            n = 8;
        }
        if (color == Color.orange) {
            n = 9;
        }
        if (color == Color.gray) {
            n = 10;
        }
        if (color == Color.pink) {
            n = 11;
        }
        return n;
    }
    
    public Color returnCI(final int n) {
        Color color = Color.white;
        if (n == 0) {
            color = Color.red;
        }
        if (n == 1) {
            color = Color.blue;
        }
        if (n == 2) {
            color = Color.white;
        }
        if (n == 3) {
            color = Color.green;
        }
        if (n == 4) {
            color = Color.yellow;
        }
        if (n == 5) {
            color = Color.cyan;
        }
        if (n == 6) {
            color = Color.magenta;
        }
        if (n == 7) {
            color = Color.lightGray;
        }
        if (n == 8) {
            color = Color.black;
        }
        if (n == 9) {
            color = Color.orange;
        }
        if (n == 10) {
            color = Color.gray;
        }
        if (n == 11) {
            color = Color.pink;
        }
        return color;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.btnEnter) {
            this.myNick = this.txtNick.getText();
            if (this.myNick.equals("")) {
                this.myNick = "guest";
            }
            this.output("0^" + this.myNick);
            this.getNick = false;
            this.remove(this.btnEnter);
            this.remove(this.txtNick);
            this.remove(this.lblPrompt);
        }
    }
}
