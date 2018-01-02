// 
// Decompiled by Procyon v0.5.30
// 

package medusa;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.awt.Color;
import java.util.Hashtable;

public class MedusaSettings
{
    Hashtable<Integer, Color> colorTable;
    Hashtable<Integer, String> interactionTable;
    Color[] colorList;
    
    public Color randomColor() {
        final int n = (int)(Math.random() * 255.0);
        final int n2 = (int)(Math.random() * 255.0);
        return new Color(n, n2, Math.min(255, 510 - n - n2));
    }
    
    public MedusaSettings() {
        this.colorTable = new Hashtable<Integer, Color>();
        this.interactionTable = new Hashtable<Integer, String>();
        this.colorList = new Color[] { new Color(0, 180, 0), new Color(255, 0, 0), new Color(0, 0, 255), new Color(140, 140, 255), new Color(30, 30, 30), new Color(200, 0, 200), new Color(0, 160, 200), new Color(170, 200, 0), this.randomColor(), this.randomColor(), this.randomColor(), this.randomColor() };
        this.init();
    }
    
    public Color getColorFromParam(final String s) {
        final String[] split = s.split(",");
        return new Color(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
    }
    
    public MedusaSettings(final String s) {
        this.colorTable = new Hashtable<Integer, Color>();
        this.interactionTable = new Hashtable<Integer, String>();
        this.colorList = new Color[] { new Color(0, 180, 0), new Color(255, 0, 0), new Color(0, 0, 255), new Color(140, 140, 255), new Color(30, 30, 30), new Color(200, 0, 200), new Color(0, 160, 200), new Color(170, 200, 0), this.randomColor(), this.randomColor(), this.randomColor(), this.randomColor() };
        if (s != null) {
            this.readParam(s);
        }
        else {
            this.init();
        }
    }
    
    public void readParam(final String s) {
        final String[] split = s.split(";");
        for (int i = 0; i < split.length; ++i) {
            final Color colorFromParam = this.getColorFromParam(split[i]);
            System.out.println(colorFromParam);
            this.colorTable.put(new Integer(i + 1), colorFromParam);
        }
    }
    
    public void clear() {
        this.colorTable.clear();
        this.interactionTable.clear();
    }
    
    public void init() {
        this.initColorTable();
        this.initInteractionTable();
    }
    
    public void initCOGS() {
        this.initColorTable();
        this.initCOGInteractionTable();
    }
    
    public void initColorTable() {
        for (int i = 0; i < this.colorList.length; ++i) {
            this.colorTable.put(new Integer(i + 1), this.colorList[i]);
        }
    }
    
    public void initInteractionTable() {
        this.interactionTable.put(new Integer(1), "Neighbourhood");
        this.interactionTable.put(new Integer(2), "Gene Fusion");
        this.interactionTable.put(new Integer(3), "Cooccurence");
        this.interactionTable.put(new Integer(4), "Homology");
        this.interactionTable.put(new Integer(5), "Coexpression");
        this.interactionTable.put(new Integer(6), "Experiments");
        this.interactionTable.put(new Integer(7), "Databases");
        this.interactionTable.put(new Integer(8), "Text Mining");
    }
    
    public void initCOGInteractionTable() {
        this.interactionTable.put(new Integer(1), "Neighbourhood");
        this.interactionTable.put(new Integer(2), "Gene Fusion");
        this.interactionTable.put(new Integer(3), "Homology");
        this.interactionTable.put(new Integer(4), "Coexpression");
        this.interactionTable.put(new Integer(5), "Experiments");
        this.interactionTable.put(new Integer(6), "Databases");
        this.interactionTable.put(new Integer(7), "Text Mining");
    }
    
    public void load(final String s) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(s))));
        final Pattern compile = Pattern.compile("(\\d+)[, ](\\d+)[, ](\\d+)");
        this.interactionTable = new Hashtable<Integer, String>();
        this.colorTable = new Hashtable<Integer, Color>();
        this.initColorTable();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            final String[] split = line.split("\\t");
            final Matcher matcher = compile.matcher(line);
            this.interactionTable.put(new Integer(split[0]), split[1]);
            if (matcher.find()) {
                this.colorTable.put(new Integer(split[0]), new Color(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3))));
            }
        }
        bufferedReader.close();
    }
    
    public int getSize() {
        return this.interactionTable.size();
    }
    
    public void report() {
        for (int i = 1; i < this.interactionTable.size(); ++i) {
            System.out.println(this.getName(new Integer(i), 0));
        }
    }
    
    public Color parseColor(final String s) {
        final String[] split = s.split(",");
        if (split.length < 3) {
            return this.randomColor();
        }
        return new Color(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
    }
    
    public Color getColor(final Integer n) {
        return this.colorTable.get(n);
    }
    
    public void setColor(final Integer n, final Color color) {
        this.colorTable.put(n, color);
    }
    
    public String getName(final Integer n, final int n2) {
        final String s = this.interactionTable.get(n);
        if (s == null) {
            return "null!?";
        }
        if (s.length() < n2 || n2 == 0) {
            return s;
        }
        return s.substring(0, n2 - 1);
    }
    
    public String getName(final Integer n) {
        final String s = this.interactionTable.get(n);
        if (s == null) {
            return "null!?";
        }
        return s;
    }
    
    public void setName(final Integer n, final String s) {
        this.interactionTable.put(n, s);
    }
}
