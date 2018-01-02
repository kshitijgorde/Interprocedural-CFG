// 
// Decompiled by Procyon v0.5.30
// 

package org.wordsmith.anagram;

import java.util.Timer;
import java.util.TimerTask;

public class LoadStringsTask extends TimerTask
{
    private final Configuration myConfiguration;
    private final AnagramStringsSupplier myStringsSupplier;
    private final Timer myTimer;
    private final ShowBannerTask myShowBannerTask;
    private final AnagramApplet myApplet;
    
    public LoadStringsTask(final AnagramApplet myApplet, final Timer myTimer, final ShowBannerTask myShowBannerTask) {
        this.myApplet = myApplet;
        this.myTimer = myTimer;
        this.myShowBannerTask = myShowBannerTask;
        this.myConfiguration = myApplet.getConfiguration();
        this.myStringsSupplier = this.myConfiguration.getStringSupplier();
        if (!this.myStringsSupplier.isReady()) {
            this.myStringsSupplier.startLoading();
        }
    }
    
    public void run() {
        if (this.myStringsSupplier.isReady()) {
            this.cancel();
            this.scheduleAnimation();
        }
    }
    
    private void scheduleAnimation() {
        if (!this.myStringsSupplier.isReady()) {
            throw new IllegalStateException("Animation is not yet ready -- no anagrams available");
        }
        int n = this.myConfiguration.getSymbolAnimationTimeMillis() / this.myConfiguration.getSymbolFramesCount();
        if (n < 10) {
            n = 10;
        }
        final int n2 = this.myConfiguration.getSymbolFramesCount() + this.myStringsSupplier.getSourceString().length() * this.myConfiguration.getSymbolDelayMillis() / n;
        final int n3 = this.myConfiguration.getPauseInMillisecs() / n;
        final AnagramCanvas anagramCanvas = this.myShowBannerTask.getAnagramCanvas();
        anagramCanvas.initAnimation(this.myStringsSupplier.getSourceString(), this.myStringsSupplier.getTargetString());
        this.myTimer.schedule(new SelectorTask(new AnimationTask(anagramCanvas, n2, n3, this.myConfiguration), this.myShowBannerTask, this.myApplet.isAuthorized() ? 0 : (this.myConfiguration.getBannerTimeMS() / n)), 0L, n);
    }
}
