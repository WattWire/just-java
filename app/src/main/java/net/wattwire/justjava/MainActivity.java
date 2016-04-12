package net.wattwire.justjava;

        import android.os.Bundle;
        import android.support.v7.app.ActionBarActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.CheckBox;
        import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    boolean wantsWhip = false;
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayQuantity(quantity);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        int price = quantity * 5;

        CheckBox wantsWhipCheckBox = (CheckBox) findViewById(R.id.whipBox);
        boolean wantsWhip = wantsWhipCheckBox.isChecked();
        String whipState = "Has whipped cream: " + wantsWhip;
        Log.v("MainActivity", whipState);

        CheckBox wantsChocoCheckBox = (CheckBox) findViewById(R.id.chocoBox);
        boolean wantsChoco = wantsChocoCheckBox.isChecked();
        String chocoState = "Wants Chocolate? " + wantsChoco;
        Log.v("MainActivity", chocoState);

        String priceMessage = whipState + "\n" + chocoState + "\n" + "Amount: $" + price + ".00 \nThank You!";
        displayMessage(priceMessage);

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

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}