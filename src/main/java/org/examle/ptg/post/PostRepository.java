package org.examle.ptg.post;

import java.util.List;

public interface PostRepository {

    List<Post> getAllPosts();

    void addPost(Post post);

    void deleteById(String id);
}
