package guru.springframework.spring5webapp.domain.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.domain.repositories.AuthorRepository;
import guru.springframework.spring5webapp.domain.repositories.BookRepository;
import guru.springframework.spring5webapp.domain.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BootStrapData implements CommandLineRunner {

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;

	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Author eric = new Author("Eric", "Evans");
		Book ddd = new Book("Domain Driven Design", "123123");
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);

		authorRepository.save(eric);
		bookRepository.save(ddd);

		Author rod = new Author("Rod", "Johnson");
		Book noEJB = new Book("J2EE Development without EJB", "393945459");
		rod.getBooks().add(noEJB);
		noEJB.getAuthors().add(rod);

		authorRepository.save(rod);
		bookRepository.save(noEJB);

		System.out.println("Started in Bootstrap");
		System.out.println("Number of Books: " + bookRepository.count());

		Publisher publisher = new Publisher("Test", "100 test ave", "Minneapolis", "MN", "55419");

		publisherRepository.save(publisher);
		Optional<Publisher> result = publisherRepository.findById(publisher.getId());

		System.out.println("Number of Publishers: " + publisherRepository.count());
		System.out.println(result.get());
	}
}
