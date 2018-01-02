// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.anno;

import org.jruby.runtime.Visibility;
import org.jruby.CompatVersion;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface JRubyMethod {
    String[] name() default {};
    
    int required() default 0;
    
    int optional() default 0;
    
    boolean rest() default false;
    
    String[] alias() default {};
    
    boolean meta() default false;
    
    boolean module() default false;
    
    boolean frame() default false;
    
    boolean scope() default false;
    
    CompatVersion compat() default CompatVersion.BOTH;
    
    Visibility visibility() default Visibility.PUBLIC;
    
    boolean backtrace() default false;
    
    FrameField[] reads() default {};
    
    FrameField[] writes() default {};
    
    Class[] argTypes() default {};
    
    boolean omit() default false;
    
    boolean notImplemented() default false;
}
