import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.image.PixelGrabber;
import java.awt.Image;
import java.util.Vector;
import java.awt.event.KeyListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class LegendObj extends PointObj implements KeyListener
{
    private final String kstrLegendParam = "DYN_LEGEND";
    protected int m_alpha;
    protected int m_red;
    protected int m_green;
    protected int m_blue;
    protected Vector m_astrDesc;
    protected Vector m_anColor;
    protected Rosa2000 m_applet;
    
    public LegendObj(final Map parent, final Rosa2000 applet) {
        super(parent);
        this.m_alpha = 0;
        this.m_red = 0;
        this.m_green = 0;
        this.m_blue = 0;
        this.m_astrDesc = new Vector(5, 5);
        this.m_anColor = new Vector(5, 5);
        (this.m_applet = applet).addKeyListener(this);
        this.initLookupTable(applet);
    }
    
    protected void initLookupTable(final Rosa2000 applet) {
        final String strParamList = applet.getParameter("DYN_LEGEND");
        String strItem = new String();
        if (strParamList != null) {
            int nIndex = 0;
            int nPreviousIndex = 0;
            while ((nIndex = strParamList.indexOf("|", nIndex++)) != -1) {
                strItem = strParamList.substring(nPreviousIndex, nIndex);
                nPreviousIndex = ++nIndex;
                this.addItemInLookup(strItem);
            }
            if (nPreviousIndex < strParamList.length() - 1) {
                strItem = strParamList.substring(nPreviousIndex);
                this.addItemInLookup(strItem);
            }
        }
    }
    
    protected void addItemInLookup(final String strItem) {
        Integer color = null;
        String strDesc = null;
        boolean bError = true;
        String strValue = null;
        int nIndex = 0;
        int nPreviousIndex = 0;
        if ((nIndex = strItem.indexOf("=", nIndex++)) != -1) {
            strValue = strItem.substring(nPreviousIndex, nIndex);
            nPreviousIndex = ++nIndex;
            try {
                final int nColorValue = Integer.valueOf(strValue, 16) - 16777216;
                color = new Integer(nColorValue);
            }
            catch (NumberFormatException e) {
                System.err.println("Invalid color: " + strValue);
                return;
            }
            if (nPreviousIndex < strItem.length() - 1) {
                strDesc = strItem.substring(nPreviousIndex);
            }
            if (color != null && strDesc != null) {
                this.m_astrDesc.addElement(strDesc);
                this.m_anColor.addElement(color);
                bError = false;
            }
        }
        if (bError) {
            System.err.println("Invalid item " + strItem + " in the parameter " + "DYN_LEGEND");
        }
    }
    
    public String getSubmitValue() {
        return new String();
    }
    
    private void handleSinglePixel(final int pixel) {
        this.m_alpha = (pixel >> 24 & 0xFF);
        this.m_red = (pixel >> 16 & 0xFF);
        this.m_green = (pixel >> 8 & 0xFF);
        this.m_blue = (pixel & 0xFF);
        if (Rosa2000.isDebugMode()) {
            System.out.println("Alpha = " + this.m_alpha + " Red = " + this.m_red + " green = " + this.m_green + " Blue = " + this.m_blue + " pixel = " + pixel);
        }
    }
    
    public int getPixels(final Image img, final int x, final int y, final int w, final int h) {
        final int[] pixels = new int[w * h];
        final PixelGrabber pg = new PixelGrabber(img, x, y, w, h, pixels, 0, w);
        try {
            pg.grabPixels();
        }
        catch (InterruptedException e) {
            System.err.println("interrupted  waiting  for  pixels!");
            return 0;
        }
        if ((pg.status() & 0x80) != 0x0) {
            System.err.println("image  fetch  aborted  or  errored");
            return 0;
        }
        for (int j = 0; j < h; ++j) {
            final int i = 0;
            if (i < w) {
                return pixels[0];
            }
        }
        return 0;
    }
    
    public void mouseReleased(final MouseEvent evt) {
        super.x = evt.getPoint().x;
        super.y = evt.getPoint().y;
        final int nColorPixel = this.getPixels(super.m_map.getImage(), super.x, super.y, 1, 1);
        final String strDesc = this.getLookupString(new Integer(nColorPixel));
        if (strDesc != null) {
            this.m_applet.showText(strDesc);
        }
    }
    
    protected String getLookupString(final Integer color) {
        final int nIndex = this.m_anColor.indexOf(color);
        if (nIndex != -1) {
            return this.m_astrDesc.elementAt(nIndex);
        }
        return null;
    }
    
    public void destroyEvent() {
        super.m_map.removeMouseListener(this);
        this.m_applet.addKeyListener(this);
    }
    
    public void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == 27) {
            this.destroyEvent();
            super.m_map.commitOperation();
        }
    }
    
    public void keyReleased(final KeyEvent e) {
    }
    
    public void keyTyped(final KeyEvent e) {
    }
}
