package br.com.alura.ceep.ui.adapter.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.model.Nota;
import br.com.alura.ceep.ui.adapter.recycler.listener.OnItemClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaNotasAdapter extends RecyclerView.Adapter<ListaNotasAdapter.NotasViewHolder> {

    private Context context;
    private final List<Nota> notas;
    private OnItemClickListener onItemClickListener;

    public ListaNotasAdapter(Context context, List<Nota> notas) {
        this.context = context;
        this.notas = notas;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return notas.size();
    }

    @NonNull
    @Override
    public NotasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nota, parent, false);
        return new NotasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotasViewHolder holder, int position) {
        Nota nota = notas.get(position);
        holder.titulo.setText(nota.getTitulo());
        holder.descricao.setText(nota.getDescricao());
    }

    public void add(Nota nota) {
        notas.add(nota);
        notifyDataSetChanged();
    }

    public void altera(int posicao, Nota nota) {
        notas.set(posicao, nota);
        notifyDataSetChanged();
    }

    public void remove(int posicao) {
        notas.remove(posicao);
        notifyDataSetChanged();
    }

    public class NotasViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_nota_titulo)
        TextView titulo;

        @BindView(R.id.item_nota_descricao)
        TextView descricao;

        public NotasViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition(), new Nota(titulo.getText().toString(), descricao.getText().toString()));
                }
            });

        }

    }

}
