package pablo.maruottolo.a04_enviaryrecibirinformacion;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pablo.maruottolo.a04_enviaryrecibirinformacion.modelos.Direccion;
import pablo.maruottolo.a04_enviaryrecibirinformacion.modelos.Usuario;

public class MainActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnDesecripar;
    private Button btnCrearDireccion;
    private final int  DIRECCIONES = 123;
    private ActivityResultLauncher<Intent> launcherDirecciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarVista();
        inicializarLaunches();


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
        btnCrearDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CrearDireccionActivity.class);
               // startActivityForResult(intent, DIRECCIONES);
                launcherDirecciones.launch(intent);
            }
        });
    }

    private void inicializarLaunches() {
        //1. Preparar como lanzar la actividad hija (equivalente a starActivityForResult())
        //2.preparar que voy hacer cuando la hija devuelva datos(equivalente al onActivityResult())
        launcherDirecciones = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            if(result.getData() != null){
                                Bundle bundle = result.getData().getExtras();
                                Direccion direccion = (Direccion) bundle.getSerializable("DIR");
                                Toast.makeText(MainActivity.this,direccion.toString(),Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(MainActivity.this,"No hay Datos",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this,"CANCELADA",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    /**
     *
     * @param requestCode --> identificador de la ventana que se ha cerrado
     * @param resultCode --> el modo en el que se ha cerrado
     * @param data --> datos que estan dentro del intent
     */
    /*
    @Override

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == DIRECCIONES){
            if(resultCode ==  RESULT_OK){
                if(data != null){
                    Bundle bundle = data.getExtras();
                    Direccion direccion = (Direccion) bundle.getSerializable("DIR");
                    Toast.makeText(this,direccion.toString(),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this," No hay datos ",Toast.LENGTH_SHORT).show();
                }
            } else{
                Toast.makeText(this," CANCELADA ",Toast.LENGTH_SHORT).show();
            }
        }
    }
*/
    private void inicializarVista() {
        txtEmail = findViewById(R.id.txtEmailMain);
        txtPassword = findViewById(R.id.txtPasswordMain);
        btnDesecripar = findViewById(R.id.btnDesencriptarMain);
        btnCrearDireccion = findViewById(R.id.btnCrearDireccion);
    }

}