package org.aom._01_demos._02_UsersFluxDemo;

public class User {
    private String name;
    private int age;
    private UserType type;

    public User(String name, int age, UserType type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    // Getters (to use in lambda printing below)
    public String getName() { return name; }
    public int getAge() { return age; }
    public UserType getType() { return type; }

    @Override
    public String toString() {
        return String.format("User{name='%s', age=%d, type=%s}", name, age, type);
    }
}