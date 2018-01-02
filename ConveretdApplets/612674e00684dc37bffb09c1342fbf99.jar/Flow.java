import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Button;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Flow extends Applet implements Runnable, MouseListener
{
    int sven;
    int FrontX;
    int turtle;
    int asok;
    int AppletWidth;
    int AppletHeight;
    int speed;
    int drift;
    int PicPauseTime;
    Thread runner;
    Image BackWater;
    Image workspace;
    Image[] FrontImg;
    Graphics offscreen;
    MediaTracker trackR;
    int picnum;
    int totalpics;
    int nextpic;
    boolean GoTurtleGo;
    boolean MovinXcomplete;
    boolean isSinglePic;
    Button vButton;
    
    public Flow() {
        this.turtle = 0;
        this.speed = 20;
        this.drift = 1;
        this.PicPauseTime = 200;
        this.FrontImg = new Image[6];
        this.trackR = new MediaTracker(this);
        this.picnum = 0;
        this.nextpic = 1;
        this.GoTurtleGo = true;
        this.isSinglePic = false;
        this.vButton = new Button("applet by virtig01");
    }
    
    public void init() {
        this.showStatus("Flow loading...");
        this.addMouseListener(this);
        this.vButton.setForeground(Color.green);
        this.vButton.setBackground(Color.black);
        this.add(this.vButton);
        this.AppletWidth = this.getSize().width;
        this.AppletHeight = this.getSize().height;
        this.workspace = this.createImage(this.AppletWidth, this.AppletHeight);
        this.offscreen = this.workspace.getGraphics();
        final String[] array = new String[6];
        int totalpics;
        for (totalpics = 0; totalpics < 6 && this.getParameter("image" + (totalpics + 1)) != null; ++totalpics) {
            array[totalpics] = this.getParameter("image" + (totalpics + 1));
        }
        this.totalpics = totalpics;
        if (this.totalpics == 1) {
            this.isSinglePic = true;
        }
        final String s = (this.getParameter("BackWater") != null) ? this.getParameter("BackWater") : "BackWater.gif";
        for (int i = 0; i < totalpics; ++i) {
            this.FrontImg[i] = this.getImage(this.getCodeBase(), array[i]);
            this.trackR.addImage(this.FrontImg[i], i + 1);
        }
        this.BackWater = this.getImage(this.getCodeBase(), s);
        this.trackR.addImage(this.BackWater, 0);
        try {
            this.trackR.waitForAll();
        }
        catch (InterruptedException ex) {}
        final String parameter = this.getParameter("FlowSpeed");
        if (parameter != null) {
            this.speed = Integer.parseInt(parameter);
        }
        final String parameter2 = this.getParameter("DriftSpeed");
        if (parameter2 != null) {
            this.drift = Integer.parseInt(parameter2);
        }
        final String parameter3 = this.getParameter("pause");
        if (parameter3 != null) {
            this.PicPauseTime = Integer.parseInt(parameter3);
        }
        this.asok = this.AppletWidth;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.showStatus("");
        try {
            this.getAppletContext().showDocument(new URL("http://applets.virtig01.cjb.net/Flow/"), "_blank");
        }
        catch (MalformedURLException ex) {}
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.vButton.setVisible(true);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.vButton.setVisible(false);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        if (this.trackR.isErrorAny() | this.totalpics < 1) {
            graphics.drawString("Missing image(s) or image parameter", 5, 10);
        }
        else {
            this.offscreen.drawImage(this.BackWater, this.sven, 0, null);
            this.offscreen.drawImage(this.BackWater, this.asok, 0, null);
            this.offscreen.drawImage(this.FrontImg[this.picnum], this.FrontX, 0, null);
            this.offscreen.drawImage(this.FrontImg[this.nextpic], this.FrontX + this.AppletWidth, 0, null);
            graphics.drawImage(this.workspace, 0, 0, this);
        }
    }
    
    public void run() {
        while (true) {
            this.repaint();
            try {
                Thread.sleep(this.speed);
            }
            catch (InterruptedException ex) {}
            --this.sven;
            --this.asok;
            if (this.sven <= this.AppletWidth * -1) {
                this.sven = this.AppletWidth;
            }
            if (this.asok <= this.AppletWidth * -1) {
                this.asok = this.AppletWidth;
            }
            if (!this.isSinglePic) {
                if (this.GoTurtleGo) {
                    ++this.turtle;
                }
                if (this.turtle <= this.PicPauseTime * (this.picnum + 1)) {
                    continue;
                }
                this.GoTurtleGo = false;
                if (this.FrontX > this.AppletWidth * -1) {
                    this.FrontX -= this.drift;
                }
                else {
                    this.FrontX = 0;
                    this.MovinXcomplete = true;
                }
                if (!this.MovinXcomplete) {
                    continue;
                }
                if (this.picnum < this.totalpics - 1) {
                    ++this.picnum;
                    if (this.picnum == this.totalpics - 1) {
                        this.nextpic = 0;
                    }
                    else {
                        ++this.nextpic;
                    }
                    this.GoTurtleGo = true;
                    this.MovinXcomplete = false;
                }
                else {
                    this.picnum = 0;
                    this.nextpic = 1;
                    this.turtle = 0;
                    this.GoTurtleGo = true;
                    this.MovinXcomplete = false;
                }
            }
        }
    }
    
    public void start() {
        this.vButton.setVisible(false);
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
