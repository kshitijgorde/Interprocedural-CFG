import java.awt.Graphics;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.awt.Font;
import java.awt.Color;
import java.io.InputStream;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class CgmContext extends Cgm
{
    CgmPicture currpic;
    Vector pictures;
    boolean FinishedLoading;
    String filename;
    int SearchResult;
    Vector FontList;
    boolean FinishedReading;
    InputStream is;
    CgmReader reader;
    
    CgmContext(final CgmViewApplet applet, final String filename) {
        this.currpic = null;
        this.pictures = new Vector(5, 2);
        this.FinishedLoading = false;
        this.FontList = new Vector(5, 2);
        this.FinishedReading = false;
        this.is = null;
        this.reader = null;
        super.applet = applet;
        super.BackColor = null;
        this.filename = filename;
        if (applet.inverseColor) {
            super.FillColor = Color.black;
            super.EdgeColor = Color.cyan;
            super.LineColor = Color.magenta;
            super.MarkerColor = Color.white;
            super.TextColor = Color.yellow;
        }
        else {
            super.FillColor = Color.white;
            super.EdgeColor = Color.red;
            super.LineColor = Color.green;
            super.MarkerColor = Color.black;
            super.TextColor = Color.blue;
        }
    }
    
    final void addFont(final String s) {
        int n = 0;
        final int index = s.indexOf(32);
        if (index >= 0) {
            if (s.indexOf("Bold") >= 0) {
                n = 1;
            }
            if (s.indexOf("Italic") >= 0) {
                n |= 0x2;
            }
            if (s.indexOf("Underline") >= 0) {
                n |= 0x80;
            }
            this.FontList.addElement(new Font(s.substring(0, index), n, 12));
        }
        else {
            this.FontList.addElement(new Font(s, n, 12));
        }
    }
    
    final void beginMF(final String name) {
        this.FinishedLoading = false;
        super.name = name;
    }
    
    final void beginPic(final String s) {
        this.currpic = new CgmPicture(this, s);
        this.pictures.addElement(this.currpic);
    }
    
    final void endMF() {
        this.FinishedLoading = true;
    }
    
    final CgmContext find(final double n, final double n2) {
        this.SearchResult = -1;
        for (int i = this.pictures.size() - 1; i >= 0; --i) {
            final CgmPicture cgmPicture = this.pictures.elementAt(i);
            if (this.SearchResult < 0) {
                this.SearchResult = cgmPicture.find(n, n2);
            }
            else {
                this.SearchResult += cgmPicture.PrimList.size();
            }
        }
        return (this.SearchResult >= 0) ? this : null;
    }
    
    final CgmContext find(final String s, final int searchResult) {
        this.SearchResult = searchResult;
        for (int i = this.pictures.size() - 1; i >= 0; --i) {
            final CgmPicture cgmPicture = this.pictures.elementAt(i);
            if (this.SearchResult < 0) {
                this.SearchResult = cgmPicture.find(s, this.SearchResult);
            }
            else {
                this.SearchResult += cgmPicture.PrimList.size();
            }
        }
        return (this.SearchResult >= 0) ? this : null;
    }
    
    protected final CgmPrimitive findComponent(int n) {
        for (int i = 0; i < this.pictures.size(); ++i) {
            final CgmPicture cgmPicture = this.pictures.elementAt(i);
            final int size = cgmPicture.PrimList.size();
            if (n < size) {
                return (CgmPrimitive)cgmPicture.PrimList.elementAt(n);
            }
            n -= size;
        }
        return null;
    }
    
    final void finishReading() {
        if (!this.FinishedReading) {
            this.reader.waitFor();
            if (this.is != null) {
                try {
                    this.is.close();
                }
                catch (IOException ex) {}
                this.is = null;
            }
            this.reader = null;
            this.FinishedReading = true;
        }
    }
    
    final void getReader(final DataInputStream dataInputStream) throws IOException {
        int byte1 = 0;
        if (dataInputStream.markSupported()) {
            dataInputStream.mark(1);
            byte1 = dataInputStream.readByte();
            dataInputStream.reset();
        }
        if (byte1 == 66) {
            this.reader = new CgmAsciiReader(dataInputStream, this);
        }
        else {
            this.reader = new CgmBinReader(dataInputStream, this);
        }
    }
    
    final void render(final Graphics graphics, final double n, final double n2, final boolean b) {
        for (int i = 0; i < this.pictures.size(); ++i) {
            ((CgmPicture)this.pictures.elementAt(i)).render(graphics, n, n2, b);
        }
    }
    
    final int replaceText(int replaceText, final String s) {
        for (int i = 0; i < this.pictures.size(); ++i) {
            replaceText = ((CgmPicture)this.pictures.elementAt(i)).replaceText(replaceText, s);
            if (replaceText < 0) {
                break;
            }
        }
        return replaceText;
    }
}
