// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.awt.Image;
import java.util.Vector;

public class CEscapeMgr
{
    public static final int ESC_OBJ = 8232;
    public static final int ESC_OBJ_LEN = 3;
    public static final int ESC_IMG = 2;
    public static final int ESC_HEADLINE = 3;
    public static final int ESC_BASE = 16;
    Vector imageList;
    Vector images;
    
    public CEscapeMgr() {
        this.imageList = new Vector();
        this.images = new Vector();
    }
    
    public int addImageData(final CImage cImage) {
        int size;
        if (cImage == null) {
            size = -1;
        }
        else {
            size = this.imageList.size();
            this.imageList.addElement(cImage);
        }
        return size + 16;
    }
    
    public int addImage(final CImageStore cImageStore) {
        int n = -1;
        int n2 = 0;
        if (cImageStore != null) {
            final Image image = cImageStore.getImage();
            final int type = cImageStore.getType();
            final int size = this.images.size();
            for (int i = 0; i < size; ++i) {
                final CImageStore image2 = this.getImage(i);
                if (image2.getImage() == image && image2.getType() == type) {
                    n2 = 1;
                    image2.addRef();
                    n = i;
                    break;
                }
            }
            if (n2 != 1) {
                this.images.addElement(cImageStore);
                n = size;
            }
        }
        return n;
    }
    
    public CImage getImageData(int n) {
        n -= 16;
        CImage cImage;
        try {
            cImage = this.imageList.elementAt(n);
        }
        catch (Exception ex) {
            System.out.println("Invalid index to CEscapeMgr.getImage(): " + ex);
            cImage = null;
        }
        return cImage;
    }
    
    public CImage findImageData(final String s) {
        CImage imageData = null;
        boolean b = false;
        if (s != null) {
            for (int i = this.imageList.size() - 1; i >= 0; --i) {
                imageData = this.getImageData(i + 16);
                if (imageData != null && s.equalsIgnoreCase(imageData.getName())) {
                    b = true;
                    break;
                }
            }
            if (!b) {
                imageData = null;
            }
        }
        return imageData;
    }
    
    public void clear() {
        if (this.imageList.size() > 0) {
            this.imageList.removeAllElements();
            this.imageList.trimToSize();
        }
    }
    
    public CImageStore getImage(final int n) {
        CImageStore cImageStore = null;
        if (n != -1) {
            try {
                cImageStore = this.images.elementAt(n);
            }
            catch (Exception ex) {
                System.out.println("Invalid index to CEscapeMgr.getImage(): " + ex);
            }
        }
        return cImageStore;
    }
    
    public int countEscObjects(final int n) {
        final int size = this.images.size();
        int n2 = 0;
        for (int i = 0; i < size; ++i) {
            final CImageStore image = this.getImage(i);
            if (image.getType() == n && image.getCount() > 0) {
                ++n2;
            }
        }
        return n2;
    }
}
