import java.io.IOException;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.io.BufferedInputStream;
import java.awt.Image;
import java.util.Vector;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class frameLoader extends Applet implements Runnable
{
    String file2Load;
    URL CodeBase;
    String loadError;
    int BorringBits;
    int VMIfileSize;
    Vector anim;
    boolean loading;
    int pageSize;
    int RowLen;
    int ColTotal;
    int frameCur;
    int frameCurLoaded;
    int noFrames;
    Thread sequence;
    Image img;
    
    frameLoader() {
        this.file2Load = "";
        this.CodeBase = null;
        this.loadError = "";
        this.BorringBits = 14;
        this.VMIfileSize = 108;
        this.anim = new Vector();
        this.loading = true;
        this.pageSize = 1536;
        this.RowLen = 48;
        this.ColTotal = 32;
        this.frameCur = 0;
        this.frameCurLoaded = 1;
        this.noFrames = 1;
        this.sequence = null;
    }
    
    public void doLoad() {
        System.out.println("starting load testing");
        for (int i = 0; i < 10000; ++i) {
            System.out.print("[" + i + "]");
            Thread.yield();
        }
        System.out.println("end of load testing");
    }
    
    public void getLCDFile(final String s) {
        this.loading = true;
        System.out.println("************************************************");
        System.out.println("* File      [" + s + "]");
        System.out.println("* URL       [" + this.CodeBase + "]");
        String s2 = "";
        try {
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL(String.valueOf(String.valueOf(this.CodeBase)) + s).openStream());
            final int n = (int)bufferedInputStream.skip(this.BorringBits);
            this.noFrames = bufferedInputStream.read();
            System.out.println("* Frames    [" + this.noFrames + "]");
            final int n2 = (int)bufferedInputStream.skip(1L);
            for (int i = 0; i < this.noFrames; ++i) {
                final int n3 = (int)bufferedInputStream.skip(1L);
                final int read = bufferedInputStream.read();
                final int n4 = (int)bufferedInputStream.skip(2L);
                this.anim.addElement(new aFrame(read, this.pageSize));
                final aFrame aFrame = this.anim.elementAt(this.anim.size() - 1);
                Thread.yield();
            }
            final int n5 = this.RowLen * 3;
            final int n6 = this.ColTotal * 4;
            for (int j = 0; j < this.noFrames; ++j) {
                this.frameCur = j;
                final aFrame aFrame2 = this.anim.elementAt(j);
                for (int k = 0; k < this.pageSize; ++k) {
                    this.frameCurLoaded = k;
                    if (bufferedInputStream.read() == 0) {
                        aFrame2.putPixel(k, 0);
                    }
                    else {
                        aFrame2.putPixel(k, -16777216);
                    }
                    Thread.yield();
                }
                aFrame2.convert();
                aFrame2.img = this.createImage(new MemoryImageSource(n5, n6, aFrame2.imageArrayBig, 0, n5));
                aFrame2.imgSmall = this.createImage(new MemoryImageSource(this.RowLen, this.ColTotal, aFrame2.imageArray, 0, this.RowLen));
            }
            char c = (char)bufferedInputStream.read();
            while (c != '\uffff') {
                s2 = String.valueOf(s2) + c;
                c = (char)bufferedInputStream.read();
                Thread.yield();
            }
        }
        catch (IOException ex) {
            this.loadError = "JVMU file/URL lost control: " + ex.getMessage();
            s2 = this.loadError;
        }
        System.out.println("* Anim Info [" + s2 + "]");
        System.out.println("* Loaded by [ JVMU  - rednuht@rocketmail.com ]");
        System.out.println();
        this.loading = false;
        Thread.yield();
    }
    
    public void getVMIFile(final String s) {
        this.loading = true;
        System.out.println("************************************************");
        System.out.println("* File      [" + s + "]");
        System.out.println("* URL       [" + this.CodeBase + "]");
        int read = 0;
        String s2 = "Unknown";
        final byte[] array = new byte[this.VMIfileSize];
        try {
            read = new BufferedInputStream(new URL(String.valueOf(String.valueOf(this.CodeBase)) + s).openStream()).read(array, 0, this.VMIfileSize);
        }
        catch (IOException ex) {
            this.loadError = "JVMU file/URL lost control: " + ex.getMessage();
            System.err.println(this.loadError);
        }
        if (read != this.VMIfileSize) {
            this.loadError = "Incorrect file size for VMI file format ";
            System.err.println(this.loadError);
        }
        else {
            final String s3 = new String(array, 4, 30);
            if (!s3.startsWith("Dreamcast Animation")) {
                this.loadError = "Incorrect file type in VMI file [" + s3 + "]";
                System.err.println(this.loadError);
            }
            else {
                s2 = new String(array, 36, 30);
                this.file2Load = new String(array, 80, 8);
                this.file2Load = String.valueOf(this.file2Load) + ".VMS";
            }
            System.out.println("* Server    [" + s2 + "]");
            System.out.println("* VMS file  [" + this.file2Load + "]");
            System.out.println("* Loaded by [ JVMU  - rednuht@rocketmail.com ]");
            System.out.println();
        }
        Thread.yield();
    }
    
    public void getVMSFile(final String s) {
        this.loading = true;
        System.out.println("************************************************");
        System.out.println("* File      [" + s + "]");
        System.out.println("* URL       [" + this.CodeBase + "]");
        try {
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL(String.valueOf(String.valueOf(this.CodeBase)) + s).openStream());
            final int n = (int)bufferedInputStream.skip(3585L);
            this.noFrames = bufferedInputStream.read();
            System.out.println("* Frames    [" + this.noFrames + "]");
            int n2 = 0;
            for (int i = 0; i < this.noFrames; ++i) {
                final int read = bufferedInputStream.read();
                final int n3 = (int)bufferedInputStream.skip(2L);
                this.anim.addElement(new aFrame(read, this.pageSize));
                this.anim.elementAt(this.anim.size() - 1).duration = read;
                Thread.yield();
            }
            final int n4 = this.RowLen * 3;
            final int n5 = this.ColTotal * 4;
            for (int j = 0; j < this.noFrames; ++j) {
                this.frameCur = j;
                final aFrame aFrame = this.anim.elementAt(j);
                int n6 = -1;
                for (int k = 0; k < this.pageSize / 8; ++k) {
                    this.frameCurLoaded = k;
                    final int read2 = bufferedInputStream.read();
                    int l = 256;
                    String s2 = "";
                    while (l > 1) {
                        ++n6;
                        l >>= 1;
                        if ((l & read2) == l) {
                            s2 = String.valueOf(s2) + "1";
                            aFrame.putPixel(n6, -16777216);
                        }
                        else {
                            s2 = String.valueOf(s2) + "0";
                            aFrame.putPixel(n6, 0);
                        }
                    }
                    if (n2 > 4) {
                        n2 = 0;
                    }
                    else {
                        ++n2;
                    }
                    Thread.yield();
                }
                aFrame.convert();
                aFrame.img = this.createImage(new MemoryImageSource(n4, n5, aFrame.imageArrayBig, 0, n4));
                aFrame.imgSmall = this.createImage(new MemoryImageSource(this.RowLen, this.ColTotal, aFrame.imageArray, 0, this.RowLen));
            }
        }
        catch (IOException ex) {
            this.loadError = "JVMU file/URL lost control: " + ex.getMessage();
            final String loadError = this.loadError;
        }
        System.out.println("* Loaded by [ JVMU  - rednuht@rocketmail.com ]");
        System.out.println();
        this.loading = false;
        Thread.yield();
    }
    
    public void reset() {
        this.loading = true;
        this.pageSize = 1536;
        this.RowLen = 48;
        this.ColTotal = 32;
        this.frameCur = 0;
        this.frameCurLoaded = 1;
        this.noFrames = 1;
        this.anim.removeAllElements();
    }
    
    public void run() {
        if (this.file2Load.endsWith(".LCD")) {
            this.getLCDFile(this.file2Load);
        }
        else if (this.file2Load.endsWith(".VMI")) {
            this.getVMIFile(this.file2Load);
            if (this.loadError.equals("")) {
                this.getVMSFile(this.file2Load);
            }
            else {
                this.loading = false;
            }
        }
        else {
            this.loadError = "JVMU file format not supported";
            System.err.println(this.loadError);
            this.loading = false;
        }
        Thread.yield();
        this.stop();
    }
    
    public void setFile(final String s, final URL codeBase) {
        this.file2Load = s.toUpperCase();
        this.CodeBase = codeBase;
    }
    
    public void start() {
        if (this.sequence == null) {
            (this.sequence = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.sequence != null) {
            this.sequence.interrupt();
            this.sequence = null;
        }
    }
}
