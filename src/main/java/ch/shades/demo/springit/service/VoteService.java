package ch.shades.demo.springit.service;

import org.springframework.stereotype.Service;

import ch.shades.demo.springit.domain.Vote;
import ch.shades.demo.springit.repository.VoteRepository;

@Service
public class VoteService {

	private final VoteRepository voteRepository;
	
	public VoteService(VoteRepository voteRepository) {
		this.voteRepository = voteRepository;
	}
	
	public Vote save(Vote vote) {
		return voteRepository.save(vote);
	}

}
