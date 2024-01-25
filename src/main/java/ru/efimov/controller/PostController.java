package ru.efimov.controller;

import org.springframework.web.bind.annotation.*;
import ru.efimov.model.Post;
import ru.efimov.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<Post> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable long id) {
        return service.getById(id);
    }

    @PostMapping
    public Post save(@RequestBody Post post) {
        return service.save(post);
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable long id) {
        service.removeById(id);
    }

    public void removeById(long id, HttpServletResponse response) {
        service.removeById(id);
    }
}
