package com.example.jeffe.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.jeffe.model.TipoComplemento;

public class TipoComplementoDAO {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public TipoComplementoDAO(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDatabase(){
        if (database == null){
            database = databaseHelper.getWritableDatabase();
        }

        return database;
    }

    private TipoComplemento criarTipoComplemento(Cursor cursor){
        TipoComplemento model = new TipoComplemento(
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TiposComplementos._ID)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.TiposComplementos.DESCRICAO))
        );
        return model;
    }

    public List<TipoComplemento> listarTipoComplemento(){
        Cursor cursor = getDatabase().query(DatabaseHelper.TiposComplementos.TABELA,
                DatabaseHelper.TiposComplementos.COLUNAS,null,
                null,null,null,null,null
        );

        List<TipoComplemento> tiposcomplementos = new ArrayList<TipoComplemento>();
        while(cursor.moveToNext()){
            TipoComplemento model = criarTipoComplemento(cursor);
            tiposcomplementos.add(model);
        }
        cursor.close();
        return tiposcomplementos;
    }

    public long salvarTipoComplemento(TipoComplemento tiposcomplementos){
        ContentValues valores = new ContentValues();
        valores.put(DatabaseHelper.TiposComplementos._ID, tiposcomplementos.get_id());

        if(tiposcomplementos.get_id() != null){
            return database.update(DatabaseHelper.TiposComplementos.TABELA, valores,
                    "_id = ?", new String[]{ tiposcomplementos.get_id().toString()} );
        }

        return getDatabase().insert(DatabaseHelper.TiposComplementos.TABELA,null, valores);
    }

    public boolean removerTipoComplemento(int id){
        return getDatabase().delete(DatabaseHelper.TiposComplementos.TABELA,
                "_id = ?", new String[]{ Integer.toString(id)}) > 0 ;
    }

    public TipoComplemento buscarTipoComplementoPorId(int id){
        Cursor cursor = getDatabase().query(DatabaseHelper.TiposComplementos.TABELA,
                DatabaseHelper.TiposComplementos.COLUNAS, "_id = ?", new String[]{ Integer.toString(id)},null,null,null);

        if(cursor.moveToNext()){
            TipoComplemento model = criarTipoComplemento(cursor);
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

