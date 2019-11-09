package ch.shades.demo.springit.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.shades.demo.springit.domain.Comment;
import ch.shades.demo.springit.domain.Link;
import ch.shades.demo.springit.repository.CommentRepository;
import ch.shades.demo.springit.service.CommentService;
import ch.shades.demo.springit.service.LinkService;


@Controller
public class LinkController {
	private static final Logger logger = LoggerFactory.getLogger(LinkController.class);

	private LinkService linkService;
	private CommentService commentService;

	public LinkController(LinkService linkService, CommentService commentService) {
		this.linkService = linkService;
		this.commentService = commentService;
	}

	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("links", linkService.findAll());
		return "link/list"; // The name of the template
	}

	@GetMapping("/link/{id}")
	public String read(@PathVariable Long id, Model model) {
		Optional<Link> link = linkService.findById(id);
		if (link.isPresent()) {
			Link currentLink = link.get();
			Comment comment = new Comment();
			comment.setLink(currentLink);
			
			model.addAttribute("link", currentLink);
			// Hier glaube ich, dass der comment im Model nicht notwendig ist. Da bereits mit link verknüpft und in der View auch von dort genommen wird
			model.addAttribute("comment", comment);
			model.addAttribute("success", model.containsAttribute("success"));
			return "link/view";
		} else {
			return "redirect:/";
		}

	}

	@GetMapping("/link/submit")
	public String newLinkForm(Model model) {
		model.addAttribute("link", new Link());
		return "link/submit";
	}

	@PostMapping("/link/submit")
	public String createLink(@Valid Link link, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			logger.info("Validation errors were found while submitting a new link.");
			model.addAttribute("link", link);

			return "link/submit";
		} else {
			// Save our link
			linkService.save(link);
			logger.info("New link was saved successfully");

			redirectAttributes
				.addAttribute("id", link.getId())
				.addFlashAttribute("success", true);

			return "redirect:/link/{id}";
		}
	}
	
	@Secured({"ROLE_USER"})
	@PostMapping("/link/comments")
	public String addComment(@Valid Comment comment, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			logger.info("There was a problem adding a new comment.");
		} else {
			commentService.save(comment);
			logger.info("New comment was saved successfully");
		}
		
		return "redirect:/link/" + comment.getLink().getId();
	}
}

// Old stuff in the course

//@RestController
//@RequestMapping("/links")
//public class LinkController {
//	private LinkRepository linkRepository;
//	
//	public LinkController(LinkRepository linkRepository) {
//		this.linkRepository = linkRepository;
//	}
//
//	// List
//	@GetMapping("/")
//	public List<Link> list(){
//		return linkRepository.findAll();
//	}
//	
//	// CRUD
//	
//	@PostMapping("/create")
//	public Link create(@ModelAttribute Link link) {
//		return linkRepository.save(link);
//	}
//	
//	@GetMapping("/{id}")
//	public Optional<Link> read(@PathVariable Long id) {
//		return linkRepository.findById(id);
//	}
//	
//	@PutMapping("/{id}")
//	public Link update(@PathVariable Long id, @ModelAttribute Link link) {
//		//TODO: Get the id
//		return linkRepository.save(link);
//	}
//	
//	@DeleteMapping("/{id}")
//	public void delete(@PathVariable Long id) {
//		linkRepository.deleteById(id);
//	}
//}
