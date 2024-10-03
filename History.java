import java.util.Stack;
public class History
{

	

    /**
       Notepad will call this function when thier text changes.

       deletion is a boolean that indicates if the action was a deletion of text or the insertion of text.
       position is the postion where the change took place
       Change is the string of characters that is the change.
     */
   public void addEvent(boolean deletion, int position, String Change)
   {
   }


    /**
       Notepad will call this function when it wishes to undo the last event.

       note is a variable to the Notepad that called this function
     */
   public void undoEvent(NotePad note)
   {
   }


    /**
       Notepad will call this function when it wishes to redo the last event that was undone.
       Note that new actions should clear out events that can be "redone"
       note is a variable to the Notepad that called this function
     */
   public void redoEvent(NotePad note)
   {
   	
   }

    /**
       returns true if there is undo data in the History
     */
   public boolean hasUndoData()
   {
       return false;
   }

    /**
       returns true if there is undo data in the History
     */
   public boolean hasReDoData()
   {
       return false;
   }
	

}
