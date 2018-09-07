package org.neocities.dolphintech.applista2.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import org.neocities.dolphintech.applista2.model.Anotacao;
import org.neocities.dolphintech.applista2.model.Anotacao;

public class AnotacaoDAO {

    public static final void inserir(Context contexto, Anotacao anotacao){
        ContentValues valores = new ContentValues();
        valores.put("nome", anotacao.getNome() );
        valores.put("anotacao", anotacao.getAnotacao() );

        Conexao conn = new Conexao(contexto);
        SQLiteDatabase banco =  conn.getWritableDatabase();
        banco.insert("notas", null, valores);

    }

    public static final List<Anotacao> getAnotacoes(Context contexto){
        List<Anotacao> listaDeAnotacoes = new ArrayList<>();

        Conexao conn = new Conexao(contexto);
        SQLiteDatabase banco = conn.getReadableDatabase();

        String sql = "SELECT * FROM notas ORDER BY nome";
        Cursor tabela = banco.rawQuery(sql, null) ;

        if ( tabela.getCount() > 0 ){

            tabela.moveToFirst();

            do{
                Anotacao not = new Anotacao();
                not.setId( tabela.getInt( 0 ) );
                not.setNome( tabela.getString( 1 ) );
                not.setAnotacao( tabela.getString( 2 ) );

                listaDeAnotacoes.add(not);

            }while ( tabela.moveToNext() );

        }
        return listaDeAnotacoes;
    }

    public static String getNota(Context contexto , int i){
        Conexao conn = new Conexao(contexto);
        SQLiteDatabase banco = conn.getReadableDatabase();

        String sql = "SELECT * FROM notas WHERE id = " + i;
        Cursor tabela = banco.rawQuery(sql, null) ;

        tabela.moveToFirst();

        Anotacao not = new Anotacao();
        not.setId( tabela.getInt( 0 ) );
        not.setNome( tabela.getString( 1 ) );
        not.setAnotacao( tabela.getString( 2 ) );
        String titulo = not.getAnotacao() ;
        return titulo;
        }


}