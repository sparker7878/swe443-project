package net.codejava;

public class Restaurant {
    private String name;
    private boolean liked;
    private boolean selected;

    public Restaurant(String name) {
        this.name = name;
        this.liked = false;
        this.selected = false;
    }

    public Restaurant() {
        this.liked = false;
        this.selected = false;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLiked() {
        return this.liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isSelected() { return this.selected; }

    public void setSelected(boolean selected) { this.selected = selected; }

    @Override
    public String toString() {
        return this.name;
    }
}
