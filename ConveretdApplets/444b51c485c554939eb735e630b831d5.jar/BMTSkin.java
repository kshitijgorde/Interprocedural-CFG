import java.awt.Component;
import java.awt.MediaTracker;
import java.io.EOFException;
import java.io.IOException;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.InputStream;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class BMTSkin
{
    public static final byte[] FIRST_BYTES;
    public static final int VERSION = 1;
    public static final int DATA_INT = 4;
    public static final int DATA_IMAGE = 8;
    public static final int DATA_COLOR = 9;
    public static final int DATA_FONT = 10;
    Image background;
    Image alink;
    Image field;
    Image options;
    Image yes;
    Image no;
    Image start;
    Image blow;
    Image appear;
    Image[] controls;
    Image[] snake;
    private final int NUM_IMAGES = 25;
    private final BMTron tron;
    
    public BMTSkin(final BMTron tron) {
        this.background = null;
        this.alink = null;
        this.field = null;
        this.options = null;
        this.yes = null;
        this.no = null;
        this.start = null;
        this.blow = null;
        this.appear = null;
        this.controls = new Image[6];
        this.snake = new Image[10];
        this.tron = tron;
    }
    
    public void load(final InputStream inputStream) throws IOException, InterruptedException {
        final DataInputStream dataInputStream = new DataInputStream(inputStream);
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        for (int i = 0; i <= BMTSkin.FIRST_BYTES.length - 1; ++i) {
            if (BMTSkin.FIRST_BYTES[i] != dataInputStream.read()) {
                throw new IOException("Bad skin data");
            }
        }
        if (dataInputStream.readInt() > 1) {
            throw new IOException("You need newer BMTron version to use this skin");
        }
        int n = 0;
        while (true) {
            int int1;
            try {
                int1 = dataInputStream.readInt();
            }
            catch (EOFException ex) {
                return;
            }
            final String utf = dataInputStream.readUTF();
            int j = dataInputStream.readInt();
            if (int1 == 8) {
                final byte[] array = new byte[j];
                dataInputStream.readFully(array);
                final Image image = defaultToolkit.createImage(array);
                final MediaTracker mediaTracker = new MediaTracker(this.tron);
                mediaTracker.addImage(image, 0);
                mediaTracker.waitForID(0);
                this.setImage(utf, image);
                ++n;
                this.tron.setStatus(n * 100 / 25 + "% loaded");
            }
            else {
                while (j > 0) {
                    dataInputStream.read();
                    --j;
                }
            }
        }
    }
    
    private void setImage(final String s, final Image appear) {
        if (s.equals("background")) {
            this.background = appear;
            this.tron.repaint();
        }
        else if (s.equals("alink")) {
            this.alink = appear;
        }
        else if (s.equals("field")) {
            this.field = appear;
        }
        else if (s.equals("options")) {
            this.options = appear;
        }
        else if (s.equals("yes")) {
            this.yes = appear;
        }
        else if (s.equals("no")) {
            this.no = appear;
        }
        else if (s.equals("start")) {
            this.start = appear;
        }
        else if (s.equals("blow")) {
            this.blow = appear;
        }
        else if (s.equals("appear")) {
            this.appear = appear;
        }
        else if (s.startsWith("control")) {
            this.controls[Integer.parseInt(s.substring(7))] = appear;
        }
        else if (s.startsWith("snake")) {
            this.snake[Integer.parseInt(s.substring(5))] = appear;
        }
    }
    
    static {
        FIRST_BYTES = "(BMTron skin)".getBytes();
    }
}
