import java.awt.GraphicsEnvironment;
import java.lang.reflect.Field;
import java.awt.Window;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.DisplayMode;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Class68
{
    private DisplayMode aDisplayMode522;
    private GraphicsDevice aGraphicsDevice523;
    
    public final void method690() {
        try {
            if (null != this.aDisplayMode522) {
                this.aGraphicsDevice523.setDisplayMode(this.aDisplayMode522);
                if (!this.aGraphicsDevice523.getDisplayMode().equals(this.aDisplayMode522)) {
                    throw new RuntimeException("Did not return to correct resolution!");
                }
                this.aDisplayMode522 = null;
            }
            this.method691((byte)(-92), null);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    private final void method691(final byte b, final Frame fullScreenWindow) {
        try {
            boolean b2 = false;
            try {
                final Field declaredField = Class.forName("sun.awt.Win32GraphicsDevice").getDeclaredField("valid");
                declaredField.setAccessible(true);
                if (declaredField.get(this.aGraphicsDevice523)) {
                    declaredField.set(this.aGraphicsDevice523, Boolean.FALSE);
                    b2 = true;
                }
                if (b != -92) {
                    this.method692(null, -99, -10, 13, 34);
                }
            }
            catch (Throwable t) {}
            try {
                this.aGraphicsDevice523.setFullScreenWindow(fullScreenWindow);
            }
            catch (Throwable t2) {
                if (b2) {
                    try {
                        Class.forName("sun.awt.Win32GraphicsDevice").getDeclaredField("valid").set(this.aGraphicsDevice523, Boolean.TRUE);
                    }
                    catch (Throwable t3) {}
                }
            }
            if (b2) {
                try {
                    Class.forName("sun.awt.Win32GraphicsDevice").getDeclaredField("valid").set(this.aGraphicsDevice523, Boolean.TRUE);
                }
                catch (Throwable t4) {}
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final void method692(final Frame frame, final int n, final int n2, final int n3, int n4) {
        try {
            this.aDisplayMode522 = this.aGraphicsDevice523.getDisplayMode();
            if (null == this.aDisplayMode522) {
                throw new NullPointerException();
            }
            frame.setUndecorated(true);
            frame.enableInputMethods(false);
            this.method691((byte)(-92), frame);
            if (-1 == ~n4) {
                final int refreshRate = this.aDisplayMode522.getRefreshRate();
                final DisplayMode[] displayModes = this.aGraphicsDevice523.getDisplayModes();
                int n5 = 0;
                for (int n6 = 0; ~n6 > ~displayModes.length; ++n6) {
                    if (displayModes[n6].getWidth() == n && n2 == displayModes[n6].getHeight() && displayModes[n6].getBitDepth() == n3) {
                        final int refreshRate2 = displayModes[n6].getRefreshRate();
                        if (n5 == 0 || Math.abs(-refreshRate + refreshRate2) < Math.abs(-refreshRate + n4)) {
                            n4 = refreshRate2;
                            n5 = 1;
                        }
                    }
                }
                if (n5 == 0) {
                    n4 = refreshRate;
                }
            }
            this.aGraphicsDevice523.setDisplayMode(new DisplayMode(n, n2, n3, n4));
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final int[] method693() {
        int[] array2;
        try {
            final DisplayMode[] displayModes = this.aGraphicsDevice523.getDisplayModes();
            final int[] array = new int[displayModes.length << 754645378];
            for (int i = 0; i < displayModes.length; ++i) {
                array[i << 670030594] = displayModes[i].getWidth();
                array[1 + (i << 697150050)] = displayModes[i].getHeight();
                array[2 + (i << -399684990)] = displayModes[i].getBitDepth();
                array[(i << -1626028094) + 3] = displayModes[i].getRefreshRate();
            }
            array2 = array;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return array2;
    }
    
    public Class68() throws Exception {
        try {
            final GraphicsEnvironment localGraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            this.aGraphicsDevice523 = localGraphicsEnvironment.getDefaultScreenDevice();
            if (!this.aGraphicsDevice523.isFullScreenSupported()) {
                final GraphicsDevice[] screenDevices = localGraphicsEnvironment.getScreenDevices();
                for (int n = 0; ~screenDevices.length < ~n; ++n) {
                    final GraphicsDevice aGraphicsDevice523 = screenDevices[n];
                    if (null != aGraphicsDevice523 && aGraphicsDevice523.isFullScreenSupported()) {
                        this.aGraphicsDevice523 = aGraphicsDevice523;
                        return;
                    }
                }
                throw new Exception();
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
