import java.awt.event.ActionEvent;
import java.awt.MenuItem;
import java.util.Stack;
import java.awt.Menu;
import java.applet.AudioClip;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Label;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.PopupMenu;
import java.awt.Panel;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DropMenuII extends Applet
{
    boolean bStdU;
    private String TmpStr;
    private boolean TmpBool1;
    Panel panel;
    SubMenu[] m_MenuItems;
    PopupMenu[] m;
    GetIt[] Creators;
    final Applet a;
    private int TmpInt3;
    private int TmpFont4;
    private int TmpFont3;
    private Font TmpFont2;
    private Font TmpFont;
    String copyright;
    boolean bHorzStyle;
    int nArrow;
    int PWay;
    int TextPadding;
    int CellPadding;
    boolean TempBool212;
    int textaliagn;
    boolean CenterTitle;
    int borderW;
    String maintarget;
    String NotActiveInfo;
    Color AppBkColor;
    Color TitlesBkCr;
    Color TitlesBkCrHover;
    Color TitlesBkCrClk;
    Color TitlesTxtCr;
    Color TitlesTxtCrHover;
    Color TitlesTxtCrClk;
    Color TitlesTxtCrShadow;
    Color TitlesTxtCrShadowHover;
    Color TitlesTxtCrShadowClk;
    int TitlesTxtShadowXShift;
    int TitlesTxtShadowYShift;
    String arrow;
    String arrowhover;
    String arrowclick;
    String MainBackImg;
    String MainBackImgHover;
    String MainBackImgClk;
    String TmPStr1;
    String TmPStr2;
    String TmPStr3;
    String MainFnt;
    int MainFntstyle;
    int MainFntsize;
    String SubMenuFnt;
    int SubMenuFntstyle;
    int SubMenuFntsize;
    String InCod;
    String MainEncode;
    String soundon;
    String soundCLK;
    String StrMenuData;
    int[] ItemsPos;
    int[] ItemsParentNum;
    boolean[] ItemsHasChilds;
    boolean[] IsActive;
    String[] ItemsLink;
    String[] ItemsTargets;
    String[] ItemsEncode;
    String[] ItemsCaption;
    String[] ItemsInfo;
    String[] ItemsIcon;
    String[] ItemsIconHover;
    String[] ItemsIconClk;
    Color[] ItemsBgCr;
    Color[] ItemsBgCrHover;
    Color[] ItemsBgCrClk;
    Color[] ItemsTxtCr;
    Color[] ItemsTxtCrHover;
    Color[] ItemsTxtCrClk;
    Color[] ItemsShadowCr;
    Color[] ItemsShadowCrHover;
    Color[] ItemsShadowCrClk;
    int[] ItemsShadowXShift;
    int[] ItemsShadowYShift;
    String[] ItemsBgImg;
    String[] ItemsBgImgHover;
    String[] ItemsBgImgClk;
    String[] ItemsSoundHover;
    String[] ItemsSoundClk;
    String[] ItemsArrow;
    String[] ItemsArrowHover;
    String[] ItemsArrowClk;
    boolean[] ItemsInside;
    int[] ItemsFrag;
    int[] ItemsSpacing;
    int[] ItemsTextAlaign;
    boolean[] ItemsCenterTitle;
    int[] ItemsBorder;
    String[] ItemsFontName;
    int[] ItemsFontStyle;
    int[] ItemsFontSize;
    ActionListener Clickers;
    
    public URL GetURL(final String s) {
        URL url = null;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {}
        return url;
    }
    
    private String EnCode(final String s, final String s2, final String s3) {
        if (s2.compareTo(s3) != 0) {
            byte[] bytes;
            try {
                bytes = s.getBytes(s2);
            }
            catch (UnsupportedEncodingException ex) {
                return s;
            }
            try {
                return new String(bytes, s3);
            }
            catch (UnsupportedEncodingException ex2) {
                return s;
            }
        }
        return s;
    }
    
    private int GetAlaign(final String s) {
        final String trim = s.toUpperCase().trim();
        if (trim.equalsIgnoreCase("LEFT")) {
            return 0;
        }
        if (trim.equalsIgnoreCase("RIGHT")) {
            return 2;
        }
        if (trim.equalsIgnoreCase("UP")) {
            return 1;
        }
        if (trim.equalsIgnoreCase("DOWN")) {
            return 3;
        }
        return trim.equalsIgnoreCase("CENTER") ? 4 : 2;
    }
    
    private int GetFontStyle(final String s) {
        final String trim = s.trim();
        if (trim.equalsIgnoreCase("PLAIN")) {
            return 0;
        }
        if (trim.equalsIgnoreCase("BOLD")) {
            return 1;
        }
        if (trim.equalsIgnoreCase("ITALIC")) {
            return 2;
        }
        return trim.equalsIgnoreCase("BOLDITALIC") ? 4 : 0;
    }
    
    public static Image loadImage(final Applet applet, final URL url) {
        final MediaTracker mediaTracker = new MediaTracker(applet);
        final Image image = applet.getImage(url);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        return image;
    }
    
    public String getParameter(final String s, final String s2) {
        if (this.bStdU) {
            return System.getProperty(s, s2);
        }
        final String parameter = this.getParameter(s);
        if (parameter != null && parameter.length() > 0) {
            return parameter;
        }
        return s2;
    }
    
    private void CreateTitleFont(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        String mainFnt = stringTokenizer.nextToken().trim();
        if (mainFnt.indexOf("|") > -1) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(mainFnt, "|");
            while (stringTokenizer2.hasMoreTokens()) {
                mainFnt = stringTokenizer2.nextToken();
            }
        }
        if (mainFnt == "") {
            mainFnt = "TimesRoman";
        }
        final String trim = stringTokenizer.nextToken().toLowerCase().trim();
        this.MainFntstyle = 0;
        if (trim.equals("plain")) {
            this.MainFntstyle = 0;
        }
        else if (trim.equalsIgnoreCase("bold")) {
            this.MainFntstyle = 1;
        }
        else if (trim.equalsIgnoreCase("italic")) {
            this.MainFntstyle = 2;
        }
        else if (trim.equalsIgnoreCase("bolditalic")) {
            this.MainFntstyle = 3;
        }
        this.MainFntsize = Integer.parseInt(stringTokenizer.nextToken().trim());
        this.MainFnt = mainFnt;
    }
    
    private void CreateSubFont(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        String subMenuFnt = stringTokenizer.nextToken().trim();
        if (subMenuFnt.indexOf("|") > -1) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(subMenuFnt, "|");
            while (stringTokenizer2.hasMoreTokens()) {
                subMenuFnt = stringTokenizer2.nextToken();
            }
        }
        if (subMenuFnt == "") {
            subMenuFnt = "TimesRoman";
        }
        final String trim = stringTokenizer.nextToken().toLowerCase().trim();
        this.SubMenuFntstyle = 0;
        if (trim.equals("plain")) {
            this.SubMenuFntstyle = 0;
        }
        else if (trim.equalsIgnoreCase("bold")) {
            this.SubMenuFntstyle = 1;
        }
        else if (trim.equalsIgnoreCase("italic")) {
            this.SubMenuFntstyle = 2;
        }
        else if (trim.equalsIgnoreCase("bolditalic")) {
            this.SubMenuFntstyle = 3;
        }
        this.SubMenuFntsize = Integer.parseInt(stringTokenizer.nextToken().trim());
        this.SubMenuFnt = subMenuFnt;
    }
    
    public String getDefaultValue(final String s, final String s2) {
        if (s != null && s.length() > 0) {
            return s;
        }
        return s2;
    }
    
    private boolean exists(final String s) {
        return this.getParameter(s) != null;
    }
    
    int GetTreeCount(final String s, final boolean b) {
        int n = 1;
        int n2 = 1;
        while (this.exists(s + "-" + n)) {
            final String string = s + "-" + n;
            for (int n3 = 1; this.exists(string + "-" + n3); ++n3) {
                n2 += this.GetTreeCount(string + "-" + n3, false);
            }
            ++n2;
            ++n;
        }
        return n2;
    }
    
    void getParameters() {
        try {
            this.bHorzStyle = !this.getParameter("menustyle", "vert").equalsIgnoreCase("vert");
            if (this.bHorzStyle) {
                this.PWay = this.GetAlaign("DOWN");
            }
            else {
                this.PWay = this.GetAlaign("RIGHT");
            }
            this.arrow = this.getParameter("arrow", "");
            this.arrowhover = this.getParameter("arrowhover", "");
            this.arrowclick = this.getParameter("arrowclick", "");
            this.textaliagn = this.GetAlaign(this.getParameter("textaliagn", "RIGHT"));
            this.CenterTitle = Boolean.valueOf(this.getParameter("centertitle", "false"));
            this.TextPadding = 2;
            this.CellPadding = 2;
            this.borderW = Integer.parseInt(this.getParameter("border", "1"));
            this.TempBool212 = true;
            this.soundon = this.getParameter("soundon", "");
            this.soundCLK = this.getParameter("soundclk", "");
            this.AppBkColor = new Color(Integer.parseInt(this.getParameter("AppBKColor", "FFFFFF"), 16));
            this.TitlesBkCr = new Color(Integer.parseInt(this.getParameter("TitlesBkCr", "646464"), 16));
            this.TitlesBkCrHover = new Color(Integer.parseInt(this.getParameter("TitlesBkCrHover", "c4c4c4"), 16));
            this.TitlesBkCrClk = new Color(Integer.parseInt(this.getParameter("TitlesBkCrClk", "FFFFFF"), 16));
            this.TitlesTxtCr = new Color(Integer.parseInt(this.getParameter("TitlesTxtCr", "000000"), 16));
            this.TitlesTxtCrHover = new Color(Integer.parseInt(this.getParameter("TitlesTxtCrHover", "0000FF"), 16));
            this.TitlesTxtCrClk = new Color(Integer.parseInt(this.getParameter("TitlesTxtCrClk", "0000FF"), 16));
            this.TitlesTxtCrShadow = new Color(Integer.parseInt(this.getParameter("TitlesTxtCrShadow", "424242"), 16));
            this.TitlesTxtCrShadowHover = new Color(Integer.parseInt(this.getParameter("TitlesTxtCrShadowHover", "424242"), 16));
            this.TitlesTxtCrShadowClk = new Color(Integer.parseInt(this.getParameter("TitlesTxtCrShadowClk", "424242"), 16));
            this.TitlesTxtShadowXShift = Integer.parseInt(this.getParameter("TitlesTxtShadowXShift", "1"));
            this.TitlesTxtShadowYShift = Integer.parseInt(this.getParameter("TitlesTxtShadowYShift", "1"));
            this.MainBackImg = this.getParameter("MainBackImg", "");
            this.MainBackImgHover = this.getParameter("MainBackImgHover", "");
            this.MainBackImgClk = this.getParameter("MainBackImgClk", "");
            this.CreateTitleFont(this.MainFnt = this.getParameter("titlefnt", "TimesRoman,BOLD,12"));
            this.CreateSubFont(this.SubMenuFnt = this.getParameter("submenufnt", "TimesRoman,BOLD,12"));
            this.InCod = this.getParameter("encoding", "iso-8859-1");
            this.MainEncode = this.getParameter("encoding", "iso-8859-1");
            this.NotActiveInfo = this.EnCode("", this.MainEncode, this.InCod);
            this.maintarget = this.getParameter("maintarget", "_self");
            this.StrMenuData = this.getParameter("MenuData", "");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.nArrow = this.GetAlaign("RIGHT");
        if (this.StrMenuData.length() > 0) {
            this.readMenuDataFile(this.StrMenuData);
        }
        else {
            int n = 1;
            int n2 = 1;
            while (true) {
                new StringBuffer().append("item").append(n).toString();
                if (this.getParameter("item" + n) == null) {
                    break;
                }
                n2 += this.GetTreeCount("item" + n, true);
                ++n;
            }
            this._$746(n2);
            int getTree = 1;
            for (int n3 = 1; this.exists("item" + n3); ++n3) {
                try {
                    getTree = this.GetTree("item" + n3, n3, getTree, true, true);
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        }
        this.m_MenuItems = new SubMenu[this.TmpInt3];
        this.m = new PopupMenu[this.TmpInt3];
        this.Creators = new GetIt[this.TmpInt3];
    }
    
    String Pre(final String s) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            charArray[i] = (char)(charArray[i] - i % 3 - 3);
        }
        return new String(charArray);
    }
    
    int GetTree(final String s, final int n, final int n2, final boolean b, final boolean b2) {
        int n3 = 1;
        int n4 = n2;
        this.ItemsPos[n4] = n2;
        if (b) {
            this.ItemsParentNum[n4] = 0;
        }
        else {
            this.ItemsParentNum[n4] = n;
        }
        if (this.ItemsParentNum[n4] == 0) {
            ++this.TmpInt3;
        }
        this.ItemsHasChilds[n4] = this.exists(s + "-1");
        final String parameter = this.getParameter(s + "-encoding", "");
        if (parameter.length() > 0) {
            this.ItemsEncode[n4] = parameter;
        }
        else {
            this.ItemsEncode[n4] = this.InCod;
        }
        String s2 = this.getParameter(s);
        String s3 = "";
        if (s2.endsWith(",")) {
            s2 += " ";
        }
        for (int i = 0; i < s2.length() - 1; ++i) {
            if (s2.charAt(i) == ',') {
                if (s2.charAt(i + 1) == ',') {
                    s3 += ", ";
                }
                else {
                    s3 += s2.charAt(i);
                }
            }
            else {
                s3 += s2.charAt(i);
            }
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s3 + s2.charAt(s2.length() - 1), ",");
        stringTokenizer.countTokens();
        try {
            this.ItemsCaption[n4] = stringTokenizer.nextToken();
            if (this.ItemsCaption[n4] == null) {
                this.ItemsCaption[n4] = "";
            }
        }
        catch (Exception ex) {
            this.ItemsCaption[n4] = "";
        }
        try {
            this.ItemsCaption[n4] = this.EnCode(this.ItemsCaption[n4], this.MainEncode, this.ItemsEncode[n4]);
            this.ItemsLink[n4] = stringTokenizer.nextToken();
            if (this.ItemsLink[n4] == null) {
                this.ItemsLink[n4] = "";
            }
            if (this.ItemsLink[n4].length() < 3) {
                this.ItemsLink[n4] = "";
            }
        }
        catch (Exception ex2) {
            this.ItemsLink[n4] = "";
        }
        try {
            this.ItemsTargets[n4] = stringTokenizer.nextToken();
            if (this.ItemsTargets[n4] == null) {
                this.ItemsTargets[n4] = this.maintarget;
            }
            if (this.ItemsLink[n4].length() < 3) {
                this.ItemsLink[n4] = "";
            }
        }
        catch (Exception ex3) {
            this.ItemsTargets[n4] = this.maintarget;
        }
        this.IsActive[n4] = this.getParameter(s + "-active", "yes").equalsIgnoreCase("yes");
        final StringBuffer sb = new StringBuffer();
        final String[] itemsCaption = this.ItemsCaption;
        final int n5 = n4;
        itemsCaption[n5] = sb.append(itemsCaption[n5]).append(this.Pre("#,Ihqt,")).toString();
        if (this.ItemsTargets[n4].length() <= 1) {
            this.ItemsTargets[n4] = "_self";
        }
        this.ItemsInfo[n4] = this.EnCode(this.Pre("Girr$[hvxlss#HwrtRhrzLM1#F~#Xtrpx#Wtix"), this.MainEncode, this.ItemsEncode[n4]);
        this.ItemsIcon[n4] = this.getParameter(s + "-icon", this.TmPStr1);
        this.ItemsIconHover[n4] = this.getParameter(s + "-iconhover", this.TmPStr2);
        this.ItemsIconClk[n4] = this.getParameter(s + "-iconclk", this.TmPStr3);
        final String parameter2 = this.getParameter(s + "-bgcr", "");
        if (parameter2.length() > 1) {
            this.ItemsBgCr[n4] = new Color(Integer.parseInt(parameter2, 16));
        }
        else {
            this.ItemsBgCr[n4] = this.TitlesBkCr;
        }
        final String parameter3 = this.getParameter(s + "-bgcrhover", "");
        if (parameter3.length() > 1) {
            this.ItemsBgCrHover[n4] = new Color(Integer.parseInt(parameter3, 16));
        }
        else {
            this.ItemsBgCrHover[n4] = this.TitlesBkCrHover;
        }
        final String parameter4 = this.getParameter(s + "-bgcrclk", "");
        if (parameter4.length() > 1) {
            this.ItemsBgCrClk[n4] = new Color(Integer.parseInt(parameter4, 16));
        }
        else {
            this.ItemsBgCrClk[n4] = this.TitlesBkCrClk;
        }
        final String parameter5 = this.getParameter(s + "-txtcr", "");
        if (parameter5.length() > 1) {
            this.ItemsTxtCr[n4] = new Color(Integer.parseInt(parameter5, 16));
        }
        else {
            this.ItemsTxtCr[n4] = this.TitlesTxtCr;
        }
        final String parameter6 = this.getParameter(s + "-txtcrhover", "");
        if (parameter6.length() > 1) {
            this.ItemsTxtCrHover[n4] = new Color(Integer.parseInt(parameter6, 16));
        }
        else {
            this.ItemsTxtCrHover[n4] = this.TitlesTxtCrHover;
        }
        final String parameter7 = this.getParameter(s + "-txtcrclk", "");
        if (parameter7.length() > 1) {
            this.ItemsTxtCrClk[n4] = new Color(Integer.parseInt(parameter7, 16));
        }
        else {
            this.ItemsTxtCrClk[n4] = this.TitlesTxtCrClk;
        }
        final String parameter8 = this.getParameter(s + "-shadowcr", "");
        if (parameter8.length() > 1) {
            this.ItemsShadowCr[n4] = new Color(Integer.parseInt(parameter8, 16));
        }
        else {
            this.ItemsShadowCr[n4] = this.TitlesTxtCrShadow;
        }
        final String parameter9 = this.getParameter(s + "-shadowcrhover", "");
        if (parameter9.length() > 1) {
            this.ItemsShadowCrHover[n4] = new Color(Integer.parseInt(parameter9, 16));
        }
        else {
            this.ItemsShadowCrHover[n4] = this.TitlesTxtCrShadowHover;
        }
        final String parameter10 = this.getParameter(s + "-shadowcrclk", "");
        if (parameter10.length() > 1) {
            this.ItemsShadowCrClk[n4] = new Color(Integer.parseInt(parameter10, 16));
        }
        else {
            this.ItemsShadowCrClk[n4] = this.TitlesTxtCrShadowClk;
        }
        final String parameter11 = this.getParameter(s + "-shadowxshift", "");
        if (parameter11.length() > 1) {
            this.ItemsShadowXShift[n4] = Integer.parseInt(parameter11);
        }
        else {
            this.ItemsShadowXShift[n4] = this.TitlesTxtShadowXShift;
        }
        final String parameter12 = this.getParameter(s + "-shadowyshift", "");
        if (parameter12.length() > 1) {
            this.ItemsShadowYShift[n4] = Integer.parseInt(parameter12);
        }
        else {
            this.ItemsShadowYShift[n4] = this.TitlesTxtShadowYShift;
        }
        this.ItemsBgImg[n4] = this.getParameter(s + "-bgimg", this.MainBackImg);
        this.ItemsBgImgHover[n4] = this.getParameter(s + "-bgimghover", this.MainBackImgHover);
        this.ItemsBgImgClk[n4] = this.getParameter(s + "-bgimgclk", this.MainBackImgClk);
        this.ItemsSoundHover[n4] = this.getParameter(s + "-soundhover", this.soundon);
        this.ItemsSoundClk[n4] = this.getParameter(s + "-soundclk", this.soundCLK);
        this.ItemsArrow[n4] = this.getParameter(s + "-arrow", this.arrow);
        this.ItemsArrowHover[n4] = this.getParameter(s + "-arrowhover", this.arrowhover);
        this.ItemsArrowClk[n4] = this.getParameter(s + "-arrowclk", this.arrowclick);
        this.ItemsInside[n4] = this.TempBool212;
        this.ItemsFrag[n4] = this.TextPadding;
        this.ItemsSpacing[n4] = this.CellPadding;
        final String parameter13 = this.getParameter(s + "-textalaign", "");
        if (parameter13.length() > 0) {
            this.ItemsTextAlaign[n4] = this.GetAlaign(parameter13);
        }
        else {
            this.ItemsTextAlaign[n4] = this.textaliagn;
        }
        final String parameter14 = this.getParameter(s + "-border", "");
        if (parameter14.length() > 0) {
            this.ItemsBorder[n4] = Integer.parseInt(parameter14);
        }
        else {
            this.ItemsBorder[n4] = this.borderW;
        }
        final String parameter15 = this.getParameter(s + "-centered", "");
        if (parameter15.length() > 0) {
            this.ItemsCenterTitle[n4] = Boolean.valueOf(parameter15);
        }
        else {
            this.ItemsCenterTitle[n4] = this.CenterTitle;
        }
        if (this.ItemsParentNum[n4] == 0) {
            this.ItemsFontName[n4] = this.MainFnt;
        }
        else {
            this.ItemsFontName[n4] = this.SubMenuFnt;
        }
        if (this.ItemsParentNum[n4] == 0) {
            this.ItemsFontStyle[n4] = this.MainFntstyle;
        }
        else {
            this.ItemsFontStyle[n4] = this.SubMenuFntstyle;
        }
        if (this.ItemsParentNum[n4] == 0) {
            this.ItemsFontSize[n4] = this.MainFntsize;
        }
        else {
            this.ItemsFontSize[n4] = this.SubMenuFntsize;
        }
        if (b2) {
            while (this.exists(s + "-" + n3)) {
                final String string = s + "-" + n3;
                n4 = this.GetTree(string, this.ItemsPos[n2], n4 + 1, false, false);
                int n6 = 1;
                final int n7 = n4;
                while (this.exists(string + "-" + n6)) {
                    n4 = this.GetTree(string + "-" + n6, n7, n4 + 1, false, true);
                    ++n6;
                }
                ++n3;
            }
        }
        if (b) {
            ++n4;
        }
        return n4;
    }
    
    private void _$746(final int n) {
        this.ItemsPos = new int[n];
        this.ItemsParentNum = new int[n];
        this.ItemsHasChilds = new boolean[n];
        this.IsActive = new boolean[n];
        this.ItemsLink = new String[n];
        this.ItemsTargets = new String[n];
        this.ItemsEncode = new String[n];
        this.ItemsCaption = new String[n];
        this.ItemsInfo = new String[n];
        this.ItemsIcon = new String[n];
        this.ItemsIconHover = new String[n];
        this.ItemsIconClk = new String[n];
        this.ItemsBgCr = new Color[n];
        this.ItemsBgCrHover = new Color[n];
        this.ItemsBgCrClk = new Color[n];
        this.ItemsTxtCr = new Color[n];
        this.ItemsTxtCrHover = new Color[n];
        this.ItemsTxtCrClk = new Color[n];
        this.ItemsShadowCr = new Color[n];
        this.ItemsShadowCrHover = new Color[n];
        this.ItemsShadowCrClk = new Color[n];
        this.ItemsShadowXShift = new int[n];
        this.ItemsShadowYShift = new int[n];
        this.ItemsBgImg = new String[n];
        this.ItemsBgImgHover = new String[n];
        this.ItemsBgImgClk = new String[n];
        this.ItemsSoundHover = new String[n];
        this.ItemsSoundClk = new String[n];
        this.ItemsArrow = new String[n];
        this.ItemsArrowHover = new String[n];
        this.ItemsArrowClk = new String[n];
        this.ItemsInside = new boolean[n];
        this.ItemsFrag = new int[n];
        this.ItemsSpacing = new int[n];
        this.ItemsTextAlaign = new int[n];
        this.ItemsCenterTitle = new boolean[n];
        this.ItemsBorder = new int[n];
        this.ItemsFontName = new String[n];
        this.ItemsFontStyle = new int[n];
        this.ItemsFontSize = new int[n];
    }
    
    public void readMenuDataFile(final String s) {
        final int n = 38;
        int n2 = 0;
        try {
            final URL getURL = this.GetURL(s);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getURL.openStream()));
            while (bufferedReader.readLine() != null) {
                ++n2;
            }
            bufferedReader.close();
            final int n3 = n2 + 1;
            this._$746(n3);
            final int[] array = new int[n3];
            int n4 = 1;
            final BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(getURL.openStream()));
            String line;
            while ((line = bufferedReader2.readLine()) != null) {
                final String[] $761 = this._$761(line, '\t', n);
                array[n4] = Integer.parseInt($761[0]);
                this.ItemsPos[n4] = n4;
                this.ItemsHasChilds[n4] = false;
                if (array[n4] == 0) {
                    ++this.TmpInt3;
                    this.ItemsParentNum[n4] = 0;
                }
                else if (n4 > 0 && array[n4] == array[n4 - 1]) {
                    this.ItemsParentNum[n4] = this.ItemsParentNum[n4 - 1];
                }
                else if (n4 > 0 && array[n4] > array[n4 - 1]) {
                    this.ItemsParentNum[n4] = this.ItemsPos[n4 - 1];
                    this.ItemsHasChilds[n4 - 1] = true;
                }
                else if (n4 > 0 && array[n4] < array[n4 - 1]) {
                    int n5;
                    for (n5 = 1; n5 < n4 && array[n4] < array[n4 - n5]; ++n5) {}
                    if (array[n4] == array[n4 - n5]) {
                        this.ItemsParentNum[n4] = this.ItemsParentNum[n4 - n5];
                    }
                }
                this.IsActive[n4] = Boolean.valueOf($761[1]);
                this.ItemsLink[n4] = $761[2];
                this.ItemsTargets[n4] = this.getDefaultValue($761[3], this.maintarget);
                if (this.ItemsTargets[n4].length() == 0) {
                    this.ItemsTargets[n4] = "_self";
                }
                this.ItemsEncode[n4] = this.getDefaultValue($761[4], this.InCod);
                this.ItemsCaption[n4] = this.EnCode($761[5], this.MainEncode, this.ItemsEncode[n4]);
                this.ItemsInfo[n4] = this.EnCode($761[6], this.MainEncode, this.ItemsEncode[n4]);
                final String s2 = $761[7];
                if (s2.length() > 0) {
                    this.ItemsTextAlaign[n4] = this.GetAlaign(s2);
                }
                else {
                    this.ItemsTextAlaign[n4] = this.textaliagn;
                }
                final String s3 = $761[8];
                if (s3.length() > 0) {
                    this.ItemsCenterTitle[n4] = Boolean.valueOf(s3);
                }
                else {
                    this.ItemsCenterTitle[n4] = this.CenterTitle;
                }
                final String s4 = $761[9];
                if (s4.length() > 0) {
                    this.ItemsFrag[n4] = Integer.parseInt(s4);
                }
                else {
                    this.ItemsFrag[n4] = this.TextPadding;
                }
                final String s5 = $761[10];
                if (s5.length() > 0) {
                    this.ItemsSpacing[n4] = Integer.parseInt(s5);
                }
                else {
                    this.ItemsSpacing[n4] = this.CellPadding;
                }
                final String s6 = $761[11];
                if (s6.length() > 0) {
                    this.ItemsBorder[n4] = Integer.parseInt(s6);
                }
                else {
                    this.ItemsBorder[n4] = this.borderW;
                }
                final String s7 = $761[12];
                if (s7.length() > 0) {
                    this.ItemsInside[n4] = Boolean.valueOf(s7);
                }
                else {
                    this.ItemsInside[n4] = this.TempBool212;
                }
                if (array[n4] == 0) {
                    this.ItemsFontName[n4] = this.getDefaultValue($761[13], this.MainFnt);
                }
                else {
                    this.ItemsFontName[n4] = this.getDefaultValue($761[13], this.SubMenuFnt);
                }
                final String s8 = $761[14];
                if (s8.length() > 0) {
                    this.ItemsFontStyle[n4] = this.GetFontStyle(s8);
                }
                else if (array[n4] == 0) {
                    this.ItemsFontStyle[n4] = this.MainFntstyle;
                }
                else {
                    this.ItemsFontStyle[n4] = this.SubMenuFntstyle;
                }
                final String s9 = $761[15];
                if (s9.length() > 0) {
                    this.ItemsFontSize[n4] = Integer.parseInt(s9);
                }
                else if (array[n4] == 0) {
                    this.ItemsFontSize[n4] = this.MainFntsize;
                }
                else {
                    this.ItemsFontSize[n4] = this.SubMenuFntsize;
                }
                final String s10 = $761[16];
                if (s10.length() > 0) {
                    this.ItemsBgCr[n4] = new Color(Integer.parseInt(s10, 16));
                }
                else {
                    this.ItemsBgCr[n4] = this.TitlesBkCr;
                }
                final String s11 = $761[17];
                if (s11.length() > 0) {
                    this.ItemsBgCrHover[n4] = new Color(Integer.parseInt(s11, 16));
                }
                else {
                    this.ItemsBgCrHover[n4] = this.TitlesBkCrHover;
                }
                final String s12 = $761[18];
                if (s12.length() > 0) {
                    this.ItemsBgCrClk[n4] = new Color(Integer.parseInt(s12, 16));
                }
                else {
                    this.ItemsBgCrClk[n4] = this.TitlesBkCrClk;
                }
                final String s13 = $761[19];
                if (s13.length() > 0) {
                    this.ItemsTxtCr[n4] = new Color(Integer.parseInt(s13, 16));
                }
                else {
                    this.ItemsTxtCr[n4] = this.TitlesTxtCr;
                }
                final String s14 = $761[20];
                if (s14.length() > 0) {
                    this.ItemsTxtCrHover[n4] = new Color(Integer.parseInt(s14, 16));
                }
                else {
                    this.ItemsTxtCrHover[n4] = this.TitlesTxtCrHover;
                }
                final String s15 = $761[21];
                if (s15.length() > 0) {
                    this.ItemsTxtCrClk[n4] = new Color(Integer.parseInt(s15, 16));
                }
                else {
                    this.ItemsTxtCrClk[n4] = this.TitlesTxtCrClk;
                }
                final String s16 = $761[22];
                if (s16.length() > 0) {
                    this.ItemsShadowCr[n4] = new Color(Integer.parseInt(s16, 16));
                }
                else {
                    this.ItemsShadowCr[n4] = this.TitlesTxtCrShadow;
                }
                final String s17 = $761[23];
                if (s17.length() > 0) {
                    this.ItemsShadowCrHover[n4] = new Color(Integer.parseInt(s17, 16));
                }
                else {
                    this.ItemsShadowCrHover[n4] = this.TitlesTxtCrShadowHover;
                }
                final String s18 = $761[24];
                if (s18.length() > 0) {
                    this.ItemsShadowCrClk[n4] = new Color(Integer.parseInt(s18, 16));
                }
                else {
                    this.ItemsShadowCrClk[n4] = this.TitlesTxtCrShadowClk;
                }
                final String s19 = $761[25];
                if (s19.length() > 0) {
                    this.ItemsShadowXShift[n4] = Integer.parseInt(s19);
                }
                else {
                    this.ItemsShadowXShift[n4] = this.TitlesTxtShadowXShift;
                }
                final String s20 = $761[26];
                if (s20.length() > 0) {
                    this.ItemsShadowYShift[n4] = Integer.parseInt(s20);
                }
                else {
                    this.ItemsShadowYShift[n4] = this.TitlesTxtShadowYShift;
                }
                this.ItemsIcon[n4] = this.getDefaultValue($761[27], this.TmPStr1);
                this.ItemsIconHover[n4] = this.getDefaultValue($761[28], this.TmPStr2);
                this.ItemsIconClk[n4] = this.getDefaultValue($761[29], this.TmPStr3);
                this.ItemsBgImg[n4] = this.getDefaultValue($761[30], this.MainBackImg);
                this.ItemsBgImgHover[n4] = this.getDefaultValue($761[31], this.MainBackImgHover);
                this.ItemsBgImgClk[n4] = this.getDefaultValue($761[32], this.MainBackImgClk);
                this.ItemsArrow[n4] = this.getDefaultValue($761[33], this.arrow);
                this.ItemsArrowHover[n4] = this.getDefaultValue($761[34], this.arrowhover);
                this.ItemsArrowClk[n4] = this.getDefaultValue($761[35], this.arrowclick);
                this.ItemsSoundHover[n4] = this.getDefaultValue($761[36], this.soundon);
                this.ItemsSoundClk[n4] = this.getDefaultValue($761[37], this.soundCLK);
                ++n4;
            }
            bufferedReader2.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private String[] _$761(final String s, final char c, final int n) {
        String string = new String();
        final String[] array = new String[n];
        int n2 = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == c) {
                array[n2] = string;
                string = new String();
                if (++n2 >= n) {
                    break;
                }
            }
            else {
                string = String.valueOf(string) + String.valueOf(s.charAt(i));
            }
        }
        if (n2 < n) {
            array[n2] = string;
            ++n2;
        }
        for (int j = n2; j < n; ++j) {
            array[j] = "";
        }
        return array;
    }
    
    public DropMenuII() {
        this.a = this;
        this.TmpInt3 = 0;
        this.TmpFont4 = 0;
        this.TmPStr3 = "";
        this.TmpFont3 = 0;
        this.bStdU = false;
        this.m_MenuItems = null;
        this.m = null;
        this.Creators = null;
        this.Clickers = new ActL(this);
        this.TmPStr1 = "";
        this.TmPStr2 = "";
        this.TmpStr = "2.0";
        this.TmpBool1 = true;
    }
    
    protected boolean CopyRights() {
        final String s = new String("DropMenuII (c) Applet, By Tools Soft Solutions");
        try {
            this.copyright = this.getParameter("Copyright", "");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return s.equalsIgnoreCase(this.copyright);
    }
    
    public void init() {
        if (!this.CopyRights()) {
            this.add(new Label("CopyRight Parameter is missing !"));
            this.validate();
            return;
        }
        this.add(new Label("Loading..."));
        this.validate();
        this.getParameters();
        this.removeAll();
        int n = 0;
        this.TmpFont = new Font(this.SubMenuFnt, this.SubMenuFntstyle, this.SubMenuFntsize);
        for (int i = 1; i < this.ItemsPos.length; ++i) {
            if (this.ItemsParentNum[i] == 0) {
                (this.m_MenuItems[n] = new SubMenu(this.ItemsCaption[i])).SetItemID(i);
                Image loadImage = null;
                if (this.ItemsIcon[i].length() > 0) {
                    loadImage = loadImage(this, this.GetURL(this.ItemsIcon[i]));
                }
                Image loadImage2 = null;
                if (this.ItemsIconHover[i].length() > 0) {
                    loadImage2 = loadImage(this, this.GetURL(this.ItemsIconHover[i]));
                }
                Image loadImage3 = null;
                if (this.ItemsIconClk[i].length() > 0) {
                    loadImage3 = loadImage(this, this.GetURL(this.ItemsIconClk[i]));
                }
                this.m_MenuItems[n].SetImgActives(loadImage, loadImage3, loadImage2);
                Image loadImage4 = null;
                if (this.ItemsBgImg[i].length() > 0) {
                    loadImage4 = loadImage(this, this.GetURL(this.ItemsBgImg[i]));
                }
                Image loadImage5 = null;
                if (this.ItemsBgImgHover[i].length() > 0) {
                    loadImage5 = loadImage(this, this.GetURL(this.ItemsBgImgHover[i]));
                }
                Image loadImage6 = null;
                if (this.ItemsBgImgClk[i].length() > 0) {
                    loadImage6 = loadImage(this, this.GetURL(this.ItemsBgImgClk[i]));
                }
                this.m_MenuItems[n].SetImgBK(loadImage4, loadImage6, loadImage5);
                AudioClip audioClip = null;
                if (this.ItemsSoundClk[i].length() > 0) {
                    audioClip = this.getAudioClip(this.GetURL(this.ItemsSoundClk[i]));
                }
                AudioClip audioClip2 = null;
                if (this.ItemsSoundHover[i].length() > 0) {
                    audioClip2 = this.getAudioClip(this.GetURL(this.ItemsSoundHover[i]));
                }
                this.m_MenuItems[n].SetSounds(audioClip, audioClip2);
                this.m_MenuItems[n].SetBordParam(this.ItemsBorder[i]);
                this.m_MenuItems[n].SetItemStatusNote(this.ItemsInside[i]);
                this.m_MenuItems[n].SetTxtPos(this.ItemsTextAlaign[i]);
                this.m_MenuItems[n].SetTitleCentered(this.ItemsCenterTitle[i]);
                this.m_MenuItems[n].SetImgGP(this.ItemsSpacing[i]);
                this.m_MenuItems[n].SetPDAs(this.ItemsFrag[i]);
                this.TmpFont2 = new Font(this.ItemsFontName[i], this.ItemsFontStyle[i], this.ItemsFontSize[i]);
                this.m_MenuItems[n].setFont(this.TmpFont2);
                this.m_MenuItems[n].SetActiveCr(this.ItemsBgCr[i], this.ItemsBgCrHover[i], this.ItemsBgCrClk[i], this.ItemsTxtCr[i], this.ItemsTxtCrHover[i], this.ItemsTxtCrClk[i], this.ItemsShadowCr[i], this.ItemsShadowCrHover[i], this.ItemsShadowCrClk[i]);
                this.m_MenuItems[n].SetShadow(this.ItemsShadowXShift[i], this.ItemsShadowYShift[i]);
                if (this.ItemsHasChilds[i]) {
                    this.m_MenuItems[n].PutActType("menu");
                    Image loadImage7 = null;
                    if (this.ItemsArrow[i].length() > 0) {
                        loadImage7 = loadImage(this, this.GetURL(this.ItemsArrow[i]));
                    }
                    Image loadImage8 = null;
                    if (this.ItemsArrowHover[i].length() > 0) {
                        loadImage8 = loadImage(this, this.GetURL(this.ItemsArrowHover[i]));
                    }
                    Image loadImage9 = null;
                    if (this.ItemsArrowClk[i].length() > 0) {
                        loadImage9 = loadImage(this, this.GetURL(this.ItemsArrowClk[i]));
                    }
                    this.m_MenuItems[n].Fun32(loadImage7, loadImage8, loadImage9, this.nArrow);
                }
                else {
                    this.m_MenuItems[n].PutActType("link");
                }
                this.TmpFont4 = Math.max(this.TmpFont4, this.m_MenuItems[n].getSize().width);
                this.TmpFont3 = Math.max(this.TmpFont3, this.m_MenuItems[n].getSize().height);
                this._$811(n, i);
                ++n;
            }
        }
        this.setBackground(this.AppBkColor);
        if (this.bHorzStyle) {
            final int n2 = this.getSize().width - this.TmpInt3 * this.TmpFont4;
            int n3;
            if (n2 > 0 && this.TmpFont4 > 0) {
                n3 = n2 / this.TmpFont4;
            }
            else {
                n3 = 0;
            }
            for (int j = 1; j <= n3; ++j) {
                this.add(new Label());
            }
            this.setLayout(new GridLayout(1, this.TmpInt3 + n3, 0, 0));
        }
        else {
            final int n4 = this.getSize().height - this.TmpInt3 * this.TmpFont3;
            int n5;
            if (n4 > 0 && this.TmpFont3 > 0) {
                n5 = n4 / this.TmpFont3;
            }
            else {
                n5 = 0;
            }
            for (int k = 1; k <= n5; ++k) {
                this.add(new Label());
            }
            this.setLayout(new GridLayout(this.TmpInt3 + n5, 1, 0, 0));
        }
        try {
            this._$818();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void _$811(final int n, final int n2) {
        this.add(this.m_MenuItems[n]);
        if (this.m_MenuItems[n].RetActType() == "menu") {
            this.add(this.m[n] = new PopupMenu());
            (this.Creators[n] = new GetIt(this, this.m[n], n2, this.Clickers)).addActionListener(new ActL2(this, n));
            this.Creators[n].start();
        }
        else {
            this.DrpM(n);
        }
    }
    
    public void DrpM(final int n) {
        this.m_MenuItems[n].addActionListener(new ActL3(this, n, this.m_MenuItems[n].GetItemID()));
    }
    
    private void _$818() throws Exception {
    }
    
    public void start() {
    }
    
    public void stop() {
    }
    
    public void destroy() {
    }
    
    public String getAppletInfo() {
        return "DropMenuII Applet (c) By Tools Soft";
    }
    
    class SetItUp
    {
        Stack st;
        ActionListener l;
        
        public void addMenuItem(final String s, final int n, final boolean enabled, final Font font) {
            final MenuItem menuItem = new MenuItem(s);
            menuItem.setFont(font);
            menuItem.setEnabled(enabled);
            menuItem.setActionCommand(Integer.toString(n));
            menuItem.addActionListener(this.l);
            this.getCurrentMenu().add(menuItem);
        }
        
        public void addSeparator() {
            this.getCurrentMenu().addSeparator();
        }
        
        public void startSubMenu(final String s, final Font font) {
            final Menu menu = new Menu(s);
            menu.setFont(font);
            this.getCurrentMenu().add(menu);
            this.st.push(menu);
        }
        
        public void endSubMenu() {
            this.st.pop();
        }
        
        Menu getCurrentMenu() {
            return this.st.peek();
        }
        
        public SetItUp(final Menu menu, final ActionListener l) {
            (this.st = new Stack()).push(menu);
            this.l = l;
        }
    }
    
    public class GetIt extends Thread
    {
        Menu m;
        int realid;
        ActionListener l;
        ActionListener doneListener;
        private final DropMenuII MainApplet;
        
        public void addActionListener(final ActionListener doneListener) {
            this.doneListener = doneListener;
        }
        
        public void run() {
            final SetItUp setItUp = this.MainApplet.new SetItUp(this.m, this.l);
            int n = 0;
            while (true) {
                for (int i = this.realid; i < this.MainApplet.ItemsPos.length; ++i) {
                    if (!this.MainApplet.ItemsHasChilds[i - 1]) {
                        for (int n2 = 1; n > 0 && n2 < i && this.MainApplet.ItemsParentNum[i] != this.MainApplet.ItemsParentNum[i - n2]; --n, ++n2) {
                            setItUp.endSubMenu();
                        }
                    }
                    if (this.MainApplet.ItemsHasChilds[i]) {
                        if (i > this.realid) {
                            setItUp.startSubMenu(this.MainApplet.ItemsCaption[i], this.MainApplet.TmpFont);
                            ++n;
                        }
                        if (this.MainApplet.ItemsLink[i].length() > 0) {
                            this.MainApplet.TmpFont2 = new Font(this.MainApplet.ItemsFontName[i], this.MainApplet.ItemsFontStyle[i], this.MainApplet.ItemsFontSize[i]);
                            setItUp.addMenuItem(this.MainApplet.ItemsCaption[i], i, this.MainApplet.IsActive[i], this.MainApplet.TmpFont2);
                            setItUp.addSeparator();
                        }
                    }
                    else {
                        this.MainApplet.TmpFont2 = new Font(this.MainApplet.ItemsFontName[i], this.MainApplet.ItemsFontStyle[i], this.MainApplet.ItemsFontSize[i]);
                        setItUp.addMenuItem(this.MainApplet.ItemsCaption[i], i, this.MainApplet.IsActive[i], this.MainApplet.TmpFont2);
                    }
                    if (i < this.MainApplet.ItemsPos.length - 1 && this.MainApplet.ItemsParentNum[i + 1] == 0) {
                        for (int j = 1; j <= n; ++j) {
                            setItUp.endSubMenu();
                        }
                        this.notifyListener("done");
                        return;
                    }
                }
                continue;
            }
        }
        
        void notifyListener(final String s) {
            if (this.doneListener != null) {
                this.doneListener.actionPerformed(new ActionEvent(this, 0, s));
            }
        }
        
        public GetIt(final DropMenuII mainApplet, final Menu m, final int realid, final ActionListener l) {
            this.MainApplet = mainApplet;
            this.m = m;
            this.l = l;
            this.realid = realid;
        }
    }
}
