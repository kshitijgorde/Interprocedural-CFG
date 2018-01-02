// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext;

import jline.FileNameCompletor;
import java.util.Collections;
import java.nio.CharBuffer;
import org.jruby.runtime.Block;
import org.jruby.RubyNumeric;
import java.util.Iterator;
import java.util.List;
import java.lang.reflect.Field;
import java.util.ArrayList;
import jline.CursorBuffer;
import org.jruby.RubyArray;
import org.jruby.CompatVersion;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.RubyString;
import org.jruby.util.ByteList;
import org.jruby.RubyIO;
import org.jruby.runtime.ThreadContext;
import jline.Completor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import jline.History;
import jline.CandidateListCompletionHandler;
import jline.ConsoleReader;
import java.io.IOException;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyModule;

@JRubyModule(name = { "Readline" })
public class Readline
{
    public static final char ESC_KEY_CODE = '\u001b';
    private static final boolean DEBUG = false;
    private static IRubyObject COMPLETION_CASE_FOLD;
    
    public static void createReadline(final Ruby runtime) throws IOException {
        final ConsoleHolder holder = new ConsoleHolder();
        holder.history = new ReadlineHistory();
        holder.currentCompletor = null;
        Readline.COMPLETION_CASE_FOLD = runtime.getNil();
        final RubyModule mReadline = runtime.defineModule("Readline");
        mReadline.dataWrapStruct(holder);
        mReadline.defineAnnotatedMethods(Readline.class);
        final IRubyObject hist = runtime.getObject().callMethod(runtime.getCurrentContext(), "new");
        mReadline.fastSetConstant("HISTORY", hist);
        hist.getSingletonClass().includeModule(runtime.getEnumerable());
        hist.getSingletonClass().defineAnnotatedMethods(HistoryMethods.class);
        mReadline.fastSetConstant("VERSION", runtime.newString("JLine wrapper"));
    }
    
    protected static void initReadline(final Ruby runtime, final ConsoleHolder holder) {
        try {
            holder.readline = new ConsoleReader();
        }
        catch (IOException ioe) {
            throw runtime.newIOErrorFromException(ioe);
        }
        holder.readline.setUseHistory(false);
        holder.readline.setUsePagination(true);
        holder.readline.setBellEnabled(true);
        ((CandidateListCompletionHandler)holder.readline.getCompletionHandler()).setAlwaysIncludeNewline(false);
        if (holder.currentCompletor == null) {
            holder.currentCompletor = (Completor)new RubyFileNameCompletor();
        }
        holder.readline.addCompletor(holder.currentCompletor);
        holder.readline.setHistory((History)holder.history);
        holder.readline.addTriggeredAction('\u001b', (ActionListener)new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    holder.readline.beep();
                }
                catch (IOException ex) {}
            }
        });
    }
    
    public static History getHistory(final ConsoleHolder holder) {
        return holder.history;
    }
    
    public static ConsoleHolder getHolder(final Ruby runtime) {
        return (ConsoleHolder)runtime.fastGetModule("Readline").dataGetStruct();
    }
    
    public static void setCompletor(final ConsoleHolder holder, final Completor completor) {
        if (holder.readline != null) {
            holder.readline.removeCompletor(holder.currentCompletor);
        }
        holder.currentCompletor = completor;
        if (holder.readline != null) {
            holder.readline.addCompletor(holder.currentCompletor);
        }
    }
    
    public static Completor getCompletor(final ConsoleHolder holder) {
        return holder.currentCompletor;
    }
    
    public static IRubyObject s_readline(final IRubyObject recv, final IRubyObject prompt, final IRubyObject add_to_hist) {
        return s_readline(recv.getRuntime().getCurrentContext(), recv, prompt, add_to_hist);
    }
    
    @JRubyMethod(name = { "readline" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject s_readline(final ThreadContext context, final IRubyObject recv, final IRubyObject prompt, final IRubyObject add_to_hist) {
        final Ruby runtime = context.getRuntime();
        final ConsoleHolder holder = getHolder(runtime);
        if (holder.readline == null) {
            initReadline(runtime, holder);
        }
        IRubyObject line = runtime.getNil();
        String v = null;
        while (true) {
            try {
                holder.readline.getTerminal().disableEcho();
                v = holder.readline.readLine(prompt.toString());
            }
            catch (IOException ioe) {
                if (RubyIO.restartSystemCall(ioe)) {
                    try {
                        holder.readline.getTerminal().initializeTerminal();
                    }
                    catch (Exception ex) {}
                    holder.readline.getTerminal().enableEcho();
                    continue;
                }
                throw runtime.newIOErrorFromException(ioe);
            }
            finally {
                holder.readline.getTerminal().enableEcho();
            }
            break;
        }
        if (null != v) {
            if (add_to_hist.isTrue()) {
                holder.readline.getHistory().addToHistory(v);
            }
            if (runtime.is1_9()) {
                final ByteList list = new ByteList(v.getBytes(), runtime.getDefaultExternalEncoding());
                line = RubyString.newString(runtime, list);
            }
            else {
                line = RubyString.newUnicodeString(recv.getRuntime(), v);
            }
        }
        return line;
    }
    
    @JRubyMethod(name = { "input=" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject setInput(final ThreadContext context, final IRubyObject recv, final IRubyObject input) {
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "output=" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject setOutput(final ThreadContext context, final IRubyObject recv, final IRubyObject output) {
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "readline" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject s_readline(final IRubyObject recv, final IRubyObject prompt) {
        return s_readline(recv, prompt, recv.getRuntime().getFalse());
    }
    
    @JRubyMethod(name = { "readline" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject s_readline(final IRubyObject recv) {
        return s_readline(recv, RubyString.newEmptyString(recv.getRuntime()), recv.getRuntime().getFalse());
    }
    
    @JRubyMethod(name = { "basic_word_break_characters=" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject s_set_basic_word_break_character(final IRubyObject recv, final IRubyObject achar) {
        final Ruby runtime = recv.getRuntime();
        if (!achar.respondsTo("to_str")) {
            throw runtime.newTypeError("can't convert " + achar.getMetaClass() + " into String");
        }
        ProcCompletor.setDelimiter(achar.convertToString().toString());
        return achar;
    }
    
    @JRubyMethod(name = { "basic_word_break_characters" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject s_get_basic_word_break_character(final IRubyObject recv) {
        return recv.getRuntime().newString(ProcCompletor.getDelimiter());
    }
    
    @JRubyMethod(name = { "completion_append_character=" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject s_set_completion_append_character(final IRubyObject recv, final IRubyObject achar) {
        return recv.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "completion_proc=" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject s_set_completion_proc(final IRubyObject recv, final IRubyObject proc) {
        if (!proc.respondsTo("call")) {
            throw recv.getRuntime().newArgumentError("argument must respond to call");
        }
        setCompletor(getHolder(recv.getRuntime()), (Completor)new ProcCompletor(proc));
        return recv.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "basic_quote_characters", "basic_quote_characters=", "completer_quote_characters", "completer_quote_characters=", "completer_word_break_characters", "completer_word_break_characters=", "completion_append_character", "completion_proc", "emacs_editing_mode", "filename_quote_characters", "filename_quote_characters=", "vi_editing_mode" }, frame = true, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject unimplemented(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        runtime.secure(4);
        final String err = context.getFrameName() + "() function is unimplemented on this machine";
        throw runtime.newNotImplementedError(err);
    }
    
    @JRubyMethod(name = { "basic_quote_characters", "basic_quote_characters=", "completer_quote_characters", "completer_quote_characters=", "completer_word_break_characters", "completer_word_break_characters=", "completion_append_character", "completion_proc", "emacs_editing_mode", "emacs_editing_mode?", "filename_quote_characters", "filename_quote_characters=", "vi_editing_mode", "vi_editing_mode?", "set_screen_size" }, frame = true, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject unimplemented19(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        runtime.secure(4);
        final String err = context.getFrameName() + "() function is unimplemented on this machine";
        throw runtime.newNotImplementedError(err);
    }
    
    @JRubyMethod(name = { "completion_case_fold" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject s_get_completion_case_fold(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        runtime.secure(4);
        return Readline.COMPLETION_CASE_FOLD;
    }
    
    @JRubyMethod(name = { "completion_case_fold=" }, required = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject s_set_completion_case_fold(final ThreadContext context, final IRubyObject recv, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        runtime.secure(4);
        return Readline.COMPLETION_CASE_FOLD = other;
    }
    
    @JRubyMethod(name = { "get_screen_size" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject s_get_screen_size(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        runtime.secure(4);
        final ConsoleHolder holder = getHolder(runtime);
        final IRubyObject[] ary = { runtime.newFixnum(holder.readline.getTermheight()), runtime.newFixnum(holder.readline.getTermwidth()) };
        return RubyArray.newArray(runtime, ary);
    }
    
    @JRubyMethod(name = { "line_buffer" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject s_get_line_buffer(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        runtime.secure(4);
        final ConsoleHolder holder = getHolder(runtime);
        if (holder.readline == null) {
            initReadline(runtime, holder);
        }
        final CursorBuffer cb = holder.readline.getCursorBuffer();
        return runtime.newString(cb.toString()).taint(context);
    }
    
    @JRubyMethod(name = { "point" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject s_get_point(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        runtime.secure(4);
        final ConsoleHolder holder = getHolder(runtime);
        if (holder.readline == null) {
            initReadline(runtime, holder);
        }
        final CursorBuffer cb = holder.readline.getCursorBuffer();
        return runtime.newFixnum(cb.cursor);
    }
    
    @JRubyMethod(name = { "refresh_line" }, module = true, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public static IRubyObject s_refresh_line(final ThreadContext context, final IRubyObject recv) {
        final Ruby runtime = context.getRuntime();
        runtime.secure(4);
        final ConsoleHolder holder = getHolder(runtime);
        try {
            holder.readline.redrawLine();
        }
        catch (IOException ioe) {
            throw runtime.newIOErrorFromException(ioe);
        }
        return runtime.getNil();
    }
    
    static {
        Readline.COMPLETION_CASE_FOLD = null;
    }
    
    public static class ReadlineHistory extends History
    {
        ArrayList historyList;
        Field index;
        private boolean securityRestricted;
        
        public ReadlineHistory() {
            this.historyList = null;
            this.index = null;
            this.securityRestricted = false;
            try {
                final Field list = History.class.getDeclaredField("history");
                list.setAccessible(true);
                this.historyList = (ArrayList)list.get(this);
                (this.index = History.class.getDeclaredField("currentIndex")).setAccessible(true);
            }
            catch (NoSuchFieldException ex) {
                ex.printStackTrace();
            }
            catch (SecurityException ex4) {
                this.securityRestricted = true;
            }
            catch (IllegalArgumentException ex2) {
                ex2.printStackTrace();
            }
            catch (IllegalAccessException ex3) {
                ex3.printStackTrace();
            }
        }
        
        public void setCurrentIndex(final int i) {
            if (this.securityRestricted) {
                return;
            }
            try {
                this.index.setInt(this, i);
            }
            catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
            catch (IllegalAccessException ex2) {
                ex2.printStackTrace();
            }
        }
        
        public void set(final int i, final String s) {
            if (this.securityRestricted) {
                return;
            }
            this.historyList.set(i, s);
        }
        
        public String pop() {
            if (this.securityRestricted) {
                final List histList = this.getHistoryList();
                return histList.get(histList.size() - 1);
            }
            return this.remove(this.historyList.size() - 1);
        }
        
        public String remove(final int i) {
            if (this.securityRestricted) {
                return "";
            }
            this.setCurrentIndex(this.historyList.size() - 2);
            return this.historyList.remove(i);
        }
    }
    
    public static class ConsoleHolder
    {
        public ConsoleReader readline;
        public Completor currentCompletor;
        public ReadlineHistory history;
    }
    
    public static class HistoryMethods
    {
        @JRubyMethod(name = { "push", "<<" }, rest = true)
        public static IRubyObject s_push(final IRubyObject recv, final IRubyObject[] lines) {
            final ConsoleHolder holder = Readline.getHolder(recv.getRuntime());
            for (int i = 0; i < lines.length; ++i) {
                final RubyString line = lines[i].convertToString();
                holder.history.addToHistory(line.getUnicodeValue());
            }
            return recv.getRuntime().getNil();
        }
        
        @JRubyMethod(name = { "pop" })
        public static IRubyObject s_pop(final IRubyObject recv) {
            final Ruby runtime = recv.getRuntime();
            final ConsoleHolder holder = Readline.getHolder(runtime);
            if (holder.history.size() == 0) {
                return runtime.getNil();
            }
            return runtime.newString(holder.history.pop()).taint(runtime.getCurrentContext());
        }
        
        @JRubyMethod(name = { "to_a" })
        public static IRubyObject s_hist_to_a(final IRubyObject recv) {
            final Ruby runtime = recv.getRuntime();
            final ConsoleHolder holder = Readline.getHolder(runtime);
            final RubyArray histList = runtime.newArray();
            final Iterator i = holder.history.getHistoryList().iterator();
            while (i.hasNext()) {
                histList.append(runtime.newString(i.next()));
            }
            return histList;
        }
        
        @JRubyMethod(name = { "to_s" })
        public static IRubyObject s_hist_to_s(final IRubyObject recv) {
            return recv.getRuntime().newString("HISTORY");
        }
        
        @JRubyMethod(name = { "[]" })
        public static IRubyObject s_hist_get(final IRubyObject recv, final IRubyObject index) {
            final Ruby runtime = recv.getRuntime();
            final ConsoleHolder holder = Readline.getHolder(runtime);
            int i = (int)index.convertToInteger().getLongValue();
            if (i < 0) {
                i += holder.history.size();
            }
            try {
                final ThreadContext context = runtime.getCurrentContext();
                return runtime.newString(holder.history.getHistoryList().get(i)).taint(context);
            }
            catch (IndexOutOfBoundsException ioobe) {
                throw runtime.newIndexError("invalid history index: " + i);
            }
        }
        
        @JRubyMethod(name = { "[]=" })
        public static IRubyObject s_hist_set(final IRubyObject recv, final IRubyObject index, final IRubyObject val) {
            final Ruby runtime = recv.getRuntime();
            final ConsoleHolder holder = Readline.getHolder(runtime);
            int i = (int)index.convertToInteger().getLongValue();
            if (i < 0) {
                i += holder.history.size();
            }
            try {
                holder.history.set(i, val.asJavaString());
            }
            catch (IndexOutOfBoundsException ioobe) {
                throw runtime.newIndexError("invalid history index: " + i);
            }
            return runtime.getNil();
        }
        
        @JRubyMethod(name = { "shift" })
        public static IRubyObject s_hist_shift(final IRubyObject recv) {
            final Ruby runtime = recv.getRuntime();
            final ConsoleHolder holder = Readline.getHolder(runtime);
            if (holder.history.size() == 0) {
                return runtime.getNil();
            }
            try {
                return runtime.newString(holder.history.remove(0)).taint(runtime.getCurrentContext());
            }
            catch (IndexOutOfBoundsException ioobe) {
                throw runtime.newIndexError("history shift error");
            }
        }
        
        @JRubyMethod(name = { "length", "size" })
        public static IRubyObject s_hist_length(final IRubyObject recv) {
            final ConsoleHolder holder = Readline.getHolder(recv.getRuntime());
            return recv.getRuntime().newFixnum(holder.history.size());
        }
        
        @JRubyMethod(name = { "empty?" })
        public static IRubyObject s_hist_empty_p(final IRubyObject recv) {
            final ConsoleHolder holder = Readline.getHolder(recv.getRuntime());
            return recv.getRuntime().newBoolean(holder.history.size() == 0);
        }
        
        @JRubyMethod(name = { "delete_at" })
        public static IRubyObject s_hist_delete_at(final IRubyObject recv, final IRubyObject index) {
            final Ruby runtime = recv.getRuntime();
            final ThreadContext context = runtime.getCurrentContext();
            final ConsoleHolder holder = Readline.getHolder(runtime);
            int i = RubyNumeric.num2int(index);
            if (i < 0) {
                i += holder.history.size();
            }
            try {
                return runtime.newString(holder.history.remove(i)).taint(context);
            }
            catch (IndexOutOfBoundsException ioobe) {
                throw runtime.newIndexError("invalid history index: " + i);
            }
        }
        
        @JRubyMethod(name = { "each" })
        public static IRubyObject s_hist_each(final IRubyObject recv, final Block block) {
            final Ruby runtime = recv.getRuntime();
            final ThreadContext context = runtime.getCurrentContext();
            final ConsoleHolder holder = Readline.getHolder(runtime);
            final Iterator i = holder.history.getHistoryList().iterator();
            while (i.hasNext()) {
                block.yield(context, runtime.newString(i.next()).taint(context));
            }
            return recv;
        }
    }
    
    public static class ProcCompletor implements Completor
    {
        IRubyObject procCompletor;
        private static String[] delimiters;
        
        public ProcCompletor(final IRubyObject procCompletor) {
            this.procCompletor = procCompletor;
        }
        
        public static String getDelimiter() {
            final StringBuilder result = new StringBuilder(ProcCompletor.delimiters.length);
            for (final String delimiter : ProcCompletor.delimiters) {
                result.append(delimiter);
            }
            return result.toString();
        }
        
        public static void setDelimiter(final String delimiter) {
            final List<String> l = new ArrayList<String>();
            final CharBuffer buf = CharBuffer.wrap(delimiter);
            while (buf.hasRemaining()) {
                l.add(String.valueOf(buf.get()));
            }
            ProcCompletor.delimiters = l.toArray(new String[l.size()]);
        }
        
        private int wordIndexOf(final String buffer) {
            int index = 0;
            for (final String c : ProcCompletor.delimiters) {
                index = buffer.lastIndexOf(c);
                if (index != -1) {
                    return index;
                }
            }
            return index;
        }
        
        public int complete(String buffer, final int cursor, final List candidates) {
            buffer = buffer.substring(0, cursor);
            final int index = this.wordIndexOf(buffer);
            if (index != -1) {
                buffer = buffer.substring(index + 1);
            }
            final Ruby runtime = this.procCompletor.getRuntime();
            final ThreadContext context = runtime.getCurrentContext();
            final IRubyObject result = this.procCompletor.callMethod(context, "call", runtime.newString(buffer));
            final IRubyObject comps = result.callMethod(context, "to_a");
            if (comps instanceof List) {
                for (final Object obj : (List)comps) {
                    if (obj != null) {
                        candidates.add(obj.toString());
                    }
                }
                Collections.sort(candidates);
            }
            return cursor - buffer.length();
        }
        
        static {
            ProcCompletor.delimiters = new String[] { " ", "\t", "\n", "\"", "\\", "'", "`", "@", "$", ">", "<", "=", ";", "|", "&", "{", "(" };
        }
    }
    
    public static class RubyFileNameCompletor extends FileNameCompletor
    {
        public int complete(String buffer, final int cursor, final List candidates) {
            buffer = buffer.substring(0, cursor);
            final int index = buffer.lastIndexOf(" ");
            if (index != -1) {
                buffer = buffer.substring(index + 1);
            }
            return index + 1 + super.complete(buffer, cursor, candidates);
        }
    }
}
