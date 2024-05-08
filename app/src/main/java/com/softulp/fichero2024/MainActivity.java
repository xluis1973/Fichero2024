package com.softulp.fichero2024;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.softulp.fichero2024.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
private MainActivityViewModel mv;
private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mv= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        binding.btLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, MostrarActivity.class);
                startActivity(intent);
            }
        });

        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre=binding.etNombre.getText().toString();
                String apellido=binding.etApellido.getText().toString();
                long dni=Long.parseLong(binding.etDni.getText().toString());
                int edad=Integer.parseInt(binding.etEdad.getText().toString());
                //mv.guardarBytes(nombre,apellido,dni,edad);
                //mv.guardarPrimitivos(nombre,apellido,dni,edad);
                mv.guardarObjeto(nombre,apellido,dni,edad);
            }
        });
    }
}