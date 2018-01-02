import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class RectButton extends ButtonCtrl
{
    public RectButton(final String strName, final Image img, final boolean bAutoPress, final Rosa2000 applet) {
        super(strName, bAutoPress, applet);
        super.m_Img = img;
        this.init();
        if (Rosa2000.isDebugMode()) {
            System.out.println("RectButton Constructor");
        }
    }
    
    public RectButton(final String strName, final Image img, final int nXSize, final int nYSize, final boolean bAutoPress, final Rosa2000 applet) {
        super(strName, nXSize, nYSize, bAutoPress, applet);
        super.m_Img = img;
        this.init();
        if (Rosa2000.isDebugMode()) {
            System.out.println("RectButton Constructor");
        }
    }
    
    protected void init() {
        if (super.m_nXSize == 0) {
            super.m_nXSize = super.m_Img.getWidth(super.m_applet) + 10;
        }
        if (super.m_nYSize == 0) {
            super.m_nYSize = super.m_Img.getHeight(super.m_applet) + 10;
        }
        super.init();
    }
    
    public void paint(final Graphics g) {
        if (super.m_bPressed) {
            g.setColor(Color.black);
            g.drawRect(0, 0, super.m_nXSize - 1, super.m_nYSize - 1);
            g.setColor(new Color(120, 120, 120));
            g.drawLine(1, 1, super.m_nXSize - 2, 1);
            g.drawLine(1, 1, 1, super.m_nYSize - 2);
            g.setColor(new Color(160, 160, 160));
            g.drawLine(2, 2, super.m_nXSize - 3, 2);
            g.drawLine(2, 2, 2, super.m_nYSize - 3);
            g.setColor(new Color(200, 200, 200));
            g.drawLine(2, super.m_nYSize - 2, super.m_nXSize - 2, super.m_nYSize - 2);
            g.drawLine(super.m_nXSize - 2, 2, super.m_nXSize - 2, super.m_nYSize - 2);
            g.setColor(new Color(240, 240, 240));
            g.drawLine(3, super.m_nYSize - 3, super.m_nXSize - 3, super.m_nYSize - 3);
            g.drawLine(super.m_nXSize - 3, 3, super.m_nXSize - 3, super.m_nYSize - 3);
            g.drawImage(super.m_Img, 5, 5, super.m_nXSize - 8, super.m_nYSize - 8, this);
        }
        else {
            g.setColor(Color.black);
            g.drawRect(0, 0, super.m_nXSize - 1, super.m_nYSize - 1);
            g.setColor(new Color(200, 200, 200));
            g.drawLine(1, 1, super.m_nXSize - 3, 1);
            g.drawLine(1, 1, 1, super.m_nYSize - 3);
            g.setColor(new Color(240, 240, 240));
            g.drawLine(2, 2, super.m_nXSize - 4, 2);
            g.drawLine(2, 2, 2, super.m_nYSize - 4);
            g.setColor(new Color(120, 120, 120));
            g.drawLine(1, super.m_nYSize - 2, super.m_nXSize - 2, super.m_nYSize - 2);
            g.drawLine(super.m_nXSize - 2, 1, super.m_nXSize - 2, super.m_nYSize - 2);
            g.setColor(new Color(160, 160, 160));
            g.drawLine(2, super.m_nYSize - 3, super.m_nXSize - 3, super.m_nYSize - 3);
            g.drawLine(super.m_nXSize - 3, 2, super.m_nXSize - 3, super.m_nYSize - 3);
            g.drawImage(super.m_Img, 4, 4, super.m_nXSize - 8, super.m_nYSize - 8, this);
        }
    }
}
