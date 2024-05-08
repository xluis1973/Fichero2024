package com.softulp.fichero2024;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

public class MostrarActivityViewModel extends AndroidViewModel {
private MutableLiveData<String> mPersona;

    public MostrarActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMPersona(){
        if(mPersona==null){
            mPersona=new MutableLiveData<>();
        }
        return mPersona;
    }



    public void leerBytes(){
        File archivo=new File(getApplication().getFilesDir(),"datos.dat");
        try {
            StringBuilder sb=new StringBuilder();

            FileInputStream fi=new FileInputStream(archivo);
            InputStreamReader isr=new InputStreamReader(fi);
            BufferedReader br=new BufferedReader(isr);
            String lineaLeida;
            int contador=1;
            while((lineaLeida=br.readLine())!=null){

                sb.append(lineaLeida+" ");
                contador++;
                if(contador==5){
                    sb.append("\n");
                    contador=1;
                }
            }



            fi.close();

            mPersona.setValue(sb.toString());

        } catch (FileNotFoundException e) {
            Toast.makeText(getApplication(),"Error al acceder al archivo",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getApplication(),"Error al acceder al archivo",Toast.LENGTH_LONG).show();
        }

    }

    public void leerPrimitivos(){
        File archivo=new File(getApplication().getFilesDir(),"primitivos.dat");
        StringBuilder sb=new StringBuilder();

        try {
            FileInputStream fis=new FileInputStream(archivo);
            BufferedInputStream bi=new BufferedInputStream(fis);
            DataInputStream dis=new DataInputStream(bi);

            String nombre;
            String apellido;
            long dni;
            int edad;
            while(dis.available()>0){

                    nombre = dis.readUTF();
                    apellido = dis.readUTF();
                    dni = dis.readLong();
                    edad = dis.readInt();
                    sb.append(nombre+" "+apellido+" "+dni+" "+edad+"\n");

            }
            mPersona.setValue(sb.toString());

            fis.close();

        } catch (FileNotFoundException e) {
            Toast.makeText(getApplication(),"Error al acceder al archivo",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getApplication(),"Error de E/s",Toast.LENGTH_LONG).show();
            Log.d("salida ",e.getMessage());
        }


    }

    public void leerObjetos(){
        StringBuilder sb=new StringBuilder();
        File archivo=new File(getApplication().getFilesDir(),"fichero.dat");
        //Nodo
        try {
            FileInputStream fis=new FileInputStream(archivo);
            BufferedInputStream bis=new BufferedInputStream(fis);
            ObjectInputStream ois=new ObjectInputStream(bis);


            while(true){
                try {
                    Persona per = (Persona) ois.readObject();
                    String nombre = per.getNombre();
                    String apellido = per.getApellido();
                    long dni = per.getDni();
                    int edad = per.getEdad();
                    sb.append(nombre + " " + apellido + " " + dni + " " + edad + " " +  "\n");
                }catch (EOFException eof){
                    mPersona.setValue(sb.toString());
                    fis.close();
                    break;
                }

            }


        } catch (FileNotFoundException e) {
            Toast.makeText(getApplication(),"Error de File",Toast.LENGTH_LONG).show();
            Log.d("salida ",e.getMessage());
        } catch (IOException e) {
            Toast.makeText(getApplication(),"Error de E/s",Toast.LENGTH_LONG).show();
            Log.d("salida ",e.toString());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            Toast.makeText(getApplication(),"Error al recuperar datos",Toast.LENGTH_LONG).show();
        }
    }


}
