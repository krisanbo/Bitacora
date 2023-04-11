package com.example.bitacora;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText)findViewById(R.id.et1);
        //crear lista de archivos, en este caso 1, vamos a crear un array para meter los archivos que vaya creando

        String archivos[] = fileList();

        // condicional xq no puede estar vacia
        // metodo buleano dentro de () al meter cosas me dice true o false
        // le pregunto para hacer la consulta, te vas a archivo y me buscas el txt
        if (ArchivoExiste(archivos, "bitacora.txt")) {

            // al final de todo metemos la excepcion para los errores

            try {// nos devuelve true, nos metemos dentro de archivo para leerlo, abreme el archivo
                InputStreamReader archivo = new InputStreamReader(openFileInput("bitacora.txt"));
                // llamar al metodo buffer que lo lea y lo ponga en la primera linea
                // br es la linea
                BufferedReader br = new BufferedReader(archivo);

                //leer la linea primera
                String linea = br.readLine();

                // hacer bucle para que lea hasta que no haya lineas escritas
                String archivoCompleto = "";

                while (linea != null) {
                    archivoCompleto = archivoCompleto + linea + "\n";
                    linea = br.readLine();
                }
                // cerrar todo lo new en sentido inverso
                // cerrar linea
                br.close();
                // cerramos archivo
                archivo.close();
                // mostrar lo que tenemos
                et1.setText(archivoCompleto);


            } catch (IOException e) {

            }

        }

    }
    // meter metodo boulenano antes de la ultima llave, si el archivo existe

    private boolean ArchivoExiste(String archivos [],String NombreArchivo){
        // for para que pare de leer
        for(int i=0;i < archivos.length;i++)
            if(NombreArchivo.equals(archivos[i]))
                return true;
        return false;
    }

    // metodo boton guardar
    public void guardar(View view) {
        //utilizar para error cada vez que sea txt
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("bitacora.txt", Activity.MODE_PRIVATE));
            archivo.write(et1.getText().toString());
            // flush archivo guardar
            archivo.flush();
            // cerrar archivo
            archivo.close();
        } catch (IOException e) {

            Toast.makeText(this, "El archivo no se ha guardado", Toast.LENGTH_SHORT).show();

        }
        Toast.makeText(this, "archivo guardado", Toast.LENGTH_SHORT).show();

      }
    }



