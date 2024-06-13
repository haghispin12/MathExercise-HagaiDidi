package com.example.mathexercise_hagaididi;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
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
    private student tempStudent;
    MutableLiveData<Integer> isExist =new MutableLiveData<Integer>();
    MutableLiveData<ArrayList<student>> LiveStudents = new MutableLiveData<>();
    MutableLiveData<ArrayList<lesson>> LiveLessons = new MutableLiveData<>();

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
    public void lessonsListener(){
        ArrayList<lesson> temp = new ArrayList<>();
        db.collection("lessons").whereEqualTo("teacherEmail",GetCurrentEmail()).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentChange dc : value.getDocumentChanges()){
                    switch (dc.getType()){
                        case ADDED:
                            String Date = dc.getDocument().getString("date");
                            String hour = dc.getDocument().getString("hour");
                            String teacherEmail = dc.getDocument().getString("teacherEmail");
                            String studentEmail = dc.getDocument().getString("studentEmail");
                            lesson temL = new lesson(Date,hour,teacherEmail,studentEmail);
                            temp.add(temL);
                            break;
                        case MODIFIED:
                            String Date1 = dc.getDocument().getString("date");
                            String hour1 = dc.getDocument().getString("hour");
                            String teacherEmail1 = dc.getDocument().getString("teacherEmail");
                            String studentEmail1 = dc.getDocument().getString("studentEmail");
                            lesson temL1 = new lesson(Date1,hour1,teacherEmail1,studentEmail1);
                            temp.add(temL1);
                            break;
                        case REMOVED:
                            String Date2 = dc.getDocument().getString("date");
                            String hour2 = dc.getDocument().getString("hour");
                            String teacherEmail2 = dc.getDocument().getString("teacherEmail");
                            String studentEmail2 = dc.getDocument().getString("studentEmail");
                            lesson temL2 = new lesson(Date2,hour2,teacherEmail2,studentEmail2);
                            temp.add(temL2);
                            break;
                    }
                }
                LiveLessons.setValue(temp);
            }
        });
    }
    public void connectionsListener(){
        ArrayList<student> temp = new ArrayList<>();
        db.collection("connections").whereEqualTo("emailTeacher",GetCurrentEmail()).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()){
                    switch (dc.getType()){
                        case ADDED:
                            String emailStudent = dc.getDocument().getString("emailStudent");
                            String Status = dc.getDocument().getString("status");
                            student temS = new student(emailStudent,Status);
                            temp.add(temS);
                            break;
                        case MODIFIED:
                            String emailStudent1 = dc.getDocument().getString("emailStudent");
                            String Status1 = dc.getDocument().getString("status");
                            student temS1 = new student(emailStudent1,Status1);
                            temp.add(temS1);
                            break;
                        case REMOVED:
                            String emailStudent2 = dc.getDocument().getString("emailStudent");
                            String Status2 = dc.getDocument().getString("status");
                            student temS2 = new student(emailStudent2,Status2);
                            temp.add(temS2);
                            break;
                    }
                }
                LiveStudents.setValue(temp);
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


    public void changeStatus(student student,String status ){
        CollectionReference update = db.collection("connections");
        update.whereEqualTo("emailStudent",student.getEmail()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot dc: queryDocumentSnapshots){
                    String dcId = dc.getId();
                    update.document(dcId).update("status",status);
                }
            }
        });
    }
    public void addLesson(lesson lesson){
        db.collection("lessons").document().set(lesson).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
            }
        });

    }
}
