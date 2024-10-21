package com.jaimito.minicalculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;
    Button btn1, btn2, btn3, btnPlus, btnMinus, btn4, btn5, btn6, btnMultiplied, btnDivided, btn7, btn8, btn9, btnRoot, btnInv, btn0, btnDot, btnNegative, btnEquals, btnClear;
    double num1 = 0, num2 = 0;
    char operator;
    boolean isOperatorPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMultiplied = findViewById(R.id.btnMultiplied);
        btnDivided = findViewById(R.id.btnDivided);
        btnRoot = findViewById(R.id.btnRoot);
        btnInv = findViewById(R.id.btnInv);
        btnDot = findViewById(R.id.btnDot);
        btnNegative = findViewById(R.id.btnNegative);
        btnEquals = findViewById(R.id.btnEquals);
        btnClear = findViewById(R.id.btnClear);

        btn1.setOnClickListener(this::addNumber);
        btn2.setOnClickListener(this::addNumber);
        btn3.setOnClickListener(this::addNumber);
        btn4.setOnClickListener(this::addNumber);
        btn5.setOnClickListener(this::addNumber);
        btn6.setOnClickListener(this::addNumber);
        btn7.setOnClickListener(this::addNumber);
        btn8.setOnClickListener(this::addNumber);
        btn9.setOnClickListener(this::addNumber);
        btn0.setOnClickListener(this::addNumber);
        btnDot.setOnClickListener(this::addNumber);
        btnPlus.setOnClickListener(v -> getCurrentOperator('+'));
        btnMinus.setOnClickListener(v -> getCurrentOperator('-'));
        btnMultiplied.setOnClickListener(v -> getCurrentOperator('x'));
        btnDivided.setOnClickListener(v -> getCurrentOperator('/'));
        btnRoot.setOnClickListener(v -> calculateRoot());
        btnInv.setOnClickListener(v -> calculateInverse());
        btnNegative.setOnClickListener(v -> toggleNegative());
        btnEquals.setOnClickListener(view -> calculateResult());
        btnClear.setOnClickListener(view -> clear());
    }

    private void getCurrentOperator(char op) {
        if (!tvResult.getText().toString().isEmpty()) {
            num1 = Double.parseDouble(tvResult.getText().toString());
            operator = op;
            isOperatorPressed = true;
            tvResult.setText("");
        }
    }

    private void calculateResult() {
        if (!tvResult.getText().toString().isEmpty()) {
            num2 = Double.parseDouble(tvResult.getText().toString());

            double result = 0;
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case 'x':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        tvResult.setText("Error");
                        return;
                    }
                    break;
            }
            tvResult.setText(String.valueOf(result));
            isOperatorPressed = false;
        }
    }

    private void calculateRoot() {
        if (!tvResult.getText().toString().isEmpty()) {
            num1 = Double.parseDouble(tvResult.getText().toString());
            double result = Math.sqrt(num1);
            tvResult.setText(String.valueOf(result));
        }
    }

    private void calculateInverse() {
        if (!tvResult.getText().toString().isEmpty()) {
            num1 = Double.parseDouble(tvResult.getText().toString());
            if (num1 != 0) {
                double result = 1 / num1;
                tvResult.setText(String.valueOf(result));
            } else {
                tvResult.setText("Error");
            }
        }
    }

    private void toggleNegative() {
        if (!tvResult.getText().toString().isEmpty()) {
            num1 = Double.parseDouble(tvResult.getText().toString());
            double result = num1 * -1;
            tvResult.setText(String.valueOf(result));
        }
    }

    private void addNumber(View view) {
        Button button = (Button) view;
        String currentText = tvResult.getText().toString();

        if (isOperatorPressed) {
            tvResult.setText(button.getText());
            isOperatorPressed = false;
        } else {
            tvResult.setText(currentText + button.getText());
        }
    }

    private void clear() {
        tvResult.setText("");
        num1 = 0;
        num2 = 0;
        operator = '\0';
        isOperatorPressed = false;
    }
}
