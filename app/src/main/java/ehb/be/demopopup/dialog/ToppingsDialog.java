package ehb.be.demopopup.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import java.util.ArrayList;
import java.util.Arrays;


public class ToppingsDialog extends DialogFragment {

    //interfacee wordt in de activity geïmplementeerd
    //zo sturen we een callback van het fragment naar de activity
    //we maken onze eigen View.onClickListener
    public interface DialogConfirmListener {
        void confirm(ArrayList<String> toppings);
    }
    private DialogConfirmListener dialogConfirmListener;

    public void setDialogConfirmListener(DialogConfirmListener dialogConfirmListener) {
        this.dialogConfirmListener = dialogConfirmListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final String[] toppings = {"mozarella", "tomatensaus", "ajuin", "hesp","salami"};
        boolean[] isChecked = new boolean[toppings.length];
        Arrays.fill(isChecked, false);

        final ArrayList<String> gekozenToppings = new ArrayList<>();

        AlertDialog.Builder toppingsBuilder = new AlertDialog.Builder(getActivity());
        toppingsBuilder.setTitle("Kies je favoriete toppings");

        toppingsBuilder.setMultiChoiceItems(toppings, isChecked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked){
                    gekozenToppings.add(toppings[which]);
                }else {
                    gekozenToppings.remove(toppings[which]);
                }
            }
        });
        toppingsBuilder.setNegativeButton("Cancel", null);
        toppingsBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //op confirm geklikt, stuurt door naar activity
                dialogConfirmListener.confirm(gekozenToppings);
            }
        });


        return toppingsBuilder.create();

    }
}
