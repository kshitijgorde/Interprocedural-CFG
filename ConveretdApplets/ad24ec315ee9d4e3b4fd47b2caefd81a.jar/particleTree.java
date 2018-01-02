import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class particleTree extends Applet implements Runnable
{
    fPointTree root;
    Image im;
    Graphics offscreen;
    Thread mainThread;
    int width;
    int height;
    int iteration;
    double kVY;
    double kVX;
    int kMaxIteration;
    double kChanceOfBranchPoint;
    double kScaleAtBranch;
    
    public void init() {
        this.resize(this.width, this.height);
        try {
            this.im = this.createImage(this.width, this.height);
            this.offscreen = this.im.getGraphics();
        }
        catch (Exception ex) {
            this.offscreen = null;
        }
        this.root = new fPointTree(this.width / 2.0, this.height, this.kVX, this.kVY, null, null);
    }
    
    public void paintApplet(final Graphics graphics, final fPointTree fPointTree) {
        graphics.setColor(Color.blue);
        if (fPointTree.left != null) {
            graphics.drawLine((int)fPointTree.x, (int)fPointTree.y, (int)fPointTree.left.x, (int)fPointTree.left.y);
            this.paintApplet(graphics, fPointTree.left);
        }
        if (fPointTree.right != null) {
            graphics.drawLine((int)fPointTree.x, (int)fPointTree.y, (int)fPointTree.right.x, (int)fPointTree.right.y);
            this.paintApplet(graphics, fPointTree.right);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.offscreen != null) {
            this.offscreen.setColor(Color.white);
            this.offscreen.fillRect(0, 0, this.width, this.height);
            this.paintApplet(this.offscreen, this.root);
            graphics.drawImage(this.im, 0, 0, this);
            return;
        }
        this.paintApplet(graphics, this.root);
    }
    
    public void start() {
        if (this.mainThread == null) {
            (this.mainThread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.mainThread.stop();
        this.mainThread = null;
    }
    
    public void AddOneLevel(final fPointTree fPointTree) {
        if (fPointTree.left != null || fPointTree.right != null) {
            if (fPointTree.left != null) {
                this.AddOneLevel(fPointTree.left);
            }
            if (fPointTree.right != null) {
                this.AddOneLevel(fPointTree.right);
            }
            return;
        }
        if (Math.random() < this.kChanceOfBranchPoint) {
            fPointTree.left = new fPointTree(fPointTree.x + fPointTree.vX, fPointTree.y + fPointTree.vY, this.kScaleAtBranch * (fPointTree.vX + Math.max(Math.abs(fPointTree.vX), Math.abs(fPointTree.vY)) * (Math.random() * 0.5 - 0.25)), this.kScaleAtBranch * (fPointTree.vY + Math.max(Math.abs(fPointTree.vX), Math.abs(fPointTree.vY)) * (Math.random() * 0.5 - 0.25)), null, null);
            fPointTree.right = new fPointTree(fPointTree.x + fPointTree.vX, fPointTree.y + fPointTree.vY, this.kScaleAtBranch * (fPointTree.vX + Math.max(Math.abs(fPointTree.vX), Math.abs(fPointTree.vY)) * (Math.random() * 0.5 - 0.25)), this.kScaleAtBranch * (fPointTree.vY + Math.max(Math.abs(fPointTree.vX), Math.abs(fPointTree.vY)) * (Math.random() * 0.5 - 0.25)), null, null);
            return;
        }
        fPointTree.left = new fPointTree(fPointTree.x + fPointTree.vX, fPointTree.y + fPointTree.vY, fPointTree.vX + Math.max(Math.abs(fPointTree.vX), Math.abs(fPointTree.vY)) * (Math.random() * 0.5 - 0.25), fPointTree.vY + Math.max(Math.abs(fPointTree.vX), Math.abs(fPointTree.vY)) * (Math.random() * 0.5 - 0.25), null, null);
    }
    
    public void run() {
        while (this.mainThread != null) {
            try {
                Thread.sleep(200L);
            }
            catch (InterruptedException ex) {}
            if (this.iteration < this.kMaxIteration) {
                this.repaint();
                this.AddOneLevel(this.root);
            }
            else if (this.iteration > 1.5 * this.kMaxIteration) {
                this.iteration = 0;
                this.root = new fPointTree(this.width / 2.0, this.height, this.kVX, this.kVY, null, null);
                this.repaint();
                this.AddOneLevel(this.root);
            }
            ++this.iteration;
        }
        this.mainThread = null;
    }
    
    public particleTree() {
        this.width = 200;
        this.height = 300;
        this.kVY = -15.0;
        this.kMaxIteration = 30;
        this.kChanceOfBranchPoint = 0.3;
        this.kScaleAtBranch = 0.7;
    }
}
