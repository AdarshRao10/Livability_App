package com.mylivability.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

//import com.github.mikephil.charting.charts.BarChart;
//import com.github.mikephil.charting.components.XAxis;
//import com.github.mikephil.charting.data.BarData;
//import com.github.mikephil.charting.data.BarDataSet;
//import com.github.mikephil.charting.data.BarEntry;
//import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
//import com.github.mikephil.charting.utils.ColorTemplate;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarchartActivity extends AppCompatActivity {

    // variable for our bar chart
    BarChart barChart;

    // variable for our bar data.
    BarData barData;

    // variable for our bar data set.
    BarDataSet barDataSet;

    // array list for storing entries.
    ArrayList barEntriesArrayList;

    double arr[]= new double[20];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchart);

        Bundle extras = getIntent().getExtras();
        arr = extras.getDoubleArray("array");

        Toast.makeText(getApplicationContext(), "arrayB"+arr[0], Toast.LENGTH_SHORT).show();




        barChart = findViewById(R.id.bargraph);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        // calling method to get bar entries.
        getBarEntries();

        // creating a new bar data set.
        barDataSet = new BarDataSet(barEntriesArrayList, "Barchart Livability");

        // creating a new bar data and
        // passing our bar data set.
        barData = new BarData(barDataSet);

        // below line is to set data
        // to our bar chart.
        barChart.setData(barData);



        final String[] labels = new String[] {"","Water", "Electricity", "Sanitation", "Public Health System", "Private Health System", "Housing",
                "Public Mass Transport", "Private Mass Transport","Public Education System", "Private Education System", "Safety and Security", "Employment and Opportunity", "Public space", "Community Life","Liesure and Recreation","Entertainment","Network Connectivity","Governance","Natural Environment","Quality of life"};
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setGranularity(1f);
        xAxis.setLabelRotationAngle(-90);
        xAxis.setCenterAxisLabels(false);

        xAxis.setGranularityEnabled(true);

        // adding color to our bar data set.
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        // setting text color.
        barDataSet.setValueTextColor(Color.BLACK);

        // setting text size
        //barDataSet.setValueTextSize(16f);
        barDataSet.setValueTextSize(16f);
        barDataSet.setDrawValues(false);

        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(false);
    }

    private void getBarEntries() {
        // creating a new array list
        barEntriesArrayList = new ArrayList<>();

        Log.e("value",""+arr[0]);

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntriesArrayList.add(new BarEntry(1f, (float) arr[0]));
        barEntriesArrayList.add(new BarEntry(2f, (float) arr[1]));
        barEntriesArrayList.add(new BarEntry(3f, (float) arr[2]));
        barEntriesArrayList.add(new BarEntry(4f, (float) arr[3]));
        barEntriesArrayList.add(new BarEntry(5f, (float) arr[4]));
        barEntriesArrayList.add(new BarEntry(6f, (float) arr[5]));
        barEntriesArrayList.add(new BarEntry(7f, (float) arr[6]));
        barEntriesArrayList.add(new BarEntry(8f, (float) arr[7]));
        barEntriesArrayList.add(new BarEntry(9f, (float) arr[8]));
        barEntriesArrayList.add(new BarEntry(10f, (float) arr[9]));
        barEntriesArrayList.add(new BarEntry(11f, (float) arr[10]));
        barEntriesArrayList.add(new BarEntry(12f, (float) arr[11]));
        barEntriesArrayList.add(new BarEntry(13f, (float) arr[12]));
        barEntriesArrayList.add(new BarEntry(14f, (float) arr[13]));
        barEntriesArrayList.add(new BarEntry(15f, (float) arr[14]));
        barEntriesArrayList.add(new BarEntry(16f, (float) arr[15]));
        barEntriesArrayList.add(new BarEntry(17f, (float) arr[16]));
        barEntriesArrayList.add(new BarEntry(18f, (float) arr[17]));
        barEntriesArrayList.add(new BarEntry(19f, (float) arr[18]));
        barEntriesArrayList.add(new BarEntry(20f, (float) arr[19]));

    }



}