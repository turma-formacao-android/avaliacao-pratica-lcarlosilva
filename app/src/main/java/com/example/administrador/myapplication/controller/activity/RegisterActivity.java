package com.example.administrador.myapplication.controller.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.model.entities.Client;
import com.example.administrador.myapplication.model.entities.ClientAddress;
import com.example.administrador.myapplication.model.services.CepServices;
import com.example.administrador.myapplication.util.FormHelper;


public class RegisterActivity extends AppCompatActivity {

    public static String CLIENT_PARAM = "CLIENT_PARAM";

    private Client client;
    private ClientAddress clientAddress = new ClientAddress();

    private EditText clientName;
    private EditText clientAge;
    private EditText clientPhone;

    private EditText editTextCep;
    private EditText editTipoLogradouro;
    private EditText editLogradouro;
    private EditText editNumber;
    private EditText clienteComplements;
    private EditText editBairro;
    private EditText editCidade;
    private EditText editEstado;
    //private Button buttonFindCep;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persist_client);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            client = (Client) extras.getParcelable(CLIENT_PARAM);
        }
        bindComponents();
        loadIfEdit();
    }

    private void loadIfEdit() {
        if (client != null) {
            clientName.setText(client.getName());
            clientAge.setText(client.getAge().toString());
            clienteComplements.setText(client.getAddress().getComplemento());
            clientPhone.setText(client.getPhone());
        }
    }

    private void bindComponents() {
        clientName = (EditText) findViewById(R.id.clientName);
        clientName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_edittext_client, 0);
        actionFindContactOnTouch();
        clientAge = (EditText) findViewById(R.id.clientAge);
        clienteComplements = (EditText) findViewById(R.id.complements);
        clientPhone = (EditText) findViewById(R.id.clientPhone);
        editTextCep = (EditText) findViewById(R.id.clientCep);
        editTextCep.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_action_search, 0);
        actionFindZipCodeOnTouch();
        editTipoLogradouro = (EditText) findViewById(R.id.clientTipoDeLogradouro);
        editLogradouro = (EditText) findViewById(R.id.clientLogradouro);
        editBairro = (EditText) findViewById(R.id.clientBairro);
        editCidade = (EditText) findViewById(R.id.clientCidade);
        editEstado = (EditText) findViewById(R.id.clientEstado);
        editNumber = (EditText) findViewById(R.id.clientNumberAdress);

        //bindButtonFindCep();
    }

    private void actionFindContactOnTouch() {
        clientName.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (clientName.getRight() - clientName.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        //TODO: Explanation 2:
                        final Intent goToSOContacts = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                        goToSOContacts.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
                        startActivityForResult(goToSOContacts, 999);
                    }
                }
                return false;
            }
        });
    }

    private void actionFindZipCodeOnTouch() {
        editTextCep.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (editTextCep.getRight() - editTextCep.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        new GetAddressByCep().execute(editTextCep.getText().toString());
                    }
                }
                return false;
            }
        });
    }

    /**
     * @see <a href="http://developer.android.com/training/basics/intents/result.html">Getting a Result from an Activity</a>
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    final Uri contactUri = data.getData();
                    final String[] projection = {
                            ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                    };
                    final Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                    cursor.moveToFirst();

                    clientName.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME)));
                    clientPhone.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));

                    cursor.close();
                } catch (Exception e) {
                    Log.d("TAG", "Unexpected error");
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*private void bindButtonFindCep() {
        buttonFindCep = (Button) findViewById(R.id.btnFindZip);
        buttonFindCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetAddressByCep().execute(editTextCep.getText().toString());
            }
        });
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_client_persist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menuSave) {
            tentarSalvarClient();
        }

        return super.onOptionsItemSelected(item);
    }

    private void tentarSalvarClient() {
        if (saveClient()) {
            Toast.makeText(RegisterActivity.this, "Cliente Cadastrado", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private boolean saveClient() {
        if (FormHelper.requireValidate(RegisterActivity.this, clientName, clientAge, clienteComplements, clientPhone)) {
            if (client == null) {
                client = new Client();
            }

            client.setName(clientName.getText().toString());
            client.setAge(Integer.parseInt(clientAge.getText().toString()));
            client.setPhone(clientPhone.getText().toString());

            clientAddress.setCep(editTextCep.getText().toString());
            clientAddress.setTipoDeLogradouro(editTipoLogradouro.getText().toString());
            clientAddress.setLogradouro(editLogradouro.getText().toString());
            clientAddress.setNumero(Integer.parseInt(editNumber.getText().toString()));
            clientAddress.setComplemento(clienteComplements.getText().toString());
            clientAddress.setCidade(editCidade.getText().toString());
            clientAddress.setEstado(editEstado.getText().toString());

            client.setAddress(clientAddress);

            client.save();
            return true;
        }
        return false;
    }


    private class GetAddressByCep extends AsyncTask<String, Void, ClientAddress> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(RegisterActivity.this);
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.show();
        }

        @Override
        protected ClientAddress doInBackground(String... params) {
            return CepServices.getAddressBy(params[0]);
        }

        @Override
        protected void onPostExecute(ClientAddress clientAddress) {
            editBairro.setText(clientAddress.getBairro());
            editCidade.setText(clientAddress.getCidade());
            editLogradouro.setText(clientAddress.getLogradouro());
            editEstado.setText(clientAddress.getEstado());
            editTipoLogradouro.setText(clientAddress.getTipoDeLogradouro());
            progressDialog.dismiss();
        }
    }

}
