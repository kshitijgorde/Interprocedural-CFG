// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver;

public interface ViewerAction
{
    void addCommunicationAction(final CommunicationAction p0);
    
    void stop();
    
    void stopAutoPan();
    
    boolean hasMapViewer();
    
    boolean emailTo(final String p0);
    
    void setFullScreen(final boolean p0);
    
    boolean hasSceneViewer();
    
    int getSceneCount();
    
    int getMapCount();
    
    void zoomIn();
    
    void zoomIn(final int p0);
    
    void zoomOut();
    
    void zoomOut(final int p0);
    
    void left();
    
    void left(final int p0);
    
    void right();
    
    void right(final int p0);
    
    void up();
    
    void up(final int p0);
    
    void down();
    
    void down(final int p0);
    
    void reset();
    
    void autoPanAtRate(final double p0, final double p1, final double p2);
    
    void autoPan(final int p0, final int p1, final int p2);
    
    void gotoView(final double p0, final double p1, final double p2);
    
    void gotoView(final double p0, final double p1, final double p2, final int p3);
    
    void showHideHotspot(final String p0);
    
    void switchToScene(final String p0);
    
    void switchToScene(final String p0, final double p1, final double p2, final double p3);
    
    void switchToScene(final String p0, final double p1, final double p2, final double p3, final String p4, final int p5);
    
    void nextScene();
    
    void previousScene();
    
    void forward();
    
    void backward();
    
    void playPauseMovie(final String p0, final boolean p1);
    
    void stopMovie();
    
    void playSound(final int p0, final boolean p1);
    
    void mute();
    
    void fullScreen(final String p0);
    
    void closeWindow();
    
    void showHelp();
    
    void print();
    
    void emailTo();
    
    void switchToMap(final String p0);
    
    void switchToMap(final String p0, final String p1, final int p2);
    
    void linkToDetailImage(final String p0);
    
    void mZoomIn();
    
    void mZoomIn(final int p0);
    
    void mZoomOut();
    
    void mZoomOut(final int p0);
    
    void mLeft();
    
    void mLeft(final int p0);
    
    void mRight();
    
    void mRight(final int p0);
    
    void mUp();
    
    void mUp(final int p0);
    
    void mDown();
    
    void mDown(final int p0);
    
    void mAutoPan(final int p0, final int p1, final int p2);
    
    void mGotoView(final double p0, final double p1, final double p2);
}
