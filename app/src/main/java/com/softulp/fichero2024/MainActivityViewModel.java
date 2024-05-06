package com.softulp.fichero2024;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.io.*;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    public MainActivityViewModel(@NonNull Application application) {

        super(application);
        this.context=application.getApplicationContext();
    }



    public void guardarBytes(String nombre, String apellido, long dni,int edad){
        File archivo=new File(context.getFilesDir(),"datos.dat");
        try {
            FileOutputStream fo=new FileOutputStream(archivo,true);
            BufferedOutputStream bo=new BufferedOutputStream(fo);
           PrintStream ps=new PrintStream(bo);
           ps.println(nombre);
           ps.println(apellido);
           ps.println(dni);
           ps.println(edad);
           ps.flush();
           fo.close();


        } catch (FileNotFoundException e) {
            Toast.makeText(context,"Error al acceder al archivo",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(context,"Error al acceder al archivo",Toast.LENGTH_LONG).show();
        }

    }

    public void guardarPrimitivos(String nombre, String apellido, long dni,int edad){
        File archivo=new File(getApplication().getFilesDir(),"primitivos.dat");
        try {
            FileOutputStream fos=new FileOutputStream(archivo,true);
            BufferedOutputStream bo=new BufferedOutputStream(fos);
            DataOutputStream dos=new DataOutputStream(bo);
            dos.writeUTF(nombre);
            dos.writeUTF(apellido);
            dos.writeLong(dni);
            dos.writeInt(edad);

            bo.flush();
            fos.close();

            Toast.makeText(context,"Datos guardados",Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            Toast.makeText(context,"Error al acceder al archivo",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(context,"Error al acceder al archivo",Toast.LENGTH_LONG).show();
        }


    }
}
