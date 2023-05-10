package com.uracle.nfbackend.nfbackend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;



@SpringBootApplication
public class NfBackendApplication {

	public static void main(String[] args) throws IOException {
		ClassLoader classLoader = NfBackendApplication.class.getClassLoader();
		File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());
		FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://uracle-20208-default-rtdb.firebaseio.com")
				.build();

		FirebaseApp.initializeApp(options);


		SpringApplication.run(NfBackendApplication.class, args);
	}

}
