// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public abstract class FJTask implements Runnable
{
    private volatile boolean done;
    
    public void cancel() {
        this.setDone();
    }
    
    public static void coInvoke(final FJTask fjTask, final FJTask fjTask2) {
        getFJTaskRunner().coInvoke(fjTask, fjTask2);
    }
    
    public static void coInvoke(final FJTask[] array) {
        getFJTaskRunner().coInvoke(array);
    }
    
    public void fork() {
        getFJTaskRunner().push(this);
    }
    
    public static FJTaskRunner getFJTaskRunner() {
        return (FJTaskRunner)Thread.currentThread();
    }
    
    public static FJTaskRunnerGroup getFJTaskRunnerGroup() {
        return getFJTaskRunner().getGroup();
    }
    
    public static void invoke(final FJTask fjTask) {
        if (!fjTask.isDone()) {
            fjTask.run();
            fjTask.setDone();
        }
    }
    
    public final boolean isDone() {
        return this.done;
    }
    
    public void join() {
        getFJTaskRunner().taskJoin(this);
    }
    
    public static FJTask par(final FJTask fjTask, final FJTask fjTask2) {
        return new Par2(fjTask, fjTask2);
    }
    
    public static FJTask par(final FJTask[] array) {
        return new Par(array);
    }
    
    public void reset() {
        this.done = false;
    }
    
    public abstract void run();
    
    public static FJTask seq(final FJTask fjTask, final FJTask fjTask2) {
        return new Seq2(fjTask, fjTask2);
    }
    
    public static FJTask seq(final FJTask[] array) {
        return new Seq(array);
    }
    
    protected final void setDone() {
        this.done = true;
    }
    
    public void start() {
        getFJTaskRunnerGroup().executeTask(this);
    }
    
    public static void yield() {
        getFJTaskRunner().taskYield();
    }
    
    public static class Wrap extends FJTask
    {
        protected final Runnable runnable;
        
        public Wrap(final Runnable runnable) {
            this.runnable = runnable;
        }
        
        public void run() {
            this.runnable.run();
        }
    }
    
    public static class Seq extends FJTask
    {
        protected final FJTask[] tasks;
        
        public Seq(final FJTask fjTask, final FJTask fjTask2) {
            this.tasks = new FJTask[] { fjTask, fjTask2 };
        }
        
        public Seq(final FJTask[] tasks) {
            this.tasks = tasks;
        }
        
        public void run() {
            for (int i = 0; i < this.tasks.length; ++i) {
                FJTask.invoke(this.tasks[i]);
            }
        }
    }
    
    public static class Par extends FJTask
    {
        protected final FJTask[] tasks;
        
        public Par(final FJTask fjTask, final FJTask fjTask2) {
            this.tasks = new FJTask[] { fjTask, fjTask2 };
        }
        
        public Par(final FJTask[] tasks) {
            this.tasks = tasks;
        }
        
        public void run() {
            FJTask.coInvoke(this.tasks);
        }
    }
    
    public static class Seq2 extends FJTask
    {
        protected final FJTask fst;
        protected final FJTask snd;
        
        public Seq2(final FJTask fst, final FJTask snd) {
            this.fst = fst;
            this.snd = snd;
        }
        
        public void run() {
            FJTask.invoke(this.fst);
            FJTask.invoke(this.snd);
        }
    }
    
    public static class Par2 extends FJTask
    {
        protected final FJTask fst;
        protected final FJTask snd;
        
        public Par2(final FJTask fst, final FJTask snd) {
            this.fst = fst;
            this.snd = snd;
        }
        
        public void run() {
            FJTask.coInvoke(this.fst, this.snd);
        }
    }
}
