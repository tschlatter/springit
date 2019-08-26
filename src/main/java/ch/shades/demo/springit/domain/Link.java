package ch.shades.demo.springit.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Link extends Auditable {
	
	@Id
	@GeneratedValue
	private Long	id;
	private String	title;
	private String 	url;
	
	@OneToMany(mappedBy = "link")
	private List<Comment> comments = new ArrayList<>();
	
}
