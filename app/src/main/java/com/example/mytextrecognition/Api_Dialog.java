package com.example.mytextrecognition;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

public class Api_Dialog extends AppCompatDialogFragment{
    private EditText editApi;
    private ApiDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.api_dialog, null);

        builder.setView(view)
                .setTitle("Enter Your API")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String api = editApi.getText().toString();
                        if (api.isEmpty()){
                            Toast.makeText(getActivity(),"Text Field is empty!!",Toast.LENGTH_SHORT).show();
                        } else{
                            listener.applyText(api);
                            Toast.makeText(getActivity(), "API saved", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        editApi = view.findViewById(R.id.api);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ApiDialogListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface ApiDialogListener {
        void applyText(String api);
    }
}
