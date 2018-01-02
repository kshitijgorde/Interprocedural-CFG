import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class AnimationEvent
{
    public long m_speed;
    public long m_xOffset;
    public Animation m_animation;
    public Component m_component;
    Color m_colour;
    String m_url;
    public AnimationEvent m_next;
    public AnimationEvent m_prev;
    
    public abstract void drawFrame(final Graphics p0);
    
    public AnimationEvent(final Component component, final Animation animation) {
        this.m_animation = animation;
        this.m_component = component;
        this.m_xOffset = 0L;
        this.m_speed = 0L;
        this.m_url = new String();
        this.m_next = null;
        this.m_prev = null;
    }
    
    public void initFromParams(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
        stringTokenizer.nextToken().indexOf(58);
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final int index = nextToken.indexOf(58);
            if (index != -1) {
                final String upperCase = nextToken.substring(0, index).toUpperCase();
                final String substring = nextToken.substring(index + 1);
                if (upperCase.equals("SPEED")) {
                    this.m_speed = 20L * Integer.valueOf(substring) * 16L / this.m_animation.m_fps;
                }
                else if (upperCase.equals("COLOUR")) {
                    this.m_colour = Misc.initColour(substring);
                }
                else {
                    if (!upperCase.equals("URL")) {
                        continue;
                    }
                    this.m_url = substring;
                }
            }
        }
    }
    
    public abstract String getUrl(final int p0, final int p1);
    
    public void incFrame() {
        this.m_xOffset += this.m_speed;
    }
}
