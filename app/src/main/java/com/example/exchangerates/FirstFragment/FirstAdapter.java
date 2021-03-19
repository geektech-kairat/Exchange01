package com.example.exchangerates.FirstFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exchangerates.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.FirstViewHolder> {
    private List<ListItemModel> list = new ArrayList<>();

    public FirstAdapter(List<ListItemModel> list) { {
        this.list = list;
    }
    }

    @NonNull
    @Override
    public FirstViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new FirstViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FirstViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void sortListBank(){
        Collections.sort(list, (o1, o2) -> o1.getNameBank().compareTo(o2.getNameBank()));
        notifyDataSetChanged();
    }
    public void reverseListBank(){
        Collections.reverse(list);
        notifyDataSetChanged();

    }
    public void sortListBye(){
        Collections.sort(list, (o1, o2) -> o1.getBye().compareTo(o2.getBye()));
    }
    public void deleteList(){
        list.removeAll(list);
        notifyDataSetChanged();
    }


    class FirstViewHolder extends RecyclerView.ViewHolder {

        private TextView nameBank;
        private TextView bye;
        private TextView sale;


        public FirstViewHolder(@NonNull View itemView) {
            super(itemView);
            nameBank = itemView.findViewById(R.id.nameBank);
            bye = itemView.findViewById(R.id.bye);
            sale = itemView.findViewById(R.id.sale);
        }


        public void onBind(ListItemModel listItemModel) {
            nameBank.setText(listItemModel.getNameBank());
            bye.setText(listItemModel.getBye());
            sale.setText(listItemModel.getSale());
        }
    }
}
