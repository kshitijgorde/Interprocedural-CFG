// 
// Decompiled by Procyon v0.5.30
// 

package org.wordsmith.anagram;

public class ShowBannerTask implements Runnable
{
    private final AnagramCanvas myAnagramCanvas;
    
    public ShowBannerTask(final AnagramCanvas myAnagramCanvas) {
        this.myAnagramCanvas = myAnagramCanvas;
    }
    
    public void run() {
        this.myAnagramCanvas.paintBannerFrame();
    }
    
    public AnagramCanvas getAnagramCanvas() {
        return this.myAnagramCanvas;
    }
}
