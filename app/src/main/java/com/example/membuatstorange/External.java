package com.example.membuatstorange;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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

public class External extends AppCompatActivity implements View.OnClickListener {

    public  static  final  String FILENAME = "namafile.txt";
    public static String TAG = BuildConfig.APPLICATION_ID;
    public static final int REQUEST_CODE_STORANGE = 100;

    Button buatFile, ubahFile, bacaFile, hapusFile;
    public int selectEvent = 0;

    TextView txtdata ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external);
        buatFile=findViewById(R.id.btnbuat);
        ubahFile=findViewById(R.id.btnubah);
        bacaFile=findViewById(R.id.btnbaca);
        hapusFile=findViewById(R.id.btnhapus);
        txtdata=findViewById(R.id.txtdata);

        buatFile.setOnClickListener(this);
        ubahFile.setOnClickListener(this);
        bacaFile.setOnClickListener(this);
        hapusFile.setOnClickListener(this);

    }

    public boolean isipenyimpanan(){
        if (Build.VERSION.SDK_INT >= 23){
            if (checkCallingOrSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED){
                return true;
            }else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_STORANGE );
                return false;
            }
        }else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_STORANGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    run(selectEvent);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnbuat:
                buatFile();
                break;
            case R.id.btnbaca:
                bacaFile();
                break;
            case R.id.btnubah:
                ubahFile();
                break;
            case R.id.btnhapus:
                if (isipenyimpanan()){
                    selectEvent = v.getId();
                    run(v.getId());
                }break;
        }

    }

    private void run(int id) {
        switch (id){
            case R.id.btnbuat:
                buatFile();
                break;
            case R.id.btnbaca:
                bacaFile();
                break;
            case R.id.btnubah:
                ubahFile();
                break;
            case R.id.btnhapus:
                hapusFile();
                break;
        }
    }

    private void hapusFile() {
        File file= new File(Environment.getExternalStorageDirectory(), FILENAME);
        if (file.exists()){
            file.delete();
            Toast.makeText(getApplicationContext(),"File Sudah Dihapus",Toast.LENGTH_SHORT).show();
        }
    }



    private void bacaFile() {
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, FILENAME);
        if (file.exists()){
            StringBuilder txt = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null){
                    txt.append(line);
                    line = br.readLine();
                }br.close();
            }catch (IOException e){
                System.out.println("error" + e.getMessage());
            }
            txtdata.setText(txt.toString());
        }else {
            txtdata.setText("File not found" );
        }
    }

    private void ubahFile() {
        String ubah = "Ubah data file text";
        String st = Environment.getExternalStorageState();

        if(!Environment.MEDIA_MOUNTED.equals(st)){
            return;
        }
        File file = new File(Environment.getExternalStorageDirectory(), FILENAME);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(ubah.getBytes());
            outputStream.close();
        } catch (IOException e) {
            System.out.println("gagal diubah " + e.getMessage());
        }
    }

    private void buatFile() {
        String isiFile = "Isikan data file text";
        String st = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(st)){
            return;
        }
        File file = new File(Environment.getExternalStorageDirectory(), FILENAME);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();

            Toast.makeText(getApplicationContext(),"File berhasil di buat",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            System.out.println("gagal dibuat " + e.getMessage());

        }
    }
}