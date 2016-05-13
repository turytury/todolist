package todolist;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodolistRepository extends MongoRepository<TodoModel, String> {
}
