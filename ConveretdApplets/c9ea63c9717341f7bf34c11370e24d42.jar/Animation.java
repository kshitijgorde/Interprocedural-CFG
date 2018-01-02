import java.awt.Rectangle;
import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Animation
{
    public int m_fps;
    public Color m_background;
    public AnimationEvent m_head;
    public AnimationEvent m_tail;
    public Dimension m_size;
    
    public Animation() {
        this.m_fps = 20;
        this.m_background = Color.black;
        this.m_head = null;
        this.m_size = new Dimension();
    }
    
    public void addEvent(final AnimationEvent animationEvent) {
        if (this.m_head == null) {
            this.m_head = animationEvent;
            this.m_tail = animationEvent;
        }
        else {
            this.m_tail.m_next = animationEvent;
            animationEvent.m_prev = this.m_tail;
            this.m_tail = animationEvent;
        }
    }
    
    public void drawFrame(final Image image) {
        final Graphics graphics = image.getGraphics();
        graphics.setColor(this.m_background);
        graphics.fillRect(0, 0, this.m_size.width, this.m_size.height);
        for (AnimationEvent animationEvent = this.m_head; animationEvent != null; animationEvent = animationEvent.m_next) {
            animationEvent.drawFrame(graphics);
        }
        graphics.dispose();
    }
    
    public void initFromParams(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final int index = nextToken.indexOf(58);
            if (index != -1) {
                final String upperCase = nextToken.substring(0, index).toUpperCase();
                final String substring = nextToken.substring(index + 1);
                if (upperCase.equals("FPS")) {
                    this.m_fps = Integer.valueOf(substring);
                }
                else {
                    if (!upperCase.equals("BACKGROUND")) {
                        continue;
                    }
                    this.m_background = Misc.initColour(substring);
                }
            }
        }
    }
    
    public void setSize(final Rectangle rectangle) {
        this.m_size.width = rectangle.width;
        this.m_size.height = rectangle.height;
    }
    
    public String getUrl(final int n, final int n2) {
        for (AnimationEvent animationEvent = this.m_tail; animationEvent != null; animationEvent = animationEvent.m_prev) {
            final String url = animationEvent.getUrl(n, n2);
            if (url != null) {
                return url;
            }
        }
        return null;
    }
    
    public void incFrame() {
        for (AnimationEvent animationEvent = this.m_head; animationEvent != null; animationEvent = animationEvent.m_next) {
            animationEvent.incFrame();
        }
    }
}
