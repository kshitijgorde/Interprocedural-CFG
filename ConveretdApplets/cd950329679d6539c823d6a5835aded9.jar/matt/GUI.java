// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import javax.swing.JTextArea;
import javax.swing.JProgressBar;

public interface GUI
{
    void clearGraphs();
    
    Graph getSignalGraph();
    
    Graph getFrameGraph();
    
    Graph getOdfGraph();
    
    JProgressBar getProgressBar();
    
    void setTitle(final String p0);
    
    void enableButtons(final boolean p0);
    
    void clearFFTGraphs();
    
    JTextArea getTxtABC();
    
    void setBns();
}
