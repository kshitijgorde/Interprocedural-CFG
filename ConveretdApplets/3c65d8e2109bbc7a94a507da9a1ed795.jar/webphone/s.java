// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

public class s
{
    public String e;
    public String n;
    public String l;
    public String for;
    public String a;
    public String long;
    public String g;
    public String b;
    public String h;
    public String void;
    public String goto;
    public String case;
    public String try;
    public String d;
    public String do;
    public String new;
    public String j;
    public String else;
    public String k;
    public String int;
    public String byte;
    public String q;
    public String c;
    public int m;
    public int if;
    public int o;
    public boolean i;
    public boolean p;
    aw f;
    bc char;
    
    public s(final bc char1) {
        this.e = "";
        this.n = "";
        this.l = "";
        this.for = "";
        this.a = "";
        this.long = "";
        this.g = "";
        this.b = "";
        this.h = "";
        this.void = "";
        this.goto = "";
        this.case = "";
        this.try = "";
        this.d = "";
        this.do = "";
        this.new = "";
        this.j = "";
        this.else = "";
        this.k = "";
        this.int = "";
        this.byte = "";
        this.q = "";
        this.c = "";
        this.m = 0;
        this.if = -1;
        this.o = 0;
        this.i = false;
        this.p = false;
        this.f = null;
        this.char = null;
        this.char = char1;
        this.f = this.char.f;
        this.m = 0;
        this.if = 0;
    }
    
    public void if() {
        try {
            this.e = "";
            this.n = "";
            this.l = "";
            this.for = "";
            this.a = "";
            this.long = "";
            this.d = "";
            this.do = "";
            this.new = "";
            this.j = "";
            this.else = "";
            this.void = "";
            this.goto = "";
            this.case = "";
            this.q = "";
            this.g = "";
            this.b = "";
            this.h = "";
            this.c = "";
            this.k = "";
            this.int = "";
            this.byte = "";
            this.m = -1;
            this.if = -1;
            this.i = false;
            this.p = false;
        }
        catch (Exception ex) {
            this.f.if("clear message", ex);
        }
    }
    
    public String do(String s) {
        try {
            if (s == null || s.length() < 1) {
                return "";
            }
            if (s.indexOf("<") >= 0) {
                s = s.substring(s.indexOf("<") + 1);
            }
            if (s.indexOf(">") >= 0) {
                s = s.substring(0, s.indexOf(">"));
            }
            if (s.indexOf("@") >= 0) {
                s = s.substring(0, s.indexOf("@"));
            }
            if (s.indexOf("sip:") >= 0) {
                s = s.substring(s.indexOf("sip:") + 4);
            }
            if (s.indexOf(";") >= 0) {
                s = s.substring(0, s.indexOf(";"));
            }
            if (s.indexOf("?") >= 0) {
                s = s.substring(0, s.indexOf("?"));
            }
        }
        catch (Exception ex) {
            this.f.if("UsernameFromURI", ex);
        }
        return s;
    }
    
    public String a(String s) {
        try {
            if (s == null || s.length() < 1) {
                return "";
            }
            if (s.indexOf("<") < 0) {
                return "";
            }
            s = s.substring(0, s.indexOf("<"));
            if (s.indexOf("\"") < 0) {
                return "";
            }
            s = s.substring(s.indexOf("\"") + 1);
            if (s.indexOf("\"") >= 0) {
                s = s.substring(0, s.indexOf("\""));
                if (s.indexOf("sip:") >= 0) {
                    s = s.substring(s.indexOf("sip:") + 4);
                }
                return s;
            }
            return "";
        }
        catch (Exception ex) {
            this.f.if("DisplayNameFromURI", ex);
            return "";
        }
    }
    
    public String for(String s) {
        try {
            if (s == null || s.length() < 1) {
                return "";
            }
            if (s.indexOf("<") >= 0) {
                s = s.substring(s.indexOf("<") + 1);
            }
            if (s.indexOf(">") >= 0) {
                s = s.substring(0, s.indexOf(">"));
            }
            if (s.indexOf("sip:") >= 0) {
                s = s.substring(s.indexOf("sip:") + 4);
            }
            if (s.indexOf("@") >= 0) {
                s = s.substring(s.indexOf("@") + 1);
            }
            if (s.indexOf(";") >= 0) {
                s = s.substring(0, s.indexOf(";"));
            }
            return s.trim();
        }
        catch (Exception ex) {
            this.f.if("UsernameFromURI", ex);
            return "";
        }
    }
    
    public String if(String s) {
        try {
            if (s == null || s.length() < 1) {
                return "";
            }
            if (s.indexOf("<") >= 0) {
                s = s.substring(s.indexOf("<") + 1);
            }
            if (s.indexOf(">") >= 0) {
                s = s.substring(0, s.indexOf(">"));
            }
            if (s.indexOf("sip:") >= 0) {
                s = s.substring(s.indexOf("sip:") + 4);
            }
            if (s.indexOf("@") >= 0) {
                s = s.substring(s.indexOf("@") + 1);
            }
            if (s.indexOf(";") >= 0) {
                s = s.substring(0, s.indexOf(";"));
            }
            if (s.indexOf(":") >= 0) {
                s = s.substring(0, s.indexOf(":"));
            }
            return this.f.char(s.trim());
        }
        catch (Exception ex) {
            this.f.if("UsernameFromURI", ex);
            return "";
        }
    }
    
    public int int(String s) {
        try {
            if (s == null || s.length() < 1) {
                return 5060;
            }
            if (s.indexOf("<") >= 0) {
                s = s.substring(s.indexOf("<") + 1);
            }
            if (s.indexOf(">") >= 0) {
                s = s.substring(0, s.indexOf(">"));
            }
            if (s.indexOf("sip:") >= 0) {
                s = s.substring(s.indexOf("sip:") + 4);
            }
            if (s.indexOf("@") >= 0) {
                s = s.substring(s.indexOf("@") + 1);
            }
            if (s.indexOf(";") >= 0) {
                s = s.substring(0, s.indexOf(";"));
            }
            if (s.indexOf(":") >= 0) {
                s = s.substring(s.indexOf(":") + 1);
            }
            return this.f.for(s.trim(), 5060);
        }
        catch (Exception ex) {
            this.f.if("UsernameFromURI", ex);
            return 5060;
        }
    }
    
    public void a(final s s) {
        try {
            if (s == null) {
                return;
            }
            if (this.if >= 0) {
                s.if = this.if;
            }
            if (this.m > 0) {
                s.m = this.m;
            }
            if (this.h.length() > 0) {
                s.h = this.h;
            }
            if (this.e.length() > 0) {
                s.e = this.e;
            }
            if (this.l.length() > 0) {
                s.l = this.l;
            }
            if (this.n.length() > 0) {
                s.n = this.n;
            }
            if (this.for.length() > 0) {
                s.for = this.for;
            }
            if (this.a.length() > 0) {
                s.a = this.a;
            }
            if (this.long.length() > 0) {
                s.long = this.long;
            }
            if (this.d.length() > 0) {
                s.d = this.d;
            }
            if (this.do.length() > 0) {
                s.do = this.do;
            }
            s.new = this.new;
            s.j = this.j;
            if (this.else.length() > 0) {
                s.else = this.else;
            }
            if (this.g.length() > 0) {
                s.g = this.g;
            }
            s.b = this.b;
            if (this.goto.length() > 0) {
                s.goto = this.goto;
            }
            if (this.void.length() > 0) {
                s.void = this.void;
            }
            if (this.case.length() > 0) {
                s.case = this.case;
            }
            if (this.q.length() > 0) {
                s.q = this.q;
            }
            if (this.c.length() > 0) {
                s.c = this.c;
            }
            if (this.try.length() > 0) {
                s.try = this.try;
            }
            if (this.k.length() > 0) {
                s.k = this.k;
            }
            if (this.int.length() > 0) {
                s.int = this.int;
            }
            s.byte = this.byte;
            if (this.o > 0) {
                s.o = this.o;
            }
            s.i = this.i;
            s.p = this.p;
        }
        catch (Exception ex) {
            this.f.if("copy message", ex);
        }
    }
    
    public boolean a(String string, final String s, final int n) {
        try {
            this.byte = "";
            this.e = string;
            this.if = -1;
            string = string.trim() + "\r\n";
            if (string.length() < 17) {
                return false;
            }
            final String lowerCase = string.toLowerCase();
            if (lowerCase.indexOf("sip/") == 0) {
                this.if = Integer.parseInt(lowerCase.substring(7, 11).trim());
            }
            else if (lowerCase.indexOf("invite ") == 0) {
                final aw f = this.f;
                this.if = 1;
            }
            else if (lowerCase.indexOf("register ") == 0) {
                final aw f2 = this.f;
                this.if = 0;
            }
            else if (lowerCase.indexOf("options ") == 0) {
                final aw f3 = this.f;
                this.if = 2;
            }
            else if (lowerCase.indexOf("ping ") == 0) {
                final aw f4 = this.f;
                this.if = 3;
            }
            else if (lowerCase.indexOf("bye ") == 0) {
                final aw f5 = this.f;
                this.if = 4;
            }
            else if (lowerCase.indexOf("cancel ") == 0) {
                final aw f6 = this.f;
                this.if = 5;
            }
            else if (lowerCase.indexOf("message ") == 0) {
                final aw f7 = this.f;
                this.if = 20;
            }
            else if (lowerCase.indexOf("prack ") == 0) {
                final aw f8 = this.f;
                this.if = 7;
            }
            else if (lowerCase.indexOf("refer ") == 0) {
                final aw f9 = this.f;
                this.if = 9;
            }
            else if (lowerCase.indexOf("subscribe ") == 0) {
                final aw f10 = this.f;
                this.if = 11;
            }
            else if (lowerCase.indexOf("notify ") == 0) {
                final aw f11 = this.f;
                this.if = 13;
            }
            else if (lowerCase.indexOf("publish ") == 0) {
                final aw f12 = this.f;
                this.if = 16;
            }
            else if (lowerCase.indexOf("info ") == 0) {
                final aw f13 = this.f;
                this.if = 17;
            }
            else if (lowerCase.indexOf("update ") == 0) {
                final aw f14 = this.f;
                this.if = 18;
            }
            else if (lowerCase.indexOf("do ") == 0) {
                final aw f15 = this.f;
                this.if = 19;
            }
            else if (lowerCase.indexOf("ack ") == 0) {
                final aw f16 = this.f;
                this.if = 6;
            }
            else if (lowerCase.indexOf("licdpwn ") == 0) {
                final aw f17 = this.f;
                this.if = 23;
            }
            else {
                final aw f18 = this.f;
                this.if = 25;
            }
            this.h = string.substring(0, lowerCase.indexOf("\r")).trim();
            final int index = lowerCase.indexOf("\ncall-id:");
            if (index > 0) {
                this.n = string.substring(index + 9, lowerCase.indexOf("\r", index + 9)).trim();
            }
            final int index2 = lowerCase.indexOf("\nfrom:");
            if (index2 > 0) {
                this.for = string.substring(index2 + 6, lowerCase.indexOf("\r", index2 + 6)).trim();
            }
            final int index3 = lowerCase.indexOf("\nserver:");
            if (index3 > 0) {
                this.a = string.substring(index3 + 8, lowerCase.indexOf("\r", index3 + 6)).trim();
            }
            final int index4 = lowerCase.indexOf("\ncontact:");
            if (index4 > 0) {
                this.g = string.substring(index4 + 9, lowerCase.indexOf("\r", index4 + 9)).trim();
                final int index5 = this.g.indexOf("<");
                if (index5 >= 0) {
                    this.g = this.g.substring(index5 + 1).trim();
                }
                final int index6 = this.g.indexOf(">");
                if (index6 > 0) {
                    this.g = this.g.substring(0, index6).trim();
                }
                this.b = this.g;
            }
            final int index7 = lowerCase.indexOf("\nproxy-authenticate:");
            if (index7 > 0) {
                this.try = string.substring(index7 + 20, lowerCase.indexOf("\r", index7 + 10)).trim();
            }
            final int index8 = lowerCase.indexOf("\nwww-authenticate:");
            if (index8 > 0) {
                this.try = string.substring(index8 + 18, lowerCase.indexOf("\r", index8 + 10)).trim();
            }
            final int index9 = lowerCase.indexOf("\nto:");
            if (index9 > 0) {
                this.long = string.substring(index9 + 4, lowerCase.indexOf("\r", index9 + 4)).trim();
            }
            final int index10 = lowerCase.indexOf("\nrequire:");
            if (index10 > 0) {
                this.new = string.substring(index10 + 9, lowerCase.indexOf("\r", index10 + 9)).trim();
            }
            final int index11 = lowerCase.indexOf("\nreplaces:");
            if (index11 > 0) {
                this.j = string.substring(index11 + 10, lowerCase.indexOf("\r", index11 + 10)).trim();
                final int index12 = this.j.indexOf(";");
                if (index12 >= 0) {
                    this.j = this.j.substring(0, index12).trim();
                }
            }
            final int index13 = lowerCase.indexOf("\nrseq:");
            if (index13 > 0) {
                this.else = string.substring(index13 + 6, lowerCase.indexOf("\r", index13 + 6)).trim();
                final int index14 = this.else.indexOf(" ");
                if (index14 >= 0) {
                    this.else = this.else.substring(0, index14).trim();
                }
            }
            final int index15 = lowerCase.indexOf("\nrefer-to:");
            if (index15 > 0) {
                this.d = this.do(string.substring(index15 + 10, lowerCase.indexOf("\r", index15 + 10)).trim());
                if (this.f.k) {
                    int n2 = this.d.toLowerCase().indexOf("replaces=");
                    if (n2 < 1) {
                        n2 = this.d.toLowerCase().indexOf("replaces:");
                    }
                    if (n2 > 0) {
                        this.f.dL = string.substring(n2 + 9, this.d.toLowerCase().indexOf("\r", n2 + 10)).trim();
                        final int index16 = this.f.dL.indexOf(";");
                        if (index16 >= 0) {
                            this.f.dL = this.f.dL.substring(0, index16).trim();
                        }
                        final int index17 = this.f.dL.indexOf(">");
                        if (index17 >= 0) {
                            this.f.dL = this.f.dL.substring(0, index17).trim();
                        }
                        if (this.f.dL.length() > 0) {
                            this.f.ec = this.f.do();
                        }
                        final int index18 = this.d.toLowerCase().indexOf("to-tag=");
                        if (index18 >= 0) {
                            this.f.ej = string.substring(index18 + 7, this.d.toLowerCase().indexOf("\r", index18 + 10)).trim();
                        }
                        final int index19 = this.f.ej.indexOf(";");
                        if (index19 >= 0) {
                            this.f.ej = this.f.ej.substring(0, index19).trim();
                        }
                        final int index20 = this.f.ej.indexOf(">");
                        if (index20 >= 0) {
                            this.f.ej = this.f.ej.substring(0, index20).trim();
                        }
                        final int index21 = this.d.toLowerCase().indexOf("from-tag=");
                        if (index21 >= 0) {
                            this.f.cx = string.substring(index21 + 9, this.d.toLowerCase().indexOf("\r", index21 + 10)).trim();
                        }
                        final int index22 = this.f.cx.indexOf(";");
                        if (index22 >= 0) {
                            this.f.cx = this.f.cx.substring(0, index22).trim();
                        }
                        final int index23 = this.f.cx.indexOf(">");
                        if (index23 >= 0) {
                            this.f.cx = this.f.cx.substring(0, index23).trim();
                        }
                    }
                }
            }
            final int index24 = lowerCase.indexOf("\nreferred-by:");
            if (index24 > 0) {
                this.do = string.substring(index24 + 13, lowerCase.indexOf("\r", index24 + 13)).trim();
            }
            final int index25 = lowerCase.indexOf("\ncseq:");
            if (index25 > 0) {
                this.goto = string.substring(index25 + 4, lowerCase.indexOf("\r", index25 + 1)).trim();
                this.void = string.substring(index25 + 6, lowerCase.indexOf("\r", index25 + 6)).trim();
                final int index26 = this.void.indexOf(" ");
                if (index26 > 0) {
                    this.void = this.void.substring(0, index26);
                }
                this.void = this.void.trim();
            }
            if (this.f.E > 30) {
                int n3 = lowerCase.indexOf("\ncredit:");
                if (n3 < 1) {
                    n3 = lowerCase.indexOf("\ncredit-amount:");
                }
                if (n3 < 1) {
                    n3 = lowerCase.indexOf("\nportabilling:");
                }
                if (n3 > 0) {
                    final String trim = string.substring(n3 + 1, lowerCase.indexOf("\r", n3 + 2)).trim().trim();
                    if (trim.length() > 0 && (!trim.equals(this.f.co) || this.f.do() - this.f.cN > 180000L)) {
                        this.f.a(1, "EVENT," + trim);
                        this.f.co = trim;
                        this.f.cN = this.f.do();
                        if (this.f.u > 1) {
                            this.f.g("EVENT,CREDIT," + string);
                        }
                    }
                    else {
                        this.f.a(4, "EVENT," + trim);
                    }
                }
            }
            int n4 = 0;
            for (int i = 0; i < 10; ++i) {
                final int index27 = lowerCase.indexOf("\nrecord-route:", n4);
                if (index27 <= 0) {
                    break;
                }
                n4 = index27 + 2;
                String s2 = string.substring(index27 + 14, lowerCase.indexOf("\r", index27 + 2)).trim();
                final int index28 = s2.indexOf("<");
                if (index28 >= 0) {
                    s2 = s2.substring(index28 + 1).trim();
                }
                final int index29 = s2.indexOf(">");
                if (index29 > 0) {
                    s2 = s2.substring(0, index29).trim();
                }
                if (s2.length() > 1) {
                    if (this.l.length() < 1) {
                        this.l = "<" + s2 + ">";
                    }
                    else {
                        this.l = "<" + s2 + ">," + this.l;
                    }
                }
            }
            final int index30 = lowerCase.indexOf("\nc=in ip4");
            if (index30 > 0) {
                this.p = false;
                this.i = false;
                this.c = string.substring(index30 + 9, lowerCase.indexOf("\r", index30 + 9)).trim();
            }
            final int index31 = lowerCase.indexOf("\nm=audio");
            if (index31 > 0) {
                String s3 = string.substring(index31 + 8, lowerCase.indexOf("\r", index31 + 8)).trim();
                final int index32 = s3.indexOf("RTP/AVP");
                if (index32 > 0) {
                    this.q = " " + s3.substring(index32 + 4) + " ";
                }
                this.o = 0;
                String string2 = "";
                for (int j = 0; j < this.q.length(); ++j) {
                    if (this.q.charAt(j) == ' ') {
                        final String trim2 = string2.trim();
                        if (trim2.length() > 0) {
                            final int for1 = this.f.for(trim2, -1);
                            if (for1 >= 0 && for1 < 999 && (for1 <= 97 || for1 > 103)) {
                                ++this.o;
                            }
                        }
                        string2 = "";
                    }
                    else {
                        string2 += this.q.charAt(j);
                    }
                }
                if (s3.length() > 0) {
                    final int index33 = s3.indexOf(" ");
                    if (index33 > 0) {
                        s3 = s3.substring(0, index33).trim();
                    }
                    try {
                        this.m = Integer.valueOf(s3);
                    }
                    catch (Exception ex2) {}
                }
            }
            if (!this.i && lowerCase.indexOf("in ipv4 0.0.0.0") > 0) {
                this.i = true;
            }
            if (!this.i && lowerCase.indexOf("a=sendonly") > 0) {
                this.i = true;
            }
            if (!this.p && lowerCase.indexOf("a=recvonly") > 0) {
                this.p = true;
            }
            if (!this.i && lowerCase.indexOf("a=inactive") > 0) {
                this.i = true;
                this.p = true;
            }
            int n5 = 0;
            for (int k = 0; k < 20; ++k) {
                final int index34 = lowerCase.indexOf("\nvia:", n5);
                if (index34 <= 0) {
                    break;
                }
                n5 = index34 + 2;
                this.case = this.case + string.substring(index34 + 1, lowerCase.indexOf("\r", index34 + 5)).trim() + "\r\n";
                if (k == 0 && this.f.bJ > 0 && (this.f.bJ > 2 || this.f.n(this.f.d()))) {
                    boolean b = true;
                    if (this.f.bJ == 1 && s.length() > 0 && (this.f.n(s) || this.f.if(s, this.f.d()))) {
                        b = false;
                    }
                    if (b) {
                        final int index35 = this.case.indexOf("received=");
                        if (index35 > 0) {
                            final int index36 = this.case.indexOf(";", index35 + 5);
                            if (index36 > 0) {
                                final String char1 = this.f.char(this.case.substring(index35 + 9, index36).trim());
                                if (char1.length() > 5 && char1.length() < 18 && char1.indexOf(".") > 0 && this.f.e(char1)) {
                                    final int index37 = this.case.indexOf("rport=");
                                    if (index37 > 0) {
                                        final int index38 = this.case.indexOf(";", index37 + 3);
                                        if (index38 > 0) {
                                            final int for2 = this.f.for(this.case.substring(index37 + 6, index38), 0);
                                            if (for2 > 0 && for2 < 67000 && (!this.f.bk.equals(char1) || this.f.Y != for2) && (this.f.bJ == 2 || for2 == this.char.do())) {
                                                this.f.a(3, "EVENT,public address changed from " + this.f.d() + ":" + this.f.c(this.char.do()) + " to " + char1 + ":" + this.f.c(for2));
                                                this.f.bk = char1;
                                                this.f.Y = for2;
                                            }
                                        }
                                        else if (this.f.bJ == 2 && !this.f.bk.equals(char1)) {
                                            this.f.a(3, "EVENT,public ip changed2 from " + this.f.d() + " to " + char1);
                                            this.f.bk = char1;
                                        }
                                    }
                                    else if (this.f.bJ == 2 && !this.f.bk.equals(char1)) {
                                        this.f.a(3, "EVENT,public ip changed2 from " + this.f.d() + " to " + char1);
                                        this.f.bk = char1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            final int index39 = lowerCase.indexOf("\r\n\r\n");
            if (index39 > 5) {
                this.k = string.substring(index39 + 4).trim();
                this.int = this.k.toLowerCase();
                this.byte = this.k;
            }
            return true;
        }
        catch (Exception ex) {
            this.f.if("parse message", ex);
            return false;
        }
    }
    
    public int a() {
        int n = 1;
        try {
            final String a = this.f.a(this.int, "\nm=audio ", "\r", false);
            int n2 = 0;
            for (int i = 0; i < a.length(); ++i) {
                if (a.charAt(i) == ' ') {
                    ++n2;
                }
            }
            n = n2 - 1;
            if (a.indexOf(" " + this.f.c(this.f.fj)) >= 0) {
                --n;
            }
            if (n < 1) {
                n = 1;
            }
        }
        catch (Exception ex) {
            this.f.if("parse GetNumOfCodecs", ex);
        }
        return n;
    }
}
