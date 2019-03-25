package esmeralda.projects.ligths.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import esmeralda.projects.ligths.dao.entities.Task;
import esmeralda.projects.ligths.dao.entities.Taskdays;

public interface ITaskManager {

	
	public Task getTaskById(Integer idtask);
	public Task getTaskByName(String taskname);
	public Iterable<Task> getAllTasks(); 	
	public boolean exitsTaskName(String taskname);
	public int addTask(Task task,ArrayList<Taskdays> taskdays);
	public int updateTask(Task task);
	public int deleteTask(Integer idtask);
	public Taskdays getTaskdaysById(Integer idtaskdays);
	public List<Taskdays> getTaskdays(Integer idtask);
    public Iterable<Taskdays> getAllTasksDays(); 
	public boolean hasgotTaskDate(Date datetask,int weekday);
	public int addTaskdays(Integer idtask,int weekday);
	public int updateTaskDays(Integer idtask,int weekday,int newweekday);
	public int deleteTaskDays(Integer idtaskdays);
	public int deleteAllTaskDays(Integer idtask); 

	
}
