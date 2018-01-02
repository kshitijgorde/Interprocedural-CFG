import java.util.Date;
import java.io.IOException;
import java.text.ParseException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.io.StringBufferInputStream;
import java.util.Stack;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public final class y
{
    public static int[] a;
    public static int[] b;
    public static a3[][] c;
    public static int[][] d;
    public d e;
    public String f;
    public String g;
    public z h;
    public String i;
    public au j;
    
    public y(final d e, final String f, final String g, final z h, final String i) {
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
        if (this.i != null) {
            this.h();
        }
    }
    
    private void h() {
        if (this.i != null) {
            try {
                this.j = new Object() {
                    public au a;
                    public Double b;
                    public Integer c;
                    public Vector d;
                    public String e;
                    public final /* synthetic */ y f;
                    
                    {
                        try {
                            final Stack stack = new Stack<Integer>();
                            stack.push(new Integer(0));
                            final Stack stack2 = new Stack<au>();
                            Object a = null;
                            int n = 1;
                            int n2 = -1;
                            final PushbackInputStream pushbackInputStream = new PushbackInputStream(new StringBufferInputStream(s));
                            final int[] array = { 0 };
                            while (true) {
                                if (n != 0) {
                                    a = this.a(pushbackInputStream, array);
                                    n2 = array[0];
                                }
                                n = 0;
                                if (stack.isEmpty()) {
                                    throw new ParseException("stateStack.isEmpty()", 0);
                                }
                                final int intValue;
                                if ((intValue = stack.peek()) == -1) {
                                    throw new ParseException("state == -1", 0);
                                }
                                final Object object;
                                if ((object = y.d()[intValue][n2]) == null) {
                                    throw new ParseException("Invalid expression: \"" + s + "\" (" + intValue + ").", 0);
                                }
                                if (object.a == 0) {
                                    if (stack2.isEmpty() || !(stack2.peek() instanceof au)) {
                                        throw new ParseException("tokenStack.isEmpty()", 0);
                                    }
                                    this.a = stack2.pop();
                                    break;
                                }
                                else if (object.a == 1) {
                                    if (a == null) {
                                        throw new ParseException("currentToken == null", 0);
                                    }
                                    stack.push(new Integer(object.b));
                                    stack2.push(a);
                                    n = 1;
                                }
                                else {
                                    if (object.a != 2) {
                                        continue;
                                    }
                                    final int b = object.b;
                                    int n3 = y.e()[b];
                                    if (stack2.size() < n3 || stack.size() < n3) {
                                        throw new ParseException("tokenStack.size() < elements", 0);
                                    }
                                    final Vector vector = new Vector<au>(n3);
                                    while (n3-- > 0) {
                                        vector.insertElementAt(stack2.pop(), 0);
                                        stack.pop();
                                    }
                                    if (stack.isEmpty()) {
                                        throw new ParseException("stateStack.isEmpty()", 0);
                                    }
                                    final int intValue2;
                                    if ((intValue2 = stack.peek()) == -1) {
                                        throw new ParseException("state == -1", 0);
                                    }
                                    final int n4;
                                    if ((n4 = y.f()[b]) == -1) {
                                        throw new ParseException("nonterminal == -1", 0);
                                    }
                                    final int n5;
                                    if ((n5 = y.g()[intValue2][n4]) == -1) {
                                        throw new ParseException("nextState == -1", 0);
                                    }
                                    stack2.push(this.a(b, vector));
                                    stack.push(new Integer(n5));
                                }
                            }
                        }
                        catch (IOException ex) {
                            throw new ParseException(ex.getMessage(), 0);
                        }
                        if (this.a == null) {
                            throw new ParseException("FResult == null", 0);
                        }
                    }
                    
                    private au a(final PushbackInputStream pushbackInputStream, final int[] array) throws IOException {
                        Object object = null;
                        array[0] = -1;
                        int read;
                        do {
                            read = pushbackInputStream.read();
                        } while (read != -1 && Character.isWhitespace((char)read));
                        if (read == -1) {
                            array[0] = 0;
                        }
                        else {
                            switch ((char)read) {
                                case '+': {
                                    array[0] = 1;
                                    object = new Object("" + (char)read) {
                                        public au a;
                                        public Double b;
                                        public Integer c;
                                        public Vector d;
                                        public String e;
                                        public final /* synthetic */ y f;
                                        
                                        {
                                            try {
                                                final Stack stack = new Stack<Integer>();
                                                stack.push(new Integer(0));
                                                final Stack stack2 = new Stack<au>();
                                                Object a = null;
                                                int n = 1;
                                                int n2 = -1;
                                                final PushbackInputStream pushbackInputStream = new PushbackInputStream(new StringBufferInputStream(s));
                                                final int[] array = { 0 };
                                                while (true) {
                                                    if (n != 0) {
                                                        a = this.a(pushbackInputStream, array);
                                                        n2 = array[0];
                                                    }
                                                    n = 0;
                                                    if (stack.isEmpty()) {
                                                        throw new ParseException("stateStack.isEmpty()", 0);
                                                    }
                                                    final int intValue;
                                                    if ((intValue = stack.peek()) == -1) {
                                                        throw new ParseException("state == -1", 0);
                                                    }
                                                    final Object object;
                                                    if ((object = y.d()[intValue][n2]) == null) {
                                                        throw new ParseException("Invalid expression: \"" + s + "\" (" + intValue + ").", 0);
                                                    }
                                                    if (object.a == 0) {
                                                        if (stack2.isEmpty() || !(stack2.peek() instanceof au)) {
                                                            throw new ParseException("tokenStack.isEmpty()", 0);
                                                        }
                                                        this.a = stack2.pop();
                                                        break;
                                                    }
                                                    else if (object.a == 1) {
                                                        if (a == null) {
                                                            throw new ParseException("currentToken == null", 0);
                                                        }
                                                        stack.push(new Integer(object.b));
                                                        stack2.push(a);
                                                        n = 1;
                                                    }
                                                    else {
                                                        if (object.a != 2) {
                                                            continue;
                                                        }
                                                        final int b = object.b;
                                                        int n3 = y.e()[b];
                                                        if (stack2.size() < n3 || stack.size() < n3) {
                                                            throw new ParseException("tokenStack.size() < elements", 0);
                                                        }
                                                        final Vector vector = new Vector<au>(n3);
                                                        while (n3-- > 0) {
                                                            vector.insertElementAt(stack2.pop(), 0);
                                                            stack.pop();
                                                        }
                                                        if (stack.isEmpty()) {
                                                            throw new ParseException("stateStack.isEmpty()", 0);
                                                        }
                                                        final int intValue2;
                                                        if ((intValue2 = stack.peek()) == -1) {
                                                            throw new ParseException("state == -1", 0);
                                                        }
                                                        final int n4;
                                                        if ((n4 = y.f()[b]) == -1) {
                                                            throw new ParseException("nonterminal == -1", 0);
                                                        }
                                                        final int n5;
                                                        if ((n5 = y.g()[intValue2][n4]) == -1) {
                                                            throw new ParseException("nextState == -1", 0);
                                                        }
                                                        stack2.push(this.a(b, vector));
                                                        stack.push(new Integer(n5));
                                                    }
                                                }
                                            }
                                            catch (IOException ex) {
                                                throw new ParseException(ex.getMessage(), 0);
                                            }
                                            if (this.a == null) {
                                                throw new ParseException("FResult == null", 0);
                                            }
                                        }
                                    };
                                    break;
                                }
                                case '-': {
                                    array[0] = 2;
                                    object = new Object("" + (char)read) {
                                        public au a;
                                        public Double b;
                                        public Integer c;
                                        public Vector d;
                                        public String e;
                                        public final /* synthetic */ y f;
                                        
                                        {
                                            try {
                                                final Stack stack = new Stack<Integer>();
                                                stack.push(new Integer(0));
                                                final Stack stack2 = new Stack<au>();
                                                Object a = null;
                                                int n = 1;
                                                int n2 = -1;
                                                final PushbackInputStream pushbackInputStream = new PushbackInputStream(new StringBufferInputStream(s));
                                                final int[] array = { 0 };
                                                while (true) {
                                                    if (n != 0) {
                                                        a = this.a(pushbackInputStream, array);
                                                        n2 = array[0];
                                                    }
                                                    n = 0;
                                                    if (stack.isEmpty()) {
                                                        throw new ParseException("stateStack.isEmpty()", 0);
                                                    }
                                                    final int intValue;
                                                    if ((intValue = stack.peek()) == -1) {
                                                        throw new ParseException("state == -1", 0);
                                                    }
                                                    final Object object;
                                                    if ((object = y.d()[intValue][n2]) == null) {
                                                        throw new ParseException("Invalid expression: \"" + s + "\" (" + intValue + ").", 0);
                                                    }
                                                    if (object.a == 0) {
                                                        if (stack2.isEmpty() || !(stack2.peek() instanceof au)) {
                                                            throw new ParseException("tokenStack.isEmpty()", 0);
                                                        }
                                                        this.a = stack2.pop();
                                                        break;
                                                    }
                                                    else if (object.a == 1) {
                                                        if (a == null) {
                                                            throw new ParseException("currentToken == null", 0);
                                                        }
                                                        stack.push(new Integer(object.b));
                                                        stack2.push(a);
                                                        n = 1;
                                                    }
                                                    else {
                                                        if (object.a != 2) {
                                                            continue;
                                                        }
                                                        final int b = object.b;
                                                        int n3 = y.e()[b];
                                                        if (stack2.size() < n3 || stack.size() < n3) {
                                                            throw new ParseException("tokenStack.size() < elements", 0);
                                                        }
                                                        final Vector vector = new Vector<au>(n3);
                                                        while (n3-- > 0) {
                                                            vector.insertElementAt(stack2.pop(), 0);
                                                            stack.pop();
                                                        }
                                                        if (stack.isEmpty()) {
                                                            throw new ParseException("stateStack.isEmpty()", 0);
                                                        }
                                                        final int intValue2;
                                                        if ((intValue2 = stack.peek()) == -1) {
                                                            throw new ParseException("state == -1", 0);
                                                        }
                                                        final int n4;
                                                        if ((n4 = y.f()[b]) == -1) {
                                                            throw new ParseException("nonterminal == -1", 0);
                                                        }
                                                        final int n5;
                                                        if ((n5 = y.g()[intValue2][n4]) == -1) {
                                                            throw new ParseException("nextState == -1", 0);
                                                        }
                                                        stack2.push(this.a(b, vector));
                                                        stack.push(new Integer(n5));
                                                    }
                                                }
                                            }
                                            catch (IOException ex) {
                                                throw new ParseException(ex.getMessage(), 0);
                                            }
                                            if (this.a == null) {
                                                throw new ParseException("FResult == null", 0);
                                            }
                                        }
                                    };
                                    break;
                                }
                                case '*': {
                                    array[0] = 3;
                                    object = new Object("" + (char)read) {
                                        public au a;
                                        public Double b;
                                        public Integer c;
                                        public Vector d;
                                        public String e;
                                        public final /* synthetic */ y f;
                                        
                                        {
                                            try {
                                                final Stack stack = new Stack<Integer>();
                                                stack.push(new Integer(0));
                                                final Stack stack2 = new Stack<au>();
                                                Object a = null;
                                                int n = 1;
                                                int n2 = -1;
                                                final PushbackInputStream pushbackInputStream = new PushbackInputStream(new StringBufferInputStream(s));
                                                final int[] array = { 0 };
                                                while (true) {
                                                    if (n != 0) {
                                                        a = this.a(pushbackInputStream, array);
                                                        n2 = array[0];
                                                    }
                                                    n = 0;
                                                    if (stack.isEmpty()) {
                                                        throw new ParseException("stateStack.isEmpty()", 0);
                                                    }
                                                    final int intValue;
                                                    if ((intValue = stack.peek()) == -1) {
                                                        throw new ParseException("state == -1", 0);
                                                    }
                                                    final Object object;
                                                    if ((object = y.d()[intValue][n2]) == null) {
                                                        throw new ParseException("Invalid expression: \"" + s + "\" (" + intValue + ").", 0);
                                                    }
                                                    if (object.a == 0) {
                                                        if (stack2.isEmpty() || !(stack2.peek() instanceof au)) {
                                                            throw new ParseException("tokenStack.isEmpty()", 0);
                                                        }
                                                        this.a = stack2.pop();
                                                        break;
                                                    }
                                                    else if (object.a == 1) {
                                                        if (a == null) {
                                                            throw new ParseException("currentToken == null", 0);
                                                        }
                                                        stack.push(new Integer(object.b));
                                                        stack2.push(a);
                                                        n = 1;
                                                    }
                                                    else {
                                                        if (object.a != 2) {
                                                            continue;
                                                        }
                                                        final int b = object.b;
                                                        int n3 = y.e()[b];
                                                        if (stack2.size() < n3 || stack.size() < n3) {
                                                            throw new ParseException("tokenStack.size() < elements", 0);
                                                        }
                                                        final Vector vector = new Vector<au>(n3);
                                                        while (n3-- > 0) {
                                                            vector.insertElementAt(stack2.pop(), 0);
                                                            stack.pop();
                                                        }
                                                        if (stack.isEmpty()) {
                                                            throw new ParseException("stateStack.isEmpty()", 0);
                                                        }
                                                        final int intValue2;
                                                        if ((intValue2 = stack.peek()) == -1) {
                                                            throw new ParseException("state == -1", 0);
                                                        }
                                                        final int n4;
                                                        if ((n4 = y.f()[b]) == -1) {
                                                            throw new ParseException("nonterminal == -1", 0);
                                                        }
                                                        final int n5;
                                                        if ((n5 = y.g()[intValue2][n4]) == -1) {
                                                            throw new ParseException("nextState == -1", 0);
                                                        }
                                                        stack2.push(this.a(b, vector));
                                                        stack.push(new Integer(n5));
                                                    }
                                                }
                                            }
                                            catch (IOException ex) {
                                                throw new ParseException(ex.getMessage(), 0);
                                            }
                                            if (this.a == null) {
                                                throw new ParseException("FResult == null", 0);
                                            }
                                        }
                                    };
                                    break;
                                }
                                case '/': {
                                    array[0] = 4;
                                    object = new Object("" + (char)read) {
                                        public au a;
                                        public Double b;
                                        public Integer c;
                                        public Vector d;
                                        public String e;
                                        public final /* synthetic */ y f;
                                        
                                        {
                                            try {
                                                final Stack stack = new Stack<Integer>();
                                                stack.push(new Integer(0));
                                                final Stack stack2 = new Stack<au>();
                                                Object a = null;
                                                int n = 1;
                                                int n2 = -1;
                                                final PushbackInputStream pushbackInputStream = new PushbackInputStream(new StringBufferInputStream(s));
                                                final int[] array = { 0 };
                                                while (true) {
                                                    if (n != 0) {
                                                        a = this.a(pushbackInputStream, array);
                                                        n2 = array[0];
                                                    }
                                                    n = 0;
                                                    if (stack.isEmpty()) {
                                                        throw new ParseException("stateStack.isEmpty()", 0);
                                                    }
                                                    final int intValue;
                                                    if ((intValue = stack.peek()) == -1) {
                                                        throw new ParseException("state == -1", 0);
                                                    }
                                                    final Object object;
                                                    if ((object = y.d()[intValue][n2]) == null) {
                                                        throw new ParseException("Invalid expression: \"" + s + "\" (" + intValue + ").", 0);
                                                    }
                                                    if (object.a == 0) {
                                                        if (stack2.isEmpty() || !(stack2.peek() instanceof au)) {
                                                            throw new ParseException("tokenStack.isEmpty()", 0);
                                                        }
                                                        this.a = stack2.pop();
                                                        break;
                                                    }
                                                    else if (object.a == 1) {
                                                        if (a == null) {
                                                            throw new ParseException("currentToken == null", 0);
                                                        }
                                                        stack.push(new Integer(object.b));
                                                        stack2.push(a);
                                                        n = 1;
                                                    }
                                                    else {
                                                        if (object.a != 2) {
                                                            continue;
                                                        }
                                                        final int b = object.b;
                                                        int n3 = y.e()[b];
                                                        if (stack2.size() < n3 || stack.size() < n3) {
                                                            throw new ParseException("tokenStack.size() < elements", 0);
                                                        }
                                                        final Vector vector = new Vector<au>(n3);
                                                        while (n3-- > 0) {
                                                            vector.insertElementAt(stack2.pop(), 0);
                                                            stack.pop();
                                                        }
                                                        if (stack.isEmpty()) {
                                                            throw new ParseException("stateStack.isEmpty()", 0);
                                                        }
                                                        final int intValue2;
                                                        if ((intValue2 = stack.peek()) == -1) {
                                                            throw new ParseException("state == -1", 0);
                                                        }
                                                        final int n4;
                                                        if ((n4 = y.f()[b]) == -1) {
                                                            throw new ParseException("nonterminal == -1", 0);
                                                        }
                                                        final int n5;
                                                        if ((n5 = y.g()[intValue2][n4]) == -1) {
                                                            throw new ParseException("nextState == -1", 0);
                                                        }
                                                        stack2.push(this.a(b, vector));
                                                        stack.push(new Integer(n5));
                                                    }
                                                }
                                            }
                                            catch (IOException ex) {
                                                throw new ParseException(ex.getMessage(), 0);
                                            }
                                            if (this.a == null) {
                                                throw new ParseException("FResult == null", 0);
                                            }
                                        }
                                    };
                                    break;
                                }
                                case '%': {
                                    array[0] = 5;
                                    object = new Object("" + (char)read) {
                                        public au a;
                                        public Double b;
                                        public Integer c;
                                        public Vector d;
                                        public String e;
                                        public final /* synthetic */ y f;
                                        
                                        {
                                            try {
                                                final Stack stack = new Stack<Integer>();
                                                stack.push(new Integer(0));
                                                final Stack stack2 = new Stack<au>();
                                                Object a = null;
                                                int n = 1;
                                                int n2 = -1;
                                                final PushbackInputStream pushbackInputStream = new PushbackInputStream(new StringBufferInputStream(s));
                                                final int[] array = { 0 };
                                                while (true) {
                                                    if (n != 0) {
                                                        a = this.a(pushbackInputStream, array);
                                                        n2 = array[0];
                                                    }
                                                    n = 0;
                                                    if (stack.isEmpty()) {
                                                        throw new ParseException("stateStack.isEmpty()", 0);
                                                    }
                                                    final int intValue;
                                                    if ((intValue = stack.peek()) == -1) {
                                                        throw new ParseException("state == -1", 0);
                                                    }
                                                    final Object object;
                                                    if ((object = y.d()[intValue][n2]) == null) {
                                                        throw new ParseException("Invalid expression: \"" + s + "\" (" + intValue + ").", 0);
                                                    }
                                                    if (object.a == 0) {
                                                        if (stack2.isEmpty() || !(stack2.peek() instanceof au)) {
                                                            throw new ParseException("tokenStack.isEmpty()", 0);
                                                        }
                                                        this.a = stack2.pop();
                                                        break;
                                                    }
                                                    else if (object.a == 1) {
                                                        if (a == null) {
                                                            throw new ParseException("currentToken == null", 0);
                                                        }
                                                        stack.push(new Integer(object.b));
                                                        stack2.push(a);
                                                        n = 1;
                                                    }
                                                    else {
                                                        if (object.a != 2) {
                                                            continue;
                                                        }
                                                        final int b = object.b;
                                                        int n3 = y.e()[b];
                                                        if (stack2.size() < n3 || stack.size() < n3) {
                                                            throw new ParseException("tokenStack.size() < elements", 0);
                                                        }
                                                        final Vector vector = new Vector<au>(n3);
                                                        while (n3-- > 0) {
                                                            vector.insertElementAt(stack2.pop(), 0);
                                                            stack.pop();
                                                        }
                                                        if (stack.isEmpty()) {
                                                            throw new ParseException("stateStack.isEmpty()", 0);
                                                        }
                                                        final int intValue2;
                                                        if ((intValue2 = stack.peek()) == -1) {
                                                            throw new ParseException("state == -1", 0);
                                                        }
                                                        final int n4;
                                                        if ((n4 = y.f()[b]) == -1) {
                                                            throw new ParseException("nonterminal == -1", 0);
                                                        }
                                                        final int n5;
                                                        if ((n5 = y.g()[intValue2][n4]) == -1) {
                                                            throw new ParseException("nextState == -1", 0);
                                                        }
                                                        stack2.push(this.a(b, vector));
                                                        stack.push(new Integer(n5));
                                                    }
                                                }
                                            }
                                            catch (IOException ex) {
                                                throw new ParseException(ex.getMessage(), 0);
                                            }
                                            if (this.a == null) {
                                                throw new ParseException("FResult == null", 0);
                                            }
                                        }
                                    };
                                    break;
                                }
                                case '(': {
                                    array[0] = 6;
                                    object = new Object("" + (char)read) {
                                        public au a;
                                        public Double b;
                                        public Integer c;
                                        public Vector d;
                                        public String e;
                                        public final /* synthetic */ y f;
                                        
                                        {
                                            try {
                                                final Stack stack = new Stack<Integer>();
                                                stack.push(new Integer(0));
                                                final Stack stack2 = new Stack<au>();
                                                Object a = null;
                                                int n = 1;
                                                int n2 = -1;
                                                final PushbackInputStream pushbackInputStream = new PushbackInputStream(new StringBufferInputStream(s));
                                                final int[] array = { 0 };
                                                while (true) {
                                                    if (n != 0) {
                                                        a = this.a(pushbackInputStream, array);
                                                        n2 = array[0];
                                                    }
                                                    n = 0;
                                                    if (stack.isEmpty()) {
                                                        throw new ParseException("stateStack.isEmpty()", 0);
                                                    }
                                                    final int intValue;
                                                    if ((intValue = stack.peek()) == -1) {
                                                        throw new ParseException("state == -1", 0);
                                                    }
                                                    final Object object;
                                                    if ((object = y.d()[intValue][n2]) == null) {
                                                        throw new ParseException("Invalid expression: \"" + s + "\" (" + intValue + ").", 0);
                                                    }
                                                    if (object.a == 0) {
                                                        if (stack2.isEmpty() || !(stack2.peek() instanceof au)) {
                                                            throw new ParseException("tokenStack.isEmpty()", 0);
                                                        }
                                                        this.a = stack2.pop();
                                                        break;
                                                    }
                                                    else if (object.a == 1) {
                                                        if (a == null) {
                                                            throw new ParseException("currentToken == null", 0);
                                                        }
                                                        stack.push(new Integer(object.b));
                                                        stack2.push(a);
                                                        n = 1;
                                                    }
                                                    else {
                                                        if (object.a != 2) {
                                                            continue;
                                                        }
                                                        final int b = object.b;
                                                        int n3 = y.e()[b];
                                                        if (stack2.size() < n3 || stack.size() < n3) {
                                                            throw new ParseException("tokenStack.size() < elements", 0);
                                                        }
                                                        final Vector vector = new Vector<au>(n3);
                                                        while (n3-- > 0) {
                                                            vector.insertElementAt(stack2.pop(), 0);
                                                            stack.pop();
                                                        }
                                                        if (stack.isEmpty()) {
                                                            throw new ParseException("stateStack.isEmpty()", 0);
                                                        }
                                                        final int intValue2;
                                                        if ((intValue2 = stack.peek()) == -1) {
                                                            throw new ParseException("state == -1", 0);
                                                        }
                                                        final int n4;
                                                        if ((n4 = y.f()[b]) == -1) {
                                                            throw new ParseException("nonterminal == -1", 0);
                                                        }
                                                        final int n5;
                                                        if ((n5 = y.g()[intValue2][n4]) == -1) {
                                                            throw new ParseException("nextState == -1", 0);
                                                        }
                                                        stack2.push(this.a(b, vector));
                                                        stack.push(new Integer(n5));
                                                    }
                                                }
                                            }
                                            catch (IOException ex) {
                                                throw new ParseException(ex.getMessage(), 0);
                                            }
                                            if (this.a == null) {
                                                throw new ParseException("FResult == null", 0);
                                            }
                                        }
                                    };
                                    break;
                                }
                                case ')': {
                                    array[0] = 7;
                                    object = new Object("" + (char)read) {
                                        public au a;
                                        public Double b;
                                        public Integer c;
                                        public Vector d;
                                        public String e;
                                        public final /* synthetic */ y f;
                                        
                                        {
                                            try {
                                                final Stack stack = new Stack<Integer>();
                                                stack.push(new Integer(0));
                                                final Stack stack2 = new Stack<au>();
                                                Object a = null;
                                                int n = 1;
                                                int n2 = -1;
                                                final PushbackInputStream pushbackInputStream = new PushbackInputStream(new StringBufferInputStream(s));
                                                final int[] array = { 0 };
                                                while (true) {
                                                    if (n != 0) {
                                                        a = this.a(pushbackInputStream, array);
                                                        n2 = array[0];
                                                    }
                                                    n = 0;
                                                    if (stack.isEmpty()) {
                                                        throw new ParseException("stateStack.isEmpty()", 0);
                                                    }
                                                    final int intValue;
                                                    if ((intValue = stack.peek()) == -1) {
                                                        throw new ParseException("state == -1", 0);
                                                    }
                                                    final Object object;
                                                    if ((object = y.d()[intValue][n2]) == null) {
                                                        throw new ParseException("Invalid expression: \"" + s + "\" (" + intValue + ").", 0);
                                                    }
                                                    if (object.a == 0) {
                                                        if (stack2.isEmpty() || !(stack2.peek() instanceof au)) {
                                                            throw new ParseException("tokenStack.isEmpty()", 0);
                                                        }
                                                        this.a = stack2.pop();
                                                        break;
                                                    }
                                                    else if (object.a == 1) {
                                                        if (a == null) {
                                                            throw new ParseException("currentToken == null", 0);
                                                        }
                                                        stack.push(new Integer(object.b));
                                                        stack2.push(a);
                                                        n = 1;
                                                    }
                                                    else {
                                                        if (object.a != 2) {
                                                            continue;
                                                        }
                                                        final int b = object.b;
                                                        int n3 = y.e()[b];
                                                        if (stack2.size() < n3 || stack.size() < n3) {
                                                            throw new ParseException("tokenStack.size() < elements", 0);
                                                        }
                                                        final Vector vector = new Vector<au>(n3);
                                                        while (n3-- > 0) {
                                                            vector.insertElementAt(stack2.pop(), 0);
                                                            stack.pop();
                                                        }
                                                        if (stack.isEmpty()) {
                                                            throw new ParseException("stateStack.isEmpty()", 0);
                                                        }
                                                        final int intValue2;
                                                        if ((intValue2 = stack.peek()) == -1) {
                                                            throw new ParseException("state == -1", 0);
                                                        }
                                                        final int n4;
                                                        if ((n4 = y.f()[b]) == -1) {
                                                            throw new ParseException("nonterminal == -1", 0);
                                                        }
                                                        final int n5;
                                                        if ((n5 = y.g()[intValue2][n4]) == -1) {
                                                            throw new ParseException("nextState == -1", 0);
                                                        }
                                                        stack2.push(this.a(b, vector));
                                                        stack.push(new Integer(n5));
                                                    }
                                                }
                                            }
                                            catch (IOException ex) {
                                                throw new ParseException(ex.getMessage(), 0);
                                            }
                                            if (this.a == null) {
                                                throw new ParseException("FResult == null", 0);
                                            }
                                        }
                                    };
                                    break;
                                }
                                default: {
                                    final StringBuffer sb = new StringBuffer();
                                    sb.append((char)read);
                                    int read2;
                                    while ((read2 = pushbackInputStream.read()) != -1) {
                                        if (Character.isWhitespace((char)read2) || read2 == 43 || read2 == 45 || read2 == 42 || read2 == 47 || read2 == 37 || read2 == 40 || read2 == 41) {
                                            pushbackInputStream.unread(read2);
                                            break;
                                        }
                                        sb.append((char)read2);
                                    }
                                    try {
                                        final Double value = Double.valueOf(sb.toString());
                                        array[0] = 8;
                                        object = new Object() {
                                            public au a;
                                            public Double b;
                                            public Integer c;
                                            public Vector d;
                                            public String e;
                                            public final /* synthetic */ y f;
                                            
                                            {
                                                try {
                                                    final Stack stack = new Stack<Integer>();
                                                    stack.push(new Integer(0));
                                                    final Stack stack2 = new Stack<au>();
                                                    Object a = null;
                                                    int n = 1;
                                                    int n2 = -1;
                                                    final PushbackInputStream pushbackInputStream = new PushbackInputStream(new StringBufferInputStream(s));
                                                    final int[] array = { 0 };
                                                    while (true) {
                                                        if (n != 0) {
                                                            a = this.a(pushbackInputStream, array);
                                                            n2 = array[0];
                                                        }
                                                        n = 0;
                                                        if (stack.isEmpty()) {
                                                            throw new ParseException("stateStack.isEmpty()", 0);
                                                        }
                                                        final int intValue;
                                                        if ((intValue = stack.peek()) == -1) {
                                                            throw new ParseException("state == -1", 0);
                                                        }
                                                        final Object object;
                                                        if ((object = y.d()[intValue][n2]) == null) {
                                                            throw new ParseException("Invalid expression: \"" + s + "\" (" + intValue + ").", 0);
                                                        }
                                                        if (object.a == 0) {
                                                            if (stack2.isEmpty() || !(stack2.peek() instanceof au)) {
                                                                throw new ParseException("tokenStack.isEmpty()", 0);
                                                            }
                                                            this.a = stack2.pop();
                                                            break;
                                                        }
                                                        else if (object.a == 1) {
                                                            if (a == null) {
                                                                throw new ParseException("currentToken == null", 0);
                                                            }
                                                            stack.push(new Integer(object.b));
                                                            stack2.push(a);
                                                            n = 1;
                                                        }
                                                        else {
                                                            if (object.a != 2) {
                                                                continue;
                                                            }
                                                            final int b = object.b;
                                                            int n3 = y.e()[b];
                                                            if (stack2.size() < n3 || stack.size() < n3) {
                                                                throw new ParseException("tokenStack.size() < elements", 0);
                                                            }
                                                            final Vector vector = new Vector<au>(n3);
                                                            while (n3-- > 0) {
                                                                vector.insertElementAt(stack2.pop(), 0);
                                                                stack.pop();
                                                            }
                                                            if (stack.isEmpty()) {
                                                                throw new ParseException("stateStack.isEmpty()", 0);
                                                            }
                                                            final int intValue2;
                                                            if ((intValue2 = stack.peek()) == -1) {
                                                                throw new ParseException("state == -1", 0);
                                                            }
                                                            final int n4;
                                                            if ((n4 = y.f()[b]) == -1) {
                                                                throw new ParseException("nonterminal == -1", 0);
                                                            }
                                                            final int n5;
                                                            if ((n5 = y.g()[intValue2][n4]) == -1) {
                                                                throw new ParseException("nextState == -1", 0);
                                                            }
                                                            stack2.push(this.a(b, vector));
                                                            stack.push(new Integer(n5));
                                                        }
                                                    }
                                                }
                                                catch (IOException ex) {
                                                    throw new ParseException(ex.getMessage(), 0);
                                                }
                                                if (this.a == null) {
                                                    throw new ParseException("FResult == null", 0);
                                                }
                                            }
                                        };
                                    }
                                    catch (NumberFormatException ex) {
                                        array[0] = 9;
                                        object = new Object(sb.toString()) {
                                            public au a;
                                            public Double b = new Double(n);
                                            public Integer c;
                                            public Vector d;
                                            public String e;
                                            public final /* synthetic */ y f = y.this;
                                            
                                            {
                                                try {
                                                    final Stack stack = new Stack<Integer>();
                                                    stack.push(new Integer(0));
                                                    final Stack stack2 = new Stack<au>();
                                                    Object a = null;
                                                    int n = 1;
                                                    int n2 = -1;
                                                    final PushbackInputStream pushbackInputStream = new PushbackInputStream(new StringBufferInputStream(s));
                                                    final int[] array = { 0 };
                                                    while (true) {
                                                        if (n != 0) {
                                                            a = this.a(pushbackInputStream, array);
                                                            n2 = array[0];
                                                        }
                                                        n = 0;
                                                        if (stack.isEmpty()) {
                                                            throw new ParseException("stateStack.isEmpty()", 0);
                                                        }
                                                        final int intValue;
                                                        if ((intValue = stack.peek()) == -1) {
                                                            throw new ParseException("state == -1", 0);
                                                        }
                                                        final Object object;
                                                        if ((object = y.d()[intValue][n2]) == null) {
                                                            throw new ParseException("Invalid expression: \"" + s + "\" (" + intValue + ").", 0);
                                                        }
                                                        if (object.a == 0) {
                                                            if (stack2.isEmpty() || !(stack2.peek() instanceof au)) {
                                                                throw new ParseException("tokenStack.isEmpty()", 0);
                                                            }
                                                            this.a = stack2.pop();
                                                            break;
                                                        }
                                                        else if (object.a == 1) {
                                                            if (a == null) {
                                                                throw new ParseException("currentToken == null", 0);
                                                            }
                                                            stack.push(new Integer(object.b));
                                                            stack2.push(a);
                                                            n = 1;
                                                        }
                                                        else {
                                                            if (object.a != 2) {
                                                                continue;
                                                            }
                                                            final int b = object.b;
                                                            int n3 = y.e()[b];
                                                            if (stack2.size() < n3 || stack.size() < n3) {
                                                                throw new ParseException("tokenStack.size() < elements", 0);
                                                            }
                                                            final Vector vector = new Vector<au>(n3);
                                                            while (n3-- > 0) {
                                                                vector.insertElementAt(stack2.pop(), 0);
                                                                stack.pop();
                                                            }
                                                            if (stack.isEmpty()) {
                                                                throw new ParseException("stateStack.isEmpty()", 0);
                                                            }
                                                            final int intValue2;
                                                            if ((intValue2 = stack.peek()) == -1) {
                                                                throw new ParseException("state == -1", 0);
                                                            }
                                                            final int n4;
                                                            if ((n4 = y.f()[b]) == -1) {
                                                                throw new ParseException("nonterminal == -1", 0);
                                                            }
                                                            final int n5;
                                                            if ((n5 = y.g()[intValue2][n4]) == -1) {
                                                                throw new ParseException("nextState == -1", 0);
                                                            }
                                                            stack2.push(this.a(b, vector));
                                                            stack.push(new Integer(n5));
                                                        }
                                                    }
                                                }
                                                catch (IOException ex) {
                                                    throw new ParseException(ex.getMessage(), 0);
                                                }
                                                if (this.a == null) {
                                                    throw new ParseException("FResult == null", 0);
                                                }
                                            }
                                        };
                                    }
                                    break;
                                }
                            }
                        }
                        return object;
                    }
                    
                    private au a(final int n, final Vector vector) throws ParseException {
                        Object object = null;
                        switch (n) {
                            case 0:
                            case 1:
                            case 3:
                            case 4:
                            case 5:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 12:
                            case 13:
                            case 14:
                            case 16:
                            case 17: {
                                object = vector.elementAt(0);
                                break;
                            }
                            case 15: {
                                object = vector.elementAt(1);
                                break;
                            }
                            case 2:
                            case 6: {
                                object = vector.elementAt(1);
                                object.a(vector.elementAt(0), vector.elementAt(2));
                                break;
                            }
                            case 11: {
                                object = vector.elementAt(0);
                                object.a(vector.elementAt(1));
                                break;
                            }
                            default: {
                                object = null;
                                break;
                            }
                        }
                        if (object == null) {
                            throw new ParseException("reduction == null", 0);
                        }
                        return object;
                    }
                    
                    {
                        if (n == 9) {
                            this.e = e;
                        }
                        else {
                            this.c = new Integer(n);
                            this.d = new Vector();
                        }
                    }
                    
                    private void a(final au object, final au object2) {
                        if (this.d != null) {
                            this.d.removeAllElements();
                            this.d.addElement(object);
                            this.d.addElement(object2);
                        }
                        this.a();
                    }
                    
                    private void a(final au object) {
                        if (this.d != null) {
                            this.d.removeAllElements();
                            this.d.addElement(object);
                        }
                        this.a();
                    }
                    
                    private void a() {
                        final Object b;
                        if (this.d != null && (b = this.b(null)) instanceof Number) {
                            this.b = new Double(((Number)b).doubleValue());
                            this.c = null;
                            this.d = null;
                        }
                    }
                    
                    private String a(final aa aa) {
                        final Object b = this.b(aa);
                        return (b == null) ? null : b.toString();
                    }
                    
                    private Object b(final aa aa) {
                        Object o = null;
                        if (this.a != null) {
                            o = this.a.b(aa);
                        }
                        else if (this.b != null) {
                            o = this.b;
                        }
                        else if (this.e != null) {
                            if (aa != null) {
                                o = aa.c(this.e);
                                if (o instanceof Number) {
                                    o = new Double(((Number)o).doubleValue());
                                }
                            }
                        }
                        else if (this.c != null) {
                            switch (this.c) {
                                case 1: {
                                    if (this.d.size() == 1) {
                                        final Object b;
                                        if ((b = this.d.elementAt(0).b(aa)) instanceof Number) {
                                            o = b;
                                            break;
                                        }
                                        break;
                                    }
                                    else {
                                        final Object b2;
                                        final Number b3;
                                        if (this.d.size() == 2 && (b2 = this.d.elementAt(0).b(aa)) instanceof Number && (b3 = (Number)this.d.elementAt(1).b(aa)) instanceof Number) {
                                            o = new Double(((Number)b2).doubleValue() + b3.doubleValue());
                                            break;
                                        }
                                        break;
                                    }
                                    break;
                                }
                                case 2: {
                                    if (this.d.size() == 1) {
                                        final Object b4;
                                        if ((b4 = this.d.elementAt(0).b(aa)) instanceof Number) {
                                            o = new Double(-((Number)b4).doubleValue());
                                            break;
                                        }
                                        break;
                                    }
                                    else {
                                        final Object b5;
                                        final Number b6;
                                        if (this.d.size() == 2 && (b5 = this.d.elementAt(0).b(aa)) instanceof Number && (b6 = (Number)this.d.elementAt(1).b(aa)) instanceof Number) {
                                            o = new Double(((Number)b5).doubleValue() - b6.doubleValue());
                                            break;
                                        }
                                        break;
                                    }
                                    break;
                                }
                                case 3: {
                                    final Object b7;
                                    final Number b8;
                                    if (this.d.size() == 2 && (b7 = this.d.elementAt(0).b(aa)) instanceof Number && (b8 = (Number)this.d.elementAt(1).b(aa)) instanceof Number) {
                                        o = new Double(((Number)b7).doubleValue() * b8.doubleValue());
                                        break;
                                    }
                                    break;
                                }
                                case 4: {
                                    final Object b9;
                                    final Number b10;
                                    if (this.d.size() == 2 && (b9 = this.d.elementAt(0).b(aa)) instanceof Number && (b10 = (Number)this.d.elementAt(1).b(aa)) instanceof Number) {
                                        o = new Double(((Number)b9).doubleValue() / b10.doubleValue());
                                        break;
                                    }
                                    break;
                                }
                                case 5: {
                                    final Object b11;
                                    final Number b12;
                                    if (this.d.size() == 2 && (b11 = this.d.elementAt(0).b(aa)) instanceof Number && (b12 = (Number)this.d.elementAt(1).b(aa)) instanceof Number) {
                                        o = new Double(((Number)b11).doubleValue() % b12.doubleValue());
                                        break;
                                    }
                                    break;
                                }
                            }
                        }
                        return o;
                    }
                };
            }
            catch (ParseException ex) {}
        }
    }
    
    public final String a() {
        String s;
        if ((s = this.g) == null) {
            s = this.f;
        }
        return s;
    }
    
    public final boolean equals(final Object o) {
        return o instanceof y && ((((y)o).f == null && this.f == null) || (((y)o).f != null && ((y)o).f.equals(this.f)));
    }
    
    public final int a(final Object o, final Object o2) {
        int compareTo = 0;
        if (o != null || o2 != null) {
            if (o == null) {
                compareTo = -1;
            }
            else if (o2 == null) {
                compareTo = 1;
            }
            else {
                switch (this.h.c) {
                    case 'T': {
                        for (int min = Math.min(((String)o).length(), ((String)o2).length()), n = 0; n < min && (compareTo = Character.toLowerCase(((String)o).charAt(n)) - Character.toLowerCase(((String)o2).charAt(n))) == 0; ++n) {}
                        if (compareTo == 0) {
                            compareTo = ((String)o).length() - ((String)o2).length();
                            break;
                        }
                        break;
                    }
                    case 'I': {
                        compareTo = ((Number)o).intValue() - ((Number)o2).intValue();
                        break;
                    }
                    case 'N': {
                        final double n2 = ((Number)o).doubleValue() - ((Number)o2).doubleValue();
                        if (n2 >= 0.0) {
                            compareTo = (int)Math.ceil(n2);
                            break;
                        }
                        compareTo = (int)Math.floor(n2);
                        break;
                    }
                    case 'D': {
                        compareTo = (int)(((Date)o).getTime() - ((Date)o2).getTime());
                        break;
                    }
                    case 'M':
                    case 'U': {
                        compareTo = o.toString().compareTo(o2.toString());
                        break;
                    }
                }
            }
        }
        return compareTo;
    }
    
    public final boolean b() {
        return this.i != null;
    }
    
    public final String a(final aa aa) {
        String a = null;
        if (this.j != null) {
            a = this.j.a(aa);
        }
        return a;
    }
    
    public final void c() {
        this.f = null;
        this.g = null;
        if (this.h != null) {
            this.h.a();
            this.h = null;
        }
        this.i = null;
        this.j = null;
        this.e = null;
    }
    
    public static /* synthetic */ a3[][] d() {
        return y.c;
    }
    
    public static /* synthetic */ int[] e() {
        return y.b;
    }
    
    public static /* synthetic */ int[] f() {
        return y.a;
    }
    
    public static /* synthetic */ int[][] g() {
        return y.d;
    }
    
    static {
        y.a = new int[18];
        y.b = new int[18];
        y.c = new a3[24][10];
        y.d = new int[24][9];
        y.a[0] = 0;
        y.b[0] = 1;
        y.a[1] = 1;
        y.b[1] = 1;
        y.a[2] = 1;
        y.b[2] = 3;
        y.a[3] = 2;
        y.b[3] = 1;
        y.a[4] = 2;
        y.b[4] = 1;
        y.a[5] = 3;
        y.b[5] = 1;
        y.a[6] = 3;
        y.b[6] = 3;
        y.a[7] = 4;
        y.b[7] = 1;
        y.a[8] = 4;
        y.b[8] = 1;
        y.a[9] = 4;
        y.b[9] = 1;
        y.a[10] = 5;
        y.b[10] = 1;
        y.a[11] = 5;
        y.b[11] = 2;
        y.a[12] = 6;
        y.b[12] = 1;
        y.a[13] = 6;
        y.b[13] = 1;
        y.a[14] = 7;
        y.b[14] = 1;
        y.a[15] = 7;
        y.b[15] = 3;
        y.a[16] = 8;
        y.b[16] = 1;
        y.a[17] = 8;
        y.b[17] = 1;
        for (int i = 0; i < 24; ++i) {
            for (int j = 0; j < 10; ++j) {
                y.c[i][j] = null;
            }
            switch (i) {
                case 0:
                case 2:
                case 4:
                case 14:
                case 19: {
                    y.c[i][1] = new Object(1, 16) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    y.c[i][2] = new Object(1, 17) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    y.c[i][6] = new Object(1, 19) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    y.c[i][8] = new Object(1, 22) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    y.c[i][9] = new Object(1, 23) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    break;
                }
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 16:
                case 17: {
                    int n = 0;
                    switch (i) {
                        case 6: {
                            n = 7;
                            break;
                        }
                        case 7: {
                            n = 8;
                            break;
                        }
                        case 8: {
                            n = 9;
                            break;
                        }
                        case 9: {
                            n = 3;
                            break;
                        }
                        case 10: {
                            n = 4;
                            break;
                        }
                        case 16: {
                            n = 12;
                            break;
                        }
                        case 17: {
                            n = 13;
                            break;
                        }
                        default: {
                            n = -1;
                            break;
                        }
                    }
                    y.c[i][1] = new Object(2, n) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    y.c[i][2] = new Object(2, n) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    y.c[i][6] = new Object(2, n) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    y.c[i][8] = new Object(2, n) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    y.c[i][9] = new Object(2, n) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    break;
                }
                case 1: {
                    y.c[i][0] = new Object(0, 0) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    y.c[i][1] = new Object(1, 9) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    y.c[i][2] = new Object(1, 10) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    y.c[i][7] = new Object(2, 0) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    break;
                }
                case 3:
                case 11: {
                    int n2 = 0;
                    switch (i) {
                        case 3: {
                            n2 = 2;
                            break;
                        }
                        case 11: {
                            n2 = 1;
                            break;
                        }
                        default: {
                            n2 = -1;
                            break;
                        }
                    }
                    y.c[i][0] = new Object(2, n2) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    y.c[i][1] = new Object(2, n2) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    y.c[i][2] = new Object(2, n2) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    y.c[i][3] = new Object(1, 6) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    y.c[i][4] = new Object(1, 7) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    y.c[i][5] = new Object(1, 8) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    y.c[i][7] = new Object(2, n2) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    break;
                }
                case 5:
                case 12:
                case 13:
                case 15:
                case 18:
                case 21:
                case 22:
                case 23: {
                    int n3 = 0;
                    switch (i) {
                        case 5: {
                            n3 = 6;
                            break;
                        }
                        case 12: {
                            n3 = 5;
                            break;
                        }
                        case 13: {
                            n3 = 10;
                            break;
                        }
                        case 15: {
                            n3 = 11;
                            break;
                        }
                        case 18: {
                            n3 = 14;
                            break;
                        }
                        case 21: {
                            n3 = 15;
                            break;
                        }
                        case 22: {
                            n3 = 16;
                            break;
                        }
                        case 23: {
                            n3 = 17;
                            break;
                        }
                        default: {
                            n3 = -1;
                            break;
                        }
                    }
                    y.c[i][0] = new Object(2, n3) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    y.c[i][1] = new Object(2, n3) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    y.c[i][2] = new Object(2, n3) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    y.c[i][3] = new Object(2, n3) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    y.c[i][4] = new Object(2, n3) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    y.c[i][5] = new Object(2, n3) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    y.c[i][7] = new Object(2, n3) {
                        public final int a;
                        public final int b;
                        
                        {
                            this.a = a;
                            this.b = b;
                        }
                    };
                    break;
                }
                case 20: {
                    y.c[i][7] = new Object(1, 21) {
                        public final int a = a;
                        public final int b = b;
                    };
                    break;
                }
            }
            for (int k = 0; k < 9; ++k) {
                y.d[i][k] = -1;
            }
            switch (i) {
                case 0: {
                    y.d[i][1] = 1;
                    y.d[i][3] = 11;
                    y.d[i][5] = 12;
                    y.d[i][6] = 14;
                    y.d[i][7] = 13;
                    y.d[i][8] = 18;
                    break;
                }
                case 1: {
                    y.d[i][2] = 2;
                    break;
                }
                case 2: {
                    y.d[i][3] = 3;
                    y.d[i][5] = 12;
                    y.d[i][6] = 14;
                    y.d[i][7] = 13;
                    y.d[i][8] = 18;
                    break;
                }
                case 3:
                case 11: {
                    y.d[i][4] = 4;
                    break;
                }
                case 4: {
                    y.d[i][5] = 5;
                    y.d[i][6] = 14;
                    y.d[i][7] = 13;
                    y.d[i][8] = 18;
                    break;
                }
                case 14: {
                    y.d[i][5] = 15;
                    y.d[i][6] = 14;
                    y.d[i][7] = 13;
                    y.d[i][8] = 18;
                    break;
                }
                case 19: {
                    y.d[i][0] = 20;
                    y.d[i][1] = 1;
                    y.d[i][3] = 11;
                    y.d[i][5] = 12;
                    y.d[i][6] = 14;
                    y.d[i][7] = 13;
                    y.d[i][8] = 18;
                    break;
                }
            }
        }
    }
}
