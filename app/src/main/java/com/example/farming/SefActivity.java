package com.example.farming;

import android.os.Bundle;

import com.example.farming.boxes.SarcinaDialog;
import com.example.farming.model.Angajat;
import com.example.farming.model.Defectiune;
import com.example.farming.model.Produs;
import com.example.farming.repositories.AngajatiRepository;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SefActivity extends AppCompatActivity implements SarcinaDialog.SarcinaDialogListener {
    private static String TAG = SefActivity.class.getName().toString();
    private static final String FILE_NAME_ANGAJATI = "angajati.txt";
    private static final String FILE_NAME_PRODUSE = "produse.txt";
    private static final String FILE_NAME_DEFECTIUNI = "defectiuni.txt";
    private static final String FILE_NAME_SARCINI = "sarcini.txt";

    private TextView status, cantitate, gravitate;
    private TableLayout tableLayout, tabelProduse, tabelDefectiuni;
    private TableRow row;
    private AngajatiRepository repoAngajati;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repoAngajati = new AngajatiRepository();
        setContentView(R.layout.activity_sef);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tableLayout = findViewById(R.id.tabel_angajati);
        tabelProduse = findViewById(R.id.tabel_productie);
        tabelDefectiuni = findViewById(R.id.tabel_defectiuni);
        tableLayout.setColumnStretchable(0, true);
        tableLayout.setColumnStretchable(1, true);
        tableLayout.setColumnStretchable(2, true);

        List<Angajat> aa = findAllAngajati();
        for (Angajat a : aa) {
            Log.d(TAG, a.getUsername());
            row = new TableRow(this);

            final TextView nume = new TextView(this);
            status = new TextView(this);
            nume.setText(a.getUsername());
            nume.setTextSize(25);
            nume.setGravity(Gravity.CENTER);
            status.setText(a.getStatus());
            status.setTextSize(25);
            status.setGravity(Gravity.CENTER);
            row.addView(nume);
            row.addView(status);
            Button trm = new Button(getApplicationContext());
            trm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addSarcinaPentru(nume.getText().toString());
                }
            });
            trm.setText("Trimite");
            row.addView(trm);
            tableLayout.addView(row);
        }

        List<Produs> pp = findAllProduse();
        for (Produs p : pp) {
            Log.d(TAG, p.getDenumire() + "       " + p.getCantitate());

            row = new TableRow(this);

            final TextView denumire = new TextView(this);
            cantitate = new TextView(this);
            denumire.setText(p.getDenumire());
            denumire.setTextSize(25);
            denumire.setGravity(Gravity.CENTER);
            cantitate.setText(p.getCantitate().toString());
            cantitate.setTextSize(25);
            cantitate.setGravity(Gravity.CENTER);
            row.addView(denumire);
            row.addView(cantitate);
            tabelProduse.addView(row);
        }

        List<Defectiune> dd = findAllDefectiuni();
        for (Defectiune d : dd) {

            row = new TableRow(this);

            final TextView utilaj = new TextView(this);
            gravitate = new TextView(this);
            utilaj.setText(d.getUtilaj());
            utilaj.setTextSize(25);
            utilaj.setGravity(Gravity.CENTER);
            gravitate.setText(d.getGravitate());
            gravitate.setTextSize(25);
            gravitate.setGravity(Gravity.CENTER);
            row.addView(utilaj);
            row.addView(gravitate);
            tabelDefectiuni.addView(row);
        }
    }

    private List<Defectiune> findAllDefectiuni() {

//        String text = "Tractor1,usoara\n";
//        FileOutputStream fos = null;
//        try {
//            fos = openFileOutput(FILE_NAME_DEFECTIUNI, MODE_PRIVATE);
//            fos.write(text.getBytes());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        List<Defectiune> ret = new ArrayList<>();
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME_DEFECTIUNI);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String text;
            while ((text = br.readLine()) != null) {
                String[] elems = text.split(",");
                ret.add(new Defectiune(elems[0], elems[1]));
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

    private List<Produs> findAllProduse() {
        Map<String, Integer> produse = new HashMap<>();

//        String text = "Porumb,100\nOrz,200\nGrau,10\nFan,55\n";
//        FileOutputStream fos = null;
//        try {
//            fos = openFileOutput(FILE_NAME_PRODUSE, MODE_PRIVATE);
//            fos.write(text.getBytes());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }


        List<Produs> ret = new ArrayList<>();
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME_PRODUSE);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String text;
            while ((text = br.readLine()) != null) {
                String[] elems = text.split(",");
                Produs p = new Produs(elems[0], Integer.parseInt(elems[1]));
                if (produse.containsKey(p.getDenumire())) {
                    Integer a = produse.get(p.getDenumire()) + p.getCantitate();
                    produse.put(p.getDenumire(), produse.get(p.getDenumire()) + p.getCantitate());
                }
                else produse.put(p.getDenumire(), p.getCantitate());
            }

            for (Map.Entry<String, Integer> e : produse.entrySet()) {
                ret.add(new Produs(e.getKey(), e.getValue()));
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

    private void addSarcinaPentru(String username) {
        Log.d(TAG, username);
        String descriere = openDialog(username);

    }

    private String openDialog(String username) {
        SarcinaDialog sarcinaDialog = new SarcinaDialog(username);
        sarcinaDialog.show(getSupportFragmentManager(), TAG);
        return "";
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

    @Override
    public String getDescriere(String d) {
        Log.d(TAG, d + "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");

        String text = d + "\n";
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME_SARCINI, MODE_APPEND);
            fos.write(text.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    return "";
    }
}