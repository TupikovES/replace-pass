package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.controller.PathOverviewController;
import sample.controller.RootLayoutController;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private RootLayoutController rootLayoutController;
    private PathOverviewController pathOverviewController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Replace password");

        initRootLayout();
        showPathOverview();
    }

    private void initRootLayout() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/rootLayout.fxml"));
        try {
            rootLayout = loader.load();
            rootLayoutController = loader.getController();
            rootLayoutController.setMain(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showPathOverview() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/pathOverview.fxml"));

        try {
            AnchorPane pathOverview = loader.load();
            pathOverviewController = loader.getController();
            rootLayout.setCenter(pathOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public RootLayoutController getRootLayoutController() {
        return rootLayoutController;
    }

    public PathOverviewController getPathOverviewController() {
        return pathOverviewController;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
