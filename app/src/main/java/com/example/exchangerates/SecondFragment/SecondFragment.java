package com.example.exchangerates.SecondFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.exchangerates.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {
    private Document doc;

    private SecondAdapter secondAdapter;
    private RecyclerView rv;

    private Thread myThread2;
    private Runnable runnable;

    private List<ListItemModel> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        rv =view.findViewById(R.id.rv2);
        secondAdapter = new SecondAdapter(list);
        rv.setAdapter(secondAdapter);
        threadForGetData();


        return view;
    }
    public void getData(){
        try {
            doc = Jsoup.connect("https://finance.rambler.ru/calculators/converter/1-USD-KGS/").get();
            Elements table = doc.getElementsByClass("converter-change-table");
            Element ourTable =table.get(0);
//            Log.e("TAG", "getDate: " + ourTable.children().get(1).child(2).text() );
            for (int i = 1; i < 5; i++) {

                ListItemModel listItemModel = new ListItemModel();

                listItemModel.setDate(ourTable.children().get(i).child(0).text());
                listItemModel.setCourse(ourTable.children().get(i).child(1).text());
                listItemModel.setResult(ourTable.children().get(i).child(2).text());
                listItemModel.setChanges(ourTable.children().get(i).child(3).text());
                listItemModel.setPercent(ourTable.children().get(i).child(4).text());

                list.add(listItemModel);
            }
            refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void threadForGetData() {
        runnable = this::getData;
        myThread2 = new Thread(runnable);
        myThread2.start();

    }
    public void refresh() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                secondAdapter.notifyDataSetChanged();
            }
        });
    }
}