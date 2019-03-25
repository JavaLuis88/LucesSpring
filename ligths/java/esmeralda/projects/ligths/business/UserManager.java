package esmeralda.projects.ligths.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import esmeralda.projects.ligths.dao.entities.Roles;
import esmeralda.projects.ligths.dao.entities.Users;
import esmeralda.projects.ligths.dao.repositories.RolesRepository;
import esmeralda.projects.ligths.dao.repositories.UsersRepository;

@Service
public class UserManager implements IUserManager {

	@Autowired
	private UsersRepository usersrepository;

	@Autowired
	private RolesRepository rolesrepository;

	@Override
	public Users getUserById(Integer iduser) {

		Optional<Users> users;
		Users user;

		user = null;
		users = this.usersrepository.findById(iduser);

		if (users.isPresent() == true) {

			user = users.get();

		}

		return user;

	}

	@Override
	public Users getUserByUserName(String username) {

		Users user;

		user = null;
		user = this.usersrepository.findByUsername(username);

		return user;

	}

	@Override
	public Iterable<Users> getAllUsers() {
	
		Iterable<Users> userslist;
		String username;
		
		return this.usersrepository.findAll();
	}
	

	
	
	@Override
	public boolean exitsUserName(String username) {
		// TODO Auto-generated method stub
		return this.usersrepository.existsByUsername(username);
	}

	@Override
	public int addUser(Users user, ArrayList<Roles> roles) {
		Users newuser;
		Roles rol;
		int count;
		int retval;
		retval = 0;

		if (user == null || roles == null) {

			retval = 1;

		} else {

			try {

				if (this.exitsUserName(user.getUsername()) == false) {

					user.setId(null);
					this.usersrepository.save(user);
					newuser = this.usersrepository.findByUsername(user.getUsername());
					if (newuser != null) {

						count = roles.size();
						for (int i = 0; i < count; i++) {
							rol = roles.get(i);
							if (rol != null) {
								rol.setId(null);
								rol.setIduser(newuser.getId());
								roles.set(i, rol);
							}

						}

						this.rolesrepository.saveAll(roles);

					} else {

						retval = 4;

					}
				} else {
					retval = 3;

				}

			} catch (Throwable e) {

				retval = 2;

			}
		}
		return retval;
	}

	@Override
	public int updateUser(Users user) {

		Users newuser;
		int retval;
		retval = 0;

		if (user == null) {

			retval = 1;

		} else {

			try {

				newuser = this.usersrepository.findByUsername(user.getUsername());
				if (newuser != null) {

					user.setId(newuser.getId());
					this.usersrepository.save(user);
				} else {

					retval = 3;

				}

			} catch (Throwable e) {

				retval = 2;

			}
		}
		return retval;

	}

	@Override
	public int deleteUser(Integer iduser) {
		int retval;
		retval = 0;

		if (iduser == null) {

			retval = 1;

		} else {

			try {

				if (this.deleteAllUserRoles(iduser) == 0) {
					this.usersrepository.deleteById(iduser);
				}

			} catch (Throwable e) {

				retval = 2;

			}
		}
		return retval;
	}

	@Override
	public Roles getRolById(Integer idrol) {
		Optional<Roles> roles;
		Roles rol;

		rol = null;
		roles = this.rolesrepository.findById(idrol);

		if (roles.isPresent() == true) {

			rol = roles.get();

		}

		return rol;
	}

	@Override
	public List<Roles> getUserRoles(Integer iduser) {
		// TODO Auto-generated method stub
		return this.rolesrepository.findByIduser(iduser);
	}

	@Override
	public Iterable<Roles> getAllRoles() {
		// TODO Auto-generated method stub
		return this.rolesrepository.findAll();
	}

	@Override
	public boolean hasgotUserRole(Integer iduser, String rol) {
		// TODO Auto-generated method stub
		return this.rolesrepository.existsByIduserAndRole(iduser, rol);
	}

	@Override
	public int addUserRol(Integer iduser, String rol) {

		Users user;
		Roles userrol;
		int retval;

		userrol = null;
		retval = 0;

		if (iduser == null || rol == null || rol.trim().equals("") == true) {

			retval = 1;

		} else {

			try {

				user = this.getUserById(iduser);
				if (user != null) {

					if (this.hasgotUserRole(iduser, rol) == false) {

						userrol = new Roles();
						userrol.setRole(rol);
						userrol.setIduser(iduser);
						this.rolesrepository.save(userrol);

					} else {

						retval = 4;
					}

				} else {

					retval = 3;
				}

			} catch (Throwable e) {

				retval = 2;

			}
		}
		return retval;
	}

	@Override
	public int updateUserRol(Integer iduser, String rol, String newrol) {
		Users user;
		Roles userrol;
		int retval;

		userrol = null;
		retval = 0;

		if (iduser == null || rol == null || rol.trim().equals("") == true || newrol == null
				|| newrol.trim().equals("") == true) {

			retval = 1;

		} else {

			try {

				user = this.getUserById(iduser);
				if (user != null) {

					userrol = this.rolesrepository.findByIduserAndRole(iduser, rol);

					if (userrol != null && this.hasgotUserRole(iduser, newrol) == false) {

						userrol.setRole(newrol);
						this.rolesrepository.save(userrol);

					} else {

						retval = 4;
					}

				} else {

					retval = 3;
				}

			} catch (Throwable e) {

				retval = 2;

			}
		}
		return retval;
	}

	@Override
	public int deleteRol(Integer idrol) {
		int retval;
		retval = 0;

		if (idrol == null) {

			retval = 1;

		} else {

			try {
				if (this.rolesrepository.existsById(idrol) == true) {
					this.rolesrepository.deleteById(idrol);
				} else {
					retval = 3;

				}
			} catch (Throwable e) {

				retval = 2;

			}
		}
		return retval;
	}

	@Override
	public int deleteAllUserRoles(Integer iduser) {
		Users user;
		int retval;

		retval = 0;

		if (iduser == null) {

			retval = 1;

		} else {

			try {

				user = this.getUserById(iduser);
				if (user != null) {

					 this.rolesrepository.deleteByIduser(iduser);

				} else {

					retval = 3;
				}

			} catch (Throwable e) {

				retval = 2;

			}
		}
		return retval;
	}

}
