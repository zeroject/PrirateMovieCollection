package MVC;

import BE.Category;
import BE.Movie;
import BLL.MovieManager;
import BLL.util.DEBUGReaderThread;
import MVC.Model.CategoryModel;
import MVC.Model.ParseModel;
import MVC.Model.MovieModel;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable
{
    MovieModel movieModel;
    CategoryModel categoryModel;

    @FXML
    private TableView<Movie> movieTableView;
    @FXML
    private TableColumn movieColumn;
    @FXML
    private TableColumn imageColumn;
    @FXML
    private javafx.scene.layout.Pane pane;
    @FXML
    private TextField searchTextField;
    @FXML
    private ComboBox<Category> comboBox;


    public MainWindowController() throws IOException{
        movieColumn = new TableColumn<MovieManager, String>();
        imageColumn = new TableColumn<MovieManager, ImageView>();
        movieTableView = new TableView<>();
        movieModel = new MovieModel();
        categoryModel = new CategoryModel();
    }



    @Override public void initialize(URL location, ResourceBundle resources)
    {
        movieColumn.setCellValueFactory(new PropertyValueFactory<MovieManager, String>("Title"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<MovieManager, ImageView>("urlImg"));
        try
        {
            movieTableView.setItems(movieModel.getObservableMovie());
        } catch (Exception e){
            e.printStackTrace();
        }
        searchTextField.textProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            try {
                movieModel.searchMovie(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }));

        comboBox.setItems(categoryModel.getObservableCategory());
    }

    public void refreshTable() {
        try
        {
            movieTableView.getItems().clear();
            movieTableView.setItems(movieModel.getObservableMovie());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createCategoryScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("View/Create Category.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void deleteCategory(){
        categoryModel.deleteCategory(comboBox.getSelectionModel().getSelectedItem());
        comboBox.getItems().remove(comboBox.getSelectionModel().getSelectedItem());
    }

    public void createMovieScene() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("View/Create Movie.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println(stage + " Laoded and the scene : " + scene + "has loaded");
    }

    public void movieScene() throws IOException
    {
        ParseModel.movieURL = movieTableView.getSelectionModel().getSelectedItem().getUrl();
        Parent root = FXMLLoader.load(getClass().getResource("View/MovieScene.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setFullScreen(true);
        stage.show();
        System.out.println(stage + " Laoded and the scene : " + scene + "has loaded");
    }
    public void chooseMovieScene() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("View/Choose Movie.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println(stage + " Laoded and the scene : " + scene + "has loaded");
        ParseModel.isDELETING = false;
    }
    public void chooseMovieSceneDEL() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("View/Choose Movie.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println(stage + " Laoded and the scene : " + scene + "has loaded");
        ParseModel.isDELETING = true;
    }

    public void createImagesForEachMovie() throws SQLException
    {
        List<ImageView> imageViewList = new ArrayList<>();
        int movieImgId = 1;
        double maxXPos = 300;
        double maxYPos = 0;
        double xPos = -200;
        double yPos = -200;
        for (Movie movie : movieModel.getObservableMovie()){
            System.out.println(movieModel.getObservableMovie().get(movieImgId).getUrlImg());
            Image image = new Image(movieModel.getObservableMovie().get(movieImgId).getUrlImg());
            movieImgId = movieImgId +1;
            ImageView imageView = new ImageView(image);
            imageView.setScaleX(0.2);
            imageView.setScaleY(0.2);
            if (xPos >= maxXPos){
                xPos = -200;
                yPos = yPos + 110;
            }
            imageView.setLayoutX(xPos);
            imageView.setLayoutY(yPos);
            xPos = xPos + 200;
            imageViewList.add(imageView);
            pane.getChildren().add(imageView);
        }
    }








    /*
    *                     **WARNING THIS PART OF THE CODE IS NOT OURS**
    *                        **DEBUG CONSOLE USED FROM Cool IT Help.
    * **LINK - https://www.coolithelp.com/2020/06/javafx-redirect-console-output-to.html - LINK**
     */
    private final PipedInputStream pipeIn = new PipedInputStream();
    private final PipedInputStream pipeIn2 = new PipedInputStream();
    Thread errorThrower;
    private Thread reader;
    private Thread reader2;
    boolean quit;
    private TextArea txtArea;
    private double xOffset = 0;
    private double yOffset = 0;

    public void debugConsole() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("View/Debug Console.fxml"));
        //Console myConsole = new Console();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        ToolBar toolBar = new ToolBar();
        stage.initStyle(StageStyle.UNDECORATED);
        //grab your root here
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        //move around here
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        stage.setScene(scene);
        stage.show();

        txtArea = DEBUGController.staticTxtArea;

        //Thread execution for reading output stream
        executeReaderThreads();

        //Thread closing on stag close event
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {

                closeThread();
                Platform.exit();
                System.exit(0);
            }
        });
    }
    synchronized void closeThread()
    {
        System.out.println("Message: Stage is closed.");
        this.quit = true;
        notifyAll();
        try { this.reader.join(1000L); this.pipeIn.close(); } catch (Exception e) {
        }try { this.reader2.join(1000L); this.pipeIn2.close(); } catch (Exception e) {
    }System.exit(0);
    }
    public void executeReaderThreads()
    {
        try
        {
            PipedOutputStream pout = new PipedOutputStream(this.pipeIn);
            System.setOut(new PrintStream(pout, true));
        }
        catch (IOException io)
        { }
        catch (SecurityException se)
        { }

        try
        {
            PipedOutputStream pout2 = new PipedOutputStream(this.pipeIn2);
            System.setErr(new PrintStream(pout2, true));
        }
        catch (IOException io)
        {
        }
        catch (SecurityException se)
        {
        }

        DEBUGReaderThread obj = new DEBUGReaderThread(pipeIn, pipeIn2, errorThrower, reader, reader2, quit, txtArea);

    }
}
