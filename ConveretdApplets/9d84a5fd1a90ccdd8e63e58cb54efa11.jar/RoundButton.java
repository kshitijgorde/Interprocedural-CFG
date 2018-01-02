import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class RoundButton extends ButtonCtrl
{
    private Image m_ImagePress;
    
    public RoundButton(final String strName, final Image img, final Image impPress, final boolean bAutoPress, final Rosa2000 applet) {
        super(strName, bAutoPress, applet);
        super.m_Img = img;
        this.m_ImagePress = impPress;
        this.init();
        if (Rosa2000.isDebugMode()) {
            System.out.println("RoundButton Constructor");
        }
    }
    
    public RoundButton(final String strName, final Image img, final Image impPress, final int nXSize, final int nYSize, final boolean bAutoPress, final Rosa2000 applet) {
        super(strName, nXSize, nYSize, bAutoPress, applet);
        super.m_Img = img;
        this.m_ImagePress = impPress;
        this.init();
        if (Rosa2000.isDebugMode()) {
            System.out.println("RoundButton Constructor");
        }
    }
    
    public void paint(final Graphics g) {
        if (super.m_bPressed) {
            g.drawImage(this.m_ImagePress, 0, 0, this);
        }
        else {
            g.drawImage(super.m_Img, 0, 0, this);
        }
    }
}
