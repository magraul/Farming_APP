package com.example.farming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.farming.boxes.AlertBox;
import com.example.farming.model.Angajat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static String TAG = MainActivity.class.getName().toString();
    private static final String FILE_NAME_ANGAJATI = "angajati.txt";
    private Button signIn;
    private EditText username, parola;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signIn = findViewById(R.id.login);
        username = findViewById(R.id.username);
        parola = findViewById(R.id.password);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, username.getText().toString() + "fffffffffffffffffffffffffffffff");
                if (username.getText().toString().equals("") || parola.getText().toString().equals("")){
                    AlertBox alertBox = new AlertBox("Ambele campuri sunt obligatorii");
                    alertBox.show(getSupportFragmentManager(), TAG);
                    return;
                }

                if (!check(username.getText().toString() ,parola.getText().toString())){
                    AlertBox alertBox = new AlertBox("Username sau parola gresite");
                    alertBox.show(getSupportFragmentManager(), TAG);
                    return;
                }

                if (username.getText().toString().equals("sef")) {
                    lodSef();
                } else {
                    loadAngajat(username.getText().toString());
                }


            }
        });
    }

    private boolean check(String username, String parola) {
        if (username.equals("sef") && parola.equals("parola"))
            return true;
        List<Angajat> angahati = findAllAngajati();
        boolean ret = false;
        for (Angajat a : angahati)
            if (a.getParola().equals(parola) && a.getUsername().equals(username))
                ret = true;
        return ret;
    }

    private void lodSef() {
        Intent intent = new Intent(this, SefActivity.class);
        startActivity(intent);
    }

    private void loadAngajat(String username) {
        Intent intent = new Intent(this, AngajatActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    private List<Angajat> findAllAngajati() {
        List<Angajat> ret = new ArrayList<>();
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME_ANGAJATI);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String text;
            while ((text = br.readLine()) != null) {
                String[] elems = text.split(",");
                ret.add(new Angajat(elems[0], elems[1], elems[2]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return ret;
    }
}