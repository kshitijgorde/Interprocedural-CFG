import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Enumeration;
import java.awt.Image;
import java.awt.Color;
import java.util.Hashtable;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class ListView extends Canvas
{
    private String m_Title;
    private Hashtable m_HT;
    private Color m_HeadColor;
    private String[] m_SortedCaption;
    private Image m_OffScreen_Image;
    private int m_TopItemIndex;
    private int m_ScrollInc;
    private Hashtable m_HitArea;
    private int mouseDown_X;
    private int mouseDown_Y;
    private final int m_Gap = 2;
    private final String UPWARD_SCROLLER = "UPWARD_SCROLLER";
    private final String DOWNWARD_SCROLLER = "DOWNWARD_SCROLLER";
    private int m_MaxSelected;
    
    public Hashtable getCaptions() {
        return this.m_HT;
    }
    
    public void setCaptions(final Hashtable ht, final int maxSelected) {
        this.m_MaxSelected = maxSelected;
        this.m_TopItemIndex = 0;
        this.m_HT = ht;
        this.m_SortedCaption = new String[ht.size()];
        final Enumeration<String> keys = this.m_HT.keys();
        int n = 0;
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final String s2 = this.m_HT.get(s);
            this.m_SortedCaption[n++] = s;
        }
        sort(true, this.m_SortedCaption);
    }
    
    public void reset() {
        this.m_OffScreen_Image = null;
        this.paint(this.getGraphics());
    }
    
    public ListView(final String title, final Hashtable ht, final int n, final Color headColor) {
        this.m_Title = title;
        this.setCaptions(ht, n);
        this.m_HT = ht;
        this.m_HeadColor = headColor;
        this.m_OffScreen_Image = null;
        this.mouseDown_X = 0;
        this.mouseDown_Y = 0;
        this.m_HitArea = null;
    }
    
    public void paint(final Graphics graphics) {
        try {
            if (this.m_OffScreen_Image == null) {
                final int width = this.size().width;
                final int height = this.size().height;
                this.m_OffScreen_Image = this.createImage(width, height);
                if (this.m_OffScreen_Image == null) {
                    return;
                }
                final Graphics graphics2 = this.m_OffScreen_Image.getGraphics();
                graphics2.setColor(this.getForeground());
                graphics2.drawRect(0, 0, width - 1, height - 1);
                final int height2 = graphics2.getFontMetrics().getHeight();
                graphics2.setColor(this.m_HeadColor);
                graphics2.fillRect(1, 1, width - 2, height2 + 4);
                graphics2.setColor(this.getForeground());
                graphics2.drawString(this.m_Title, 4, 2 + height2);
                graphics2.drawRect(0, 0, width - 1, height2 + 4);
                this.m_HitArea = new Hashtable();
                final int scrollInc = (height - height2) / (height2 + 4);
                this.m_ScrollInc = scrollInc;
                for (int n = (this.m_HT.size() - this.m_TopItemIndex < scrollInc) ? (this.m_HT.size() - this.m_TopItemIndex) : scrollInc, i = 0; i < n; ++i) {
                    final String s = this.m_SortedCaption[this.m_TopItemIndex + i];
                    final int n2 = 2 * (height2 + 4) + (2 + height2) * i;
                    final int[] array = { 6, n2 - height2 + 2, 0, 0 };
                    array[2] = array[0] + 11;
                    array[3] = array[1] + 11;
                    this.m_HitArea.put(s, array);
                    this.drawTickBox(graphics2, array[0], array[1], this.m_HT.get(s).equals("1"));
                    graphics2.drawString(s, 25, n2);
                }
                if (this.m_TopItemIndex > 0) {
                    final int[] array2 = { width - 6 - 11, 2 * (height2 + 4) - height2 + 2, 0, 0 };
                    array2[2] = array2[0] + 11;
                    array2[3] = array2[1] + 11;
                    this.m_HitArea.put("UPWARD_SCROLLER", array2);
                    this.drawScrollBar(graphics2, array2[0], array2[1], true);
                }
                if (this.m_HT.size() - this.m_TopItemIndex > scrollInc) {
                    final int[] array3 = { width - 6 - 11, height - (height2 + 4), 0, 0 };
                    array3[2] = array3[0] + 11;
                    array3[3] = array3[1] + 11;
                    this.m_HitArea.put("DOWNWARD_SCROLLER", array3);
                    this.drawScrollBar(graphics2, width - 6 - 11, height - (height2 + 4), false);
                }
            }
            graphics.drawImage(this.m_OffScreen_Image, 0, 0, this);
        }
        catch (Exception ex) {}
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.m_HitArea == null) {
            return false;
        }
        final String hitAreaName = this.getHitAreaName(this.mouseDown_X, this.mouseDown_Y);
        final String hitAreaName2 = this.getHitAreaName(n, n2);
        if (hitAreaName == hitAreaName2 && hitAreaName2 != "") {
            if (hitAreaName2 == "UPWARD_SCROLLER") {
                this.m_TopItemIndex -= this.m_ScrollInc;
            }
            else if (hitAreaName2 == "DOWNWARD_SCROLLER") {
                this.m_TopItemIndex += this.m_ScrollInc;
            }
            else if (this.m_HT.containsKey(hitAreaName2)) {
                if (((String)this.m_HT.get(hitAreaName2)).equals("0")) {
                    if (this.m_MaxSelected > this.getSelectedItemCount()) {
                        this.m_HT.put(hitAreaName2, "1");
                    }
                }
                else {
                    this.m_HT.put(hitAreaName2, "0");
                }
                this.postEvent(new Event(this, 1001, hitAreaName2 + "," + (String)this.m_HT.get(hitAreaName2)));
            }
            this.reset();
        }
        return true;
    }
    
    public static void sort(final boolean b, final String[] array) {
        for (int i = array.length / 2; i > 0; i /= 2) {
            for (int j = i; j < array.length; ++j) {
                for (int n = j - i; n >= 0 && !orderOK(b, array[n], array[n + i]); n -= i) {
                    final String s = array[n];
                    array[n] = array[n + i];
                    array[n + i] = s;
                }
            }
        }
    }
    
    private String getHitAreaName(final int n, final int n2) {
        boolean b = false;
        final Enumeration keys = this.m_HitArea.keys();
        String s = "";
        while (keys.hasMoreElements() && !b) {
            s = keys.nextElement();
            final int[] array = this.m_HitArea.get(s);
            if (n >= array[0] && n <= array[2] && n2 >= array[1] && n2 <= array[3]) {
                b = true;
            }
        }
        if (b) {
            return s;
        }
        return "";
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void drawScrollBar(final Graphics graphics, final int n, final int n2, final boolean b) {
        final int n3 = 11;
        graphics.setColor(this.m_HeadColor);
        graphics.fillRoundRect(n, n2, n3, n3, 2, 2);
        graphics.setColor(this.getForeground());
        graphics.drawRoundRect(n, n2, n3, n3, 2, 2);
        if (b) {
            graphics.drawLine(n + 3, n2 + 7, n + 6, n2 + 4);
            graphics.drawLine(n + 3, n2 + 7, n + 7, n2 + 5);
            graphics.drawLine(n + 3, n2 + 7, n + 8, n2 + 6);
            graphics.drawLine(n + 3, n2 + 7, n + 9, n2 + 7);
            return;
        }
        graphics.drawLine(n + 6, n2 + 8, n + 3, n2 + 5);
        graphics.drawLine(n + 6, n2 + 8, n + 4, n2 + 5);
        graphics.drawLine(n + 6, n2 + 8, n + 5, n2 + 5);
        graphics.drawLine(n + 6, n2 + 8, n + 6, n2 + 5);
        graphics.drawLine(n + 6, n2 + 8, n + 7, n2 + 5);
        graphics.drawLine(n + 6, n2 + 8, n + 8, n2 + 5);
        graphics.drawLine(n + 6, n2 + 8, n + 9, n2 + 5);
    }
    
    public boolean mouseDown(final Event event, final int mouseDown_X, final int mouseDown_Y) {
        this.mouseDown_X = mouseDown_X;
        this.mouseDown_Y = mouseDown_Y;
        return true;
    }
    
    public static boolean orderOK(final boolean b, final String s, final String s2) {
        boolean b2 = false;
        if (b) {
            if (s.compareTo(s2) <= 0) {
                b2 = true;
            }
        }
        else if (s.compareTo(s2) > 0) {
            b2 = true;
        }
        return b2;
    }
    
    private int getSelectedItemCount() {
        int n = 0;
        final Enumeration<Object> keys = (Enumeration<Object>)this.m_HT.keys();
        while (keys.hasMoreElements()) {
            if (((String)this.m_HT.get(keys.nextElement())).equals("1")) {
                ++n;
            }
        }
        return n;
    }
    
    private void drawTickBox(final Graphics graphics, final int n, final int n2, final boolean b) {
        final int n3 = 11;
        graphics.setColor(this.getForeground());
        graphics.drawRect(n, n2, n3, n3);
        if (b) {
            graphics.drawLine(n + 3, n2 + 6, n + 5, n2 + 8);
            graphics.drawLine(n + 5, n2 + 8, n + 9, n2 + 4);
            graphics.drawLine(n + 3, n2 + 7, n + 5, n2 + 9);
            graphics.drawLine(n + 5, n2 + 9, n + 9, n2 + 5);
        }
    }
    
    public String[] getSelectedItems() {
        final String[] array = new String[this.getSelectedItemCount()];
        int n = 0;
        for (int i = 0; i < this.m_SortedCaption.length; ++i) {
            if (((String)this.m_HT.get(this.m_SortedCaption[i])).equals("1")) {
                array[n] = this.m_SortedCaption[i];
                ++n;
            }
        }
        return array;
    }
}
