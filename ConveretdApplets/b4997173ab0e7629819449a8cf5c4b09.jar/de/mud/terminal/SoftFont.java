// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.terminal;

import java.awt.Graphics;
import java.util.Hashtable;

public class SoftFont
{
    private static final char SF_BITMAP = '\0';
    private static final char SF_FILLRECT = '\u0001';
    private static final char SF_CHAR = '\0';
    private static final char SF_WIDTH = '\u0001';
    private static final char SF_HEIGHT = '\u0002';
    private static final char SF_TYPE = '\u0003';
    private static final char SF_DATA = '\u0004';
    Hashtable font;
    private static char[][] fontdata;
    
    public SoftFont() {
        this.font = new Hashtable();
        for (int i = 0; i < SoftFont.fontdata.length; ++i) {
            this.font.put(new Integer(SoftFont.fontdata[i][0]), new Integer(i));
        }
    }
    
    public boolean inSoftFont(final char c) {
        final boolean insoftfont = null != this.font.get(new Integer(c));
        if (!insoftfont && c >= '\u0100') {
            System.out.println("Character " + (int)c + " not in softfont");
        }
        return insoftfont;
    }
    
    public void drawChar(final Graphics g, final char c, final int x, final int y, final int cw, final int ch) {
        final Object Ientry = this.font.get(new Integer(c));
        if (Ientry == null) {
            return;
        }
        final int entry = (int)Ientry;
        final int fontwidth = SoftFont.fontdata[entry][1];
        final int fontheight = SoftFont.fontdata[entry][2];
        final double dw = cw * 1.0 / fontwidth;
        final double dh = ch * 1.0 / fontheight;
        switch (SoftFont.fontdata[entry][3]) {
            case '\0': {
                for (int h = 0; h < fontheight; ++h) {
                    for (int w = 0; w < fontwidth; ++w) {
                        if (0x0 != (SoftFont.fontdata[entry][h + 4] & 1 << 7 - w)) {
                            g.fillRect(x + (int)(w * dw), y + (int)(h * dh), (int)((w + 1) * dw) - (int)(w * dw), (int)((h + 1) * dh) - (int)(h * dh));
                        }
                    }
                }
                break;
            }
            case '\u0001': {
                for (int i = 4; i < SoftFont.fontdata[entry].length; ++i) {
                    final int w = (SoftFont.fontdata[entry][i] & '\uf000') >> 12;
                    final int h = (SoftFont.fontdata[entry][i] & '\u0f00') >> 8;
                    final int xw = (SoftFont.fontdata[entry][i] & '\u00f0') >> 4;
                    final int xh = SoftFont.fontdata[entry][i] & '\u000f';
                    g.fillRect(x + (int)(w * dw), y + (int)(h * dh), (int)((w + xw) * dw) - (int)(w * dw), (int)((h + xh) * dh) - (int)(h * dh));
                }
                break;
            }
        }
    }
    
    static {
        SoftFont.fontdata = new char[][] { { '\u0001', '\b', '\b', '\0', '~', '\u0081', '¥', '\u0081', '½', '\u0099', '\u0081', '~' }, { '\u0002', '\b', '\b', '\0', '~', '\u00ff', '\u00db', '\u00ff', '\u00c3', '\u00e7', '\u00ff', '~' }, { '\u0003', '\b', '\b', '\0', 'l', '\u00fe', '\u00fe', '\u00fe', '|', '8', '\u0010', '\0' }, { '\u0004', '\b', '\b', '\0', '\u0010', '8', '|', '\u00fe', '|', '8', '\u0010', '\0' }, { '\u0005', '\b', '\b', '\0', '8', '|', '8', '\u00fe', '\u00fe', '\u00d6', '\u0010', '8' }, { '\u0006', '\b', '\b', '\0', '\u0010', '8', '|', '\u00fe', '\u00fe', '|', '\u0010', '8' }, { '\u2666', '\b', '\b', '\0', '\0', '\0', '\u0018', '<', '<', '\u0018', '\0', '\0' }, { '\u0007', '\b', '\b', '\0', '\0', '\0', '\u0018', '<', '<', '\u0018', '\0', '\0' }, { '\b', '\b', '\b', '\0', '\u00ff', '\u00ff', '\u00e7', '\u00c3', '\u00c3', '\u00e7', '\u00ff', '\u00ff' }, { '\t', '\b', '\b', '\0', '\0', '<', 'f', 'B', 'B', 'f', '<', '\0' }, { '\n', '\b', '\b', '\0', '\u00ff', '\u00c3', '\u0099', '½', '½', '\u0099', '\u00c3', '\u00ff' }, { '\u000b', '\b', '\b', '\0', '\u000f', '\u0007', '\u000f', '}', '\u00cc', '\u00cc', '\u00cc', 'x' }, { '\f', '\b', '\b', '\0', '<', 'f', 'f', 'f', '<', '\u0018', '~', '\u0018' }, { '\r', '\b', '\b', '\0', '?', '3', '?', '0', '0', 'p', '\u00f0', '\u00e0' }, { '\u000e', '\b', '\b', '\0', '\u007f', 'c', '\u007f', 'c', 'c', 'g', '\u00e6', '\u00c0' }, { '\u000f', '\b', '\b', '\0', '\u0018', '\u00db', '<', '\u00e7', '\u00e7', '<', '\u00db', '\u0018' }, { '\u0010', '\b', '\b', '\0', '\u0080', '\u00e0', '\u00f8', '\u00fe', '\u00f8', '\u00e0', '\u0080', '\0' }, { '\u0011', '\b', '\b', '\0', '\u0002', '\u000e', '>', '\u00fe', '>', '\u000e', '\u0002', '\0' }, { '\u0012', '\b', '\b', '\0', '\u0018', '<', '~', '\u0018', '\u0018', '~', '<', '\u0018' }, { '\u0013', '\b', '\b', '\0', 'f', 'f', 'f', 'f', 'f', '\0', 'f', '\0' }, { '\u0014', '\b', '\b', '\0', '\u007f', '\u00db', '\u00db', '{', '\u001b', '\u001b', '\u001b', '\0' }, { '\u0015', '\b', '\b', '\0', '>', 'a', '<', 'f', 'f', '<', '\u0086', '|' }, { '\u0016', '\b', '\b', '\0', '\0', '\0', '\0', '\0', '~', '~', '~', '\0' }, { '\u0017', '\b', '\b', '\0', '\u0018', '<', '~', '\u0018', '~', '<', '\u0018', '\u00ff' }, { '\u0018', '\b', '\b', '\0', '\u0018', '<', '~', '\u0018', '\u0018', '\u0018', '\u0018', '\0' }, { '\u0019', '\b', '\b', '\0', '\u0018', '\u0018', '\u0018', '\u0018', '~', '<', '\u0018', '\0' }, { '\u001a', '\b', '\b', '\0', '\0', '\u0018', '\f', '\u00fe', '\f', '\u0018', '\0', '\0' }, { '\u001b', '\b', '\b', '\0', '\0', '0', '`', '\u00fe', '`', '0', '\0', '\0' }, { '\u001c', '\b', '\b', '\0', '\0', '\0', '\u00c0', '\u00c0', '\u00c0', '\u00fe', '\0', '\0' }, { '\u001d', '\b', '\b', '\0', '\0', '$', 'f', '\u00ff', 'f', '$', '\0', '\0' }, { '\u001e', '\b', '\b', '\0', '\0', '\u0018', '<', '~', '\u00ff', '\u00ff', '\0', '\0' }, { '\u001f', '\b', '\b', '\0', '\0', '\u00ff', '\u00ff', '~', '<', '\u0018', '\0', '\0' }, { '\u007f', '\b', '\b', '\0', '\0', '\u0010', '8', 'l', '\u00c6', '\u00c6', '\u00fe', '\0' }, { '\u2591', '\b', '\b', '\0', '\"', '\u0088', '\"', '\u0088', '\"', '\u0088', '\"', '\u0088' }, { '\u2592', '\b', '\b', '\0', 'U', 'ª', 'U', 'ª', 'U', 'ª', 'U', 'ª' }, { '\u2593', '\b', '\b', '\0', 'w', '\u00dd', 'w', '\u00dd', 'w', '\u00dd', 'w', '\u00dd' }, { '\u221a', '\b', '\b', '\0', 'x', '\f', '\u0018', '0', '|', '\0', '\0', '\0' }, { '\u2320', '\b', '\b', '\u0001', '\u4031', '\u3127', '\u6122' }, { '\u2321', '\b', '\b', '\u0001', '\u3027', '\u0522', '\u1731' }, { '\u25a0', '\b', '\b', '\u0001', '\u2244' }, { '\u2502', '\b', '\b', '\u0001', '\u3028' }, { '\u2524', '\b', '\b', '\u0001', '\u3028', '\u0431' }, { '\u2561', '\b', '\b', '\u0001', '\u3028', '\u0231', '\u0431' }, { '\u2562', '\b', '\b', '\u0001', '\u2028', '\u5028', '\u0421' }, { '\u2556', '\b', '\b', '\u0001', '\u0471', '\u2523', '\u5523' }, { '\u2555', '\b', '\b', '\u0001', '\u3226', '\u0231', '\u0431' }, { '\u2563', '\b', '\b', '\u0001', '\u2022', '\u0221', '\u0421', '\u2424', '\u5028' }, { '\u2551', '\b', '\b', '\u0001', '\u2028', '\u5028' }, { '\u2557', '\b', '\b', '\u0001', '\u0271', '\u5325', '\u0441', '\u2523' }, { '\u255d', '\b', '\b', '\u0001', '\u2022', '\u0241', '\u5025', '\u0451' }, { '\u255c', '\b', '\b', '\u0001', '\u2024', '\u5024', '\u0471' }, { '\u255b', '\b', '\b', '\u0001', '\u3025', '\u0231', '\u0431' }, { '\u2510', '\b', '\b', '\u0001', '\u0451', '\u3523' }, { '\u2514', '\b', '\b', '\u0001', '\u3025', '\u5431' }, { '\u2534', '\b', '\b', '\u0001', '\u3024', '\u0481' }, { '\u252c', '\b', '\b', '\u0001', '\u0481', '\u3523' }, { '\u251c', '\b', '\b', '\u0001', '\u3028', '\u5431' }, { '\u2500', '\b', '\b', '\u0001', '\u0481' }, { '\u253c', '\b', '\b', '\u0001', '\u3028', '\u0481' }, { '\u255e', '\b', '\b', '\u0001', '\u3028', '\u5231', '\u5431' }, { '\u255f', '\b', '\b', '\u0001', '\u2028', '\u5028', '\u7411' }, { '\u255a', '\b', '\b', '\u0001', '\u2025', '\u5023', '\u7211', '\u4441' }, { '\u2554', '\b', '\b', '\u0001', '\u2261', '\u2325', '\u5424', '\u7411' }, { '\u2569', '\b', '\b', '\u0001', '\u2022', '\u0241', '\u5022', '\u5231', '\u0481' }, { '\u2566', '\b', '\b', '\u0001', '\u0281', '\u0441', '\u2523', '\u5431', '\u5523' }, { '\u2560', '\b', '\b', '\u0001', '\u2028', '\u5022', '\u5231', '\u5431', '\u5623' }, { '\u2550', '\b', '\b', '\u0001', '\u0281', '\u0481' }, { '\u256c', '\b', '\b', '\u0001', '\u2022', '\u0241', '\u5022', '\u5231', '\u0441', '\u2523', '\u5431', '\u5523' }, { '\u2567', '\b', '\b', '\u0001', '\u3022', '\u0281', '\u0481' }, { '\u2568', '\b', '\b', '\u0001', '\u2024', '\u5024', '\u0481' }, { '\u2564', '\b', '\b', '\u0001', '\u0281', '\u0481', '\u3523' }, { '\u2565', '\b', '\b', '\u0001', '\u0481', '\u2523', '\u5523' }, { '\u2559', '\b', '\b', '\u0001', '\u2024', '\u5024', '\u2461' }, { '\u2558', '\b', '\b', '\u0001', '\u3025', '\u5231', '\u5431' }, { '\u2552', '\b', '\b', '\u0001', '\u3226', '\u5231', '\u5431' }, { '\u2553', '\b', '\b', '\u0001', '\u2461', '\u2523', '\u5523' }, { '\u256b', '\b', '\b', '\u0001', '\u2028', '\u5028', '\u0481' }, { '\u256a', '\b', '\b', '\u0001', '\u3028', '\u0281', '\u0481' }, { '\u2518', '\b', '\b', '\u0001', '\u3025', '\u0431' }, { '\u250c', '\b', '\b', '\u0001', '\u3451', '\u3523' }, { '\u2588', '\b', '\b', '\u0001', '\u0088' }, { '\u2584', '\b', '\b', '\u0001', '\u0484' }, { '\u258c', '\b', '\b', '\u0001', 'H' }, { '\u2590', '\b', '\b', '\u0001', '\u4048' }, { '\u2580', '\b', '\b', '\u0001', '\u0084' }, { '\u2261', '\b', '\b', '\u0001', '\u2081', '\u4081', '\u6081' }, { '\u221e', '\b', '\b', '\0', '\0', '\0', '~', '\u00db', '\u00db', '~', '\0', '\0' }, { '\u207f', '\b', '\b', '\u0001', '\u1041', '\u1124', '\u4124' }, { '²', '\b', '\b', '\0', 'p', '\u001c', '8', '`', 'x', '\0', '\0', '\0' }, { '\u2219', '\b', '\b', '\u0001', '\u3322' } };
    }
}
