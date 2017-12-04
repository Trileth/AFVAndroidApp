package dao;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String BANCO_DADOS = "task";
    private static final int VERSAO = 1;

    public DatabaseHelper(Context context){
        super (context,BANCO_DADOS,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tabela EMPRESA
        db.execSQL("CREATE TABLE EMPRESA (" +
                "    cod_empresa     integer      primary key   not null," +
                "    nome_fantasia   varchar (30)               not null," +
                "    razao_social    varchar (50)               not null," +
                "    endereco        varchar (50)               not null," +
                "    bairro          varchar (30)               not null," +
                "    cidade          varchar (50)               not null," +
                "    cep             varchar (10)               not null," +
                "    telefone        varchar (15)               not null," +
                "    fax             varchar (15), " +
                "    cnpj            varchar (18)               not null, " +
                "    ie              varchar (18)               not null)" );

        db.execSQL("INSERT INTO EMPRESA (cod_empresa, nome_fantasia, razao_social, endereco, bairro, cidade, cep, telefone, cnpj, ie) " +
                "VALUES (1, 'EMPRESA 1', 'ROMA VENDAS LTDA', 'RUA NELSON CALIXTO 142','bairro','LINS', '16200-320', '(18)3644-7333','88.060.431/0001-94','ISENTO');");
        db.execSQL("INSERT INTO EMPRESA (cod_empresa, nome_fantasia, razao_social, endereco, bairro, cidade, cep, telefone, cnpj, ie) " +
                "VALUES (2, 'EMPRESA 2', 'ITALIA VENDAS LTDA', 'RUA NELSON CALIXTO 142','bairro','BIRIGUI', '16200-320', '(18)3644-7333','88.060.431/0001-94','ISENTO');");
        db.execSQL("INSERT INTO EMPRESA (cod_empresa, nome_fantasia, razao_social, endereco, bairro, cidade, cep,  telefone, cnpj, ie) " +
                "VALUES (3, 'EMPRESA 3', 'VENEZA VENDAS LTDA', 'RUA NELSON CALIXTO 142','bairro','SÃO CARLOS', '16200-320', '(18)3644-7333','88.060.431/0001-94','ISENTO');");



        //Tabela TIPO_COMPLEMENTO
        db.execSQL("create table tipo_complemento (" +
                "    cod_tipo_complemento       integer primary key," +
                "    descricao_tipo_complemento varchar (30))");
        db.execSQL("insert into tipo_complemento(cod_tipo_complemento,descricao_tipo_complemento) " +
                 "   values (1,'TECIDO');");

        //Tabela GRUPO_PRODUTO
        db.execSQL("create table grupo_produto (" +
                "    cod_grupo_produto             integer    primary key     not null," +
                "    descricao_grupo_produto       varchar (50)," +
                "    perc_desconto                 numeric (5, 2)," +
                "    fk_tipo_complemento           integer," +
                "FOREIGN KEY(fk_tipo_complemento) REFERENCES tipo_complemento(cod_tipo_complemento))");
        db.execSQL("insert into grupo_produto(cod_grupo_produto,descricao_grupo_produto,perc_desconto,fk_tipo_complemento) " +
                "   values (1,'CAMA MESA BANHO',0.00,1);");

        //Tabela PRODUTO
        db.execSQL("create table produto (" +
                "    cod_produto                  integer   primary key autoincrement not null," +
                "    fk_empresa                   integer               not null," +
                "    fk_grupo_produto             integer               not null," +
                "    fk_subgrupo_produto          integer," +
                "    descricao_produto            varchar (50)          not null," +
                "    apelido_produto              varchar (30)," +
                "    situacao                     varchar (1)           not null," +
                "    peso_liquido                 numeric (11, 4)," +
                "    classificacao_fiscal         varchar (10)," +
                "    codigo_barras                varchar (15)," +
                "    colecao                      varchar (100)," +
                "FOREIGN KEY(fk_empresa) REFERENCES empresa(cod_empresa)," +
                "FOREIGN KEY(fk_grupo_produto) REFERENCES grupo_produto(cod_grupo_produto)," +
                "FOREIGN KEY(fk_subgrupo_produto) REFERENCES grupo_produto(cod_grupo_produto))");
        //Inserindo Produtos
        db.execSQL("insert into produto(fk_empresa,fk_grupo_produto,fk_subgrupo_produto,"+
                                       "descricao_produto,apelido_produto,situacao,peso_liquido,"+
                                       "classificacao_fiscal,codigo_barras) " +
                "   values (1,1,1,'CAMA DE SOLTEIRO QUEEN SIZE','CAMA_1','A',3.5,'6302.10.00','1234567891234');");
        db.execSQL("insert into produto(fk_empresa,fk_grupo_produto,fk_subgrupo_produto,"+
                "descricao_produto,apelido_produto,situacao,peso_liquido,"+
                "classificacao_fiscal,codigo_barras) " +
                "   values (1,1,1,'CAMA DE CASAL KING SIZE','CAMA_2','A',3.5,'6302.10.00','1234567891234');");
        db.execSQL("insert into produto(fk_empresa,fk_grupo_produto,fk_subgrupo_produto,"+
                "descricao_produto,apelido_produto,situacao,peso_liquido,"+
                "classificacao_fiscal,codigo_barras) " +
                "   values (1,1,1,'CAMA DE CASAL QUEEN SIZE','CAMA_3','A',3.5,'6302.10.00','1234567891234');");
        db.execSQL("insert into produto(fk_empresa,fk_grupo_produto,fk_subgrupo_produto,"+
                "descricao_produto,apelido_produto,situacao,peso_liquido,"+
                "classificacao_fiscal,codigo_barras) " +
                "   values (1,1,1,'BERÇO EMBUTIDO BOX','CAMA_4','A',3.5,'6302.10.00','1234567891234');");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static class Empresas{
        public static final String TABELA        =  "empresa";
        public static final String _ID           =  "cod_empresa";
        public static final String NOME_FANTASIA =  "nome_fantasia";
        public static final String RAZAO_SOCIAL  =  "razao_social";
        public static final String ENDERECO      =  "endereco";
        public static final String BAIRRO        =  "bairro";
        public static final String CIDADE        =  "cidade";
        public static final String CEP           =  "cep";
        public static final String TELEFONE      =  "telefone";
        public static final String FAX           =  "fax";
        public static final String CNPJ          =  "cnpj";
        public static final String IE            =  "ie";

        public static final String[] COLUNAS = new String[]{
            _ID ,NOME_FANTASIA,RAZAO_SOCIAL,ENDERECO,BAIRRO,CIDADE,CEP,
            TELEFONE,FAX,CNPJ,IE
        };
    }
    public static class TiposComplementos{
        public static final String TABELA    = "tipo_complemento";
        public static final String _ID       = "cod_tipo_complemento";
        public static final String DESCRICAO = "descricao_tipo_complemento";

        public static final String[] COLUNAS = new String[]{
                _ID ,DESCRICAO
        };
    }
    public static class GruposProdutos{
        public static final String TABELA        = "grupo_produto";
        public static final String _ID           = "cod_grupo_produto";
        public static final String DESCRICAO     = "descricao_grupo_produto";
        public static final String PERC_DESC     = "perc_desconto";
        public static final String COMPLEMENTO   = "fk_tipo_complemento";

        public static final String[] COLUNAS = new String[]{
                _ID ,DESCRICAO,PERC_DESC,COMPLEMENTO
        };
    }
    public static class Produtos{
        public static final String TABELA        =  "produto";
        public static final String _ID           =  "cod_produto";
        public static final String EMPRESA       =  "fk_empresa";
        public static final String GRUPO         =  "fk_grupo_produto";
        public static final String SUBGRUPO      =  "fk_subgrupo_produto";
        public static final String DESCRICAO     =  "descricao_produto";
        public static final String APELIDO       =  "apelido_produto";
        public static final String SITUACAO      =  "situacao";
        public static final String PESO_LIQUIDO  =  "peso_liquido";
        public static final String CLASSIFICACAO =  "classificacao_fiscal";
        public static final String GTIN          =  "codigo_barras";
        public static final String COLECAO       =  "colecao";

        public static final String[] COLUNAS = new String[]{
                _ID ,EMPRESA,GRUPO,SUBGRUPO,DESCRICAO,APELIDO,SITUACAO,
                PESO_LIQUIDO,CLASSIFICACAO,GTIN,COLECAO
        };
    }


}
