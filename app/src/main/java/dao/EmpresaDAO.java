package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Empresa;

public class EmpresaDAO {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public EmpresaDAO(Context context){
       databaseHelper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDatabase(){
        if (database == null){
            database = databaseHelper.getWritableDatabase();
        }

        return database;
    }

    private Empresa criarEmpresa(Cursor cursor){
        Empresa model = new Empresa(
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Empresas._ID)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Empresas.NOME_FANTASIA)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Empresas.RAZAO_SOCIAL)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Empresas.ENDERECO)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Empresas.BAIRRO)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Empresas.CIDADE)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Empresas.CEP)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Empresas.TELEFONE)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Empresas.FAX)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Empresas.CNPJ)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Empresas.IE))
        );
        return model;
    }

    public List<Empresa> listarEmpresas(){
        Cursor cursor = getDatabase().query(DatabaseHelper.Empresas.TABELA,
                                            DatabaseHelper.Empresas.COLUNAS,null,
                                           null,null,null,null,null
                                            );

        List<Empresa> empresas = new ArrayList<Empresa>();
        while(cursor.moveToNext()){
            Empresa model = criarEmpresa(cursor);
            empresas.add(model);
        }
        cursor.close();
        return empresas;
    }

    public List<String> listarRazaoSocial() {
        Cursor cursor = getDatabase().query(DatabaseHelper.Empresas.TABELA,
                new String[]{DatabaseHelper.Empresas.RAZAO_SOCIAL,DatabaseHelper.Empresas.CIDADE}, null,
                null, null, null, null, null
        );

        List<String> empresas = new ArrayList<>();
        while (cursor.moveToNext()){
            empresas.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.Empresas.RAZAO_SOCIAL))+","+
                         cursor.getString(cursor.getColumnIndex(DatabaseHelper.Empresas.CIDADE)));
        }

        cursor.close();
        return empresas;
    }

    public long salvarEmpresa(Empresa empresa){
        ContentValues valores = new ContentValues();
        valores.put(DatabaseHelper.Empresas.BAIRRO, empresa.getBairro());

        if(empresa.get_id() != null){
            return database.update(DatabaseHelper.Empresas.TABELA, valores,
                    "_id = ?", new String[]{ empresa.get_id().toString()} );
        }

        return getDatabase().insert(DatabaseHelper.Empresas.TABELA,null, valores);
    }

    public boolean removerEmpresa(int id){
        return getDatabase().delete(DatabaseHelper.Empresas.TABELA,
                "_id = ?", new String[]{ Integer.toString(id)}) > 0 ;
    }

    public Empresa buscarEmpresaPorId(int id){
        Cursor cursor = getDatabase().query(DatabaseHelper.Empresas.TABELA,
                DatabaseHelper.Empresas.COLUNAS, "_id = ?", new String[]{ Integer.toString(id)},null,null,null);

        if(cursor.moveToNext()){
           Empresa model = criarEmpresa(cursor);
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
