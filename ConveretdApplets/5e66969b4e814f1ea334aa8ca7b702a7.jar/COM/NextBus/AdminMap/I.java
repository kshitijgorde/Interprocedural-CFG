// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.util.Map;

public final class I
{
    private Map a;
    private O b;
    
    public I(final O b, final Map a) {
        this.b = b;
        this.a = a;
    }
    
    public final void a() {
        if (this.b.o()) {
            this.f(true);
            this.g(false);
            this.h(false);
            this.l(true);
            this.d(false);
            this.a(false);
            this.b(false);
            this.m(false);
            this.c(false);
            this.j(false);
            this.k(false);
            this.o(false);
            this.x(false);
            this.y(false);
            this.r(false);
            this.n(false);
            this.p(false);
            this.q(false);
            this.s(false);
            this.t(false);
            this.u(false);
            this.w(false);
            return;
        }
        this.f(true);
        this.g(false);
        this.h(false);
        this.l(true);
        this.d(false);
        this.a(!this.b.B().startsWith("act"));
        this.b(true);
        this.m(false);
        this.c(false);
        this.j(false);
        this.k(false);
        this.o(false);
        this.x(true);
        this.y(true);
        this.r(true);
        this.n(false);
        this.p(false);
        this.q(false);
        this.s(false);
        this.t(true);
        this.u(false);
        this.w(false);
    }
    
    public final void a(final boolean b) {
        this.a.put("unassigned_vehicles", new Boolean(b).toString());
    }
    
    public final void b(final boolean b) {
        this.a.put("off_route_vehicles", new Boolean(b).toString());
    }
    
    public final void c(final boolean b) {
        this.a.put("vehicles_in_yard", new Boolean(b).toString());
    }
    
    public final void d(final boolean b) {
        this.a.put("stale_vehicles", new Boolean(b).toString());
    }
    
    public final void e(final boolean b) {
        this.a.put("vehicle_nolabels", new Boolean(b).toString());
    }
    
    public final void f(final boolean b) {
        this.a.put("vehicle_labels", new Boolean(b).toString());
    }
    
    public final void g(final boolean b) {
        this.a.put("adherence_labels", new Boolean(b).toString());
    }
    
    public final void h(final boolean b) {
        this.a.put("headway_labels", new Boolean(b).toString());
    }
    
    public final void i(final boolean b) {
        this.a.put("route_mode", new Boolean(b).toString());
    }
    
    public final void j(final boolean b) {
        this.a.put("adherence", new Boolean(b).toString());
    }
    
    public final void k(final boolean b) {
        this.a.put("headway", new Boolean(b).toString());
    }
    
    public final void l(final boolean b) {
        this.a.put("streetmap", new Boolean(b).toString());
    }
    
    public final void m(final boolean b) {
        this.a.put("hidden_stops", new Boolean(b).toString());
    }
    
    public final void n(final boolean b) {
        this.a.put("prediction_time", new Boolean(b).toString());
    }
    
    public final void o(final boolean b) {
        this.a.put("snap_vehicles_to_route", new Boolean(b).toString());
    }
    
    public final void p(final boolean b) {
        this.a.put("mouse_lat_long", new Boolean(b).toString());
    }
    
    public final void q(final boolean b) {
        this.a.put("lat_log_detail", new Boolean(b).toString());
    }
    
    public final void r(final boolean b) {
        this.a.put("time_gps_received_detail", new Boolean(b).toString());
    }
    
    private void x(final boolean b) {
        this.a.put("vehicle_id_detail", new Boolean(b).toString());
    }
    
    private void y(final boolean b) {
        this.a.put("job_id_detail", new Boolean(b).toString());
    }
    
    public final void s(final boolean b) {
        this.a.put("routeDirStop_labels", new Boolean(b).toString());
    }
    
    public final void t(final boolean b) {
        this.a.put("vehicle_Id_arrivals", new Boolean(b).toString());
    }
    
    public final void u(final boolean b) {
        this.a.put("debug_on", new Boolean(b).toString());
    }
    
    public final void v(final boolean b) {
        this.a.put("on_break", new Boolean(b).toString());
    }
    
    public final void w(final boolean b) {
        this.a.put("allow_select_all_routes", new Boolean(b).toString());
    }
    
    public final boolean b() {
        return Boolean.valueOf(this.a.get("unassigned_vehicles"));
    }
    
    public final boolean c() {
        return Boolean.valueOf(this.a.get("off_route_vehicles"));
    }
    
    public final boolean d() {
        return Boolean.valueOf(this.a.get("vehicles_in_yard"));
    }
    
    public final boolean e() {
        return Boolean.valueOf(this.a.get("stale_vehicles"));
    }
    
    public final boolean f() {
        return Boolean.valueOf(this.a.get("vehicle_nolabels"));
    }
    
    public final boolean g() {
        return Boolean.valueOf(this.a.get("vehicle_labels"));
    }
    
    public final boolean h() {
        return Boolean.valueOf(this.a.get("adherence_labels"));
    }
    
    public final boolean i() {
        return Boolean.valueOf(this.a.get("headway_labels"));
    }
    
    public final boolean j() {
        return Boolean.valueOf(this.a.get("route_mode"));
    }
    
    public final boolean k() {
        return Boolean.valueOf(this.a.get("adherence"));
    }
    
    public final boolean l() {
        return Boolean.valueOf(this.a.get("headway"));
    }
    
    public final boolean m() {
        return Boolean.valueOf(this.a.get("streetmap"));
    }
    
    public final boolean n() {
        return Boolean.valueOf(this.a.get("hidden_stops"));
    }
    
    public final boolean o() {
        return Boolean.valueOf(this.a.get("prediction_time"));
    }
    
    public final boolean p() {
        return Boolean.valueOf(this.a.get("snap_vehicles_to_route"));
    }
    
    public final boolean q() {
        return Boolean.valueOf(this.a.get("mouse_lat_long"));
    }
    
    public final boolean r() {
        return Boolean.valueOf(this.a.get("lat_log_detail"));
    }
    
    public final boolean s() {
        return Boolean.valueOf(this.a.get("time_gps_received_detail"));
    }
    
    public final boolean t() {
        return Boolean.valueOf(this.a.get("vehicle_id_detail"));
    }
    
    public final boolean u() {
        return Boolean.valueOf(this.a.get("job_id_detail"));
    }
    
    public final boolean v() {
        return Boolean.valueOf(this.a.get("direction_detail"));
    }
    
    public final boolean w() {
        return Boolean.valueOf(this.a.get("debug_on"));
    }
    
    public final boolean x() {
        return Boolean.valueOf(this.a.get("routeDirStop_labels"));
    }
    
    public final boolean y() {
        return Boolean.valueOf(this.a.get("vehicle_Id_arrivals"));
    }
    
    public final boolean z() {
        return Boolean.valueOf(this.a.get("on_break"));
    }
    
    public final boolean A() {
        return Boolean.valueOf(this.a.get("allow_select_all_routes"));
    }
}
