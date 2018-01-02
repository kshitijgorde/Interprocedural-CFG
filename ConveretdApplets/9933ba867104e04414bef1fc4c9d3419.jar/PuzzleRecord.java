import java.security.AccessControlException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.DataInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class PuzzleRecord
{
    private boolean loaded;
    private boolean readError;
    public String name;
    public int width;
    public int height;
    public int numWords;
    public boolean useClues;
    public String[] words;
    public String[] clues;
    public byte[] puzzle;
    public int[] begSquare;
    public int[] endSquare;
    
    public PuzzleRecord() {
        this.loaded = false;
        this.readError = false;
    }
    
    private int byteToInt(final byte[] array, final int n) {
        final byte b = array[n];
        int n2;
        if (b < 0) {
            n2 = (0xFFFFFF00 ^ b);
        }
        else {
            n2 = b;
        }
        final byte b2 = array[n + 1];
        int n3;
        if (b2 < 0) {
            n3 = n2 + (0xFFFF00FF ^ b2 << 8);
        }
        else {
            n3 = n2 + (b2 << 8);
        }
        final byte b3 = array[n + 2];
        int n4;
        if (b3 < 0) {
            n4 = n3 + (0xFF00FFFF ^ b3 << 16);
        }
        else {
            n4 = n3 + (b3 << 16);
        }
        final byte b4 = array[n + 3];
        int n5;
        if (b4 < 0) {
            n5 = n4 + (0xFFFFFF ^ b4 << 24);
        }
        else {
            n5 = n4 + (b4 << 24);
        }
        return n5;
    }
    
    public void loadFromStream(final DataInputStream dataInputStream) {
        this.loaded = false;
        try {
            final byte[] array = new byte[4];
            dataInputStream.read(array);
            final byte[] array2 = new byte[this.byteToInt(array, 0)];
            dataInputStream.read(array2);
            this.name = new String(array2);
            final byte[] array3 = new byte[13];
            dataInputStream.read(array3);
            this.width = this.byteToInt(array3, 0);
            this.height = this.byteToInt(array3, 4);
            this.numWords = this.byteToInt(array3, 8);
            this.useClues = (array3[12] != 0);
            this.words = new String[this.numWords];
            this.begSquare = new int[this.numWords];
            this.endSquare = new int[this.numWords];
            if (this.useClues) {
                this.clues = new String[this.numWords];
            }
            for (int i = 0; i < this.numWords; ++i) {
                final byte[] array4 = new byte[4];
                dataInputStream.read(array4);
                final byte[] array5 = new byte[this.byteToInt(array4, 0)];
                dataInputStream.read(array5);
                this.words[i] = new String(array5);
                final byte[] array6 = new byte[8];
                dataInputStream.read(array6);
                this.begSquare[i] = this.byteToInt(array6, 0);
                this.endSquare[i] = this.byteToInt(array6, 4);
                if (this.useClues) {
                    final byte[] array7 = new byte[4];
                    dataInputStream.read(array7);
                    final byte[] array8 = new byte[this.byteToInt(array7, 0)];
                    dataInputStream.read(array8);
                    this.clues[i] = new String(array8);
                }
            }
            dataInputStream.read(this.puzzle = new byte[2500]);
            this.loaded = true;
        }
        catch (IOException ex) {
            ex.printStackTrace(System.err);
            System.exit(1);
        }
    }
    
    public void loadFromFile(final File file) {
        this.readError = false;
        try {
            if (file.exists() && !file.isDirectory() && file.canRead()) {
                try {
                    final FileInputStream fileInputStream = new FileInputStream(file);
                    this.loadFromStream(new DataInputStream(fileInputStream));
                    try {
                        fileInputStream.close();
                    }
                    catch (IOException ex) {}
                }
                catch (FileNotFoundException ex2) {}
            }
        }
        catch (AccessControlException ex3) {
            this.readError = true;
        }
    }
    
    public boolean isLoaded() {
        return this.loaded;
    }
    
    public boolean hasReadError() {
        return this.readError;
    }
}
