package sg.edu.nus.iss.paf_day27workshop_jul2023.controller;

import sg.edu.nus.iss.paf_day27workshop_jul2023.model.Comment;
import sg.edu.nus.iss.paf_day27workshop_jul2023.model.Game;
import sg.edu.nus.iss.paf_day27workshop_jul2023.service.CommentService;
import sg.edu.nus.iss.paf_day27workshop_jul2023.service.GameService;
import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class MainController {
    private final GameService gameService;
    private final CommentService commentService;

    public MainController(GameService gameService, CommentService commentService) {
        this.gameService = gameService;
        this.commentService = commentService;
    }

    @GetMapping
    public ModelAndView getLanding() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("landing");
        mav.addObject("games", gameService.getGames(10, 0));
        mav.setStatus(HttpStatus.OK);
        return mav;
    }

    @GetMapping("/search")
    public ModelAndView getSearchForGame(@RequestParam String name) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("landing");
        Game game = gameService.getGameByName(name);

        if (game == null) {
            mav.addObject("message", "No games found!");
            mav.setStatus(HttpStatus.NOT_FOUND);
            return mav;
        }
        mav.addObject("game", game);
        mav.setStatus(HttpStatus.OK);
        return mav;
    }

    @GetMapping("/game/{id}")
    public ModelAndView getSearchForGame(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("game");
        mav.addObject("game", gameService.getGameById(id));
        mav.setStatus(HttpStatus.OK);
        return mav;
    }

    @PostMapping("/game/{id}/new-comment")
    public ModelAndView postComment(@PathVariable Integer id, @ModelAttribute Comment comment) {
        Comment newComment = new Comment("test", comment.text(), id, comment.rating(), comment.user());
        Document result = commentService.createComment(newComment);
        if (result == null) {
            return null;
        }
        return new ModelAndView("redirect:/game/{id}");
    }
}