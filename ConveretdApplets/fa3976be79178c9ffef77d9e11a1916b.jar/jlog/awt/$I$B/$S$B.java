// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$I$B;

import java.awt.Cursor;
import java.awt.AWTEvent;
import java.io.IOException;
import java.util.Vector;
import java.io.Reader;
import java.io.LineNumberReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.awt.Dimension;

public class $S$B extends $T$B
{
    Dimension $W$B() {
        return this.getSize();
    }
    
    void $X$B(final InputStream inputStream) throws IOException {
        final LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(inputStream));
        this.flush();
        super.$Z$B = new Vector();
        String s;
        while ((s = lineNumberReader.readLine()) != null) {
            try {
                s = s.trim();
                if (s.startsWith("#") || s.length() == 0) {
                    continue;
                }
                super.$Z$B.addElement(new $A_B(s, this));
            }
            catch (Exception ex) {
                ex.printStackTrace(System.err);
                System.err.println("BannerLine failed: " + s + ": " + ex.getMessage());
            }
        }
    }
    
    public $S$B(final $J$B $j$B) {
        super($j$B);
        this.enableEvents(16L);
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.$V$B("BANNER_WIDTH", 234), this.$V$B("BANNER_HEIGHT", 60));
    }
    
    public void processEvent(final AWTEvent awtEvent) {
        super.processEvent(awtEvent);
        if (awtEvent.getID() == 502) {
            this.mousePressed();
        }
    }
    
    void setCursor(final boolean b) {
        if (b) {
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
        else {
            this.setCursor(Cursor.getPredefinedCursor(0));
        }
    }
}
