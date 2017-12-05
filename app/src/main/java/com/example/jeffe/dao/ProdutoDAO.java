package com.example.jeffe.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.jeffe.model.Produto;

public class ProdutoDAO {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public ProdutoDAO(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDatabase(){
        if (database == null){
            database = databaseHelper.getWritableDatabase();
        }

        return database;
    }

    private Produto criarProduto(Cursor cursor){
        Produto model = new Produto(
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Produtos._ID)),
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Produtos.EMPRESA)),
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Produtos.GRUPO)),
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Produtos.SUBGRUPO)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Produtos.DESCRICAO)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Produtos.APELIDO)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Produtos.SITUACAO)),
                cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.Produtos.PESO_LIQUIDO)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Produtos.CLASSIFICACAO)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Produtos.GTIN)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Produtos.COLECAO))
        );
        return model;
    }

    public List<Produto> listarProdutos(int emp,String ordem,String busca,int pagina){
        busca = busca.toUpperCase();
        String query = "";
        query =   " fk_empresa = ? and (UPPER(cod_produto) like '%"+busca+"%' or "+
                  " UPPER(fk_empresa) like '%"+busca+"%' or "+
                  " UPPER(fk_grupo_produto) like '%"+busca+"%' or "+
                  " UPPER(fk_subgrupo_produto) like '%"+busca+"%' or "+
                  " UPPER(descricao_produto) like '%"+busca+"%' or "+
                  " UPPER(apelido_produto) like '%"+busca+"%' or "+
                  " UPPER(situacao) like '%"+busca+"%' or "+
                  " UPPER(peso_liquido) like '%"+busca+"%' or "+
                  " UPPER(classificacao_fiscal) like '%"+busca+"%' or "+
                  " UPPER(codigo_barras) like '%"+busca+"%' or "+
                  " UPPER(colecao)   like '%"+busca+"%')";

        String limite = String.valueOf(pagina*5)+", "+String.valueOf(5);
        Cursor cursor = getDatabase().query(DatabaseHelper.Produtos.TABELA,
                DatabaseHelper.Produtos.COLUNAS,query,
                new String[]{String.valueOf(emp)},null,null,ordem, limite
        );

        List<Produto> produtos = new ArrayList<>();
        while(cursor.moveToNext()){
            Produto model = criarProduto(cursor);
            produtos.add(model);
        }
        cursor.close();
        return produtos;
    }

    public int contarProdutos(int emp,String busca){
        busca = busca.toUpperCase();
        String query =   " fk_empresa = ? and (UPPER(cod_produto) like '%"+busca+"%' or "+
                " UPPER(fk_empresa) like '%"+busca+"%' or "+
                " UPPER(fk_grupo_produto) like '%"+busca+"%' or "+
                " UPPER(fk_subgrupo_produto) like '%"+busca+"%' or "+
                " UPPER(descricao_produto) like '%"+busca+"%' or "+
                " UPPER(apelido_produto) like '%"+busca+"%' or "+
                " UPPER(situacao) like '%"+busca+"%' or "+
                " UPPER(peso_liquido) like '%"+busca+"%' or "+
                " UPPER(classificacao_fiscal) like '%"+busca+"%' or "+
                " UPPER(codigo_barras) like '%"+busca+"%' or "+
                " UPPER(colecao)   like '%"+busca+"%')";

        Cursor cursor = getDatabase().query(DatabaseHelper.Produtos.TABELA,
                new String[]{" count(*) as qntd "},query,
                new String[]{String.valueOf(emp)},null,null,null, null
        );
        int qntd = 0;
        while(cursor.moveToNext()){
            qntd = cursor.getInt(0);
        }
        cursor.close();
        return qntd;
    }

    public long salvarProduto(Produto produto){
        ContentValues valores = new ContentValues();
        valores.put(DatabaseHelper.Produtos.EMPRESA, produto.getEmpresa());
        valores.put(DatabaseHelper.Produtos.GRUPO, produto.getGrupo());
        valores.put(DatabaseHelper.Produtos.SUBGRUPO, produto.getSubgrupo());
        valores.put(DatabaseHelper.Produtos.DESCRICAO, produto.getDescricao());
        valores.put(DatabaseHelper.Produtos.APELIDO, produto.getApelido());
        valores.put(DatabaseHelper.Produtos.SITUACAO, produto.getSituacao());
        valores.put(DatabaseHelper.Produtos.PESO_LIQUIDO, produto.getPeso_liquido());
        valores.put(DatabaseHelper.Produtos.CLASSIFICACAO, produto.getClassificacao());

        if(produto.getId() > 0){
            return database.update(DatabaseHelper.Produtos.TABELA, valores,
                    "cod_produto = ?", new String[]{ produto.getId().toString()} );
        }

        return getDatabase().insert(DatabaseHelper.Produtos.TABELA,null, valores);
    }

    public boolean removerProduto(int id){
        return getDatabase().delete(DatabaseHelper.Produtos.TABELA,
                "cod_produto = ?", new String[]{ Integer.toString(id)}) > 0 ;
    }


    public Produto buscarProdutoPorId(Integer id){
        Cursor cursor = getDatabase().query(DatabaseHelper.Produtos.TABELA,
                DatabaseHelper.Produtos.COLUNAS, "cod_produto = ?", new String[]{ Integer.toString(id)},null,null,null);

        if(cursor.moveToNext()){
            Produto model = criarProduto(cursor);
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
