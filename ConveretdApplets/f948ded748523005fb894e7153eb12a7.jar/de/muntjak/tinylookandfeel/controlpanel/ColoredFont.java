// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.controlpanel;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import de.muntjak.tinylookandfeel.Theme;
import java.awt.Font;
import java.awt.Color;
import javax.swing.plaf.FontUIResource;

public class ColoredFont
{
    private ColorReference[] ref;
    private FontUIResource font;
    private boolean isPlainFont;
    private boolean isBoldFont;
    
    public ColoredFont(final String s, final int n, final int n2) {
        this.font = new FontUIResource(s, n, n2);
    }
    
    public ColoredFont() {
        this.font = new FontUIResource("sansserif", 0, 12);
        this.isPlainFont = true;
    }
    
    public ColoredFont(final ColorReference[] array) {
        this("sansserif", 0, 12, array);
        this.isPlainFont = true;
    }
    
    public ColoredFont(final String s, final int n, final int n2, final ColorReference[] ref) {
        this.font = new FontUIResource(s, n, n2);
        this.ref = ref;
        if (ref[0] == null) {
            ref[0] = new ColorReference(new Color(0, 0, 0));
        }
        if (ref[1] == null) {
            ref[1] = new ColorReference(new Color(0, 0, 0));
        }
        if (ref[2] == null) {
            ref[2] = new ColorReference(new Color(0, 0, 0));
        }
        if (ref[3] == null) {
            ref[3] = new ColorReference(new Color(0, 0, 0));
        }
    }
    
    public void setPlainFont(final boolean isPlainFont) {
        this.isPlainFont = isPlainFont;
        if (isPlainFont) {
            this.isBoldFont = false;
        }
    }
    
    public void setBoldFont(final boolean isBoldFont) {
        this.isBoldFont = isBoldFont;
        if (isBoldFont) {
            this.isPlainFont = false;
        }
    }
    
    public boolean isPlainFont() {
        return this.isPlainFont;
    }
    
    public boolean isBoldFont() {
        return this.isBoldFont;
    }
    
    public void setFont(final String s, final int n, final int n2) {
        this.font = new FontUIResource(s, n, n2);
    }
    
    public void setFont(final Font font) {
        this.font = new FontUIResource(font);
    }
    
    public void setFont(final FontUIResource font) {
        this.font = font;
    }
    
    public FontUIResource getFont() {
        if (this.isPlainFont) {
            return Theme.plainFont[Theme.style].font;
        }
        if (this.isBoldFont) {
            return Theme.boldFont[Theme.style].font;
        }
        return this.font;
    }
    
    public ColorReference[] getColorReference() {
        return this.ref;
    }
    
    public void setColorReference(final ColorReference[] ref) {
        this.ref = ref;
    }
    
    public void save(final DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(this.font.getFamily());
        dataOutputStream.writeBoolean(this.font.isBold());
        dataOutputStream.writeInt(this.font.getSize());
        dataOutputStream.writeBoolean(this.isPlainFont);
        dataOutputStream.writeBoolean(this.isBoldFont);
    }
    
    public void load(final DataInputStream dataInputStream) throws IOException {
        this.font = new FontUIResource(dataInputStream.readUTF(), dataInputStream.readBoolean() ? 1 : 0, dataInputStream.readInt());
        this.isPlainFont = dataInputStream.readBoolean();
        this.isBoldFont = dataInputStream.readBoolean();
    }
}
