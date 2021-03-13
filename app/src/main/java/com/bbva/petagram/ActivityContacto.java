package com.bbva.petagram;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ActivityContacto extends AppCompatActivity {

    Button sendmail;
    ProgressDialog pdialog = null;
    Context context = null;
    Session sesion = null;
    String textMessage, correo, contraseña, maildest;
    EditText comentarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Button sendmail = (Button) findViewById(R.id.boton);
        EditText comentarios = (EditText) findViewById(R.id.txtComentario);
        EditText mail = (EditText) findViewById(R.id.mail);

        sendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                textMessage = comentarios.getText().toString();
                correo = "origenMail@gmail.com";
                contraseña = "abc123";
                maildest = mail.getText().toString();

                Properties prop = new Properties();
                prop.put("mail.smtp.host", "smtp.googlemail.com");
                prop.put("mail.smtp.socketFactory.port", "465");
                prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                prop.put("mail.smtp.auth", "true");
                prop.put("mail.smtp.port", "465");

                try {
                    sesion = Session.getDefaultInstance(prop, new Authenticator() {
                                @Override
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new  PasswordAuthentication(correo, contraseña);
                                }
                            }
                    );

                    if (sesion!=null){
                        //javax.mail.Message mesage = new MimeMessage(sesion);
                        Message mesage = new MimeMessage(sesion);
                        mesage.setFrom(new InternetAddress(correo));
                        mesage.setSubject("Prueba Mail Android");
                        mesage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(maildest));
                        mesage.setContent(textMessage,"text/html; charset=utf-8");
                        Transport.send(mesage);
                    }

                }catch (Exception e){
                    e.printStackTrace();               }

                Toast.makeText(ActivityContacto.this,"Correo Enviado", Toast.LENGTH_LONG).show();

            }


        });


    }
}