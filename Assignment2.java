/** 
* This file has the main method for executing the Tennis Database Program
* @project CS-102 Assignment 1
* @author William Wakefield
* @date 06 June 2019
*/

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

	
import TennisDatabase.TennisDatabase;
import TennisDatabase.TennisDatabaseException;
import TennisDatabase.TennisDatabaseRuntimeException;
import TennisDatabase.TennisMatch;
import TennisDatabase.TennisPlayer;

public class Assignment2 extends Application {
	
	static TennisDatabase database = new TennisDatabase();
	
	  Desktop desktop = Desktop.getDesktop();
	
	// Console UI & File input/Loading
	public static void main(String[] args) {
		
		launch(args);
		
	}
	
		public void start(Stage primaryStage) throws Exception {
			
		     //Text Area where error messages show
	        TextArea errorArea = new TextArea();
	        
	        errorArea.setDisable(true);
	        
	        errorArea.setStyle("-fx-border-color: #000000 ; -fx-text-fill: #000000; -fx-font-size: 20");
	        
	        //Stage for the GUI
	        primaryStage.setTitle("Tennis Database Manager");
	        
	        Label playersTitle= new Label( "Tennis Database" );	
	        playersTitle.setFont( new Font( "Impact", 72 ) );
	        playersTitle.setStyle("-fx-background-color: #7FFF00; -fx-text-fill: #000000 ");
	        
	        Label matchesTitle = new Label("\t\t" + "Tennis Players" + "\t\t\t\t\t " + "Matches" );
	        matchesTitle.setFont( new Font( "Impact", 40 ) );

	        TableView<TennisPlayer> playerTableView = new TableView<TennisPlayer>();
	        TableView<TennisMatch> matchTableView = new TableView<TennisMatch>();
	        
	        //Player Table Headings
	        TableColumn<TennisPlayer, String> id = new TableColumn<TennisPlayer, String>("Id");
	        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
	        
	        TableColumn<TennisPlayer, String> name = new TableColumn<TennisPlayer, String>("Name");
	        name.setCellValueFactory(new PropertyValueFactory<>("FullName"));
	        
	        TableColumn<TennisPlayer, String> birthYear = new TableColumn<TennisPlayer, String>("BirthYear");
	        birthYear.setCellValueFactory(new PropertyValueFactory<>("BirthYear"));
	        
	        TableColumn<TennisPlayer, String> country = new TableColumn<TennisPlayer, String>("Country");
	        country.setCellValueFactory(new PropertyValueFactory<>("Country"));
	        
	        TableColumn<TennisPlayer, String> wins = new TableColumn<TennisPlayer, String>("Wins");
	        wins.setCellValueFactory(new PropertyValueFactory<>("Wins"));
	        
	        TableColumn<TennisPlayer, String> losses = new TableColumn<TennisPlayer, String>("Losses");
	        losses.setCellValueFactory(new PropertyValueFactory<>("Losses"));

	        playerTableView.getColumns().addAll(id, name, birthYear, country, wins, losses);
	        
	        playerTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

	        //Match Table Headings
	        TableColumn<TennisMatch, String> id1 = new TableColumn<TennisMatch, String>("Player 1 Id");
	        id1.setCellValueFactory(new PropertyValueFactory<>("IdPlayer1"));
	        
	        TableColumn<TennisMatch, String> id2 = new TableColumn<TennisMatch, String>("Player 2 Id");
	        id2.setCellValueFactory(new PropertyValueFactory<>("IdPlayer2"));
	        
	        TableColumn<TennisMatch, String[]> date = new TableColumn<TennisMatch, String[]>("Date");
	        date.setCellValueFactory(new PropertyValueFactory<>("DateString"));
	        
	        TableColumn<TennisMatch, String> tournament = new TableColumn<TennisMatch, String>("Tournament");
	        tournament.setCellValueFactory(new PropertyValueFactory<>("Tournament"));
	        
	        TableColumn<TennisMatch, String> scores = new TableColumn<TennisMatch, String>("Scores");
	        scores.setCellValueFactory(new PropertyValueFactory<>("MatchScore"));

	        matchTableView.getColumns().addAll(id1, id2, date, tournament, scores);
	        
	        matchTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

	        //Load From File Button and Parameters. 
	        
	        FileChooser fileChooser = new FileChooser();
	        
	        Button loadButton = new Button("Load From File");
	        
	        loadButton.setStyle("-fx-background-color: #7FFF00; -fx-text-fill: #000000 ");
	        
//	        TextField loadFileName = new TextField();
//	        
//	        loadFileName.setPromptText("File Name (if in current directory)");
	        
	        loadButton.setOnAction(
	        		
	                eventLoadFromFile -> {
	                	
	                	configureFileChooser( fileChooser );
	                	
                    	File file = fileChooser.showOpenDialog( primaryStage ); // Creates a Pop-up to choose your file
                    	
//                    	if( file != null ) { 
//                    		
//                    		openFile( file ); 
//                    		
//                    	} 
//	                	
	                    try {
	                    	
	                        database.loadFromFile(file.getPath());
	                        
	                        playerTableView.setItems(this.getPlayers());
	                        
	                        matchTableView.setItems(this.getMatches());
	                        
	                        errorArea.setText("Data Loaded in Tennis Database from: " + file.getPath());
	                        
	                    } catch (TennisDatabaseException | TennisDatabaseRuntimeException e) {
	                    	
	                        System.out.println(e.getMessage());
	                        errorArea.setText(e.getMessage());
	                        
	                    }
	                }
	        );
	        
			//Save to File Button and Parameters

	        FileChooser fileSaver = new FileChooser();
	        
	        Button saveButton = new Button("Save to File");
	        
	        saveButton.setStyle("-fx-background-color: #7FFF00; -fx-text-fill: #000000 ");
	        
//	        TextField saveFileName = new TextField();
	        
//	        saveFileName.setPromptText("File Name.txt");
	        
	        saveButton.setOnAction(
	        		
	                eventSaveToFile -> {
	                	
	                	configureFileChooser( fileSaver );
	                	
	                	File savedFile = fileSaver.showSaveDialog( primaryStage ); // Creates a Pop-up to choose your file
	                	
	                    try {
	                    	
//	                        String fileName = saveFileName.getText();
	                        
//	                        database.saveToFile(fileName);
	                        
	                        database.saveToFile(savedFile.getPath());
	                        
	                        openFile( savedFile ); 
	                        
	                    } catch (TennisDatabaseException e) {
	                    	
	                        System.out.println(e.getMessage());
	                        errorArea.setText(e.getMessage());
	                        
	                    }
	                }
	        );

	        //Delete Button and Parameters
	        Button deleteButton = new Button("Delete Player");
	        
	        deleteButton.setStyle("-fx-background-color: #FF0000 ; -fx-text-fill: #FFFFFF");
	        
	        TextField deleteId = new TextField();
	       
	        deleteId.setPromptText("Player Id");
	        
	        deleteButton.setOnAction(
	        		
	                deleteEvent -> {
	                	
	                    try{
	                    	
	                        database.deletePlayer(deleteId.getText());
	                        
	                        playerTableView.setItems(this.getPlayers());
	                        
	                        matchTableView.setItems(this.getMatches());
	                        
	                        errorArea.setText("Player " + deleteId.getText() + " Deleted from Database");
	                        
	                    } catch (TennisDatabaseRuntimeException e) {
	                    	
	                        System.out.println(e.getMessage());
	                        errorArea.setText(e.getMessage());
	                        
	                    }
	                }
	        );

	        //Reset Button and Parameters
	        Button resetButton = new Button("Reset Database");
	        
	        resetButton.setStyle("-fx-background-color: #FF0000 ; -fx-text-fill: #FFFFFF");
	        
	        resetButton.setOnAction(
	        		
	                eventReset -> {
	                	
	                    try {
	                    	
	                        database.reset();
	                        
	                        playerTableView.setItems(this.getPlayers());
	                        
	                        matchTableView.setItems(this.getMatches());
	                        
	                        errorArea.setText("Database has been resetted. Please load a new file or insert players & matches manually");
	                        
	                    } catch (TennisDatabaseRuntimeException e) {
	                    	
	                        System.out.println(e.getMessage());
	                        errorArea.setText(e.getMessage());
	                        
	                    }
	                }
	        );
	        
	        //Insert Player Button and Parameters
	        Button insertPlayerButton = new Button("Insert Player");
	        
	        TextField insertId = new TextField();
	        insertId.setPromptText("Player Id");
	        
	        TextField insertFName = new TextField();
	        insertFName.setPromptText("First Name");
	        
	        TextField insertLName = new TextField();
	        insertLName.setPromptText("Last Name");
	        
	        TextField insertBirthYear = new TextField();
	        insertBirthYear.setPromptText("Birth Year <YYYY>");
	        
	        TextField insertCountry = new TextField();
	        insertCountry.setPromptText("Country");
	        
	        insertPlayerButton.setOnAction(
	        		
	                insertPlayerEvent -> {
	                	
	                    try {
	                    	
	                        database.insertPlayer(insertId.getText(), insertFName.getText(), insertLName.getText(), Integer.valueOf(insertBirthYear.getText()), insertCountry.getText());
	                        
	                        playerTableView.setItems(this.getPlayers());
	                        
	                        matchTableView.setItems(this.getMatches());
	                        
	                        errorArea.setText(insertFName.getText() + " " + insertLName.getText() + " (" + insertId.getText() + ")" + " has been added");
	                        
	                        insertId.clear();
	                        insertFName.clear();
	                        insertLName.clear();
	                        insertBirthYear.clear();
	                        insertCountry.clear();
	                        
	                    } catch (TennisDatabaseException e ) {
	                    	
	                        System.out.println(e.getMessage());
	                        errorArea.setText(e.getMessage());
	                        
	                    }
	                }
	        );

	        ////Insert Button and Parameters
	        Button insertMatchButton = new Button("Insert Match");
	        
	        TextField insertId1 = new TextField();
	        insertId1.setPromptText("Player 1 Id");
	        
	        TextField insertId2 = new TextField();
	        insertId2.setPromptText("Player 2 Id")
	        ;
	        TextField insertYear = new TextField();
	        insertYear.setPromptText("Year <YYYY>");

	        TextField insertMonth = new TextField();
	        insertMonth.setPromptText("Month <MM>");

	        TextField insertDay = new TextField();
	        insertDay.setPromptText("Day <DD>");

	        TextField insertTournament = new TextField();
	        insertTournament.setPromptText("Tournament");

	        TextField insertScore = new TextField();
	        insertScore.setPromptText("Score");
	        
	        insertMatchButton.setOnAction(
	        		
	                insertMatchEvent -> {
	                	
	                    try {
	                    	
	                        database.insertMatch(insertId1.getText(), insertId2.getText(),
	                        		
	                                Integer.valueOf(insertYear.getText()),Integer.valueOf(insertMonth.getText()),
	                                
	                                Integer.valueOf(insertDay.getText()), insertTournament.getText(), insertScore.getText() );
	                        
	                        playerTableView.setItems(this.getPlayers());
	                        
	                        playerTableView.refresh();
	                        
	                        matchTableView.setItems(this.getMatches());
	                        
	                        errorArea.setText("Match between " + insertId1.getText() + " & " + insertId2.getText() + " has been added");
	                        
	                        insertId1.clear();
	                        insertId2.clear();
	                        insertYear.clear();
	                        insertMonth.clear();
	                        insertDay.clear();
	                        insertTournament.clear();
	                        insertScore.clear();
	                        
	                    } catch (TennisDatabaseException e) {
	                    	
	                        System.out.println(e.getMessage());
	                        
	                        errorArea.setText(e.getMessage());
	                        
	                    }
	                }
	        );



	        //HBox with the database tables
	        HBox databaseHBox = new HBox(30);
	        
	        databaseHBox.getChildren().addAll( playerTableView,  matchTableView);

	        //VBox with the load, save, reset, and delete actions
	        VBox vBox = new VBox(30);
	        
	        vBox.setSpacing(5);
	        vBox.setPadding(new Insets(10, 0, 0, 10));
	        
	        vBox.getChildren().addAll( resetButton, playersTitle, matchesTitle, databaseHBox, loadButton, saveButton, deleteButton, deleteId);

	        //VBox set to the right side for inserting players and matches
	        VBox sideBar = new VBox(2);
	        
	        sideBar.setSpacing(20);
	        sideBar.setPadding(new Insets(100,300,10,10));
	        sideBar.getChildren().addAll(insertPlayerButton, insertId, insertFName, insertLName, insertBirthYear,
	                insertCountry, insertMatchButton, insertId1, insertId2, insertYear, insertMonth, insertDay, insertTournament, insertScore);
	        
	        //Set up borders where error, tables, and insert matches/players are
	        BorderPane borderPane = new BorderPane();
	        
	        borderPane.setLeft(vBox);
	        
	        borderPane.setRight(sideBar);
	        
	        borderPane.setBottom(errorArea);
	        
	        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	        int width = gd.getDisplayMode().getWidth(); 
	        int height = gd.getDisplayMode().getHeight();
	        
	        Scene scene = new Scene(borderPane, width, height);
	        
	        primaryStage.setScene(scene);
	        
	        primaryStage.show();
	        
	    }
		
		  private void openFile( File file ) {
	        	
	        	try {
	        		
	        		desktop.open( file ); 
	        		
	        	} catch( IOException e ) {
	        		
	        	Logger.getLogger(Assignment2.class.getName()).log(Level.SEVERE,null,e); 
	        	
	        	}
	        }
	        
		  //Desc.: Configures what type of file can be chosen (Only supports .txt at this time)
		  //Input: none
		  //Output: Configures popup for type of file to be modified
	       private static void configureFileChooser( FileChooser fileChooser ) {
	    	   
	        	fileChooser.setTitle( "Load Tennis Player Data from Text File" );
	        	
	        	fileChooser.setInitialDirectory( new File( System.getProperty( "user.home" ) ) );
	        	
	        	fileChooser.getExtensionFilters().addAll(
	        	new FileChooser.ExtensionFilter( "Text Files", "*.txt" )
	        	);
	        }
	        
	    //Desc.: Places the data to a string within the Tennis Players table
	    //Input: TennisPlayer Object   
		//Output: ObservableList of TennisPlayer[] for GUI
	    public ObservableList<TennisPlayer> getPlayers() {
	    	
	        TennisPlayer[] playerContainerArray = database.getAllPlayers();
	        
	        ObservableList<TennisPlayer> players = FXCollections.observableArrayList(playerContainerArray);
	        
	        return players;
	        
	    }
	    
	    //Desc.: Places the data to a string within the match table
	    //Input: TennisMatch Object
	    //Output: ObservableList of TennisMatch[] for GUI
	    public ObservableList<TennisMatch> getMatches() {
	    	
	        ObservableList<TennisMatch> matches = FXCollections.observableArrayList();

	        try {
	        	
	            matches.addAll(database.getAllMatches());
	            
	        } catch (TennisDatabaseRuntimeException e) {
	        	
	            throw e;
	            
	        }
	        
	        return matches;
	    }
	    
	    

}
