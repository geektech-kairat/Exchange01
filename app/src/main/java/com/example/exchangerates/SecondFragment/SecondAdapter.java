package com.example.exchangerates.SecondFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exchangerates.R;

import java.util.ArrayList;
import java.util.List;

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.SecondViewHolder> {
    private List<ListItemModel> list = new ArrayList<>();

    public SecondAdapter(List<ListItemModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public SecondViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list2_item, parent, false);
        return new SecondViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SecondViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SecondViewHolder extends RecyclerView.ViewHolder {
        private TextView date;
        private TextView course;
        private TextView result;
        private TextView change;
        private TextView parcent;

        public SecondViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            course = itemView.findViewById(R.id.course);
            result = itemView.findViewById(R.id.result);
            change = itemView.findViewById(R.id.changes);
            parcent = itemView.findViewById(R.id.parcent);
        }


        public void onBind(ListItemModel listItemModel) {
            date.setText(listItemModel.getDate());
            course.setText(listItemModel.getCourse());
            result.setText(listItemModel.getResult());
            change.setText(listItemModel.getChanges());
            parcent.setText(listItemModel.getPercent());

        }
    }
}
