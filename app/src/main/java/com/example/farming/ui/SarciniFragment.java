package com.example.farming.ui;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.farming.R;
import com.example.farming.model.Sarcina;

import java.util.List;

public class SarciniFragment extends Fragment {
    private List<Sarcina> sarcini;
    private TableRow row;
    private TextView descriere_sarcina;
    public SarciniFragment(List<Sarcina> sarcini) {
        this.sarcini = sarcini;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sarcini_fragment ,container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TableLayout tabelSarcini = (TableLayout)getView().findViewById(R.id.tabel_sarcinii);

        for (Sarcina s : this.sarcini) {
            row = new TableRow(getContext());
            descriere_sarcina = new TextView(getContext());
            descriere_sarcina.setText(s.getDescriere());
            descriere_sarcina.setTextSize(25);
            descriere_sarcina.setGravity(Gravity.CENTER);
            row.addView(descriere_sarcina);
            tabelSarcini.addView(row);
        }
    }


}
