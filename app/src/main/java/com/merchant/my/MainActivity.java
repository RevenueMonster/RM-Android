package com.merchant.my;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.revenuemonster.payment.Checkout;
import com.revenuemonster.payment.PaymentResult;
import com.revenuemonster.payment.constant.Env;
import com.revenuemonster.payment.constant.Method;
import com.revenuemonster.payment.model.Error;
import com.revenuemonster.payment.model.Transaction;

public class MainActivity extends Activity implements PaymentResult {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Button button = (Button) findViewById(R.id.rmPay);

        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Payment Method")
                            .setItems(new String[]{"WeChatPay MY", "TNG", "Boost", "AliPay CN", "GrabPay"}, new DialogInterface.OnClickListener() {
                                String checkoutId = "1563058628703907802";
                                String weChatAppID = "";
                                Method method;
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case 0:
                                            method = Method.WECHATPAY_MY;
                                            break;
                                        case 1:
                                            method = Method.TNG_MY;
                                            break;
                                        case 2:
                                            method = Method.BOOST_MY;
                                            break;
                                        case 3:
                                            method = Method.ALIPAY_CN;
                                            break;
                                        case 4:
                                            method = Method.GRABPAY_MY;
                                            break;
                                    }
                                    try {
                                        new Checkout(getApplication()).getInstance().setWeChatAppID(weChatAppID).
                                                setEnv(Env.SANDBOX).
                                                pay(method, checkoutId, MainActivity.this);
                                    } catch(Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                    builder.show();
               } catch(Exception e) {
                   e.printStackTrace();
               }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onPaymentSuccess(Transaction transaction) {
        Log.d("SUCCESS", transaction.getStatus());
    }
    public void onPaymentFailed(Error error) {
        Log.d("FAILED", error.getMessage());
    }
    public void onPaymentCancelled() {
        Log.d("CANCELLED", "cancelled");
    }
}




