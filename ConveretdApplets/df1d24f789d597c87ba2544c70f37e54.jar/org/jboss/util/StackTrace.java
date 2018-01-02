// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.util.ArrayList;
import java.io.Reader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.Iterator;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import org.jboss.util.stream.Printable;
import java.io.Serializable;

public final class StackTrace implements Serializable, Cloneable, Printable
{
    static final long serialVersionUID = -6077429788585907990L;
    public static final int UNLIMITED = 0;
    private static final String EMPTY_PREFIX = "";
    protected final List stack;
    
    public StackTrace(final Throwable detail, final int level, final int limit) {
        if (level < 0) {
            throw new IllegalArgumentException("level < 0");
        }
        if (limit < 0) {
            throw new IllegalArgumentException("limit < 0");
        }
        try {
            final Parser parser = Parser.getInstance();
            this.stack = parser.parse(detail, level, limit);
        }
        catch (InstantiationException e) {
            throw new NestedRuntimeException(e);
        }
        catch (IOException e2) {
            throw new NestedRuntimeException(e2);
        }
    }
    
    public StackTrace(final Throwable detail, final int level) {
        this(detail, level, 0);
    }
    
    public StackTrace(final Throwable detail) {
        this(detail, 0, 0);
    }
    
    public StackTrace(final int level, final int limit) {
        this(new Throwable(), level + 1, limit);
    }
    
    public StackTrace(final int level) {
        this(new Throwable(), level + 1, 0);
    }
    
    public StackTrace() {
        this(new Throwable(), 1, 0);
    }
    
    protected StackTrace(final List stack) {
        this.stack = stack;
    }
    
    public boolean equals(final Object obj) {
        return obj == this || (obj != null && obj.getClass() == this.getClass() && ((StackTrace)obj).stack.equals(this.stack));
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }
    
    public Entry getEntry(final int level) {
        return this.stack.get(level);
    }
    
    public Entry getCallerEntry() {
        return this.getEntry(0);
    }
    
    public Entry getRootEntry() {
        return this.getEntry(this.stack.size() - 1);
    }
    
    public StackTrace getSubTrace(final int level) {
        return new StackTrace(this.stack.subList(level, this.stack.size()));
    }
    
    public StackTrace getSubTrace(final int level, int limit) {
        if (limit > 0) {
            limit = Math.min(level + limit, this.stack.size());
        }
        else {
            limit = this.stack.size();
        }
        return new StackTrace(this.stack.subList(level, limit));
    }
    
    public StackTrace getCallerTrace() {
        return this.getSubTrace(1);
    }
    
    public void print(final PrintWriter writer, final String prefix) {
        for (final Entry entry : this.stack) {
            entry.print(writer, prefix);
        }
    }
    
    public void print(final PrintWriter writer) {
        this.print(writer, "");
    }
    
    public void print(final PrintStream stream, final String prefix) {
        for (final Entry entry : this.stack) {
            entry.print(stream, prefix);
        }
    }
    
    public void print(final PrintStream stream) {
        this.print(stream, "");
    }
    
    public void print(final String prefix) {
        this.print(System.err, prefix);
    }
    
    public void print() {
        this.print(System.err);
    }
    
    public Iterator iterator() {
        return this.stack.iterator();
    }
    
    public int size() {
        return this.stack.size();
    }
    
    public static final Entry currentEntry() {
        return new StackTrace().getCallerEntry();
    }
    
    public static final Entry callerEntry() {
        return new StackTrace(1).getCallerEntry();
    }
    
    public static final Entry rootEntry() {
        return new StackTrace().getRootEntry();
    }
    
    public static final class Entry implements Cloneable, Serializable, Printable
    {
        static final long serialVersionUID = 7013023772762859280L;
        public static final String UNKNOWN = "<unknown>";
        public static final String DEFAULT = "<default>";
        protected String className;
        protected String methodName;
        protected String sourceFileName;
        protected String lineNumber;
        
        public Entry(final String className, final String methodName, final String sourceFileName, final String lineNumber) {
            this.className = "<unknown>";
            this.methodName = "<unknown>";
            this.sourceFileName = "<unknown>";
            this.lineNumber = "<unknown>";
            this.className = className;
            this.methodName = methodName;
            this.sourceFileName = sourceFileName;
            this.lineNumber = lineNumber;
        }
        
        public Entry(final String raw) {
            this.className = "<unknown>";
            this.methodName = "<unknown>";
            this.sourceFileName = "<unknown>";
            this.lineNumber = "<unknown>";
            this.parse(raw);
        }
        
        protected void parse(final String raw) {
            int j = raw.indexOf("at ") + 3;
            int i = raw.indexOf("(");
            if (j == -1 || i == -1) {
                return;
            }
            final String temp = raw.substring(j, i);
            i = temp.lastIndexOf(".");
            if (i == -1) {
                return;
            }
            this.className = temp.substring(0, i);
            this.methodName = temp.substring(i + 1);
            j = raw.indexOf("(") + 1;
            i = raw.indexOf(":");
            if (j == -1) {
                return;
            }
            if (i == -1) {
                i = raw.indexOf(")");
                if (i == -1) {
                    return;
                }
                this.sourceFileName = raw.substring(j, i);
            }
            else {
                this.sourceFileName = raw.substring(j, i);
                j = i + 1;
                i = raw.lastIndexOf(")");
                if (i != -1) {
                    this.lineNumber = raw.substring(j, i);
                }
                else {
                    this.lineNumber = raw.substring(j);
                }
            }
        }
        
        public String getClassName() {
            return this.className;
        }
        
        public String getShortClassName() {
            return Classes.stripPackageName(this.className);
        }
        
        public String getMethodName() {
            return this.methodName;
        }
        
        public String getFullMethodName() {
            return this.className + "." + this.methodName;
        }
        
        public String getSourceFileName() {
            return this.sourceFileName;
        }
        
        public String getLineNumber() {
            return this.lineNumber;
        }
        
        public String toString(final String prefix) {
            final StringBuffer buff = new StringBuffer();
            if (prefix != null) {
                buff.append(prefix);
            }
            buff.append(this.className).append(".").append(this.methodName).append("(").append(this.sourceFileName);
            if (!this.lineNumber.equals("<unknown>")) {
                buff.append(":").append(this.lineNumber);
            }
            buff.append(")");
            return buff.toString();
        }
        
        public String toString() {
            return this.toString("");
        }
        
        public int hashCode() {
            return HashCode.generate(new String[] { this.className, this.methodName, this.sourceFileName, this.lineNumber });
        }
        
        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj != null && obj.getClass() == this.getClass()) {
                final Entry entry = (Entry)obj;
                return entry.className.equals(this.className) && entry.methodName.equals(this.methodName) && entry.sourceFileName.equals(this.sourceFileName) && entry.lineNumber.equals(this.lineNumber);
            }
            return false;
        }
        
        public Object clone() {
            try {
                return super.clone();
            }
            catch (CloneNotSupportedException e) {
                throw new InternalError();
            }
        }
        
        public void print(final PrintWriter writer, final String prefix) {
            writer.println(this.toString(prefix));
        }
        
        public void print(final PrintWriter writer) {
            writer.println(this.toString());
        }
        
        public void print(final PrintStream stream, final String prefix) {
            stream.println(this.toString(prefix));
        }
        
        public void print(final PrintStream stream) {
            stream.println(this.toString());
        }
        
        public void print(final String prefix) {
            this.print(System.err, prefix);
        }
        
        public void print() {
            this.print(System.err);
        }
    }
    
    public static class Parser
    {
        private static Parser instance;
        
        protected void skipDescription(final BufferedReader reader) throws IOException {
            reader.readLine();
        }
        
        protected void setLevel(final BufferedReader reader, final int level) throws IOException {
            for (int i = 0; i < level; ++i) {
                reader.readLine();
            }
        }
        
        protected byte[] readBytes(final Throwable detail) throws IOException {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final PrintStream ps = new PrintStream(baos);
            try {
                detail.printStackTrace(ps);
            }
            finally {
                ps.close();
            }
            return baos.toByteArray();
        }
        
        protected BufferedReader createReader(final Throwable detail) throws IOException {
            final byte[] bytes = this.readBytes(detail);
            final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            final InputStreamReader reader = new InputStreamReader(bais);
            return new BufferedReader(reader);
        }
        
        public List parse(final Throwable detail, final int level, final int limit) throws IOException {
            final BufferedReader reader = this.createReader(detail);
            this.skipDescription(reader);
            this.setLevel(reader, level);
            final List list = new ArrayList();
            int count = 0;
            String raw;
            while ((raw = reader.readLine()) != null) {
                final Entry entry = this.createEntry(raw);
                list.add(entry);
                if (limit > 0 && ++count >= limit) {
                    break;
                }
            }
            return list;
        }
        
        protected Entry createEntry(final String raw) throws IOException {
            return new Entry(raw);
        }
        
        public static final synchronized Parser getInstance() throws InstantiationException {
            if (Parser.instance == null) {
                Parser.instance = new Parser();
            }
            return Parser.instance;
        }
        
        static {
            Parser.instance = null;
        }
    }
}
