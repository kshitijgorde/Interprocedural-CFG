// 
// Decompiled by Procyon v0.5.30
// 

package imaging.graphics;

import java.awt.GraphicsConfiguration;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.Graphics2D;
import java.lang.reflect.InvocationTargetException;
import java.awt.EventQueue;
import java.awt.Window;
import javax.swing.JFrame;
import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;

public class ScreenManager
{
    private GraphicsDevice device;
    
    public ScreenManager() {
        final GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.device = environment.getDefaultScreenDevice();
    }
    
    public DisplayMode[] getCompatibleDisplayModes() {
        return this.device.getDisplayModes();
    }
    
    public DisplayMode findFirstCompatibleMode(final DisplayMode[] modes) {
        final DisplayMode[] goodModes = this.device.getDisplayModes();
        for (int i = 0; i < modes.length; ++i) {
            for (int j = 0; j < goodModes.length; ++j) {
                if (this.displayModesMatch(modes[i], goodModes[j])) {
                    return modes[i];
                }
            }
        }
        return null;
    }
    
    public DisplayMode getCurrentDisplayMode() {
        return this.device.getDisplayMode();
    }
    
    public boolean displayModesMatch(final DisplayMode mode1, final DisplayMode mode2) {
        return mode1.getWidth() == mode2.getWidth() && mode1.getHeight() == mode2.getHeight() && (mode1.getBitDepth() == -1 || mode2.getBitDepth() == -1 || mode1.getBitDepth() == mode2.getBitDepth()) && (mode1.getRefreshRate() == 0 || mode2.getRefreshRate() == 0 || mode1.getRefreshRate() == mode2.getRefreshRate());
    }
    
    public void setFullScreen(final DisplayMode displayMode) {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        frame.setUndecorated(true);
        frame.setIgnoreRepaint(true);
        frame.setResizable(false);
        this.device.setFullScreenWindow(frame);
        if (displayMode != null && this.device.isDisplayChangeSupported()) {
            try {
                this.device.setDisplayMode(displayMode);
            }
            catch (IllegalArgumentException ex) {}
            frame.setSize(displayMode.getWidth(), displayMode.getHeight());
        }
        try {
            EventQueue.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    frame.createBufferStrategy(2);
                }
            });
        }
        catch (InterruptedException ex2) {}
        catch (InvocationTargetException ex3) {}
    }
    
    public Graphics2D getGraphics() {
        final Window window = this.device.getFullScreenWindow();
        if (window != null) {
            final BufferStrategy strategy = window.getBufferStrategy();
            return (Graphics2D)strategy.getDrawGraphics();
        }
        return null;
    }
    
    public void update() {
        final Window window = this.device.getFullScreenWindow();
        if (window != null) {
            final BufferStrategy strategy = window.getBufferStrategy();
            if (!strategy.contentsLost()) {
                strategy.show();
            }
        }
    }
    
    public JFrame getFullScreenWindow() {
        return (JFrame)this.device.getFullScreenWindow();
    }
    
    public int getWidth() {
        final Window window = this.device.getFullScreenWindow();
        if (window != null) {
            return window.getWidth();
        }
        return 0;
    }
    
    public int getHeight() {
        final Window window = this.device.getFullScreenWindow();
        if (window != null) {
            return window.getHeight();
        }
        return 0;
    }
    
    public void restoreScreen() {
        final Window window = this.device.getFullScreenWindow();
        if (window != null) {
            window.dispose();
        }
        this.device.setFullScreenWindow(null);
    }
    
    public BufferedImage createCompatibleImage(final int w, final int h, final int transparancy) {
        final Window window = this.device.getFullScreenWindow();
        if (window != null) {
            final GraphicsConfiguration gc = window.getGraphicsConfiguration();
            return gc.createCompatibleImage(w, h, transparancy);
        }
        return null;
    }
}
