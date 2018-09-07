package org.neocities.dolphintech.applista2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.neocities.dolphintech.applista2.dao.AnotacaoDAO;
import org.neocities.dolphintech.applista2.model.Anotacao;

import java.util.ArrayList;
import java.util.List;


public class ListaActivity extends AppCompatActivity {

    private ArrayAdapter adapter;
    private ListView lvLista;
    private TextView tvLeitor;
    private List<String> listaDeAnotacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lvLista = (ListView) findViewById(R.id.lvLista);
        tvLeitor = (TextView) findViewById(R.id.tvLeitor);
        listaDeAnotacoes = new ArrayList<>();
        adapter = new ArrayAdapter(
                ListaActivity.this,
                android.R.layout.simple_list_item_1,
                listaDeAnotacoes);
        lvLista.setAdapter(adapter);

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Anotacao nota = (Anotacao) lvLista.getItemAtPosition(i);
                carregarNota(nota.getId());
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(
                        ListaActivity.this,
                        FormularioActivity.class);
                startActivity(intent);
            }
        });

        carregarAnotacoes();

    }

    public void carregarNota(int i) {
        String titulo = AnotacaoDAO.getNota(this, i);
        tvLeitor.setText(titulo);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarAnotacoes();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarAnotacoes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void carregarAnotacoes() {
        List<Anotacao> listaDeAnotacoes = AnotacaoDAO.getAnotacoes(this);
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, listaDeAnotacoes);
        lvLista.setAdapter(adapter);

    }

}
