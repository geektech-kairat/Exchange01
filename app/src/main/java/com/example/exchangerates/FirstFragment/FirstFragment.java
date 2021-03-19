package com.example.exchangerates.FirstFragment;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exchangerates.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FirstFragment extends Fragment {

    private Document doc;
    private List<ListItemModel> list = new ArrayList<>();
    private FirstAdapter firstAdapter;
    private RecyclerView rv;

    private Thread myThread2;

    private TextView titleForRv;

    private String titleForRvTxt;
    private Runnable runnable;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_firstk, container, false);

        titleForRv = view.findViewById(R.id.titleForRv);
        firstAdapter = new FirstAdapter(list);
        rv = view.findViewById(R.id.rv);
        rv.setAdapter(firstAdapter);
        threadForGetData();
        return view;
    }


    public void getData() {
        try {
            doc = Jsoup.connect("https://www.akchabar.kg/ru/exchange-rates/dollar/").get();
            Elements upDateInfo = doc.getElementsByClass("date_big ffo");
            titleForRvTxt = upDateInfo.get(0).text();

            Elements table = doc.getElementsByTag("tbody");
            Element our_table = table.get(0);
//            Log.e("TAG", "getData: " + our_table.child(0).text() );
            for (int i = 0; i < our_table.childrenSize(); i++) {
                ListItemModel items = new ListItemModel();
                Log.e("TAG", "getDate: " + our_table.children().get(1).text());

                items.setNameBank(our_table.children().get(i).child(0).text());
                items.setBye(our_table.children().get(i).child(1).text());
                items.setSale(our_table.children().get(i).child(2).text());
                list.add(items);
            }
            refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void refresh() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                titleForRv.setText(titleForRvTxt);
                firstAdapter.notifyDataSetChanged();
            }
        });
    }

    public void threadForGetData() {
        runnable = this::getData;
        myThread2 = new Thread(runnable);
        myThread2.start();

    }


}