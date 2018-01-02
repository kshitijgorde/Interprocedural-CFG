import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class Function
{
    private String f;
    private String paramname;
    double paramvalue;
    Parameter param;
    public Vector Parameters;
    public Vector paramvalues;
    public boolean paramnew;
    public boolean error;
    public boolean prodfix;
    private double minx;
    private double maxx;
    
    public Function() {
        this.f = "0";
        this.paramname = "";
        this.paramvalue = 0.0;
        this.Parameters = new Vector();
        this.paramvalues = new Vector();
        this.paramnew = false;
        this.error = false;
        this.prodfix = true;
    }
    
    public Function(String productFix) {
        this.f = "0";
        this.paramname = "";
        this.paramvalue = 0.0;
        this.Parameters = new Vector();
        this.paramvalues = new Vector();
        this.paramnew = false;
        this.error = false;
        this.prodfix = true;
        if (this.prodfix) {
            productFix = this.productFix(productFix);
        }
        this.f = this.fixup(productFix);
    }
    
    public Function(String productFix, final Vector parameters) {
        this.f = "0";
        this.paramname = "";
        this.paramvalue = 0.0;
        this.Parameters = new Vector();
        this.paramvalues = new Vector();
        this.paramnew = false;
        this.error = false;
        this.prodfix = true;
        if (this.prodfix) {
            productFix = this.productFix(productFix);
        }
        this.f = this.fixup(productFix);
        this.Parameters = parameters;
    }
    
    public double giveyfor(final double n) {
        return this.evaluate(this.f, n);
    }
    
    public boolean errorTest() {
        this.evaluate(this.f, 0.0);
        return this.error;
    }
    
    public String getString() {
        return this.f;
    }
    
    public boolean equals(final Function function) {
        return this.f.equals(function.getString());
    }
    
    public int netBracketsIn(final String s) {
        int n = 0;
        int i = s.length();
        while (i >= 0) {
            i = Math.max(s.lastIndexOf(40, i - 1), s.lastIndexOf(41, i - 1));
            if (i >= 0) {
                if (s.charAt(i) == '(') {
                    ++n;
                }
                else {
                    --n;
                }
            }
        }
        return n;
    }
    
    public int minBrack(final String s) {
        int n = 0;
        int n2 = 0;
        int i = s.length();
        while (i >= 0) {
            i = Math.max(s.lastIndexOf(40, i - 1), s.lastIndexOf(41, i - 1));
            if (i >= 0) {
                if (s.charAt(i) == ')') {
                    ++n2;
                }
                else {
                    --n2;
                }
            }
            if (n2 < n) {
                n = n2;
            }
        }
        return n;
    }
    
    public int lastIndexInOfbare(final String s, final int n) {
        int i = s.length();
        while (i >= 0) {
            i = s.lastIndexOf(n, i - 1);
            if (i >= 0) {
                final int netBracketsIn = this.netBracketsIn(s.substring(0, i));
                if (netBracketsIn < 0) {
                    System.out.println("unmatched right bracket");
                    this.error = true;
                    return -1;
                }
                if (netBracketsIn == 0) {
                    return i;
                }
                continue;
            }
        }
        return -1;
    }
    
    public String splitStringAt(String s, final int n) {
        String fixup = "";
        if (n > 0) {
            fixup = this.fixup(s.substring(0, n));
        }
        String fixup2 = "";
        if (n > 0) {
            fixup2 = this.fixup(s.substring(n + 1));
        }
        if (s.charAt(n) == '^') {
            s = "pow((" + fixup + "),(" + fixup2 + "))";
        }
        else {
            s = "(" + fixup + ")" + s.charAt(n) + "(" + fixup2 + ")";
        }
        return s;
    }
    
    public String nameFix(String string, final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        s.getChars(0, length, array, 0);
        for (int i = 0; i < s.length(); ++i) {
            array[i] += '@';
        }
        final String s2 = new String(array);
        final StringBuffer sb = new StringBuffer(string);
        for (int j = string.indexOf(s); j != -1; j = string.indexOf(s)) {
            string = string.substring(0, j) + s2 + string.substring(j + length);
        }
        return string;
    }
    
    public String productFix(String s) {
        s = this.nameFix(s, "abs");
        s = this.nameFix(s, "ceil");
        s = this.nameFix(s, "floor");
        s = this.nameFix(s, "arcsin");
        s = this.nameFix(s, "arccos");
        s = this.nameFix(s, "arctan");
        s = this.nameFix(s, "sin");
        s = this.nameFix(s, "cos");
        s = this.nameFix(s, "tan");
        s = this.nameFix(s, "csc");
        s = this.nameFix(s, "sec");
        s = this.nameFix(s, "cot");
        s = this.nameFix(s, "exp");
        s = this.nameFix(s, "pow");
        s = this.nameFix(s, "ln");
        s = this.nameFix(s, "log");
        s = this.nameFix(s, "sqrt");
        s = this.nameFix(s, "min");
        s = this.nameFix(s, "max");
        final StringBuffer sb = new StringBuffer(s);
        int n = 0;
        final char[] array = new char[s.length()];
        s.getChars(0, s.length(), array, 0);
        if (s.length() > 1) {
            for (int i = 0; i < s.length() - 1; ++i) {
                if (array[i] < '\u0100' && (Character.isLetter(array[i + 1]) || array[i + 1] == '(' || array[i + 1] > '\u0080') && (Character.isLetterOrDigit(array[i]) || array[i] == ')')) {
                    sb.insert(i + 1 + n, "*");
                    ++n;
                }
            }
        }
        final String s2 = new String(sb);
        final char[] array2 = new char[s2.length()];
        s2.getChars(0, s2.length(), array2, 0);
        for (int j = 0; j < s2.length(); ++j) {
            if (array2[j] > '\u0080') {
                array2[j] -= '@';
            }
        }
        return new String(array2);
    }
    
    public String fixup(String s) {
        this.error = false;
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == ' ') {
                s = s.substring(0, i) + s.substring(i + 1);
            }
            else {
                ++i;
            }
        }
        if (s.startsWith("+") || s.startsWith("-")) {
            s = "0" + s;
        }
        final int lastIndexInOfbare = this.lastIndexInOfbare(s, 44);
        if (lastIndexInOfbare != -1) {
            return this.splitStringAt(s, lastIndexInOfbare);
        }
        final int max = Math.max(this.lastIndexInOfbare(s, 43), this.lastIndexInOfbare(s, 45));
        if (max != -1) {
            return this.splitStringAt(s, max);
        }
        final int max2 = Math.max(this.lastIndexInOfbare(s, 42), this.lastIndexInOfbare(s, 47));
        if (max2 != -1) {
            return this.splitStringAt(s, max2);
        }
        int j = 0;
        while (j >= 0) {
            j = s.indexOf(94, j + 1);
            if (j >= 0) {
                final int netBracketsIn = this.netBracketsIn(s.substring(0, j));
                if (netBracketsIn < 0) {
                    System.out.println("unmatched right bracket");
                    this.error = true;
                    return s;
                }
                if (netBracketsIn == 0) {
                    return this.splitStringAt(s, j);
                }
                continue;
            }
        }
        final int index = s.indexOf(40);
        if (index >= 0) {
            int n = index + 1;
            int k = 1;
            int n2 = 0;
            while (k > 0) {
                n2 = s.indexOf(41, n);
                if (n2 < 0) {
                    System.out.println("unmatched left bracket!");
                    this.error = true;
                    return s;
                }
                final int index2 = s.indexOf(40, n);
                if (index2 > 0) {
                    n2 = Math.min(index2, n2);
                }
                n = n2 + 1;
                if (s.charAt(n2) == '(') {
                    ++k;
                }
                else {
                    --k;
                }
            }
            String fixup = "";
            if (index > 0) {
                fixup = this.fixup(s.substring(0, index));
            }
            final String fixup2 = this.fixup(s.substring(index + 1, n2));
            String fixup3 = "";
            if (n2 + 2 < s.length()) {
                fixup3 = this.fixup(s.substring(n2 + 1));
            }
            s = fixup + "(" + fixup2 + ")" + fixup3;
        }
        return s;
    }
    
    public double evaluate(final String paramname, final double n) {
        String substring = "";
        String substring2 = "";
        double n2 = 0.0;
        double n3 = 0.0;
        if (paramname.equals("")) {
            this.error = true;
        }
        if (paramname.equals("x")) {
            n2 = n;
        }
        else if (paramname.length() == 1 && Character.isLetter(paramname.charAt(0))) {
            this.paramname = paramname;
            this.paramnew = true;
            if (this.Parameters.size() > 0) {
                for (int i = 0; i < this.Parameters.size(); ++i) {
                    if (i >= this.Parameters.size()) {
                        System.out.println("Java screwed up!");
                    }
                    else {
                        try {
                            this.param = (Parameter)this.Parameters.elementAt(i);
                        }
                        catch (ArrayIndexOutOfBoundsException ex) {
                            System.out.println("Parameters.size()= " + this.Parameters.size() + ", i= " + i + "\n Java screwed up?");
                        }
                    }
                    if (this.paramname.equals(this.param.name)) {
                        n2 = this.param.value;
                        this.param.active = true;
                        this.paramnew = false;
                    }
                }
            }
            if (this.paramnew) {
                this.Parameters.addElement(new Parameter(this.paramname, 0.0));
                n2 = 0.0;
            }
        }
        else {
            try {
                n2 = Double.valueOf(paramname);
            }
            catch (NumberFormatException ex2) {
                try {
                    final int index = paramname.indexOf(40);
                    int n4 = index + 1;
                    int j = 1;
                    int n5 = 0;
                    while (j > 0) {
                        n5 = paramname.indexOf(41, n4);
                        if (n5 < 0) {
                            this.error = true;
                            return 0.0;
                        }
                        final int index2 = paramname.indexOf(40, n4);
                        if (index2 > 0) {
                            n5 = Math.min(index2, n5);
                        }
                        n4 = n5 + 1;
                        if (paramname.charAt(n5) == '(') {
                            ++j;
                        }
                        else {
                            --j;
                        }
                    }
                    String substring3 = "";
                    if (index > 0) {
                        substring3 = paramname.substring(0, index);
                    }
                    final String substring4 = paramname.substring(index + 1, n5);
                    String substring5 = "";
                    if (n5 + 2 < paramname.length()) {
                        substring5 = paramname.substring(n5 + 2);
                    }
                    if (substring3.equals("")) {
                        n3 = this.evaluate(substring4, n);
                    }
                    if (substring3.equals("abs")) {
                        n3 = Math.abs(this.evaluate(substring4, n));
                    }
                    if (substring3.equals("ceil")) {
                        n3 = Math.ceil(this.evaluate(substring4, n));
                    }
                    if (substring3.equals("cos")) {
                        n3 = Math.cos(this.evaluate(substring4, n));
                    }
                    if (substring3.equals("sin")) {
                        n3 = Math.sin(this.evaluate(substring4, n));
                    }
                    if (substring3.equals("tan")) {
                        n3 = Math.tan(this.evaluate(substring4, n));
                    }
                    if (substring3.equals("sec")) {
                        n3 = 1.0 / Math.cos(this.evaluate(substring4, n));
                    }
                    if (substring3.equals("csc")) {
                        n3 = 1.0 / Math.sin(this.evaluate(substring4, n));
                    }
                    if (substring3.equals("cot")) {
                        n3 = 1.0 / Math.tan(this.evaluate(substring4, n));
                    }
                    if (substring3.equals("arccos")) {
                        n3 = Math.acos(this.evaluate(substring4, n));
                    }
                    if (substring3.equals("arcsin")) {
                        n3 = Math.asin(this.evaluate(substring4, n));
                    }
                    if (substring3.equals("arctan")) {
                        n3 = Math.atan(this.evaluate(substring4, n));
                    }
                    if (substring3.equals("exp") && this.lastIndexInOfbare(substring4, 44) == -1) {
                        n3 = Math.exp(this.evaluate(substring4, n));
                    }
                    if (substring3.equals("ln")) {
                        n3 = Math.log(this.evaluate(substring4, n));
                    }
                    if (substring3.equals("log") && this.lastIndexInOfbare(substring4, 44) == -1) {
                        n3 = Math.log(this.evaluate(substring4, n)) / Math.log(10.0);
                    }
                    if (substring3.equals("sqrt")) {
                        n3 = Math.sqrt(this.evaluate(substring4, n));
                    }
                    if (substring3.equals("floor")) {
                        n3 = Math.floor(this.evaluate(substring4, n));
                    }
                    if (this.lastIndexInOfbare(substring4, 44) != -1) {
                        if (substring3.equals("max") || substring3.equals("min") || substring3.equals("pow") || substring3.equals("exp") || substring3.equals("log")) {
                            final int lastIndexInOfbare = this.lastIndexInOfbare(substring4, 44);
                            substring = substring4.substring(0, lastIndexInOfbare);
                            substring2 = substring4.substring(lastIndexInOfbare + 1);
                        }
                        if (substring3.equals("max")) {
                            n3 = Math.max(this.evaluate(substring, n), this.evaluate(substring2, n));
                        }
                        if (substring3.equals("min")) {
                            n3 = Math.min(this.evaluate(substring, n), this.evaluate(substring2, n));
                        }
                        if (substring3.equals("pow")) {
                            n3 = Math.pow(this.evaluate(substring, n), this.evaluate(substring2, n));
                        }
                        if (substring3.equals("exp")) {
                            n3 = Math.pow(this.evaluate(substring, n), this.evaluate(substring2, n));
                        }
                        if (substring3.equals("log")) {
                            n3 = Math.log(this.evaluate(substring2, n)) / Math.log(this.evaluate(substring, n));
                        }
                    }
                    if (n5 + 1 < paramname.length()) {
                        final char char1 = paramname.charAt(n5 + 1);
                        final double evaluate = this.evaluate(substring5, n);
                        switch (char1) {
                            case 43: {
                                n2 = n3 + evaluate;
                                break;
                            }
                            case 45: {
                                n2 = n3 - evaluate;
                                break;
                            }
                            case 42: {
                                n2 = n3 * evaluate;
                                break;
                            }
                            case 47: {
                                n2 = n3 / evaluate;
                                break;
                            }
                            default: {
                                this.quit();
                                break;
                            }
                        }
                    }
                    else {
                        n2 = n3;
                    }
                }
                catch (StringIndexOutOfBoundsException ex3) {
                    this.error = true;
                    return 0.0;
                }
            }
        }
        return n2;
    }
    
    public void quit() {
    }
}
