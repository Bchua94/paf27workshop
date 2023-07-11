package sg.edu.nus.iss.paf_day27workshop_jul2023.service;

import sg.edu.nus.iss.paf_day27workshop_jul2023.model.Comment;
import sg.edu.nus.iss.paf_day27workshop_jul2023.repository.CommentRepository;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository repo;

    public CommentService(CommentRepository repo) {
        this.repo = repo;
    }

    public List<Comment> getCommentsByGameId(Integer id) {
        return repo.getCommentsByGameId(id)
                .stream()
                .map(doc -> new Comment(
                        doc.getString("c_id"),
                        doc.getString("c_text"),
                        doc.getInteger("gid"),
                        doc.getInteger("rating"),
                        doc.getString("user")
                ))
                .toList();
    }

    public Document createComment(Comment comment) {
        JsonObject obj = Json.createObjectBuilder()
                .add("c_id", comment.id())
                .add("c_text", comment.text())
                .add("gid", comment.gameId())
                .add("rating", comment.rating())
                .add("user", comment.user())
                .build();
        return repo.createComment(Document.parse(obj.toString()));
    }
}