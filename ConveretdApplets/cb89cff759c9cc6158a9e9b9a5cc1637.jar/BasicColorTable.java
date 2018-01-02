import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class BasicColorTable extends Panel
{
    HexColorTool hct;
    
    public BasicColorTable(final HexColorTool hct) {
        this.setBackground(Color.lightGray);
        final GridLayout layout = new GridLayout(9, 9);
        layout.setVgap(4);
        layout.setHgap(4);
        this.setLayout(layout);
        this.hct = hct;
        final BasicColorField[] array = new BasicColorField[75];
        int n = 0;
        for (int i = 0; i <= 256; i += 64) {
            for (int j = 0; j <= 256; j += 64) {
                for (int k = 0; k <= 256; k += 128) {
                    this.add(array[n] = new BasicColorField(new Color(((i < 1) ? 1 : i) - 1, ((j < 1) ? 1 : j) - 1, ((k < 1) ? 1 : k) - 1), hct));
                    ++n;
                }
            }
        }
    }
}
