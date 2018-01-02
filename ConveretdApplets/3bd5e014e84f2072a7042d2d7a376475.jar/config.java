import java.io.InputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.net.URL;
import java.io.BufferedReader;

// 
// Decompiled by Procyon v0.5.30
// 

public class config
{
    boolean verbose;
    boolean isapplet;
    String configfile;
    String section;
    public option[] opt;
    int instptr;
    imgslide a;
    
    public config(final imgslide a, final String configfile, final boolean verbose) {
        this.configfile = configfile;
        this.verbose = verbose;
        this.a = a;
        this.isapplet = imgslide.isapplet;
        this.installoptions(verbose ? "V" : "");
        if (configfile != null) {
            this.read();
        }
    }
    
    private void installoptions(final String s) {
        final option[] opt = { new option("FreeColor", "0", s, this), new option("FreeSquare", "-1", s, this), new option("GridX", "4", s, this), new option("GridY", "4", s, this), new option("Image", "", s, this), new option("OldRules", "0", s, this) };
        this.checkorder(opt);
        this.opt = opt;
    }
    
    private void checkorder(final cfgitem[] array) {
        for (int n = array.length - 1, i = 0; i < n; ++i) {
            if (this.cmp(array[i].name, array[i + 1].name) >= 0) {
                System.out.println("Bad order of cfgitems at " + array[i + 1].name);
                System.exit(-1);
            }
        }
    }
    
    private int cmp(final String s, final String s2) {
        return s.toUpperCase().compareTo(s2.toUpperCase());
    }
    
    public void set(final String s, final String s2) {
        final int findcfgitem = this.findcfgitem(this.opt, s);
        if (findcfgitem >= 0) {
            this.opt[findcfgitem].set(s2);
            this.opt[findcfgitem].print();
            return;
        }
        this.msg("Unknown option " + s, "\n");
    }
    
    public String get(final String s) {
        final int findcfgitem = this.findcfgitem(this.opt, s);
        if (findcfgitem >= 0) {
            return this.opt[findcfgitem].get();
        }
        return "";
    }
    
    public int getint(final String s) {
        final int findcfgitem = this.findcfgitem(this.opt, s);
        if (findcfgitem >= 0) {
            return this.opt[findcfgitem].getint();
        }
        return 0;
    }
    
    public int gethex(final String s) {
        final int findcfgitem = this.findcfgitem(this.opt, s);
        if (findcfgitem >= 0) {
            return this.opt[findcfgitem].gethex();
        }
        return 0;
    }
    
    public float getfloat(final String s) {
        final int findcfgitem = this.findcfgitem(this.opt, s);
        if (findcfgitem >= 0) {
            return this.opt[findcfgitem].getfloat();
        }
        return 0.0f;
    }
    
    public boolean getbool(final String s) {
        final int findcfgitem = this.findcfgitem(this.opt, s);
        return findcfgitem >= 0 && this.opt[findcfgitem].getbool();
    }
    
    int findcfgitem(final cfgitem[] array, final String s) {
        int n = -1;
        int n2 = array.length - 1;
        int n3 = 0;
        while (n == -1 && n3 <= n2) {
            final int n4 = (n3 + n2) / 2;
            final int cmp = this.cmp(s, array[n4].name);
            if (cmp == 0) {
                n = n4;
            }
            else if (cmp > 0) {
                n3 = n4 + 1;
            }
            else {
                n2 = n4 - 1;
            }
        }
        return n;
    }
    
    void read() {
        final BufferedReader open = this.open();
        if (open == null) {
            this.verbose = true;
            this.msg("Cannot open " + this.configfile, "\n");
            System.exit(-1);
            return;
        }
        this.msg("Reading " + this.configfile, "\n");
        this.section = "?";
        String line;
        do {
            try {
                line = open.readLine();
                if (line != null) {
                    this.process(line);
                }
            }
            catch (Exception ex) {
                line = null;
            }
        } while (line != null);
        try {
            open.close();
        }
        catch (Exception ex2) {}
        this.msg("Reading " + this.configfile + " complete", "\n");
    }
    
    BufferedReader open() {
        BufferedReader bufferedReader;
        try {
            InputStream openStream;
            if (this.isapplet) {
                openStream = new URL(this.a.getCodeBase(), this.configfile).openStream();
            }
            else {
                openStream = new FileInputStream(this.configfile);
            }
            bufferedReader = new BufferedReader(new InputStreamReader(openStream));
        }
        catch (Exception ex) {
            bufferedReader = null;
        }
        return bufferedReader;
    }
    
    void process(String s) {
        try {
            final int index = s.indexOf(59);
            if (index >= 0) {
                s = s.substring(0, index);
            }
            final int index2 = s.indexOf(35);
            if (index2 >= 0) {
                s = s.substring(0, index2);
            }
            s = s.trim();
            switch (s.charAt(0)) {
                case '[': {
                    final int index3 = s.indexOf(93);
                    if (index3 > 0) {
                        this.section = s.substring(1, index3).toUpperCase();
                        this.msg("Section [" + this.section + "]", "\n");
                        return;
                    }
                    break;
                }
                default: {
                    if (this.section.equals("OPTIONS")) {
                        this.processoption(s);
                        break;
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    void processoption(final String s) {
        try {
            final int index = s.indexOf(61);
            String trim;
            String trim2;
            if (index >= 0) {
                trim = s.substring(0, index).trim();
                trim2 = s.substring(index + 1, s.length()).trim();
            }
            else {
                trim = s;
                trim2 = null;
            }
            this.set(trim, trim2);
        }
        catch (Exception ex) {}
    }
    
    void msg(final String s, final String s2) {
        if (this.verbose) {
            System.out.print(String.valueOf(s) + s2);
        }
    }
}
