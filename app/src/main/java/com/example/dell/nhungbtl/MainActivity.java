package com.example.dell.nhungbtl;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    private CombinedChart mChart,mChart2;
    ArrayList<Data> datas=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        setGraph(mChart);
        setGraph(mChart2);


    }
    public void setGraph(CombinedChart mChart){
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);

        final List<String> xLabel = new ArrayList<>();
        for (int i = 0; i <datas.size() ; i++) {
            xLabel.add(datas.get(i).getCreatedDate()+"");

        }

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xLabel.get((int) value % xLabel.size());
            }
        });

        CombinedData data = new CombinedData();
        LineData lineDatas = new LineData();
        lineDatas.addDataSet((ILineDataSet) dataChart(mChart));
        data.setData(lineDatas);
        xAxis.setAxisMaximum(data.getXMax() + 0.25f);
        mChart.setData(data);
        mChart.invalidate();
    }

    private void addControls() {
        mChart = (CombinedChart) findViewById(R.id.combinedChart1);
        mChart2=findViewById(R.id.combinedChart2);

        mChart.getDescription().setEnabled(false);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(false);
        mChart.setOnChartValueSelectedListener(this);

        mChart2.getDescription().setEnabled(false);
        mChart2.setBackgroundColor(Color.WHITE);
        mChart2.setDrawGridBackground(false);
        mChart2.setDrawBarShadow(false);
        mChart2.setHighlightFullBarEnabled(false);
        mChart2.setOnChartValueSelectedListener(this);
        Data data1 =new Data();
        data1.setTemperature(25);
        data1.setHumidity(86);
        data1.setCreatedDate(19);

        Data data2 =new Data();
        data2.setTemperature(27);
        data2.setHumidity(89);
        data2.setCreatedDate(20);
        Data data3 =new Data();
        data3.setTemperature(29);
        data3.setHumidity(82);
        data3.setCreatedDate(21);
        Data data4 =new Data();
        data4.setTemperature(36);
        data4.setHumidity(90);
        data4.setCreatedDate(22);
        Data data5 =new Data();
        data5.setTemperature(30);
        data5.setHumidity(84);
        data5.setCreatedDate(23);
        Data data6 =new Data();
        data6.setTemperature(25);
        data6.setHumidity(80);
        data6.setCreatedDate(24);
        Data data7 =new Data();
        data7.setTemperature(32);
        data7.setHumidity(86);
        data7.setCreatedDate(25);
        datas.add(data1);
        datas.add(data2);
        datas.add(data3);
        datas.add(data4);
        datas.add(data5);
        datas.add(data6);
        datas.add(data7);
    }
    public LineDataSet setEntry(ArrayList<Integer> data){
        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index < datas.size(); index++) {
            entries.add(new Entry(index, data.get(index)));
        }

        LineDataSet set = new LineDataSet(entries, "Request Ots approved");
        set.setColor(Color.GREEN);
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.GREEN);
        set.setCircleRadius(5f);
        set.setFillColor(Color.GREEN);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.GREEN);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        return set;

    }


    public  DataSet dataChart(CombinedChart mChart1) {

        LineData d = new LineData();
        ArrayList<Integer> data = new ArrayList<>();
        switch (mChart1.getId()){
            case R.id.combinedChart1:
                for (int i = 0; i <datas.size(); i++) {
                    data.add(datas.get(i).getTemperature());

                }
                break;
            case R.id.combinedChart2:
                for (int i = 0; i <datas.size(); i++) {
                    data.add(datas.get(i).getHumidity());

                }
                break;
        }


        d.addDataSet(setEntry(data));

        return setEntry(data);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Toast.makeText(this, "Value: "
                + e.getY()
                + ", index: "
                + h.getX()
                + ", DataSet index: "
                + h.getDataSetIndex(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected() {

    }
}
