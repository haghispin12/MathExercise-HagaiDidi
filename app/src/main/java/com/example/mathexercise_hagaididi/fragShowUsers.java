package com.example.mathexercise_hagaididi;

import static android.app.Activity.RESULT_OK;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class fragShowUsers extends Fragment {
    private View v;
    private MainViewModel viewModelMain;
    private TextView username;
    private TextView score;
    private Button addPic;
    private Button addUser;
    private ImageView pic;
    private Uri uri;
    private RecyclerView rcShowUsers;
    private MyUsersAdapter usersAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v=inflater.inflate(R.layout.fragment_frag_show_users, container, false);
        activateViewModel();
        initViews(v);
        activity();
        setUserDataInFrag();
        initUsersAdapter();
         return v;
    }
    public void activateViewModel(){
        viewModelMain = new ViewModelProvider(getActivity()).get(MainViewModel.class);

    }
    public void initViews(View v){
        rcShowUsers =v.findViewById(R.id.rcShowUsers);
        username = v.findViewById(R.id.fragUser);
        score = v.findViewById(R.id.fragScore);
        addPic = v.findViewById(R.id.fragAddPicture);
        addUser = v.findViewById(R.id.fragAddUser);
        pic = v.findViewById(R.id.fragPicture);
    }
    public void setUserDataInFrag(){
        username.setText(viewModelMain.getUsername()+"");
        score.setText(viewModelMain.getScore()+"");
    }
    public void activity(){
        addPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "New Picture");
                values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");
                uri = requireContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startCamera.launch(cameraIntent);
            }
        });
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModelMain.vinsert(requireActivity());
                username.setText(viewModelMain.getId()+"");
            }
        });
    }
    ActivityResultLauncher<Intent> startCamera = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        pic.setImageURI(uri);
                        viewModelMain.seturi(uri);
                    }
                }
            });
    public void initUsersAdapter(){
        usersAdapter = new MyUsersAdapter(viewModelMain.selectAll(requireActivity()), new MyUsersAdapter.OnItemClickListener() {
            @Override
            public void onitemClick(User item) {

            }
        });
        rcShowUsers.setLayoutManager(new LinearLayoutManager(requireActivity()));
        rcShowUsers.setAdapter(usersAdapter);
        rcShowUsers.setHasFixedSize(true);
    }

}