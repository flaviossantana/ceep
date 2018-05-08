package br.com.alura.ceep.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.com.alura.ceep.R;
import br.com.alura.ceep.dao.NotaDAO;
import br.com.alura.ceep.model.Nota;
import br.com.alura.ceep.ui.adapter.recycler.ListaNotasAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListaNotasActivity extends AppCompatActivity {

    @BindView(R.id.lista_notas_recyclerview)
    public RecyclerView notas;

    private NotaDAO notaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_notas);

        ButterKnife.bind(this);
        notaDAO = new NotaDAO();

        notaDAO.insere(new Nota("Ir ao Supermercado", "Comprar frutas para a semana."));
        notaDAO.insere(new Nota("Fazer Reunião Diaria:", "Rever com os envolvidos o relacionamento com as ativiades deistribuidas entre si ara a cinferencia das informações repassadas ao front."));

    }

    @Override
    protected void onResume() {
        setAdapter();
        super.onResume();
    }

    private void setAdapter() {
        notas.setAdapter(new ListaNotasAdapter(this, notaDAO.todos()));
    }

    @OnClick(R.id.lista_notas_insere_nota)
    public void on(View view){
        Intent irFormulario = new Intent(this, FormuarioNotasActivity.class);
        startActivity(irFormulario);
    }

    private void gerarNotas(int qtd) {
        for (int i = 1; i <= qtd; i++) {
            notaDAO.insere(new Nota("Título " + i, "Descrição " + i));
        }
    }
}
