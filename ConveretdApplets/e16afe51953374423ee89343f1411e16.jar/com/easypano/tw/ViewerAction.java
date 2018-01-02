// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

public interface ViewerAction
{
    void zoomIn();
    
    void zoomOut();
    
    void panLeft();
    
    void panRight();
    
    void panUp();
    
    void panDown();
    
    void autoPan(final double p0, final double p1, final double p2);
    
    void autoPanAtRate(final int p0, final int p1, final int p2);
    
    void stopAutoPan();
    
    void gotoView(final double p0, final double p1, final double p2);
    
    void showHS();
    
    void hideHS();
    
    void toggleHS();
    
    void switchToScene(final int p0);
    
    void switchToScene(final int p0, final double p1, final double p2, final double p3);
    
    void nextScene();
    
    void previousScene();
    
    void forward();
    
    void backward();
    
    void playMovie(final int p0);
    
    void playPath(final int p0);
    
    void playPausePath();
    
    void stopMovie();
    
    void playSound(final int p0, final boolean p1);
    
    void stopSound();
}
