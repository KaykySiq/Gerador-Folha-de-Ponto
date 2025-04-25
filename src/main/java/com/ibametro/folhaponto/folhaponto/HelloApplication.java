package com.ibametro.folhaponto.folhaponto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Carrega o FXML e exibe a interface gráfica
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/ibametro/folhaponto/folhaponto/view/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
