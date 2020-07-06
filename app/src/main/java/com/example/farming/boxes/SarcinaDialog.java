package com.example.farming.boxes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.farming.R;

public class SarcinaDialog extends AppCompatDialogFragment {
    public EditText descriereEditText;
    private String username;
    private SarcinaDialogListener listener;

    public SarcinaDialog(String username) {
        this.username = username;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        descriereEditText = view.findViewById(R.id.descriere_id);
        builder
                .setView(view)
                .setTitle("Detalii sarcina")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String descriere = descriereEditText.getText().toString();
                        listener.getDescriere(username + "," + descriere);
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (SarcinaDialogListener)context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface SarcinaDialogListener{
        String getDescriere(String d);
    }
}
