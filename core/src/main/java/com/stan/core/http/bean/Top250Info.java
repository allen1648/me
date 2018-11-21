package com.stan.core.http.bean;

import java.util.List;

public class Top250Info {
    private int count;
    private int start;
    private int total;
    private List<MovieInfo> subjects;

    public List<MovieInfo> getMoviesInfo() {
        return subjects;
    }


    public static class MovieInfo {
        private String title;
        private String year;
        private Images images;
        private List<Director> directors;

        public String getTitle() {
            return title;
        }

        public String getYear() {
            return year;
        }

        public Images getImages() {
            return images;
        }

        public List<Director> getDirectors() {
            return directors;
        }
    }

    public static class Director {
        private String name;

        public String getName() {
            return name;
        }
    }

    public static class Images {
        private String small;
        private String medium;
        private String large;

        public String getSmall() {
            return small;
        }

        public String getMedium() {
            return medium;
        }

        public String getLarge() {
            return large;
        }
    }
}
