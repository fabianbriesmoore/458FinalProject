package scheduler;

public abstract class Task implements Comparable<Task>, Schedulable{
	protected int computation_time;
	protected int period;
	protected int deadline;
	protected int absolute_deadline;
	protected int task_id;
	protected static int id = 0;

	public Task(int computation_time, int period, int deadline) {
		this.computation_time = computation_time;
		this.period = period;
		this.deadline = deadline;
		this.task_id = id++;
	}
	
	//added so task can be easily copied with correct id
	public Task(int computation_time, int period, int deadline, int task_id) {
		this.computation_time = computation_time;
		this.period = period;
		this.deadline = deadline;
		this.task_id = task_id;
	}

	public int getComputation_time() {
		return computation_time;
	}

	public int getPeriod() {
		return period;
	}

	public int getDeadline() {
		return deadline;
	}

	public int getAbsolute_deadline() {
		return absolute_deadline;
	}

	public void setAbsolute_deadline(int absolute_deadline) {
		this.absolute_deadline = absolute_deadline;
	}
	
	public int getTask_id() {
		return task_id;
	}

	public int decrementComputationTime() {
		return --computation_time;
	}
	
	public void updateDeadline(int current_time) {
	}
	
	public String toString() {
		String str = "Task id:" + getTask_id();
		str+= "\nComputation Time:" + getComputation_time();
		str+= "\nAbsolute Deadline:" + getAbsolute_deadline();
		return str;
	}
}
