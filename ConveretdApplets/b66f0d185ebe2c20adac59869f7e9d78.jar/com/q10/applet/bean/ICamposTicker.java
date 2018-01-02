// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.applet.bean;

import java.util.Vector;
import java.awt.Image;
import java.awt.Font;
import com.q10.util.QData;
import java.awt.Color;

public interface ICamposTicker
{
    public static final String ULT = "ULT";
    public static final String MAX = "MAX";
    public static final String MIN = "MIN";
    public static final String FEC = "FEC";
    public static final String VAR = "VAR";
    public static final String VAR30 = "VAR30";
    
    void setSeparador(final String p0);
    
    void setUltimo(final float p0);
    
    void setVar(final float p0);
    
    void setMax(final float p0);
    
    void setMin(final float p0);
    
    void setFec(final float p0);
    
    void setVar30(final float p0);
    
    void setCorVarNeg(final Color p0);
    
    void setCorVarPos(final Color p0);
    
    void setCorSemVar(final Color p0);
    
    void setCorFonteHora(final Color p0);
    
    void setMostraCodigo(final boolean p0);
    
    void setMostraNome(final boolean p0);
    
    void setMostraHora(final boolean p0);
    
    void setDataInvalida(final boolean p0);
    
    void setDataUlt(final QData p0);
    
    void setData(final QData p0, final boolean p1);
    
    Image getImagemCotacao(final Font p0, final int p1, final Color p2, final Color p3);
    
    void setNomeAtivo(final String p0);
    
    void setCodAtivo(final String p0);
    
    void setVarPorcento(final boolean p0);
    
    void setDec(final int p0);
    
    void setLegMax(final String p0);
    
    void setLegMin(final String p0);
    
    void setLegFec(final String p0);
    
    void setLegUlt(final String p0);
    
    void setLegVarDia(final String p0);
    
    void setLegVar30(final String p0);
    
    void setCampos(final Vector p0);
    
    void setCorLegendas(final Color p0);
    
    void setIdioma(final String p0);
}
