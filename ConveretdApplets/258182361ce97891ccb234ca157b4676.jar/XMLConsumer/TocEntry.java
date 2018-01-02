// 
// Decompiled by Procyon v0.5.30
// 

package XMLConsumer;

import java.awt.FontMetrics;
import java.util.Vector;
import hhapplet.IActionSink;
import hhapplet.BsscFontFixPatch;
import java.net.MalformedURLException;
import hhapplet.URLFileHandler;
import java.awt.Graphics;
import hhapplet.ImageCache;
import treeview.TreeViewImageSet;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import treeview.SiblingChildTree;

public class TocEntry extends SiblingChildTree implements IEntry
{
    public static final int TOC_BOOK = 1;
    public static final int TOC_ITEM = 2;
    public static final int TOC_CHUNK = 3;
    public static final int TOC_PROJECT = 4;
    public static final int TOC_REMOTEITEM = 5;
    private static final int TOC_INDENT = 16;
    private String m_name;
    private String m_url;
    private String m_ref;
    private int m_nType;
    private int m_nLevel;
    private boolean m_bSelect;
    private boolean m_bHighLight;
    private TocData m_TocData;
    private boolean m_bOpen;
    private int m_nPosOffset;
    private int m_nIndex;
    private ITocEntryContainer m_Container;
    private String m_strTarget;
    private String m_strIcon;
    private Image m_openIcon;
    private Image m_closeIcon;
    private boolean m_bStubFilled;
    public static final int BOOKOPEN = 1;
    public static final int BOOKCLOSE = 2;
    public static final int ITEM = 3;
    public static final int REMOTE = 4;
    public static final int NEWBOOKOPEN = 5;
    public static final int NEWBOOKCLOSE = 6;
    public static final int NEWITEM = 7;
    public static final int NEWREMOTE = 8;
    private static Image m_iconBookClose;
    private static Image m_iconBookOpen;
    private static Image m_iconItem;
    private static Image m_iconRemote;
    private static Image m_iconNewBookClose;
    private static Image m_iconNewBookOpen;
    private static Image m_iconNewItem;
    private static Image m_iconNewRemote;
    private static Color GREEN;
    private static Color m_activeColor;
    private static int m_indent;
    private static Font m_normalFont;
    private static Font m_hoverFont;
    private static Color m_normalColor;
    private static Color m_hoverColor;
    private static boolean m_normalUnderline;
    private static boolean m_hoverUnderline;
    private static ImageObserver m_observer;
    
    public void setContainer(final ITocEntryContainer container) {
        this.m_Container = container;
    }
    
    public ITocEntryContainer getContainer() {
        return this.m_Container;
    }
    
    public static boolean hasImage(final Image image) {
        return TocEntry.m_iconBookClose == image || TocEntry.m_iconBookOpen == image || TocEntry.m_iconItem == image || TocEntry.m_iconRemote == image || TocEntry.m_iconNewBookClose == image || TocEntry.m_iconNewBookOpen == image || TocEntry.m_iconNewItem == image || TocEntry.m_iconNewRemote == image;
    }
    
    public TocEntry getDirectParent() {
        return (TocEntry)this.getParent();
    }
    
    public void updatePosOffset(final int n) {
        this.m_nPosOffset += n;
    }
    
    public boolean isMainEntry() {
        return true;
    }
    
    public TocEntry getPrevSibling() {
        return (TocEntry)this.getSiblingLeft();
    }
    
    public void select(final boolean bSelect) {
        this.m_bSelect = bSelect;
    }
    
    public static void setNormalUnderline(final boolean normalUnderline) {
        TocEntry.m_normalUnderline = normalUnderline;
    }
    
    public static void setActiveColor(final Color activeColor) {
        TocEntry.m_activeColor = activeColor;
    }
    
    public String getName() {
        return this.m_name;
    }
    
    public void highLight(final boolean bHighLight) {
        this.m_bHighLight = bHighLight;
    }
    
    public static void setIconImage(final int n, final Image image) {
        switch (n) {
            case 1: {
                TocEntry.m_iconBookOpen = image;
            }
            case 2: {
                TocEntry.m_iconBookClose = image;
            }
            case 3: {
                TocEntry.m_iconItem = image;
            }
            case 4: {
                TocEntry.m_iconRemote = image;
            }
            case 5: {
                TocEntry.m_iconNewBookOpen = image;
            }
            case 6: {
                TocEntry.m_iconNewBookClose = image;
            }
            case 7: {
                TocEntry.m_iconNewItem = image;
            }
            case 8: {
                TocEntry.m_iconNewRemote = image;
            }
            default: {}
        }
    }
    
    private static Image getImage(final int n) {
        final TreeViewImageSet instance = TreeViewImageSet.getInstance();
        if (instance != null) {
            return instance.getImage(n);
        }
        return null;
    }
    
    public int getType() {
        return this.m_nType;
    }
    
    private Image getREmoteItemIcon() {
        if (this.m_closeIcon == null) {
            if (this.m_strIcon != null) {
                final String icon = this.getIcon(this.m_strIcon, 0);
                if (icon != null) {
                    this.m_closeIcon = ImageCache.getInstance().GetImage(this.getFullPath(icon));
                }
            }
            else if (TocEntry.m_iconRemote != null) {
                this.m_closeIcon = TocEntry.m_iconRemote;
            }
            else {
                this.m_closeIcon = getImage(14);
            }
        }
        return this.m_closeIcon;
    }
    
    public int getPosOffset() {
        return this.m_nPosOffset;
    }
    
    public void setPosOffset(final int nPosOffset) {
        this.m_nPosOffset = nPosOffset;
    }
    
    public int getLevel() {
        return this.m_nLevel;
    }
    
    public int getWidth(final Graphics graphics) {
        final int stringWidth = graphics.getFontMetrics(TocEntry.m_normalFont).stringWidth(this.m_name + " ");
        Image image = null;
        switch (this.m_nType) {
            case 1:
            case 3:
            case 4: {
                if (this.m_bOpen) {
                    image = this.getBookOpenIcon();
                    break;
                }
                image = this.getBookCloseIcon();
                break;
            }
            case 2: {
                image = this.getItemIcon();
                break;
            }
            case 5: {
                image = this.getREmoteItemIcon();
                break;
            }
        }
        int n = 0;
        if (image != null) {
            n = image.getWidth(TocEntry.m_observer) + 1;
        }
        return TocEntry.m_indent * this.m_nLevel + stringWidth + 1 + n;
    }
    
    public void updateIndex(final int nIndex) {
        this.m_nIndex = nIndex;
    }
    
    public void remove() {
        this.pruneThisSubtree();
    }
    
    private Image getBookOpenIcon() {
        if (this.m_openIcon == null) {
            if (this.m_strIcon != null) {
                final String icon = this.getIcon(this.m_strIcon, 1);
                if (icon != null) {
                    this.m_closeIcon = ImageCache.getInstance().GetImage(this.getFullPath(icon));
                }
            }
            else if (TocEntry.m_iconBookOpen != null) {
                this.m_openIcon = TocEntry.m_iconBookOpen;
            }
            else {
                this.m_openIcon = getImage(1);
            }
        }
        return this.m_openIcon;
    }
    
    private Image getItemIcon() {
        if (this.m_closeIcon == null) {
            if (this.m_strIcon != null) {
                final String icon = this.getIcon(this.m_strIcon, 0);
                if (icon != null) {
                    this.m_closeIcon = ImageCache.getInstance().GetImage(this.getFullPath(icon));
                }
            }
            else if (TocEntry.m_iconItem != null) {
                this.m_closeIcon = TocEntry.m_iconItem;
            }
            else {
                this.m_closeIcon = getImage(8);
            }
        }
        return this.m_closeIcon;
    }
    
    public int getPrevSpan() {
        final TocEntry tocEntry = (TocEntry)this.getSiblingLeft();
        if (tocEntry != null) {
            return this.m_nIndex - tocEntry.getIndex() - 1;
        }
        return -1;
    }
    
    public void setStubFilled() {
        this.m_bStubFilled = true;
    }
    
    public void setTarget(final String strTarget) {
        this.m_strTarget = strTarget;
    }
    
    public TocEntry getNextSibling() {
        return (TocEntry)this.getSibling();
    }
    
    public static void setHoverUnderline(final boolean hoverUnderline) {
        TocEntry.m_hoverUnderline = hoverUnderline;
    }
    
    public static void setHoverFont(final Font hoverFont) {
        if (hoverFont.getSize() != TocEntry.m_normalFont.getSize()) {
            TocEntry.m_hoverFont = new Font(hoverFont.getFamily(), hoverFont.getStyle(), TocEntry.m_normalFont.getSize());
            return;
        }
        TocEntry.m_hoverFont = hoverFont;
    }
    
    public static void setNormalColor(final Color normalColor) {
        TocEntry.m_normalColor = normalColor;
    }
    
    public TocEntry(final String name, final String url, final String ref, final int nIndex, final int nType, final int nLevel, final TocData tocData) {
        this.m_name = name;
        this.m_url = url;
        this.m_nIndex = nIndex;
        this.m_nType = nType;
        this.m_nLevel = nLevel;
        this.m_bHighLight = false;
        this.m_bSelect = false;
        this.m_TocData = tocData;
        this.m_bOpen = false;
        this.m_nPosOffset = 0;
        this.m_Container = null;
        this.m_ref = ref;
        this.m_bStubFilled = false;
    }
    
    public boolean isStubFilled() {
        return this.m_bStubFilled;
    }
    
    public boolean isOpen() {
        return this.m_bOpen;
    }
    
    public TocEntry getFirstChild() {
        return (TocEntry)this.getChild();
    }
    
    public static void SetObserver(final ImageObserver observer) {
        TocEntry.m_observer = observer;
    }
    
    public void setIcon(final String strIcon) {
        this.m_strIcon = strIcon;
    }
    
    private String getIcon(String substring, int n) {
        int n2;
        for (n2 = substring.indexOf(59); n-- > 0 && n2 != -1; n2 = substring.indexOf(59)) {
            substring = substring.substring(n2 + 1);
        }
        if (n >= 0) {
            return null;
        }
        if (n2 != -1) {
            return substring.substring(0, n2);
        }
        return substring;
    }
    
    public static void setNormalFont(final Font normalFont) {
        TocEntry.m_normalFont = normalFont;
    }
    
    public TocEntry getNextOut() {
        for (TocEntry directParent = this; directParent != null; directParent = directParent.getDirectParent()) {
            final TocEntry nextSibling = directParent.getNextSibling();
            if (nextSibling != null) {
                return nextSibling;
            }
        }
        return null;
    }
    
    private String getFullPath(final String s) {
        String string = null;
        try {
            string = URLFileHandler.makeURL(this.m_TocData.getProjURL(), s, null).toString();
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        return string;
    }
    
    static {
        TocEntry.GREEN = new Color(0, 127, 0);
        TocEntry.m_activeColor = Color.gray;
        TocEntry.m_indent = 16;
        TocEntry.m_normalFont = new Font(BsscFontFixPatch.GetFontName(), 0, BsscFontFixPatch.GetFontSize());
        TocEntry.m_hoverFont = new Font(BsscFontFixPatch.GetFontName(), 0, BsscFontFixPatch.GetFontSize());
        TocEntry.m_normalColor = Color.black;
        TocEntry.m_hoverColor = TocEntry.GREEN;
        TocEntry.m_normalUnderline = false;
        TocEntry.m_hoverUnderline = true;
        TocEntry.m_observer = null;
    }
    
    public boolean isStubEntry() {
        return this.getType() == 3 || this.getType() == 4;
    }
    
    public int getIndex() {
        return this.m_nIndex;
    }
    
    public String getRef() {
        return this.m_ref;
    }
    
    public void toggle() {
        this.m_bOpen = !this.m_bOpen;
    }
    
    public void action(final IActionSink actionSink) {
        final Vector<TocEntry> vector = new Vector<TocEntry>();
        vector.addElement(this);
        vector.addElement((TocEntry)this.m_url);
        vector.addElement((TocEntry)this.m_ref);
        vector.addElement((TocEntry)this.m_TocData);
        vector.addElement((TocEntry)this.m_strTarget);
        actionSink.accept(vector);
    }
    
    public int getNextSpan() {
        final TocEntry tocEntry = (TocEntry)this.getSibling();
        if (tocEntry != null) {
            return tocEntry.getIndex() - this.m_nIndex - 1;
        }
        return -1;
    }
    
    public void display(final Graphics graphics, final int n, final int n2, final Color color, final Image image) {
        final FontMetrics fontMetrics = graphics.getFontMetrics(TocEntry.m_normalFont);
        final FontMetrics fontMetrics2 = graphics.getFontMetrics(TocEntry.m_hoverFont);
        final int stringWidth = fontMetrics.stringWidth(this.m_name + " ");
        final int stringWidth2 = fontMetrics2.stringWidth(this.m_name + " ");
        final int n3 = (stringWidth > stringWidth2) ? stringWidth : stringWidth2;
        final int ascent = fontMetrics.getAscent();
        final int leading = fontMetrics.getLeading();
        final int ascent2 = fontMetrics2.getAscent();
        final int leading2 = fontMetrics2.getLeading();
        Image image2 = null;
        switch (this.m_nType) {
            case 1:
            case 3:
            case 4: {
                if (this.m_bOpen) {
                    image2 = this.getBookOpenIcon();
                    break;
                }
                image2 = this.getBookCloseIcon();
                break;
            }
            case 2: {
                image2 = this.getItemIcon();
                break;
            }
            case 5: {
                image2 = this.getREmoteItemIcon();
                break;
            }
        }
        final Color color2 = graphics.getColor();
        final Font font = graphics.getFont();
        int n4 = 0;
        if (image2 != null) {
            n4 = image2.getWidth(TocEntry.m_observer) + 1;
        }
        graphics.drawImage(image2, TocEntry.m_indent * this.m_nLevel, n * n2, TocEntry.m_observer);
        if (this.m_bSelect) {
            graphics.setColor(TocEntry.m_activeColor);
            graphics.fill3DRect(TocEntry.m_indent * this.m_nLevel + n4, n * n2, n3, n2, true);
            graphics.setColor(color2);
        }
        else if (image != null) {
            graphics.drawImage(image, TocEntry.m_indent * this.m_nLevel + n4, n * n2, TocEntry.m_indent * this.m_nLevel + n4 + n3, n * n2 + n2, TocEntry.m_indent * this.m_nLevel + n4, n * n2, TocEntry.m_indent * this.m_nLevel + n4 + n3, n * n2 + n2, null);
        }
        else {
            graphics.setColor(color);
            graphics.fillRect(TocEntry.m_indent * this.m_nLevel + n4, n * n2, n3, n2);
            graphics.setColor(color2);
        }
        if (this.m_bHighLight) {
            graphics.setFont(TocEntry.m_hoverFont);
            graphics.setColor(TocEntry.m_hoverColor);
            graphics.drawString(this.m_name, TocEntry.m_indent * this.m_nLevel + n4, n * n2 + ascent2 + leading2);
            if (TocEntry.m_hoverUnderline) {
                graphics.drawLine(TocEntry.m_indent * this.m_nLevel + n4, (n + 1) * n2 - 1, TocEntry.m_indent * this.m_nLevel + n3 - 1 + n4, (n + 1) * n2 - 1);
            }
            graphics.setFont(font);
            graphics.setColor(color2);
            return;
        }
        graphics.setFont(TocEntry.m_normalFont);
        graphics.setColor(TocEntry.m_normalColor);
        graphics.drawString(this.m_name, TocEntry.m_indent * this.m_nLevel + n4, n * n2 + ascent + leading);
        if (TocEntry.m_normalUnderline) {
            graphics.drawLine(TocEntry.m_indent * this.m_nLevel + n4, (n + 1) * n2 - 1, TocEntry.m_indent * this.m_nLevel + n3 - 1 + n4, (n + 1) * n2 - 1);
        }
        graphics.setFont(font);
        graphics.setColor(color2);
    }
    
    public static void setIndent(final int indent) {
        TocEntry.m_indent = indent;
    }
    
    public static void setHoverColor(final Color hoverColor) {
        TocEntry.m_hoverColor = hoverColor;
    }
    
    private Image getBookCloseIcon() {
        if (this.m_closeIcon == null) {
            if (this.m_strIcon != null) {
                final String icon = this.getIcon(this.m_strIcon, 0);
                if (icon != null) {
                    this.m_closeIcon = ImageCache.getInstance().GetImage(this.getFullPath(icon));
                }
            }
            else if (TocEntry.m_iconBookClose != null) {
                this.m_closeIcon = TocEntry.m_iconBookClose;
            }
            else {
                this.m_closeIcon = getImage(0);
            }
        }
        return this.m_closeIcon;
    }
}
