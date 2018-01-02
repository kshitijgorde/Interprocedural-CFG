// 
// Decompiled by Procyon v0.5.30
// 

package Go.strategy;

import Go.GoPosition;

public interface GoEstimatorInterface
{
    float estimatePosition(final GoPosition p0, final int p1);
    
    int checkPositionState(final GoPosition p0);
}
