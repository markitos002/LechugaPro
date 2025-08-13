package eu.villacristina.lechugapro.notifications;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ:\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u0013\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Leu/villacristina/lechugapro/notifications/ReminderScheduler;", "", "()V", "TAG_PREFIX", "", "cancelCycle", "", "context", "Landroid/content/Context;", "cicloId", "", "schedule", "Landroidx/work/WorkRequest;", "delayMillis", "title", "message", "notificationId", "", "tag", "tagForCycle", "app_release"})
public final class ReminderScheduler {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG_PREFIX = "ciclo_";
    @org.jetbrains.annotations.NotNull()
    public static final eu.villacristina.lechugapro.notifications.ReminderScheduler INSTANCE = null;
    
    private ReminderScheduler() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String tagForCycle(long cicloId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.work.WorkRequest schedule(@org.jetbrains.annotations.NotNull()
    android.content.Context context, long delayMillis, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String message, int notificationId, @org.jetbrains.annotations.Nullable()
    java.lang.String tag) {
        return null;
    }
    
    public final void cancelCycle(@org.jetbrains.annotations.NotNull()
    android.content.Context context, long cicloId) {
    }
}