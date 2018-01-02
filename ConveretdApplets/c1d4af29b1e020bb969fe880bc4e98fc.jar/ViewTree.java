import java.awt.Dimension;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Scrollbar;
import java.awt.Font;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ViewTree extends Applet
{
    private final String sUnregistered = "(Unregistered) ViewTree by Dave Jackson, COSMOSoft Ltd www.cosmosoft.co.uk";
    private final int iMaxBranches = 20;
    private final int iMaxImages = 5;
    private final int iMaxColors = 4;
    private Branch FirstBranch;
    private Branch LastBranch;
    private Branch CurrentBranch;
    private int iTop;
    private int iIndent;
    private int iLastLine;
    private int IconWidth;
    private boolean bLines;
    private Image imgBuffer;
    private Image imgBackImage;
    private Graphics grpSurface;
    private imagexx FirstImage;
    private imagexx LastImage;
    private colorxx FirstColor;
    private colorxx LastColor;
    private MediaTracker m;
    private Font fFontData;
    Scrollbar vsbScroll;
    
    private void drawSurface() {
        Branch tItem = null;
        imagexx tImage = null;
        if (this.imgBackImage != null) {
            if (this.imgBackImage.getHeight(null) > 0) {
                this.TileBack(this.grpSurface, this.imgBackImage);
            }
            else {
                this.grpSurface.clearRect(0, 0, this.getSize().width, this.getSize().height);
            }
        }
        else {
            this.grpSurface.clearRect(0, 0, this.getSize().width, this.getSize().height);
        }
        for (tItem = this.FirstBranch; tItem != null; tItem = tItem.NextBranch) {
            tItem.Top = 0;
        }
        tImage = this.GetImage(0);
        if (tImage != null) {
            this.IconWidth = tImage.Image.getWidth(null) + 8;
        }
        if (this.IconWidth < 0) {
            this.IconWidth = 8;
        }
        this.iLastLine = this.drawChildren(0, this.iTop, 0);
    }
    
    public void paint(final Graphics g) {
        this.drawSurface();
        g.drawImage(this.imgBuffer, 0, 0, null);
    }
    
    public ViewTree() {
        this.FirstBranch = null;
        this.LastBranch = null;
        this.CurrentBranch = null;
        this.iTop = 0;
        this.iIndent = 0;
        this.iLastLine = 0;
        this.IconWidth = 0;
        this.bLines = false;
        this.imgBuffer = null;
        this.imgBackImage = null;
        this.FirstImage = null;
        this.LastImage = null;
        this.FirstColor = null;
        this.LastColor = null;
        this.m = new MediaTracker(this);
        this.fFontData = null;
        this.vsbScroll = new Scrollbar(1, 100, 1, 0, 100);
    }
    
    public boolean mouseUp(final Event evt, final int x, final int y) {
        String location = null;
        String target = null;
        String path = null;
        URL url = null;
        Branch tItem = null;
        tItem = this.GetBranch(x, y);
        if (tItem != null) {
            if (x <= tItem.Left && x >= tItem.Left - this.IconWidth) {
                if (tItem.Expanded) {
                    tItem.Expanded = false;
                }
                else {
                    tItem.Expanded = true;
                }
                this.repaint();
            }
            else if (x >= tItem.Left && x <= tItem.Left + tItem.Width) {
                location = tItem.HLink.trim();
                target = tItem.Target;
                if (location.length() > 0) {
                    if (location.startsWith("http://") || location.startsWith("mailto")) {
                        path = "";
                    }
                    else {
                        path = "" + this.getCodeBase();
                    }
                    try {
                        url = new URL(path + location);
                    }
                    catch (MalformedURLException e) {
                        this.showStatus("Malformed URL");
                    }
                    this.getAppletContext().showDocument(url, target);
                }
            }
        }
        return true;
    }
    
    private void initForm() {
        this.setLayout(new BorderLayout());
    }
    
    private imagexx GetImage(final int iIndex) {
        imagexx tImage;
        for (tImage = null, tImage = this.FirstImage; tImage != null; tImage = tImage.NextImage) {
            if (tImage.Index == iIndex) {
                return tImage;
            }
        }
        return null;
    }
    
    private Branch GetRelatedBranch(final int iKey) {
        Branch tItem = null;
        if (iKey >= 0) {
            for (tItem = this.FirstBranch; tItem != null; tItem = tItem.NextBranch) {
                if (tItem.Key == iKey) {
                    return tItem;
                }
            }
        }
        return null;
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    private int drawChildren(final int iRelation, int iLine, int iLevel) {
        int iLineHeight = 0;
        int iLinePos = 0;
        int iWidth = 0;
        imagexx tImage = null;
        colorxx tColor = null;
        Branch tItem = null;
        Font fntData = this.grpSurface.getFont();
        final int iStart = iLine;
        final int iLeft = this.IconWidth + (iLevel + 1) * this.iIndent;
        ++iLevel;
        for (tItem = this.FirstBranch; tItem != null; tItem = tItem.NextBranch) {
            if (tItem.Relative == iRelation) {
                iLineHeight = this.grpSurface.getFontMetrics().getMaxAscent() + 4;
                iLine += iLineHeight;
                iLinePos = iLine - iLineHeight / 2 + 2;
                int iImage = -1;
                if (tItem.Heavy) {
                    fntData = new Font(fntData.getName(), 1, fntData.getSize());
                }
                else {
                    fntData = new Font(fntData.getName(), 0, fntData.getSize());
                }
                this.grpSurface.setFont(fntData);
                iWidth = 0;
                if (tItem.ImageIndex >= 2) {
                    tImage = this.GetImage(tItem.ImageIndex);
                    if (tImage != null) {
                        iWidth = tImage.Image.getWidth(null);
                        if (iWidth > 0) {
                            this.grpSurface.drawImage(tImage.Image, iLeft, iLine - tImage.Image.getHeight(null), null);
                        }
                        else {
                            iWidth = 0;
                        }
                    }
                }
                tItem.Top = iLine;
                tItem.Left = iLeft;
                tItem.Width = this.grpSurface.getFontMetrics().stringWidth(tItem.Text) + iWidth;
                if (tItem.HighLighted) {
                    tColor = this.GetColor(3);
                    if (tColor == null) {
                        this.grpSurface.setColor(Color.red);
                    }
                    else {
                        this.grpSurface.setColor(tColor.Color);
                    }
                    this.grpSurface.drawLine(iLeft + iWidth, iLine + 2, iLeft + tItem.Width, iLine + 2);
                    tColor = this.GetColor(2);
                    if (tColor == null) {
                        this.grpSurface.setColor(Color.blue);
                    }
                    else {
                        this.grpSurface.setColor(tColor.Color);
                    }
                }
                else {
                    tColor = this.GetColor(tItem.ColorIndex);
                    if (tColor == null) {
                        this.grpSurface.setColor(Color.black);
                    }
                    else {
                        this.grpSurface.setColor(tColor.Color);
                    }
                }
                this.grpSurface.drawString(tItem.Text, iLeft + iWidth, iLine);
                tColor = this.GetColor(0);
                if (tColor == null) {
                    this.grpSurface.setColor(Color.black);
                }
                else {
                    this.grpSurface.setColor(tColor.Color);
                }
                if (tItem.Children > 0) {
                    if (tItem.Expanded) {
                        iImage = 0;
                        iLine = this.drawChildren(tItem.Key, iLine, iLevel);
                    }
                    else {
                        iImage = 1;
                    }
                }
                iWidth = 0;
                if (iImage >= 0) {
                    tImage = this.GetImage(iImage);
                    if (tImage != null && tImage.Image.getWidth(null) > 0) {
                        iWidth = tImage.Image.getWidth(null) + 2;
                        this.grpSurface.drawImage(tImage.Image, iLeft - iWidth, iLinePos - tImage.Image.getHeight(null) / 2, null);
                    }
                }
                if (this.bLines) {
                    tColor = this.GetColor(0);
                    if (tColor == null) {
                        this.grpSurface.setColor(Color.black);
                    }
                    else {
                        this.grpSurface.setColor(tColor.Color);
                    }
                    this.grpSurface.drawLine(iLeft - this.IconWidth / 2 - this.iIndent, iStart + iLineHeight / 2 - 2, iLeft - this.IconWidth / 2 - this.iIndent, iLinePos);
                    this.grpSurface.drawLine(iLeft - this.IconWidth / 2 - this.iIndent, iLinePos, iLeft - 2 - iWidth, iLinePos);
                }
            }
        }
        return iLine;
    }
    
    private void usePageParams() {
        int iParam = 0;
        String sParam = null;
        Branch RelatedBranch = null;
        this.imgBackImage = this.getImage(this.getDocumentBase(), this.getParameter("backimage"));
        this.m.addImage(this.imgBackImage, 0);
        do {
            sParam = this.getParameter("Item" + iParam);
            if (sParam != null) {
                final StringTokenizer sTokens = new StringTokenizer(sParam, ",");
                if (this.FirstBranch == null) {
                    this.FirstBranch = new Branch();
                    this.LastBranch = this.FirstBranch;
                }
                else {
                    this.LastBranch.NextBranch = new Branch();
                    this.LastBranch = this.LastBranch.NextBranch;
                }
                this.LastBranch.Relative = Integer.parseInt(sTokens.nextToken());
                if (sTokens.hasMoreTokens()) {
                    this.LastBranch.Key = Integer.parseInt(sTokens.nextToken());
                }
                else {
                    this.LastBranch.Key = 0;
                }
                if (sTokens.hasMoreTokens()) {
                    this.LastBranch.Text = sTokens.nextToken();
                }
                else {
                    this.LastBranch.Text = "";
                }
                if (sTokens.hasMoreTokens()) {
                    this.LastBranch.HLink = sTokens.nextToken();
                }
                else {
                    this.LastBranch.HLink = "";
                }
                if (sTokens.hasMoreTokens()) {
                    this.LastBranch.Target = sTokens.nextToken();
                }
                else {
                    this.LastBranch.Target = "";
                }
                if (sTokens.hasMoreTokens()) {
                    this.LastBranch.Status = sTokens.nextToken();
                }
                else {
                    this.LastBranch.Status = "";
                }
                if (sTokens.hasMoreTokens()) {
                    sParam = sTokens.nextToken();
                    sParam = sParam.toUpperCase();
                    if (sParam.equals("B")) {
                        this.LastBranch.Heavy = true;
                    }
                    else {
                        this.LastBranch.Heavy = false;
                    }
                }
                if (sTokens.hasMoreTokens()) {
                    this.LastBranch.ImageIndex = Integer.parseInt(sTokens.nextToken());
                }
                else {
                    this.LastBranch.ImageIndex = -1;
                }
                if (sTokens.hasMoreTokens()) {
                    this.LastBranch.ColorIndex = Integer.parseInt(sTokens.nextToken());
                }
                else {
                    this.LastBranch.ColorIndex = 0;
                }
                this.LastBranch.Top = 0;
                this.LastBranch.Expanded = false;
                RelatedBranch = this.GetRelatedBranch(this.LastBranch.Relative);
                if (RelatedBranch != null) {
                    final Branch branch = RelatedBranch;
                    ++branch.Children;
                }
                if (++iParam != 20) {
                    continue;
                }
                sParam = null;
            }
        } while (sParam != null);
        this.LastBranch.NextBranch = new Branch();
        this.LastBranch = this.LastBranch.NextBranch;
        this.LastBranch.Relative = 0;
        this.LastBranch.Key = -255;
        this.LastBranch.Text = "COSMOSoft Ltd";
        this.LastBranch.HLink = "http://www.cosmosoft.co.uk";
        this.LastBranch.Status = "(Unregistered) ViewTree by Dave Jackson, COSMOSoft Ltd www.cosmosoft.co.uk";
        this.LastBranch.Target = "_top";
        this.LastBranch.Heavy = true;
        iParam = 0;
        do {
            sParam = this.getParameter("Image" + iParam);
            if (sParam != null) {
                if (this.FirstImage == null) {
                    this.FirstImage = new imagexx();
                    this.LastImage = this.FirstImage;
                }
                else {
                    this.LastImage.NextImage = new imagexx();
                    this.LastImage = this.LastImage.NextImage;
                }
                this.LastImage.Image = this.getImage(this.getDocumentBase(), sParam);
                this.LastImage.Index = iParam;
                this.m.addImage(this.LastImage.Image, iParam + 1);
                if (++iParam != 5) {
                    continue;
                }
                sParam = null;
            }
        } while (sParam != null);
        sParam = this.getParameter("fontname");
        iParam = Integer.parseInt(this.getParameter("fontsize"));
        if (iParam < 8) {
            iParam = 8;
        }
        this.fFontData = new Font(sParam, 0, iParam);
        this.iIndent = Integer.parseInt(this.getParameter("indent"));
        if (this.iIndent < 16) {
            this.iIndent = 16;
        }
        iParam = 0;
        do {
            sParam = this.getParameter("color" + iParam);
            if (sParam != null) {
                if (this.FirstColor == null) {
                    this.FirstColor = new colorxx();
                    this.LastColor = this.FirstColor;
                }
                else {
                    this.LastColor.NextColor = new colorxx();
                    this.LastColor = this.LastColor.NextColor;
                }
                try {
                    this.LastColor.Color = Color.decode(sParam);
                }
                catch (Exception e) {
                    if (iParam == 0) {
                        this.LastColor.Color = Color.black;
                    }
                    if (iParam == 1) {
                        this.LastColor.Color = Color.lightGray;
                    }
                    if (iParam == 2) {
                        this.LastColor.Color = Color.blue;
                    }
                    if (iParam == 3) {
                        this.LastColor.Color = Color.red;
                    }
                }
                this.LastColor.Index = iParam;
            }
            if (++iParam == 4) {
                sParam = null;
            }
        } while (sParam != null);
        sParam = this.getParameter("treelines");
        if (sParam == null) {
            sParam = "no";
        }
        sParam = sParam.toUpperCase();
        if (sParam.equals("YES")) {
            this.bLines = true;
        }
        else {
            this.bLines = false;
        }
    }
    
    private colorxx GetColor(final int iIndex) {
        for (colorxx tColor = this.FirstColor; tColor != null; tColor = tColor.NextColor) {
            if (tColor.Index == iIndex) {
                return tColor;
            }
        }
        return null;
    }
    
    public void start() {
        try {
            this.m.waitForAll();
        }
        catch (InterruptedException e) {
            return;
        }
        colorxx tColor = this.GetColor(1);
        if (tColor == null) {
            this.setBackground(Color.white);
        }
        else {
            this.setBackground(tColor.Color);
        }
        tColor = this.GetColor(0);
        if (tColor == null) {
            this.setForeground(Color.black);
        }
        else {
            this.setForeground(tColor.Color);
        }
        this.drawSurface();
        this.repaint();
    }
    
    public void CollapseAll(final boolean State) {
        Branch tItem;
        for (tItem = null, tItem = this.FirstBranch; tItem != null; tItem = tItem.NextBranch) {
            tItem.Expanded = State;
        }
        this.repaint();
    }
    
    private void TileBack(final Graphics GraphicSurface, final Image ImageData) {
        final Dimension dim = this.getSize();
        final int iImageHeight = ImageData.getHeight(null);
        final int iImageWidth = ImageData.getWidth(null);
        int iDown = 0;
        if (iImageHeight > 0 && iImageWidth > 0) {
            while (iDown <= dim.height) {
                for (int iAcross = 0; iAcross <= dim.width; iAcross += iImageWidth) {
                    GraphicSurface.drawImage(ImageData, iAcross, iDown, null);
                }
                iDown += iImageHeight;
            }
        }
    }
    
    private Branch GetBranch(final int iX, final int iY) {
        Branch tItem = null;
        final int iHeight = this.grpSurface.getFontMetrics().getMaxAscent();
        for (tItem = this.FirstBranch; tItem != null; tItem = tItem.NextBranch) {
            if (tItem.Top > iY && tItem.Top < iY + iHeight) {
                return tItem;
            }
        }
        return null;
    }
    
    public void init() {
        this.initForm();
        this.usePageParams();
        this.imgBuffer = this.createImage(this.getSize().width, this.getSize().height);
        (this.grpSurface = this.imgBuffer.getGraphics()).setFont(this.fFontData);
    }
    
    public boolean mouseMove(final Event evt, final int x, final int y) {
        final int iLine = 0;
        final int iLineHeight = 0;
        Branch tItem = null;
        tItem = this.GetBranch(x, y);
        if (this.CurrentBranch != null) {
            this.CurrentBranch.HighLighted = false;
        }
        if ((this.CurrentBranch = tItem) != null) {
            if (x >= tItem.Left && x <= tItem.Left + tItem.Width) {
                this.showStatus(tItem.Status);
                String sText;
                if (tItem.HLink != null) {
                    sText = tItem.HLink.trim();
                }
                else {
                    sText = "";
                }
                if (sText.length() > 0) {
                    tItem.HighLighted = true;
                }
            }
        }
        else {
            this.showStatus("(Unregistered) ViewTree by Dave Jackson, COSMOSoft Ltd www.cosmosoft.co.uk");
        }
        this.repaint();
        return true;
    }
}
