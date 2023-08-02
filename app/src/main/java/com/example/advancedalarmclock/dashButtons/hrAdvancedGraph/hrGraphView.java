package com.example.advancedalarmclock.dashButtons.hrAdvancedGraph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

public class hrGraphView extends View {
    private int setLinecolor;
    private int minHrValue; // Added
    private float lineStrokeWidth; // Added
    private List<String> dateData;
    private List<Integer> hrData;
    private int maxHrValue;
    private int graphWidth;
    private int lineColor;

    public void setLineColor(int color) {
        lineColor = color;
    }

    public hrGraphView(Context context) {
        super(context);
    }

    public hrGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public hrGraphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (hrData == null) {
            // If glucoseData is null, set a default width for the view
            int defaultWidth = 200;
            int width = resolveSize(defaultWidth, widthMeasureSpec);
            int height = resolveSize(MeasureSpec.getSize(heightMeasureSpec), heightMeasureSpec);
            setMeasuredDimension(width, height);
        } else {
            int desiredWidth = hrData.size() * 100; // Adjust the width based on the number of data points
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

    public void setHrData(List<String> dateData, List<Integer> hrData, int minHrValue, int maxHrValue, int lineColor) {
        this.dateData = dateData;
        this.hrData = hrData;
        this.minHrValue = minHrValue;
        this.maxHrValue = maxHrValue;
        this.lineColor = lineColor; // Added lineColor assignment
        invalidate(); // Redraw the graph
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (dateData == null || dateData.isEmpty() || hrData == null || hrData.isEmpty())
            return;

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(lineColor); // Set the line color
        paint.setStrokeWidth(lineStrokeWidth);
        canvas.drawColor(Color.WHITE);

        // Draw grid lines and labels
        drawGrid(canvas, width, height, paint);

        // Calculate step sizes for X and Y axes
        float xStep = (float) width / (dateData.size() - 1);
        float yStep = (float) height / (maxHrValue - minHrValue);

        // Draw data points
        float startX = 0;
        float startY = height - (hrData.get(0) - minHrValue) * yStep;

        for (int i = 1; i < hrData.size(); i++) {
            float endX = startX + xStep;
            float endY = height - (hrData.get(i) - minHrValue) * yStep;
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
        float yStep = (float) height / (maxHrValue - minHrValue + 1); // Updated
        for (int i = minHrValue; i <= maxHrValue; i++) { // Updated
            float y = height - (i - minHrValue) * yStep; // Updated
            canvas.drawLine(0, y, width, y, paint);

            // Draw Y-axis labels
            String label = String.valueOf(i);
            float labelWidth = paint.measureText(label);
            float labelX = 20;
            float labelY = y - 10 + paint.getTextSize() / 2;
            canvas.drawText(label, labelX, labelY, paint);
        }


        float xStep = (float) width / (dateData.size() - 1); // Calculate the step size for X-axis labels
        for (int i = 0; i < dateData.size(); i++) {
            float x = i * xStep;
            canvas.drawLine(x, 0, x, height, paint);

            // Draw X-axis labels (Show mm/dd format)
            String label = dateData.get(i); // Get the label from dateData
            if (label.length() == 7) {
                label = label.substring(0, 1) + "/" + label.substring(1, 3);
            } else if (label.length() == 8) {
                label = label.substring(0, 2) + "/" + label.substring(2, 4);
            }
            float labelWidth = paint.measureText(label);
            canvas.drawText(label, x - labelWidth / 2, height - 10, paint);
        }
    }
}
