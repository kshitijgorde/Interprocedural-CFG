// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver;

import com.easypano.tourweaver.f.a;
import com.easypano.tourweaver.f.ab;
import com.easypano.tourweaver.f.h;

public interface n
{
    void play();
    
    void forward();
    
    void backward();
    
    void destroy();
    
    void setResManager(final p p0);
    
    void previousScene();
    
    void nextScene();
    
    void reset();
    
    void setAutoRunMovie(final boolean p0);
    
    void switchToScene(final String p0);
    
    void switchToScene(final String p0, final double p1, final double p2, final double p3);
    
    void switchToScene(final String p0, final double p1, final double p2, final double p3, final String p4, final long p5, final double p6, final double p7, final double p8, final double p9, final double p10, final double p11, final String p12, final String p13, final String p14);
    
    void goToViewer(final double p0, final double p1, final double p2);
    
    void switchToMap(final String p0, final String p1);
    
    void switchToMap(final String p0, final String p1, final String p2, final long p3);
    
    void playMovie();
    
    void setDefaultMovie(final String p0);
    
    void playMovie(final boolean p0);
    
    void playMovie(final String p0);
    
    void playMovie(final String p0, final boolean p1);
    
    void stopMovie();
    
    void stop();
    
    void autoPanScene(final double p0, final double p1, final double p2);
    
    void autoPanScene(final int p0, final int p1, final int p2);
    
    void addPlayerListener(final PlayerListener p0);
    
    void removePlayerListener(final PlayerListener p0);
    
    void addRenderable(final h p0, final int p1);
    
    void addRenderTarget(final ab p0, final String p1);
    
    int getRenderNum(final int p0);
    
    h getRenderable(final String p0, final int p1);
    
    com.easypano.tourweaver.f.n getMovie(final String p0);
    
    void goToViewer(final double p0, final double p1, final double p2, final int p3);
    
    void addAnimation(final a p0, final int p1);
    
    void setQuality(final int p0);
}
