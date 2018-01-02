import java.io.IOException;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class Letters
{
    int HEIGHT;
    int TOTAL;
    String let;
    String path;
    URL url;
    InputStream file;
    DataInputStream dis;
    int w;
    int h;
    int num;
    int place;
    int len;
    int space;
    int swidth;
    Index[] index;
    
    public Letters(final URL url, final String path, final int swidth) {
        try {
            this.file = new URL(url, path).openStream();
            this.dis = new DataInputStream(this.file);
            this.path = path;
            this.swidth = swidth;
            if (this.initLetters() == -1) {
                this.w = -1;
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public int height() {
        return this.HEIGHT;
    }
    
    int initLetters() {
        this.w = 5;
        this.h = 5;
        this.num = 100;
        try {
            for (boolean b = false; !b; b = true) {
                final String line = this.dis.readLine();
                if (!line.startsWith("!!")) {
                    this.h = new Integer(line);
                    this.HEIGHT = this.h;
                }
            }
            for (boolean b2 = false; !b2; b2 = true) {
                final String line2 = this.dis.readLine();
                if (!line2.startsWith("!!")) {
                    this.w = new Integer(line2);
                }
            }
            for (boolean b3 = false; !b3; b3 = true) {
                final String line3 = this.dis.readLine();
                if (!line3.startsWith("!!")) {
                    this.num = new Integer(line3);
                }
            }
            this.index = new Index[this.num + 1];
            for (int i = 0; i < this.num; ++i) {
                byte b4 = 2;
                int intValue = 10;
                for (boolean b5 = false; !b5; b5 = true) {
                    final String line4 = this.dis.readLine();
                    if (!line4.startsWith("!!")) {
                        b4 = (byte)line4.charAt(0);
                    }
                }
                for (boolean b6 = false; !b6; b6 = true) {
                    final String line5 = this.dis.readLine();
                    if (!line5.startsWith("!!")) {
                        intValue = new Integer(line5);
                    }
                }
                this.index[i] = new Index(b4, intValue, this.h);
                for (int j = 0; j < this.h; ++j) {
                    boolean b7 = false;
                    String line6 = "";
                    while (!b7) {
                        line6 = this.dis.readLine();
                        if (line6.length() > 0) {
                            if (line6.startsWith("!!")) {
                                continue;
                            }
                            b7 = true;
                        }
                        else {
                            line6 = " ";
                            b7 = true;
                        }
                    }
                    for (int k = 0; k < this.index[i].width; ++k) {
                        if (k >= line6.length()) {
                            this.index[i].letter[k][j] = false;
                        }
                        else if (line6.charAt(k) == '#') {
                            this.index[i].letter[k][j] = true;
                        }
                        else {
                            this.index[i].letter[k][j] = false;
                        }
                    }
                }
            }
            this.index[this.num] = new Index((byte)32, this.swidth, this.h);
            this.file.close();
            this.dis.close();
        }
        catch (IOException ex) {
            return -1;
        }
        return 1;
    }
    
    public Index getLetter(final char c) {
        int num;
        if (c == ' ') {
            num = this.num;
        }
        else {
            for (num = 0; c != this.index[num].ch && num < this.num; ++num) {}
        }
        return this.index[num];
    }
}
