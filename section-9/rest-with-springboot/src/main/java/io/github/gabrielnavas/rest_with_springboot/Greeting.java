package io.github.gabrielnavas.rest_with_springboot;

public class Greeting {
    private final String id;
    private final String content;

    public Greeting() {
        this(null, null);
    }

    public Greeting(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
