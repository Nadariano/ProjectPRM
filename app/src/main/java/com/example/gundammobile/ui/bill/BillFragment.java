package com.example.gundammobile.ui.bill;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gundammobile.OrderDetailsAdapter;
import com.example.gundammobile.R;
import com.example.gundammobile.databinding.FragmentBillBinding;
import com.example.gundammobile.databinding.FragmentHomeBinding;
import com.example.gundammobile.model.OrderDetails;
import com.example.gundammobile.ui.home.HomeViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BillFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BillFragment extends Fragment {
    private FragmentBillBinding binding;
    ArrayList<OrderDetails> itemList;
    RecyclerView billItemList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BillFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BillFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BillFragment newInstance(String param1, String param2) {
        BillFragment fragment = new BillFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBillBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        billItemList = binding.billItemList;

        itemList = new ArrayList<>();
        itemList.add(new OrderDetails(1,
                "1",
                "HG RX-78-2 Fighter Beyond Global ",
                "https://firebasestorage.googleapis.com/v0/b/prmgundamshop.appspot.com/o/HG001.jpg?alt=media&token=a1bba414-f632-45a1-8cb9-96835e6b8f42",
                5,
                420));
        itemList.add(new OrderDetails(1,
                "2",
                "HG 1/144 MS-06R Zaku II High Mobility Type “Psycho Zaku” (Thunderbolt Ver.) ",
                "https://firebasestorage.googleapis.com/v0/b/prmgundamshop.appspot.com/o/HG002.jpg?alt=media&token=9f8b0a5e-e2ef-43c9-803f-8796949a263c",
                3,
                599));
        itemList.add(new OrderDetails(1,
                "1",
                "HG RX-78-2 Fighter Beyond Global ",
                "https://firebasestorage.googleapis.com/v0/b/prmgundamshop.appspot.com/o/HG001.jpg?alt=media&token=a1bba414-f632-45a1-8cb9-96835e6b8f42",
                5,
                420));
        itemList.add(new OrderDetails(1,
                "2",
                "HG 1/144 MS-06R Zaku II High Mobility Type “Psycho Zaku” (Thunderbolt Ver.) ",
                "https://firebasestorage.googleapis.com/v0/b/prmgundamshop.appspot.com/o/HG002.jpg?alt=media&token=9f8b0a5e-e2ef-43c9-803f-8796949a263c",
                3,
                599));itemList.add(new OrderDetails(1,
                "1",
                "HG RX-78-2 Fighter Beyond Global ",
                "https://firebasestorage.googleapis.com/v0/b/prmgundamshop.appspot.com/o/HG001.jpg?alt=media&token=a1bba414-f632-45a1-8cb9-96835e6b8f42",
                5,
                420));
        itemList.add(new OrderDetails(1,
                "2",
                "HG 1/144 MS-06R Zaku II High Mobility Type “Psycho Zaku” (Thunderbolt Ver.) ",
                "https://firebasestorage.googleapis.com/v0/b/prmgundamshop.appspot.com/o/HG002.jpg?alt=media&token=9f8b0a5e-e2ef-43c9-803f-8796949a263c",
                3,
                599));itemList.add(new OrderDetails(1,
                "1",
                "HG RX-78-2 Fighter Beyond Global ",
                "https://firebasestorage.googleapis.com/v0/b/prmgundamshop.appspot.com/o/HG001.jpg?alt=media&token=a1bba414-f632-45a1-8cb9-96835e6b8f42",
                5,
                420));
        itemList.add(new OrderDetails(1,
                "2",
                "HG 1/144 MS-06R Zaku II High Mobility Type “Psycho Zaku” (Thunderbolt Ver.) ",
                "https://firebasestorage.googleapis.com/v0/b/prmgundamshop.appspot.com/o/HG002.jpg?alt=media&token=9f8b0a5e-e2ef-43c9-803f-8796949a263c",
                3,
                599));

        OrderDetailsAdapter orderDetailsAdapter = new OrderDetailsAdapter(itemList);

        billItemList.setAdapter(orderDetailsAdapter);

        billItemList.setLayoutManager(new LinearLayoutManager(requireContext()));
        return root;
    }
}