package p06registadora;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author Daniel
 */
public class P06Registadora extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        stage.setTitle("Calculadora (UBI 2020/2021)");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        FXMLDocumentController controller = loader.getController();

        Scene scene = new Scene(root, 400, 300);
        
        /*scene.setOnKeyPressed((KeyEvent event) -> {
            System.out.println(event.getCode().toString());
        });*/
        
        stage.setScene(scene);
        stage.requestFocus();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
