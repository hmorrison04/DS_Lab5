import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class NotePad extends Application implements ChangeListener<String>
{

   private History history = new History();
   
   public NotePad()
   {
   	
   }
	
	
   private TextArea area;
   private Button undo;
   private Button redo;


   /**
      Initialize the window and connect all of the buttons
    */
   @Override
   public void start(Stage stage) throws Exception
   {
   
      BorderPane pane = new BorderPane();
      area= new TextArea();
      area.textProperty().addListener(this);
      pane.setCenter(area);
   	
      //buttons
      HBox buttons = new HBox(8);
   	
      undo = new Button("Undo");
      redo = new Button("ReDo");
      buttons.getChildren().addAll(undo,redo);
      pane.setTop(buttons);
   	
      undo.setOnAction(e->{history.undoEvent(this);});
      redo.setOnAction(e->{history.redoEvent(this);});
   	
      undo.setDisable(true);
      redo.setDisable(true);
   	
      Scene scene = new Scene(pane,600,600);
   
      stage.setTitle("NotePad, Now with Undo");
      stage.setScene(scene);
      stage.show();
   }
	
	
	
	
   public static void main(String [] args)
   {
      launch(args);
   
   }


   /**
      This is a method that can be called (say from History)
      to insert text into the Notepad
    */
   public void insert(int position, String change)
   {
      area.textProperty().removeListener(this);
   	
      String text =  area.getText();
      String newText =text.substring(0,position)+change+text.substring(position);
      area.setText(newText);
   	
      area.positionCaret(position+change.length());
   
      area.textProperty().addListener(this);
   	
      undo.setDisable(! history.hasUndoData());
      redo.setDisable(! history.hasReDoData());
   }

   /**
      This is a method that can be called (say from History)
      to remove text from the Notepad
    */
   public void remove(int position, int length)
   {
      area.textProperty().removeListener(this);
   	
      String text =  area.getText();
      String newText =text.substring(0,position);
      if(position+length<text.length())
      {
         newText +=text.substring(position+length);			
      }
   
      area.setText(newText);
      area.positionCaret(position);
   
      area.textProperty().addListener(this);
      undo.setDisable(! history.hasUndoData());
      redo.setDisable(! history.hasReDoData());
   }



/**
 * Used for the changes in the textArea
 * (non-Javadoc)
 * @see javafx.beans.value.ChangeListener#changed(javafx.beans.value.ObservableValue, java.lang.Object, java.lang.Object)
 */
   @Override
   public void changed(ObservableValue<? extends String> observable,
   		String old,String curr)
   {
      undo.setDisable(false);
      redo.setDisable(true);
   	
      int len = Math.min(old.length(), curr.length());
      int lenMax = Math.max(old.length(), curr.length());
      int changePos = 0;
      boolean deletion = true;
      while(changePos < len && old.charAt(changePos)==curr.charAt(changePos))
      {
         changePos++;
      }
   	
      deletion = (old.length() > curr.length());
      int changeLen = lenMax-len;
      String change = "";
      if(deletion)
      {
         change = old.substring(changePos, changePos+changeLen);
      }
      else
      {
         change = curr.substring(changePos, changePos+changeLen);
      }
   	
   	
      history.addEvent(deletion, changePos, change);
   }

}
