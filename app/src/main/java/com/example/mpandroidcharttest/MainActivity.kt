package com.example.mpandroidcharttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.LargeValueFormatter
import com.github.mikephil.charting.utils.EntryXComparator


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        chart_main.setTouchEnabled(false)

        var entries : MutableList<Entry> = arrayListOf()

//        entries.add(Entry(0f, 12f))
//        entries.add(Entry(1f, 12.15f))
//        entries.add(Entry(2f, 10f))
//        entries.add(Entry(3f, 5f))
//        entries.add(Entry(4f, 120f))
//        entries.add(Entry(5f, 52f))
        entries.add(Entry(1f, 12f))
        entries.add(Entry(2f, 12.15f))
        entries.add(Entry(3f, 10f))
        entries.add(Entry(4f, 5f))
        entries.add(Entry(5f, 120f))
        entries.add(Entry(6f, 52f))
//        entries.add(Entry(7f, 12f))
//        entries.add(Entry(8f, 12.15f))
//        entries.add(Entry(9f, 10f))
//        entries.add(Entry(10f, 5f))
//        entries.add(Entry(11f, 120f))
//        entries.add(Entry(12f, 52f))

        Collections.sort(entries, EntryXComparator())

//        val quarters = arrayListOf<String>("1","2","3","4","5","6","7","8","9","10","11","12")
        val quarters = arrayListOf<String>("1","2","3","4","5","6")
//        val formatter = object : ValueFormatter() {
        //LargeValueFormatter：可用於格式化大於“ 1.000”的大值。它將諸如“ 1.000”的值轉換為“ 1k”，“
        // 1.000.000”將為“ 1m”（百萬），“ 1.000.000.000”將為“ 1b”（十億），諸如一萬億的值例如為“ 1t” ”。
        val formatter = object : LargeValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
//                return quarters[value.toInt()]
                return quarters.getOrNull(value.toInt()) ?: value.toString()
            }
        }

        var xAxis = chart_main.xAxis
        xAxis.granularity = 1f
//        xAxis.valueFormatter = formatter
        xAxis.setDrawGridLines(false)
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        var yAxisLeft = chart_main.axisLeft
        yAxisLeft.setDrawGridLines(false)
        yAxisLeft.setDrawZeroLine(false)

        var yAxisRight = chart_main.axisRight
        yAxisRight.setDrawGridLines(false)
        yAxisRight.setDrawZeroLine(false)

        var dataSet = LineDataSet(entries, "月份")
        dataSet.color = getColor(R.color.material_grey_300)
        dataSet.valueTextColor = getColor(R.color.material_blue_grey_800)
        dataSet.valueFormatter = formatter

        var lineData = LineData(dataSet)
        chart_main.data = lineData
        chart_main.invalidate()
    }
}
