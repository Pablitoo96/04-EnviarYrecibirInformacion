package pablo.maruottolo.a04_enviaryrecibirinformacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pablo.maruottolo.a04_enviaryrecibirinformacion.modelos.Usuario;

public class MainActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnDesecripar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarVista();

        btnDesecripar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();

                if(password.isEmpty() || email.isEmpty()){
                    Toast.makeText(MainActivity.this,"FALTAN DATOS",Toast.LENGTH_SHORT).show();
                }else{
                    //ENVIAR INFORMACION A LA SEGUNDA ACTIVIDAD
                      Intent intent = new Intent(MainActivity.this,DesencriptarActivity.class);
                    //PASAR INFORMACION
                      Bundle bundle = new Bundle();
                  //    bundle.putString("EMAIL",email);
                  //    bundle.putString("PASS",password);
                    bundle.putSerializable("USER",new Usuario(email,password));
                      intent.putExtras(bundle);
                      startActivity(intent);
                }
            }
        });
    }

    private void inicializarVista() {
        txtEmail = findViewById(R.id.txtEmailMain);
        txtPassword = findViewById(R.id.txtPasswordMain);
        btnDesecripar = findViewById(R.id.btnDesencriptarMain);
    }

}