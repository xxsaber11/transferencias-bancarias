package com.migui.transferenciasbancarias.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
@PropertySource("classpath:application.yml")
public class DBConnect {

    @Value("${firebase.projectId}")
    private String projectId;

    @Value("${firebase.credentialsPath}")
    private String credentialsPath;

    private static final Logger log = LoggerFactory.getLogger(DBConnect.class);

    public DBConnect() {
        super();
    }
    
    @Bean
    public Firestore getFirestore() throws IOException {
        FirestoreOptions options;
        Firestore db;

        if (credentialsPath == null || projectId == null) {
            throw new IllegalArgumentException("No se han configurado correctamente las propiedades firebase.projectId y firebase.credentialsPath");
        }

        FileInputStream serviceAccount = null;
        try {
            serviceAccount = new FileInputStream(credentialsPath);
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

            options = FirestoreOptions.newBuilder()
                    .setProjectId(projectId)
                    .setCredentials(credentials)
                    .build();

            db = options.getService();
        } catch (IOException e) {
            log.error("Error al leer el archivo de credenciales.", e);
            throw e;
        } finally {
            if (serviceAccount != null) {
                try {
                    serviceAccount.close();
                } catch (IOException e) {
                    log.error("Error al cerrar el archivo de credenciales.", e);
                }
            }
        }

        return db;
    }
}
