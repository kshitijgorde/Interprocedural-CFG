import java.util.Iterator;
import java.awt.geom.Rectangle2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.text.AttributedCharacterIterator;
import java.util.Map;
import java.text.AttributedString;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CompatibilityApplet extends Applet
{
    private Font font;
    private static final String inputText = "Click here to get the new Java Plug-In";
    private static final String url = "https://jdk6.dev.java.net/6uNea.html";
    
    public void init() {
        int int1 = 36;
        try {
            int1 = Integer.parseInt(this.getParameter("compat_fontsize"));
        }
        catch (Exception ex) {}
        Color foreground = Color.black;
        Color background = Color.white;
        try {
            foreground = Color.decode(this.getParameter("compat_fgcolor"));
        }
        catch (Exception ex2) {}
        try {
            background = Color.decode(this.getParameter("compat_bgcolor"));
        }
        catch (Exception ex3) {}
        this.font = new Font("SansSerif", 0, int1);
        this.setForeground(foreground);
        this.setBackground(background);
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                try {
                    CompatibilityApplet.this.getAppletContext().showDocument(new URL("https://jdk6.dev.java.net/6uNea.html"), "_blank");
                }
                catch (Exception ex) {}
            }
        });
    }
    
    public void paint(final Graphics graphics) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final ArrayList<LineInfo> list = new ArrayList<LineInfo>();
        graphics2D.getFontMetrics();
        final FontRenderContext fontRenderContext = graphics2D.getFontRenderContext();
        final HashMap<TextAttribute, Font> hashMap = new HashMap<TextAttribute, Font>();
        hashMap.put(TextAttribute.FONT, this.font);
        float n = 0.0f;
        int position = 0;
        final LineBreakMeasurer lineBreakMeasurer = new LineBreakMeasurer(new AttributedString("Click here to get the new Java Plug-In", hashMap).getIterator(), fontRenderContext);
        while (lineBreakMeasurer.getPosition() < "Click here to get the new Java Plug-In".length()) {
            final int nextOffset = lineBreakMeasurer.nextOffset(this.getWidth() - 10);
            final String substring = "Click here to get the new Java Plug-In".substring(position, nextOffset);
            final Rectangle2D visualBounds = this.font.createGlyphVector(fontRenderContext, substring).getVisualBounds();
            final float n2 = (float)visualBounds.getHeight() + 5.0f;
            list.add(new LineInfo(substring, (float)visualBounds.getWidth(), n2));
            n += n2;
            position = nextOffset;
            lineBreakMeasurer.setPosition(position);
        }
        graphics2D.setFont(this.font);
        float n3 = (this.getHeight() - n) / 2.0f;
        for (final LineInfo lineInfo : list) {
            n3 += lineInfo.height;
            graphics2D.drawString(lineInfo.text, (int)((this.getWidth() - lineInfo.width) / 2.0f), (int)n3);
        }
    }
    
    static class LineInfo
    {
        String text;
        float width;
        float height;
        
        LineInfo(final String text, final float width, final float height) {
            this.text = text;
            this.width = width;
            this.height = height;
        }
    }
}
