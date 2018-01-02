// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang.splitter;

import java.awt.Dimension;
import java.awt.Component;

public class SplitterSpace extends Component
{
    public synchronized Dimension getPreferredSize() {
        return new Dimension(10, 10);
    }
    
    public synchronized Dimension getMinimumSize() {
        return new Dimension(10, 10);
    }
}
