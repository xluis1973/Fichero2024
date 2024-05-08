package com.softulp.fichero2024;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.softulp.fichero2024.databinding.ActivityMostrarBinding;

public class MostrarActivity extends AppCompatActivity {
private MostrarActivityViewModel mv;
private ActivityMostrarBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityMostrarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mv= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MostrarActivityViewModel.class);
        mv.getMPersona().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvMostrar.setText(s);
            }
        });

        //mv.leerBytes();
        //mv.leerPrimitivos();
        mv.leerObjetos();
    }
}