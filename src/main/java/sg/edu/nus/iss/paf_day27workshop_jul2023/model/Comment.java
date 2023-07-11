package sg.edu.nus.iss.paf_day27workshop_jul2023.model;


public record Comment(String id,
                      String text,
                      Integer gameId,
                      Integer rating,
                      String user) {
                      }