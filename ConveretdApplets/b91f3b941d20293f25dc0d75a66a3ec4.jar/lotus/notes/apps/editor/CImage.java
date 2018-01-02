// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.awt.Image;
import java.awt.image.ImageObserver;

class CImage
{
    private int cImgIdx;
    private String cImageURLStr;
    private int cHeight;
    private int cWidth;
    private int cAlign;
    private String cName;
    private String cAltText;
    private static ImageObserver cObserver;
    private static CEscapeMgr cMgr;
    
    public CImage(final Image image, final String cImageURLStr, final int cAlign, final int n, final ImageObserver cObserver, final CEscapeMgr cMgr) {
        if (cMgr != null) {
            CImage.cMgr = cMgr;
        }
        if (CImage.cMgr != null && image != null) {
            this.cImgIdx = CImage.cMgr.addImage(new CImageStore(image, n));
        }
        else {
            this.cImgIdx = -1;
        }
        this.cImageURLStr = cImageURLStr;
        final int n2 = -1;
        this.cWidth = n2;
        this.cHeight = n2;
        this.cAlign = cAlign;
        CImage.cObserver = cObserver;
        this.cName = null;
        this.cAltText = null;
    }
    
    public Object clone() {
        final CImage cImage = new CImage(null, new String(this.cImageURLStr), this.cAlign, 0, CImage.cObserver, null);
        if (this.cName != null && this.cName.length() > 0) {
            cImage.setName(this.cName);
        }
        if (this.cAltText != null && this.cAltText.length() > 0) {
            cImage.setAltText(this.cAltText);
        }
        cImage.setImgIdx(this.cImgIdx);
        final CImageStore image = CImage.cMgr.getImage(this.cImgIdx);
        if (image != null) {
            image.addRef();
        }
        return cImage;
    }
    
    public int release() {
        int release = 0;
        final CImageStore image = CImage.cMgr.getImage(this.cImgIdx);
        if (image != null) {
            release = image.release();
        }
        return release;
    }
    
    public int getHeight() {
        int cHeight = this.cHeight;
        if (this.cHeight == -1 && CImage.cMgr != null) {
            final CImageStore image = CImage.cMgr.getImage(this.cImgIdx);
            if (image != null) {
                cHeight = (this.cHeight = image.getImage().getHeight(CImage.cObserver));
            }
            if (this.cHeight == -1) {
                cHeight = 50;
            }
        }
        return cHeight;
    }
    
    public int getWidth() {
        int cWidth = this.cWidth;
        if (this.cWidth == -1 && CImage.cMgr != null) {
            final CImageStore image = CImage.cMgr.getImage(this.cImgIdx);
            if (image != null) {
                cWidth = (this.cWidth = image.getImage().getWidth(CImage.cObserver));
            }
            if (this.cWidth == -1) {
                cWidth = 50;
            }
        }
        return cWidth;
    }
    
    public int getAlignment() {
        return this.cAlign;
    }
    
    public void setAlignment(final int cAlign) {
        this.cAlign = cAlign;
    }
    
    public String getImageURLStr() {
        return this.cImageURLStr;
    }
    
    public Image getImage() {
        Image image = null;
        if (CImage.cMgr != null) {
            final CImageStore image2 = CImage.cMgr.getImage(this.cImgIdx);
            if (image2 != null) {
                image = image2.getImage();
            }
        }
        return image;
    }
    
    public String getName() {
        return this.cName;
    }
    
    public void setName(final String s) {
        if (s != null) {
            this.cName = new String(s);
        }
    }
    
    public String getAltText() {
        return this.cAltText;
    }
    
    public void setAltText(final String s) {
        if (s != null) {
            this.cAltText = new String(s);
        }
    }
    
    public void updateImage(final Image image, final int n, final String cImageURLStr) {
        this.cImgIdx = CImage.cMgr.addImage(new CImageStore(image, n));
        this.cImageURLStr = cImageURLStr;
        final int n2 = -1;
        this.cWidth = n2;
        this.cHeight = n2;
    }
    
    protected void setImgIdx(final int cImgIdx) {
        this.cImgIdx = cImgIdx;
    }
}
