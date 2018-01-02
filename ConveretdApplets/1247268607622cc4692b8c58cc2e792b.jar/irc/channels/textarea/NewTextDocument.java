// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.textarea;

import java.text.ParseException;
import irc.com.User;
import javax.swing.ImageIcon;
import irc.channels.components.smileys.SmileyLoader;
import java.util.StringTokenizer;
import irc.com.messages.MircMessage;
import irc.managers.Resources;
import javax.swing.Icon;
import irc.com.nick.NickInfos;
import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.awt.Color;
import irc.EIRC;
import java.util.Hashtable;
import irc.channels.components.PopupInfos;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.MutableAttributeSet;
import java.text.MessageFormat;
import javax.swing.text.DefaultStyledDocument;

public class NewTextDocument extends DefaultStyledDocument
{
    private static final String separator;
    private static final MessageFormat ME;
    private static final MessageFormat ACTION;
    private static final MessageFormat MY_NOTICE;
    private static final MessageFormat MY_ACTION;
    private static final MessageFormat OP_PRIVMSG;
    private static final MessageFormat HOP_PRIVMSG;
    private static final MessageFormat VOICE_PRIVMSG;
    private static final MessageFormat ADMIN_PRIVMSG;
    private static final MessageFormat OWNER_PRIVMSG;
    private static final MessageFormat MALE_PRIVMSG;
    private static final MessageFormat FEMALE_PRIVMSG;
    private static final MessageFormat UNKNOWN_PRIVMSG;
    protected static final MessageFormat EIRC_NOTICE;
    protected static final MessageFormat EIRC_INFO;
    protected static final MessageFormat UNMANGLED;
    protected static final MessageFormat EIRC_ERROR;
    protected static final MessageFormat EIRC_WARNING;
    protected static final MessageFormat SERV_NOTICE;
    protected static final MessageFormat STRIP_NOTICE;
    boolean putlink;
    MutableAttributeSet newAttr;
    SimpleAttributeSet icones;
    SimpleAttributeSet links;
    public PopupInfos nickinfos;
    int lastindex;
    private static final Hashtable smileykeys;
    String returns;
    boolean souligner;
    EIRC eirc;
    private TextAreaEvent textareaevent;
    private String channeltag;
    private Color defult_color;
    
    public static String now(final String s) {
        return new SimpleDateFormat(s).format(Calendar.getInstance().getTime());
    }
    
    public NewTextDocument(final EIRC eirc, final TextAreaEvent textareaevent) {
        this.putlink = false;
        this.newAttr = new SimpleAttributeSet();
        this.icones = new SimpleAttributeSet();
        this.links = new SimpleAttributeSet();
        this.lastindex = 0;
        this.returns = "";
        this.souligner = false;
        this.textareaevent = textareaevent;
        this.eirc = eirc;
        this.defult_color = new Color(0, 0, 0, 0);
        this.nickinfos = new PopupInfos("");
        StyleConstants.setBidiLevel(this.links, 21);
        StyleConstants.setUnderline(this.links, true);
        StyleConstants.setForeground(this.links, Color.BLUE);
        StyleConstants.setFontSize(this.newAttr, EIRC.fontsize);
        StyleConstants.setFontFamily(this.newAttr, EIRC.fontfamily);
        try {
            this.insertString(0, this.returns, this.newAttr);
        }
        catch (BadLocationException ex) {}
    }
    
    public void addContent(String s, final String s2) {
        this.lastindex = this.getLength();
        StyleConstants.setFontSize(this.newAttr, EIRC.fontsize);
        StyleConstants.setFontFamily(this.newAttr, EIRC.fontfamily);
        StyleConstants.setBold(this.newAttr, false);
        if (this.getLength() >= 30000) {
            try {
                this.remove(0, 5000);
            }
            catch (BadLocationException ex) {}
        }
        if (s2 != null) {
            if (NickInfos.getSmall_avatar(s2) != null) {
                StyleConstants.setIcon(this.icones, NickInfos.getSmall_avatar35(s2));
                try {
                    this.insertString(this.getLength(), "   ", this.icones);
                    final MutableAttributeSet newAttr = this.newAttr;
                    final EIRC eirc = this.eirc;
                    StyleConstants.setFontSize(newAttr, EIRC.fontsize);
                    final MutableAttributeSet newAttr2 = this.newAttr;
                    final EIRC eirc2 = this.eirc;
                    StyleConstants.setFontFamily(newAttr2, EIRC.fontfamily);
                    final MutableAttributeSet newAttr3 = this.newAttr;
                    final EIRC eirc3 = this.eirc;
                    StyleConstants.setLineSpacing(newAttr3, EIRC.interlignes);
                    StyleConstants.setBackground(this.newAttr, this.defult_color);
                    StyleConstants.setForeground(this.newAttr, Color.black);
                    this.insertString(this.getLength(), "", this.newAttr);
                }
                catch (BadLocationException ex2) {}
            }
            else {
                this.insertvide();
            }
        }
        else {
            this.insertvide();
        }
        try {
            if (this.eirc.isShowtime() && Resources.isTimeActive()) {
                final String string = "[" + now("H:mm") + "] ";
                StyleConstants.setFontSize(this.newAttr, EIRC.fontsize);
                StyleConstants.setFontFamily(this.newAttr, EIRC.fontfamily);
                StyleConstants.setLineSpacing(this.newAttr, EIRC.interlignes);
                StyleConstants.setBackground(this.newAttr, Color.white);
                StyleConstants.setForeground(this.newAttr, Color.black);
                this.insertString(this.getLength(), string, this.newAttr);
            }
        }
        catch (BadLocationException ex3) {}
        try {
            s = this.DecodeSmiley(s);
            s = this.DecodeUrls(s);
            if (s2 != null) {
                s = this.DecodeNicks(s, s2);
            }
            this.insertText(s);
            this.insertString(this.getLength(), "\n", this.newAttr);
        }
        catch (Exception ex4) {}
        if (this.textareaevent != null) {
            this.textareaevent.TextAdded(s);
        }
    }
    
    public void appendNickInfo(final String s, final String s2) {
        this.addContent(MircMessage.attrDecode(s), s2);
    }
    
    public String DecodeNicks(String s, final String s2) {
        String s3 = s;
        if (s.indexOf(">") != -1) {
            String s4 = ('\u034a' + s3).replaceFirst(">", "\u034b>");
            if (NickInfos.isRegister(s2)) {
                s4 = s4.replaceFirst(">", "\u0349>");
            }
            s = s4;
            s3 = s4.substring(0, s4.indexOf(843) + 1);
            final StringTokenizer stringTokenizer = new StringTokenizer(s.substring(s.indexOf(843) + 1));
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                final int index;
                String s5;
                if ((index = nextToken.toLowerCase().indexOf(this.eirc.getNick().toLowerCase())) != -1) {
                    s5 = s3 + nextToken.substring(0, index) + '\u0347' + this.eirc.getNick() + '\u0348' + nextToken.substring(index + this.eirc.getNick().length());
                    this.souligner = true;
                }
                else {
                    s5 = s3 + nextToken;
                }
                s3 = s5 + " ";
            }
        }
        return s3;
    }
    
    public String DecodeSmiley(final String s) {
        String string = s;
        for (int i = 0; i < SmileyLoader.keyS.size(); ++i) {
            final String s2 = SmileyLoader.keyS.elementAt(i);
            try {
                while (string.indexOf(s2) != -1) {
                    string = string.substring(0, string.indexOf(s2)) + (char)Integer.parseInt((String)NewTextDocument.smileykeys.get(s2)) + string.substring(string.indexOf(s2) + s2.length());
                }
            }
            catch (Exception ex) {}
        }
        return string;
    }
    
    public String DecodeUrls(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        String string = "";
        while (stringTokenizer.hasMoreTokens()) {
            String s2 = stringTokenizer.nextToken();
            if (s2.indexOf("http") != -1) {
                s2 = s2.substring(0, s2.indexOf("http")) + '\u0345' + s2.substring(s2.indexOf("http")) + '\u0346';
            }
            else if (s2.indexOf("www.") != -1) {
                s2 = s2.substring(0, s2.indexOf("www.")) + '\u0345' + s2.substring(s2.indexOf("www.")) + '\u0346';
            }
            else if (s2.indexOf("#") != -1) {
                s2 = s2.substring(0, s2.indexOf("#")) + '\u0345' + s2.substring(s2.indexOf("#")) + '\u0346';
            }
            string = string + s2 + " ";
        }
        return string;
    }
    
    public String emphazeNick(String string, final String s) {
        final int index = string.toLowerCase().indexOf(s.toLowerCase());
        if (index >= 0) {
            String substring = "";
            String substring2 = "";
            char char1 = ' ';
            char char2 = ' ';
            if (index > 0) {
                substring = string.substring(0, index);
            }
            if (index + s.length() < string.length()) {
                substring2 = string.substring(index + s.length());
            }
            final String filterMircAttributes = MircMessage.filterMircAttributes(substring);
            if (filterMircAttributes.length() > 0) {
                char1 = filterMircAttributes.charAt(filterMircAttributes.length() - 1);
            }
            final String filterMircAttributes2 = MircMessage.filterMircAttributes(substring2);
            if (filterMircAttributes2.length() > 0) {
                char2 = filterMircAttributes2.charAt(0);
            }
            if (!Character.isLetterOrDigit(char1) && !Character.isLetterOrDigit(char2)) {
                string = substring + NewTextDocument.ME.format(new Object[] { s }) + substring2;
            }
        }
        return string;
    }
    
    public String getChanneltag() {
        return this.channeltag;
    }
    
    public MessageFormat getMessageFormat(final String s) {
        MessageFormat messageFormat = null;
        switch (NickInfos.getSex(s)) {
            case 1: {
                messageFormat = NewTextDocument.MALE_PRIVMSG;
                break;
            }
            case 2: {
                messageFormat = NewTextDocument.FEMALE_PRIVMSG;
                break;
            }
            default: {
                messageFormat = NewTextDocument.UNKNOWN_PRIVMSG;
                break;
            }
        }
        return messageFormat;
    }
    
    public void insertText(final String s) {
        final char[] charArray = s.toCharArray();
        StyleConstants.setFontSize(this.newAttr, EIRC.fontsize);
        StyleConstants.setFontFamily(this.newAttr, EIRC.fontfamily);
        StyleConstants.setLineSpacing(this.newAttr, EIRC.interlignes);
        StyleConstants.setBackground(this.newAttr, Color.white);
        StyleConstants.setForeground(this.newAttr, Color.black);
        StyleConstants.setUnderline(this.newAttr, this.souligner);
        for (int i = 0; i < charArray.length; ++i) {
            final char c = charArray[i];
            String string = "";
            switch (c) {
                case 3: {
                    StyleConstants.setBackground(this.newAttr, Color.white);
                    StyleConstants.setForeground(this.newAttr, Color.black);
                    if (i == charArray.length - 1) {
                        break;
                    }
                    int n;
                    for (n = i + 1; n <= n + 2 && Character.isDigit(charArray[n]); ++n) {
                        string += charArray[n];
                    }
                    if (!string.equals("")) {
                        try {
                            StyleConstants.setForeground(this.newAttr, Color.decode(Resources.getStringEirc("n" + Integer.parseInt(string))));
                        }
                        catch (Exception ex) {}
                    }
                    final int n2 = i + string.length();
                    String string2 = "";
                    if (charArray[n] == ',') {
                        while (n <= ++n + 2 && Character.isDigit(charArray[n])) {
                            string2 += charArray[n];
                            ++n;
                        }
                        if (!string2.equals("")) {
                            try {
                                StyleConstants.setBackground(this.newAttr, Color.decode(Resources.getStringEirc("n" + Integer.parseInt(string2))));
                            }
                            catch (Exception ex2) {}
                        }
                        i = n2 + (string2.length() + 1);
                        break;
                    }
                    i = n2 + string2.length();
                    break;
                }
                case 2: {
                    StyleConstants.setBold(this.newAttr, !StyleConstants.isBold(this.newAttr));
                    break;
                }
                case 15: {
                    StyleConstants.setBackground(this.newAttr, Color.white);
                    StyleConstants.setForeground(this.newAttr, Color.black);
                    break;
                }
                case 31: {
                    break;
                }
                case 20: {
                    break;
                }
                case 22: {
                    break;
                }
                case 7: {
                    break;
                }
                case 837: {
                    this.putlink = true;
                    try {
                        this.insertString(this.getLength(), " ", this.newAttr);
                    }
                    catch (BadLocationException ex3) {}
                    break;
                }
                case 838: {
                    if (Character.isLetterOrDigit(s.charAt(i - 1))) {}
                    try {
                        this.insertString(this.getLength(), " ", this.newAttr);
                    }
                    catch (BadLocationException ex4) {}
                    this.putlink = false;
                    this.getCharacterElement(this.getLength() - 1).toString();
                    StyleConstants.setBidiLevel(this.newAttr, 5);
                    break;
                }
                case 839: {
                    try {
                        this.insertString(this.getLength(), " ", this.newAttr);
                    }
                    catch (BadLocationException ex5) {}
                    StyleConstants.setBidiLevel(this.newAttr, 20);
                    StyleConstants.setBold(this.newAttr, true);
                    break;
                }
                case 842: {
                    try {
                        this.insertString(this.getLength(), " ", this.newAttr);
                    }
                    catch (BadLocationException ex6) {}
                    StyleConstants.setBidiLevel(this.newAttr, 20);
                    break;
                }
                case 840: {
                    if (Character.isLetterOrDigit(s.charAt(i - 2))) {}
                    StyleConstants.setBold(this.newAttr, false);
                    try {
                        this.insertString(this.getLength(), " ", this.newAttr);
                    }
                    catch (BadLocationException ex7) {}
                    StyleConstants.setBidiLevel(this.newAttr, 5);
                    break;
                }
                case 843: {
                    if (Character.isLetterOrDigit(s.charAt(i - 2))) {}
                    try {
                        this.insertString(this.getLength(), " ", this.newAttr);
                    }
                    catch (BadLocationException ex8) {}
                    this.getCharacterElement(this.getLength() - 2).toString();
                    StyleConstants.setBidiLevel(this.newAttr, 5);
                    break;
                }
                case 162: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.ange.image")));
                    try {
                        this.insertString(this.getLength(), "o:-)", this.icones);
                    }
                    catch (BadLocationException ex9) {}
                    break;
                }
                case 164: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.f\u00eate.image")));
                    try {
                        this.insertString(this.getLength(), ":f\u00eate:", this.icones);
                    }
                    catch (BadLocationException ex10) {}
                    break;
                }
                case 165: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.amoureux.image")));
                    try {
                        this.insertString(this.getLength(), ":amoureux:", this.icones);
                    }
                    catch (BadLocationException ex11) {}
                    break;
                }
                case 166: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.arf.image")));
                    try {
                        this.insertString(this.getLength(), ":arf:", this.icones);
                    }
                    catch (BadLocationException ex12) {}
                    break;
                }
                case 167: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.boulet.image")));
                    try {
                        this.insertString(this.getLength(), ":boulet:", this.icones);
                    }
                    catch (BadLocationException ex13) {}
                    break;
                }
                case 168: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.bete.image")));
                    try {
                        this.insertString(this.getLength(), ":bete:", this.icones);
                    }
                    catch (BadLocationException ex14) {}
                    break;
                }
                case 169: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.bisou.image")));
                    try {
                        this.insertString(this.getLength(), ":x", this.icones);
                    }
                    catch (BadLocationException ex15) {}
                    break;
                }
                case 170: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.bof.image")));
                    try {
                        this.insertString(this.getLength(), ":|", this.icones);
                    }
                    catch (BadLocationException ex16) {}
                    break;
                }
                case 172: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.bob.image")));
                    try {
                        this.insertString(this.getLength(), "(B)", this.icones);
                    }
                    catch (BadLocationException ex17) {}
                    break;
                }
                case 173: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.casse.image")));
                    try {
                        this.insertString(this.getLength(), ":kc:", this.icones);
                    }
                    catch (BadLocationException ex18) {}
                    break;
                }
                case 174: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.class.image")));
                    try {
                        this.insertString(this.getLength(), "8-)", this.icones);
                    }
                    catch (BadLocationException ex19) {}
                    break;
                }
                case 175: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.clin_doeil.image")));
                    try {
                        this.insertString(this.getLength(), ";)", this.icones);
                    }
                    catch (BadLocationException ex20) {}
                    break;
                }
                case 176: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.colere.image")));
                    try {
                        this.insertString(this.getLength(), ">#", this.icones);
                    }
                    catch (BadLocationException ex21) {}
                    break;
                }
                case 177: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.coeurs.image")));
                    try {
                        this.insertString(this.getLength(), ":coeurs:", this.icones);
                    }
                    catch (BadLocationException ex22) {}
                    break;
                }
                case 178: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.cadeau.image")));
                    try {
                        this.insertString(this.getLength(), ":cadeau:", this.icones);
                    }
                    catch (BadLocationException ex23) {}
                    break;
                }
                case 179: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.cafe.image")));
                    try {
                        this.insertString(this.getLength(), ":cafe:", this.icones);
                    }
                    catch (BadLocationException ex24) {}
                    break;
                }
                case 182: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.chut.image")));
                    try {
                        this.insertString(this.getLength(), ":chut:", this.icones);
                    }
                    catch (BadLocationException ex25) {}
                    break;
                }
                case 188: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.coeur_diable.image")));
                    try {
                        this.insertString(this.getLength(), ":dcoeur:", this.icones);
                    }
                    catch (BadLocationException ex26) {}
                    break;
                }
                case 189: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.coeur_fleche.image")));
                    try {
                        this.insertString(this.getLength(), ":love:", this.icones);
                    }
                    catch (BadLocationException ex27) {}
                    break;
                }
                case 190: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.coeur.image")));
                    try {
                        this.insertString(this.getLength(), ":coeur:", this.icones);
                    }
                    catch (BadLocationException ex28) {}
                    break;
                }
                case 222: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.happy.image")));
                    try {
                        this.insertString(this.getLength(), ":)", this.icones);
                    }
                    catch (BadLocationException ex29) {}
                    break;
                }
                case 191: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.diable.image")));
                    try {
                        this.insertString(this.getLength(), ":diable:", this.icones);
                    }
                    catch (BadLocationException ex30) {}
                    break;
                }
                case 196: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.dsl.image")));
                    try {
                        this.insertString(this.getLength(), ":dsl:", this.icones);
                    }
                    catch (BadLocationException ex31) {}
                    break;
                }
                case 197: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.dodo.image")));
                    try {
                        this.insertString(this.getLength(), ":z", this.icones);
                    }
                    catch (BadLocationException ex32) {}
                    break;
                }
                case 198: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.etoile.image")));
                    try {
                        this.insertString(this.getLength(), ":etoile:", this.icones);
                    }
                    catch (BadLocationException ex33) {}
                    break;
                }
                case 199: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.froid.image")));
                    try {
                        this.insertString(this.getLength(), ":froid:", this.icones);
                    }
                    catch (BadLocationException ex34) {}
                    break;
                }
                case 200: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.fatigue.image")));
                    try {
                        this.insertString(this.getLength(), ":fatigue:", this.icones);
                    }
                    catch (BadLocationException ex35) {}
                    break;
                }
                case 201: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.fatigue1.image")));
                    try {
                        this.insertString(this.getLength(), ":fatigue1:", this.icones);
                    }
                    catch (BadLocationException ex36) {}
                    break;
                }
                case 202: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.frimeur.image")));
                    try {
                        this.insertString(this.getLength(), ":frime:", this.icones);
                    }
                    catch (BadLocationException ex37) {}
                    break;
                }
                case 203: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.foot.image")));
                    try {
                        this.insertString(this.getLength(), ":foot:", this.icones);
                    }
                    catch (BadLocationException ex38) {}
                    break;
                }
                case 208: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.gateau.image")));
                    try {
                        this.insertString(this.getLength(), ":gateau:", this.icones);
                    }
                    catch (BadLocationException ex39) {}
                    break;
                }
                case 209: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.gr_rose.image")));
                    try {
                        this.insertString(this.getLength(), ":Rose:", this.icones);
                    }
                    catch (BadLocationException ex40) {}
                    break;
                }
                case 213: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.hoo.image")));
                    try {
                        this.insertString(this.getLength(), ":o", this.icones);
                    }
                    catch (BadLocationException ex41) {}
                    break;
                }
                case 214: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.hum.image")));
                    try {
                        this.insertString(this.getLength(), ":hum:", this.icones);
                    }
                    catch (BadLocationException ex42) {}
                    break;
                }
                case 223: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.hein.image")));
                    try {
                        this.insertString(this.getLength(), "O_o", this.icones);
                    }
                    catch (BadLocationException ex43) {}
                    break;
                }
                case 230: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.hihi.image")));
                    try {
                        this.insertString(this.getLength(), ":D", this.icones);
                    }
                    catch (BadLocationException ex44) {}
                    break;
                }
                case 235: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.kiss.image")));
                    try {
                        this.insertString(this.getLength(), "(k)", this.icones);
                    }
                    catch (BadLocationException ex45) {}
                    break;
                }
                case 236: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.langue.image")));
                    try {
                        this.insertString(this.getLength(), ":p", this.icones);
                    }
                    catch (BadLocationException ex46) {}
                    break;
                }
                case 240: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.lune.image")));
                    try {
                        this.insertString(this.getLength(), ":lune:", this.icones);
                    }
                    catch (BadLocationException ex47) {}
                    break;
                }
                case 241: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.mort.image")));
                    try {
                        this.insertString(this.getLength(), ":mort:", this.icones);
                    }
                    catch (BadLocationException ex48) {}
                    break;
                }
                case 246: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.music.image")));
                    try {
                        this.insertString(this.getLength(), ":music:", this.icones);
                    }
                    catch (BadLocationException ex49) {}
                    break;
                }
                case 248: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.miam.image")));
                    try {
                        this.insertString(this.getLength(), ":d", this.icones);
                    }
                    catch (BadLocationException ex50) {}
                    break;
                }
                case 252: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.maiseuh.image")));
                    try {
                        this.insertString(this.getLength(), ":maiseuh:", this.icones);
                    }
                    catch (BadLocationException ex51) {}
                    break;
                }
                case 253: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.oeil.image")));
                    try {
                        this.insertString(this.getLength(), ":oeil:", this.icones);
                    }
                    catch (BadLocationException ex52) {}
                    break;
                }
                case 254: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.oups.image")));
                    try {
                        this.insertString(this.getLength(), ":oups:", this.icones);
                    }
                    catch (BadLocationException ex53) {}
                    break;
                }
                case 402: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.phone.image")));
                    try {
                        this.insertString(this.getLength(), ":phone:", this.icones);
                    }
                    catch (BadLocationException ex54) {}
                    break;
                }
                case 381: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.pleure.image")));
                    try {
                        this.insertString(this.getLength(), ":'(", this.icones);
                    }
                    catch (BadLocationException ex55) {}
                    break;
                }
                case 376: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.quoi.image")));
                    try {
                        this.insertString(this.getLength(), ":quoi:", this.icones);
                    }
                    catch (BadLocationException ex56) {}
                    break;
                }
                case 841: {
                    break;
                }
                case 353: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.rire.image")));
                    try {
                        this.insertString(this.getLength(), "XD", this.icones);
                    }
                    catch (BadLocationException ex57) {}
                    break;
                }
                case 338: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.rougie_fille.image")));
                    try {
                        this.insertString(this.getLength(), "::$", this.icones);
                    }
                    catch (BadLocationException ex58) {}
                    break;
                }
                case 339: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.rougit.image")));
                    try {
                        this.insertString(this.getLength(), ":$", this.icones);
                    }
                    catch (BadLocationException ex59) {}
                    break;
                }
                case 255: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.rose.image")));
                    try {
                        this.insertString(this.getLength(), ":rose:", this.icones);
                    }
                    catch (BadLocationException ex60) {}
                    break;
                }
                case 204: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.register.image")));
                    try {
                        this.insertString(this.getLength(), "(x)", this.icones);
                    }
                    catch (BadLocationException ex61) {}
                    break;
                }
                case 161: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.sex.image")));
                    try {
                        this.insertString(this.getLength(), ":sexe:", this.icones);
                    }
                    catch (BadLocationException ex62) {}
                    break;
                }
                case 180: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.shit.image")));
                    try {
                        this.insertString(this.getLength(), ":shit:", this.icones);
                    }
                    catch (BadLocationException ex63) {}
                    break;
                }
                case 181: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.siffle.image")));
                    try {
                        this.insertString(this.getLength(), ":siffle:", this.icones);
                    }
                    catch (BadLocationException ex64) {}
                    break;
                }
                case 184: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.soule.image")));
                    try {
                        this.insertString(this.getLength(), ":soule:", this.icones);
                    }
                    catch (BadLocationException ex65) {}
                    break;
                }
                case 185: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.tire_langue.image")));
                    try {
                        this.insertString(this.getLength(), ":pp", this.icones);
                    }
                    catch (BadLocationException ex66) {}
                    break;
                }
                case 186: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.waza.image")));
                    try {
                        this.insertString(this.getLength(), ":waza:", this.icones);
                    }
                    catch (BadLocationException ex67) {}
                    break;
                }
                case 192: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.vi.image")));
                    try {
                        this.insertString(this.getLength(), ":vi:", this.icones);
                    }
                    catch (BadLocationException ex68) {}
                    break;
                }
                case 844: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.lool_1.image")));
                    try {
                        this.insertString(this.getLength(), ":lool_1:", this.icones);
                    }
                    catch (BadLocationException ex69) {}
                    break;
                }
                case 848: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.box1.image")));
                    try {
                        this.insertString(this.getLength(), ":box1:", this.icones);
                    }
                    catch (BadLocationException ex70) {}
                    break;
                }
                case 847: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.siffle1.image")));
                    try {
                        this.insertString(this.getLength(), ":siffle1:", this.icones);
                    }
                    catch (BadLocationException ex71) {}
                    break;
                }
                case 849: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.sex1.image")));
                    try {
                        this.insertString(this.getLength(), ":sex1:", this.icones);
                    }
                    catch (BadLocationException ex72) {}
                    break;
                }
                case 193: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.baffe.image")));
                    try {
                        this.insertString(this.getLength(), ":baffe:", this.icones);
                    }
                    catch (BadLocationException ex73) {}
                    break;
                }
                case 195: {
                    StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getSmileyImage("symbol.cheese.image")));
                    try {
                        this.insertString(this.getLength(), ":cheese:", this.icones);
                    }
                    catch (BadLocationException ex74) {}
                    break;
                }
                default: {
                    try {
                        if (!this.putlink) {
                            this.insertString(this.getLength(), "" + charArray[i], this.newAttr);
                        }
                        else {
                            StyleConstants.setFontSize(this.links, StyleConstants.getFontSize(this.newAttr));
                            StyleConstants.setBold(this.links, StyleConstants.isBold(this.newAttr));
                            this.insertString(this.getLength(), "" + charArray[i], this.links);
                        }
                    }
                    catch (BadLocationException ex75) {}
                    break;
                }
            }
        }
        this.setParagraphAttributes(0, this.getLength(), this.newAttr, false);
        this.souligner = false;
        StyleConstants.setUnderline(this.newAttr, this.souligner);
    }
    
    public void insertvide() {
        StyleConstants.setIcon(this.icones, new ImageIcon(Resources.getImages("zirc_inconnu_n")));
        try {
            this.insertString(this.getLength(), " ", this.icones);
            final MutableAttributeSet newAttr = this.newAttr;
            final EIRC eirc = this.eirc;
            StyleConstants.setFontSize(newAttr, EIRC.fontsize);
            final MutableAttributeSet newAttr2 = this.newAttr;
            final EIRC eirc2 = this.eirc;
            StyleConstants.setFontFamily(newAttr2, EIRC.fontfamily);
            final MutableAttributeSet newAttr3 = this.newAttr;
            final EIRC eirc3 = this.eirc;
            StyleConstants.setLineSpacing(newAttr3, EIRC.interlignes);
            StyleConstants.setBackground(this.newAttr, this.defult_color);
            StyleConstants.setForeground(this.newAttr, Color.black);
            this.insertString(this.getLength(), "", this.newAttr);
        }
        catch (BadLocationException ex) {}
    }
    
    public void printAction(String emphazeNick, final String s) {
        emphazeNick = this.emphazeNick(emphazeNick, s);
        this.addContent(MircMessage.attrDecode(NewTextDocument.MY_ACTION.format(new Object[] { s, emphazeNick })), s);
    }
    
    public void printError(final String s) {
        this.addContent(MircMessage.attrDecode(NewTextDocument.EIRC_ERROR.format(new Object[] { s })), null);
    }
    
    public void printInfo(final String s) {
        this.addContent(MircMessage.attrDecode(NewTextDocument.EIRC_INFO.format(new Object[] { s })), null);
    }
    
    public void printNotice(final String s, final String s2) {
        this.addContent(MircMessage.attrDecode(NewTextDocument.EIRC_NOTICE.format(new Object[] { s2, s })), null);
    }
    
    public void printPrivmsg(final String s, final String s2) {
        this.addContent(MircMessage.attrDecode(this.getMessageFormat(s2).format(new Object[] { s2, NewTextDocument.separator, s })), s2);
    }
    
    public void printPrivmsg(String emphazeNick, final String s, final User user) {
        emphazeNick = this.emphazeNick(emphazeNick, s);
        final Object[] array = { s, NewTextDocument.separator, emphazeNick };
        MessageFormat messageFormat = this.getMessageFormat(s);
        if (user != null) {
            if (user.isVoiced()) {
                messageFormat = NewTextDocument.VOICE_PRIVMSG;
            }
            if (user.isHalfOp()) {
                messageFormat = NewTextDocument.HOP_PRIVMSG;
            }
            if (user.isOp()) {
                messageFormat = NewTextDocument.OP_PRIVMSG;
            }
            if (user.isAdmin()) {
                messageFormat = NewTextDocument.ADMIN_PRIVMSG;
            }
            if (user.isOwner()) {
                messageFormat = NewTextDocument.OWNER_PRIVMSG;
            }
        }
        this.addContent(MircMessage.attrDecode(messageFormat.format(array)), s);
    }
    
    public void printServerNotice(String string, final String s) {
        try {
            final Object[] parse = NewTextDocument.STRIP_NOTICE.parse(string);
            if (parse[0] != null || !parse[0].equals("")) {
                string = parse[0].toString();
            }
        }
        catch (ParseException ex) {}
        this.addContent(MircMessage.attrDecode(NewTextDocument.SERV_NOTICE.format(new Object[] { s, string })), null);
    }
    
    public void printUnmangled(final String s) {
        this.addContent(MircMessage.attrDecode(NewTextDocument.UNMANGLED.format(new Object[] { s })), null);
    }
    
    public void printWarning(final String s) {
        this.addContent(MircMessage.attrDecode(NewTextDocument.EIRC_WARNING.format(new Object[] { s })), null);
    }
    
    public void setChanneltag(final String channeltag) {
        this.channeltag = channeltag;
    }
    
    static {
        separator = Resources.getStringEirc("msg.separator");
        ME = new MessageFormat(Resources.getStringEirc("msg.me"));
        ACTION = new MessageFormat(Resources.getStringEirc("msg.action"));
        MY_NOTICE = new MessageFormat(Resources.getStringEirc("msg.my_notice"));
        MY_ACTION = new MessageFormat(Resources.getStringEirc("msg.my_action"));
        OP_PRIVMSG = new MessageFormat(Resources.getStringEirc("msg.op"));
        HOP_PRIVMSG = new MessageFormat(Resources.getStringEirc("msg.hop"));
        VOICE_PRIVMSG = new MessageFormat(Resources.getStringEirc("msg.voice"));
        ADMIN_PRIVMSG = new MessageFormat(Resources.getStringEirc("msg.admin"));
        OWNER_PRIVMSG = new MessageFormat(Resources.getStringEirc("msg.owner"));
        MALE_PRIVMSG = new MessageFormat(Resources.getStringEirc("msg.male"));
        FEMALE_PRIVMSG = new MessageFormat(Resources.getStringEirc("msg.female"));
        UNKNOWN_PRIVMSG = new MessageFormat(Resources.getStringEirc("msg.unknown"));
        EIRC_NOTICE = new MessageFormat(Resources.getStringEirc("msg.notice"));
        EIRC_INFO = new MessageFormat(Resources.getStringEirc("msg.info"));
        UNMANGLED = new MessageFormat(Resources.getStringEirc("msg.unmangled"));
        EIRC_ERROR = new MessageFormat(Resources.getStringEirc("msg.error"));
        EIRC_WARNING = new MessageFormat(Resources.getStringEirc("msg.warning"));
        SERV_NOTICE = new MessageFormat(Resources.getStringEirc("msg.serv_notice"));
        STRIP_NOTICE = new MessageFormat(Resources.getStringEirc("msg.strip_notice"));
        smileykeys = SmileyLoader.Smileykeys();
    }
}
