import java.awt.Component;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.util.Random;
import java.util.Comparator;
import javax.swing.JComponent;

// 
// Decompiled by Procyon v0.5.30
// 

public class FileComponent extends JComponent implements Comparator
{
    private static final int INT_SECTORS = 63;
    private static Random _random;
    private static int _intCurrentTrack;
    ImageIcon _image;
    private int _intTrack;
    private int _intPriority;
    private boolean[] _boolBits;
    
    public FileComponent() {
        this._image = null;
        this._intTrack = 0;
        this._boolBits = new boolean[63];
        this._intPriority = 18;
    }
    
    public FileComponent(final ImageIcon icon, final int track) {
        this._intTrack = track;
        this._intPriority = 18;
        this._image = new ImageIcon(icon.getImage());
        this._boolBits = new boolean[63];
        this.setSize(this._image.getIconWidth() + 2, this._image.getIconHeight() + 2);
        final int intStartSector = FileComponent._random.nextInt(63);
        int intSectorCount;
        for (intSectorCount = 0; intSectorCount < 1; intSectorCount = FileComponent._random.nextInt(21)) {}
        for (int i = 0; i < intSectorCount; ++i) {
            this._boolBits[(intStartSector + i) % 63] = true;
        }
    }
    
    public FileComponent(final FileComponent file) {
        this._image = new ImageIcon(file.getImage().getImage());
        this._boolBits = new boolean[63];
        this.setSize(file.getWidth(), file.getHeight());
        this._intTrack = file.getTrack();
        this._intPriority = file.getPriority();
        final boolean[] boolBits = file.getBits();
        for (int i = 0; i < 63; ++i) {
            if (boolBits[i]) {
                this._boolBits[i] = true;
            }
        }
    }
    
    public boolean[] getBits() {
        return this._boolBits;
    }
    
    public void resetBit(final int index) {
        this._boolBits[index] = false;
    }
    
    public boolean isDone() {
        for (int i = 0; i < 63; ++i) {
            if (this._boolBits[i]) {
                return false;
            }
        }
        return true;
    }
    
    public static void setCurrentTrack(final int track) {
        FileComponent._intCurrentTrack = track;
    }
    
    public ImageIcon getImage() {
        return this._image;
    }
    
    public int getTrack() {
        return this._intTrack;
    }
    
    protected void paintComponent(final Graphics g) {
        if (this._image == null) {
            return;
        }
        this._image.paintIcon(this, g, 0, 0);
    }
    
    public int getPriority() {
        int track = this._intTrack;
        int priority = this._intPriority;
        if (FileComponent._intCurrentTrack > this._intTrack) {
            track = 18 - this._intTrack;
            if (FileComponent._intCurrentTrack >= track) {
                priority = 18 - FileComponent._intCurrentTrack + this._intTrack;
            }
            else {
                priority = track - FileComponent._intCurrentTrack;
            }
        }
        if (FileComponent._intCurrentTrack < this._intTrack) {
            priority = this._intTrack - FileComponent._intCurrentTrack;
        }
        return priority;
    }
    
    public int getPriority(final int currentTrack) {
        int track = this._intTrack;
        int priority = this._intPriority;
        if (currentTrack >= this._intTrack) {
            track = 18 - this._intTrack;
            if (currentTrack >= track) {
                priority = 18 - currentTrack + this._intTrack;
            }
            else if (currentTrack < track) {
                priority = track - currentTrack;
            }
        }
        if (currentTrack < this._intTrack) {
            priority = this._intTrack - currentTrack;
        }
        return this._intPriority = priority;
    }
    
    public String toString() {
        return "track = " + this._intTrack;
    }
    
    public int compare(final Object o1, final Object o2) {
        final FileComponent file1 = (FileComponent)o1;
        final FileComponent file2 = (FileComponent)o2;
        System.out.println("file1 = " + file1.getPriority());
        System.out.println("file2 = " + file2.getPriority());
        System.out.println("current = " + FileComponent._intCurrentTrack);
        if (file1.getPriority() > file2.getPriority()) {
            return 1;
        }
        if (file1.getPriority() < file2.getPriority()) {
            return -1;
        }
        return 0;
    }
    
    static {
        FileComponent._random = new Random();
        FileComponent._intCurrentTrack = 0;
    }
}
