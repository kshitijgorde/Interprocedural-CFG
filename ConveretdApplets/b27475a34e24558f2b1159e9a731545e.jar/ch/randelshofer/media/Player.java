// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.media;

import ch.randelshofer.gui.event.ChangeListener;
import java.awt.Component;
import java.awt.image.ImageProducer;
import ch.randelshofer.gui.BoundedRangeModel;

public interface Player
{
    void start();
    
    void stop();
    
    boolean isActive();
    
    BoundedRangeModel getBoundedRangeModel();
    
    ImageProducer getImageProducer();
    
    Component getVisualComponent();
    
    Component getControlPanelComponent();
    
    void addChangeListener(final ChangeListener p0);
    
    void removeChangeListener(final ChangeListener p0);
}
