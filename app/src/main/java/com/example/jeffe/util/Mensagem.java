package com.example.jeffe.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

public class Mensagem {

    public static void Msg(Activity activity, String msg){
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }

    public static void addMsgOK(Activity activity, String titulo, String msg, int icone){
        AlertDialog.Builder alert = new AlertDialog.Builder(activity,android.R.style.Theme_Material_Light_Dialog_Alert);
        alert.setTitle(titulo);
        alert.setMessage(msg);
        alert.setNeutralButton("OK", null);
        alert.setIcon(icone);
        alert.show();
    }

    public static void MsgConfirm(Activity activity, String titulo, String msg, int icone, DialogInterface.OnClickListener listener){
        AlertDialog.Builder alert = new AlertDialog.Builder(activity,android.R.style.Theme_Material_Light_Dialog_Alert);
        alert.setTitle(titulo);
        alert.setMessage(msg);
        alert.setNegativeButton("Não", null);
        alert.setPositiveButton("Sim", listener);
        alert.setIcon(icone);
        alert.show();
    }

    public static AlertDialog criarAlertDialog(Activity activity){
        final CharSequence[] items = {
                "Editar",
                "Excluir"
        };

        AlertDialog.Builder alert= new AlertDialog.Builder(activity,android.R.style.Theme_Material_Light_Dialog_Alert);
        alert.setTitle("Opções");
        alert.setItems(items, (DialogInterface.OnClickListener) activity);
        return alert.create();
    }

    public static AlertDialog criarDialogConfirmacao(Activity activity){
        AlertDialog.Builder alert = new AlertDialog.Builder(activity,android.R.style.Theme_Material_Light_Dialog_Alert);
        alert.setMessage("Deseja realmente excluir?");
        alert.setNegativeButton("Não", (DialogInterface.OnClickListener) activity);
        alert.setPositiveButton("Sim", (DialogInterface.OnClickListener) activity);


        return alert.create();
    }
}
