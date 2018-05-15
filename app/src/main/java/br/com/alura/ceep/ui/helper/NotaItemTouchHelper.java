package br.com.alura.ceep.ui.helper;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import br.com.alura.ceep.dao.NotaDAO;
import br.com.alura.ceep.ui.adapter.recycler.ListaNotasAdapter;

import static android.support.v7.widget.helper.ItemTouchHelper.DOWN;
import static android.support.v7.widget.helper.ItemTouchHelper.LEFT;
import static android.support.v7.widget.helper.ItemTouchHelper.RIGHT;
import static android.support.v7.widget.helper.ItemTouchHelper.UP;

public class NotaItemTouchHelper extends ItemTouchHelper.Callback {

    private ListaNotasAdapter adapter;

    public NotaItemTouchHelper(ListaNotasAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int marcacaoDeslize = LEFT | RIGHT;
        int marcacaoArraste = LEFT | RIGHT | UP | DOWN;
        return makeMovementFlags(marcacaoArraste, marcacaoDeslize);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        new NotaDAO().troca(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        adapter.troca(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int posicao = viewHolder.getAdapterPosition();
        new NotaDAO().remove(posicao);
        adapter.remove(posicao);
    }
}
