package sg.edu.nus.iss.paf_day27workshop_jul2023.repository;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameRepository {
    private final MongoTemplate template;
    public static final String C_GAME = "game";
    public static final String A_ID = "gid";
    public static final String A_NAME = "name";

    public GameRepository(MongoTemplate template) {
        this.template = template;
    }

    public List<Document> getGames(Integer limit, Integer skip) {
        return template.find(new Query().limit(limit).skip(skip), Document.class, C_GAME);
    }

    public Document getGameById(Integer id) {
        Criteria criteria = Criteria.where(A_ID).is(id);
        Query query = Query.query(criteria);
        return template.findOne(query, Document.class, C_GAME);
    }

    public Document getGameByName(String name) {
        Criteria criteria = Criteria.where(A_NAME).is(name);
        Query query = Query.query(criteria);
        return template.findOne(query, Document.class, C_GAME);
    }
}