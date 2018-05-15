package br.com.alura.ceep.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import br.com.alura.ceep.R;
import br.com.alura.ceep.dao.NotaDAO;
import br.com.alura.ceep.model.Nota;
import br.com.alura.ceep.ui.adapter.recycler.ListaNotasAdapter;
import br.com.alura.ceep.ui.adapter.recycler.listener.OnItemClickListener;
import br.com.alura.ceep.ui.helper.NotaItemTouchHelper;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static br.com.alura.ceep.ui.activity.FormuarioNotasActivity.EXTRA_NOTA;
import static br.com.alura.ceep.ui.activity.FormuarioNotasActivity.EXTRA_POSICAO;
import static br.com.alura.ceep.ui.activity.FormuarioNotasActivity.RESULT_CODE_NOTA_CRIADA;

public class ListaNotasActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_FORM = 1;
    public static final int REQUEST_CODE_FORM_EDIT = 2;

    @BindView(R.id.lista_notas_recyclerview)
    public RecyclerView notas;

    private NotaDAO notaDAO;
    private ListaNotasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        ButterKnife.bind(this);
        notaDAO = new NotaDAO();

        gerarNotas(4);

        setAdapter();

        touchHelper();

    }

    private void touchHelper() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new NotaItemTouchHelper());
        itemTouchHelper.attachToRecyclerView(notas);
    }

    private void setAdapter() {
        adapter = new ListaNotasAdapter(this,  notaDAO.todos());
        notas.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int posicao, Nota nota) {

                Intent irFormulario = new Intent(ListaNotasActivity.this, FormuarioNotasActivity.class);
                irFormulario.putExtra(EXTRA_NOTA, nota);
                irFormulario.putExtra(EXTRA_POSICAO, posicao);

                startActivityForResult(irFormulario, REQUEST_CODE_FORM_EDIT);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick(R.id.lista_notas_insere_nota)
    public void onClickAddNota(View view){
        Intent irFormulario = new Intent(this, FormuarioNotasActivity.class);
        startActivityForResult(irFormulario, REQUEST_CODE_FORM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_FORM && resultCode == RESULT_CODE_NOTA_CRIADA && data.hasExtra(EXTRA_NOTA)){

            Nota nota = (Nota) data.getSerializableExtra(EXTRA_NOTA);
            notaDAO.insere(nota);
            adapter.add(nota);
        }

        if(requestCode == REQUEST_CODE_FORM_EDIT && resultCode == RESULT_CODE_NOTA_CRIADA && data.hasExtra(EXTRA_NOTA) && data.hasExtra(EXTRA_POSICAO)){

            Nota nota = (Nota) data.getSerializableExtra(EXTRA_NOTA);
            int posicao = data.getIntExtra(EXTRA_POSICAO, -1);
            notaDAO.altera(posicao, nota);
            adapter.altera(posicao, nota);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void gerarNotas(int qtd) {
        for (int i = 1; i <= qtd; i++) {
            notaDAO.insere(new Nota("Título " + i, "Descrição " + i));
        }
    }
}
