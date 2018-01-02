// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.applet.bean;

import java.awt.Label;

public class QLabelFactory
{
    public static Label getQLabel(final String s, final String s2) {
        Label label = null;
        if (s.equalsIgnoreCase("QLabel")) {
            label = new QLabel(s2);
        }
        if (s.equalsIgnoreCase("QLabel2")) {
            label = new QLabel2(s2);
        }
        return label;
    }
}
