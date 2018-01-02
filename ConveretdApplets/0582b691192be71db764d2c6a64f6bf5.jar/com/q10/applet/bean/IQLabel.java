// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.applet.bean;

import java.awt.Color;
import java.awt.Image;

public interface IQLabel
{
    void limpaTela();
    
    String getTexto();
    
    void setImage(final Image p0);
    
    void setImagePosition(final int p0);
    
    void setSublinhado(final boolean p0, final boolean p1);
    
    void setCorSublinhado(final Color p0);
    
    void setDistImgTxt(final int p0);
}
