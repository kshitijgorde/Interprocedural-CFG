// 
// Decompiled by Procyon v0.5.30
// 

package viewer.cards;

import java.awt.Component;
import java.awt.Event;
import viewer.ImageViewerApplet;
import viewer.cards.mozaic.Mozaic_Panel;
import viewer.RaisedPanel;

public class Card_MozaicPanel extends RaisedPanel
{
    public Mozaic_Panel[] mozaicTile;
    private ImageViewerApplet app;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 502: {
                for (int i = 0; i < this.mozaicTile.length; ++i) {
                    if (event.target == this.mozaicTile[i].canvas) {
                        this.app.showSingle(i);
                        break;
                    }
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public Card_MozaicPanel(final ImageViewerApplet app) {
        super(4, 4, 4, 4, 6);
        this.app = app;
        final int length = ImageViewerApplet.fileList.length;
        this.mozaicTile = new Mozaic_Panel[length];
        for (int i = 0; i < length; ++i) {
            (this.mozaicTile[i] = new Mozaic_Panel(i)).setBackground(app.getBackground());
            this.add(this.mozaicTile[i]);
        }
    }
}
