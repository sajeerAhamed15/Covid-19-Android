package com.example.acmcovidapplication.db;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class FirebaseHelper {
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    //add this when detected
    public void update(final DeviceModel deviceModel, final DatabaseHelper databaseHelper) {

        //timestamp format --> "2020-05-18 07:42:24"
        String uid = firebaseUser.getUid();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = dateFormat.parse(deviceModel.getTimeStamp());
//            System.out.println(date);
            Map<String, Object> data = new HashMap<>();
            data.put("timestamp", date);

            db.collection("users")
                    .document(uid)
                    .collection(deviceModel.getUserID())
                    .document()
                    .set(data)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            databaseHelper.delete(deviceModel.getID());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            System.out.println("fail");
                        }
                    });
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    // add this when creating a new user
    public void onCreteUser(String id){
        Map<String, Object> data = new HashMap<>();
        data.put("bluetooth id",id);

        db.collection("users")
                .document(firebaseUser.getUid())
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Success");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("fail");
                    }
                });
    }


}
