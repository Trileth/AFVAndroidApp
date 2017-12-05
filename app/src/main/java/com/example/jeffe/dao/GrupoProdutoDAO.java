package com.example.jeffe.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.jeffe.model.GrupoProduto;

public class GrupoProdutoDAO {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public GrupoProdutoDAO(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDatabase(){
        if (database == null){
            database = databaseHelper.getWritableDatabase();
        }

        return database;
    }

    private GrupoProduto criarGrupoProduto(Cursor cursor){
        GrupoProduto model = new GrupoProduto(
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.GruposProdutos._ID)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.GruposProdutos.DESCRICAO)),
                cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.GruposProdutos.PERC_DESC)),
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.GruposProdutos.COMPLEMENTO))
        );
        return model;
    }

    public List<GrupoProduto> listarGrupoProduto(){
        Cursor cursor = getDatabase().query(DatabaseHelper.GruposProdutos.TABELA,
                DatabaseHelper.GruposProdutos.COLUNAS,null,
                null,null,null,null,null
        );

        List<GrupoProduto> gruposprodutos = new ArrayList<GrupoProduto>();
        while(cursor.moveToNext()){
            GrupoProduto model = criarGrupoProduto(cursor);
            gruposprodutos.add(model);
        }
        cursor.close();
        return gruposprodutos;
    }

    public long salvarGrupoProduto(GrupoProduto gruposprodutos){
        ContentValues valores = new ContentValues();
        valores.put(DatabaseHelper.GruposProdutos._ID, gruposprodutos.getId());

        if(gruposprodutos.getId() != null){
            return database.update(DatabaseHelper.GruposProdutos.TABELA, valores,
                    "_id = ?", new String[]{ gruposprodutos.getId().toString()} );
        }

        return getDatabase().insert(DatabaseHelper.GruposProdutos.TABELA,null, valores);
    }

    public boolean removerGrupoProduto(int id){
        return getDatabase().delete(DatabaseHelper.GruposProdutos.TABELA,
                "_id = ?", new String[]{ Integer.toString(id)}) > 0 ;
    }

    public GrupoProduto buscarGrupoProdutoPorId(int id){
        Cursor cursor = getDatabase().query(DatabaseHelper.GruposProdutos.TABELA,
                DatabaseHelper.GruposProdutos.COLUNAS, "_id = ?", new String[]{ Integer.toString(id)},null,null,null);

        if(cursor.moveToNext()){
            GrupoProduto model = criarGrupoProduto(cursor);
            cursor.close();
            return model;
        }

        return null;
    }


    public void fechar(){
        databaseHelper.close();
        database = null;
    }

}
