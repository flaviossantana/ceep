package br.com.alura.ceep.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import br.com.alura.ceep.R;
import br.com.alura.ceep.dao.NotaDAO;
import br.com.alura.ceep.model.Nota;
import br.com.alura.ceep.ui.adapter.recycler.ListaNotasAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

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

//        notaDAO.insere(new Nota("Primeira Nota", "Minha descrição da nota."));
//        notaDAO.insere(new Nota("Segunda Nota", "Esta nota tem muitos detalhes."));


        for (int i = 1; i <= 10000; i++){
            notaDAO.insere(new Nota("Título " + i, "Descrição " + i));
        }

        notas.setAdapter(new ListaNotasAdapter(this, notaDAO.todos()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        notas.setLayoutManager(layoutManager);

    }
}
