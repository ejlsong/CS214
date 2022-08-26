//Eric Song
//112294760
//CSE214
//HW2

public class CpuCore {

    // TODO
    // instance variables...


    Process currentProcess;
    int coreID;
    int timesWorkedInRow;
    int timesteps;
    Queue<Process> queue;
    int positiveSteps;
    int cutoff;

    /**
     * Constructor for CpuCore.
     * CoreID, queue, and cutoff are set to specified parameters.
     * CurrentProcess is set to null and all of the counters are set to zero.
     * @param i the integer ID that will be associated with the CpuCore
     * @param q the queue of processes that the CpuCore will take processes from
     * @param c the maximum amount of times the CpuCore is allowed to work on a process at a time
     */
    public CpuCore(int i, Queue<Process> q, int c) {
        // TODO
        // set up this core
        currentProcess = null;
        coreID = i;
        timesWorkedInRow = 0;
        queue = q;
        positiveSteps = 0;
        timesteps = 0;
        cutoff = c;


    }

    /**
     * This method makes a CpuCore take a step.
     * The timestep is incremented by one.
     * If there is no process currently being worked on and the queue holding the processes is not empty, the CpuCore takes a process from the queue.
     * If there is a process currently being worked on, the method checks if the CpuCore worked on the process too many times in a row.
     * If the process has been worked on too many times in a row, the process is enqueued back into the queue and the current process is set to null.
     * Then, the amount of times worked in a row is set to zero.
     * If the current process is not finished, the CpuCore does work on the process.
     */
    public void step() {
        // TODO
        // either work on the current process, give the process back to the queue if we've worked on it, or claim the next process to work on
        timesteps++;


        if(currentProcess == null && !queue.isEmpty()){ //get a process if there is no process
            claimProcess();

        }
        else if(currentProcess != null){
            if (timesWorkedInRow == cutoff) {
                queue.enqueue(currentProcess); //put back in queue if met cutoff
                currentProcess = null;
                timesWorkedInRow = 0; //reset counter
            } else if (!currentProcess.isFinished()) {
                doWork();


            }

        }

    }

    /**
     * This method takes a process from the queue holding processes and gives it to a CpuCore.
     * PositiveSteps is incremented by one since it is a productive step.
     * The current process is set to the process that was just dequeued from the queue holding processes.
     */
    private void claimProcess() {
        if(!queue.isEmpty()) {

            currentProcess = queue.dequeue();
        }

    }

    /**
     * This method does work on the current process and then checks if it is finished or not.
     * The current process does work, and the positive steps and the amount of times worked in a row increment by one.
     * If the process is finished, the coreID, the current process's ID, and the CpuCore's timestep is printed out.
     * The current process is discarded by not putting it back in the queue and setting it to null.
     */
    private void doWork() {

        currentProcess.doWork();
        positiveSteps++; //efficient step
        timesWorkedInRow++;

        if(currentProcess.isFinished() == true){
            System.out.println(coreID + ", " + currentProcess.getID() + ", " + timesteps); //printed when done
            currentProcess = null;
            timesWorkedInRow = 0; //reset counter and currentProcess
        }

    }

}