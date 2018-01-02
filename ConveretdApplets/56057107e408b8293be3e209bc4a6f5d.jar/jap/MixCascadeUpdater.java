// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import anon.infoservice.MixCascade;
import anon.infoservice.AbstractDatabaseEntry;
import anon.util.Updater;
import anon.infoservice.update.AbstractMixCascadeUpdater;

public class MixCascadeUpdater extends AbstractMixCascadeUpdater
{
    public MixCascadeUpdater(final ObservableInfo observableInfo) {
        super(observableInfo);
    }
    
    public MixCascadeUpdater(final long n, final boolean b, final ObservableInfo observableInfo) {
        super(n, b, observableInfo);
    }
    
    protected AbstractDatabaseEntry getPreferredEntry() {
        return JAPController.getInstance().getCurrentMixCascade();
    }
    
    protected void setPreferredEntry(final AbstractDatabaseEntry abstractDatabaseEntry) {
        if (abstractDatabaseEntry instanceof MixCascade) {
            JAPController.getInstance().setCurrentMixCascade((MixCascade)abstractDatabaseEntry);
        }
    }
}
