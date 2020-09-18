package com.example.save_me.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.save_me.R;
import com.example.save_me.common.BaseAdapter;
import com.example.save_me.databinding.FragmentHomeBinding;
import com.example.save_me.model.Note;
import com.example.save_me.viewmodel.NoteViewmodel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    FragmentHomeBinding binding;
    NoteViewmodel noteViewmodel;
    NavController navController;
    BaseAdapter<Note> adapter;
    //    NoteAdapter adapter;
    ArrayList<Note> noteList = new ArrayList<>();
    Note note = new Note();


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        noteViewmodel = new ViewModelProvider(getActivity()).get(NoteViewmodel.class);
        navController = Navigation.findNavController(view);

        binding.saveNote.setOnClickListener(view1 -> {
            HomeFragmentDirections.ActionHomeFragmentToSaveFragment action = HomeFragmentDirections.actionHomeFragmentToSaveFragment(note);
            navController.navigate(action);
//            navController.navigate(R.id.action_homeFragment_to_save_Fragment);
        });

        NoteAdapterSetup();
        adapter.setActionListener(position -> {
            note = adapter.getItemAtPosition(position);
            HomeFragmentDirections.ActionHomeFragmentToSaveFragment action = HomeFragmentDirections.actionHomeFragmentToSaveFragment(note);
            navController.navigate(action);

            Log.e(TAG, "onClickCreated: " + adapter.getItemAtPosition(position));
        });

    }

    private void NoteAdapterSetup() {
        adapter = new BaseAdapter<>(R.layout.single_layout);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerview.setAdapter(adapter);
        setData();
    }


    private void setData() {
        noteViewmodel.getAllNotes().observe(getViewLifecycleOwner(), notes -> {
            Log.e(TAG, "setData: " + notes.size());
            adapter.setList(notes);
        });
    }
}