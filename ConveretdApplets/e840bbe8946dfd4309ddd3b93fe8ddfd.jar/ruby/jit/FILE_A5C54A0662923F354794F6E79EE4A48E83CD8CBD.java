// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.RubyInstanceConfig;
import org.jruby.RubySymbol;
import org.jruby.Ruby;
import org.jruby.RubyBoolean;
import org.jruby.runtime.CallSite;
import org.jruby.RubyFixnum;
import org.jruby.exceptions.JumpException;
import org.jruby.runtime.DynamicScope;
import org.jruby.runtime.Arity;
import org.jruby.RubyString;
import org.jruby.RubyArray;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD extends AbstractScript
{
    public FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD() {
        this.filename = "./lib//lister/runner.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffautoload\uffffF\uffffautoload\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffinclude\uffffF\uffffattr_reader\uffffF\uffffattr_accessor\uffffF\ufffflog\uffffF\uffff`\uffffF\uffffeach\uffffN\uffffloggers\uffffV\uffffsend\uffffN\ufffflog\uffffF\uffffconfig_file\uffffV\uffffmkdir_p\uffffN\ufffflister_dir\uffffV\uffffopen\uffffN\uffffconfig_file\uffffV\uffffputs\uffffN\uffffdump\uffffN\uffffconfig\uffffV\uffff[]\uffffN\uffff[]\uffffN\ufffflog\uffffF\uffffeach\uffffN\uffffdebug_logfile\uffffV\ufffflister_dir\uffffV\uffffconfig_file\uffffV\uffffchown\uffffN\uffffgenerate\uffffN\ufffflog\uffffF\uffffexpand_path\uffffN\uffffjoin\uffffN\uffffexpand_path\uffffN\uffffjoin\uffffN\ufffflister_dir\uffffV\uffffexpand_path\uffffN\uffffjoin\uffffN\ufffflister_dir\uffffV\ufffflog\uffffF\ufffffile?\uffffN\uffffconfig_file\uffffV\ufffflog\uffffF\uffffload_file\uffffN\uffffconfig_file\uffffV\ufffflog\uffffF\ufffflog\uffffF\uffffnew_uid\uffffV\uffffread_config\uffffV\uffffdefault_config\uffffV\uffffget_config\uffffV\uffffget_config\uffffV\uffff[]\uffffN\uffffconfig\uffffV\uffff[]\uffffN\uffffconfig\uffffV\uffff[]\uffffN\uffffconfig\uffffV\uffffconfigured_language\uffffV\ufffflog\uffffF\uffffstop_logging_in_file\uffffV\ufffffix_files_ownership\uffffV\uffffnew\uffffN\uffffwindow_name\uffffV\ufffflayout=\uffffN\ufffflayout=\uffffV\uffffnew\uffffN\uffffdefault_close_operation=\uffffN\uffffdefault_close_operation=\uffffV\uffffadd_window_listener\uffffN\uffffvisible=\uffffN\uffffvisible=\uffffV\uffffset_size\uffffN\uffffmenu_bar=\uffffN\uffffmenu_bar=\uffffV\uffffnew\uffffN\uffffnew_frame\uffffV\uffffnew_frame\uffffV\uffffmerge\uffffN\uffffblock_given?\uffffF\uffffeach\uffffN\uffffselect\uffffN\uffffto_a\uffffN\uffffinstalled_look_and_feels\uffffN\uffff==\uffffN\uffffname\uffffN\ufffflog\uffffF\uffffname\uffffN\ufffflook_and_feel=\uffffN\ufffflook_and_feel=\uffffV\uffffclass_name\uffffN\uffffshow_confirm_dialog\uffffN\uffffframe\uffffV\uffff==\uffffN\uffffshow_message_dialog\uffffN\uffffframe\uffffV\uffffMeasurements\uffffN\uffffclass\uffffN\uffff-\uffffN\uffffmeasurements\uffffV\uffffinclude?\uffffN\uffffmeasurements\uffffV\uffffshould_perform_the_poll?\uffffF\uffffon_linux?\uffffF\uffffchange_look\uffffF\uffffsleep\uffffF\uffffsleep\uffffF\uffff+\uffffN\uffffrand\uffffF\uffffsleep\uffffF\uffff*\uffffN\uffffrand\uffffF\uffffattr_accessor\uffffF\uffff[]\uffffN\uffff[]\uffffN\ufffflog\uffffF\uffffnew\uffffN\uffffblock_given?\uffffF\uffffon_progress\uffffN\uffffperform\uffffN\uffffexclusive\uffffN\uffffduration\uffffN\uffffsudo?\uffffN\uffffmerge\uffffN\uffffreports\uffffN\uffffresults\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffsleep_between_results\uffffV\ufffflog\uffffF\uffffduration\uffffN\uffffnew\uffffN\uffffeach_with_index\uffffN\uffffnon_poll_measurements\uffffV\uffffperform_measurement\uffffF\uffff+\uffffN\uffff*\uffffN\uffffduration_weight\uffffN\uffffmin\uffffN\uffffprogress_bar\uffffV\uffffvalue=\uffffN\uffffvalue=\uffffV\uffff+\uffffN\uffff*\uffffN\uffffduration_weight\uffffN\uffffprogress_bar\uffffV\uffffvalue=\uffffN\uffffvalue=\uffffV\uffffdebug_measurements_tasks\uffffV\uffff[]\uffffN\uffffparams\uffffV\ufffflog\uffffF\uffff[]\uffffN\uffffparams\uffffV\uffffsleep\uffffF\uffff[]\uffffN\uffffparams\uffffV\uffff[]\uffffN\uffffparams\uffffV\ufffflog\uffffF\uffffraise\uffffF\uffffnew\uffffN\uffff[]\uffffN\uffffparams\uffffV\ufffflog\uffffF\uffffraise\uffffF\uffffnew\uffffN\uffffnew\uffffN\ufffflanguage\uffffV\uffffwait\uffffN\uffffinterpret\uffffV\uffffselect_among_languages_question\uffffN\uffffnew\uffffN\uffffselect_language_question_description\uffffV\uffffinterpret\uffffV\uffffstart\uffffN\uffffadd\uffffN\uffffcontent_pane\uffffN\uffffframe\uffffV\uffffframe\uffffV\uffffvisible=\uffffN\uffffvisible=\uffffV\uffffwait_button\uffffN\uffff[]\uffffN\uffffbuttons\uffffN\uffffvisible=\uffffN\uffffvisible=\uffffV\uffffstore_question\uffffN\ufffffind\uffffN\uffff[]\uffffN\uffffresults\uffffN\uffffconfig\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\ufffffirst\uffffN\uffffconfig\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffremove\uffffN\uffffcontent_pane\uffffN\uffffframe\uffffV\uffffselect_among_measurements_question\uffffN\uffffnon_poll_measurements\uffffV\uffffnew\uffffN\uffffselect_measurements_question_description\uffffV\uffffinterpret\uffffV\uffffnew\uffffN\uffffinterpret\uffffV\uffffstart\uffffN\uffffadd\uffffN\uffffcontent_pane\uffffN\uffffframe\uffffV\uffffframe\uffffV\uffffvisible=\uffffN\uffffvisible=\uffffV\uffffwait_questionnaire\uffffN\uffffvisible=\uffffN\uffffvisible=\uffffV\uffffstore_question\uffffN\uffffremove\uffffN\uffffcontent_pane\uffffN\uffffframe\uffffV\uffffpartition\uffffN\uffffmeasurements\uffffV\uffff[]\uffffN\uffff[]\uffffN\uffffresults\uffffN\uffffeach\uffffN\uffffresults\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffmeasurements=\uffffN\uffffmeasurements=\uffffV\uffffselect_poll_or_not_question\uffffN\uffffnew\uffffN\uffffselect_poll_or_not_question_description\uffffV\uffffinterpret\uffffV\uffffstart\uffffN\uffffadd\uffffN\uffffpoll_panel\uffffV\uffffframe\uffffV\uffffvisible=\uffffN\uffffvisible=\uffffV\uffffwait_questionnaire\uffffN\uffffvisible=\uffffN\uffffvisible=\uffffV\uffffstore_question\uffffN\uffffremove\uffffN\uffffpoll_panel\uffffV\uffff[]\uffffN\uffffresults\uffffN\uffffraise\uffffF\uffff[]\uffffN\ufffflog\uffffF\uffff<<\uffffN\uffffmeasurements\uffffN\ufffflog\uffffF\uffffjoin\uffffN\uffffthread\uffffV\ufffflog\uffffF\uffffperform_measurements\uffffV\uffffwait_end_of_measurements\uffffV\uffffshould_skip_the_poll?\uffffF\ufffflog\uffffF\uffffshow_the_poll\uffffV\ufffflog\uffffF\uffffperform_measurement\uffffF\uffffdebug_poll_tasks\uffffV\ufffflog\uffffF\ufffflog\uffffF\uffffperform_measurement\uffffF\uffffjoin\uffffN\uffffuid\uffffV\uffffnew\uffffN\uffffurl\uffffV\uffffeditable=\uffffN\uffffeditable=\uffffV\uffffeach\uffffN\uffffUtils\uffffN\uffffclass\uffffN\uffffrespond_to?\uffffN\ufffflogger=\uffffN\ufffflogger=\uffffV\ufffffirst\uffffN\uffffloggers\uffffV\uffffattr_reader\uffffF\uffffcontent_pane\uffffN\uffffframe\uffffV\ufffflayout=\uffffN\ufffflayout=\uffffV\uffffnew\uffffN\uffffprepare_poll_panel\uffffV\uffffprepare_progress_bar\uffffV\uffffprepare_wait_panel\uffffV\uffffnew\uffffN\uffffpoll_panel\uffffV\ufffflayout=\uffffN\ufffflayout=\uffffV\uffffnew\uffffN\uffffadd\uffffN\uffffcontent_pane\uffffN\uffffframe\uffffV\uffffpoll_panel\uffffV\uffffnew\uffffN\uffffwait_panel\uffffV\ufffflayout=\uffffN\ufffflayout=\uffffV\uffffnew\uffffN\uffffnew\uffffN\uffffwait_text\uffffV\uffffpreferred_size=\uffffN\uffffpreferred_size=\uffffV\uffffnew\uffffN\uffffadd\uffffN\uffffwait_panel\uffffV\ufffflog\uffffF\uffffcontent_pane\uffffN\uffffframe\uffffV\uffffvisible=\uffffN\uffffvisible=\uffffV\uffffpoll_panel\uffffV\uffffvisible=\uffffN\uffffvisible=\uffffV\uffffremove\uffffN\uffffcontent_pane\uffffN\uffffframe\uffffV\uffffpoll_panel\uffffV\ufffflog\uffffF\uffffadd\uffffN\uffffcontent_pane\uffffN\uffffframe\uffffV\uffffwait_panel\uffffV\uffffpack\uffffN\uffffframe\uffffV\uffffcontent_pane\uffffN\uffffframe\uffffV\uffffvisible=\uffffN\uffffvisible=\uffffV\ufffflog\uffffF\uffffsleep\uffffF\uffff>\uffffN\uffffsize\uffffN\uffffnon_poll_measurements\uffffV\uffff*\uffffN\uffffinject\uffffN\uffffnon_poll_measurements\uffffV\uffff+\uffffN\uffffduration_weight\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffffadd\uffffN\uffffprogress_bar_panel\uffffV\uffffnew\uffffN\uffffbkg_meas\uffffN\uffffinterpret\uffffV\uffffadd\uffffN\uffffprogress_bar_panel\uffffV\uffffprogress_bar\uffffV\uffffprogress_bar\uffffV\uffffvalue=\uffffN\uffffvalue=\uffffV\uffffprogress_bar\uffffV\uffffstring_painted=\uffffN\uffffstring_painted=\uffffV\uffffadd\uffffN\uffffcontent_pane\uffffN\uffffframe\uffffV\uffffprogress_bar_panel\uffffV\ufffflog\uffffF\uffffprogress_bar_panel\uffffV\uffffremove\uffffN\uffffcontent_pane\uffffN\uffffframe\uffffV\uffffprogress_bar_panel\uffffV\uffffnew\uffffN\uffffsize\uffffN\uffffresults\uffffV\uffffnew\uffffN\uffffadd\uffffN\uffffupload_bar_panel\uffffV\uffffnew\uffffN\uffffbkg_upload\uffffN\uffffinterpret\uffffV\uffffadd\uffffN\uffffupload_bar_panel\uffffV\uffffupload_bar\uffffV\uffffupload_bar\uffffV\uffffvalue=\uffffN\uffffvalue=\uffffV\uffffupload_bar\uffffV\uffffstring_painted=\uffffN\uffffstring_painted=\uffffV\ufffflog\uffffF\uffffadd\uffffN\uffffcontent_pane\uffffN\uffffframe\uffffV\uffffupload_bar_panel\uffffV\uffffrevalidate\uffffN\uffffcontent_pane\uffffN\uffffframe\uffffV\uffffask\uffffF\uffffnew\uffffN\ufffflog\uffffF\ufffffull_url\uffffN\ufffflog\uffffF\ufffferror_message\uffffN\uffffperform\uffffN\ufffflog\uffffF\uffffmkdir_p\uffffN\ufffflister_dir\uffffV\uffffdirectory?\uffffN\ufffflister_dir\uffffV\ufffflog\uffffF\ufffffile?\uffffN\uffffdebug_logfile\uffffV\uffffrm\uffffN\uffffdebug_logfile\uffffV\uffffputs\uffffF\ufffflog\uffffF\uffffnew\uffffN\uffffdebug_logfile\uffffV\uffff<<\uffffN\uffffloggers\uffffV\uffffputs\uffffF\ufffflog\uffffF\uffffdelete\uffffN\uffffloggers\uffffV\uffffclose\uffffN\uffffputs\uffffF\ufffflog\uffffF\uffff[]\uffffN\uffffparams\uffffV\uffffstart_logging_in_file\uffffV\uffffrun_steps\uffffV\uffffstop_logging_in_file\uffffV\uffffpropagate_bug_reports?\uffffF\uffffsend_bug_report?\uffffF\uffffsend_bug_report\uffffF\uffffsend_bug_report?\uffffF\uffffraise\uffffF\uffffstop_logging_in_file\uffffV\ufffffix_files_ownership\uffffV\uffffdo_exit\uffffV\uffffstop_logging_in_file\uffffV\ufffffix_files_ownership\uffffV\uffffdo_exit\uffffV\uffffdispose\uffffN\uffffframe\uffffV\ufffflog\uffffF\uffffabort_on_exception=\uffffN\uffffabort_on_exception=\uffffV\ufffflog\uffffF\uffffinspect\uffffN\uffffconfig\uffffV\uffffprepare_utils\uffffV\uffffset_look\uffffV\uffffconfigured_language\uffffV\uffffselect_language\uffffV\uffffselect_measurements\uffffV\uffffprepare_layout\uffffV\uffffduring_measurements\uffffF\uffffselect_poll_or_not\uffffV\uffffshow_the_poll_if_needed\uffffV\uffffask_for_email\uffffV\uffffremove_poll_panel\uffffV\uffffshow_wait_panel\uffffV\uffffwait_a_bit\uffffV\uffffconfig\uffffV\uffff[]\uffffF\uffff+\uffffN\uffff[]=\uffffF\uffffstore_config\uffffV\uffffremove_progress_bar\uffffV\uffffprepare_upload_bar\uffffV\uffffpropagate_results\uffffV\uffffopen_web_browser\uffffV\uffffsleep_before_opening_browser\uffffV\uffffcatch\uffffF\uffffdesktop_supported?\uffffN\uffffdesktop\uffffN\uffffsupported?\uffffN\uffffjoin\uffffN\uffffurl\uffffV\uffffto_s\uffffN\uffffrun_id\uffffV\ufffflanguage\uffffV\uffff===\uffffN\uffffjoin\uffffN\ufffflog\uffffF\uffffbrowse\uffffN\uffffnew\uffffN\uffffURI\uffffN\uffffnet\uffffN\uffffjava\uffffV\uffffthrow\uffffF\ufffflog\uffffF\uffffIOException\uffffN\uffffio\uffffN\uffffjava\uffffV\ufffflog\uffffF\uffff[]\uffffN\uffffparams\uffffV\ufffflog\uffffF\uffffeach_pair\uffffN\uffffresults\uffffV\uffffupload_bar\uffffV\uffffvalue=\uffffN\uffffvalue=\uffffV\uffff+\uffffN\uffffpropagate_results?\uffffF\ufffflog\uffffF\uffffreport\uffffN\uffffsleep_between_uploads\uffffV\ufffflog\uffffF\uffffputs\uffffF\uffffJSON\uffffF\uffffsleep\uffffF\uffffrand\uffffF\uffff[]\uffffN\uffffparams\uffffV\ufffflog\uffffF\uffffraise\uffffF\uffffnew\uffffN\uffffupload_bar\uffffV\uffffvalue=\uffffN\uffffvalue=\uffffV\uffff\u0002!\u0006\u0002§\u0000\u0000\b\u0011\u0011\u0000\u0000{\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(73, "window is closing", this.getEncoding0());
        this.setByteList(57, "SUDO_UID", this.getEncoding0());
        this.setByteList(13, "javax.swing.JProgressBar", this.getEncoding0());
        this.setByteList(101, "removing progress bar", this.getEncoding0());
        this.setByteList(48, "lister/runner/questionnaire/checkbox_question", this.getEncoding0());
        this.setByteList(29, "lister/utils/traceroute", this.getEncoding0());
        this.setByteList(24, "lister/portability_tricks", this.getEncoding0());
        this.setByteList(115, "opening web browser at: ", this.getEncoding0());
        this.setByteList(71, "HomeNet Profiler (v: ", this.getEncoding0());
        this.setByteList(118, "propagating results", this.getEncoding0());
        this.setByteList(109, "/! could not stop logging", this.getEncoding0());
        this.setByteList(44, "lister/runner/measurements/lan_services", this.getEncoding0());
        this.setByteList(1, "thread", this.getEncoding0());
        this.setByteList(83, "injecting a debug measurement error", this.getEncoding0());
        this.setByteList(120, "dumping result ", this.getEncoding0());
        this.setByteList(114, "fr", this.getEncoding0());
        this.setByteList(87, "skipped by user", this.getEncoding0());
        this.setByteList(80, " in ", this.getEncoding0());
        this.setByteList(7, "java.awt.datatransfer.StringSelection", this.getEncoding0());
        this.setByteList(65, "loading config", this.getEncoding0());
        this.setByteList(19, "java.awt.MenuItem", this.getEncoding0());
        this.setByteList(108, "/! could not start logging in a file, you may not see this message", this.getEncoding0());
        this.setByteList(54, "'", this.getEncoding0());
        this.setByteList(58, "SUDO_GID", this.getEncoding0());
        this.setByteList(72, ")", this.getEncoding0());
        this.setByteList(97, "/", this.getEncoding0());
        this.setByteList(50, "lister/runner/questionnaire/workflow", this.getEncoding0());
        this.setByteList(116, "could not open the web brower", this.getEncoding0());
        this.setByteList(22, "yaml", this.getEncoding0());
        this.setByteList(111, "Using config: ", this.getEncoding0());
        this.setByteList(11, "javax.swing.JPanel", this.getEncoding0());
        this.setByteList(106, "could not create configuration directory, program will probably crash", this.getEncoding0());
        this.setByteList(94, "poll done", this.getEncoding0());
        this.setByteList(112, "run", this.getEncoding0());
        this.setByteList(64, "debug.log", this.getEncoding0());
        this.setByteList(86, "injected poll error on purpose", this.getEncoding0());
        this.setByteList(119, "propagating result for ", this.getEncoding0());
        this.setByteList(9, "javax.swing.JOptionPane", this.getEncoding0());
        this.setByteList(43, "lister/runner/measurements/services", this.getEncoding0());
        this.setByteList(38, "lister/runner/measurements/binaries", this.getEncoding0());
        this.setByteList(33, "lister/utils/servers", this.getEncoding0());
        this.setByteList(47, "lister/runner/measurements/performance", this.getEncoding0());
        this.setByteList(53, "spawning `", this.getEncoding0());
        this.setByteList(105, "creating the configuration directory if not present", this.getEncoding0());
        this.setByteList(20, "java.awt.Desktop", this.getEncoding0());
        this.setByteList(62, ".hnp", this.getEncoding0());
        this.setByteList(42, "lister/runner/measurements/upnp", this.getEncoding0());
        this.setByteList(21, "fileutils", this.getEncoding0());
        this.setByteList(76, ">> Start: ", this.getEncoding0());
        this.setByteList(84, "injected measurement error on purpose", this.getEncoding0());
        this.setByteList(102, "adding upload bar", this.getEncoding0());
        this.setByteList(56, "w", this.getEncoding0());
        this.setByteList(60, "Using uid: ", this.getEncoding0());
        this.setByteList(25, "lister/debug", this.getEncoding0());
        this.setByteList(35, "lister/utils/traffic", this.getEncoding0());
        this.setByteList(2, "java.awt.Toolkit", this.getEncoding0());
        this.setByteList(66, "found config file", this.getEncoding0());
        this.setByteList(85, "injecting a debug poll error", this.getEncoding0());
        this.setByteList(81, "sleeping ", this.getEncoding0());
        this.setByteList(61, "~", this.getEncoding0());
        this.setByteList(110, "exiting", this.getEncoding0());
        this.setByteList(92, "skipping the main poll", this.getEncoding0());
        this.setByteList(5, "java.awt.datatransfer.ClipboardOwner", this.getEncoding0());
        this.setByteList(45, "lister/runner/measurements/network_scan", this.getEncoding0());
        this.setByteList(41, "lister/runner/measurements/ask_email", this.getEncoding0());
        this.setByteList(28, "lister/utils/platform", this.getEncoding0());
        this.setByteList(8, "javax.swing.JFrame", this.getEncoding0());
        this.setByteList(34, "lister/utils/services", this.getEncoding0());
        this.setByteList(30, "lister/utils/programs", this.getEncoding0());
        this.setByteList(122, "injecting a reporting error", this.getEncoding0());
        this.setByteList(32, "lister/utils/scan", this.getEncoding0());
        this.setByteList(95, "asking for email", this.getEncoding0());
        this.setByteList(37, "lister/runner/measurements/netalyzr", this.getEncoding0());
        this.setByteList(63, "config.yaml", this.getEncoding0());
        this.setByteList(90, "try joining the measurements thread", this.getEncoding0());
        this.setByteList(6, "java.awt.datatransfer.DataFlavor", this.getEncoding0());
        this.setByteList(3, "java.awt.event.ActionListener", this.getEncoding0());
        this.setByteList(103, "Crashed :(", this.getEncoding0());
        this.setByteList(96, "agent", this.getEncoding0());
        this.setByteList(55, "Storing config in ", this.getEncoding0());
        this.setByteList(113, "Fran\uffc3\uffa7ais", this.getEncoding0());
        this.setByteList(79, "<< done (", this.getEncoding0());
        this.setByteList(91, "thread joined", this.getEncoding0());
        this.setByteList(12, "javax.swing.JTextField", this.getEncoding0());
        this.setByteList(40, "lister/runner/measurements/poll", this.getEncoding0());
        this.setByteList(10, "javax.swing.UIManager", this.getEncoding0());
        this.setByteList(67, "correctly loaded YAML config file", this.getEncoding0());
        this.setByteList(52, "lister/toolbar", this.getEncoding0());
        this.setByteList(88, "no result", this.getEncoding0());
        this.setByteList(121, " instead", this.getEncoding0());
        this.setByteList(104, "Home Net Profiler has crashed. Send an error trace?", this.getEncoding0());
        this.setByteList(15, "java.awt.FlowLayout", this.getEncoding0());
        this.setByteList(51, "lister/runner/report", this.getEncoding0());
        this.setByteList(46, "lister/runner/measurements/wlan_scan", this.getEncoding0());
        this.setByteList(75, "switching UI look and feel to ", this.getEncoding0());
        this.setByteList(93, "starting the poll", this.getEncoding0());
        this.setByteList(107, "/! could not remove the logfile, will try appending", this.getEncoding0());
        this.setByteList(16, "java.awt.BorderLayout", this.getEncoding0());
        this.setByteList(17, "java.awt.MenuBar", this.getEncoding0());
        this.setByteList(59, "fixing files ownership", this.getEncoding0());
        this.setByteList(36, "lister/utils/wifiscan", this.getEncoding0());
        this.setByteList(23, "json", this.getEncoding0());
        this.setByteList(82, " to simulate ongoing measurements", this.getEncoding0());
        this.setByteList(98, "removing poll panel", this.getEncoding0());
        this.setByteList(49, "lister/runner/questionnaire/questionnaire", this.getEncoding0());
        this.setByteList(4, "java.awt.event.WindowListener", this.getEncoding0());
        this.setByteList(18, "java.awt.Menu", this.getEncoding0());
        this.setByteList(31, "lister/utils/route", this.getEncoding0());
        this.setByteList(117, "rescued a non-important error opening the web browser", this.getEncoding0());
        this.setByteList(68, "no config file", this.getEncoding0());
        this.setByteList(78, "metadata.was_sudo?", this.getEncoding0());
        this.setByteList(70, "English", this.getEncoding0());
        this.setByteList(99, "showing wait panel", this.getEncoding0());
        this.setByteList(74, "Nimbus", this.getEncoding0());
        this.setByteList(27, "lister/utils/uid", this.getEncoding0());
        this.setByteList(14, "java.net.URL", this.getEncoding0());
        this.setByteList(26, "lister/interpret", this.getEncoding0());
        this.setByteList(77, "metadata.duration", this.getEncoding0());
        this.setByteList(100, "let the user read the wait message", this.getEncoding0());
        this.setByteList(0, "java", this.getEncoding0());
        this.setByteList(39, "lister/runner/measurements/configuration", this.getEncoding0());
        this.setByteList(69, "instance-variable", this.getEncoding0());
        this.setByteList(89, "adding the poll to the list of measurements", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite0().call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString0(threadContext.runtime, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite1().call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString1(threadContext.runtime, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite2().call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString2(threadContext.runtime, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite3().call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString3(threadContext.runtime, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite4().call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString4(threadContext.runtime, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite5().call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString5(threadContext.runtime, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite6().call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString6(threadContext.runtime, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite7().call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString7(threadContext.runtime, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite8().call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString8(threadContext.runtime, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite9().call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString9(threadContext.runtime, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(10).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 10, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(11).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 11, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(12).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 12, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(13).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 13, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(14).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 14, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(15).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 15, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(16).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 16, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(17).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 17, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(18).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 18, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(19).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 19, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(20).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 20, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(21).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol0(threadContext.runtime, "FileUtils"), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 21, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(22).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol1(threadContext.runtime, "YAML"), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 22, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(23).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 23, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(24).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 24, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(25).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 25, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(26).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 26, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(27).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 27, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(28).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 28, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(29).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 29, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(30).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 30, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(31).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 31, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(32).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 32, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(33).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 33, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(34).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 34, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(35).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 35, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(36).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 36, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(37).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 37, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(38).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 38, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(39).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 39, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(40).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 40, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(41).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 41, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(42).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 42, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(43).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 43, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(44).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 44, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(45).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 45, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(46).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 46, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(47).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 47, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(48).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 48, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(49).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 49, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(50).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 50, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(51).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 51, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(52).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 52, 32));
        return module__0$RUBY$Lister(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Lister"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    33: invokestatic    ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.class_1$RUBY$Runner:(Lruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    36: aload_1        
        //    37: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    40: goto            48
        //    43: aload_1        
        //    44: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    47: athrow         
        //    48: aload_1        
        //    49: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    52: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     40     43     48     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_1$RUBY$Runner(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: aload_1        
        //     2: aload_1        
        //     3: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     6: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     9: swap           
        //    10: ldc             "Runner"
        //    12: swap           
        //    13: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    16: dup            
        //    17: astore_2       
        //    18: aload_1        
        //    19: swap           
        //    20: aload_0        
        //    21: aload_1        
        //    22: ldc             ",0,0,-1"
        //    24: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    27: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    30: aload_0        
        //    31: bipush          53
        //    33: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    36: aload_1        
        //    37: aload_2        
        //    38: aload_2        
        //    39: aload_0        
        //    40: aload_1        
        //    41: ldc             "Utils"
        //    43: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    46: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    49: aload_0        
        //    50: swap           
        //    51: aload_1        
        //    52: ldc             "Platform"
        //    54: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom1:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: pop            
        //    61: aload_0        
        //    62: bipush          54
        //    64: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    67: aload_1        
        //    68: aload_2        
        //    69: aload_2        
        //    70: aload_0        
        //    71: aload_1        
        //    72: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    75: ldc             "results"
        //    77: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    80: aload_0        
        //    81: aload_1        
        //    82: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    85: ldc             "loggers"
        //    87: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    90: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    93: pop            
        //    94: aload_0        
        //    95: bipush          55
        //    97: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   100: aload_1        
        //   101: aload_2        
        //   102: aload_2        
        //   103: aload_0        
        //   104: aload_1        
        //   105: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   108: ldc             "measurements"
        //   110: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   113: aload_0        
        //   114: aload_1        
        //   115: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   118: ldc             "params"
        //   120: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   123: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   126: pop            
        //   127: aload_1        
        //   128: aload_2        
        //   129: aload_2        
        //   130: aload_0        
        //   131: ldc             "Measurements"
        //   133: ldc_w           "method__2$RUBY$Measurements"
        //   136: ldc             ",0,0,-1"
        //   138: iconst_0       
        //   139: ldc             "./lib//lister/runner.rb"
        //   141: ldc_w           68
        //   144: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   147: ldc_w           "NONE"
        //   150: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   153: pop            
        //   154: aload_1        
        //   155: aload_2        
        //   156: aload_2        
        //   157: aload_0        
        //   158: ldc             "Utils"
        //   160: ldc_w           "method__3$RUBY$Utils"
        //   163: ldc             ",0,0,-1"
        //   165: iconst_0       
        //   166: ldc             "./lib//lister/runner.rb"
        //   168: ldc_w           83
        //   171: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   174: ldc_w           "NONE"
        //   177: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   180: pop            
        //   181: aload_1        
        //   182: aload_2        
        //   183: aload_0        
        //   184: ldc_w           "sh"
        //   187: ldc_w           "method__4$RUBY$sh"
        //   190: ldc_w           "cmd,1,0,-1"
        //   193: iconst_1       
        //   194: ldc             "./lib//lister/runner.rb"
        //   196: ldc_w           99
        //   199: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   202: ldc_w           "qcmd"
        //   205: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   208: pop            
        //   209: aload_1        
        //   210: aload_2        
        //   211: aload_0        
        //   212: ldc_w           "log"
        //   215: ldc_w           "method__5$RUBY$log"
        //   218: ldc_w           "msg;type,1,1,-1"
        //   221: bipush          -2
        //   223: ldc             "./lib//lister/runner.rb"
        //   225: ldc_w           104
        //   228: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   231: ldc_w           "qmsg;otype"
        //   234: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   237: pop            
        //   238: aload_1        
        //   239: aload_2        
        //   240: aload_0        
        //   241: ldc_w           "store_config"
        //   244: ldc_w           "method__6$RUBY$store_config"
        //   247: ldc             ",0,0,-1"
        //   249: iconst_0       
        //   250: ldc             "./lib//lister/runner.rb"
        //   252: ldc_w           110
        //   255: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   258: ldc_w           "NONE"
        //   261: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   264: pop            
        //   265: aload_1        
        //   266: aload_2        
        //   267: aload_0        
        //   268: ldc_w           "fix_files_ownership"
        //   271: ldc_w           "method__7$RUBY$fix_files_ownership"
        //   274: ldc_w           "uid;gid,0,0,-1"
        //   277: iconst_0       
        //   278: ldc             "./lib//lister/runner.rb"
        //   280: ldc_w           118
        //   283: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   286: ldc_w           "NONE"
        //   289: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   292: pop            
        //   293: aload_1        
        //   294: aload_2        
        //   295: aload_0        
        //   296: ldc_w           "new_uid"
        //   299: ldc_w           "method__9$RUBY$new_uid"
        //   302: ldc_w           "uid,0,0,-1"
        //   305: iconst_0       
        //   306: ldc             "./lib//lister/runner.rb"
        //   308: ldc_w           132
        //   311: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   314: ldc_w           "NONE"
        //   317: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   320: pop            
        //   321: aload_1        
        //   322: aload_2        
        //   323: aload_0        
        //   324: ldc_w           "lister_dir"
        //   327: ldc_w           "method__10$RUBY$lister_dir"
        //   330: ldc             ",0,0,-1"
        //   332: iconst_0       
        //   333: ldc             "./lib//lister/runner.rb"
        //   335: ldc_w           138
        //   338: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   341: ldc_w           "NONE"
        //   344: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   347: pop            
        //   348: aload_1        
        //   349: aload_2        
        //   350: aload_0        
        //   351: ldc_w           "config_file"
        //   354: ldc_w           "method__11$RUBY$config_file"
        //   357: ldc             ",0,0,-1"
        //   359: iconst_0       
        //   360: ldc             "./lib//lister/runner.rb"
        //   362: ldc_w           142
        //   365: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   368: ldc_w           "NONE"
        //   371: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   374: pop            
        //   375: aload_1        
        //   376: aload_2        
        //   377: aload_0        
        //   378: ldc_w           "debug_logfile"
        //   381: ldc_w           "method__12$RUBY$debug_logfile"
        //   384: ldc             ",0,0,-1"
        //   386: iconst_0       
        //   387: ldc             "./lib//lister/runner.rb"
        //   389: ldc_w           146
        //   392: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   395: ldc_w           "NONE"
        //   398: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   401: pop            
        //   402: aload_1        
        //   403: aload_2        
        //   404: aload_0        
        //   405: ldc_w           "read_config"
        //   408: ldc_w           "method__13$RUBY$read_config"
        //   411: ldc_w           "ret,0,0,-1"
        //   414: iconst_0       
        //   415: ldc             "./lib//lister/runner.rb"
        //   417: ldc_w           150
        //   420: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   423: ldc_w           "NONE"
        //   426: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   429: pop            
        //   430: aload_1        
        //   431: aload_2        
        //   432: aload_0        
        //   433: ldc_w           "default_config"
        //   436: ldc_w           "method__14$RUBY$default_config"
        //   439: ldc             ",0,0,-1"
        //   441: iconst_0       
        //   442: ldc             "./lib//lister/runner.rb"
        //   444: ldc_w           163
        //   447: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   450: ldc_w           "NONE"
        //   453: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   456: pop            
        //   457: aload_1        
        //   458: aload_2        
        //   459: aload_0        
        //   460: ldc_w           "get_config"
        //   463: ldc_w           "method__15$RUBY$get_config"
        //   466: ldc             ",0,0,-1"
        //   468: iconst_0       
        //   469: ldc             "./lib//lister/runner.rb"
        //   471: ldc_w           170
        //   474: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   477: ldc_w           "NONE"
        //   480: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   483: pop            
        //   484: aload_1        
        //   485: aload_2        
        //   486: aload_0        
        //   487: ldc_w           "config"
        //   490: ldc_w           "method__16$RUBY$config"
        //   493: ldc             ",0,0,-1"
        //   495: iconst_0       
        //   496: ldc             "./lib//lister/runner.rb"
        //   498: ldc_w           174
        //   501: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   504: ldc_w           "NONE"
        //   507: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   510: pop            
        //   511: aload_1        
        //   512: aload_2        
        //   513: aload_0        
        //   514: ldc_w           "uid"
        //   517: ldc_w           "method__17$RUBY$uid"
        //   520: ldc             ",0,0,-1"
        //   522: iconst_0       
        //   523: ldc             "./lib//lister/runner.rb"
        //   525: ldc_w           178
        //   528: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   531: ldc_w           "NONE"
        //   534: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   537: pop            
        //   538: aload_1        
        //   539: aload_2        
        //   540: aload_0        
        //   541: ldc_w           "run_id"
        //   544: ldc_w           "method__18$RUBY$run_id"
        //   547: ldc             ",0,0,-1"
        //   549: iconst_0       
        //   550: ldc             "./lib//lister/runner.rb"
        //   552: ldc_w           182
        //   555: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   558: ldc_w           "NONE"
        //   561: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   564: pop            
        //   565: aload_1        
        //   566: aload_2        
        //   567: aload_0        
        //   568: ldc_w           "configured_language"
        //   571: ldc_w           "method__19$RUBY$configured_language"
        //   574: ldc             ",0,0,-1"
        //   576: iconst_0       
        //   577: ldc             "./lib//lister/runner.rb"
        //   579: ldc_w           186
        //   582: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   585: ldc_w           "NONE"
        //   588: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   591: pop            
        //   592: aload_1        
        //   593: aload_2        
        //   594: aload_0        
        //   595: ldc_w           "language"
        //   598: ldc_w           "method__20$RUBY$language"
        //   601: ldc             ",0,0,-1"
        //   603: iconst_0       
        //   604: ldc             "./lib//lister/runner.rb"
        //   606: ldc_w           190
        //   609: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   612: ldc_w           "NONE"
        //   615: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   618: pop            
        //   619: aload_1        
        //   620: aload_2        
        //   621: aload_0        
        //   622: ldc_w           "window_name"
        //   625: ldc_w           "method__21$RUBY$window_name"
        //   628: ldc             ",0,0,-1"
        //   630: iconst_0       
        //   631: ldc             "./lib//lister/runner.rb"
        //   633: ldc_w           194
        //   636: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   639: ldc_w           "NONE"
        //   642: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   645: pop            
        //   646: aload_1        
        //   647: aload_2        
        //   648: aload_0        
        //   649: ldc_w           "dummy_window_event"
        //   652: ldc_w           "method__22$RUBY$dummy_window_event"
        //   655: ldc_w           "e,1,0,-1"
        //   658: iconst_1       
        //   659: ldc             "./lib//lister/runner.rb"
        //   661: ldc_w           198
        //   664: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   667: ldc_w           "qe"
        //   670: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   673: pop            
        //   674: aload_1        
        //   675: aload_2        
        //   676: ldc_w           "windowActivated"
        //   679: ldc_w           "dummy_window_event"
        //   682: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defineAlias:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/Object;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   685: pop            
        //   686: aload_1        
        //   687: aload_2        
        //   688: ldc_w           "windowClosed"
        //   691: ldc_w           "dummy_window_event"
        //   694: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defineAlias:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/Object;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   697: pop            
        //   698: aload_1        
        //   699: aload_2        
        //   700: ldc_w           "windowClosing"
        //   703: ldc_w           "dummy_window_event"
        //   706: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defineAlias:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/Object;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   709: pop            
        //   710: aload_1        
        //   711: aload_2        
        //   712: ldc_w           "windowDeactivated"
        //   715: ldc_w           "dummy_window_event"
        //   718: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defineAlias:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/Object;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   721: pop            
        //   722: aload_1        
        //   723: aload_2        
        //   724: ldc_w           "windowDeiconified"
        //   727: ldc_w           "dummy_window_event"
        //   730: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defineAlias:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/Object;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   733: pop            
        //   734: aload_1        
        //   735: aload_2        
        //   736: ldc_w           "windowIconified"
        //   739: ldc_w           "dummy_window_event"
        //   742: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defineAlias:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/Object;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   745: pop            
        //   746: aload_1        
        //   747: aload_2        
        //   748: ldc_w           "windowOpened"
        //   751: ldc_w           "dummy_window_event"
        //   754: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defineAlias:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/Object;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   757: pop            
        //   758: aload_1        
        //   759: aload_2        
        //   760: aload_0        
        //   761: ldc_w           "windowClosing"
        //   764: ldc_w           "method__23$RUBY$windowClosing"
        //   767: ldc_w           "e,1,0,-1"
        //   770: iconst_1       
        //   771: ldc             "./lib//lister/runner.rb"
        //   773: ldc_w           209
        //   776: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   779: ldc_w           "qe"
        //   782: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   785: pop            
        //   786: aload_1        
        //   787: aload_2        
        //   788: aload_0        
        //   789: ldc_w           "new_frame"
        //   792: ldc_w           "method__24$RUBY$new_frame"
        //   795: ldc_w           "frame,0,0,-1"
        //   798: iconst_0       
        //   799: ldc             "./lib//lister/runner.rb"
        //   801: ldc_w           215
        //   804: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   807: ldc_w           "NONE"
        //   810: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   813: pop            
        //   814: aload_1        
        //   815: aload_2        
        //   816: aload_0        
        //   817: ldc_w           "frame"
        //   820: ldc_w           "method__25$RUBY$frame"
        //   823: ldc             ",0,0,-1"
        //   825: iconst_0       
        //   826: ldc             "./lib//lister/runner.rb"
        //   828: ldc_w           226
        //   831: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   834: ldc_w           "NONE"
        //   837: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   840: pop            
        //   841: aload_1        
        //   842: aload_2        
        //   843: aload_0        
        //   844: ldc_w           "initialize"
        //   847: ldc_w           "method__26$RUBY$initialize"
        //   850: ldc_w           "params,0,1,-1"
        //   853: iconst_m1      
        //   854: ldc             "./lib//lister/runner.rb"
        //   856: ldc_w           230
        //   859: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   862: ldc_w           "oparams"
        //   865: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   868: pop            
        //   869: aload_1        
        //   870: aload_2        
        //   871: aload_0        
        //   872: ldc_w           "change_look"
        //   875: ldc_w           "method__27$RUBY$change_look"
        //   878: ldc_w           "val,0,1,-1"
        //   881: iconst_m1      
        //   882: ldc             "./lib//lister/runner.rb"
        //   884: ldc_w           237
        //   887: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   890: ldc_w           "oval"
        //   893: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   896: pop            
        //   897: aload_1        
        //   898: aload_2        
        //   899: aload_0        
        //   900: ldc_w           "ask"
        //   903: ldc_w           "method__28$RUBY$ask"
        //   906: ldc_w           "title;message;ret,2,0,-1"
        //   909: iconst_2       
        //   910: ldc             "./lib//lister/runner.rb"
        //   912: ldc_w           245
        //   915: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   918: ldc_w           "qtitle;qmessage"
        //   921: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   924: pop            
        //   925: aload_1        
        //   926: aload_2        
        //   927: aload_0        
        //   928: ldc_w           "display"
        //   931: ldc_w           "method__29$RUBY$display"
        //   934: ldc_w           "title;message,2,0,-1"
        //   937: iconst_2       
        //   938: ldc             "./lib//lister/runner.rb"
        //   940: ldc_w           253
        //   943: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   946: ldc_w           "qtitle;qmessage"
        //   949: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   952: pop            
        //   953: aload_1        
        //   954: aload_2        
        //   955: aload_0        
        //   956: ldc             "measurements"
        //   958: ldc_w           "method__30$RUBY$measurements"
        //   961: ldc             ",0,0,-1"
        //   963: iconst_0       
        //   964: ldc             "./lib//lister/runner.rb"
        //   966: ldc_w           260
        //   969: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   972: ldc_w           "NONE"
        //   975: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   978: pop            
        //   979: aload_1        
        //   980: aload_2        
        //   981: aload_0        
        //   982: ldc_w           "non_poll_measurements"
        //   985: ldc_w           "method__31$RUBY$non_poll_measurements"
        //   988: ldc             ",0,0,-1"
        //   990: iconst_0       
        //   991: ldc             "./lib//lister/runner.rb"
        //   993: ldc_w           264
        //   996: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   999: ldc_w           "NONE"
        //  1002: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1005: pop            
        //  1006: aload_1        
        //  1007: aload_2        
        //  1008: aload_0        
        //  1009: ldc_w           "should_perform_the_poll?"
        //  1012: ldc_w           "method__32$RUBY$should_perform_the_poll_p_"
        //  1015: ldc             ",0,0,-1"
        //  1017: iconst_0       
        //  1018: ldc             "./lib//lister/runner.rb"
        //  1020: ldc_w           268
        //  1023: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1026: ldc_w           "NONE"
        //  1029: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1032: pop            
        //  1033: aload_1        
        //  1034: aload_2        
        //  1035: aload_0        
        //  1036: ldc_w           "should_skip_the_poll?"
        //  1039: ldc_w           "method__33$RUBY$should_skip_the_poll_p_"
        //  1042: ldc             ",0,0,-1"
        //  1044: iconst_0       
        //  1045: ldc             "./lib//lister/runner.rb"
        //  1047: ldc_w           272
        //  1050: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1053: ldc_w           "NONE"
        //  1056: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1059: pop            
        //  1060: aload_1        
        //  1061: aload_2        
        //  1062: aload_0        
        //  1063: ldc_w           "set_look"
        //  1066: ldc_w           "method__34$RUBY$set_look"
        //  1069: ldc             ",0,0,-1"
        //  1071: iconst_0       
        //  1072: ldc             "./lib//lister/runner.rb"
        //  1074: ldc_w           276
        //  1077: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1080: ldc_w           "NONE"
        //  1083: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1086: pop            
        //  1087: aload_1        
        //  1088: aload_2        
        //  1089: aload_0        
        //  1090: ldc_w           "sleep_between_results"
        //  1093: ldc_w           "method__35$RUBY$sleep_between_results"
        //  1096: ldc             ",0,0,-1"
        //  1098: iconst_0       
        //  1099: ldc             "./lib//lister/runner.rb"
        //  1101: ldc_w           282
        //  1104: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1107: ldc_w           "NONE"
        //  1110: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1113: pop            
        //  1114: aload_1        
        //  1115: aload_2        
        //  1116: aload_0        
        //  1117: ldc_w           "sleep_before_opening_browser"
        //  1120: ldc_w           "method__36$RUBY$sleep_before_opening_browser"
        //  1123: ldc             ",0,0,-1"
        //  1125: iconst_0       
        //  1126: ldc             "./lib//lister/runner.rb"
        //  1128: ldc_w           286
        //  1131: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1134: ldc_w           "NONE"
        //  1137: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1140: pop            
        //  1141: aload_1        
        //  1142: aload_2        
        //  1143: aload_0        
        //  1144: ldc_w           "sleep_between_uploads"
        //  1147: ldc_w           "method__37$RUBY$sleep_between_uploads"
        //  1150: ldc             ",0,0,-1"
        //  1152: iconst_0       
        //  1153: ldc             "./lib//lister/runner.rb"
        //  1155: ldc_w           290
        //  1158: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1161: ldc_w           "NONE"
        //  1164: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1167: pop            
        //  1168: aload_0        
        //  1169: sipush          161
        //  1172: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1175: aload_1        
        //  1176: aload_2        
        //  1177: aload_2        
        //  1178: aload_0        
        //  1179: aload_1        
        //  1180: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1183: ldc_w           11
        //  1186: ldc_w           "thread"
        //  1189: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1192: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1195: pop            
        //  1196: aload_1        
        //  1197: aload_2        
        //  1198: aload_0        
        //  1199: ldc_w           "sudo?"
        //  1202: ldc_w           "method__38$RUBY$sudo_p_"
        //  1205: ldc             ",0,0,-1"
        //  1207: iconst_0       
        //  1208: ldc             "./lib//lister/runner.rb"
        //  1210: ldc_w           296
        //  1213: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1216: ldc_w           "NONE"
        //  1219: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1222: pop            
        //  1223: aload_1        
        //  1224: aload_2        
        //  1225: aload_0        
        //  1226: ldc_w           "perform_measurement"
        //  1229: ldc_w           "method__39$RUBY$perform_measurement"
        //  1232: ldc_w           "klass;blk;obj,1,0,-1"
        //  1235: iconst_1       
        //  1236: ldc             "./lib//lister/runner.rb"
        //  1238: ldc_w           300
        //  1241: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1244: ldc_w           "qklass;bblk"
        //  1247: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1250: pop            
        //  1251: aload_1        
        //  1252: aload_2        
        //  1253: aload_0        
        //  1254: ldc_w           "perform_measurements"
        //  1257: ldc_w           "method__40$RUBY$perform_measurements"
        //  1260: ldc             ",0,0,-1"
        //  1262: iconst_0       
        //  1263: ldc             "./lib//lister/runner.rb"
        //  1265: ldc_w           316
        //  1268: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1271: ldc_w           "NONE"
        //  1274: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1277: pop            
        //  1278: aload_1        
        //  1279: aload_2        
        //  1280: aload_0        
        //  1281: ldc_w           "debug_measurements_tasks"
        //  1284: ldc_w           "method__41$RUBY$debug_measurements_tasks"
        //  1287: ldc             ",0,0,-1"
        //  1289: iconst_0       
        //  1290: ldc             "./lib//lister/runner.rb"
        //  1292: ldc_w           333
        //  1295: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1298: ldc_w           "NONE"
        //  1301: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1304: pop            
        //  1305: aload_1        
        //  1306: aload_2        
        //  1307: aload_0        
        //  1308: ldc_w           "debug_poll_tasks"
        //  1311: ldc_w           "method__42$RUBY$debug_poll_tasks"
        //  1314: ldc             ",0,0,-1"
        //  1316: iconst_0       
        //  1317: ldc             "./lib//lister/runner.rb"
        //  1319: ldc_w           345
        //  1322: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1325: ldc_w           "NONE"
        //  1328: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1331: pop            
        //  1332: aload_1        
        //  1333: aload_2        
        //  1334: aload_0        
        //  1335: ldc_w           "interpret"
        //  1338: ldc_w           "method__43$RUBY$interpret"
        //  1341: ldc             ",0,0,-1"
        //  1343: iconst_0       
        //  1344: ldc             "./lib//lister/runner.rb"
        //  1346: ldc_w           352
        //  1349: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1352: ldc_w           "NONE"
        //  1355: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1358: pop            
        //  1359: aload_1        
        //  1360: aload_2        
        //  1361: aload_0        
        //  1362: ldc_w           "wait_text"
        //  1365: ldc_w           "method__44$RUBY$wait_text"
        //  1368: ldc             ",0,0,-1"
        //  1370: iconst_0       
        //  1371: ldc             "./lib//lister/runner.rb"
        //  1373: ldc_w           356
        //  1376: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1379: ldc_w           "NONE"
        //  1382: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1385: pop            
        //  1386: aload_1        
        //  1387: aload_2        
        //  1388: aload_0        
        //  1389: ldc_w           "select_language_question_description"
        //  1392: ldc_w           "method__45$RUBY$select_language_question_description"
        //  1395: ldc             ",0,0,-1"
        //  1397: iconst_0       
        //  1398: ldc             "./lib//lister/runner.rb"
        //  1400: ldc_w           360
        //  1403: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1406: ldc_w           "NONE"
        //  1409: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1412: pop            
        //  1413: aload_1        
        //  1414: aload_2        
        //  1415: aload_0        
        //  1416: ldc_w           "select_language"
        //  1419: ldc_w           "method__46$RUBY$select_language"
        //  1422: ldc_w           "questionnaire;pair,0,0,-1"
        //  1425: iconst_0       
        //  1426: ldc             "./lib//lister/runner.rb"
        //  1428: ldc_w           364
        //  1431: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1434: ldc_w           "NONE"
        //  1437: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1440: pop            
        //  1441: aload_1        
        //  1442: aload_2        
        //  1443: aload_0        
        //  1444: ldc_w           "select_measurements_question_description"
        //  1447: ldc_w           "method__47$RUBY$select_measurements_question_description"
        //  1450: ldc             ",0,0,-1"
        //  1452: iconst_0       
        //  1453: ldc             "./lib//lister/runner.rb"
        //  1455: ldc_w           385
        //  1458: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1461: ldc_w           "NONE"
        //  1464: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1467: pop            
        //  1468: aload_1        
        //  1469: aload_2        
        //  1470: aload_0        
        //  1471: ldc_w           "select_measurements"
        //  1474: ldc_w           "method__48$RUBY$select_measurements"
        //  1477: ldc_w           "question;questionnaire;kept;avoided,0,0,-1"
        //  1480: iconst_0       
        //  1481: ldc             "./lib//lister/runner.rb"
        //  1483: ldc_w           389
        //  1486: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1489: ldc_w           "NONE"
        //  1492: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1495: pop            
        //  1496: aload_1        
        //  1497: aload_2        
        //  1498: aload_0        
        //  1499: ldc_w           "select_poll_or_not_question_description"
        //  1502: ldc_w           "method__49$RUBY$select_poll_or_not_question_description"
        //  1505: ldc             ",0,0,-1"
        //  1507: iconst_0       
        //  1508: ldc             "./lib//lister/runner.rb"
        //  1510: ldc_w           416
        //  1513: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1516: ldc_w           "NONE"
        //  1519: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1522: pop            
        //  1523: aload_1        
        //  1524: aload_2        
        //  1525: aload_0        
        //  1526: ldc_w           "select_poll_or_not"
        //  1529: ldc_w           "method__50$RUBY$select_poll_or_not"
        //  1532: ldc_w           "questionnaire;res,0,0,-1"
        //  1535: iconst_0       
        //  1536: ldc             "./lib//lister/runner.rb"
        //  1538: ldc_w           420
        //  1541: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1544: ldc_w           "NONE"
        //  1547: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1550: pop            
        //  1551: aload_1        
        //  1552: aload_2        
        //  1553: aload_0        
        //  1554: ldc_w           "wait_end_of_measurements"
        //  1557: ldc_w           "method__51$RUBY$wait_end_of_measurements"
        //  1560: ldc             ",0,0,-1"
        //  1562: iconst_0       
        //  1563: ldc             "./lib//lister/runner.rb"
        //  1565: ldc_w           440
        //  1568: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1571: ldc_w           "NONE"
        //  1574: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1577: pop            
        //  1578: aload_1        
        //  1579: aload_2        
        //  1580: aload_0        
        //  1581: ldc_w           "during_measurements"
        //  1584: ldc_w           "method__52$RUBY$during_measurements"
        //  1587: ldc             ",0,0,-1"
        //  1589: iconst_0       
        //  1590: ldc             "./lib//lister/runner.rb"
        //  1592: ldc_w           447
        //  1595: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1598: ldc_w           "NONE"
        //  1601: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1604: pop            
        //  1605: aload_1        
        //  1606: aload_2        
        //  1607: aload_0        
        //  1608: ldc_w           "show_the_poll_if_needed"
        //  1611: ldc_w           "method__53$RUBY$show_the_poll_if_needed"
        //  1614: ldc             ",0,0,-1"
        //  1616: iconst_0       
        //  1617: ldc             "./lib//lister/runner.rb"
        //  1619: ldc_w           453
        //  1622: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1625: ldc_w           "NONE"
        //  1628: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1631: pop            
        //  1632: aload_1        
        //  1633: aload_2        
        //  1634: aload_0        
        //  1635: ldc_w           "show_the_poll"
        //  1638: ldc_w           "method__54$RUBY$show_the_poll"
        //  1641: ldc             ",0,0,-1"
        //  1643: iconst_0       
        //  1644: ldc             "./lib//lister/runner.rb"
        //  1646: ldc_w           461
        //  1649: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1652: ldc_w           "NONE"
        //  1655: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1658: pop            
        //  1659: aload_1        
        //  1660: aload_2        
        //  1661: aload_0        
        //  1662: ldc_w           "ask_for_email"
        //  1665: ldc_w           "method__55$RUBY$ask_for_email"
        //  1668: ldc             ",0,0,-1"
        //  1670: iconst_0       
        //  1671: ldc             "./lib//lister/runner.rb"
        //  1673: ldc_w           468
        //  1676: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1679: ldc_w           "NONE"
        //  1682: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1685: pop            
        //  1686: aload_1        
        //  1687: aload_2        
        //  1688: aload_0        
        //  1689: ldc_w           "url"
        //  1692: ldc_w           "method__56$RUBY$url"
        //  1695: ldc             ",0,0,-1"
        //  1697: iconst_0       
        //  1698: ldc             "./lib//lister/runner.rb"
        //  1700: ldc_w           473
        //  1703: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1706: ldc_w           "NONE"
        //  1709: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1712: pop            
        //  1713: aload_1        
        //  1714: aload_2        
        //  1715: aload_0        
        //  1716: ldc_w           "url_textfield"
        //  1719: ldc_w           "method__57$RUBY$url_textfield"
        //  1722: ldc_w           "tf,0,0,-1"
        //  1725: iconst_0       
        //  1726: ldc             "./lib//lister/runner.rb"
        //  1728: ldc_w           477
        //  1731: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1734: ldc_w           "NONE"
        //  1737: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1740: pop            
        //  1741: aload_1        
        //  1742: aload_2        
        //  1743: aload_0        
        //  1744: ldc_w           "prepare_utils"
        //  1747: ldc_w           "method__58$RUBY$prepare_utils"
        //  1750: ldc             ",0,0,-1"
        //  1752: iconst_0       
        //  1753: ldc             "./lib//lister/runner.rb"
        //  1755: ldc_w           483
        //  1758: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1761: ldc_w           "NONE"
        //  1764: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1767: pop            
        //  1768: aload_0        
        //  1769: sipush          334
        //  1772: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1775: aload_1        
        //  1776: aload_2        
        //  1777: aload_2        
        //  1778: bipush          6
        //  1780: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //  1783: dup            
        //  1784: iconst_0       
        //  1785: aload_0        
        //  1786: aload_1        
        //  1787: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1790: ldc_w           23
        //  1793: ldc_w           "progress_bar_panel"
        //  1796: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1799: aastore        
        //  1800: dup            
        //  1801: iconst_1       
        //  1802: aload_0        
        //  1803: aload_1        
        //  1804: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1807: ldc_w           24
        //  1810: ldc_w           "progress_bar"
        //  1813: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1816: aastore        
        //  1817: dup            
        //  1818: iconst_2       
        //  1819: aload_0        
        //  1820: aload_1        
        //  1821: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1824: ldc_w           25
        //  1827: ldc_w           "upload_bar_panel"
        //  1830: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1833: aastore        
        //  1834: dup            
        //  1835: iconst_3       
        //  1836: aload_0        
        //  1837: aload_1        
        //  1838: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1841: ldc_w           26
        //  1844: ldc_w           "upload_bar"
        //  1847: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1850: aastore        
        //  1851: dup            
        //  1852: iconst_4       
        //  1853: aload_0        
        //  1854: aload_1        
        //  1855: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1858: ldc_w           27
        //  1861: ldc_w           "poll_panel"
        //  1864: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1867: aastore        
        //  1868: dup            
        //  1869: iconst_5       
        //  1870: aload_0        
        //  1871: aload_1        
        //  1872: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1875: ldc_w           28
        //  1878: ldc_w           "wait_panel"
        //  1881: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1884: aastore        
        //  1885: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1888: pop            
        //  1889: aload_1        
        //  1890: aload_2        
        //  1891: aload_0        
        //  1892: ldc_w           "prepare_layout"
        //  1895: ldc_w           "method__59$RUBY$prepare_layout"
        //  1898: ldc             ",0,0,-1"
        //  1900: iconst_0       
        //  1901: ldc             "./lib//lister/runner.rb"
        //  1903: ldc_w           493
        //  1906: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1909: ldc_w           "NONE"
        //  1912: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1915: pop            
        //  1916: aload_1        
        //  1917: aload_2        
        //  1918: aload_0        
        //  1919: ldc_w           "prepare_poll_panel"
        //  1922: ldc_w           "method__60$RUBY$prepare_poll_panel"
        //  1925: ldc             ",0,0,-1"
        //  1927: iconst_0       
        //  1928: ldc             "./lib//lister/runner.rb"
        //  1930: ldc_w           500
        //  1933: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1936: ldc_w           "NONE"
        //  1939: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1942: pop            
        //  1943: aload_1        
        //  1944: aload_2        
        //  1945: aload_0        
        //  1946: ldc_w           "prepare_wait_panel"
        //  1949: ldc_w           "method__61$RUBY$prepare_wait_panel"
        //  1952: ldc_w           "label,0,0,-1"
        //  1955: iconst_0       
        //  1956: ldc             "./lib//lister/runner.rb"
        //  1958: ldc_w           506
        //  1961: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1964: ldc_w           "NONE"
        //  1967: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1970: pop            
        //  1971: aload_1        
        //  1972: aload_2        
        //  1973: aload_0        
        //  1974: ldc_w           "remove_poll_panel"
        //  1977: ldc_w           "method__62$RUBY$remove_poll_panel"
        //  1980: ldc             ",0,0,-1"
        //  1982: iconst_0       
        //  1983: ldc             "./lib//lister/runner.rb"
        //  1985: ldc_w           514
        //  1988: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1991: ldc_w           "NONE"
        //  1994: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1997: pop            
        //  1998: aload_1        
        //  1999: aload_2        
        //  2000: aload_0        
        //  2001: ldc_w           "show_wait_panel"
        //  2004: ldc_w           "method__63$RUBY$show_wait_panel"
        //  2007: ldc             ",0,0,-1"
        //  2009: iconst_0       
        //  2010: ldc             "./lib//lister/runner.rb"
        //  2012: ldc_w           521
        //  2015: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  2018: ldc_w           "NONE"
        //  2021: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  2024: pop            
        //  2025: aload_1        
        //  2026: aload_2        
        //  2027: aload_0        
        //  2028: ldc_w           "wait_a_bit"
        //  2031: ldc_w           "method__64$RUBY$wait_a_bit"
        //  2034: ldc             ",0,0,-1"
        //  2036: iconst_0       
        //  2037: ldc             "./lib//lister/runner.rb"
        //  2039: ldc_w           528
        //  2042: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  2045: ldc_w           "NONE"
        //  2048: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  2051: pop            
        //  2052: aload_1        
        //  2053: aload_2        
        //  2054: aload_0        
        //  2055: ldc_w           "prepare_progress_bar"
        //  2058: ldc_w           "method__65$RUBY$prepare_progress_bar"
        //  2061: ldc_w           "total_duration_weight,0,0,-1"
        //  2064: iconst_0       
        //  2065: ldc             "./lib//lister/runner.rb"
        //  2067: ldc_w           533
        //  2070: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  2073: ldc_w           "NONE"
        //  2076: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  2079: pop            
        //  2080: aload_1        
        //  2081: aload_2        
        //  2082: aload_0        
        //  2083: ldc_w           "remove_progress_bar"
        //  2086: ldc_w           "method__66$RUBY$remove_progress_bar"
        //  2089: ldc             ",0,0,-1"
        //  2091: iconst_0       
        //  2092: ldc             "./lib//lister/runner.rb"
        //  2094: ldc_w           546
        //  2097: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  2100: ldc_w           "NONE"
        //  2103: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  2106: pop            
        //  2107: aload_1        
        //  2108: aload_2        
        //  2109: aload_0        
        //  2110: ldc_w           "prepare_upload_bar"
        //  2113: ldc_w           "method__67$RUBY$prepare_upload_bar"
        //  2116: ldc             ",0,0,-1"
        //  2118: iconst_0       
        //  2119: ldc             "./lib//lister/runner.rb"
        //  2121: ldc_w           551
        //  2124: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  2127: ldc_w           "NONE"
        //  2130: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  2133: pop            
        //  2134: aload_1        
        //  2135: aload_2        
        //  2136: aload_0        
        //  2137: ldc_w           "send_bug_report?"
        //  2140: ldc_w           "method__68$RUBY$send_bug_report_p_"
        //  2143: ldc             ",0,0,-1"
        //  2145: iconst_0       
        //  2146: ldc             "./lib//lister/runner.rb"
        //  2148: ldc_w           563
        //  2151: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  2154: ldc_w           "NONE"
        //  2157: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  2160: pop            
        //  2161: aload_1        
        //  2162: aload_2        
        //  2163: aload_0        
        //  2164: ldc_w           "send_bug_report"
        //  2167: ldc_w           "method__69$RUBY$send_bug_report"
        //  2170: ldc_w           "err;rep,1,0,-1"
        //  2173: iconst_1       
        //  2174: ldc             "./lib//lister/runner.rb"
        //  2176: ldc_w           567
        //  2179: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  2182: ldc_w           "qerr"
        //  2185: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  2188: pop            
        //  2189: aload_1        
        //  2190: aload_2        
        //  2191: aload_0        
        //  2192: ldc_w           "start_logging_in_file"
        //  2195: ldc_w           "method__70$RUBY$start_logging_in_file"
        //  2198: ldc             ",0,0,-1"
        //  2200: iconst_0       
        //  2201: ldc             "./lib//lister/runner.rb"
        //  2203: ldc_w           574
        //  2206: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  2209: ldc_w           "NONE"
        //  2212: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  2215: pop            
        //  2216: aload_1        
        //  2217: aload_2        
        //  2218: aload_0        
        //  2219: ldc_w           "stop_logging_in_file"
        //  2222: ldc_w           "method__75$RUBY$stop_logging_in_file"
        //  2225: ldc             ",0,0,-1"
        //  2227: iconst_0       
        //  2228: ldc             "./lib//lister/runner.rb"
        //  2230: ldc_w           599
        //  2233: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  2236: ldc_w           "NONE"
        //  2239: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  2242: pop            
        //  2243: aload_1        
        //  2244: aload_2        
        //  2245: aload_0        
        //  2246: ldc_w           "propagate_bug_reports?"
        //  2249: ldc_w           "method__78$RUBY$propagate_bug_reports_p_"
        //  2252: ldc             ",0,0,-1"
        //  2254: iconst_0       
        //  2255: ldc             "./lib//lister/runner.rb"
        //  2257: ldc_w           612
        //  2260: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  2263: ldc_w           "NONE"
        //  2266: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  2269: pop            
        //  2270: aload_1        
        //  2271: aload_2        
        //  2272: aload_0        
        //  2273: ldc_w           "run"
        //  2276: ldc_w           "method__79$RUBY$run"
        //  2279: ldc_w           "err,0,0,-1"
        //  2282: iconst_0       
        //  2283: ldc             "./lib//lister/runner.rb"
        //  2285: ldc_w           616
        //  2288: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  2291: ldc_w           "NONE"
        //  2294: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  2297: pop            
        //  2298: aload_1        
        //  2299: aload_2        
        //  2300: aload_0        
        //  2301: ldc_w           "do_exit"
        //  2304: ldc_w           "method__83$RUBY$do_exit"
        //  2307: ldc             ",0,0,-1"
        //  2309: iconst_0       
        //  2310: ldc             "./lib//lister/runner.rb"
        //  2312: ldc_w           635
        //  2315: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  2318: ldc_w           "NONE"
        //  2321: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  2324: pop            
        //  2325: aload_1        
        //  2326: aload_2        
        //  2327: aload_0        
        //  2328: ldc_w           "run_steps"
        //  2331: ldc_w           "method__84$RUBY$run_steps"
        //  2334: ldc             ",0,0,-1"
        //  2336: iconst_0       
        //  2337: ldc             "./lib//lister/runner.rb"
        //  2339: ldc_w           640
        //  2342: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  2345: ldc_w           "NONE"
        //  2348: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  2351: pop            
        //  2352: aload_1        
        //  2353: aload_2        
        //  2354: aload_0        
        //  2355: ldc_w           "open_web_browser"
        //  2358: ldc_w           "method__85$RUBY$open_web_browser"
        //  2361: ldc             ",0,0,-1"
        //  2363: iconst_0       
        //  2364: ldc             "./lib//lister/runner.rb"
        //  2366: ldc_w           664
        //  2369: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  2372: ldc_w           "NONE"
        //  2375: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  2378: pop            
        //  2379: aload_1        
        //  2380: aload_2        
        //  2381: aload_0        
        //  2382: ldc_w           "propagate_results?"
        //  2385: ldc_w           "method__88$RUBY$propagate_results_p_"
        //  2388: ldc             ",0,0,-1"
        //  2390: iconst_0       
        //  2391: ldc             "./lib//lister/runner.rb"
        //  2393: ldc_w           688
        //  2396: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  2399: ldc_w           "NONE"
        //  2402: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  2405: pop            
        //  2406: aload_1        
        //  2407: aload_2        
        //  2408: aload_0        
        //  2409: ldc_w           "propagate_results"
        //  2412: ldc_w           "method__89$RUBY$propagate_results"
        //  2415: ldc_w           "idx,0,0,-1"
        //  2418: iconst_0       
        //  2419: ldc             "./lib//lister/runner.rb"
        //  2421: ldc_w           692
        //  2424: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  2427: ldc_w           "NONE"
        //  2430: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  2433: aload_1        
        //  2434: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //  2437: goto            2445
        //  2440: aload_1        
        //  2441: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //  2444: athrow         
        //  2445: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  30     2433   2440   2445   Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "Measurements", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__2$RUBY$Measurements(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return RubyArray.newArrayNoCopy(threadContext.runtime, new IRubyObject[] { file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom3(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant2(threadContext, "Measurements")), threadContext, "Poll"), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom5(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant4(threadContext, "Measurements")), threadContext, "Configuration"), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom7(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant6(threadContext, "Measurements")), threadContext, "Binaries"), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom9(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant8(threadContext, "Measurements")), threadContext, "Services"), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Measurements", 10)), threadContext, "NetworkServices", 11), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Measurements", 12)), threadContext, "UPnP", 13), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Measurements", 14)), threadContext, "NetworkScan", 15), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Measurements", 16)), threadContext, "WlanScan", 17), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Measurements", 18)), threadContext, "Netalyzr", 19) });
    }
    
    @JRubyMethod(name = "Utils", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$Utils(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return RubyArray.newArrayNoCopy(threadContext.runtime, new IRubyObject[] { file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Utils", 20)), threadContext, "Platform", 21), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Utils", 22)), threadContext, "Programs", 23), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Utils", 24)), threadContext, "Route", 25), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Utils", 26)), threadContext, "Scan", 27), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Utils", 28)), threadContext, "Servers", 29), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Utils", 30)), threadContext, "Services", 31), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Utils", 32)), threadContext, "Traffic", 33), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Utils", 34)), threadContext, "WifiScan", 35), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Utils", 36)), threadContext, "Traceroute", 37), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Utils", 38)), threadContext, "DNS", 39), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Utils", 40)), threadContext, "NetConfig", 41) });
    }
    
    @JRubyMethod(name = "sh", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$sh(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(56).call(threadContext, rubyObject, rubyObject, RubyString.newStringLight(threadContext.runtime, 20).append(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 53, 32)).append(rubyObject2.asString()).append(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 54, 32)));
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(57).call(threadContext, rubyObject, rubyObject, RubyString.newStringLight(threadContext.runtime, 20).append(rubyObject2.asString()));
    }
    
    @JRubyMethod(name = "log", frame = true, required = 1, optional = 1, rest = -1)
    public static IRubyObject method__5$RUBY$log(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject self, final IRubyObject[] array, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        Arity.raiseArgumentError(threadContext.runtime, array, 1, 2);
        currentScope.setValueZeroDepthZero(array[0]);
        final IRubyObject elementOrNull = RuntimeHelpers.elementOrNull(array, 1);
        if (elementOrNull != null) {
            currentScope.setValueOneDepthZero(elementOrNull);
        }
        else {
            currentScope.setValueOneDepthZero(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol6(threadContext.runtime, "info"));
        }
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(58).callIter(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(59).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getBlockBody0(threadContext, "block_0$RUBY$log,1,logger,false,2,./lib//lister/runner.rb,105,true")));
    }
    
    public static IRubyObject block_0$RUBY$log(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    10: astore          9
        //    12: aload_1        
        //    13: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    16: aload           4
        //    18: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    21: aload_3        
        //    22: astore          9
        //    24: pop            
        //    25: aload_0        
        //    26: bipush          60
        //    28: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           logger
        //    35: aload           5
        //    37: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    40: aload_1        
        //    41: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: aload           5
        //    49: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    52: aload_1        
        //    53: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    56: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  ---------------------------------------
        //  25     38      9     logger  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "store_config", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$store_config(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(61).call(threadContext, self, self, RubyString.newStringLight(threadContext.runtime, 20).append(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 55, 32)).append(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(62).call(threadContext, self, self).asString()));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(63).call(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "FileUtils", 42), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(64).call(threadContext, self, self));
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(65).callIter(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "File", 43), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(66).call(threadContext, self, self), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 56, 32), RuntimeHelpers.createBlock(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getBlockBody1(threadContext, "block_1$RUBY$store_config,1,f,false,2,./lib//lister/runner.rb,113,true")));
    }
    
    public static IRubyObject block_1$RUBY$store_config(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    10: astore          9
        //    12: aload_1        
        //    13: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    16: aload           4
        //    18: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    21: aload_3        
        //    22: astore          9
        //    24: pop            
        //    25: aload_0        
        //    26: bipush          67
        //    28: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           f
        //    35: aload_0        
        //    36: bipush          68
        //    38: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_0        
        //    44: aload_1        
        //    45: ldc             "YAML"
        //    47: bipush          44
        //    49: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: aload_0        
        //    53: bipush          69
        //    55: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    58: aload_1        
        //    59: aload_2        
        //    60: aload_2        
        //    61: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    64: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     46      9     f     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "fix_files_ownership", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$fix_files_ownership(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(70).call(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "ENV", 45), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 57, 32)));
        locals.setValueOneDepthZero(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(71).call(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "ENV", 46), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 58, 32)));
        IRubyObject rubyObject;
        if ((rubyObject = locals.getValueZeroDepthZeroOrNil(threadContext.nil)).isTrue()) {
            rubyObject = locals.getValueOneDepthZeroOrNil(threadContext.nil);
        }
        IRubyObject rubyObject2;
        if (rubyObject.isTrue()) {
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(72).call(threadContext, self, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 59, 32));
            rubyObject2 = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(73).callIter(threadContext, self, RuntimeHelpers.constructRubyArray(threadContext.runtime, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(74).call(threadContext, self, self), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(75).call(threadContext, self, self), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(76).call(threadContext, self, self)), RuntimeHelpers.createBlock(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getBlockBody2(threadContext, "block_2$RUBY$fix_files_ownership,1,name,false,2,./lib//lister/runner.rb,123,false")));
        }
        else {
            rubyObject2 = threadContext.nil;
        }
        return rubyObject2;
    }
    
    public static IRubyObject block_2$RUBY$fix_files_ownership(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject valueZeroDepthZero, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        currentScope.setValueZeroDepthZero(threadContext.nil);
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        currentScope.setValueZeroDepthZero(valueZeroDepthZero);
        try {
            return chained_8_rescue_1$RUBY$SYNTHETICfix_files_ownership(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, threadContext, rubyObject, valueZeroDepthZero, block);
        }
        catch (JumpException.RedoJump redoJump) {
            return chained_8_rescue_1$RUBY$SYNTHETICfix_files_ownership(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, threadContext, rubyObject, valueZeroDepthZero, block);
        }
    }
    
    public static IRubyObject chained_8_rescue_1$RUBY$SYNTHETICfix_files_ownership(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final DynamicScope currentScope = context.getCurrentScope();
        final IRubyObject errorInfo = context.getErrorInfo();
        IRubyObject rubyObject3 = null;
        Label_0134: {
            try {
                try {
                    rubyObject3 = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(77).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "FileUtils", 47), currentScope.getNextCapturedScope().getValueZeroDepthZeroOrNil(context.nil), currentScope.getNextCapturedScope().getValueOneDepthZeroOrNil(context.nil), currentScope.getValueZeroDepthZeroOrNil(context.nil));
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable currentThrowable) {
                    if (RuntimeHelpers.isJavaExceptionHandled(currentThrowable, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "Errno", 48)), context, "ENOENT", 49), context).isTrue()) {
                        rubyObject3 = context.nil;
                        RuntimeHelpers.clearErrorInfo(context);
                        break Label_0134;
                    }
                    throw currentThrowable;
                }
            }
            catch (JumpException.FlowControlException ex2) {
                context.setErrorInfo(errorInfo);
                throw ex2;
            }
        }
        context.setErrorInfo(errorInfo);
        return rubyObject3;
    }
    
    @JRubyMethod(name = "new_uid", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$new_uid(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        IRubyObject uid = context.nil;
        uid = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(78).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "UID", 50), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getFixnum0(context.runtime, 24));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(79).call(context, rubyObject, rubyObject, RubyString.newStringLight(context.runtime, 20).append(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(context.runtime, 60, 32)).append(uid.asString()));
        return uid;
    }
    
    @JRubyMethod(name = "lister_dir", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$lister_dir(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(80).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "File", 51), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(81).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "File", 52), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 61, 32), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 62, 32)));
    }
    
    @JRubyMethod(name = "config_file", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$config_file(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(82).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "File", 53), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(83).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "File", 54), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(84).call(threadContext, rubyObject, rubyObject), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 63, 32)));
    }
    
    @JRubyMethod(name = "debug_logfile", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$debug_logfile(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(85).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "File", 55), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(86).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "File", 56), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(87).call(threadContext, rubyObject, rubyObject), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 64, 32)));
    }
    
    @JRubyMethod(name = "read_config", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$read_config(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject ret = threadContext.nil;
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(88).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 65, 32));
        IRubyObject nil;
        if (file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(89).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "File", 57), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(90).call(threadContext, rubyObject, rubyObject)).isTrue()) {
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(91).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 66, 32));
            ret = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(92).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "YAML", 58), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(93).call(threadContext, rubyObject, rubyObject));
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(94).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 67, 32));
            nil = ret;
        }
        else {
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(95).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 68, 32));
            nil = threadContext.nil;
        }
        return nil;
    }
    
    @JRubyMethod(name = "default_config", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__14$RUBY$default_config(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return RuntimeHelpers.constructHash(threadContext.runtime, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol7(threadContext.runtime, "uid"), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(96).call(threadContext, rubyObject, rubyObject), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol8(threadContext.runtime, "run_id"), RubyFixnum.zero(threadContext.runtime));
    }
    
    @JRubyMethod(name = "get_config", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__15$RUBY$get_config(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject rubyObject2;
        if (!(rubyObject2 = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(97).call(threadContext, rubyObject, rubyObject)).isTrue()) {
            rubyObject2 = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(98).call(threadContext, rubyObject, rubyObject);
        }
        return rubyObject2;
    }
    
    @JRubyMethod(name = "config", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__16$RUBY$config(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@config") ? file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getByteList(69) : null) == null) {
            rubyObject = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.setVariable0(threadContext.runtime, "@config", object, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(99).call(threadContext, object, object));
        }
        else if (!(rubyObject = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getVariable0(threadContext.runtime, "@config", object)).isTrue()) {
            rubyObject = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.setVariable1(threadContext.runtime, "@config", object, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(100).call(threadContext, object, object));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "uid", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__17$RUBY$uid(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(101).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(102).call(threadContext, rubyObject, rubyObject), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol7(threadContext.runtime, "uid"));
    }
    
    @JRubyMethod(name = "run_id", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__18$RUBY$run_id(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(103).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(104).call(threadContext, rubyObject, rubyObject), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol8(threadContext.runtime, "run_id"));
    }
    
    @JRubyMethod(name = "configured_language", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__19$RUBY$configured_language(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(105).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(106).call(threadContext, rubyObject, rubyObject), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol9(threadContext.runtime, "language"));
    }
    
    @JRubyMethod(name = "language", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__20$RUBY$language(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject rubyObject2;
        if (!(rubyObject2 = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(107).call(threadContext, rubyObject, rubyObject)).isTrue()) {
            rubyObject2 = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 70, 32);
        }
        return rubyObject2;
    }
    
    @JRubyMethod(name = "window_name", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__21$RUBY$window_name(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return RubyString.newStringLight(threadContext.runtime, 20).append(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 71, 32)).append(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Lister", 59)), threadContext, "VERSION", 60).asString()).append(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 72, 32));
    }
    
    @JRubyMethod(name = "dummy_window_event", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__22$RUBY$dummy_window_event(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return threadContext.nil;
    }
    
    @JRubyMethod(name = "windowClosing", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__23$RUBY$windowClosing(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(108).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 73, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(109).call(threadContext, rubyObject, rubyObject);
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(110).call(threadContext, rubyObject, rubyObject);
    }
    
    @JRubyMethod(name = "new_frame", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__24$RUBY$new_frame(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        IRubyObject frame = context.nil;
        final IRubyObject call;
        frame = (call = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(111).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "JFrame", 61), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(112).call(context, rubyObject, rubyObject)));
        RuntimeHelpers.doAttrAsgn(call, RuntimeHelpers.selectAttrAsgnCallSite(call, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(113), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(114)), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(115).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "FlowLayout", 62)), context, rubyObject);
        final IRubyObject rubyObject2 = frame;
        RuntimeHelpers.doAttrAsgn(rubyObject2, RuntimeHelpers.selectAttrAsgnCallSite(rubyObject2, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(116), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(117)), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "JFrame", 63)), context, "EXIT_ON_CLOSE", 64), context, rubyObject);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(118).call(context, rubyObject, frame, rubyObject);
        final IRubyObject rubyObject3 = frame;
        final CallSite selectAttrAsgnCallSite = RuntimeHelpers.selectAttrAsgnCallSite(rubyObject3, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(119), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(120));
        final RubyBoolean true = context.runtime.getTrue();
        context.pollThreadEvents();
        RuntimeHelpers.doAttrAsgn(rubyObject3, selectAttrAsgnCallSite, true, context, rubyObject);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(121).call(context, rubyObject, frame, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getFixnum1(context.runtime, 650), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getFixnum2(context.runtime, 582));
        final IRubyObject rubyObject4 = frame;
        RuntimeHelpers.doAttrAsgn(rubyObject4, RuntimeHelpers.selectAttrAsgnCallSite(rubyObject4, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(122), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(123)), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(124).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "Toolbar", 65), rubyObject), context, rubyObject);
        return frame;
    }
    
    @JRubyMethod(name = "frame", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__25$RUBY$frame(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@frame") ? file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getByteList(69) : null) == null) {
            rubyObject = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.setVariable2(threadContext.runtime, "@frame", object, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(125).call(threadContext, object, object));
        }
        else if (!(rubyObject = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getVariable1(threadContext.runtime, "@frame", object)).isTrue()) {
            rubyObject = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.setVariable3(threadContext.runtime, "@frame", object, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(126).call(threadContext, object, object));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "initialize", frame = true, required = 0, optional = 1, rest = -1)
    public static IRubyObject method__26$RUBY$initialize(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     4: astore          9
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    10: aload_3        
        //    11: iconst_0       
        //    12: iconst_1       
        //    13: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    16: aload_3        
        //    17: iconst_0       
        //    18: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.elementOrNull:([Lorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    21: dup            
        //    22: ifnull          30
        //    25: astore          9
        //    27: goto            40
        //    30: aload_1        
        //    31: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    34: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //    37: astore          9
        //    39: pop            
        //    40: aload_0        
        //    41: aload_1        
        //    42: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    45: ldc_w           "@results"
        //    48: aload_2        
        //    49: aload_1        
        //    50: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    53: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //    56: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.setVariable4:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: pop            
        //    60: aload_0        
        //    61: aload_1        
        //    62: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    65: ldc_w           "@loggers"
        //    68: aload_2        
        //    69: aload_1        
        //    70: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    73: invokevirtual   org/jruby/Ruby.newArray:()Lorg/jruby/RubyArray;
        //    76: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.setVariable5:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: pop            
        //    80: aload_0        
        //    81: aload_1        
        //    82: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    85: ldc_w           "@params"
        //    88: aload_2        
        //    89: aload_0        
        //    90: bipush          127
        //    92: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    95: aload_1        
        //    96: aload_2        
        //    97: aload_1        
        //    98: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   101: aload_0        
        //   102: aload_1        
        //   103: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   106: ldc_w           10
        //   109: ldc_w           "propagate_results"
        //   112: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   115: aload_1        
        //   116: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   119: invokevirtual   org/jruby/Ruby.getTrue:()Lorg/jruby/RubyBoolean;
        //   122: aload_1        
        //   123: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //   126: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructHash:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyHash;
        //   129: aload           params
        //   131: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   134: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.setVariable6:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   137: pop            
        //   138: aload_0        
        //   139: sipush          128
        //   142: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   145: aload_1        
        //   146: aload_2        
        //   147: aload_2        
        //   148: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   151: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   156: ifeq            169
        //   159: aload           4
        //   161: aload_1        
        //   162: aload_2        
        //   163: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   166: goto            173
        //   169: aload_1        
        //   170: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   173: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  ---------------------------------------
        //  40     134     9     params  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "change_look", frame = true, required = 0, optional = 1, rest = -1)
    public static IRubyObject method__27$RUBY$change_look(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        final DynamicScope currentScope = context.getCurrentScope();
        Arity.raiseArgumentError(context.runtime, array, 0, 1);
        final IRubyObject elementOrNull = RuntimeHelpers.elementOrNull(array, 0);
        if (elementOrNull != null) {
            currentScope.setValueZeroDepthZero(elementOrNull);
        }
        else {
            currentScope.setValueZeroDepthZero(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(context.runtime, 74, 32));
        }
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(129).callIter(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(130).callIter(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(131).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(132).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "UIManager", 66))), RuntimeHelpers.createBlock(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getBlockBody3(context, "block_3$RUBY$change_look,1,i,false,2,./lib//lister/runner.rb,238,true"))), RuntimeHelpers.createBlock(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getBlockBody4(context, "block_4$RUBY$change_look,1,i,false,2,./lib//lister/runner.rb,238,true")));
    }
    
    public static IRubyObject block_3$RUBY$change_look(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    10: astore          9
        //    12: aload_1        
        //    13: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    16: aload           4
        //    18: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    21: aload_3        
        //    22: astore          9
        //    24: pop            
        //    25: aload_0        
        //    26: sipush          133
        //    29: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    32: aload_1        
        //    33: aload_2        
        //    34: aload_0        
        //    35: sipush          134
        //    38: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload           i
        //    45: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    48: aload           5
        //    50: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    53: aload_1        
        //    54: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     39      9     i     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_4$RUBY$change_look(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    10: astore          9
        //    12: aload_1        
        //    13: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    16: aload           4
        //    18: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    21: aload_3        
        //    22: astore          9
        //    24: pop            
        //    25: aload_0        
        //    26: sipush          135
        //    29: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    32: aload_1        
        //    33: aload_2        
        //    34: aload_2        
        //    35: aload_1        
        //    36: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    39: ldc_w           20
        //    42: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    45: aload_0        
        //    46: aload_1        
        //    47: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    50: bipush          75
        //    52: bipush          32
        //    54: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    57: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    60: aload_0        
        //    61: sipush          136
        //    64: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    67: aload_1        
        //    68: aload_2        
        //    69: aload           i
        //    71: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //    79: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    82: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    85: pop            
        //    86: aload_0        
        //    87: aload_1        
        //    88: ldc_w           "UIManager"
        //    91: bipush          67
        //    93: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    96: dup            
        //    97: aload_2        
        //    98: aload_0        
        //    99: sipush          137
        //   102: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   105: aload_0        
        //   106: sipush          138
        //   109: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   112: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   115: aload_0        
        //   116: sipush          139
        //   119: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   122: aload_1        
        //   123: aload_2        
        //   124: aload           i
        //   126: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   129: aload_1        
        //   130: aload_2        
        //   131: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   134: pop            
        //   135: aload_1        
        //   136: aload_1        
        //   137: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   140: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.breakJump:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   143: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     119     9     i     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "ask", frame = true, required = 2, optional = 0, rest = -1)
    public static IRubyObject method__28$RUBY$ask(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     4: astore          12
        //     6: aload_3        
        //     7: astore          10
        //     9: aload           4
        //    11: astore          message
        //    13: aload_0        
        //    14: sipush          140
        //    17: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    20: aload_1        
        //    21: aload_2        
        //    22: aload_0        
        //    23: aload_1        
        //    24: ldc_w           "JOptionPane"
        //    27: bipush          68
        //    29: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    32: aload_0        
        //    33: sipush          141
        //    36: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    39: aload_1        
        //    40: aload_2        
        //    41: aload_2        
        //    42: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: aload           message
        //    47: aload           title
        //    49: aload_0        
        //    50: aload_1        
        //    51: ldc_w           "JOptionPane"
        //    54: bipush          69
        //    56: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    62: aload_0        
        //    63: swap           
        //    64: aload_1        
        //    65: ldc_w           "YES_NO_OPTION"
        //    68: bipush          70
        //    70: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: astore          ret
        //    81: aload_0        
        //    82: sipush          142
        //    85: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    88: aload_1        
        //    89: aload_2        
        //    90: aload           ret
        //    92: ldc2_w          0
        //    95: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //    98: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   103: ifeq            120
        //   106: aload_1        
        //   107: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   110: invokevirtual   org/jruby/Ruby.getTrue:()Lorg/jruby/RubyBoolean;
        //   113: aload_1        
        //   114: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //   117: goto            131
        //   120: aload_1        
        //   121: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   124: invokevirtual   org/jruby/Ruby.getFalse:()Lorg/jruby/RubyBoolean;
        //   127: aload_1        
        //   128: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //   131: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  ---------------------------------------
        //  13     119     10    title    Lorg/jruby/runtime/builtin/IRubyObject;
        //  13     119     11    message  Lorg/jruby/runtime/builtin/IRubyObject;
        //  13     119     12    ret      Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "display", frame = true, required = 2, optional = 0, rest = -1)
    public static IRubyObject method__29$RUBY$display(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
        //     1: astore          10
        //     3: aload           4
        //     5: astore          message
        //     7: aload_0        
        //     8: sipush          143
        //    11: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    14: aload_1        
        //    15: aload_2        
        //    16: aload_0        
        //    17: aload_1        
        //    18: ldc_w           "JOptionPane"
        //    21: bipush          71
        //    23: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    26: aload_0        
        //    27: sipush          144
        //    30: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    33: aload_1        
        //    34: aload_2        
        //    35: aload_2        
        //    36: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: aload           message
        //    41: aload           title
        //    43: aload_0        
        //    44: aload_1        
        //    45: ldc_w           "JOptionPane"
        //    48: bipush          72
        //    50: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    56: aload_0        
        //    57: swap           
        //    58: aload_1        
        //    59: ldc_w           "PLAIN_MESSAGE"
        //    62: bipush          73
        //    64: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  ---------------------------------------
        //  7      67      10    title    Lorg/jruby/runtime/builtin/IRubyObject;
        //  7      67      11    message  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "measurements", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__30$RUBY$measurements(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if (!(rubyObject = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getVariable2(threadContext.runtime, "@measurements", object)).isTrue()) {
            rubyObject = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(145).call(threadContext, object, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(146).call(threadContext, object, object));
        }
        return rubyObject;
    }
    
    @JRubyMethod(name = "non_poll_measurements", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__31$RUBY$non_poll_measurements(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(147).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(148).call(threadContext, rubyObject, rubyObject), RuntimeHelpers.constructRubyArray(threadContext.runtime, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Measurements", 74)), threadContext, "Poll", 75)));
    }
    
    @JRubyMethod(name = "should_perform_the_poll?", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__32$RUBY$should_perform_the_poll_p_(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(149).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(150).call(threadContext, rubyObject, rubyObject), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Measurements", 76)), threadContext, "Poll", 77));
    }
    
    @JRubyMethod(name = "should_skip_the_poll?", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__33$RUBY$should_skip_the_poll_p_(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return RuntimeHelpers.negate(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(151).call(threadContext, rubyObject, rubyObject), threadContext.runtime);
    }
    
    @JRubyMethod(name = "set_look", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__34$RUBY$set_look(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(152).call(threadContext, rubyObject, rubyObject).isTrue() ? file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(153).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 74, 32)) : threadContext.nil;
    }
    
    @JRubyMethod(name = "sleep_between_results", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__35$RUBY$sleep_between_results(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(154).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getFloat0(threadContext.runtime, 0.5));
    }
    
    @JRubyMethod(name = "sleep_before_opening_browser", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__36$RUBY$sleep_before_opening_browser(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(155).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(156).call(threadContext, rubyObject, RubyFixnum.one(threadContext.runtime), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(157).call(threadContext, rubyObject, rubyObject, RubyFixnum.five(threadContext.runtime))));
    }
    
    @JRubyMethod(name = "sleep_between_uploads", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__37$RUBY$sleep_between_uploads(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(158).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(159).call(threadContext, rubyObject, RubyFixnum.three(threadContext.runtime), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(160).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getFloat1(threadContext.runtime, 0.5))));
    }
    
    @JRubyMethod(name = "sudo?", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__38$RUBY$sudo_p_(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject rubyObject2;
        if ((rubyObject2 = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(162).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "ENV", 78), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 57, 32))).isTrue() && (rubyObject2 = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(163).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "ENV", 79), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 58, 32))).isTrue()) {
            rubyObject2 = threadContext.runtime.getTrue();
            threadContext.pollThreadEvents();
        }
        return rubyObject2;
    }
    
    @JRubyMethod(name = "perform_measurement", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__39$RUBY$perform_measurement(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_3        
        //     7: aload           5
        //     9: swap           
        //    10: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: pop            
        //    14: aload_1        
        //    15: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    18: aload           4
        //    20: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    23: aload           5
        //    25: swap           
        //    26: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    29: pop            
        //    30: aload_0        
        //    31: sipush          164
        //    34: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    37: aload_1        
        //    38: aload_2        
        //    39: aload_2        
        //    40: aload_1        
        //    41: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    44: ldc_w           20
        //    47: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    50: aload_0        
        //    51: aload_1        
        //    52: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    55: bipush          76
        //    57: bipush          32
        //    59: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    62: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    65: aload           locals
        //    67: aload_1        
        //    68: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    71: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //    79: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    82: aload_0        
        //    83: aload_1        
        //    84: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    87: ldc_w           12
        //    90: ldc_w           "debug"
        //    93: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    96: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    99: pop            
        //   100: aload           locals
        //   102: aload_0        
        //   103: sipush          165
        //   106: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   109: aload_1        
        //   110: aload_2        
        //   111: aload           locals
        //   113: aload_1        
        //   114: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   117: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   120: aload_2        
        //   121: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   124: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   127: pop            
        //   128: aload_0        
        //   129: sipush          166
        //   132: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   135: aload_1        
        //   136: aload_2        
        //   137: aload_2        
        //   138: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   141: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   146: ifeq            188
        //   149: aload_0        
        //   150: sipush          167
        //   153: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   156: aload_1        
        //   157: aload_2        
        //   158: aload           locals
        //   160: aload_1        
        //   161: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   164: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   167: aload           locals
        //   169: aload_1        
        //   170: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   173: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   176: aload           4
        //   178: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.getBlockFromBlockPassBody:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/Block;
        //   181: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   184: pop            
        //   185: goto            188
        //   188: aload_0        
        //   189: sipush          168
        //   192: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   195: aload_1        
        //   196: aload_2        
        //   197: aload           locals
        //   199: aload_1        
        //   200: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   203: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   206: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   209: pop            
        //   210: aload_0        
        //   211: sipush          169
        //   214: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   217: aload_1        
        //   218: aload_2        
        //   219: aload_0        
        //   220: aload_1        
        //   221: ldc_w           "Thread"
        //   224: bipush          80
        //   226: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   229: aload_1        
        //   230: aload_2        
        //   231: aload_0        
        //   232: aload_1        
        //   233: ldc_w           "block_5$RUBY$perform_measurement,-1,meta_datas;hash,false,0,./lib//lister/runner.rb,305,true"
        //   236: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getBlockBody5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   239: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   242: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   245: pop            
        //   246: aload_0        
        //   247: sipush          177
        //   250: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   253: aload_1        
        //   254: aload_2        
        //   255: aload_2        
        //   256: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   259: pop            
        //   260: aload_0        
        //   261: sipush          178
        //   264: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   267: aload_1        
        //   268: aload_2        
        //   269: aload_2        
        //   270: aload_1        
        //   271: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   274: ldc_w           20
        //   277: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   280: aload_0        
        //   281: aload_1        
        //   282: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   285: bipush          79
        //   287: bipush          32
        //   289: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   292: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   295: aload           locals
        //   297: aload_1        
        //   298: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   301: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   304: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   309: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   312: aload_0        
        //   313: aload_1        
        //   314: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   317: bipush          80
        //   319: bipush          32
        //   321: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   324: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   327: aload_0        
        //   328: sipush          179
        //   331: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   334: aload_1        
        //   335: aload_2        
        //   336: aload           locals
        //   338: aload_1        
        //   339: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   342: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   345: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   348: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   353: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   356: aload_0        
        //   357: aload_1        
        //   358: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   361: bipush          72
        //   363: bipush          32
        //   365: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   368: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   371: aload_0        
        //   372: aload_1        
        //   373: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   376: ldc_w           12
        //   379: ldc_w           "debug"
        //   382: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   385: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   388: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  30     359     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_5$RUBY$perform_measurement(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final DynamicScope currentScope = context.getCurrentScope();
        final IRubyObject nil = context.nil;
        final IRubyObject nil2 = context.nil;
        RuntimeHelpers.processBlockArgument(context.runtime, block);
        final IRubyObject meta_datas = RuntimeHelpers.constructHash(context.runtime, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(context.runtime, 77, 32), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(170).call(context, rubyObject, currentScope.getNextCapturedScope().getValueTwoDepthZeroOrNil(context.nil)), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(context.runtime, 78, 32), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(171).call(context, rubyObject, rubyObject));
        final IRubyObject hash = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(172).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(173).call(context, rubyObject, currentScope.getNextCapturedScope().getValueTwoDepthZeroOrNil(context.nil)), meta_datas);
        final IRubyObject call = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(174).call(context, rubyObject, rubyObject);
        return RuntimeHelpers.doAttrAsgn(call, RuntimeHelpers.selectAttrAsgnCallSite(call, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(175), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(176)), currentScope.getNextCapturedScope().getValueZeroDepthZeroOrNil(context.nil), hash, context, rubyObject);
    }
    
    @JRubyMethod(name = "perform_measurements", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__40$RUBY$perform_measurements(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.setVariable7(context.runtime, "@thread", rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(180).callIter(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "Thread", 81), RuntimeHelpers.createBlock(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getBlockBody8(context, "block_6$RUBY$perform_measurements,-1,progress,false,0,./lib//lister/runner.rb,317,false"))));
    }
    
    public static IRubyObject block_6$RUBY$perform_measurements(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    10: aload           5
        //    12: swap           
        //    13: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    16: pop            
        //    17: aload_1        
        //    18: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    21: aload           4
        //    23: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    26: aload_3        
        //    27: pop            
        //    28: pop            
        //    29: aload           locals
        //    31: aload_1        
        //    32: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    35: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    38: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: pop            
        //    42: aload_0        
        //    43: sipush          181
        //    46: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload_0        
        //    52: sipush          182
        //    55: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    58: aload_1        
        //    59: aload_2        
        //    60: aload_2        
        //    61: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    64: aload_1        
        //    65: aload_2        
        //    66: aload_0        
        //    67: aload_1        
        //    68: ldc_w           "block_7$RUBY$perform_measurements,2,klass;idx,true,1,./lib//lister/runner.rb,319,false"
        //    71: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getBlockBody7:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    74: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    77: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: pop            
        //    81: aload_0        
        //    82: sipush          197
        //    85: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    88: aload_1        
        //    89: aload_2        
        //    90: aload_2        
        //    91: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    94: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  29     66      5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_7$RUBY$perform_measurements(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    10: aload           5
        //    12: swap           
        //    13: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    16: aload           5
        //    18: swap           
        //    19: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    22: pop            
        //    23: aload_1        
        //    24: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    27: aload           4
        //    29: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    32: aload_3        
        //    33: aload_1        
        //    34: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    37: iconst_1       
        //    38: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.ensureMultipleAssignableRubyArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;Z)Lorg/jruby/RubyArray;
        //    41: astore          9
        //    43: aload           9
        //    45: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilZero:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    48: aload           5
        //    50: swap           
        //    51: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: pop            
        //    55: aload           9
        //    57: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilOne:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: aload           5
        //    62: swap           
        //    63: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: pop            
        //    67: aload           9
        //    69: pop            
        //    70: pop            
        //    71: aload_0        
        //    72: sipush          183
        //    75: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    78: aload_1        
        //    79: aload_2        
        //    80: aload_2        
        //    81: aload           locals
        //    83: aload_1        
        //    84: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    87: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    90: aload_1        
        //    91: aload_2        
        //    92: aload_0        
        //    93: aload_1        
        //    94: ldc_w           "block_8$RUBY$perform_measurements,1,percent;v,false,2,./lib//lister/runner.rb,320,true"
        //    97: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getBlockBody6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   100: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   103: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   106: pop            
        //   107: aload           locals
        //   109: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   112: aload_0        
        //   113: sipush          191
        //   116: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   119: aload_1        
        //   120: aload_2        
        //   121: aload           locals
        //   123: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   126: aload_1        
        //   127: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   130: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   133: aload_0        
        //   134: sipush          192
        //   137: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   140: aload_1        
        //   141: aload_2        
        //   142: aload_0        
        //   143: sipush          193
        //   146: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   149: aload_1        
        //   150: aload_2        
        //   151: aload           locals
        //   153: aload_1        
        //   154: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   157: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   160: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   163: ldc2_w          100
        //   166: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //   169: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   172: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   175: pop            
        //   176: aload_0        
        //   177: sipush          194
        //   180: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   183: aload_1        
        //   184: aload_2        
        //   185: aload_2        
        //   186: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   189: dup            
        //   190: aload_2        
        //   191: aload_0        
        //   192: sipush          195
        //   195: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   198: aload_0        
        //   199: sipush          196
        //   202: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   205: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   208: aload           locals
        //   210: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   213: aload_1        
        //   214: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   217: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   220: aload_1        
        //   221: aload_2        
        //   222: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   225: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  71     155     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_8$RUBY$perform_measurements(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    10: astore          9
        //    12: aload_1        
        //    13: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    16: astore          10
        //    18: aload_1        
        //    19: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    22: aload           4
        //    24: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    27: aload_3        
        //    28: astore          9
        //    30: pop            
        //    31: aload_0        
        //    32: sipush          184
        //    35: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    38: aload_1        
        //    39: aload_2        
        //    40: aload           5
        //    42: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    45: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    48: aload_1        
        //    49: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: aload_0        
        //    56: sipush          185
        //    59: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    62: aload_1        
        //    63: aload_2        
        //    64: aload_0        
        //    65: sipush          186
        //    68: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    71: aload_1        
        //    72: aload_2        
        //    73: aload           5
        //    75: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    78: aload_1        
        //    79: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    85: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: aload_0        
        //    89: sipush          187
        //    92: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    95: aload_1        
        //    96: aload_2        
        //    97: aload_1        
        //    98: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   101: aload           percent
        //   103: aload_0        
        //   104: aload_1        
        //   105: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   108: bipush          95
        //   110: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getFixnum3:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   113: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   116: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   119: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   122: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   125: astore          v
        //   127: aload_0        
        //   128: sipush          188
        //   131: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   134: aload_1        
        //   135: aload_2        
        //   136: aload_2        
        //   137: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   140: dup            
        //   141: aload_2        
        //   142: aload_0        
        //   143: sipush          189
        //   146: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   149: aload_0        
        //   150: sipush          190
        //   153: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   156: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   159: aload           v
        //   161: aload_1        
        //   162: aload_2        
        //   163: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   166: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  ---------------------------------------
        //  31     136     9     percent  Lorg/jruby/runtime/builtin/IRubyObject;
        //  31     136     10    v        Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "debug_measurements_tasks", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__41$RUBY$debug_measurements_tasks(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        if (file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(198).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(199).call(threadContext, rubyObject, rubyObject), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(threadContext.runtime, 13, "artificial_measurement_delay")).isTrue()) {
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(200).call(threadContext, rubyObject, rubyObject, RubyString.newStringLight(threadContext.runtime, 20).append(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 81, 32)).append(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(201).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(202).call(threadContext, rubyObject, rubyObject), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(threadContext.runtime, 13, "artificial_measurement_delay")).asString()).append(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 82, 32)));
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(203).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(204).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(205).call(threadContext, rubyObject, rubyObject), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(threadContext.runtime, 13, "artificial_measurement_delay")));
        }
        IRubyObject rubyObject2;
        if (file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(206).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(207).call(threadContext, rubyObject, rubyObject), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(threadContext.runtime, 14, "inject_measurement_error")).isTrue()) {
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(208).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 83, 32));
            rubyObject2 = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(209).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(210).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Debug", 82)), threadContext, "DebugException", 83), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 84, 32)));
        }
        else {
            rubyObject2 = threadContext.nil;
        }
        return rubyObject2;
    }
    
    @JRubyMethod(name = "debug_poll_tasks", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__42$RUBY$debug_poll_tasks(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject rubyObject2;
        if (file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(211).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(212).call(threadContext, rubyObject, rubyObject), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(threadContext.runtime, 15, "inject_poll_error")).isTrue()) {
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(213).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 85, 32));
            rubyObject2 = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(214).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(215).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Debug", 84)), threadContext, "DebugException", 85), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 86, 32)));
        }
        else {
            rubyObject2 = threadContext.nil;
        }
        return rubyObject2;
    }
    
    @JRubyMethod(name = "interpret", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__43$RUBY$interpret(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(216).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "Interpret", 86), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(217).call(context, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "wait_text", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__44$RUBY$wait_text(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(218).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(219).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "select_language_question_description", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__45$RUBY$select_language_question_description(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(220).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "QuestionsList", 87));
    }
    
    @JRubyMethod(name = "select_language", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__46$RUBY$select_language(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(221).call(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Questionnaire", 88)), threadContext, "Questionnaire", 89), RuntimeHelpers.constructRubyArray(threadContext.runtime, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(222).call(threadContext, self, self)), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(223).call(threadContext, self, self), RuntimeHelpers.constructHash(threadContext.runtime, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(threadContext.runtime, 16, "done"), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(threadContext.runtime, 17, "ok"))));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(224).call(threadContext, self, locals.getValueZeroDepthZeroOrNil(threadContext.nil));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(225).call(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(226).call(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(227).call(threadContext, self, self)), locals.getValueZeroDepthZeroOrNil(threadContext.nil), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "BorderLayout", 90)), threadContext, "CENTER", 91));
        final IRubyObject call = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(228).call(threadContext, self, self);
        final CallSite selectAttrAsgnCallSite = RuntimeHelpers.selectAttrAsgnCallSite(call, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(229), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(230));
        final RubyBoolean true = threadContext.runtime.getTrue();
        threadContext.pollThreadEvents();
        RuntimeHelpers.doAttrAsgn(call, selectAttrAsgnCallSite, true, threadContext, self);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(231).call(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Questionnaire", 92)), threadContext, "ApplicationWorkFlow", 93), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(232).call(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(233).call(threadContext, self, locals.getValueZeroDepthZeroOrNil(threadContext.nil)), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(threadContext.runtime, 16, "done")));
        final IRubyObject valueZeroDepthZeroOrNil = locals.getValueZeroDepthZeroOrNil(threadContext.nil);
        final CallSite selectAttrAsgnCallSite2 = RuntimeHelpers.selectAttrAsgnCallSite(valueZeroDepthZeroOrNil, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(234), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(235));
        final RubyBoolean false = threadContext.runtime.getFalse();
        threadContext.pollThreadEvents();
        RuntimeHelpers.doAttrAsgn(valueZeroDepthZeroOrNil, selectAttrAsgnCallSite2, false, threadContext, self);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(236).call(threadContext, self, locals.getValueZeroDepthZeroOrNil(threadContext.nil));
        locals.setValueOneDepthZero(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(237).callIter(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(238).call(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(239).call(threadContext, self, locals.getValueZeroDepthZeroOrNil(threadContext.nil)), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol9(threadContext.runtime, "language")), RuntimeHelpers.createBlock(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getBlockBody9(threadContext, "block_9$RUBY$select_language,2,k;val,true,1,./lib//lister/runner.rb,374,true"))));
        if (locals.getValueOneDepthZeroOrNil(threadContext.nil).isTrue()) {
            final IRubyObject call2 = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(240).call(threadContext, self, self);
            RuntimeHelpers.doAttrAsgn(call2, RuntimeHelpers.selectAttrAsgnCallSite(call2, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(241), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(242)), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol9(threadContext.runtime, "language"), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(243).call(threadContext, self, locals.getValueOneDepthZeroOrNil(threadContext.nil)), threadContext, self);
        }
        else {
            final IRubyObject call3 = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(244).call(threadContext, self, self);
            RuntimeHelpers.doAttrAsgn(call3, RuntimeHelpers.selectAttrAsgnCallSite(call3, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(245), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(246)), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol9(threadContext.runtime, "language"), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 70, 32), threadContext, self);
        }
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(247).call(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(248).call(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(249).call(threadContext, self, self)), locals.getValueZeroDepthZeroOrNil(threadContext.nil));
    }
    
    public static IRubyObject block_9$RUBY$select_language(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    10: astore          9
        //    12: aload_1        
        //    13: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    16: astore          10
        //    18: aload_1        
        //    19: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    22: aload           4
        //    24: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    27: aload_3        
        //    28: aload_1        
        //    29: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    32: iconst_1       
        //    33: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.ensureMultipleAssignableRubyArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;Z)Lorg/jruby/RubyArray;
        //    36: astore          11
        //    38: aload           11
        //    40: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilZero:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: astore          9
        //    45: aload           11
        //    47: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilOne:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: astore          10
        //    52: aload           11
        //    54: pop            
        //    55: pop            
        //    56: aload           val
        //    58: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  56     3       9     k     Lorg/jruby/runtime/builtin/IRubyObject;
        //  56     3       10    val   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "select_measurements_question_description", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__47$RUBY$select_measurements_question_description(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(250).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "QuestionsList", 94), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(251).call(context, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "select_measurements", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__48$RUBY$select_measurements(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        final DynamicScope currentScope;
        final DynamicScope locals = currentScope = context.getCurrentScope();
        final CallSite callSite = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(252);
        final IRubyObject constant = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "Questionnaire", 95)), context, "CheckBoxQuestion", 96);
        final IRubyObject call = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(253).call(context, rubyObject, rubyObject);
        final IRubyObject call2 = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(254).call(context, rubyObject, rubyObject);
        final Ruby runtime = context.runtime;
        final RubySymbol symbol = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(context.runtime, 18, "default");
        final RubyBoolean true = context.runtime.getTrue();
        context.pollThreadEvents();
        currentScope.setValueZeroDepthZero(callSite.call(context, rubyObject, constant, call, call2, RuntimeHelpers.constructHash(runtime, symbol, true)));
        locals.setValueOneDepthZero(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(255).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "Questionnaire", 97)), context, "Questionnaire", 98), RuntimeHelpers.constructRubyArray(context.runtime, locals.getValueZeroDepthZeroOrNil(context.nil)), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(256).call(context, rubyObject, rubyObject), RuntimeHelpers.constructHash(context.runtime, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(context.runtime, 16, "done"), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(context.runtime, 19, "start"))));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(257).call(context, rubyObject, locals.getValueOneDepthZeroOrNil(context.nil));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(258).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(259).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(260).call(context, rubyObject, rubyObject)), locals.getValueOneDepthZeroOrNil(context.nil), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "BorderLayout", 99)), context, "CENTER", 100));
        final IRubyObject call3 = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(261).call(context, rubyObject, rubyObject);
        final CallSite selectAttrAsgnCallSite = RuntimeHelpers.selectAttrAsgnCallSite(call3, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(262), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(263));
        final RubyBoolean true2 = context.runtime.getTrue();
        context.pollThreadEvents();
        RuntimeHelpers.doAttrAsgn(call3, selectAttrAsgnCallSite, true2, context, rubyObject);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(264).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "Questionnaire", 101)), context, "ApplicationWorkFlow", 102), locals.getValueOneDepthZeroOrNil(context.nil));
        final IRubyObject valueOneDepthZeroOrNil = locals.getValueOneDepthZeroOrNil(context.nil);
        final CallSite selectAttrAsgnCallSite2 = RuntimeHelpers.selectAttrAsgnCallSite(valueOneDepthZeroOrNil, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(265), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(266));
        final RubyBoolean false = context.runtime.getFalse();
        context.pollThreadEvents();
        RuntimeHelpers.doAttrAsgn(valueOneDepthZeroOrNil, selectAttrAsgnCallSite2, false, context, rubyObject);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(267).call(context, rubyObject, locals.getValueOneDepthZeroOrNil(context.nil));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(268).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(269).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(270).call(context, rubyObject, rubyObject)), locals.getValueOneDepthZeroOrNil(context.nil));
        final RubyArray ensureMultipleAssignableRubyArray = RuntimeHelpers.ensureMultipleAssignableRubyArray(RuntimeHelpers.aryToAry(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(271).callIter(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(272).call(context, rubyObject, rubyObject), RuntimeHelpers.createBlock(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getBlockBody(context, 10, "block_10$RUBY$select_measurements,1,m,false,2,./lib//lister/runner.rb,403,false")))), context.runtime, true);
        locals.setValueTwoDepthZero(RuntimeHelpers.arrayEntryOrNilZero(ensureMultipleAssignableRubyArray));
        locals.setValueThreeDepthZero(RuntimeHelpers.arrayEntryOrNilOne(ensureMultipleAssignableRubyArray));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(276).callIter(context, rubyObject, locals.getValueThreeDepthZeroOrNil(context.nil), RuntimeHelpers.createBlock(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getBlockBody(context, 11, "block_11$RUBY$select_measurements,1,klass,false,2,./lib//lister/runner.rb,407,true")));
        return RuntimeHelpers.doAttrAsgn(rubyObject, RuntimeHelpers.selectAttrAsgnCallSite(rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(280), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(281)), locals.getValueTwoDepthZeroOrNil(context.nil), context, rubyObject);
    }
    
    public static IRubyObject block_10$RUBY$select_measurements(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    10: aload           5
        //    12: swap           
        //    13: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    16: pop            
        //    17: aload_1        
        //    18: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    21: aload           4
        //    23: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    26: aload_3        
        //    27: aload           5
        //    29: swap           
        //    30: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: pop            
        //    34: pop            
        //    35: aload_0        
        //    36: sipush          273
        //    39: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    42: aload_1        
        //    43: aload_2        
        //    44: aload_0        
        //    45: sipush          274
        //    48: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    51: aload_1        
        //    52: aload_2        
        //    53: aload_0        
        //    54: sipush          275
        //    57: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    60: aload_1        
        //    61: aload_2        
        //    62: aload           locals
        //    64: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    67: aload_1        
        //    68: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    71: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: aload_0        
        //    78: aload_1        
        //    79: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    82: ldc             "measurements"
        //    84: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    87: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    90: aload           locals
        //    92: aload_1        
        //    93: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    96: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    99: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   102: areturn        
        //   103: pop            
        //   104: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     68      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     103    103    107    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_11$RUBY$select_measurements(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    10: astore          9
        //    12: aload_1        
        //    13: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    16: aload           4
        //    18: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    21: aload_3        
        //    22: astore          9
        //    24: pop            
        //    25: aload_0        
        //    26: sipush          277
        //    29: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    32: aload_1        
        //    33: aload_2        
        //    34: aload_2        
        //    35: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    38: dup            
        //    39: aload_2        
        //    40: aload_0        
        //    41: sipush          278
        //    44: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    47: aload_0        
        //    48: sipush          279
        //    51: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    54: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    57: aload           klass
        //    59: aload_1        
        //    60: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    63: aload_0        
        //    64: aload_1        
        //    65: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    68: bipush          87
        //    70: bipush          32
        //    72: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    75: aload_1        
        //    76: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    79: invokevirtual   org/jruby/Ruby.getTrue:()Lorg/jruby/RubyBoolean;
        //    82: aload_1        
        //    83: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //    86: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructHash:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyHash;
        //    89: aload_1        
        //    90: aload_2        
        //    91: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    94: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name   Signature
        //  -----  ------  ----  -----  ---------------------------------------
        //  25     70      9     klass  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "select_poll_or_not_question_description", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__49$RUBY$select_poll_or_not_question_description(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(282).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "QuestionsList", 103));
    }
    
    @JRubyMethod(name = "select_poll_or_not", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__50$RUBY$select_poll_or_not(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        final DynamicScope locals = context.getCurrentScope();
        locals.setValueZeroDepthZero(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(283).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "Questionnaire", 104)), context, "Questionnaire", 105), RuntimeHelpers.constructRubyArray(context.runtime, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(284).call(context, rubyObject, rubyObject)), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(285).call(context, rubyObject, rubyObject), RuntimeHelpers.constructHash(context.runtime, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(context.runtime, 16, "done"), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(context.runtime, 19, "start"))));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(286).call(context, rubyObject, locals.getValueZeroDepthZeroOrNil(context.nil));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(287).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(288).call(context, rubyObject, rubyObject), locals.getValueZeroDepthZeroOrNil(context.nil), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "BorderLayout", 106)), context, "CENTER", 107));
        final IRubyObject call = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(289).call(context, rubyObject, rubyObject);
        final CallSite selectAttrAsgnCallSite = RuntimeHelpers.selectAttrAsgnCallSite(call, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(290), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(291));
        final RubyBoolean true = context.runtime.getTrue();
        context.pollThreadEvents();
        RuntimeHelpers.doAttrAsgn(call, selectAttrAsgnCallSite, true, context, rubyObject);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(292).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "Questionnaire", 108)), context, "ApplicationWorkFlow", 109), locals.getValueZeroDepthZeroOrNil(context.nil));
        final IRubyObject valueZeroDepthZeroOrNil = locals.getValueZeroDepthZeroOrNil(context.nil);
        final CallSite selectAttrAsgnCallSite2 = RuntimeHelpers.selectAttrAsgnCallSite(valueZeroDepthZeroOrNil, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(293), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(294));
        final RubyBoolean false = context.runtime.getFalse();
        context.pollThreadEvents();
        RuntimeHelpers.doAttrAsgn(valueZeroDepthZeroOrNil, selectAttrAsgnCallSite2, false, context, rubyObject);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(295).call(context, rubyObject, locals.getValueZeroDepthZeroOrNil(context.nil));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(296).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(297).call(context, rubyObject, rubyObject), locals.getValueZeroDepthZeroOrNil(context.nil));
        locals.setValueOneDepthZero(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(298).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(299).call(context, rubyObject, locals.getValueZeroDepthZeroOrNil(context.nil)), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(context.runtime, 20, "poll_or_not")));
        if (!locals.getValueOneDepthZeroOrNil(context.nil).isTrue()) {
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(300).call(context, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "RuntimeError", 110), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(context.runtime, 88, 32));
        }
        IRubyObject rubyObject2;
        if (file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(301).call(context, rubyObject, locals.getValueOneDepthZeroOrNil(context.nil), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(context.runtime, 21, "yes")).isTrue()) {
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(302).call(context, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(context.runtime, 89, 32));
            rubyObject2 = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(303).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(304).call(context, rubyObject, rubyObject), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "Measurements", 111)), context, "Poll", 112));
        }
        else {
            rubyObject2 = context.nil;
        }
        return rubyObject2;
    }
    
    @JRubyMethod(name = "wait_end_of_measurements", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__51$RUBY$wait_end_of_measurements(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(305).call(threadContext, object, object, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 90, 32), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(threadContext.runtime, 12, "debug"));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(306).call(threadContext, object, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(307).call(threadContext, object, object));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(308).call(threadContext, object, object, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 91, 32), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(threadContext.runtime, 12, "debug"));
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.setVariable8(threadContext.runtime, "@thread", object, threadContext.nil);
    }
    
    @JRubyMethod(name = "during_measurements", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__52$RUBY$during_measurements(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(309).call(context, rubyObject, rubyObject);
        block.yieldSpecific(context);
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(310).call(context, rubyObject, rubyObject);
    }
    
    @JRubyMethod(name = "show_the_poll_if_needed", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__53$RUBY$show_the_poll_if_needed(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(311).call(threadContext, rubyObject, rubyObject).isTrue() ? file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(312).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 92, 32)) : file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(313).call(threadContext, rubyObject, rubyObject);
    }
    
    @JRubyMethod(name = "show_the_poll", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__54$RUBY$show_the_poll(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(314).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 93, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(315).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Measurements", 113)), threadContext, "Poll", 114));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(316).call(threadContext, rubyObject, rubyObject);
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(317).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 94, 32));
    }
    
    @JRubyMethod(name = "ask_for_email", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__55$RUBY$ask_for_email(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(318).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 95, 32));
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(319).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Measurements", 115)), threadContext, "AskEmail", 116));
    }
    
    @JRubyMethod(name = "url", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__56$RUBY$url(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(320).call(threadContext, rubyObject, RuntimeHelpers.constructRubyArray(threadContext.runtime, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Lister", 117)), threadContext, "WEBSITE", 118), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 96, 32), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(321).call(threadContext, rubyObject, rubyObject)), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 97, 32));
    }
    
    @JRubyMethod(name = "url_textfield", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__57$RUBY$url_textfield(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject tf = threadContext.nil;
        final IRubyObject call;
        tf = (call = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(322).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "JTextField", 119), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(323).call(threadContext, rubyObject, rubyObject)));
        final CallSite selectAttrAsgnCallSite = RuntimeHelpers.selectAttrAsgnCallSite(call, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(324), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(325));
        final RubyBoolean false = threadContext.runtime.getFalse();
        threadContext.pollThreadEvents();
        RuntimeHelpers.doAttrAsgn(call, selectAttrAsgnCallSite, false, threadContext, rubyObject);
        return tf;
    }
    
    @JRubyMethod(name = "prepare_utils", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__58$RUBY$prepare_utils(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(326).callIter(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(327).call(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(328).call(threadContext, self, self)), RuntimeHelpers.createBlock(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getBlockBody(threadContext, 12, "block_12$RUBY$prepare_utils,1,util,false,2,./lib//lister/runner.rb,484,true")));
    }
    
    public static IRubyObject block_12$RUBY$prepare_utils(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    10: astore          9
        //    12: aload_1        
        //    13: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    16: aload           4
        //    18: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    21: aload_3        
        //    22: astore          9
        //    24: pop            
        //    25: aload_0        
        //    26: sipush          329
        //    29: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    32: aload_1        
        //    33: aload_2        
        //    34: aload           util
        //    36: aload_0        
        //    37: aload_1        
        //    38: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    41: ldc_w           22
        //    44: ldc_w           "logger="
        //    47: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    50: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    58: ifeq            115
        //    61: aload           util
        //    63: dup            
        //    64: aload_2        
        //    65: aload_0        
        //    66: sipush          330
        //    69: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    72: aload_0        
        //    73: sipush          331
        //    76: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    79: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    82: aload_0        
        //    83: sipush          332
        //    86: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    89: aload_1        
        //    90: aload_2        
        //    91: aload_0        
        //    92: sipush          333
        //    95: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    98: aload_1        
        //    99: aload_2        
        //   100: aload_2        
        //   101: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   104: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   107: aload_1        
        //   108: aload_2        
        //   109: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   112: goto            119
        //   115: aload_1        
        //   116: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   119: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     95      9     util  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "prepare_layout", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__59$RUBY$prepare_layout(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        final IRubyObject call = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(335).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(336).call(threadContext, rubyObject, rubyObject));
        RuntimeHelpers.doAttrAsgn(call, RuntimeHelpers.selectAttrAsgnCallSite(call, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(337), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(338)), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(339).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "BorderLayout", 120)), threadContext, rubyObject);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(340).call(threadContext, rubyObject, rubyObject);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(341).call(threadContext, rubyObject, rubyObject);
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(342).call(threadContext, rubyObject, rubyObject);
    }
    
    @JRubyMethod(name = "prepare_poll_panel", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__60$RUBY$prepare_poll_panel(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext context, final IRubyObject self, final Block block) {
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.setVariable9(context.runtime, "@poll_panel", self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(343).call(context, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "JPanel", 121)));
        final IRubyObject call = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(344).call(context, self, self);
        RuntimeHelpers.doAttrAsgn(call, RuntimeHelpers.selectAttrAsgnCallSite(call, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(345), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(346)), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(347).call(context, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "FlowLayout", 122)), context, self);
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(348).call(context, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(349).call(context, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(350).call(context, self, self)), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(351).call(context, self, self), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "BorderLayout", 123)), context, "CENTER", 124));
    }
    
    @JRubyMethod(name = "prepare_wait_panel", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__61$RUBY$prepare_wait_panel(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        IRubyObject label = threadContext.nil;
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.setVariable(threadContext.runtime, 10, "@wait_panel", self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(352).call(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "JPanel", 125)));
        final IRubyObject call = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(353).call(threadContext, self, self);
        RuntimeHelpers.doAttrAsgn(call, RuntimeHelpers.selectAttrAsgnCallSite(call, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(354), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(355)), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(356).call(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "FlowLayout", 126)), threadContext, self);
        final IRubyObject call2;
        label = (call2 = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(357).call(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "JLabel", 127), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(358).call(threadContext, self, self), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "JLabel", 128)), threadContext, "CENTER", 129)));
        RuntimeHelpers.doAttrAsgn(call2, RuntimeHelpers.selectAttrAsgnCallSite(call2, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(359), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(360)), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(361).call(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Dimension", 130), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getFixnum4(threadContext.runtime, 300), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getFixnum4(threadContext.runtime, 300)), threadContext, self);
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(362).call(threadContext, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(363).call(threadContext, self, self), label);
    }
    
    @JRubyMethod(name = "remove_poll_panel", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__62$RUBY$remove_poll_panel(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(364).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 98, 32));
        final IRubyObject call = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(365).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(366).call(threadContext, rubyObject, rubyObject));
        final CallSite selectAttrAsgnCallSite = RuntimeHelpers.selectAttrAsgnCallSite(call, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(367), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(368));
        final RubyBoolean false = threadContext.runtime.getFalse();
        threadContext.pollThreadEvents();
        RuntimeHelpers.doAttrAsgn(call, selectAttrAsgnCallSite, false, threadContext, rubyObject);
        final IRubyObject call2 = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(369).call(threadContext, rubyObject, rubyObject);
        final CallSite selectAttrAsgnCallSite2 = RuntimeHelpers.selectAttrAsgnCallSite(call2, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(370), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(371));
        final RubyBoolean false2 = threadContext.runtime.getFalse();
        threadContext.pollThreadEvents();
        RuntimeHelpers.doAttrAsgn(call2, selectAttrAsgnCallSite2, false2, threadContext, rubyObject);
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(372).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(373).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(374).call(threadContext, rubyObject, rubyObject)), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(375).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "show_wait_panel", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__63$RUBY$show_wait_panel(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(376).call(context, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(context.runtime, 99, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(377).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(378).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(379).call(context, rubyObject, rubyObject)), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(380).call(context, rubyObject, rubyObject), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "BorderLayout", 131)), context, "CENTER", 132));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(381).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(382).call(context, rubyObject, rubyObject));
        final IRubyObject call = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(383).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(384).call(context, rubyObject, rubyObject));
        final CallSite selectAttrAsgnCallSite = RuntimeHelpers.selectAttrAsgnCallSite(call, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(385), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(386));
        final RubyBoolean true = context.runtime.getTrue();
        context.pollThreadEvents();
        return RuntimeHelpers.doAttrAsgn(call, selectAttrAsgnCallSite, true, context, rubyObject);
    }
    
    @JRubyMethod(name = "wait_a_bit", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__64$RUBY$wait_a_bit(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(387).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 100, 32));
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(388).call(threadContext, rubyObject, rubyObject, RubyFixnum.five(threadContext.runtime));
    }
    
    @JRubyMethod(name = "prepare_progress_bar", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__65$RUBY$prepare_progress_bar(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext context, final IRubyObject caller, final Block block) {
        final DynamicScope locals = context.getCurrentScope();
        IRubyObject rubyObject;
        if (file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(389).call(context, caller, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(390).call(context, caller, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(391).call(context, caller, caller)), 0L).isTrue()) {
            locals.setValueZeroDepthZero(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(392).call(context, caller, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getFixnum5(context.runtime, 100), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(393).callIter(context, caller, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(394).call(context, caller, caller), RubyFixnum.zero(context.runtime), RuntimeHelpers.createBlock(context, caller, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getBlockBody(context, 13, "block_13$RUBY$prepare_progress_bar,2,sum;m,true,1,./lib//lister/runner.rb,535,true")))));
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.setVariable(context.runtime, 11, "@progress_bar", caller, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(397).call(context, caller, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "JProgressBar", 133), RubyFixnum.zero(context.runtime), locals.getValueZeroDepthZeroOrNil(context.nil)));
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.setVariable(context.runtime, 12, "@progress_bar_panel", caller, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(398).call(context, caller, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "JPanel", 134)));
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(399).call(context, caller, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(400).call(context, caller, caller), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(401).call(context, caller, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "JLabel", 135), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(402).call(context, caller, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(403).call(context, caller, caller))));
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(404).call(context, caller, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(405).call(context, caller, caller), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(406).call(context, caller, caller));
            final IRubyObject call = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(407).call(context, caller, caller);
            RuntimeHelpers.doAttrAsgn(call, RuntimeHelpers.selectAttrAsgnCallSite(call, caller, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(408), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(409)), RubyFixnum.zero(context.runtime), context, caller);
            final IRubyObject call2 = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(410).call(context, caller, caller);
            final CallSite selectAttrAsgnCallSite = RuntimeHelpers.selectAttrAsgnCallSite(call2, caller, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(411), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(412));
            final RubyBoolean true = context.runtime.getTrue();
            context.pollThreadEvents();
            RuntimeHelpers.doAttrAsgn(call2, selectAttrAsgnCallSite, true, context, caller);
            rubyObject = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(413).call(context, caller, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(414).call(context, caller, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(415).call(context, caller, caller)), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(416).call(context, caller, caller), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "BorderLayout", 136)), context, "SOUTH", 137));
        }
        else {
            rubyObject = context.nil;
        }
        return rubyObject;
    }
    
    public static IRubyObject block_13$RUBY$prepare_progress_bar(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    10: astore          9
        //    12: aload_1        
        //    13: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    16: astore          10
        //    18: aload_1        
        //    19: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    22: aload           4
        //    24: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    27: aload_3        
        //    28: aload_1        
        //    29: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    32: iconst_1       
        //    33: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.ensureMultipleAssignableRubyArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;Z)Lorg/jruby/RubyArray;
        //    36: astore          11
        //    38: aload           11
        //    40: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilZero:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: astore          9
        //    45: aload           11
        //    47: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilOne:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: astore          10
        //    52: aload           11
        //    54: pop            
        //    55: pop            
        //    56: aload_0        
        //    57: sipush          395
        //    60: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    63: aload_1        
        //    64: aload_2        
        //    65: aload           sum
        //    67: aload_0        
        //    68: sipush          396
        //    71: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    74: aload_1        
        //    75: aload_2        
        //    76: aload           m
        //    78: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    81: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    84: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  56     29      9     sum   Lorg/jruby/runtime/builtin/IRubyObject;
        //  56     29      10    m     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "remove_progress_bar", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__66$RUBY$remove_progress_bar(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(417).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 101, 32));
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(418).call(threadContext, rubyObject, rubyObject).isTrue() ? file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(419).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(420).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(421).call(threadContext, rubyObject, rubyObject)), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(422).call(threadContext, rubyObject, rubyObject)) : threadContext.nil;
    }
    
    @JRubyMethod(name = "prepare_upload_bar", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__67$RUBY$prepare_upload_bar(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.setVariable(context.runtime, 13, "@upload_bar", rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(423).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "JProgressBar", 138), RubyFixnum.zero(context.runtime), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(424).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(425).call(context, rubyObject, rubyObject))));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.setVariable(context.runtime, 14, "@upload_bar_panel", rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(426).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "JPanel", 139)));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(427).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(428).call(context, rubyObject, rubyObject), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(429).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "JLabel", 140), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(430).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(431).call(context, rubyObject, rubyObject))));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(432).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(433).call(context, rubyObject, rubyObject), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(434).call(context, rubyObject, rubyObject));
        final IRubyObject call = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(435).call(context, rubyObject, rubyObject);
        RuntimeHelpers.doAttrAsgn(call, RuntimeHelpers.selectAttrAsgnCallSite(call, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(436), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(437)), RubyFixnum.zero(context.runtime), context, rubyObject);
        final IRubyObject call2 = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(438).call(context, rubyObject, rubyObject);
        final CallSite selectAttrAsgnCallSite = RuntimeHelpers.selectAttrAsgnCallSite(call2, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(439), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(440));
        final RubyBoolean true = context.runtime.getTrue();
        context.pollThreadEvents();
        RuntimeHelpers.doAttrAsgn(call2, selectAttrAsgnCallSite, true, context, rubyObject);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(441).call(context, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(context.runtime, 102, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(442).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(443).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(444).call(context, rubyObject, rubyObject)), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(445).call(context, rubyObject, rubyObject), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "BorderLayout", 141)), context, "SOUTH", 142));
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(446).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(447).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(448).call(context, rubyObject, rubyObject)));
    }
    
    @JRubyMethod(name = "send_bug_report?", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__68$RUBY$send_bug_report_p_(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(449).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 103, 32), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 104, 32));
    }
    
    @JRubyMethod(name = "send_bug_report", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__69$RUBY$send_bug_report(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final IRubyObject nil = context.nil;
        final IRubyObject rep = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(450).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "BugReport", 143), rubyObject, rubyObject2);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(451).call(context, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(452).call(context, rubyObject, rep));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(453).call(context, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(454).call(context, rubyObject, rep));
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(455).call(context, rubyObject, rep);
    }
    
    @JRubyMethod(name = "start_logging_in_file", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__70$RUBY$start_logging_in_file(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(456).call(threadContext, object, object, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 105, 32));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(457).call(threadContext, object, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "FileUtils", 144), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(458).call(threadContext, object, object));
        if (!file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(459).call(threadContext, object, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "File", 145), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(460).call(threadContext, object, object)).isTrue()) {
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(461).call(threadContext, object, object, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 106, 32), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(threadContext.runtime, 29, "error"));
        }
        IRubyObject rubyObject;
        if (file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getVariable3(threadContext.runtime, "@debug_logger", object).isTrue()) {
            rubyObject = threadContext.nil;
        }
        else {
            chained_71_rescue_2$RUBY$SYNTHETICstart_logging_in_file(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, threadContext, object, block);
            rubyObject = chained_73_rescue_3$RUBY$SYNTHETICstart_logging_in_file(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, threadContext, object, block);
        }
        return rubyObject;
    }
    
    public static IRubyObject chained_71_rescue_2$RUBY$SYNTHETICstart_logging_in_file(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        context.getCurrentScope();
        final IRubyObject errorInfo = context.getErrorInfo();
        IRubyObject chained_72_rescue_line_584 = null;
        Label_0178: {
            try {
                try {
                    chained_72_rescue_line_584 = (file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(462).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "File", 146), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(463).call(context, rubyObject, rubyObject)).isTrue() ? file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(464).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "FileUtils", 147), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(465).call(context, rubyObject, rubyObject)) : context.nil);
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable t) {
                    if (RuntimeHelpers.isJavaExceptionHandled(t, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "Errno", 148)), context, "EACCESS", 149), context).isTrue()) {
                        RuntimeHelpers.storeExceptionInErrorInfo(t, context);
                        chained_72_rescue_line_584 = chained_72_rescue_line_584(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, context, rubyObject, block);
                        RuntimeHelpers.clearErrorInfo(context);
                        break Label_0178;
                    }
                    throw t;
                }
            }
            catch (JumpException.FlowControlException ex2) {
                context.setErrorInfo(errorInfo);
                throw ex2;
            }
        }
        context.setErrorInfo(errorInfo);
        return chained_72_rescue_line_584;
    }
    
    public static IRubyObject chained_72_rescue_line_584(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        threadContext.getCurrentScope();
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(466).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 107, 32));
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(467).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 107, 32));
    }
    
    public static IRubyObject chained_73_rescue_3$RUBY$SYNTHETICstart_logging_in_file(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        threadContext.getCurrentScope();
        final IRubyObject errorInfo = threadContext.getErrorInfo();
        IRubyObject rubyObject2 = null;
        Label_0180: {
            try {
                try {
                    file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.setVariable(threadContext.runtime, 15, "@debug_logger", rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(468).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Logger", 150), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(469).call(threadContext, rubyObject, rubyObject)));
                    rubyObject2 = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(470).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(471).call(threadContext, rubyObject, rubyObject), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getVariable4(threadContext.runtime, "@debug_logger", rubyObject));
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable t) {
                    if (RuntimeHelpers.isJavaExceptionHandled(t, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Errno", 151)), threadContext, "EACCESS", 152), threadContext).isTrue()) {
                        RuntimeHelpers.storeExceptionInErrorInfo(t, threadContext);
                        rubyObject2 = chained_74_rescue_line_592(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, threadContext, rubyObject, block);
                        RuntimeHelpers.clearErrorInfo(threadContext);
                        break Label_0180;
                    }
                    throw t;
                }
            }
            catch (JumpException.FlowControlException ex2) {
                threadContext.setErrorInfo(errorInfo);
                throw ex2;
            }
        }
        threadContext.setErrorInfo(errorInfo);
        return rubyObject2;
    }
    
    public static IRubyObject chained_74_rescue_line_592(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        threadContext.getCurrentScope();
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(472).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 108, 32));
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(473).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 108, 32), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(threadContext.runtime, 30, "warn"));
    }
    
    @JRubyMethod(name = "stop_logging_in_file", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__75$RUBY$stop_logging_in_file(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return chained_76_rescue_4$RUBY$SYNTHETICstop_logging_in_file(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, threadContext, rubyObject, block);
    }
    
    public static IRubyObject chained_76_rescue_4$RUBY$SYNTHETICstop_logging_in_file(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        context.getCurrentScope();
        final IRubyObject errorInfo = context.getErrorInfo();
        IRubyObject rubyObject2 = null;
        Label_0200: {
            try {
                try {
                    if (file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getVariable5(context.runtime, "@debug_logger", rubyObject).isTrue()) {
                        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(474).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(475).call(context, rubyObject, rubyObject), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getVariable6(context.runtime, "@debug_logger", rubyObject));
                        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(476).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getVariable7(context.runtime, "@debug_logger", rubyObject));
                        rubyObject2 = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.setVariable(context.runtime, 16, "@debug_logger", rubyObject, context.nil);
                    }
                    else {
                        rubyObject2 = context.nil;
                    }
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable t) {
                    if (RuntimeHelpers.isJavaExceptionHandled(t, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "Errno", 153)), context, "EACCESS", 154), context).isTrue()) {
                        RuntimeHelpers.storeExceptionInErrorInfo(t, context);
                        rubyObject2 = chained_77_rescue_line_606(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, context, rubyObject, block);
                        RuntimeHelpers.clearErrorInfo(context);
                        break Label_0200;
                    }
                    throw t;
                }
            }
            catch (JumpException.FlowControlException ex2) {
                context.setErrorInfo(errorInfo);
                throw ex2;
            }
        }
        context.setErrorInfo(errorInfo);
        return rubyObject2;
    }
    
    public static IRubyObject chained_77_rescue_line_606(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        threadContext.getCurrentScope();
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(477).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 109, 32), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(threadContext.runtime, 30, "warn"));
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(478).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 109, 32), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(threadContext.runtime, 30, "warn"));
    }
    
    @JRubyMethod(name = "propagate_bug_reports?", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__78$RUBY$propagate_bug_reports_p_(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return RuntimeHelpers.negate(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(479).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(480).call(threadContext, rubyObject, rubyObject), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(threadContext.runtime, 31, "dont_propagate_bug_reports")), threadContext.runtime);
    }
    
    @JRubyMethod(name = "run", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__79$RUBY$run(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        return chained_80_ensure_1$RUBY$__ensure__(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, threadContext, rubyObject, block);
    }
    
    public static IRubyObject chained_80_ensure_1$RUBY$__ensure__(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        threadContext.getCurrentScope();
        try {
            final IRubyObject chained_81_rescue_5$RUBY$SYNTHETICrun = chained_81_rescue_5$RUBY$SYNTHETICrun(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, threadContext, rubyObject, block);
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(489).call(threadContext, rubyObject, rubyObject);
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(490).call(threadContext, rubyObject, rubyObject);
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(491).call(threadContext, rubyObject, rubyObject);
            return chained_81_rescue_5$RUBY$SYNTHETICrun;
        }
        finally {
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(492).call(threadContext, rubyObject, rubyObject);
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(493).call(threadContext, rubyObject, rubyObject);
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(494).call(threadContext, rubyObject, rubyObject);
        }
    }
    
    public static IRubyObject chained_81_rescue_5$RUBY$SYNTHETICrun(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        threadContext.getCurrentScope();
        final IRubyObject errorInfo = threadContext.getErrorInfo();
        IRubyObject rubyObject2 = null;
        Label_0104: {
            try {
                try {
                    file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(481).call(threadContext, rubyObject, rubyObject);
                    rubyObject2 = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(482).call(threadContext, rubyObject, rubyObject);
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable t) {
                    if (RuntimeHelpers.isJavaExceptionHandled(t, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(threadContext, "Exception", 155), threadContext).isTrue()) {
                        RuntimeHelpers.storeExceptionInErrorInfo(t, threadContext);
                        rubyObject2 = chained_82_rescue_line_620(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, threadContext, rubyObject, block);
                        RuntimeHelpers.clearErrorInfo(threadContext);
                        break Label_0104;
                    }
                    throw t;
                }
            }
            catch (JumpException.FlowControlException ex2) {
                threadContext.setErrorInfo(errorInfo);
                throw ex2;
            }
        }
        threadContext.setErrorInfo(errorInfo);
        return rubyObject2;
    }
    
    public static IRubyObject chained_82_rescue_line_620(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        currentScope.setValueZeroDepthZero(RuntimeHelpers.getGlobalVariable(threadContext.runtime, "$!"));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(483).call(threadContext, rubyObject, rubyObject);
        IRubyObject call;
        if (file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(484).call(threadContext, rubyObject, rubyObject).isTrue()) {
            call = (file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(485).call(threadContext, rubyObject, rubyObject).isTrue() ? file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(486).call(threadContext, rubyObject, rubyObject, currentScope.getValueZeroDepthZeroOrNil(threadContext.nil)) : threadContext.nil);
        }
        else {
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(487).call(threadContext, rubyObject, rubyObject);
            call = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(488).call(threadContext, rubyObject, rubyObject, currentScope.getValueZeroDepthZeroOrNil(threadContext.nil));
        }
        return call;
    }
    
    @JRubyMethod(name = "do_exit", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__83$RUBY$do_exit(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(495).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(496).call(threadContext, rubyObject, rubyObject));
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(497).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 110, 32));
    }
    
    @JRubyMethod(name = "run_steps", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__84$RUBY$run_steps(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        final IRubyObject constant = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "Thread", 156);
        final CallSite selectAttrAsgnCallSite = RuntimeHelpers.selectAttrAsgnCallSite(constant, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(498), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(499));
        final RubyBoolean true = context.runtime.getTrue();
        context.pollThreadEvents();
        RuntimeHelpers.doAttrAsgn(constant, selectAttrAsgnCallSite, true, context, rubyObject);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(500).call(context, rubyObject, rubyObject, RubyString.newStringLight(context.runtime, 20).append(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(context.runtime, 111, 32)).append(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(501).call(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(502).call(context, rubyObject, rubyObject)).asString()));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(503).call(context, rubyObject, rubyObject);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(504).call(context, rubyObject, rubyObject);
        if (!file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(505).call(context, rubyObject, rubyObject).isTrue()) {
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(506).call(context, rubyObject, rubyObject);
        }
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(507).call(context, rubyObject, rubyObject);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(508).call(context, rubyObject, rubyObject);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(509).callIter(context, rubyObject, rubyObject, RuntimeHelpers.createBlock(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getBlockBody(context, 14, "block_14$RUBY$run_steps,-1,,false,0,./lib//lister/runner.rb,648,true")));
        RuntimeHelpers.opElementAsgnWithMethod(context, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(516).call(context, rubyObject, rubyObject), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol8(context.runtime, "run_id"), RubyFixnum.one(context.runtime), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(517), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(518), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(519));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(520).call(context, rubyObject, rubyObject);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(521).call(context, rubyObject, rubyObject);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(522).call(context, rubyObject, rubyObject);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(523).call(context, rubyObject, rubyObject);
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(524).call(context, rubyObject, rubyObject);
    }
    
    public static IRubyObject block_14$RUBY$run_steps(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(510).call(threadContext, rubyObject, rubyObject);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(511).call(threadContext, rubyObject, rubyObject);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(512).call(threadContext, rubyObject, rubyObject);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(513).call(threadContext, rubyObject, rubyObject);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(514).call(threadContext, rubyObject, rubyObject);
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(515).call(threadContext, rubyObject, rubyObject);
    }
    
    @JRubyMethod(name = "open_web_browser", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__85$RUBY$open_web_browser(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return chained_86_rescue_6$RUBY$SYNTHETICopen_web_browser(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, threadContext, rubyObject, block);
    }
    
    public static IRubyObject chained_86_rescue_6$RUBY$SYNTHETICopen_web_browser(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext context, final IRubyObject self, final Block block) {
        context.getCurrentScope();
        final IRubyObject errorInfo = context.getErrorInfo();
        IRubyObject rubyObject = null;
        Label_0159: {
            try {
                try {
                    file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(525).call(context, self, self);
                    rubyObject = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(526).callIter(context, self, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(context.runtime, 16, "done"), RuntimeHelpers.createBlock(context, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getBlockBody(context, 15, "block_15$RUBY$open_web_browser,-1,desktop;u,false,0,./lib//lister/runner.rb,667,false")));
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable t) {
                    if (RuntimeHelpers.isJavaExceptionHandled(t, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(545).call(context, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(546).call(context, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(547).call(context, self, self))), context).isTrue()) {
                        RuntimeHelpers.storeExceptionInErrorInfo(t, context);
                        rubyObject = chained_87_rescue_line_683(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, context, self, block);
                        RuntimeHelpers.clearErrorInfo(context);
                        break Label_0159;
                    }
                    throw t;
                }
            }
            catch (JumpException.FlowControlException ex2) {
                context.setErrorInfo(errorInfo);
                throw ex2;
            }
        }
        context.setErrorInfo(errorInfo);
        return rubyObject;
    }
    
    public static IRubyObject block_15$RUBY$open_web_browser(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    10: aload           5
        //    12: swap           
        //    13: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    16: aload           5
        //    18: swap           
        //    19: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    22: pop            
        //    23: aload_1        
        //    24: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    27: aload           4
        //    29: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    32: aload_3        
        //    33: pop            
        //    34: pop            
        //    35: aload_0        
        //    36: sipush          527
        //    39: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    42: aload_1        
        //    43: aload_2        
        //    44: aload_0        
        //    45: aload_1        
        //    46: ldc_w           "Desktop"
        //    49: sipush          157
        //    52: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    63: ifeq            556
        //    66: aload           locals
        //    68: aload_0        
        //    69: sipush          528
        //    72: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    75: aload_1        
        //    76: aload_2        
        //    77: aload_0        
        //    78: aload_1        
        //    79: ldc_w           "Desktop"
        //    82: sipush          158
        //    85: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    94: pop            
        //    95: aload_0        
        //    96: sipush          529
        //    99: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   102: aload_1        
        //   103: aload_2        
        //   104: aload           locals
        //   106: aload_1        
        //   107: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   110: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   113: aload_0        
        //   114: aload_1        
        //   115: ldc_w           "Desktop"
        //   118: sipush          159
        //   121: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   124: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   127: aload_0        
        //   128: swap           
        //   129: aload_1        
        //   130: ldc_w           "Action"
        //   133: sipush          160
        //   136: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   139: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   142: aload_0        
        //   143: swap           
        //   144: aload_1        
        //   145: ldc_w           "BROWSE"
        //   148: sipush          161
        //   151: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   154: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   157: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   162: ifeq            553
        //   165: aload           locals
        //   167: aload_0        
        //   168: sipush          530
        //   171: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   174: aload_1        
        //   175: aload_2        
        //   176: aload_0        
        //   177: aload_1        
        //   178: ldc_w           "File"
        //   181: sipush          162
        //   184: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   187: aload_0        
        //   188: sipush          531
        //   191: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   194: aload_1        
        //   195: aload_2        
        //   196: aload_2        
        //   197: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   200: aload_0        
        //   201: aload_1        
        //   202: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   205: bipush          112
        //   207: bipush          32
        //   209: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   212: aload_0        
        //   213: sipush          532
        //   216: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   219: aload_1        
        //   220: aload_2        
        //   221: aload_0        
        //   222: sipush          533
        //   225: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   228: aload_1        
        //   229: aload_2        
        //   230: aload_2        
        //   231: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   234: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   237: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   240: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   243: pop            
        //   244: aload_0        
        //   245: sipush          534
        //   248: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   251: aload_1        
        //   252: aload_2        
        //   253: aload_2        
        //   254: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   257: aload_1        
        //   258: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //   261: astore          9
        //   263: aload           9
        //   265: instanceof      Lorg/jruby/RubyString;
        //   268: ifeq            304
        //   271: aload           9
        //   273: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.isFastSwitchableString:(Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   276: ifeq            304
        //   279: aload           9
        //   281: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.getFastSwitchString:(Lorg/jruby/runtime/builtin/IRubyObject;)I
        //   284: lookupswitch {
        //          -687935476: 333
        //          default: 386
        //        }
        //   304: aload_0        
        //   305: sipush          535
        //   308: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   311: aload_1        
        //   312: aload_2        
        //   313: aload           9
        //   315: aload_0        
        //   316: aload_1        
        //   317: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   320: bipush          113
        //   322: bipush          64
        //   324: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   327: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   330: ifeq            386
        //   333: aload           locals
        //   335: aload_0        
        //   336: sipush          536
        //   339: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   342: aload_1        
        //   343: aload_2        
        //   344: aload_0        
        //   345: aload_1        
        //   346: ldc_w           "File"
        //   349: sipush          163
        //   352: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   355: aload           locals
        //   357: aload_1        
        //   358: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   361: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   364: aload_0        
        //   365: aload_1        
        //   366: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   369: bipush          114
        //   371: bipush          32
        //   373: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   376: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   379: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   382: pop            
        //   383: goto            386
        //   386: aload_0        
        //   387: sipush          537
        //   390: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   393: aload_1        
        //   394: aload_2        
        //   395: aload_2        
        //   396: aload_1        
        //   397: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   400: ldc_w           20
        //   403: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   406: aload_0        
        //   407: aload_1        
        //   408: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   411: bipush          115
        //   413: bipush          32
        //   415: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   418: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   421: aload           locals
        //   423: aload_1        
        //   424: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   427: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   430: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   435: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   438: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   441: pop            
        //   442: aload_0        
        //   443: sipush          538
        //   446: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   449: aload_1        
        //   450: aload_2        
        //   451: aload           locals
        //   453: aload_1        
        //   454: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   457: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   460: aload_0        
        //   461: sipush          539
        //   464: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   467: aload_1        
        //   468: aload_2        
        //   469: aload_0        
        //   470: sipush          540
        //   473: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   476: aload_1        
        //   477: aload_2        
        //   478: aload_0        
        //   479: sipush          541
        //   482: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   485: aload_1        
        //   486: aload_2        
        //   487: aload_0        
        //   488: sipush          542
        //   491: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   494: aload_1        
        //   495: aload_2        
        //   496: aload_2        
        //   497: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   500: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   503: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   506: aload           locals
        //   508: aload_1        
        //   509: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   512: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   515: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   518: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   521: pop            
        //   522: aload_0        
        //   523: sipush          543
        //   526: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   529: aload_1        
        //   530: aload_2        
        //   531: aload_2        
        //   532: aload_0        
        //   533: aload_1        
        //   534: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   537: ldc_w           16
        //   540: ldc_w           "done"
        //   543: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   546: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   549: pop            
        //   550: goto            553
        //   553: goto            556
        //   556: aload_0        
        //   557: sipush          544
        //   560: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   563: aload_1        
        //   564: aload_2        
        //   565: aload_2        
        //   566: aload_0        
        //   567: aload_1        
        //   568: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   571: bipush          116
        //   573: bipush          32
        //   575: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   578: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   581: areturn        
        //   582: pop            
        //   583: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     547     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     582    582    586    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject chained_87_rescue_line_683(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        threadContext.getCurrentScope();
        return file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(548).call(threadContext, rubyObject, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(threadContext.runtime, 117, 32));
    }
    
    @JRubyMethod(name = "propagate_results?", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__88$RUBY$propagate_results_p_(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject rubyObject2;
        if ((rubyObject2 = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(549).call(threadContext, rubyObject, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(550).call(threadContext, rubyObject, rubyObject), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(threadContext.runtime, 10, "propagate_results"))).isTrue()) {
            rubyObject2 = threadContext.runtime.getTrue();
            threadContext.pollThreadEvents();
        }
        return rubyObject2;
    }
    
    @JRubyMethod(name = "propagate_results", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__89$RUBY$propagate_results(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext context, final IRubyObject self, final Block block) {
        final DynamicScope locals = context.getCurrentScope();
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(551).call(context, self, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(context.runtime, 118, 32));
        locals.setValueZeroDepthZero(RubyFixnum.zero(context.runtime));
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(552).callIter(context, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(553).call(context, self, self), RuntimeHelpers.createBlock(context, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getBlockBody(context, 16, "block_16$RUBY$propagate_results,2,mod;result,true,1,./lib//lister/runner.rb,696,true")));
        if (file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(567).call(context, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(568).call(context, self, self), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getSymbol(context.runtime, 32, "inject_reporting_error")).isTrue()) {
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(569).call(context, self, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(context.runtime, 122, 32));
            file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(570).call(context, self, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(571).call(context, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstantFrom(RuntimeHelpers.checkIsModule(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant(context, "Debug", 165)), context, "DebugException", 166), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString(context.runtime, 86, 32)));
        }
        final IRubyObject call = file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(572).call(context, self, self);
        return RuntimeHelpers.doAttrAsgn(call, RuntimeHelpers.selectAttrAsgnCallSite(call, self, file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(573), file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite(574)), locals.getValueZeroDepthZeroOrNil(context.nil), context, self);
    }
    
    public static IRubyObject block_16$RUBY$propagate_results(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    10: astore          9
        //    12: aload_1        
        //    13: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    16: astore          10
        //    18: aload_1        
        //    19: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    22: aload           4
        //    24: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    27: aload_3        
        //    28: aload_1        
        //    29: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    32: iconst_1       
        //    33: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.ensureMultipleAssignableRubyArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;Z)Lorg/jruby/RubyArray;
        //    36: astore          11
        //    38: aload           11
        //    40: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilZero:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: astore          9
        //    45: aload           11
        //    47: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilOne:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: astore          10
        //    52: aload           11
        //    54: pop            
        //    55: pop            
        //    56: aload_0        
        //    57: sipush          554
        //    60: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    63: aload_1        
        //    64: aload_2        
        //    65: aload_2        
        //    66: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    69: dup            
        //    70: aload_2        
        //    71: aload_0        
        //    72: sipush          555
        //    75: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    78: aload_0        
        //    79: sipush          556
        //    82: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    85: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    88: aload           5
        //    90: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    93: aload_1        
        //    94: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    97: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   100: aload_1        
        //   101: aload_2        
        //   102: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: pop            
        //   106: aload           5
        //   108: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   111: aload_0        
        //   112: sipush          557
        //   115: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   118: aload_1        
        //   119: aload_2        
        //   120: aload           5
        //   122: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   125: aload_1        
        //   126: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   129: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   132: ldc2_w          1
        //   135: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //   138: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   141: pop            
        //   142: aload_0        
        //   143: sipush          558
        //   146: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   149: aload_1        
        //   150: aload_2        
        //   151: aload_2        
        //   152: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   155: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   160: ifeq            257
        //   163: aload_0        
        //   164: sipush          559
        //   167: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   170: aload_1        
        //   171: aload_2        
        //   172: aload_2        
        //   173: aload_1        
        //   174: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   177: ldc_w           20
        //   180: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   183: aload_0        
        //   184: aload_1        
        //   185: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   188: bipush          119
        //   190: bipush          32
        //   192: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   195: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   198: aload           mod
        //   200: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   205: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   208: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   211: pop            
        //   212: aload_0        
        //   213: sipush          560
        //   216: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   219: aload_1        
        //   220: aload_2        
        //   221: aload_0        
        //   222: aload_1        
        //   223: ldc_w           "MeasurementReport"
        //   226: sipush          164
        //   229: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   232: aload_2        
        //   233: aload           mod
        //   235: aload           result
        //   237: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   240: pop            
        //   241: aload_0        
        //   242: sipush          561
        //   245: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   248: aload_1        
        //   249: aload_2        
        //   250: aload_2        
        //   251: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   254: goto            383
        //   257: aload_0        
        //   258: sipush          562
        //   261: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   264: aload_1        
        //   265: aload_2        
        //   266: aload_2        
        //   267: aload_1        
        //   268: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   271: ldc_w           20
        //   274: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   277: aload_0        
        //   278: aload_1        
        //   279: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   282: bipush          120
        //   284: bipush          32
        //   286: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   289: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   292: aload           mod
        //   294: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   299: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   302: aload_0        
        //   303: aload_1        
        //   304: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   307: bipush          121
        //   309: bipush          32
        //   311: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   314: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   317: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   320: pop            
        //   321: aload_0        
        //   322: sipush          563
        //   325: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   328: aload_1        
        //   329: aload_2        
        //   330: aload_2        
        //   331: aload_0        
        //   332: sipush          564
        //   335: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   338: aload_1        
        //   339: aload_2        
        //   340: aload_2        
        //   341: aload           result
        //   343: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   346: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   349: pop            
        //   350: aload_0        
        //   351: sipush          565
        //   354: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   357: aload_1        
        //   358: aload_2        
        //   359: aload_2        
        //   360: aload_0        
        //   361: sipush          566
        //   364: invokevirtual   ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   367: aload_1        
        //   368: aload_2        
        //   369: aload_2        
        //   370: aload_1        
        //   371: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   374: invokestatic    org/jruby/RubyFixnum.two:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   377: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   380: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   383: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  ---------------------------------------
        //  56     328     9     mod     Lorg/jruby/runtime/builtin/IRubyObject;
        //  56     328     10    result  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_1$RUBY$Runner(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_1$RUBY$Runner(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD, threadContext, rubyObject, block);
    }
    
    @Override
    public IRubyObject load(final ThreadContext context, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        try {
            RuntimeHelpers.preLoad(context, ",0,0,-2");
            final IRubyObject _file__ = __file__(this, context, rubyObject, array, block);
            RuntimeHelpers.postLoad(context);
            return _file__;
        }
        finally {
            RuntimeHelpers.postLoad(context);
        }
    }
    
    public static void main(final String[] argv) {
        final FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD = new FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD();
        final String string = FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.class.getClassLoader().getResource("ruby/jit/FILE_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.class").toString();
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_A5C54A0662923F354794F6E79EE4A48E83CD8CBD.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
