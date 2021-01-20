package co.edu.unipiloto.laboratorio_servicio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnServicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicialización de Atributos
        this.btnServicio = (Button) findViewById(R.id.btn_servicio);

        //Acción de Boton
        this.btnServicio.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == this.btnServicio.getId()){
            Intent servicio = new Intent(MainActivity.this, Mensaje.class);
            servicio.putExtra("mensaje", "Tiempo desde MainActivity");
            startService(servicio);
        }
    }
}