package com.example.elasegura.monitor

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.elasegura.R

class EmergencyWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    companion object {
        const val KEY_BUTTON_TYPE = "button_type"
        private const val CHANNEL_ID = "ELA_SEGURA_ALERT"
    }

    override fun doWork(): Result {

        val buttonType = inputData.getString(KEY_BUTTON_TYPE)

        showNotification(buttonType)

        return Result.success()
    }

    private fun showNotification(buttonType: String?) {

        createNotificationChannel()

        val message = when (buttonType) {
            "HELP" -> "Você clicou em 'Preciso de Ajuda'"
            "DANGER" -> "Você clicou em 'Me sinto ameaçada'"
            "CALL" -> "Você clicou em 'Estou bem'"
            else -> "Alerta ativado"
        }

        val builder = NotificationCompat.Builder(
            applicationContext,
            CHANNEL_ID
        )
            .setSmallIcon(R.drawable.ic_stat_name)
            .setContentTitle("ElaSegura")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        val manager = applicationContext
            .getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        manager.notify(System.currentTimeMillis().toInt(), builder.build())
    }

    private fun createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            val manager = applicationContext
                .getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager

            val channel = NotificationChannel(
                CHANNEL_ID,
                "Alertas ElaSegura",
                NotificationManager.IMPORTANCE_HIGH
            )

            manager.createNotificationChannel(channel)
        }
    }
}