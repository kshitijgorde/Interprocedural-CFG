// 
// Decompiled by Procyon v0.5.30
// 

package XMLConsumer;

import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.net.MalformedURLException;
import hhapplet.URLFileHandler;
import hhapplet.BsscFontFixPatch;
import java.util.Enumeration;
import java.net.URL;
import hhapplet.IActionSink;
import java.awt.Font;
import java.awt.Color;
import java.util.Vector;

public class IdxEntry implements IEntry
{
    public static final int IDX_NODE = 1;
    public static final int IDX_KEY = 2;
    public static final int IDX_SUBKEY = 3;
    private int m_nNext;
    private int m_nPrev;
    private String m_name;
    private Vector m_topics;
    private int m_nType;
    private int m_nLevel;
    private boolean m_bSelect;
    private boolean m_bHighLight;
    private IdxData m_IdxData;
    private String m_target;
    private static SecondaryDialog m_dialog;
    private static Color GREEN;
    private static final int IDX_INDENT = 10;
    private static Color m_activeColor;
    private static Color m_hoverColor;
    private static int m_indent;
    private static Font m_normalFont;
    private static Font m_hoverFont;
    private static Color m_normalColor;
    private static boolean m_normalUnderline;
    private static boolean m_hoverUnderline;
    
    public static SecondaryDialog getDialog(final IActionSink actionSink, final URL url, final String s, final Vector vector) {
        if (IdxEntry.m_dialog != null) {
            IdxEntry.m_dialog.closeDialog();
            IdxEntry.m_dialog = null;
        }
        return IdxEntry.m_dialog = new SecondaryDialog(actionSink, url, s, vector);
    }
    
    public int getPrevSpan() {
        return this.m_nPrev;
    }
    
    public void setTarget(final String target) {
        this.m_target = target;
    }
    
    public static void setHoverUnderline(final boolean hoverUnderline) {
        IdxEntry.m_hoverUnderline = hoverUnderline;
    }
    
    public boolean isMainEntry() {
        return this.m_nType == 2;
    }
    
    public static void setNormalColor(final Color normalColor) {
        IdxEntry.m_normalColor = normalColor;
    }
    
    public static void setHoverFont(final Font hoverFont) {
        if (hoverFont.getSize() != IdxEntry.m_normalFont.getSize()) {
            IdxEntry.m_hoverFont = new Font(hoverFont.getFamily(), hoverFont.getStyle(), IdxEntry.m_normalFont.getSize());
            return;
        }
        IdxEntry.m_hoverFont = hoverFont;
    }
    
    public IdxEntry(final String name, final int nPrev, final int nType, final int nLevel, final IdxData idxData) {
        this.m_name = name;
        this.m_nPrev = nPrev;
        this.m_nNext = 0;
        this.m_nType = nType;
        this.m_nLevel = nLevel;
        this.m_bHighLight = false;
        this.m_bSelect = false;
        this.m_IdxData = idxData;
        this.m_target = null;
    }
    
    public Enumeration getTopics() {
        if (this.m_topics != null) {
            return this.m_topics.elements();
        }
        return null;
    }
    
    public void select(final boolean bSelect) {
        this.m_bSelect = bSelect;
    }
    
    public void addTopic(final Topic topic) {
        if (this.m_topics == null) {
            this.m_topics = new Vector();
        }
        this.m_topics.addElement(topic);
    }
    
    public static void setNormalFont(final Font normalFont) {
        IdxEntry.m_normalFont = normalFont;
    }
    
    public static void setNormalUnderline(final boolean normalUnderline) {
        IdxEntry.m_normalUnderline = normalUnderline;
    }
    
    public static void setActiveColor(final Color activeColor) {
        IdxEntry.m_activeColor = activeColor;
    }
    
    static {
        IdxEntry.m_dialog = null;
        IdxEntry.GREEN = new Color(0, 127, 0);
        IdxEntry.m_activeColor = Color.gray;
        IdxEntry.m_hoverColor = IdxEntry.GREEN;
        IdxEntry.m_indent = 10;
        IdxEntry.m_normalFont = new Font(BsscFontFixPatch.GetFontName(), 0, BsscFontFixPatch.GetFontSize());
        IdxEntry.m_hoverFont = new Font(BsscFontFixPatch.GetFontName(), 0, BsscFontFixPatch.GetFontSize());
        IdxEntry.m_normalColor = Color.black;
        IdxEntry.m_normalUnderline = false;
        IdxEntry.m_hoverUnderline = true;
    }
    
    public String getName() {
        return this.m_name;
    }
    
    public void highLight(final boolean bHighLight) {
        this.m_bHighLight = bHighLight;
    }
    
    public void action(final IActionSink actionSink) {
        if (this.m_topics != null) {
            if (this.m_topics.size() == 1) {
                final Topic topic = this.m_topics.elementAt(0);
                try {
                    URL url = URLFileHandler.makeURL(this.m_IdxData.getProjURL(), topic.getURL(), null);
                    final String string = url.toString();
                    if (string.indexOf("file:/\\\\") == 0) {
                        url = new URL("file://" + string.substring(8));
                    }
                    final Vector<String> vector = new Vector<String>();
                    vector.addElement(url.toString());
                    vector.addElement(this.m_target);
                    actionSink.accept(vector);
                    return;
                }
                catch (MalformedURLException ex) {
                    ex.printStackTrace();
                    return;
                }
            }
            final SecondaryDialog dialog = getDialog(actionSink, this.m_IdxData.getProjURL(), this.m_target, this.m_topics);
            dialog.showTopic();
            dialog.getList().requestFocus();
        }
    }
    
    public int getType() {
        return this.m_nType;
    }
    
    public int getNextSpan() {
        return this.m_nNext;
    }
    
    public void display(final Graphics graphics, final int n, final int n2, final Color color, final Image image) {
        final FontMetrics fontMetrics = graphics.getFontMetrics(IdxEntry.m_normalFont);
        final FontMetrics fontMetrics2 = graphics.getFontMetrics(IdxEntry.m_hoverFont);
        final int stringWidth = fontMetrics.stringWidth(this.m_name + " ");
        final int stringWidth2 = fontMetrics2.stringWidth(this.m_name + " ");
        final int n3 = (stringWidth > stringWidth2) ? stringWidth : stringWidth2;
        final int ascent = fontMetrics.getAscent();
        final int leading = fontMetrics.getLeading();
        final int ascent2 = fontMetrics2.getAscent();
        final int leading2 = fontMetrics2.getLeading();
        switch (this.m_nType) {
            case 1: {
                final Color color2 = graphics.getColor();
                if (this.m_bSelect) {
                    graphics.setColor(IdxEntry.m_activeColor);
                    graphics.fill3DRect(0, n * n2, n3, n2, true);
                }
                else if (image != null) {
                    graphics.drawImage(image, 0, n * n2, n3, n * n2 + n2, 0, n * n2, n3, n * n2 + n2, null);
                }
                else {
                    graphics.setColor(color);
                    graphics.fillRect(0, n * n2, n3, n2);
                }
                graphics.setColor(color2);
                final Font font = graphics.getFont();
                graphics.setFont(new Font(font.getName(), font.getStyle() | 0x1, font.getSize()));
                graphics.drawString(this.m_name, 0, n * n2 + ascent + leading);
                graphics.setFont(font);
            }
            case 2:
            case 3: {
                final Color color3 = graphics.getColor();
                if (this.m_bSelect) {
                    graphics.setColor(IdxEntry.m_activeColor);
                    graphics.fill3DRect(IdxEntry.m_indent * this.m_nLevel, n * n2, n3, n2, true);
                }
                else if (image != null) {
                    graphics.drawImage(image, IdxEntry.m_indent * this.m_nLevel, n * n2, IdxEntry.m_indent * this.m_nLevel + n3, n * n2 + n2, IdxEntry.m_indent * this.m_nLevel, n * n2, IdxEntry.m_indent * this.m_nLevel + n3, n * n2 + n2, null);
                }
                else {
                    graphics.setColor(color);
                    graphics.fillRect(IdxEntry.m_indent * this.m_nLevel, n * n2, n3, n2);
                }
                graphics.setColor(color3);
                final Font font2 = graphics.getFont();
                if (this.m_bHighLight) {
                    graphics.setColor(IdxEntry.m_hoverColor);
                    graphics.setFont(IdxEntry.m_hoverFont);
                    graphics.drawString(this.m_name, IdxEntry.m_indent * this.m_nLevel, n * n2 + ascent2 + leading2);
                    if (IdxEntry.m_hoverUnderline) {
                        graphics.drawLine(IdxEntry.m_indent * this.m_nLevel, (n + 1) * n2 - 1, IdxEntry.m_indent * this.m_nLevel + n3 - 1, (n + 1) * n2 - 1);
                    }
                    graphics.setFont(font2);
                    graphics.setColor(color3);
                    return;
                }
                graphics.setColor(IdxEntry.m_normalColor);
                graphics.setFont(IdxEntry.m_normalFont);
                graphics.drawString(this.m_name, IdxEntry.m_indent * this.m_nLevel, n * n2 + ascent + leading);
                if (IdxEntry.m_normalUnderline) {
                    graphics.drawLine(IdxEntry.m_indent * this.m_nLevel, (n + 1) * n2 - 1, IdxEntry.m_indent * this.m_nLevel + n3 - 1, (n + 1) * n2 - 1);
                }
                graphics.setFont(font2);
                graphics.setColor(color3);
                break;
            }
        }
    }
    
    public static void setIndent(final int indent) {
        IdxEntry.m_indent = indent;
    }
    
    public void setNextSpan(final int nNext) {
        this.m_nNext = nNext;
    }
    
    public int getWidth(final Graphics graphics) {
        return IdxEntry.m_indent * this.m_nLevel + graphics.getFontMetrics(IdxEntry.m_normalFont).stringWidth(this.m_name + " ") + 1;
    }
    
    public static void setHoverColor(final Color hoverColor) {
        IdxEntry.m_hoverColor = hoverColor;
    }
}
