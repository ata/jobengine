package org.dynebolic.jobengine;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.dynebolic.jobengine.entity.Employer;
import org.dynebolic.jobengine.entity.Job;
import org.dynebolic.jobengine.entity.JobSkill;
import org.dynebolic.jobengine.entity.Role;
import org.dynebolic.jobengine.entity.User;
import org.dynebolic.jobengine.hibernate.support.EMUtil;
import org.dynebolic.jobengine.service.EmployerService;
import org.dynebolic.jobengine.service.JobService;
import org.dynebolic.jobengine.service.PasswordService;
import org.dynebolic.jobengine.service.UserService;

public class InitialData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*
		UserService userService = new UserService();
		
		System.out.println(PasswordService.encrypt("rahasia"));
		
		Role role = new Role();
		role.setName("Administrator");
		
		User user = new User();
		user.setUsername("admin");
		user.setEmail("admin@admin.com");
		user.setPassword("rahasia");
		user.hashPassword();
		user.setRole(role);
		userService.save(user);
		*/
		EmployerService employerService = new EmployerService();
		JobService service = new JobService();
		Employer employer = employerService.find(new Long(180));
		
		System.out.println(service.findSubmit(employer));
		
		
	}

}
