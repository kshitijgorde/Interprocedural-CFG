// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Date;
import java.awt.BorderLayout;
import java.awt.Component;
import java.net.MalformedURLException;
import java.awt.LayoutManager;
import JAVACharter.util.b;
import java.net.URL;
import java.awt.Panel;
import JAVACharter.c.a;
import JAVACharter.c.c;
import JAVACharter.c.e;
import JAVACharter.b.d;
import JAVACharter.a.f;
import JAVACharter.StyleManage.StyleCache;
import java.applet.Applet;

public class Charter extends Applet
{
    private StyleCache try;
    private f byte;
    private d do;
    private e[] void;
    private JAVACharter.c.f if;
    private c long;
    private a else;
    private Panel new;
    private Panel int;
    private String[] case;
    public URL baseURL;
    private String for;
    private String null;
    private String goto;
    private boolean char;
    private boolean a;
    
    public Charter() {
        this.for = "";
        this.goto = "";
        this.char = false;
        this.a = false;
    }
    
    public void init() {
        this.baseURL = null;
        this.baseURL = this.getCodeBase();
        this.if = new JAVACharter.c.f(this);
        this.long = new c(this, this.if);
        (this.try = new StyleCache(this.getParameter("styleNumber"), this.baseURL.toString(), this.long, this.getParameter("stylePath"))).b();
        final int k = this.try.k();
        this.void = new e[k];
        this.byte = new f(this.baseURL, this.void);
        this.else = new a(this);
        synchronized (this.byte) {
            final Date a = b.a(true);
            this.byte.a(b.do(a, 1), a);
            this.byte.if(1);
            this.byte.a(this.try);
            final String parameter = this.getParameter("defaultsid");
            final String parameter2 = this.getParameter("baseLabel");
            if (parameter == "" || parameter == null) {
                String parameter3 = this.getParameter("defaultsymbol");
                if (parameter3 == "" || parameter3 == null) {
                    parameter3 = "DJIA";
                }
                if (parameter2 == "" || parameter2 == null) {
                    this.byte.for(parameter3);
                }
                else {
                    this.byte.if(parameter3, parameter2);
                }
            }
            else if (parameter2 == "" || parameter2 == null) {
                this.byte.byte(parameter);
            }
            else {
                this.byte.do(parameter, parameter2);
            }
            (this.int = new Panel()).setLayout(null);
            final int try1 = this.try.a(0).try;
            int n = 0;
            Image image = null;
            String parameter4 = this.getParameter("bgsPath");
            if (parameter4 == null) {
                parameter4 = new String(this.baseURL.toString() + "JAVACharter/bgs");
            }
            for (int i = 0; i < k; ++i) {
                try {
                    image = this.getImage(new URL(parameter4 + "/"), this.try.a(i).k);
                }
                catch (MalformedURLException ex) {
                    System.out.println(ex);
                }
                (this.void[i] = new e(this.try.a(i), this.byte, this.void.length, this.long, this.if, image, this)).if(this.try.a(i).else);
                this.void[i].a(this.try.c());
                this.void[i].a(this.else);
                this.void[i].setLocation(0, n);
                n += this.try.a(i).m;
                this.int.add(this.void[i]);
            }
            this.int.setBounds(0, 0, try1, n);
            this.int.setSize(try1, n);
            this.long.a(this.try);
            JAVACharter.c.d.a(0);
            this.if.case = this.void;
            this.if.a = this.void[0].int();
            final int width = this.long.getWidth();
            final int height = this.long.getHeight();
            this.long.setBounds(0, 0, width, height);
            this.new = new Panel(new BorderLayout());
            switch (this.try.int()) {
                case 0: {
                    this.new.add(this.long, "North");
                    this.new.add(this.int, "South");
                    this.new.setBounds(0, 0, try1, n + height);
                    break;
                }
                case 1: {
                    this.new.add(this.long, "South");
                    this.new.add(this.int, "North");
                    this.new.setBounds(0, 0, try1, n + height);
                    break;
                }
                case 2: {
                    this.new.add(this.int, "North");
                    this.new.setBounds(0, 0, try1, n);
                    break;
                }
                case 3: {
                    this.new.add(this.long, "West");
                    this.new.add(this.int, "Center");
                    this.new.setBounds(0, 0, try1 + width, n);
                    break;
                }
                case 4: {
                    this.new.add(this.long, "East");
                    this.new.add(this.int, "Center");
                    this.new.setBounds(0, 0, try1 + width, n);
                    break;
                }
                default: {
                    this.new.add(this.long, "North");
                    this.new.add(this.int, "South");
                    this.new.setBounds(0, 0, try1, n + height);
                    break;
                }
            }
            this.setLayout(null);
            this.add(this.new);
            if (!this.byte.h()) {
                this.if.if(false);
                this.do();
                this.goto = "Error: Symbol Cannot Be Found";
                this.a = true;
                this.byte.g();
            }
            this.byte.do(10);
            this.char();
        }
    }
    
    public e[] getChartArray() {
        return this.void;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.char) {
            graphics.setFont(new Font("xFont", 0, 11));
            this.setBackground(Color.white);
        }
    }
    
    public void showLastError() {
        synchronized (this.byte) {
            this.innerShowLastError();
        }
    }
    
    public void refreshAllCharts() {
        synchronized (this.byte) {
            this.innerRefreshAllCharts();
        }
    }
    
    public void setAnchor(final String s, final int n) {
        synchronized (this.byte) {
            this.innerSetAnchor(s, n);
        }
    }
    
    public void doCompare(final String s) {
        synchronized (this.byte) {
            this.a(s, null);
        }
    }
    
    public void doCompare(final String s, final String s2) {
        synchronized (this.byte) {
            this.a(s, s2);
        }
    }
    
    public void doCompareSid(final String s, final String s2) {
        synchronized (this.byte) {
            this.if(s, s2);
        }
    }
    
    public void doCompareSid(final String s) {
        synchronized (this.byte) {
            this.if(s, null);
        }
    }
    
    public void doIndexCompare(final String s) {
        synchronized (this.byte) {
            this.do(s);
        }
    }
    
    public void addCompare(final String s) {
        synchronized (this.byte) {
            this.if(s);
        }
    }
    
    public void addCompareSid(final String s) {
        synchronized (this.byte) {
            this.a(s);
        }
    }
    
    public void addOverlay(final String s, final int n) {
        synchronized (this.byte) {
            this.if(s, n);
        }
    }
    
    public void deleteOverlay(final String s, final int n) {
        synchronized (this.byte) {
            this.a(s, n);
        }
    }
    
    public void chartSymb(final String s) {
        this.chartSymb(s, null);
    }
    
    public void chartSymb(final String s, final String s2) {
        synchronized (this.byte) {
            this.for(s, s2);
        }
    }
    
    public void chartSid(final String s) {
        this.chartSid(s, null);
    }
    
    public void chartSid(final String s, final String s2) {
        synchronized (this.byte) {
            this.do(s, s2);
        }
    }
    
    public void addSMA(final String s, final int n, final int n2) {
        synchronized (this.byte) {
            this.a(s, n, n2);
        }
    }
    
    public void setTime(final int n) {
        synchronized (this.byte) {
            this.new(n);
        }
    }
    
    public int getTime() {
        synchronized (this.byte) {
            return this.a();
        }
    }
    
    public int getFreq() {
        synchronized (this.byte) {
            return this.try();
        }
    }
    
    public boolean isError() {
        synchronized (this.byte) {
            return this.isError();
        }
    }
    
    public void setFrequency(final int n) {
        synchronized (this.byte) {
            this.a(n);
        }
    }
    
    public void clearTrendlines() {
        synchronized (this.byte) {
            this.byte();
        }
    }
    
    public void setCustomTf(final int n) {
        synchronized (this.byte) {
            this.if(n);
        }
    }
    
    public void setLogScale(final int n) {
        synchronized (this.byte) {
            this.int(n);
        }
    }
    
    public void setDrawingStyle(final int n) {
        synchronized (this.byte) {
            this.for(n);
        }
    }
    
    public void zoomOut() {
        synchronized (this.byte) {
            this.int();
        }
    }
    
    public void zoomIn() {
        synchronized (this.byte) {
            this.if();
        }
    }
    
    public void zoomReset() {
        synchronized (this.byte) {
            this.for();
        }
    }
    
    public boolean isHistorical() {
        synchronized (this.byte) {
            return this.innerIsHistorical();
        }
    }
    
    public void updateLabel() {
        synchronized (this.byte) {
            this.char();
        }
    }
    
    public void sendError() {
        synchronized (this.byte) {
            this.case();
        }
    }
    
    public String addMarker(final String s, final String s2, final String s3, final String s4, final String s5) {
        synchronized (this.byte) {
            return this.a(s, s2, s3, s4, s5);
        }
    }
    
    public String addMarkerBySid(final String s, final String s2, final String s3, final String s4, final String s5) {
        synchronized (this.byte) {
            return this.if(s, s2, s3, s4, s5);
        }
    }
    
    public void removeAllMarkers() {
        synchronized (this.byte) {
            this.new();
        }
    }
    
    public void removeMarker(final String s) {
        synchronized (this.byte) {
            this.for(s);
        }
    }
    
    private void a(final String s, final String s2) {
        if (!this.char) {
            if (s == null || s.equalsIgnoreCase("0")) {
                if (this.null != "" && this.null != null) {
                    final String new1 = this.byte.new();
                    this.byte.c();
                    this.byte.for(new1);
                    this.byte.g();
                    this.if(this.null);
                }
                else {
                    final String new2 = this.byte.new();
                    this.byte.c();
                    this.byte.for(new2);
                    this.void[0].if("Price");
                    this.void[0].j.h();
                    this.innerRefreshAllCharts();
                }
            }
            else {
                StringTokenizer stringTokenizer = null;
                final StringTokenizer stringTokenizer2 = new StringTokenizer(s, " ,");
                if (s2 != null) {
                    stringTokenizer = new StringTokenizer(s2, " ,");
                }
                int countTokens = stringTokenizer2.countTokens();
                if (countTokens > 7) {
                    countTokens = 7;
                }
                final String d = this.byte.d();
                this.byte.c();
                this.byte.byte(d);
                for (int i = 1; i <= countTokens; ++i) {
                    final String nextToken = stringTokenizer2.nextToken();
                    this.byte.null(nextToken);
                    if (s2 != null) {
                        this.byte.h();
                        final String case1 = this.byte.case(nextToken);
                        if (case1 != null) {
                            this.byte.a(case1, stringTokenizer.nextToken());
                        }
                    }
                }
                if (this.null != "" && this.null != null) {
                    this.byte.null(this.null.toUpperCase());
                }
                this.void[0].if("Compare");
                this.byte.g();
                if (!this.byte.h()) {
                    this.void[0].a(this.byte.m());
                }
                this.if.if(true);
                this.innerRefreshAllCharts();
            }
            this.char();
        }
    }
    
    private void if(final String s, final String s2) {
        if (!this.char) {
            if (s == null || s.equalsIgnoreCase("0")) {
                if (this.null != "" && this.null != null) {
                    final String d = this.byte.d();
                    this.byte.int(d);
                    this.byte.c();
                    this.byte.byte(d);
                    this.byte.g();
                    this.a(this.null);
                }
                else {
                    final String d2 = this.byte.d();
                    this.byte.int(d2);
                    this.byte.c();
                    this.byte.byte(d2);
                    this.void[0].if("Price");
                    this.void[0].j.h();
                    this.innerRefreshAllCharts();
                }
            }
            else {
                StringTokenizer stringTokenizer = null;
                final StringTokenizer stringTokenizer2 = new StringTokenizer(s, " ,");
                if (s2 != null) {
                    stringTokenizer = new StringTokenizer(s2, " ,");
                }
                int countTokens = stringTokenizer2.countTokens();
                if (countTokens > 7) {
                    countTokens = 7;
                }
                final String d3 = this.byte.d();
                this.byte.int(d3);
                this.byte.c();
                this.byte.byte(d3);
                for (int i = 1; i <= countTokens; ++i) {
                    final String nextToken = stringTokenizer2.nextToken();
                    this.byte.try(nextToken);
                    if (s2 != null) {
                        this.byte.a(nextToken, stringTokenizer.nextToken());
                    }
                }
                if (this.null != "" && this.null != null) {
                    this.byte.try(this.null);
                }
                this.void[0].if("Compare");
                this.byte.g();
                if (!this.byte.h()) {
                    this.void[0].a(this.byte.m());
                }
                this.if.if(true);
                this.innerRefreshAllCharts();
            }
            this.char();
        }
    }
    
    private void do(final String null) {
        if (!this.char) {
            if (null.equalsIgnoreCase("0")) {
                if (this.null != "" && this.null != null) {
                    if (!this.null.equalsIgnoreCase(this.byte.new())) {
                        final String case1 = this.byte.case(this.null);
                        this.byte.int(case1);
                        this.byte.else(case1);
                    }
                    this.null = "";
                    if (this.byte.int() > 1) {
                        this.void[0].if("Compare");
                        this.void[0].a(this.byte.m());
                        this.byte.h();
                        this.innerRefreshAllCharts();
                    }
                    else {
                        this.void[0].if("Price");
                        this.byte.h();
                        this.innerRefreshAllCharts();
                    }
                }
            }
            else {
                if (null != this.null && "" != this.null && !this.null.equalsIgnoreCase(this.byte.new())) {
                    this.byte.else(this.byte.case(this.null));
                }
                this.null = "";
                this.if(null);
                this.null = null;
            }
        }
    }
    
    private void if(final String s) {
        if (!this.char) {
            this.byte.null(s);
            this.void[0].if("Compare");
            this.byte.h();
            this.void[0].a(this.byte.m());
            this.if.if(true);
            this.innerRefreshAllCharts();
        }
    }
    
    private void a(final String s) {
        if (!this.char) {
            this.byte.try(s);
            this.void[0].if("Compare");
            this.byte.h();
            this.void[0].a(this.byte.m());
            this.if.if(true);
            this.innerRefreshAllCharts();
        }
    }
    
    private void if(final String s, final int n) {
        if (!this.char) {
            if (s.equalsIgnoreCase("none")) {
                this.void[n].if();
            }
            else {
                this.void[n].if(s, 0);
            }
        }
    }
    
    private void a(final String s, final int n) {
        if (!this.char) {
            this.void[n].a(s);
            this.void[n].new();
        }
    }
    
    private void for(final String for1, final String s) {
        if (!this.char) {
            final String case1 = this.byte.case(for1);
            if (!case1.equals("0")) {
                this.do(case1, s);
            }
            else {
                this.long.a();
                this.byte.int(this.byte.d());
                if (for1 != null) {
                    if (!for1.equalsIgnoreCase("0")) {
                        this.byte.if(for1.toUpperCase(), s);
                    }
                }
                this.byte.if(this.for);
                if (this.byte.h()) {
                    this.if.if(true);
                    this.a = false;
                    for (int i = 0; i < this.void.length; ++i) {
                        this.void[i].new();
                    }
                    this.char();
                }
                else {
                    this.for = for1;
                    this.if.if(false);
                    this.a = true;
                    this.do();
                    this.void[0].a("Error: Symbol Cannot Be Found", true, false);
                    this.goto = "Error: Symbol Cannot Be Found";
                }
            }
        }
    }
    
    private void do(final String for1, final String s) {
        if (!this.char) {
            this.long.a();
            this.byte.int(this.byte.d());
            this.byte.do(for1, s);
            this.byte.if(this.for);
            if (this.byte.h()) {
                this.if.if(true);
                this.a = false;
                for (int i = 0; i < this.void.length; ++i) {
                    this.void[i].new();
                }
                this.char();
            }
            else {
                this.for = for1;
                this.if.if(false);
                this.a = true;
                this.do();
                this.void[0].a("Error: Sid Cannot Be Found", true, false);
                this.goto = "Error: Sid Cannot Be Found";
            }
        }
    }
    
    private void a(final String s, final int n, final int n2) {
        if (!this.char) {
            if (s.equalsIgnoreCase("None")) {
                this.void[n].a("SMA");
                this.void[n].a("EMA");
                this.void[n].new();
            }
            else {
                this.byte.a(n2);
                this.void[n].if(s, n2);
            }
        }
    }
    
    private void new(final int n) {
        if (this.byte.do(n)) {
            this.if.if(true);
            this.char = false;
            if (this.byte.h()) {
                this.innerRefreshAllCharts();
                this.char();
            }
            else {
                this.if.if(false);
                this.char = true;
                this.do();
                if (this.innerIsHistorical()) {
                    this.void[0].a("Error: Historical data not available", true, false);
                    this.goto = "Error: Historical data not available";
                }
                else {
                    this.void[0].a("Error: Intraday data not available", true, false);
                    this.goto = "Error: Intraday data not available";
                }
            }
        }
        else {
            this.if.if(false);
            this.char = true;
            this.do();
            this.case();
        }
    }
    
    private int a() {
        return this.byte.f();
    }
    
    private int try() {
        return this.byte.goto();
    }
    
    private void a(final int n) {
        if (this.byte.if(n)) {
            this.if.if(true);
            this.char = false;
            if (this.byte.h()) {
                this.innerRefreshAllCharts();
                this.char();
            }
            else {
                this.if.if(false);
                this.char = true;
                this.do();
                if (this.innerIsHistorical()) {
                    this.void[0].a("Error: Historical data not available", true, false);
                    this.goto = "Error: Historical data not available";
                }
                else {
                    this.void[0].a("Error: Intraday data not available", true, false);
                    this.goto = "Error: Intraday data not available";
                }
            }
        }
        else {
            this.if.if(false);
            this.char = true;
            this.do();
            this.case();
        }
    }
    
    public boolean innerIsError() {
        return this.a || this.char;
    }
    
    private void byte() {
        if (!this.char) {
            this.else.a(this.byte.d());
            this.innerRefreshAllCharts();
        }
    }
    
    private void if(final int n) {
        if (!this.char) {
            if (n == 1) {
                this.do(0);
                this.if.a(true);
            }
            else {
                this.do(1);
                this.if.a(false);
            }
            this.innerRefreshAllCharts();
        }
    }
    
    private void do(final int n) {
        for (int length = this.void.length, i = 0; i < length; ++i) {
            this.void[i].a(n);
        }
    }
    
    private void int(final int n) {
        if (!this.char) {
            if (n == 1 && !this.void[0].char()) {
                this.void[0].a(true);
                this.void[0].new();
            }
            else {
                this.void[0].a(false);
                this.void[0].new();
            }
        }
    }
    
    private void for(final int n) {
        if (!this.char) {
            JAVACharter.c.d.a(n);
            this.innerRefreshAllCharts();
        }
    }
    
    private void int() {
        this.byte.a();
        this.byte.h();
        this.innerRefreshAllCharts();
        this.char();
    }
    
    private void if() {
        this.byte.char();
        this.byte.h();
        this.innerRefreshAllCharts();
        this.char();
    }
    
    private void for() {
        this.byte.for();
        this.byte.h();
        this.innerRefreshAllCharts();
        this.char();
    }
    
    public boolean innerIsHistorical() {
        return this.byte.try();
    }
    
    private void char() {
        this.long.a(this.byte.null(), this.byte.j(), this.byte.e());
    }
    
    private void case() {
        String s = "";
        switch (this.try()) {
            case 6:
            case 7:
            case 8:
            case 9: {}
        }
        switch (this.a()) {
            case 1:
            case 2:
            case 3:
            case 4: {
                s = "An Intraday";
                break;
            }
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15: {
                s = "A Non-Intraday";
                break;
            }
        }
        this.void[0].a("The Time Frame Selected Requires " + s + " Frequency.", true, false);
        this.goto = "The Time Frame Selected Requires " + s + " Frequency.";
    }
    
    private String a(final String s, final String s2, final String s3, final String s4, final String s5) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(s3);
        Date parse;
        try {
            parse = simpleDateFormat.parse(s2);
        }
        catch (ParseException ex) {
            System.out.println("Invalid format string, using system default");
            parse = new Date(s2);
        }
        String case1;
        if (s.equals("*")) {
            case1 = "0";
        }
        else {
            case1 = this.byte.case(s.toUpperCase());
            if (case1.equals("0")) {
                return null;
            }
        }
        System.out.println("Adding marker for " + case1 + " at " + parse + " style: " + s4 + "Label: " + s5);
        return this.byte.byte().a(case1, new JAVACharter.b.a(parse, this.long.new(s4), s5));
    }
    
    private String if(final String s, final String s2, final String s3, final String s4, final String s5) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(s3);
        Date parse;
        try {
            parse = simpleDateFormat.parse(s2);
        }
        catch (ParseException ex) {
            System.out.println("Invalid format string, using system default");
            parse = new Date(s2);
        }
        return this.byte.byte().a(s, new JAVACharter.b.a(parse, this.long.new(s4), s5));
    }
    
    private void new() {
        this.byte.byte().a();
    }
    
    private void for(final String s) {
        this.byte.byte().a(s);
    }
    
    public void innerSetAnchor(final String s, final int n) {
        if (!this.char) {
            this.void[n].if(s);
            this.byte.h();
            this.innerRefreshAllCharts();
            this.char();
        }
    }
    
    public void innerShowLastError() {
        this.void[0].a(this.goto, true, false);
    }
    
    private void do() {
        for (int i = 0; i < this.try.new; ++i) {
            this.void[i].try();
        }
    }
    
    public void innerRefreshAllCharts() {
        if (!this.char) {
            for (int i = 0; i < this.try.new; ++i) {
                this.void[i].new();
            }
            this.char();
        }
    }
}
