// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter;

import java.io.UnsupportedEncodingException;
import com.stonewall.parser.interpreter.builtin.Builtin;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import org.jdom.xpath.XPath;
import java.util.ArrayList;
import java.util.Iterator;
import com.stonewall.parser.Tuple;
import java.io.Reader;
import java.io.StringReader;
import org.xmodel.log.Log;
import com.stonewall.parser.PathStore;
import org.jdom.Element;
import com.stonewall.parser.Dictionary;
import org.jdom.Document;
import java.util.LinkedList;
import java.util.List;

public class Interpreter implements InterpreterConstants
{
    public static final String version = "M20091220_01";
    private String string;
    private Dispatcher dispatcher;
    private List<EventListener> listeners;
    private LinkedList<String> stack;
    private Document model;
    private Dictionary<String> references;
    private Dictionary<Element> nodeReferences;
    private Dictionary<Dictionary<String>> dictionaries;
    private PathStore pathStore;
    static Log log;
    public InterpreterTokenManager token_source;
    SimpleCharStream jj_input_stream;
    public Token token;
    public Token jj_nt;
    private int jj_ntk;
    private Token jj_scanpos;
    private Token jj_lastpos;
    private int jj_la;
    private int jj_gen;
    private final int[] jj_la1;
    private static int[] jj_la1_0;
    private static int[] jj_la1_1;
    private final JJCalls[] jj_2_rtns;
    private boolean jj_rescan;
    private int jj_gc;
    private final LookaheadSuccess jj_ls;
    private List<int[]> jj_expentries;
    private int[] jj_expentry;
    private int jj_kind;
    private int[] jj_lasttokens;
    private int jj_endpos;
    
    static {
        Interpreter.log = Log.getLog(Interpreter.class);
        jj_la1_init_0();
        jj_la1_init_1();
    }
    
    public Interpreter() {
        this(new StringReader(""));
    }
    
    public void set(final Log logger) {
        Interpreter.log = logger;
    }
    
    public void attach(final Dispatcher dispatcher) {
        this.dispatcher.attach(dispatcher);
    }
    
    public void setTokens(final List<String> tokens) {
        this.setTokens(tokens, true);
    }
    
    public void setTokens(final List<String> tokens, final boolean detailed) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tokens.size(); ++i) {
            sb.append(tokens.get(i));
            sb.append(Tuple.delimiter);
            if (detailed) {
                this.references.put("$" + i, (String)tokens.get(i));
            }
        }
        this.references.put("$*", sb.toString());
        this.references.put("$_", String.valueOf(tokens.size()));
        Interpreter.log.debug("references updated w/ tokens: " + this.references);
    }
    
    public void setKeywords(final Dictionary<String> keywords) {
        final StringBuilder sb = new StringBuilder();
        for (final String k : keywords.keySet()) {
            this.references.put("#" + k, (String)keywords.get(k));
            sb.append(k);
            sb.append('=');
            sb.append(keywords.get(k));
            sb.append(Tuple.delimiter);
        }
        this.references.put("#*", sb.toString());
        this.references.put("#_", String.valueOf(keywords.size()));
        Interpreter.log.debug("references updated w/ keywords: " + this.references);
    }
    
    public void clearTokensAndKeywords() {
        final List<String> rmList = new ArrayList<String>();
        for (final String k : this.references.keySet()) {
            if (k.startsWith("#") || k.startsWith("$")) {
                rmList.add(k);
            }
        }
        for (final String k : rmList) {
            this.references.remove(k);
        }
    }
    
    public void setDictionaries(final Dictionary<Dictionary<String>> dictionaries) {
        this.dictionaries = dictionaries;
    }
    
    public void set(final Document model) {
        this.model = model;
    }
    
    public void set(final PathStore pathStore) {
        this.pathStore = pathStore;
    }
    
    public Dispatcher dispatcher() {
        return this.dispatcher;
    }
    
    public Document model() {
        return this.model;
    }
    
    public Dictionary<Element> nodeReferences() {
        return this.nodeReferences;
    }
    
    public void nodeReferences(final Dictionary<Element> nodeReferences) {
        this.nodeReferences = nodeReferences;
    }
    
    public Dictionary<String> references() {
        return this.references;
    }
    
    public void references(final Dictionary<String> references) {
        this.references = references;
    }
    
    public Dictionary<Dictionary<String>> dictionaries() {
        return this.dictionaries;
    }
    
    public Dictionary<String> dictionary(final String name) {
        return this.dictionaries.get(name);
    }
    
    public XPath xpath(final String p) throws Exception {
        return this.pathStore.get(p, (Map<String, String>)this.references);
    }
    
    public String execute(final String s) throws Exception {
        return this.execute(new StringReader(s));
    }
    
    public String execute(final Reader reader) throws Exception {
        this.ReInit(reader);
        this.stack.clear();
        return this.start();
    }
    
    @Override
    public String toString() {
        return "interpreter: [M20091220_01]\n" + this.references;
    }
    
    public void register(final EventListener lnr) {
        if (!this.listeners.contains(lnr)) {
            this.listeners.add(lnr);
        }
    }
    
    public void raiseEvent(final Event.Severity severity, final String msg) {
        this.raiseEvent(severity, msg, null);
    }
    
    public void raiseEvent(final Event.Severity severity, final String msg, final Throwable e) {
        final Event event = new Event(this, severity, msg, e);
        if (this.listeners.isEmpty()) {
            event.log(Interpreter.log);
            return;
        }
        for (final EventListener lnr : this.listeners) {
            lnr.onEvent(event);
        }
    }
    
    String dispatch(final String[] args) {
        return this.dispatcher.dispatch(this, args);
    }
    
    void push(final String s) {
        this.stack.add(s);
        Interpreter.log.debug("push: \"" + s + "\" stack:" + this.stack);
    }
    
    String top() {
        return this.stack.getLast();
    }
    
    String top(final String s) {
        this.pop();
        this.push(s);
        return s;
    }
    
    String pop() {
        String result = "";
        if (this.stack.size() > 0) {
            result = this.stack.removeLast();
        }
        Interpreter.log.debug("pop: \"" + result + "\" stack:" + this.stack);
        return result;
    }
    
    String result() {
        final StringBuilder sb = new StringBuilder();
        for (final String s : this.stack) {
            sb.append(s);
        }
        return sb.toString();
    }
    
    String resolve(final String name) {
        if (this.nodeReferences.keySet().contains(name)) {
            return name;
        }
        String result = this.references.get(name);
        if (result == null) {
            result = (this.tokenOrKeyword(name) ? "" : name);
        }
        else {
            Interpreter.log.debug("reference: \"" + name + "\" resolved as: \"" + result + "\"");
        }
        return result;
    }
    
    boolean tokenOrKeyword(final String name) {
        return name.startsWith("$") || name.startsWith("#");
    }
    
    boolean validateAssignment(final String s) {
        if (s.charAt(0) == '$' && this.references.keySet().contains(s)) {
            final String msg = "assignment of (" + s + ") hides token!";
            Interpreter.log.warn(msg);
            this.raiseEvent(Event.Severity.warning, msg);
        }
        return true;
    }
    
    boolean defined(final String s) {
        Object v = this.references.get(s);
        if (v == null) {
            v = this.nodeReferences.get(s);
        }
        return v != null;
    }
    
    public final String start() throws ParseException {
        try {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 8: {
                    this.jj_consume_token(8);
                    break;
                }
                default: {
                    this.jj_la1[0] = this.jj_gen;
                    break;
                }
            }
            while (true) {
                switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                    case 10:
                    case 13:
                    case 20:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28: {
                        if (this.jj_2_1(Integer.MAX_VALUE)) {
                            this.assignment();
                            continue;
                        }
                        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                            case 10:
                            case 13:
                            case 20:
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28: {
                                this.push(this.string = this.term());
                                continue;
                            }
                            default: {
                                this.jj_la1[2] = this.jj_gen;
                                this.jj_consume_token(-1);
                                throw new ParseException();
                            }
                        }
                        break;
                    }
                    default: {
                        this.jj_la1[1] = this.jj_gen;
                        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                            case 9: {
                                this.jj_consume_token(9);
                                break;
                            }
                            default: {
                                this.jj_la1[3] = this.jj_gen;
                                break;
                            }
                        }
                        return this.result();
                    }
                }
            }
        }
        catch (ParseException e) {
            Interpreter.log.debug(e);
            throw e;
        }
    }
    
    public final String function() throws ParseException {
        List<String> list = Collections.emptyList();
        final String s = this.identifier();
        this.jj_consume_token(10);
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 10:
            case 13:
            case 20:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28: {
                list = this.arglist();
                break;
            }
            default: {
                this.jj_la1[4] = this.jj_gen;
                break;
            }
        }
        this.jj_consume_token(11);
        int i = 0;
        final String[] args = new String[list.size() + 1];
        args[i++] = s;
        for (final String a : list) {
            args[i++] = a;
        }
        return this.string = this.dispatch(args);
    }
    
    public final List<String> arglist() throws ParseException {
        int i = 0;
        final String[] array = new String[100];
        array[i++] = this.argument();
        while (true) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 12: {
                    this.jj_consume_token(12);
                    array[i++] = this.argument();
                    continue;
                }
                default: {
                    this.jj_la1[5] = this.jj_gen;
                    final List<String> list = new ArrayList<String>(i);
                    for (int n = 0; n < i; ++n) {
                        list.add(array[n]);
                    }
                    return list;
                }
            }
        }
    }
    
    public final String argument() throws ParseException {
        return this.string = this.term();
    }
    
    public final String term() throws ParseException {
        if (this.jj_2_2(2)) {
            this.string = this.defined();
        }
        else if (this.jj_2_3(2)) {
            this.string = this.function();
        }
        else if (this.jj_2_4(4)) {
            this.string = this.contained();
        }
        else if (this.jj_2_5(2)) {
            this.string = this.xpath();
        }
        else if (this.jj_2_6(2)) {
            this.string = this.tuple();
        }
        else if (this.jj_2_7(4)) {
            this.string = this.dictionary();
        }
        else if (this.jj_2_8(4)) {
            this.string = this.identifier();
            this.string = this.resolve(this.string);
        }
        else if (this.jj_2_9(2)) {
            this.string = this.escaped();
        }
        else if (this.jj_2_10(2)) {
            this.string = this.string();
        }
        else {
            if (!this.jj_2_11(2)) {
                this.jj_consume_token(-1);
                throw new ParseException();
            }
            this.string = this.number();
        }
        if (this.jj_2_12(Integer.MAX_VALUE)) {
            this.string = this.postfix();
        }
        return this.string;
    }
    
    public final String string() throws ParseException {
        String v = null;
        final Token t = this.jj_consume_token(28);
        if (this.jj_2_13(2)) {
            this.jj_consume_token(22);
            v = this.term();
        }
        final String s = t.toString();
        this.string = s.substring(1, s.length() - 1);
        if (v != null) {
            String[] split;
            for (int length = (split = Tuple.split(v)).length, i = 0; i < length; ++i) {
                final String r = split[i];
                this.string = this.string.replaceFirst("%s", r);
            }
        }
        return this.string;
    }
    
    public final String identifier() throws ParseException {
        final Token t = this.jj_consume_token(27);
        return this.string = t.toString();
    }
    
    public final String number() throws ParseException {
        final Token t = this.jj_consume_token(26);
        return this.string = t.toString();
    }
    
    public final String escaped() throws ParseException {
        final Token t = this.jj_consume_token(25);
        return this.string = t.toString().substring(1);
    }
    
    public final String postfix() throws ParseException {
        if (this.jj_2_14(4)) {
            this.string = this.substring();
        }
        else {
            if (!this.jj_2_15(4)) {
                this.jj_consume_token(-1);
                throw new ParseException();
            }
            this.string = this.subscript();
        }
        return this.string;
    }
    
    public final void assignment() throws ParseException {
        if (this.jj_2_16(2)) {
            this.assignVar();
        }
        else if (this.jj_2_17(2)) {
            this.assignNode();
        }
        else {
            if (!this.jj_2_18(2)) {
                this.jj_consume_token(-1);
                throw new ParseException();
            }
            this.assignDictionary();
        }
        this.jj_consume_token(16);
    }
    
    public final void assignVar() throws ParseException {
        final String s = this.identifier();
        this.jj_consume_token(17);
        final String v = this.argument();
        if (!this.validateAssignment(s)) {
            return;
        }
        Interpreter.log.debug("reference: \"" + s + "\" assigned: \"" + v + "\"");
        this.references.put(s, v);
        if (this.nodeReferences.keySet().contains(s)) {
            final String msg = "assignment of (" + s + ") hides node!";
            Interpreter.log.warn(msg);
            this.raiseEvent(Event.Severity.warning, msg);
        }
    }
    
    public final void assignNode() throws ParseException {
        String r = null;
        final String s = this.identifier();
        this.jj_consume_token(18);
        if (this.jj_2_19(2)) {
            r = this.identifier();
            this.jj_consume_token(12);
        }
        final String v = this.argument();
        if (!this.validateAssignment(s)) {
            return;
        }
        try {
            Object root = this.model;
            if (r != null) {
                root = this.nodeReferences.get(r);
                if (root == null) {
                    final String msg = "referenced (context) node (" + r + ") not-found";
                    this.raiseEvent(Event.Severity.error, msg);
                    return;
                }
            }
            final Element node = (Element)this.xpath(v).selectSingleNode(root);
            if (node != null) {
                Interpreter.log.debug("{node} reference: \"" + s + "\" assigned: \"" + v + "\"");
                this.nodeReferences.put(s, node);
            }
            else {
                final String msg2 = "{node} reference: \"" + s + "\" not assigned, path: \"" + v + "\" not-found";
                this.raiseEvent(Event.Severity.error, msg2);
            }
        }
        catch (Exception e) {
            this.raiseEvent(Event.Severity.error, s, e);
        }
    }
    
    public final void assignDictionary() throws ParseException {
        final String s = this.identifier();
        this.jj_consume_token(13);
        final String k = this.key();
        this.jj_consume_token(14);
        this.jj_consume_token(17);
        final String v = this.argument();
        Dictionary<String> dictionary = this.dictionary(s);
        if (dictionary == null) {
            dictionary = new Dictionary<String>();
            this.dictionaries.put(s, dictionary);
        }
        Interpreter.log.debug("dictionary: \"" + s + "\" key: \"" + k + "\"" + "\" assigned: \"" + v + "\"");
        dictionary.put(k, v);
    }
    
    public final String substring() throws ParseException {
        Token s = null;
        Token e = null;
        this.jj_consume_token(13);
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 26: {
                s = this.jj_consume_token(26);
                break;
            }
            default: {
                this.jj_la1[6] = this.jj_gen;
                break;
            }
        }
        this.jj_consume_token(15);
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 26: {
                e = this.jj_consume_token(26);
                break;
            }
            default: {
                this.jj_la1[7] = this.jj_gen;
                break;
            }
        }
        this.jj_consume_token(14);
        final String[] args = { "substring", this.string, (s != null) ? s.toString() : "0", (e != null) ? e.toString() : null };
        return this.string = this.dispatch(args);
    }
    
    public final String subscript() throws ParseException {
        this.jj_consume_token(13);
        final Token i = this.jj_consume_token(26);
        this.jj_consume_token(14);
        final String[] args = { "subscript", this.string, i.toString() };
        return this.string = this.dispatch(args);
    }
    
    public final String dictionary() throws ParseException {
        final String d = this.identifier();
        this.jj_consume_token(13);
        final String k = this.key();
        this.jj_consume_token(14);
        final Dictionary<String> dictionary = this.dictionary(d);
        if (dictionary == null) {
            this.string = "<dictionary:not-found>";
            final String msg = "dictonary: " + d + " - not-defined";
            this.raiseEvent(Event.Severity.error, msg);
            return this.string;
        }
        this.string = dictionary.get(k);
        if (this.string == null) {
            this.string = "<key:not-found>";
            final String msg = String.valueOf(d) + "[" + k + "]" + ", key not-found";
            this.raiseEvent(Event.Severity.error, msg);
        }
        return this.string;
    }
    
    public final String key() throws ParseException {
        String k;
        if (this.jj_2_20(4)) {
            k = this.function();
        }
        else if (this.jj_2_21(4)) {
            k = this.defined();
        }
        else if (this.jj_2_22(4)) {
            k = this.string();
        }
        else {
            if (!this.jj_2_23(4)) {
                this.jj_consume_token(-1);
                throw new ParseException();
            }
            k = this.identifier();
            k = this.resolve(k);
        }
        return k;
    }
    
    public final String contained() throws ParseException {
        this.jj_consume_token(13);
        final String s = this.argument();
        this.jj_consume_token(19);
        final List<String> list = this.arglist();
        this.jj_consume_token(14);
        final String[] args = new String[list.size() + 2];
        args[0] = "contained";
        args[1] = s;
        for (int i = 2; i < args.length; ++i) {
            args[i] = list.get(i - 2);
        }
        return this.string = this.dispatch(args);
    }
    
    public final String tuple() throws ParseException {
        this.jj_consume_token(10);
        final List<String> list = this.arglist();
        this.jj_consume_token(11);
        return Tuple.join(list);
    }
    
    public final String xpath() throws ParseException {
        String r = null;
        this.jj_consume_token(20);
        if (this.jj_2_24(2)) {
            r = this.identifier();
            this.jj_consume_token(12);
        }
        final String p = this.argument();
        final String[] args = { "xpath", r, p };
        return this.string = this.dispatch(args);
    }
    
    public final String defined() throws ParseException {
        int i = 0;
        final Token[] array = new Token[100];
        this.jj_consume_token(24);
        this.jj_consume_token(10);
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 27: {
                array[i++] = this.jj_consume_token(27);
                break;
            }
            case 26: {
                array[i++] = this.jj_consume_token(26);
                break;
            }
            default: {
                this.jj_la1[8] = this.jj_gen;
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        while (true) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 23: {
                    this.jj_consume_token(23);
                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                        case 27: {
                            array[i++] = this.jj_consume_token(27);
                            continue;
                        }
                        case 26: {
                            array[i++] = this.jj_consume_token(26);
                            continue;
                        }
                        default: {
                            this.jj_la1[10] = this.jj_gen;
                            this.jj_consume_token(-1);
                            throw new ParseException();
                        }
                    }
                    break;
                }
                default: {
                    this.jj_la1[9] = this.jj_gen;
                    this.jj_consume_token(11);
                    String result = "false";
                    if (i > 1) {
                        for (int n = 0; n < i; ++n) {
                            final String v = this.references.get(array[n].toString());
                            if (v != null) {
                                result = v;
                                break;
                            }
                        }
                    }
                    else {
                        result = String.valueOf(this.defined(array[0].toString()));
                    }
                    return result;
                }
            }
        }
    }
    
    private boolean jj_2_1(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_1();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(0, xla);
        }
    }
    
    private boolean jj_2_2(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_2();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(1, xla);
        }
    }
    
    private boolean jj_2_3(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_3();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(2, xla);
        }
    }
    
    private boolean jj_2_4(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_4();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(3, xla);
        }
    }
    
    private boolean jj_2_5(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_5();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(4, xla);
        }
    }
    
    private boolean jj_2_6(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_6();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(5, xla);
        }
    }
    
    private boolean jj_2_7(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_7();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(6, xla);
        }
    }
    
    private boolean jj_2_8(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_8();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(7, xla);
        }
    }
    
    private boolean jj_2_9(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_9();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(8, xla);
        }
    }
    
    private boolean jj_2_10(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_10();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(9, xla);
        }
    }
    
    private boolean jj_2_11(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_11();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(10, xla);
        }
    }
    
    private boolean jj_2_12(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_12();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(11, xla);
        }
    }
    
    private boolean jj_2_13(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_13();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(12, xla);
        }
    }
    
    private boolean jj_2_14(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_14();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(13, xla);
        }
    }
    
    private boolean jj_2_15(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_15();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(14, xla);
        }
    }
    
    private boolean jj_2_16(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_16();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(15, xla);
        }
    }
    
    private boolean jj_2_17(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_17();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(16, xla);
        }
    }
    
    private boolean jj_2_18(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_18();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(17, xla);
        }
    }
    
    private boolean jj_2_19(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_19();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(18, xla);
        }
    }
    
    private boolean jj_2_20(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_20();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(19, xla);
        }
    }
    
    private boolean jj_2_21(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_21();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(20, xla);
        }
    }
    
    private boolean jj_2_22(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_22();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(21, xla);
        }
    }
    
    private boolean jj_2_23(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_23();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(22, xla);
        }
    }
    
    private boolean jj_2_24(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_24();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
        finally {
            this.jj_save(23, xla);
        }
    }
    
    private boolean jj_3_9() {
        return this.jj_3R_12();
    }
    
    private boolean jj_3_8() {
        return this.jj_3R_11();
    }
    
    private boolean jj_3_7() {
        return this.jj_3R_10();
    }
    
    private boolean jj_3R_20() {
        if (this.jj_3R_11()) {
            return true;
        }
        if (this.jj_scan_token(18)) {
            return true;
        }
        final Token xsp = this.jj_scanpos;
        if (this.jj_3_19()) {
            this.jj_scanpos = xsp;
        }
        return this.jj_3R_22();
    }
    
    private boolean jj_3_6() {
        return this.jj_3R_9();
    }
    
    private boolean jj_3_15() {
        return this.jj_3R_18();
    }
    
    private boolean jj_3_5() {
        return this.jj_3R_8();
    }
    
    private boolean jj_3_4() {
        return this.jj_3R_7();
    }
    
    private boolean jj_3_3() {
        return this.jj_3R_6();
    }
    
    private boolean jj_3_2() {
        return this.jj_3R_5();
    }
    
    private boolean jj_3R_16() {
        Token xsp = this.jj_scanpos;
        if (this.jj_3_2()) {
            this.jj_scanpos = xsp;
            if (this.jj_3_3()) {
                this.jj_scanpos = xsp;
                if (this.jj_3_4()) {
                    this.jj_scanpos = xsp;
                    if (this.jj_3_5()) {
                        this.jj_scanpos = xsp;
                        if (this.jj_3_6()) {
                            this.jj_scanpos = xsp;
                            if (this.jj_3_7()) {
                                this.jj_scanpos = xsp;
                                if (this.jj_3_8()) {
                                    this.jj_scanpos = xsp;
                                    if (this.jj_3_9()) {
                                        this.jj_scanpos = xsp;
                                        if (this.jj_3_10()) {
                                            this.jj_scanpos = xsp;
                                            if (this.jj_3_11()) {
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        xsp = this.jj_scanpos;
        if (this.jj_3R_27()) {
            this.jj_scanpos = xsp;
        }
        return false;
    }
    
    private boolean jj_3R_28() {
        return this.jj_scan_token(12) || this.jj_3R_22();
    }
    
    private boolean jj_3R_10() {
        return this.jj_3R_11() || this.jj_scan_token(13) || this.jj_3R_24() || this.jj_scan_token(14);
    }
    
    private boolean jj_3R_19() {
        return this.jj_3R_11() || this.jj_scan_token(17) || this.jj_3R_22();
    }
    
    private boolean jj_3R_22() {
        return this.jj_3R_16();
    }
    
    private boolean jj_3_18() {
        return this.jj_3R_21();
    }
    
    private boolean jj_3_17() {
        return this.jj_3R_20();
    }
    
    private boolean jj_3R_26() {
        if (this.jj_scan_token(23)) {
            return true;
        }
        final Token xsp = this.jj_scanpos;
        if (this.jj_scan_token(27)) {
            this.jj_scanpos = xsp;
            if (this.jj_scan_token(26)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean jj_3_16() {
        return this.jj_3R_19();
    }
    
    private boolean jj_3R_4() {
        final Token xsp = this.jj_scanpos;
        if (this.jj_3_16()) {
            this.jj_scanpos = xsp;
            if (this.jj_3_17()) {
                this.jj_scanpos = xsp;
                if (this.jj_3_18()) {
                    return true;
                }
            }
        }
        return this.jj_scan_token(16);
    }
    
    private boolean jj_3R_25() {
        return this.jj_3R_23();
    }
    
    private boolean jj_3R_18() {
        return this.jj_scan_token(13) || this.jj_scan_token(26) || this.jj_scan_token(14);
    }
    
    private boolean jj_3R_23() {
        if (this.jj_3R_22()) {
            return true;
        }
        Token xsp;
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_28());
        this.jj_scanpos = xsp;
        return false;
    }
    
    private boolean jj_3_14() {
        return this.jj_3R_17();
    }
    
    private boolean jj_3R_15() {
        final Token xsp = this.jj_scanpos;
        if (this.jj_3_14()) {
            this.jj_scanpos = xsp;
            if (this.jj_3_15()) {
                return true;
            }
        }
        return false;
    }
    
    private boolean jj_3R_5() {
        if (this.jj_scan_token(24)) {
            return true;
        }
        if (this.jj_scan_token(10)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_scan_token(27)) {
            this.jj_scanpos = xsp;
            if (this.jj_scan_token(26)) {
                return true;
            }
        }
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_26());
        this.jj_scanpos = xsp;
        return this.jj_scan_token(11);
    }
    
    private boolean jj_3R_17() {
        if (this.jj_scan_token(13)) {
            return true;
        }
        Token xsp = this.jj_scanpos;
        if (this.jj_scan_token(26)) {
            this.jj_scanpos = xsp;
        }
        if (this.jj_scan_token(15)) {
            return true;
        }
        xsp = this.jj_scanpos;
        if (this.jj_scan_token(26)) {
            this.jj_scanpos = xsp;
        }
        return this.jj_scan_token(14);
    }
    
    private boolean jj_3_24() {
        return this.jj_3R_11() || this.jj_scan_token(12);
    }
    
    private boolean jj_3R_8() {
        if (this.jj_scan_token(20)) {
            return true;
        }
        final Token xsp = this.jj_scanpos;
        if (this.jj_3_24()) {
            this.jj_scanpos = xsp;
        }
        return this.jj_3R_22();
    }
    
    private boolean jj_3R_12() {
        return this.jj_scan_token(25);
    }
    
    private boolean jj_3R_6() {
        if (this.jj_3R_11()) {
            return true;
        }
        if (this.jj_scan_token(10)) {
            return true;
        }
        final Token xsp = this.jj_scanpos;
        if (this.jj_3R_25()) {
            this.jj_scanpos = xsp;
        }
        return this.jj_scan_token(11);
    }
    
    private boolean jj_3_1() {
        return this.jj_3R_4();
    }
    
    private boolean jj_3R_9() {
        return this.jj_scan_token(10) || this.jj_3R_23() || this.jj_scan_token(11);
    }
    
    private boolean jj_3R_14() {
        return this.jj_scan_token(26);
    }
    
    private boolean jj_3R_21() {
        return this.jj_3R_11() || this.jj_scan_token(13) || this.jj_3R_24() || this.jj_scan_token(14) || this.jj_scan_token(17) || this.jj_3R_22();
    }
    
    private boolean jj_3R_11() {
        return this.jj_scan_token(27);
    }
    
    private boolean jj_3_19() {
        return this.jj_3R_11() || this.jj_scan_token(12);
    }
    
    private boolean jj_3R_7() {
        return this.jj_scan_token(13) || this.jj_3R_22() || this.jj_scan_token(19) || this.jj_3R_23() || this.jj_scan_token(14);
    }
    
    private boolean jj_3_13() {
        return this.jj_scan_token(22) || this.jj_3R_16();
    }
    
    private boolean jj_3_12() {
        return this.jj_3R_15();
    }
    
    private boolean jj_3_23() {
        return this.jj_3R_11();
    }
    
    private boolean jj_3_22() {
        return this.jj_3R_13();
    }
    
    private boolean jj_3_21() {
        return this.jj_3R_5();
    }
    
    private boolean jj_3_20() {
        return this.jj_3R_6();
    }
    
    private boolean jj_3R_24() {
        final Token xsp = this.jj_scanpos;
        if (this.jj_3_20()) {
            this.jj_scanpos = xsp;
            if (this.jj_3_21()) {
                this.jj_scanpos = xsp;
                if (this.jj_3_22()) {
                    this.jj_scanpos = xsp;
                    if (this.jj_3_23()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean jj_3R_13() {
        if (this.jj_scan_token(28)) {
            return true;
        }
        final Token xsp = this.jj_scanpos;
        if (this.jj_3_13()) {
            this.jj_scanpos = xsp;
        }
        return false;
    }
    
    private boolean jj_3R_27() {
        return this.jj_3R_15();
    }
    
    private boolean jj_3_11() {
        return this.jj_3R_14();
    }
    
    private boolean jj_3_10() {
        return this.jj_3R_13();
    }
    
    private static void jj_la1_init_0() {
        Interpreter.jj_la1_0 = new int[] { 256, 521151488, 521151488, 512, 521151488, 4096, 67108864, 67108864, 201326592, 8388608, 201326592 };
    }
    
    private static void jj_la1_init_1() {
        Interpreter.jj_la1_1 = new int[11];
    }
    
    public Interpreter(final InputStream stream) {
        this(stream, null);
    }
    
    public Interpreter(final InputStream stream, final String encoding) {
        this.string = "";
        this.dispatcher = new Builtin();
        this.listeners = new ArrayList<EventListener>();
        this.stack = new LinkedList<String>();
        this.model = new Document();
        this.references = new Dictionary<String>();
        this.nodeReferences = new Dictionary<Element>();
        this.dictionaries = new Dictionary<Dictionary<String>>();
        this.pathStore = new PathStore();
        this.jj_la1 = new int[11];
        this.jj_2_rtns = new JJCalls[24];
        this.jj_rescan = false;
        this.jj_gc = 0;
        this.jj_ls = new LookaheadSuccess((LookaheadSuccess)null);
        this.jj_expentries = new ArrayList<int[]>();
        this.jj_kind = -1;
        this.jj_lasttokens = new int[100];
        try {
            this.jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1);
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        this.token_source = new InterpreterTokenManager(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (int i = 0; i < 11; ++i) {
            this.jj_la1[i] = -1;
        }
        for (int i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }
    
    public void ReInit(final InputStream stream) {
        this.ReInit(stream, null);
    }
    
    public void ReInit(final InputStream stream, final String encoding) {
        try {
            this.jj_input_stream.ReInit(stream, encoding, 1, 1);
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        this.token_source.ReInit(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (int i = 0; i < 11; ++i) {
            this.jj_la1[i] = -1;
        }
        for (int i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }
    
    public Interpreter(final Reader stream) {
        this.string = "";
        this.dispatcher = new Builtin();
        this.listeners = new ArrayList<EventListener>();
        this.stack = new LinkedList<String>();
        this.model = new Document();
        this.references = new Dictionary<String>();
        this.nodeReferences = new Dictionary<Element>();
        this.dictionaries = new Dictionary<Dictionary<String>>();
        this.pathStore = new PathStore();
        this.jj_la1 = new int[11];
        this.jj_2_rtns = new JJCalls[24];
        this.jj_rescan = false;
        this.jj_gc = 0;
        this.jj_ls = new LookaheadSuccess((LookaheadSuccess)null);
        this.jj_expentries = new ArrayList<int[]>();
        this.jj_kind = -1;
        this.jj_lasttokens = new int[100];
        this.jj_input_stream = new SimpleCharStream(stream, 1, 1);
        this.token_source = new InterpreterTokenManager(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (int i = 0; i < 11; ++i) {
            this.jj_la1[i] = -1;
        }
        for (int i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }
    
    public void ReInit(final Reader stream) {
        this.jj_input_stream.ReInit(stream, 1, 1);
        this.token_source.ReInit(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (int i = 0; i < 11; ++i) {
            this.jj_la1[i] = -1;
        }
        for (int i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }
    
    public Interpreter(final InterpreterTokenManager tm) {
        this.string = "";
        this.dispatcher = new Builtin();
        this.listeners = new ArrayList<EventListener>();
        this.stack = new LinkedList<String>();
        this.model = new Document();
        this.references = new Dictionary<String>();
        this.nodeReferences = new Dictionary<Element>();
        this.dictionaries = new Dictionary<Dictionary<String>>();
        this.pathStore = new PathStore();
        this.jj_la1 = new int[11];
        this.jj_2_rtns = new JJCalls[24];
        this.jj_rescan = false;
        this.jj_gc = 0;
        this.jj_ls = new LookaheadSuccess((LookaheadSuccess)null);
        this.jj_expentries = new ArrayList<int[]>();
        this.jj_kind = -1;
        this.jj_lasttokens = new int[100];
        this.token_source = tm;
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (int i = 0; i < 11; ++i) {
            this.jj_la1[i] = -1;
        }
        for (int i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }
    
    public void ReInit(final InterpreterTokenManager tm) {
        this.token_source = tm;
        this.token = new Token();
        this.jj_ntk = -1;
        this.jj_gen = 0;
        for (int i = 0; i < 11; ++i) {
            this.jj_la1[i] = -1;
        }
        for (int i = 0; i < this.jj_2_rtns.length; ++i) {
            this.jj_2_rtns[i] = new JJCalls();
        }
    }
    
    private Token jj_consume_token(final int kind) throws ParseException {
        final Token oldToken;
        if ((oldToken = this.token).next != null) {
            this.token = this.token.next;
        }
        else {
            final Token token = this.token;
            final Token nextToken = this.token_source.getNextToken();
            token.next = nextToken;
            this.token = nextToken;
        }
        this.jj_ntk = -1;
        if (this.token.kind == kind) {
            ++this.jj_gen;
            if (++this.jj_gc > 100) {
                this.jj_gc = 0;
                for (int i = 0; i < this.jj_2_rtns.length; ++i) {
                    for (JJCalls c = this.jj_2_rtns[i]; c != null; c = c.next) {
                        if (c.gen < this.jj_gen) {
                            c.first = null;
                        }
                    }
                }
            }
            return this.token;
        }
        this.token = oldToken;
        this.jj_kind = kind;
        throw this.generateParseException();
    }
    
    private boolean jj_scan_token(final int kind) {
        if (this.jj_scanpos == this.jj_lastpos) {
            --this.jj_la;
            if (this.jj_scanpos.next == null) {
                final Token jj_scanpos = this.jj_scanpos;
                final Token nextToken = this.token_source.getNextToken();
                jj_scanpos.next = nextToken;
                this.jj_scanpos = nextToken;
                this.jj_lastpos = nextToken;
            }
            else {
                final Token next = this.jj_scanpos.next;
                this.jj_scanpos = next;
                this.jj_lastpos = next;
            }
        }
        else {
            this.jj_scanpos = this.jj_scanpos.next;
        }
        if (this.jj_rescan) {
            int i = 0;
            Token tok;
            for (tok = this.token; tok != null && tok != this.jj_scanpos; tok = tok.next) {
                ++i;
            }
            if (tok != null) {
                this.jj_add_error_token(kind, i);
            }
        }
        if (this.jj_scanpos.kind != kind) {
            return true;
        }
        if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) {
            throw this.jj_ls;
        }
        return false;
    }
    
    public final Token getNextToken() {
        if (this.token.next != null) {
            this.token = this.token.next;
        }
        else {
            final Token token = this.token;
            final Token nextToken = this.token_source.getNextToken();
            token.next = nextToken;
            this.token = nextToken;
        }
        this.jj_ntk = -1;
        ++this.jj_gen;
        return this.token;
    }
    
    public final Token getToken(final int index) {
        Token t = this.token;
        for (int i = 0; i < index; ++i) {
            if (t.next != null) {
                t = t.next;
            }
            else {
                final Token token = t;
                final Token nextToken = this.token_source.getNextToken();
                token.next = nextToken;
                t = nextToken;
            }
        }
        return t;
    }
    
    private int jj_ntk() {
        final Token next = this.token.next;
        this.jj_nt = next;
        if (next == null) {
            final Token token = this.token;
            final Token nextToken = this.token_source.getNextToken();
            token.next = nextToken;
            return this.jj_ntk = nextToken.kind;
        }
        return this.jj_ntk = this.jj_nt.kind;
    }
    
    private void jj_add_error_token(final int kind, final int pos) {
        if (pos >= 100) {
            return;
        }
        if (pos == this.jj_endpos + 1) {
            this.jj_lasttokens[this.jj_endpos++] = kind;
        }
        else if (this.jj_endpos != 0) {
            this.jj_expentry = new int[this.jj_endpos];
            for (int i = 0; i < this.jj_endpos; ++i) {
                this.jj_expentry[i] = this.jj_lasttokens[i];
            }
        Label_0171:
            for (final int[] oldentry : this.jj_expentries) {
                if (oldentry.length == this.jj_expentry.length) {
                    for (int j = 0; j < this.jj_expentry.length; ++j) {
                        if (oldentry[j] != this.jj_expentry[j]) {
                            continue Label_0171;
                        }
                    }
                    this.jj_expentries.add(this.jj_expentry);
                    break;
                }
            }
            if (pos != 0) {
                this.jj_lasttokens[(this.jj_endpos = pos) - 1] = kind;
            }
        }
    }
    
    public ParseException generateParseException() {
        this.jj_expentries.clear();
        final boolean[] la1tokens = new boolean[36];
        if (this.jj_kind >= 0) {
            la1tokens[this.jj_kind] = true;
            this.jj_kind = -1;
        }
        for (int i = 0; i < 11; ++i) {
            if (this.jj_la1[i] == this.jj_gen) {
                for (int j = 0; j < 32; ++j) {
                    if ((Interpreter.jj_la1_0[i] & 1 << j) != 0x0) {
                        la1tokens[j] = true;
                    }
                    if ((Interpreter.jj_la1_1[i] & 1 << j) != 0x0) {
                        la1tokens[32 + j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < 36; ++i) {
            if (la1tokens[i]) {
                (this.jj_expentry = new int[1])[0] = i;
                this.jj_expentries.add(this.jj_expentry);
            }
        }
        this.jj_endpos = 0;
        this.jj_rescan_token();
        this.jj_add_error_token(0, 0);
        final int[][] exptokseq = new int[this.jj_expentries.size()][];
        for (int k = 0; k < this.jj_expentries.size(); ++k) {
            exptokseq[k] = this.jj_expentries.get(k);
        }
        return new ParseException(this.token, exptokseq, Interpreter.tokenImage);
    }
    
    public final void enable_tracing() {
    }
    
    public final void disable_tracing() {
    }
    
    private void jj_rescan_token() {
        this.jj_rescan = true;
        for (int i = 0; i < 24; ++i) {
            try {
                JJCalls p = this.jj_2_rtns[i];
                do {
                    if (p.gen > this.jj_gen) {
                        this.jj_la = p.arg;
                        final Token first = p.first;
                        this.jj_scanpos = first;
                        this.jj_lastpos = first;
                        switch (i) {
                            case 0: {
                                this.jj_3_1();
                                break;
                            }
                            case 1: {
                                this.jj_3_2();
                                break;
                            }
                            case 2: {
                                this.jj_3_3();
                                break;
                            }
                            case 3: {
                                this.jj_3_4();
                                break;
                            }
                            case 4: {
                                this.jj_3_5();
                                break;
                            }
                            case 5: {
                                this.jj_3_6();
                                break;
                            }
                            case 6: {
                                this.jj_3_7();
                                break;
                            }
                            case 7: {
                                this.jj_3_8();
                                break;
                            }
                            case 8: {
                                this.jj_3_9();
                                break;
                            }
                            case 9: {
                                this.jj_3_10();
                                break;
                            }
                            case 10: {
                                this.jj_3_11();
                                break;
                            }
                            case 11: {
                                this.jj_3_12();
                                break;
                            }
                            case 12: {
                                this.jj_3_13();
                                break;
                            }
                            case 13: {
                                this.jj_3_14();
                                break;
                            }
                            case 14: {
                                this.jj_3_15();
                                break;
                            }
                            case 15: {
                                this.jj_3_16();
                                break;
                            }
                            case 16: {
                                this.jj_3_17();
                                break;
                            }
                            case 17: {
                                this.jj_3_18();
                                break;
                            }
                            case 18: {
                                this.jj_3_19();
                                break;
                            }
                            case 19: {
                                this.jj_3_20();
                                break;
                            }
                            case 20: {
                                this.jj_3_21();
                                break;
                            }
                            case 21: {
                                this.jj_3_22();
                                break;
                            }
                            case 22: {
                                this.jj_3_23();
                                break;
                            }
                            case 23: {
                                this.jj_3_24();
                                break;
                            }
                        }
                    }
                    p = p.next;
                } while (p != null);
            }
            catch (LookaheadSuccess lookaheadSuccess) {}
        }
        this.jj_rescan = false;
    }
    
    private void jj_save(final int index, final int xla) {
        JJCalls p;
        for (p = this.jj_2_rtns[index]; p.gen > this.jj_gen; p = p.next) {
            if (p.next == null) {
                final JJCalls jjCalls = p;
                final JJCalls next = new JJCalls();
                jjCalls.next = next;
                p = next;
                break;
            }
        }
        p.gen = this.jj_gen + xla - this.jj_la;
        p.first = this.token;
        p.arg = xla;
    }
    
    private static final class LookaheadSuccess extends Error
    {
    }
    
    static final class JJCalls
    {
        int gen;
        Token first;
        int arg;
        JJCalls next;
    }
}
