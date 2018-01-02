import java.util.StringTokenizer;
import java.awt.font.TextLayout;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.text.AttributedCharacterIterator;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class Hotspot
{
    public static final int IP_HSTYPE;
    public static final int IP_HSFLAGS;
    public static final int IP_HSURL;
    public static final int IP_HSPOPUPTEXT;
    public static final int IP_HSVIEWPOINT;
    public static final int IP_HSREFVIEWPOINT;
    public static final int IP_HSDSTBOUNDS;
    public static final int IP_HSDSTTARGET;
    public static final int IP_HOTSPOTMEDIA;
    public static final int TF_COLORS;
    public static final int TF_COLORSHIFT;
    public static final int K_MEDIA_IPIX = 1;
    public static final int K_MEDIA_WAVE = 3;
    public static final int K_MEDIA_MIDI = 4;
    public static final int K_MEDIA_TEXT = 5;
    public static final int K_MEDIA_URL = 8;
    public static final int K_MEDIA_POPUP = 24;
    public static final int K_MEDIA_FILE = 26;
    private int mType;
    private int mFlags;
    private String mURL;
    private String mPopupText;
    private float[] mVp;
    private float[] mRefVp;
    private Rectangle mBounds;
    private Point mTarget;
    private float[] mDstVp;
    private float[] mDstRefVp;
    private Dimension mDstSize;
    private float[] mDstCenter;
    private float mRadius;
    private FisheyeTransform mXfDst;
    private FisheyeTransform mXfHsDst;
    public boolean paintHotspot;
    public boolean paintTarget;
    public boolean paintPopupText;
    public boolean activateHotspot;
    private Point hotspotEntry;
    
    static {
        IP_HSTYPE = FOUR_CC("type");
        IP_HSFLAGS = FOUR_CC("flgs");
        IP_HSURL = FOUR_CC("hurl");
        IP_HSPOPUPTEXT = FOUR_CC("text");
        IP_HSVIEWPOINT = FOUR_CC("vwpt");
        IP_HSREFVIEWPOINT = FOUR_CC("rfvp");
        IP_HSDSTBOUNDS = FOUR_CC("dbnd");
        IP_HSDSTTARGET = FOUR_CC("dtgt");
        IP_HOTSPOTMEDIA = FOUR_CC("hspm");
        TF_COLORS = Integer.decode("0x000380");
        TF_COLORSHIFT = Integer.decode("0x000007");
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("HotSpot:");
        sb.append(" mType='" + this.mType + "'");
        sb.append(" mFlags='" + this.mFlags + "'");
        sb.append(" mURL='" + this.mURL + "'");
        sb.append(" mPopupText='" + this.mPopupText + "'");
        sb.append(" paintHotspot='" + this.paintHotspot + "'");
        sb.append(" paintTarget='" + this.paintTarget + "'");
        sb.append(" paintPopupText='" + this.paintPopupText + "'");
        sb.append(" activateHotspot='" + this.activateHotspot + "'");
        return sb.toString();
    }
    
    private static int FOUR_CC(final String cc) {
        final byte[] p = { 32, 32, 32, 32 };
        cc.getBytes(0, 4, p, 0);
        return (p[0] << 24 & 0xFF000000) | (p[1] << 16 & 0xFF0000) | (p[2] << 8 & 0xFF00) | (p[3] & 0xFF);
    }
    
    Hotspot() {
        this.mDstCenter = new float[] { 0.0f, 0.0f };
        this.paintHotspot = false;
        this.paintTarget = false;
        this.paintPopupText = false;
        this.activateHotspot = false;
        this.hotspotEntry = null;
        this.mXfHsDst = new FisheyeTransform();
        this.mXfDst = new FisheyeTransform();
    }
    
    public Color getTargetColor() {
        Color targetColor = Color.red;
        final int color = (this.mFlags & Hotspot.TF_COLORS) >> Hotspot.TF_COLORSHIFT;
        switch (color) {
            case 0: {
                targetColor = new Color(255, 0, 0);
                break;
            }
            case 1: {
                targetColor = new Color(128, 0, 128);
                break;
            }
            case 2: {
                targetColor = new Color(128, 128, 0);
                break;
            }
            case 3: {
                targetColor = new Color(255, 0, 255);
                break;
            }
            case 4: {
                targetColor = new Color(0, 128, 0);
                break;
            }
            case 5: {
                targetColor = new Color(0, 0, 255);
                break;
            }
            case 6: {
                targetColor = new Color(0, 0, 0);
                break;
            }
            case 7: {
                targetColor = new Color(255, 255, 255, 0);
                break;
            }
            default: {
                targetColor = new Color(255, 0, 0);
                break;
            }
        }
        return targetColor;
    }
    
    public int getType() {
        return this.mType;
    }
    
    public int getFlags() {
        return this.mFlags;
    }
    
    public String getURL() {
        return this.mURL;
    }
    
    public String getPopupText() {
        return this.mPopupText;
    }
    
    public void setRadius(final float radius) {
        this.mRadius = radius;
        this.mXfHsDst.setRadius(radius);
        this.mXfDst.setRadius(radius);
    }
    
    public float[] getRefVp() {
        return this.mRefVp;
    }
    
    public void setDest(final float[] vp, final float[] refVp, final Dimension size) {
        this.mDstVp = vp.clone();
        this.mDstRefVp = refVp.clone();
        this.mDstSize = size;
        this.mDstCenter[0] = size.width / 2 - 0.5f;
        this.mDstCenter[1] = size.height / 2 - 0.5f;
        final float diagonal = (float)Math.sqrt(this.mDstSize.width * this.mDstSize.width + this.mDstSize.height * this.mDstSize.height);
        final float zs = 0.64f * diagonal / this.mRadius;
        final float[] mDstVp = this.mDstVp;
        final int n = 3;
        mDstVp[n] *= zs;
        this.mXfDst.setRefViewpoint(this.mDstRefVp);
        this.mXfDst.setViewpoint(this.mDstVp);
    }
    
    public boolean isDestPointInHotspot(final Point point) {
        final float[] array;
        final float[] ptDst = array = new float[] { point.x, point.y };
        final int n = 0;
        array[n] -= this.mDstCenter[0];
        final float[] array2 = ptDst;
        final int n2 = 1;
        array2[n2] -= this.mDstCenter[1];
        final float[] ptSrc = { 0.0f, 0.0f };
        final float image = this.mXfDst.forward(ptDst, ptSrc);
        final float[] ptHsDst = { 0.0f, 0.0f };
        if (!this.mXfHsDst.inverse(image, ptSrc, ptHsDst)) {
            return false;
        }
        final Float x = new Float(ptHsDst[0]);
        final Float y = new Float(ptHsDst[1]);
        final Point iPt = new Point((int)(Object)x, (int)(Object)y);
        return this.mBounds.contains(iPt);
    }
    
    public Rectangle getDestBounds() {
        final float[][] hsDstPts = new float[4][2];
        hsDstPts[0][0] = new Integer(this.mBounds.x);
        hsDstPts[0][1] = new Integer(this.mBounds.y);
        hsDstPts[1][0] = new Integer(this.mBounds.x + this.mBounds.width);
        hsDstPts[1][1] = new Integer(this.mBounds.y);
        hsDstPts[2][0] = new Integer(this.mBounds.x + this.mBounds.width);
        hsDstPts[2][1] = new Integer(this.mBounds.y + this.mBounds.height);
        hsDstPts[3][0] = new Integer(this.mBounds.x);
        hsDstPts[3][1] = new Integer(this.mBounds.y + this.mBounds.height);
        final float[] rf = { 10000.0f, 10000.0f, 10000.0f, 10000.0f };
        final float[] dstPt1 = { 0.0f, 0.0f };
        final float[] dstPt2 = { 0.0f, 0.0f };
        for (int i = 0; i < 4; ++i) {
            if (this.findEnd(hsDstPts[i], hsDstPts[(i + 1) % 4], dstPt1)) {
                if (this.findEnd(hsDstPts[(i + 1) % 4], hsDstPts[i], dstPt2)) {
                    rf[0] = Math.min(dstPt2[0], Math.min(dstPt1[0], rf[0]));
                    rf[1] = Math.min(dstPt2[1], Math.min(dstPt1[1], rf[1]));
                    rf[2] = Math.max(dstPt2[0], Math.max(dstPt1[0], rf[2]));
                    rf[3] = Math.max(dstPt2[1], Math.max(dstPt1[1], rf[3]));
                }
            }
        }
        final float[] array = rf;
        final int n = 0;
        array[n] += this.mDstCenter[0] - 2.0f;
        final float[] array2 = rf;
        final int n2 = 1;
        array2[n2] += this.mDstCenter[1] - 2.0f;
        final float[] array3 = rf;
        final int n3 = 2;
        array3[n3] += this.mDstCenter[0] + 2.0f;
        final float[] array4 = rf;
        final int n4 = 3;
        array4[n4] += this.mDstCenter[1] + 2.0f;
        final Rectangle r = new Rectangle();
        r.x = Math.round(rf[0]);
        r.y = Math.round(rf[1]);
        r.width = Math.round(rf[2]) - r.x;
        r.height = Math.round(rf[3]) - r.y;
        return r;
    }
    
    public Point[] getDestPoints() {
        final float[][] hsDstPts = new float[4][2];
        hsDstPts[0][0] = new Integer(this.mBounds.x);
        hsDstPts[0][1] = new Integer(this.mBounds.y);
        hsDstPts[1][0] = new Integer(this.mBounds.x + this.mBounds.width);
        hsDstPts[1][1] = new Integer(this.mBounds.y);
        hsDstPts[2][0] = new Integer(this.mBounds.x + this.mBounds.width);
        hsDstPts[2][1] = new Integer(this.mBounds.y + this.mBounds.height);
        hsDstPts[3][0] = new Integer(this.mBounds.x);
        hsDstPts[3][1] = new Integer(this.mBounds.y + this.mBounds.height);
        final Point[] dstPts = new Point[8];
        for (int i = 0; i < 8; ++i) {
            dstPts[i] = new Point();
            dstPts[i].x = -1;
            dstPts[i].y = -1;
        }
        final float[] dstPt1 = { 0.0f, 0.0f };
        final float[] dstPt2 = { 0.0f, 0.0f };
        for (int i = 0; i < 4; ++i) {
            if (this.findEnd(hsDstPts[i], hsDstPts[(i + 1) % 4], dstPt1)) {
                if (this.findEnd(hsDstPts[(i + 1) % 4], hsDstPts[i], dstPt2)) {
                    dstPts[i * 2].x = Math.round(dstPt1[0] + this.mDstCenter[0]);
                    dstPts[i * 2].y = Math.round(dstPt1[1] + this.mDstCenter[1]);
                    dstPts[i * 2 + 1].x = Math.round(dstPt2[0] + this.mDstCenter[0]);
                    dstPts[i * 2 + 1].y = Math.round(dstPt2[1] + this.mDstCenter[1]);
                }
            }
        }
        return dstPts;
    }
    
    public Point getTargetDestPoint() {
        final Point pt = new Point(-1, -1);
        final float[] srcPt = { 0.0f, 0.0f };
        final float[] dstPt = { 0.0f, 0.0f };
        dstPt[0] = new Integer(this.mTarget.x);
        dstPt[1] = new Integer(this.mTarget.y);
        final float image = this.mXfHsDst.forward(dstPt, srcPt);
        if (!this.mXfDst.inverse(image, srcPt, dstPt)) {
            return pt;
        }
        pt.x = Math.round(dstPt[0] + this.mDstCenter[0]);
        pt.y = Math.round(dstPt[1] + this.mDstCenter[1]);
        return pt;
    }
    
    private boolean findEnd(final float[] p1, final float[] p2, final float[] out) {
        float dx = p2[0] - p1[0];
        float dy = p2[1] - p1[1];
        float incr = Math.max(Math.abs(dx), Math.abs(dy)) / 20.0f + 0.5f;
        if (incr == 0.0f) {
            incr = 1.0f;
        }
        dx /= incr;
        dy /= incr;
        final float[] px = { p1[0], p1[1] };
        final float[] ptSrc = new float[2];
        for (int i = 0; i < incr; ++i) {
            final float image = this.mXfHsDst.forward(px, ptSrc);
            if (this.mXfDst.inverse(image, ptSrc, out)) {
                return true;
            }
            final float[] array = px;
            final int n = 0;
            array[n] += dx;
            final float[] array2 = px;
            final int n2 = 1;
            array2[n2] += dy;
        }
        return false;
    }
    
    public void readFromTag(final TagData hotspotTag) {
        TagData tag = hotspotTag.getChildByID(Hotspot.IP_HSTYPE);
        if (tag != null) {
            this.mType = tag.getInt();
        }
        tag = hotspotTag.getChildByID(Hotspot.IP_HSFLAGS);
        if (tag != null) {
            this.mFlags = tag.getInt();
        }
        tag = hotspotTag.getChildByID(Hotspot.IP_HSURL);
        if (tag != null) {
            this.mURL = tag.getString();
        }
        tag = hotspotTag.getChildByID(Hotspot.IP_HSPOPUPTEXT);
        if (tag != null) {
            this.mPopupText = tag.getString();
        }
        tag = hotspotTag.getChildByID(Hotspot.IP_HSVIEWPOINT);
        if (tag != null) {
            this.mVp = new float[4];
            final byte[] data = tag.getBinary();
            this.mVp[0] = EndianL2B.ToFloat(this.getSubArray(data, 0, 4));
            this.mVp[1] = -EndianL2B.ToFloat(this.getSubArray(data, 4, 4));
            this.mVp[2] = EndianL2B.ToFloat(this.getSubArray(data, 8, 4));
            this.mVp[3] = EndianL2B.ToFloat(this.getSubArray(data, 12, 4));
        }
        tag = hotspotTag.getChildByID(Hotspot.IP_HSREFVIEWPOINT);
        if (tag != null) {
            this.mRefVp = new float[4];
            final byte[] data = tag.getBinary();
            this.mRefVp[0] = EndianL2B.ToFloat(this.getSubArray(data, 0, 4));
            this.mRefVp[1] = -EndianL2B.ToFloat(this.getSubArray(data, 4, 4));
            this.mRefVp[2] = EndianL2B.ToFloat(this.getSubArray(data, 8, 4));
            this.mRefVp[3] = EndianL2B.ToFloat(this.getSubArray(data, 12, 4));
        }
        tag = hotspotTag.getChildByID(Hotspot.IP_HSDSTBOUNDS);
        if (tag != null) {
            this.mBounds = new Rectangle();
            final byte[] data = tag.getBinary();
            this.mBounds.x = EndianL2B.ToInt(this.getSubArray(data, 0, 4));
            this.mBounds.y = EndianL2B.ToInt(this.getSubArray(data, 4, 4));
            this.mBounds.width = EndianL2B.ToInt(this.getSubArray(data, 8, 4)) - this.mBounds.x;
            this.mBounds.height = EndianL2B.ToInt(this.getSubArray(data, 12, 4)) - this.mBounds.y;
        }
        tag = hotspotTag.getChildByID(Hotspot.IP_HSDSTTARGET);
        if (tag != null) {
            this.mTarget = new Point();
            final byte[] data = tag.getBinary();
            this.mTarget.x = EndianL2B.ToInt(this.getSubArray(data, 0, 4));
            this.mTarget.y = EndianL2B.ToInt(this.getSubArray(data, 4, 4));
        }
        this.mXfHsDst.setRefViewpoint(this.mRefVp);
        this.mXfHsDst.setViewpoint(this.mVp);
    }
    
    private byte[] getSubArray(final byte[] a, final int start, final int count) {
        if (start + count > a.length) {
            return null;
        }
        final byte[] subArray = new byte[count];
        for (int i = 0; i < count; ++i) {
            subArray[i] = a[start + i];
        }
        return subArray;
    }
    
    boolean paint(final Graphics g, final Controller controller) {
        if (!controller.getHost().hotspotsActive) {
            return false;
        }
        try {
            if (this.paintHotspot) {
                g.setColor(controller.getHost().hotspotBoundsColor);
                final Point[] points = this.getDestPoints();
                if (points[0].x != -1 && points[0].y != -1 && points[1].x != -1 && points[1].y != -1) {
                    g.drawLine(points[0].x, points[0].y, points[1].x, points[1].y);
                }
                if (points[2].x != -1 && points[2].y != -1 && points[3].x != -1 && points[3].y != -1) {
                    g.drawLine(points[2].x, points[2].y, points[3].x, points[3].y);
                }
                if (points[4].x != -1 && points[4].y != -1 && points[5].x != -1 && points[5].y != -1) {
                    g.drawLine(points[4].x, points[4].y, points[5].x, points[5].y);
                }
                if (points[6].x != -1 && points[6].y != -1 && points[7].x != -1 && points[7].y != -1) {
                    g.drawLine(points[6].x, points[6].y, points[7].x, points[7].y);
                }
            }
            if (controller.getHost().targetsOn) {
                final Color targetColor = this.getTargetColor();
                final Point target = this.getTargetDestPoint();
                if (target.x != -1 && target.y != -1 && targetColor.getAlpha() != 0) {
                    g.setColor(targetColor);
                    g.fillOval(target.x - 8, target.y - 8, 16, 16);
                    g.setColor(Color.white);
                    g.fillOval(target.x + 2 - 8, target.y + 2 - 8, 12, 12);
                    g.setColor(targetColor);
                    g.fillOval(target.x + 5 - 8, target.y + 5 - 8, 6, 6);
                }
            }
            if (controller.getHost().popupTextOn & this.paintPopupText) {
                final int appletWidth = new Integer(controller.getHost().getParameter("width"));
                final int appletHeight = new Integer(controller.getHost().getParameter("height"));
                final String popupText = this.getPopupText().trim();
                if (popupText.length() > 0) {
                    final Graphics2D g2d = (Graphics2D)g;
                    final FontRenderContext frc = g2d.getFontRenderContext();
                    final int rightAlignWidth = appletWidth - this.hotspotEntry.x;
                    final int leftAlignWidth = this.hotspotEntry.x;
                    final int bottomAlignHeight = appletHeight - this.hotspotEntry.y;
                    final int topAlignHeight = this.hotspotEntry.y;
                    final Rectangle textRect = new Rectangle();
                    if (leftAlignWidth > rightAlignWidth) {
                        textRect.x = 3;
                        textRect.width = leftAlignWidth - 16;
                    }
                    else {
                        textRect.x = this.hotspotEntry.x + 13;
                        textRect.width = rightAlignWidth - 16;
                    }
                    if (topAlignHeight > bottomAlignHeight) {
                        textRect.y = 3;
                        textRect.height = topAlignHeight - 6;
                    }
                    else {
                        textRect.y = this.hotspotEntry.y + 3;
                        textRect.height = bottomAlignHeight - 6;
                    }
                    final int maxTextWidth = textRect.width;
                    final AttributedString aString = new AttributedString(popupText);
                    aString.addAttribute(TextAttribute.FONT, g.getFont());
                    final AttributedCharacterIterator styledText = aString.getIterator();
                    LineBreakMeasurer measurer = new LineBreakMeasurer(styledText, frc);
                    final Point textBoxSize = new Point(0, 0);
                    while (measurer.getPosition() < popupText.length()) {
                        final TextLayout layout = measurer.nextLayout(maxTextWidth);
                        textBoxSize.x = Math.max(textBoxSize.x, (int)layout.getAdvance());
                        final int dy = (int)(layout.getAscent() + layout.getDescent() + layout.getLeading());
                        if (textBoxSize.y + dy >= textRect.height) {
                            break;
                        }
                        final Point point = textBoxSize;
                        point.y += dy;
                    }
                    if (leftAlignWidth > rightAlignWidth) {
                        final Rectangle rectangle = textRect;
                        rectangle.x += textRect.width - textBoxSize.x;
                    }
                    if (topAlignHeight > bottomAlignHeight) {
                        final Rectangle rectangle2 = textRect;
                        rectangle2.y += textRect.height - textBoxSize.y;
                    }
                    textRect.width = textBoxSize.x;
                    textRect.height = textBoxSize.y + 1;
                    final Rectangle outlineRect = new Rectangle(textRect.x - 3, textRect.y - 3, textRect.width + 6, textRect.height + 6);
                    final Rectangle bgRect = new Rectangle(textRect.x - 2, textRect.y - 2, textRect.width + 4, textRect.height + 4);
                    g.setColor(new Color(253, 249, 206));
                    g.fillRect(bgRect.x, bgRect.y, bgRect.width, bgRect.height);
                    g.setColor(Color.black);
                    g.drawRect(outlineRect.x, outlineRect.y, outlineRect.width, outlineRect.height);
                    final Point pen = new Point(textRect.x, textRect.y);
                    measurer = new LineBreakMeasurer(styledText, frc);
                    while (measurer.getPosition() < popupText.length()) {
                        final TextLayout layout2 = measurer.nextLayout(maxTextWidth);
                        final int dy2 = (int)(layout2.getAscent() + layout2.getDescent() + layout2.getLeading());
                        if (pen.y + dy2 - textRect.y >= textRect.height) {
                            break;
                        }
                        final Point point2 = pen;
                        point2.y += (int)layout2.getAscent();
                        final float dx = layout2.isLeftToRight() ? 0.0f : (textRect.width - layout2.getAdvance());
                        layout2.draw(g2d, pen.x + dx, pen.y);
                        final Point point3 = pen;
                        point3.y += (int)(layout2.getDescent() + layout2.getLeading());
                    }
                }
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    void setEntryPoint(final Point entry) {
        if (!this.paintHotspot) {
            this.hotspotEntry = new Point(entry.x, entry.y);
        }
    }
    
    boolean activate(final Controller controller) {
        if (controller.getHost().hotspotsActive && this.activateHotspot) {
            String url = null;
            String frame = null;
            final StringTokenizer urlWithFrame = new StringTokenizer(this.getURL(), ";");
            if (urlWithFrame.hasMoreTokens()) {
                url = urlWithFrame.nextToken().trim();
            }
            if (urlWithFrame.hasMoreTokens()) {
                frame = urlWithFrame.nextToken().trim();
            }
            switch (this.mType) {
                case 1: {
                    controller.getHost().LoadIpixFile(url, true);
                    break;
                }
                case 3: {
                    controller.getHost().PlayClip(url, true);
                    break;
                }
                case 4: {
                    controller.getHost().PlayClip(url, true);
                    break;
                }
                case 5: {
                    break;
                }
                case 8: {
                    controller.getHost().LaunchURL(url, frame, true);
                    break;
                }
                case 24: {
                    break;
                }
                case 26: {
                    if (url.endsWith(".wav") || url.endsWith(".mid")) {
                        controller.getHost().PlayClip(url, true);
                        break;
                    }
                    controller.getHost().LaunchFile(url, frame, true);
                    break;
                }
                default: {
                    controller.getHost().LaunchFile(url, frame, true);
                    break;
                }
            }
            this.activateHotspot = false;
            return true;
        }
        return false;
    }
}
