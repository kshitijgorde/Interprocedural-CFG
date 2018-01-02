// 
// Decompiled by Procyon v0.5.30
// 

package S1S;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Font;
import java.awt.Label;
import java.awt.Image;
import java.applet.Applet;

public class ImageTest extends Applet
{
    Image image1;
    private WtEntrySpin ivja1;
    private Label ivjLabel1;
    
    private WtEntrySpin geta1() {
        if (this.ivja1 == null) {
            try {
                (this.ivja1 = new WtEntrySpin()).setName("a1");
                this.ivja1.setBounds(227, 56, 75, 122);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivja1;
    }
    
    public String getAppletInfo() {
        return "S1S.ImageTest created using VisualAge for Java.";
    }
    
    private Label getLabel1() {
        if (this.ivjLabel1 == null) {
            try {
                (this.ivjLabel1 = new Label()).setName("Label1");
                this.ivjLabel1.setFont(new Font("dialog", 1, 16));
                this.ivjLabel1.setAlignment(1);
                this.ivjLabel1.setText("A Label");
                this.ivjLabel1.setBounds(126, 114, 125, 30);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivjLabel1;
    }
    
    private void handleException(final Throwable exception) {
    }
    
    public void init() {
        super.init();
        try {
            this.setName("ImageTest");
            this.setLayout(null);
            this.setSize(365, 259);
            this.add(this.getLabel1(), this.getLabel1().getName());
            this.add(this.geta1(), this.geta1().getName());
            this.image1 = this.getImage(this.getCodeBase(), "N22XP_SideView16.gif");
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    public static void main(final String[] args) {
        try {
            Frame frame;
            try {
                final Class aFrameClass = Class.forName("uvm.abt.edit.TestFrame");
                frame = aFrameClass.newInstance();
            }
            catch (Throwable t) {
                frame = new Frame();
            }
            final ImageTest aImageTest = new ImageTest();
            frame.add("Center", aImageTest);
            frame.setSize(aImageTest.getSize());
            aImageTest.init();
            aImageTest.start();
            frame.setVisible(true);
            aImageTest.destroy();
        }
        catch (Throwable t2) {
            System.err.println("Exception occurred in main() of java.applet.Applet");
        }
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.image1, 0, 0, this);
        g.drawImage(this.image1, 0, 300, 300, 120, this);
    }
    
    public ImageTest() {
        this.ivja1 = null;
        this.ivjLabel1 = null;
    }
}
