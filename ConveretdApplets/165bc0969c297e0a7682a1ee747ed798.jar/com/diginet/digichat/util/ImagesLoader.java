// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.util;

import java.awt.image.ImageObserver;
import com.diginet.digichat.common.bp;
import com.diginet.digichat.client.i;
import java.util.Vector;

public class ImagesLoader implements Runnable
{
    private boolean fImages;
    private int nImages;
    private int iImage;
    private String strImages;
    private Vector vecLoad;
    private Vector vecListeners;
    private i iUser;
    private n arrImages;
    
    public void reset(final Vector vector) {
        synchronized (this.arrImages) {
            this.arrImages.a(true);
            for (int i = 0; i < vector.size(); ++i) {
                vector.elementAt(i).a = null;
            }
            this.arrImages.a();
            this.iImage = 0;
        }
        // monitorexit(this.arrImages)
    }
    
    public ImagesLoader(final i iUser, final n arrImages, final String strImages, final int nImages, final Vector vector) {
        this.iUser = iUser;
        this.nImages = nImages;
        this.arrImages = arrImages;
        this.strImages = strImages;
        this.fImages = true;
        this.vecLoad = new Vector();
        this.vecListeners = new Vector();
        this.reset(vector);
        new Thread(this).start();
    }
    
    public void addListener(final ImagesListener imagesListener) {
        if (this.fImages) {
            this.vecListeners.addElement(imagesListener);
        }
        else {
            imagesListener.loadDone();
        }
    }
    
    public boolean isActive() {
        return this.fImages;
    }
    
    public void load(final bp bp) {
        this.vecLoad.addElement(bp);
        synchronized (bp) {
            Label_0020: {
                try {
                    bp.wait();
                    break Label_0020;
                }
                catch (InterruptedException ex) {
                }
                // monitorexit(bp)
            }
        }
    }
    
    public void run() {
        while (this.iImage < this.arrImages.b()) {
            Object element;
            bp bp;
            if (this.vecLoad.size() > 0) {
                bp = (bp)(element = this.vecLoad.elementAt(0));
                this.vecLoad.removeElementAt(0);
            }
            else {
                element = null;
                bp = (bp)this.arrImages.d(this.iImage++);
            }
            if (bp.a == null) {
                bp.a = this.iUser.loadImage(String.valueOf(this.strImages).concat(String.valueOf(bp.x())), null, this.nImages);
            }
            if (element != null) {
                synchronized (element) {
                    element.notify();
                }
                // monitorexit(element)
            }
        }
        this.fImages = false;
        while (this.vecListeners.size() > 0) {
            this.vecListeners.elementAt(0).loadDone();
            this.vecListeners.removeElementAt(0);
        }
    }
}
