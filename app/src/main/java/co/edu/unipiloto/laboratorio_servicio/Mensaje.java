package co.edu.unipiloto.laboratorio_servicio;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class Mensaje extends IntentService {

    public static String IDENTIFICADOR = "NOTIFICACION";
    public static int ID_NOTIFICACION = 10;

    public Mensaje() {
        super("Mensaje");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this){
            try{
                wait(10000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        mostrarTextoConsola(intent.getStringExtra("mensaje"));
        mostrarTextoNotificacion(intent.getStringExtra("mensaje"));
    }

    public void mostrarTextoConsola(String mensaje){
        Log.v("Mensaje", "El mensaje es: " + mensaje);
    }

    public void mostrarTextoNotificacion(String mensaje){
        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this, IDENTIFICADOR);
        notificacion.setAutoCancel(true);
        notificacion.setSmallIcon(R.drawable.ic_launcher_foreground);
        notificacion.setContentTitle("Mensaje Servicio");
        notificacion.setContentText(mensaje);
        notificacion.setPriority(NotificationCompat.PRIORITY_MAX);
        notificacion.setVibrate(new long[]{0, 1000});

        notificacion.setContentIntent(PendingIntent.getActivity(
                this,
                0,
                new Intent(Mensaje.this, MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT
        ));

        NotificationManager notificacionManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            if (notificacionManager != null && notificacionManager.getNotificationChannel(IDENTIFICADOR) == null) {
                NotificationChannel notificationChannel = new NotificationChannel(IDENTIFICADOR,
                        "Mensaje",
                        NotificationManager.IMPORTANCE_HIGH);
                notificationChannel.setDescription("Mensaje Laboratorio");
                notificacionManager.createNotificationChannel(notificationChannel);
            }
        }
        notificacionManager.notify(ID_NOTIFICACION, notificacion.build());
    }


}