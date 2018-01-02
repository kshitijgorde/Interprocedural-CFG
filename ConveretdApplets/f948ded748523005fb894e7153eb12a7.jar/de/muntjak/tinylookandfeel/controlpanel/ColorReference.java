// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.controlpanel;

import javax.swing.AbstractButton;
import java.awt.Graphics;
import java.awt.Component;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import javax.swing.Icon;
import de.muntjak.tinylookandfeel.Theme;
import java.awt.Color;
import javax.swing.plaf.ColorUIResource;

public class ColorReference
{
    public static final int ABS_COLOR = 1;
    public static final int MAIN_COLOR = 2;
    public static final int BACK_COLOR = 3;
    public static final int DIS_COLOR = 4;
    public static final int FRAME_COLOR = 5;
    public static final int SUB1_COLOR = 6;
    public static final int SUB2_COLOR = 7;
    public static final int SUB3_COLOR = 8;
    public static final int SUB4_COLOR = 9;
    public static final int SUB5_COLOR = 10;
    public static final int SUB6_COLOR = 11;
    public static final int SUB7_COLOR = 12;
    public static final int SUB8_COLOR = 13;
    protected ColorUIResource c;
    protected int sat;
    protected int bri;
    protected int ref;
    protected boolean locked;
    protected ColorIcon icon;
    protected static ColorIcon absolueIcon;
    
    public ColorReference() {
    }
    
    public ColorReference(final Color color) {
        this.c = new ColorUIResource(color);
        this.sat = 0;
        this.bri = 0;
        this.ref = 1;
    }
    
    public ColorReference(final Color color, final int sat, final int bri, final int ref) {
        this.c = new ColorUIResource(color);
        this.sat = sat;
        this.bri = bri;
        this.ref = ref;
    }
    
    public ColorReference(final Color color, final int sat, final int bri, final int ref, final boolean locked) {
        this.c = new ColorUIResource(color);
        this.sat = sat;
        this.bri = bri;
        this.ref = ref;
        this.locked = locked;
    }
    
    public ColorReference(final ColorReference colorReference) {
        this.c = colorReference.c;
        this.sat = colorReference.sat;
        this.bri = colorReference.bri;
        this.ref = colorReference.ref;
    }
    
    public ColorReference(final ColorReference colorReference, final int ref) {
        this.c = colorReference.c;
        this.sat = 0;
        this.bri = 0;
        this.ref = ref;
    }
    
    public void reset() {
        this.sat = 0;
        this.bri = 0;
    }
    
    public ColorUIResource getColor() {
        return this.c;
    }
    
    public int getSaturation() {
        return this.sat;
    }
    
    public int getBrightness() {
        return this.bri;
    }
    
    public int getReference() {
        return this.ref;
    }
    
    public ColorUIResource getReferenceColor() {
        switch (this.ref) {
            case 2: {
                return Theme.mainColor[Theme.style].getColor();
            }
            case 3: {
                return Theme.backColor[Theme.style].getColor();
            }
            case 4: {
                return Theme.disColor[Theme.style].getColor();
            }
            case 5: {
                return Theme.frameColor[Theme.style].getColor();
            }
            case 6: {
                return Theme.sub1Color[Theme.style].getColor();
            }
            case 7: {
                return Theme.sub2Color[Theme.style].getColor();
            }
            case 8: {
                return Theme.sub3Color[Theme.style].getColor();
            }
            case 9: {
                return Theme.sub4Color[Theme.style].getColor();
            }
            case 10: {
                return Theme.sub5Color[Theme.style].getColor();
            }
            case 11: {
                return Theme.sub6Color[Theme.style].getColor();
            }
            case 12: {
                return Theme.sub7Color[Theme.style].getColor();
            }
            case 13: {
                return Theme.sub8Color[Theme.style].getColor();
            }
            default: {
                return this.c;
            }
        }
    }
    
    public static ColorUIResource getReferenceColor(final int n) {
        switch (n) {
            case 2: {
                return Theme.mainColor[Theme.style].getColor();
            }
            case 3: {
                return Theme.backColor[Theme.style].getColor();
            }
            case 4: {
                return Theme.disColor[Theme.style].getColor();
            }
            case 5: {
                return Theme.frameColor[Theme.style].getColor();
            }
            case 6: {
                return Theme.sub1Color[Theme.style].getColor();
            }
            case 7: {
                return Theme.sub2Color[Theme.style].getColor();
            }
            case 8: {
                return Theme.sub3Color[Theme.style].getColor();
            }
            case 9: {
                return Theme.sub4Color[Theme.style].getColor();
            }
            case 10: {
                return Theme.sub5Color[Theme.style].getColor();
            }
            case 11: {
                return Theme.sub6Color[Theme.style].getColor();
            }
            case 12: {
                return Theme.sub7Color[Theme.style].getColor();
            }
            case 13: {
                return Theme.sub8Color[Theme.style].getColor();
            }
            default: {
                return null;
            }
        }
    }
    
    public String getReferenceString() {
        switch (this.ref) {
            case 2: {
                return "Main Color";
            }
            case 3: {
                return "Back Color";
            }
            case 4: {
                return "Disabled Color";
            }
            case 5: {
                return "Frame Color";
            }
            case 6: {
                return "Sub1 Color";
            }
            case 7: {
                return "Sub2 Color";
            }
            case 8: {
                return "Sub3 Color";
            }
            case 9: {
                return "Sub4 Color";
            }
            case 10: {
                return "Sub5 Color";
            }
            case 11: {
                return "Sub6 Color";
            }
            case 12: {
                return "Sub7 Color";
            }
            case 13: {
                return "Sub8 Color";
            }
            default: {
                return "";
            }
        }
    }
    
    public void setColor(final Color color) {
        if (!this.isAbsoluteColor()) {
            return;
        }
        this.c = new ColorUIResource(color);
    }
    
    public void setSaturation(final int sat) {
        this.sat = sat;
    }
    
    public void setBrightness(final int bri) {
        this.bri = bri;
    }
    
    public void setReference(final int ref) {
        this.ref = ref;
    }
    
    public void setColor(final int sat, final int bri) {
        if (this.isAbsoluteColor()) {
            return;
        }
        this.sat = sat;
        this.bri = bri;
        switch (this.ref) {
            case 2: {
                this.c = new ColorUIResource(SBChooser.getAdjustedColor(Theme.mainColor[Theme.style].getColor(), sat, bri));
                break;
            }
            case 3: {
                this.c = new ColorUIResource(SBChooser.getAdjustedColor(Theme.backColor[Theme.style].getColor(), sat, bri));
                break;
            }
            case 4: {
                this.c = new ColorUIResource(SBChooser.getAdjustedColor(Theme.disColor[Theme.style].getColor(), sat, bri));
                break;
            }
            case 5: {
                this.c = new ColorUIResource(SBChooser.getAdjustedColor(Theme.frameColor[Theme.style].getColor(), sat, bri));
                break;
            }
            case 6: {
                this.c = new ColorUIResource(SBChooser.getAdjustedColor(Theme.sub1Color[Theme.style].getColor(), sat, bri));
                break;
            }
            case 7: {
                this.c = new ColorUIResource(SBChooser.getAdjustedColor(Theme.sub2Color[Theme.style].getColor(), sat, bri));
                break;
            }
            case 8: {
                this.c = new ColorUIResource(SBChooser.getAdjustedColor(Theme.sub3Color[Theme.style].getColor(), sat, bri));
                break;
            }
            case 9: {
                this.c = new ColorUIResource(SBChooser.getAdjustedColor(Theme.sub4Color[Theme.style].getColor(), sat, bri));
                break;
            }
            case 10: {
                this.c = new ColorUIResource(SBChooser.getAdjustedColor(Theme.sub5Color[Theme.style].getColor(), sat, bri));
                break;
            }
            case 11: {
                this.c = new ColorUIResource(SBChooser.getAdjustedColor(Theme.sub6Color[Theme.style].getColor(), sat, bri));
                break;
            }
            case 12: {
                this.c = new ColorUIResource(SBChooser.getAdjustedColor(Theme.sub7Color[Theme.style].getColor(), sat, bri));
                break;
            }
            case 13: {
                this.c = new ColorUIResource(SBChooser.getAdjustedColor(Theme.sub8Color[Theme.style].getColor(), sat, bri));
                break;
            }
        }
    }
    
    public ColorUIResource update() {
        if (this.isAbsoluteColor()) {
            return this.c;
        }
        switch (this.ref) {
            case 2: {
                this.c = new ColorUIResource(SBChooser.getAdjustedColor(Theme.mainColor[Theme.style].getColor(), this.sat, this.bri));
                break;
            }
            case 3: {
                this.c = new ColorUIResource(SBChooser.getAdjustedColor(Theme.backColor[Theme.style].getColor(), this.sat, this.bri));
                break;
            }
            case 4: {
                this.c = new ColorUIResource(SBChooser.getAdjustedColor(Theme.disColor[Theme.style].getColor(), this.sat, this.bri));
                break;
            }
            case 5: {
                this.c = new ColorUIResource(SBChooser.getAdjustedColor(Theme.frameColor[Theme.style].getColor(), this.sat, this.bri));
                break;
            }
            case 6: {
                this.c = new ColorUIResource(SBChooser.getAdjustedColor(Theme.sub1Color[Theme.style].getColor(), this.sat, this.bri));
                break;
            }
            case 7: {
                this.c = new ColorUIResource(SBChooser.getAdjustedColor(Theme.sub2Color[Theme.style].getColor(), this.sat, this.bri));
                break;
            }
            case 8: {
                this.c = new ColorUIResource(SBChooser.getAdjustedColor(Theme.sub3Color[Theme.style].getColor(), this.sat, this.bri));
                break;
            }
            case 9: {
                this.c = new ColorUIResource(SBChooser.getAdjustedColor(Theme.sub4Color[Theme.style].getColor(), this.sat, this.bri));
                break;
            }
            case 10: {
                this.c = new ColorUIResource(SBChooser.getAdjustedColor(Theme.sub5Color[Theme.style].getColor(), this.sat, this.bri));
                break;
            }
            case 11: {
                this.c = new ColorUIResource(SBChooser.getAdjustedColor(Theme.sub6Color[Theme.style].getColor(), this.sat, this.bri));
                break;
            }
            case 12: {
                this.c = new ColorUIResource(SBChooser.getAdjustedColor(Theme.sub7Color[Theme.style].getColor(), this.sat, this.bri));
                break;
            }
            case 13: {
                this.c = new ColorUIResource(SBChooser.getAdjustedColor(Theme.sub8Color[Theme.style].getColor(), this.sat, this.bri));
                break;
            }
        }
        return this.c;
    }
    
    public boolean isAbsoluteColor() {
        return this.ref == 1;
    }
    
    public void setLocked(final boolean locked) {
        this.locked = locked;
    }
    
    public boolean isLocked() {
        return this.locked;
    }
    
    public String toString() {
        return this.c.toString();
    }
    
    public Icon getIcon() {
        if (this.icon == null) {
            this.icon = new ColorIcon(false);
        }
        return this.icon;
    }
    
    public static Icon getAbsoluteIcon() {
        if (ColorReference.absolueIcon == null) {
            ColorReference.absolueIcon = new ColorReference(Color.BLACK).new ColorIcon(true);
        }
        return ColorReference.absolueIcon;
    }
    
    public void save(final DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(this.c.getRGB());
        dataOutputStream.writeInt(this.sat);
        dataOutputStream.writeInt(this.bri);
        dataOutputStream.writeInt(this.ref);
        dataOutputStream.writeBoolean(this.locked);
    }
    
    public void load(final DataInputStream dataInputStream) throws IOException {
        try {
            if (Theme.fileID >= 12852) {
                this.c = new ColorUIResource(dataInputStream.readInt());
            }
            else {
                this.c = new ColorUIResource(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readInt());
            }
            this.sat = dataInputStream.readInt();
            this.bri = dataInputStream.readInt();
            this.ref = dataInputStream.readInt();
            this.locked = dataInputStream.readBoolean();
        }
        catch (Exception ex) {
            throw new IOException("ColorReference.load() : " + ex.getMessage());
        }
    }
    
    class ColorIcon implements Icon
    {
        private boolean paintGradients;
        
        ColorIcon(final boolean paintGradients) {
            this.paintGradients = paintGradients;
        }
        
        public int getIconHeight() {
            return 16;
        }
        
        public int getIconWidth() {
            return 16;
        }
        
        public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
            final Color color = graphics.getColor();
            graphics.setColor(Color.GRAY);
            graphics.drawRect(n, n2, this.getIconWidth(), this.getIconHeight());
            if (this.paintGradients) {
                float n3 = 0.0f;
                for (int i = 0; i < 15; ++i) {
                    graphics.setColor(Color.getHSBColor(n3, 0.5f, 1.0f));
                    graphics.drawLine(n + 1 + i, n2 + 1, n + 1 + i, n2 + this.getIconHeight() - 1);
                    n3 += 0.0625;
                }
            }
            else {
                graphics.setColor(ColorReference.this.c);
                graphics.fillRect(n + 1, n2 + 1, this.getIconWidth() - 1, this.getIconHeight() - 1);
            }
            if (component instanceof AbstractButton && ((AbstractButton)component).isSelected()) {
                graphics.setColor(Color.WHITE);
                this.drawArrow(graphics, n + 1, n2 + 1);
                graphics.setColor(Color.BLACK);
                this.drawArrow(graphics, n, n2);
            }
            graphics.setColor(color);
        }
        
        private void drawArrow(final Graphics graphics, final int n, final int n2) {
            graphics.drawLine(n + 3, n2 + 5, n + 3, n2 + 7);
            graphics.drawLine(n + 4, n2 + 6, n + 4, n2 + 8);
            graphics.drawLine(n + 5, n2 + 7, n + 5, n2 + 9);
            graphics.drawLine(n + 6, n2 + 6, n + 6, n2 + 8);
            graphics.drawLine(n + 7, n2 + 5, n + 7, n2 + 7);
            graphics.drawLine(n + 8, n2 + 4, n + 8, n2 + 6);
            graphics.drawLine(n + 9, n2 + 3, n + 9, n2 + 5);
        }
    }
}
