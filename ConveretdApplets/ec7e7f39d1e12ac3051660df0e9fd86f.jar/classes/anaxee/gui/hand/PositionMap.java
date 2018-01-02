// 
// Decompiled by Procyon v0.5.30
// 

package classes.anaxee.gui.hand;

import java.util.List;
import com.neurotec.biometrics.NFPosition;

class PositionMap
{
    private NFPosition nfPosition;
    private List<HandComponent.Position> positions;
    
    public PositionMap(final NFPosition nfPosition, final List<HandComponent.Position> positions) {
        this.nfPosition = nfPosition;
        this.positions = positions;
    }
    
    public NFPosition getNfPosition() {
        return this.nfPosition;
    }
    
    public List<HandComponent.Position> getPositions() {
        return this.positions;
    }
}
