package ru.efimov.repository;

import org.springframework.stereotype.Repository;
import ru.efimov.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Repository
public class PostRepositoryRecord implements PostRepository {
    private static final Map<Long, Post> storageMap = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(0);

    public List<Post> all() {
        return new ArrayList<>(storageMap.values());
    }

    public Optional<Post> getById(long id) {
        if (!storageMap.containsKey(id))
            return Optional.empty();
        return Optional.of(storageMap.get(id));
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            if (!storageMap.isEmpty()) {
                post.setId(idCounter.intValue() + 1);
                idCounter.incrementAndGet();
            }
            storageMap.put(post.getId(), post);
        } else {
            if (storageMap.containsKey(post.getId())) {
                storageMap.put(post.getId(), post);
            } else {
                return null;
            }
        }
        return post;
    }

    public void removeById(long id) {
        storageMap.remove(id);

    }
}
