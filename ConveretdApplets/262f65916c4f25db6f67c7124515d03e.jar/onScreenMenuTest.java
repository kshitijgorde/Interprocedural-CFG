import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.image.VolatileImage;
import java.awt.Graphics;
import java.awt.Component;
import UTIL.onScreenMenu;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class onScreenMenuTest extends Applet implements KeyListener, Runnable
{
    public Image offscreen;
    public Dimension dim;
    public onScreenMenu oSM;
    public String[][] menuTextArray;
    
    public void init() {
        this.dim = this.getSize();
        this.addKeyListener(this);
        this.a();
        this.menuTextArray = new String[2][3];
        this.menuTextArray[0][0] = "SNESonline Menu";
        this.menuTextArray[0][1] = "Boost";
        this.menuTextArray[0][2] = "Save";
        this.menuTextArray[1][0] = "Success";
        this.menuTextArray[1][1] = "OK";
        this.oSM = new onScreenMenu(this.dim, this.offscreen, this, this.menuTextArray);
    }
    
    private void a() {
        this.offscreen = this.getGraphicsConfiguration().createCompatibleVolatileImage(this.dim.width, this.dim.height);
    }
    
    public void paint(final Graphics graphics) {
        if (this.offscreen instanceof VolatileImage) {
            final VolatileImage volatileImage = (VolatileImage)this.offscreen;
            do {
                if (volatileImage.validate(this.getGraphicsConfiguration()) == 2) {
                    this.a();
                }
            } while (volatileImage.contentsLost());
        }
        graphics.drawImage(this.oSM.getImage(), 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void start() {
        new Thread(this).start();
    }
    
    public void run() {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 40) {
            this.oSM.moveDown();
            return;
        }
        if (keyEvent.getKeyCode() == 38) {
            this.oSM.moveUp();
            return;
        }
        Label_0267: {
            if (keyEvent.getKeyCode() == 10) {
                final int[][] menuPosition;
                final int length = (menuPosition = this.oSM.getMenuPosition()).length;
                final int length2 = menuPosition[length - 1].length;
                switch (length) {
                    case 1: {
                        switch (length2) {
                            case 1: {
                                this.oSM.switchMenu(new int[2][1]);
                                break Label_0267;
                            }
                            case 2: {
                                this.oSM.switchMenu(new int[2][1]);
                                break Label_0267;
                            }
                            default: {
                                System.out.println("Menu : " + length + " - Selection : " + length2);
                                break Label_0267;
                            }
                        }
                        break;
                    }
                    case 2: {
                        this.oSM.switchMenu(new int[1][1]);
                        break;
                    }
                    default: {
                        System.out.println("Menu : " + length + " - Selection : " + length2);
                        return;
                    }
                }
            }
            else {
                System.out.println("key : " + keyEvent);
            }
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
}
