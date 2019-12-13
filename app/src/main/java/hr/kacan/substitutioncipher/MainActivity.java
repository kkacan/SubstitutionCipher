package hr.kacan.substitutioncipher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
    private final String KEY = "GXDJFULHSOAZKNPCYQWVIREMTB ";
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);
        textView = findViewById(R.id.text);

        Button btn_encrypt = findViewById(R.id.btn_encrypt);
        Button btn_decrypt = findViewById(R.id.btn_decrypt);
        Button btn_clear = findViewById(R.id.btn_clear);

        btn_encrypt.setOnClickListener(this);
        btn_decrypt.setOnClickListener(this);
        btn_clear.setOnClickListener(this);

        InputFilter[] editFilters = editText.getFilters();
        InputFilter[] newFilters = new InputFilter[editFilters.length + 1];
        System.arraycopy(editFilters, 0, newFilters, 0, editFilters.length);
        newFilters[editFilters.length] = new InputFilter.AllCaps();
        editText.setFilters(newFilters);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {
            case (R.id.btn_encrypt):
                textView.setText(encrypt(editText.getText().toString()));
                break;
            case (R.id.btn_decrypt):
                textView.setText(decrypt(editText.getText().toString()));
                break;
            case (R.id.btn_clear):
                editText.setText("");
                textView.setText("");
                break;
        }
    }

    private String encrypt(String plainText) {
        String cipherText = "";
        for (int i = 0; i < plainText.length(); i++) {
            for (int j = 0; j < KEY.length(); j++)
                if (plainText.charAt(i) == ALPHABET.charAt(j)) {
                    cipherText += KEY.charAt(j);
                    break;
                }
        }
        return cipherText;
    }

    private String decrypt(String cipherText) {
        String plainText = "";
        for (int i = 0; i < cipherText.length(); i++) {
            for (int j = 0; j < KEY.length(); j++) {
                if (cipherText.charAt(i) == KEY.charAt(j)) {
                    plainText += ALPHABET.charAt(j);
                    break;
                }
            }
        }
        return plainText;
    }


}
