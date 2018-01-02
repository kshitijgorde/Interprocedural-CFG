import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.image.BufferedImage;

// 
// Decompiled by Procyon v0.5.30
// 

class Skin
{
    static boolean skinInitialized;
    static BufferedImage askBubbleFrame;
    static BufferedImage askPointerL;
    static BufferedImage askPointerR;
    static BufferedImage bubbleFrame;
    static BufferedImage goButton;
    static BufferedImage goButtonOver;
    static BufferedImage promptCheckButton;
    static BufferedImage sliderKnob;
    static BufferedImage sliderSlot;
    static BufferedImage stopButton;
    static BufferedImage stopButtonOver;
    static BufferedImage talkPointerL;
    static BufferedImage talkPointerR;
    static BufferedImage thinkPointerL;
    static BufferedImage thinkPointerR;
    static BufferedImage watcherOuterFrame;
    static BufferedImage listWatcherOuterFrame;
    static BufferedImage listWatcherOuterFrameError;
    static BufferedImage vScrollFrame;
    static BufferedImage vScrollSlider;
    
    static synchronized void readSkin(final Component component) {
        if (Skin.skinInitialized) {
            return;
        }
        Skin.askBubbleFrame = readImage("askBubbleFrame.gif", component);
        Skin.askPointerL = readImage("askBubblePointer.gif", component);
        Skin.askPointerR = flipImage(Skin.askPointerL);
        Skin.bubbleFrame = readImage("talkBubbleFrame.gif", component);
        Skin.goButton = readImage("goButton.gif", component);
        Skin.goButtonOver = readImage("goButtonOver.gif", component);
        Skin.promptCheckButton = readImage("promptCheckButton.png", component);
        Skin.sliderKnob = readImage("sliderKnob.gif", component);
        Skin.sliderSlot = readImage("sliderSlot.gif", component);
        Skin.stopButton = readImage("stopButton.gif", component);
        Skin.stopButtonOver = readImage("stopButtonOver.gif", component);
        Skin.talkPointerL = readImage("talkBubbleTalkPointer.gif", component);
        Skin.talkPointerR = flipImage(Skin.talkPointerL);
        Skin.thinkPointerL = readImage("talkBubbleThinkPointer.gif", component);
        Skin.thinkPointerR = flipImage(Skin.thinkPointerL);
        Skin.watcherOuterFrame = readImage("watcherOuterFrame.png", component);
        Skin.listWatcherOuterFrame = readImage("listWacherOuterFrame.png", component);
        Skin.listWatcherOuterFrameError = readImage("listWacherOuterFrameError.png", component);
        Skin.vScrollFrame = readImage("vScrollFrame.png", component);
        Skin.vScrollSlider = readImage("vScrollSlider.png", component);
        Skin.skinInitialized = true;
    }
    
    static BufferedImage readImage(final String s, final Component component) {
        final Image image = Toolkit.getDefaultToolkit().createImage(component.getClass().getResource("skin/" + s));
        final MediaTracker mediaTracker = new MediaTracker(component);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        final BufferedImage bufferedImage = new BufferedImage(width, height, 2);
        final Graphics graphics = bufferedImage.getGraphics();
        graphics.drawImage(image, 0, 0, width, height, null);
        graphics.dispose();
        return bufferedImage;
    }
    
    static BufferedImage flipImage(final BufferedImage bufferedImage) {
        final int width = bufferedImage.getWidth(null);
        final int height = bufferedImage.getHeight(null);
        final BufferedImage bufferedImage2 = new BufferedImage(width, height, 2);
        final Graphics graphics = bufferedImage2.getGraphics();
        graphics.drawImage(bufferedImage, width, 0, 0, height, 0, 0, width, height, null);
        graphics.dispose();
        return bufferedImage2;
    }
}
