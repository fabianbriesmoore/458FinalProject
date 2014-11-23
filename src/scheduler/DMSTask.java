package scheduler;

import java.util.PriorityQueue;

public class DMSTask extends Task{
	
	public DMSTask(int computation_time, int period, int deadline){
		super(computation_time, period, deadline);
	}
	
	public DMSTask(int computation_time, int period, int deadline, int task_id){
		super(computation_time, period, deadline, task_id);
	}

	@Override
	public int compareTo(Task t) {
		return this.getDeadline() - t.getDeadline();
	}

	@Override
	public void addTask(PriorityQueue<Task> task_set, Task t, int current_time) {
		Task new_task = getCopy();
		new_task.setAbsolute_deadline(current_time + new_task.getDeadline());
		task_set.add(new_task);
	}
	
	public Task getCopy() {
		return new DMSTask(computation_time, period, deadline, task_id);
	}

}
