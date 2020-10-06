package com.example.membuatstorange;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Internal extends AppCompatActivity {
    public static String FILENAME = "data.txt";
    private Button bacaFile, buatFile, ubahFile, hapusFile;
    private TextView txtdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);

        bacaFile=findViewById(R.id.btnbaca);
        buatFile=findViewById(R.id.btnbuat);
        ubahFile= findViewById(R.id.btnubah);
        hapusFile=findViewById(R.id.btnhapus);
        txtdata=findViewById(R.id.txtdata);

   //     buatFile.setOnClickListener((View.OnClickListener) this);
   //     bacaFile.setOnClickListener((View.OnClickListener) this);
   //     ubahFile.setOnClickListener((View.OnClickListener) this);
   //     hapusFile.setOnClickListener((View.OnClickListener) this);

        bacaFile.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                File sdcard = getFilesDir();
                File file = new File(sdcard,FILENAME);
                if(file.exists()){
                    StringBuilder txt = new StringBuilder();
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String line = br.readLine();
                        while (line != null){
                            txt.append(line);
                            line = br.readLine();
                        }
                        br.close();
                    }catch (IOException e){
                        System.out.println("Error" + e.getMessage());
                    }
                    txtdata.setText(txt.toString());
                }else {
                    txtdata.setText("File not found");
                }
            }
        });
        buatFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isiFile = "Isi data File Text";
                File file = new File(getFilesDir(), FILENAME);
                try {
                   file.createNewFile();
                    FileOutputStream outputStream = new FileOutputStream(file, true);
                    outputStream.write(isiFile.getBytes());
                    outputStream.flush();
                    outputStream.close();

                } catch (IOException e) {
                    System.out.println("gagal dibuat " + e.getMessage());
                }
            }
        });

        ubahFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ubah= "Update isi data file text";
                File file = new File(getFilesDir(),FILENAME);
                FileOutputStream outputStream;
                try {
                    file.createNewFile();
                    outputStream = new FileOutputStream(file, false);
                    outputStream.write(ubah.getBytes());
                    outputStream.flush();
                    outputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        hapusFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    File file= new File(getFilesDir(), FILENAME);
                try {
                    if (file.exists()){
                        file.delete();
                        Toast.makeText(getApplicationContext(),"File Sudah Dihapus",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    System.out.println("gagal dihapus" + e.getMessage());
                }
                txtdata.setText(txtdata.getText().toString());
            }
        });

        }



//         public void onClick (View v)
//         { jalankan(v.getId()); }
//         public  void jalankan(int id){
//            switch (id){
//                case R.id.btnbuat:
//                    buatFile();
//                    break;
//                case R.id.btnbaca:
//                    bacaFile();
//                    break;
//                case R.id.btnubah:
//                    ubahFile();
//                    break;
//                case R.id.btnhapus:
//                    hapusFile();
//                    break;
//
//            }



}