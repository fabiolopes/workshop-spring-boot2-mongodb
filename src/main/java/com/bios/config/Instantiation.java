package com.bios.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.bios.domain.Post;
import com.bios.domain.User;
import com.bios.dto.AuthorDTO;
import com.bios.dto.CommentDTO;
import com.bios.repository.PostRepository;
import com.bios.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		postRepository.deleteAll();
		userRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia.", " Acordei feliz hoje!", new AuthorDTO(maria));		
		
		CommentDTO c1 = new CommentDTO(new AuthorDTO(alex), sdf.parse("21/03/2018"), "Boa viagem mano!");
		CommentDTO c2 = new CommentDTO(new AuthorDTO(bob), sdf.parse("22/03/2018"), "Aproveite");
		CommentDTO c3 = new CommentDTO(new AuthorDTO(alex), sdf.parse("23/03/2018"), "Tenha um ótimo dia!");
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
