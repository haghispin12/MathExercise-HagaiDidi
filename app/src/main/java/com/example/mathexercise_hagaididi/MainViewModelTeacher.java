package com.example.mathexercise_hagaididi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainViewModelTeacher extends ViewModel {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private  teacher currentTeacher;
    MutableLiveData<Integer> isExist =new MutableLiveData<Integer>();
    MutableLiveData<ArrayList<student>> LiveStudents = new MutableLiveData<>();

    public void editProfile(teacher teacher){
        db.collection("teachers profiles").document().set(teacher).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
            }
        });
    }
    public void getStudents() {
        db.collection("connections").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    ArrayList<student> tempStudents;
                    if (documentSnapshot.exists()) {

                    }
                }
            }

        });
    }
    public void connectionsListener(){
        db.collection("connections").whereEqualTo("EmailTeacher",GetCurrentEmail()).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()){
                    switch (dc.getType()){
                        case ADDED:

                            break;
                        case MODIFIED:
                            break;
                        case REMOVED:
                            break;
                    }
                }
            }
        });
    }

    public void isProfileExist(){

        db.collection("teachers profiles").whereEqualTo("email",GetCurrentEmail()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    if (documentSnapshot.exists()) {
                        String location = documentSnapshot.getString("location");
                        String phoneNumber = documentSnapshot.getString("phoneNumber");
                        String name = documentSnapshot.getString("name");
                        int price = Integer.valueOf(Math.toIntExact(documentSnapshot.getLong("price")));
                        currentTeacher = new teacher(phoneNumber, price,location,name,GetCurrentEmail());
                        isExist.setValue(1);
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                isExist.setValue(2);
            }
        });

    }
    public String  GetCurrentEmail(){
        return auth.getCurrentUser().getEmail();
    }
    public teacher getCurrentTeacher(){
        return currentTeacher;
    }
}
