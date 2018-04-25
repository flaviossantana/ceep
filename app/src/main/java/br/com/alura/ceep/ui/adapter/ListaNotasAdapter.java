package br.com.alura.ceep.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.model.Nota;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaNotasAdapter extends BaseAdapter {

    @BindView(R.id.item_nota_titulo)
    public TextView titulo;

    @BindView(R.id.item_nota_descricao)
    public TextView descricao;

    private final Context context;
    private final List<Nota> notas;


    public ListaNotasAdapter(Context context, List<Nota> notas) {
        this.context = context;
        this.notas = notas;
    }

    @Override
    public int getCount() {
        return notas.size();
    }

    @Override
    public Nota getItem(int posicao) {
        return notas.get(posicao);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup viewGroup) {

        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_nota, viewGroup, false);

        ButterKnife.bind(this, viewCriada);

        Nota nota = notas.get(posicao);

        titulo.setText(nota.getTitulo());
        descricao.setText(nota.getDescricao());

        return viewCriada;
    }
}
