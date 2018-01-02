import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import javax.sound.midi.Soundbank;
import java.security.AccessControlException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.MidiSystem;
import java.net.URLConnection;
import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import javax.sound.midi.MidiChannel;
import java.awt.Component;
import java.awt.Container;
import java.awt.Color;
import javax.sound.midi.Synthesizer;

// 
// Decompiled by Procyon v0.5.30
// 

class PlayerPrims extends Primitives
{
    static Synthesizer midiSynth;
    static boolean midiSynthInitialized;
    static int[] sensorValues;
    static final String[] primlist;
    static final String[] primclasses;
    
    public String[] primlist() {
        return PlayerPrims.primlist;
    }
    
    public Object dispatch(final int n, final Object[] array, final LContext lContext) {
        switch (n) {
            case 0: {
                lContext.canvas.redraw_all();
                return null;
            }
            case 1: {
                lContext.canvas.redraw_invalid();
                return null;
            }
            case 2: {
                return this.prim_drawrect(array[0], array[1], array[2], array[3], lContext);
            }
            case 3: {
                return (lContext.canvas.stage == null) ? new Object[0] : lContext.canvas.stage;
            }
            case 4: {
                return this.prim_setstage(array[0], lContext);
            }
            case 5: {
                return lContext.canvas.sprites;
            }
            case 6: {
                lContext.canvas.sprites = (Object[])array[0];
                return null;
            }
            case 7: {
                return this.prim_readprojfile(array[0], lContext);
            }
            case 8: {
                return this.prim_readprojurl(lContext);
            }
            case 9: {
                return new Boolean(true);
            }
            case 10: {
                return new Boolean(array[0] instanceof String || array[0] instanceof Symbol);
            }
            case 11: {
                return new Boolean(array[0] instanceof Sprite);
            }
            case 12: {
                return new Boolean(array[0] instanceof Color);
            }
            case 13: {
                return new Double(lContext.canvas.mouseX);
            }
            case 14: {
                return new Double(lContext.canvas.mouseY);
            }
            case 15: {
                return new Boolean(lContext.canvas.mouseIsDown);
            }
            case 16: {
                return this.prim_mouseclick(lContext);
            }
            case 17: {
                return this.prim_keystroke(lContext);
            }
            case 18: {
                return this.prim_keydown(array[0], lContext);
            }
            case 19: {
                lContext.canvas.clearkeys();
                return null;
            }
            case 20: {
                return this.prim_midisetinstrument(array[0], array[1], lContext);
            }
            case 21: {
                return this.prim_midinoteon(array[0], array[1], array[2], lContext);
            }
            case 22: {
                return this.prim_midinoteoff(array[0], array[1], lContext);
            }
            case 23: {
                return this.prim_midicontrol(array[0], array[1], array[2], lContext);
            }
            case 24: {
                lContext.canvas.updatePenTrails();
                return null;
            }
            case 25: {
                return new Double(lContext.canvas.soundLevel());
            }
            case 26: {
                return this.prim_jpegDecode(array[0], lContext);
            }
            case 27: {
                return new Double(Runtime.getRuntime().totalMemory());
            }
            case 28: {
                return new Double(Runtime.getRuntime().freeMemory());
            }
            case 29: {
                Runtime.getRuntime().gc();
                return null;
            }
            case 30: {
                lContext.canvas.clearall(lContext);
                return null;
            }
            case 31: {
                lContext.canvas.requestFocus();
                return null;
            }
            case 32: {
                lContext.canvas.setMessage(Logo.aString(array[0], lContext));
                return null;
            }
            case 33: {
                return this.prim_getSensorValue(array[0], lContext);
            }
            case 34: {
                return new Boolean(lContext.autostart);
            }
            case 35: {
                return new Boolean(array[0] instanceof QuotedSymbol);
            }
            case 36: {
                return ((QuotedSymbol)array[0]).sym;
            }
            default: {
                return null;
            }
        }
    }
    
    static synchronized LContext startup(final String codeBase, final String projectURL, final Container container, final boolean autostart) {
        final LContext lc = new LContext();
        Logo.setupPrims(PlayerPrims.primclasses, lc);
        lc.codeBase = codeBase;
        lc.projectURL = projectURL;
        lc.autostart = autostart;
        final PlayerCanvas canvas = new PlayerCanvas();
        canvas.lc = lc;
        Skin.readSkin(lc.canvas = canvas);
        SoundPlayer.startPlayer();
        container.add(canvas, "Center");
        LogoCommandRunner.startLogoThread("load \"startup startup", lc);
        return lc;
    }
    
    static synchronized void shutdown(final LContext lContext) {
        if (lContext != null) {
            LogoCommandRunner.stopLogoThread(lContext);
            lContext.canvas.clearall(lContext);
        }
        SoundPlayer.stopSoundsForApplet(lContext);
        PlayerPrims.sensorValues = new int[16];
        for (int i = 3; i < 8; ++i) {
            PlayerPrims.sensorValues[i] = 1000;
        }
    }
    
    static void stopMIDINotes() {
        if (PlayerPrims.midiSynth == null) {
            return;
        }
        final MidiChannel[] channels = PlayerPrims.midiSynth.getChannels();
        for (int i = 0; i < channels.length; ++i) {
            if (channels[i] != null) {
                channels[i].allNotesOff();
                channels[i].programChange(0);
            }
        }
    }
    
    Object prim_drawrect(final Object o, final Object o2, final Object o3, final Object o4, final LContext lContext) {
        lContext.canvas.drawRect(Logo.anInt(o, lContext), Logo.anInt(o2, lContext), Logo.anInt(o3, lContext), Logo.anInt(o4, lContext));
        return null;
    }
    
    Object prim_setstage(final Object o, final LContext lContext) {
        if (!(o instanceof Sprite)) {
            return null;
        }
        final Sprite sprite;
        final Sprite stage = sprite = (Sprite)o;
        final double n = 0.0;
        stage.y = n;
        sprite.x = n;
        lContext.canvas.stage = stage;
        return null;
    }
    
    Object prim_readprojfile(final Object o, final LContext lContext) {
        final Object[] array = new Object[0];
        lContext.canvas.startLoading();
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(o.toString());
        }
        catch (FileNotFoundException ex2) {
            Logo.error("File not found: " + o, lContext);
            return array;
        }
        lContext.canvas.loadingProgress(0.5);
        Object[][] objects;
        try {
            objects = new ObjReader(fileInputStream).readObjects(lContext);
            fileInputStream.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return array;
        }
        lContext.canvas.stopLoading();
        return objects;
    }
    
    Object prim_readprojurl(final LContext lContext) {
        final Object[] array = new Object[0];
        if (lContext.projectURL == null) {
            return array;
        }
        URL url;
        try {
            url = new URL(lContext.projectURL);
        }
        catch (MalformedURLException ex) {
            Logo.error("Bad project URL: " + lContext.projectURL, lContext);
            return array;
        }
        Object[][] objects;
        try {
            lContext.canvas.startLoading();
            final URLConnection openConnection = url.openConnection();
            openConnection.connect();
            final int contentLength = openConnection.getContentLength();
            final byte[] array2 = new byte[contentLength];
            final InputStream inputStream = openConnection.getInputStream();
            int read = 0;
            int n = 0;
            while (read >= 0 && n < contentLength) {
                read = inputStream.read(array2, n, contentLength - n);
                if (read > 0) {
                    n += read;
                    lContext.canvas.loadingProgress(n / contentLength);
                }
                else {
                    try {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException ex2) {}
                }
            }
            inputStream.close();
            objects = new ObjReader(new ByteArrayInputStream(array2)).readObjects(lContext);
        }
        catch (IOException ex3) {
            Logo.error("Problem reading project from URL: " + lContext.projectURL, lContext);
            return array;
        }
        lContext.canvas.stopLoading();
        return objects;
    }
    
    Object prim_mouseclick(final LContext lContext) {
        if (lContext.canvas.mouseclicks.isEmpty()) {
            return new Object[0];
        }
        return lContext.canvas.mouseclicks.remove(0);
    }
    
    Object prim_keystroke(final LContext lContext) {
        if (lContext.canvas.keystrokes.isEmpty()) {
            return new Object[0];
        }
        return lContext.canvas.keystrokes.remove(0);
    }
    
    Object prim_keydown(final Object o, final LContext lContext) {
        final int anInt = Logo.anInt(o, lContext);
        if (anInt > 255) {
            return new Boolean(false);
        }
        return new Boolean(lContext.canvas.keydown[anInt]);
    }
    
    Object prim_midisetinstrument(final Object o, final Object o2, final LContext lContext) {
        this.init_midi(lContext);
        if (PlayerPrims.midiSynth == null) {
            return null;
        }
        PlayerPrims.midiSynth.getChannels()[Logo.anInt(o, lContext) - 1 & 0xF].programChange(Logo.anInt(o2, lContext) - 1 & 0x7F);
        return null;
    }
    
    Object prim_midinoteon(final Object o, final Object o2, final Object o3, final LContext lContext) {
        this.init_midi(lContext);
        if (PlayerPrims.midiSynth == null) {
            return null;
        }
        PlayerPrims.midiSynth.getChannels()[Logo.anInt(o, lContext) - 1 & 0xF].noteOn(Logo.anInt(o2, lContext) & 0x7F, Logo.anInt(o3, lContext) & 0x7F);
        return null;
    }
    
    Object prim_midinoteoff(final Object o, final Object o2, final LContext lContext) {
        this.init_midi(lContext);
        if (PlayerPrims.midiSynth == null) {
            return null;
        }
        PlayerPrims.midiSynth.getChannels()[Logo.anInt(o, lContext) - 1 & 0xF].noteOff(Logo.anInt(o2, lContext) & 0x7F);
        return null;
    }
    
    Object prim_midicontrol(final Object o, final Object o2, final Object o3, final LContext lContext) {
        this.init_midi(lContext);
        if (PlayerPrims.midiSynth == null) {
            return null;
        }
        PlayerPrims.midiSynth.getChannels()[Logo.anInt(o, lContext) - 1 & 0xF].controlChange(Logo.anInt(o2, lContext) & 0x7F, Logo.anInt(o3, lContext) & 0x7F);
        return null;
    }
    
    void init_midi(final LContext lContext) {
        if (PlayerPrims.midiSynthInitialized) {
            return;
        }
        PlayerPrims.midiSynthInitialized = true;
        try {
            (PlayerPrims.midiSynth = MidiSystem.getSynthesizer()).open();
            if (PlayerPrims.midiSynth.getDefaultSoundbank() == null) {
                lContext.canvas.setMessage("Reading sound bank from server. Please wait...");
                final Soundbank soundbank = MidiSystem.getSoundbank(new URL(lContext.codeBase + "soundbank.gm"));
                if (soundbank != null) {
                    PlayerPrims.midiSynth.loadAllInstruments(soundbank);
                    lContext.canvas.setMessage("");
                }
                else {
                    PlayerPrims.midiSynth.close();
                    PlayerPrims.midiSynth = null;
                }
            }
        }
        catch (MidiUnavailableException ex) {
            ex.printStackTrace();
            PlayerPrims.midiSynth = null;
        }
        catch (MalformedURLException ex2) {
            ex2.printStackTrace();
            PlayerPrims.midiSynth = null;
        }
        catch (InvalidMidiDataException ex3) {
            ex3.printStackTrace();
            PlayerPrims.midiSynth = null;
        }
        catch (IOException ex4) {
            ex4.printStackTrace();
            PlayerPrims.midiSynth = null;
        }
        catch (AccessControlException ex5) {
            ex5.printStackTrace();
            PlayerPrims.midiSynth = null;
        }
        if (PlayerPrims.midiSynth != null) {
            final MidiChannel[] channels = PlayerPrims.midiSynth.getChannels();
            for (int i = 0; i < channels.length; ++i) {
                if (channels[i] != null) {
                    channels[i].programChange(0);
                }
            }
        }
        else {
            lContext.canvas.setMessage("No soundbank; note & drum commands disabled.");
        }
    }
    
    Object prim_jpegDecode(final Object o, final LContext lContext) {
        if (!(o instanceof byte[])) {
            return null;
        }
        final Image image = Toolkit.getDefaultToolkit().createImage((byte[])o);
        final MediaTracker mediaTracker = new MediaTracker(lContext.canvas);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        if (image == null) {
            return null;
        }
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        final BufferedImage bufferedImage = new BufferedImage(width, height, 2);
        final Graphics graphics = bufferedImage.getGraphics();
        graphics.drawImage(image, 0, 0, width, height, null);
        graphics.dispose();
        return bufferedImage;
    }
    
    Object prim_getSensorValue(final Object o, final LContext lContext) {
        final int n = Logo.anInt(o, lContext) - 1;
        if (n < 0 || n > 15) {
            return new Double(123.0);
        }
        return new Double(PlayerPrims.sensorValues[n] / 10.0);
    }
    
    static {
        PlayerPrims.midiSynthInitialized = false;
        PlayerPrims.sensorValues = new int[] { 0, 0, 0, 1000, 1000, 1000, 1000, 1000, 0, 0, 0, 0, 0, 0, 0, 0 };
        primlist = new String[] { "redrawall", "0", "redraw", "0", "drawrect", "4", "stage", "0", "setstage", "1", "sprites", "0", "setsprites", "1", "readprojfile", "1", "readprojurl", "0", "applet?", "0", "string?", "1", "sprite?", "1", "color?", "1", "mouseX", "0", "mouseY", "0", "mouseIsDown", "0", "mouseClick", "0", "keystroke", "0", "keydown?", "1", "clearkeys", "0", "midisetinstrument", "2", "midinoteon", "3", "midinoteoff", "2", "midicontrol", "3", "updatePenTrails", "0", "soundLevel", "0", "jpegDecode", "1", "memTotal", "0", "memFree", "0", "gc", "0", "clearall", "0", "requestFocus", "0", "setMessage", "1", "getSensorValue", "1", "autostart?", "0", "quoted?", "1", "unquote", "1" };
        primclasses = new String[] { "SystemPrims", "MathPrims", "ControlPrims", "DefiningPrims", "WordListPrims", "FilePrims", "PlayerPrims", "SpritePrims" };
    }
}
