// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.awt.Color;

class CFormatInfo implements Cloneable
{
    private String cFaceName;
    private int cSize;
    private Color cColor;
    private String cURL;
    private int cFlags;
    private int cOverride;
    private boolean cRevert;
    public static Color COLOR_UNK;
    private static final int FF_BOLD = 1;
    private static final int FF_ITALIC = 2;
    private static final int FF_UNDERLINE = 4;
    private static final int FF_STRIKETHROUGH = 8;
    private static final int FF_FACENAME = 16;
    private static final int FF_SIZE = 32;
    private static final int FF_COLOR = 64;
    private static final int FF_URL = 128;
    private static final int FF_UNKHTML = 256;
    private static final int FF_ATTRIBUTES = 271;
    private static final int FF_ALL = 511;
    
    CFormatInfo() {
        this.clear();
    }
    
    void clear() {
        this.cFaceName = "TimesRoman";
        this.cSize = 11;
        this.cColor = Color.black;
        this.cURL = null;
        final boolean b = false;
        this.cOverride = (b ? 1 : 0);
        this.cFlags = (b ? 1 : 0);
        this.cRevert = false;
    }
    
    void duplicate(final CFormatInfo cFormatInfo) {
        cFormatInfo.cFaceName = this.cFaceName;
        cFormatInfo.cSize = this.cSize;
        cFormatInfo.cColor = this.cColor;
        cFormatInfo.cURL = this.cURL;
        cFormatInfo.cFlags = this.cFlags;
        cFormatInfo.cOverride = this.cOverride;
        cFormatInfo.cRevert = this.cRevert;
    }
    
    void revert() {
        this.cFlags = 0;
        this.cOverride = 0;
    }
    
    boolean equals(final CFormatInfo cFormatInfo) {
        if (this.cOverride != cFormatInfo.cOverride || this.cFlags != cFormatInfo.cFlags || this.cSize != cFormatInfo.cSize || this.cRevert != cFormatInfo.cRevert || !this.cColor.equals(cFormatInfo.cColor) || !this.cFaceName.equals(cFormatInfo.cFaceName)) {
            return false;
        }
        if (this.cURL == null) {
            return cFormatInfo.cURL == null;
        }
        return this.cURL.equals(cFormatInfo.cURL);
    }
    
    public boolean equals(final Object o) {
        return o instanceof CFormatInfo && this.equals((CFormatInfo)o);
    }
    
    private void setState(final boolean b, final int n) {
        if (b) {
            this.cFlags |= n;
        }
        else {
            this.cFlags &= ~n;
        }
        this.cOverride |= n;
    }
    
    final boolean isAnythingOverridden() {
        return this.cOverride != 0;
    }
    
    final String getFaceName() {
        return this.cFaceName;
    }
    
    void setFaceName(final String cFaceName) {
        this.cFaceName = cFaceName;
        this.cOverride |= 0x10;
    }
    
    final boolean isFaceNameOverridden() {
        return (this.cOverride & 0x10) != 0x0;
    }
    
    final int getPointSize() {
        return this.cSize;
    }
    
    void setPointSize(final int cSize) {
        this.cSize = cSize;
        this.cOverride |= 0x20;
    }
    
    final boolean isSizeOverridden() {
        return (this.cOverride & 0x20) != 0x0;
    }
    
    final boolean isBold() {
        return (this.cFlags & 0x1) != 0x0;
    }
    
    final void setBold(final boolean b) {
        this.setState(b, 1);
    }
    
    final boolean isBoldOverridden() {
        return (this.cOverride & 0x1) != 0x0;
    }
    
    final boolean isStrikeThrough() {
        return (this.cFlags & 0x8) != 0x0;
    }
    
    void setStrikeThrough(final boolean b) {
        this.setState(b, 8);
    }
    
    final boolean isStrikeThroughOverridden() {
        return (this.cOverride & 0x8) != 0x0;
    }
    
    final boolean isItalic() {
        return (this.cFlags & 0x2) != 0x0;
    }
    
    void setItalic(final boolean b) {
        this.setState(b, 2);
    }
    
    final boolean isItalicOverridden() {
        return (this.cOverride & 0x2) != 0x0;
    }
    
    final boolean isUnderline() {
        return (this.cFlags & 0x4) != 0x0;
    }
    
    void setUnderline(final boolean b) {
        this.setState(b, 4);
    }
    
    final boolean isUnderlineOverridden() {
        return (this.cOverride & 0x4) != 0x0;
    }
    
    final boolean isDifficultToPaint() {
        return (this.cOverride & 0x8C) != 0x0;
    }
    
    final Color getColor() {
        return this.cColor;
    }
    
    void setColor(final Color cColor) {
        this.cColor = cColor;
        this.cOverride |= 0x40;
    }
    
    final boolean isColorOverridden() {
        return (this.cOverride & 0x40) != 0x0;
    }
    
    final String getURL() {
        return this.cURL;
    }
    
    void setURL(final String curl) {
        this.cURL = curl;
        this.cOverride |= 0x80;
    }
    
    final boolean isURLOverridden() {
        return (this.cOverride & 0x80) != 0x0;
    }
    
    final boolean isUnkHtml() {
        return (this.cFlags & 0x100) != 0x0;
    }
    
    void setUnkHtml(final boolean b) {
        this.setState(b, 256);
    }
    
    void setNormal() {
        this.cRevert = true;
    }
    
    void apply(final CFormatInfo cFormatInfo) {
        if (this.cRevert) {
            final boolean b = false;
            cFormatInfo.cOverride = (b ? 1 : 0);
            cFormatInfo.cFlags = (b ? 1 : 0);
            return;
        }
        cFormatInfo.cOverride |= this.cOverride;
        cFormatInfo.cFlags &= ~this.cOverride;
        cFormatInfo.cFlags |= (this.cOverride & this.cFlags);
        if ((this.cOverride & 0x10) != 0x0) {
            cFormatInfo.cFaceName = this.cFaceName;
        }
        if ((this.cOverride & 0x20) != 0x0) {
            cFormatInfo.cSize = this.cSize;
        }
        if ((this.cOverride & 0x40) != 0x0) {
            cFormatInfo.cColor = this.cColor;
        }
        if ((this.cOverride & 0x80) != 0x0) {
            cFormatInfo.cURL = this.cURL;
            if (cFormatInfo.cURL == null) {
                cFormatInfo.cOverride &= 0xFFFFFF7F;
            }
        }
    }
    
    boolean affects(final CFormatInfo cFormatInfo) {
        if (this.cOverride == 0) {
            return false;
        }
        if ((this.cOverride & this.cFlags & 0x10F) != (cFormatInfo.cFlags & 0x10F)) {
            return true;
        }
        if ((this.cOverride & 0x20) != 0x0 && this.cSize != cFormatInfo.cSize) {
            return true;
        }
        if ((this.cOverride & 0x10) != 0x0 && !this.cFaceName.equals(cFormatInfo.cFaceName)) {
            return true;
        }
        if ((this.cOverride & 0x40) != 0x0 && !this.cColor.equals(cFormatInfo.cColor)) {
            return true;
        }
        if ((this.cOverride & 0x80) == 0x0) {
            return false;
        }
        if (this.cURL == null) {
            return cFormatInfo.cURL != null;
        }
        return !this.cURL.equals(cFormatInfo.cURL);
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
    }
    
    static {
        CFormatInfo.COLOR_UNK = new Color(-13447886);
    }
}
