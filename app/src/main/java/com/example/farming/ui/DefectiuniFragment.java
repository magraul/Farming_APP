package com.example.farming.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.farming.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DefectiuniFragment extends Fragment {
    private static final String FILE_NAME_DEFECTIUNI = "defectiuni.txt";
    public DefectiuniFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.defectiuni_fragment ,container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button adauga = (Button) getView().findViewById(R.id.adauga_defectiune);
        adauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText utilaj = (EditText)getView().findViewById(R.id.nume_utilajj);
                EditText gravitate = (EditText)getView().findViewById(R.id.gravitatee);
                String text = utilaj.getText().toString() + "," + gravitate.getText().toString() + "\n";

                FileOutputStream fos = null;
                try {
                    fos = getActivity().openFileOutput(FILE_NAME_DEFECTIUNI, Context.MODE_APPEND);
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

                utilaj.setText("");
                gravitate.setText("");
            }
        });
    }

}
