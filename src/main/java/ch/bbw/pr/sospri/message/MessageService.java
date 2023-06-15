package ch.bbw.pr.sospri.message;

import ch.bbw.pr.sospri.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * MessageService
 * 
 * @author Peter Rutschmann
 * @version 25.06.2020
 */
@Service
public class MessageService {
	@Autowired
	private MessageRepository repository;
	
	public Iterable<Message> getAll(){
		return repository.findAll();
	}

	public void add(Message message) {
		repository.save(message);
	}

	public Message getById(Long id) {
		Iterable<Message> messageitr = repository.findAll();

		for(Message message: messageitr){
			if (message.getId() == id) {
				return message;
			}
		}
		System.out.println("MessageService:getById(), id does not exist in repository: " + id);
		return null;
	}

	public void update(Long id, Message message) {
		//save geht auch für update.
		repository.save(message);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
