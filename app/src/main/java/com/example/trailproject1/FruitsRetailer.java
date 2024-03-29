package com.example.trailproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class FruitsRetailer extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    TextView applequantity,orangequantity;
    int countApple,countOrange = 0;
    Button RetailerAppleSelectBtn,RetailerOrangeSelectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits_retailer);
        applequantity=(TextView)findViewById(R.id.RetailerAppleQuantityValue);
        orangequantity = (TextView) findViewById(R.id.RetailerOrangeQuantityValue);


        RetailerAppleSelectBtn=(Button)findViewById(R.id.RetailerAppleQauntitySelectButton);
        RetailerOrangeSelectBtn=(Button)findViewById(R.id.RetailerOrangeQauntitySelectButton);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        RetailerAppleSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference docRef=fStore.collection("users").document(fAuth.getCurrentUser().getUid());
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists())
                        {
                            try
                            {
                                String QuantityOfApple=applequantity.getText().toString();
                                docRef.update("appleQuantity",String.valueOf(QuantityOfApple));

                            }
                            catch (Exception e)
                            {
                                String str = "hello "+e.toString();
                                Toast.makeText(FruitsRetailer.this, str , Toast.LENGTH_SHORT).show();
                            }


                        }
                    };
                });
                Intent intent=new Intent(FruitsRetailer.this,RetailerFruitAppleAddToCartPage.class);
                startActivity(intent);
            }
        });
        RetailerOrangeSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference docRef=fStore.collection("users").document(fAuth.getCurrentUser().getUid());
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists())
                        {
                            try
                            {
                                String QuantityOfApple=applequantity.getText().toString();
                                docRef.update("appleQuantity",String.valueOf(QuantityOfApple));
                            }
                            catch (Exception e)
                            {
                                String str = "hello "+e.toString();
                                Toast.makeText(FruitsRetailer.this, str , Toast.LENGTH_SHORT).show();
                            }


                        }
                    };
                });
                Intent intent=new Intent(FruitsRetailer.this,RetailerFruitOrangeAddToCartPage.class);
                startActivity(intent);

            }
        });

      /*  RetailerAppleSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FruitsRetailer.this,RetailerFruitAppleAddToCartPage.class);
                startActivity(intent);
            }
        });*/
        /*RetailerOrangeSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FruitsRetailer.this,RetailerFruitOrangeAddToCartPage.class);
                startActivity(intent);
            }
        });*/
    }

    public void incrementapple(View v)
    {
        countApple++;
        applequantity.setText("" + countApple);
    }
    public void decrementapple(View v)
    {
        if(countApple<=0)
            countApple = 0;
        else
            countApple--;
        applequantity.setText("" + countApple);
    }
    public void incrementorange(View v)
    {
        countOrange++;
        orangequantity.setText("" + countOrange);
    }
    public void decrementorange(View v)
    {
        if(countOrange<=0)
            countOrange = 0;
        else
            countOrange--;
        orangequantity.setText("" + countOrange);
    }
}