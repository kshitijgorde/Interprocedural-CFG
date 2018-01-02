import java.awt.Graphics;
import java.awt.MediaTracker;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.awt.CheckboxGroup;
import java.awt.event.MouseListener;
import java.awt.GridLayout;
import java.awt.Component;
import java.applet.Applet;
import java.net.URLConnection;
import java.io.DataInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.io.InputStream;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.LayoutManager;
import java.util.Date;
import java.util.Vector;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Scrollbar;
import java.awt.Checkbox;
import com.pzzl.utils.TextCanvas;
import java.awt.List;
import com.pzzl.utils.ImageButton;
import java.awt.Font;
import java.awt.Label;
import com.pzzl.utils.ImageHyperLinkPanel;
import com.pzzl.utils.ImagePanel;
import java.awt.Color;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class SetTogether extends PZZLApplet
{
    String \u026e;
    final int \u0261 = 25;
    final int \u026f = 5;
    final int \u0270 = 4;
    Image \u0271;
    Image \u0272;
    Image \u0273;
    Image \u0274;
    Image \u0275;
    Image \u0276;
    Image \u0277;
    Image \u0278;
    Image \u0279;
    Image \u027a;
    Image \u027b;
    Image \u027c;
    Image \u027d;
    Image \u027e;
    Image[] \u027f;
    Image[] \u0280;
    Image[] \u0281;
    Image \u0282;
    Image \u0283;
    Image \u0284;
    Image \u0285;
    Image \u0286;
    Image \u0287;
    Image[] \u0288;
    Image[] \u0289;
    Image[] \u028a;
    Image[] \u028b;
    final int \u028c = 6;
    int \u028d;
    int \u028e;
    int \u028f;
    final int \u0290 = 82;
    Image \u0291;
    Image \u0292;
    Image[] \u0251;
    Color \u0293;
    Color \u0294;
    Color \u00c9;
    Color \u0295;
    Color \u0296;
    Color \u0297;
    Color \u0298;
    Color \u0299;
    Color \u029a;
    Color \u029b;
    Color \u029c;
    Color \u029d;
    Color \u029e;
    Color \u029f;
    Color \u02a0;
    Color \u02a1;
    String[] \u02a2;
    String \u02a3;
    boolean \u01b2;
    boolean \u02a4;
    String[] \u02a5;
    Image[] \u02a6;
    Image \u02a7;
    Image \u02a8;
    Image \u02b0;
    Image \u02b1;
    Image \u02b2;
    Image \u02b3;
    Image \u02b4;
    Image \u02b5;
    Image \u02b6;
    Image \u02b7;
    Image \u02b8;
    ImagePanel \u01c7;
    int \u01c8;
    int \u01c9;
    int \u01ca;
    int \u01cb;
    Color \u01cc;
    Color \u01e7;
    String \u01e8;
    ImagePanel \u02bb;
    int \u02bc;
    int \u02bd;
    int \u02be;
    int \u02bf;
    Color \u02c0;
    Color \u02c1;
    String \u02d0;
    ImagePanel \u02d1;
    int \u02e0;
    int \u02e1;
    int \u02e2;
    int \u02e3;
    Color \u02e4;
    Color \u0388;
    String \u0389;
    int \u038a;
    int \u038e;
    int \u038f;
    Color \u0390;
    Color \u0391;
    String \u0392;
    ImagePanel \u0393;
    int \u0394;
    int \u0395;
    int \u0396;
    int \u0397;
    Color \u0398;
    Color \u0399;
    String \u039a;
    ImagePanel \u039b;
    int \u039c;
    int \u039d;
    int \u039e;
    int \u039f;
    Color \u03a0;
    Color \u03a1;
    String \u03a3;
    ImageHyperLinkPanel \u03a4;
    int \u03a5;
    int \u03a6;
    int \u03a7;
    int \u03a8;
    Color \u03a9;
    Color \u03aa;
    String \u03ab;
    ImagePanel \u03ac;
    int \u03ad;
    int \u03ae;
    int \u03af;
    int \u03b0;
    Color \u03b1;
    Color \u03b2;
    String \u03b3;
    ImagePanel \u03b4;
    int \u03b5;
    int \u03b6;
    int \u03b7;
    int \u03b8;
    Color \u03b9;
    Color \u03ba;
    String \u03bb;
    Label \u03bc;
    int \u03bd;
    int \u03be;
    int \u03bf;
    int \u03c0;
    String \u03c1;
    Font \u03c2;
    Color \u03c3;
    Color \u03c4;
    SetPanel \u03c5;
    ImagePanel \u03c6;
    ImagePanel \u03c7;
    ImagePanel \u03c8;
    ImagePanel \u03c9;
    ImagePanel \u03ca;
    final int \u03cb = 1;
    int \u03cc;
    Label \u03cd;
    Label \u03ce;
    Label \u03d0;
    ImageButton \u03d1;
    Font \u03d2;
    Font \u03d3;
    Color \u03d4;
    Color \u03d5;
    Color \u03d6;
    ImagePanel \u03e2;
    int \u03e3;
    int \u03e4;
    int \u03e5;
    int \u03e6;
    Color \u03e7;
    Color \u03e8;
    String \u03e9;
    ImagePanel \u03ea;
    int \u03eb;
    int \u03ec;
    int \u03ed;
    int \u03ee;
    Color \u03ef;
    Color \u03f0;
    String \u03f1;
    int \u03f2;
    int \u03f3;
    int \u0401;
    int \u0402;
    String \u0403;
    Color \u0404;
    String \u0405;
    int \u0406;
    int \u0407;
    int \u0408;
    int \u0409;
    Color \u040a;
    String \u040b;
    int \u040c;
    int \u040e;
    int \u040f;
    int \u0410;
    Color \u0411;
    String \u0412;
    int \u0413;
    int \u0414;
    int \u0415;
    int \u0416;
    Color \u0417;
    int \u0418;
    int \u0419;
    int \u041a;
    int \u041b;
    Color \u041c;
    Color \u041d;
    Color \u041e;
    int \u041f;
    int \u0420;
    int \u0421;
    int \u0422;
    Color \u0423;
    Color \u0424;
    int \u0425;
    int \u0426;
    int \u0427;
    int \u0428;
    Color \u0429;
    Color \u042a;
    String \u042b;
    ImageButton \u042c;
    int \u042d;
    int \u042e;
    int \u042f;
    int \u0430;
    String \u0431;
    ImageButton \u0432;
    int \u0433;
    int \u0434;
    int \u0435;
    int \u0436;
    String \u0437;
    int \u0438;
    int \u0439;
    int \u043a;
    int \u043b;
    Color \u043c;
    Color \u043d;
    String \u043e;
    ImagePanel \u043f;
    int \u0440;
    int \u0441;
    int \u0442;
    int \u0443;
    Color \u0444;
    Color \u0445;
    String \u0446;
    int \u0447;
    int \u0448;
    int \u0449;
    int \u044a;
    String \u044b;
    Color \u044c;
    String \u044d;
    int \u044e;
    int \u044f;
    int \u0451;
    int \u0452;
    Color \u0453;
    List \u0454;
    int \u0455;
    int \u0456;
    int \u0457;
    int \u0458;
    Color \u0459;
    Color \u045a;
    int \u045b;
    int \u045c;
    int \u045e;
    int \u045f;
    Color \u0460;
    Color \u0461;
    String \u0462;
    int \u0463;
    int \u0464;
    int \u0465;
    int \u0466;
    Color \u0467;
    Color \u0468;
    String \u0469;
    ImageButton[] \u046a;
    Color \u046b;
    ImagePanel \u046c;
    int \u046d;
    int \u046e;
    int \u046f;
    int \u0470;
    Color \u0471;
    Color \u0472;
    String \u0473;
    int \u0474;
    int \u0475;
    int \u0476;
    int \u0477;
    String \u0478;
    Color \u0479;
    String \u047a;
    int \u047b;
    int \u047c;
    int \u047d;
    int \u047e;
    Color \u047f;
    String \u0480;
    int \u0481;
    int \u0490;
    int \u0491;
    int \u0492;
    Color \u0493;
    String \u0494;
    int \u0495;
    int \u0496;
    int \u0497;
    int \u0498;
    Color \u0499;
    int \u049a;
    int \u049b;
    int \u049c;
    int \u049d;
    Color \u049e;
    Color \u049f;
    int \u04a0;
    int \u04a1;
    int \u04a2;
    int \u04a3;
    Color \u04a4;
    Color \u04a5;
    int \u04a6;
    int \u04a7;
    int \u04a8;
    int \u04a9;
    Color \u04aa;
    Color \u04ab;
    String \u04ac;
    int \u04ad;
    int \u04ae;
    int \u04af;
    int \u04b0;
    Color \u04b1;
    Color \u04b2;
    String \u04b3;
    ImagePanel \u01e9;
    int \u01ea;
    int \u01eb;
    int \u01ec;
    int \u01ed;
    Color \u04b4;
    Color \u01f1;
    String \u01f2;
    Label \u04b5;
    int \u04b6;
    int \u04b7;
    int \u04b8;
    int \u04b9;
    String \u04ba;
    Color \u04bb;
    ImageButton \u04bc;
    int \u04bd;
    int \u04be;
    int \u04bf;
    int \u04c0;
    Color \u04c1;
    Color \u04c2;
    String \u04c3;
    int \u04c4;
    int \u04c7;
    int \u04c8;
    int \u04cb;
    Color \u04cc;
    Color \u04d0;
    String \u04d1;
    Label \u04d2;
    int \u04d3;
    int \u04d4;
    int \u04d5;
    int \u04d6;
    String \u04d7;
    Color \u04d8;
    TextCanvas \u04d9;
    int \u04da;
    int \u04db;
    int \u04dc;
    int \u04dd;
    Color \u04de;
    ImageButton \u04df;
    int \u04e0;
    int \u04e1;
    int \u04e2;
    int \u04e3;
    Color \u04e4;
    Color \u04e5;
    String \u04e6;
    int \u04e7;
    int \u04e8;
    int \u04e9;
    int \u04ea;
    Color \u04eb;
    Color \u04ee;
    String \u04ef;
    int \u04f0;
    int \u04f1;
    int \u04f2;
    int \u04f3;
    String \u04f4;
    Color \u04f5;
    String \u04f8;
    int \u04f9;
    int \u0531;
    int \u0532;
    int \u0533;
    Color \u0534;
    Checkbox \u0535;
    String \u0536;
    int \u0537;
    int \u0538;
    int \u0539;
    int \u053a;
    Color \u053b;
    Color \u053c;
    Color \u053d;
    Checkbox \u053e;
    String \u053f;
    int \u0540;
    int \u0541;
    int \u0542;
    int \u0543;
    Color \u0544;
    String \u0545;
    int \u0546;
    int \u0547;
    int \u0548;
    int \u0549;
    Color \u054a;
    int \u054b;
    int \u054c;
    int \u054d;
    int \u054e;
    Color \u054f;
    Color \u0550;
    String \u0551;
    int \u0552;
    int \u0553;
    int \u0554;
    int \u0555;
    Color \u0556;
    Color \u0561;
    String \u0562;
    Color \u0563;
    Color \u0564;
    String \u0565;
    int \u0566;
    int \u0567;
    int \u0568;
    int \u0569;
    Color \u056a;
    Color \u056b;
    String \u056c;
    int \u056d;
    int \u056e;
    int \u056f;
    int \u0570;
    Color \u0571;
    Color \u0572;
    String \u0573;
    int \u0574;
    int \u0575;
    int \u0576;
    int \u0577;
    Color \u0578;
    Color \u0579;
    String \u057a;
    int \u057b;
    int \u057c;
    int \u057d;
    int \u057e;
    Color \u057f;
    Color \u0580;
    String \u0581;
    int \u0582;
    int \u0583;
    int \u0584;
    int \u0585;
    Color \u0586;
    Color \u0587;
    String \u05d0;
    int \u05d1;
    int \u05d2;
    int \u05d3;
    int \u05d4;
    Color \u05d5;
    Color \u05d6;
    String \u05d7;
    int \u05d8;
    int \u05d9;
    int \u05da;
    int \u05db;
    Color \u05dc;
    Color \u05dd;
    String \u05de;
    int \u05df;
    int \u05e0;
    int \u05e1;
    int \u05e2;
    Color \u05e3;
    Color \u05e4;
    String \u05e5;
    int \u05e6;
    int \u05e7;
    int \u05e8;
    int \u05e9;
    Color \u05ea;
    Color \u05f0;
    String \u05f1;
    int \u05f2;
    int \u0621;
    int \u0622;
    int \u0623;
    Color \u0624;
    Color \u0625;
    String \u0626;
    int \u0627;
    int \u0628;
    int \u0629;
    int \u062a;
    Color \u062b;
    Color \u062c;
    int \u062d;
    int \u062e;
    int \u062f;
    int \u0630;
    Color \u0631;
    Color \u0632;
    String \u0633;
    int \u0634;
    int \u0635;
    int \u0636;
    int \u0637;
    Color \u0638;
    Color \u0639;
    String \u063a;
    int \u0640;
    int \u0641;
    int \u0642;
    int \u0643;
    Color \u0644;
    Color \u0645;
    String \u0646;
    ImageButton \u0252;
    int \u0647;
    int \u0648;
    int \u0649;
    int \u064a;
    Color \u0671;
    Color \u0672;
    String \u0673;
    ImagePanel \u0674;
    int \u0675;
    int \u0676;
    int \u0677;
    int \u0678;
    Color \u0679;
    Color \u067a;
    String \u067b;
    String \u067c;
    int \u067d;
    int \u067e;
    String \u067f;
    String \u0680;
    String \u0681;
    String \u00d4;
    String \u0682;
    Color \u0683;
    String[] \u0684;
    String \u0685;
    String \u0686;
    String \u0687;
    String \u0688;
    String \u0689;
    String \u068a;
    String \u068b;
    String \u068c;
    String \u068d;
    String \u068e;
    String \u068f;
    String \u0690;
    String \u0691;
    String \u0692;
    String \u0693;
    String \u0694;
    String \u0695;
    String \u0696;
    String \u0697;
    String \u0698;
    String \u0699;
    String \u069a;
    String \u069b;
    String \u069c;
    String \u069d;
    String \u069e;
    String \u069f;
    String \u06a0;
    String \u06a1;
    String \u06a2;
    String \u06a3;
    String \u06a4;
    String \u06a5;
    String \u06a6;
    String \u06a7;
    String \u06a8;
    String \u06a9;
    String \u06aa;
    String \u06ab;
    String \u06ac;
    String \u06ad;
    String \u06ae;
    String \u06af;
    String \u06b0;
    String \u06b1;
    String \u06b2;
    String \u06b3;
    String \u06b4;
    String \u06b5;
    String \u06b6;
    String \u06b7;
    String \u06ba;
    String \u06bb;
    String \u06bc;
    String \u06bd;
    String \u06be;
    String \u06c0;
    String \u06c1;
    String \u06c2;
    String \u06c3;
    String \u06c4;
    String \u06c5;
    String \u06c6;
    String \u06c7;
    String \u06c8;
    String \u06c9;
    String \u06ca;
    String \u06cb;
    String \u06cc;
    String \u06cd;
    String \u06ce;
    String \u06d0;
    String \u06d1;
    String \u06d2;
    String \u06d3;
    String \u06e5;
    String \u06e6;
    String \u00c0B;
    String \u00c1B;
    String \u00c2B;
    String \u00c3B;
    String \u00c4B;
    String \u00c5B;
    String \u00c6B;
    String \u00c7B;
    String \u00c8B;
    String \u00c9B;
    String \u00caB;
    String \u00cbB;
    String \u00ccB;
    String \u00cdB;
    String \u00ceB;
    ImagePanel \u00cfB;
    Label \u0115;
    InfoW \u010d;
    String \u00d0B;
    Commu003 \u0139;
    String \u00d1B;
    final int \u00d2B = 3;
    boolean \u00d3B;
    boolean \u00d4B;
    boolean \u00d5B;
    String \u013e;
    String[][] \u00d6B;
    String[][] \u00d8B;
    int[][] \u00d9B;
    int \u00daB;
    int \u00dbB;
    int \u00dcB;
    int \u00ddB;
    int \u00deB;
    int \u00dfB;
    int \u00e0B;
    int \u00e1B;
    int \u00e2B;
    int \u00e3B;
    int[][] \u00e4B;
    int[][] \u00e5B;
    boolean[][] \u00e6B;
    boolean \u00e7B;
    boolean \u00e8B;
    boolean \u00e9B;
    boolean \u00eaB;
    boolean \u00ebB;
    boolean \u00ecB;
    boolean \u00edB;
    boolean \u00eeB;
    Scrollbar \u00efB;
    Scrollbar \u00f0B;
    int \u00f1B;
    int \u00f2B;
    int \u00f3B;
    int \u00f4B;
    boolean \u00f5B;
    String[] \u00f6B;
    int \u00f8B;
    boolean[][] \u00f9B;
    boolean[][] \u00faB;
    boolean[][] \u00fbB;
    boolean[][] \u00fcB;
    String \u00fdB;
    boolean \u00feB;
    int \u00ffB;
    int \u0100B;
    int \u0101B;
    int \u0102B;
    int \u0103B;
    int \u0104B;
    int \u0105B;
    int \u0106B;
    int \u0107B;
    int \u0108B;
    int \u0109B;
    int \u010aB;
    int \u010bB;
    int \u010cB;
    int \u010dB;
    int \u010eB;
    int \u010fB;
    int \u0110B;
    int \u0111B;
    int \u0112B;
    int \u0113B;
    int[] \u0114B;
    int[] \u0115B;
    int[] \u0116B;
    int[] \u0117B;
    int \u0118B;
    int \u0119B;
    int \u011aB;
    int \u011bB;
    ImagePanel \u011cB;
    Choice \u011dB;
    int \u011eB;
    plaatjeKnop \u011fB;
    plaatjeKnop \u0120B;
    plaatjeKnop \u0121B;
    plaatjeKnop \u0122B;
    Font \u0123B;
    ImagePanel \u0124B;
    plaatjeKnop \u0125B;
    plaatjeKnop \u0126B;
    plaatjeKnop \u0127B;
    plaatjeKnop \u0128B;
    plaatjeKnop \u0129B;
    plaatjeKnop \u012aB;
    plaatjeKnop \u012bB;
    ImagePanel \u012cB;
    ImagePanel \u012dB;
    ImagePanel \u012eB;
    ImagePanel \u012fB;
    ImagePanel \u0130B;
    ImagePanel \u0131B;
    ImagePanel \u0132B;
    Color \u0133B;
    Color \u0134B;
    Color \u0135B;
    Color \u0136B;
    int \u0137B;
    int \u0138B;
    int \u0139B;
    BlacklinedPanel \u013aB;
    BlacklinedPanel \u013bB;
    BlacklinedPanel \u013cB;
    BlacklinedPanel \u013dB;
    BlacklinedPanel \u013eB;
    Label \u013fB;
    Label \u0140B;
    plaatjeKnop \u0141B;
    Label \u0142B;
    Label \u0143B;
    Label \u0144B;
    Label \u0145B;
    Label \u0146B;
    Label \u0147B;
    Label \u0148B;
    Label \u0149B;
    Label \u014aB;
    Label \u014bB;
    Label \u014cB;
    Label \u014dB;
    ImageButton \u014eB;
    ImageButton \u014fB;
    plaatjeKnop \u0150B;
    plaatjeKnop \u0151B;
    plaatjeKnop \u0152B;
    plaatjeKnop \u0153B;
    Font \u0154B;
    List \u0155B;
    ImagePanel \u0156B;
    ImagePanel \u0157B;
    ImagePanel \u0158B;
    ImagePanel \u0159B;
    ImagePanel \u015aB;
    ImagePanel \u015bB;
    ImagePanel \u015cB;
    ImagePanel \u015dB;
    Label \u015eB;
    Label \u015fB;
    Label \u0160B;
    Label \u0161B;
    TextField \u0162B;
    Label \u0163B;
    plaatjeKnop \u0164B;
    plaatjeKnop \u0165B;
    plaatjeKnop \u0166B;
    plaatjeKnop \u0167B;
    Label \u0168B;
    TextField \u0169B;
    TextArea \u016aB;
    Label \u016bB;
    Label \u016cB;
    Label \u016dB;
    Label \u016eB;
    Label \u016fB;
    Label \u0170B;
    Label \u0171B;
    Label \u0172B;
    Label \u0173B;
    Label \u0174B;
    boolean \u0175B;
    String[][] \u0176B;
    String[][] \u0177B;
    String \u0178B;
    String \u0179B;
    String \u017aB;
    Font \u017bB;
    Font \u017cB;
    Font \u017dB;
    Font \u017eB;
    Font \u017fB;
    Font \u0180B;
    Font \u0181B;
    Font \u0182B;
    Font \u0183B;
    Font \u0184B;
    Font \u0185B;
    Font \u0186B;
    Font \u0187B;
    Font \u0188B;
    Font \u0189B;
    Font \u018aB;
    Font \u018bB;
    Font \u018cB;
    Font \u018dB;
    Font \u018eB;
    Font \u018fB;
    Font \u0190B;
    int \u0191B;
    int \u0192B;
    int \u0193B;
    int \u0194B;
    int \u0195B;
    int \u0196B;
    int \u0197B;
    int \u0198B;
    int \u0199B;
    int \u019aB;
    int \u019bB;
    int \u019cB;
    int \u019dB;
    boolean \u019eB;
    boolean \u019fB;
    boolean \u01a0B;
    boolean \u01a1B;
    int \u01a2B;
    int \u01a3B;
    int \u01a4B;
    int \u01a5B;
    int \u01a6B;
    boolean \u01a7B;
    boolean \u01a8B;
    boolean \u01a9B;
    boolean \u01aaB;
    boolean \u01abB;
    static final Color \u01acB;
    int \u01adB;
    int \u01aeB;
    int \u01afB;
    int \u01b0B;
    int \u01b1B;
    int \u01b2B;
    int \u01b3B;
    int \u01b4B;
    int \u01b5B;
    int \u01b6B;
    int \u01b7B;
    int \u01b8B;
    Color \u01b9B;
    Color \u01baB;
    int \u01bbB;
    int \u01bcB;
    int \u01bdB;
    KillT001 \u01beB;
    ImagePanel \u01bfB;
    ImagePanel \u01c0B;
    ImagePanel \u01c1B;
    Label \u01c2B;
    Label \u01c3B;
    Label \u01c4B;
    Label \u01c5B;
    Label \u01c6B;
    Label \u01c7B;
    Label \u01c8B;
    Button \u01c9B;
    Button \u01caB;
    Button \u01cbB;
    Button \u01ccB;
    Button \u01cdB;
    Button \u01ceB;
    Button \u01cfB;
    int \u01d0B;
    int \u01d1B;
    int \u01d2B;
    int \u01d3B;
    int \u01d4B;
    int \u01d5B;
    int \u01d6B;
    int \u01d7B;
    int \u01d8B;
    int \u01d9B;
    int \u01daB;
    int \u01dbB;
    int \u01dcB;
    int \u01ddB;
    int \u01deB;
    int \u01dfB;
    int \u01e0B;
    int \u01e1B;
    int \u01e2B;
    int \u01e3B;
    int \u01e4B;
    int \u01e5B;
    String \u01e6B;
    String \u01e7B;
    Color \u01e8B;
    Color \u01e9B;
    Color \u01eaB;
    Color \u01ebB;
    Font \u01ecB;
    Font \u01edB;
    String \u026b;
    String \u013f;
    int \u01eeB;
    int \u01efB;
    int \u01f0B;
    int \u01f1B;
    int \u01f2B;
    int \u01f3B;
    int \u01f4B;
    int \u01f5B;
    int \u01faB;
    int \u01fbB;
    ClientUserList \u01fcB;
    UserList \u01fdB;
    BlacklinedPanel \u01feB;
    Color[] \u01ffB;
    Color[] \u0200B;
    Label[] \u0201B;
    Label[] \u0202B;
    int \u0203B;
    Color \u0204B;
    Color \u0205B;
    final int \u0206B = 1;
    final int \u0207B = 2;
    final int \u0208B = 3;
    int \u0209B;
    Font \u020aB;
    ImagePanel \u020bB;
    Image \u020cB;
    Image \u020dB;
    Image \u020eB;
    Image \u020fB;
    Image \u0210B;
    Image \u0211B;
    boolean \u0212B;
    boolean \u0213B;
    TextArea \u0214B;
    HyperlinkComponent \u0215B;
    int \u0216B;
    ScrollColorList \u0217B;
    ScrollColorList \u0250B;
    Vector \u0251B;
    Vector \u0252B;
    Label \u0253B;
    Label \u0254B;
    Label \u0255B;
    Label \u0256B;
    Label \u0257B;
    Label \u0258B;
    Label \u0259B;
    Label \u025aB;
    Label \u025bB;
    Label \u025cB;
    Label \u025dB;
    Label \u025eB;
    Label \u025fB;
    Label \u0260B;
    Label \u0261B;
    Label \u0262B;
    Label \u0263B;
    TextField \u0264B;
    TextField \u0265B;
    TextField \u0266B;
    plaatjeKnop \u0267B;
    plaatjeKnop \u0268B;
    plaatjeKnop \u0269B;
    plaatjeKnop \u026aB;
    Checkbox \u026bB;
    Checkbox \u026cB;
    plaatjeKnop \u026dB;
    Label \u026eB;
    Label \u026fB;
    Label \u0270B;
    String \u0271B;
    String \u0272B;
    String \u0273B;
    String \u0274B;
    String \u0275B;
    String \u0276B;
    String \u0277B;
    String \u0278B;
    String \u0279B;
    Font \u027aB;
    String \u027bB;
    String \u027cB;
    String \u027dB;
    String \u027eB;
    String \u027fB;
    String \u0280B;
    String \u0281B;
    String \u0282B;
    String \u0283B;
    String \u0284B;
    String \u0285B;
    String \u0286B;
    String \u0287B;
    String \u0288B;
    String \u0289B;
    String \u028aB;
    String \u028bB;
    String \u028cB;
    String \u028dB;
    String \u028eB;
    String \u028fB;
    String \u0290B;
    ListDataRefresher \u0291B;
    boolean \u0292B;
    int \u0293B;
    final int \u0294B = 15;
    final int \u0295B = 5;
    Color \u0296B;
    Color \u0297B;
    public boolean displayTogetherPanel;
    
    public void init() {
        final Date date = new Date();
        this.displayLoadTime("[START INIT]", date);
        this.setLayout(null);
        this.\u010d = null;
        this.\u026e();
        this.\u01ca = this.getSize().width;
        this.\u01cb = this.getSize().height;
        this.\u02e2 = this.\u01ca;
        if (this.displayTogetherPanel) {
            this.displayLoadTime("[START COMMUNICATOR]", date);
            this.\u0139 = new Commu003(this, this.\u00d1B, this.\u010d);
            this.displayLoadTime("[COMMUNICATOR STARTED]", date);
        }
        this.displayLoadTime("[START LOAD IMAGES]", date);
        this.\u027f = new Image[6];
        this.\u0280 = new Image[6];
        this.\u0281 = new Image[6];
        this.\u028b = new Image[25];
        this.\u0288 = new Image[25];
        this.\u0289 = new Image[25];
        this.\u028a = new Image[25];
        this.loadImages(date);
        this.displayLoadTime("[IMAGES LOADED]", date);
        this.\u027f[0] = this.\u0272;
        this.\u027f[1] = this.\u0275;
        this.\u027f[2] = this.\u0278;
        this.\u027f[3] = this.\u027b;
        this.\u0280[0] = this.\u0273;
        this.\u0280[1] = this.\u0276;
        this.\u0280[2] = this.\u0279;
        this.\u0280[3] = this.\u027c;
        this.\u0281[0] = this.\u0271;
        this.\u0281[1] = this.\u0274;
        this.\u0281[2] = this.\u0277;
        this.\u0281[3] = this.\u027a;
        this.readHelpText();
        this.readColors();
        this.readText();
        this.setText();
        this.setBackground(this.\u00c9);
        this.displayLoadTime("[TEXTS LOADED]", date);
        this.\u027e();
        this.displayLoadTime("[COMPONENTS LOADED]", date);
        this.\u027f();
        this.displayLoadTime("[OLD COMPONENTS LOADED]", date);
        this.declareerButtons();
        this.displayLoadTime("[BUTTONS LOADED]", date);
        this.declareCredits();
        this.declareerChatPaneel();
        if (this.displayTogetherPanel) {
            this.haalWachtlijst();
            this.haalKamerlijst();
            this.displayLoadTime("[LISTS RETREIVED]", date);
            (this.\u0291B = new ListDataRefresher(this, this.getHostname())).\u0124();
            this.\u0291B.start();
        }
        this.\u02a3 = this.getTagValue(super.initialisationData, "<MODE>");
        this.displayLoadTime("[LOAD PUZZLE]", date);
        this.loadPuzzle();
        this.displayLoadTime("[PUZZLE LOADED]", date);
        this.requestFocus();
        this.displayLoadTime("[INIT READY]", date);
        super.appletInitResult = 0;
    }
    
    private void displayLoadTime(final String s, final Date date) {
        new Date().getTime();
        date.getTime();
    }
    
    private void \u026e() {
        this.\u00d1B = this.getParameter("host");
        this.\u026b = this.getParameter("loginname");
        if (this.\u026b != null) {
            this.\u0685 = this.getParameter("sessionID");
            this.\u00d4 = this.getParameter("username");
            this.\u0685 = this.getParameter("sessionID");
            if (this.\u0685 != null) {
                this.\u00d4 = this.\u0685;
                this.\u0682 = this.\u0685;
            }
            else {
                this.\u0682 = this.\u00d4;
            }
        }
        else {
            this.\u00d4 = "anonymous";
            this.\u0685 = "no-sessionID";
            this.\u0682 = "no-password";
            this.\u0395 = 306;
            this.displayTogetherPanel = false;
        }
        final String parameter = this.getParameter("disableScrollbars");
        if (parameter != null) {
            this.\u0212B = parameter.equals("true");
        }
    }
    
    public void passParameters(final String \u00f1b) {
        this.\u00d1B = \u00f1b;
    }
    
    public void leesPlaatjes() {
    }
    
    public void toonZwartPaneel() {
        this.\u00cfB.show();
        this.repaint();
    }
    
    public Image getMyImage(final String s) {
        Image image;
        try {
            final InputStream resourceAsStream = this.getClass().getResourceAsStream(s);
            if (resourceAsStream == null) {
                System.err.println("Image not found.");
                return null;
            }
            final byte[] array = new byte[resourceAsStream.available()];
            resourceAsStream.read(array);
            image = Toolkit.getDefaultToolkit().createImage(array);
        }
        catch (IOException ex) {
            System.err.println("Unable to read image.");
            ex.printStackTrace();
            return null;
        }
        return image;
    }
    
    public int ActiefXYinBeeld(final boolean b) {
        if (b) {
            final int n = this.\u00f1B / this.\u00daB + 1;
            final int n2 = n + this.\u00deB - 3;
            if (this.\u0193B < n) {
                return -1;
            }
            if (this.\u0193B > n2) {
                return 1;
            }
            return 0;
        }
        else {
            final int n3 = this.\u00f2B / this.\u00dbB + 1;
            final int n4 = n3 + this.\u00dfB - 3;
            if (this.\u0194B < n3) {
                return -1;
            }
            if (this.\u0194B > n4) {
                return 1;
            }
            return 0;
        }
    }
    
    public void checkPuzzelformaat() {
        if (this.\u0212B) {
            this.\u00f5B = false;
            this.\u00efB.show(false);
            this.\u00f0B.show(false);
            return;
        }
        if (this.\u00dcB > this.\u00deB + 1 || this.\u00ddB > this.\u00dfB + 1) {
            this.\u00f5B = true;
            this.\u00efB.show(true);
            this.\u00f0B.show(true);
            this.\u00f0B.setValues(0, this.\u00e1B - 15, 0, this.\u00f4B);
            this.\u00efB.setValues(0, this.\u00e0B - 15, 0, this.\u00f3B);
            this.\u00f0B.setPageIncrement(this.\u00f4B);
            this.\u00efB.setPageIncrement(this.\u00f3B);
            return;
        }
        this.\u00f5B = false;
        this.\u00efB.show(false);
        this.\u00f0B.show(false);
    }
    
    public void declareerdiagramArrays() {
        this.\u00e4B = new int[this.\u00ddB][this.\u00dcB];
        for (int i = 0; i < this.\u00ddB; ++i) {
            for (int j = 0; j < this.\u00dcB; ++j) {
                this.\u00e4B[i][j] = 0;
            }
        }
        this.\u00e5B = new int[this.\u00ddB][this.\u00dcB];
        this.\u00e6B = new boolean[this.\u00ddB][this.\u00dcB];
        this.\u00d8B = new String[this.\u00dcB][this.\u00ddB];
        this.\u00d9B = new int[this.\u00dcB][this.\u00ddB];
        this.\u00d6B = new String[this.\u00dcB][this.\u00ddB];
        this.\u00f9B = new boolean[this.\u00dcB][this.\u00ddB];
        this.\u00faB = new boolean[this.\u00dcB][this.\u00ddB];
        this.\u00fbB = new boolean[this.\u00dcB][this.\u00ddB];
        this.\u00fcB = new boolean[this.\u00dcB][this.\u00ddB];
    }
    
    public void declareeromschrijvingenArrays() {
        this.\u0176B = new String[this.\u0118B][5];
        this.\u0177B = new String[this.\u0119B][5];
    }
    
    public void initLayout() {
        for (int i = 0; i < this.\u00ddB; ++i) {
            for (int j = 0; j < this.\u00dcB; ++j) {
                this.\u00e5B[i][j] = 0;
                this.\u00e6B[i][j] = false;
                this.\u00e4B[i][j] = 0;
            }
        }
    }
    
    public void controleerAllesIngevuld() {
        for (int i = 0; i < this.\u00ddB; ++i) {
            for (int j = 0; j < this.\u00dcB; ++j) {
                if (this.\u00d8B[j][i].trim().length() > 0 && !this.\u00d8B[j][i].equalsIgnoreCase(this.\u00d6B[j][i])) {
                    this.\u00faB[j][i] = true;
                    this.\u00fcB[j][i] = true;
                    this.\u00f9B[j][i] = true;
                }
            }
        }
    }
    
    public void puzzlerInWaitingRoom(final String s) {
        this.\u026f();
        this.\u0292B = false;
        this.\u0253B.setText(this.\u027bB);
        this.\u0254B.setText(this.\u027cB);
        this.\u0255B.setText(this.\u027dB);
        this.\u03c5.clearSolutionList();
        this.\u01fdB.clearList();
        final int \u028f = this.\u028f;
        this.\u028f = this.\u0139.getPuzzleType();
        if (\u028f != this.\u028f && !this.loadPuzzle()) {
            this.toonZwartPaneel();
        }
        this.adjustButtonPanel(this.\u028f);
    }
    
    public boolean action(final Event event, final Object o) {
        if (o.equals("klaar")) {
            return false;
        }
        if (event.target == this.\u026dB) {
            this.\u0159B.show(false);
            this.\u0158B.show(true);
            return true;
        }
        if (event.target instanceof Checkbox) {
            if (event.target == this.\u026bB) {
                this.\u0262B.show(false);
                this.\u0265B.show(false);
            }
            if (event.target == this.\u026cB) {
                this.\u0262B.show(true);
                this.\u0265B.show(true);
            }
        }
        if (event.target == this.\u012aB) {
            final int verzendPuzzel = this.verzendPuzzel();
            this.\u013eB.show(true);
            if (verzendPuzzel == 1) {
                this.\u013fB.setText(this.\u00cbB);
                this.\u0140B.setText(this.\u00ccB);
            }
            else {
                this.\u013fB.setText(this.\u00cdB);
                this.\u0140B.setText(this.\u00ceB);
            }
            this.repaint();
            return true;
        }
        if (event.target == this.\u0141B) {
            this.\u013eB.show(false);
            return true;
        }
        if (event.target == this.\u0217B) {
            final int \u00e6 = this.\u0217B.\u00e6();
            if (!this.\u0139.blocked()) {
                this.\u0139.block();
                final WaitingPuzzler waitingPuzzler = this.\u0251B.elementAt(\u00e6);
                if (this.\u026b.equals(waitingPuzzler.getName())) {
                    this.\u0139.unblock();
                    this.\u03c7.hide();
                    this.\u03cd.setText(this.\u0274B);
                    this.\u03ce.setText(this.\u0275B);
                    this.\u03d0.setText(this.\u0276B);
                    this.\u03cc = 0;
                    this.\u03ca.show();
                    return true;
                }
                if (this.\u0139.inRoom()) {
                    this.\u0139.block();
                    this.\u0139.leaveRoom();
                    this.\u0292B = false;
                    this.\u0253B.setText(this.\u027bB);
                    this.\u0254B.setText(this.\u027cB);
                    this.\u0255B.setText(this.\u027dB);
                    this.\u0139.unblock();
                    this.\u03c5.clearSolutionList();
                    this.\u01fdB.clearList();
                    final int \u028f = this.\u028f;
                    this.\u028f = this.\u0139.getPuzzleType();
                    if (\u028f != this.\u028f && !this.loadPuzzle()) {
                        this.toonZwartPaneel();
                    }
                    this.adjustButtonPanel(this.\u028f);
                }
                if (this.\u0139.enterWaitingRoom2(waitingPuzzler.getRoomID(), waitingPuzzler.getUniqueRoomNo(), this.\u026b)) {
                    this.\u013f = this.\u0139.getWaitingRoomName();
                    final int \u028f2 = this.\u028f;
                    this.\u028f = this.\u0139.getPuzzleType();
                    if (\u028f2 != this.\u028f && !this.loadPuzzle()) {
                        this.toonZwartPaneel();
                    }
                    this.adjustButtonPanel(this.\u028f);
                    this.\u026f();
                }
                else {
                    final String errorMessage = this.\u0139.getErrorMessage();
                    if (errorMessage.length() > 15) {
                        this.\u016cB.setText(errorMessage.substring(0, 15));
                        this.\u016dB.setText(errorMessage.substring(15));
                    }
                    else {
                        this.\u016cB.setText(errorMessage);
                    }
                    if (errorMessage.trim().startsWith("Puzzler is no")) {
                        this.\u026eB.setText(this.\u0277B);
                        this.\u026fB.setText(this.\u0278B);
                        this.\u0270B.setText(this.\u0279B);
                        this.\u0158B.show(false);
                        this.\u0159B.show(true);
                    }
                    this.\u0158B.show(false);
                    this.\u03c9.show(false);
                    this.\u0164B.show(true);
                }
            }
            this.repaint();
            this.\u0139.unblock();
            return true;
        }
        if (event.target == this.\u0269B) {
            String s = this.\u0266B.getText();
            if (s != null) {
                s = s.trim();
            }
            if (s != null && s.length() > 0 && !this.\u0139.blocked()) {
                if (this.\u0139.inRoom()) {
                    this.\u0139.block();
                    this.\u0139.leaveRoom();
                    this.\u0139.unblock();
                    this.\u03c5.clearSolutionList();
                    this.\u01fdB.clearList();
                }
                this.\u0139.block();
                final OccupiedRoom occupiedRoom = this.\u0252B.elementAt(this.\u0293B);
                if (this.\u0139.enterRoom(occupiedRoom.getRoomID(), occupiedRoom.getUniqueRoomNo(), this.\u026b, s)) {
                    this.\u03c5.clearSolutionList();
                    this.\u01fdB.clearList();
                    this.\u016aB.setText("");
                    this.\u013f = occupiedRoom.getName();
                    this.\u0168B.setText(String.valueOf(this.\u06cb) + " " + this.\u013f);
                    final int \u028f3 = this.\u028f;
                    this.\u028f = this.\u0139.getPuzzleType();
                    if (\u028f3 != this.\u028f && !this.loadPuzzle()) {
                        this.toonZwartPaneel();
                    }
                    this.adjustButtonPanel(this.\u028f);
                    this.\u026f();
                    this.\u015bB.show(false);
                }
                else {
                    final String errorMessage2 = this.\u0139.getErrorMessage();
                    if (errorMessage2.length() > 15) {
                        this.\u016cB.setText(errorMessage2.substring(0, 15));
                        this.\u016dB.setText(errorMessage2.substring(15));
                    }
                    else {
                        this.\u016cB.setText(errorMessage2);
                    }
                    if (errorMessage2.trim().startsWith("Incorrect")) {
                        this.\u0260B.show(true);
                    }
                    this.\u015cB.show(true);
                    this.\u0158B.show(false);
                    this.\u03c9.show(false);
                    this.\u0164B.show(true);
                }
            }
            this.\u0139.unblock();
            this.repaint();
            return true;
        }
        if (event.target == this.\u0250B) {
            final int \u00e62 = this.\u0250B.\u00e6();
            if (!this.\u0139.blocked()) {
                if (this.\u0139.inRoom()) {
                    this.\u0139.block();
                    this.\u0139.leaveRoom();
                    this.\u0139.unblock();
                    this.\u03c5.clearSolutionList();
                    this.\u01fdB.clearList();
                }
                this.\u0292B = false;
                this.\u0253B.setText(this.\u027bB);
                this.\u0254B.setText(this.\u027cB);
                this.\u0255B.setText(this.\u027dB);
                final OccupiedRoom occupiedRoom2 = this.\u0252B.elementAt(\u00e62);
                final int roomID = occupiedRoom2.getRoomID();
                final int uniqueRoomNo = occupiedRoom2.getUniqueRoomNo();
                if (occupiedRoom2.isPublic()) {
                    this.\u0139.block();
                    if (this.\u0139.enterRoom(roomID, uniqueRoomNo, this.\u026b, null)) {
                        this.\u03c5.clearSolutionList();
                        this.\u01fdB.clearList();
                        this.\u016aB.setText("");
                        this.\u013f = occupiedRoom2.getName();
                        this.\u0168B.setText(String.valueOf(this.\u06cb) + " " + this.\u013f);
                        final int \u028f4 = this.\u028f;
                        this.\u028f = this.\u0139.getPuzzleType();
                        if (\u028f4 != this.\u028f) {
                            this.loadPuzzle();
                        }
                        this.adjustButtonPanel(this.\u028f);
                        this.setSolutions(this.\u0139.getCurrentSolutions());
                        this.\u026f();
                        this.adjustButtonPanel(this.\u028f);
                    }
                    else {
                        final String errorMessage3 = this.\u0139.getErrorMessage();
                        if (errorMessage3.length() > 15) {
                            this.\u016cB.setText(errorMessage3.substring(0, 15));
                            this.\u016dB.setText(errorMessage3.substring(15));
                        }
                        else {
                            this.\u016cB.setText(errorMessage3);
                        }
                        this.\u015cB.show(true);
                        this.\u0158B.show(false);
                        this.\u03c9.show(false);
                        this.\u0164B.show(true);
                    }
                    this.\u0139.unblock();
                    this.repaint();
                    return true;
                }
                this.\u0293B = \u00e62;
                this.\u03c7.hide();
                this.\u015bB.show(true);
                this.\u0260B.show(false);
                this.\u0266B.setText("");
            }
        }
        if (event.target == this.\u026aB) {
            this.\u03c7.show();
            this.\u025fB.show(true);
            this.\u015bB.show(false);
            return true;
        }
        if (event.target == this.\u011fB) {
            this.scrollOmhoog(true);
            this.repaint();
            return true;
        }
        if (event.target == this.\u0120B) {
            this.scrollOmlaag(true);
            this.repaint();
            return true;
        }
        if (event.target == this.\u0121B) {
            this.scrollOmhoog(false);
            this.repaint();
            return true;
        }
        if (event.target == this.\u0122B) {
            this.scrollOmlaag(false);
            this.repaint();
            return true;
        }
        if (event.target == this.\u014fB) {
            this.toonLabel(0);
            this.\u013aB.show(false);
            this.toonComponenten();
            return true;
        }
        if (event.target == this.\u0153B) {
            this.toonLabel(0);
            this.\u013bB.show(false);
            this.toonComponenten();
            return true;
        }
        if (event.target == this.\u011dB) {
            if (this.\u028f == 1) {
                return true;
            }
            if (this.\u011dB.getSelectedIndex() == 0) {
                this.\u011eB = 0;
                this.laatOplZien();
                this.repaint();
                return true;
            }
            if (this.\u011dB.getSelectedIndex() == 1) {
                this.\u011eB = 1;
                this.laatOplZien();
                this.repaint();
                return true;
            }
            if (this.\u011dB.getSelectedIndex() == 2) {
                this.\u011eB = 2;
                this.laatOplZien();
                this.repaint();
            }
            return true;
        }
        else {
            if (event.target == this.\u0152B) {
                this.\u013cB.show(false);
                this.toonComponenten();
                this.repaint();
                return true;
            }
            if (event.target == this.\u0151B) {
                this.\u013dB.show(false);
                this.toonComponenten();
                return true;
            }
            if (event.target == this.\u014eB) {
                this.\u013cB.show(false);
                this.toonLabel(0);
                this.toonComponenten();
                return true;
            }
            if (event.target == this.\u0169B) {
                final String text = this.\u0169B.getText();
                this.\u0169B.setText("");
                this.\u016aB.appendText("[" + this.\u00d0B + "]:" + text + "\n");
                this.\u0279(text);
                return true;
            }
            if (event.target == this.\u0165B && !this.\u0139.blocked()) {
                this.\u03c9.hide();
                this.\u03c7.show();
                this.\u0264B.setText("");
                this.\u0139.block();
                this.leaveRoom();
                this.haalWachtlijst();
                this.\u013f = "";
                this.\u0139.leaveRoom();
                this.\u0271();
                this.haalKamerlijst();
                this.\u0139.unblock();
                this.\u03c5.clearSolutionList();
                this.\u01fdB.clearList();
                this.\u0432.show();
                this.\u042c.show();
                this.\u03c5.clearSolutionList();
                this.resetButtonPanel();
            }
            if (event.target == this.\u0166B && !this.\u0139.blocked()) {
                ++this.\u028f;
                if (this.\u028f > this.\u028e) {
                    this.\u028f = 1;
                }
                this.loadPuzzle();
                this.setSolutions(this.\u0139.getCurrentSolutions());
                this.\u0139.loadPuzzle(this.\u0139.getRoomID(), this.\u028f);
                this.\u0139.unblock();
                this.\u03c5.clearSolutionList();
                this.\u03c5.\u020b.setText("Try to find a valid set.");
                this.\u03c5.\u020b.setBackground(this.\u03c5.\u01ee);
                this.\u03c5.clearSolutionList();
                this.\u03c5.resetCardPanel();
                this.\u0166B.hide();
                this.adjustButtonPanel(this.\u028f);
                this.repaint();
                return true;
            }
            if (event.target == this.\u0164B && !this.\u0139.blocked()) {
                this.\u0139.block();
                this.\u0164B.show(false);
                if (this.\u0162B.getText().length() > 0) {
                    if (this.\u0139.createRoom(this.\u026b, this.\u0162B.getText(), String.valueOf(this.\u028f), null)) {
                        this.\u013f = this.\u0162B.getText();
                        this.\u026f();
                    }
                    else {
                        final String errorMessage4 = this.\u0139.getErrorMessage();
                        if (errorMessage4.length() > 15) {
                            this.\u016cB.setText(errorMessage4.substring(0, 15));
                            this.\u016dB.setText(errorMessage4.substring(15));
                        }
                        else {
                            this.\u016cB.setText(errorMessage4);
                        }
                        this.\u015cB.show(true);
                        this.\u0158B.show(false);
                        this.\u03c9.show(false);
                        this.\u0164B.show(true);
                    }
                }
                else {
                    this.\u016cB.setText(this.\u06a8);
                    this.\u016dB.setText(this.\u06a9);
                    this.\u016eB.setText(this.\u06aa);
                    this.\u015cB.show(true);
                    this.\u0158B.show(false);
                    this.\u03c9.show(false);
                    this.\u0164B.show(true);
                }
                this.repaint();
                this.\u0139.unblock();
                return true;
            }
            if (event.target == this.\u0167B) {
                final String text2 = this.\u0169B.getText();
                if (text2.equals("pzzlpzzl-solution")) {
                    System.out.println(this.\u026e);
                }
                if (text2.equals("pzzlpzzl-debug")) {
                    super.debug = true;
                }
                this.\u0169B.setText("");
                this.\u016aB.appendText("[" + this.\u00d0B + "]:" + text2 + "\n");
                this.\u0279(text2);
                return true;
            }
            if (event.target == this.\u01c9B) {
                if (this.\u0139.reconnectAttempts() < 3) {
                    this.\u01c2B.setText(this.\u068b);
                    this.\u01c3B.setText("");
                    this.\u01c9B.hide();
                    switch (this.\u0139.reconnect()) {
                        case -1: {
                            this.\u01c2B.setText(String.valueOf(this.\u068c) + this.\u0139.reconnectAttempts() + this.\u068d);
                            this.\u01c3B.setText(this.\u0694);
                            this.\u01c9B.show();
                            break;
                        }
                        case 0:
                        case 1: {
                            this.\u01c2B.setText(this.\u068e);
                            this.\u01c3B.setText(this.\u0695);
                            this.\u01c9B.show();
                            this.\u01bfB.show(false);
                            this.\u0272();
                            break;
                        }
                        case 2: {
                            this.\u01c2B.setText(this.\u0692);
                            this.\u01c3B.setText(this.\u0697);
                            this.\u01cbB.show();
                            break;
                        }
                        case 3: {
                            this.\u01c2B.setText(this.\u0693);
                            this.\u01c3B.setText(this.\u0698);
                            this.\u01ccB.show();
                            break;
                        }
                    }
                }
                else {
                    this.\u01c2B.setText(this.\u068f);
                    this.\u01c3B.setText(this.\u0695);
                    this.\u01c9B.hide();
                    this.\u01caB.show();
                }
                return true;
            }
            if (event.target == this.\u01caB) {
                this.\u027d();
                this.\u01bfB.show(false);
            }
            if (event.target == this.\u01cfB) {
                this.\u027d();
                this.\u00d3B = true;
                this.\u01c1B.show(false);
            }
            if (event.target == this.\u01cbB) {
                this.\u027d();
                this.\u01bfB.show(false);
            }
            if (event.target == this.\u01ccB) {
                this.\u027d();
                this.\u01bfB.show(false);
            }
            if (event.target == this.\u01ceB) {
                this.\u01ceB.show(false);
                this.\u01bfB.show(true);
                return true;
            }
            if (event.target == this.\u01cdB) {
                this.\u01c0B.show(false);
                this.\u0164B.enable();
                this.\u0165B.disable();
                this.\u0179B = "";
                this.\u017aB = "";
                this.\u013e = "";
            }
            if (event.target == this.\u0127B) {
                if (this.\u028f == 1) {
                    this.\u0132B.show(true);
                    return true;
                }
                this.controleerAllesIngevuld();
                this.repaint();
                return true;
            }
            else if (event.target == this.\u0128B) {
                if (this.\u028f == 1) {
                    this.\u0132B.show(true);
                    return true;
                }
                this.laatOplZien();
                this.repaint();
                return true;
            }
            else {
                if (event.target == this.\u0129B) {
                    this.\u011cB.show(true);
                    this.verbergComponenten();
                    this.repaint();
                    return true;
                }
                if (event.target == this.\u0150B) {
                    this.\u013aB.show(false);
                    this.toonComponenten();
                    return true;
                }
                return false;
            }
        }
    }
    
    public void adjustButtonPanel(final int n) {
        for (int i = 0; i < 4; ++i) {
            if (i != n - 1) {
                this.\u046a[i].hide();
            }
            else {
                this.\u046a[i].show();
                this.\u046a[i].enable();
                this.\u046a[i].setMouseDown();
            }
        }
        this.repaint();
    }
    
    public void showNextPuzzleButton() {
        for (int i = 0; i < 4; ++i) {
            this.\u046a[i].hide();
        }
        this.\u0166B.show();
        this.repaint();
    }
    
    public void resetButtonPanel() {
        for (int i = 0; i < 4; ++i) {
            this.\u046a[i].show();
            this.\u046a[i].enable();
        }
        this.repaint();
    }
    
    public void setSolutions(final SetSolutionList list) {
        if (list != null) {
            for (int i = 0; i < list.getSize(); ++i) {
                final SetSolution solution = list.getSolution(i);
                this.\u03c5.setSolution(solution.getCard(1), solution.getCard(2), solution.getCard(3), this.getUserColor(solution.getLoginName()));
            }
        }
        this.\u03c5.repaint();
    }
    
    public void reshapePanels() {
        this.\u013bB.reshape(this.\u01f0B, this.\u01f1B, this.\u01eeB, this.\u01efB);
        this.\u013aB.reshape(this.\u01f0B, this.\u01f1B, this.\u01eeB, this.\u01efB);
        this.\u013cB.reshape(this.\u01f0B, this.\u01f1B, this.\u01eeB, this.\u01efB);
        this.\u013dB.reshape(this.\u01f0B, this.\u01f1B, this.\u01eeB, this.\u01efB);
    }
    
    private void \u026f() {
        this.\u03c7.hide();
        this.\u03c8.hide();
        this.haalWachtlijst();
        this.\u0270();
        this.\u0291B.\u0125();
        this.\u0160B.setText(String.valueOf(this.\u06c7) + this.\u026b + this.\u06c8);
        this.\u0161B.setText(String.valueOf(this.\u06c9) + this.\u013f);
        this.\u03c9.show(true);
    }
    
    private void \u0270() {
        this.\u00d0B = this.\u026b;
        this.\u0162B.setEditable(false);
    }
    
    private void \u0271() {
        this.\u00d0B = "<NOT DEFINED>";
        this.\u0162B.setEditable(true);
    }
    
    void \u0272() {
    }
    
    public void laatOplZien() {
        switch (this.\u011eB) {
            case 0: {
                if (!this.\u00d8B[this.\u0193B][this.\u0194B].equalsIgnoreCase(this.\u00d6B[this.\u0193B][this.\u0194B])) {
                    this.\u00fbB[this.\u0193B][this.\u0194B] = true;
                    this.\u00d8B[this.\u0193B][this.\u0194B] = this.\u00d6B[this.\u0193B][this.\u0194B];
                    this.\u00f9B[this.\u0193B][this.\u0194B] = true;
                    return;
                }
                break;
            }
            case 1: {
                this.\u0273(this.\u0193B, this.\u0194B);
                if (this.\u0192B == 0) {
                    for (int i = this.\u0198B; i <= this.\u0199B; ++i) {
                        if (!this.\u00d8B[i][this.\u0194B].equalsIgnoreCase(this.\u00d6B[i][this.\u0194B])) {
                            this.\u00f9B[i][this.\u0194B] = true;
                            this.\u00fbB[i][this.\u0194B] = true;
                            this.\u00d8B[i][this.\u0194B] = this.\u00d6B[i][this.\u0194B];
                        }
                    }
                    return;
                }
                for (int j = this.\u0196B; j <= this.\u0197B; ++j) {
                    if (!this.\u00d8B[this.\u0193B][j].equalsIgnoreCase(this.\u00d6B[this.\u0193B][j])) {
                        this.\u00fbB[this.\u0193B][j] = true;
                        this.\u00f9B[this.\u0193B][j] = true;
                        this.\u00d8B[this.\u0193B][j] = this.\u00d6B[this.\u0193B][j];
                    }
                }
            }
            case 2: {
                for (int k = 0; k < this.\u00dcB; ++k) {
                    for (int l = 0; l < this.\u00ddB; ++l) {
                        if (!this.\u00d8B[k][l].equalsIgnoreCase(this.\u00d6B[k][l])) {
                            this.\u00fbB[k][l] = true;
                            this.\u00f9B[k][l] = true;
                            this.\u00d8B[k][l] = this.\u00d6B[k][l];
                        }
                    }
                }
            }
        }
    }
    
    public String checkPuzzel() {
        String s = "";
        for (int i = 0; i < this.\u00ddB; ++i) {
            for (int j = 0; j < this.\u00dcB; ++j) {
                if (this.\u00e5B[i][j] >= 0) {
                    if (this.\u00d8B[j][i].length() == 1) {
                        s = String.valueOf(s) + this.\u00d8B[j][i];
                    }
                    else {
                        final String s2 = this.\u00d8B[j][i];
                        final int length = s2.length();
                        for (int k = 0; k < length - 1; ++k) {
                            s = String.valueOf(new StringBuffer(String.valueOf(s)).append(s2.charAt(k)).toString()) + ",";
                        }
                        s = String.valueOf(s) + s2.charAt(length - 1);
                    }
                }
            }
        }
        return s;
    }
    
    public void declareerButtons() {
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (event.target == this.\u0125B) {
            this.\u012cB.show(true);
        }
        if (event.target == this.\u0126B) {
            this.\u012dB.show(true);
        }
        if (event.target == this.\u0129B) {
            this.\u0130B.show(true);
        }
        if (event.target == this.\u0128B) {
            this.\u012fB.show(true);
        }
        if (event.target == this.\u0127B) {
            this.\u012eB.show(true);
        }
        if (event.target == this.\u012bB) {
            this.\u0131B.show(true);
        }
        return true;
    }
    
    public int bepaalHorVraagdeel(final int n) {
        int n2 = 0;
        int n3 = n;
        final int n4 = this.\u0116B[n];
        if (n >= this.\u011aB) {
            return -1;
        }
        while (n4 == this.\u0116B[n3] && n3 > 0) {
            if (n4 == this.\u0116B[n3 - 1]) {
                ++n2;
            }
            --n3;
        }
        return n2;
    }
    
    public int bepaalVerVraagdeel(final int n) {
        int n2 = 0;
        int n3 = n;
        if (n >= this.\u011bB) {
            return -1;
        }
        for (int n4 = this.\u0117B[n]; n4 == this.\u0117B[n3] && n3 > 0; --n3) {
            if (n4 == this.\u0117B[n3 - 1]) {
                ++n2;
            }
        }
        return n2;
    }
    
    private void \u0273(final int n, final int n2) {
        this.\u019aB = this.\u0196B;
        this.\u019bB = this.\u0197B;
        this.\u019cB = this.\u0198B;
        this.\u019dB = this.\u0199B;
        if (this.\u0192B == 0) {
            this.\u0196B = n2;
            this.\u0197B = n2;
            int n3;
            for (n3 = n; n3 > 0 && this.\u00e5B[n2][n3] >= 0; --n3) {}
            if (n3 > 0) {
                this.\u0198B = n3 + 1;
            }
            else if (this.\u00e5B[n2][0] < 0) {
                this.\u0198B = 1;
            }
            else {
                this.\u0198B = 0;
            }
            int n4;
            for (n4 = n; n4 < this.\u00dcB && this.\u00e5B[n2][n4] >= 0; ++n4) {}
            this.\u0199B = n4 - 1;
            return;
        }
        this.\u0198B = n;
        this.\u0199B = n;
        int n5;
        for (n5 = n2; n5 > 0 && this.\u00e5B[n5][n] >= 0; --n5) {}
        if (n5 > 0) {
            this.\u0196B = n5 + 1;
        }
        else if (this.\u00e5B[0][n] < 0) {
            this.\u0196B = 1;
        }
        else {
            this.\u0196B = 0;
        }
        int n6;
        for (n6 = n2; n6 < this.\u00ddB && this.\u00e5B[n6][n] >= 0; ++n6) {}
        this.\u0197B = n6 - 1;
    }
    
    private void \u0274(final int \u0199b, final int \u0269b) {
        if (this.\u0192B == 0) {
            this.\u0196B = \u0269b;
            int n;
            for (n = \u0199b; n > 0 && this.\u00e5B[\u0269b][n] >= 0; --n) {}
            if (n > 0) {
                this.\u0198B = n + 1;
                return;
            }
            if (this.\u00e5B[\u0269b][0] < 0) {
                this.\u0198B = 1;
                return;
            }
            this.\u0198B = 0;
        }
        else {
            this.\u0198B = \u0199b;
            int n2;
            for (n2 = \u0269b; n2 > 0 && this.\u00e5B[n2][\u0199b] >= 0; --n2) {}
            if (n2 > 0) {
                this.\u0196B = n2 + 1;
                return;
            }
            if (this.\u00e5B[0][\u0199b] < 0) {
                this.\u0196B = 1;
                return;
            }
            this.\u0196B = 0;
        }
    }
    
    public void bepaalActiefXY(final int n) {
        int i = 1;
        int \u0260b = this.\u0193B;
        int \u0263b = this.\u0194B;
        if (this.\u0192B == 0) {
            while (i != 0) {
                if (this.\u00e5B[\u0263b][\u0260b] == n) {
                    this.\u0193B = \u0260b;
                    this.\u0194B = \u0263b;
                    i = 0;
                }
                if (++\u0260b == this.\u00dcB) {
                    \u0260b = 0;
                    if (++\u0263b != this.\u00ddB) {
                        continue;
                    }
                    \u0263b = 0;
                }
            }
        }
        else {
            while (i != 0) {
                if (this.\u00e5B[\u0263b][\u0260b] == n) {
                    this.\u0193B = \u0260b;
                    this.\u0194B = \u0263b;
                    i = 0;
                }
                if (++\u0263b == this.\u00ddB) {
                    \u0263b = 0;
                    if (++\u0260b != this.\u00dcB) {
                        continue;
                    }
                    \u0260b = 0;
                }
            }
        }
        if (this.\u00d8B[this.\u0193B][this.\u0194B].trim().length() > 0) {
            if (this.\u0192B == 0) {
                this.gaNaarLegeVakje(0, false);
                return;
            }
            this.gaNaarLegeVakje(1, false);
        }
    }
    
    public void bepaalomschrHor(final int n, final int n2) {
        if (this.\u0192B == 0) {
            final int n3 = this.\u00e5B[this.\u0196B][this.\u0198B];
            int i = this.\u0110B;
            if (this.\u0114B[this.\u0110B] < n3) {
                while (i < this.\u0118B) {
                    if (this.\u0114B[i] == n3) {
                        this.\u0110B = i;
                        i = this.\u0118B;
                    }
                    else {
                        ++i;
                    }
                }
                return;
            }
            if (this.\u0114B[this.\u0110B] > n3) {
                while (i >= 0) {
                    if (this.\u0114B[i] == n3) {
                        this.\u0110B = i;
                        i = -1;
                    }
                    else {
                        --i;
                    }
                }
            }
        }
        else {
            this.\u0275();
            this.\u0273(n, n2);
            final int n4 = this.\u00e5B[this.\u0196B][this.\u0198B];
            int j = this.\u0110B;
            if (this.\u0114B[this.\u0110B] < n4) {
                while (j < this.\u0118B) {
                    if (this.\u0114B[j] == n4) {
                        this.\u0110B = j;
                        j = this.\u0118B;
                    }
                    else {
                        ++j;
                    }
                }
            }
            else if (this.\u0114B[this.\u0110B] > n4) {
                while (j >= 0) {
                    if (this.\u0114B[j] == n4) {
                        this.\u0110B = j;
                        j = -1;
                    }
                    else {
                        --j;
                    }
                }
            }
            this.\u0275();
        }
    }
    
    public void bepaalomschrVer(final int n, final int n2) {
        if (this.\u0192B == 1) {
            final int n3 = this.\u00e5B[this.\u0196B][this.\u0198B];
            int i = this.\u0111B;
            if (this.\u0115B[this.\u0111B] < n3) {
                while (i < this.\u0119B) {
                    if (this.\u0115B[i] == n3) {
                        this.\u0111B = i;
                        i = this.\u0119B;
                    }
                    else {
                        ++i;
                    }
                }
                return;
            }
            if (this.\u0115B[this.\u0111B] > n3) {
                while (i >= 0) {
                    if (this.\u0115B[i] == n3) {
                        this.\u0111B = i;
                        i = -1;
                    }
                    else {
                        --i;
                    }
                }
            }
        }
        else {
            this.\u0275();
            this.\u0273(n, n2);
            final int n4 = this.\u00e5B[this.\u0196B][this.\u0198B];
            int j = this.\u0111B;
            if (this.\u0115B[this.\u0111B] < n4) {
                while (j < this.\u0119B) {
                    if (this.\u0115B[j] == n4) {
                        this.\u0111B = j;
                        j = this.\u0119B;
                    }
                    else {
                        ++j;
                    }
                }
            }
            else if (this.\u0115B[this.\u0111B] > n4) {
                while (j >= 0) {
                    if (this.\u0115B[j] == n4) {
                        this.\u0111B = j;
                        j = -1;
                    }
                    else {
                        --j;
                    }
                }
            }
            this.\u0275();
        }
    }
    
    public void gaNaarLegeVakje(final int n, final boolean b) {
        int n2 = 0;
        if (n == 0) {
            final int \u0260b = this.\u0193B;
            while (this.\u0193B < this.\u00dcB - 1 && this.\u00e5B[this.\u0194B][this.\u0193B + 1] >= 0 && n2 == 0) {
                ++this.\u0193B;
                if (this.\u00d8B[this.\u0193B][this.\u0194B].trim().length() == 0) {
                    n2 = 1;
                }
            }
            if (n2 == 0) {
                while (this.\u0193B > 0) {
                    if (this.\u00e5B[this.\u0194B][this.\u0193B - 1] < 0) {
                        break;
                    }
                    --this.\u0193B;
                }
            }
            while (this.\u00d8B[this.\u0193B][this.\u0194B].trim().length() > 0 && this.\u0193B < \u0260b) {
                ++this.\u0193B;
            }
            if (this.\u0193B == \u0260b && b && \u0260b < this.\u00dcB - 1 && this.\u00e5B[this.\u0194B][\u0260b + 1] >= 0) {
                this.\u0193B = \u0260b + 1;
            }
        }
        else {
            final int \u0263b = this.\u0194B;
            while (this.\u0194B < this.\u00ddB - 1 && this.\u00e5B[this.\u0194B + 1][this.\u0193B] >= 0 && n2 == 0) {
                ++this.\u0194B;
                if (this.\u00d8B[this.\u0193B][this.\u0194B].trim().length() == 0) {
                    n2 = 1;
                }
            }
            if (n2 == 0) {
                while (this.\u0194B > 0) {
                    if (this.\u00e5B[this.\u0194B - 1][this.\u0193B] < 0) {
                        break;
                    }
                    --this.\u0194B;
                }
            }
            while (this.\u00d8B[this.\u0193B][this.\u0194B].trim().length() > 0 && this.\u0194B < \u0263b) {
                ++this.\u0194B;
            }
            if (this.\u0194B == \u0263b && b && \u0263b < this.\u00ddB - 1 && this.\u00e5B[\u0263b + 1][this.\u0193B] >= 0) {
                this.\u0194B = \u0263b + 1;
            }
        }
    }
    
    public boolean keyUp(final Event event, final int n) {
        return true;
    }
    
    public int zoekOmschr(final int n, final boolean b) {
        if (b) {
            for (int i = 0; i < this.\u0118B; ++i) {
                if (this.\u0114B[i] == n) {
                    return i;
                }
            }
            return -1;
        }
        for (int j = 0; j < this.\u0119B; ++j) {
            if (this.\u0115B[j] == n) {
                return j;
            }
        }
        return -1;
    }
    
    void \u0275() {
        if (this.\u0192B == 1) {
            this.\u0192B = 0;
        }
        else {
            this.\u0192B = 1;
        }
        this.\u0273(this.\u0193B, this.\u0194B);
    }
    
    public void scrollOmhoog(final boolean b) {
        if (b) {
            this.\u0112B -= this.\u0104B;
            this.\u0112B = Math.max(this.\u0112B, 0);
            return;
        }
        this.\u0113B -= this.\u0105B;
        this.\u0113B = Math.max(this.\u0113B, 0);
    }
    
    public void scrollOmlaag(final boolean b) {
        if (b) {
            this.\u0112B += this.\u0104B;
            this.\u0112B = Math.min(this.\u0112B, this.\u011aB - this.\u0104B);
            return;
        }
        this.\u0113B += this.\u0105B;
        this.\u0113B = Math.min(this.\u0113B, this.\u011bB - this.\u0105B);
    }
    
    public int checkPijl(final int n, final int n2) {
        if (n >= this.\u010aB && n <= this.\u010aB + this.\u010eB && n2 >= this.\u010bB && n2 <= this.\u010bB + this.\u010fB) {
            return 1;
        }
        if (n >= this.\u010aB && n <= this.\u010aB + this.\u010eB && n2 >= this.\u010bB + 7 * this.\u0106B && n2 <= this.\u010bB + 7 * this.\u0106B + this.\u010fB) {
            return 2;
        }
        if (n >= this.\u010cB && n <= this.\u010cB + this.\u010eB && n2 >= this.\u010dB && n2 <= this.\u010dB + this.\u010fB) {
            return 3;
        }
        if (n >= this.\u010cB && n <= this.\u010cB + this.\u010eB && n2 >= this.\u010dB + 7 * this.\u0106B && n2 <= this.\u010dB + 7 * this.\u0106B + this.\u010fB) {
            return 4;
        }
        return 0;
    }
    
    public void declareCredits() {
        (this.\u020bB = new ImagePanel(this.\u020cB, 320, 12)).reshape(2, 40 + this.\u00e3B + 2, 320, 12);
    }
    
    public void reshapeCredits() {
        this.\u020bB.reshape(2, 40 + this.\u00e3B + 2, 320, 12);
    }
    
    public void verwerkDiagramRegel(final String s, final int n, final int n2) {
        int n3 = 0;
        int n4 = 0;
        if (n2 == 2) {
            for (int i = 0; i < s.length(); ++i) {
                if (n4 == 0) {
                    switch (s.charAt(i)) {
                        case '.': {
                            this.\u00e5B[n][i - n3] = -2;
                            this.\u00d6B[i - n3][n] = "";
                            break;
                        }
                        case '%': {
                            this.\u00e6B[n][i - n3] = true;
                            ++n3;
                            break;
                        }
                        case ',': {
                            final String[] array = this.\u00d6B[i - 1 - n3];
                            array[n] = String.valueOf(array[n]) + s.charAt(i + 1);
                            n3 += 2;
                            n4 = 1;
                            break;
                        }
                        case '#': {
                            this.\u00e5B[n][i - n3] = -1;
                            this.\u00d6B[i - n3][n] = "";
                            break;
                        }
                        default: {
                            this.\u00e5B[n][i - n3] = 0;
                            this.\u00d6B[i - n3][n] = String.valueOf(s.charAt(i));
                            break;
                        }
                    }
                }
                else {
                    n4 = 0;
                }
            }
            return;
        }
        for (int j = 0; j < s.length(); ++j) {
            switch (s.charAt(j)) {
                case '3': {
                    this.\u00e6B[n][j - n3] = true;
                    this.\u00e5B[n][j - n3] = 0;
                    this.\u00d6B[j - n3][n] = "";
                    break;
                }
                case '2': {
                    this.\u00e5B[n][j - n3] = -2;
                    this.\u00d6B[j - n3][n] = "";
                    break;
                }
                case '1': {
                    this.\u00e5B[n][j - n3] = -1;
                    this.\u00d6B[j - n3][n] = "";
                    break;
                }
                case '0': {
                    this.\u00e5B[n][j - n3] = 0;
                    this.\u00d6B[j - n3][n] = "";
                    break;
                }
                default: {
                    this.\u00e5B[n][j - n3] = 0;
                    this.\u00d6B[j - n3][n] = "";
                    break;
                }
            }
        }
    }
    
    public boolean bepaalVorigeWeekString() {
        URL url = null;
        final String s = "/nytimes-settogether/PuzzleDatesNY";
        try {
            url = new URL("http", this.getHostname(), 80, s);
        }
        catch (MalformedURLException ex2) {
            this.consoleMessage("UNABLE TO DETECT SERVER");
        }
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            this.\u01e6B = dataInputStream.readLine().trim();
            this.\u01e7B = dataInputStream.readLine().trim();
            dataInputStream.close();
        }
        catch (IOException ex) {
            System.out.println("[ERROR] e=[" + ex.toString() + "]");
            return false;
        }
        return true;
    }
    
    public boolean haalWachtlijst() {
        URL url = null;
        final String s = "/nytimes-settogether/multiplayerservletny?messagetype=waitinguserlist";
        try {
            url = new URL("http", this.getHostname(), 80, s);
        }
        catch (MalformedURLException ex2) {
            this.consoleMessage("UNABLE TO DETECT SERVER");
        }
        String trim;
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            trim = dataInputStream.readLine().trim();
            dataInputStream.close();
        }
        catch (IOException ex) {
            System.out.println("Getting waitingList failed e=[" + ex.toString() + "]");
            return false;
        }
        if (trim != null) {
            this.\u0277(trim);
        }
        return true;
    }
    
    void \u0276(final String s) {
        final String tagValue = this.getTagValue(s, "<WAITINGLIST>");
        if (tagValue.startsWith("[OK]")) {
            final int tagValueInt = this.getTagValueInt(tagValue, "<TOTAL>");
            this.\u0217B.\u00e6();
            this.\u0217B.\u00e3();
            this.\u0251B.removeAllElements();
            if (tagValueInt > 0) {
                for (int i = 1; i <= tagValueInt; ++i) {
                    final String tagValue2 = this.getTagValue(tagValue, "<PACKET" + i + ">");
                    final String tagValue3 = this.getTagValue(tagValue2, "<USERNAME>");
                    final int tagValueInt2 = this.getTagValueInt(tagValue2, "<ROOMID>");
                    final int tagValueInt3 = this.getTagValueInt(tagValue2, "<UNIQUEROOMNO>");
                    this.\u0217B.\u00e1(tagValue3);
                    this.\u0251B.addElement(new WaitingPuzzler(tagValueInt2, tagValueInt3, tagValue3));
                }
            }
        }
        final String tagValue4 = this.getTagValue(s, "<ROOMLIST>");
        if (tagValue4.startsWith("[OK]")) {
            final int tagValueInt4 = this.getTagValueInt(tagValue4, "<TOTAL>");
            this.\u0250B.\u00e6();
            this.\u0250B.\u00e3();
            this.\u0252B.removeAllElements();
            if (tagValueInt4 > 0) {
                for (int j = 1; j <= tagValueInt4; ++j) {
                    final String tagValue5 = this.getTagValue(tagValue4, "<PACKET" + j + ">");
                    final String tagValue6 = this.getTagValue(tagValue5, "<ROOMNAME>");
                    final String tagValue7 = this.getTagValue(tagValue5, "<ACCESS>");
                    boolean b = true;
                    if (tagValue7.equals("private")) {
                        b = false;
                    }
                    final int tagValueInt5 = this.getTagValueInt(tagValue5, "<ROOMID>");
                    final int tagValueInt6 = this.getTagValueInt(tagValue5, "<UNIQUEROOMNO>");
                    if (this.\u0292B && tagValueInt5 == this.\u0139.getRoomID()) {
                        this.\u0292B = false;
                        this.\u0250B.\u00e3();
                        this.puzzlerInWaitingRoom(null);
                        this.\u0253B.setText(this.\u027bB);
                        this.\u0254B.setText(this.\u027cB);
                        this.\u0255B.setText(this.\u027dB);
                        return;
                    }
                    final int tagValueInt7 = this.getTagValueInt(tagValue5, "<ROOMPUZZLERS>");
                    this.getTagValueInt(tagValue5, "<SOLVED>");
                    if (tagValueInt7 == 1) {}
                    final String s2 = tagValue6;
                    if (b) {
                        this.\u0250B.\u00e1(s2, Color.black);
                    }
                    else {
                        this.\u0250B.\u00e1(s2, this.\u0297B);
                    }
                    this.\u0252B.addElement(new OccupiedRoom(tagValueInt5, tagValueInt6, tagValue6, b));
                }
            }
        }
    }
    
    public boolean haalKamerlijst() {
        URL url = null;
        final String s = "/nytimes-settogether/multiplayerservletny?messagetype=roomlist";
        try {
            url = new URL("http", this.getHostname(), 80, s);
        }
        catch (MalformedURLException ex2) {
            this.consoleMessage("UNABLE TO DETECT SERVER");
        }
        String trim;
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            trim = dataInputStream.readLine().trim();
            dataInputStream.close();
        }
        catch (IOException ex) {
            System.out.println("Getting roomList failed e=" + ex.toString() + "]");
            return false;
        }
        if (trim != null) {
            this.\u0278(trim);
        }
        return true;
    }
    
    void \u0277(final String s) {
        if (s.startsWith("[OK]")) {
            final int tagValueInt = this.getTagValueInt(s, "<TOTAL>");
            this.\u0217B.\u00e6();
            this.\u0217B.\u00e3();
            this.\u0251B.removeAllElements();
            if (tagValueInt > 0) {
                for (int i = 1; i <= tagValueInt; ++i) {
                    final String tagValue = this.getTagValue(s, "<PACKET" + i + ">");
                    final String tagValue2 = this.getTagValue(tagValue, "<USERNAME>");
                    final int tagValueInt2 = this.getTagValueInt(tagValue, "<ROOMID>");
                    final int tagValueInt3 = this.getTagValueInt(tagValue, "<UNIQUEROOMNO>");
                    this.\u0217B.\u00e1(tagValue2);
                    this.\u0251B.addElement(new WaitingPuzzler(tagValueInt2, tagValueInt3, tagValue2));
                }
            }
        }
    }
    
    void \u0278(final String s) {
        if (s.startsWith("[OK]")) {
            final int tagValueInt = this.getTagValueInt(s, "<TOTAL>");
            this.\u0250B.\u00e6();
            this.\u0250B.\u00e3();
            this.\u0252B.removeAllElements();
            if (tagValueInt > 0) {
                for (int i = 1; i <= tagValueInt; ++i) {
                    final String tagValue = this.getTagValue(s, "<PACKET" + i + ">");
                    final String tagValue2 = this.getTagValue(tagValue, "<ROOMNAME>");
                    final String tagValue3 = this.getTagValue(tagValue, "<ACCESS>");
                    boolean b = true;
                    if (tagValue3.equals("private")) {
                        b = false;
                    }
                    final int tagValueInt2 = this.getTagValueInt(tagValue, "<ROOMID>");
                    final int tagValueInt3 = this.getTagValueInt(tagValue, "<UNIQUEROOMNO>");
                    if (this.\u0292B && tagValueInt2 == this.\u0139.getRoomID()) {
                        this.\u0292B = false;
                        this.\u0250B.\u00e3();
                        this.puzzlerInWaitingRoom(null);
                        this.\u0253B.setText(this.\u027bB);
                        this.\u0254B.setText(this.\u027cB);
                        this.\u0255B.setText(this.\u027dB);
                        return;
                    }
                    final int tagValueInt4 = this.getTagValueInt(tagValue, "<ROOMPUZZLERS>");
                    this.getTagValueInt(tagValue, "<SOLVED>");
                    if (tagValueInt4 == 1) {}
                    final String s2 = tagValue2;
                    if (b) {
                        this.\u0250B.\u00e1(s2, Color.black);
                    }
                    else {
                        this.\u0250B.\u00e1(s2, this.\u0297B);
                    }
                    this.\u0252B.addElement(new OccupiedRoom(tagValueInt2, tagValueInt3, tagValue2, b));
                }
            }
        }
    }
    
    public boolean leesomschrijvingen() {
        return true;
    }
    
    public void bepaalLayout() {
        int n = 1;
        int n2 = 0;
        int n3 = 0;
        for (int i = 0; i < this.\u00ddB; ++i) {
            for (int j = 0; j < this.\u00dcB; ++j) {
                boolean b = false;
                if (this.\u00e5B[i][j] >= 0) {
                    if ((i == 0 || this.\u00e5B[i - 1][j] < 0) && i < this.\u00ddB - 1 && this.\u00e5B[i + 1][j] >= 0) {
                        this.\u00e5B[i][j] = n;
                        this.\u0115B[n3] = n;
                        ++n;
                        b = true;
                        ++n3;
                    }
                    if ((j == 0 || this.\u00e5B[i][j - 1] < 0) && j < this.\u00dcB - 1 && this.\u00e5B[i][j + 1] >= 0) {
                        if (!b) {
                            this.\u00e5B[i][j] = n;
                            ++n;
                        }
                        this.\u0114B[n2] = n - 1;
                        ++n2;
                    }
                }
            }
        }
        if (n > 99) {
            this.\u0216B = 25;
        }
    }
    
    public int bepaalActiefX() {
        int n;
        for (n = 0; this.\u00e5B[0][n] == -1 || this.\u00e5B[0][n + 1] == -1; ++n) {}
        return n;
    }
    
    public void toonLabel(final int n) {
        this.\u0142B.show(false);
        this.\u0143B.show(false);
        this.\u0146B.show(false);
        this.\u0147B.show(false);
        this.\u0149B.show(false);
        this.\u014aB.show(false);
        this.\u0144B.show(false);
        this.\u0148B.show(false);
        this.\u014dB.show(false);
        this.\u0145B.show(false);
        this.\u014bB.show(false);
        this.\u014cB.show(false);
        switch (n) {
            case 1: {
                this.\u0142B.show(true);
            }
            case 2: {
                this.\u0143B.show(true);
            }
            case 3: {
                this.\u0146B.show(true);
            }
            case 4: {
                this.\u0147B.show(true);
            }
            case 5: {
                this.\u0149B.show(true);
            }
            case 6: {
                this.\u014aB.show(true);
            }
            case 11: {
                this.\u0144B.show(true);
            }
            case 12: {
                this.\u0148B.show(true);
            }
            case 13: {
                this.\u014dB.show(true);
            }
            case 21: {
                this.\u0145B.show(true);
            }
            case 22: {
                this.\u014bB.show(true);
            }
            case 23: {
                this.\u014cB.show(true);
            }
            default: {}
        }
    }
    
    public void verbergComponenten() {
        this.\u0124B.show(false);
        this.\u0157B.show(false);
        if (this.\u03c9.isVisible()) {
            this.\u0209B = 1;
            this.\u03c9.show(false);
        }
        if (this.\u0158B.isVisible()) {
            this.\u0209B = 2;
            this.\u0158B.show(false);
        }
        if (this.\u015cB.isVisible()) {
            this.\u0209B = 3;
            this.\u015cB.show(false);
        }
        this.\u015dB.show(false);
        this.\u011fB.show(false);
        this.\u0120B.show(false);
        this.\u0121B.show(false);
        this.\u0122B.show(false);
        if (this.\u00f5B) {
            this.\u00efB.show(false);
            this.\u00f0B.show(false);
        }
    }
    
    public void toonComponenten() {
        this.\u0124B.show(true);
        this.\u0157B.show(true);
        switch (this.\u0209B) {
            case 1: {
                this.\u03c9.show(true);
                break;
            }
            case 2: {
                this.\u0158B.show(true);
                break;
            }
            case 3: {
                this.\u015cB.show(true);
                break;
            }
        }
        this.\u015dB.show(true);
        this.\u011fB.show(true);
        this.\u0120B.show(true);
        this.\u0121B.show(true);
        this.\u0122B.show(true);
        if (this.\u00f5B) {
            this.\u00efB.show(true);
            this.\u00f0B.show(true);
        }
    }
    
    public void voegtoeaanLijst(final String s) {
        final String substring = s.substring(1);
        this.\u00f6B[this.\u00f8B] = substring;
        ++this.\u00f8B;
        this.\u0155B.addItem(this.converteerPuzzeldatum(substring));
    }
    
    public String converteerPuzzeldatum(final String s) {
        if (s.equalsIgnoreCase(this.\u01e6B)) {
            return "Today";
        }
        return "Yesterday";
    }
    
    public void initPuzzelrij() {
        this.\u0155B.clear();
        for (int i = 0; i < 14; ++i) {
            this.\u00f6B[i] = "";
        }
        this.\u00f8B = 0;
    }
    
    public void declareerChatPaneel() {
        final int n = 2;
        this.\u0157B.setLayout(null);
        this.\u0157B.setBackground(this.\u0134B);
        this.\u0157B.show(false);
        this.\u0158B.setLayout(null);
        this.\u0158B.setBackground(this.\u00c9);
        this.\u0158B.reshape(this.\u0137B + this.\u0138B, 0, this.\u056f - 10, 40 + this.\u00e3B);
        this.\u0159B.setLayout(null);
        this.\u0159B.setBackground(this.\u0133B);
        this.\u0159B.reshape(this.\u0137B + this.\u0138B, 0, this.\u056f - 10, 40 + this.\u00e3B);
        this.\u0159B.show(false);
        this.\u015aB.setLayout(null);
        this.\u015aB.setBackground(this.\u0133B);
        this.\u015aB.show(false);
        this.\u015aB.reshape(this.\u0137B + this.\u0138B, 0, this.\u056f - 10, 40 + this.\u00e3B);
        this.\u015bB.setLayout(null);
        this.\u015bB.setBounds(1, 38, this.\u056f - 2, this.\u0570 - 39);
        this.\u015bB.show(false);
        (this.\u0214B = new TextArea("", 18, 0, 3)).reshape(1, 70, this.\u0158B.size().width - 2, this.\u0158B.size().height - 50);
        this.\u0214B.setEditable(false);
        this.\u0214B.setFont(this.\u020aB);
        this.\u0214B.setBackground(Color.white);
        this.\u0214B.appendText(String.valueOf(this.\u00c9B) + this.\u026b);
        this.\u0214B.appendText(this.\u00caB);
        (this.\u0215B = new HyperlinkComponent("Help", "http://www.nytimes.com/ref/membercenter/help/xwordtogether.html", this, 10)).reshape((this.\u0158B.size().width - 50) / 2, this.\u0158B.size().height - 25, 50, 15);
        this.\u016bB.setText("");
        this.\u016bB.setFont(this.\u018eB);
        this.\u016bB.setBackground(Color.black);
        this.\u016bB.reshape(70, 2, 450, 20);
        this.\u016cB.setText("");
        this.\u016cB.setFont(this.\u018eB);
        this.\u016cB.setBackground(Color.white);
        this.\u016cB.reshape(5, 40, 120, 20);
        this.\u015cB.add(this.\u016cB);
        this.\u016dB.setText("");
        this.\u016dB.setFont(this.\u018eB);
        this.\u016dB.setBackground(Color.white);
        this.\u016dB.reshape(5, 60, 120, 20);
        this.\u015cB.add(this.\u016dB);
        this.\u016eB.setText("");
        this.\u016eB.setFont(this.\u018eB);
        this.\u016eB.setBackground(Color.white);
        this.\u016eB.reshape(5, 80, 120, 20);
        this.\u015cB.add(this.\u016eB);
        this.\u015eB.setText(this.\u06c5);
        this.\u015eB.setFont(this.\u018dB);
        this.\u015eB.setForeground(Color.white);
        this.\u0157B.add(this.\u015eB);
        this.\u015eB.reshape(0, 0, 65, 20);
        this.\u0160B.setText(this.\u06c7);
        this.\u0160B.setFont(this.\u018dB);
        this.\u0160B.setForeground(Color.white);
        this.\u0160B.show(false);
        this.\u0157B.add(this.\u0160B);
        this.\u0160B.reshape(0, 0, this.\u03c9.size().width - 10, 20);
        this.\u0161B.setText(this.\u06c9);
        this.\u0161B.setForeground(Color.white);
        this.\u0161B.setFont(this.\u018dB);
        this.\u0161B.show(false);
        this.\u0157B.add(this.\u0161B);
        this.\u0161B.reshape(0, 21, this.\u03c9.size().width - 50, 20);
        this.\u0157B.add(this.\u0162B);
        this.\u0162B.setBackground(Color.white);
        this.\u0162B.reshape(this.\u03c9.size().width - 100, 1, 99, 18);
        this.\u0163B.setText(this.\u06ca);
        this.\u0163B.setForeground(Color.white);
        this.\u0163B.setBackground(this.\u0134B);
        this.\u0163B.setFont(this.\u018dB);
        this.\u0157B.add(this.\u0163B);
        this.\u0163B.reshape(0, 21, 120, 20);
        this.\u0170B.setBackground(this.\u0134B);
        this.\u0170B.reshape(0, 0, 550, 12);
        this.\u0170B.setFont(this.\u018fB);
        this.\u0171B.setBackground(this.\u0134B);
        this.\u0171B.reshape(0, 0, 550, 12);
        this.\u0171B.setFont(this.\u018fB);
        this.\u0172B.setBackground(Color.blue);
        this.\u0172B.reshape(580, 2, 6, 6);
        this.\u0172B.hide();
        this.\u0173B.setBackground(Color.blue);
        this.\u0173B.reshape(590, 2, 6, 6);
        this.\u0173B.hide();
        this.\u0174B.setBackground(Color.black);
        this.\u0173B.reshape(560, 2, 6, 6);
        (this.\u0164B = new plaatjeKnop(Color.white, null, this.\u0210B, this.\u0211B, this.\u0210B)).reshape(this.\u0157B.size().width - 40, 23, 40, 19);
        this.\u0157B.add(this.\u0164B);
        (this.\u0166B = new plaatjeKnop(Color.white, null, this.\u020eB, this.\u020fB, this.\u020eB)).setBounds(320, 2, 30, 21);
        this.\u0166B.hide();
        this.\u039b.add(this.\u0166B);
        (this.\u0165B = new plaatjeKnop(Color.white, null, this.\u0285, this.\u0285, this.\u0285)).reshape(this.\u03c9.size().width - 40, 122, 34, 19);
        this.\u03c9.add(this.\u0165B);
        this.\u0157B.show(false);
        (this.\u0167B = new plaatjeKnop(Color.white, null, this.\u0284, this.\u0284, this.\u0284)).reshape(358, 122, 34, 19);
        this.\u03c9.add(this.\u0167B);
        this.\u0203B = 40 + this.\u00e3B - 44 - 3 * n;
        if (this.\u0203B * 5 / 100 - this.\u0139B < 0) {}
        (this.\u0168B = new Label()).setText(String.valueOf(this.\u06cb) + " " + this.\u013f);
        this.\u0168B.setFont(this.\u018eB);
        this.\u0168B.reshape(400, 0, 182, 15);
        this.\u03c9.add(this.\u0168B);
        this.\u016aB.setBackground(Color.white);
        this.\u016aB.setFont(this.\u0183B);
        this.\u016aB.setEditable(false);
        this.\u016aB.reshape(12, 0, 380, 115);
        this.\u03c9.add(this.\u016aB);
        this.\u0169B.setBackground(Color.white);
        this.\u0169B.setFont(this.\u0183B);
        this.\u0169B.reshape(12, 120, 344, 22);
        this.\u03c9.add(this.\u0169B);
        (this.\u01feB = new BlacklinedPanel()).setBounds(400, 15, 187, 105);
        this.\u01feB.setBackground(this.\u0571);
        this.\u01feB.setLayout(new GridLayout(10, 0));
        this.\u03c9.add(this.\u01feB);
        (this.\u0258B = new Label()).setText(this.\u0283B);
        this.\u0258B.setFont(this.\u018bB);
        this.\u0258B.setForeground(Color.black);
        this.\u0258B.setBackground(this.\u0133B);
        this.\u0258B.reshape(5, 20, this.\u056f - 10, 15);
        this.\u03c8.add(this.\u0258B);
        (this.\u0259B = new Label()).setText(this.\u0284B);
        this.\u0259B.setFont(this.\u018bB);
        this.\u0259B.setForeground(Color.black);
        this.\u0259B.setBackground(this.\u0133B);
        this.\u0259B.reshape(5, 40, this.\u056f - 10, 15);
        (this.\u025aB = new Label()).setText(this.\u0285B);
        this.\u025aB.setFont(this.\u018bB);
        this.\u025aB.setForeground(this.\u0133B);
        this.\u025aB.setBackground(Color.black);
        this.\u025aB.show(false);
        this.\u025aB.reshape(25, 20, this.\u056f - 10, 15);
        this.\u03c8.add(this.\u025aB);
        (this.\u025bB = new Label()).setText(this.\u0286B);
        this.\u025bB.setFont(this.\u018bB);
        this.\u025bB.setForeground(this.\u0133B);
        this.\u025bB.show(false);
        this.\u025bB.setBackground(Color.black);
        this.\u025bB.reshape(5, 40, this.\u056f - 10, 15);
        this.\u03c8.add(this.\u025bB);
        (this.\u025cB = new Label()).setText(this.\u0289B);
        this.\u025cB.setFont(this.\u018bB);
        this.\u025cB.setForeground(this.\u0133B);
        this.\u025cB.show(false);
        this.\u025cB.setBackground(Color.black);
        this.\u025cB.reshape(5, 40, this.\u056f - 10, 15);
        this.\u03c8.add(this.\u025cB);
        (this.\u025dB = new Label()).setText(this.\u028bB);
        this.\u025dB.setFont(this.\u018bB);
        this.\u025dB.setForeground(this.\u0133B);
        this.\u025dB.setBackground(Color.black);
        this.\u025dB.show(false);
        this.\u025dB.reshape(5, 20, this.\u056f - 10, 15);
        this.\u03c8.add(this.\u025dB);
        (this.\u025eB = new Label()).setText(this.\u028cB);
        this.\u025eB.setFont(this.\u018bB);
        this.\u025eB.setForeground(this.\u0133B);
        this.\u025eB.show(false);
        this.\u025eB.setBackground(Color.black);
        this.\u025eB.reshape(5, 40, this.\u056f - 10, 15);
        this.\u03c8.add(this.\u025eB);
        (this.\u025fB = new Label()).setText(this.\u028dB);
        this.\u025fB.setFont(this.\u018bB);
        this.\u025fB.setForeground(Color.black);
        this.\u025fB.reshape(5, 20, this.\u056f - 10, 15);
        this.\u015bB.add(this.\u025fB);
        (this.\u0263B = new Label()).setText(this.\u0290B);
        this.\u0263B.setFont(this.\u018bB);
        this.\u0263B.setForeground(Color.black);
        this.\u0263B.reshape(5, 40, 75, 15);
        this.\u015bB.add(this.\u0263B);
        (this.\u0266B = new TextField()).setForeground(Color.black);
        this.\u0266B.setFont(this.\u018aB);
        this.\u0266B.reshape(90, 40, 100, 20);
        this.\u015bB.add(this.\u0266B);
        (this.\u0260B = new Label()).setText(this.\u028eB);
        this.\u0260B.setFont(this.\u018bB);
        this.\u0260B.setForeground(Color.black);
        this.\u0260B.setBackground(Color.red);
        this.\u0260B.show(false);
        this.\u0260B.reshape(200, 40, 100, 15);
        this.\u015bB.add(this.\u0260B);
        (this.\u0269B = new plaatjeKnop(Color.white, null, this.\u0286, this.\u0286, this.\u0286)).reshape(5, 65, 44, 19);
        this.\u015bB.add(this.\u0269B);
        (this.\u026aB = new plaatjeKnop(Color.white, null, this.\u0283, this.\u0283, this.\u0283)).reshape(55, 65, 44, 19);
        this.\u015bB.add(this.\u026aB);
        (this.\u0261B = new Label()).setText(this.\u028fB);
        this.\u0261B.setFont(this.\u018bB);
        this.\u0261B.setForeground(Color.black);
        this.\u0261B.setBackground(this.\u0133B);
        this.\u0261B.reshape(5, 80, this.\u056f - 10, 15);
        (this.\u0262B = new Label()).setText(this.\u0290B);
        this.\u0262B.show(false);
        this.\u0262B.reshape(160, 70, 75, 20);
        this.\u03c8.add(this.\u0262B);
        (this.\u0264B = new TextField(15)).reshape(5, 40, 250, 20);
        this.\u03c8.add(this.\u0264B);
        (this.\u0265B = new TextField()).show(false);
        this.\u0265B.setBounds(250, 70, 100, 20);
        this.\u03c8.add(this.\u0265B);
        (this.\u0267B = new plaatjeKnop(Color.white, null, this.\u0282, this.\u0282, this.\u0282)).reshape(5, 94, 44, 19);
        this.\u0267B.addMouseListener(new SetTogether$1(this));
        this.\u03c8.add(this.\u0267B);
        (this.\u0268B = new plaatjeKnop(Color.white, null, this.\u0283, this.\u0283, this.\u0283)).reshape(52, 94, 44, 19);
        this.\u0268B.addMouseListener(new SetTogether$2(this));
        this.\u03c8.add(this.\u0268B);
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        (this.\u026bB = new Checkbox("public", checkboxGroup, true)).reshape(10, 70, 60, 20);
        this.\u03c8.add(this.\u026bB);
        (this.\u026cB = new Checkbox("private", checkboxGroup, false)).reshape(80, 70, 60, 20);
        this.\u03c8.add(this.\u026cB);
        (this.\u026eB = new Label()).setText(this.\u0271B);
        this.\u026eB.setFont(this.\u018bB);
        this.\u026eB.setForeground(Color.black);
        this.\u026eB.setBackground(this.\u0133B);
        this.\u026eB.reshape(5, 40, this.\u056f - 10, 15);
        this.\u0159B.add(this.\u026eB);
        (this.\u026fB = new Label()).setText(this.\u0272B);
        this.\u026fB.setFont(this.\u018bB);
        this.\u026fB.setForeground(Color.black);
        this.\u026fB.setBackground(this.\u0133B);
        this.\u026fB.reshape(5, 60, this.\u056f - 10, 15);
        this.\u0159B.add(this.\u026fB);
        (this.\u0270B = new Label()).setText(this.\u0273B);
        this.\u0270B.setFont(this.\u018bB);
        this.\u0270B.setForeground(Color.black);
        this.\u0270B.setBackground(this.\u0133B);
        this.\u0270B.reshape(5, 80, this.\u056f - 10, 15);
        this.\u0159B.add(this.\u0270B);
        (this.\u026dB = new plaatjeKnop(Color.white, null, this.\u0286, this.\u0286, this.\u0286)).reshape(10, 150, 60, 19);
        this.\u0159B.add(this.\u026dB);
        this.\u03c9.add(this.\u0157B);
        this.\u03c6.add(this.\u015bB);
        this.declareerChatList();
    }
    
    public void createRoom() {
        if (this.\u0264B.getText().trim().length() == 0) {
            this.\u03c8.hide();
            this.\u03cd.setText(this.\u0285B);
            this.\u03ce.setText(this.\u0286B);
            this.\u03d0.setText(this.\u0287B);
            this.\u03cc = 1;
            this.\u03ca.show();
            return;
        }
        if (!this.\u0139.blocked()) {
            if (this.\u0139.inRoom()) {
                this.\u0139.block();
                this.\u0139.leaveRoom();
                this.\u01feB.removeAll();
                this.\u0139.unblock();
                this.\u03c5.clearSolutionList();
                this.\u01fdB.clearList();
            }
            this.\u0292B = false;
            this.\u0253B.setText(this.\u027bB);
            this.\u0254B.setText(this.\u027cB);
            this.\u0255B.setText(this.\u027dB);
            if (this.\u0264B.getText().trim().toLowerCase().startsWith("auto")) {
                this.\u03cd.setText(this.\u0288B);
                this.\u03ce.setText(this.\u0289B);
                this.\u03d0.setText(this.\u028aB);
                this.\u03cc = 1;
                this.\u03ca.show();
                return;
            }
            this.\u0139.block();
            this.\u0164B.show(false);
            String s = this.\u0265B.getText();
            if (s != null) {
                s = s.trim();
            }
            if (this.\u026bB.getState() || (s != null && s.length() > 0)) {
                if (this.\u026bB.getState()) {
                    s = null;
                }
                if (this.\u0139.createRoom(this.\u026b, this.\u0264B.getText().trim(), String.valueOf(this.\u028f), s)) {
                    this.showEnterRoomComponents(this.\u013f = this.\u0264B.getText().trim());
                }
                else {
                    final String errorMessage = this.\u0139.getErrorMessage();
                    if (errorMessage.length() > 15) {
                        this.\u016cB.setText(errorMessage.substring(0, 15));
                        this.\u016dB.setText(errorMessage.substring(15));
                    }
                    else {
                        this.\u016cB.setText(errorMessage);
                    }
                    if (errorMessage.trim().startsWith("Name already")) {
                        this.\u0258B.show(false);
                        this.\u0259B.show(false);
                        this.\u025aB.show(false);
                        this.\u025bB.show(false);
                        this.\u025dB.show(false);
                        this.\u025eB.show(false);
                    }
                    this.\u015cB.show(true);
                    this.\u0158B.show(false);
                    this.\u03c9.show(false);
                    this.\u0164B.show(true);
                }
            }
            else {
                this.\u0258B.show(false);
                this.\u0259B.show(false);
                this.\u025aB.show(false);
                this.\u025bB.show(false);
                this.\u025dB.show(true);
                this.\u025eB.show(true);
            }
            this.repaint();
            this.\u0139.unblock();
        }
    }
    
    public void showEnterRoomComponents(final String s) {
        this.\u0168B.setText(String.valueOf(this.\u06cb) + " " + s);
        this.\u016aB.setText("");
        this.\u03c5.clearSolutionList();
        this.\u01fdB.clearList();
        this.adjustButtonPanel(this.\u028f);
        this.\u026f();
    }
    
    public String pasTekstaan(final String s) {
        String s2 = s;
        final int length = s2.length();
        if (length > 8) {
            s2 = s2.substring(0, 8);
        }
        else if (length < 8) {
            for (int i = 0; i < 8 - length; ++i) {
                s2 = String.valueOf(s2) + ".";
            }
        }
        return s2;
    }
    
    public void verwerkPuzzelString(final String s) {
        int n = 0;
        int n2 = 0;
        int i = 0;
        final int length = s.length();
        try {
            while (i < length - 1) {
                this.\u00d8B[n][n2] = String.valueOf(s.charAt(i));
                this.\u00f9B[n][n2] = true;
                boolean b = true;
                while (b) {
                    if (i < length - 1) {
                        if (s.charAt(i + 1) == ',') {
                            ++i;
                            ++i;
                            final String[] array = this.\u00d8B[n];
                            final int n3 = n2;
                            array[n3] = String.valueOf(array[n3]) + s.charAt(i);
                        }
                        else {
                            b = false;
                        }
                    }
                    else {
                        b = false;
                    }
                }
                if (++n2 == this.\u00ddB) {
                    ++n;
                    n2 = 0;
                }
                ++i;
            }
        }
        catch (Exception ex) {
            this.consoleMessage("i:[" + n + "]j:[" + n2 + "]string:[" + s + "]");
        }
        this.repaint();
    }
    
    public void setTestingConnection(final boolean \u00f5b) {
        this.\u00d5B = \u00f5b;
    }
    
    private synchronized void \u0279(final String s) {
        if (this.\u0139.inRoom()) {
            this.\u0139.sendChatMessage("[" + this.\u00d0B + "]:" + s);
        }
    }
    
    public synchronized void processSetFound(final int n, final int n2, final int n3) {
        if (this.displayTogetherPanel && this.\u0139.inRoom() && !this.\u0292B) {
            this.\u0139.sendSetFound(n, n2, n3, this.\u026b);
        }
    }
    
    public void verwerkPuzzelstukje(final int n, final int n2, String trim, final int n3) {
        trim = trim.toUpperCase().trim();
        if (trim.trim().length() != 0) {
            this.\u00d8B[n][n2] = trim;
            this.\u00d9B[n][n2] = n3;
            this.\u00f9B[n][n2] = true;
        }
        else {
            this.\u00d8B[n][n2] = "";
            this.\u00f9B[n][n2] = true;
        }
        if (this.\u027c()) {
            this.\u012aB.enable();
        }
        else {
            this.\u012aB.disable();
        }
        this.repaint();
    }
    
    public void processSetReceived(final int n, final int n2, final int n3) {
        this.\u03c5.setSolution(n, n2, n3);
    }
    
    String \u027a(final String s, final String s2, final String s3, final String s4) {
        String s5 = "";
        String substring = s;
        for (int i = substring.indexOf(s4, 0); i >= 0; i = substring.indexOf(s4, 0)) {
            int n;
            if (i > 0) {
                n = i - s2.length();
            }
            else {
                n = -1;
            }
            final int index = substring.indexOf(s2, i + s4.length());
            if (n >= 0 && index >= 0) {
                s5 = String.valueOf(substring.substring(0, n)) + substring.substring(index);
            }
            if (n >= 0 && index == -1) {
                s5 = substring.substring(0, n);
            }
            if (n == -1 && index >= 0) {
                s5 = substring.substring(index + s2.length());
            }
            if (n == -1 && index == -1) {
                s5 = "";
            }
            substring = s5;
        }
        int j = substring.indexOf(s2, 0);
        String string = "";
        while (j >= 0) {
            string = String.valueOf(string) + substring.substring(0, j) + s3;
            substring = substring.substring(j + s2.length());
            j = substring.indexOf(s2, 0);
        }
        return String.valueOf(string) + substring;
    }
    
    public void verwerkChatTekst(final String s) {
        final String \u027a = this.\u027a(s, "<DELIM>", "\n", "[" + this.\u00d0B + "]:");
        if (\u027a.length() > 0) {
            this.\u016aB.appendText(String.valueOf(\u027a) + "\n");
        }
    }
    
    public void verwerkSetMove(String s) {
        final int index = s.indexOf("[");
        final int index2 = s.indexOf("]");
        final int int1 = Integer.parseInt(s.substring(index + 1, index2));
        s = s.substring(index2 + 1);
        final int index3 = s.indexOf("[");
        final int index4 = s.indexOf("]");
        final int int2 = Integer.parseInt(s.substring(index3 + 1, index4));
        s = s.substring(index4 + 1);
        final int index5 = s.indexOf("[");
        final int index6 = s.indexOf("]");
        final int int3 = Integer.parseInt(s.substring(index5 + 1, index6));
        s = s.substring(index6 + 1);
        this.\u03c5.setSolution(int1, int2, int3, this.getUserColor(s.substring(s.indexOf("[") + 1, s.indexOf("]"))));
    }
    
    public void declareerChatList() {
        this.\u01fcB = new ClientUserList();
        this.\u01fdB = new UserList(5);
        this.\u01ffB = new Color[101];
        (this.\u0200B = new Color[5])[0] = new Color(255, 255, 153);
        this.\u0200B[1] = new Color(204, 255, 255);
        this.\u0200B[2] = new Color(255, 102, 102);
        this.\u0200B[3] = new Color(153, 255, 153);
        this.\u0200B[4] = new Color(204, 153, 255);
        this.\u027b();
        final int n = 17;
        final int n2 = 10;
        final int n3 = 160;
        final int n4 = 20;
        this.\u0201B = new Label[5];
        for (int i = 0; i < 5; ++i) {
            (this.\u0201B[i] = new Label()).setBackground(this.\u0571);
            this.\u0201B[i].setFont(this.\u01edB);
            this.\u0201B[i].reshape(0, 12 + i * n4 - 5, n, n2);
        }
        this.\u0202B = new Label[5];
        for (int j = 0; j < 5; ++j) {
            (this.\u0202B[j] = new Label()).setBackground(this.\u0571);
            this.\u0202B[j].setFont(this.\u01edB);
            this.\u0202B[j].reshape(22, 12 + j * n4 - 10, n3, n4);
        }
        for (int k = 0; k < 5; ++k) {
            this.\u01feB.add(this.\u0201B[k]);
            this.\u01feB.add(this.\u0202B[k]);
        }
    }
    
    void \u027b() {
        for (int i = 0; i < 101; ++i) {
            this.\u01ffB[i] = Color.black;
        }
    }
    
    public Color getUserColor(final String s) {
        if (this.\u01fdB.getPosition(s) == -1) {
            System.out.println("CORRECTION:updating list");
            this.updateRoomUserList(this.\u0139.processGetRoomUsersList());
        }
        return this.\u0200B[this.\u01fdB.getPosition(s)];
    }
    
    public synchronized void updateRoomUserList(String s) {
        for (int i = 0; i < 5; ++i) {
            this.\u0201B[i].setBackground(this.\u0571);
            this.\u0202B[i].setForeground(Color.black);
            this.\u0202B[i].setText("");
        }
        final boolean[] array = new boolean[5];
        for (int j = 0; j < 5; ++j) {
            array[j] = false;
        }
        final String s2 = s;
        while (s.trim().length() > 0) {
            final String tagValue = this.getTagValue(s, "<USER>");
            s = this.removeTagValue(s, "<USER>");
            final String tagValue2 = this.getTagValue(tagValue, "<NAME>");
            final int tagValueInt = this.getTagValueInt(tagValue, "<ID>");
            if (this.\u01fdB.userExists(tagValueInt, tagValue2)) {
                array[this.\u01fdB.getPosition(tagValueInt, tagValue2)] = true;
            }
        }
        for (int k = 0; k < 5; ++k) {
            if (!array[k]) {
                this.\u01fdB.removeUserAtPosition(k);
            }
        }
        s = s2;
        int n = 0;
        while (s.trim().length() > 0) {
            final String tagValue3 = this.getTagValue(s, "<USER>");
            s = this.removeTagValue(s, "<USER>");
            final String tagValue4 = this.getTagValue(tagValue3, "<NAME>");
            final int tagValueInt2 = this.getTagValueInt(tagValue3, "<ID>");
            if (!this.\u01fdB.userExists(tagValueInt2, tagValue4)) {
                this.\u01fdB.addUser(tagValueInt2, tagValue4);
            }
            this.\u0201B[n].setBackground(this.\u0200B[this.\u01fdB.getPosition(tagValueInt2, tagValue4)]);
            this.\u0202B[n].setForeground(Color.black);
            this.\u0202B[n].setText(tagValue4);
            ++n;
        }
        this.repaint();
    }
    
    public synchronized void updatePuzzle(final String s) {
        this.consoleMessage("gkb:[" + s + "]");
        final int int1 = Integer.parseInt(s.substring(0, 1));
        if (int1 != this.\u028f) {
            this.\u028f = int1;
            this.loadPuzzle();
        }
        this.verwerkPuzzelString(s.substring(2));
    }
    
    public synchronized void reportConnectionLost(final String s) {
        if (!this.\u01c1B.isVisible()) {
            this.\u01c2B.setText(String.valueOf(this.\u0690) + s + this.\u0691);
            this.\u01c3B.setText(this.\u0696);
            this.\u01bfB.setBackground(SetTogether.\u01acB);
            this.\u01bfB.show(true);
        }
    }
    
    public void reportOutOfRoom() {
        if (!this.\u01bfB.isVisible() && !this.\u00d3B) {
            this.\u01c1B.show(true);
        }
    }
    
    public int sendServlet(final String s) {
        String line = null;
        URL url = null;
        try {
            url = new URL("http", this.getHostname(), 80, s);
        }
        catch (MalformedURLException ex) {
            this.consoleMessage("UNABLE TO DETECT SERVER");
        }
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            line = dataInputStream.readLine();
            dataInputStream.close();
        }
        catch (IOException ex2) {
            System.out.println("Can't connect to servlet for reading puzzeldata!");
        }
        int int1;
        if (line == null) {
            int1 = 0;
        }
        else {
            int1 = Integer.parseInt(line);
        }
        return int1;
    }
    
    public int verzendPuzzel() {
        String checkPuzzel = this.checkPuzzel();
        final String s = "/nytimes-settogether/CheckCrosswordTogether?";
        final String s2 = "";
        final String s3 = "&puzzle=";
        final String s4 = "resultaat=";
        if (checkPuzzel.length() == 0) {
            checkPuzzel = "NIKS";
        }
        return this.sendServlet(String.valueOf(s) + "&" + s2.concat(s4).concat(URLEncoder.encode(checkPuzzel)).concat(s3));
    }
    
    boolean \u027c() {
        for (int i = 0; i < this.\u00dcB; ++i) {
            for (int j = 0; j < this.\u00ddB; ++j) {
                if (this.\u00e5B[j][i] >= 0 && this.\u00d8B[i][j].trim().length() == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean connected() {
        return this.\u013e != null && this.\u013e.length() > 0;
    }
    
    public String getConnectionID() {
        return this.\u013e;
    }
    
    public String getUsername() {
        return this.\u0179B;
    }
    
    public void consoleMessage(final String s) {
    }
    
    public void displaySystemMessage(final String text) {
        this.\u0170B.setText(text);
    }
    
    public void showSystemMessages() {
        this.\u0170B.setText("");
        this.\u015dB.remove(this.\u0171B);
        this.\u015dB.add(this.\u0170B);
    }
    
    public void hideSystemMessages() {
        this.\u0170B.setText("");
        this.\u015dB.add(this.\u0171B);
        this.\u015dB.remove(this.\u0170B);
    }
    
    public void start() {
        this.\u03c5.repaint();
        this.\u03c5.requestFocus();
        this.repaint();
        if (this.\u01beB != null) {
            this.\u01beB.stop();
            this.\u01beB = null;
        }
    }
    
    public void stop() {
        if (this.\u01beB != null) {
            this.consoleMessage("Problem with killThread. Exists but should not");
            return;
        }
        (this.\u01beB = new KillT001(this)).start();
    }
    
    public void destroy() {
        if (this.displayTogetherPanel) {
            this.\u0139.killAll();
            if (this.\u0291B != null) {
                this.\u0291B.stop();
            }
            this.\u0291B = null;
            if (this.\u01beB != null) {
                this.\u01beB.stop();
                this.\u01beB = null;
            }
        }
    }
    
    private synchronized void \u027d() {
        this.\u01c9B.show();
        this.\u01caB.hide();
        this.\u01cbB.hide();
        this.\u01ccB.hide();
        this.\u0271();
        this.leaveRoom();
        this.\u0292B = false;
        this.\u0253B.setText(this.\u027bB);
        this.\u0254B.setText(this.\u027cB);
        this.\u0255B.setText(this.\u027dB);
        this.\u0139.reset();
        this.\u03c5.clearSolutionList();
        this.\u01fdB.clearList();
    }
    
    public void leaveRoom() {
        this.\u0291B.\u0124();
        this.\u016cB.setText("");
        this.\u016dB.setText("");
        this.\u016eB.setText("");
        this.\u0179B = "";
        this.\u017aB = "";
        this.\u013e = "";
        this.\u01fcB.\u00dc();
        this.\u03c5.clearSolutionList();
        this.\u01fdB.clearList();
        this.\u03c5.repaint();
        this.repaint();
    }
    
    public void displayLoadTime(final String s) {
        final Date date = new Date();
        date.getHours();
        date.getMinutes();
        date.getSeconds();
    }
    
    public String getPuzzleContent() {
        String s = "";
        for (int i = 0; i < this.\u00dcB; ++i) {
            for (int j = 0; j < this.\u00ddB; ++j) {
                if (this.\u00d8B[i][j].length() == 1) {
                    s = String.valueOf(s) + this.\u00d8B[i][j];
                }
                else {
                    s = String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(s)).append(this.\u00d8B[i][j].substring(0, 1)).toString())).append(",").toString()) + this.\u00d8B[i][j].substring(1).trim();
                }
            }
        }
        return s;
    }
    
    public synchronized int getHashCodeCurrentSituation() {
        return this.getPuzzleContent().hashCode();
    }
    
    public boolean loadPuzzle() {
        String s;
        if (this.\u00d4 == null && this.\u0685 == null) {
            s = this.getUUID("", "");
        }
        else {
            s = this.getUUID(this.\u00d4, this.\u0682);
        }
        return this.loadPuzzle(s);
    }
    
    private boolean loadPuzzle(final String s) {
        final String doGet = this.doGet("pm=load&type=current&uuid=" + URLEncoder.encode(s) + "&puzzlenumber=" + this.\u028f);
        if (doGet.startsWith("<ERROR>")) {
            super.appletInitResult = 1;
            return false;
        }
        if (!this.\u03c5.setPuzzle(doGet)) {
            super.appletInitResult = 1;
            return false;
        }
        final int tagValueInt = this.getTagValueInt(doGet, "<LEVEL>");
        if (this.\u02a6[tagValueInt] != null) {
            this.\u0674.setImage(this.\u02a6[tagValueInt]);
        }
        this.\u028e = 4;
        this.\u028f = this.getTagValueInt(doGet, "<NUMBER>");
        for (int i = 0; i < 4; ++i) {
            this.\u046a[i].setMouseUp();
        }
        this.\u046a[this.\u028f - 1].setMouseDown();
        return true;
    }
    
    public String readTextFromJar(final String s) {
        final StringBuffer sb = new StringBuffer();
        try {
            String line;
            while ((line = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(s))).readLine()) != null) {
                sb.append(String.valueOf(line) + " <n> ");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }
    
    public void readHelpText() {
        this.\u067c = this.readTextFromJar("help.txt");
    }
    
    public void readColors() {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("colors.txt")));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String tagValueString = this.getTagValueString(line, "[", "]");
                final String trim = line.substring(line.indexOf("]") + 1).trim();
                final String trim2 = this.getTagValueString(trim, "[", "]").trim();
                final String trim3 = trim.substring(trim.indexOf("]") + 1).trim();
                final String trim4 = this.getTagValueString(trim3, "[", "]").trim();
                final String trim5 = this.getTagValueString(trim3.substring(trim3.indexOf("]") + 1).trim(), "[", "]").trim();
                int int1;
                int int2;
                int int3;
                try {
                    int1 = Integer.parseInt(trim2);
                    int2 = Integer.parseInt(trim4);
                    int3 = Integer.parseInt(trim5);
                }
                catch (Exception ex) {
                    int1 = 255;
                    int2 = 255;
                    int3 = 255;
                    System.out.println("Exception occured. e=[" + ex.toString() + "]");
                }
                if (tagValueString.equals("APPLETCOLOR")) {
                    this.\u00c9 = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("CURSOR_COLOR")) {
                    this.\u0293 = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("FONTCOLOR")) {
                    this.\u0296 = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("HEADERLABEL_BACKGROUND")) {
                    this.\u0295 = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("STARTCELL_BACKGROUND_COLOR")) {
                    this.\u0297 = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("STARTCELL_FOREGROUND_COLOR")) {
                    this.\u0298 = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("CHECK_WRONG_COLOR")) {
                    this.\u0294 = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("MENUBAR")) {
                    this.\u0299 = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("ACTIVE_LETTER")) {
                    this.\u029e = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("ACTIVE_WORD")) {
                    this.\u029f = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("CLUE_BACKGROUND")) {
                    this.\u029a = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("LETTER_WRONG")) {
                    this.\u029d = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("ACTIVE_CLUE")) {
                    this.\u029b = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("CROSSING_CLUE")) {
                    this.\u029c = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("LETTER_SHOWN")) {
                    this.\u02a0 = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("MARK")) {
                    this.\u02a1 = new Color(int1, int2, int3);
                }
            }
            bufferedReader.close();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
    
    public void readText() {
        this.\u02a2 = new String[150];
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("text.txt")));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final int n = line.indexOf("]", 0) + 1;
                if (n >= 0) {
                    this.\u02a2[this.getTagValue(line, "[", "]")] = line.substring(n).trim();
                }
            }
            bufferedReader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void setText() {
        this.\u0403 = this.\u02a2[20];
        this.\u0405 = this.\u02a2[21];
        this.\u040b = this.\u02a2[22];
        this.\u0412 = this.\u02a2[23];
        this.\u044b = this.\u02a2[30];
        this.\u044d = this.\u02a2[31];
        this.\u0478 = this.\u02a2[40];
        this.\u047a = this.\u02a2[41];
        this.\u0494 = this.\u02a2[43];
        this.\u0480 = this.\u02a2[42];
        this.\u04f4 = this.\u02a2[50];
        this.\u04f8 = this.\u02a2[51];
        this.\u0536 = this.\u02a2[52];
        this.\u053f = this.\u02a2[53];
        this.\u0545 = this.\u02a2[54];
        this.\u00c2B = this.\u02a2[55];
        this.\u067f = this.\u02a2[56];
        this.\u0680 = this.\u02a2[57];
        this.\u0681 = this.\u02a2[58];
        this.\u04d7 = this.\u02a2[60];
    }
    
    public int getTagValue(final String s, final String s2, final String s3) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, String.valueOf(s2) + s3);
        String nextToken;
        try {
            if (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
            }
            else {
                nextToken = "0";
            }
        }
        catch (NoSuchElementException ex) {
            nextToken = "0";
        }
        int int1;
        try {
            int1 = Integer.parseInt(nextToken);
        }
        catch (Exception ex2) {
            int1 = 0;
        }
        return int1;
    }
    
    public String removeChar(final String s, final char c) {
        String string = "";
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != c) {
                string = String.valueOf(string) + s.charAt(i);
            }
        }
        return string;
    }
    
    public String getTagValueString(final String s, final String s2, final String s3) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, String.valueOf(s2) + s3);
        String nextToken;
        try {
            if (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
            }
            else {
                nextToken = "";
            }
        }
        catch (NoSuchElementException ex) {
            nextToken = "";
        }
        return nextToken;
    }
    
    private void \u027e() {
        this.\u0251B = new Vector();
        this.\u0252B = new Vector();
        (this.\u00cfB = new ImagePanel()).reshape(0, 0, this.size().width, this.size().height);
        this.\u00cfB.setBackground(Color.black);
        (this.\u0115 = new Label("ERROR LOADING PUZZLE")).setBackground(Color.white);
        this.\u00cfB.add(this.\u0115);
        this.\u00cfB.hide();
        (this.\u01c7 = new ImagePanel()).setLayout(null);
        this.\u01c7.setBounds(this.\u01c8, this.\u01c9, this.\u01ca, this.\u01cb);
        this.\u01c7.setBackground(this.\u01cc);
        this.\u01c7.setForeground(this.\u01e7);
        this.\u01c7.setString(this.\u01e8);
        this.add(this.\u01c7);
        (this.\u02bb = new ImagePanel()).setBounds(this.\u02bc, this.\u02bd, this.\u02be, this.\u02bf);
        this.\u02bb.setBackground(this.\u02c0);
        this.\u02bb.setForeground(this.\u02c1);
        this.\u02bb.setString(this.\u02d0);
        this.\u02bb.setImage(this.\u02a7);
        this.\u02bb.hideString();
        (this.\u02d1 = new ImagePanel()).setLayout(null);
        this.\u02d1.setBounds(this.\u02e0, this.\u02e1, this.\u02e2, this.\u02e3);
        this.\u02d1.setBackground(this.\u02e4);
        this.\u02d1.setForeground(this.\u0388);
        this.\u02d1.setString(this.\u0389);
        this.\u01c7.add(this.\u02d1);
        (this.\u0393 = new ImagePanel()).setBounds(this.\u0394, this.\u0395, this.\u0396, this.\u0397);
        this.\u0393.setBackground(this.\u0398);
        this.\u0393.setForeground(this.\u0399);
        this.\u0393.setString(this.\u039a);
        this.\u0393.setImage(this.\u02a8);
        this.\u0393.setImagePosition(this.\u0396 - 134, 1);
        this.\u0393.hideString();
        this.\u01c7.add(this.\u0393);
        (this.\u039b = new ImagePanel()).setLayout(null);
        this.\u039b.setBounds(0, 1, 397, 26);
        this.\u039b.setBackground(this.\u03a0);
        this.\u039b.setForeground(this.\u03a1);
        (this.\u03a4 = new ImageHyperLinkPanel(this, this.\u0292, this.\u0292, "http://www.setgame.com")).setLayout(null);
        this.\u03a4.setBounds(this.\u03a5, this.\u03a6, this.\u03a7, this.\u03a8);
        this.\u03a4.setBackground(this.\u03a9);
        this.\u03a4.setForeground(this.\u03aa);
        (this.\u03ac = new ImagePanel()).setLayout(null);
        this.\u03ac.setBounds(this.\u03ad, this.\u03ae, this.\u03af, this.\u03b0);
        this.\u03ac.setBackground(this.\u03b1);
        this.\u03ac.setForeground(this.\u03b2);
        this.\u03ac.setString(this.\u03b3);
        this.\u03ac.setImage(this.\u027d);
        this.\u03ac.setImagePosition(0, 0);
        this.\u03ac.hideString();
        this.\u039b.add(this.\u03ac);
        (this.\u03b4 = new ImagePanel()).setLayout(null);
        this.\u03b4.setBounds(this.\u03b5, this.\u03b6, this.\u03b7, this.\u03b8);
        this.\u03b4.setBackground(this.\u03b9);
        this.\u03b4.setForeground(this.\u03ba);
        this.\u03b4.setString(this.\u03bb);
        this.\u03b4.setImage(this.\u027e);
        this.\u03b4.setImagePosition(0, 0);
        this.\u03b4.hideString();
        this.\u039b.add(this.\u03b4);
        (this.\u03bc = new Label()).setText(this.\u03c1);
        this.\u03bc.setFont(this.\u03c2);
        this.\u03bc.setBackground(this.\u03c4);
        this.\u03bc.setForeground(this.\u03c3);
        this.\u03bc.setBounds(this.\u03bd, this.\u03bd, this.\u03bf, this.\u03c0);
        (this.\u03c6 = new ImagePanel()).setLayout(null);
        this.\u03c6.setBounds(this.\u056d, this.\u056e, this.\u056f, this.\u0570);
        this.\u03c6.setBackground(this.\u0571);
        this.\u03c6.setForeground(this.\u0572);
        this.\u03c6.setString(this.\u0573);
        this.\u03c6.setBorderColor(new Color(204, 204, 204));
        this.\u03c6.showBorder(true);
        this.\u03c6.hideString();
        if (!this.displayTogetherPanel) {
            this.\u03c6.hide();
        }
        this.\u01c7.add(this.\u03c6);
        (this.\u03c7 = new ImagePanel()).setLayout(null);
        this.\u03c7.setForeground(Color.black);
        this.\u03c7.setBounds(1, 38, this.\u056f - 2, this.\u0570 - 39);
        this.\u03c6.add(this.\u03c7);
        (this.\u03c8 = new ImagePanel()).setLayout(null);
        this.\u03c8.setForeground(Color.black);
        this.\u03c8.setBounds(1, 38, this.\u056f - 2, this.\u0570 - 39);
        this.\u03c8.hide();
        this.\u03c6.add(this.\u03c8);
        (this.\u03c9 = new ImagePanel()).setLayout(null);
        this.\u03c9.setForeground(Color.black);
        this.\u03c9.setBounds(1, 38, this.\u056f - 2, this.\u0570 - 39);
        this.\u03c9.hide();
        this.\u03c6.add(this.\u03c9);
        (this.\u03ca = new ImagePanel()).setLayout(null);
        this.\u03ca.setForeground(Color.black);
        this.\u03ca.setBounds(1, 38, this.\u056f, this.\u0570 - 39);
        this.\u03ca.hide();
        this.\u03c6.add(this.\u03ca);
        (this.\u03cd = new Label()).setText("");
        this.\u03cd.setFont(this.\u027aB);
        this.\u03cd.setBackground(this.\u0133B);
        this.\u03cd.reshape(12, 20, this.\u056f, 15);
        this.\u03ca.add(this.\u03cd);
        (this.\u03ce = new Label()).setText("");
        this.\u03ce.setFont(this.\u027aB);
        this.\u03ce.setBackground(this.\u0133B);
        this.\u03ce.reshape(12, 40, this.\u056f, 15);
        this.\u03ca.add(this.\u03ce);
        (this.\u03d0 = new Label()).setText("");
        this.\u03d0.setFont(this.\u027aB);
        this.\u03d0.setBackground(this.\u0133B);
        this.\u03d0.reshape(12, 60, this.\u056f, 15);
        this.\u03ca.add(this.\u03d0);
        (this.\u03d1 = new ImageButton(this.\u02b3, this.\u02b4, this.\u02b5)).setBounds(12, 80, this.\u04bf, this.\u04c0);
        this.\u03d1.hideString();
        this.\u03d1.addMouseListener(new SetTogether$3(this));
        this.\u03ca.add(this.\u03d1);
        (this.\u03e2 = new ImagePanel(this.\u020dB)).setBounds(this.\u03e3, this.\u03e4, this.\u03e5, this.\u03e6);
        this.\u03e2.setBackground(this.\u03e7);
        this.\u03c6.add(this.\u03e2);
        (this.\u0253B = new Label()).setText(this.\u027bB);
        this.\u0253B.setFont(this.\u027aB);
        this.\u0253B.setBackground(this.\u0133B);
        this.\u0253B.setBounds(12, 0, this.\u03e5, 15);
        this.\u03c7.add(this.\u0253B);
        (this.\u0254B = new Label()).setText(this.\u027cB);
        this.\u0254B.setFont(this.\u027aB);
        this.\u0254B.setBackground(this.\u0133B);
        this.\u0254B.setBounds(12, 15, this.\u03e5, 15);
        this.\u03c7.add(this.\u0254B);
        (this.\u0255B = new Label()).setText(this.\u027dB);
        this.\u0255B.setFont(this.\u027aB);
        this.\u0255B.setBackground(this.\u0133B);
        this.\u0255B.reshape(5, 50, this.\u056f - 10, 15);
        (this.\u0256B = new Label()).setText(this.\u0281B);
        this.\u0256B.setFont(this.\u027aB);
        this.\u0256B.setBackground(this.\u0133B);
        this.\u0256B.reshape(12 + this.\u03e5 + 70, 0, this.\u03e5, 15);
        this.\u03c7.add(this.\u0256B);
        (this.\u0257B = new Label()).setText(this.\u0282B);
        this.\u0257B.setFont(this.\u027aB);
        this.\u0257B.setBackground(this.\u0133B);
        this.\u0257B.reshape(12 + this.\u03e5 + 70, 15, this.\u03e5, 15);
        this.\u03c7.add(this.\u0257B);
        (this.\u0217B = new ScrollColorList()).setBackground(this.\u00c9);
        this.\u0217B.setFont(new Font("Arial", 1, 12));
        this.\u0217B.reshape(12, 40, this.\u03e5, 85);
        this.\u03c7.add(this.\u0217B);
        (this.\u0250B = new ScrollColorList()).setBackground(this.\u00c9);
        this.\u0250B.setFont(new Font("Arial", 1, 12));
        this.\u0250B.reshape(12 + this.\u03e5 + 70, 40, this.\u03e5, 85);
        this.\u03c7.add(this.\u0250B);
        (this.\u01c0B = new ImagePanel()).setBackground(SetTogether.\u01acB);
        this.\u01c0B.reshape(140, 50, 400, 200);
        this.\u01c0B.setLayout(null);
        this.\u01c0B.show(false);
        (this.\u01c4B = new Label()).setBackground(SetTogether.\u01acB);
        this.\u01c4B.setAlignment(1);
        this.\u01c4B.reshape(5, 10, 390, 20);
        this.\u01c4B.setForeground(Color.black);
        this.\u01c4B.setText(this.\u06cd);
        this.\u01c0B.add(this.\u01c4B);
        (this.\u01c5B = new Label()).setBackground(SetTogether.\u01acB);
        this.\u01c5B.setAlignment(1);
        this.\u01c5B.reshape(5, 30, 390, 20);
        this.\u01c5B.setForeground(Color.black);
        this.\u01c5B.setText(this.\u06ce);
        this.\u01c0B.add(this.\u01c5B);
        (this.\u01cdB = new Button("OK")).setFont(this.\u0154B);
        this.\u01cdB.reshape(180, 140, 40, 20);
        this.\u01c0B.add(this.\u01cdB);
        (this.\u01c1B = new ImagePanel()).setBackground(SetTogether.\u01acB);
        this.\u01c1B.reshape(140, 50, 400, 200);
        this.\u01c1B.setLayout(null);
        this.\u01c1B.show(false);
        (this.\u01c6B = new Label()).setBackground(SetTogether.\u01acB);
        this.\u01c6B.setAlignment(1);
        this.\u01c6B.reshape(5, 10, 390, 20);
        this.\u01c6B.setForeground(Color.black);
        this.\u01c6B.setText(this.\u06d0);
        this.\u01c1B.add(this.\u01c6B);
        (this.\u01c7B = new Label()).setBackground(SetTogether.\u01acB);
        this.\u01c7B.setAlignment(1);
        this.\u01c7B.reshape(5, 30, 390, 20);
        this.\u01c7B.setForeground(Color.black);
        this.\u01c7B.setText(this.\u06d1);
        this.\u01c1B.add(this.\u01c7B);
        (this.\u01c8B = new Label()).setBackground(SetTogether.\u01acB);
        this.\u01c8B.setAlignment(1);
        this.\u01c8B.reshape(5, 50, 390, 20);
        this.\u01c8B.setForeground(Color.black);
        this.\u01c8B.setText(this.\u06d2);
        this.\u01c1B.add(this.\u01c8B);
        (this.\u01cfB = new Button("OK")).setFont(this.\u0154B);
        this.\u01cfB.reshape(180, 140, 40, 20);
        this.\u01c1B.add(this.\u01cfB);
        (this.\u042c = new ImageButton(this.\u0286, this.\u0286, this.\u0286)).setBounds(this.\u042d, this.\u042e, this.\u042f, this.\u0430);
        this.\u042c.addMouseListener(new SetTogether$4(this));
        this.\u03c7.add(this.\u042c);
        (this.\u0432 = new ImageButton(this.\u0287, this.\u0287, this.\u0287)).setBounds(this.\u0433, this.\u0434, this.\u0435, this.\u0436);
        this.\u0432.addMouseListener(new SetTogether$5(this));
        this.\u03c7.add(this.\u0432);
    }
    
    private void \u027f() {
        (this.\u03c5 = new SetPanel(this, this.\u00c9, this.\u0291, this.\u0251, this.\u02a2[100], this.\u02a2[101], this.\u0566, this.\u0567, this.\u0568, this.\u0569)).setBounds(this.\u0566, this.\u0567, this.\u0568, this.\u0569);
        this.\u03c5.setForeground(this.\u056b);
        this.\u03c5.setBackground(this.\u056a);
        this.\u03c5.setString(this.\u056c);
        this.\u03c5.hideString();
        this.\u02d1.add(this.\u03c5);
        this.\u03c5.\u01c7.add(this.\u039b);
        (this.\u0252 = new ImageButton(this.\u02b0, this.\u02b1, this.\u02b2)).setBounds(this.\u0647, this.\u0648, this.\u0649, this.\u064a);
        this.\u0252.setBackground(this.\u0671);
        this.\u0252.setForeground(this.\u0672);
        this.\u0252.setString(this.\u0673);
        this.\u0252.hideString();
        this.\u0252.addMouseListener(new SetTogether$6(this));
        (this.\u011cB = new ImagePanel()).setLayout(null);
        this.\u011cB.setBounds(this.\u04c4, this.\u04c7, this.\u04c8, this.\u04cb);
        this.\u011cB.setBackground(this.\u04cc);
        this.\u011cB.setForeground(this.\u04d0);
        this.\u011cB.setString(this.\u04d1);
        this.\u011cB.showBorder(true);
        this.\u011cB.hideString();
        this.\u011cB.hide();
        this.\u02d1.add(this.\u011cB);
        (this.\u04d2 = new Label(this.\u04d7)).setAlignment(1);
        this.\u04d2.setBounds(this.\u04d3, this.\u04d4, this.\u04d5, this.\u04d6);
        this.\u04d2.setBackground(this.\u0295);
        this.\u04d2.setForeground(this.\u04d8);
        this.\u011cB.add(this.\u04d2);
        (this.\u04d9 = new TextCanvas()).setFont(this.\u03d3);
        this.\u04d9.setText(this.\u067c);
        this.\u04d9.setBounds(this.\u04da, this.\u04db, this.\u04dc, this.\u04dd);
        this.\u04d9.setBackground(this.\u04de);
        this.\u04d9.setForeground(this.\u0296);
        this.\u011cB.add(this.\u04d9);
        (this.\u04df = new ImageButton(this.\u02b3, this.\u02b4, this.\u02b5)).setBounds(this.\u04e0, this.\u04e1, this.\u04e2, this.\u04e3);
        this.\u04df.setBackground(this.\u04e4);
        this.\u04df.setForeground(this.\u04e5);
        this.\u04df.setString(this.\u04e6);
        this.\u04df.hideString();
        this.\u04df.addMouseListener(new SetTogether$7(this));
        this.\u011cB.add(this.\u04df);
        this.\u046a = new ImageButton[4];
        for (int i = 0; i < 4; ++i) {
            final int n = i;
            this.\u046a[i] = new ImageButton(this.\u027f[i], this.\u0281[i], this.\u0280[i]);
            int n2 = 0;
            switch (i) {
                case 0: {
                    n2 = 132;
                    break;
                }
                case 1: {
                    n2 = 161;
                    break;
                }
                case 2: {
                    n2 = 68;
                    break;
                }
                case 3: {
                    n2 = 97;
                    break;
                }
            }
            this.\u046a[i].setBounds(n2, 0, 24, 17);
            this.\u046a[i].setMaintainState(true);
            this.\u046a[i].addMouseListener(new SetTogether$8(n, this));
            if (i < 2) {
                this.\u03ac.add(this.\u046a[i]);
            }
            else {
                this.\u03b4.add(this.\u046a[i]);
            }
        }
    }
    
    public void loadImages(final Date date) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        this.displayLoadTime("[START LOAD CARD IMAGES]", date);
        for (int i = 0; i < 82; ++i) {
            mediaTracker.addImage(this.\u0251[i] = this.getImage(this.getCodeBase(), "graphics/" + i + ".gif"), 0);
        }
        this.displayLoadTime("[END LOAD CARD IMAGES]", date);
        this.\u02a6 = new Image[3];
        mediaTracker.addImage(this.\u0271 = this.getImage(this.getCodeBase(), "graphics/1_black.gif"), 0);
        mediaTracker.addImage(this.\u0272 = this.getImage(this.getCodeBase(), "graphics/1_blue.gif"), 0);
        mediaTracker.addImage(this.\u0273 = this.getImage(this.getCodeBase(), "graphics/1_line.gif"), 0);
        mediaTracker.addImage(this.\u0274 = this.getImage(this.getCodeBase(), "graphics/2_black.gif"), 0);
        mediaTracker.addImage(this.\u0275 = this.getImage(this.getCodeBase(), "graphics/2_blue.gif"), 0);
        mediaTracker.addImage(this.\u0276 = this.getImage(this.getCodeBase(), "graphics/2_line.gif"), 0);
        mediaTracker.addImage(this.\u0277 = this.getImage(this.getCodeBase(), "graphics/3_black.gif"), 0);
        mediaTracker.addImage(this.\u0278 = this.getImage(this.getCodeBase(), "graphics/3_blue.gif"), 0);
        mediaTracker.addImage(this.\u0279 = this.getImage(this.getCodeBase(), "graphics/3_line.gif"), 0);
        mediaTracker.addImage(this.\u027a = this.getImage(this.getCodeBase(), "graphics/4_black.gif"), 0);
        mediaTracker.addImage(this.\u027b = this.getImage(this.getCodeBase(), "graphics/4_blue.gif"), 0);
        mediaTracker.addImage(this.\u027c = this.getImage(this.getCodeBase(), "graphics/4_line.gif"), 0);
        mediaTracker.addImage(this.\u027d = this.getImage(this.getCodeBase(), "graphics/basic.gif"), 0);
        mediaTracker.addImage(this.\u027e = this.getImage(this.getCodeBase(), "graphics/advanced.gif"), 0);
        mediaTracker.addImage(this.\u0286 = this.getImage(this.getCodeBase(), "graphics/button_addme.gif"), 0);
        mediaTracker.addImage(this.\u0287 = this.getImage(this.getCodeBase(), "graphics/button_createroom.gif"), 0);
        mediaTracker.addImage(this.\u0291 = this.getImage(this.getCodeBase(), "graphics/setgame.gif"), 0);
        mediaTracker.addImage(this.\u0292 = this.getImage(this.getCodeBase(), "graphics/setlogo.gif"), 0);
        mediaTracker.addImage(this.\u02a8 = this.getImage(this.getCodeBase(), "graphics/footer.gif"), 0);
        mediaTracker.addImage(this.\u02b3 = this.getImage(this.getCodeBase(), "graphics/btn_ok_0.gif"), 0);
        mediaTracker.addImage(this.\u02b4 = this.getImage(this.getCodeBase(), "graphics/btn_ok_2.gif"), 0);
        mediaTracker.addImage(this.\u02b5 = this.getImage(this.getCodeBase(), "graphics/btn_ok_1.gif"), 0);
        mediaTracker.addImage(this.\u02b6 = this.getImage(this.getCodeBase(), "graphics/btn_cancel_0.gif"), 0);
        mediaTracker.addImage(this.\u02b7 = this.getImage(this.getCodeBase(), "graphics/btn_cancel_2.gif"), 0);
        mediaTracker.addImage(this.\u02b8 = this.getImage(this.getCodeBase(), "graphics/btn_cancel_1.gif"), 0);
        mediaTracker.addImage(this.\u02b0 = this.getImage(this.getCodeBase(), "graphics/mnu_help_0.gif"), 0);
        mediaTracker.addImage(this.\u02b1 = this.getImage(this.getCodeBase(), "graphics/mnu_help_2.gif"), 0);
        mediaTracker.addImage(this.\u02b2 = this.getImage(this.getCodeBase(), "graphics/mnu_help_1.gif"), 0);
        mediaTracker.addImage(this.\u020dB = this.getImage(this.getCodeBase(), "graphics/header.gif"), 1);
        mediaTracker.addImage(this.\u020eB = this.getImage(this.getCodeBase(), "graphics/next_black.gif"), 1);
        mediaTracker.addImage(this.\u020fB = this.getImage(this.getCodeBase(), "graphics/next_line.gif"), 1);
        mediaTracker.addImage(this.\u0282 = this.getImage(this.getCodeBase(), "graphics/button_create.gif"), 1);
        mediaTracker.addImage(this.\u0283 = this.getImage(this.getCodeBase(), "graphics/button_cancel.gif"), 1);
        mediaTracker.addImage(this.\u0284 = this.getImage(this.getCodeBase(), "graphics/button_send.gif"), 1);
        mediaTracker.addImage(this.\u0285 = this.getImage(this.getCodeBase(), "graphics/button_exit.gif"), 1);
        try {
            mediaTracker.waitForAll(0L);
        }
        catch (Exception ex) {
            System.out.println("Error while waiting for images = [" + ex.toString() + "]");
        }
    }
    
    public boolean openSudoku(final String s, final String s2, final String s3) {
        final String encode = URLEncoder.encode(this.getUUID(s, s2));
        final String doGet = this.doGet("pm=load&type=saved&uuid=" + URLEncoder.encode(encode) + "&puzzlename=" + s3);
        if (!doGet.startsWith("<OK>")) {
            return false;
        }
        final String doGet2 = this.doGet("pm=load&type=other&uuid=" + URLEncoder.encode(encode) + "&puzzlename=" + s3);
        if (!this.\u03c5.setPuzzle(doGet2)) {
            return false;
        }
        final int tagValueInt = this.getTagValueInt(doGet2, "<LEVEL>");
        if (this.\u02a6[tagValueInt] != null) {
            this.\u0674.setImage(this.\u02a6[tagValueInt]);
        }
        this.\u03c5.setCurrentSituation(doGet);
        return true;
    }
    
    public String getTagValue(final String s, final String s2) {
        final String lowerCase = s.toLowerCase();
        final String lowerCase2 = s2.toLowerCase();
        final int n = lowerCase.indexOf(lowerCase2, 0) + lowerCase2.length();
        final int index = lowerCase.indexOf("</" + lowerCase2.substring(1, lowerCase2.length()), 0);
        if (n >= 0 & index > 0) {
            return s.substring(n, index);
        }
        return "";
    }
    
    public String removeTagValue(final String s, final String s2) {
        final String lowerCase = s.toLowerCase();
        final String lowerCase2 = s2.toLowerCase();
        final int index = lowerCase.indexOf(lowerCase2, 0);
        final int index2 = lowerCase.indexOf("</" + lowerCase2.substring(1, lowerCase2.length()), 0);
        if (index >= 0 & index2 > 0) {
            return s.substring(0, index).concat(s.substring(index2 + s2.length() + 1, s.length()));
        }
        return s;
    }
    
    public void processSavedSudoku(String removeTagValue) {
        for (int i = 0; i < 5; ++i) {
            this.\u02a5[i] = "";
        }
        this.\u0454.clear();
        int n = 0;
        while (removeTagValue.length() > 0) {
            final String tagValue = this.getTagValue(removeTagValue, "<SUDOKU>");
            final String tagValue2 = this.getTagValue(tagValue, "<NAME>");
            final String tagValue3 = this.getTagValue(tagValue, "<LABEL>");
            this.\u02a5[n] = tagValue2;
            this.\u0454.add(tagValue3);
            ++n;
            removeTagValue = this.removeTagValue(removeTagValue, "<SUDOKU>");
        }
        this.\u0454.select(0);
    }
    
    public int getTagValueInt(final String s, final String s2) {
        int int1;
        try {
            final String lowerCase = s.toLowerCase();
            final String lowerCase2 = s2.toLowerCase();
            final int n = lowerCase.indexOf(lowerCase2, 0) + lowerCase2.length();
            final int index = lowerCase.indexOf("</" + lowerCase2.substring(1, lowerCase2.length()), 0);
            if (n >= 0 & index > 0) {
                int1 = Integer.parseInt(s.substring(n, index));
            }
            else {
                int1 = 0;
            }
        }
        catch (NumberFormatException ex) {
            int1 = 0;
        }
        return int1;
    }
    
    public String getUUID(String encode, String encode2) {
        encode = URLEncoder.encode(encode);
        encode2 = URLEncoder.encode(encode2);
        return this.doGet("pm=uuid&username=" + encode + "&password=" + encode2);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public SetTogether() {
        this.\u028d = 6;
        this.\u028f = 1;
        this.\u0251 = new Image[82];
        this.\u01b2 = false;
        this.\u02a4 = false;
        this.\u01cc = this.\u00c9;
        this.\u01e7 = Color.white;
        this.\u01e8 = "Main Panel";
        this.\u02c0 = this.\u00c9;
        this.\u02c1 = Color.white;
        this.\u02d0 = "Header Panel";
        this.\u02e3 = 308;
        this.\u02e4 = this.\u00c9;
        this.\u0388 = Color.white;
        this.\u0389 = "Center Panel";
        this.\u038a = 70;
        this.\u038e = 475;
        this.\u038f = 368;
        this.\u0390 = this.\u00c9;
        this.\u0391 = Color.black;
        this.\u0392 = "login Panel";
        this.\u0394 = 2;
        this.\u0395 = 503;
        this.\u0396 = 595;
        this.\u0397 = 11;
        this.\u0398 = this.\u00c9;
        this.\u0399 = Color.white;
        this.\u039a = "Footer Panel";
        this.\u039c = 4;
        this.\u039d = 272;
        this.\u039e = 397;
        this.\u039f = 25;
        this.\u03a0 = this.\u00c9;
        this.\u03a1 = Color.white;
        this.\u03a3 = "Button Panel";
        this.\u03a5 = this.\u039e - 114;
        this.\u03a7 = 114;
        this.\u03a8 = 27;
        this.\u03a9 = this.\u00c9;
        this.\u03aa = Color.white;
        this.\u03ab = "setLogo Panel";
        this.\u03ae = 4;
        this.\u03af = 185;
        this.\u03b0 = 17;
        this.\u03b1 = this.\u00c9;
        this.\u03b2 = Color.white;
        this.\u03b3 = "buttonBasic Panel";
        this.\u03b5 = 185;
        this.\u03b6 = 4;
        this.\u03b7 = 121;
        this.\u03b8 = 17;
        this.\u03b9 = this.\u00c9;
        this.\u03ba = Color.white;
        this.\u03bb = "buttonAdvanced Panel";
        this.\u03bd = 2;
        this.\u03be = 2;
        this.\u03bf = 75;
        this.\u03c0 = 25;
        this.\u03c1 = "Daily Puzzles";
        this.\u03c2 = new Font("Arial", 1, 11);
        this.\u03c3 = new Color(102, 102, 102);
        this.\u03c4 = this.\u00c9;
        this.\u03cc = 0;
        this.\u03d2 = new Font("Roman", 1, 12);
        this.\u03d3 = new Font("Roman", 1, 10);
        this.\u03d4 = Color.white;
        this.\u03d5 = Color.white;
        this.\u03d6 = Color.white;
        this.\u03e3 = 12;
        this.\u03e4 = 10;
        this.\u03e5 = 249;
        this.\u03e6 = 15;
        this.\u03e7 = this.\u00c9;
        this.\u03e8 = Color.white;
        this.\u03e9 = "togetherStartlHeaderPanel";
        this.\u03eb = 17;
        this.\u03ec = 90;
        this.\u03ed = 355;
        this.\u03ee = 150;
        this.\u03ef = this.\u00c9;
        this.\u03f0 = Color.white;
        this.\u03f1 = "openPanel";
        this.\u03f2 = 2;
        this.\u03f3 = 2;
        this.\u0401 = 351;
        this.\u0402 = 20;
        this.\u0404 = Color.white;
        this.\u0406 = 2;
        this.\u0407 = 24;
        this.\u0408 = 351;
        this.\u0409 = 30;
        this.\u040a = this.\u00c9;
        this.\u040c = 80;
        this.\u040e = 56;
        this.\u040f = 100;
        this.\u0410 = 20;
        this.\u0411 = this.\u00c9;
        this.\u0413 = 80;
        this.\u0414 = 80;
        this.\u0415 = 80;
        this.\u0416 = 20;
        this.\u0417 = this.\u00c9;
        this.\u0418 = 190;
        this.\u0419 = 56;
        this.\u041a = 80;
        this.\u041b = 20;
        this.\u041c = Color.white;
        this.\u041d = Color.black;
        this.\u041e = Color.red;
        this.\u041f = 190;
        this.\u0420 = 80;
        this.\u0421 = 80;
        this.\u0422 = 20;
        this.\u0423 = Color.white;
        this.\u0424 = Color.black;
        this.\u0425 = 80;
        this.\u0426 = 110;
        this.\u0427 = 67;
        this.\u0428 = 20;
        this.\u0429 = new Color(204, 255, 204);
        this.\u042a = new Color(0, 0, 102);
        this.\u042b = "open";
        this.\u042d = 215;
        this.\u042e = 129;
        this.\u042f = 44;
        this.\u0430 = 19;
        this.\u0431 = "open";
        this.\u0433 = 511;
        this.\u0434 = 129;
        this.\u0435 = 69;
        this.\u0436 = 19;
        this.\u0437 = "open";
        this.\u0438 = 205;
        this.\u0439 = 110;
        this.\u043a = 67;
        this.\u043b = 20;
        this.\u043c = new Color(204, 255, 204);
        this.\u043d = new Color(0, 0, 102);
        this.\u043e = "Cancel";
        this.\u0440 = 17;
        this.\u0441 = 50;
        this.\u0442 = 355;
        this.\u0443 = 290;
        this.\u0444 = this.\u00c9;
        this.\u0445 = Color.white;
        this.\u0446 = "openSudokuPanel";
        this.\u0447 = 2;
        this.\u0448 = 2;
        this.\u0449 = 351;
        this.\u044a = 20;
        this.\u044c = Color.white;
        this.\u044e = 2;
        this.\u044f = 24;
        this.\u0451 = 351;
        this.\u0452 = 20;
        this.\u0453 = this.\u00c9;
        this.\u0455 = 100;
        this.\u0456 = 50;
        this.\u0457 = 150;
        this.\u0458 = 180;
        this.\u0459 = this.\u00c9;
        this.\u045a = Color.black;
        this.\u045b = 80;
        this.\u045c = 240;
        this.\u045e = 67;
        this.\u045f = 20;
        this.\u0460 = new Color(204, 255, 204);
        this.\u0461 = new Color(0, 0, 102);
        this.\u0462 = "openSudoku";
        this.\u0463 = 205;
        this.\u0464 = 240;
        this.\u0465 = 67;
        this.\u0466 = 20;
        this.\u0467 = new Color(204, 255, 204);
        this.\u0468 = new Color(0, 0, 102);
        this.\u0469 = "Cancel";
        this.\u046b = new Color(0, 66, 118);
        this.\u046d = 17;
        this.\u046e = 90;
        this.\u046f = 355;
        this.\u0470 = 190;
        this.\u0471 = this.\u00c9;
        this.\u0472 = Color.white;
        this.\u0473 = "savePanel";
        this.\u0474 = 2;
        this.\u0475 = 2;
        this.\u0476 = 351;
        this.\u0477 = 20;
        this.\u0479 = Color.white;
        this.\u047b = 2;
        this.\u047c = 24;
        this.\u047d = 351;
        this.\u047e = 70;
        this.\u047f = this.\u00c9;
        this.\u0481 = 80;
        this.\u0490 = 96;
        this.\u0491 = 100;
        this.\u0492 = 20;
        this.\u0493 = this.\u00c9;
        this.\u0495 = 80;
        this.\u0496 = 120;
        this.\u0497 = 80;
        this.\u0498 = 20;
        this.\u0499 = this.\u00c9;
        this.\u049a = 190;
        this.\u049b = 96;
        this.\u049c = 80;
        this.\u049d = 20;
        this.\u049e = Color.white;
        this.\u049f = Color.black;
        this.\u04a0 = 190;
        this.\u04a1 = 120;
        this.\u04a2 = 80;
        this.\u04a3 = 20;
        this.\u04a4 = Color.white;
        this.\u04a5 = Color.black;
        this.\u04a6 = 80;
        this.\u04a7 = 150;
        this.\u04a8 = 67;
        this.\u04a9 = 20;
        this.\u04aa = new Color(204, 255, 204);
        this.\u04ab = new Color(0, 0, 102);
        this.\u04ac = "Save";
        this.\u04ad = 205;
        this.\u04ae = 150;
        this.\u04af = 67;
        this.\u04b0 = 20;
        this.\u04b1 = new Color(204, 255, 204);
        this.\u04b2 = new Color(0, 0, 102);
        this.\u04b3 = "Cancel";
        this.\u01ea = 62;
        this.\u01eb = 140;
        this.\u01ec = 255;
        this.\u01ed = 46;
        this.\u04b4 = this.\u00c9;
        this.\u01f1 = Color.white;
        this.\u01f2 = "messagePanel";
        this.\u04b6 = 2;
        this.\u04b7 = 2;
        this.\u04b8 = 251;
        this.\u04b9 = 20;
        this.\u04ba = "SAVING SUDOKU";
        this.\u04bb = Color.white;
        this.\u04bd = 100;
        this.\u04be = 24;
        this.\u04bf = 67;
        this.\u04c0 = 20;
        this.\u04c1 = new Color(204, 255, 204);
        this.\u04c2 = new Color(0, 0, 102);
        this.\u04c3 = "OK";
        this.\u04c4 = 125;
        this.\u04c7 = 30;
        this.\u04c8 = 355;
        this.\u04cb = 300;
        this.\u04cc = this.\u00c9;
        this.\u04d0 = Color.white;
        this.\u04d1 = "helpPanel";
        this.\u04d3 = 2;
        this.\u04d4 = 2;
        this.\u04d5 = 351;
        this.\u04d6 = 20;
        this.\u04d8 = Color.white;
        this.\u04da = 2;
        this.\u04db = 15;
        this.\u04dc = 351;
        this.\u04dd = 255;
        this.\u04de = this.\u00c9;
        this.\u04e0 = 140;
        this.\u04e1 = 275;
        this.\u04e2 = 67;
        this.\u04e3 = 20;
        this.\u04e4 = new Color(204, 255, 204);
        this.\u04e5 = new Color(0, 0, 102);
        this.\u04e6 = "help";
        this.\u04e7 = 17;
        this.\u04e8 = 70;
        this.\u04e9 = 355;
        this.\u04ea = 250;
        this.\u04eb = this.\u00c9;
        this.\u04ee = Color.white;
        this.\u04ef = "printPanel";
        this.\u04f0 = 2;
        this.\u04f1 = 2;
        this.\u04f2 = 351;
        this.\u04f3 = 20;
        this.\u04f5 = Color.white;
        this.\u04f9 = 2;
        this.\u0531 = 24;
        this.\u0532 = 351;
        this.\u0533 = 20;
        this.\u0534 = this.\u00c9;
        this.\u0537 = 130;
        this.\u0538 = 42;
        this.\u0539 = 120;
        this.\u053a = 20;
        this.\u053b = this.\u00c9;
        this.\u053c = this.\u00c9;
        this.\u053d = this.\u00c9;
        this.\u0540 = 130;
        this.\u0541 = 62;
        this.\u0542 = 120;
        this.\u0543 = 20;
        this.\u0544 = this.\u00c9;
        this.\u0546 = 130;
        this.\u0547 = 82;
        this.\u0548 = 120;
        this.\u0549 = 20;
        this.\u054a = this.\u00c9;
        this.\u054b = 80;
        this.\u054c = 200;
        this.\u054d = 67;
        this.\u054e = 20;
        this.\u054f = new Color(204, 255, 204);
        this.\u0550 = new Color(0, 0, 102);
        this.\u0551 = "print";
        this.\u0552 = 205;
        this.\u0553 = 200;
        this.\u0554 = 67;
        this.\u0555 = 20;
        this.\u0556 = new Color(204, 255, 204);
        this.\u0561 = new Color(0, 0, 102);
        this.\u0562 = "Cancel";
        this.\u0563 = this.\u00c9;
        this.\u0564 = Color.white;
        this.\u0565 = "saveHeaderPanelLabel";
        this.\u0566 = 2;
        this.\u0568 = 596;
        this.\u0569 = 306;
        this.\u056a = this.\u00c9;
        this.\u056b = Color.white;
        this.\u056c = "Board Panel";
        this.\u056d = 2;
        this.\u056e = 310;
        this.\u056f = 598;
        this.\u0570 = 190;
        this.\u0571 = new Color(240, 244, 245);
        this.\u0572 = Color.white;
        this.\u0573 = "together Panel";
        this.\u0574 = 390;
        this.\u0575 = 160;
        this.\u0576 = 67;
        this.\u0577 = 20;
        this.\u0578 = new Color(204, 255, 204);
        this.\u0579 = new Color(0, 0, 102);
        this.\u057a = "Undo";
        this.\u057b = 390;
        this.\u057c = 292;
        this.\u057d = 67;
        this.\u057e = 20;
        this.\u057f = new Color(204, 255, 204);
        this.\u0580 = new Color(0, 0, 102);
        this.\u0581 = "Hint";
        this.\u0582 = 390;
        this.\u0583 = 182;
        this.\u0584 = 67;
        this.\u0585 = 20;
        this.\u0586 = new Color(204, 255, 204);
        this.\u0587 = new Color(0, 0, 102);
        this.\u05d0 = "pencil";
        this.\u05d1 = 390;
        this.\u05d2 = 314;
        this.\u05d3 = 67;
        this.\u05d4 = 20;
        this.\u05d5 = new Color(204, 255, 204);
        this.\u05d6 = new Color(0, 0, 102);
        this.\u05d7 = "Reveal";
        this.\u05d8 = 390;
        this.\u05d9 = 204;
        this.\u05da = 67;
        this.\u05db = 20;
        this.\u05dc = new Color(204, 255, 204);
        this.\u05dd = new Color(0, 0, 102);
        this.\u05de = "Check";
        this.\u05df = 390;
        this.\u05e0 = 50;
        this.\u05e1 = 67;
        this.\u05e2 = 17;
        this.\u05e3 = this.\u00c9;
        this.\u05e4 = new Color(0, 0, 102);
        this.\u05e5 = "00:00";
        this.\u05e6 = 390;
        this.\u05e7 = 70;
        this.\u05e8 = 67;
        this.\u05e9 = 25;
        this.\u05ea = this.\u00c9;
        this.\u05f0 = Color.white;
        this.\u05f1 = "00:00";
        this.\u05f2 = 390;
        this.\u0621 = 97;
        this.\u0622 = 67;
        this.\u0623 = 20;
        this.\u0624 = new Color(204, 255, 204);
        this.\u0625 = new Color(0, 0, 102);
        this.\u0626 = "Clock";
        this.\u0627 = 20;
        this.\u0628 = 4;
        this.\u0629 = 48;
        this.\u062a = 19;
        this.\u062b = new Color(204, 255, 204);
        this.\u062c = new Color(0, 0, 102);
        this.\u062d = 68;
        this.\u062e = 4;
        this.\u062f = 45;
        this.\u0630 = 19;
        this.\u0631 = new Color(204, 255, 204);
        this.\u0632 = new Color(0, 0, 102);
        this.\u0633 = "Save";
        this.\u0634 = 67;
        this.\u0635 = 4;
        this.\u0636 = 70;
        this.\u0637 = 19;
        this.\u0638 = new Color(204, 255, 204);
        this.\u0639 = new Color(0, 0, 102);
        this.\u063a = "Print";
        this.\u0640 = 390;
        this.\u0641 = 270;
        this.\u0642 = 67;
        this.\u0643 = 20;
        this.\u0644 = new Color(204, 255, 204);
        this.\u0645 = new Color(0, 0, 102);
        this.\u0646 = "1..9";
        this.\u0647 = 100;
        this.\u0648 = 4;
        this.\u0649 = 45;
        this.\u064a = 19;
        this.\u0671 = new Color(204, 255, 204);
        this.\u0672 = new Color(0, 0, 102);
        this.\u0673 = "Help";
        this.\u0675 = 358;
        this.\u0676 = 3;
        this.\u0677 = 110;
        this.\u0678 = 19;
        this.\u0679 = this.\u00c9;
        this.\u067a = Color.white;
        this.\u067b = "levelPanel";
        this.\u067c = "";
        this.\u067d = this.\u04e9 - 2;
        this.\u067e = 20;
        this.\u0683 = new Color(204, 255, 255);
        this.\u0684 = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        this.\u0685 = "";
        this.\u0686 = "Letter";
        this.\u0687 = "Word";
        this.\u0688 = "All";
        this.\u0689 = " has entered the room \n";
        this.\u068a = "Please fill in both NAME and CHAMBER fields";
        this.\u068b = "Trying to reconnect";
        this.\u068c = "Attempt ";
        this.\u068d = " failed.";
        this.\u068e = "Reconnection succesfull.";
        this.\u068f = "Reconnection failed.";
        this.\u0690 = "COMMUNICATION ERROR! [code:";
        this.\u0691 = "]";
        this.\u0692 = "The room doesn't exist anymore";
        this.\u0693 = "An other client with the same name is in the room now.";
        this.\u0694 = "click [ok] to try again";
        this.\u0695 = "click [ok] to continue";
        this.\u0696 = "check connection and click [ok] to continue.";
        this.\u0697 = "click [ok] to re-enter the room.";
        this.\u0698 = "click [ok] to re-enter the room with another name.";
        this.\u0699 = "Only with yesterday's puzzle";
        this.\u069a = "Only with today's puzzle";
        this.\u069b = "Save";
        this.\u069c = "Open";
        this.\u069d = "Print";
        this.\u069e = "Check";
        this.\u069f = "Help";
        this.\u06a0 = "Submit";
        this.\u06a1 = "Reveal";
        this.\u06a2 = "Across";
        this.\u06a3 = "Down";
        this.\u06a4 = "OK";
        this.\u06a5 = "Login";
        this.\u06a6 = "Cancel";
        this.\u06a7 = "Back to puzzle";
        this.\u06a8 = "Create a name for";
        this.\u06a9 = "a room and enter it";
        this.\u06aa = "into the box below.";
        this.\u06ab = "Open a previously saved puzzle";
        this.\u06ac = "Fill in the loginname and password you used";
        this.\u06ad = "when saving the puzzle and click 'login'.";
        this.\u06ae = "You have previously saved the puzzles";
        this.\u06af = "shown here. Select the puzzle of";
        this.\u06b0 = "your choice and click 'OK'.";
        this.\u06b1 = "Save your puzzle";
        this.\u06b2 = "To save your present work and";
        this.\u06b3 = "continue later. This will overwrite";
        this.\u06b4 = "a previously saved version.";
        this.\u06b5 = "";
        this.\u06b6 = "Overwrite existing puzzle?";
        this.\u06b7 = "Save new puzzle?";
        this.\u06ba = "No internet connection.";
        this.\u06bb = "Please, fill in all fields.";
        this.\u06bc = "Puzzle has been saved.";
        this.\u06bd = "No puzzle has been saved.";
        this.\u06be = "No internet connection.";
        this.\u06c0 = "Please, fill in all fields.";
        this.\u06c1 = "No such puzzles exists.";
        this.\u06c2 = "No internet connection.";
        this.\u06c3 = "No puzzle selected. Please, select one.";
        this.\u06c4 = "You can't change puzzeltype whilst in room.";
        this.\u06c5 = "Enter room";
        this.\u06c6 = "roomname";
        this.\u06c7 = " Welcome, ";
        this.\u06c8 = ". You are";
        this.\u06c9 = "in room ";
        this.\u06ca = "for puzzling together";
        this.\u06cb = "You are in room :";
        this.\u06cc = "Logging in failed. Click OK";
        this.\u06cd = "Another player is already in the room with the same name.";
        this.\u06ce = "Click OK and try to log in with another name.";
        this.\u06d0 = "You are not in the room anymore.";
        this.\u06d1 = "There probably were connection problems.";
        this.\u06d2 = "Click OK to reenter the room";
        this.\u06d3 = "Another puzzle is being loaded.";
        this.\u06e5 = "One moment, please.";
        this.\u06e6 = "Loginname:";
        this.\u00c0B = "BACK";
        this.\u00c1B = "Choose your print-option";
        this.\u00c2B = "and click OK";
        this.\u00c3B = "Print an empty diagram";
        this.\u00c4B = "Print the diagram filled in by you";
        this.\u00c5B = "Print the diagram with the complete solution";
        this.\u00c6B = "You haven't filled in all squares.";
        this.\u00c7B = "Submitted puzzle is incorrect.";
        this.\u00c8B = "Please check your puzzle and try again.";
        this.\u00c9B = " Welcome, ";
        this.\u00caB = "\n\nUp to five members can now solve today's crossword collaboratively in real-time.\n\nHow to play?\n1. Create a name for a room and enter it into the box below. (ex. nytimes)\n2. Coordinate with another member to also enter the room name. (ex. nytimes)\n\nNote:\nEach member will have their own color for the letters that they enter.\nIf a member exits the room, their letters will turn black.";
        this.\u00cbB = "Congratulations!";
        this.\u00ccB = "You solved the puzzle correctly.";
        this.\u00cdB = "Sorry.";
        this.\u00ceB = "The puzzle is incorrect.";
        this.\u00d0B = "<NOT DEFINED>";
        this.\u00d3B = false;
        this.\u00d4B = false;
        this.\u00d5B = false;
        this.\u013e = "";
        this.\u00daB = 23;
        this.\u00dbB = 23;
        this.\u00dcB = 15;
        this.\u00ddB = 15;
        this.\u00deB = 15;
        this.\u00dfB = 15;
        this.\u00e0B = this.\u00deB * this.\u00daB;
        this.\u00e1B = this.\u00dfB * this.\u00dbB;
        this.\u00e2B = this.\u00deB * this.\u00daB;
        this.\u00e3B = this.\u00dfB * this.\u00dbB;
        this.\u00e4B = new int[this.\u00ddB][this.\u00dcB];
        this.\u00e5B = new int[this.\u00ddB][this.\u00dcB];
        this.\u00e7B = false;
        this.\u00e8B = false;
        this.\u00e9B = true;
        this.\u00eaB = false;
        this.\u00ebB = false;
        this.\u00ecB = false;
        this.\u00edB = true;
        this.\u00eeB = true;
        this.\u00f5B = false;
        this.\u00f6B = new String[14];
        this.\u00fdB = "";
        this.\u00feB = true;
        this.\u00ffB = 2 + this.\u00e0B + 5;
        this.\u0100B = 40;
        this.\u0101B = 225;
        this.\u0102B = this.\u00e1B;
        this.\u0103B = 9;
        this.\u0104B = 9;
        this.\u0105B = 9;
        this.\u0106B = 16;
        this.\u0107B = 225;
        this.\u0108B = 225;
        this.\u010aB = this.\u00ffB + this.\u0109B + 5;
        this.\u010bB = this.\u0100B + 12;
        this.\u010cB = this.\u010aB;
        this.\u010dB = this.\u010bB + this.\u0102B / 2;
        this.\u010eB = 20;
        this.\u010fB = 39;
        this.\u011eB = 0;
        this.\u0123B = new Font("roman", 0, 10);
        this.\u0133B = this.\u00c9;
        this.\u0134B = new Color(0, 0, 52);
        this.\u0135B = new Color(102, 102, 153);
        this.\u0136B = new Color(204, 204, 255);
        this.\u0137B = 600;
        this.\u0138B = 5;
        this.\u0139B = 20;
        this.\u0154B = new Font("helvetica", 0, 11);
        this.\u0157B = new ImagePanel();
        this.\u0158B = new ImagePanel();
        this.\u0159B = new ImagePanel();
        this.\u015aB = new ImagePanel();
        this.\u015bB = new ImagePanel();
        this.\u015cB = new ImagePanel();
        this.\u015dB = new ImagePanel();
        this.\u015eB = new Label();
        this.\u015fB = new Label();
        this.\u0160B = new Label();
        this.\u0161B = new Label();
        this.\u0162B = new TextField();
        this.\u0163B = new Label();
        this.\u0169B = new TextField();
        this.\u016aB = new TextArea("", 0, 0, 1);
        this.\u016bB = new Label();
        this.\u016cB = new Label();
        this.\u016dB = new Label();
        this.\u016eB = new Label();
        this.\u016fB = new Label();
        this.\u0170B = new Label();
        this.\u0171B = new Label();
        this.\u0172B = new Label();
        this.\u0173B = new Label();
        this.\u0174B = new Label();
        this.\u0175B = true;
        this.\u0178B = "";
        this.\u0179B = "anoniem";
        this.\u017aB = "";
        this.\u017bB = new Font("helvetica", 0, 9);
        this.\u017cB = new Font("Helvetica", 1, 14);
        this.\u017dB = new Font("Helvetica", 1, 10);
        this.\u017eB = new Font("Verdana", 1, 12);
        this.\u017fB = new Font("Verdana", 0, 10);
        this.\u0180B = new Font("Verdana", 0, 12);
        this.\u0181B = new Font("Verdana", 1, 12);
        this.\u0182B = new Font("Verdana", 0, 12);
        this.\u0183B = new Font("roman", 0, 11);
        this.\u0184B = new Font("Verdana", 0, 11);
        this.\u0185B = new Font("Verdana", 1, 12);
        this.\u0186B = new Font("Verdana", 0, 12);
        this.\u0187B = new Font("roman", 0, 10);
        this.\u0188B = new Font("Verdana", 0, 11);
        this.\u0189B = new Font("Verdana", 1, 11);
        this.\u018aB = new Font("Verdana", 0, 11);
        this.\u018bB = new Font("Verdana", 0, 11);
        this.\u018cB = new Font("Verdana", 1, 11);
        this.\u018dB = new Font("Verdana", 0, 11);
        this.\u018eB = new Font("Verdana", 1, 11);
        this.\u018fB = new Font("Verdana", 0, 11);
        this.\u0190B = new Font("roman", 1, 11);
        this.\u0191B = 17;
        this.\u0192B = 0;
        this.\u0195B = 1;
        this.\u019eB = true;
        this.\u019fB = false;
        this.\u01a0B = false;
        this.\u01a1B = true;
        this.\u01a2B = 10;
        this.\u01a3B = this.\u00e2B;
        this.\u01a4B = this.\u00e3B - 2 * this.\u00dbB;
        this.\u01a5B = 1;
        this.\u01a6B = 12;
        this.\u01a7B = true;
        this.\u01a8B = false;
        this.\u01a9B = false;
        this.\u01aaB = false;
        this.\u01abB = false;
        this.\u01adB = 255;
        this.\u01aeB = 209;
        this.\u01afB = 129;
        this.\u01b1B = 143;
        this.\u01b2B = 224;
        this.\u01b3B = 204;
        this.\u01b4B = 204;
        this.\u01b5B = 204;
        this.\u01b6B = 205;
        this.\u01b7B = 255;
        this.\u01b8B = 255;
        this.\u01b9B = new Color(this.\u01b0B, this.\u01b1B, this.\u01b2B);
        this.\u01baB = new Color(this.\u01b3B, this.\u01b4B, this.\u01b5B);
        this.\u01bbB = 255;
        this.\u01bcB = 255;
        this.\u01bdB = 255;
        this.\u01d0B = 50;
        this.\u01d1B = 50;
        this.\u01d2B = 10;
        this.\u01d3B = 40;
        this.\u01d4B = 400;
        this.\u01d5B = 20;
        this.\u01d6B = 40;
        this.\u01d7B = 80;
        this.\u01d8B = 170;
        this.\u01d9B = 200;
        this.\u01daB = 200;
        this.\u01dbB = 290;
        this.\u01dcB = 20;
        this.\u01ddB = this.\u01d5B;
        this.\u01deB = this.\u01d6B;
        this.\u01dfB = this.\u01d7B;
        this.\u01e0B = this.\u01d8B;
        this.\u01e1B = this.\u01d9B;
        this.\u01e2B = this.\u01daB;
        this.\u01e3B = this.\u01dbB;
        this.\u01e4B = this.\u01dcB;
        this.\u01e5B = 40;
        this.\u01e8B = new Color(173, 191, 219);
        this.\u01e9B = new Color(238, 156, 0);
        this.\u01eaB = new Color(100, 205, 0);
        this.\u01ebB = new Color(0, 150, 200);
        this.\u01ecB = new Font("Verdana", 1, 11);
        this.\u01edB = new Font("Helvetica", 1, 11);
        this.\u01f2B = 5;
        this.\u01f3B = 60;
        this.\u01f4B = 19;
        this.\u01f5B = this.\u01f2B;
        this.\u01faB = this.\u01f3B + 2 * this.\u01f2B;
        this.\u01fbB = 160;
        this.\u0204B = new Color(0, 200, 0);
        this.\u0205B = new Color(200, 0, 0);
        this.\u020aB = new Font("helvetica", 0, 10);
        this.\u0212B = false;
        this.\u0213B = false;
        this.\u0216B = 20;
        this.\u0271B = "You are already in the waiting list";
        this.\u0272B = "Click 'OK' to continue.";
        this.\u0273B = "";
        this.\u0274B = "You can't select yourself, please select another member.";
        this.\u0275B = "Click 'OK' to continue.";
        this.\u0276B = "";
        this.\u0277B = "The person you selected is";
        this.\u0278B = "no longer waiting. Click";
        this.\u0279B = "'OK' to continue.";
        this.\u027aB = new Font("Arial", 0, 11);
        this.\u027bB = "Select a name to start solving together or let others";
        this.\u027cB = "choose you by adding your name to this list.";
        this.\u027dB = "";
        this.\u027eB = "Click on another name to automatically create";
        this.\u027fB = "a room and solve the puzzle with them.";
        this.\u0280B = "";
        this.\u0281B = "Select a room to join or create your own room.";
        this.\u0282B = "";
        this.\u0283B = "To create your own room, please enter the following.";
        this.\u0284B = "";
        this.\u0285B = "Please enter the name of your";
        this.\u0286B = "room.";
        this.\u0287B = "Click 'OK' to continue.";
        this.\u0288B = "this roomname is already";
        this.\u0289B = "in use";
        this.\u028aB = "Click 'OK' to continue.";
        this.\u028bB = "please enter the password of the room.";
        this.\u028cB = "";
        this.\u028dB = "Please enter the password to get into the room";
        this.\u028eB = "incorrect password";
        this.\u028fB = "name:";
        this.\u0290B = "password:";
        this.\u0292B = false;
        this.\u0293B = -1;
        this.\u0296B = new Color(0, 153, 0);
        this.\u0297B = new Color(153, 0, 0);
        this.displayTogetherPanel = true;
    }
    
    static {
        \u01acB = new Color(255, 255, 204);
    }
}
