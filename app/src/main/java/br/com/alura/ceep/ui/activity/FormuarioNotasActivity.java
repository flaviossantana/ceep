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
    public static final String EXTRA_NOTA = "nota";
    public static final String EXTRA_POSICAO = "POSICAO";
    public static final int POSICAO_INVALIDA = -1;
    public static final String TITLE_INSERE_NOTAS = "Insere Notas";
    public static final String TITLE_ALTERA_NOTAS = "Altera Notas";

    @BindView(R.id.form_titulo)
    public EditText titulo;

    @BindView(R.id.form_descricao)
    public EditText descricao;

    private NotaDAO notaDAO;
    private int posicao = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formuario_notas);
        setTitle(TITLE_INSERE_NOTAS);

        ButterKnife.bind(this);
        notaDAO = new NotaDAO();

        if(getIntent().hasExtra(EXTRA_NOTA)){
            setTitle(TITLE_ALTERA_NOTAS);
            posicao = getIntent().getIntExtra(EXTRA_POSICAO, POSICAO_INVALIDA);
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
        irListaNota.putExtra(EXTRA_POSICAO, posicao);
        setResult(RESULT_CODE_NOTA_CRIADA, irListaNota);
    }

}
