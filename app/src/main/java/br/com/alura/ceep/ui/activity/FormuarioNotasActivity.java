package br.com.alura.ceep.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.Serializable;

import br.com.alura.ceep.R;
import br.com.alura.ceep.dao.NotaDAO;
import br.com.alura.ceep.model.Nota;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FormuarioNotasActivity extends AppCompatActivity {

    public static final int RESULT_CODE_NOTA_CRIADA = 2;
    public static final String EXTRA_NOTA = "nota";;

    @BindView(R.id.form_titulo)
    public EditText titulo;

    @BindView(R.id.form_descricao)
    public EditText descricao;

    private NotaDAO notaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formuario_notas);

        ButterKnife.bind(this);
        notaDAO = new NotaDAO();

        if(getIntent().hasExtra(EXTRA_NOTA)){
            Nota nota = (Nota) getIntent().getSerializableExtra(EXTRA_NOTA);
            titulo.setText(nota.getTitulo());
            descricao.setText(nota.getDescricao());
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form_acoes, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(isSalvar(item)){

            Nota nota = new Nota(getText(titulo), getText(descricao));
            notaDAO.insere(nota);

            enviarNota(nota);

            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @NonNull
    private String getText(EditText editText) {
        return editText.getText().toString();
    }

    private boolean isSalvar(MenuItem item) {
        return item.getItemId() == R.id.menu_form_acao;
    }

    private void enviarNota(Nota nota) {
        Intent irListaNota = new Intent();
        irListaNota.putExtra(EXTRA_NOTA, nota);
        setResult(RESULT_CODE_NOTA_CRIADA, irListaNota);
    }

}
