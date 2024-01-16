package com.example.mathexercise_hagaididi;

import static android.app.Activity.RESULT_OK;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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
    private MenuItem  itemDelete;
    private MenuItem  itemEdit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v=inflater.inflate(R.layout.fragment_frag_show_users, container, false);
        initViews(v);
         activateViewModel();
        activity();
        setUserDataInFrag();
        viewModelMain.selectAll(requireActivity());

         return v;
    }
    public void activateViewModel(){
        viewModelMain = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        viewModelMain.users.observe(requireActivity(), new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                if (users!=null && users.size()>0) {
                    initUsersAdapter(users);
                }
            }
        });

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
        username.setText(viewModelMain.getUsername()+"  ");
        score.setText(",score:"+viewModelMain.getScore()+"");

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
                if (viewModelMain.checkIfNotExists(viewModelMain.getUsername())) {
                    viewModelMain.vinsert(requireActivity());
                }
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
    public void initUsersAdapter(ArrayList<User> users){
        usersAdapter = new MyUsersAdapter(users, new MyUsersAdapter.OnItemClickListener() {
            @Override
            public void onitemClick(User item) {
                itemDelete.setVisible(true);
                itemEdit.setVisible(true);


            }
        });
        rcShowUsers.setLayoutManager(new LinearLayoutManager(requireActivity()));
        rcShowUsers.setAdapter(usersAdapter);
        rcShowUsers.setHasFixedSize(true);
    }

    public void  onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.main_menu,menu);
        itemDelete = menu.findItem(R.id.delete);
        itemDelete.setVisible(false);
        itemEdit = menu.findItem(R.id.edit);
        itemEdit.setVisible(false);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.delete:

                return  true;
            case R.id.edit:
                return true;
        }
        return false;
    }
}