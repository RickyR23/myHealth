package com.example.advancedalarmclock.dashButtons.gcAdvancedGraph;

import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

public class GraphView extends View {
    private int setLinecolor;
    private int minGlucoseValue; // Added
    private float lineStrokeWidth; // Added
    private List<String> dateData;
    private List<Integer> glucoseData;
    private int maxGlucoseValue;
    private int graphWidth;

    private int lineColor;

    public void setLineColor(int color) {
        lineColor = color;
    }

    public GraphView(Context context) {
        super(context);
    }

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GraphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (glucoseData == null) {
            // If glucoseData is null, set a default width for the view
            int defaultWidth = 200;
            int width = resolveSize(defaultWidth, widthMeasureSpec);
            int height = resolveSize(MeasureSpec.getSize(heightMeasureSpec), heightMeasureSpec);
            setMeasuredDimension(width, height);
        } else {
            int desiredWidth = glucoseData.size() * 100; // Adjust the width based on the number of data points
            int desiredHeight = MeasureSpec.getSize(heightMeasureSpec);

            int width = resolveSize(desiredWidth, widthMeasureSpec);
            int height = resolveSize(desiredHeight, heightMeasureSpec);

            setMeasuredDimension(width, height);
        }
    }
    public void setGraphWidth(int width) { // Added
        graphWidth = width;
        requestLayout(); // Request a layout update
    }

    private void init() {
        graphWidth = 0;
        lineStrokeWidth = 15f; // Set the desired line stroke width
    }
    public void setGlucoseData(List<String> dateData, List<Integer> glucoseData, int minGlucoseValue, int maxGlucoseValue, int lineColor) {
        this.dateData = dateData;
        this.glucoseData = glucoseData;
        this.minGlucoseValue = minGlucoseValue;
        this.maxGlucoseValue = maxGlucoseValue;
        this.lineColor = lineColor; // Added lineColor assignment
        invalidate(); // Redraw the graph
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (dateData == null || dateData.isEmpty() || glucoseData == null || glucoseData.isEmpty())
            return;

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(lineColor); // Set the line color
        paint.setStrokeWidth(lineStrokeWidth);

        // Draw grid lines and labels
        drawGrid(canvas, width, height, paint);

        // Calculate step sizes for X and Y axes
        float xStep = (float) width / (dateData.size() - 1);
        float yStep = (float) height / (maxGlucoseValue - minGlucoseValue);

        // Draw data points
        float startX = 0;
        float startY = height - (glucoseData.get(0) - minGlucoseValue) * yStep;

        for (int i = 1; i < glucoseData.size(); i++) {
            float endX = startX + xStep;
            float endY = height - (glucoseData.get(i) - minGlucoseValue) * yStep;
            canvas.drawLine(startX, startY, endX, endY, paint);
            startX = endX;
            startY = endY;
        }
    }

    private void drawGrid(Canvas canvas, int width, int height, Paint paint) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2f);
        paint.setTextSize(40f);

        // Draw horizontal grid lines and labels
        float yStep = (float) height / (maxGlucoseValue - minGlucoseValue + 1); // Updated
        for (int i = minGlucoseValue; i <= maxGlucoseValue; i++) { // Updated
            float y = height - (i - minGlucoseValue) * yStep; // Updated
            canvas.drawLine(0, y, width, y, paint);

            // Draw Y-axis labels
            String label = String.valueOf(i);
            float labelWidth = paint.measureText(label);
            float labelX = 20;
            float labelY = y - 10 + paint.getTextSize() / 2;
            canvas.drawText(label, labelX, labelY, paint);
        }


        // Draw vertical grid lines and labels
        float xStep = (float) width / (dateData.size() - 1); // Calculate the step size for X-axis labels
        for (int i = 0; i < dateData.size(); i++) {
            float x = i * xStep;
            canvas.drawLine(x, 0, x, height, paint);

            // Draw X-axis labels
            String label = dateData.get(i); // Get the label from dateData
            float labelWidth = paint.measureText(label);
            canvas.drawText(label, x - labelWidth / 2, height - 10, paint);
        }
    }
}
