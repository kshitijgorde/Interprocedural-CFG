// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.sharklet.ui;

import com.grooveshark.songscanner.SongScannerListener;
import javax.swing.event.TableModelListener;
import com.grooveshark.ui.wizard.WizardContentPanel;

public abstract class EditorPanel extends WizardContentPanel implements TableModelListener, SongScannerListener
{
    private static final long serialVersionUID = -2145266979826435491L;
    
    public EditorPanel(final String title, final String description) {
        super(title, description);
    }
}
