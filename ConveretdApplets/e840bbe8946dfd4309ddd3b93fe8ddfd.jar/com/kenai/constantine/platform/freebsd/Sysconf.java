// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.freebsd;

import com.kenai.constantine.Constant;

public enum Sysconf implements Constant
{
    _SC_ARG_MAX(1), 
    _SC_CHILD_MAX(2), 
    _SC_CLK_TCK(3), 
    _SC_NGROUPS_MAX(4), 
    _SC_OPEN_MAX(5), 
    _SC_JOB_CONTROL(6), 
    _SC_SAVED_IDS(7), 
    _SC_VERSION(8), 
    _SC_BC_BASE_MAX(9), 
    _SC_BC_DIM_MAX(10), 
    _SC_BC_SCALE_MAX(11), 
    _SC_BC_STRING_MAX(12), 
    _SC_COLL_WEIGHTS_MAX(13), 
    _SC_EXPR_NEST_MAX(14), 
    _SC_LINE_MAX(15), 
    _SC_RE_DUP_MAX(16), 
    _SC_2_VERSION(17), 
    _SC_2_C_BIND(18), 
    _SC_2_C_DEV(19), 
    _SC_2_CHAR_TERM(20), 
    _SC_2_FORT_DEV(21), 
    _SC_2_FORT_RUN(22), 
    _SC_2_LOCALEDEF(23), 
    _SC_2_SW_DEV(24), 
    _SC_2_UPE(25), 
    _SC_STREAM_MAX(26), 
    _SC_TZNAME_MAX(27), 
    _SC_ASYNCHRONOUS_IO(28), 
    _SC_PAGESIZE(47), 
    _SC_MEMLOCK(30), 
    _SC_MEMLOCK_RANGE(31), 
    _SC_MEMORY_PROTECTION(32), 
    _SC_MESSAGE_PASSING(33), 
    _SC_PRIORITIZED_IO(34), 
    _SC_PRIORITY_SCHEDULING(35), 
    _SC_REALTIME_SIGNALS(36), 
    _SC_SEMAPHORES(37), 
    _SC_FSYNC(38), 
    _SC_SHARED_MEMORY_OBJECTS(39), 
    _SC_SYNCHRONIZED_IO(40), 
    _SC_TIMERS(41), 
    _SC_AIO_LISTIO_MAX(42), 
    _SC_AIO_MAX(43), 
    _SC_AIO_PRIO_DELTA_MAX(44), 
    _SC_DELAYTIMER_MAX(45), 
    _SC_MQ_OPEN_MAX(46), 
    _SC_MAPPED_FILES(29), 
    _SC_RTSIG_MAX(48), 
    _SC_SEM_NSEMS_MAX(49), 
    _SC_SEM_VALUE_MAX(50), 
    _SC_SIGQUEUE_MAX(51), 
    _SC_TIMER_MAX(52), 
    _SC_NPROCESSORS_CONF(57), 
    _SC_NPROCESSORS_ONLN(58), 
    _SC_2_PBS(59), 
    _SC_2_PBS_ACCOUNTING(60), 
    _SC_2_PBS_CHECKPOINT(61), 
    _SC_2_PBS_LOCATE(62), 
    _SC_2_PBS_MESSAGE(63), 
    _SC_2_PBS_TRACK(64), 
    _SC_ADVISORY_INFO(65), 
    _SC_BARRIERS(66), 
    _SC_CLOCK_SELECTION(67), 
    _SC_CPUTIME(68), 
    _SC_FILE_LOCKING(69), 
    _SC_GETGR_R_SIZE_MAX(70), 
    _SC_GETPW_R_SIZE_MAX(71), 
    _SC_HOST_NAME_MAX(72), 
    _SC_LOGIN_NAME_MAX(73), 
    _SC_MONOTONIC_CLOCK(74), 
    _SC_MQ_PRIO_MAX(75), 
    _SC_READER_WRITER_LOCKS(76), 
    _SC_REGEXP(77), 
    _SC_SHELL(78), 
    _SC_SPAWN(79), 
    _SC_SPIN_LOCKS(80), 
    _SC_SPORADIC_SERVER(81), 
    _SC_THREAD_ATTR_STACKADDR(82), 
    _SC_THREAD_ATTR_STACKSIZE(83), 
    _SC_THREAD_CPUTIME(84), 
    _SC_THREAD_DESTRUCTOR_ITERATIONS(85), 
    _SC_THREAD_KEYS_MAX(86), 
    _SC_THREAD_PRIO_INHERIT(87), 
    _SC_THREAD_PRIO_PROTECT(88), 
    _SC_THREAD_PRIORITY_SCHEDULING(89), 
    _SC_THREAD_PROCESS_SHARED(90), 
    _SC_THREAD_SAFE_FUNCTIONS(91), 
    _SC_THREAD_SPORADIC_SERVER(92), 
    _SC_THREAD_STACK_MIN(93), 
    _SC_THREAD_THREADS_MAX(94), 
    _SC_TIMEOUTS(95), 
    _SC_THREADS(96), 
    _SC_TRACE(97), 
    _SC_TRACE_EVENT_FILTER(98), 
    _SC_TRACE_INHERIT(99), 
    _SC_TRACE_LOG(100), 
    _SC_TTY_NAME_MAX(101), 
    _SC_TYPED_MEMORY_OBJECTS(102), 
    _SC_V6_ILP32_OFF32(103), 
    _SC_V6_ILP32_OFFBIG(104), 
    _SC_V6_LP64_OFF64(105), 
    _SC_V6_LPBIG_OFFBIG(106), 
    _SC_IPV6(118), 
    _SC_RAW_SOCKETS(119), 
    _SC_SYMLOOP_MAX(120), 
    _SC_ATEXIT_MAX(107), 
    _SC_IOV_MAX(56), 
    _SC_PAGE_SIZE(47), 
    _SC_XOPEN_CRYPT(108), 
    _SC_XOPEN_ENH_I18N(109), 
    _SC_XOPEN_LEGACY(110), 
    _SC_XOPEN_REALTIME(111), 
    _SC_XOPEN_REALTIME_THREADS(112), 
    _SC_XOPEN_SHM(113), 
    _SC_XOPEN_STREAMS(114), 
    _SC_XOPEN_UNIX(115), 
    _SC_XOPEN_VERSION(116), 
    _SC_XOPEN_XCU_VERSION(117);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 120L;
    
    private Sysconf(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
