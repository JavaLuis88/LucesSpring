package esmeralda.projects.ligths.web.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import esmeralda.projects.ligths.beans.Ligths;
import esmeralda.projects.ligths.beans.NetworkConfig;
import esmeralda.projects.ligths.beans.UserInfo;
import esmeralda.projects.ligths.business.ILigthsConfig;
import esmeralda.projects.ligths.business.IPoweroffReboot;
import esmeralda.projects.ligths.business.IRouterConfig;
import esmeralda.projects.ligths.business.IUserManager;
import esmeralda.projects.ligths.business.SHA256Encoder;
import esmeralda.projects.ligths.dao.entities.Roles;
import esmeralda.projects.ligths.dao.entities.Users;

@Controller
public class LigthsController {
	@Autowired
	IUserManager usermanager;

	@Autowired
	IRouterConfig routerconfig;

	
	@Autowired
	IPoweroffReboot poweroffrebootconfig;

	
	@Autowired
	ILigthsConfig ligthsconfig;
	
	
	@RequestMapping(value = "/")
	public ModelAndView indexPublic() {

		return new ModelAndView("index");

	}

	@RequestMapping(value = "/back/users.html")
	public ModelAndView usersBack() {
		HashMap<String, Object> model;
		model = new HashMap<String, Object>();
		model.put("sessionusername", SecurityContextHolder.getContext().getAuthentication().getName());

		return new ModelAndView("back/usersback", "model", model);

	}

	@RequestMapping(value = "/back/network.html")
	public ModelAndView networkBack() {
		HashMap<String, Object> model;
		NetworkConfig networkconfig;
		networkconfig = new NetworkConfig(routerconfig.getSSID(), routerconfig.getPassword(), routerconfig.getChannel(),
				routerconfig.getWPAtype());
		model = new HashMap<String, Object>();
		model.put("sessionusername", SecurityContextHolder.getContext().getAuthentication().getName());
		model.put("formnetwork", networkconfig);
		return new ModelAndView("back/network", "model", model);

	}


	@RequestMapping(value = "/back/ligths.html")
	public ModelAndView ligthsBack() {
		HashMap<String, Object> model;
		Ligths ligths;
		ligths = new Ligths(ligthsconfig.getSecretKey(),ligthsconfig.getPin());
		model = new HashMap<String, Object>();
		model.put("sessionusername", SecurityContextHolder.getContext().getAuthentication().getName());
		model.put("formligths", ligths);
		return new ModelAndView("back/ligths", "model", model);

	}


	
	@RequestMapping("/back/accessdenied")
	public String accessdenied(Model model) {
		return "back/accessdenied";
	}

	/////////////
	//URL AJAX//
	///////////

	@ResponseBody
	@RequestMapping(value = "/back/getuserinfo")
	public ArrayList<UserInfo> getUserInfo() {

		Iterable<Users> users;
		Iterator<Users> userslist;
		Users user;
		UserInfo userinfo;
		List<Roles> roles;
		List<String> userroles;
		ArrayList<UserInfo> retval;
		retval = new ArrayList<UserInfo>();

		users = this.usermanager.getAllUsers();
		userslist = users.iterator();

		while (userslist.hasNext()) {

			user = userslist.next();
			roles = this.usermanager.getUserRoles(user.getId());
			userroles = new ArrayList<String>();
			for (int i = 0; i < roles.size(); i++) {

				userroles.add(roles.get(i).getRole());

			}
			userinfo = new UserInfo(user.getUsername(), "", userroles);
			retval.add(userinfo);
		}

		return retval;

	}

	@ResponseBody
	@RequestMapping(value = "/back/adduser", method = RequestMethod.POST)

	public int AddUserInfo(@RequestBody UserInfo userinfo) {
		Users user;
		ArrayList<Roles> roles;

		int retval;
		retval = 0;

		retval = this.validateuserinfo(userinfo, true);
		if (retval == 0) {

			user = new Users(0, userinfo.getUsername(), (new SHA256Encoder()).encode(userinfo.getPassword()));
			roles = new ArrayList<Roles>();
			for (int i = 0; i < userinfo.getRoles().size(); i++) {
				roles.add(new Roles(0, userinfo.getRoles().get(i), 0));
			}
			retval = this.usermanager.addUser(user, roles);

			if (retval == 3) {

				retval = 4;

			} else if (retval != 0) {

				retval = 5;
			}

		}

		return retval;
	}

	@ResponseBody
	@RequestMapping(value = "/back/edituser", method = RequestMethod.POST)

	public int editUserInfo(@RequestBody UserInfo userinfo) {

		Users user;
		ArrayList<Roles> roles;

		int retval;
		retval = 0;

		retval = this.validateuserinfo(userinfo, false);
		if (retval == 0) {

			user = this.usermanager.getUserByUserName(userinfo.getUsername());
			if (user != null) {
				if (userinfo.getPassword() != null && userinfo.getPassword().trim().equals("") != true) {
					user.setPassword((new SHA256Encoder()).encode(userinfo.getPassword()));
				}
				roles = new ArrayList<Roles>();
				for (int i = 0; i < userinfo.getRoles().size(); i++) {
					roles.add(new Roles(0, userinfo.getRoles().get(i), 0));
				}
				retval = this.usermanager.updateUser(user);

				if (retval == 3) {

					retval = 6;

				} else if (retval != 0) {

					retval = 5;
				} else if (user.getUsername()
						.equals(SecurityContextHolder.getContext().getAuthentication().getName()) == false) {

					if (this.updateUserRoles(user.getId(), roles) != 0) {

						retval = 5;

					}

				}
			} else {

				retval = 6;

			}

		}

		return retval;
	}

	@ResponseBody
	@RequestMapping(value = "/back/deleteuser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public int deleteUser(@RequestParam("username") String username) {

		Users user;
		ArrayList<Roles> roles;

		int retval;
		retval = 0;

		user = this.usermanager.getUserByUserName(username);
		if (username == "" || username.trim().equals("") == true) {

			retval = 2;

		} else if (username.equals(SecurityContextHolder.getContext().getAuthentication().getName()) == true) {

			retval = 1;

		}

		else if (user != null && this.usermanager.deleteUser(user.getId()) != 0) {

			retval = 2;

		}

		return retval;
	}

	@ResponseBody
	@RequestMapping(value = "/back/networksave", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public int networkBackPost(@RequestBody NetworkConfig networkConfig) {

		int retval;
		retval = 0;

		try {

			this.routerconfig.setSSID(networkConfig.getSsid());
			this.routerconfig.setPassword(networkConfig.getPassword());
			this.routerconfig.setChannel(networkConfig.getChannel());
			this.routerconfig.setWPAType(networkConfig.getSecuritytype());
			this.routerconfig.saveConfig();
		} catch (IllegalArgumentException e) {

			retval = 1;
		} catch (IOException e) {

			retval = 2;

		}
		return retval;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/back/reboot", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public int reboot() {




		return this.poweroffrebootconfig.reboot();
	}
	

	
	@ResponseBody
	@RequestMapping(value = "/back/poweroff", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public int poweroff() {




		return this.poweroffrebootconfig.poweroff();
	}
	
	

	@ResponseBody
	@RequestMapping(value = "/back/ligthssave", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public int ligthsBackPost(@RequestBody Ligths ligths) {


		int retval;
		retval = 0;
		
		try {

			this.ligthsconfig.setSecretKey(ligths.getSecretkey());
			this.ligthsconfig.setPin(ligths.getPin());
			this.ligthsconfig.saveConfig();
			if (this.poweroffrebootconfig.reboot()!=0) {
				
				retval=3;
			}
			
			
		} catch (IllegalArgumentException e) {

			retval = 1;
		} catch (IOException e) {

			retval = 2;

		}
		
		return retval;
		
	}


	/////////////////////
	//MÉTODOS PRIVADOS//
	///////////////////

	private int validateuserinfo(UserInfo userinfo, boolean adduser) {

		List<String> roles;
		List<String> validroles;
		boolean valid;
		int retval;
		int i;

		roles = null;
		validroles = null;
		valid = true;
		retval = 0;
		i = 0;

		if (userinfo.getUsername() == null || userinfo.getUsername().trim().equals("") == true
				|| userinfo.getUsername().trim().equals(userinfo.getUsername()) == false
				|| userinfo.getUsername().length() < 6 || userinfo.getUsername().length() > 12) {

			retval = 1;

		} else if (adduser == true
				&& (userinfo.getPassword() == null || userinfo.getPassword().trim().equals("") == true
						|| userinfo.getPassword().trim().equals(userinfo.getPassword()) == false
						|| userinfo.getPassword().length() < 6 || userinfo.getPassword().length() > 12)) {

			retval = 2;

		} else if (adduser == false && userinfo.getPassword() != null
				&& userinfo.getPassword().trim().equals("") == false
				&& (userinfo.getPassword().trim().equals(userinfo.getPassword()) == false
						|| userinfo.getPassword().length() < 6 || userinfo.getPassword().length() > 12)) {

			retval = 2;

		}

		else {

			roles = userinfo.getRoles();
			validroles = new ArrayList<String>();
			validroles.add("ROLE_USER");
			validroles.add("ROLE_ADMIN");

			while (i < roles.size() && valid == true) {

				valid = validroles.contains(roles.get(i));
				i++;

			}

			if (valid == false) {

				retval = 3;

			}

		}
		return retval;

	}

	private int updateUserRoles(Integer userid, ArrayList<Roles> roles) {

		int retval;
		int i = 0;
		retval = 0;

		retval = this.usermanager.deleteAllUserRoles(userid);
		if (retval == 0) {

			while (i < roles.size() && retval == 0) {

				retval = this.usermanager.addUserRol(userid, roles.get(i).getRole());
				i++;

			}

		}

		return retval;

	}

}
