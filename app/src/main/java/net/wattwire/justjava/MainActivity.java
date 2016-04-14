package net.wattwire.justjava;

        import android.content.Intent;
        import android.net.Uri;
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
//----------------------------------------------------------------------

    public void startTimer(String message, int seconds) {

        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_LENGTH, seconds)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void composeEmail(String[] addresses, String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

//----------------------------------------------------------------------
//     * This method is called when the ORDER button is clicked.
    public void submitOrder(View view) {

//          startTimer("You time iz Zup!", 3);

//        Uri mapLoc = Uri.parse("geo:0,0?q=220+Union+Ave%2C+CA+95032");
//        showMap(mapLoc);

        CheckBox wantsWhipCheckBox = (CheckBox) findViewById(R.id.whipBox);
        boolean wantsWhip = wantsWhipCheckBox.isChecked();
        String whipState = "Whipped Cream: " + wantsWhip;

        CheckBox wantsChocoCheckBox = (CheckBox) findViewById(R.id.chocoBox);
        boolean wantsChoco = wantsChocoCheckBox.isChecked();
        String chocoState = "Chocolate: " + wantsChoco;

        EditText editName = (EditText) findViewById(R.id.edit_text_name);

        String priceMessage = "Name: " + editName.getText() + "\n";
        priceMessage += whipState + "\n";
        priceMessage += chocoState + "\n";
        priceMessage += "Quantity: " + quantity + "\n";
        priceMessage += "Total: $" + calcTotal() + ".00 \n";
        priceMessage +=  "Thank You!";

        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(priceMessage);

//***** For Now, We'll Display the order message for debugging + Emailing the Order:
        String[] destAddress = new String[1];
        destAddress[0] = "gonebikn@gmail.com";
        String orderSubject = "Just Java Order";

        composeEmail(destAddress, orderSubject, priceMessage);

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