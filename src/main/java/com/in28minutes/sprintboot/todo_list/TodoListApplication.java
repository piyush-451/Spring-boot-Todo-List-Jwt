package com.in28minutes.sprintboot.todo_list;

import com.in28minutes.sprintboot.todo_list.DataModels.Role;
import com.in28minutes.sprintboot.todo_list.DataModels.User;
import com.in28minutes.sprintboot.todo_list.Repository.RoleRepo;
import com.in28minutes.sprintboot.todo_list.Repository.UserRepo;
import org.hibernate.Hibernate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ComponentScan(basePackages = "com.in28minutes.sprintboot.todo_list")
@EnableAspectJAutoProxy
public class TodoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);

	}

	@Bean
	public CommandLineRunner insertRolesAndAdmin(RoleRepo roleRepository, UserRepo userRepo) {
		return args -> {
			if (!roleRepository.existsByName("ROLE_ADMIN")) {
				roleRepository.save(new Role("ROLE_ADMIN"));
			}
			if (!roleRepository.existsByName("ROLE_USER")) {
				roleRepository.save(new Role("ROLE_USER"));
			}

//			Role roleAdmin = roleRepository.findByRolename("ROLE_ADMIN");
//			Role roleUser = roleRepository.findByRolename("ROLE_USER");
//
//			User adminUser;
//			if (!userRepo.existsByUsername("admin")) {
//				adminUser = new User("admin", "admin@123");
//			} else {
//				adminUser = userRepo.findByUsername("admin");
//			}
//
//
//			if (!adminUser.getRoleList().contains(roleAdmin)) {
//				adminUser.getRoleList().add(roleAdmin);
//				roleUser.getUserList().add(adminUser);
//
//			}
//			if (!adminUser.getRoleList().contains(roleUser)) {
//				adminUser.getRoleList().add(roleUser);
//				roleAdmin.getUserList().add(adminUser);
//			}
//
//			System.out.println("adminUser: " + adminUser);
//			userRepo.save(adminUser);
//			roleRepository.save(roleAdmin);
//			roleRepository.save(roleUser);


		};
	}


}
