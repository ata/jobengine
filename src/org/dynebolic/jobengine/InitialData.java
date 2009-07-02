package org.dynebolic.jobengine;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.dynebolic.jobengine.entity.Job;
import org.dynebolic.jobengine.entity.JobSkill;
import org.dynebolic.jobengine.entity.Role;
import org.dynebolic.jobengine.entity.User;
import org.dynebolic.jobengine.hibernate.support.EMUtil;
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
		
		
		JobService service = new JobService();
		Job job = service.find(new Long(152));
		System.out.println(job);
		System.out.println(job.getSkillRequirements());
		/*
		JobService service = new JobService();
		Job job = new Job();
		List<JobSkill> list = new ArrayList<JobSkill>();
		JobSkill s1 = new JobSkill();
		job.setTitle("HEllo");
		s1.setName("Java");
		s1.setJob(job);
		JobSkill s2 = new JobSkill();
		s2.setName("Ruby");
		s2.setJob(job);
		list.add(s1);
		list.add(s2);
		job.setSkillRequirements(list);
		service.save(job);
		
		*/
		
	}

}
