package ca.sheridancollege.mohamriz.controllers;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.mohamriz.beans.Store;
import ca.sheridancollege.mohamriz.emails.EmailServiceImpl;
import ca.sheridancollege.mohamriz.repositories.StoreRepository;

@Controller
public class FinalController {

	@Autowired
	private StoreRepository storeRepo;
	
	@Autowired
	private EmailServiceImpl esi;

	@GetMapping("/")
	public String root() {
		return "root.html";
	}

	@GetMapping("/login")
	public String login() {
		return "login.html";
	}

	@GetMapping("/denied")
	public String denied() {
		return "/error/denied.html";
	}

	@GetMapping("/addItem")
	public String addItem(Model model, @ModelAttribute Store store) {
		model.addAttribute("store", new Store());
		return "additem.html";
	}

	@GetMapping("/addItemList")
	public String addItemList(Model model, @ModelAttribute Store store) {
		storeRepo.addItem(store);
		model.addAttribute("store", new Store());
		return "additem.html";
	}

	@GetMapping("/viewItems")
	public String viewItems(Model model, @ModelAttribute Store store) {
		model.addAttribute("items", storeRepo.getStoreItems());
		return "viewitems.html";
	}

	@GetMapping("/edit/{id}")
	public String loadEdit(@PathVariable long id, Model model) {
		Store s = storeRepo.getItemById(id);
		model.addAttribute("item", s);
		return "edititem.html";
	}

	@GetMapping("/editItem")
	public String editItem(Model model, @ModelAttribute Store store) {
		storeRepo.editItem(store);
		model.addAttribute("item", storeRepo.getStoreItems());
		return "redirect:/viewItems";
	}

	@GetMapping("/delete/{id}")
	public String deleteItem(@PathVariable long id) {
		storeRepo.deleteItem(id);
		return "redirect:/viewItems";
	}
	
	@GetMapping("/email")
	public String emailPage() {
		return "sendemail.html";
	}
	
	
	@GetMapping("/sendEmail")
	public String email(@RequestParam String email, Model model) {
		try {
			model.addAttribute("contacts", storeRepo.getStoreItems());
			esi.sendMailWithThymeleaf(email, "Final Exam", "Professor Jon", storeRepo.getStoreItems(),
					"Thank You");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/";}
	
}
