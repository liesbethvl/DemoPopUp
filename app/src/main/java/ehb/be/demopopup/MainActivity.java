package ehb.be.demopopup;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import ehb.be.demopopup.dialog.SimpleDialogFragment;
import ehb.be.demopopup.dialog.ToppingsDialog;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabSnackBar;
    private Button btnToast, btnDialog, btnMenu;

    private View.OnClickListener fabSnackBarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Snackbar sb = Snackbar.make(findViewById(R.id.main_container), "Hello pizza", Snackbar.LENGTH_LONG);
            sb.setAction("Cancel", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("TEST", "Clicked in the snackbar");
                }
            });
            sb.setActionTextColor(Color.RED);
            sb.show();
        }
    };

    private View.OnClickListener toastOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Cheers!", Toast.LENGTH_LONG).show();

        }
    };

    private View.OnClickListener dialogOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SimpleDialogFragment simpleDialog = new SimpleDialogFragment();
            simpleDialog.show(getSupportFragmentManager(), "id");
        }
    };

    private View.OnClickListener menuOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ToppingsDialog toppingsDialog = new ToppingsDialog();
            toppingsDialog.setDialogConfirmListener(dialogConfirmListener);
            toppingsDialog.show(getSupportFragmentManager(), "id2");
            //of: new toppingsDialog().show(getSupportFragmentManager(), "id2");
        }
    };

    private ToppingsDialog.DialogConfirmListener dialogConfirmListener = new ToppingsDialog.DialogConfirmListener() {
        @Override
        public void confirm(ArrayList<String> toppings) {
            Toast.makeText(getApplicationContext(), toppings.toString(), Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabSnackBar = findViewById(R.id.fab_snackbar);
        fabSnackBar.setOnClickListener(fabSnackBarOnClickListener);

        btnToast = findViewById(R.id.btn_toast);
        btnToast.setOnClickListener(toastOnClickListener);

        btnDialog = findViewById(R.id.btn_dialog);
        btnDialog.setOnClickListener(dialogOnClickListener);

        btnMenu = findViewById(R.id.btn_menu);
        btnMenu.setOnClickListener(menuOnClickListener);
    }
}
