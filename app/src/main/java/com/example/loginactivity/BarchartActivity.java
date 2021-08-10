package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

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

    float arr[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchart);

        Bundle extras = getIntent().getExtras();
        arr = extras.getFloatArray("array");

        Toast.makeText(getApplicationContext(), "arrayB"+arr, Toast.LENGTH_SHORT).show();


        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("Sun");
        xAxisLabel.add("Mon");
        xAxisLabel.add("Tue");
        xAxisLabel.add("Wed");
        xAxisLabel.add("Thu");
        xAxisLabel.add("Fri");
        xAxisLabel.add("Sat");

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
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);
    }

    private void getBarEntries() {
        // creating a new array list
        barEntriesArrayList = new ArrayList<>();

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntriesArrayList.add(new BarEntry(1f, arr[0]));
        barEntriesArrayList.add(new BarEntry(2f, arr[1]));
        barEntriesArrayList.add(new BarEntry(3f, arr[2]));
        barEntriesArrayList.add(new BarEntry(4f, arr[3]));
        barEntriesArrayList.add(new BarEntry(5f, arr[4]));
        barEntriesArrayList.add(new BarEntry(6f, arr[5]));
        barEntriesArrayList.add(new BarEntry(7f, arr[6]));
        barEntriesArrayList.add(new BarEntry(8f, arr[7]));
        barEntriesArrayList.add(new BarEntry(9f, arr[8]));
        barEntriesArrayList.add(new BarEntry(10f, arr[9]));
        barEntriesArrayList.add(new BarEntry(11f, arr[10]));
        barEntriesArrayList.add(new BarEntry(12f, arr[11]));
        barEntriesArrayList.add(new BarEntry(13f, arr[12]));
        barEntriesArrayList.add(new BarEntry(14f, arr[13]));
        barEntriesArrayList.add(new BarEntry(15f, arr[14]));
        barEntriesArrayList.add(new BarEntry(16f, arr[15]));
        barEntriesArrayList.add(new BarEntry(17f, arr[16]));
        barEntriesArrayList.add(new BarEntry(18f, arr[17]));
        barEntriesArrayList.add(new BarEntry(19f, arr[18]));
        barEntriesArrayList.add(new BarEntry(20f, arr[19]));

    }



}