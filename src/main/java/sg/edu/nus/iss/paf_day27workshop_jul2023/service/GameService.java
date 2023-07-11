package sg.edu.nus.iss.paf_day27workshop_jul2023.service;

import sg.edu.nus.iss.paf_day27workshop_jul2023.model.Game;
import sg.edu.nus.iss.paf_day27workshop_jul2023.repository.GameRepository;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private final GameRepository repo;
    private final CommentService service;
    public GameService(GameRepository repo, CommentService service) {
        this.repo = repo;
        this.service = service;
    }

    public List<Game> getGames(Integer limit, Integer skip) {
        return repo.getGames(limit, skip)
                .stream()
                .map(doc -> new Game(
                        doc.getInteger("gid"),
                        doc.getString("image"),
                        doc.getString("name"),
                        doc.getInteger("ranking"),
                        doc.getString("url"),
                        doc.getInteger("users_rated"),
                        doc.getInteger("year"),
                        service.getCommentsByGameId(doc.getInteger("gid"))
                ))
                .toList();
    }

    public Game getGameById(Integer id) {
        Document doc = repo.getGameById(id);
        if (doc == null) {
            return null;
        }
        return new Game(
                doc.getInteger("gid"),
                doc.getString("image"),
                doc.getString("name"),
                doc.getInteger("ranking"),
                doc.getString("url"),
                doc.getInteger("users_rated"),
                doc.getInteger("year"),
                service.getCommentsByGameId(doc.getInteger("gid"))
        );
    }

    public Game getGameByName(String name) {
        Document doc = repo.getGameByName(name);
        if (doc == null) {
            return null;
        }
        return new Game(
                doc.getInteger("gid"),
                doc.getString("image"),
                doc.getString("name"),
                doc.getInteger("ranking"),
                doc.getString("url"),
                doc.getInteger("users_rated"),
                doc.getInteger("year"),
                service.getCommentsByGameId(doc.getInteger("gid"))
        );
    }
}