package org.neocities.dolphintech.applista2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.neocities.dolphintech.applista2.dao.AnotacaoDAO;
import org.neocities.dolphintech.applista2.model.Anotacao;

public class FormularioActivity extends AppCompatActivity {


    private ListView lvLista;
    private EditText etNome;
    private EditText etNota;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        etNome = (EditText) findViewById(R.id.etNome);
        lvLista = (ListView) findViewById(R.id.lvLista);
        etNota = (EditText) findViewById(R.id.etNota);
        btnSalvar =    (Button)   findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarAnotacao();
            }
        });

    }

    private void salvarAnotacao() {

        String nome = etNome.getText().toString();
        String anotacao = etNota.getText().toString();

        if (nome.isEmpty()) {

            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setTitle(getResources().getString(R.string.txtAtencao));
            alerta.setIcon(android.R.drawable.ic_dialog_alert);
            alerta.setMessage(R.string.txtCamposObrigatorios);
            alerta.setNeutralButton("OK", null);
            alerta.show();

        } else {
            Anotacao nota = new Anotacao();
            nota.setNome(nome);
            nota.setAnotacao(anotacao);
            AnotacaoDAO.inserir(this, nota);
            finish();

        }
    }


}
