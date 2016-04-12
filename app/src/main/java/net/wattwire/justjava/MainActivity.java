package net.wattwire.justjava;

        import android.os.Bundle;
        import android.support.v7.app.ActionBarActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.TextView;


// * This app displays an order form to order coffee.

public class MainActivity extends ActionBarActivity {

    boolean wantsWhip = false;
    boolean nameTap = false;
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayQuantity(quantity);

    }
//----------------------------------------------------------------------


//     * This method is called when the order button is clicked.

    public void submitOrder(View view) {

        int price = quantity * 5;

        CheckBox wantsWhipCheckBox = (CheckBox) findViewById(R.id.whipBox);
        boolean wantsWhip = wantsWhipCheckBox.isChecked();
        String whipState = "Whipped Cream: " + wantsWhip;
        Log.v("MainActivity", whipState);

        CheckBox wantsChocoCheckBox = (CheckBox) findViewById(R.id.chocoBox);
        boolean wantsChoco = wantsChocoCheckBox.isChecked();
        String chocoState = "Chocolate: " + wantsChoco;
        Log.v("MainActivity", chocoState);

        String priceMessage = whipState + "\n";
        priceMessage += chocoState + "\n";
        priceMessage += "Quantity: " + quantity + "\n";
        priceMessage += "Total: $" + price + ".00 \n";
        priceMessage +=  "Thank You!";

        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(priceMessage);

    }

    public void clearName(View view) {

        if (!nameTap) {
            EditText nameText = (EditText) findViewById(R.id.editName);
            nameText.setText("");
            nameTap = true;
        }
    }

    public void increment(View view) {
        quantity++;
        displayQuantity(quantity);
    }

    public void decrement(View view) {

        if (quantity > 0) {
            quantity--;
        }

        displayQuantity(quantity);
    }

 //   * This method displays the given quantity value on the screen.

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);

//      If quantity is updated, go into Pre-Order State: Wipe Out Prev Order Info.

        int price = quantity * 5;

        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText("$" + price + ".00");

    }


}