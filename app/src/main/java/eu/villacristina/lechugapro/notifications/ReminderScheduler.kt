package eu.villacristina.lechugapro.notifications

import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import java.util.concurrent.TimeUnit

object ReminderScheduler {
    private const val TAG_PREFIX = "ciclo_"
    fun tagForCycle(cicloId: Long): String = TAG_PREFIX + cicloId

    fun schedule(
        context: android.content.Context,
        delayMillis: Long,
        title: String,
        message: String,
        notificationId: Int,
        tag: String? = null
    ): WorkRequest {
        val data = Data.Builder()
            .putString(ReminderWorker.KEY_TITLE, title)
            .putString(ReminderWorker.KEY_MESSAGE, message)
            .putInt(ReminderWorker.KEY_NOTIFICATION_ID, notificationId)
            .build()
        val builder = OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInputData(data)
            .setInitialDelay(delayMillis, TimeUnit.MILLISECONDS)
        if (tag != null) builder.addTag(tag)
        val request = builder.build()
        WorkManager.getInstance(context).enqueue(request)
        return request
    }

    fun cancelCycle(context: android.content.Context, cicloId: Long) {
        val tag = tagForCycle(cicloId)
        WorkManager.getInstance(context).cancelAllWorkByTag(tag)
    }
}
