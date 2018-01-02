import java.awt.Color;
import java.awt.Graphics;
import java.awt.Event;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class splash extends Applet implements Runnable
{
    int XMAX;
    float[] m_Height;
    float[] m_G;
    float b;
    float fWeight;
    private Thread m_NAMI2D;
    
    public splash() {
        this.XMAX = 112;
        this.m_Height = new float[this.XMAX];
        this.m_G = new float[this.XMAX];
        this.b = 1.005f;
        this.fWeight = 3.0f;
        this.m_NAMI2D = null;
    }
    
    public String getAppletInfo() {
        return "\u540d\u524d: \u6c34\u306e\u6a21\u578b\r\n\u8457\u4f5c\u8005: \u5152\u5800 \u6cbb\u9053\r\nCopyright (C) 1998 by H.Kohori";
    }
    
    public void init() {
        this.resize(440, 60);
        this.m_Height[24] = -20.0f;
        this.m_Height[25] = -40.0f;
        this.m_Height[26] = -20.0f;
        this.m_Height[74] = 30.0f;
        this.m_Height[75] = 60.0f;
        this.m_Height[76] = 30.0f;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final float[] height = this.m_Height;
        final int n3 = n / 4 - 1;
        height[n3] += n2 / 2;
        final float[] height2 = this.m_Height;
        final int n4 = n / 4;
        height2[n4] += n2;
        final float[] height3 = this.m_Height;
        final int n5 = n / 4 + 1;
        height3[n5] += n2 / 2;
        return false;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.blue);
        final float n = 3.0f * this.fWeight;
        final float n2 = n - 2.0f;
        this.m_Height[0] = this.m_Height[1];
        this.m_Height[this.XMAX - 1] = this.m_Height[this.XMAX - 2];
        float n3 = this.m_Height[0];
        for (int i = 1; i < this.XMAX - 1; ++i) {
            final float n4 = ((n3 + n2 * this.m_Height[i] + this.m_Height[i + 1]) / n + this.m_G[i]) / this.b;
            n3 = this.m_Height[i];
            this.m_G[i] = n4 - n3;
            this.m_Height[i] = n4;
            graphics.clearRect((i - 1) * 4, (int)n4 - 30, 4, 60);
            graphics.fillRect((i - 1) * 4, (int)n4 + 30, 4, 60);
        }
    }
    
    public void run() {
        while (true) {
            try {
                while (true) {
                    this.repaint();
                    Thread.sleep(50L);
                }
            }
            catch (InterruptedException ex) {
                this.stop();
                continue;
            }
            break;
        }
    }
    
    public void start() {
        if (this.m_NAMI2D == null) {
            (this.m_NAMI2D = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.m_NAMI2D != null) {
            this.m_NAMI2D.stop();
            this.m_NAMI2D = null;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
