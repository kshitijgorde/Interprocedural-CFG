// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.applet.bean;

import java.util.Vector;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Font;
import com.q10.util.Formata;
import java.awt.Color;
import com.q10.util.QData;
import com.q10.comum.cot.Ultima;
import java.text.DateFormat;
import java.text.DecimalFormat;

public class CamposAtivo implements ICamposTicker
{
    private String nomeAtivo;
    private String codAtivo;
    private boolean varPorcento;
    private DecimalFormat dfValor;
    private DecimalFormat dfVar;
    private DateFormat dtf;
    private DateFormat hrf;
    private String idioma;
    private int dec;
    private Ultima ultimo;
    private float ult;
    private float var;
    private QData data;
    private String hora;
    private boolean horaInvalida;
    private QData dataUlt;
    private boolean dataInvalida;
    private boolean mostraCodigo;
    private boolean mostraNome;
    private boolean mostraHora;
    private static final String espaco = " ";
    private String separador;
    private Color corVarPos;
    private Color corVarNeg;
    private Color corSemVar;
    private Color corFonteHora;
    
    public CamposAtivo() {
        this.idioma = "br";
        this.separador = "  ";
        this.corVarPos = Color.blue;
        this.corVarNeg = Color.red;
        this.corSemVar = Color.black;
        this.corFonteHora = Color.black;
    }
    
    public CamposAtivo(final String nomeAtivo, final String codAtivo, final boolean varPorcento, final int dec) {
        this.idioma = "br";
        this.separador = "  ";
        this.corVarPos = Color.blue;
        this.corVarNeg = Color.red;
        this.corSemVar = Color.black;
        this.corFonteHora = Color.black;
        this.nomeAtivo = nomeAtivo;
        this.codAtivo = codAtivo;
        this.varPorcento = varPorcento;
        this.mostraCodigo = true;
        this.mostraNome = true;
        this.setDec(dec);
    }
    
    public void setNomeAtivo(final String nomeAtivo) {
        this.nomeAtivo = nomeAtivo;
    }
    
    public void setCodAtivo(final String codAtivo) {
        this.codAtivo = codAtivo;
    }
    
    public void setVarPorcento(final boolean varPorcento) {
        this.varPorcento = varPorcento;
    }
    
    public void setIdioma(final String idioma) {
        this.idioma = idioma;
        this.setDec(this.dec);
    }
    
    public void setDec(final int dec) {
        this.dec = dec;
        this.dfValor = Formata.formataDecimal(dec, this.idioma);
        this.dfVar = Formata.formataDecimal(2, this.idioma);
        Formata.getLocale(this.idioma);
        this.dtf = Formata.formataData('G', this.idioma);
        this.hrf = Formata.formataData('H');
    }
    
    public synchronized void setUltimo(final Ultima ultimo) {
        this.ultimo = ultimo;
    }
    
    public synchronized void setUltimo(final float ult) {
        this.ult = ult;
    }
    
    public synchronized void setVar(final float var) {
        this.var = var;
    }
    
    public void setData(final QData data, final boolean horaInvalida) {
        this.setHoraInvalida(horaInvalida);
        if (horaInvalida) {
            this.hora = this.dtf.format(this.dataUlt.getData().getTime());
        }
        else {
            this.hora = this.hrf.format(data.getData().getTime());
        }
        this.data = data;
        this.hora = String.valueOf(this.hora) + " ";
    }
    
    public void setHoraInvalida(final boolean horaInvalida) {
        this.horaInvalida = horaInvalida;
    }
    
    public void setDataUlt(final QData dataUlt) {
        this.dataUlt = dataUlt;
    }
    
    public void setDataInvalida(final boolean dataInvalida) {
        this.dataInvalida = dataInvalida;
    }
    
    public void setMostraCodigo(final boolean mostraCodigo) {
        this.mostraCodigo = mostraCodigo;
    }
    
    public void setMostraNome(final boolean mostraNome) {
        this.mostraNome = mostraNome;
    }
    
    public void setMostraHora(final boolean mostraHora) {
        this.mostraHora = mostraHora;
    }
    
    public void setSeparador(final String separador) {
        if (separador != null) {
            this.separador = separador;
        }
    }
    
    public synchronized String getCotacao() {
        final StringBuffer sb = new StringBuffer();
        if (this.mostraNome) {
            sb.append((this.nomeAtivo != null) ? this.nomeAtivo : "");
            sb.append(" ");
        }
        if (this.mostraCodigo) {
            sb.append(this.mostraNome ? "(" : "");
            sb.append((this.codAtivo != null) ? this.codAtivo.toUpperCase() : "");
            sb.append(this.mostraNome ? ")" : "");
            sb.append(" ");
        }
        sb.append(this.dfValor.format(this.ult));
        sb.append(" ");
        boolean b = true;
        if (!this.horaInvalida) {
            sb.append((this.var > 0.0f) ? "+" : "");
            sb.append(this.dfVar.format(this.var));
            sb.append(this.varPorcento ? "%" : "");
        }
        else {
            b = false;
            sb.append(this.dtf.format(this.dataUlt.getData().getTime()));
        }
        sb.append(this.separador);
        if (this.mostraHora && b) {
            sb.append(this.hora);
        }
        return sb.toString();
    }
    
    public void setCorVarPos(final Color corVarPos) {
        this.corVarPos = corVarPos;
    }
    
    public void setCorVarNeg(final Color corVarNeg) {
        this.corVarNeg = corVarNeg;
    }
    
    public void setCorSemVar(final Color corSemVar) {
        this.corSemVar = corSemVar;
    }
    
    public void setCorFonteHora(final Color corFonteHora) {
        this.corFonteHora = corFonteHora;
    }
    
    public synchronized Image getImagemCotacao(final Font font, final int n, final Color background, final Color foreground) {
        final Frame frame = new Frame();
        frame.addNotify();
        frame.setBackground(background);
        frame.setForeground(foreground);
        final String cotacao = this.getCotacao();
        final FontMetrics fontMetrics = frame.getFontMetrics(font);
        final Image image = frame.createImage(fontMetrics.stringWidth(cotacao), n + 2);
        final Graphics graphics = image.getGraphics();
        graphics.setFont(font);
        final StringBuffer sb = new StringBuffer();
        if (this.mostraNome) {
            sb.append((this.nomeAtivo != null) ? this.nomeAtivo : "");
            sb.append(" ");
        }
        if (this.mostraCodigo) {
            sb.append("(");
            sb.append((this.codAtivo != null) ? this.codAtivo.toUpperCase() : "");
            sb.append(")");
            sb.append(" ");
        }
        sb.append(this.dfValor.format(this.ult));
        sb.append(" ");
        final String string = sb.toString();
        graphics.drawString(string, 0, n);
        final StringBuffer sb2 = new StringBuffer();
        boolean b = true;
        int n2;
        if (!this.horaInvalida) {
            graphics.setColor((this.var > 0.0f) ? this.corVarPos : ((this.var < 0.0f) ? this.corVarNeg : this.corSemVar));
            sb2.append((this.var > 0.0f) ? "+" : "");
            sb2.append(this.dfVar.format(this.var));
            sb2.append(this.varPorcento ? "%" : "");
            sb2.append(" ");
            n2 = fontMetrics.stringWidth(string);
            graphics.drawString(sb2.toString(), n2, n);
        }
        else {
            b = false;
            graphics.setColor(this.corSemVar);
            sb2.append(this.dtf.format(this.dataUlt.getData().getTime()));
            n2 = fontMetrics.stringWidth(string);
            graphics.drawString(sb2.toString(), n2, n);
        }
        graphics.setColor(this.corSemVar);
        int n3 = n2 + fontMetrics.stringWidth(sb2.toString());
        if (this.mostraHora && b) {
            graphics.setColor(this.corFonteHora);
            graphics.drawString(this.hora, n3, n);
            n3 += fontMetrics.stringWidth(this.hora);
        }
        graphics.setColor(this.corSemVar);
        graphics.drawString(this.separador, n3, n);
        graphics.dispose();
        frame.dispose();
        return image;
    }
    
    public void setFec(final float n) {
    }
    
    public void setMax(final float n) {
    }
    
    public void setMin(final float n) {
    }
    
    public void setLegFec(final String s) {
    }
    
    public void setLegMax(final String s) {
    }
    
    public void setLegMin(final String s) {
    }
    
    public void setLegUlt(final String s) {
    }
    
    public void setLegVarDia(final String s) {
    }
    
    public void setCampos(final Vector vector) {
    }
    
    public void setCorLegendas(final Color color) {
    }
    
    public void setVar30(final float n) {
    }
    
    public void setLegVar30(final String s) {
    }
}
