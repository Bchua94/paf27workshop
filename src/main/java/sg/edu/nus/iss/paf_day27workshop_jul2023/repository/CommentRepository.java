package sg.edu.nus.iss.paf_day27workshop_jul2023.repository;

import org.bson.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository {
    private final MongoTemplate template;
    public static final String C_COMMENT = "comment";
    public static final String A_GAME_ID = "gid";
    public static final String A_RATING = "rating";

    public CommentRepository(MongoTemplate template) {
        this.template = template;
    }

    public List<Document> getCommentsByGameId(Integer id) {
        Criteria criteria = Criteria.where(A_GAME_ID).is(id);
        Query query = Query.query(criteria).with(Sort.by(Sort.Direction.DESC, A_RATING));
        return template.find(query, Document.class, C_COMMENT);
    }

    public Document createComment(Document document) {
        return template.insert(document, C_COMMENT);
    }

}