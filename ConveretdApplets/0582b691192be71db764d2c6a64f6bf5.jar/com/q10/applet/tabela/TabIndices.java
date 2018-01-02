// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.applet.tabela;

import java.util.Observable;
import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Panel;
import com.q10.applet.conexao.ConServlet;
import java.util.Calendar;
import java.text.DecimalFormat;
import com.q10.comum.cot.Indice;
import com.q10.util.Formata;
import java.util.Enumeration;
import java.util.Date;
import java.applet.Applet;
import com.q10.util.TabFile;
import java.util.StringTokenizer;
import com.q10.util.Tool;
import java.awt.Label;
import java.util.Properties;
import java.awt.Color;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import com.q10.applet.bean.Tabela;
import com.q10.comum.flags.AppletFlags;
import com.q10.applet.BaseQ10;

public class TabIndices extends BaseQ10 implements AppletFlags, Runnable
{
    private static final String versao = "Tabela \u00cdndices - Vers\u00e3o: 2.01.34";
    private static final int TIPOS_POSSIVEIS = 3;
    private String[] nomesMeses;
    private Tabela tabela;
    private String[] nomesAtivos;
    private String[] codAtivos;
    private Hashtable ativos;
    private Hashtable tipos;
    private GregorianCalendar gc;
    public Color linhaPar;
    public Color linhaImpar;
    public Color linhaCabecalho;
    public Color corFonteCabe\u00e7alho;
    public Color corFonteTabela;
    public Color corFonteColunaMeses;
    private int nLinhas;
    private int nColunas;
    private String arqLinhas;
    private Properties quaisLinhas;
    private boolean pegaMeses;
    private boolean calculaAno;
    private boolean calcula12Meses;
    private Label lbFonte1;
    private Label lbFonte2;
    Hashtable fontes;
    private String[] fonte;
    private String textoFonte1;
    private String textoFonte2;
    private boolean mostraFonte;
    private Color corFundoFonte;
    private Color corFonteFonte;
    private Hashtable ativosCategorias;
    private String dataRef;
    
    public void init() {
        super.init();
        System.out.println("Tabela \u00cdndices - Vers\u00e3o: 2.01.34");
        this.linhaPar = Tool.cor(this.getParameter("corLinhaPar"));
        this.linhaImpar = Tool.cor(this.getParameter("corLinhaImpar"));
        this.linhaCabecalho = Tool.cor(this.getParameter("corLinhaCabecalho"));
        this.corFonteCabe\u00e7alho = Tool.cor(this.getParameter("corFonteCabecalho"));
        this.corFonteTabela = Tool.cor(this.getParameter("corFonteTabela"));
        this.corFonteColunaMeses = Tool.cor(this.getParameter("corFonteColunaMeses"));
        final String parameter = this.getParameter("alinhaValores");
        this.arqLinhas = this.getParameter("arqLinhas");
        this.gc = new GregorianCalendar();
        final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("nomesAtivos"), ",");
        this.nomesAtivos = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < this.nomesAtivos.length; ++i) {
            this.nomesAtivos[i] = stringTokenizer.nextToken();
        }
        this.ativos = new Hashtable();
        StringTokenizer stringTokenizer2 = new StringTokenizer(this.getParameter("codAtivos"), ",");
        this.codAtivos = new String[stringTokenizer2.countTokens()];
        for (int j = 0; j < this.codAtivos.length; ++j) {
            this.codAtivos[j] = stringTokenizer2.nextToken();
            this.ativos.put(this.codAtivos[j], this.nomesAtivos[j]);
        }
        this.ativosCategorias = new Hashtable();
        final String parameter2 = this.getParameter("categoriasAtivos");
        if (parameter2 != null) {
            stringTokenizer2 = new StringTokenizer(parameter2, ",");
        }
        for (int k = 0; k < this.codAtivos.length; ++k) {
            this.ativosCategorias.put(this.codAtivos[k].toUpperCase(), (parameter2 != null) ? stringTokenizer2.nextToken() : "null");
        }
        if (this.arqLinhas != null) {
            this.tipos = new Hashtable(3);
            this.quaisLinhas = new TabFile(this.getCodeBase(), String.valueOf(this.arqLinhas) + ".properties").getTabFile();
            final Enumeration<String> keys = (Enumeration<String>)this.quaisLinhas.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                final String nextToken = new StringTokenizer(this.quaisLinhas.getProperty(s), ";").nextToken();
                this.tipos.put(s, nextToken);
                if (nextToken.equalsIgnoreCase("MESES")) {
                    this.nLinhas += 12;
                }
                else if (nextToken.equalsIgnoreCase("12M")) {
                    ++this.nLinhas;
                }
                else {
                    if (!nextToken.equalsIgnoreCase("NOANO")) {
                        continue;
                    }
                    ++this.nLinhas;
                }
            }
        }
        final String parameter3 = this.getParameter("fonte");
        if (parameter3 != null) {
            this.fontes = new Hashtable();
            final Properties tabFile = new TabFile(this.getCodeBase(), String.valueOf(parameter3) + ".properties").getTabFile();
            final Enumeration<String> keys2 = ((Hashtable<String, V>)tabFile).keys();
            int n = 0;
            while (keys2.hasMoreElements()) {
                final String s2 = keys2.nextElement();
                if (s2.equalsIgnoreCase("dataRef")) {
                    this.dataRef = tabFile.getProperty(s2);
                }
                else {
                    final StringTokenizer stringTokenizer3 = new StringTokenizer(tabFile.getProperty(s2), ";");
                    this.fontes.put(s2, stringTokenizer3.nextToken());
                    this.fonte[n++] = stringTokenizer3.nextToken();
                }
            }
            this.mostraFonte = true;
        }
        this.corFonteFonte = Tool.cor(this.getParameter("corFonteFonte"));
        this.corFundoFonte = Tool.cor(this.getParameter("corFundoFonte"));
        this.initComponents();
        if (this.mostraFonte) {
            this.lbFonte1.setFont(Tool.setaFonte(this, "nomeFonte1", "tamFonte1", "estiloFonte1"));
            if (this.lbFonte2 != null) {
                this.lbFonte2.setFont(Tool.setaFonte(this, "nomeFonte2", "tamFonte2", "estiloFonte2"));
            }
        }
        final int n2 = "ESQUERDA".equalsIgnoreCase(parameter) ? 0 : ("DIREITA".equalsIgnoreCase(parameter) ? 2 : 1);
        for (int l = 1; l < this.tabela.getColunas(); ++l) {
            this.tabela.alinhaColuna(n2, l);
        }
        this.tabela.mudaFonteLinha(Tool.setaFonte(this, "fonteCabecalho", "tamFonteCabecalho", "estiloFonteCabecalho"), 0);
        try {
            for (int n3 = 1; n3 < this.tabela.getLinhas(); ++n3) {
                this.tabela.mudaFonteLinha(Tool.setaFonte(this, "fonteTabela", "tamFonteTabela", "estiloFonteTabela"), n3);
                for (int n4 = 1; n4 < this.tabela.getColunas(); ++n4) {
                    this.tabela.mudaCorFonteLabel(n3, n4, this.corFonteTabela);
                }
            }
        }
        catch (NullPointerException ex) {}
        this.tabela.setCorFonteColuna(this.corFonteColunaMeses, 0);
        this.tabela.mudaFonteColuna(Tool.setaFonte(this, "fonteColunaMeses", "tamFonteColunaMeses", "estiloFonteColunaMeses"), 0);
        int n5 = 1;
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date(System.currentTimeMillis()));
        for (int n6 = 1; n6 < this.tabela.getLinhas(); ++n6) {
            final String s3 = new String("linha" + n5);
            if ("12M".equalsIgnoreCase((String)this.tipos.get(s3))) {
                this.tabela.escreve(n6, 0, "12 meses");
                this.calcula12Meses = true;
                ++n5;
            }
            else if ("NOANO".equalsIgnoreCase((String)this.tipos.get(s3))) {
                this.tabela.escreve(n6, 0, "no ano");
                this.calculaAno = true;
                ++n5;
            }
            else if ("MESES".equalsIgnoreCase((String)this.tipos.get(s3))) {
                final int n7 = gregorianCalendar.get(2) - 1;
                if (gregorianCalendar.get(2) > 0) {
                    for (int n8 = n7; n8 >= 0; --n8) {
                        this.tabela.escreve(n6++, 0, this.nomesMeses[n8]);
                    }
                }
                for (int n9 = this.nomesMeses.length - 1; n9 > n7; --n9) {
                    this.tabela.escreve(n6++, 0, this.nomesMeses[n9]);
                }
                ++n5;
                this.pegaMeses = true;
                --n6;
            }
            else {
                ++n5;
            }
        }
        this.tabela.setCorFundoLinha(this.linhaCabecalho, 0);
        this.tabela.setCorFonteLinha(this.corFonteCabe\u00e7alho, 0);
        for (int n10 = 1; n10 < this.tabela.getLinhas(); ++n10) {
            for (int n11 = 1; n11 < this.tabela.getColunas(); ++n11) {
                this.tabela.escreve(n10, n11, "-");
                this.tabela.escreve(0, n11, this.nomesAtivos[n11 - 1]);
                this.tabela.setCorFundoLinha((n10 % 2 == 0) ? this.linhaPar : this.linhaImpar, n10);
            }
        }
    }
    
    public void atualizaTela() {
        try {
            final DecimalFormat formataDecimal = Formata.formataDecimal(2);
            final Hashtable buscaIndices = this.buscaIndices(this.codAtivos, new String[] { "var_12_meses", "var_ano" }, this.pegaMeses);
            if (this.dataRef != null) {
                this.dataRef = String.valueOf(this.dataRef) + " " + this.buscaDataServidor();
                this.tabela.escreve(0, 0, this.dataRef);
            }
            int n = 1;
            final int procuraMesMaisRecente = this.procuraMesMaisRecente(buscaIndices);
            for (int i = 1; i <= this.codAtivos.length; ++i) {
                this.msg(this.codAtivos[i - 1]);
                for (int j = 1; j < this.tabela.getLinhas(); ++j) {
                    final Indice indice = buscaIndices.get(this.codAtivos[i - 1].toUpperCase());
                    if (indice != null) {
                        final String s = new String("linha" + n);
                        if ("12M".equalsIgnoreCase((String)this.tipos.get(s))) {
                            if (((String)this.ativosCategorias.get(indice.getCodIndice())).equalsIgnoreCase("t")) {
                                this.tabela.escreve(j, i, " - ");
                            }
                            else {
                                this.tabela.escreve(j, i, formataDecimal.format(indice.getVariacao12Meses()));
                            }
                            ++n;
                        }
                        else if ("NOANO".equalsIgnoreCase((String)this.tipos.get(s))) {
                            if (((String)this.ativosCategorias.get(indice.getCodIndice())).equalsIgnoreCase("t")) {
                                this.tabela.escreve(j, i, " - ");
                            }
                            else {
                                this.tabela.escreve(j, i, formataDecimal.format(indice.getVariacaoAno()));
                            }
                            ++n;
                        }
                        else if ("MESES".equalsIgnoreCase((String)this.tipos.get(s))) {
                            final float[] ultimosMeses = indice.getUltimosMeses();
                            final boolean b = indice.getMes() == this.gc.get(2) - 2;
                            if (indice.getMes() == this.gc.get(2) - 2 || b) {
                                int n2 = j;
                                final int n3 = (procuraMesMaisRecente != -1) ? procuraMesMaisRecente : this.gc.get(2);
                                this.msg("mesMaisRecente: " + procuraMesMaisRecente);
                                this.msg("mes escolhido: " + n3);
                                if (n3 >= 0) {
                                    for (int k = n3; k >= 0; --k) {
                                        this.tabela.escreve(n2++, 0, this.nomesMeses[k]);
                                    }
                                }
                                for (int l = this.nomesMeses.length - 1; l > n3; --l) {
                                    this.tabela.escreve(n2++, 0, this.nomesMeses[l]);
                                }
                            }
                            else {
                                int n4 = j;
                                if (procuraMesMaisRecente >= 0) {
                                    for (int n5 = procuraMesMaisRecente; n5 >= 0; --n5) {
                                        this.tabela.escreve(n4++, 0, this.nomesMeses[n5]);
                                    }
                                }
                                for (int n6 = this.nomesMeses.length - 1; n6 > procuraMesMaisRecente; --n6) {
                                    this.tabela.escreve(n4++, 0, this.nomesMeses[n6]);
                                }
                            }
                            if ((procuraMesMaisRecente == 0 && indice.getMes() > procuraMesMaisRecente) || indice.getMes() < procuraMesMaisRecente) {
                                ++j;
                            }
                            for (int n7 = 0; n7 < ultimosMeses.length; ++n7) {
                                this.tabela.escreve(j, i, formataDecimal.format(ultimosMeses[n7]));
                                if (++j >= this.tabela.getLinhas()) {
                                    break;
                                }
                            }
                            ++n;
                            j += 12 - ultimosMeses.length - 1;
                        }
                        else {
                            ++n;
                        }
                    }
                }
                n = 1;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private int procuraMesMaisRecente(final Hashtable hashtable) {
        Calendar calendar = null;
        for (int i = 0; i < this.codAtivos.length; ++i) {
            try {
                final GregorianCalendar gregorianCalendar = new GregorianCalendar(hashtable.get(this.codAtivos[i].toUpperCase()).getAno(), hashtable.get(this.codAtivos[i].toUpperCase()).getMes(), 1);
                if (calendar == null || gregorianCalendar.getTime().after(calendar.getTime())) {
                    calendar = gregorianCalendar;
                }
            }
            catch (NullPointerException ex) {
                System.out.println("Sem dados para: " + this.codAtivos[i]);
            }
        }
        if (calendar == null) {
            return -1;
        }
        return calendar.get(2);
    }
    
    private Hashtable buscaIndices(final String[] array, final String[] array2, final boolean b) {
        return this.parser(this.fazRequisicao(this.montaParametros(array, array2, b)), b);
    }
    
    private Hashtable parser(final byte[] array, final boolean b) {
        final Hashtable<String, Indice> hashtable = new Hashtable<String, Indice>();
        final StringTokenizer stringTokenizer = new StringTokenizer(new String(array), ";");
        while (stringTokenizer.hasMoreTokens()) {
            try {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), ":");
                final Indice indice = new Indice(stringTokenizer2.nextToken().toUpperCase());
                indice.setMes(new Integer(stringTokenizer2.nextToken()));
                indice.setAno(new Integer(stringTokenizer2.nextToken()));
                indice.setVariacaoAno(new Float(stringTokenizer2.nextToken()));
                indice.setVariacao12Meses(new Float(stringTokenizer2.nextToken()));
                if (b) {
                    final float[] ultimosMeses = new float[stringTokenizer2.countTokens()];
                    for (int i = 0; i < ultimosMeses.length; ++i) {
                        ultimosMeses[i] = new Float(stringTokenizer2.nextToken());
                    }
                    indice.setUltimosMeses(ultimosMeses);
                }
                hashtable.put(indice.getCodIndice(), indice);
            }
            catch (NumberFormatException ex) {
                System.out.println(ex);
            }
        }
        return hashtable;
    }
    
    private byte[] fazRequisicao(final Hashtable parametros) {
        byte[] recebeResposta = null;
        try {
            final ConServlet conServlet = new ConServlet();
            conServlet.abreConexao("http://" + this.getCodeBase().getHost() + "/finansite2/servlet/request");
            conServlet.enviaComando(conServlet.setParametros(parametros));
            recebeResposta = conServlet.recebeResposta();
            conServlet.terminaConexao();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return recebeResposta;
    }
    
    private Hashtable montaParametros(final String[] array, final String[] array2, final boolean b) {
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        hashtable.put("tipo", "applet");
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(array[i]).append(";");
        }
        hashtable.put("codigos", sb.toString());
        final StringBuffer sb2 = new StringBuffer();
        for (int j = 0; j < array.length; ++j) {
            sb2.append(this.ativosCategorias.get(array[j].toUpperCase())).append(";");
        }
        hashtable.put("categorias", sb2.toString());
        final StringBuffer sb3 = new StringBuffer();
        for (int k = 0; k < array2.length; ++k) {
            if (array2[k] != null) {
                sb3.append(array2[k]).append(";");
            }
        }
        hashtable.put("datas_var", sb3.toString());
        hashtable.put("mostra12Meses", "true");
        hashtable.put("controle", "indices");
        if (this.getParameter("licenca") != null) {
            hashtable.put("licenca", this.getParameter("licenca"));
        }
        hashtable.put("origem", this.getDocumentBase().toString());
        return hashtable;
    }
    
    private void initComponents() {
        final Panel panel = new Panel();
        this.tabela = new Tabela(this.nLinhas + 1, this.codAtivos.length + 1);
        panel.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, -1, 0, -1);
        gridBagConstraints.fill = 1;
        ((GridBagLayout)panel.getLayout()).setConstraints(this.tabela, gridBagConstraints);
        panel.add(this.tabela);
        this.setLayout(new BorderLayout());
        this.add(panel, "Center");
        if (this.mostraFonte) {
            final Panel panel2 = new Panel(new FlowLayout(2, 0, 1));
            panel2.setBackground(this.corFundoFonte);
            panel2.setForeground(this.corFonteFonte);
            (this.lbFonte1 = new Label()).setText(this.fontes.get("fonte1"));
            this.lbFonte1.setAlignment(2);
            this.lbFonte1.addMouseListener(new MouseAdapter() {
                public void mouseEntered(final MouseEvent mouseEvent) {
                    TabIndices.this.mudaMouse(true);
                }
                
                public void mouseExited(final MouseEvent mouseEvent) {
                    TabIndices.this.mudaMouse(false);
                }
                
                public void mouseClicked(final MouseEvent mouseEvent) {
                    TabIndices.this.abreJanela((Label)mouseEvent.getComponent());
                }
            });
            panel2.add(this.lbFonte1);
            if (this.fontes.get("fonte2") != null) {
                (this.lbFonte2 = new Label()).setText(this.fontes.get("fonte2"));
                this.lbFonte2.setAlignment(2);
                this.lbFonte2.addMouseListener(new MouseAdapter() {
                    public void mouseEntered(final MouseEvent mouseEvent) {
                        TabIndices.this.mudaMouse(true);
                    }
                    
                    public void mouseExited(final MouseEvent mouseEvent) {
                        TabIndices.this.mudaMouse(false);
                    }
                    
                    public void mouseClicked(final MouseEvent mouseEvent) {
                        TabIndices.this.abreJanela((Label)mouseEvent.getComponent());
                    }
                });
                panel2.add(this.lbFonte2);
            }
            this.add(panel2, "South");
        }
    }
    
    private void abreJanela(final Label label) {
        Tool.abreJanela(this, (label == this.lbFonte1) ? this.fonte[0] : this.fonte[1]);
    }
    
    public void mudaMouse(final boolean b) {
        this.setCursor(new Cursor(b ? 12 : 0));
    }
    
    public void run() {
        try {
            this.atualizaTela();
        }
        catch (Exception ex) {
            try {
                Thread.sleep(60000L);
                this.atualizaTela();
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void update(final Observable observable, final Object o) {
    }
    
    private String buscaDataServidor() {
        byte[] recebeResposta = null;
        try {
            final ConServlet conServlet = new ConServlet();
            conServlet.abreConexao("http://" + this.getCodeBase().getHost() + "/finansite2/servlet/data");
            conServlet.enviaComando(conServlet.setParametros(new Hashtable()));
            recebeResposta = conServlet.recebeResposta();
            conServlet.terminaConexao();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return new String(recebeResposta);
    }
    
    public TabIndices() {
        this.nomesMeses = new String[] { "janeiro", "fevereiro", "mar\u00e7o", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro" };
        this.pegaMeses = false;
        this.calculaAno = false;
        this.calcula12Meses = false;
        this.fonte = new String[2];
        this.mostraFonte = false;
    }
}
