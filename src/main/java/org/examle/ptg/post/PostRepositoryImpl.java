package org.examle.ptg.post;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PostRepositoryImpl implements PostRepository {

    private List<Post> posts = new CopyOnWriteArrayList<>();

    @Override
    public List<Post> getAllPosts () {
        return posts;
    }

    @Override
    public void addPost (Post post) {
        posts.add(post);
    }

    @Override
    public void deleteById (String id) {
        posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .ifPresent(post -> posts.remove(post));
    }
}
