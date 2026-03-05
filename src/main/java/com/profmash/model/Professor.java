package com.profmash.model;

public class Professor {
    private int id;
    private String name;
    private String department;
    private String subject;
    private String photoUrl;
    private double eloRating;
    private int wins;
    private int losses;
    private int totalVotes;

    // Default Elo rating
    public static final double DEFAULT_ELO = 1200.0;

    public Professor() {
        this.eloRating = DEFAULT_ELO;
    }

    public Professor(int id, String name, String department, String subject, String photoUrl) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.subject = subject;
        this.photoUrl = photoUrl;
        this.eloRating = DEFAULT_ELO;
        this.wins = 0;
        this.losses = 0;
        this.totalVotes = 0;
    }

    // Win rate as percentage
    public double getWinRate() {
        if (totalVotes == 0) return 0.0;
        return (wins * 100.0) / totalVotes;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public double getEloRating() { return eloRating; }
    public void setEloRating(double eloRating) { this.eloRating = eloRating; }

    public int getWins() { return wins; }
    public void setWins(int wins) { this.wins = wins; }

    public int getLosses() { return losses; }
    public void setLosses(int losses) { this.losses = losses; }

    public int getTotalVotes() { return totalVotes; }
    public void setTotalVotes(int totalVotes) { this.totalVotes = totalVotes; }

    @Override
    public String toString() {
        return "Professor{id=" + id + ", name='" + name + "', elo=" + String.format("%.0f", eloRating) + "}";
    }
}
