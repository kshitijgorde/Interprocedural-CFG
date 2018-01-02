// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import wordle.core.c.f;
import java.util.Iterator;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import java.awt.geom.AffineTransform;
import java.awt.font.FontRenderContext;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.util.ArrayList;
import wordle.core.b.a.d;
import java.awt.Color;
import java.util.Collections;
import wordle.core.b.a.e;
import wordle.core.c.a;
import java.util.Map;
import java.util.LinkedHashMap;

final class am extends LinkedHashMap
{
    am() {
        this.put("BW", b(16777215, new int[] { 0 }));
        this.put("WB", b(0, new int[] { 16777215 }));
        this.put("Ghostly", b(16777215, new int[] { 0, 3355443, 6710886, 8947848 }));
        this.put("Indian Earthy", b(0, new int[] { 13208367, 8404007, 12936480, 14791707, 8419606 }));
        this.put("Firenze", b(0, new int[] { 4622694, 16773285, 16756795, 11946278, 9316352 }));
        this.put("Chilled Summer", b(0, new int[] { 9290738, 13362424, 15921906, 9223967, 8037915 }));
        this.put("Blue Meets Orange", b(0, new int[] { 143449, 4161190, 15921906, 14261810, 12541450 }));
        this.put("Kindled", b(16777215, new int[] { 2032135, 5374733, 9766670, 10691341, 12408320 }));
        this.put("Shooting Star", b(16777215, new int[] { 1313574, 2035776, 4726061, 6826273, 9781010 }));
        this.put("Organic Carrot", b(16777215, new int[] { 2833152, 6516224, 16232704, 15417856, 7012377 }));
        this.put("Milk Paints", b(16777215, new int[] { 6499109, 11901515, 3167313, 6911287, 7883822 }));
        this.put("Moss", b(16777215, new int[] { 410128, 3036678, 10918928, 7626768, 4337168 }));
        this.put("Heat", b(16777215, new int[] { 13421772, 10053222, 6684672, 3342336, 6710886 }));
        this.put("yramirP", b(0, new int[] { 16711680, 65280, 255 }));
    }
}
