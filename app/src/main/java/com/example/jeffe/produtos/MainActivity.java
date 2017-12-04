package com.example.jeffe.produtos;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import adapter.SpinnerEmpresaAdapter;
import dao.EmpresaDAO;
import model.Empresa;


public class MainActivity extends Activity {

    private EmpresaDAO helper =  new EmpresaDAO(this);
    private List<Empresa> listaEmp = new ArrayList<Empresa>();
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);

        listaEmp = helper.listarEmpresas();
        Empresa[] empArr = listaEmp.toArray(new Empresa[listaEmp.size()]);
        spinner = (Spinner) findViewById(R.id.empresa_spinner);
        SpinnerEmpresaAdapter spinnerArrayAdapter = new SpinnerEmpresaAdapter(this, android.R.layout.simple_spinner_dropdown_item, empArr);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);
    }
    public void onConfirmarClick(View view) {
        getWindow().setExitTransition(new Explode());
        Empresa empresaSelecionada = (Empresa) spinner.getSelectedItem();
        Intent intent = new Intent(this, ProdutosActivity.class);
        intent.putExtra("emp",empresaSelecionada.get_id());
        startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}
