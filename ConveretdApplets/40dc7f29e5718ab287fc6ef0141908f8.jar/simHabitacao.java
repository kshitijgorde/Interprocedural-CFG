import java.awt.MediaTracker;
import java.awt.Cursor;
import netscape.javascript.JSObject;
import java.awt.event.ItemEvent;
import java.util.Enumeration;
import java.awt.event.MouseEvent;
import java.util.StringTokenizer;
import java.awt.TextField;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;
import java.text.DecimalFormat;
import java.awt.CheckboxGroup;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Dimension;
import java.util.Properties;
import bbpi.dnc.simstats.Record;
import bbpi.dnc.simstats.StatsManager;
import java.awt.Checkbox;
import java.awt.FontMetrics;
import java.util.Hashtable;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Choice;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ItemListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class simHabitacao extends Applet implements ItemListener, MouseListener, MouseMotionListener
{
    private String version;
    private String inValorResidual;
    private boolean hideTAN_Seg;
    private boolean started;
    private Choice choiceResidual;
    private Label labelResidual;
    public static final int REGIME_GERAL = 0;
    public static final int REGIME_EMIGRANTE = 1;
    public static final int REGIME_BONIFICADO = 2;
    public static final int REGIME_ESTRANGEIRO = 3;
    int regime;
    int modalidade;
    int tipoPlano;
    int finalidade;
    int prazoCarencia;
    boolean carencia;
    public static final int FINALIDADE_AQUISICAO = 1;
    public static final int FINALIDADE_TRANSFERENCIA = 2;
    public static final int FINALIDADE_CONSTRUCAO = 3;
    public static final int FINALIDADE_OBRAS = 4;
    public static final int FINALIDADE_LCPPURA = 5;
    public static final int TIPOPLANO_PREST_CONST = 1;
    public static final int TIPOPLANO_VALOR_RESIDUAL = 2;
    public static final int TIPOPLANO_PREST_MISTAS = 3;
    public static final int MODALIDADE_VARIAVEL = 1;
    public static final int MODALIDADE_TAXA_FIXA = 2;
    public static final int MODALIDADE_MG3 = 3;
    public static final int MODALIDADE_MG5 = 4;
    public static final int MODALIDADE_PRESTACAO_CONSTANTE = 5;
    public static final int MODALIDADE_VARIAVEL_SPC = 6;
    private double CE_limiteFinanciamento;
    private double CE_spreadUnico;
    private Matriz CE_matriz;
    private boolean condicoesEspeciais;
    private static final int CLIENT_LAYOUT_DEFAULT = 0;
    private static final int CLIENT_LAYOUT_SAPO = 1;
    private static final int CLIENT_LAYOUT_INGLES = 2;
    private static final int CLIENT_LAYOUT_ALLIANZ_MEDIADORAS = 3;
    private static final int NOVO_CLIENT_LAYOUT_SAPO = 4;
    private static final int NOVO_CLIENT_LAYOUT_IMOB = 5;
    private static final int CLIENT_LAYOUT_CARENCIA = 6;
    private static final int CLIENT_LAYOUT_EXPRESSO = 7;
    private static final int ajusteLayoutImob = 30;
    private int clientLayout;
    private boolean layoutIngles;
    private boolean debug;
    private boolean localMode;
    boolean eFixa;
    public final int SIMULADORDEFAULT = 1;
    public final int SONAE = 2;
    public final int MICROSOFT = 3;
    public final int GRELHA_A = 4;
    public final int GRELHA_B = 5;
    public final int SNPVAC = 6;
    public final int NOVARTIS = 7;
    public final int GRELHA_C = 8;
    public final int MEDIADORAS = 9;
    public final int SIMULADOR_GRELHA_ESPECIAL_GENERICO = 10;
    public final int SIMULADOR_GRELHA_ESPECIAL_PROTOCOLO_SPAC = 11;
    public final int SIMULADOR_NOVA_GRELHA_2004_12_23 = 12;
    public final int SIMULADOR_BONIFICADO = 13;
    public final int SIMULADOR_REGIME_GERAL_SPC = 14;
    public final int SIMULADOR_PROTOCOLO_MAXIMO = 14;
    private int simulador;
    static final int BAR_INDEX = 0;
    static final int PIN_INDEX = 1;
    static final int BG_INDEX = 2;
    static final int MAX_IMAGES = 3;
    static int BAR_WIDTH;
    static int BAR_HEIGHT;
    static int PIN_WIDTH;
    static int PIN_HEIGHT;
    static int WIDTH;
    static int HEIGHT;
    private double EMPRESTIMO_MIN;
    private double EMPRESTIMO_MAX;
    private double EMPRESTIMO_INI;
    private double EMPRESTIMO_JUMP;
    private double EMPRESTIMOLCP_JUMP;
    private double EMPRESTIMO_FINANCIAMENTO;
    private double EMPRESTIMO_FINANCIAMENTO_LCP;
    private double PRAZO_MIN;
    private double PRAZO_MIN_TAXAS;
    private double PRAZO_JUMP;
    private double PRAZO_MAX;
    private double PRAZO_LIMITE;
    private double PRAZO_INI;
    private double BONIFICACAOSPREAD;
    private double IMPOSTO_SELO;
    private double EMPRESTIMO_COMPLEMENTAR_INI;
    private double EMPRESTIMO_COMPLEMENTAR_MIN;
    private double EMPRESTIMO_COMPLEMENTAR_MAX;
    private double PRAZOCARENCIA_MAX;
    private double PRAZOCARENCIA_MIN;
    private double PRAZOCARENCIA_LIMITE;
    private double PRESTACAO_MIN;
    private s_sliderpt sliderEmprestimo;
    private s_sliderpt sliderEmprestimoLCP;
    private s_sliderpt sliderPrazo;
    private s_sliderpt sliderPrazoCarencia;
    private s_sliderpt sliderPrestacao;
    private Label tfEmprestimo;
    private Label tfEmprestimoLCP;
    private Label lPrazo;
    private Label lEmprestimo;
    private Label lPrestacao;
    private Label lSeguroVida;
    private Label lTaxa;
    private Label lPrestacaoCarencia;
    private Label lPrazoCarencia;
    private Image[] imageArray;
    private Image offScreen;
    private Graphics offScreen_g;
    private Graphics offScreen_link;
    private double currentTaxa;
    private double currentSpread;
    private double currentTaxaEfectiva;
    private double selo;
    private double emprestimomax;
    private double currentBonificacao;
    private double garantia;
    private double[] arrTaxasFixas;
    private double originalTaxa;
    private double maxTaxa;
    private double TRCB;
    private double TRCPE;
    private double seguroVida;
    private int idade1;
    private int idade2;
    private Hashtable tabela;
    private FontMetrics fontMetrics;
    private boolean componentsDrawn;
    private s_image_loader imageLoader;
    private int image_index;
    private boolean escudo;
    private Checkbox cbEuro;
    private Checkbox cbEscudo;
    private String simboloEscudo;
    private String simboloEuro;
    private String simboloMoeda;
    private Label lMaxPrazo;
    private Label lMaxPrazoCarencia;
    private Label lMinPrazo;
    private double segundoMinimo;
    private StatsManager manager;
    private boolean enableStats;
    private boolean enableFullStats;
    private int siteId;
    int inputData;
    Record record;
    String lastInput;
    boolean initialStatInserted;
    private String[] allowedProperties;
    private boolean clienteInit;
    Properties parameters;
    Hashtable parametersCanChange;
    Dimension LINK_DIMENSION_NORMAL_LAYOUT;
    Point LINK_MARGIN_NORMAL_LAYOUT;
    Dimension LINK_DIMENSION_SAPO_LAYOUT;
    Point LINK_MARGIN_SAPO_LAYOUT;
    
    public simHabitacao() {
        this.version = "1.0.8.40";
        this.hideTAN_Seg = false;
        this.started = false;
        this.regime = 0;
        this.modalidade = 1;
        this.tipoPlano = -1;
        this.finalidade = -1;
        this.prazoCarencia = -1;
        this.carencia = false;
        this.CE_limiteFinanciamento = -1.0;
        this.CE_spreadUnico = -1.0;
        this.condicoesEspeciais = false;
        this.clientLayout = 0;
        this.layoutIngles = false;
        this.debug = false;
        this.localMode = false;
        this.simulador = 1;
        this.PRAZO_LIMITE = 50.0;
        this.PRAZOCARENCIA_MAX = 5.0;
        this.PRAZOCARENCIA_MIN = 1.0;
        this.PRAZOCARENCIA_LIMITE = this.PRAZOCARENCIA_MAX;
        this.enableStats = false;
        this.enableFullStats = false;
        this.lastInput = "";
        this.initialStatInserted = false;
        this.allowedProperties = new String[] { "euribor", "prazo_limiteMax", "prazo_limiteMin", "financiamento", "financiamentolcp", "taxa", "prazo_jump", "prazo_min_taxas", "taxas_fixas", "garantia", "prazo_max", "selo", "bonificacao", "prazo", "idade1", "idade2", "limite", "modalidade", "regime", "trcb", "trcpe", "browser", "simulador", "layout", "ve", "vo", "oic", "condicoesEspeciais", "spreadMatrizColunas", "spreadMatrizLinhas", "spreadMatrizValores", "limitefinanciamento", "limitefinanciamentolcp", "spreadUnico", "enableStats", "enableFullStats", "statsDebug", "siteId", "grelha", "tipoplano", "valorresidual", "emprestimomin", "emprestimomax", "emprestimoini", "emprestimolcpini", "emprestimolcpmin", "link", "uselink", "emprestimoinipercentual", "finalidade", "prazoCarencia", "bonificacaospread", "impostoselo" };
        this.clienteInit = false;
        this.LINK_DIMENSION_NORMAL_LAYOUT = new Dimension(72, 13);
        this.LINK_MARGIN_NORMAL_LAYOUT = new Point(5, 2);
        this.LINK_DIMENSION_SAPO_LAYOUT = new Dimension(72, 13);
        this.LINK_MARGIN_SAPO_LAYOUT = new Point(5, 2);
        this.EMPRESTIMO_MIN = 0.0;
        this.EMPRESTIMO_INI = 5000.0;
        this.EMPRESTIMO_COMPLEMENTAR_MIN = 5000.0;
        this.EMPRESTIMO_JUMP = 0.0;
        this.EMPRESTIMOLCP_JUMP = 0.0;
        this.PRAZO_MIN = 3.0;
        this.PRAZO_MIN_TAXAS = 3.0;
        this.PRAZO_JUMP = 1.0;
        this.PRAZO_MAX = 50.0;
        this.PRAZO_INI = 30.0;
        this.PRESTACAO_MIN = 0.0;
        this.sliderEmprestimo = null;
        this.sliderEmprestimoLCP = null;
        this.sliderPrazo = null;
        this.sliderPrestacao = null;
        this.sliderPrazoCarencia = null;
        this.tfEmprestimo = null;
        this.tfEmprestimoLCP = null;
        this.lPrazo = null;
        this.lPrazoCarencia = null;
        this.lPrestacaoCarencia = null;
        this.lPrestacao = null;
        this.lSeguroVida = null;
        this.lTaxa = null;
        this.imageArray = null;
        this.offScreen = null;
        this.offScreen_g = null;
        this.currentTaxa = 13.5;
        this.selo = 4.0;
        this.emprestimomax = 0.0;
        this.currentBonificacao = 4.0;
        this.garantia = 1.0;
        this.arrTaxasFixas = null;
        this.originalTaxa = 13.5;
        this.maxTaxa = 10.0;
        this.TRCB = 5.916;
        this.TRCPE = 8.0;
        this.fontMetrics = null;
        this.componentsDrawn = false;
        this.imageLoader = null;
        this.image_index = 0;
        this.simboloEscudo = "$";
        this.simboloEuro = "EUR";
    }
    
    public static double arredonda(final double n, final double n2) {
        final double pow = Math.pow(10.0, n2);
        return Math.floor(n) + Math.round((n - Math.floor(n)) * pow) / pow;
    }
    
    public static double arredonda1000(final double n) {
        return Math.floor(n) + Math.round((n - Math.floor(n)) * 1000.0) / 1000.0;
    }
    
    public static double arredonda125(final double n) {
        return arredonda1000(n);
    }
    
    public static float arredondaDown(final double n, final double n2) {
        final float floatValue = new Float(Math.pow(10.0, n2));
        return (long)(float)new Float(floatValue * n) / floatValue;
    }
    
    private double calcEmprestimo(final double n, final double n2, final double n3, final double n4) {
        final double n5 = Math.pow(1.0 + n3 / 1200.0, 12.0) - 1.0;
        double n6;
        if (this.regime == 1) {
            n6 = Math.min(n5 * 100.0, this.TRCPE);
        }
        else {
            n6 = Math.min(n5 * 100.0, this.TRCB);
        }
        return n / (n3 / 1200.0 / (1.0 - 1.0 / Math.pow(1.0 + n3 / 1200.0, n2 * 12.0)) - this.currentBonificacao * (n6 * n3 / (n5 * 100.0)) / 1200.0);
    }
    
    private double calcPrestacao(final double n, final double n2, double n3, final double n4, final double n5) {
        final double n6 = n3 / 12.0 / 100.0;
        final double n7 = n * n6 * n5;
        if (this.getIParameter("tipoplano") != null && this.getIParameter("tipoplano").equals("prestmist")) {
            final double n8 = 120.0;
            final double n9 = n2 * 12.0;
            final double min = Math.min(arredondaDown(0.0158 * Math.exp(-0.05 * n2 - (0.8829 * n2 + 3.656) * (n6 * 12.0 + 0.002 - 0.0425)), 5.0), 0.003);
            return n / ((Math.pow(1.0 + n6, n8) - Math.pow(1.0 + min, n8)) / ((n6 - min) * Math.pow(1.0 + n6, n8)) + Math.pow(1.0 + min, n8) * (Math.pow(1.0 + n6, n9) / Math.pow(1.0 + n6, n8) - 1.0) / (n6 * Math.pow(1.0 + n6, n9))) + n7;
        }
        if (this.getIParameter("tipoplano") != null && this.getIParameter("tipoplano").equals("prestvr")) {
            final double n10 = new Double(this.inValorResidual) / 100.0;
            final double n11 = n2 * 12.0;
            return n * (1.0 - n10) * n6 * Math.pow(1.0 + n6, n11) / (Math.pow(1.0 + n6, n11) - 1.0) + n * n10 * n6 + n7;
        }
        double n14;
        if (this.regime == 1) {
            final double n12 = (Math.pow(1.0 + n3 / 100.0 / 12.0, 12.0) - 1.0) / 12.0 * 12.0;
            final double n13 = Math.pow(1.0 + (n12 / 12.0 - 0.25 * Math.min(n12 / 12.0, this.TRCPE / 100.0 / 12.0)) * 12.0, 0.083333333) - 1.0;
            n14 = n * n13 * Math.pow(1.0 + n13, n2 * 12.0) / (Math.pow(1.0 + n13, n2 * 12.0) - 1.0);
        }
        else {
            if (Integer.parseInt(this.getIParameter("modalidade")) == 6 && this.sliderPrazoCarencia != null && this.sliderPrazoCarencia.getValue() == 5.0) {
                n3 -= 0.175;
            }
            final double n15 = Math.pow(1.0 + n3 / 1200.0, 12.0) - 1.0;
            n14 = n * n3 / 1200.0 / (1.0 - 1.0 / Math.pow(1.0 + n3 / 1200.0, n2 * 12.0)) - n * this.currentBonificacao * (Math.min(n15 * 100.0, this.TRCB) * n3 / (n15 * 100.0)) / 1200.0;
        }
        return n14 + n7;
    }
    
    private double calcPrestacaoCarencia() {
        double n;
        if (this.EMPRESTIMO_FINANCIAMENTO_LCP <= 0.0) {
            n = this.calcTaxaJuro(this.originalTaxa, this.sliderEmprestimo.getValue(), this.garantia) / 100.0;
        }
        else {
            n = this.calcTaxaJuro(this.originalTaxa, this.sliderEmprestimo.getValue() + this.sliderEmprestimoLCP.getValue() - this.EMPRESTIMO_COMPLEMENTAR_MIN, this.garantia) / 100.0;
        }
        double n2 = n * this.sliderEmprestimo.getValue() / 12.0;
        if (this.EMPRESTIMO_FINANCIAMENTO_LCP > 0.0) {
            n2 += n * (this.sliderEmprestimoLCP.getValue() - this.EMPRESTIMO_COMPLEMENTAR_MIN) / 12.0 + n * (this.sliderEmprestimoLCP.getValue() - this.EMPRESTIMO_COMPLEMENTAR_MIN) / 12.0 * this.IMPOSTO_SELO;
        }
        return n2;
    }
    
    private double calcSeguroVida(final double n, final double n2, final int n3, final int n4, final int n5) {
        final Float n6 = this.tabela.get(new Integer(n3).toString());
        final Float n7 = this.tabela.get(new Integer(n4).toString());
        if (n6 != null && n7 != null) {
            return (n6 / 1000.0f * 0.5 + n7 / 1000.0f * 0.5) * n / 12.0;
        }
        if (n6 != null) {
            return n6 / 1000.0f * n / 12.0;
        }
        return 0.0;
    }
    
    private double calcSpreadEstrangeiro(final double n, final double n2) {
        final double n3 = n / n2 * 100.0;
        double n4 = 0.0;
        if (n3 <= 60.0) {
            n4 = 2.8;
        }
        else if (n3 <= 70.0) {
            n4 = 3.2;
        }
        return n4 - this.BONIFICACAOSPREAD;
    }
    
    private double calcSpreadGeral(final double n, final double n2) {
        final double n3 = n / n2 * 100.0;
        double n4 = 0.0;
        if (this.finalidade != 5) {
            if (n3 <= 60.0) {
                if (n < 100000.0) {
                    n4 = 3.9;
                }
                else if (n < 200000.0) {
                    n4 = 3.4;
                }
                else {
                    n4 = 2.9;
                }
            }
            else if (n3 <= 80.0) {
                if (n < 100000.0) {
                    n4 = 4.3;
                }
                else if (n < 200000.0) {
                    n4 = 3.8;
                }
                else {
                    n4 = 3.3;
                }
            }
            if (this.regime == 1) {
                n4 = 2.0;
            }
        }
        else {
            n4 = 5.6;
        }
        return n4 - this.BONIFICACAOSPREAD;
    }
    
    private double calcSpreadSonae(final double n, final double n2) {
        return this.calcSpreadGeral(n, n2) - 0.1;
    }
    
    private void calcTaxaFixa() {
        final double n = (this.sliderPrazo == null) ? this.PRAZO_INI : ((double)(int)Math.round(this.sliderPrazo.getValue()));
        if (this.arrTaxasFixas.length > 0) {
            this.originalTaxa = this.arrTaxasFixas[(int)((n - this.PRAZO_MIN_TAXAS) / this.PRAZO_JUMP)];
            this.maxTaxa = this.originalTaxa + 2.0;
            if (this.EMPRESTIMO_FINANCIAMENTO_LCP <= 0.0) {
                this.currentTaxa = this.calcTaxaJuro(this.originalTaxa, this.EMPRESTIMO_INI, this.garantia);
            }
            else {
                this.currentTaxa = this.calcTaxaJuro(this.originalTaxa, this.EMPRESTIMO_INI + this.EMPRESTIMO_COMPLEMENTAR_INI, this.garantia);
            }
            if (this.lTaxa != null) {
                try {
                    if (this.eFixa) {
                        this.lTaxa.setText(String.valueOf(format(this.currentTaxa, 1, ",")) + " %");
                        return;
                    }
                    this.lTaxa.setText(String.valueOf(format(this.currentTaxa, 3, ",")) + " %");
                }
                catch (Exception ex) {}
            }
        }
    }
    
    private double calcTaxaJuro(final double n, final double n2, final double n3) {
        this.calcSpreadGeral(n2, n3);
        double n4 = 0.0;
        if (this.simulador == 6) {
            return arredonda1000(n + 0.5);
        }
        if (this.condicoesEspeciais) {
            if (this.CE_spreadUnico != -1.0) {
                final double ce_spreadUnico = this.CE_spreadUnico;
            }
            else {
                this.CE_matriz.getValue(n2, n2 / n3);
            }
        }
        if (this.regime == 1) {
            final double currentSpread = 2.0;
            this.currentTaxa = arredonda1000(n + currentSpread);
            this.currentSpread = currentSpread;
            return arredonda1000(n + currentSpread);
        }
        if (this.regime == 3) {
            return arredonda1000(this.calcSpreadEstrangeiro(n2, n3) + n);
        }
        if (Integer.parseInt(this.getIParameter("regime")) == 0) {
            n4 = arredonda1000(this.calcSpreadGeral(n2, n3) + n);
        }
        if (Integer.parseInt(this.getIParameter("regime")) == 2) {
            n4 = arredonda1000(this.calcSpreadGeral(n2, n3) + 0.7 + n);
        }
        if (Integer.parseInt(this.getIParameter("modalidade")) == 6) {
            n4 = arredonda1000(this.calcSpreadGeral(n2, n3) + 0.175 + n);
        }
        return n4;
    }
    
    private void createLayout() {
        this.setLayout(null);
        this.setBackground(Color.white);
        this.setFont(new Font("Helvetica", 0, 10));
        simHabitacao.WIDTH = Integer.parseInt(this.getParameter("width"));
        simHabitacao.HEIGHT = Integer.parseInt(this.getParameter("height"));
        this.componentsDrawn = false;
        this.fontMetrics = this.getFontMetrics(this.getFont());
        this.offScreen = null;
        this.offScreen_g = null;
        this.imageArray = new Image[3];
        if (this.clientLayout == 7) {
            this.imageArray[0] = this.getImage(this.getCodeBase(), "img/barexpresso.gif");
            this.imageArray[1] = this.getImage(this.getCodeBase(), "img/pinexpresso.gif");
        }
        else {
            this.imageArray[0] = this.getImage(this.getCodeBase(), "img/bar.gif");
            this.imageArray[1] = this.getImage(this.getCodeBase(), "img/pin.gif");
        }
        if (this.clientLayout == 1) {
            if (this.useLink()) {
                this.imageArray[2] = this.getImage(this.getCodeBase(), "img/bgVertLink.gif");
            }
            else {
                this.imageArray[2] = this.getImage(this.getCodeBase(), "img/bgVert.gif");
            }
        }
        else if (this.clientLayout == 4) {
            if (this.useLink()) {
                this.imageArray[2] = this.getImage(this.getCodeBase(), "img/bgnovocasasapo.gif");
            }
            else {
                this.imageArray[2] = this.getImage(this.getCodeBase(), "img/bgVert.gif");
            }
        }
        else if (this.clientLayout == 5) {
            this.imageArray[2] = this.getImage(this.getCodeBase(), "img/bgimob.gif");
        }
        else if (this.clientLayout == 7) {
            this.imageArray[2] = this.getImage(this.getCodeBase(), "img/bgexpresso.gif");
        }
        else if (this.clientLayout == 2) {
            this.imageArray[2] = this.getImage(this.getCodeBase(), "img/ingles.gif");
        }
        else if (this.clientLayout == 3) {
            this.imageArray[2] = this.getImage(this.getCodeBase(), "img/bg_allianz.gif");
        }
        else if (this.useLink()) {
            this.imageArray[2] = this.getImage(this.getCodeBase(), "img/bgLink.gif");
        }
        else if (this.EMPRESTIMO_FINANCIAMENTO_LCP > 0.0) {
            this.imageArray[2] = this.getImage(this.getCodeBase(), "img/bg_LCP.gif");
        }
        else {
            this.imageArray[2] = this.getImage(this.getCodeBase(), "img/bg.gif");
        }
        if (this.carencia) {
            if (this.layoutIngles) {
                this.imageArray[2] = this.getImage(this.getCodeBase(), "img/bgCarenciaIngles.gif");
            }
            else if (this.EMPRESTIMO_FINANCIAMENTO_LCP != 0.0) {
                this.imageArray[2] = this.getImage(this.getCodeBase(), "img/bgCarencia_LCP.gif");
            }
            else {
                this.imageArray[2] = this.getImage(this.getCodeBase(), "img/bgCarencia.gif");
            }
            final Label label = new Label(String.valueOf((int)this.PRAZOCARENCIA_MIN));
            if (this.EMPRESTIMO_FINANCIAMENTO_LCP != 0.0) {
                label.setBounds(5, 121, 25, 10);
            }
            else {
                label.setBounds(5, 76, 25, 10);
            }
            this.add(label);
        }
        this.imageLoader = new s_image_loader(this.imageArray, this);
        this.image_index = 0;
        this.imageLoader.start();
    }
    
    private void createOffScreen() {
        if (this.imageArray[2].getWidth(null) == -1 || this.imageArray[2].getHeight(null) == -1) {
            this.waitForImage(this, this.imageArray[2]);
        }
        try {
            this.offScreen = this.createImage(this.imageArray[2].getWidth(null), this.imageArray[2].getHeight(null));
            (this.offScreen_g = this.offScreen.getGraphics()).clearRect(0, 0, this.offScreen.getWidth(this), this.offScreen.getHeight(this));
        }
        catch (IllegalArgumentException ex) {
            System.out.println("array 2 = " + this.imageArray[2]);
            System.out.println("\t" + this.imageArray[2].getWidth(null));
            System.out.println("\t" + this.imageArray[2].getHeight(null));
        }
    }
    
    private boolean doLayout(final Event event) {
        this.setForeground(new Color(0, 0, 66));
        this.image_index = (int)event.arg;
        if (event.id == -2) {
            System.out.println("Image ID " + this.image_index + " loaded unsuccessfully.");
        }
        if (this.image_index < this.imageArray.length - 1) {
            if (this.offScreen == null) {
                this.createOffScreen();
            }
            if (this.offScreen == null) {
                for (int i = 0; i < 5; ++i) {
                    this.createOffScreen();
                    if (this.offScreen != null) {
                        i = 5;
                    }
                }
            }
            final String string = String.valueOf((int)((this.image_index + 1) / this.imageArray.length * 100.0f)) + "%";
            this.offScreen_g.setColor(this.getBackground());
            this.offScreen_g.fill3DRect(this.offScreen.getWidth(this) / 2 - 46, this.offScreen.getHeight(this) / 2 - 8, 92, 16, false);
            this.offScreen_g.fillRect(this.offScreen.getWidth(this) / 2 - 45, this.offScreen.getHeight(this) / 2 - 7, 90, 14);
            this.offScreen_g.setColor(new Color(204, 204, 255));
            this.offScreen_g.fillRect(this.offScreen.getWidth(this) / 2 - 45, this.offScreen.getHeight(this) / 2 - 7, 30 * (this.image_index + 1), 14);
            this.offScreen_g.setColor(Color.black);
            this.offScreen_g.drawString(string, this.offScreen.getWidth(this) / 2 - this.fontMetrics.stringWidth(string) / 2, 83);
            while (this.getGraphics() == null) {}
            this.paint(this.getGraphics());
        }
        else {
            this.offScreen_g.setColor(this.getBackground());
            this.offScreen_g.clearRect(0, 0, this.offScreen.getWidth(this), this.offScreen.getHeight(this));
            this.paint(this.getGraphics());
            simHabitacao.BAR_WIDTH = this.imageArray[0].getWidth(this);
            simHabitacao.BAR_HEIGHT = this.imageArray[0].getHeight(this);
            simHabitacao.PIN_WIDTH = this.imageArray[1].getWidth(this);
            simHabitacao.PIN_HEIGHT = this.imageArray[1].getHeight(this);
            this.sliderEmprestimo = new s_sliderpt(this.EMPRESTIMO_MIN, this.EMPRESTIMO_MAX - this.EMPRESTIMO_COMPLEMENTAR_MIN, this.EMPRESTIMO_INI, this.imageArray[0], simHabitacao.BAR_WIDTH, simHabitacao.BAR_HEIGHT, this.imageArray[1], simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
            this.sliderEmprestimoLCP = new s_sliderpt(this.EMPRESTIMO_COMPLEMENTAR_MIN, this.EMPRESTIMO_MAX, this.EMPRESTIMO_COMPLEMENTAR_INI + this.EMPRESTIMO_COMPLEMENTAR_MIN, this.imageArray[0], simHabitacao.BAR_WIDTH, simHabitacao.BAR_HEIGHT, this.imageArray[1], simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
            this.sliderPrazo = new s_sliderpt(this.PRAZO_MIN, this.PRAZO_LIMITE, this.PRAZO_INI - this.PRAZO_MIN, this.imageArray[0], simHabitacao.BAR_WIDTH, simHabitacao.BAR_HEIGHT, this.imageArray[1], simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
            if (this.carencia) {
                this.add(this.sliderPrazoCarencia = new s_sliderpt(this.PRAZOCARENCIA_MIN, this.PRAZOCARENCIA_MAX, this.prazoCarencia, this.imageArray[0], simHabitacao.BAR_WIDTH, simHabitacao.BAR_HEIGHT, this.imageArray[1], simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT));
            }
            if (this.EMPRESTIMO_FINANCIAMENTO_LCP > 0.0) {
                if (this.carencia) {
                    this.sliderPrestacao = new s_sliderpt(0.0, 5000.0, this.calcPrestacao(this.EMPRESTIMO_COMPLEMENTAR_INI, this.PRAZO_INI - this.prazoCarencia, this.currentTaxa, this.TRCB, this.IMPOSTO_SELO) + this.calcPrestacao(this.EMPRESTIMO_INI, this.PRAZO_INI - this.prazoCarencia, this.currentTaxa, this.TRCB, 0.0), this.imageArray[0], simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.BAR_HEIGHT, this.imageArray[1], simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                }
                else {
                    this.sliderPrestacao = new s_sliderpt(0.0, 5000.0, this.calcPrestacao(this.EMPRESTIMO_COMPLEMENTAR_INI, this.PRAZO_INI, this.currentTaxa, this.TRCB, this.IMPOSTO_SELO) + this.calcPrestacao(this.EMPRESTIMO_INI, this.PRAZO_INI, this.currentTaxa, this.TRCB, 0.0), this.imageArray[0], simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.BAR_HEIGHT, this.imageArray[1], simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                }
            }
            else if (this.carencia) {
                this.sliderPrestacao = new s_sliderpt(this.PRESTACAO_MIN, this.calcPrestacao(this.EMPRESTIMO_MAX, this.PRAZO_MIN, this.currentTaxa, this.TRCB, this.IMPOSTO_SELO) + 200.0, this.calcPrestacao(this.EMPRESTIMO_INI, this.PRAZO_INI - this.prazoCarencia, this.currentTaxa, this.TRCB, this.IMPOSTO_SELO), this.imageArray[0], simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.BAR_HEIGHT, this.imageArray[1], simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
            }
            else {
                this.sliderPrestacao = new s_sliderpt(this.PRESTACAO_MIN, this.calcPrestacao(this.EMPRESTIMO_MAX, this.PRAZO_MIN, this.currentTaxa, this.TRCB, this.IMPOSTO_SELO) + 200.0, this.calcPrestacao(this.EMPRESTIMO_INI, this.PRAZO_INI, this.currentTaxa, this.TRCB, this.IMPOSTO_SELO), this.imageArray[0], simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.BAR_HEIGHT, this.imageArray[1], simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
            }
            this.add(this.sliderEmprestimo);
            this.add(this.sliderEmprestimoLCP);
            this.add(this.sliderPrazo);
            this.add(this.sliderPrestacao);
            this.lMaxPrazo = new Label(new Integer((int)this.PRAZO_LIMITE).toString());
            this.lMinPrazo = new Label(new Integer((int)this.PRAZO_MIN).toString());
            this.lMaxPrazoCarencia = new Label(new Integer((int)this.PRAZOCARENCIA_MAX).toString());
            if (this.clientLayout == 1) {
                this.sliderEmprestimo.setBounds(2, 35, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                this.sliderPrazo.setBounds(2, 91, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                this.sliderPrestacao.setBounds(2, 155, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                this.lMaxPrazo.setBounds(114, 102, 15, 15);
                this.lMaxPrazo.setText(new Integer((int)this.PRAZO_LIMITE).toString());
                this.lMaxPrazo.setBackground(Color.white);
            }
            else if (this.clientLayout == 4) {
                this.sliderEmprestimo.setBounds(12, 35, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                this.sliderPrazo.setBounds(150, 35, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                this.sliderPrestacao.setBounds(390, 35, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                this.lMaxPrazo.setBounds(383, 40, 15, 15);
                this.lMaxPrazo.setText(new Integer((int)this.PRAZO_LIMITE).toString());
                this.lMaxPrazo.setBackground(Color.white);
                this.lMinPrazo.setBounds(274, 40, 15, 15);
                this.lMinPrazo.setText(new Integer((int)this.PRAZO_MIN).toString());
                this.lMinPrazo.setBackground(Color.white);
                this.add(this.lMinPrazo);
            }
            else if (!this.carencia && this.EMPRESTIMO_FINANCIAMENTO_LCP == 0.0) {
                this.sliderEmprestimo.setBounds(4, 27, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                this.sliderPrazo.setBounds(4, 67, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                this.sliderPrestacao.setBounds(4, 106, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                this.lMaxPrazo.setBounds(120, 74, 15, 15);
                this.lMaxPrazo.setText(new Integer((int)this.PRAZO_LIMITE).toString());
                this.lMaxPrazo.setBackground(Color.white);
                this.lMinPrazo.setBounds(5, 74, 15, 15);
                this.lMinPrazo.setText(new Integer((int)this.PRAZO_MIN).toString());
                this.lMinPrazo.setBackground(Color.white);
                this.add(this.lMinPrazo);
            }
            else if (this.EMPRESTIMO_FINANCIAMENTO_LCP > 0.0) {
                this.sliderEmprestimo.setBounds(4, 27, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                this.sliderEmprestimoLCP.setBounds(4, 70, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                if (this.carencia) {
                    this.sliderPrazo.setBounds(4, 150, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                }
                else {
                    this.sliderPrazo.setBounds(4, 110, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                }
                if (this.carencia) {
                    this.lMaxPrazo.setBounds(120, 156, 15, 15);
                }
                else {
                    this.lMaxPrazo.setBounds(120, 116, 15, 15);
                }
                this.lMaxPrazo.setText(new Integer((int)this.PRAZO_LIMITE).toString());
                this.lMaxPrazo.setBackground(Color.white);
                if (this.carencia) {
                    this.lMinPrazo.setBounds(5, 156, 15, 15);
                }
                else {
                    this.lMinPrazo.setBounds(5, 116, 15, 15);
                }
                this.lMinPrazo.setText(new Integer((int)this.PRAZO_MIN).toString());
                this.lMinPrazo.setBackground(Color.white);
                this.add(this.lMinPrazo);
                if (this.carencia) {
                    this.sliderPrazoCarencia.setBounds(4, 110, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                    if (this.EMPRESTIMO_FINANCIAMENTO_LCP > 0.0) {
                        this.lMaxPrazoCarencia.setBounds(114, 116, 15, 15);
                    }
                    else {
                        this.lMaxPrazoCarencia.setBounds(114, 75, 15, 15);
                    }
                    this.lMaxPrazoCarencia.setText(new Integer((int)this.PRAZOCARENCIA_MAX).toString());
                    this.lMaxPrazoCarencia.setBackground(Color.white);
                }
            }
            else {
                this.sliderEmprestimo.setBounds(4, 27, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                this.sliderPrazo.setBounds(4, 106, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                this.sliderPrazoCarencia.setBounds(4, 67, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                this.sliderPrestacao.setBounds(4, 145, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                this.lMaxPrazo.setBounds(120, 113, 15, 15);
                this.lMaxPrazo.setText(new Integer((int)this.PRAZO_LIMITE).toString());
                this.lMaxPrazo.setBackground(Color.white);
                this.lMaxPrazoCarencia.setBounds(114, 75, 15, 15);
                this.lMaxPrazoCarencia.setText(new Integer((int)this.PRAZOCARENCIA_MAX).toString());
                this.lMaxPrazoCarencia.setBackground(Color.white);
                this.lMinPrazo.setBounds(5, 113, 15, 15);
                this.lMinPrazo.setText(new Integer((int)this.PRAZO_MIN).toString());
                this.lMinPrazo.setBackground(Color.white);
                this.add(this.lMinPrazo);
            }
            (this.tfEmprestimo = new Label("")).setAlignment(2);
            final int n = 142;
            final int n2 = 21;
            final int n3 = 70;
            final int n4 = 13;
            final int n5 = 142;
            final int n6 = 63;
            final int n7 = 70;
            final int n8 = 13;
            (this.tfEmprestimoLCP = new Label("")).setAlignment(2);
            this.lPrestacao = new Label("");
            this.lSeguroVida = new Label("");
            this.lPrazo = new Label("");
            this.lTaxa = new Label("");
            this.lPrestacaoCarencia = new Label("");
            this.lPrazoCarencia = new Label("");
            this.tfEmprestimo.setFont(this.getFont());
            this.tfEmprestimoLCP.setFont(this.getFont());
            this.lPrazo.setFont(this.getFont());
            this.lPrestacao.setFont(this.getFont());
            this.lSeguroVida.setFont(new Font("Helvetica", 0, 10));
            this.lTaxa.setFont(new Font("Helvetica", 0, 10));
            this.lPrazo.setAlignment(2);
            this.lPrestacao.setAlignment(2);
            this.lSeguroVida.setAlignment(2);
            this.lTaxa.setAlignment(2);
            this.lPrazo.setBackground(this.getBackground());
            this.lPrestacao.setBackground(this.getBackground());
            this.lSeguroVida.setBackground(this.getBackground());
            this.lTaxa.setBackground(this.getBackground());
            this.add(this.tfEmprestimo);
            if (this.EMPRESTIMO_FINANCIAMENTO_LCP > 0.0) {
                this.add(this.tfEmprestimoLCP);
            }
            this.add(this.lPrazo);
            this.add(this.lPrestacao);
            this.add(this.lTaxa);
            this.add(this.lMaxPrazo);
            if (this.carencia) {
                (this.lPrazoCarencia = new Label("")).setFont(this.getFont());
                this.lPrazoCarencia.setAlignment(2);
                this.lPrazoCarencia.setBackground(this.getBackground());
                this.add(this.lPrazoCarencia);
                (this.lPrestacaoCarencia = new Label("")).setFont(this.getFont());
                this.lPrestacaoCarencia.setAlignment(0);
                this.lPrestacaoCarencia.setBackground(this.getBackground());
                this.add(this.lPrestacaoCarencia);
                this.lMaxPrazoCarencia.setFont(this.getFont());
                this.lMaxPrazoCarencia.setAlignment(2);
                this.lMaxPrazoCarencia.setBackground(this.getBackground());
                this.add(this.lMaxPrazoCarencia);
            }
            if (this.getIParameter("tipoplano") != null && this.getIParameter("tipoplano").equals("prestvr")) {
                (this.choiceResidual = new Choice()).addItem("0%");
                this.choiceResidual.addItem("10%");
                this.choiceResidual.addItem("20%");
                this.choiceResidual.addItem("30%");
                this.choiceResidual.setBackground(this.getBackground());
                this.add(this.choiceResidual);
                (this.labelResidual = new Label()).setFont(this.getFont());
                this.labelResidual.setBackground(this.getBackground());
                this.labelResidual.setText("Residual");
                this.labelResidual.setForeground(this.getForeground());
                this.add(this.labelResidual);
            }
            else {
                this.choiceResidual = null;
            }
            if (this.clientLayout == 1) {
                this.lPrazo.setBounds(65, 74, 30, 16);
                this.lPrestacao.setBounds(43, 140, 80, 16);
                this.lSeguroVida.setBounds(80, 178, 42, 16);
                this.lSeguroVida.setAlignment(2);
                this.lTaxa.setBounds(51, 191, 72, 17);
                this.lTaxa.setAlignment(2);
                (this.lEmprestimo = new Label()).setBounds(67, 15, 100, 20);
                this.add(this.lEmprestimo);
            }
            else if (this.clientLayout == 4) {
                this.sliderEmprestimo.setBounds(30, 30, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                this.sliderPrazo.setBounds(270, 30, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                this.sliderPrestacao.setBounds(475, 30, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                this.lPrazo.setBounds(387, 27, 30, 16);
                this.lPrestacao.setBounds(590, 27, 72, 16);
                this.lSeguroVida.setBounds(460, 53, 42, 16);
                this.lTaxa.setBounds(540, 52, 52, 17);
                (this.lEmprestimo = new Label()).setBounds(this.sliderEmprestimo.getLocation().x + this.sliderEmprestimo.getSize().width + 20, this.sliderEmprestimo.getLocation().y, 60, 10);
                this.add(this.lEmprestimo);
            }
            else if (this.clientLayout == 5) {
                this.lMaxPrazo.setBounds(115, 40, 15, 15);
                this.lMinPrazo.setBounds(5, 40, 15, 15);
                this.sliderPrazo.setBounds(0, 30, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                this.lPrazo.setBounds(this.sliderPrazo.getLocation().x + this.sliderPrazo.getSize().width - 10, 27, 30, 16);
                this.sliderPrestacao.setBounds(this.lPrazo.getLocation().x + this.lPrazo.getSize().width + 46, 30, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                this.lPrestacao.setBounds(this.sliderPrestacao.getLocation().x + this.sliderPrestacao.getSize().width + 7, 29, 58, 12);
                this.sliderEmprestimo.setBounds(this.lPrestacao.getLocation().x + this.lPrestacao.getSize().width + 3, 30, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                (this.lEmprestimo = new Label()).setBounds(this.sliderEmprestimo.getLocation().x + this.sliderEmprestimo.getSize().width + 13, this.sliderEmprestimo.getLocation().y, 63, 10);
                this.add(this.lEmprestimo);
                this.lSeguroVida.setBounds(388, 53, 42, 16);
                this.lTaxa.setBounds(468, 52, 52, 17);
            }
            else if (this.clientLayout == 7) {
                this.sliderEmprestimo.setBounds(0, 22, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                (this.lEmprestimo = new Label()).setBounds(this.sliderEmprestimo.getLocation().x + this.sliderEmprestimo.getSize().width + 18, this.sliderEmprestimo.getLocation().y, 77, 10);
                this.add(this.lEmprestimo);
                final int x = this.lEmprestimo.getLocation().x;
                this.sliderPrazo.setBounds(0, 60, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                final int n9 = this.sliderPrazo.getLocation().x + this.sliderPrazo.getSize().width;
                final int y = this.sliderPrazo.getLocation().y;
                this.lPrazo.setBounds(x + 36, y - 2, 40, 16);
                this.lMinPrazo.setBounds(4, y + 10, 15, 15);
                this.lMaxPrazo.setBounds(n9 - 15, y + 10, 15, 15);
                this.sliderPrestacao.setBounds(0, 103, simHabitacao.BAR_WIDTH + simHabitacao.PIN_WIDTH, simHabitacao.PIN_HEIGHT);
                this.lPrestacao.setBounds(this.sliderPrestacao.getLocation().x + this.sliderPrestacao.getSize().width + 20, this.sliderPrestacao.getLocation().y, 72, 12);
                this.lSeguroVida.setBounds(55, 125, 42, 16);
                this.lTaxa.setBounds(35, 139, 52, 17);
            }
            else {
                this.tfEmprestimo.setBounds(n, n2 + 4, n3 + 10, n4);
                this.tfEmprestimoLCP.setBounds(n5, n6 + 4, n7 + 10, n8);
                this.lPrazo.setBounds(155, 68, 66, 16);
                this.lPrestacao.setBounds(143, 102, 78, 16);
                this.lSeguroVida.setBounds(65, 125, 52, 16);
                this.lTaxa.setBounds(34, 139, 45, 17);
                final int n10 = 39;
                if (this.carencia && this.EMPRESTIMO_FINANCIAMENTO_LCP <= 0.0) {
                    this.lPrazo.setBounds(165, 104 + n10, 50, 16);
                    this.lPrestacao.setBounds(143, 102 + n10, 72, 16);
                    this.lSeguroVida.setBounds(70, 125 + n10 + 5, 53, 16);
                    this.lTaxa.setBounds(170, 168, 45, 17);
                }
                else if (this.carencia && this.EMPRESTIMO_FINANCIAMENTO_LCP > 0.0) {
                    this.lPrazo.setBounds(165, 104 + n10, 50, 16);
                    this.lPrazoCarencia.setBounds(170, 64 + n10, 50, 16);
                    this.lPrestacao.setBounds(157, 159 + n10, 64, 16);
                    this.lSeguroVida.setBounds(65, 175 + n10, 53, 16);
                    this.lTaxa.setBounds(170, 213, 45, 17);
                }
                else if (!this.carencia && this.EMPRESTIMO_FINANCIAMENTO_LCP > 0.0) {
                    this.lPrazo.setBounds(171, 70 + n10, 50, 16);
                    this.lPrestacao.setBounds(87, 155, 67, 16);
                    this.lSeguroVida.setBounds(62, 170, 55, 16);
                    this.lTaxa.setBounds(34, 184, 45, 17);
                }
                if (this.clientLayout == 2) {
                    this.lSeguroVida.setBounds(120, 125, 42, 16);
                    this.lTaxa.setBounds(92, 139, 72, 17);
                    if (this.getIParameter("tipoplano") != null && this.getIParameter("tipoplano").equals("prestvr")) {
                        this.choiceResidual.setBounds(168, 40, 45, 19);
                        this.labelResidual.setBounds(125, 43, 85, 19);
                        this.choiceResidual.addItemListener(this);
                        if (this.inValorResidual != null) {
                            if (this.inValorResidual.equals("30")) {
                                this.choiceResidual.select(3);
                            }
                            else if (this.inValorResidual.equals("20")) {
                                this.choiceResidual.select(2);
                            }
                            else if (this.inValorResidual.equals("10")) {
                                this.choiceResidual.select(1);
                            }
                            else {
                                this.choiceResidual.select(3);
                            }
                        }
                    }
                    if (this.getIParameter("tipoplano") == null || !this.getIParameter("tipoplano").equals("prestmist")) {}
                }
                else {
                    if (this.getIParameter("tipoplano") != null && this.getIParameter("tipoplano").equals("prestvr")) {
                        if (this.EMPRESTIMO_FINANCIAMENTO_LCP > 0.0) {
                            this.choiceResidual.setBounds(175, 82, 45, 19);
                            this.labelResidual.setBounds(125, 85, 100, 19);
                        }
                        else {
                            this.choiceResidual.setBounds(168, 40, 45, 19);
                            this.labelResidual.setBounds(125, 43, 85, 19);
                        }
                        this.choiceResidual.addItemListener(this);
                        if (this.inValorResidual != null) {
                            if (this.inValorResidual.equals("30")) {
                                this.choiceResidual.select(3);
                            }
                            else if (this.inValorResidual.equals("20")) {
                                this.choiceResidual.select(2);
                            }
                            else if (this.inValorResidual.equals("10")) {
                                this.choiceResidual.select(1);
                            }
                            else {
                                this.choiceResidual.select(3);
                            }
                        }
                    }
                    if (this.getIParameter("tipoplano") == null || !this.getIParameter("tipoplano").equals("prestmist")) {}
                }
                if (this.carencia && this.EMPRESTIMO_FINANCIAMENTO_LCP <= 0.0) {
                    if (this.layoutIngles) {
                        this.lPrazoCarencia.setBounds(145, 64, 30, 16);
                        this.lPrazo.setBounds(145, 64 + n10, 30, 16);
                        this.lPrestacao.setBounds(145, 102 + n10, 72, 16);
                        this.lSeguroVida.setBounds(93, 125 + n10 + 5, 42, 16);
                        this.lTaxa.setBounds(90, 181, 45, 17);
                        this.lPrestacaoCarencia.setBounds(140, 194, 80, 16);
                    }
                    else {
                        this.lPrazoCarencia.setBounds(155, 364, 30, 16);
                        this.lPrazo.setBounds(170, 67 + n10, 50, 16);
                        this.lPrazoCarencia.setBounds(185, 64, 35, 16);
                        this.lPrestacaoCarencia.setBounds(135, 185, 80, 16);
                    }
                }
                else if (this.carencia && this.EMPRESTIMO_FINANCIAMENTO_LCP > 0.0) {
                    this.lPrazoCarencia.setBounds(168, 108, 50, 16);
                    this.lPrestacaoCarencia.setBounds(135, 230, 80, 16);
                }
            }
            if (this.clientLayout == 7) {
                this.lEmprestimo.setText(String.valueOf(this.simboloMoeda) + " " + format(this.EMPRESTIMO_INI, 2, ""));
            }
            this.tfEmprestimo.setText(String.valueOf(this.simboloMoeda) + " " + format(this.EMPRESTIMO_INI, 2, ""));
            if (this.EMPRESTIMO_FINANCIAMENTO_LCP > 0.0) {
                this.tfEmprestimoLCP.setText(String.valueOf(this.simboloMoeda) + " " + format(this.EMPRESTIMO_COMPLEMENTAR_INI, 2, ""));
            }
            if (!this.carencia && this.EMPRESTIMO_FINANCIAMENTO_LCP > 0.0) {
                this.lPrazo.setText(String.valueOf(String.valueOf(Math.round(this.PRAZO_INI))) + " anos");
                this.lPrestacao.setText(String.valueOf(this.simboloMoeda) + " " + format(this.calcPrestacao(this.EMPRESTIMO_INI, this.PRAZO_INI, this.currentTaxa, this.TRCB, 0.0) + this.calcPrestacao(this.EMPRESTIMO_COMPLEMENTAR_INI, this.PRAZO_INI, this.currentTaxa, this.TRCB, this.IMPOSTO_SELO), 2, "."));
                this.lSeguroVida.setText(String.valueOf(this.simboloMoeda) + " " + format(this.calcSeguroVida(this.EMPRESTIMO_INI, this.garantia, this.idade1, this.idade2, this.regime) + this.calcSeguroVida(this.EMPRESTIMO_COMPLEMENTAR_INI, this.garantia, this.idade1, this.idade2, this.regime), 2, ""));
            }
            else if (this.carencia && this.EMPRESTIMO_FINANCIAMENTO_LCP > 0.0) {
                this.lPrazo.setText(String.valueOf(String.valueOf(Math.round(this.PRAZO_INI))) + " anos");
                this.lPrestacao.setText(String.valueOf(this.simboloMoeda) + " " + format(this.calcPrestacao(this.EMPRESTIMO_INI, this.PRAZO_INI - this.prazoCarencia, this.currentTaxa, this.TRCB, 0.0) + this.calcPrestacao(this.EMPRESTIMO_COMPLEMENTAR_INI, this.PRAZO_INI - this.prazoCarencia, this.currentTaxa, this.TRCB, this.IMPOSTO_SELO), 2, "."));
                this.lSeguroVida.setText(String.valueOf(this.simboloMoeda) + " " + format(this.calcSeguroVida(this.EMPRESTIMO_INI, this.garantia, this.idade1, this.idade2, this.regime) + this.calcSeguroVida(this.EMPRESTIMO_COMPLEMENTAR_INI, this.garantia, this.idade1, this.idade2, this.regime), 2, ""));
            }
            else {
                this.lPrazo.setText(String.valueOf(String.valueOf(Math.round(this.PRAZO_INI))) + " anos");
                this.lPrestacao.setText(String.valueOf(this.simboloMoeda) + " " + format(this.calcPrestacao(this.EMPRESTIMO_INI, this.PRAZO_INI - this.prazoCarencia, this.currentTaxa, this.TRCB, this.IMPOSTO_SELO), 2, "."));
                if (this.clientLayout == 0) {
                    this.lSeguroVida.setText(String.valueOf(this.simboloMoeda) + " " + format(this.calcSeguroVida(this.EMPRESTIMO_INI, this.garantia, this.idade1, this.idade2, this.regime), 2, ""));
                }
            }
            if (this.carencia && this.EMPRESTIMO_FINANCIAMENTO_LCP <= 0.0) {
                this.lPrazoCarencia.setText(String.valueOf(String.valueOf(Math.round(this.prazoCarencia))) + " anos");
                this.lPrestacaoCarencia.setText(String.valueOf(this.simboloMoeda) + " " + format(this.calcPrestacaoCarencia(), 2, "."));
            }
            if (this.clientLayout == 0) {
                if (this.eFixa) {
                    this.lTaxa.setText(String.valueOf(format(this.currentTaxa, 1, "")) + " %");
                }
                else {
                    this.lTaxa.setText(String.valueOf(format(this.currentTaxa, 3, "")) + " %");
                }
            }
            final CheckboxGroup checkboxGroup = new CheckboxGroup();
            this.cbEscudo = new Checkbox("Esc.");
            this.cbEuro = new Checkbox("Euro");
            this.cbEscudo.setCheckboxGroup(checkboxGroup);
            this.cbEuro.setCheckboxGroup(checkboxGroup);
            this.cbEscudo.setFont(this.getFont());
            this.cbEuro.setFont(this.getFont());
            this.cbEscudo.setBackground(this.getBackground());
            this.cbEuro.setBackground(this.getBackground());
            if (this.escudo) {
                checkboxGroup.setSelectedCheckbox(this.cbEscudo);
            }
            else {
                checkboxGroup.setSelectedCheckbox(this.cbEuro);
            }
            this.add(this.cbEscudo);
            this.add(this.cbEuro);
            final int n11 = 173;
            final int n12 = 128;
            final int n13 = 143;
            final int n14 = 42;
            final int n15 = 10;
            if (this.clientLayout == 1) {
                this.cbEscudo.setBounds(50, 225, n14, n15);
                this.cbEuro.setBounds(90, 225, n14, n15);
            }
            else if (this.clientLayout == 4) {
                this.cbEscudo.setBounds(50, 225, n14, n15);
                this.cbEuro.setBounds(90, 225, n14, n15);
            }
            else {
                this.cbEscudo.setBounds(n11, n12, n14, n15);
                this.cbEuro.setBounds(n11, n13, n14, n15);
            }
            if (this.clientLayout != 3) {
                this.add(this.lSeguroVida);
            }
            this.paint(this.getGraphics());
            this.componentsDrawn = true;
            this.tfEmprestimo.setText(String.valueOf(this.simboloMoeda) + " " + format(this.EMPRESTIMO_INI, 2, ""));
            if (this.EMPRESTIMO_FINANCIAMENTO_LCP > 0.0) {
                this.tfEmprestimoLCP.setText(String.valueOf(this.simboloMoeda) + " " + format(this.EMPRESTIMO_COMPLEMENTAR_INI, 2, ""));
            }
            this.escudo = false;
            this.simboloMoeda = this.simboloEuro;
            this.cbEscudo.setVisible(false);
            this.cbEuro.setVisible(false);
            this.sliderPrazo.setValue(this.PRAZO_INI - this.PRAZO_MIN);
            if (this.carencia) {
                this.sliderPrazoCarencia.setValue(this.prazoCarencia);
                this.processSliderPrazo();
            }
            this.updateDisplay();
        }
        return false;
    }
    
    public static String format(double n, final int n2, final String s) {
        n *= Math.pow(10.0, n2 + 1);
        long n3 = (long)n;
        if (n3 % 10L >= 5L) {
            n3 += 10L;
        }
        final double n4 = n3 / 10L / Math.pow(10.0, n2);
        String s2 = "##0";
        if (n2 > 0) {
            s2 = String.valueOf(s2) + ".";
            for (int i = 0; i < n2; ++i) {
                s2 = String.valueOf(s2) + "0";
            }
        }
        String s3 = new DecimalFormat(s2).format(n4).replace('.', ',');
        int n5 = s3.indexOf(",");
        if (n5 == -1) {
            n5 = s3.length();
        }
        if (s != null && s.length() > 0) {
            while (0 < s3.length()) {
                n5 -= 3;
                if (n5 < 0) {
                    if (s3.substring(0, s.length()).equals(s)) {
                        s3 = s3.substring(s.length());
                    }
                    return s3;
                }
                s3 = String.valueOf(s3.substring(0, n5)) + s + s3.substring(n5);
            }
            if (s3.substring(0, s.length()).equals(s)) {
                s3 = s3.substring(s.length());
            }
        }
        return s3;
    }
    
    public String getCurrentTaxa() {
        return this.lTaxa.getText();
    }
    
    private String getDecimals(double n) {
        n -= (int)n;
        final int n2 = (int)(n * 1000.0);
        int n3;
        if (n2 % 10 >= 5) {
            n3 = n2 / 10 + 1;
        }
        else {
            n3 = n2 / 10;
        }
        return String.valueOf(n3);
    }
    
    public int getEuro() {
        return this.escudo ? 0 : 1;
    }
    
    private Vector getFile(final String s) {
        final Vector<String> vector = new Vector<String>();
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(this.getCodeBase(), s).openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                vector.addElement(line);
            }
            bufferedReader.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("Ficheiro nao encontrado: (" + s + ").");
            throw new RuntimeException();
        }
        catch (IOException ex2) {
            System.out.println("Erro IO");
            throw new RuntimeException();
        }
        return vector;
    }
    
    public String getFinanciamento() {
        return this.tfEmprestimo.getText();
    }
    
    public String getFinanciamentoLCP() {
        try {
            return this.tfEmprestimoLCP.getText();
        }
        catch (Exception ex) {
            return "0";
        }
    }
    
    private String getIParameter(final String s) {
        if (!this.clienteInit) {
            this.parameters = new Properties();
            this.parametersCanChange = new Hashtable();
            try {
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(this.getCodeBase(), "config/_master.txt").openStream()));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    final int n = 0;
                    final int n2 = 0;
                    if (line.length() != 0) {
                        int index = line.indexOf(9, n2);
                        final String substring = line.substring(n, index);
                        final int n3 = ++index;
                        int index2 = line.indexOf(9, index);
                        final Boolean b = line.substring(n3, index2).equals("0") ? new Boolean(true) : new Boolean(false);
                        ((Hashtable<String, String>)this.parameters).put(substring, line.substring(++index2));
                        this.parametersCanChange.put(substring, b);
                    }
                }
                bufferedReader.close();
            }
            catch (FileNotFoundException ex) {
                System.out.println(ex);
            }
            catch (IOException ex2) {
                System.out.println(ex2);
            }
            if (this.getParameter("cliente") != null && this.getParameter("cliente").length() != 0) {
                try {
                    final BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new URL(this.getCodeBase(), "config/" + this.getParameter("cliente") + ".txt").openStream()));
                    String line2;
                    while ((line2 = bufferedReader2.readLine()) != null) {
                        final int n4 = 0;
                        int index3 = line2.indexOf(9, 0);
                        final String substring2 = line2.substring(n4, index3);
                        final int n5 = ++index3;
                        int index4 = line2.indexOf(9, index3);
                        final Boolean b2 = line2.substring(n5, index4).equals("0") ? new Boolean(true) : new Boolean(false);
                        ((Hashtable<String, String>)this.parameters).put(substring2, line2.substring(++index4));
                        this.parametersCanChange.put(substring2, b2);
                    }
                    bufferedReader2.close();
                }
                catch (FileNotFoundException ex3) {
                    System.out.println(ex3);
                }
                catch (IOException ex4) {
                    System.out.println(ex4);
                }
            }
            String parameter = this.getParameter("taxas_fixas");
            if (parameter == null) {
                parameter = "";
            }
            if (!this.parameters.containsKey("taxa") && this.getParameter("taxa") == null && parameter.equals("")) {
                String s2;
                if (this.parameters.containsKey("euribor")) {
                    s2 = this.parameters.getProperty("euribor");
                    if (this.parametersCanChange.get("euribor") && this.getParameter("euribor") != null) {
                        s2 = this.getParameter("euribor");
                    }
                }
                else {
                    s2 = this.getParameter("euribor");
                }
                if (s2 != null) {
                    String s3;
                    if (this.localMode) {
                        s3 = this.getFile("getEuribor.asp").elementAt(0);
                    }
                    else {
                        s3 = this.getFile("getEuribor.asp?euribor=" + ((Hashtable<K, String>)this.parameters).get("euribor")).elementAt(0);
                    }
                    ((Hashtable<String, String>)this.parameters).put("taxa", s3);
                    this.parametersCanChange.put("taxa", new Boolean(true));
                }
            }
            if (this.parameters.getProperty("garantia") == null) {
                ((Hashtable<String, String>)this.parameters).put("garantia", this.getParameter("financiamento"));
                this.parametersCanChange.put("garantia", new Boolean(true));
            }
            this.clienteInit = true;
        }
        boolean b3 = false;
        for (int i = 0; i < this.allowedProperties.length; ++i) {
            if (this.allowedProperties[i].equals(s)) {
                b3 = true;
            }
        }
        if (!b3) {
            return null;
        }
        final String parameter2 = this.getParameter(s);
        if (parameter2 == null) {
            return this.parameters.getProperty(s);
        }
        if (this.parameters.getProperty(s) == null) {
            return parameter2;
        }
        if (this.parametersCanChange.get(s) || this.parameters.getProperty("cliente") == null) {
            return parameter2;
        }
        return this.parameters.getProperty(s);
    }
    
    private double getPrazoMaxFG() {
        final double n = (this.sliderEmprestimo.getValue() + this.sliderEmprestimoLCP.getValue()) / this.garantia;
        if (this.finalidade == 1 || this.finalidade == 2) {
            if (this.regime == 0) {
                if (this.tipoPlano == 1) {
                    if (this.modalidade == 1 || this.modalidade == 6) {
                        if (n <= 0.75) {}
                    }
                }
                else if (this.tipoPlano == 2) {}
            }
        }
        else if (this.finalidade == 3) {
            if (this.regime == 0) {
                if (this.modalidade == 5) {}
            }
        }
        else if (this.finalidade == 4) {
            if (this.regime == 0) {
                if (this.modalidade == 5) {}
            }
        }
        final int intValue = new Integer(this.getIParameter("modalidade"));
        double n2;
        if (this.regime == 3 || intValue == 5) {
            n2 = 30.0;
        }
        else if (intValue == 1 && n <= 0.75) {
            n2 = 50.0;
        }
        else {
            n2 = Integer.parseInt(this.lPrazo.getText().substring(0, this.lPrazo.getText().length() - 5));
        }
        return Math.min(n2, this.PRAZO_MAX);
    }
    
    private double getMaxFinanciamento() {
        if (this.condicoesEspeciais && this.CE_limiteFinanciamento != -1.0) {
            return this.CE_limiteFinanciamento * this.EMPRESTIMO_FINANCIAMENTO;
        }
        return Math.min(this.getMaxPercentFinanciamento() * this.garantia, this.segundoMinimo);
    }
    
    private double getMaxPercentFinanciamento() {
        if (this.condicoesEspeciais && this.CE_limiteFinanciamento != -1.0) {
            return this.CE_limiteFinanciamento * this.EMPRESTIMO_FINANCIAMENTO;
        }
        double n = 0.0;
        if (this.simulador == 10 || this.simulador == 11) {
            n = 0.9;
        }
        else if (this.regime == 3) {
            switch (this.finalidade) {
                case 3: {
                    n = 0.6;
                    break;
                }
                case 4: {
                    n = 0.6;
                    break;
                }
                case 2: {
                    n = 0.7;
                    break;
                }
                case 1: {
                    n = 0.7;
                    break;
                }
            }
        }
        else {
            double n2;
            if (this.sliderPrazo != null) {
                n2 = this.sliderPrazo.getValue();
            }
            else {
                n2 = new Double(this.PRAZO_INI);
            }
            try {
                new Integer(this.getIParameter("modalidade"));
            }
            catch (Exception ex) {}
            try {
                new Integer(this.getIParameter("regime"));
            }
            catch (Exception ex2) {}
            if (this.getIParameter("tipoplano") != null) {
                this.getIParameter("tipoplano");
            }
            if (this.finalidade == 5) {
                n = 0.65;
            }
            else {
                switch (this.finalidade) {
                    case 3: {
                        n = 0.6;
                        break;
                    }
                    case 4: {
                        n = 0.6;
                        break;
                    }
                    case 2: {
                        if (this.tipoPlano == 2 || this.tipoPlano == 3) {
                            n = 0.8;
                            break;
                        }
                        n = 0.8;
                        if (this.prazoCarencia <= 0 && (this.modalidade == 1 || this.modalidade == 6) && n2 <= 40.0) {
                            n = 0.8;
                            break;
                        }
                        break;
                    }
                    case 1: {
                        if (this.tipoPlano == 2 || this.tipoPlano == 3) {
                            n = 0.8;
                            break;
                        }
                        n = 0.8;
                        if (this.prazoCarencia <= 0 && (this.modalidade == 1 || this.modalidade == 6) && n2 <= 40.0) {
                            n = 0.8;
                            break;
                        }
                        break;
                    }
                }
            }
        }
        return n;
    }
    
    public String getPrazo() {
        return String.valueOf((int)Math.round(this.sliderPrazo.getValue()));
    }
    
    public String getPrazoCarencia() {
        try {
            return String.valueOf((int)Math.round(this.sliderPrazoCarencia.getValue()));
        }
        catch (Exception ex) {
            return "0";
        }
    }
    
    public String getPrestacao() {
        if (this.escudo) {
            return String.valueOf(this.lPrestacao.getText()) + "00";
        }
        return this.lPrestacao.getText();
    }
    
    public String getPrestacaoCarencia() {
        try {
            return this.lPrestacaoCarencia.getText();
        }
        catch (Exception ex) {
            return "0";
        }
    }
    
    public String getSeguroVida() {
        if (this.lSeguroVida == null || this.lSeguroVida.getText() == null || this.lSeguroVida.getText().length() == 0) {
            return "0";
        }
        final String substring = this.lSeguroVida.getText().substring(0, this.lSeguroVida.getText().length() - 2);
        if (this.escudo) {
            return String.valueOf(substring) + "$00";
        }
        return String.valueOf(substring) + "EUR";
    }
    
    public String getValorResidual() {
        return this.inValorResidual;
    }
    
    public boolean handleEvent(final Event event) {
        return this.hE(event);
    }
    
    private boolean hE(final Event event) {
        if (event.id == -1 || event.id == -2) {
            this.doLayout(event);
        }
        if (!this.componentsDrawn) {
            return true;
        }
        if (event.target instanceof TextField) {
            if ((event.id == 401 && event.key != 127 && event.key != 8 && event.key != 9 && event.key != 10 && (event.key < 48 || event.key > 57)) || (event.id == 403 && event.key != 1005 && event.key != 1007 && event.key < 1006 && event.key > 1004)) {
                return true;
            }
            if (event.target == this.tfEmprestimo) {
                return true;
            }
        }
        else if (event.target == this.sliderEmprestimo) {
            if (event.id == 501 || event.id == 506) {
                this.processSliderEmprestimo();
            }
            else if (event.id == 502) {
                this.updateDisplay();
            }
        }
        else if (event.target == this.sliderEmprestimoLCP) {
            if (event.id == 501 || event.id == 506) {
                this.processSliderEmprestimoLCP();
            }
            else if (event.id == 502) {
                this.updateDisplay();
            }
        }
        else if (event.target == this.sliderPrazoCarencia) {
            if (event.id == 501 || event.id == 506) {
                this.processSliderPrazoCarencia(true);
            }
            else if (event.id == 502) {
                this.updateDisplay();
            }
        }
        else if (event.target == this.sliderPrazo) {
            if (event.id == 501 || event.id == 506) {
                this.processSliderPrazo();
            }
            else if (event.id == 502) {
                this.updateDisplay();
            }
        }
        else if (event.target == this.sliderPrestacao && (event.id == 501 || event.id == 506)) {
            this.processSliderPrestacao();
        }
        else if (event.id == 502) {
            this.updateDisplay();
        }
        if (this.hideTAN_Seg) {
            this.lTaxa.setSize(0, 0);
            this.lSeguroVida.setSize(0, 0);
        }
        return false;
    }
    
    public void init() {
        System.out.println("Banco BPI. Simulador de Cr\u00e9dito \u00e0 Habita\u00e7\u00e3o");
        System.out.println("Vers\u00e3o=" + this.version);
        if (this.getParameter("cliente") != null && !this.getParameter("cliente").equals("bancobpi")) {
            this.hideTAN_Seg = true;
        }
        else {
            this.hideTAN_Seg = false;
        }
        if (this.getParameter("localMode") != null) {
            this.localMode = true;
        }
        else {
            this.localMode = false;
        }
        if (this.getParameter("debug") != null) {
            this.debug = true;
        }
        if (this.getIParameter("condicoesEspeciais") != null) {
            this.condicoesEspeciais = true;
        }
        System.out.println("layout=" + this.getIParameter("layout"));
        if (this.getIParameter("regime") != null) {
            this.regime = Integer.parseInt(this.getIParameter("regime"));
        }
        try {
            if (this.getIParameter("impostoselo") != null) {
                this.IMPOSTO_SELO = new Double(this.getIParameter("impostoselo").replace(',', '.'));
            }
        }
        catch (Exception ex) {
            this.IMPOSTO_SELO = 0.0;
        }
        try {
            if (this.getIParameter("bonificacaospread") != null) {
                this.BONIFICACAOSPREAD = new Double(this.getIParameter("bonificacaospread").replace(',', '.'));
            }
        }
        catch (Exception ex2) {
            this.BONIFICACAOSPREAD = 0.0;
        }
        try {
            if (this.getIParameter("prazoCarencia") != null) {
                this.prazoCarencia = new Integer(this.getIParameter("prazoCarencia"));
                this.carencia = true;
            }
        }
        catch (Exception ex3) {}
        if (this.carencia) {
            if (this.regime == 3) {
                this.carencia = true;
                this.PRAZOCARENCIA_MAX = 3.0;
            }
            else if (this.regime == 0) {
                this.PRAZOCARENCIA_MAX = 5.0;
                this.carencia = true;
            }
            else {
                this.carencia = false;
                this.prazoCarencia = 0;
            }
            this.PRAZOCARENCIA_LIMITE = this.PRAZOCARENCIA_MAX;
        }
        if (this.carencia) {
            this.clientLayout = 6;
            final String iParameter = this.getIParameter("layout");
            if (iParameter != null && iParameter.equals("ingles")) {
                this.layoutIngles = true;
            }
        }
        else if (this.getIParameter("layout") != null) {
            if (this.getIParameter("layout").equals("sapo")) {
                this.clientLayout = 1;
            }
            else if (this.getIParameter("layout").equals("novo_sapo")) {
                this.clientLayout = 4;
            }
            else if (this.getIParameter("layout").equals("ingles")) {
                this.clientLayout = 2;
            }
            else if (this.getIParameter("layout").equals("allianz_mediadoras")) {
                this.clientLayout = 3;
            }
            else if (this.getIParameter("layout").equals("imobiliario")) {
                this.clientLayout = 0;
            }
            else if (this.getIParameter("layout").equals("imobiliarioHorizontal")) {
                this.clientLayout = 5;
            }
            else if (this.getIParameter("layout").equals("expresso")) {
                this.clientLayout = 7;
            }
        }
        if (this.getIParameter("ve") != null) {
            this.segundoMinimo = new Double(this.getIParameter("ve"));
        }
        else if (this.getIParameter("vo") != null) {
            this.segundoMinimo = new Double(this.getIParameter("vo"));
        }
        else if (this.getIParameter("oic") != null) {
            this.segundoMinimo = new Double(this.getIParameter("oic"));
        }
        else if (!this.condicoesEspeciais) {
            throw new RuntimeException("ve, vo ou oic em falta.");
        }
        try {
            this.TRCB = new Double(this.getIParameter("trcb"));
        }
        catch (NullPointerException ex4) {}
        try {
            this.TRCPE = new Double(this.getIParameter("trcpe"));
        }
        catch (NullPointerException ex5) {}
        this.simboloMoeda = (this.escudo ? this.simboloEscudo : this.simboloEuro);
        if (this.getIParameter("simulador") != null) {
            try {
                this.simulador = new Integer(this.getIParameter("simulador"));
                if (this.simulador < 1 || this.simulador > 14) {
                    this.simulador = 1;
                }
            }
            catch (NumberFormatException ex6) {
                this.simulador = 1;
            }
            if (this.debug) {
                System.out.println("Protocolo Id=" + this.simulador);
            }
        }
        this.selo = Double.valueOf(this.getIParameter("selo").replace(',', '.')) / 100.0 + 1.0;
        this.tipoPlano = 1;
        if (this.getIParameter("tipoplano") != null) {
            if (this.getIParameter("tipoplano").equals("prestmist")) {
                this.tipoPlano = 3;
            }
            else if (this.getIParameter("tipoplano").equals("prestvr")) {
                this.tipoPlano = 2;
            }
            else if (this.getIParameter("tipoplano").equals("prestconst")) {
                this.tipoPlano = 1;
            }
        }
        this.finalidade = 1;
        if (this.getIParameter("finalidade") != null) {
            if (this.getIParameter("finalidade").equals("aquisicao")) {
                this.finalidade = 1;
            }
            else if (this.getIParameter("finalidade").equals("construcao")) {
                this.finalidade = 3;
            }
            else if (this.getIParameter("finalidade").equals("obras")) {
                this.finalidade = 4;
            }
            else if (this.getIParameter("finalidade").equals("transferencia")) {
                this.finalidade = 2;
            }
            else if (this.getIParameter("finalidade").equals("lcppura")) {
                this.finalidade = 5;
            }
        }
        final String iParameter2 = this.getIParameter("taxas_fixas");
        if (iParameter2 != null && iParameter2.length() > 0) {
            this.eFixa = true;
            final StringTokenizer stringTokenizer = new StringTokenizer(iParameter2, "|");
            this.arrTaxasFixas = new double[stringTokenizer.countTokens()];
            for (int countTokens = stringTokenizer.countTokens(), i = 0; i < countTokens; ++i) {
                if (this.getIParameter("browser").compareTo("ie") == 0) {
                    this.arrTaxasFixas[i] = new Double(stringTokenizer.nextToken().replace(',', '.'));
                }
                else {
                    this.arrTaxasFixas[i] = new Double(stringTokenizer.nextToken());
                }
            }
        }
        else {
            this.currentTaxa = new Double(this.getIParameter("taxa").replace(',', '.')) * this.selo;
            this.arrTaxasFixas = new double[0];
            this.eFixa = false;
        }
        if (this.getIParameter("browser").compareTo("ie") == 0) {
            this.currentBonificacao = Double.valueOf(this.getIParameter("bonificacao").replace(',', '.'));
        }
        else {
            this.currentBonificacao = Double.valueOf(this.getIParameter("bonificacao"));
        }
        if (this.getIParameter("emprestimoini") != null) {
            this.EMPRESTIMO_INI = Double.valueOf(this.getIParameter("emprestimoini").replace(',', '.'));
        }
        else if (this.getIParameter("emprestimoinipercentual") != null) {
            this.EMPRESTIMO_INI = Double.valueOf(this.getIParameter("emprestimoinipercentual").replace(',', '.')) * Double.valueOf(this.getIParameter("financiamento").replace(',', '.'));
        }
        else {
            this.EMPRESTIMO_INI = Double.valueOf(this.getIParameter("financiamento").replace(',', '.'));
        }
        if (this.getIParameter("emprestimolcpini") != null) {
            this.EMPRESTIMO_COMPLEMENTAR_INI = Double.valueOf(this.getIParameter("emprestimolcpini").replace(',', '.'));
        }
        else {
            this.EMPRESTIMO_COMPLEMENTAR_INI = 0.0;
        }
        this.PRAZO_JUMP = Integer.parseInt(this.getIParameter("prazo_jump"));
        this.PRAZO_MIN_TAXAS = Integer.parseInt(this.getIParameter("prazo_min_taxas"));
        this.EMPRESTIMO_FINANCIAMENTO = Double.valueOf(this.getIParameter("financiamento").replace(',', '.'));
        if (this.getIParameter("emprestimomax") != null) {
            this.emprestimomax = Double.valueOf(this.getIParameter("emprestimomax").replace(',', '.'));
        }
        else {
            this.emprestimomax = this.getMaxFinanciamento();
        }
        if (this.getIParameter("financiamentolcp") != null) {
            this.EMPRESTIMO_FINANCIAMENTO_LCP = Double.valueOf(this.getIParameter("financiamentolcp").replace(',', '.'));
        }
        else {
            this.EMPRESTIMO_FINANCIAMENTO_LCP = 0.0;
        }
        if (this.EMPRESTIMO_MAX < 15000.0) {
            this.EMPRESTIMO_JUMP = 500.0;
        }
        else if (this.EMPRESTIMO_MAX < 30000.0) {
            this.EMPRESTIMO_JUMP = 1000.0;
        }
        else if (this.EMPRESTIMO_MAX < 100000.0) {
            this.EMPRESTIMO_JUMP = 5000.0;
        }
        else if (this.EMPRESTIMO_MAX < 250000.0) {
            this.EMPRESTIMO_JUMP = 5000.0;
        }
        else {
            this.EMPRESTIMO_JUMP = 10000.0;
        }
        if (this.getIParameter("garantia") != null && this.getIParameter("garantia").length() != 0) {
            this.garantia = Double.valueOf(this.getIParameter("garantia").replace(',', '.'));
        }
        else {
            this.garantia = this.EMPRESTIMO_FINANCIAMENTO;
        }
        if (this.getIParameter("limite") != null) {
            this.EMPRESTIMO_MAX = Double.valueOf(this.getIParameter("limite").replace(',', '.'));
        }
        else {
            this.EMPRESTIMO_MAX = this.EMPRESTIMO_FINANCIAMENTO * this.emprestimomax;
        }
        if (this.getIParameter("limitefinanciamentolcp") != null && this.EMPRESTIMO_FINANCIAMENTO_LCP > 0.0) {
            this.EMPRESTIMO_COMPLEMENTAR_MAX = Double.valueOf(this.getIParameter("limitefinanciamentolcp").replace(',', '.'));
        }
        else {
            this.EMPRESTIMO_COMPLEMENTAR_MAX = 0.0;
        }
        if (this.EMPRESTIMO_COMPLEMENTAR_MAX < 10000.0) {
            this.EMPRESTIMOLCP_JUMP = 100.0;
        }
        else if (this.EMPRESTIMO_MAX < 20000.0) {
            this.EMPRESTIMOLCP_JUMP = 200.0;
        }
        else if (this.EMPRESTIMO_MAX < 50000.0) {
            this.EMPRESTIMOLCP_JUMP = 500.0;
        }
        else {
            this.EMPRESTIMOLCP_JUMP = 1000.0;
        }
        if (this.getIParameter("emprestimomin") != null) {
            this.EMPRESTIMO_MIN = new Double(this.getIParameter("emprestimomin"));
        }
        else {
            this.EMPRESTIMO_MIN = 30000.0;
        }
        if (this.getIParameter("emprestimolcpmin") != null && this.EMPRESTIMO_FINANCIAMENTO_LCP > 0.0) {
            this.EMPRESTIMO_COMPLEMENTAR_MIN = new Double(this.getIParameter("emprestimolcpmin"));
        }
        else {
            this.EMPRESTIMO_COMPLEMENTAR_MIN = 0.0;
        }
        this.idade1 = Integer.parseInt(this.getIParameter("idade1"));
        this.idade2 = Integer.parseInt(this.getIParameter("idade2"));
        this.PRAZO_INI = Integer.parseInt(this.getIParameter("prazo"));
        this.PRAZO_MAX = Integer.parseInt(this.getIParameter("prazo_max"));
        if (this.getIParameter("prazo_limiteMax") != null) {
            this.PRAZO_LIMITE = new Integer(this.getIParameter("prazo_limiteMax"));
        }
        if (this.getIParameter("prazo_limiteMin") != null) {
            this.PRAZO_MIN = new Integer(this.getIParameter("prazo_limiteMin"));
        }
        if (this.PRAZO_INI > this.PRAZO_MAX) {
            this.PRAZO_INI = this.PRAZO_MAX;
        }
        (this.tabela = new Hashtable()).put("18", new Float(1.111));
        this.tabela.put("19", new Float(1.122));
        this.tabela.put("20", new Float(1.134));
        this.tabela.put("21", new Float(1.146));
        this.tabela.put("22", new Float(1.158));
        this.tabela.put("23", new Float(1.171));
        this.tabela.put("24", new Float(1.183));
        this.tabela.put("25", new Float(1.195));
        this.tabela.put("26", new Float(1.207));
        this.tabela.put("27", new Float(1.219));
        this.tabela.put("28", new Float(1.232));
        this.tabela.put("29", new Float(1.244));
        this.tabela.put("30", new Float(1.256));
        this.tabela.put("31", new Float(1.27));
        this.tabela.put("32", new Float(1.293));
        this.tabela.put("33", new Float(1.333));
        this.tabela.put("34", new Float(1.391));
        this.tabela.put("35", new Float(1.47));
        this.tabela.put("36", new Float(1.569));
        this.tabela.put("37", new Float(1.692));
        this.tabela.put("38", new Float(1.838));
        this.tabela.put("39", new Float(2.011));
        this.tabela.put("40", new Float(2.211));
        this.tabela.put("41", new Float(2.44));
        this.tabela.put("42", new Float(2.699));
        this.tabela.put("43", new Float(2.989));
        this.tabela.put("44", new Float(3.314));
        this.tabela.put("45", new Float(3.673));
        this.tabela.put("46", new Float(4.069));
        this.tabela.put("47", new Float(4.502));
        this.tabela.put("48", new Float(4.975));
        this.tabela.put("49", new Float(5.489));
        this.tabela.put("50", new Float(6.064f));
        this.tabela.put("51", new Float(6.673));
        this.tabela.put("52", new Float(7.346));
        this.tabela.put("53", new Float(8.09));
        this.tabela.put("54", new Float(8.914));
        this.tabela.put("55", new Float(9.823));
        this.tabela.put("56", new Float(10.829));
        this.tabela.put("57", new Float(11.939));
        this.tabela.put("58", new Float(13.166));
        this.tabela.put("59", new Float(14.519));
        this.tabela.put("60", new Float(16.014));
        this.tabela.put("61", new Float(17.662));
        this.tabela.put("62", new Float(19.479));
        this.tabela.put("63", new Float(21.481));
        this.tabela.put("64", new Float(23.686));
        this.tabela.put("65", new Float(26.113));
        this.tabela.put("66", new Float(28.783));
        this.tabela.put("67", new Float(31.716));
        this.tabela.put("68", new Float(34.938));
        this.tabela.put("69", new Float(38.472));
        this.tabela.put("70", new Float(42.346));
        this.tabela.put("71", new Float(46.586));
        this.tabela.put("72", new Float(51.223));
        this.tabela.put("73", new Float(56.286));
        this.tabela.put("74", new Float(61.808));
        this.tabela.put("75", new Float(67.818));
        this.originalTaxa = this.currentTaxa;
        this.maxTaxa = this.originalTaxa + 2.0;
        if (this.condicoesEspeciais) {
            this.setCondicoesEspeciais();
        }
        this.modalidade = Integer.parseInt(this.getIParameter("modalidade"));
        if (this.simulador != 6) {
            if (this.modalidade == 1 && Integer.parseInt(this.getIParameter("regime")) == 0) {
                this.simulador = 12;
            }
            else if (this.modalidade == 6 && Integer.parseInt(this.getIParameter("regime")) == 0) {
                this.simulador = 14;
            }
            try {
                if (Integer.parseInt(this.getIParameter("regime")) == 2) {
                    this.simulador = 13;
                }
            }
            catch (Exception ex7) {}
        }
        this.inValorResidual = this.getIParameter("valorresidual");
        this.currentTaxa = this.calcTaxaJuro(this.originalTaxa, this.EMPRESTIMO_INI + this.EMPRESTIMO_COMPLEMENTAR_INI, this.garantia);
        this.createLayout();
        this.initEstatisticas();
        if (this.useLink()) {
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        }
        this.calcTaxaFixa();
    }
    
    private void initEstatisticas() {
        if (this.localMode) {
            return;
        }
        if (this.getIParameter("enableStats") != null && this.getIParameter("enableStats").equals("1")) {
            this.enableStats = true;
            System.out.println("Stats ON.");
        }
        else {
            this.enableStats = false;
            System.out.println("Stats OFF.");
        }
        if (this.enableStats) {
            if (this.getIParameter("siteId") == null) {
                this.manager = new StatsManager(this.getCodeBase(), 3);
            }
            else {
                try {
                    this.siteId = new Integer(this.getIParameter("siteId"));
                    this.manager = new StatsManager(this.getCodeBase(), this.siteId, 3);
                }
                catch (NumberFormatException ex) {
                    this.siteId = 1000;
                    this.manager = new StatsManager(this.getCodeBase(), this.siteId, 3, this.getIParameter("siteId"));
                }
            }
            if (this.getIParameter("statsDebug") != null && this.getIParameter("statsDebug").equals("1")) {
                this.manager.setDebug(true);
                System.out.println("Stats Debugging ON");
            }
            if (this.getIParameter("enableFullStats") != null && this.getIParameter("enableFullStats").equals("1")) {
                this.enableFullStats = true;
            }
        }
    }
    
    private boolean inLink(final MouseEvent mouseEvent) {
        if (this.clientLayout == 4) {
            if (mouseEvent.getX() < 600) {
                return false;
            }
            if (mouseEvent.getX() > 680) {
                return false;
            }
        }
        else if (this.clientLayout == 5) {
            if (mouseEvent.getX() < 540) {
                return false;
            }
            if (mouseEvent.getX() > 609) {
                return false;
            }
            if (mouseEvent.getY() < 56) {
                return false;
            }
            if (mouseEvent.getY() > 65) {
                return false;
            }
        }
        else if (this.clientLayout == 7) {
            if (mouseEvent.getX() < 319) {
                return false;
            }
            if (mouseEvent.getX() > 399) {
                return false;
            }
            if (mouseEvent.getY() < 145) {
                return false;
            }
            if (mouseEvent.getY() > 156) {
                return false;
            }
        }
        else {
            if (mouseEvent.getX() < this.imageArray[2].getWidth(null) - this.LINK_MARGIN_NORMAL_LAYOUT.x - this.LINK_DIMENSION_NORMAL_LAYOUT.width) {
                return false;
            }
            if (mouseEvent.getY() < this.imageArray[2].getHeight(null) - this.LINK_MARGIN_NORMAL_LAYOUT.y - this.LINK_DIMENSION_NORMAL_LAYOUT.height) {
                return false;
            }
            if (mouseEvent.getX() > this.imageArray[2].getWidth(null) - this.LINK_MARGIN_NORMAL_LAYOUT.x) {
                return false;
            }
            if (mouseEvent.getY() > this.imageArray[2].getHeight(null) - this.LINK_MARGIN_NORMAL_LAYOUT.y) {
                return false;
            }
        }
        return true;
    }
    
    private void insertInitialStat() {
        if (this.enableStats) {
            (this.record = new Record()).setVersao(this.version);
            final Enumeration<String> keys = ((Hashtable<String, V>)this.parameters).keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                this.record.addInput(s, (String)this.parameters.get(s));
            }
            if (this.getParameter("cliente") != null && this.getParameter("cliente").length() != 0) {
                this.record.addAux("cliente", this.getParameter("cliente"));
            }
            this.record.addOutput("emprestimo", new Double(this.sliderEmprestimo.getValue()).toString());
            this.record.addOutput("prazo", new Double(this.sliderPrazo.getValue()).toString());
            if (this.carencia) {
                this.record.addOutput("prazoCarencia", new Double(this.sliderPrazoCarencia.getValue()).toString());
            }
            this.record.addOutput("prestacao", new Double(this.sliderPrestacao.getValue()).toString());
            this.record.addOutput("seguroVida", new Double(this.seguroVida).toString());
            this.record.addOutput("tan", new Double(this.currentTaxa).toString());
            this.manager.addSimulationRecord(this.record);
            this.initialStatInserted = true;
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.inValorResidual = itemEvent.getItem().toString().substring(0, itemEvent.getItem().toString().length() - 1);
        if (this.EMPRESTIMO_FINANCIAMENTO_LCP > 0.0) {
            this.calcPrestacao(this.sliderEmprestimo.getValue(), this.sliderPrazo.getValue(), this.currentTaxa, this.TRCB, 0.0);
            this.calcPrestacao(this.sliderEmprestimoLCP.getValue(), this.sliderPrazo.getValue(), this.currentTaxa, this.TRCB, this.IMPOSTO_SELO);
        }
        else {
            this.calcPrestacao(this.sliderEmprestimo.getValue(), this.sliderPrazo.getValue(), this.currentTaxa, this.TRCB, this.IMPOSTO_SELO);
        }
        this.updateDisplay();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (!this.inLink(mouseEvent)) {
            return;
        }
        try {
            final JSObject window = JSObject.getWindow((Applet)this);
            final String string = "?financiamento=" + format(this.sliderEmprestimo.getValue(), 2, "") + "&prazo=" + format(this.sliderPrazo.getValue(), 2, "") + "&prestacao=" + format(this.sliderPrestacao.getValue(), 2, "") + "&valorImovel=" + format(this.garantia, 2, "");
            String s;
            if (this.hideTAN_Seg) {
                s = "window.open('http://" + this.getIParameter("link") + string + "', '_blank', '')";
            }
            else {
                s = "window.open('http://" + this.getIParameter("link") + "', '_blank', '')";
            }
            System.out.println(s);
            window.eval(s);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        try {
            if (!this.inLink(mouseEvent)) {
                this.setCursor(new Cursor(0));
                return;
            }
            this.setCursor(new Cursor(12));
        }
        catch (Exception ex) {}
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        try {
            this.setCursor(new Cursor(0));
        }
        catch (Exception ex) {}
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        try {
            if (!this.inLink(mouseEvent)) {
                this.setCursor(new Cursor(0));
                return;
            }
            this.setCursor(new Cursor(12));
        }
        catch (Exception ex) {}
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        if (this.offScreen == null) {
            this.createOffScreen();
        }
        if (this.offScreen == null) {
            for (int i = 0; i < 5; ++i) {
                this.createOffScreen();
                if (this.offScreen != null) {
                    i = 5;
                }
            }
        }
        if (this.image_index == this.imageArray.length - 1) {
            this.offScreen_g.drawImage(this.imageArray[2], 0, 0, this);
        }
        graphics.drawImage(this.offScreen, 0, 0, this);
    }
    
    private void processSliderEmprestimo() {
        this.lastInput = "sliderEmprestimo";
        double n = this.sliderEmprestimo.getValue();
        if (n % this.EMPRESTIMO_JUMP != 0.0) {
            if (n % this.EMPRESTIMO_JUMP < this.EMPRESTIMO_JUMP / 2.0) {
                if (this.EMPRESTIMO_MAX < this.EMPRESTIMO_JUMP * (int)(n / this.EMPRESTIMO_JUMP + 1.0)) {
                    this.sliderEmprestimo.setValue(this.EMPRESTIMO_MAX);
                }
                else {
                    this.sliderEmprestimo.setValue(this.EMPRESTIMO_JUMP * (int)(n / this.EMPRESTIMO_JUMP) - this.EMPRESTIMO_MIN);
                }
            }
            else {
                this.sliderEmprestimo.setValue(Math.min(this.EMPRESTIMO_JUMP * (int)(n / this.EMPRESTIMO_JUMP + 1.0) - this.EMPRESTIMO_MIN, this.EMPRESTIMO_MAX));
            }
            n = this.sliderEmprestimo.getValue() - this.EMPRESTIMO_MIN;
        }
        else {
            this.sliderEmprestimo.setValue(n - this.EMPRESTIMO_MIN);
        }
        final double maxFinanciamento = this.getMaxFinanciamento();
        if (n >= maxFinanciamento) {
            n = maxFinanciamento;
        }
        else if (n < this.EMPRESTIMO_MIN) {
            n = this.EMPRESTIMO_MIN;
        }
        this.sliderEmprestimo.setValue(n - this.EMPRESTIMO_MIN);
        if (this.EMPRESTIMO_FINANCIAMENTO_LCP <= 0.0) {
            this.currentTaxa = this.calcTaxaJuro(this.originalTaxa, n, this.garantia);
            this.sliderPrestacao.setValue(this.calcPrestacao(n, this.sliderPrazo.getValue(), this.currentTaxa, this.TRCB, 0.0));
        }
        else {
            if (this.sliderEmprestimoLCP.getValue() - this.EMPRESTIMO_COMPLEMENTAR_MIN > this.EMPRESTIMO_MAX - this.sliderEmprestimo.getValue()) {
                this.sliderEmprestimoLCP.setValue(this.EMPRESTIMO_MAX - this.sliderEmprestimo.getValue());
            }
            if (this.getPrazoMaxFG() < this.sliderPrazo.getValue()) {
                this.sliderPrazo.setValue(this.getPrazoMaxFG() - this.PRAZO_MIN);
            }
            this.currentTaxa = this.calcTaxaJuro(this.originalTaxa, this.sliderEmprestimo.getValue() + this.sliderEmprestimoLCP.getValue() - this.EMPRESTIMO_COMPLEMENTAR_MIN, this.garantia);
            this.processSliderPrazo();
        }
        this.processSliderPrazoCarencia(false);
        this.updateDisplay();
    }
    
    private void processSliderEmprestimoLCP() {
        this.lastInput = "sliderEmprestimoLCP";
        double value = this.sliderEmprestimoLCP.getValue() - this.EMPRESTIMO_COMPLEMENTAR_MIN;
        if (value % this.EMPRESTIMOLCP_JUMP != 0.0) {
            if (value - value % this.EMPRESTIMOLCP_JUMP > this.getMaxPercentFinanciamento() * this.garantia - this.sliderEmprestimo.getValue()) {
                this.sliderEmprestimoLCP.setValue(this.getMaxPercentFinanciamento() * this.garantia - this.sliderEmprestimo.getValue() - this.EMPRESTIMO_COMPLEMENTAR_MIN);
            }
            else if (value % this.EMPRESTIMOLCP_JUMP < this.EMPRESTIMOLCP_JUMP / 2.0) {
                this.sliderEmprestimoLCP.setValue((int)value - (int)(value % this.EMPRESTIMOLCP_JUMP) - this.EMPRESTIMO_COMPLEMENTAR_MIN);
            }
            else {
                this.sliderEmprestimoLCP.setValue((int)value - (int)(value % this.EMPRESTIMOLCP_JUMP) + this.EMPRESTIMOLCP_JUMP - this.EMPRESTIMO_COMPLEMENTAR_MIN);
            }
            value = this.sliderEmprestimoLCP.getValue();
        }
        else {
            this.sliderEmprestimoLCP.setValue((int)value - this.EMPRESTIMO_COMPLEMENTAR_MIN);
        }
        final double n = this.getMaxPercentFinanciamento() * this.garantia - this.sliderEmprestimo.getValue();
        if (value > n) {
            value = n;
            this.sliderEmprestimoLCP.setValue(value);
        }
        if (value < this.EMPRESTIMO_COMPLEMENTAR_MIN) {
            value = this.EMPRESTIMO_COMPLEMENTAR_MIN;
            this.sliderEmprestimoLCP.setValue(value);
        }
        this.currentTaxa = this.calcTaxaJuro(this.originalTaxa, this.sliderEmprestimo.getValue() + this.sliderEmprestimoLCP.getValue() - this.EMPRESTIMO_COMPLEMENTAR_MIN, this.garantia);
        this.processSliderPrazoCarencia(false);
        this.currentTaxa = this.calcTaxaJuro(this.originalTaxa, value + this.sliderEmprestimo.getValue(), this.garantia);
        this.sliderEmprestimoLCP.setValue(value);
        this.updateDisplay();
    }
    
    private void processSliderPrazo() {
        this.lastInput = "sliderPrazo";
        final int n = (int)Math.round(this.sliderPrazo.getValue());
        if (n > this.PRAZO_MAX) {
            this.sliderPrazo.setValue(this.PRAZO_MAX - this.PRAZO_MIN);
        }
        if (n < this.PRAZO_MIN_TAXAS) {
            this.sliderPrazo.setValue(this.PRAZO_MIN_TAXAS - this.PRAZO_MIN);
            final int n2 = (int)Math.round(this.sliderPrazo.getValue());
        }
        else if (n % this.PRAZO_JUMP != 0.0) {
            if (n % this.PRAZO_JUMP <= (int)((this.PRAZO_JUMP - 1.0) / 2.0)) {
                this.sliderPrazo.setValue(n - n % this.PRAZO_JUMP - this.PRAZO_MIN);
            }
            else {
                this.sliderPrazo.setValue(n + (this.PRAZO_JUMP - n % this.PRAZO_JUMP) - this.PRAZO_MIN);
            }
            final int n3 = (int)Math.round(this.sliderPrazo.getValue());
        }
        else {
            this.sliderPrazo.setValue(n - this.PRAZO_MIN);
        }
        if (this.sliderPrazo.getValue() > this.PRAZO_MAX) {
            this.sliderPrazo.setValue(this.PRAZO_MAX - this.PRAZO_MIN);
            final int n4 = (int)Math.round(this.sliderPrazo.getValue());
        }
        this.calcTaxaFixa();
        this.processSliderPrazoCarencia(false);
        final double maxFinanciamento = this.getMaxFinanciamento();
        if (this.sliderEmprestimo.getValue() > maxFinanciamento) {
            this.sliderEmprestimo.setValue(maxFinanciamento - this.EMPRESTIMO_MIN);
        }
        if (this.EMPRESTIMO_FINANCIAMENTO_LCP > 0.0 && this.sliderEmprestimoLCP.getValue() - this.EMPRESTIMO_COMPLEMENTAR_MIN + this.sliderEmprestimo.getValue() > this.getMaxPercentFinanciamento() * this.garantia) {
            this.sliderEmprestimo.setValue(maxFinanciamento - this.EMPRESTIMO_MIN - this.EMPRESTIMO_COMPLEMENTAR_MIN);
            if (this.sliderEmprestimoLCP.getValue() - this.EMPRESTIMO_COMPLEMENTAR_MIN > this.getMaxPercentFinanciamento() * this.garantia - this.sliderEmprestimo.getValue()) {
                this.sliderEmprestimoLCP.setValue(this.getMaxPercentFinanciamento() * this.garantia - this.sliderEmprestimo.getValue());
            }
        }
        this.currentTaxa = this.calcTaxaJuro(this.originalTaxa, this.sliderEmprestimo.getValue() + this.sliderEmprestimoLCP.getValue() - this.EMPRESTIMO_COMPLEMENTAR_MIN, this.garantia);
        this.sliderEmprestimo.getValue();
        this.updateDisplay();
    }
    
    private void processSliderPrazoCarencia(final boolean b) {
        if (!this.carencia) {
            return;
        }
        if (b) {
            this.lastInput = "sliderPrazoCarencia";
        }
        int n = (int)Math.round(this.sliderPrazoCarencia.getValue());
        final int n2 = (int)Math.round(this.sliderPrazo.getValue());
        double n3;
        if (this.EMPRESTIMO_FINANCIAMENTO_LCP > 0.0) {
            n3 = (this.sliderEmprestimo.getValue() + (this.sliderEmprestimoLCP.getValue() - this.EMPRESTIMO_COMPLEMENTAR_MIN)) / this.garantia * 100.0;
        }
        else {
            n3 = this.sliderEmprestimo.getValue() / this.garantia * 100.0;
        }
        int n4;
        if (this.regime == 3) {
            if (n3 <= 75.0) {
                n4 = Math.min(n2 - 1, 3);
            }
            else {
                n4 = 0;
            }
        }
        else if (this.regime == 0) {
            if (n3 > 80.0) {
                n4 = 0;
            }
            else {
                n4 = Math.min(n2 - 1, 5);
            }
            if (this.regime == 0 && this.sliderPrazo.getValue() > 40.0) {
                n4 = 0;
            }
        }
        else {
            n4 = 0;
        }
        if (n > n4) {
            n = n4;
        }
        this.sliderPrazoCarencia.setValue(n - this.PRAZOCARENCIA_MIN);
        if (b) {
            this.updateDisplay();
        }
    }
    
    private void processSliderPrestacao() {
        this.lastInput = "sliderPrestacao";
        final int n = (int)Math.round(this.sliderPrazo.getValue());
        double value = this.calcEmprestimo(this.sliderPrestacao.getValue(), n, this.currentTaxa, this.TRCB);
        final double maxFinanciamento = this.getMaxFinanciamento();
        if (value > maxFinanciamento) {
            value = maxFinanciamento;
            this.sliderEmprestimo.setValue(value);
        }
        if (value > this.EMPRESTIMO_MAX) {
            value = this.EMPRESTIMO_MAX;
            this.sliderPrestacao.setValue(this.calcPrestacao(value, n, this.currentTaxa, this.TRCB, this.IMPOSTO_SELO));
        }
        else if (value < this.EMPRESTIMO_MIN) {
            value = this.EMPRESTIMO_MIN;
            this.sliderPrestacao.setValue(this.calcPrestacao(value, n, this.currentTaxa, this.TRCB, this.IMPOSTO_SELO));
        }
        this.currentTaxa = this.calcTaxaJuro(this.originalTaxa, value, this.garantia);
        this.sliderPrestacao.setValue(this.calcPrestacao(value, n, this.currentTaxa, this.TRCB, this.IMPOSTO_SELO));
        this.sliderEmprestimo.setValue(value - this.EMPRESTIMO_MIN);
        this.processSliderEmprestimo();
        this.updateDisplay();
    }
    
    private void processTextEmprestimo() {
        this.lastInput = "textboxEmprestimo";
        double value = new Double(this.unformatCurrency(this.tfEmprestimo.getText()));
        if (this.unformatCurrency(this.tfEmprestimo.getText()).length() == 0 || value < 0.0) {
            this.tfEmprestimo.setText(format(0.0, 0, ""));
        }
        if (value > this.EMPRESTIMO_MAX) {
            value = this.EMPRESTIMO_MAX;
        }
        else if (value < this.EMPRESTIMO_MIN) {
            value = this.EMPRESTIMO_MIN;
        }
        this.sliderEmprestimo.setValue(value);
        this.tfEmprestimo.setText(format(value, 2, ""));
        final double value2 = this.sliderEmprestimo.getValue();
        Math.round(this.sliderPrazo.getValue());
        this.calcTaxaFixa();
        this.currentTaxa = this.calcTaxaJuro(this.originalTaxa, value2, this.garantia);
        this.updateDisplay();
    }
    
    public boolean ready() {
        return this.started;
    }
    
    private void setCondicoesEspeciais() {
        if (this.getIParameter("limiteFinanciamento") != null) {
            this.CE_limiteFinanciamento = new Double(this.getIParameter("limiteFinanciamento"));
        }
        else {
            this.segundoMinimo = this.EMPRESTIMO_FINANCIAMENTO;
        }
        if (this.getIParameter("spreadUnico") != null) {
            this.CE_spreadUnico = new Double(this.getIParameter("spreadUnico"));
            return;
        }
        this.CE_matriz = new Matriz(this.getIParameter("spreadMatrizColunas"), this.getIParameter("spreadMatrizLinhas"), this.getIParameter("spreadMatrizValores"));
    }
    
    private double[] split(String substring, final char c) {
        if (substring == null || substring.length() == 0) {
            return new double[0];
        }
        if (substring.indexOf(c) == -1) {
            return new double[] { new Double(substring) };
        }
        final Vector vector = new Vector<String>();
        for (int i = substring.indexOf(c); i != -1; i = substring.indexOf(c)) {
            vector.addElement(substring.substring(0, i));
            substring = substring.substring(i + 1);
        }
        vector.addElement(substring);
        final double[] array = new double[vector.size()];
        for (int j = 0; j < array.length; ++j) {
            array[j] = new Double(vector.elementAt(j));
        }
        return array;
    }
    
    public void start() {
        this.started = true;
    }
    
    private String unformatCurrency(final String s) {
        String string;
        int index;
        for (string = s; (index = string.indexOf(".")) != -1; string = String.valueOf(string.substring(0, index)) + string.substring(index + 1)) {}
        return string;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void updateDisplay() {
        double calcPrestacao = this.calcPrestacao(this.sliderEmprestimo.getValue(), this.sliderPrazo.getValue(), this.currentTaxa, this.TRCB, 0.0);
        if (this.EMPRESTIMO_FINANCIAMENTO_LCP > 0.0) {
            if (this.carencia) {
                calcPrestacao = this.calcPrestacao(this.sliderEmprestimo.getValue(), this.sliderPrazo.getValue() - this.sliderPrazoCarencia.getValue(), this.currentTaxa, this.TRCB, 0.0) + this.calcPrestacao(this.sliderEmprestimoLCP.getValue(), this.sliderPrazo.getValue() - this.sliderPrazoCarencia.getValue(), this.currentTaxa, this.TRCB, this.IMPOSTO_SELO);
            }
            else {
                calcPrestacao += this.calcPrestacao(this.sliderEmprestimoLCP.getValue() - this.EMPRESTIMO_COMPLEMENTAR_MIN, this.sliderPrazo.getValue(), this.currentTaxa, this.TRCB, this.IMPOSTO_SELO);
            }
        }
        double calcPrestacao2 = 0.0;
        if (this.carencia) {
            calcPrestacao2 = this.calcPrestacao(this.sliderEmprestimo.getValue(), this.sliderPrazo.getValue() - this.sliderPrazoCarencia.getValue(), this.currentTaxa, this.TRCB, 0.0);
            if (this.EMPRESTIMO_FINANCIAMENTO_LCP > 0.0) {
                calcPrestacao2 += this.calcPrestacao(this.sliderEmprestimoLCP.getValue() - this.EMPRESTIMO_COMPLEMENTAR_MIN, this.sliderPrazo.getValue() - this.sliderPrazoCarencia.getValue(), this.currentTaxa, this.TRCB, this.IMPOSTO_SELO);
            }
        }
        if (this.EMPRESTIMO_FINANCIAMENTO_LCP <= 0.0) {
            this.sliderPrestacao.setValue(calcPrestacao);
        }
        this.tfEmprestimo.setText(String.valueOf(this.simboloMoeda) + " " + format(this.sliderEmprestimo.getValue(), 2, "."));
        if (this.lEmprestimo != null) {
            this.lEmprestimo.setText(String.valueOf(this.simboloMoeda) + " " + format(this.sliderEmprestimo.getValue(), 2, "."));
        }
        this.lPrazo.setText(String.valueOf(String.valueOf((int)this.sliderPrazo.getValue())) + " anos");
        if (this.carencia) {
            this.lPrazoCarencia.setText(String.valueOf(String.valueOf((int)this.sliderPrazoCarencia.getValue())) + " anos");
            this.lPrestacaoCarencia.setText(String.valueOf(this.simboloMoeda) + " " + format(this.calcPrestacaoCarencia(), 2, "."));
        }
        if (this.EMPRESTIMO_FINANCIAMENTO_LCP > 0.0) {
            if (this.sliderEmprestimoLCP.getValue() <= 0.0) {
                this.tfEmprestimoLCP.setText(String.valueOf(this.simboloMoeda) + " " + format(this.EMPRESTIMO_COMPLEMENTAR_MIN, 2, "."));
            }
            else {
                this.tfEmprestimoLCP.setText(String.valueOf(this.simboloMoeda) + " " + format(this.sliderEmprestimoLCP.getValue() - this.EMPRESTIMO_COMPLEMENTAR_MIN, 2, "."));
            }
        }
        if (this.carencia) {
            this.lPrestacao.setText(String.valueOf(this.simboloMoeda) + " " + format(calcPrestacao2, 2, "."));
        }
        else {
            this.lPrestacao.setText(String.valueOf(this.simboloMoeda) + " " + format(calcPrestacao, 2, "."));
        }
        if (this.EMPRESTIMO_FINANCIAMENTO_LCP > 0.0) {
            this.seguroVida = this.calcSeguroVida(this.sliderEmprestimo.getValue() + this.sliderEmprestimoLCP.getValue() - this.EMPRESTIMO_COMPLEMENTAR_MIN, this.garantia, this.idade1, this.idade2, this.regime);
        }
        else {
            this.seguroVida = this.calcSeguroVida(this.sliderEmprestimo.getValue(), this.garantia, this.idade1, this.idade2, this.regime);
        }
        this.lSeguroVida.setText(String.valueOf(this.simboloMoeda) + " " + format(this.seguroVida, 2, "."));
        this.lTaxa.setText(String.valueOf(format(this.currentTaxa, 3, "")) + " %");
        if (this.enableStats) {
            if (!this.initialStatInserted) {
                this.insertInitialStat();
            }
            if (this.enableFullStats && ((this.lastInput.equals("sliderEmprestimo") && this.sliderEmprestimo.mouseUp) || (this.lastInput.equals("sliderEmprestimoLCP") && this.sliderEmprestimoLCP.mouseUp) || (this.lastInput.equals("sliderPrazo") && this.sliderPrazo.mouseUp) || (this.lastInput.equals("sliderPrazoCarencia") && this.sliderPrazoCarencia.mouseUp) || (this.lastInput.equals("sliderPrestacao") && this.sliderPrestacao.mouseUp) || this.lastInput.equals("textboxEmprestimo"))) {
                (this.record = new Record()).addOutput("emprestimo", new Double(this.sliderEmprestimo.getValue()).toString());
                this.record.addOutput("emprestimolcp", new Double(this.sliderEmprestimoLCP.getValue()).toString());
                this.record.addOutput("prazo", new Double(this.sliderPrazo.getValue()).toString());
                this.record.addOutput("prestacao", new Double(this.sliderPrestacao.getValue()).toString());
                this.record.addOutput("seguroVida", new Double(this.seguroVida).toString());
                this.record.addOutput("tan", new Double(this.currentTaxa).toString());
                if (this.carencia) {
                    this.record.addOutput("prazoCarencia", new Double(this.sliderPrazoCarencia.getValue()).toString());
                    this.record.addOutput("prestacaoCarencia", new Double(calcPrestacao2).toString());
                }
                this.record.addAux("inputAlterado", this.lastInput);
                this.manager.addSimulationRecord(this.record);
            }
        }
    }
    
    private boolean useLink() {
        return this.getIParameter("uselink") != null && this.getIParameter("uselink").equals("1");
    }
    
    public void waitForImage(final Component component, final Image image) {
        final MediaTracker mediaTracker = new MediaTracker(component);
        try {
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
    }
    
    class TextBox extends TextField
    {
        private double currentValue;
        
        public String getText() {
            return super.getText();
        }
        
        public TextBox() {
            this.currentValue = 0.0;
        }
        
        public TextBox(final String s) {
            super(s);
            this.currentValue = 0.0;
        }
    }
}
