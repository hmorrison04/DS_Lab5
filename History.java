import java.util.Stack;
public class History
{

	Stack<Event> redoStack = new Stack<>();
	Stack<Event> undoStack = new Stack<>();

    /**
       Notepad will call this function when thier text changes.

       deletion is a boolean that indicates if the action was a deletion of text or the insertion of text.
       position is the postion where the change took place
       Change is the string of characters that is the change.
     */
   public void addEvent(boolean deletion, int position, String Change)
   {
	   Event newEvent = new Event(position, Change, deletion);
	   undoStack.push(newEvent);
	   redoStack.clear();
	   
	   
   }


    /**
       Notepad will call this function when it wishes to undo the last event.

       note is a variable to the Notepad that called this function
     */
   public void undoEvent(NotePad note)
   {
	   Event unEvent = undoStack.pop();
	   redoStack.push(unEvent);
	   if(unEvent.deletion)
	   {
		   note.insert(unEvent.pos, unEvent.changeVal);
	   } 
	   else 
	   {
		   note.remove(unEvent.pos, unEvent.changeVal.length());
		   
	   }
   }


    /**
       Notepad will call this function when it wishes to redo the last event that was undone.
       Note that new actions should clear out events that can be "redone"
       note is a variable to the Notepad that called this function
     */
   public void redoEvent(NotePad note)
   {

	   Event reEvent = redoStack.pop();
	   undoStack.push(reEvent);

	   if(reEvent.deletion)
	   {
		   note.remove(reEvent.pos, reEvent.changeVal.length());
		   undoStack.push(reEvent);
		   redoStack.clear();
	   } 
	   else 
	   {
		   note.insert(reEvent.pos, reEvent.changeVal);
		   undoStack.push(reEvent);
		   redoStack.clear();
	   }
	
   }

    /**
       returns true if there is undo data in the History
     */
   public boolean hasUndoData()
   {
	   if(!undoStack.isEmpty())
	   {
		   return true;
	   }
       return false;
   }

    /**
       returns true if there is undo data in the History
     */
   public boolean hasReDoData()
   {
	   if(!redoStack.isEmpty())
	   {
		   return true;
	   }
       return false;
   }
	

}
