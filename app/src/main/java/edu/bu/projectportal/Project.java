package edu.bu.projectportal;



public class Project {
    private int id;
    private String title;
    private String summary;
    private String author;
    private String keywords;
    private boolean isFavorite;
    private String github;
    private String youtube;


    public Project(String title, String summary,String author,
                   String keywords,  boolean isFavorite,  String github,  String youtube) {
        this.title = title;
        this.summary = summary;
        this.author = author;
        this.keywords = keywords;
        this.isFavorite = isFavorite;
        this.github = github;
        this.youtube = youtube;
    }

    public Project(int id, String title, String summary, String author,
                   String keywords,  boolean isFavorite,  String github,  String youtube) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.author = author;
        this.keywords = keywords;
        this.isFavorite = isFavorite;
        this.github = github;
        this.youtube = youtube;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getGithub() {return github;}

    public void setGithub(String github) {
        this.github = github;
    }

    public String getYoutube() {return youtube;}

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

}
