package br.com.alura.ceep.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.dao.NotaDAO;
import br.com.alura.ceep.model.Nota;
import br.com.alura.ceep.ui.adapter.ListaNotasAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaNotasActivity extends AppCompatActivity {

    @BindView(R.id.listView)
    public ListView notas;

    private NotaDAO notaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        ButterKnife.bind(this);
        notaDAO = new NotaDAO();

        notaDAO.insere(new Nota("Primeira Nota", "Minha descrição da nota."));
        notaDAO.insere(new Nota("Segunda Nota", "Esta nota tem muitos detalhes."));

        notas.setAdapter(new ListaNotasAdapter(this, notaDAO.todos()));

    }
}
