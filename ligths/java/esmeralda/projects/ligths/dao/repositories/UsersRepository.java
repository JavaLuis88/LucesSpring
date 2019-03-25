package esmeralda.projects.ligths.dao.repositories;






import org.springframework.data.repository.CrudRepository;


import esmeralda.projects.ligths.dao.entities.Users;

public interface UsersRepository extends CrudRepository<Users, Integer> {

	
	public Users findByUsername(String username);
	public boolean existsByUsername(String username);  
}


