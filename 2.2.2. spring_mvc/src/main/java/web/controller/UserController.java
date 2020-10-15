package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {

	UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/")
	public String printWelcome(ModelMap model) {
		model.addAttribute("userList", userService.allUsers());
		return "index";
	}

	@RequestMapping(value = "/new")
	public String newUser(ModelMap model){
		User user = new User();
		model.put("user", user);
		return "new";
	}

	@PostMapping(value = "/save")
	public String save(@ModelAttribute("user") User user){
		userService.add(user);
		return "redirect:/";
	}

	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") long id){
		userService.deleteById(id);
		return "redirect:/";
	}

	@RequestMapping(value = "/edit/{id}")
	public String edit (@PathVariable("id") long id, ModelMap model){
		User user = userService.getById(id);
		model.put("user", user);
		return "edit";
	}

	@PostMapping(value = "/editUser/{id}")
	public String editUser (@PathVariable("id") long id, @ModelAttribute("user") User user){
		userService.changeUser(id, user.getName(), user.getSurname(), user.getAddress());
		return "redirect:/";
	}
}

//TODO 1. Написать CRUD-приложение. Использовать Spring MVC + Hibernate.
//TODO 2. Должен быть класс User с произвольными полями (id, name и т.п.). ++
//TODO 3. В приложении должна быть страница, на которую выводятся все юзеры с возможностью добавлять, удалять и изменять юзера.++
//TODO 4. Конфигурация Spring через JavaConfig и аннотации, по аналогии с предыдущими проектами. Без использования xml. Без Spring Boot. ++
//TODO 5. Внесите изменения в конфигурацию для работы с базой данных. Вместо SessionFactory должен использоваться EntityManager. ++