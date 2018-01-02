// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import java.awt.Insets;
import java.net.MalformedURLException;
import java.net.URL;
import geracemenu.parser.IntValue;
import geracemenu.parser.StringValue;
import geracemenu.parser.Type;
import java.util.Hashtable;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;

public class ListItem extends MenuItem
{
    public static final int LISTTYPE_CIRCLE = 0;
    public static final int LISTTYPE_DISC = 1;
    public static final int LISTTYPE_SQUARE = 2;
    public static final int LISTTYPE_3DRECT_SUNK = 3;
    public static final int LISTTYPE_3DRECT_RAISED = 4;
    private int listType;
    private int listSize;
    private Color listColor;
    private int listPending;
    private VImage listImage;
    
    public void paintItem(final Graphics graphics) {
        this.paintListItem(graphics);
        this.getLayout().paint(graphics, new Point(0, 0));
    }
    
    private void paintListItem(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (this.listImage != null) {
            final Dimension size2 = this.listImage.getSize();
            this.listImage.paint(graphics, new Point(this.listPending, (Math.max(size.height, size2.height) - size2.height) / 2));
        }
        else {
            graphics.setColor(this.listColor);
            switch (this.listType) {
                case 2: {
                    graphics.fillRect(this.listPending, size.height / 2 - this.listSize / 2, this.listSize, this.listSize);
                    break;
                }
                case 3: {
                    graphics.fill3DRect(this.listPending, size.height / 2 - this.listSize / 2, this.listSize, this.listSize, false);
                    break;
                }
                case 4: {
                    graphics.fill3DRect(this.listPending, size.height / 2 - this.listSize / 2, this.listSize, this.listSize, true);
                    break;
                }
                case 0: {
                    graphics.drawArc(this.listPending, size.height / 2 - this.listSize / 2, this.listSize, this.listSize, 0, 360);
                    break;
                }
                case 1: {
                    graphics.fillArc(this.listPending, size.height / 2 - this.listSize / 2, this.listSize, this.listSize, 0, 360);
                    break;
                }
            }
        }
    }
    
    public ListItem(final String s) {
        this(s, (Hashtable)null);
    }
    
    public ListItem(final String s, final Hashtable hashtable) {
        super(s, hashtable);
        this.listType = 2;
        this.listSize = 6;
        this.listColor = Color.gray;
        this.listPending = 5;
        this.listImage = null;
        if (hashtable != null) {
            final Type type;
            if ((type = hashtable.get("listType")) != null) {
                final String lowerCase = ((String)((StringValue)type).getValue()).toLowerCase();
                if ("disc".equals(lowerCase)) {
                    this.listType = 1;
                }
                else if ("square".equals(lowerCase)) {
                    this.listType = 2;
                }
                else if ("circle".equals(lowerCase)) {
                    this.listType = 0;
                }
                else if ("3drect_sunk".equals(lowerCase)) {
                    this.listType = 3;
                }
                else if ("3drect_raised".equals(lowerCase)) {
                    this.listType = 4;
                }
            }
            final Type type2;
            if ((type2 = hashtable.get("listSize")) != null) {
                this.listSize = (int)((IntValue)type2).getValue();
            }
            final Type type3;
            if ((type3 = hashtable.get("listColor")) != null) {
                this.listColor = GuiUtil.findColor((String)((StringValue)type3).getValue(), this.listColor);
            }
            final Type type4;
            if ((type4 = hashtable.get("listImage")) != null) {
                try {
                    this.listImage = new VImage(new URL(parseMenuApplet.URLspec + (String)((StringValue)type4).getValue()));
                }
                catch (MalformedURLException ex) {}
            }
        }
        if (this.listImage != null) {
            this.listSize = this.listImage.getSize().width;
        }
        super.insets = new Insets(5, this.listPending + this.listSize + this.listPending, 5, 3);
    }
}
