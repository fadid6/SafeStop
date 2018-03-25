package vt.cs.fadid6.safestop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;
//import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private boolean notified = false;
    private String notification = "Nothing";
    private TextView notificationText;
    private TextView sentText;
    private Button sendButton;
    private Button okButton;
    private Button clientidbutton;
    private EditText clientidedittext;
    private TextView clientidtextview;

    final int clientID = 1178;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.gray_circle).setVisibility(View.VISIBLE);
        findViewById(R.id.green_circle).setVisibility(View.GONE);

        notificationText = findViewById(R.id.notification_text);
        clientidedittext = findViewById(R.id.input1);
        clientidtextview = findViewById(R.id.clientid_text);
        sentText = findViewById(R.id.sent_text);
        sendButton = findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sentText.setVisibility(View.VISIBLE);
                notified = true;
                populateTable();
                //createUser(.01);
            }
        });

        dbRef.child("Users").child("" + clientID).child("Message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String msg = "" + dataSnapshot.getValue();
                if (!msg.equals("")) {
                    notificationText.setText("" + dataSnapshot.getValue());
                    findViewById(R.id.gray_circle).setVisibility(View.GONE);
                    findViewById(R.id.green_circle).setVisibility(View.VISIBLE);
                } else {
                    notificationText.setText("" + dataSnapshot.getValue());
                    findViewById(R.id.gray_circle).setVisibility(View.VISIBLE);
                    findViewById(R.id.green_circle).setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        okButton = findViewById(R.id.ok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendButton.setVisibility(View.VISIBLE);
                sentText.setVisibility(View.VISIBLE);

                okButton.setVisibility(View.GONE);
                notificationText.setVisibility(View.GONE);
            }
        });

        clientidbutton = findViewById(R.id.clientid_button);
        clientidbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clientidedittext.setVisibility(View.GONE);
                clientidtextview.setText("" + clientidtextview.getText() + clientidedittext.getText());

                clientidbutton.setVisibility(View.GONE);
                sendButton.setVisibility(View.VISIBLE);
            }
        });


        ImageButton notificationButton = findViewById(R.id.notification_button);
        notificationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (notified) {
                    sentText.setVisibility(View.GONE);
                    sendButton.setVisibility(View.GONE);
                    notificationText.setVisibility(View.VISIBLE);
                    okButton.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void populateTable() {
        create("1-10-97", "Cody Bolling", 1523, "B17899",
                "Warrant Out, Expired License", 3.58, true, "");
        create("8-22-87", "Ethel Washington", 846, "C432122",
                "Nothing", 16.52, true, "");
        create("3-17-99", "Kyle Davis", 6498, "D456123",
                "Nothing", 2.64, true, "");
        create("5-08-82", "Fadi Durah", 1178, "F128462",
                "Nothing", 0.01, true, "");
        create("9-31-88", "Reddard Taurus", 1672, "L45329",
                "Expired License", 8.49, true, "");
    }

    public void create(String dob, String name, int clientid, String licenseid,
                       String details, double proximity, boolean active, String message){
        dbRef.child("Users").child("" + clientid).child("DOB").setValue(dob);
        dbRef.child("Users").child("" + clientid).child("Name").setValue(name);
        dbRef.child("Users").child("" + clientid).child("ClientID").setValue(clientid);
        dbRef.child("Users").child("" + clientid).child("LicenseID").setValue(licenseid);
        dbRef.child("Users").child("" + clientid).child("Details").setValue(details);
        dbRef.child("Users").child("" + clientid).child("Proximity").setValue(proximity);
        dbRef.child("Users").child("" + clientid).child("Active").setValue(active);
        dbRef.child("Users").child("" + clientid).child("Message").setValue(message);

    }

    public void createUser(double proximity) {
        dbRef.child("Users").child("" + clientID).child("Active").setValue(true);
        dbRef.child("Users").child("" + clientID).child("Proximity").setValue(proximity);
    }

}
