package net.wattwire.justjava;

        import android.content.Intent;
        import android.os.Bundle;
        import android.provider.AlarmClock;
        import android.support.v7.app.ActionBarActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.TextView;


// * This app displays an order form to order coffee.

public class MainActivity extends ActionBarActivity {

    int orderWhip = 0;
    int orderChoco = 0;
    boolean nameTap = false;
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayQuantity();

    }


//----------------------------------------------------------------------
//     * This method is called when the ORDER button is clicked.
    public void submitOrder(View view) {

        String message = "Your Time is Up!!!";
        int seconds = 4;

        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_LENGTH, seconds)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


//        CheckBox wantsWhipCheckBox = (CheckBox) findViewById(R.id.whipBox);
//        boolean wantsWhip = wantsWhipCheckBox.isChecked();
//        String whipState = "Whipped Cream: " + wantsWhip;
//
//        CheckBox wantsChocoCheckBox = (CheckBox) findViewById(R.id.chocoBox);
//        boolean wantsChoco = wantsChocoCheckBox.isChecked();
//        String chocoState = "Chocolate: " + wantsChoco;
//
//        EditText editName = (EditText) findViewById(R.id.edit_text_name);
//
//        String priceMessage = "Name: " + editName.getText() + "\n";
//        priceMessage += whipState + "\n";
//        priceMessage += chocoState + "\n";
//        priceMessage += "Quantity: " + quantity + "\n";
//        priceMessage += "Total: $" + calcTotal() + ".00 \n";
//        priceMessage +=  "Thank You!";
//
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(priceMessage);


    }

    public int calcTotal() {

        CheckBox wantsWhipCheckBox = (CheckBox) findViewById(R.id.whipBox);
        boolean wantsWhip = wantsWhipCheckBox.isChecked();

        CheckBox wantsChocoCheckBox = (CheckBox) findViewById(R.id.chocoBox);
        boolean wantsChoco = wantsChocoCheckBox.isChecked();

        int extras = 0;
        if (wantsWhip) { extras ++; }
        if (wantsChoco) { extras += 2; }

        Log.v("calcTotal","extras = " + extras);

        return quantity * (5 + extras);

    }

    public void clearName(View view) {

        if (!nameTap) {
            EditText nameText = (EditText) findViewById(R.id.edit_text_name);
            nameText.setText("");
            nameTap = true;
        }
    }

    public void increment(View view) {
        quantity++;
        displayQuantity();
    }

    //*** Called by onClick of the Extras Checkboxes to update total
    public void extrasChanged(View view) {

        displayQuantity();
    }

    public void decrement(View view) {

        if (quantity > 0) {
            quantity--;
        }

        displayQuantity();
    }

 //   * This method displays the given quantity value on the screen.

    private void displayQuantity() {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);

//      If quantity is updated, go into Pre-Order State: Wipe Out Prev Order Info.

        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText("$" + calcTotal() + ".00");

    }


}