// 
// Decompiled by Procyon v0.5.30
// 

package viewer.tools;

import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import viewer.RoundButton;
import java.awt.Choice;
import viewer.RaisedPanel;

public class Tools_ImagePanel extends RaisedPanel
{
    public Choice imgChoice;
    public RoundButton modeBtn;
    
    public void addImageItems(final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.imgChoice.addItem(array[i]);
        }
    }
    
    public Tools_ImagePanel() {
        super(20, 10, 10, 10, 0);
        super.setLabel("Image");
        super.setBorder(true);
        this.setLayout(new GridLayout(2, 1, 0, 8));
        this.add(this.imgChoice = new Choice());
        (this.modeBtn = new RoundButton("Mosaic")).setColor(Color.orange);
        this.modeBtn.setForeground(Color.black);
        this.add(this.modeBtn);
    }
}
