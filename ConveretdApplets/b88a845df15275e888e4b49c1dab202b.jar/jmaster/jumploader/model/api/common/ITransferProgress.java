// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.api.common;

public interface ITransferProgress
{
    long getBytesTransferred();
    
    long getBytesLeft();
    
    long getBytesTotal();
    
    long getTimeLeft();
    
    double getCompletion();
    
    double getCompletionPercent();
    
    int getRate();
}
