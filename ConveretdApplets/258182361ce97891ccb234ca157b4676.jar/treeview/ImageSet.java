// 
// Decompiled by Procyon v0.5.30
// 

package treeview;

import java.awt.Image;

public class ImageSet
{
    protected Image[] images;
    
    public ImageSet() {
        this(null);
    }
    
    public ImageSet(final Image[] array) {
        if (array == null) {
            return;
        }
        this.images = new Image[array.length];
        for (int i = 0; i < array.length; ++i) {
            this.images[i] = array[i];
        }
    }
    
    public Image getImage(final int n) {
        try {
            return this.images[n];
        }
        catch (NullPointerException ex) {
            throw new IndexOutOfBoundsException("ImageSet(): no images set for this ImageSet.");
        }
    }
    
    public ImageSet getImages(final int n, final int n2) {
        if (this.images == null) {
            throw new IndexOutOfBoundsException("ImageSet(): no images set for this ImageSet.");
        }
        if (n < 0) {
            throw new IndexOutOfBoundsException("ImageSet(): offset less than zero");
        }
        if (n >= this.images.length) {
            throw new IndexOutOfBoundsException("ImageSet(): offset greater than number of images in set");
        }
        if (n + n2 > this.images.length) {
            throw new IndexOutOfBoundsException("ImageSet(): to many images requested");
        }
        final Image[] array = new Image[n2];
        for (int i = 0; i < n2; ++i) {
            array[i] = this.images[n + i];
        }
        return new ImageSet(array);
    }
    
    public ImageSet getImages(final int[] array) {
        if (this.images == null) {
            throw new IndexOutOfBoundsException("ImageSet(): no images set for this ImageSet.");
        }
        if (array == null) {
            throw new NullPointerException("ImageSet(): images_to_retrieve is a null reference");
        }
        final Image[] array2 = new Image[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = this.getImage(array[i]);
        }
        return new ImageSet(array2);
    }
}
