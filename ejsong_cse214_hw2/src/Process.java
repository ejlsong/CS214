//Eric Song
//112294760
//CSE214
//HW2

public class Process {


    int size;
    String id;
    int progress;

    /**
     * Constructor for Process.
     * Size and id are set to specified variables. Progress is set to zero.
     * @param s the size that the process will have
     * @param i the ID that will be associated with the process
     */
    public Process(int s, String i) {

        size = s;
        id = i;
        progress = 0;
    }


    /**
     * This method increments the progress of the Process by 1.
     */
    public void doWork() {

        progress++;
    }

    /**
     * This method returns the ID of type String of the Process.
     * @return the ID associated with the Process
     */
    public String getID(){
        return id;
    }

    /**
     * This method determines whether the Process is finished or not.
     * The method uses an if statement to check if its progress is equal to its size.
     * @return true if the Process's progress is equal to its size, returns false otherwise
     */

    public boolean isFinished(){
        if(progress == size){
            return true;
        }
        return false;
    }



}