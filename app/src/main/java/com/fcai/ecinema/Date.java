package com.fcai.ecinema;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Date extends AppCompatActivity implements View.OnClickListener {
    ViewGroup layout;
    TextView t;
    Button b;
    DatabaseReference mDatabase;
    public  static  String date;
    RadioButton rd1,rd2,rd3,rd4,rd5,rd6,rd7,rd8;
    public  static  String  time;
    ArrayList<Integer> res= new ArrayList<>();
    public static String seats ;
    List<TextView> seatViewList = new ArrayList<>();
    int seatSize = 100;
    int seatGaping = 10;
    int STATUS_AVAILABLE = 1;
    int STATUS_BOOKED = 2;
    int STATUS_RESERVED = 3;
    String selectedIds = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        t=findViewById(R.id.Selected_days_text);
        layout = findViewById(R.id.layoutSeat);
        LocalDate localDate = LocalDate.now();
    b= findViewById(R.id.che);
    b.setClickable(false);
        String s = localDate.toString();
        String[] parts = s.split("-");
        LocalDate localDate1;
        localDate1=LocalDate.of(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),Integer.parseInt(parts[2]));
        rd1=(RadioButton) findViewById(R.id.radioButton2wel);
        rd2=(RadioButton) findViewById(R.id.radioButtontany);
        rd3=(RadioButton) findViewById(R.id.radioButtontalet);
        rd4=(RadioButton) findViewById(R.id.radioButtonrab3);
        rd5=(RadioButton) findViewById(R.id.radioButton5ames);
        rd6=(RadioButton) findViewById(R.id.radioButtonsads);
        rd7=(RadioButton) findViewById(R.id.radioButtonsab3);
        rd8=(RadioButton) findViewById(R.id.radioButtontamn);
        rd6.setClickable(false);
        rd7.setClickable(false);
        rd8.setClickable(false);
        rd1.setText(localDate1.format(DateTimeFormatter.ofPattern("d-MMM")).toString());
        rd2.setText(localDate1.plusDays(1).format(DateTimeFormatter.ofPattern("d-MMM")).toString());
        rd3.setText(localDate1.plusDays(2).format(DateTimeFormatter.ofPattern("d-MMM")).toString());
        rd4.setText(localDate1.plusDays(3).format(DateTimeFormatter.ofPattern("d-MMM")).toString());
        rd5.setText(localDate1.plusDays(4).format(DateTimeFormatter.ofPattern("d-MMM")).toString());

    }
    public  void checkout(View v)
    {
        int counter=0;
        for(int i=0;i<seats.length();i++)
        {
            if(seats.charAt(i)=='U')
            {
                counter++;
            }
        }
        if(counter!=0)
        {
        startActivity(new Intent(getApplicationContext(), Pay.class).putExtra("seats",seats));
        }
        else
        {
            Toast.makeText(this, "you didn't choose a seat to check out", Toast.LENGTH_SHORT).show();
        }
    }
    public static String day ;
    ArrayList<String> times=new ArrayList<>() ;
    ArrayList<String> seatsarr=new ArrayList<>() ;
    public void rbclick(View v)
    {
        if(rd1.isChecked())
        {
            day = "day 0";
            date=rd1.getText().toString();
        }
        if(rd2.isChecked())
        {
            day = "day 1";
            date=rd2.getText().toString();


        }
        if(rd3.isChecked())
        {
            day = "day 2";
            date=rd3.getText().toString();


        }
        if(rd4.isChecked())
        {
            day = "day 3";
            date=rd4.getText().toString();


        }
        if(rd5.isChecked())
        {
            day = "day 4";
            date=rd5.getText().toString();

        }
        mDatabase = FirebaseDatabase.getInstance("https://wireless-cinema-e0d2b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("date&time");

        // add a ValueEventListener to the reference
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot snapshot = dataSnapshot.child(Movie.movname).child(day);
                times.clear();
                seatsarr.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    times.add(snapshot1.getKey());
                    seatsarr.add(snapshot1.getValue(String.class));
                }
                rd6.setText(times.get(0));
                rd7.setText(times.get(1));
                rd8.setText(times.get(2));
                rd6.setChecked(false);
                rd7.setChecked(false);
                rd8.setChecked(false);
                rd6.setClickable(true);
                rd7.setClickable(true);
                rd8.setClickable(true);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // handle anyo9n errors
                Toast.makeText(Date.this, "Error retrieving data from Firebase Database", Toast.LENGTH_SHORT).show();
            }
        });
    }
public void choose(View v)
{
    if(rd6.isChecked())
    {
     seats= seatsarr.get(0);
     time=rd6.getText().toString();
    }
    if(rd7.isChecked())
    {
        seats= seatsarr.get(1);
        time=rd7.getText().toString();
    }
    if(rd8.isChecked())
    {
        seats= seatsarr.get(2);
        time=rd8.getText().toString();
    }
    seats=showw(seats);
    b.setClickable(true);
}
private String showw(String seats)
{
    layout.removeAllViews();
    //seats = "/" + seats;
    seats=seats.replace("/","");
    LinearLayout layoutSeat = new LinearLayout(this);
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    layoutSeat.setOrientation(LinearLayout.VERTICAL);
    layoutSeat.setLayoutParams(params);
    layoutSeat.setPadding(8 * seatGaping, 8 * seatGaping, 8 * seatGaping, 8 * seatGaping);
    //

    layout.addView(layoutSeat);
    LinearLayout layout = null;
    int count = 0;

    for (int index = 0; index < seats.length(); index++) {
        if (index%8==0) {
            layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layoutSeat.addView(layout);
        }
        if (seats.charAt(index) == 'A') {
            count++;
            TextView view = new TextView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
            layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
            view.setLayoutParams(layoutParams);
            view.setPadding(0, 0, 0, 2 * seatGaping);
            view.setId(count);
            view.setGravity(Gravity.CENTER);
            view.setBackgroundResource(R.drawable.ic_seats_book);
            view.setText(count + "");
            view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
            view.setTextColor(Color.BLACK);
            view.setTag(STATUS_AVAILABLE);
            layout.addView(view);
            seatViewList.add(view);
            view.setOnClickListener(this);
        } else if (seats.charAt(index) == 'R') {
            count++;
            TextView view = new TextView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
            layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
            view.setLayoutParams(layoutParams);
            view.setPadding(0, 0, 0, 2 * seatGaping);
            view.setId(count);
            view.setGravity(Gravity.CENTER);
            view.setBackgroundResource(R.drawable.ic_seats_reserved);
            view.setText(count + "");
            view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
            view.setTextColor(Color.WHITE);
            view.setTag(STATUS_RESERVED);
            layout.addView(view);
            seatViewList.add(view);
            view.setOnClickListener(this);}
    }
    return seats;
}

    @Override
    public void onClick(View view) {
        if ((int) view.getTag() == STATUS_AVAILABLE) {
            if (selectedIds.contains(view.getId() + ",")) {
                StringBuilder string = new StringBuilder(seats);
                selectedIds = selectedIds.replace(+view.getId() + ",", "");
                int place = view.getId();
                string.setCharAt(place-1, 'A');
                seats=string.toString();
                view.setBackgroundResource(R.drawable.ic_seats_book);
            } else {
                selectedIds = selectedIds + view.getId() + ",";
                view.setBackgroundResource(R.drawable.ic_seats_selected);
                StringBuilder string = new StringBuilder(seats);
                int place = view.getId();
                string.setCharAt(place-1, 'U');
                seats=string.toString();

            }
        } else if ((int) view.getTag() == STATUS_BOOKED) {
            Toast.makeText(this, "Seat " + view.getId() + " is Booked", Toast.LENGTH_SHORT).show();
        } else if ((int) view.getTag() == STATUS_RESERVED) {
            Toast.makeText(this, "Seat " + view.getId() + " is Reserved", Toast.LENGTH_SHORT).show();
        }
    }
}
