package watarumaeda.com.petsos_android_app.view.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class MissingDialogFlagment extends DialogFragment
{
    String title;
    String message;

    public MissingDialogFlagment(String title, String message) {
        this.title = title;
        this.message = message;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    @Override
        public void onPause() {
        super.onPause();
        dismiss();
    }
}
