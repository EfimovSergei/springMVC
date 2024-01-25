package ru.efimov.service;

import org.springframework.stereotype.Service;
import ru.efimov.exception.NotFoundException;
import ru.efimov.model.Post;
import ru.efimov.repository.PostRepository;

import java.util.List;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> all() {
        return repository.all();
    }

    public Post getById(long id) {
        return repository.getById(id).orElseThrow(() ->
                new NotFoundException(String.format("Запись с id=%d отсутствует", id)));
    }

    public Post save(Post post) {
        Post result = repository.save(post);
        if (result == null)
            throw new NotFoundException(
                    String.format("Не удалось обновить запись с id = %d т.к. она отсутствует",
                            post.getId()));
        return result;
    }

    public void removeById(long id) {
        repository.removeById(id);
    }
}