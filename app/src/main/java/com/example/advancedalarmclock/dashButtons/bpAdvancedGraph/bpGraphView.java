package com.example.advancedalarmclock.dashButtons.bpAdvancedGraph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

public class bpGraphView extends View {
    private List<String> dateData;
    private List<Integer> bpSysData;
    private List<Integer> bpDiaData;

    private int minBPSysValue;
    private int maxBPSysValue;
    private int minBPDiaValue;
    private int maxBPDiaValue;
    private int graphWidth;

    public bpGraphView(Context context) {
        super(context);
    }

    public bpGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public bpGraphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setGraphWidth(int width) {
        graphWidth = width;
        requestLayout();
    }

    private void init() {
        graphWidth = 0;
         // Set the desired line stroke width

    }

    public void setBPData(List<String> dateData, List<Integer> bpSysData, List<Integer> bpDiaData,
                          int minBPSysValue, int maxBPSysValue, int minBPDiaValue, int maxBPDiaValue) {
        this.dateData = dateData;
        this.bpSysData = bpSysData;
        this.bpDiaData = bpDiaData;
        this.minBPSysValue = minBPSysValue;
        this.maxBPSysValue = maxBPSysValue;
        this.minBPDiaValue = minBPDiaValue;
        this.maxBPDiaValue = maxBPDiaValue;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (dateData == null || dateData.isEmpty() || bpSysData == null || bpSysData.isEmpty()
                || bpDiaData == null || bpDiaData.isEmpty())
            return;

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        Paint paintSys = new Paint();
        paintSys.setStyle(Paint.Style.STROKE);
        paintSys.setColor(Color.RED);
        paintSys.setStrokeWidth(15f);

        Paint paintDia = new Paint();
        paintDia.setStyle(Paint.Style.STROKE);
        paintDia.setColor(Color.BLUE);
        paintDia.setStrokeWidth(5f);
        canvas.drawColor(Color.WHITE);

        drawGrid(canvas, width, height, paintSys);

        float xStep = (float) width / (dateData.size() - 1);
        float yStepSys = (float) height / (maxBPSysValue - minBPSysValue);
        float yStepDia = (float) height / (maxBPDiaValue - minBPDiaValue);

        float startX = 0;
        float startYSys = height - (bpSysData.get(0) - minBPSysValue) * yStepSys;
        float startYDia = height - (bpDiaData.get(0) - minBPDiaValue) * yStepDia;

        for (int i = 1; i < bpSysData.size(); i++) {
            float endX = startX + xStep;
            float endYSys = height - (bpSysData.get(i) - minBPSysValue) * yStepSys;
            float endYDia = height - (bpDiaData.get(i) - minBPDiaValue) * yStepDia;
            canvas.drawLine(startX, startYSys, endX, endYSys, paintSys);
            canvas.drawLine(startX, startYDia, endX, endYDia, paintDia);
            startX = endX;
            startYSys = endYSys;
            startYDia = endYDia;
        }
    }

    private void drawGrid(Canvas canvas, int width, int height, Paint paint) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2f);
        paint.setTextSize(40f);

        float yStepSys = (float) height / (maxBPSysValue - minBPSysValue + 1);
        float yStepDia = (float) height / (maxBPDiaValue - minBPDiaValue + 1);

        for (int i = minBPSysValue; i <= maxBPSysValue; i++) {
            float y = height - (i - minBPSysValue) * yStepSys;
            canvas.drawLine(0, y, width, y, paint);

            String label = String.valueOf(i);
            float labelWidth = paint.measureText(label);
            float labelX = width - labelWidth - 20;
            float labelY = y - 10 + paint.getTextSize() / 2;
            canvas.drawText(label, labelX, labelY, paint);
        }

        for (int i = minBPDiaValue; i <= maxBPDiaValue; i++) {
            float y = height - (i - minBPDiaValue) * yStepDia;
            canvas.drawLine(0, y, width, y, paint);

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

            // Draw X-axis labels (Shows mm/dd format)
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
