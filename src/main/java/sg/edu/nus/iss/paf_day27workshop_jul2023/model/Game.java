package sg.edu.nus.iss.paf_day27workshop_jul2023.model;

import java.util.List;

public record Game(Integer id,
                   String image,
                   String name,
                   Integer ranking,
                   String url,
                   Integer usersRated,
                   Integer year,
                   List<Comment> comments) {
}