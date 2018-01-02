// 
// Decompiled by Procyon v0.5.30
// 

package mechanalyst;

import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;

public class MainBrain extends Thread
{
    final boolean echoInput = false;
    final boolean printData = false;
    final boolean printKeys = false;
    final boolean printSyns = false;
    final boolean printPrePost = false;
    final boolean printInitialFinal = false;
    KeyList keys;
    SynList syns;
    PrePostList pre;
    PrePostList post;
    String initial;
    String finl;
    WordList quit;
    KeyStack keyStack;
    Mem mem;
    DecompList lastDecomp;
    ReasembList lastReasemb;
    boolean finished;
    static final int success = 0;
    static final int failure = 1;
    static final int gotoRule = 2;
    
    public void run() {
    }
    
    public boolean finished() {
        return this.finished;
    }
    
    public void collect(String s) {
        final String[] array = new String[4];
        if (EString.match(s, "*reasmb: *", array)) {
            if (this.lastReasemb == null) {
                System.out.println("Error: no last reasemb");
                return;
            }
            this.lastReasemb.add(array[1]);
        }
        else if (EString.match(s, "*decomp: *", array)) {
            if (this.lastDecomp == null) {
                System.out.println("Error: no last decomp");
                return;
            }
            this.lastReasemb = new ReasembList();
            final String s2 = new String(array[1]);
            if (EString.match(s2, "$ *", array)) {
                this.lastDecomp.add(array[0], true, this.lastReasemb);
                return;
            }
            this.lastDecomp.add(s2, false, this.lastReasemb);
        }
        else {
            if (EString.match(s, "*key: * #*", array)) {
                this.lastDecomp = new DecompList();
                this.lastReasemb = null;
                int int1 = 0;
                if (array[2].length() != 0) {
                    try {
                        int1 = Integer.parseInt(array[2]);
                    }
                    catch (NumberFormatException ex) {
                        System.out.println("Number is wrong in key: " + array[2]);
                    }
                }
                this.keys.add(array[1], int1, this.lastDecomp);
                return;
            }
            if (EString.match(s, "*key: *", array)) {
                this.lastDecomp = new DecompList();
                this.lastReasemb = null;
                this.keys.add(array[1], 0, this.lastDecomp);
                return;
            }
            if (EString.match(s, "*synon: * *", array)) {
                final WordList list = new WordList();
                list.add(array[1]);
                for (s = array[2]; EString.match(s, "* *", array); s = array[1]) {
                    list.add(array[0]);
                }
                list.add(s);
                this.syns.add(list);
                return;
            }
            if (EString.match(s, "*pre: * *", array)) {
                this.pre.add(array[1], array[2]);
                return;
            }
            if (EString.match(s, "*post: * *", array)) {
                this.post.add(array[1], array[2]);
                return;
            }
            if (EString.match(s, "*initial: *", array)) {
                this.initial = array[1];
                return;
            }
            if (EString.match(s, "*final: *", array)) {
                this.finl = array[1];
                return;
            }
            if (EString.match(s, "*quit: *", array)) {
                this.quit.add(" " + array[1] + " ");
                return;
            }
            System.out.println("Unrecognized input: " + s);
        }
    }
    
    public void print() {
    }
    
    public String processInput(String s) {
        s = EString.translate(s, "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "abcdefghijklmnopqrstuvwxyz");
        s = EString.translate(s, "@#$%^&*()_-+=~`{[}]|:;<>\\\"", "                          ");
        s = EString.translate(s, ",?!", "...");
        s = EString.compress(s);
        for (String[] array = new String[2]; EString.match(s, "*.*", array); s = EString.trim(array[1])) {
            final String sentence = this.sentence(array[0]);
            if (sentence != null) {
                return sentence;
            }
        }
        if (s.length() != 0) {
            final String sentence2 = this.sentence(s);
            if (sentence2 != null) {
                return sentence2;
            }
        }
        final String value = this.mem.get();
        if (value != null) {
            return value;
        }
        final Key key = this.keys.getKey("xnone");
        if (key != null) {
            final String decompose = this.decompose(key, s, null);
            if (decompose != null) {
                return decompose;
            }
        }
        return "I am at a loss for words.";
    }
    
    String sentence(String s) {
        s = this.pre.translate(s);
        s = EString.pad(s);
        if (this.quit.find(s)) {
            this.finished = true;
            return this.finl;
        }
        this.keys.buildKeyStack(this.keyStack, s);
        for (int i = 0; i < this.keyStack.keyTop(); ++i) {
            final Key key = new Key();
            final String decompose = this.decompose(this.keyStack.key(i), s, key);
            if (decompose != null) {
                return decompose;
            }
            while (key.key() != null) {
                final String decompose2 = this.decompose(key, s, key);
                if (decompose2 != null) {
                    return decompose2;
                }
            }
        }
        return null;
    }
    
    String decompose(final Key key, final String s, final Key key2) {
        final String[] array = new String[10];
        for (int i = 0; i < key.decomp().size(); ++i) {
            final Decomp decomp = key.decomp().elementAt(i);
            if (this.syns.matchDecomp(s, decomp.pattern(), array)) {
                final String assemble = this.assemble(decomp, array, key2);
                if (assemble != null) {
                    return assemble;
                }
                if (key2.key() != null) {
                    return null;
                }
            }
        }
        return null;
    }
    
    String assemble(final Decomp decomp, final String[] array, final Key key) {
        final String[] array2 = new String[3];
        decomp.stepRule();
        String nextRule = decomp.nextRule();
        if (EString.match(nextRule, "goto *", array2)) {
            key.copy(this.keys.getKey(array2[0]));
            if (key.key() != null) {
                return null;
            }
            System.out.println("Goto rule did not match key: " + array2[0]);
            return null;
        }
        else {
            String string = "";
            while (EString.match(nextRule, "* (#)*", array2)) {
                nextRule = array2[2];
                int n = 0;
                try {
                    n = Integer.parseInt(array2[1]) - 1;
                }
                catch (NumberFormatException ex) {
                    System.out.println("Number is wrong in reassembly rule " + array2[1]);
                }
                if (n < 0 || n >= array.length) {
                    System.out.println("Substitution number is bad " + array2[1]);
                    return null;
                }
                array[n] = this.post.translate(array[n]);
                string = String.valueOf(string) + array2[0] + " " + array[n];
            }
            final String string2 = String.valueOf(string) + nextRule;
            if (decomp.mem()) {
                this.mem.save(string2);
                return null;
            }
            return string2;
        }
    }
    
    public int readScript(final boolean b, final URL url) {
        DataInputStream dataInputStream = null;
        try {
            if (b) {
                dataInputStream = new DataInputStream(url.openStream());
            }
            while (true) {
                final String line = dataInputStream.readLine();
                if (line == null) {
                    break;
                }
                this.collect(line);
            }
        }
        catch (IOException ex) {
            System.out.println("There was a problem reading the script file.");
            System.out.println("Tried " + url);
            return 1;
        }
        return 0;
    }
    
    public MainBrain() {
        this.echoInput = false;
        this.printData = false;
        this.printKeys = false;
        this.printSyns = false;
        this.printPrePost = false;
        this.printInitialFinal = false;
        this.keys = new KeyList();
        this.syns = new SynList();
        this.pre = new PrePostList();
        this.post = new PrePostList();
        this.initial = "Hello.";
        this.finl = "Goodbye.";
        this.quit = new WordList();
        this.keyStack = new KeyStack();
        this.mem = new Mem();
        this.finished = false;
    }
}
