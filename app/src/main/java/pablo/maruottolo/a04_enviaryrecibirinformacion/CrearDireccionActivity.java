package pablo.maruottolo.a04_enviaryrecibirinformacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pablo.maruottolo.a04_enviaryrecibirinformacion.modelos.Direccion;

public class CrearDireccionActivity extends AppCompatActivity {

    private EditText txtCalle;
    private EditText txtNumero;
    private EditText txtCiudad;
    private Button btnCrear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_direccion);

        inicializarVista();
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String calle = txtCalle.getText().toString();
                int numero = Integer.parseInt(txtNumero.getText().toString());
                String ciudad = txtCiudad.getText().toString();

                Direccion direccion = new Direccion(calle,numero,ciudad);

                Bundle bundle = new Bundle();
                bundle.putSerializable("DIR",direccion);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();

            }
        });

    }

    private void inicializarVista() {
        txtCalle = findViewById(R.id.txtCalle);
        txtNumero = findViewById(R.id.txtNumero);
        txtCiudad = findViewById(R.id.txtCiudad);
        btnCrear = findViewById(R.id.btnCrear);
     }
}