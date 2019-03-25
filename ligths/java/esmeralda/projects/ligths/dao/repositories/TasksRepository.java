package esmeralda.projects.ligths.dao.repositories;



import org.springframework.data.repository.CrudRepository;

import esmeralda.projects.ligths.dao.entities.Task;

public interface TasksRepository extends CrudRepository<Task, Integer> {

	
	public Task findByTaskname(String taskname);
	public boolean existsByTaskname(String taskname);
	
	
}


