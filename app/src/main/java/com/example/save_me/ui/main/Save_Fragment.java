package com.example.save_me.ui.main;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.save_me.R;
import com.example.save_me.databinding.FragmentHomeBinding;
import com.example.save_me.databinding.FragmentSaveBinding;
import com.example.save_me.model.Note;
import com.example.save_me.viewmodel.NoteViewmodel;


public class Save_Fragment extends Fragment {


    FragmentSaveBinding binding;
    NoteViewmodel noteViewmodel;
    NavController navController;
    public static final String TAG = "Save_Fragment";
    Save_FragmentArgs args;

    public Save_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSaveBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        noteViewmodel = new ViewModelProvider(getActivity()).get(NoteViewmodel.class);
        navController = Navigation.findNavController(view);

        args = Save_FragmentArgs.fromBundle(getArguments());
        Note notes = args.getNote();
        if (notes != null) {
            Log.e(TAG, "Note is Not Null" + notes.toString());
            binding.title.setText(notes.getTitle());
            binding.description.setText(notes.getDescription());
        }
        getArguments().clear();

        if (notes.getId() != 0 && notes.getTitle() != null){
            binding.saveFragmentTitle.setText("Update Your Note");
        }else{
            binding.saveFragmentTitle.setText("Set Your Note");
        }


        binding.cancelButton.setOnClickListener(view1 -> {
            navController.navigate(R.id.action_save_Fragment_to_homeFragment);
        });


        binding.saveNote.setOnClickListener(view1 -> {
            Log.e(TAG, "onViewCreated: " + binding.description.getText().toString());
            Log.e(TAG, "NoteSaveLog: " + notes.getId());


            if (binding.title.getText().length() > 0 && binding.description.getText().length() > 0) {
                Note note = new Note(binding.title.getText().toString(), binding.description.getText().toString());
                if (notes.getId() != 0) {
                    note.setId(notes.getId());
                    noteViewmodel.update(note);
                    Log.e(TAG, "NoteSaveLog: " + note.toString());
                } else {
                    noteViewmodel.insert(note);
                }

                navController.navigate(R.id.action_save_Fragment_to_homeFragment);
                Log.e(TAG, "onViewCreated: " + binding.description.getText().toString());
            } else {

                if (binding.title.getText().toString().length() <= 0) {
                    binding.title.setError("pease Enter Title");
                } else if (binding.description.getText().toString().length() <= 0) {
                    binding.description.setError("pease Enter Description.");
                }
                Toast.makeText(getContext(), "You Need To Enter All Feilds.", Toast.LENGTH_SHORT).show();

            }


        });


    }
}