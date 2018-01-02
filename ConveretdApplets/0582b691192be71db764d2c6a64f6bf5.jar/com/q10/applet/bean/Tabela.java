// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.applet.bean;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import com.q10.util.Link;
import java.applet.Applet;
import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Component;
import java.util.Hashtable;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Label;
import java.io.Serializable;
import java.awt.Panel;

public class Tabela extends Panel implements Serializable
{
    protected int linhas;
    protected int colunas;
    private String texto;
    protected Label[][] lbMatriz;
    private GridBagConstraints gbc;
    private GridBagLayout gl;
    private String tipo;
    private Color corFonte;
    private String funcao;
    private Color corLink;
    private Color corBkp;
    private Hashtable me;
    private String[] links;
    private boolean inverteArgs;
    private boolean bTemMaisParametros;
    private String parametroExtra;
    private boolean bMandaTexto;
    
    protected int[] getPosicao(final Component component) {
        final int[] array = new int[2];
        for (int i = 0; i < this.linhas; ++i) {
            for (int j = 0; j < this.colunas; ++j) {
                if (component.equals(this.lbMatriz[i][j])) {
                    array[0] = i;
                    array[1] = j;
                    break;
                }
            }
        }
        return array;
    }
    
    public synchronized void setLinhas(final int linhas) {
        this.linhas = linhas;
        this.reseta();
    }
    
    public int getLinhas() {
        return this.linhas;
    }
    
    public synchronized void setColunas(final int colunas) {
        this.colunas = colunas;
        this.reseta();
    }
    
    public int getColunas() {
        return this.colunas;
    }
    
    private void mudaMatriz() {
        this.esvazia();
        this.lbMatriz = new Label[this.linhas][this.colunas];
        for (int i = 0; i < this.linhas; ++i) {
            for (int j = 0; j < this.colunas; ++j) {
                this.lbMatriz[i][j] = QLabelFactory.getQLabel(this.tipo, this.texto);
            }
        }
    }
    
    public void esvazia() {
        if (this.getComponentCount() > 0) {
            this.removeAll();
        }
        this.lbMatriz = null;
    }
    
    private void montaTabela() {
        try {
            this.gbc.fill = 1;
            for (int i = 0; i < this.linhas; ++i) {
                this.gbc.gridy = i;
                for (int j = 0; j < this.colunas; ++j) {
                    this.gbc.gridx = j;
                    this.gl.setConstraints(this.lbMatriz[i][j], this.gbc);
                    this.add(this.lbMatriz[i][j]);
                }
            }
        }
        catch (Exception ex) {
            System.out.println("com.q10.beans.Tabela: " + ex);
        }
    }
    
    private void reseta() {
        if (this.getComponentCount() > 0) {
            this.removeAll();
        }
        this.invalidate();
        this.mudaMatriz();
        this.montaTabela();
        this.validate();
        this.doLayout();
        this.setCorFundo(Color.white);
        this.setCorFonte(Color.black);
        this.ajustaTamanho();
    }
    
    public Tabela(final int linhas, final int colunas, final String tipo) {
        super(new GridBagLayout());
        this.linhas = 1;
        this.colunas = 1;
        this.texto = "";
        this.tipo = "QLabel";
        this.funcao = "abreJanela";
        this.corLink = Color.blue;
        this.tipo = tipo;
        this.gbc = new GridBagConstraints();
        this.gbc.fill = 1;
        this.gbc.weightx = 1.0;
        this.gbc.weighty = 1.0;
        this.linhas = linhas;
        this.colunas = colunas;
        this.gl = (GridBagLayout)this.getLayout();
        this.reseta();
    }
    
    public Tabela(final int n, final int n2) {
        this(n, n2, "QLabel");
    }
    
    public Tabela() {
        this(1, 1);
    }
    
    public void escreve(final int n, final int n2, final String text) {
        this.tipo = this.tipo;
        this.lbMatriz[n][n2].setText(text);
        this.lbMatriz[n][n2].repaint();
    }
    
    public String le(final int n, final int n2) {
        return this.lbMatriz[n][n2].getText();
    }
    
    public void alinhaLabel(final int n, final int n2, final int alignment) {
        this.lbMatriz[n][n2].setAlignment(alignment);
    }
    
    public void mudaCorFundoLabel(final int n, final int n2, final Color background) {
        this.lbMatriz[n][n2].setBackground(background);
    }
    
    public void mudaCorFonteLabel(final int n, final int n2, final Color foreground) {
        this.lbMatriz[n][n2].setForeground(foreground);
    }
    
    public void mudaFonteLinha(final Font font, final int n) {
        for (int i = 0; i < this.colunas; ++i) {
            this.lbMatriz[n][i].setFont(font);
        }
    }
    
    public void mudaFonteColuna(final Font font, final int n) {
        for (int i = 0; i < this.linhas; ++i) {
            this.lbMatriz[i][n].setFont(font);
        }
    }
    
    public void setCorFundo(final Color background) {
        for (int i = 0; i < this.linhas; ++i) {
            for (int j = 0; j < this.colunas; ++j) {
                this.lbMatriz[i][j].setBackground(background);
            }
        }
    }
    
    public void setCorFonte(final Color color) {
        this.corFonte = color;
        for (int i = 0; i < this.linhas; ++i) {
            for (int j = 0; j < this.colunas; ++j) {
                this.lbMatriz[i][j].setForeground(color);
            }
        }
    }
    
    public void setCorFundoLinha(final Color background, final int n) {
        for (int i = 0; i < this.colunas; ++i) {
            this.lbMatriz[n][i].setBackground(background);
        }
    }
    
    public void setCorFonteLinha(final Color foreground, final int n) {
        for (int i = 0; i < this.colunas; ++i) {
            this.lbMatriz[n][i].setForeground(foreground);
        }
    }
    
    public void setCorFundoColuna(final Color background, final int n) {
        for (int i = 0; i < this.linhas; ++i) {
            this.lbMatriz[i][n].setBackground(background);
        }
    }
    
    public void setCorFonteColuna(final Color foreground, final int n) {
        for (int i = 0; i < this.linhas; ++i) {
            this.lbMatriz[i][n].setForeground(foreground);
        }
    }
    
    public void alinhaColuna(final int alignment, final int n) {
        for (int i = 0; i < this.linhas; ++i) {
            this.lbMatriz[i][n].setAlignment(alignment);
        }
    }
    
    public void alinhaLinha(final int alignment, final int n) {
        for (int i = 0; i < this.colunas; ++i) {
            this.lbMatriz[n][i].setAlignment(alignment);
        }
    }
    
    public void alinhaTodos(final int alignment) {
        for (int i = 0; i < this.linhas; ++i) {
            for (int j = 0; j < this.colunas; ++j) {
                this.lbMatriz[i][j].setAlignment(alignment);
            }
        }
    }
    
    public void ajustaTamanho() {
        final Dimension preferredSize = this.getPreferredSize();
        this.setSize(preferredSize.width, preferredSize.height);
        final Container parent = this.getParent();
        if (parent != null) {
            parent.invalidate();
            parent.doLayout();
        }
    }
    
    public Image getLinha(final int n) {
        final Frame frame = new Frame();
        frame.addNotify();
        final Image image = frame.createImage(this.getSize().width, this.getSize().height / this.linhas);
        final Graphics graphics = image.getGraphics();
        int n2 = 0;
        for (int i = 0; i < 1; ++i) {
            frame.add(this.lbMatriz[n][i]);
            this.lbMatriz[n][i].setSize(this.getSize().width / this.colunas, this.getSize().height / this.linhas);
            final Image image2 = this.lbMatriz[n][i].createImage(this.getSize().width, this.getSize().height / this.linhas);
            this.lbMatriz[n][i].printAll(image2.getGraphics());
            graphics.drawImage(image2, n2, 0, this);
            this.lbMatriz[n][i].printAll(graphics);
            n2 += this.lbMatriz[n][i].getSize().width;
        }
        frame.removeNotify();
        return image;
    }
    
    public Image getColuna(final int n) {
        final Image image = this.createImage(this.getSize().width / this.colunas, this.getSize().height);
        final Graphics graphics = image.getGraphics();
        final Image[] array = new Image[this.linhas];
        int n2 = 0;
        for (int i = 0; i < this.linhas; ++i) {
            array[i] = this.createImage(this.lbMatriz[i][n].getSize().width, this.lbMatriz[i][n].getSize().height);
            graphics.drawImage(image, 0, n2, this);
            n2 += this.lbMatriz[i][n].getSize().height;
        }
        return image;
    }
    
    public void setFuncao(final String funcao) {
        this.funcao = funcao;
    }
    
    public void setCorLink(final Color corLink) {
        this.corLink = corLink;
    }
    
    public void setLink(final int n, final int n2) {
        if (this.me == null) {
            this.me = new Hashtable();
        }
        final MouseEvents mouseEvents = this.me.get(this.lbMatriz[n][n2]);
        if (mouseEvents != null) {
            this.lbMatriz[n][n2].removeMouseListener(mouseEvents);
        }
        final MouseEvents mouseEvents2 = new MouseEvents();
        this.lbMatriz[n][n2].addMouseListener(mouseEvents2);
        this.me.put(this.lbMatriz[n][n2], mouseEvents2);
    }
    
    private void mudaMouse(final boolean b) {
        this.setCursor(new Cursor(b ? 12 : 0));
        this.repaint();
    }
    
    public void setLinks(final String[] links) {
        this.links = links;
    }
    
    public void setInverteArgs(final boolean inverteArgs) {
        this.inverteArgs = inverteArgs;
    }
    
    private void chamaLink(final IQLabel iqLabel) {
        final Link link = new Link((this.links == null) ? iqLabel.getTexto() : this.links[this.achaIndice(iqLabel)], (Applet)this.getParent().getParent());
        if (this.bMandaTexto) {
            link.setParametros(new String[] { this.inverteArgs ? iqLabel.getTexto() : this.links[this.achaIndice(iqLabel)], this.inverteArgs ? this.links[this.achaIndice(iqLabel)] : iqLabel.getTexto() });
        }
        else if (this.bTemMaisParametros) {
            link.setParametros(new String[] { (this.links == null) ? iqLabel.getTexto() : this.links[this.achaIndice(iqLabel)], this.parametroExtra });
        }
        link.disparaLink(this.funcao);
    }
    
    public void setTemMaisParametros(final boolean bTemMaisParametros) {
        this.bTemMaisParametros = bTemMaisParametros;
    }
    
    public void setParametroExtra(final String parametroExtra) {
        this.parametroExtra = parametroExtra;
    }
    
    public void setMandaTexto(final boolean bMandaTexto) {
        this.bMandaTexto = bMandaTexto;
    }
    
    private int achaIndice(final IQLabel iqLabel) {
        for (int i = 0; i < this.colunas; ++i) {
            int j = 0;
            while (j < this.linhas) {
                if (iqLabel.equals(this.lbMatriz[j][i])) {
                    if (this.linhas > this.links.length) {
                        return j - 1;
                    }
                    return j;
                }
                else {
                    ++j;
                }
            }
        }
        return 0;
    }
    
    public void setImagem(final Image image, final int n, final int n2) {
        ((IQLabel)this.lbMatriz[n][n2]).setImage(image);
    }
    
    public void setDistImgTxt(final int distImgTxt, final int n, final int n2) {
        ((IQLabel)this.lbMatriz[n][n2]).setDistImgTxt(distImgTxt);
    }
    
    public void setVisible(final boolean visible, final int n) {
        for (int i = 0; i < this.linhas; ++i) {
            this.lbMatriz[i][n].setVisible(visible);
        }
    }
    
    static /* synthetic */ void access$2(final Tabela tabela, final Color corBkp) {
        tabela.corBkp = corBkp;
    }
    
    class MouseEvents extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent mouseEvent) {
            Tabela.this.chamaLink((IQLabel)mouseEvent.getComponent());
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            Tabela.access$2(Tabela.this, mouseEvent.getComponent().getForeground());
            mouseEvent.getComponent().setForeground(Tabela.this.corLink);
            Tabela.this.mudaMouse(true);
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            mouseEvent.getComponent().setForeground(Tabela.this.corBkp);
            Tabela.this.mudaMouse(false);
        }
    }
}
